import React, { useState, useEffect, useRef, useCallback } from 'react';
import { API_BASE_URL, fetchWithTimeout } from '../config';
import { supabase } from '../supabaseClient';
import { cacheUserMasterVector, cacheGeometricVector, getAllMasterVectors, cosineSimilarity, toVectorArray, queueScanAttempt, saveEmployeeFaceThumb } from '../db';
import { buildTemplateDetailed, BIOMETRIC_CONFIG, vectorChecksum, createEnrollmentCollector, selectEnrollmentFrames } from '../biometrics';
import { captureEnrollmentPhoto } from '../utils/enrollmentPhoto';
import { useNormalizedFaceMesh } from '../hooks/useNormalizedFaceMesh';
import { human } from '../humanSingleton';
import { useAuth } from '../context/AuthContext';
import { Capacitor } from '@capacitor/core';
import { checkOnline } from '../syncEngine';
import { FaceFrameOverlay, getFrameRect, evaluateFaceInFrame, FRAME_HINTS } from '../FaceFrameTemplate';
import { useScanViewport } from '../hooks/useScanViewport';
import FullscreenScanShell from './FullscreenScanShell';
import FaceScanSurface from './FaceScanSurface';

export default function TabEmployeeManagement({
  employees,
  modelsLoaded,
  showToast,
  refreshEmployees,
  openConfirmModal,
}) {
  const { user } = useAuth();
  const { useFullscreen, isPortrait, canvasSize } = useScanViewport();

  // Face Frame Geometry Refs
  const regFrameStateRef = useRef({ state: 'idle', progress: 0 });
  const [regScannerOpen, setRegScannerOpen] = useState(false);
  const frameRectRef = useRef(getFrameRect(canvasSize.width, canvasSize.height, isPortrait));

  useEffect(() => {
    frameRectRef.current = getFrameRect(canvasSize.width, canvasSize.height, isPortrait);
  }, [canvasSize, isPortrait]);

  // Form State
  const [empNik, setEmpNik] = useState('');
  const [empName, setEmpName] = useState('');
  const [empAfdeling, setEmpAfdeling] = useState('');
  const [empNamaKebun, setEmpNamaKebun] = useState('');

  // Prefill kebun name for estate admins
  useEffect(() => {
    if (user && user.role === 'estate_admin' && user.kebun) {
      setEmpNamaKebun(user.kebun);
    }
  }, [user]);
  const [empStatusTk, setEmpStatusTk] = useState('');
  const [empStatusTkCustom, setEmpStatusTkCustom] = useState('');
  const [empJabatan, setEmpJabatan] = useState('');
  const [empStatusPerkawinan, setEmpStatusPerkawinan] = useState('');
  const [formStep, setFormStep] = useState(1); // 1 = Data Karyawan, 2 = Biometrik
  const [cameraFacingMode, setCameraFacingMode] = useState('environment');
  const [empFormMode, setEmpFormMode] = useState('camera'); // 'camera' | 'file'
  const [cameraStatusText, setCameraStatusText] = useState('Menunggu Wajah di Kamera...');
  const [cameraStatusColor, setCameraStatusColor] = useState('var(--accent-warning)');
  const [photoPreview, setPhotoPreview] = useState(null);
  const [photoFileName, setPhotoFileName] = useState('Format: JPG, PNG, WEBP (Pastikan 1 Wajah Terlihat Jelas)');
  const [isSubmitting, setIsSubmitting] = useState(false);
  // null = belum dicek | { isDuplicate, matchedName, matchedNik, similarity } = hasil cek
  const [faceCheckResult, setFaceCheckResult] = useState(null);
  const [regFailCount, setRegFailCount] = useState(0);
  const [regMode, setRegMode] = useState('multi_pose');
  const currentEmpDescriptorRef = useRef(null);
  const enrollTemplateVersionRef = useRef(null);
  const descriptorHistoryRef = useRef([]);
  const enrollmentCollectorRef = useRef(createEnrollmentCollector());
  const currentEmpGFVRef = useRef(null); // 40-d Geometric Feature Vector
  const enrollmentThumbRef = useRef(null);
  const regVideoRef = useRef(null);
  const regCanvasRef = useRef(null);
  const fileInputRef = useRef(null);

  const detectRegFacesCallback = useCallback(async (croppedCanvas) => {
    if (!modelsLoaded) return null;
    const result = await human.detect(croppedCanvas);
    return result?.face?.[0] ?? null;
  }, [modelsLoaded]);

  // Callback saat wajah diproses di register mode
  const onRegFaceProcessed = useCallback(({ detection, smoothedMesh, ctx, boundingBox }) => {
    const frameCheck = evaluateFaceInFrame(boundingBox, frameRectRef.current);
    if (!frameCheck.ok) {
      regFrameStateRef.current = { state: 'misaligned', progress: 0 };
      setCameraStatusText(FRAME_HINTS[frameCheck.reason]);
      setCameraStatusColor('var(--accent-warning)');
    } else {
      regFrameStateRef.current = { state: 'scanning', progress: 0.5 };
    }

    if (detection?.embedding && frameCheck.ok && !photoPreview) {
      const now = performance.now();
      const res = selectEnrollmentFrames(enrollmentCollectorRef.current, detection, now);

      if (res.status === 'paused') {
        setCameraStatusText(res.message || 'Arahkan wajah ke dalam bingkai...');
        setCameraStatusColor('var(--accent-warning)');
      } else if (res.status === 'collecting') {
        setCameraStatusText(res.promptText || `Mengambil sampel ${res.frameCount}/${res.targetFrames}`);
        setCameraStatusColor('var(--accent-warning)');
        regFrameStateRef.current = { state: 'scanning', progress: res.progress };
      } else if (res.status === 'low_consistency' || res.status === 'timeout') {
        const failMsg = res.message || (res.status === 'timeout' ? 'Waktu pendaftaran habis. Mengulang...' : 'Wajah terlalu banyak bergerak. Mengulang...');
        setCameraStatusText(failMsg);
        setCameraStatusColor('var(--accent-error)');
        setRegFailCount(prev => prev + 1);
        queueScanAttempt({
          attempt_type: 'ENROLL',
          outcome: 'FAILED',
          max_cosine: null,
          frames_used: res.framesCount || enrollmentCollectorRef.current.frames.length,
          threshold_used: BIOMETRIC_CONFIG.ENROLL_MIN_CONSISTENCY,
          template_version: BIOMETRIC_CONFIG.TEMPLATE_VERSION
        }).catch(() => {});
        enrollmentCollectorRef.current = createEnrollmentCollector(regMode);
      } else if (res.status === 'complete') {
        setRegFailCount(0);
        const templateResult = {
          vector: res.vector,
          consistency: res.consistency,
          template_version: res.template_version || 1
        };
        queueScanAttempt({
          attempt_type: 'ENROLL',
          outcome: 'SUCCESS',
          max_cosine: parseFloat(res.consistency.toFixed(4)),
          frames_used: res.frames.length,
          threshold_used: BIOMETRIC_CONFIG.ENROLL_MIN_CONSISTENCY,
          template_version: res.template_version
        }).catch(() => {});

        currentEmpDescriptorRef.current = Array.from(templateResult.vector);
        enrollTemplateVersionRef.current = templateResult.template_version || (regMode === 'frontal_only' ? 1 : 2);
        currentEmpGFVRef.current = [];

        // Capture face thumbnail for local cache & preview
        const thumb = captureEnrollmentPhoto(regVideoRef.current, boundingBox, cameraFacingMode);
        if (thumb?.base64) {
          enrollmentThumbRef.current = thumb.base64;
        }

        // Cek duplikasi wajah secara real-time (async, non-blocking)
      (async () => {
        try {
          const allMasters = await getAllMasterVectors();
          let bestSim = 0, bestName = '', bestNik = '';
          const currentVec = templateResult.vector;
          if ((!allMasters || allMasters.length === 0) && !navigator.onLine) {
            console.warn('[TabEmployeeManagement] Database master lokal kosong saat offline. Melanjutkan.');
          }
          for (const m of (allMasters || [])) {
            const vec = toVectorArray(m.descriptor_json);
            if (!vec) continue;
            const sim = cosineSimilarity(currentVec, vec);
            if (sim > bestSim) { bestSim = sim; bestName = m.name; bestNik = m.nik; }
          }
          if (bestSim >= BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD) {
            setFaceCheckResult({ isDuplicate: true, matchedName: bestName, matchedNik: bestNik, similarity: bestSim });
            setCameraStatusText(`⚠️ WAJAH SUDAH TERDAFTAR: ${bestName} (${(bestSim * 100).toFixed(1)}%). Jika saudara kandung / keliru, laporkan ke mandor.`);
            setCameraStatusColor('var(--accent-error)');
            queueScanAttempt({
              attempt_type: 'ENROLL-DUPLICATE',
              matched_cosine: parseFloat(bestSim.toFixed(4)),
              threshold_used: BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD,
              template_version: enrollTemplateVersionRef.current || 2,
              outcome: 'NOT_MATCHED'
            }).catch(() => {});
          } else {
            setFaceCheckResult({ isDuplicate: false, matchedName: '', matchedNik: '', similarity: bestSim });
            const offlineNote = ((!allMasters || allMasters.length === 0) && !navigator.onLine)
              ? ' (Pemeriksaan duplikasi hanya terhadap data di perangkat ini)'
              : '';
            setCameraStatusText(`✓ Wajah Baru Terdeteksi (Valid, Belum Terdaftar)${offlineNote}`);
            setCameraStatusColor('var(--accent-success)');
            regFrameStateRef.current = { state: 'success', progress: 1 };
            if (useFullscreen) {
              setTimeout(() => {
                setRegScannerOpen(false);
              }, 1500);
            }
          }
        } catch (_) {
          // fallback: jika tidak bisa cek, tetap izinkan
          setCameraStatusText(`✓ Biometrik Wajah Master Terdeteksi [1024-dim Human]`);
          setCameraStatusColor('var(--accent-success)');
          setFaceCheckResult(null);
          regFrameStateRef.current = { state: 'success', progress: 1 };
          if (useFullscreen) {
            setTimeout(() => {
              setRegScannerOpen(false);
            }, 1500);
          }
        }
      })();
    }
  }

    // Menggambar 478 Mesh Points ke canvas overlay
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
    if (smoothedMesh && smoothedMesh.length > 0) {
      ctx.fillStyle = 'rgba(0, 255, 0, 0.85)';
      for (const pt of smoothedMesh) {
        if (!pt) continue;
        const px = Array.isArray(pt) ? (pt[0] ?? 0) : (pt.x ?? 0);
        const py = Array.isArray(pt) ? (pt[1] ?? 0) : (pt.y ?? 0);
        ctx.beginPath();
        ctx.arc(px, py, 2.5, 0, 2 * Math.PI);
        ctx.fill();
      }
    }
  }, [useFullscreen]);

  // Callback saat tidak ada wajah di register mode
  const onRegNoFace = useCallback(() => {
    if (enrollmentCollectorRef.current) {
      selectEnrollmentFrames(enrollmentCollectorRef.current, null, performance.now());
    }
    setFaceCheckResult(null);
    regFrameStateRef.current = { state: 'idle', progress: 0 };
    setCameraStatusText('Arahkan wajah ke dalam bingkai...');
    setCameraStatusColor('var(--accent-warning)');
  }, []);

  // Callback saat kamera gagal dibuka di register mode
  const onRegCameraError = useCallback((err) => {
    console.error('[REG CAMERA ERROR]:', err);
    setCameraStatusText('Gagal Membuka Kamera: ' + err.message);
    setCameraStatusColor('var(--accent-error)');
  }, []);

  // Hubungkan ke useNormalizedFaceMesh hook
  useNormalizedFaceMesh({
    videoRef: regVideoRef,
    canvasRef: regCanvasRef,
    active: modelsLoaded && empFormMode === 'camera' && formStep === 2 && (!useFullscreen || regScannerOpen),
    facingMode: cameraFacingMode,
    smoothAlpha: 0.35,
    detectFaces: detectRegFacesCallback,
    onFaceProcessed: onRegFaceProcessed,
    onNoFace: onRegNoFace,
    onCameraError: onRegCameraError,
    canvasSize,
  });


  // Handle Photo File Upload
  const handlePhotoFileUpload = (e) => {
    const file = e.target.files[0];
    if (!file) return;

    setPhotoFileName(`File: ${file.name} (${(file.size / 1024).toFixed(1)} KB)`);
    const reader = new FileReader();

    reader.onload = (event) => {
      const imgUrl = event.target.result;
      setPhotoPreview(imgUrl);
      setCameraStatusText('Memproses ekstraksi biometrik wajah dari foto...');
      setCameraStatusColor('var(--accent-warning)');

      const img = new Image();
      img.crossOrigin = 'anonymous';
      img.src = imgUrl;
      img.onload = async () => {
        if (!modelsLoaded) {
          setCameraStatusText('Model AI belum selesai dimuat. Silakan tunggu sebentar.');
          setCameraStatusColor('var(--accent-error)');
          return;
        }

        try {
          const result = await human.detect(img);

          if (result.face && result.face.length > 0 && result.face[0].embedding) {
            const newVec = Array.from(result.face[0].embedding);
            enrollTemplateVersionRef.current = 0;
            // Cek duplikasi untuk upload foto juga
            try {
              const allMasters = await getAllMasterVectors();
              let bestSim = 0, bestName = '';
              for (const m of (allMasters || [])) {
                const vec = toVectorArray(m.descriptor_json);
                if (!vec) continue;
                const sim = cosineSimilarity(newVec, vec);
                if (sim > bestSim) { bestSim = sim; bestName = m.name; }
              }
              if (bestSim >= BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD) {
                currentEmpDescriptorRef.current = newVec;
                currentEmpGFVRef.current = [];
                setFaceCheckResult({ isDuplicate: true, matchedName: bestName, matchedNik: '', similarity: bestSim });
                setCameraStatusText(`⚠️ WAJAH SUDAH TERDAFTAR: ${bestName} (${(bestSim * 100).toFixed(1)}%)`);
                setCameraStatusColor('var(--accent-error)');
              } else {
                currentEmpDescriptorRef.current = newVec;
                currentEmpGFVRef.current = [];
                setFaceCheckResult({ isDuplicate: false, matchedName: '', matchedNik: '', similarity: bestSim });
                const offlineNote = ((!allMasters || allMasters.length === 0) && !navigator.onLine)
                  ? ' (Pemeriksaan duplikasi hanya terhadap data di perangkat ini)'
                  : '';
                setCameraStatusText(`✓ Wajah Diekstrak dari Foto [Valid — Belum Terdaftar]${offlineNote}`);
                setCameraStatusColor('var(--accent-success)');
              }
            } catch (_) {
              currentEmpDescriptorRef.current = newVec;
              currentEmpGFVRef.current = [];
              setFaceCheckResult(null);
              setCameraStatusText(`✓ Wajah Diekstrak dari Foto [1024-dim Human]`);
              setCameraStatusColor('var(--accent-success)');
            }
          } else {
            currentEmpDescriptorRef.current = null;
            enrollTemplateVersionRef.current = null;
            currentEmpGFVRef.current = null;
            setFaceCheckResult(null);
            setCameraStatusText('Wajah Tidak Terdeteksi dalam Foto! Gunakan foto lain.');
            setCameraStatusColor('var(--accent-error)');
          }
        } catch (err) {
          console.error('[PHOTO EXTRACT ERROR]:', err);
          currentEmpDescriptorRef.current = null;
          enrollTemplateVersionRef.current = null;
          setCameraStatusText('Gagal mengekstrak foto: ' + err.message);
          setCameraStatusColor('var(--accent-error)');
        }
      };
    };

    reader.readAsDataURL(file);
  };

  // Submit Add Employee Form
  const handleAddEmployeeSubmit = async (e) => {
    e.preventDefault();
    if (!empNik.trim() || !empName.trim() || !empJabatan.trim()) {
      showToast('Peringatan Form', 'Mohon lengkapi NIK, Nama, dan Jabatan!', 'error');
      return;
    }

    // Blokir jika wajah sudah terdeteksi sebagai duplikasi
    if (faceCheckResult?.isDuplicate) {
      showToast(
        'Wajah Sudah Terdaftar',
        `Wajah ini sudah terdaftar atas nama "${faceCheckResult.matchedName}". Satu wajah hanya boleh digunakan untuk satu karyawan.`,
        'error'
      );
      return;
    }

    // Jika ada vektor wajah, lakukan double-check server-side di submit
    if (currentEmpDescriptorRef.current) {
      try {
        const allMasters = await getAllMasterVectors();
        for (const m of (allMasters || [])) {
          const vec = toVectorArray(m.descriptor_json);
          if (!vec) continue;
          const sim = cosineSimilarity(currentEmpDescriptorRef.current, vec);
          if (sim >= BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD) {
            showToast(
              'Wajah Sudah Terdaftar',
              `Wajah ini sudah terdaftar atas nama "${m.name}" (NIK: ${m.nik}). Similaritas: ${(sim * 100).toFixed(1)}%.`,
              'error'
            );
            return;
          }
        }
      } catch (dupErr) {
        console.warn('[Duplicate Check Error]:', dupErr);
        // Lanjutkan jika cek gagal (tidak blokir pendaftaran)
      }
    }

    setIsSubmitting(true);

    try {
      // Sesuai permintaan: Generate 9 digit desimal di belakang NIK
      const generatedSuffix = Date.now().toString().slice(-9);
      const finalNik = empNik.trim() + generatedSuffix;

      const empPayload = {
        nik: finalNik,
        name: empName.trim(),
        department: empJabatan.trim(),
        afdeling: empAfdeling.trim(),
        nama_kebun: empNamaKebun.trim(),
        status_tk: empStatusTk === 'Lainnya...' ? empStatusTkCustom.trim() : empStatusTk,
        jabatan: empJabatan.trim(),
        status_perkawinan: empStatusPerkawinan,
      };

      // ── MENGGUNAKAN OUTBOX QUEUE PATTERN (CORE ARCHITECTURE) ──
      // UI tidak lagi menembak API secara langsung. Semua data baru masuk ke antrean lokal (SQLite).
      // SyncEngine di latar belakang yang akan memproses ini ke Server.

      const tempId = 'off_emp_' + Date.now() + '_' + Math.random().toString(36).substring(2, 7);
      const createdEmp = {
        id: tempId,
        ...empPayload,
        has_master_biometric: !!currentEmpDescriptorRef.current,
        is_synced: false,
        created_at: new Date().toISOString()
      };

      const chosenTemplateVersion = enrollTemplateVersionRef.current !== undefined && enrollTemplateVersionRef.current !== null
        ? enrollTemplateVersionRef.current
        : (currentEmpDescriptorRef.current ? 2 : null);

      const empQueueRecord = {
        ...createdEmp,
        descriptor_json: currentEmpDescriptorRef.current || null,
        geometric_descriptor_json: currentEmpGFVRef.current || null,
        template_version: chosenTemplateVersion
      };

      if (Capacitor.isNativePlatform()) {
        const { sqliteSavePendingEmployee } = await import('../services/sqliteService');
        await sqliteSavePendingEmployee(empQueueRecord);
      } else {
        const { db } = await import('../db');
        await db.employee_sync_queue.add(empQueueRecord);
        await db.employees_cache.put({
          ...createdEmp,
          has_master_biometric: !!currentEmpDescriptorRef.current,
          descriptor_json: currentEmpDescriptorRef.current || null,
          template_version: chosenTemplateVersion,
          is_synced: false
        });
      }

      // Simpan Thumbnail Foto Wajah jika ada
      if (enrollmentThumbRef.current) {
        await saveEmployeeFaceThumb(tempId, enrollmentThumbRef.current, chosenTemplateVersion !== null ? chosenTemplateVersion : 2, null, empNik.trim());
      }

      // Simpan Vektor Wajah (Biometrik) secara lokal
      if (currentEmpDescriptorRef.current) {
        const cs = vectorChecksum(currentEmpDescriptorRef.current);
        const { cacheUserMasterVector } = await import('../db');
        await cacheUserMasterVector({
          employee_id: tempId,
          ...createdEmp,
          descriptor_json: currentEmpDescriptorRef.current,
          geometric_descriptor_json: currentEmpGFVRef.current || null,
          template_version: chosenTemplateVersion !== null ? chosenTemplateVersion : 2,
          vector_checksum: cs
        });
      }

      showToast(
        'Karyawan Berhasil Disimpan',
        `Karyawan ${empName.trim()} (${empNik.trim()}) tersimpan secara lokal dan otomatis masuk antrean sinkronisasi.`,
        'success'
      );

      // Reset Form
      setEmpNik('');
      setEmpName('');
      setEmpAfdeling('');
      setEmpNamaKebun(user?.role === 'estate_admin' && user?.kebun ? user.kebun : '');
      setEmpStatusTk('');
      setEmpStatusTkCustom('');
      setEmpJabatan('');
      setEmpStatusPerkawinan('');
      setFormStep(1);
      setPhotoPreview(null);
      setPhotoFileName('Format: JPG, PNG, WEBP (Pastikan 1 Wajah Terlihat Jelas)');
      currentEmpDescriptorRef.current = null;
      descriptorHistoryRef.current = [];
      currentEmpGFVRef.current = null;
      enrollmentThumbRef.current = null;
      setFaceCheckResult(null);

      refreshEmployees();
      window.dispatchEvent(new CustomEvent('refresh_employees'));
    } catch (err) {
      console.error('[ADD EMP ERROR]:', err);
      showToast('Gagal Simpan', err.message || 'Terjadi kesalahan sistem.', 'error');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="grid-2">
      <div className="glass-card">
        <div className="card-title">
          <i className="fa-solid fa-user-plus" style={{ color: 'var(--accent-cyan)' }}></i>
          Input Data &amp; Biometrik Wajah Karyawan
        </div>

        <form onSubmit={handleAddEmployeeSubmit}>
          {formStep === 1 && (
            <>
              <div className="form-group">
                <label htmlFor="emp-nik">NIK / Employee ID</label>
                <input
                  type="text"
                  id="emp-nik"
                  placeholder="Contoh: JMK 112"
                  value={empNik}
                  onChange={(e) => setEmpNik(e.target.value)}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="emp-name">Nama Lengkap Karyawan</label>
                <input
                  type="text"
                  id="emp-name"
                  placeholder="Contoh: STEVE"
                  value={empName}
                  onChange={(e) => setEmpName(e.target.value)}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="emp-afdeling">Afdeling</label>
                <input
                  type="text"
                  id="emp-afdeling"
                  placeholder="Contoh: Afdeling 1"
                  value={empAfdeling}
                  onChange={(e) => setEmpAfdeling(e.target.value)}
                />
              </div>

              <div className="form-group">
                <label htmlFor="emp-nama-kebun">Nama Kebun</label>
                <input
                  type="text"
                  id="emp-nama-kebun"
                  placeholder="Contoh: Kebun Sawit Utama"
                  value={empNamaKebun}
                  onChange={(e) => setEmpNamaKebun(e.target.value)}
                  disabled={user?.role === 'estate_admin' && !!user?.kebun}
                />
              </div>

              <div className="form-group">
                <label htmlFor="emp-status-tk">Status TK</label>
                <select
                  id="emp-status-tk"
                  value={empStatusTk}
                  onChange={(e) => setEmpStatusTk(e.target.value)}
                >
                  <option value="">-- Pilih Status TK --</option>
                  <option value="BHL">BHL (Buruh Harian Lepas)</option>
                  <option value="Karyawan Tetap (PKWTT)">Karyawan Tetap (PKWTT)</option>
                  <option value="Karyawan Kontrak (PKWT)">Karyawan Kontrak (PKWT)</option>
                  <option value="Lainnya...">Lainnya...</option>
                </select>
                {empStatusTk === 'Lainnya...' && (
                  <input
                    type="text"
                    placeholder="Masukkan Status TK lainnya..."
                    value={empStatusTkCustom}
                    onChange={(e) => setEmpStatusTkCustom(e.target.value)}
                    style={{ marginTop: '8px' }}
                    required
                  />
                )}
              </div>

              <div className="form-group">
                <label htmlFor="emp-jabatan">Jabatan</label>
                <input
                  type="text"
                  id="emp-jabatan"
                  placeholder="Contoh: Mandor"
                  value={empJabatan}
                  onChange={(e) => setEmpJabatan(e.target.value)}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="emp-status-perkawinan">Status Perkawinan</label>
                <select
                  id="emp-status-perkawinan"
                  value={empStatusPerkawinan}
                  onChange={(e) => setEmpStatusPerkawinan(e.target.value)}
                >
                  <option value="">-- Pilih Status Perkawinan --</option>
                  <option value="Lajang">Lajang</option>
                  <option value="Menikah">Menikah</option>
                  <option value="Duda/Janda">Duda/Janda</option>
                </select>
              </div>

              <div style={{ display: 'flex', gap: '10px', marginTop: '1rem' }}>
                <button
                  type="submit"
                  className="btn"
                  style={{ flex: 1, background: 'var(--bg-secondary)', color: 'var(--text-main)', border: '1px solid var(--border-color)' }}
                >
                  Simpan Tanpa Biometrik
                </button>
                <button
                  type="button"
                  className="btn btn-primary"
                  style={{ flex: 1 }}
                  onClick={() => {
                    if (!empNik.trim() || !empName.trim() || !empJabatan.trim()) {
                      showToast('Peringatan Form', 'Mohon lengkapi NIK, Nama, dan Jabatan terlebih dahulu!', 'warning');
                      return;
                    }
                    setFormStep(2);
                  }}
                >
                  Lanjut &rarr; Foto Wajah
                </button>
              </div>
            </>
          )}

          {formStep === 2 && (
            <>
              <div className="form-group">
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                  <label style={{ margin: 0 }}>
                    <i className="fa-solid fa-camera"></i> Foto Wajah Master
                  </label>
                  {empFormMode === 'camera' && (
                    <button
                      type="button"
                      className="btn"
                      onClick={() => setCameraFacingMode(prev => prev === 'user' ? 'environment' : 'user')}
                      style={{
                        padding: '4px 8px',
                        fontSize: '0.8rem',
                        background: 'rgba(255,255,255,0.1)',
                        border: '1px solid var(--border-color)',
                        width: 'auto',
                        display: 'flex',
                        alignItems: 'center',
                        gap: '6px'
                      }}
                      title="Ganti Kamera Depan/Belakang"
                    >
                      <i className="fa-solid fa-rotate"></i> Tukar Kamera
                    </button>
                  )}
                </div>

                <div style={{ display: 'flex', gap: '8px', marginBottom: '10px', marginTop: '10px' }}>
                  <button
                    type="button"
                    className={`btn ${empFormMode === 'camera' ? 'btn-primary' : ''}`}
                    style={{
                      padding: '6px 12px',
                      fontSize: '0.8rem',
                      background: empFormMode !== 'camera' ? 'var(--bg-primary)' : undefined,
                      color: empFormMode !== 'camera' ? 'var(--text-main)' : '#fff',
                      border: empFormMode !== 'camera' ? '1px solid var(--border-color)' : '1px solid transparent',
                      width: 'auto',
                    }}
                    onClick={() => setEmpFormMode('camera')}
                  >
                    <i className="fa-solid fa-video"></i> Kamera
                  </button>
                  <button
                    type="button"
                    className={`btn ${empFormMode === 'file' ? 'btn-primary' : ''}`}
                    style={{
                      padding: '6px 12px',
                      fontSize: '0.8rem',
                      background: empFormMode !== 'file' ? 'var(--bg-primary)' : undefined,
                      color: empFormMode !== 'file' ? 'var(--text-main)' : '#fff',
                      border: empFormMode !== 'file' ? '1px solid var(--border-color)' : '1px solid transparent',
                      width: 'auto',
                    }}
                    onClick={() => setEmpFormMode('file')}
                  >
                    <i className="fa-solid fa-upload"></i> Upload
                  </button>
                </div>

                {empFormMode === 'camera' && (
                  !useFullscreen ? (
                    <div className="webcam-wrapper" style={{ aspectRatio: '4/3' }}>
                      <video
                        ref={regVideoRef}
                        autoPlay
                        muted
                        playsInline
                        style={{ transform: cameraFacingMode === 'user' ? 'scaleX(-1)' : 'none' }}
                      ></video>
                      <canvas
                        ref={regCanvasRef}
                        className="overlay-canvas"
                        style={{ transform: cameraFacingMode === 'user' ? 'scaleX(-1)' : 'none' }}
                      ></canvas>
                      <FaceFrameOverlay mode="register" stateRef={regFrameStateRef} />
                    </div>
                  ) : (
                    <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
                      {currentEmpDescriptorRef.current ? (
                        <div
                          style={{
                            background: 'rgba(16, 185, 129, 0.1)',
                            border: '1px solid #10b981',
                            borderRadius: '8px',
                            padding: '12px',
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                            gap: '8px'
                          }}
                        >
                          <span style={{ color: '#10b981', fontWeight: 700, fontSize: '0.85rem' }}>
                            ✓ Wajah Master Terdeteksi
                          </span>
                          <button
                            type="button"
                            className="btn"
                            onClick={() => {
                              currentEmpDescriptorRef.current = null;
                              descriptorHistoryRef.current = [];
                              setRegScannerOpen(true);
                            }}
                            style={{
                              fontSize: '0.78rem',
                              padding: '6px 14px',
                              background: 'var(--bg-secondary)',
                              color: 'var(--text-main)',
                              border: '1px solid var(--border-color)',
                              borderRadius: '6px'
                            }}
                          >
                            <i className="fa-solid fa-camera" /> Ambil Ulang Foto
                          </button>
                        </div>
                      ) : (
                        <button
                          type="button"
                          className="btn btn-primary"
                          onClick={() => {
                            currentEmpDescriptorRef.current = null;
                            descriptorHistoryRef.current = [];
                            setRegScannerOpen(true);
                          }}
                          style={{
                            padding: '12px',
                            fontSize: '0.85rem',
                            fontWeight: 700,
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'center',
                            gap: '8px',
                            minHeight: '44px'
                          }}
                        >
                          <i className="fa-solid fa-camera" /> Buka Scanner Wajah Fullscreen
                        </button>
                      )}
                    </div>
                  )
                )}


                {empFormMode === 'file' && (
                  <div
                    style={{
                      background: 'rgba(15,23,42,0.8)',
                      border: '1px dashed var(--border-color)',
                      padding: '1.5rem',
                      borderRadius: '10px',
                      textAlign: 'center',
                    }}
                  >
                    <input
                      type="file"
                      ref={fileInputRef}
                      accept="image/*"
                      style={{ display: 'none' }}
                      onChange={handlePhotoFileUpload}
                    />
                    <button
                      type="button"
                      className="btn"
                      style={{
                        background: 'rgba(99,102,241,0.2)',
                        border: '1px solid var(--accent-primary)',
                        color: '#fff',
                        width: 'auto',
                      }}
                      onClick={() => fileInputRef.current && fileInputRef.current.click()}
                    >
                      <i className="fa-solid fa-image"></i> Pilih File Foto Wajah
                    </button>
                    <div style={{ marginTop: '8px', fontSize: '0.82rem', color: 'var(--text-muted)' }}>
                      {photoFileName}
                    </div>
                    {photoPreview && (
                      <img
                        src={photoPreview}
                        style={{
                          maxWidth: '100%',
                          maxHeight: '220px',
                          borderRadius: '8px',
                          marginTop: '10px',
                        }}
                        alt="Preview Foto Master"
                      />
                    )}
                    {photoPreview && currentEmpDescriptorRef.current && (
                      <div
                        style={{
                          marginTop: '12px',
                          padding: '10px 14px',
                          background: 'rgba(245, 158, 11, 0.12)',
                          border: '1px solid var(--accent-warning)',
                          borderRadius: '8px',
                          textAlign: 'left'
                        }}
                      >
                        <div style={{ color: 'var(--accent-warning)', fontWeight: 700, fontSize: '0.82rem', marginBottom: '4px', display: 'flex', alignItems: 'center', gap: '6px' }}>
                          <i className="fa-solid fa-triangle-exclamation" /> Peringatan: Template Sempit (1 Foto)
                        </div>
                        <div style={{ color: 'var(--text-muted)', fontSize: '0.78rem', marginBottom: '8px', lineHeight: 1.4 }}>
                          Template dari satu foto lebih sempit dan lebih mudah gagal di lapangan. Disarankan mendaftar ulang lewat kamera.
                        </div>
                        <button
                          type="button"
                          className="btn"
                          onClick={() => {
                            setEmpFormMode('camera');
                            currentEmpDescriptorRef.current = null;
                            enrollTemplateVersionRef.current = null;
                            setPhotoPreview(null);
                          }}
                          style={{
                            padding: '6px 12px',
                            fontSize: '0.78rem',
                            background: 'var(--bg-secondary)',
                            color: 'var(--text-main)',
                            border: '1px solid var(--border-color)',
                            borderRadius: '6px',
                            width: 'auto'
                          }}
                        >
                          <i className="fa-solid fa-camera" /> Beralih ke Pendaftaran Kamera
                        </button>
                      </div>
                    )}
                  </div>
                )}

                <div
                  style={{
                    marginTop: '8px',
                    fontSize: '0.82rem',
                    display: 'flex',
                    justifyContent: 'space-between',
                    background: 'rgba(0,0,0,0.2)',
                    padding: '6px 10px',
                    borderRadius: '6px',
                  }}
                >
                  <span>Status Biometrik Master:</span>
                  <strong style={{ color: cameraStatusColor }}>{cameraStatusText}</strong>
                </div>

                {regFailCount >= 2 && regMode !== 'frontal_only' && (
                  <div style={{ marginTop: '8px', padding: '8px 12px', background: 'rgba(245, 158, 11, 0.15)', border: '1px solid var(--accent-warning)', borderRadius: '6px', fontSize: '0.8rem' }}>
                    <div style={{ color: 'var(--accent-warning)', fontWeight: 'bold', marginBottom: '4px' }}>
                      Pendaftaran berulang kali gagal?
                    </div>
                    <div style={{ color: 'var(--text-muted)', marginBottom: '8px', fontSize: '0.75rem' }}>
                      Gunakan mode lurus saja (hanya 6 frame frontal). Akurasi pada cahaya sore hari mungkin sedikit berkurang.
                    </div>
                    <button
                      type="button"
                      className="btn"
                      style={{ width: '100%', fontSize: '0.8rem', padding: '6px', background: 'var(--accent-warning)', color: '#000', fontWeight: 'bold' }}
                      onClick={() => {
                        setRegMode('frontal_only');
                        enrollmentCollectorRef.current = createEnrollmentCollector('frontal_only');
                        setCameraStatusText('Mode lurus aktif: tatap lurus ke kamera');
                      }}
                    >
                      Daftar Mode Lurus Saja
                    </button>
                  </div>
                )}
              </div>

              <div style={{ display: 'flex', gap: '10px', marginTop: '1rem' }}>
                <button
                  type="button"
                  className="btn"
                  style={{ background: 'rgba(255,255,255,0.1)' }}
                  onClick={() => setFormStep(1)}
                >
                  &larr; Kembali
                </button>
                <button
                  type="submit"
                  className="btn btn-success"
                  style={{ flex: 1 }}
                  disabled={isSubmitting}
                >
                  <i className="fa-solid fa-floppy-disk"></i> Simpan Karyawan
                </button>
              </div>
            </>
          )}
        </form>
      </div>

      {/* Fullscreen Registration Scanner for Mobile */}
      {formStep === 2 && empFormMode === 'camera' && useFullscreen && (
        <FullscreenScanShell
          open={regScannerOpen}
          onClose={() => setRegScannerOpen(false)}
          title="PENDAFTARAN WAJAH"
          subtitle={`${empName || 'Karyawan Baru'} (NIK: ${empNik || '-'})`}
          facingMode={cameraFacingMode}
          onToggleFacing={() => setCameraFacingMode(prev => prev === 'user' ? 'environment' : 'user')}
          hint={cameraStatusText}
          hintTone={faceCheckResult?.isDuplicate ? 'error' : (currentEmpDescriptorRef.current ? 'success' : 'info')}
          footer={
            regFailCount >= 2 && regMode !== 'frontal_only' ? (
              <div style={{ padding: '8px 12px', background: 'rgba(245, 158, 11, 0.2)', border: '1px solid var(--accent-warning)', borderRadius: '6px', textAlign: 'center' }}>
                <div style={{ color: 'var(--accent-warning)', fontSize: '0.8rem', fontWeight: 'bold', marginBottom: '4px' }}>
                  Pendaftaran berulang kali gagal?
                </div>
                <div style={{ color: '#fff', fontSize: '0.75rem', marginBottom: '8px' }}>
                  Gunakan mode lurus saja (hanya 6 frame frontal). Akurasi sore hari mungkin sedikit berkurang.
                </div>
                <button
                  type="button"
                  className="btn"
                  style={{ width: '100%', fontSize: '0.8rem', padding: '8px', background: 'var(--accent-warning)', color: '#000', fontWeight: 'bold' }}
                  onClick={() => {
                    setRegMode('frontal_only');
                    enrollmentCollectorRef.current = createEnrollmentCollector('frontal_only');
                    setCameraStatusText('Mode lurus aktif: tatap lurus ke kamera');
                  }}
                >
                  Daftar Mode Lurus Saja
                </button>
              </div>
            ) : null
          }
        >
          <FaceScanSurface
            videoRef={regVideoRef}
            canvasRef={regCanvasRef}
            facingMode={cameraFacingMode}
            canvasSize={canvasSize}
            mode="register"
            frameStateRef={regFrameStateRef}
          />
        </FullscreenScanShell>
      )}
    </div>
  );
}
