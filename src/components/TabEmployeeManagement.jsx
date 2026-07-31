import React, { useState, useEffect, useRef } from 'react';
import { API_BASE_URL } from '../config';

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
              setCameraStatusText('Biometrik Wajah Master Terdeteksi [128-dim]!');
              setCameraStatusColor('var(--accent-success)');
            } else {
              currentEmpDescriptorRef.current = null;
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
            setCameraStatusText('Wajah Berhasil Diekstrak dari Foto [128-dim]!');
            setCameraStatusColor('var(--accent-success)');
          } else {
            currentEmpDescriptorRef.current = null;
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
    if (!empNik.trim() || !empName.trim() || !empDept.trim()) {
      showToast('Peringatan Form', 'Mohon lengkapi NIK, Nama, dan Departemen!', 'error');
      return;
    }

    setIsSubmitting(true);

    try {
      // 1. Add Employee Record
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
      if (!dataEmp.success) {
        showToast('Gagal Menambah Karyawan', dataEmp.message, 'error');
        return;
      }

      const createdEmpId = dataEmp.data.id;

      // 2. Save Master Biometrics if Descriptor Available
      if (currentEmpDescriptorRef.current && currentEmpDescriptorRef.current.length === 128) {
        const resBio = await fetch(`${API_BASE_URL}/api/biometrics/register`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            employee_id: createdEmpId,
            descriptor: currentEmpDescriptorRef.current,
          }),
        });
        const dataBio = await resBio.json();
        if (!dataBio.success) {
          // Rollback: Hapus data karyawan yang baru dibuat agar tidak meninggalkan record tanpa biometrik / duplikat
          await fetch(`${API_BASE_URL}/api/employees/${createdEmpId}`, { method: 'DELETE' });
          showToast('Registrasi Biometrik Gagal', dataBio.message, 'error');
          return;
        }
      }

      showToast(
        'Karyawan Berhasil Disimpan',
        `Karyawan ${empName.trim()} (${empNik.trim()}) telah berhasil ditambahkan ke database Supabase!`,
        'success'
      );

      // Reset Form
      setEmpNik('');
      setEmpName('');
      setEmpDept('');
      setPhotoPreview(null);
      setPhotoFileName('Format: JPG, PNG, WEBP (Pastikan 1 Wajah Terlihat Jelas)');
      currentEmpDescriptorRef.current = null;

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
