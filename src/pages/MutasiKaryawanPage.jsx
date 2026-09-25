import React, { useState, useEffect, useMemo, useCallback } from 'react';
import { supabase } from '../supabaseClient';
import {
  Table, TableBody, TableCell, TableHead, TableHeader, TableRow,
} from '../components/ui/table';
import {
  Plus, FileSpreadsheet, FileDown, Trash2, RefreshCw, ChevronLeftIcon, ChevronRightIcon, Search, X, ArrowLeft
} from 'lucide-react';
import { Filesystem, Directory } from '@capacitor/filesystem';
import { Share } from '@capacitor/share';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const MUTATION_LABELS = {
  ONBOARDING: 'Onboarding',
  PINDAH_KEBUN: 'Pindah Kebun',
  PINDAH_AFDELING: 'Pindah Afdeling',
  OFFBOARDING: 'Offboarding',
};

const MUTATION_COLORS = {
  ONBOARDING: { bg: 'rgba(16,185,129,0.12)', color: '#10b981', border: 'rgba(16,185,129,0.3)' },
  PINDAH_KEBUN: { bg: 'rgba(245,158,11,0.12)', color: '#f59e0b', border: 'rgba(245,158,11,0.3)' },
  PINDAH_AFDELING: { bg: 'rgba(249,115,22,0.12)', color: '#f97316', border: 'rgba(249,115,22,0.3)' },
  OFFBOARDING: { bg: 'rgba(239,68,68,0.12)', color: '#ef4444', border: 'rgba(239,68,68,0.3)' },
};

function MutasiBadge({ type }) {
  const c = MUTATION_COLORS[type] || { bg: 'var(--bg-secondary)', color: 'var(--text-muted)', border: 'var(--border-color)' };
  return (
    <span style={{
      display: 'inline-flex', alignItems: 'center', padding: '2px 8px', borderRadius: '4px',
      fontSize: '0.75rem', fontWeight: 700, background: c.bg, color: c.color, border: `1px solid ${c.border}`,
    }}>
      {MUTATION_LABELS[type] || type}
    </span>
  );
}

const ITEMS_PER_PAGE = 25;

export default function MutasiKaryawanPage({ employees, showToast }) {
  const { user } = useAuth();
  const navigate = useNavigate();

  const availableKebuns = useMemo(() => {
    if (!employees) return [];
    return [...new Set(employees.map(e => e.nama_kebun).filter(Boolean))].sort();
  }, [employees]);

  const [mutations, setMutations] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [filterType, setFilterType] = useState('');
  const [filterKebun, setFilterKebun] = useState('');
  const [filterAfdeling, setFilterAfdeling] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isSaving, setIsSaving] = useState(false);
  const [form, setForm] = useState({
    employee_id: '',
    mutation_type: 'ONBOARDING',
    effective_date: new Date().toISOString().split('T')[0],
    kebun_asal: '',
    kebun_tujuan: '',
    afdeling_asal: '',
    afdeling_tujuan: '',
    keterangan: '',
  });

  const fetchMutations = useCallback(async () => {
    if (!navigator.onLine) {
      showToast('Offline', 'Data mutasi memerlukan koneksi internet.', 'warning');
      return;
    }
    setIsLoading(true);
    try {
      let query = supabase.from('employee_mutations').select('*').order('effective_date', { ascending: false });
      if (user?.role === 'estate_admin' && user?.kebun) {
        query = query.or(`kebun_tujuan.eq.${user.kebun},kebun_asal.eq.${user.kebun}`);
      } else if (user?.role === 'regional_admin' && availableKebuns?.length > 0) {
        query = query.or(
          availableKebuns.map(k => `kebun_tujuan.eq.${k}`).join(',') + ',' +
          availableKebuns.map(k => `kebun_asal.eq.${k}`).join(',')
        );
      }
      const { data, error } = await query;
      if (error) throw error;
      
      // Secondary filter just in case
      let finalData = data || [];
      if (user?.role === 'estate_admin' && user?.kebun) {
          finalData = finalData.filter(m => m.kebun_tujuan === user.kebun || m.kebun_asal === user.kebun);
      }
      setMutations(finalData);
    } catch (err) {
      showToast('Gagal memuat', err.message, 'error');
    } finally {
      setIsLoading(false);
    }
  }, [user, availableKebuns, showToast]);

  useEffect(() => {
    fetchMutations();
  }, [fetchMutations]);

  useEffect(() => {
    setCurrentPage(1);
  }, [searchQuery, filterType, filterKebun, filterAfdeling]);

  const uniqueAfdelings = useMemo(() => {
    const all = mutations.flatMap(m => [m.afdeling_asal, m.afdeling_tujuan]).filter(Boolean);
    return [...new Set(all)].sort();
  }, [mutations]);

  const filtered = useMemo(() => {
    const q = searchQuery.toLowerCase();
    return mutations.filter(m => {
      if (q && !m.name?.toLowerCase().includes(q) && !m.nik?.toLowerCase().includes(q)) return false;
      if (filterType && m.mutation_type !== filterType) return false;
      if (filterKebun && m.kebun_tujuan !== filterKebun && m.kebun_asal !== filterKebun) return false;
      if (filterAfdeling && m.afdeling_tujuan !== filterAfdeling && m.afdeling_asal !== filterAfdeling) return false;
      return true;
    });
  }, [mutations, searchQuery, filterType, filterKebun, filterAfdeling]);

  const totalPages = Math.max(1, Math.ceil(filtered.length / ITEMS_PER_PAGE));
  const paginated = filtered.slice((currentPage - 1) * ITEMS_PER_PAGE, currentPage * ITEMS_PER_PAGE);

  const selectedEmployee = useMemo(
    () => employees?.find(e => e.id === form.employee_id) || null,
    [employees, form.employee_id]
  );

  const handleEmployeeSelect = (e) => {
    const emp = employees?.find(emp => emp.id === e.target.value);
    setForm(prev => ({
      ...prev,
      employee_id: e.target.value,
      kebun_asal: emp?.nama_kebun || '',
      afdeling_asal: emp?.afdeling || '',
      kebun_tujuan: prev.mutation_type === 'ONBOARDING' ? (emp?.nama_kebun || '') : prev.kebun_tujuan,
      afdeling_tujuan: prev.mutation_type === 'PINDAH_AFDELING' ? prev.afdeling_tujuan : (emp?.afdeling || ''),
    }));
  };

  const handleSave = async () => {
    if (!navigator.onLine) {
      showToast('Offline', 'Fitur tambah mutasi hanya tersedia saat online.', 'error');
      return;
    }
    const emp = employees?.find(e => e.id === form.employee_id);
    if (!emp) { showToast('Pilih Karyawan', 'Harap pilih karyawan terlebih dahulu.', 'error'); return; }
    if (!form.effective_date) { showToast('Tanggal Kosong', 'Harap isi tanggal efektif.', 'error'); return; }

    setIsSaving(true);
    try {
      const payload = {
        employee_id: emp.id,
        nik: emp.nik,
        name: emp.name,
        mutation_type: form.mutation_type,
        effective_date: form.effective_date,
        kebun_asal: form.kebun_asal || null,
        kebun_tujuan: form.kebun_tujuan || null,
        afdeling_asal: form.afdeling_asal || null,
        afdeling_tujuan: form.afdeling_tujuan || null,
        keterangan: form.keterangan || null,
        created_by: user?.username || user?.email || 'admin',
      };
      const { error } = await supabase.from('employee_mutations').insert(payload);
      if (error) throw error;
      showToast('Berhasil', 'Mutasi karyawan berhasil dicatat.', 'success');
      setIsModalOpen(false);
      setForm({ employee_id: '', mutation_type: 'ONBOARDING', effective_date: new Date().toISOString().split('T')[0], kebun_asal: '', kebun_tujuan: '', afdeling_asal: '', afdeling_tujuan: '', keterangan: '' });
      fetchMutations();
    } catch (err) {
      showToast('Gagal menyimpan', err.message, 'error');
    } finally {
      setIsSaving(false);
    }
  };

  const handleDelete = async (mutation) => {
    if (!navigator.onLine) { showToast('Offline', 'Hapus mutasi hanya tersedia saat online.', 'error'); return; }
    if (!window.confirm(`Hapus record mutasi ${MUTATION_LABELS[mutation.mutation_type]} untuk ${mutation.name}?`)) return;
    const { error } = await supabase.from('employee_mutations').delete().eq('id', mutation.id);
    if (error) { showToast('Gagal hapus', error.message, 'error'); return; }
    showToast('Terhapus', 'Record mutasi berhasil dihapus.', 'success');
    fetchMutations();
  };

  const buildExcelRows = () => filtered.map(m => [
    m.effective_date,
    MUTATION_LABELS[m.mutation_type] || m.mutation_type,
    m.nik,
    m.name,
    m.kebun_asal || '-',
    m.kebun_tujuan || '-',
    m.afdeling_asal || '-',
    m.afdeling_tujuan || '-',
    m.keterangan || '-',
    m.created_by,
  ]);

  const exportToExcel = async () => {
    if (filtered.length === 0) { showToast('Kosong', 'Tidak ada data untuk diekspor.', 'info'); return; }
    const headers = ['Tanggal Efektif', 'Jenis Mutasi', 'NIK', 'Nama Karyawan', 'Kebun Asal', 'Kebun Tujuan', 'Afdeling Asal', 'Afdeling Tujuan', 'Keterangan', 'Dicatat Oleh'];
    const rows = buildExcelRows();
    const tableHtml = `<table border="1"><thead><tr>${headers.map(h => `<th style="background-color:#46bdc6;color:#fff;font-weight:bold;padding:5px;">${h}</th>`).join('')}</tr></thead><tbody>${rows.map(row => `<tr>${row.map(cell => `<td style="padding:5px;">${cell}</td>`).join('')}</tr>`).join('')}</tbody></table>`;
    const template = `<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body>${tableHtml}</body></html>`;
    const fileName = `Mutasi_Karyawan_${new Date().toISOString().split('T')[0]}.xls`;
    if (window?.Capacitor?.getPlatform() === 'android') {
      const blob = new Blob([template], { type: 'application/vnd.ms-excel' });
      const reader = new FileReader();
      reader.readAsDataURL(blob);
      reader.onloadend = async () => {
        const base64Data = reader.result.split(',')[1];
        const fileResult = await Filesystem.writeFile({ path: fileName, data: base64Data, directory: Directory.Cache });
        await Share.share({ title: 'Export Mutasi', url: fileResult.uri, dialogTitle: 'Kirim / Simpan Excel' });
      };
      return;
    }
    const blob = new Blob([template], { type: 'application/vnd.ms-excel' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url; a.download = fileName;
    document.body.appendChild(a); a.click(); document.body.removeChild(a);
  };

  const exportToPDF = () => {
    if (filtered.length === 0) { showToast('Kosong', 'Tidak ada data untuk diekspor.', 'info'); return; }
    const headers = ['Tanggal', 'Jenis Mutasi', 'NIK', 'Nama Karyawan', 'Kebun Asal', 'Kebun Tujuan', 'Afdeling Asal', 'Afdeling Tujuan', 'Keterangan', 'Dicatat Oleh'];
    const rows = buildExcelRows();
    const printHtml = `<!DOCTYPE html><html><head><meta charset="utf-8"><title>Mutasi Karyawan</title><style>@page{size:landscape;margin:10mm}body{font-family:Arial,sans-serif;font-size:11px}h3{text-align:center}table{width:100%;border-collapse:collapse}th,td{border:1px solid #000;padding:4px;text-align:center;vertical-align:middle}th{background-color:#46bdc6!important;color:#fff!important;-webkit-print-color-adjust:exact;print-color-adjust:exact}</style></head><body><h3>Riwayat Mutasi Karyawan</h3><table><thead><tr>${headers.map(h => `<th>${h}</th>`).join('')}</tr></thead><tbody>${rows.map(row => `<tr>${row.map(c => `<td>${c}</td>`).join('')}</tr>`).join('')}</tbody></table><script>window.onload=function(){window.print()}</script></body></html>`;
    const w = window.open('', '_blank');
    w.document.write(printHtml);
    w.document.close();
  };

  const showKebunFilter = user?.role !== 'estate_admin';

  const needsKebunFields = ['PINDAH_KEBUN', 'ONBOARDING'].includes(form.mutation_type);
  const needsAfdelingFields = ['PINDAH_AFDELING', 'ONBOARDING', 'PINDAH_KEBUN'].includes(form.mutation_type);

  const sortedEmployees = useMemo(() => {
    if (!employees) return [];
    return [...employees].sort((a, b) => a.name.localeCompare(b.name));
  }, [employees]);

  const formatTanggal = (dateStr) => {
    if (!dateStr) return '-';
    const bulan = ['Jan','Feb','Mar','Apr','Mei','Jun','Jul','Ags','Sep','Okt','Nov','Des'];
    const d = new Date(dateStr + 'T00:00:00');
    if (isNaN(d)) return dateStr;
    return `${String(d.getDate()).padStart(2, '0')} ${bulan[d.getMonth()]} ${d.getFullYear()}`;
  };

  return (
    <div style={{ width: '100%', padding: 0, boxSizing: 'border-box' }}>

      <div className="page-layout">

        {/* Card 1: Toolbar */}
        <div className="toolbar-card no-print">
          <div className="toolbar-card-row">
            <h1 className="page-layout-h1">Riwayat Mutasi</h1>
            <div className="toolbar-action-group">
              <button className="btn-primary-green" onClick={() => setIsModalOpen(true)}>
                <Plus size={16} /> Tambah Mutasi
              </button>
            </div>
          </div>

          <div className="toolbar-card-row">
            <div className="filter-bar" style={{ flex: 1 }}>
              <div className="filter-bar-search">
                <Search size={16} color="var(--text-muted)" />
                <input
                  type="text"
                  placeholder="Cari nama / NIK..."
                  value={searchQuery}
                  onChange={e => setSearchQuery(e.target.value)}
                />
              </div>
              <select value={filterType} onChange={e => setFilterType(e.target.value)}>
                <option value="">Semua Jenis</option>
                {Object.entries(MUTATION_LABELS).map(([k, v]) => <option key={k} value={k}>{v}</option>)}
              </select>
              {showKebunFilter && (
                <select value={filterKebun} onChange={e => setFilterKebun(e.target.value)}>
                  <option value="">Semua Kebun</option>
                  {(availableKebuns || []).map(k => <option key={k} value={k}>{k}</option>)}
                </select>
              )}
              <select value={filterAfdeling} onChange={e => setFilterAfdeling(e.target.value)}>
                <option value="">Semua Afdeling</option>
                {uniqueAfdelings.map(a => <option key={a} value={a}>{a}</option>)}
              </select>
            </div>
            <div className="toolbar-action-group">
              <button className="btn-outline-action" onClick={exportToExcel}>
                <FileSpreadsheet size={15} color="#107C41" /> Excel
              </button>
              <button className="btn-outline-action" onClick={exportToPDF}>
                <FileDown size={15} color="#E81123" /> PDF
              </button>
              <button className="btn-outline-action" onClick={fetchMutations}>
                <RefreshCw size={15} /> Refresh
              </button>
            </div>
          </div>
        </div>

        {/* Card 2: Table */}
        <div className="table-card">
          <div className="table-container" style={{ maxHeight: '60vh', overflowY: 'auto', position: 'relative' }}>
            <Table className="enterprise-table compact-mobile-table">
              <TableHeader>
                <TableRow>
                  <TableHead style={{ width: '48px', textAlign: 'center' }}>No.</TableHead>
                  <TableHead>Tanggal</TableHead>
                  <TableHead>Nama</TableHead>
                  <TableHead>NIK</TableHead>
                  <TableHead>Jenis Mutasi</TableHead>
                  <TableHead>Nama Kebun</TableHead>
                  <TableHead>Dari</TableHead>
                  <TableHead>Ke</TableHead>
                  {user?.role === 'headoffice_admin' && <TableHead className="no-print">Aksi</TableHead>}
                </TableRow>
              </TableHeader>
              <TableBody>
                {isLoading ? (
                  <TableRow>
                    <TableCell colSpan={9} style={{ textAlign: 'center', padding: '3rem 0', color: 'var(--text-muted)' }}>
                      Memuat data mutasi...
                    </TableCell>
                  </TableRow>
                ) : filtered.length === 0 ? (
                  <TableRow>
                    <TableCell colSpan={9} style={{ textAlign: 'center', padding: '3rem 0', color: 'var(--text-muted)' }}>
                      Belum ada data mutasi yang tercatat.
                    </TableCell>
                  </TableRow>
                ) : (
                  paginated.map((m, idx) => {
                    const dari = [m.kebun_asal, m.afdeling_asal].filter(Boolean).join(' / ') || '-';
                    const ke = [m.kebun_tujuan, m.afdeling_tujuan].filter(Boolean).join(' / ') || '-';
                    const namaKebun = m.kebun_tujuan || m.kebun_asal || '-';
                    return (
                      <TableRow key={m.id}>
                        <TableCell style={{ textAlign: 'center', color: 'var(--text-muted)' }}>{(currentPage - 1) * ITEMS_PER_PAGE + idx + 1}</TableCell>
                        <TableCell style={{ fontWeight: 600, whiteSpace: 'nowrap' }}>{formatTanggal(m.effective_date)}</TableCell>
                        <TableCell style={{ fontWeight: 600 }}>{m.name}</TableCell>
                        <TableCell style={{ color: 'var(--text-muted)' }}>{m.nik}</TableCell>
                        <TableCell><MutasiBadge type={m.mutation_type} /></TableCell>
                        <TableCell style={{ color: 'var(--text-muted)' }}>{namaKebun}</TableCell>
                        <TableCell style={{ color: 'var(--text-muted)', fontSize: '0.82rem' }}>{dari}</TableCell>
                        <TableCell style={{ fontSize: '0.82rem' }}>{ke}</TableCell>
                        {user?.role === 'headoffice_admin' && (
                          <TableCell className="no-print">
                            <button className="btn-action delete" style={{ padding: '5px', minWidth: 'auto' }} onClick={() => handleDelete(m)} title="Hapus">
                              <Trash2 size={14} />
                            </button>
                          </TableCell>
                        )}
                      </TableRow>
                    );
                  })
                )}
              </TableBody>
            </Table>
          </div>

          {filtered.length > 0 && (
            <div style={{ padding: '12px', display: 'flex', alignItems: 'center', justifyContent: 'center', borderTop: '1px solid var(--border-color)' }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: '4px' }}>
                <button onClick={() => setCurrentPage(p => Math.max(1, p - 1))} disabled={currentPage === 1}
                  style={{ width: '32px', height: '32px', borderRadius: '50%', border: 'none', background: 'transparent', cursor: currentPage === 1 ? 'not-allowed' : 'pointer', opacity: currentPage === 1 ? 0.4 : 1, display: 'flex', alignItems: 'center', justifyContent: 'center', color: 'var(--text-main)' }}>
                  <ChevronLeftIcon size={16} />
                </button>
                {Array.from({ length: totalPages }, (_, i) => i + 1).map(page => {
                  if (totalPages > 7 && page !== 1 && page !== totalPages && Math.abs(page - currentPage) > 1) {
                    if (page === currentPage - 2 || page === currentPage + 2) return <span key={`e-${page}`} style={{ color: 'var(--text-muted)' }}>...</span>;
                    return null;
                  }
                  return (
                    <button key={page} onClick={() => setCurrentPage(page)}
                      style={{ minWidth: '32px', height: '32px', borderRadius: '8px', border: 'none', cursor: 'pointer', background: page === currentPage ? 'var(--accent-primary)' : 'transparent', color: page === currentPage ? '#fff' : 'var(--text-main)', fontWeight: page === currentPage ? 700 : 500, fontSize: '0.85rem' }}>
                      {page}
                    </button>
                  );
                })}
                <button onClick={() => setCurrentPage(p => Math.min(totalPages, p + 1))} disabled={currentPage === totalPages}
                  style={{ width: '32px', height: '32px', borderRadius: '50%', border: 'none', background: 'transparent', cursor: currentPage === totalPages ? 'not-allowed' : 'pointer', opacity: currentPage === totalPages ? 0.4 : 1, display: 'flex', alignItems: 'center', justifyContent: 'center', color: 'var(--text-main)' }}>
                  <ChevronRightIcon size={16} />
                </button>
              </div>
            </div>
          )}
        </div>

      </div>

      {/* Modal Tambah Mutasi */}
      {isModalOpen && (
        <div style={{ position: 'fixed', inset: 0, background: 'rgba(0,0,0,0.65)', backdropFilter: 'blur(6px)', zIndex: 1000, display: 'flex', alignItems: 'center', justifyContent: 'center', padding: '1rem' }}>
          <div className="glass-card" style={{ maxWidth: '520px', width: '100%', maxHeight: '90vh', overflowY: 'auto', border: '1px solid var(--border-color)' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.25rem' }}>
              <h3 style={{ fontSize: '1rem', fontWeight: 700, color: 'var(--text-main)' }}>Tambah Mutasi Karyawan</h3>
              <button onClick={() => setIsModalOpen(false)} style={{ background: 'transparent', border: 'none', cursor: 'pointer', color: 'var(--text-muted)' }}>
                <X size={20} />
              </button>
            </div>

            <div style={{ display: 'flex', flexDirection: 'column', gap: '14px' }}>
              <div>
                <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Karyawan *</label>
                <div className="custom-select-wrapper" style={{ position: 'relative' }}>
                  <select value={form.employee_id} onChange={handleEmployeeSelect}
                    style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem' }}>
                    <option value="">-- Pilih Karyawan --</option>
                    {sortedEmployees.map(e => (
                      <option key={e.id} value={e.id}>{e.name} ({e.nik})</option>
                    ))}
                  </select>
                </div>
              </div>

              <div>
                <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Jenis Mutasi *</label>
                <div className="custom-select-wrapper" style={{ position: 'relative' }}>
                  <select value={form.mutation_type} onChange={e => setForm(prev => ({ ...prev, mutation_type: e.target.value }))}
                    style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem' }}>
                    {Object.entries(MUTATION_LABELS).map(([k, v]) => <option key={k} value={k}>{v}</option>)}
                  </select>
                </div>
              </div>

              <div>
                <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Tanggal Efektif *</label>
                <input type="date" value={form.effective_date} onChange={e => setForm(prev => ({ ...prev, effective_date: e.target.value }))}
                  style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem' }} />
              </div>

              {needsKebunFields && (
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '10px' }}>
                  <div>
                    <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Kebun Asal</label>
                    <input type="text" value={form.kebun_asal} onChange={e => setForm(prev => ({ ...prev, kebun_asal: e.target.value }))} placeholder="Kebun asal..."
                      style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem' }} />
                  </div>
                  <div>
                    <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Kebun Tujuan</label>
                    <input type="text" value={form.kebun_tujuan} onChange={e => setForm(prev => ({ ...prev, kebun_tujuan: e.target.value }))} placeholder="Kebun tujuan..."
                      style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem' }} />
                  </div>
                </div>
              )}

              {needsAfdelingFields && (
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '10px' }}>
                  <div>
                    <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Afdeling Asal</label>
                    <input type="text" value={form.afdeling_asal} onChange={e => setForm(prev => ({ ...prev, afdeling_asal: e.target.value }))} placeholder="Afdeling asal..."
                      style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem' }} />
                  </div>
                  <div>
                    <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Afdeling Tujuan</label>
                    <input type="text" value={form.afdeling_tujuan} onChange={e => setForm(prev => ({ ...prev, afdeling_tujuan: e.target.value }))} placeholder="Afdeling tujuan..."
                      style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem' }} />
                  </div>
                </div>
              )}

              {form.mutation_type === 'OFFBOARDING' && selectedEmployee && (
                <div>
                  <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Kebun Asal</label>
                  <input type="text" value={form.kebun_asal} onChange={e => setForm(prev => ({ ...prev, kebun_asal: e.target.value }))}
                    style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem' }} />
                </div>
              )}

              <div>
                <label style={{ fontSize: '0.8rem', fontWeight: 600, color: 'var(--text-muted)', display: 'block', marginBottom: '6px' }}>Keterangan</label>
                <textarea value={form.keterangan} onChange={e => setForm(prev => ({ ...prev, keterangan: e.target.value }))}
                  placeholder="Alasan atau catatan mutasi..." rows={3}
                  style={{ width: '100%', padding: '8px 12px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', fontSize: '0.875rem', resize: 'vertical' }} />
              </div>

              <div style={{ display: 'flex', gap: '10px', justifyContent: 'flex-end', marginTop: '4px' }}>
                <button onClick={() => setIsModalOpen(false)}
                  style={{ padding: '8px 18px', borderRadius: '6px', border: '1px solid var(--border-color)', background: 'transparent', color: 'var(--text-main)', fontSize: '0.875rem', cursor: 'pointer' }}>
                  Batal
                </button>
                <button onClick={handleSave} disabled={isSaving}
                  style={{ padding: '8px 18px', borderRadius: '6px', border: 'none', background: isSaving ? 'var(--text-muted)' : 'var(--accent-primary)', color: '#fff', fontSize: '0.875rem', fontWeight: 700, cursor: isSaving ? 'not-allowed' : 'pointer' }}>
                  {isSaving ? 'Menyimpan...' : 'Simpan Mutasi'}
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
