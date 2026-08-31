import React, { useState, useRef, useEffect, useMemo } from 'react';
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "../components/ui/table";
import { Edit2, Trash2, FileSpreadsheet, FileDown, Plus, Upload } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import { Filesystem, Directory } from '@capacitor/filesystem';
import { Share } from '@capacitor/share';
import { API_BASE_URL } from '../config';
import { supabase } from '../supabaseClient';
import { cacheUserMasterVector, getAllMasterVectors, cosineSimilarity, deleteLocalEmployee } from '../db';
import { useNormalizedFaceMesh } from '../hooks/useNormalizedFaceMesh';
import { human } from '../humanSingleton';
import { useAuth } from '../context/AuthContext';

export default function DaftarKaryawanPage({ employees, modelsLoaded, showToast, refreshEmployees, refreshLogs, openConfirmModal }) {
  const navigate = useNavigate();

  // Filters State
  const { user } = useAuth();
  const [searchQuery, setSearchQuery] = useState('');
  const [filterStatusTk, setFilterStatusTk] = useState('');
  const [filterStatusPerkawinan, setFilterStatusPerkawinan] = useState('');
  const [filterKebun, setFilterKebun] = useState('');

  // List of all kebuns from regional CSV data
  const allKebunsFromCSV = useMemo(() => [
    'Bukit Harapan I',
    'Bukit Harapan II',
    'Parsub',
    'Patogu Janji',
    'Panca Agro Lestari (Pal)',
    'Wana Jingga Timur (Wjt)',
    'Duta Palma Nusantara (Dpn) I',
    'Duta Palma Nusantara (Dpn) II',
    'Duta Palma Nusantara (Dpn) III',
    'Eluan Mahkota (EMA) - KT',
    'Johan Sentosa',
    'Palma Inti Lestari (PIL)',
    'Bukit Jago Indah (BJI)',
    'Kaliau Mas Perkasa A (KMP A)',
    'Kaliau Mas Perkasa B (KMP B)',
    'Teluk Keramat (TKR)',
    'Wana Hijau Semesta I (WHS I)',
    'Wana Hijau Semesta II (L1)',
    'Wana Hijau Semesta II (L2)',
    'Wana Hijau Semesta II (WHS II)',
    'Wana Hijau Semesta III (WHS III)',
    'Wana Hijau Semesta IV',
    'Mitra Wawasan (MWS)',
    'Persada Alam (PA)',
    'Darmex - I',
    'Darmex - II',
    'Darmex - X'
  ], []);

  // Compute allowed kebuns based on logged-in admin's role & region
  const availableKebuns = useMemo(() => {
    const dynamicKebuns = [...new Set(employees.map(e => e.nama_kebun).filter(Boolean))];
    if (user?.role === 'headoffice_admin') {
      const merged = [...new Set([...dynamicKebuns, ...allKebunsFromCSV])];
      merged.sort();
      return merged;
    } else if (user?.role === 'regional_admin') {
      const regionKebuns = {
        'Sumut 2': ['Bukit Harapan I', 'Bukit Harapan II', 'Parsub', 'Patogu Janji'],
        'Riau 1': ['Panca Agro Lestari (Pal)', 'Wana Jingga Timur (Wjt)', 'Duta Palma Nusantara (Dpn) I', 'Duta Palma Nusantara (Dpn) II', 'Duta Palma Nusantara (Dpn) III', 'Eluan Mahkota (EMA) - KT', 'Johan Sentosa', 'Palma Inti Lestari (PIL)'],
        'Kalbar 1A': ['Bukit Jago Indah (BJI)', 'Kaliau Mas Perkasa A (KMP A)', 'Kaliau Mas Perkasa B (KMP B)', 'Teluk Keramat (TKR)', 'Wana Hijau Semesta I (WHS I)', 'Wana Hijau Semesta II (L1)', 'Wana Hijau Semesta II (L2)', 'Wana Hijau Semesta II (WHS II)', 'Wana Hijau Semesta III (WHS III)', 'Wana Hijau Semesta IV'],
        'Kalbar 1B': ['Mitra Wawasan (MWS)', 'Persada Alam (PA)', 'Darmex - I', 'Darmex - II', 'Darmex - X']
      };
      const allowed = regionKebuns[user.region] || [];
      const merged = [...new Set([...dynamicKebuns, ...allowed])];
      merged.sort();
      return merged;
    }
    dynamicKebuns.sort();
    return dynamicKebuns;
  }, [employees, user, allKebunsFromCSV]);
  // Edit Modal State
  const [editModalOpen, setEditModalOpen] = useState(false);
  const [editingEmp, setEditingEmp] = useState({ id: '', nik: '', name: '', department: '', afdeling: '', nama_kebun: '', status_tk: '', jabatan: '', status_perkawinan: '' });
  const [editStatusTkCustom, setEditStatusTkCustom] = useState('');
  const [editUpdateBiometrics, setEditUpdateBiometrics] = useState(false);
  const [editFormMode, setEditFormMode] = useState('camera'); // 'camera' | 'file'
  const [editCameraStatusText, setEditCameraStatusText] = useState('Menunggu Wajah di Kamera...');
  const [editCameraStatusColor, setEditCameraStatusColor] = useState('var(--accent-warning)');
  const [editPhotoPreview, setEditPhotoPreview] = useState(null);
  // null = belum dicek | { isDuplicate, matchedName, similarity } = hasil cek duplikasi
  const [editFaceCheckResult, setEditFaceCheckResult] = useState(null);
  const EDIT_DUPLICATE_THRESHOLD = 0.85;
  
  const editCurrentDescriptorRef = useRef(null);
  const editVideoRef = useRef(null);
  const editCanvasRef = useRef(null);
  const editStreamRef = useRef(null);
  const editFileInputRef = useRef(null);

  // Injected detection callback untuk human.js di mode Edit Kamera
  const detectEditFacesCallback = React.useCallback(async (croppedCanvas) => {
    if (!modelsLoaded) return null;
    if (human.config?.face?.description) {
      human.config.face.description.enabled = true; // FORCE ENABLE: Pastikan embedding selalu diekstrak saat edit
    }
    const result = await human.detect(croppedCanvas);
    return result?.face?.[0] ?? null;
  }, [modelsLoaded]);

  // Callback saat wajah diproses
  const onEditFaceProcessed = React.useCallback(({ detection, smoothedMesh, ctx }) => {
    if (detection.embedding) {
      const newVec = Array.from(detection.embedding);
      editCurrentDescriptorRef.current = newVec;

      // Cek duplikasi real-time, kecuali diri sendiri (editingEmp.id)
      (async () => {
        try {
          const allMasters = await getAllMasterVectors();
          let bestSim = 0, bestName = '';
          for (const m of allMasters) {
            if (String(m.employee_id) === String(editingEmp.id)) continue; // skip self
            const vec = m.descriptor_json;
            if (!Array.isArray(vec) || vec.length !== 1024) continue;
            const sim = cosineSimilarity(newVec, vec);
            if (sim > bestSim) { bestSim = sim; bestName = m.name; }
          }
          if (bestSim >= EDIT_DUPLICATE_THRESHOLD) {
            setEditFaceCheckResult({ isDuplicate: true, matchedName: bestName, similarity: bestSim });
            setEditCameraStatusText(`⚠️ WAJAH SUDAH TERDAFTAR: ${bestName} (${(bestSim * 100).toFixed(1)}%)`);
            setEditCameraStatusColor('var(--accent-error)');
          } else {
            setEditFaceCheckResult({ isDuplicate: false, matchedName: '', similarity: bestSim });
            setEditCameraStatusText(`✓ Wajah Baru Valid [Belum Digunakan Karyawan Lain]`);
            setEditCameraStatusColor('var(--accent-success)');
          }
        } catch (_) {
          setEditFaceCheckResult(null);
          setEditCameraStatusText(`✓ Wajah Terdeteksi [1024-dim Human]`);
          setEditCameraStatusColor('var(--accent-success)');
        }
      })();
    }

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
  }, [editingEmp.id, EDIT_DUPLICATE_THRESHOLD]);

  // Callback saat tidak ada wajah
  const onEditNoFace = React.useCallback(() => {
    editCurrentDescriptorRef.current = null;
    setEditFaceCheckResult(null);
    setEditCameraStatusText('Menunggu Wajah di Kamera...');
    setEditCameraStatusColor('var(--accent-warning)');
  }, []);

  // Callback kamera error
  const onEditCameraError = React.useCallback((err) => {
    setEditCameraStatusText('Kamera Tidak Bisa Diakses');
    setEditCameraStatusColor('var(--accent-danger)');
  }, []);

  useNormalizedFaceMesh({
    videoRef: editVideoRef,
    canvasRef: editCanvasRef,
    active: editModalOpen && editUpdateBiometrics && editFormMode === 'camera' && modelsLoaded,
    facingMode: 'user',
    smoothAlpha: 0.35,
    detectFaces: detectEditFacesCallback,
    onFaceProcessed: onEditFaceProcessed,
    onNoFace: onEditNoFace,
    onCameraError: onEditCameraError,
  });

  // Handle Edit Photo File
  const handleEditPhotoFileUpload = (e) => {
    const file = e.target.files[0];
    if (!file) return;

    if (!modelsLoaded) {
      showToast('Model Belum Siap', 'Tunggu hingga AI Face Recognition selesai dimuat.', 'warning');
      return;
    }

    const imgUrl = URL.createObjectURL(file);
    setEditPhotoPreview(imgUrl);
    setEditCameraStatusText('Menganalisis Wajah dari Foto...');
    setEditCameraStatusColor('var(--accent-warning)');
    
    const img = new Image();
    img.crossOrigin = 'anonymous';
    img.onload = async () => {
      try {
        if (human.config?.face?.description) {
          human.config.face.description.enabled = true; // FORCE ENABLE: Pastikan embedding diekstrak dari upload foto saat edit
        }
        const result = await human.detect(img);
        if (result.face && result.face.length > 0 && result.face[0].embedding) {
          const newVec = Array.from(result.face[0].embedding);
          // Cek duplikasi, skip diri sendiri
          try {
            const allMasters = await getAllMasterVectors();
            let bestSim = 0, bestName = '';
            for (const m of allMasters) {
              if (String(m.employee_id) === String(editingEmp.id)) continue;
              const vec = m.descriptor_json;
              if (!Array.isArray(vec) || vec.length !== 1024) continue;
              const sim = cosineSimilarity(newVec, vec);
              if (sim > bestSim) { bestSim = sim; bestName = m.name; }
            }
            editCurrentDescriptorRef.current = newVec;
            if (bestSim >= EDIT_DUPLICATE_THRESHOLD) {
              setEditFaceCheckResult({ isDuplicate: true, matchedName: bestName, similarity: bestSim });
              setEditCameraStatusText(`⚠️ WAJAH SUDAH TERDAFTAR: ${bestName} (${(bestSim * 100).toFixed(1)}%)`);
              setEditCameraStatusColor('var(--accent-error)');
            } else {
              setEditFaceCheckResult({ isDuplicate: false, matchedName: '', similarity: bestSim });
              setEditCameraStatusText('✓ Wajah Diekstrak dari Foto [Valid — Belum Digunakan Lain]');
              setEditCameraStatusColor('var(--accent-success)');
            }
          } catch (_) {
            editCurrentDescriptorRef.current = newVec;
            setEditFaceCheckResult(null);
            setEditCameraStatusText('✓ Wajah Diekstrak dari Foto [1024-dim Human]');
            setEditCameraStatusColor('var(--accent-success)');
          }
        } else {
          editCurrentDescriptorRef.current = null;
          setEditFaceCheckResult(null);
          setEditCameraStatusText('Wajah Tidak Ditemukan! Coba foto lain.');
          setEditCameraStatusColor('var(--accent-danger)');
        }
      } catch (err) {
        setEditCameraStatusText('Error menganalisis foto.');
        setEditCameraStatusColor('var(--accent-danger)');
      }
    };
    img.src = imgUrl;
  };

  // Open Edit Modal
  const openEditModal = (emp) => {
    setEditingEmp({
      id: emp.id,
      nik: emp.nik || '',
      name: emp.name || '',
      department: emp.department || '',
      afdeling: emp.afdeling || '',
      nama_kebun: emp.nama_kebun || '',
      status_tk: emp.status_tk || '',
      jabatan: emp.jabatan || emp.department || '',
      status_perkawinan: emp.status_perkawinan || '',
    });
    setEditStatusTkCustom(emp.status_tk && !['BHL', 'Karyawan Tetap (PKWTT)', 'Karyawan Kontrak (PKWT)'].includes(emp.status_tk) ? emp.status_tk : '');
    setEditUpdateBiometrics(false);
    setEditPhotoPreview(null);
    editCurrentDescriptorRef.current = null;
    setEditFaceCheckResult(null);
    setEditModalOpen(true);
  };

  // Submit Edit Form
  const handleEditSubmit = async (e) => {
    e.preventDefault();
    if (!editingEmp.nik.trim() || !editingEmp.name.trim() || !editingEmp.jabatan.trim()) {
      showToast('Peringatan Form', 'Mohon lengkapi NIK, Nama, dan Jabatan!', 'error');
      return;
    }

    if (editUpdateBiometrics && !editCurrentDescriptorRef.current) {
      showToast('Peringatan Form', 'Anda memilih Update Biometrik, namun Wajah belum terdeteksi.', 'error');
      return;
    }

    // Blokir jika wajah yang baru terdeteksi sebagai duplikasi karyawan lain
    if (editUpdateBiometrics && editFaceCheckResult?.isDuplicate) {
      showToast(
        'Wajah Sudah Terdaftar',
        `Wajah ini sudah digunakan oleh "${editFaceCheckResult.matchedName}". Satu wajah hanya boleh untuk satu karyawan.`,
        'error'
      );
      return;
    }

    // Double-check saat submit jika ada vektor baru
    if (editUpdateBiometrics && editCurrentDescriptorRef.current) {
      try {
        const allMasters = await getAllMasterVectors();
        for (const m of allMasters) {
          if (String(m.employee_id) === String(editingEmp.id)) continue; // skip self
          const vec = m.descriptor_json;
          if (!Array.isArray(vec) || vec.length !== 1024) continue;
          const sim = cosineSimilarity(editCurrentDescriptorRef.current, vec);
          if (sim >= EDIT_DUPLICATE_THRESHOLD) {
            showToast(
              'Wajah Sudah Terdaftar',
              `Wajah ini sudah digunakan oleh "${m.name}" (NIK: ${m.nik}). Similaritas: ${(sim * 100).toFixed(1)}%.`,
              'error'
            );
            return;
          }
        }
      } catch (dupErr) {
        console.warn('[Edit Duplicate Check Error]:', dupErr);
      }
    }

    const payload = {
      nik: editingEmp.nik.trim(),
      name: editingEmp.name.trim(),
      department: editingEmp.jabatan.trim(),
      afdeling: editingEmp.afdeling.trim(),
      nama_kebun: editingEmp.nama_kebun.trim(),
      status_tk: editingEmp.status_tk === 'Lainnya...' ? editStatusTkCustom.trim() : editingEmp.status_tk,
      jabatan: editingEmp.jabatan.trim(),
      status_perkawinan: editingEmp.status_perkawinan,
    };

    const descriptorJson = editUpdateBiometrics ? JSON.stringify(editCurrentDescriptorRef.current) : null;

    try {
      const { error: empErr } = await supabase.from('employees').update(payload).eq('id', editingEmp.id);
      if (empErr) throw empErr;

      if (descriptorJson) {
        const { error: descErr } = await supabase
          .from('master_descriptors')
          .upsert({ employee_id: editingEmp.id, descriptor_json: descriptorJson }, { onConflict: 'employee_id' });
        if (descErr) throw descErr;

        await cacheUserMasterVector({
          employee_id: editingEmp.id,
          nik: editingEmp.nik || '',
          name: editingEmp.name || '',
          department: editingEmp.department || editingEmp.jabatan || '',
          descriptor_json: editCurrentDescriptorRef.current,
        });
      }

      showToast('Berhasil', 'Data karyawan berhasil diperbarui.', 'success');
      setEditModalOpen(false);
      refreshEmployees();
    } catch (err) {
      showToast('Error', err.message, 'error');
    }
  };

  const handleDeleteClick = (emp) => {
    openConfirmModal({
      title: 'Hapus Karyawan',
      message: `Anda yakin ingin menghapus "${emp.name}"? Data biometrik dan log absensi terkait karyawan ini juga akan terhapus.`,
      confirmText: 'Ya, Hapus Data',
      onConfirm: async () => {
        try {
          // 1. Hapus dari database cloud Supabase
          const { error: delErr } = await supabase.from('employees').delete().eq('id', emp.id);
          if (delErr) throw delErr;

          // 2. Bersihkan cache biometrik lokal (IndexedDB / SQLite) agar wajah bisa didaftarkan ulang
          await deleteLocalEmployee(emp.id);
          
          showToast('Penghapusan Berhasil', 'Sukses menghapus karyawan.', 'success');
          refreshEmployees();
          if (refreshLogs) refreshLogs();
        } catch (err) {
          showToast('Error Sistem', err.message, 'error');
        }
      },
    });
  };

  // CSV & Photos Bulk Import States
  const [importModalOpen, setImportModalOpen] = useState(false);
  const [csvFile, setCsvFile] = useState(null);
  const [photoFiles, setPhotoFiles] = useState([]);
  const [importing, setImporting] = useState(false);
  const [importProgress, setImportProgress] = useState({ current: 0, total: 0, status: '' });

  // Parse CSV function supporting comma and semicolon
  const parseCSV = (text) => {
    const lines = text.split(/\r?\n/).filter(line => line.trim() !== '');
    if (lines.length === 0) return [];
    
    const delimiter = lines[0].includes(';') ? ';' : ',';
    const headers = lines[0].split(delimiter).map(h => h.trim().toLowerCase().replace(/^"|"$/g, ''));
    
    const result = [];
    for (let i = 1; i < lines.length; i++) {
      const values = lines[i].split(delimiter).map(v => v.trim().replace(/^"|"$/g, ''));
      if (values.length < headers.length) continue;
      
      const obj = {};
      headers.forEach((header, index) => {
        obj[header] = values[index];
      });
      result.push(obj);
    }
    return result;
  };

  // Convert File to Embedding
  const processImageFileForEmbedding = (file) => {
    return new Promise((resolve) => {
      const imgUrl = URL.createObjectURL(file);
      const img = new Image();
      img.crossOrigin = 'anonymous';
      img.onload = async () => {
        try {
          if (human.config?.face?.description) {
            human.config.face.description.enabled = true;
          }
          const result = await human.detect(img);
          URL.revokeObjectURL(imgUrl);
          if (result.face && result.face.length > 0 && result.face[0].embedding) {
            resolve(Array.from(result.face[0].embedding));
          } else {
            resolve(null);
          }
        } catch (err) {
          console.error('Error detecting face in imported photo:', err);
          URL.revokeObjectURL(imgUrl);
          resolve(null);
        }
      };
      img.onerror = () => {
        URL.revokeObjectURL(imgUrl);
        resolve(null);
      };
      img.src = imgUrl;
    });
  };

  const handleStartImport = async () => {
    if (!csvFile) {
      showToast('File CSV Kosong', 'Harap pilih file CSV terlebih dahulu.', 'error');
      return;
    }
    
    setImporting(true);
    setImportProgress({ current: 0, total: 0, status: 'Membaca file CSV...' });
    
    try {
      const csvText = await csvFile.text();
      const rows = parseCSV(csvText);
      if (rows.length === 0) {
        showToast('CSV Kosong', 'Tidak ada data karyawan yang valid di dalam file CSV.', 'error');
        setImporting(false);
        return;
      }
      
      setImportProgress({ current: 0, total: rows.length, status: 'Memulai pencocokan foto...' });
      
      let successCount = 0;
      let failCount = 0;
      
      for (let i = 0; i < rows.length; i++) {
        const row = rows[i];
        const nik = row.nik || row.employee_id;
        const name = row.name || row.nama;
        
        if (!nik || !name) {
          failCount++;
          continue;
        }
        
        setImportProgress(prev => ({
          ...prev,
          current: i + 1,
          status: `Memproses ${name} (NIK: ${nik})...`
        }));
        
        // Find matching photo
        const photoFile = photoFiles.find(file => {
          const baseName = file.name.substring(0, file.name.lastIndexOf('.')) || file.name;
          return baseName.trim() === String(nik).trim();
        });
        
        let descriptorJson = null;
        let hasMasterBiometric = false;
        
        if (photoFile) {
          const embedding = await processImageFileForEmbedding(photoFile);
          if (embedding) {
            descriptorJson = JSON.stringify(embedding);
            hasMasterBiometric = true;
          }
        }
        
        const employeePayload = {
          nik: String(nik).trim(),
          name: String(name).trim(),
          department: row.jabatan || row.department || 'Pekerja',
          afdeling: row.afdeling || '',
          nama_kebun: row.nama_kebun || row.kebun || '',
          status_tk: row.status_tk || 'BHL',
          jabatan: row.jabatan || row.department || 'Pekerja',
          status_perkawinan: row.status_perkawinan || 'Lajang',
          has_master_biometric: hasMasterBiometric
        };
        
        try {
          // 1. Insert/Upsert into Supabase
          const { data: insertedEmp, error: dbErr } = await supabase
            .from('employees')
            .upsert(employeePayload, { onConflict: 'nik' })
            .select('id')
            .single();
            
          if (dbErr) throw dbErr;
          
          const employeeId = insertedEmp?.id;
          
          // 2. If biometrics are ready, upsert descriptor
          if (employeeId && descriptorJson) {
            const { error: descErr } = await supabase
              .from('master_descriptors')
              .upsert({ employee_id: employeeId, descriptor_json: descriptorJson });
            if (descErr) console.warn('Warning: Failed to save master descriptor:', descErr.message);
          }
          
          // 3. Cache locally in SQLite/IndexedDB
          await cacheUserMasterVector({
            employee_id: employeeId,
            nik: employeePayload.nik,
            name: employeePayload.name,
            department: employeePayload.jabatan,
            afdeling: employeePayload.afdeling,
            nama_kebun: employeePayload.nama_kebun,
            status_tk: employeePayload.status_tk,
            jabatan: employeePayload.jabatan,
            status_perkawinan: employeePayload.status_perkawinan,
            descriptor_json: descriptorJson,
            has_master_biometric: hasMasterBiometric
          });
          
          successCount++;
        } catch (err) {
          console.error(`Gagal menyimpan karyawan NIK: ${nik}`, err);
          failCount++;
        }
      }
      
      showToast('Import Selesai', `Berhasil mengimpor ${successCount} karyawan. Gagal: ${failCount}`, 'success');
      refreshEmployees();
      setImportModalOpen(false);
      setCsvFile(null);
      setPhotoFiles([]);
    } catch (err) {
      console.error('Import CSV error:', err);
      showToast('Gagal Import', `Terjadi kesalahan saat memproses CSV: ${err.message}`, 'error');
    } finally {
      setImporting(false);
    }
  };

  // ---------------------------------
  // Filter Logic
  // ---------------------------------
  const filteredEmployees = employees.filter((emp) => {
    const matchesSearch =
      emp.nik?.toLowerCase().includes(searchQuery.toLowerCase()) ||
      emp.name?.toLowerCase().includes(searchQuery.toLowerCase());
      
    const matchesStatusTk = filterStatusTk ? emp.status_tk === filterStatusTk : true;
    const matchesStatusPerkawinan = filterStatusPerkawinan ? emp.status_perkawinan === filterStatusPerkawinan : true;
    const matchesKebun = filterKebun ? emp.nama_kebun === filterKebun : true;

    return matchesSearch && matchesStatusTk && matchesStatusPerkawinan && matchesKebun;
  });

  // ---------------------------------
  // ---------------------------------
  // Export Logic (Formatted Excel & PDF Printout)
  // ---------------------------------
  const exportToCSV = () => {
    if (filteredEmployees.length === 0) {
      showToast('Data Kosong', 'Tidak ada data untuk diekspor.', 'warning');
      return;
    }
    
    const headers = ['NIK', 'Nama', 'Afdeling', 'Nama Kebun', 'Jabatan', 'Status TK', 'Status Pernikahan', 'Biometrik Siap'];
    
    const rows = filteredEmployees.map(emp => [
      emp.nik || '',
      emp.name || '',
      emp.afdeling || '',
      emp.nama_kebun || '',
      emp.jabatan || emp.department || '',
      emp.status_tk || '',
      emp.status_perkawinan || '',
      emp.has_master_biometric ? 'Ya' : 'Tidak'
    ]);

    const tableHtml = `
      <table border="1">
        <thead>
          <tr style="height: 30px;">
            ${headers.map(h => `<th style="background-color: #46bdc6; color: #ffffff; font-family: 'Consolas'; font-size: 14pt; font-weight: bold; text-align: center; vertical-align: middle; padding: 5px; border: 1px solid #ddd;">${h}</th>`).join('')}
          </tr>
        </thead>
        <tbody>
          ${rows.map(row => `
            <tr style="height: 24px;">
              ${row.map(cell => `<td style="font-family: 'Consolas'; font-size: 14pt; text-align: center; vertical-align: middle; padding: 5px; border: 1px solid #ddd;">${cell}</td>`).join('')}
            </tr>
          `).join('')}
        </tbody>
      </table>
    `;

    const template = `
      <html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
        <head>
          <!--[if gte mso 9]>
          <xml>
            <x:ExcelWorkbook>
              <x:ExcelWorksheets>
                <x:ExcelWorksheet>
                  <x:Name>Data Karyawan</x:Name>
                  <x:WorksheetOptions>
                    <x:Selected/>
                    <x:FreezePanes/>
                    <x:SplitHorizontal>1</x:SplitHorizontal>
                    <x:SplitHorizontal>1</x:SplitHorizontal>
                    <x:TopRowBottomPane>1</x:TopRowBottomPane>
                    <x:ActivePane>2</x:ActivePane>
                  </x:WorksheetOptions>
                </x:ExcelWorksheet>
              </x:ExcelWorksheets>
            </x:ExcelWorkbook>
          </xml>
          <![endif]-->
          <meta http-equiv="content-type" content="text/plain; charset=UTF-8"/>
        </head>
        <body>
          ${tableHtml}
        </body>
      </html>
    `;

    const fileName = `Data_Karyawan_${new Date().toISOString().split('T')[0]}.xls`;

    if (typeof window !== 'undefined' && window.Capacitor && window.Capacitor.getPlatform() === 'android') {
      try {
        const blob = new Blob([template], { type: 'application/vnd.ms-excel' });
        const reader = new FileReader();
        reader.readAsDataURL(blob);
        reader.onloadend = async () => {
          try {
            const base64Data = reader.result.split(',')[1];
            const fileResult = await Filesystem.writeFile({
              path: fileName,
              data: base64Data,
              directory: Directory.Cache
            });
            await Share.share({
              title: 'Bagikan File Excel',
              text: `File Excel Karyawan: ${fileName}`,
              url: fileResult.uri,
              dialogTitle: 'Kirim / Simpan Excel'
            });
          } catch (err) {
            showToast('Gagal Simpan', `Error: ${err.message}`, 'error');
          }
        };
      } catch (err) {
        showToast('Gagal Ekspor', `Error: ${err.message}`, 'error');
      }
      return;
    }

    const blob = new Blob([template], { type: 'application/vnd.ms-excel' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.setAttribute("href", url);
    link.setAttribute("download", fileName);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  const exportToPDF = () => {
    const headers = ['NIK', 'Nama', 'Afdeling', 'Nama Kebun', 'Jabatan', 'Status TK', 'Status Pernikahan'];
    const rows = filteredEmployees.map(emp => [
      emp.nik || '',
      emp.name || '',
      emp.afdeling || '',
      emp.nama_kebun || '',
      emp.jabatan || emp.department || '',
      emp.status_tk || '',
      emp.status_perkawinan || ''
    ]);

    const printHtml = `
      <!DOCTYPE html>
      <html>
        <head>
          <meta charset="utf-8">
          <title>Cetak Data Karyawan</title>
          <style>
            @page {
              size: landscape;
              margin: 10mm;
            }
            body {
              margin: 0;
              padding: 0;
              font-family: 'Consolas', Courier, monospace;
              font-size: 14px;
            }
            h3 {
              text-align: center;
              margin-bottom: 20px;
            }
            table {
              width: 100%;
              border-collapse: collapse;
            }
            th, td {
              border: 1px solid #000;
              padding: 8px;
              text-align: center;
              vertical-align: middle;
            }
            th {
              background-color: #46bdc6 !important;
              color: white !important;
              font-weight: bold;
              -webkit-print-color-adjust: exact;
              print-color-adjust: exact;
            }
          </style>
        </head>
        <body>
          <h3>Data Master Karyawan</h3>
          <table>
            <thead>
              <tr>
                ${headers.map(h => `<th>${h}</th>`).join('')}
              </tr>
            </thead>
            <tbody>
              ${rows.map(row => `
                <tr>
                  ${row.map(cell => `<td>${cell}</td>`).join('')}
                </tr>
              `).join('')}
            </tbody>
          </table>
          <script>
            window.onload = function() {
              window.print();
            }
          </script>
        </body>
      </html>
    `;

    const fileName = `Cetak_Data_Karyawan_${new Date().toISOString().split('T')[0]}.html`;

    if (typeof window !== 'undefined' && window.Capacitor && window.Capacitor.getPlatform() === 'android') {
      try {
        const blob = new Blob([printHtml], { type: 'text/html' });
        const reader = new FileReader();
        reader.readAsDataURL(blob);
        reader.onloadend = async () => {
          try {
            const base64Data = reader.result.split(',')[1];
            const fileResult = await Filesystem.writeFile({
              path: fileName,
              data: base64Data,
              directory: Directory.Cache
            });
            await Share.share({
              title: 'Cetak Laporan PDF',
              text: `Buka file ini di Chrome HP untuk mencetak ke PDF`,
              url: fileResult.uri,
              dialogTitle: 'Kirim / Buka File Cetak'
            });
          } catch (err) {
            showToast('Gagal Cetak', `Error: ${err.message}`, 'error');
          }
        };
      } catch (err) {
        showToast('Gagal Cetak', `Error: ${err.message}`, 'error');
      }
      return;
    }

    window.print();
  };

  return (
    <div style={{ width: '100%', padding: '1rem', boxSizing: 'border-box' }} className="print-container">
      {/* Table & Print Styles */}
      <style>{`
        .freeze-table-header th, 
        .freeze-table-header td,
        .freeze-table-header th *,
        .freeze-table-header td * {
          font-family: Arial, Helvetica, sans-serif !important;
          font-size: 14px !important;
          text-align: center !important;
          vertical-align: middle !important;
        }

        .freeze-table-header th {
          position: sticky !important;
          top: 0 !important;
          background-color: #46bdc6 !important;
          color: #ffffff !important;
          font-weight: bold !important;
          z-index: 5 !important;
          text-transform: uppercase !important;
          -webkit-print-color-adjust: exact !important;
          print-color-adjust: exact !important;
        }
        
        @media print {
          body * {
            visibility: hidden;
          }
          .print-area, .print-area * {
            visibility: visible;
          }
          .print-area {
            position: absolute;
            left: 0;
            top: 0;
            width: 100% !important;
            max-width: 100% !important;
            margin: 0 !important;
            padding: 0 !important;
            border: none !important;
            box-shadow: none !important;
          }
          .no-print {
            display: none !important;
          }
          .table-container {
            max-height: none !important;
            overflow: visible !important;
            height: auto !important;
          }
          .freeze-table-header th {
            background-color: #46bdc6 !important;
            color: #ffffff !important;
            -webkit-print-color-adjust: exact !important;
            print-color-adjust: exact !important;
          }
          .freeze-table-header th, 
          .freeze-table-header td,
          .freeze-table-header th *,
          .freeze-table-header td * {
            font-family: 'Consolas', Courier, monospace !important;
            font-size: 14px !important;
            text-align: center !important;
            vertical-align: middle !important;
            white-space: normal !important;
            word-break: break-word !important;
          }
          table {
            width: 100% !important;
            border-collapse: collapse !important;
          }
          tr {
            page-break-inside: avoid !important;
            break-inside: avoid !important;
          }
          thead {
            display: table-header-group !important;
          }
          @page {
            size: landscape;
            margin: 10mm;
          }
        }
      `}</style>
      
      <div className="glass-card print-area" style={{ width: '100%', maxWidth: '100%' }}>
        
        {/* Header Title */}
        <div className="card-title" style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>
          Data Master Karyawan
        </div>
        
        {/* Toolbar & Filters (Hidden when printing) */}
        <div className="no-print" style={{ display: 'flex', flexWrap: 'wrap', gap: '1rem', justifyContent: 'space-between', marginBottom: '1.5rem', alignItems: 'flex-end' }}>
          
          {/* Export & Action Buttons */}
          <div style={{ display: 'flex', gap: '10px', flexWrap: 'wrap' }}>
            <button type="button" className="btn" onClick={exportToCSV} style={{ background: 'var(--bg-secondary)', color: 'var(--text-main)', border: '1px solid var(--border-color)', padding: '8px 16px', display: 'flex', alignItems: 'center', gap: '6px', width: 'auto' }}>
              <FileSpreadsheet size={16} color="#107C41" /> Export Excel
            </button>
            <button type="button" className="btn" onClick={exportToPDF} style={{ background: 'var(--bg-secondary)', color: 'var(--text-main)', border: '1px solid var(--border-color)', padding: '8px 16px', display: 'flex', alignItems: 'center', gap: '6px', width: 'auto' }}>
              <FileDown size={16} color="#E81123" /> Export PDF
            </button>
            <button type="button" className="btn" onClick={() => setImportModalOpen(true)} style={{ background: 'var(--bg-secondary)', color: 'var(--text-main)', border: '1px solid var(--border-color)', padding: '8px 16px', display: 'flex', alignItems: 'center', gap: '6px', width: 'auto' }}>
              <Upload size={16} color="#4f46e5" /> Import CSV + Foto
            </button>
            <button type="button" className="btn btn-primary" onClick={() => navigate('/karyawan')} style={{ padding: '8px 16px', display: 'flex', alignItems: 'center', gap: '6px', width: 'auto' }}>
              <Plus size={16} /> Tambah Karyawan
            </button>
          </div>

          {/* Filters */}
          <div style={{ display: 'flex', gap: '10px', flexWrap: 'wrap', alignItems: 'center' }}>
            <input 
              type="text" 
              placeholder="Cari NIK / Nama..." 
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              style={{ padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'rgba(255,255,255,0.05)', color: 'inherit' }}
            />
            
            {user?.role !== 'estate_admin' && (
              <select 
                value={filterKebun} 
                onChange={(e) => setFilterKebun(e.target.value)}
                style={{ padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'rgba(255,255,255,0.05)', color: 'inherit' }}
              >
                <option value="" style={{ color: '#000' }}>Filter Kebun (Semua)</option>
                {availableKebuns.map(k => (
                  <option key={k} value={k} style={{ color: '#000' }}>{k}</option>
                ))}
              </select>
            )}

            <select 
              value={filterStatusTk} 
              onChange={(e) => setFilterStatusTk(e.target.value)}
              style={{ padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'rgba(255,255,255,0.05)', color: 'inherit' }}
            >
              <option value="" style={{ color: '#000' }}>Filter Status TK (Semua)</option>
              <option value="BHL" style={{ color: '#000' }}>BHL</option>
              <option value="Karyawan Tetap (PKWTT)" style={{ color: '#000' }}>PKWTT</option>
              <option value="Karyawan Kontrak (PKWT)" style={{ color: '#000' }}>PKWT</option>
            </select>

            <select 
              value={filterStatusPerkawinan} 
              onChange={(e) => setFilterStatusPerkawinan(e.target.value)}
              style={{ padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'rgba(255,255,255,0.05)', color: 'inherit' }}
            >
              <option value="" style={{ color: '#000' }}>Filter Perkawinan (Semua)</option>
              <option value="Lajang" style={{ color: '#000' }}>Lajang</option>
              <option value="Menikah" style={{ color: '#000' }}>Menikah</option>
              <option value="Duda/Janda" style={{ color: '#000' }}>Duda/Janda</option>
            </select>
          </div>
        </div>

        <div className="table-container" style={{ marginTop: 0, maxHeight: '550px', overflowY: 'auto', position: 'relative' }}>
          <Table className="freeze-table-header">
            <TableHeader>
              <TableRow>
                <TableHead>NIK</TableHead>
                <TableHead>Nama</TableHead>
                <TableHead>Afdeling</TableHead>
                <TableHead>Nama Kebun</TableHead>
                <TableHead>Jabatan</TableHead>
                <TableHead>Status TK</TableHead>
                <TableHead>Status Pernikahan</TableHead>
                <TableHead className="no-print">Biometrik</TableHead>
                <TableHead className="no-print">Aksi</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {filteredEmployees.length === 0 ? (
                <TableRow>
                  <TableCell colSpan={9} style={{ textAlign: 'center' }}>Tidak ada data yang cocok.</TableCell>
                </TableRow>
              ) : (
                filteredEmployees.map((emp) => (
                  <TableRow key={emp.id}>
                    <TableCell className="font-medium">{emp.nik}</TableCell>
                    <TableCell>{emp.name}</TableCell>
                    <TableCell>{emp.afdeling || '-'}</TableCell>
                    <TableCell>{emp.nama_kebun || '-'}</TableCell>
                    <TableCell>{emp.jabatan || emp.department || '-'}</TableCell>
                    <TableCell>{emp.status_tk || '-'}</TableCell>
                    <TableCell>{emp.status_perkawinan || '-'}</TableCell>
                    <TableCell className="no-print">
                      <div style={{ display: 'flex', flexDirection: 'column', gap: '4px', alignItems: 'center' }}>
                        {emp.has_master_biometric ? (
                          <span className="status-badge success">Siap</span>
                        ) : (
                          <span className="status-badge fail">Belum</span>
                        )}
                        {(emp.is_synced === false || String(emp.id).startsWith('off_')) && (
                          <span className="status-badge warning" style={{ background: '#f59e0b', color: '#000', fontSize: '0.7rem', padding: '2px 6px', fontWeight: 'bold' }} title="Tersimpan di perangkat lokal, akan diunggah otomatis saat terhubung ke internet">
                            ⌛ Pending Sync
                          </span>
                        )}
                      </div>
                    </TableCell>
                    <TableCell className="no-print">
                      <div style={{ display: 'flex', gap: '8px' }}>
                        <button type="button" onClick={() => openEditModal(emp)} style={{ background: 'transparent', border: 'none', cursor: 'pointer' }} title="Edit">
                          <Edit2 size={18} color="var(--accent-cyan)" />
                        </button>
                        <button type="button" onClick={() => handleDeleteClick(emp)} style={{ background: 'transparent', border: 'none', cursor: 'pointer' }} title="Hapus">
                          <Trash2 size={18} color="var(--accent-error)" />
                        </button>
                      </div>
                    </TableCell>
                  </TableRow>
                ))
              )}
            </TableBody>
          </Table>
        </div>
      </div>

      {/* Modal Edit Data Karyawan (Sama seperti sebelumnya) */}
      {editModalOpen && (
        <div style={{ position: 'fixed', inset: 0, background: 'rgba(0,0,0,0.7)', backdropFilter: 'blur(8px)', zIndex: 1000, display: 'flex', alignItems: 'center', justifyContent: 'center', padding: '1rem', overflowY: 'auto' }}>
          <div className="glass-card" style={{ maxWidth: '500px', width: '100%', border: '1px solid var(--accent-primary)', maxHeight: '90vh', overflowY: 'auto' }}>
            <div className="card-title" style={{ display: 'flex', justifyContent: 'space-between' }}>
              <span>Edit Data Karyawan</span>
              <button type="button" style={{ background: 'transparent', border: 'none', color: 'var(--text-muted)', fontSize: '1.2rem', cursor: 'pointer' }} onClick={() => setEditModalOpen(false)}>
                &times;
              </button>
            </div>

            <form onSubmit={handleEditSubmit}>
              <div className="form-group">
                <label>NIK / Employee ID</label>
                <input type="text" value={editingEmp.nik} onChange={(e) => setEditingEmp({ ...editingEmp, nik: e.target.value })} required />
              </div>
              <div className="form-group">
                <label>Nama Lengkap</label>
                <input type="text" value={editingEmp.name} onChange={(e) => setEditingEmp({ ...editingEmp, name: e.target.value })} required />
              </div>
              <div className="form-group">
                <label>Afdeling</label>
                <input type="text" value={editingEmp.afdeling} onChange={(e) => setEditingEmp({ ...editingEmp, afdeling: e.target.value })} />
              </div>
              <div className="form-group">
                <label>Nama Kebun</label>
                <input type="text" value={editingEmp.nama_kebun} onChange={(e) => setEditingEmp({ ...editingEmp, nama_kebun: e.target.value })} />
              </div>
              <div className="form-group">
                <label>Status TK</label>
                <select value={editingEmp.status_tk === 'Lainnya...' ? 'Lainnya...' : (['BHL', 'Karyawan Tetap (PKWTT)', 'Karyawan Kontrak (PKWT)'].includes(editingEmp.status_tk) ? editingEmp.status_tk : (editingEmp.status_tk ? 'Lainnya...' : ''))} onChange={(e) => { const val = e.target.value; setEditingEmp({ ...editingEmp, status_tk: val }); if (val !== 'Lainnya...') setEditStatusTkCustom(''); }}>
                  <option value="">-- Pilih Status TK --</option>
                  <option value="BHL">BHL (Buruh Harian Lepas)</option>
                  <option value="Karyawan Tetap (PKWTT)">Karyawan Tetap (PKWTT)</option>
                  <option value="Karyawan Kontrak (PKWT)">Karyawan Kontrak (PKWT)</option>
                  <option value="Lainnya...">Lainnya...</option>
                </select>
                {(editingEmp.status_tk === 'Lainnya...' || (editingEmp.status_tk && !['BHL', 'Karyawan Tetap (PKWTT)', 'Karyawan Kontrak (PKWT)'].includes(editingEmp.status_tk))) && (
                  <input type="text" placeholder="Status Lainnya..." value={editStatusTkCustom} onChange={(e) => { setEditStatusTkCustom(e.target.value); setEditingEmp({ ...editingEmp, status_tk: 'Lainnya...' }); }} style={{ marginTop: '8px' }} required />
                )}
              </div>
              <div className="form-group">
                <label>Jabatan</label>
                <input type="text" value={editingEmp.jabatan} onChange={(e) => setEditingEmp({ ...editingEmp, jabatan: e.target.value })} required />
              </div>
              <div className="form-group">
                <label>Status Perkawinan</label>
                <select value={editingEmp.status_perkawinan} onChange={(e) => setEditingEmp({ ...editingEmp, status_perkawinan: e.target.value })}>
                  <option value="">-- Pilih Status --</option>
                  <option value="Lajang">Lajang</option>
                  <option value="Menikah">Menikah</option>
                  <option value="Duda/Janda">Duda/Janda</option>
                </select>
              </div>
              
              <div className="form-group" style={{ marginTop: '1rem', background: 'rgba(0,0,0,0.2)', padding: '10px', borderRadius: '8px' }}>
                <label style={{ display: 'flex', alignItems: 'center', gap: '8px', cursor: 'pointer', marginBottom: editUpdateBiometrics ? '10px' : '0' }}>
                  <input type="checkbox" checked={editUpdateBiometrics} onChange={(e) => setEditUpdateBiometrics(e.target.checked)} />
                  <span>Perbarui Biometrik Wajah? (Re-Scan)</span>
                </label>
                {editUpdateBiometrics && (
                  <div style={{ marginTop: '10px', borderTop: '1px solid rgba(255,255,255,0.1)', paddingTop: '10px' }}>
                    <div style={{ display: 'flex', gap: '8px', marginBottom: '10px' }}>
                      <button type="button" className={`btn ${editFormMode === 'camera' ? 'btn-primary' : ''}`} style={{ padding: '6px 12px', fontSize: '0.8rem', background: editFormMode !== 'camera' ? 'var(--bg-primary)' : undefined, color: editFormMode !== 'camera' ? 'var(--text-main)' : '#fff', border: editFormMode !== 'camera' ? '1px solid var(--border-color)' : '1px solid transparent', width: 'auto' }} onClick={() => setEditFormMode('camera')}><i className="fa-solid fa-video"></i> Gunakan Kamera</button>
                      <button type="button" className={`btn ${editFormMode === 'file' ? 'btn-primary' : ''}`} style={{ padding: '6px 12px', fontSize: '0.8rem', background: editFormMode !== 'file' ? 'var(--bg-primary)' : undefined, color: editFormMode !== 'file' ? 'var(--text-main)' : '#fff', border: editFormMode !== 'file' ? '1px solid var(--border-color)' : '1px solid transparent', width: 'auto' }} onClick={() => setEditFormMode('file')}><i className="fa-solid fa-upload"></i> Unggah File Foto</button>
                    </div>
                    {editFormMode === 'camera' ? (
                      <div className="webcam-wrapper" style={{ aspectRatio: '4/3', borderRadius: '6px' }}>
                        <video ref={editVideoRef} autoPlay muted playsInline></video>
                        <canvas ref={editCanvasRef} className="overlay-canvas"></canvas>
                      </div>
                    ) : (
                      <div style={{ background: 'rgba(15,23,42,0.8)', border: '1px dashed var(--border-color)', padding: '1rem', borderRadius: '10px', textAlign: 'center' }}>
                        <input type="file" ref={editFileInputRef} accept="image/*" style={{ display: 'none' }} onChange={handleEditPhotoFileUpload} />
                        <button type="button" className="btn" style={{ background: 'rgba(99,102,241,0.2)', border: '1px solid var(--accent-primary)', width: 'auto' }} onClick={() => editFileInputRef.current && editFileInputRef.current.click()}><i className="fa-solid fa-image"></i> Pilih Foto</button>
                        {editPhotoPreview && <img src={editPhotoPreview} style={{ maxWidth: '100%', maxHeight: '180px', borderRadius: '8px', marginTop: '10px' }} alt="Preview" />}
                      </div>
                    )}
                    <div style={{ marginTop: '8px', fontSize: '0.8rem', display: 'flex', justifyContent: 'space-between', background: 'rgba(0,0,0,0.2)', padding: '6px 10px', borderRadius: '6px' }}>
                      <span>Status Biometrik:</span>
                      <strong style={{ color: editCameraStatusColor }}>{editCameraStatusText}</strong>
                    </div>
                  </div>
                )}
              </div>

              <div style={{ display: 'flex', gap: '10px', marginTop: '1.5rem' }}>
                <button type="button" className="btn" style={{ background: 'rgba(255,255,255,0.1)' }} onClick={() => setEditModalOpen(false)}>Batal</button>
                <button type="submit" className="btn btn-primary" style={{ flex: 1 }}>Simpan Perubahan</button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* ── Bulk Import CSV + Photos Modal ── */}
      {importModalOpen && (
        <div className="login-modal-overlay no-print" style={{ zIndex: 99999 }}>
          <div className="login-modal-content" style={{ maxWidth: '500px', width: '90%', padding: '1.75rem', background: 'var(--bg-secondary)', border: '1px solid var(--border-color)', borderRadius: '12px', color: 'var(--text-main)' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.25rem', borderBottom: '1px solid var(--border-color)', paddingBottom: '10px' }}>
              <h3 style={{ margin: 0, fontSize: '1.2rem', fontWeight: 'bold' }}>Import Massal CSV & Foto</h3>
              <button type="button" onClick={() => !importing && setImportModalOpen(false)} style={{ background: 'transparent', border: 'none', color: 'inherit', fontSize: '1.2rem', cursor: 'pointer' }}>&times;</button>
            </div>
            
            <div style={{ fontSize: '0.85rem', color: 'var(--text-muted)', marginBottom: '1rem', background: 'rgba(0,0,0,0.05)', padding: '10px', borderRadius: '6px', borderLeft: '3px solid var(--accent-primary)' }}>
              <strong>Petunjuk CSV:</strong> File CSV harus memiliki header <code>nik</code> dan <code>name</code>. Nama file foto harus sama dengan NIK (contoh: <code>12345.jpg</code> untuk NIK 12345).
            </div>

            <div className="form-group" style={{ marginBottom: '1rem' }}>
              <label style={{ display: 'block', marginBottom: '6px', fontWeight: 'bold', fontSize: '0.85rem' }}>1. Pilih File CSV Karyawan</label>
              <input 
                type="file" 
                accept=".csv" 
                disabled={importing}
                onChange={(e) => setCsvFile(e.target.files[0])}
                style={{ width: '100%', padding: '8px', border: '1px solid var(--border-color)', borderRadius: '6px', background: 'var(--bg-primary)', color: 'inherit' }}
              />
            </div>

            <div className="form-group" style={{ marginBottom: '1.5rem' }}>
              <label style={{ display: 'block', marginBottom: '6px', fontWeight: 'bold', fontSize: '0.85rem' }}>2. Pilih Foto Wajah Karyawan (Bisa Banyak File)</label>
              <input 
                type="file" 
                multiple
                accept="image/*" 
                disabled={importing}
                onChange={(e) => setPhotoFiles(Array.from(e.target.files))}
                style={{ width: '100%', padding: '8px', border: '1px solid var(--border-color)', borderRadius: '6px', background: 'var(--bg-primary)', color: 'inherit' }}
              />
              {photoFiles.length > 0 && (
                <div style={{ marginTop: '6px', fontSize: '0.8rem', color: 'var(--accent-success)' }}>
                  ✓ {photoFiles.length} foto wajah siap diproses.
                </div>
              )}
            </div>

            {importing && (
              <div style={{ background: 'rgba(0,0,0,0.05)', padding: '12px', borderRadius: '6px', marginBottom: '1.5rem', border: '1px solid var(--border-color)' }}>
                <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: '0.8rem', fontWeight: 'bold', marginBottom: '6px' }}>
                  <span>Progres Import:</span>
                  <span>{importProgress.current} / {importProgress.total}</span>
                </div>
                <div style={{ width: '100%', height: '8px', background: 'rgba(0,0,0,0.1)', borderRadius: '4px', overflow: 'hidden', marginBottom: '8px' }}>
                  <div style={{ width: `${(importProgress.current / importProgress.total) * 100}%`, height: '100%', background: 'var(--accent-primary)', transition: 'width 0.2s' }}></div>
                </div>
                <div style={{ fontSize: '0.78rem', color: 'var(--text-muted)', fontStyle: 'italic' }}>
                  {importProgress.status}
                </div>
              </div>
            )}

            <div style={{ display: 'flex', gap: '10px', justifyContent: 'flex-end', borderTop: '1px solid var(--border-color)', paddingTop: '15px' }}>
              <button 
                type="button" 
                className="btn" 
                disabled={importing}
                onClick={() => setImportModalOpen(false)}
                style={{ background: 'rgba(255,255,255,0.05)', border: '1px solid var(--border-color)', padding: '8px 16px', width: 'auto' }}
              >
                Batal
              </button>
              <button 
                type="button" 
                className="btn btn-primary" 
                disabled={importing || !csvFile}
                onClick={handleStartImport}
                style={{ padding: '8px 16px', width: 'auto' }}
              >
                {importing ? 'Memproses...' : 'Mulai Import'}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
