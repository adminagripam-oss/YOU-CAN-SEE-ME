/**
 * humanSingleton.js
 *
 * Singleton instance of @vladmandic/human untuk seluruh aplikasi AgriFace.
 * WAJIB hanya ada SATU instance Human yang aktif di waktu bersamaan.
 * Deteksi backend otomatis: WebGL (GPU) → WASM (CPU) fallback untuk kompatibilitas Android.
 */

import { Human } from '@vladmandic/human';

const humanConfig = {
  // Jalur model absolut berbasis origin domain saat ini
  modelBasePath: window.location.origin + '/models',

  face: {
    enabled: true,
    detector: {
      enabled: true,
      rotation: true,
      maxDetected: 1,
      skipFrames: 0,        // Tidak lewatkan satupun frame
      minConfidence: 0.15,  // Threshold rendah agar deteksi lebih sensitif
    },
    mesh: { enabled: true },
    iris: { enabled: false },
    description: { enabled: true },
  },

  body: { enabled: false },
  hand: { enabled: false },
  object: { enabled: false },
  gesture: { enabled: false },

  // Backend akan di-set secara otomatis oleh loadHumanWithFallback
  backend: 'webgl',
  warmup: 'none', // Warmup dilakukan manual setelah backend terverifikasi
};

// SATU-SATUNYA instance Human di seluruh aplikasi
export const human = new Human(humanConfig);

/**
 * Deteksi backend GPU terbaik yang didukung oleh device:
 * 1. WebGL  (GPU hardware acceleration)
 * 2. WASM   (CPU SIMD — kompatibel semua Android)
 */
async function detectBestBackend() {
  // Tes apakah WebGL benar-benar dapat menjalankan shader TF.js
  try {
    const canvas = document.createElement('canvas');
    const gl = canvas.getContext('webgl2') || canvas.getContext('webgl');
    if (!gl) throw new Error('WebGL not available');
    // Tes kemampuan float texture — wajib untuk TF.js inference
    const floatExt = gl.getExtension('OES_texture_float') || gl.getExtension('EXT_color_buffer_float');
    if (!floatExt) throw new Error('WebGL float texture not supported');
    console.log('[Human Backend] WebGL OK — menggunakan GPU acceleration');
    return 'webgl';
  } catch (e) {
    console.warn('[Human Backend] WebGL gagal, fallback ke WASM CPU:', e.message);
    return 'wasm';
  }
}

/**
 * Pemuatan model tangguh (Backend Auto-Detection + 3-Tier Path Fallback):
 * 1. Deteksi backend terbaik (WebGL atau WASM)
 * 2. Coba path: Origin Domain → Relatif → Cloud CDN
 * 3. Warmup deteksi wajah pertama kali
 */
export async function loadHumanWithFallback() {
  // Step 1: Pilih backend terbaik untuk device ini
  const bestBackend = await detectBestBackend();
  human.config.backend = bestBackend;
  console.log('[Human Singleton] Backend ditetapkan:', bestBackend);

  const pathsToTry = [
    window.location.origin + '/models',
    './models',
    'https://cdn.jsdelivr.net/npm/@vladmandic/human/models'
  ];

  let lastErr = null;
  let loadedPath = null;

  // Step 2: Load model dari path yang berhasil
  for (const basePath of pathsToTry) {
    try {
      console.log('[Human Singleton] Mencoba load model dari:', basePath);
      human.config.modelBasePath = basePath;
      await human.load();
      loadedPath = basePath;
      console.log('[Human Singleton] Model berhasil dimuat dari:', basePath);
      break;
    } catch (err) {
      console.warn('[Human Singleton] Gagal dari:', basePath, err?.message || err);
      lastErr = err;
    }
  }

  if (!loadedPath) {
    console.error('[Human Singleton] Semua path gagal. Error terakhir:', lastErr);
    throw lastErr || new Error('Gagal mengunduh model AI dari seluruh lokasi');
  }

  // Step 3: Warmup — jalankan deteksi pada frame kosong agar GPU shader ter-compile
  try {
    console.log('[Human Singleton] Memulai warmup deteksi wajah...');
    // Buat canvas 64x64 piksel untuk warmup ringan
    const warmupCanvas = document.createElement('canvas');
    warmupCanvas.width = 64;
    warmupCanvas.height = 64;
    const warmupCtx = warmupCanvas.getContext('2d');
    if (warmupCtx) {
      warmupCtx.fillStyle = '#888888';
      warmupCtx.fillRect(0, 0, 64, 64);
    }
    await human.detect(warmupCanvas);
    console.log('[Human Singleton] Warmup berhasil — model siap mendeteksi wajah!');
  } catch (warnErr) {
    console.warn('[Human Singleton] Warmup error (non-fatal):', warnErr?.message);
  }

  return true;
}
