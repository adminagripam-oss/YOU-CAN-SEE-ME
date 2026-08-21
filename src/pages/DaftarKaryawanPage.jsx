import React, { useState, useRef, useEffect } from 'react';
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "../components/ui/table";
import { Edit2, Trash2, FileSpreadsheet, FileDown, Plus } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import { API_BASE_URL } from '../config';
import { supabase } from '../supabaseClient';
import { cacheUserMasterVector } from '../db';
import { useNormalizedFaceMesh } from '../hooks/useNormalizedFaceMesh';
import { human } from '../humanSingleton';

export default function DaftarKaryawanPage({ employees, modelsLoaded, showToast, refreshEmployees, refreshLogs, openConfirmModal }) {
  const navigate = useNavigate();

  // Filters State
  const [searchQuery, setSearchQuery] = useState('');
  const [filterStatusTk, setFilterStatusTk] = useState('');
  const [filterStatusPerkawinan, setFilterStatusPerkawinan] = useState('');
  // Edit Modal State
  const [editModalOpen, setEditModalOpen] = useState(false);
  const [editingEmp, setEditingEmp] = useState({ id: '', nik: '', name: '', department: '', afdeling: '', nama_kebun: '', status_tk: '', jabatan: '', status_perkawinan: '' });
  const [editStatusTkCustom, setEditStatusTkCustom] = useState('');
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
      editCurrentDescriptorRef.current = Array.from(detection.embedding);
      setEditCameraStatusText(`✓ Wajah Terdeteksi [1024-dim Human]`);
      setEditCameraStatusColor('var(--accent-success)');
    }

    ctx.clearRect(0, 0, 640, 480);
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
  }, []);

  // Callback saat tidak ada wajah
  const onEditNoFace = React.useCallback(() => {
    editCurrentDescriptorRef.current = null;
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
          editCurrentDescriptorRef.current = Array.from(result.face[0].embedding);
          setEditCameraStatusText('✓ Wajah Diekstrak dari Foto [1024-dim Human]');
          setEditCameraStatusColor('var(--accent-success)');
        } else {
          editCurrentDescriptorRef.current = null;
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

    if (editUpdateBiometrics && editCurrentDescriptorRef.current) {
      payload.descriptor_json = JSON.stringify(editCurrentDescriptorRef.current);
    }

    try {
      const { error: empErr } = await supabase.from('employees').update(payload).eq('id', editingEmp.id);
      if (empErr) throw empErr;

      if (payload.descriptor_json) {
        await supabase.from('master_descriptors').upsert({ employee_id: editingEmp.id, descriptor_json: payload.descriptor_json });
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
          const { error: delErr } = await supabase.from('employees').delete().eq('id', emp.id);
          if (delErr) throw delErr;
          
          showToast('Penghapusan Berhasil', 'Sukses menghapus karyawan.', 'success');
          refreshEmployees();
          if (refreshLogs) refreshLogs();
        } catch (err) {
          showToast('Error Sistem', err.message, 'error');
        }
      },
    });
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

    return matchesSearch && matchesStatusTk && matchesStatusPerkawinan;
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
            ${headers.map(h => `<th style="background-color: #46bdc6; color: #ffffff; font-family: 'Consolas'; font-size: 12pt; font-weight: bold; text-align: center; vertical-align: middle; padding: 5px; border: 1px solid #ddd;">${h}</th>`).join('')}
          </tr>
        </thead>
        <tbody>
          ${rows.map(row => `
            <tr style="height: 24px;">
              ${row.map(cell => `<td style="font-family: 'Consolas'; font-size: 12pt; text-align: center; vertical-align: middle; padding: 5px; border: 1px solid #ddd;">${cell}</td>`).join('')}
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

    const blob = new Blob([template], { type: 'application/vnd.ms-excel' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.setAttribute("href", url);
    link.setAttribute("download", `Data_Karyawan_${new Date().toISOString().split('T')[0]}.xls`);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  const exportToPDF = () => {
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
          font-family: 'Consolas', Courier, monospace !important;
          font-size: 12px !important;
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
            width: 100%;
          }
          .no-print {
            display: none !important;
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
            font-size: 12px !important;
            text-align: center !important;
            vertical-align: middle !important;
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
            <button type="button" className="btn" onClick={exportToCSV} style={{ background: '#fff', color: '#333', border: '1px solid #ddd', padding: '8px 16px', display: 'flex', alignItems: 'center', gap: '6px' }}>
              <FileSpreadsheet size={16} color="#107C41" /> Export Excel
            </button>
            <button type="button" className="btn" onClick={exportToPDF} style={{ background: '#fff', color: '#333', border: '1px solid #ddd', padding: '8px 16px', display: 'flex', alignItems: 'center', gap: '6px' }}>
              <FileDown size={16} color="#E81123" /> Export PDF
            </button>
            <button type="button" className="btn btn-primary" onClick={() => navigate('/karyawan')} style={{ background: '#1e293b', border: '1px solid #334155', padding: '8px 16px', display: 'flex', alignItems: 'center', gap: '6px' }}>
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
                <TableHead>Biometrik</TableHead>
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
                    <TableCell>
                      {emp.has_master_biometric ? (
                        <span className="status-badge success">Siap</span>
                      ) : (
                        <span className="status-badge fail">Belum</span>
                      )}
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
                      <button type="button" className={`btn ${editFormMode === 'camera' ? 'btn-primary' : ''}`} style={{ padding: '6px 12px', fontSize: '0.8rem', background: editFormMode !== 'camera' ? 'rgba(255,255,255,0.1)' : '' }} onClick={() => setEditFormMode('camera')}><i className="fa-solid fa-video"></i> Gunakan Kamera</button>
                      <button type="button" className={`btn ${editFormMode === 'file' ? 'btn-primary' : ''}`} style={{ padding: '6px 12px', fontSize: '0.8rem', background: editFormMode !== 'file' ? 'rgba(255,255,255,0.1)' : '' }} onClick={() => setEditFormMode('file')}><i className="fa-solid fa-upload"></i> Unggah File Foto</button>
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
    </div>
  );
}
