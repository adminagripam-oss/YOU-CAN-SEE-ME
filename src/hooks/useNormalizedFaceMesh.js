import { useEffect, useRef, useCallback } from 'react';

// ═══════════════════════════════════════════════════════════════════════════
// useNormalizedFaceMesh
// ─────────────────────────────────────────────────────────────────────────
// Custom React Hook untuk pipeline Face Mesh yang device-independent.
//
// Pipeline:
//   1. Standardisasi input video (getUserMedia 640×480)
//   2. Center Crop 4:3 ke offscreen canvas (eliminasi distorsi aspect ratio)
//   3. Deteksi via injected `detectFaces` callback (Human / MediaPipe)
//   4. EMA Smoothing per-koordinat (x, y, z) untuk meredam jitter
//   5. Normalisasi Bounding Box → Local Coordinates (origin di tengah wajah)
//   6. requestAnimationFrame loop yang cleanup-safe saat unmount
// ═══════════════════════════════════════════════════════════════════════════

// ─────────────────────────────────────────────────────────────────────────
// CONSTANTS
// ─────────────────────────────────────────────────────────────────────────

/** Resolusi standar referensi canvas (device-independent target) */
const STD_WIDTH = 640;
const STD_HEIGHT = 480;

/** Target aspect ratio 4:3 — sama di semua device */
const TARGET_ASPECT = 4 / 3;

// ─────────────────────────────────────────────────────────────────────────
// HELPER: Center Crop ke 4:3
// ─────────────────────────────────────────────────────────────────────────

/**
 * Menghitung region source video yang harus di-crop agar menghasilkan
 * frame 4:3 tanpa distorsi/stretching, terlepas dari resolusi kamera fisik.
 *
 * @param {number} videoWidth  - Lebar resolusi kamera aktual
 * @param {number} videoHeight - Tinggi resolusi kamera aktual
 * @returns {{ srcX, srcY, srcW, srcH }} Region crop pada video sumber
 */
function computeCenterCrop(videoWidth, videoHeight) {
  const videoAspect = videoWidth / videoHeight;

  let srcW = videoWidth;
  let srcH = videoHeight;
  let srcX = 0;
  let srcY = 0;

  if (videoAspect > TARGET_ASPECT) {
    // Video lebih lebar dari 4:3 (misal 16:9) → crop sisi kiri-kanan
    srcW = Math.round(videoHeight * TARGET_ASPECT);
    srcX = Math.round((videoWidth - srcW) / 2);
  } else if (videoAspect < TARGET_ASPECT) {
    // Video lebih tinggi dari 4:3 → crop atas-bawah
    srcH = Math.round(videoWidth / TARGET_ASPECT);
    srcY = Math.round((videoHeight - srcH) / 2);
  }
  // Jika sudah 4:3 persis → tidak ada crop

  return { srcX, srcY, srcW, srcH };
}

// ─────────────────────────────────────────────────────────────────────────
// HELPER: Hitung Bounding Box dari kumpulan landmark
// ─────────────────────────────────────────────────────────────────────────

/**
 * Menghitung Bounding Box minimal dari array landmark.
 * Landmark bisa berupa [x, y] array atau { x, y } object.
 *
 * @param {Array} landmarks - Array of [x,y] atau {x,y}
 * @returns {{ minX, minY, maxX, maxY, width, height, centerX, centerY }}
 */
function computeBoundingBox(landmarks) {
  let minX = Infinity, minY = Infinity;
  let maxX = -Infinity, maxY = -Infinity;

  for (const p of landmarks) {
    // Support both array [x,y] and object {x,y} format dari Human.js
    const px = Array.isArray(p) ? p[0] : p.x;
    const py = Array.isArray(p) ? p[1] : p.y;
    if (px < minX) minX = px;
    if (py < minY) minY = py;
    if (px > maxX) maxX = px;
    if (py > maxY) maxY = py;
  }

  const width = maxX - minX;
  const height = maxY - minY;

  return {
    minX, minY, maxX, maxY,
    width, height,
    centerX: minX + width / 2,
    centerY: minY + height / 2,
  };
}

// ─────────────────────────────────────────────────────────────────────────
// HELPER: EMA Smoothing satu frame
// ─────────────────────────────────────────────────────────────────────────

/**
 * Menerapkan Exponential Moving Average pada setiap koordinat landmark.
 * Hasil: getaran/jitter dikurangi tanpa menambah latency berlebihan.
 *
 * Formula EMA:
 *   smoothed[t] = alpha * raw[t] + (1 - alpha) * smoothed[t-1]
 *
 * @param {Array}  rawLandmarks      - Array landmark frame saat ini
 * @param {Array|null} prevSmoothed  - Array landmark smoothed frame sebelumnya
 * @param {number} alpha             - Bobot frame baru (0.0 = no update, 1.0 = no smoothing)
 * @returns {Array} Landmark yang sudah di-smooth
 */
function applyEMASmoothing(rawLandmarks, prevSmoothed, alpha) {
  // Frame pertama: tidak ada referensi sebelumnya → gunakan raw langsung
  if (!prevSmoothed || prevSmoothed.length !== rawLandmarks.length) {
    return rawLandmarks;
  }

  return rawLandmarks.map((pt, i) => {
    const prev = prevSmoothed[i];

    // Support both array [x,y,z] dan object {x,y,z} dari Human.js
    if (Array.isArray(pt)) {
      return [
        alpha * pt[0] + (1 - alpha) * (prev[0] ?? pt[0]),
        alpha * pt[1] + (1 - alpha) * (prev[1] ?? pt[1]),
        alpha * (pt[2] ?? 0) + (1 - alpha) * (prev[2] ?? 0),
      ];
    } else {
      return {
        x: alpha * pt.x + (1 - alpha) * (prev.x ?? pt.x),
        y: alpha * pt.y + (1 - alpha) * (prev.y ?? pt.y),
        z: alpha * (pt.z ?? 0) + (1 - alpha) * (prev.z ?? 0),
      };
    }
  });
}

// ─────────────────────────────────────────────────────────────────────────
// HELPER: Normalisasi koordinat landmark ke Local Coordinates
// ─────────────────────────────────────────────────────────────────────────

/**
 * Mengkonversi koordinat landmark (dalam satuan piksel canvas) menjadi
 * koordinat lokal yang ternormalisasi berbasis Bounding Box wajah.
 *
 * Sistem koordinat lokal:
 *   - Pusat bounding box = Origin (0, 0)
 *   - Rentang X: -0.5 (kiri wajah) hingga +0.5 (kanan wajah)
 *   - Rentang Y: -0.5 (atas wajah) hingga +0.5 (bawah wajah)
 *   - Z: dinormalisasi relatif terhadap lebar wajah
 *
 * Keunggulan: Koordinat ini invariant terhadap:
 *   ✓ Ukuran piksel canvas (resolusi device berbeda)
 *   ✓ Jarak wajah ke kamera (scale)
 *   ✓ Posisi wajah di frame
 *
 * @param {Array}  landmarks - Landmark yang sudah di-smooth
 * @param {{ centerX, centerY, width, height }} bbox - Bounding Box wajah
 * @returns {Array} Landmark dengan tambahan property { localX, localY, localZ }
 */
function normalizeToLocalCoords(landmarks, bbox) {
  const { centerX, centerY, width, height } = bbox;
  const scaleX = width > 0 ? width : 1;
  const scaleY = height > 0 ? height : 1;

  return landmarks.map((pt) => {
    const px = Array.isArray(pt) ? pt[0] : pt.x;
    const py = Array.isArray(pt) ? pt[1] : pt.y;
    const pz = Array.isArray(pt) ? (pt[2] ?? 0) : (pt.z ?? 0);

    return {
      // Koordinat piksel asli (untuk rendering ke canvas)
      rawX: px,
      rawY: py,
      rawZ: pz,
      // Koordinat lokal ternormalisasi (untuk matching & analitik geometri)
      localX: (px - centerX) / scaleX,
      localY: (py - centerY) / scaleY,
      localZ: pz / scaleX, // Z diskalakan relatif lebar wajah
    };
  });
}

// ═══════════════════════════════════════════════════════════════════════════
// MAIN HOOK: useNormalizedFaceMesh
// ═══════════════════════════════════════════════════════════════════════════

/**
 * Hook untuk menjalankan pipeline face mesh detection yang device-independent.
 *
 * @param {Object} config
 * @param {React.RefObject<HTMLVideoElement>}  config.videoRef      - Ref ke elemen video
 * @param {React.RefObject<HTMLCanvasElement>} config.canvasRef     - Ref ke canvas overlay
 * @param {boolean}                            config.active        - Aktifkan/matikan loop (misal saat modelsLoaded)
 * @param {string}                             config.facingMode    - 'user' atau 'environment'
 * @param {number}                             config.smoothAlpha   - EMA alpha (0.1-0.9, default 0.4)
 * @param {(canvas: HTMLCanvasElement, timestamp: number) => any} config.detectFaces
 *   Injected function untuk menjalankan model AI (Human.js/MediaPipe).
 *   Harus menerima canvas (sudah ter-crop ke 640×480) dan timestamp rAF.
 *   Harus mengembalikan hasil deteksi (format apapun dari model).
 * @param {(result: { detection, smoothedMesh, normalizedMesh, boundingBox, ctx }) => void} config.onFaceProcessed
 *   Callback setiap frame berhasil diproses. Berisi semua data yang dibutuhkan komponen.
 * @param {() => void} config.onNoFace
 *   Callback ketika tidak ada wajah terdeteksi pada frame.
 * @param {(err: Error) => void} config.onCameraError
 *   Callback ketika kamera gagal diakses.
 */
export function useNormalizedFaceMesh({
  videoRef,
  canvasRef,
  active = true,
  facingMode = 'user',
  smoothAlpha = 0.4,
  detectFaces,
  onFaceProcessed,
  onNoFace,
  onCameraError,
}) {
  // Ref untuk state internal hook — tidak trigger re-render
  const rafRef       = useRef(null);   // requestAnimationFrame ID
  const smoothedRef  = useRef(null);   // Landmark dari frame sebelumnya (untuk EMA)
  const isRunningRef = useRef(false);  // Guard: apakah loop sedang berjalan
  const streamRef    = useRef(null);   // MediaStream agar bisa di-stop saat unmount

  // Offscreen canvas untuk center-crop (dibuat sekali, di-reuse setiap frame)
  const offscreenRef = useRef(null);

  // Wrap callbacks ke ref agar tidak menyebabkan effect re-run
  const detectFacesRef      = useRef(detectFaces);
  const onFaceProcessedRef  = useRef(onFaceProcessed);
  const onNoFaceRef         = useRef(onNoFace);
  const onCameraErrorRef    = useRef(onCameraError);

  // Sync callback refs setiap render
  useEffect(() => { detectFacesRef.current     = detectFaces; },     [detectFaces]);
  useEffect(() => { onFaceProcessedRef.current  = onFaceProcessed; }, [onFaceProcessed]);
  useEffect(() => { onNoFaceRef.current         = onNoFace; },        [onNoFace]);
  useEffect(() => { onCameraErrorRef.current    = onCameraError; },   [onCameraError]);

  // ── Core Frame Processing ──────────────────────────────────────────────

  /**
   * Dijalankan setiap frame via requestAnimationFrame.
   * Bertanggung jawab untuk:
   *   1. Center-crop video ke offscreen canvas 640×480
   *   2. Panggil detectFaces (injected model)
   *   3. EMA smoothing pada mesh landmark
   *   4. Normalisasi local coordinates
   *   5. Panggil onFaceProcessed callback
   */
  const processFrame = useCallback(async (timestamp) => {
    if (!isRunningRef.current) return;

    const video  = videoRef.current;
    const canvas = canvasRef.current;

    // Pastikan semua elemen siap sebelum proses
    if (video && canvas && video.readyState >= 2 && video.videoWidth > 0) {

      // ── 1. Pastikan offscreen canvas ada & berukuran standar ──────────
      if (!offscreenRef.current) {
        offscreenRef.current = document.createElement('canvas');
      }
      const offscreen = offscreenRef.current;
      if (offscreen.width !== STD_WIDTH || offscreen.height !== STD_HEIGHT) {
        offscreen.width  = STD_WIDTH;
        offscreen.height = STD_HEIGHT;
      }

      const offCtx = offscreen.getContext('2d', { willReadFrequently: false });

      // ── 2. Center Crop: ambil region 4:3 dari video asli ─────────────
      const { srcX, srcY, srcW, srcH } = computeCenterCrop(
        video.videoWidth,
        video.videoHeight
      );

      // Gambar frame video (ter-crop) ke offscreen canvas berukuran 640×480
      offCtx.drawImage(video, srcX, srcY, srcW, srcH, 0, 0, STD_WIDTH, STD_HEIGHT);

      // ── 3. Panggil model AI via injected function ─────────────────────
      let detection = null;
      try {
        detection = await detectFacesRef.current?.(offscreen, timestamp);
      } catch (err) {
        console.warn('[useNormalizedFaceMesh] detectFaces error:', err);
      }

      // ── 4. Sinkronkan canvas overlay ke resolusi standar ─────────────
      if (canvas.width !== STD_WIDTH)  canvas.width  = STD_WIDTH;
      if (canvas.height !== STD_HEIGHT) canvas.height = STD_HEIGHT;

      const ctx = canvas.getContext('2d');

      if (!detection || !detection.mesh || detection.mesh.length === 0) {
        // Tidak ada wajah — bersihkan canvas & panggil callback
        if (ctx) ctx.clearRect(0, 0, STD_WIDTH, STD_HEIGHT);
        smoothedRef.current = null; // Reset EMA state saat wajah hilang
        onNoFaceRef.current?.();
      } else {
        // ── 5. EMA Smoothing per-koordinat landmark ───────────────────
        const rawMesh = detection.mesh;
        const smoothedMesh = applyEMASmoothing(rawMesh, smoothedRef.current, smoothAlpha);
        smoothedRef.current = smoothedMesh; // Simpan untuk frame berikutnya

        // ── 6. Hitung Bounding Box dari mesh yang sudah di-smooth ─────
        const bbox = computeBoundingBox(smoothedMesh);

        // ── 7. Normalisasi ke Local Coordinates (device-independent) ──
        const normalizedMesh = normalizeToLocalCoords(smoothedMesh, bbox);

        // ── 8. Panggil callback dengan semua data ─────────────────────
        onFaceProcessedRef.current?.({
          detection,       // Raw detection result dari model (termasuk .embedding, .rotation, .score)
          smoothedMesh,    // Landmark ter-smooth dalam koordinat piksel canvas 640×480
          normalizedMesh,  // Landmark ternormalisasi (localX, localY, localZ, rawX, rawY, rawZ)
          boundingBox: bbox,
          ctx,             // Canvas context untuk rendering overlay
        });
      }
    }

    // ── Loop ke frame berikutnya ───────────────────────────────────────
    if (isRunningRef.current) {
      rafRef.current = requestAnimationFrame(processFrame);
    }
  }, [smoothAlpha, videoRef, canvasRef]);

  // ── Camera Setup + Loop Lifecycle ─────────────────────────────────────

  useEffect(() => {
    if (!active) return; // Tidak jalankan jika belum aktif (misal modelsLoaded = false)

    let cancelled = false;

    async function startCamera() {
      try {
        // Minta stream dengan constraint resolusi ideal 640×480
        const stream = await navigator.mediaDevices.getUserMedia({
          video: {
            width:      { ideal: STD_WIDTH },
            height:     { ideal: STD_HEIGHT },
            facingMode: facingMode,
          },
          audio: false,
        });

        if (cancelled) {
          // Effect sudah di-cleanup sebelum kamera selesai buka → stop langsung
          stream.getTracks().forEach(t => t.stop());
          return;
        }

        streamRef.current = stream;
        if (videoRef.current) {
          videoRef.current.srcObject = stream;
          // Tunggu video siap sebelum mulai loop
          await videoRef.current.play().catch(() => {}); // Ignore autoplay error
        }

        // Mulai rendering loop
        isRunningRef.current = true;
        rafRef.current = requestAnimationFrame(processFrame);

      } catch (err) {
        console.error('[useNormalizedFaceMesh] Camera error:', err);
        onCameraErrorRef.current?.(err);
      }
    }

    startCamera();

    // Cleanup: stop stream, cancel rAF saat unmount / dependency berubah
    return () => {
      cancelled = true;
      isRunningRef.current = false;

      if (rafRef.current) {
        cancelAnimationFrame(rafRef.current);
        rafRef.current = null;
      }

      if (streamRef.current) {
        streamRef.current.getTracks().forEach(t => t.stop());
        streamRef.current = null;
      }

      smoothedRef.current = null; // Reset EMA state
    };
  }, [active, facingMode, processFrame, videoRef]);
  // Note: processFrame ada di deps karena di-memo dengan useCallback.
  // Ia hanya berubah jika smoothAlpha berubah, yang jarang terjadi.
}
