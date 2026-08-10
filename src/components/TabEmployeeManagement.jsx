import React, { useState, useEffect, useRef } from 'react';
import { API_BASE_URL } from '../config';
import { supabase } from '../supabaseClient';
import { cacheUserMasterVector, cacheGeometricVector } from '../db';
import { Human } from '@vladmandic/human';

const humanConfig = {
  modelBasePath: 'https://cdn.jsdelivr.net/npm/@vladmandic/human/models',
  face: { enabled: true, mesh: true, iris: true, description: true },
  body: { enabled: false },
  hand: { enabled: false },
  object: { enabled: false },
  gesture: { enabled: false },
};
const human = new Human(humanConfig);



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
  const [editUpdateBiometrics, setEditUpdateBiometrics] = useState(false);
  const [editFormMode, setEditFormMode] = useState('camera'); // 'camera' | 'file'
  const [editCameraStatusText, setEditCameraStatusText] = useState('Menunggu Wajah di Kamera...');
  const [editCameraStatusColor, setEditCameraStatusColor] = useState('var(--accent-warning)');
  const [editPhotoPreview, setEditPhotoPreview] = useState(null);
  
  const editCurrentDescriptorRef = useRef(null);
  const editVideoRef = useRef(null);
  const editCanvasRef = useRef(null);
  const editStreamRef = useRef(null);
  const editFileInputRef = useRef(null);

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
            if (!regVideoRef.current || !modelsLoaded) return;

            const video = regVideoRef.current;
            const canvas = regCanvasRef.current;
            if (!video.videoWidth) return;

            const displaySize = { width: video.videoWidth, height: video.videoHeight };
            if (canvas) {
              canvas.width = displaySize.width;
              canvas.height = displaySize.height;
            }

            const result = await human.detect(video);
            
            if (canvas) {
              const ctx = canvas.getContext('2d');
              ctx.clearRect(0, 0, canvas.width, canvas.height);
              
              if (result.face && result.face.length > 0) {
                const face = result.face[0];
                
                // Draw 478 Mesh Points
                if (face.mesh && face.mesh.length > 0) {
                  ctx.fillStyle = 'rgba(0, 255, 0, 0.5)';
                  for (const pt of face.mesh) {
                    ctx.beginPath();
                    ctx.arc(pt[0], pt[1], 1, 0, 2 * Math.PI);
                    ctx.fill();
                  }
                }
                
                if (face.embedding) {
                  currentEmpDescriptorRef.current = Array.from(face.embedding);
                  currentEmpGFVRef.current = []; // Obsolete GFV
                  setCameraStatusText(`✓ Biometrik Wajah Master Terdeteksi [1024-dim Human]`);
                  setCameraStatusColor('var(--accent-success)');
                }
              } else {
                currentEmpDescriptorRef.current = null;
                currentEmpGFVRef.current = null;
                setCameraStatusText('Menunggu Wajah di Kamera...');
                setCameraStatusColor('var(--accent-warning)');
              }
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


  // Live Camera for Edit Face Registration
  useEffect(() => {
    let intervalId = null;

    if (editModalOpen && editUpdateBiometrics && editFormMode === 'camera') {
      async function startEditCamera() {
        try {
          const stream = await navigator.mediaDevices.getUserMedia({
            video: { width: 640, height: 480, facingMode: 'user' },
            audio: false,
          });
          editStreamRef.current = stream;
          if (editVideoRef.current) {
            editVideoRef.current.srcObject = stream;
          }

          intervalId = setInterval(async () => {
            if (!editVideoRef.current || !modelsLoaded) return;
            const video = editVideoRef.current;
            const canvas = editCanvasRef.current;
            if (!video.videoWidth) return;
            const displaySize = { width: video.videoWidth, height: video.videoHeight };
            if (canvas) {
              canvas.width = displaySize.width;
              canvas.height = displaySize.height;
            }
            const result = await human.detect(video);
            if (canvas) {
              const ctx = canvas.getContext('2d');
              ctx.clearRect(0, 0, canvas.width, canvas.height);
              if (result.face && result.face.length > 0) {
                const face = result.face[0];
                if (face.mesh && face.mesh.length > 0) {
                  ctx.fillStyle = 'rgba(0, 255, 0, 0.5)';
                  for (const pt of face.mesh) {
                    ctx.beginPath();
                    ctx.arc(pt[0], pt[1], 1, 0, 2 * Math.PI);
                    ctx.fill();
                  }
                }
                if (face.embedding) {
                  editCurrentDescriptorRef.current = Array.from(face.embedding);
                  setEditCameraStatusText(`✓ Biometrik Wajah Master Terdeteksi [1024-dim Human]`);
                  setEditCameraStatusColor('var(--accent-success)');
                }
              } else {
                editCurrentDescriptorRef.current = null;
                setEditCameraStatusText('Menunggu Wajah di Kamera...');
                setEditCameraStatusColor('var(--accent-warning)');
              }
            }
          }, 200);
        } catch (err) {
          console.error('[REG CAMERA ERROR]:', err);
          setEditCameraStatusText('Gagal Membuka Kamera: ' + err.message);
          setEditCameraStatusColor('var(--accent-error)');
        }
      }
      startEditCamera();
    } else {
      editCurrentDescriptorRef.current = null;
      if (editStreamRef.current) {
        editStreamRef.current.getTracks().forEach((track) => track.stop());
      }
    }

    return () => {
      if (intervalId) clearInterval(intervalId);
      if (editStreamRef.current) {
        editStreamRef.current.getTracks().forEach((track) => track.stop());
      }
    };
  }, [editModalOpen, editUpdateBiometrics, editFormMode, modelsLoaded]);

  // Handle Edit Photo File Upload
  const handleEditPhotoFileUpload = async (e) => {
    const file = e.target.files[0];
    if (!file) return;

    if (!modelsLoaded) {
      showToast('Model Belum Siap', 'Tunggu model AI biometrik dimuat...', 'warning');
      return;
    }

    setEditCameraStatusText('Memproses ekstraksi wajah dari foto...');
    setEditCameraStatusColor('var(--accent-warning)');

    const img = new Image();
    img.src = URL.createObjectURL(file);
    img.onload = async () => {
      setEditPhotoPreview(img.src);
      try {
        const result = await human.detect(img);
        if (result.face && result.face.length > 0 && result.face[0].embedding) {
          editCurrentDescriptorRef.current = Array.from(result.face[0].embedding);
          setEditCameraStatusText(`✓ Wajah Diekstrak dari Foto [1024-dim Human]`);
          setEditCameraStatusColor('var(--accent-success)');
        } else {
          editCurrentDescriptorRef.current = null;
          setEditCameraStatusText('Wajah Tidak Terdeteksi dalam Foto! Gunakan foto lain.');
          setEditCameraStatusColor('var(--accent-error)');
        }
      } catch (err) {
        console.error('[PHOTO EXTRACT ERROR]:', err);
        setEditCameraStatusText('Gagal ekstraksi foto: ' + err.message);
        setEditCameraStatusColor('var(--accent-error)');
      }
    };
  };

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
            currentEmpDescriptorRef.current = Array.from(result.face[0].embedding);
            currentEmpGFVRef.current = []; // Obsolete GFV
            setCameraStatusText(`✓ Wajah Diekstrak dari Foto [1024-dim Human]`);
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

    if (editUpdateBiometrics && !editCurrentDescriptorRef.current) {
      showToast('Peringatan Form', 'Anda memilih Update Biometrik, namun Wajah belum terdeteksi. Silakan deteksi wajah atau matikan opsi Update Biometrik.', 'error');
      return;
    }

    const payload = {
      nik: editingEmp.nik.trim(),
      name: editingEmp.name.trim(),
      department: editingEmp.department.trim(),
    };

    if (editUpdateBiometrics && editCurrentDescriptorRef.current) {
      payload.face_vector = JSON.stringify(editCurrentDescriptorRef.current);
    }

    try {
      let success = false;
      let errorMsg = '';
      
      try {
        const res = await fetch(`${API_BASE_URL}/api/employees/${editingEmp.id}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload),
        });

        if (res.ok) {
          success = true;
        } else {
          const text = await res.text();
          let data = {};
          if (text) { try { data = JSON.parse(text); } catch (e) {} }
          if (data.success) success = true;
          else errorMsg = data.message || `HTTP ${res.status}`;
        }
      } catch (fetchErr) {
        errorMsg = fetchErr.message;
      }

      // Tier 2: Supabase Direct Fallback
      if (!success) {
        console.warn('[EDIT EMP API WARN - FALLING BACK TO SUPABASE DIRECT]:', errorMsg);
        
        // 1. Update employees table
        const { error: empErr } = await supabase
          .from('employees')
          .update({ nik: payload.nik, name: payload.name, department: payload.department })
          .eq('id', editingEmp.id);
        if (empErr) throw empErr;

        // 2. Update master_biometrics if requested
        if (payload.face_vector) {
          const { error: bioErr } = await supabase
            .from('master_biometrics')
            .update({ face_vector: payload.face_vector })
            .eq('employee_id', editingEmp.id);
          if (bioErr) throw bioErr;
          
          // Refresh indexeddb cache
          try {
            await cacheUserMasterVector(editingEmp.id, {
              face_vector: payload.face_vector
            });
          } catch(e) {}
        }
        
        success = true;
      }

      if (success) {
        showToast(
          'Perubahan Disimpan',
          `Data karyawan "${editingEmp.name}" telah berhasil diperbarui.`,
          'success'
        );
        setEditModalOpen(false);
        setEditUpdateBiometrics(false); // Reset
        refreshEmployees();
      } else {
        showToast('Gagal Edit Karyawan', errorMsg, 'error');
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
          let success = false;
          let errorMessage = '';
          
          try {
            const res = await fetch(`${API_BASE_URL}/api/employees/${emp.id}`, { method: 'DELETE' });
            if (res.ok) {
              success = true;
            } else {
              const text = await res.text();
              let data = {};
              if (text) {
                try { data = JSON.parse(text); } catch (e) { console.warn('Failed to parse response:', text); }
              }
              if (data.success) {
                success = true;
              } else {
                errorMessage = data.message || `Error HTTP ${res.status}: Respons kosong`;
              }
            }
          } catch (fetchErr) {
            errorMessage = fetchErr.message;
          }

          // Tier 2: Supabase Direct Fallback
          if (!success) {
            console.warn('[DELETE EMP API WARN - FALLING BACK TO SUPABASE DIRECT]:', errorMessage);
            const { error: delErr } = await supabase.from('employees').delete().eq('id', emp.id);
            if (delErr) {
              throw delErr;
            }
            success = true;
          }
          
          if (success) {
            showToast('Penghapusan Berhasil', `Karyawan "${emp.name}" telah berhasil dihapus.`, 'success');
            refreshEmployees();
          } else {
            showToast('Gagal Menghapus', errorMessage, 'error');
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
              
              {/* Optional Biometric Re-Scan */}
              <div className="form-group" style={{ marginTop: '1rem', background: 'rgba(0,0,0,0.2)', padding: '10px', borderRadius: '8px' }}>
                <label style={{ display: 'flex', alignItems: 'center', gap: '8px', cursor: 'pointer', marginBottom: editUpdateBiometrics ? '10px' : '0' }}>
                  <input 
                    type="checkbox" 
                    checked={editUpdateBiometrics} 
                    onChange={(e) => setEditUpdateBiometrics(e.target.checked)} 
                  />
                  <span>Perbarui Biometrik Wajah? (Re-Scan)</span>
                </label>

                {editUpdateBiometrics && (
                  <div style={{ marginTop: '10px', borderTop: '1px solid rgba(255,255,255,0.1)', paddingTop: '10px' }}>
                    <div style={{ display: 'flex', gap: '8px', marginBottom: '10px' }}>
                      <button
                        type="button"
                        className={`btn ${editFormMode === 'camera' ? 'btn-primary' : ''}`}
                        style={{ padding: '6px 12px', fontSize: '0.8rem', background: editFormMode !== 'camera' ? 'rgba(255,255,255,0.1)' : '' }}
                        onClick={() => setEditFormMode('camera')}
                      >
                        <i className="fa-solid fa-video"></i> Gunakan Kamera
                      </button>
                      <button
                        type="button"
                        className={`btn ${editFormMode === 'file' ? 'btn-primary' : ''}`}
                        style={{ padding: '6px 12px', fontSize: '0.8rem', background: editFormMode !== 'file' ? 'rgba(255,255,255,0.1)' : '' }}
                        onClick={() => setEditFormMode('file')}
                      >
                        <i className="fa-solid fa-upload"></i> Unggah File Foto
                      </button>
                    </div>

                    {editFormMode === 'camera' ? (
                      <div className="webcam-wrapper" style={{ aspectRatio: '4/3', borderRadius: '6px' }}>
                        <video ref={editVideoRef} autoPlay muted playsInline></video>
                        <canvas ref={editCanvasRef} className="overlay-canvas"></canvas>
                      </div>
                    ) : (
                      <div style={{ background: 'rgba(15,23,42,0.8)', border: '1px dashed var(--border-color)', padding: '1rem', borderRadius: '6px', textAlign: 'center' }}>
                        <input
                          type="file"
                          ref={editFileInputRef}
                          accept="image/*"
                          style={{ display: 'none' }}
                          onChange={handleEditPhotoFileUpload}
                        />
                        <button
                          type="button"
                          className="btn"
                          style={{ background: 'rgba(99,102,241,0.2)', border: '1px solid var(--accent-primary)', width: 'auto' }}
                          onClick={() => editFileInputRef.current.click()}
                        >
                          <i className="fa-solid fa-image"></i> Pilih Foto Baru
                        </button>
                        {editPhotoPreview && (
                          <img
                            src={editPhotoPreview}
                            style={{ maxWidth: '100%', maxHeight: '150px', borderRadius: '8px', marginTop: '10px' }}
                            alt="Preview Foto Baru"
                          />
                        )}
                      </div>
                    )}

                    <div style={{ marginTop: '8px', fontSize: '0.82rem', display: 'flex', justifyContent: 'space-between', background: 'rgba(0,0,0,0.3)', padding: '6px 10px', borderRadius: '6px' }}>
                      <span>Status Biometrik Baru:</span>
                      <strong style={{ color: editCameraStatusColor }}>{editCameraStatusText}</strong>
                    </div>
                  </div>
                )}
              </div>

              <div style={{ display: 'flex', gap: '10px', marginTop: '1.5rem' }}>
                <button
                  type="button"
                  className="btn"
                  style={{ background: 'rgba(255,255,255,0.1)', color: '#fff' }}
                  onClick={() => {
                    setEditModalOpen(false);
                    setEditUpdateBiometrics(false);
                  }}
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
