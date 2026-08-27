import React, { useState, useEffect, useRef, useCallback } from 'react';
import { API_BASE_URL, fetchWithTimeout } from '../config';
import { supabase } from '../supabaseClient';
import { cacheUserMasterVector, cacheGeometricVector, getAllMasterVectors, cosineSimilarity } from '../db';
import { useNormalizedFaceMesh } from '../hooks/useNormalizedFaceMesh';
import { human } from '../humanSingleton';
import { useAuth } from '../context/AuthContext';



export default function TabEmployeeManagement({
  employees,
  modelsLoaded,
  showToast,
  refreshEmployees,
  openConfirmModal,
}) {
  const { user } = useAuth();

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
  const [empFormMode, setEmpFormMode] = useState('camera'); // 'camera' | 'file'
  const [cameraStatusText, setCameraStatusText] = useState('Menunggu Wajah di Kamera...');
  const [cameraStatusColor, setCameraStatusColor] = useState('var(--accent-warning)');
  const [photoPreview, setPhotoPreview] = useState(null);
  const [photoFileName, setPhotoFileName] = useState('Format: JPG, PNG, WEBP (Pastikan 1 Wajah Terlihat Jelas)');
  const [isSubmitting, setIsSubmitting] = useState(false);
  // null = belum dicek | { isDuplicate, matchedName, matchedNik, similarity } = hasil cek
  const [faceCheckResult, setFaceCheckResult] = useState(null);
  const DUPLICATE_THRESHOLD = 0.85; // threshold cosine similarity

  // Removed Edit Modal State (moved to DaftarKaryawanPage)

  const currentEmpDescriptorRef = useRef(null);
  const currentEmpGFVRef = useRef(null); // 40-d Geometric Feature Vector
  const regVideoRef = useRef(null);
  const regCanvasRef = useRef(null);
  const fileInputRef = useRef(null);

  // Injected detection callback untuk human.js di register mode
  const detectRegFacesCallback = useCallback(async (croppedCanvas) => {
    if (!modelsLoaded) return null;
    if (human.config?.face?.description) {
      human.config.face.description.enabled = true; // FORCE ENABLE: Pastikan embedding selalu diekstrak saat daftar
    }
    const result = await human.detect(croppedCanvas);
    return result?.face?.[0] ?? null;
  }, [modelsLoaded]);

  // Callback saat wajah diproses di register mode
  const onRegFaceProcessed = useCallback(({ detection, smoothedMesh, ctx }) => {
    if (detection.embedding) {
      const newVec = Array.from(detection.embedding);
      currentEmpDescriptorRef.current = newVec;
      currentEmpGFVRef.current = [];

      // Cek duplikasi wajah secara real-time (async, non-blocking)
      (async () => {
        try {
          const allMasters = await getAllMasterVectors();
          let bestSim = 0, bestName = '', bestNik = '';
          for (const m of allMasters) {
            const vec = m.descriptor_json;
            if (!Array.isArray(vec) || vec.length !== 1024) continue;
            const sim = cosineSimilarity(newVec, vec);
            if (sim > bestSim) { bestSim = sim; bestName = m.name; bestNik = m.nik; }
          }
          if (bestSim >= DUPLICATE_THRESHOLD) {
            setFaceCheckResult({ isDuplicate: true, matchedName: bestName, matchedNik: bestNik, similarity: bestSim });
            setCameraStatusText(`⚠️ WAJAH SUDAH TERDAFTAR: ${bestName} (${(bestSim * 100).toFixed(1)}%)`);
            setCameraStatusColor('var(--accent-error)');
          } else {
            setFaceCheckResult({ isDuplicate: false, matchedName: '', matchedNik: '', similarity: bestSim });
            setCameraStatusText(`✓ Wajah Baru Terdeteksi [Valid — Belum Terdaftar]`);
            setCameraStatusColor('var(--accent-success)');
          }
        } catch (_) {
          // fallback: jika tidak bisa cek, tetap izinkan
          setCameraStatusText(`✓ Biometrik Wajah Master Terdeteksi [1024-dim Human]`);
          setCameraStatusColor('var(--accent-success)');
          setFaceCheckResult(null);
        }
      })();
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
  }, [DUPLICATE_THRESHOLD]);

  // Callback saat tidak ada wajah di register mode
  const onRegNoFace = useCallback(() => {
    currentEmpDescriptorRef.current = null;
    currentEmpGFVRef.current = null;
    setFaceCheckResult(null);
    setCameraStatusText('Menunggu Wajah di Kamera...');
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
    active: modelsLoaded && empFormMode === 'camera',
    facingMode: 'user',
    smoothAlpha: 0.35,
    detectFaces: detectRegFacesCallback,
    onFaceProcessed: onRegFaceProcessed,
    onNoFace: onRegNoFace,
    onCameraError: onRegCameraError,
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
          if (human.config?.face?.description) {
            human.config.face.description.enabled = true; // FORCE ENABLE: Pastikan embedding selalu diekstrak saat upload foto
          }
          const result = await human.detect(img);

          if (result.face && result.face.length > 0 && result.face[0].embedding) {
            const newVec = Array.from(result.face[0].embedding);
            // Cek duplikasi untuk upload foto juga
            try {
              const allMasters = await getAllMasterVectors();
              let bestSim = 0, bestName = '';
              for (const m of allMasters) {
                const vec = m.descriptor_json;
                if (!Array.isArray(vec) || vec.length !== 1024) continue;
                const sim = cosineSimilarity(newVec, vec);
                if (sim > bestSim) { bestSim = sim; bestName = m.name; }
              }
              if (bestSim >= DUPLICATE_THRESHOLD) {
                currentEmpDescriptorRef.current = newVec;
                currentEmpGFVRef.current = [];
                setFaceCheckResult({ isDuplicate: true, matchedName: bestName, matchedNik: '', similarity: bestSim });
                setCameraStatusText(`⚠️ WAJAH SUDAH TERDAFTAR: ${bestName} (${(bestSim * 100).toFixed(1)}%)`);
                setCameraStatusColor('var(--accent-error)');
              } else {
                currentEmpDescriptorRef.current = newVec;
                currentEmpGFVRef.current = [];
                setFaceCheckResult({ isDuplicate: false, matchedName: '', matchedNik: '', similarity: bestSim });
                setCameraStatusText(`✓ Wajah Diekstrak dari Foto [Valid — Belum Terdaftar]`);
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
            currentEmpGFVRef.current = null;
            setFaceCheckResult(null);
            setCameraStatusText('Wajah Tidak Terdeteksi dalam Foto! Gunakan foto lain.');
            setCameraStatusColor('var(--accent-error)');
          }
        } catch (err) {
          console.error('[PHOTO EXTRACT ERROR]:', err);
          currentEmpDescriptorRef.current = null;
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
        for (const m of allMasters) {
          const vec = m.descriptor_json;
          if (!Array.isArray(vec) || vec.length !== 1024) continue;
          const sim = cosineSimilarity(currentEmpDescriptorRef.current, vec);
          if (sim >= DUPLICATE_THRESHOLD) {
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
      const { data: createdEmp, error } = await supabase
        .from('employees')
        .insert([
          {
            nik: empNik.trim(),
            name: empName.trim(),
            department: empJabatan.trim(),
            afdeling: empAfdeling.trim(),
            nama_kebun: empNamaKebun.trim(),
            status_tk: empStatusTk === 'Lainnya...' ? empStatusTkCustom.trim() : empStatusTk,
            jabatan: empJabatan.trim(),
            status_perkawinan: empStatusPerkawinan,
          },
        ])
        .select()
        .single();

      if (error) {
        showToast('Gagal Menambah Karyawan', error.message, 'error');
        setIsSubmitting(false);
        return;
      }

      const createdEmpId = createdEmp.id;

      if (currentEmpDescriptorRef.current) {
        const descJson = JSON.stringify(currentEmpDescriptorRef.current);
        await supabase
          .from('master_descriptors')
          .upsert({
            employee_id: createdEmpId,
            descriptor_json: descJson,
          }, { onConflict: 'employee_id' });



        await cacheUserMasterVector({
          employee_id: createdEmpId,
          nik: createdEmp.nik,
          name: createdEmp.name,
          department: createdEmp.department || createdEmp.jabatan,
          afdeling: createdEmp.afdeling,
          nama_kebun: createdEmp.nama_kebun,
          status_tk: createdEmp.status_tk,
          jabatan: createdEmp.jabatan,
          status_perkawinan: createdEmp.status_perkawinan,
          descriptor_json: currentEmpDescriptorRef.current,
          geometric_descriptor_json: currentEmpGFVRef.current || null,
        });
      }

      showToast(
        'Karyawan Berhasil Disimpan',
        `Karyawan ${empName.trim()} (${empNik.trim()}) telah berhasil ditambahkan ke database!`,
        'success'
      );

      setEmpNik('');
      setEmpName('');
      setEmpAfdeling('');
      setEmpNamaKebun(user?.role === 'estate_admin' && user?.kebun ? user.kebun : '');
      setEmpStatusTk('');
      setEmpStatusTkCustom('');
      setEmpJabatan('');
      setEmpStatusPerkawinan('');
      setPhotoPreview(null);
      setPhotoFileName('Format: JPG, PNG, WEBP (Pastikan 1 Wajah Terlihat Jelas)');
      currentEmpDescriptorRef.current = null;
      currentEmpGFVRef.current = null;
      setFaceCheckResult(null);

      refreshEmployees();
    } catch (err) {
      console.error('[ADD EMP ERROR]:', err);
      showToast('Error Sistem', 'Terjadi kesalahan: ' + err.message, 'error');
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

          <div className="form-group">
            <label>
              <i className="fa-solid fa-camera"></i> Foto Wajah Master (Kamera / Unggah Berkas)
            </label>

            <div style={{ display: 'flex', gap: '8px', marginBottom: '10px' }}>
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
                <i className="fa-solid fa-video"></i> Gunakan Kamera
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
                <i className="fa-solid fa-upload"></i> Unggah File Foto
              </button>
            </div>

            {empFormMode === 'camera' && (
              <div className="webcam-wrapper" style={{ aspectRatio: '4/3' }}>
                <video
                  ref={regVideoRef}
                  autoPlay
                  muted
                  playsInline
                  style={{ transform: 'scaleX(-1)' }}
                ></video>
                <canvas
                  ref={regCanvasRef}
                  className="overlay-canvas"
                  style={{ transform: 'scaleX(-1)' }}
                ></canvas>
              </div>
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
          </div>

          <button
            type="submit"
            className="btn btn-success"
            style={{ marginTop: '1rem' }}
            disabled={isSubmitting}
          >
            <i className="fa-solid fa-floppy-disk"></i> Simpan Karyawan &amp; Master Biometrik
          </button>
        </form>
      </div>
    </div>
  );
}
