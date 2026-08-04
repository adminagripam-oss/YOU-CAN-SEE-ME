import React, { useState, useEffect, useRef } from 'react';
import { API_BASE_URL } from '../config';
import { supabase } from '../supabaseClient';
import { cacheUserMasterVector, cacheGeometricVector } from '../db';

/**
 * Extract a 40-d Geometric Feature Vector (GFV) from 68 face-api landmark points.
 * All distances normalized by Inter-Pupillary Distance (IPD) → scale invariant.
 */
function extractGeometricFeatureVector(landmarks) {
  try {
    const pts = landmarks.positions;
    if (!pts || pts.length < 68) return null;

    const d = (a, b) => Math.sqrt((a.x - b.x) ** 2 + (a.y - b.y) ** 2);
    const cen = (arr) => ({
      x: arr.reduce((s, p) => s + p.x, 0) / arr.length,
      y: arr.reduce((s, p) => s + p.y, 0) / arr.length,
    });

    const leftEyePts = pts.slice(36, 42);
    const rightEyePts = pts.slice(42, 48);
    const lEye = cen(leftEyePts);
    const rEye = cen(rightEyePts);
    const lBrow = cen(pts.slice(17, 22));
    const rBrow = cen(pts.slice(22, 27));
    const midEye = cen([lEye, rEye]);

    const ipd = d(lEye, rEye);
    if (ipd < 5) return null; // face too small
    const n = (val) => parseFloat((val / ipd).toFixed(6));

    const noseTip = pts[30];
    const noseBtm = pts[33];
    const chin = pts[8];

    return [
      // ── Eyes ──────────────────────────────────────────────────
      n(d(pts[36], pts[39])),        // 0  L eye width
      n(d(pts[42], pts[45])),        // 1  R eye width
      n(d(pts[37], pts[41])),        // 2  L eye height (outer)
      n(d(pts[43], pts[47])),        // 3  R eye height (outer)
      n(d(pts[38], pts[40])),        // 4  L eye height (inner)
      n(d(pts[44], pts[46])),        // 5  R eye height (inner)
      // ── Eyebrows ──────────────────────────────────────────────
      n(d(pts[17], pts[21])),        // 6  L eyebrow width
      n(d(pts[22], pts[26])),        // 7  R eyebrow width
      n(d(lBrow, lEye)),             // 8  L eyebrow-to-eye gap
      n(d(rBrow, rEye)),             // 9  R eyebrow-to-eye gap
      // ── Nose ──────────────────────────────────────────────────
      n(d(pts[31], pts[35])),        // 10 nose width
      n(d(pts[27], pts[30])),        // 11 nose bridge length
      n(d(noseTip, noseBtm)),        // 12 nose tip → nose bottom
      n(d(noseTip, chin)),           // 13 nose tip → chin
      // ── Mouth ─────────────────────────────────────────────────
      n(d(pts[48], pts[54])),        // 14 mouth width
      n(d(pts[51], pts[57])),        // 15 mouth height
      n(d(pts[51], pts[62])),        // 16 upper lip thickness
      n(d(pts[57], pts[66])),        // 17 lower lip thickness
      // ── Inter-feature distances ────────────────────────────────
      n(d(lEye, noseTip)),           // 18 L eye → nose tip
      n(d(rEye, noseTip)),           // 19 R eye → nose tip
      n(d(midEye, pts[51])),         // 20 eye-midpoint → upper lip
      n(d(noseTip, pts[51])),        // 21 nose tip → upper lip
      n(d(midEye, chin)),            // 22 eye-midpoint → chin
      // ── Jaw ───────────────────────────────────────────────────
      n(d(pts[0], pts[16])),        // 23 face width (jaw corners)
      n(d(pts[4], pts[12])),        // 24 cheek width
      n(d(pts[2], pts[14])),        // 25 jaw intermediate width
      n(d(pts[0], pts[8])),         // 26 L jaw height (corner → chin)
      n(d(pts[16], pts[8])),         // 27 R jaw height (corner → chin)
      n(d(pts[0], pts[4])),         // 28 L jaw segment
      n(d(pts[4], pts[8])),         // 29 L-mid jaw segment
      n(d(pts[8], pts[12])),        // 30 R-mid jaw segment
      n(d(pts[12], pts[16])),        // 31 R jaw segment
      // ── Symmetry offsets (signed, ~0 for frontal face) ─────────
      parseFloat(((lEye.x - midEye.x) / ipd).toFixed(6)),  // 32 L eye offset
      parseFloat(((rEye.x - midEye.x) / ipd).toFixed(6)),  // 33 R eye offset
      parseFloat(((noseTip.x - midEye.x) / ipd).toFixed(6)), // 34 nose offset
      parseFloat(((pts[57].x - midEye.x) / ipd).toFixed(6)), // 35 mouth offset
      // ── Facial ratios (scale-independent) ─────────────────────
      parseFloat((d(midEye, chin) / d(pts[0], pts[16])).toFixed(6)), // 36 face aspect ratio
      parseFloat((d(pts[27], noseTip) / d(noseTip, chin)).toFixed(6)), // 37 upper/lower split
      parseFloat((d(pts[48], pts[54]) / d(pts[0], pts[16])).toFixed(6)), // 38 mouth/face width ratio
      parseFloat((d(pts[17], pts[26]) / d(pts[0], pts[16])).toFixed(6)), // 39 brow span / face width
    ];
  } catch (e) {
    console.warn('[GFV Extract Error]:', e);
    return null;
  }
}

export default function TabEmployeeManagement({
  employees,
  modelsLoaded,
  showToast,
  refreshEmployees,
  openConfirmModal,
}) {
  // Form State
  const [empNik, setEmpNik] = useState('');
  const [empName, setEmpName] = useState('');
  const [empDept, setEmpDept] = useState('');
  const [empFormMode, setEmpFormMode] = useState('camera'); // 'camera' | 'file'
  const [cameraStatusText, setCameraStatusText] = useState('Menunggu Wajah di Kamera...');
  const [cameraStatusColor, setCameraStatusColor] = useState('var(--accent-warning)');
  const [photoPreview, setPhotoPreview] = useState(null);
  const [photoFileName, setPhotoFileName] = useState('Format: JPG, PNG, WEBP (Pastikan 1 Wajah Terlihat Jelas)');
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Edit Modal State
  const [editModalOpen, setEditModalOpen] = useState(false);
  const [editingEmp, setEditingEmp] = useState({ id: '', nik: '', name: '', department: '' });

  const currentEmpDescriptorRef = useRef(null);
  const currentEmpGFVRef = useRef(null); // 40-d Geometric Feature Vector
  const regVideoRef = useRef(null);
  const regCanvasRef = useRef(null);
  const regStreamRef = useRef(null);
  const fileInputRef = useRef(null);

  // Live Camera for Master Face Registration
  useEffect(() => {
    let intervalId = null;

    if (empFormMode === 'camera') {
      async function startRegCamera() {
        try {
          const stream = await navigator.mediaDevices.getUserMedia({
            video: { width: 640, height: 480, facingMode: 'user' },
            audio: false,
          });
          regStreamRef.current = stream;
          if (regVideoRef.current) {
            regVideoRef.current.srcObject = stream;
          }

          intervalId = setInterval(async () => {
            if (!regVideoRef.current || !modelsLoaded || !window.faceapi) return;

            const video = regVideoRef.current;
            const canvas = regCanvasRef.current;
            if (!video.videoWidth) return;

            const displaySize = { width: video.videoWidth, height: video.videoHeight };
            if (canvas) {
              window.faceapi.matchDimensions(canvas, displaySize);
            }

            const detection = await window.faceapi
              .detectSingleFace(video)
              .withFaceLandmarks()
              .withFaceDescriptor();

            if (canvas) {
              const ctx = canvas.getContext('2d');
              ctx.clearRect(0, 0, canvas.width, canvas.height);
            }

            if (detection) {
              const resizedDetection = window.faceapi.resizeResults(detection, displaySize);
              if (canvas) {
                window.faceapi.draw.drawDetections(canvas, resizedDetection);
                window.faceapi.draw.drawFaceLandmarks(canvas, resizedDetection);
              }
              currentEmpDescriptorRef.current = Array.from(detection.descriptor);
              // Extract 40-d Geometric Feature Vector from landmarks
              const gfv = extractGeometricFeatureVector(detection.landmarks);
              currentEmpGFVRef.current = gfv;
              const gfvLabel = gfv ? `[128-dim + GFV ${gfv.length}]` : '[128-dim]';
              setCameraStatusText(`✓ Biometrik Wajah Master Terdeteksi ${gfvLabel}`);
              setCameraStatusColor('var(--accent-success)');
            } else {
              currentEmpDescriptorRef.current = null;
              currentEmpGFVRef.current = null;
              setCameraStatusText('Menunggu Wajah di Kamera...');
              setCameraStatusColor('var(--accent-warning)');
            }
          }, 200);
        } catch (err) {
          console.error('[REG CAMERA ERROR]:', err);
          setCameraStatusText('Gagal Membuka Kamera: ' + err.message);
          setCameraStatusColor('var(--accent-error)');
        }
      }

      startRegCamera();
    } else {
      currentEmpDescriptorRef.current = null;
      if (regStreamRef.current) {
        regStreamRef.current.getTracks().forEach((track) => track.stop());
      }
    }

    return () => {
      if (intervalId) clearInterval(intervalId);
      if (regStreamRef.current) {
        regStreamRef.current.getTracks().forEach((track) => track.stop());
      }
    };
  }, [empFormMode, modelsLoaded]);

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
        if (!window.faceapi || !modelsLoaded) {
          setCameraStatusText('Model AI belum selesai dimuat. Silakan tunggu sebentar.');
          setCameraStatusColor('var(--accent-error)');
          return;
        }

        try {
          const detection = await window.faceapi
            .detectSingleFace(img)
            .withFaceLandmarks()
            .withFaceDescriptor();

          if (detection) {
            currentEmpDescriptorRef.current = Array.from(detection.descriptor);
            // Extract 40-d GFV from photo landmarks
            const gfv = extractGeometricFeatureVector(detection.landmarks);
            currentEmpGFVRef.current = gfv;
            const gfvLabel = gfv ? `[128-dim + GFV ${gfv.length}]` : '[128-dim]';
            setCameraStatusText(`✓ Wajah Diekstrak dari Foto ${gfvLabel}`);
            setCameraStatusColor('var(--accent-success)');
          } else {
            currentEmpDescriptorRef.current = null;
            currentEmpGFVRef.current = null;
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

  // Submit Add Employee Form (with Direct Supabase Cloud Fallback)
  const handleAddEmployeeSubmit = async (e) => {
    e.preventDefault();
    if (!empNik.trim() || !empName.trim() || !empDept.trim()) {
      showToast('Peringatan Form', 'Mohon lengkapi NIK, Nama, dan Departemen!', 'error');
      return;
    }

    setIsSubmitting(true);

    try {
      let createdEmp = null;

      // Tier 1: Express REST API Endpoint
      try {
        const resEmp = await fetch(`${API_BASE_URL}/api/employees`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            nik: empNik.trim(),
            name: empName.trim(),
            department: empDept.trim(),
          }),
        });
        const dataEmp = await resEmp.json();
        if (dataEmp.success && dataEmp.data) {
          createdEmp = dataEmp.data;
        }
      } catch (err) {
        console.warn('[ADD EMP API WARN - FALLING BACK TO SUPABASE DIRECT]:', err.message);
      }

      // Tier 2: Direct Supabase Cloud Database Insert
      if (!createdEmp) {
        const { data, error } = await supabase
          .from('employees')
          .insert([
            {
              nik: empNik.trim(),
              name: empName.trim(),
              department: empDept.trim(),
            },
          ])
          .select()
          .single();

        if (error) {
          showToast('Gagal Menambah Karyawan', error.message, 'error');
          setIsSubmitting(false);
          return;
        }
        createdEmp = data;
      }

      const createdEmpId = createdEmp.id;

      // 2. Save Master Biometrics if Descriptor Available
      if (currentEmpDescriptorRef.current && currentEmpDescriptorRef.current.length === 128) {
        let bioSaved = false;
        try {
          const resBio = await fetch(`${API_BASE_URL}/api/biometrics/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
              employee_id: createdEmpId,
              descriptor: currentEmpDescriptorRef.current,
            }),
          });
          const dataBio = await resBio.json();
          if (dataBio.success) bioSaved = true;
        } catch (err) {
          console.warn('[REGISTER BIO API WARN - FALLING BACK TO SUPABASE DIRECT]:', err.message);
        }

        if (!bioSaved) {
          const descJson = JSON.stringify(currentEmpDescriptorRef.current);
          const { error: bioErr } = await supabase
            .from('employees')
            .update({
              descriptor_json: descJson,
              has_master_biometric: true,
            })
            .eq('id', createdEmpId);

          if (bioErr) {
            console.error('[SUPABASE DIRECT REGISTER BIO ERROR]:', bioErr);
          } else {
            bioSaved = true;
          }
        }

        // Cache Master Vector + GFV in local IndexedDB for offline geometric verification
        await cacheUserMasterVector({
          employee_id: createdEmpId,
          nik: createdEmp.nik,
          name: createdEmp.name,
          department: createdEmp.department,
          descriptor_json: currentEmpDescriptorRef.current,
          geometric_descriptor_json: currentEmpGFVRef.current || null,
        });

        if (currentEmpGFVRef.current) {
          console.log(`[GFV] Stored ${currentEmpGFVRef.current.length}-d geometric vector for ${createdEmp.name}`);
        }
      }

      showToast(
        'Karyawan Berhasil Disimpan',
        `Karyawan ${empName.trim()} (${empNik.trim()}) telah berhasil ditambahkan ke database Supabase Cloud!`,
        'success'
      );

      // Reset Form
      setEmpNik('');
      setEmpName('');
      setEmpDept('');
      setPhotoPreview(null);
      setPhotoFileName('Format: JPG, PNG, WEBP (Pastikan 1 Wajah Terlihat Jelas)');
      currentEmpDescriptorRef.current = null;
      currentEmpGFVRef.current = null;

      refreshEmployees();
    } catch (err) {
      console.error('[ADD EMP ERROR]:', err);
      showToast('Error Sistem', 'Terjadi kesalahan: ' + err.message, 'error');
    } finally {
      setIsSubmitting(false);
    }
  };

  // Open Edit Modal
  const openEditModal = (emp) => {
    setEditingEmp({
      id: emp.id,
      nik: emp.nik || '',
      name: emp.name || '',
      department: emp.department || '',
    });
    setEditModalOpen(true);
  };

  // Submit Edit Employee Form
  const handleEditSubmit = async (e) => {
    e.preventDefault();
    if (!editingEmp.nik.trim() || !editingEmp.name.trim() || !editingEmp.department.trim()) {
      showToast('Peringatan Form', 'Mohon lengkapi NIK, Nama, dan Departemen!', 'error');
      return;
    }

    try {
      const res = await fetch(`${API_BASE_URL}/api/employees/${editingEmp.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          nik: editingEmp.nik.trim(),
          name: editingEmp.name.trim(),
          department: editingEmp.department.trim(),
        }),
      });

      const data = await res.json();
      if (data.success) {
        showToast(
          'Perubahan Disimpan',
          `Data karyawan "${editingEmp.name}" telah berhasil diperbarui di database Supabase.`,
          'success'
        );
        setEditModalOpen(false);
        refreshEmployees();
      } else {
        showToast('Gagal Edit Karyawan', data.message, 'error');
      }
    } catch (err) {
      console.error('[EDIT ERROR]:', err);
      showToast('Error Sistem', err.message, 'error');
    }
  };

  // Delete Employee Confirmation Trigger
  const handleDeleteClick = (emp) => {
    openConfirmModal({
      title: `Hapus Karyawan "${emp.name}"?`,
      message: `Apakah Anda yakin ingin menghapus karyawan "${emp.name}" dari database Supabase? Semua data biometrik master dan log absensi terkait karyawan ini akan ikut terhapus secara permanen.`,
      confirmText: 'Hapus Karyawan',
      onConfirm: async () => {
        try {
          const res = await fetch(`${API_BASE_URL}/api/employees/${emp.id}`, { method: 'DELETE' });
          const data = await res.json();
          if (data.success) {
            showToast('Penghapusan Berhasil', `Karyawan "${emp.name}" telah berhasil dihapus.`, 'success');
            refreshEmployees();
          } else {
            showToast('Gagal Menghapus', data.message, 'error');
          }
        } catch (err) {
          console.error('[DELETE ERROR]:', err);
          showToast('Error Sistem', err.message, 'error');
        }
      },
    });
  };

  return (
    <div className="grid-2">
      {/* Form Input Karyawan & Master Biometrics */}
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
            <label htmlFor="emp-dept">Departemen / Divisi</label>
            <input
              type="text"
              id="emp-dept"
              placeholder="Contoh: BNN"
              value={empDept}
              onChange={(e) => setEmpDept(e.target.value)}
              required
            />
          </div>

          {/* Biometric Capture Mode Selector */}
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
                  background: empFormMode !== 'camera' ? 'rgba(255,255,255,0.1)' : undefined,
                  color: '#fff',
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
                  background: empFormMode !== 'file' ? 'rgba(255,255,255,0.1)' : undefined,
                  color: '#fff',
                }}
                onClick={() => setEmpFormMode('file')}
              >
                <i className="fa-solid fa-upload"></i> Unggah File Foto
              </button>
            </div>

            {/* Mode Live Camera */}
            {empFormMode === 'camera' && (
              <div className="webcam-wrapper" style={{ aspectRatio: '4/3' }}>
                <video ref={regVideoRef} autoPlay muted playsInline></video>
                <canvas ref={regCanvasRef} className="overlay-canvas"></canvas>
              </div>
            )}

            {/* Mode File Upload */}
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

            {/* Biometric Status Indicator */}
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

      {/* Tabel Data Karyawan */}
      <div className="glass-card">
        <div className="card-title">
          <i className="fa-solid fa-address-book" style={{ color: 'var(--accent-cyan)' }}></i>
          Daftar Karyawan Terdaftar
        </div>

        <div className="table-container">
          <table>
            <thead>
              <tr>
                <th>NIK</th>
                <th>Nama</th>
                <th>Departemen</th>
                <th>Biometrik Master</th>
                <th>Aksi</th>
              </tr>
            </thead>
            <tbody>
              {employees.length === 0 ? (
                <tr>
                  <td colSpan="5" style={{ textAlign: 'center', color: 'var(--text-muted)' }}>
                    Belum ada data karyawan.
                  </td>
                </tr>
              ) : (
                employees.map((emp) => (
                  <tr key={emp.id}>
                    <td>
                      <strong>{emp.nik}</strong>
                    </td>
                    <td>{emp.name}</td>
                    <td>{emp.department}</td>
                    <td>
                      {emp.has_master_biometric ? (
                        <span className="status-badge success">
                          <i className="fa-solid fa-check-circle"></i> Siap
                        </span>
                      ) : (
                        <span className="status-badge fail">
                          <i className="fa-solid fa-triangle-exclamation"></i> Belum
                        </span>
                      )}
                    </td>
                    <td>
                      <button
                        type="button"
                        className="btn-action edit"
                        onClick={() => openEditModal(emp)}
                      >
                        <i className="fa-solid fa-pen-to-square"></i> Edit
                      </button>
                      <button
                        type="button"
                        className="btn-action delete"
                        onClick={() => handleDeleteClick(emp)}
                      >
                        <i className="fa-solid fa-trash"></i> Hapus
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>

      {/* Modal Edit Data Karyawan */}
      {editModalOpen && (
        <div
          style={{
            position: 'fixed',
            inset: 0,
            background: 'rgba(0,0,0,0.7)',
            backdropFilter: 'blur(8px)',
            zIndex: 1000,
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            padding: '1rem',
          }}
        >
          <div
            className="glass-card"
            style={{
              maxWidth: '500px',
              width: '100%',
              border: '1px solid var(--accent-primary)',
              boxShadow: '0 10px 40px rgba(0,0,0,0.5)',
            }}
          >
            <div className="card-title" style={{ display: 'flex', justifyContent: 'space-between' }}>
              <span>
                <i className="fa-solid fa-user-pen" style={{ color: 'var(--accent-cyan)' }}></i> Edit Data Karyawan
              </span>
              <button
                type="button"
                style={{
                  background: 'transparent',
                  border: 'none',
                  color: 'var(--text-muted)',
                  fontSize: '1.2rem',
                  cursor: 'pointer',
                }}
                onClick={() => setEditModalOpen(false)}
              >
                <i className="fa-solid fa-xmark"></i>
              </button>
            </div>

            <form onSubmit={handleEditSubmit}>
              <div className="form-group">
                <label htmlFor="edit-emp-nik">NIK / Employee ID</label>
                <input
                  type="text"
                  id="edit-emp-nik"
                  value={editingEmp.nik}
                  onChange={(e) => setEditingEmp({ ...editingEmp, nik: e.target.value })}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="edit-emp-name">Nama Lengkap</label>
                <input
                  type="text"
                  id="edit-emp-name"
                  value={editingEmp.name}
                  onChange={(e) => setEditingEmp({ ...editingEmp, name: e.target.value })}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="edit-emp-dept">Departemen</label>
                <input
                  type="text"
                  id="edit-emp-dept"
                  value={editingEmp.department}
                  onChange={(e) => setEditingEmp({ ...editingEmp, department: e.target.value })}
                  required
                />
              </div>

              <div style={{ display: 'flex', gap: '10px', marginTop: '1.5rem' }}>
                <button
                  type="button"
                  className="btn"
                  style={{ background: 'rgba(255,255,255,0.1)', color: '#fff' }}
                  onClick={() => setEditModalOpen(false)}
                >
                  Batal
                </button>
                <button type="submit" className="btn btn-primary">
                  <i className="fa-solid fa-floppy-disk"></i> Simpan Perubahan
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
