import React, { useState, useMemo, useEffect } from 'react';
import { supabase } from '../supabaseClient';
import { API_BASE_URL } from '../config';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "../components/ui/table";
import { Search, FileSpreadsheet, FileDown, Edit2, Trash2, CheckCircle, Mail, Power, XCircle, MapPin, Clock, CloudOff } from 'lucide-react';
import { Filesystem, Directory } from '@capacitor/filesystem';
import { Share } from '@capacitor/share';

function formatDurasi(durasi) {
  if (!durasi) return '-';
  if (typeof durasi === 'number') {
    const totalSec = Math.round(durasi);
    const jam = Math.floor(totalSec / 3600);
    const menit = Math.floor((totalSec % 3600) / 60);
    const detik = totalSec % 60;
    return `${jam}j ${menit}m ${detik}d`;
  }
  const str = String(durasi).trim();
  const idMatch = str.match(/^(\d+)j\s*(\d+)m(?:\s*(\d+)d)?$/);
  if (idMatch) {
    const jam = parseInt(idMatch[1], 10);
    const menit = parseInt(idMatch[2], 10);
    const detik = idMatch[3] ? parseInt(idMatch[3], 10) : 0;
    return `${jam}j ${menit}m ${detik}d`;
  }
  const enMatch = str.match(/(\d+)\s*h(?:ours?)?\s*(\d+)\s*m(?:in(?:utes?)?)?/i);
  if (enMatch) {
    const jam = parseInt(enMatch[1], 10);
    const menit = parseInt(enMatch[2], 10);
    return `${jam}j ${menit}m 0d`;
  }
  const secMatch = str.match(/^(\d+)\s*(?:s|sec(?:onds?)?)?$/i);
  if (secMatch) {
    const totalSec = parseInt(secMatch[1], 10);
    const jam = Math.floor(totalSec / 3600);
    const menit = Math.floor((totalSec % 3600) / 60);
    const detik = totalSec % 60;
    return `${jam}j ${menit}m ${detik}d`;
  }
  return str;
}

/**
 * Mengurai koordinat GPS dari string lokasi yang disimpan di database.
 * Contoh input: "GPS (-2.12345, 108.56789) [Akurasi: 15.2m] - GeoMesh Scanner"
 * Mengembalikan { lat, lng, accuracy } atau null jika bukan format GPS.
 */
function parseLokasiGPS(lokasi) {
  if (!lokasi) return null;
  const match = lokasi.match(/GPS\s*\(([\-\d.]+),\s*([\-\d.]+)\)(?:\s*\[Akurasi:\s*([\d.]+)m\])?/);
  if (!match) return null;
  return {
    lat: parseFloat(match[1]),
    lng: parseFloat(match[2]),
    accuracy: match[3] ? parseFloat(match[3]) : null,
  };
}

export default function TabAttendanceLogs({
  logs,
  onRefreshLogs,
  showToast,
  openConfirmModal,
  refreshLogs,
}) {
  const [searchQuery, setSearchQuery] = useState('');
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [editData, setEditData] = useState(null);
  const [deletedLogIds, setDeletedLogIds] = useState([]);

  // Reset local deleted buffer when logs change from parent
  useEffect(() => {
    setDeletedLogIds([]);
  }, [logs]);

  // GROUP LOGS BY NIK + DATE
  const groupedLogs = useMemo(() => {
    if (!logs) return [];
    const groups = {};

    // Optimistic filter: instantly exclude deleted items
    const activeLogs = logs.filter(log => !deletedLogIds.includes(log.id));

    activeLogs.forEach(log => {
      const tsDate = new Date(log.timestamp);
      const dateKey = tsDate.toLocaleDateString('id-ID', { year: 'numeric', month: '2-digit', day: '2-digit' }).split('/').reverse().join('-'); // YYYY-MM-DD format for internal grouping
      const displayDate = tsDate.toLocaleDateString('id-ID', { day: '2-digit', month: 'short', year: 'numeric' });
      const key = `${log.nik}_${dateKey}`;

      if (!groups[key]) {
        groups[key] = {
          id: key,
          date: dateKey,
          displayDate: displayDate,
          nik: log.nik,
          name: log.name,
          department: log.department,
          afdeling: log.afdeling || '-',
          checkIn: '-',
          checkOut: '-',
          durasi: null,
          keterangan: 'Hadir', // default
          lokasi: '',
          inLog: null,
          outLog: null
        };
      }

      const isCheckOut = log.attendance_type === 'CHECK-OUT' || (log.status && log.status.includes('CHECK-OUT')) || (log.location && log.location.includes('CHECK-OUT'));
      const timePart = tsDate.toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit', second: '2-digit' });
      const cleanLocation = (log.location || '').replace(/\s*\[CHECK-(IN|OUT)\]/g, '').trim();

      // Extract Keterangan safely
      let ket = 'Hadir';
      if (log.status) {
        if (log.status.includes('Izin')) ket = 'Izin';
        else if (log.status.includes('Sakit')) ket = 'Sakit';
        else if (log.status.includes('Mangkir')) ket = 'Mangkir';
      }

      if (!isCheckOut) {
        groups[key].checkIn = timePart;
        groups[key].inLog = log;
        groups[key].lokasi = cleanLocation;
        if (ket !== 'Hadir') groups[key].keterangan = ket;
      } else {
        groups[key].checkOut = timePart;
        groups[key].outLog = log;
        groups[key].durasi = log.durasi;
        if (cleanLocation && groups[key].lokasi === '') {
          groups[key].lokasi = cleanLocation;
        }
        if (ket !== 'Hadir') groups[key].keterangan = ket;
      }
    });

    // Calculate dynamic duration for each group if both check-in and check-out exist
    Object.values(groups).forEach(g => {
      if (g.inLog && g.outLog) {
        const inMs = new Date(g.inLog.timestamp).getTime();
        const outMs = new Date(g.outLog.timestamp).getTime();
        const diffMs = outMs - inMs;
        if (diffMs > 0) {
          g.durasi = Math.round(diffMs / 1000); // duration in seconds
        }
      } else if (g.outLog && g.outLog.durasi) {
        g.durasi = g.outLog.durasi;
      }
    });

    return Object.values(groups).sort((a, b) => b.date.localeCompare(a.date));
  }, [logs, deletedLogIds]);

  // FILTER
  const filteredLogs = useMemo(() => {
    return groupedLogs.filter(log => {
      const q = searchQuery.toLowerCase();
      return (
        log.name.toLowerCase().includes(q) ||
        log.nik.toLowerCase().includes(q)
      );
    });
  }, [groupedLogs, searchQuery]);


  const handleDeleteGroup = (group) => {
    openConfirmModal({
      title: 'Hapus Riwayat Absensi?',
      message: `Apakah Anda yakin ingin menghapus catatan absensi ${group.name} pada ${group.displayDate}? Data Check-In dan Check-Out (jika ada) akan dihapus secara permanen.`,
      confirmText: 'Hapus Data',
      onConfirm: async () => {
        try {
          // Kumpulkan ID yang akan dihapus
          const idsToDelete = [];
          if (group.inLog?.id) idsToDelete.push(group.inLog.id);
          if (group.outLog?.id) idsToDelete.push(group.outLog.id);

          if (idsToDelete.length === 0) {
            showToast('Tidak Ada Data', 'Tidak ada ID log yang valid untuk dihapus.', 'warning');
            return;
          }

          console.log('[DELETE] Menghapus IDs:', idsToDelete);

          // Hapus data (SQLite/IndexedDB jika offline log, atau Supabase jika online log)
          try {
            // Optimistic update: instantly remove from local state
            setDeletedLogIds(prev => [...prev, ...idsToDelete]);

            const offlineIds = idsToDelete.filter(id => String(id).startsWith('offline_'));
            const onlineIds = idsToDelete.filter(id => !String(id).startsWith('offline_'));

            const { db } = await import('../db');

            if (offlineIds.length > 0) {
              for (const offlineId of offlineIds) {
                // Strip the 'offline_' prefix to get actual queue ID
                const rawQueueId = offlineId.replace('offline_', '');
                await db.attendance_sync_queue.delete(rawQueueId);
                await db.attendance_logs.delete(offlineId);
              }
            }

            if (onlineIds.length > 0) {
              const { error: sbErr } = await supabase
                .from('attendance_logs')
                .delete()
                .in('id', onlineIds);

              if (sbErr) {
                throw sbErr;
              }

              // Also delete from local logs cache table
              for (const onlineId of onlineIds) {
                await db.attendance_logs.delete(String(onlineId));
              }
            }

            showToast('Data Dihapus', `${idsToDelete.length} catatan absensi berhasil dihapus.`, 'success');
          } catch (sbEx) {
            console.error('[DELETE EXCEPTION]:', sbEx?.message || sbEx);
            // Revert optimistic update on failure
            setDeletedLogIds(prev => prev.filter(id => !idsToDelete.includes(id)));
            showToast('Gagal Menghapus', 'Gagal menghapus data.', 'error');
          }

          // Refresh tabel setelah operasi (berhasil atau tidak)
          refreshLogs();
        } catch (err) {
          console.error('[DELETE LOG ERROR]:', err);
          showToast('Error Sistem', `Gagal menghapus: ${err.message}`, 'error');
          refreshLogs();
        }
      },
    });
  };

  // EDIT
  const handleOpenEdit = (group) => {
    setEditData({
      ...group,
      editCheckIn: group.checkIn !== '-' ? group.checkIn : '',
      editCheckOut: group.checkOut !== '-' ? group.checkOut : '',
      editKeterangan: group.keterangan
    });
    setIsEditModalOpen(true);
  };

  const saveEdit = async () => {
    try {
      let success = true;
      const { db } = await import('../db');

      // Update inLog if it exists and checkIn changed
      if (editData.inLog && editData.editCheckIn) {
        // Construct new timestamp
        const oldDate = new Date(editData.inLog.timestamp);
        const [hours, minutes, seconds] = editData.editCheckIn.split(':');
        oldDate.setHours(parseInt(hours || 0), parseInt(minutes || 0), parseInt(seconds || 0));

        let newStatus = editData.editKeterangan === 'Hadir' ? 'Hadir (Verified)' : editData.editKeterangan;
        newStatus = `[CHECK-IN BERHASIL] - ${newStatus}`;

        const { error } = await supabase.from('attendance_logs').update({
          timestamp: oldDate.toISOString(),
          status: newStatus
        }).eq('id', editData.inLog.id);

        if (!error) {
          try {
            const localLogs = await db.attendance_logs.toArray();
            const found = localLogs.find(l => String(l.id) === String(editData.inLog.id));
            if (found) {
              found.timestamp = oldDate.toISOString();
              found.status = newStatus;
              await db.attendance_logs.put(found);
            }
          } catch (dbErr) {
            console.warn('[Local Database] Failed to update edited inLog locally:', dbErr);
          }
        } else {
          success = false;
        }
      }

      // Update outLog if it exists and checkOut changed
      if (editData.outLog && editData.editCheckOut) {
        const oldDate = new Date(editData.outLog.timestamp);
        const [hours, minutes, seconds] = editData.editCheckOut.split(':');
        oldDate.setHours(parseInt(hours || 0), parseInt(minutes || 0), parseInt(seconds || 0));

        let newStatus = editData.editKeterangan === 'Hadir' ? 'Hadir (Verified)' : editData.editKeterangan;
        newStatus = `[CHECK-OUT BERHASIL] - ${newStatus}`;

        const { error } = await supabase.from('attendance_logs').update({
          timestamp: oldDate.toISOString(),
          status: newStatus
        }).eq('id', editData.outLog.id);

        if (!error) {
          try {
            const localLogs = await db.attendance_logs.toArray();
            const found = localLogs.find(l => String(l.id) === String(editData.outLog.id));
            if (found) {
              found.timestamp = oldDate.toISOString();
              found.status = newStatus;
              await db.attendance_logs.put(found);
            }
          } catch (dbErr) {
            console.warn('[Local Database] Failed to update edited outLog locally:', dbErr);
          }
        } else {
          success = false;
        }
      }

      if (success) {
        showToast('Berhasil', 'Log absensi berhasil diperbarui.', 'success');
        setIsEditModalOpen(false);
        refreshLogs();
      } else {
        showToast('Gagal', 'Terjadi kesalahan saat mengupdate log.', 'error');
      }
    } catch (err) {
      showToast('Error', err.message, 'error');
    }
  };

  // EXPORT EXCEL (Formatted XLS)
  const exportToCSV = () => {
    if (filteredLogs.length === 0) return showToast('Kosong', 'Tidak ada data untuk diekspor', 'info');

    const headers = ['Tanggal', 'NIK', 'Nama Karyawan', 'Afdeling', 'Check In', 'Check Out', 'Durasi', 'Keterangan', 'Lokasi'];

    const rows = filteredLogs.map(row => [
      row.displayDate,
      row.nik,
      row.name,
      row.afdeling,
      row.checkIn,
      row.checkOut,
      row.durasi ? formatDurasi(row.durasi) : '-',
      row.keterangan,
      row.lokasi || '-'
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
                  <x:Name>Riwayat Absensi</x:Name>
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

    const fileName = `Riwayat_Absensi_${new Date().toISOString().split('T')[0]}.xls`;

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
              text: `File Excel Absensi: ${fileName}`,
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

  // EXPORT PDF
  const exportToPDF = () => {
    const headers = ['Tanggal', 'Check In', 'NIK', 'Nama Karyawan', 'Afdeling', 'Check Out', 'Durasi', 'Keterangan', 'Lokasi'];
    const rows = filteredLogs.map(row => [
      row.displayDate,
      row.checkIn,
      row.nik,
      row.name,
      row.afdeling,
      row.checkOut,
      row.durasi ? formatDurasi(row.durasi) : '-',
      row.keterangan,
      row.lokasi || '-'
    ]);

    const printHtml = `
      <!DOCTYPE html>
      <html>
        <head>
          <meta charset="utf-8">
          <title>Cetak Riwayat Absensi</title>
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
          <h3>Riwayat Log Absensi Biometrik</h3>
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

    const fileName = `Cetak_Riwayat_Absensi_${new Date().toISOString().split('T')[0]}.html`;

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

  const renderKeteranganIcon = (ket) => {
    switch (ket) {
      case 'Hadir': return <span className="status-badge success" style={{ gap: '4px', fontSize: '0.75rem' }}><CheckCircle size={14} /> Hadir</span>;
      case 'Izin': return <span className="status-badge info" style={{ background: 'rgba(56, 189, 248, 0.1)', color: '#38bdf8', border: '1px solid rgba(56, 189, 248, 0.3)', gap: '4px', fontSize: '0.75rem' }}><Mail size={14} /> Izin</span>;
      case 'Sakit': return <span className="status-badge warning" style={{ background: 'rgba(245, 158, 11, 0.1)', color: '#f59e0b', border: '1px solid rgba(245, 158, 11, 0.3)', gap: '4px', fontSize: '0.75rem' }}><Power size={14} /> Sakit</span>;
      case 'Mangkir': return <span className="status-badge danger" style={{ background: 'rgba(239, 68, 68, 0.1)', color: '#ef4444', border: '1px solid rgba(239, 68, 68, 0.3)', gap: '4px', fontSize: '0.75rem' }}><XCircle size={14} /> Mangkir</span>;
      default: return ket;
    }
  };

  return (
    <div className="glass-card print-area" style={{ padding: 0, overflow: 'hidden' }}>
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

      {/* Header & Toolbar */}
      <div style={{ padding: '1.5rem', borderBottom: '1px solid var(--border-color)', display: 'flex', flexDirection: 'column', gap: '16px' }}>
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '12px' }}>
          <div>
            <h3 style={{ fontSize: '1.1rem', fontWeight: 700, color: 'var(--text-main)' }}>
              Riwayat Log Absensi Biometrik
            </h3>
            <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)', marginTop: '4px' }}>
              Pencatatan data gabungan absensi harian karyawan.
            </p>
          </div>
          <div style={{ display: 'flex', gap: '8px' }} className="no-print">
            <button className="btn-action edit" onClick={exportToCSV} style={{ padding: '8px 14px' }}>
              <FileSpreadsheet size={16} /> Export Excel
            </button>
            <button className="btn-action delete" onClick={exportToPDF} style={{ padding: '8px 14px', background: 'var(--accent-primary)', color: '#fff', border: 'none' }}>
              <FileDown size={16} /> Export PDF
            </button>
          </div>
        </div>

        {/* Search Bar */}
        <div className="no-print" style={{ display: 'flex', gap: '12px', alignItems: 'center', background: 'var(--bg-input)', padding: '10px 14px', borderRadius: '8px', border: '1px solid var(--border-color)' }}>
          <Search size={18} color="var(--text-muted)" />
          <input
            type="text"
            placeholder="Cari berdasarkan Nama atau NIK..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            style={{ border: 'none', background: 'transparent', color: 'var(--text-main)', width: '100%', outline: 'none', fontSize: '0.9rem' }}
          />
        </div>
      </div>

      {/* Table */}
      <div className="table-container" style={{ marginTop: 0, maxHeight: '550px', overflowY: 'auto', position: 'relative' }}>
        <Table className="freeze-table-header">
          <TableHeader>
            <TableRow>
              <TableHead>Tanggal</TableHead>
              <TableHead>Check In</TableHead>
              <TableHead>NIK</TableHead>
              <TableHead>Nama Karyawan</TableHead>
              <TableHead>Afdeling</TableHead>
              <TableHead>Check Out</TableHead>
              <TableHead>Durasi</TableHead>
              <TableHead>Keterangan</TableHead>
              <TableHead>Lokasi</TableHead>
              <TableHead className="no-print">Aksi</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {filteredLogs.length === 0 ? (
              <TableRow>
                <TableCell colSpan={10} style={{ textAlign: 'center', padding: '3rem 0', color: 'var(--text-muted)' }}>
                  Belum ada log absensi yang tercatat.
                </TableCell>
              </TableRow>
            ) : (
              filteredLogs.map((log) => (
                <TableRow key={log.id}>
                  <TableCell style={{ fontWeight: 600 }}>{log.displayDate}</TableCell>
                  <TableCell style={{ color: log.checkIn !== '-' ? 'var(--accent-cyan)' : 'inherit' }}>{log.checkIn}</TableCell>
                  <TableCell className="nik-cell">{log.nik}</TableCell>
                  <TableCell>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                      <span>{log.name}</span>
                      {((log.inLog && log.inLog.isOfflineQueue) || (log.outLog && log.outLog.isOfflineQueue)) && (
                        <span 
                          style={{ 
                            fontSize: '0.65rem', 
                            background: 'var(--accent-warning)', 
                            color: '#fff', 
                            padding: '2px 6px', 
                            borderRadius: '4px', 
                            fontWeight: 'bold',
                            display: 'inline-flex',
                            alignItems: 'center',
                            gap: '3px' 
                          }}
                          title="Data absensi disimpan lokal dan akan disinkronkan saat online"
                        >
                          <CloudOff size={11} /> Offline
                        </span>
                      )}
                    </div>
                  </TableCell>
                  <TableCell style={{ color: 'var(--text-muted)' }}>{log.afdeling}</TableCell>
                  <TableCell style={{ color: log.checkOut !== '-' ? 'var(--accent-primary)' : 'inherit' }}>{log.checkOut}</TableCell>
                  <TableCell>
                    {log.durasi ? (
                      <span className="durasi-badge" style={{ display: 'inline-flex', alignItems: 'center', gap: '4px' }}><Clock size={13} /> {formatDurasi(log.durasi)}</span>
                    ) : (log.checkIn !== '-' && log.checkOut === '-' && log.keterangan === 'Hadir') ? (
                      <span className="durasi-badge checkin" style={{ fontSize: '0.7rem', padding: '2px 6px' }}>Sedang Bekerja</span>
                    ) : '-'}
                  </TableCell>
                  <TableCell>{renderKeteranganIcon(log.keterangan)}</TableCell>
                  <TableCell style={{ fontSize: '0.8rem', maxWidth: '180px' }}>
                    {(() => {
                      const gps = parseLokasiGPS(log.lokasi);
                      if (gps) {
                        const mapsUrl = `https://www.google.com/maps?q=${gps.lat},${gps.lng}`;
                        return (
                          <a
                            href={mapsUrl}
                            target="_blank"
                            rel="noopener noreferrer"
                            title={log.lokasi}
                            style={{
                              display: 'flex',
                              alignItems: 'center',
                              gap: '5px',
                              color: '#10b981',
                              textDecoration: 'none',
                              fontWeight: 600,
                              whiteSpace: 'nowrap',
                              overflow: 'hidden',
                              textOverflow: 'ellipsis',
                            }}
                          >
                            <MapPin size={13} style={{ flexShrink: 0 }} />
                            {gps.lat.toFixed(4)}, {gps.lng.toFixed(4)}
                            {gps.accuracy && (
                              <span style={{ color: 'var(--text-muted)', fontWeight: 400, fontSize: '0.72rem' }}>
                                &nbsp;±{gps.accuracy.toFixed(0)}m
                              </span>
                            )}
                          </a>
                        );
                      }
                      return (
                        <span style={{ color: 'var(--text-muted)', whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis', display: 'block' }}>
                          {log.lokasi || '-'}
                        </span>
                      );
                    })()}
                  </TableCell>
                  <TableCell className="no-print">
                    <div style={{ display: 'flex', gap: '6px' }}>
                      {((log.inLog && log.inLog.isOfflineQueue) || (log.outLog && log.outLog.isOfflineQueue)) ? (
                        <span style={{ fontSize: '0.72rem', color: 'var(--text-muted)', fontStyle: 'italic' }}>Menunggu Sinkronisasi</span>
                      ) : (
                        <>
                          <button className="btn-action edit" style={{ padding: '6px', minWidth: 'auto' }} onClick={() => handleOpenEdit(log)} title="Edit">
                            <Edit2 size={14} />
                          </button>
                          <button className="btn-action delete" style={{ padding: '6px', minWidth: 'auto' }} onClick={() => handleDeleteGroup(log)} title="Hapus">
                            <Trash2 size={14} />
                          </button>
                        </>
                      )}
                    </div>
                  </TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </div>

      {/* EDIT MODAL */}
      {isEditModalOpen && editData && (
        <div style={{ position: 'fixed', inset: 0, background: 'rgba(0,0,0,0.7)', backdropFilter: 'blur(8px)', zIndex: 1000, display: 'flex', alignItems: 'center', justifyContent: 'center', padding: '1rem', overflowY: 'auto' }}>
          <div className="glass-card" style={{ maxWidth: '450px', width: '100%', border: '1px solid var(--accent-primary)', maxHeight: '90vh', overflowY: 'auto' }}>
            <div style={{ borderBottom: '1px solid var(--border-color)', paddingBottom: '1rem', marginBottom: '1rem' }}>
              <h3 style={{ margin: 0, fontSize: '1.2rem', color: 'var(--text-main)' }}>Edit Log Absensi</h3>
            </div>

            <div style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
              <div style={{ fontSize: '0.9rem', color: 'var(--text-muted)' }}>
                Karyawan: <strong style={{ color: 'var(--text-main)' }}>{editData.name} ({editData.nik})</strong><br />
                Tanggal: <strong style={{ color: 'var(--text-main)' }}>{editData.displayDate}</strong>
              </div>

              <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
                <label style={{ fontSize: '0.85rem', fontWeight: 600, color: 'var(--text-main)' }}>Waktu Check-In</label>
                <input
                  type="time"
                  step="1"
                  value={editData.editCheckIn}
                  onChange={(e) => setEditData({ ...editData, editCheckIn: e.target.value })}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '8px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', outline: 'none' }}
                  disabled={!editData.inLog}
                />
                {!editData.inLog && <small style={{ color: 'var(--text-muted)' }}>Belum ada data check-in</small>}
              </div>

              <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
                <label style={{ fontSize: '0.85rem', fontWeight: 600, color: 'var(--text-main)' }}>Waktu Check-Out</label>
                <input
                  type="time"
                  step="1"
                  value={editData.editCheckOut}
                  onChange={(e) => setEditData({ ...editData, editCheckOut: e.target.value })}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '8px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', outline: 'none' }}
                  disabled={!editData.outLog}
                />
                {!editData.outLog && <small style={{ color: 'var(--text-muted)' }}>Belum ada data check-out</small>}
              </div>

              <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
                <label style={{ fontSize: '0.85rem', fontWeight: 600, color: 'var(--text-main)' }}>Keterangan</label>
                <select
                  value={editData.editKeterangan}
                  onChange={(e) => setEditData({ ...editData, editKeterangan: e.target.value })}
                  style={{ width: '100%', padding: '10px 14px', borderRadius: '8px', border: '1px solid var(--border-color)', background: 'var(--bg-input)', color: 'var(--text-main)', outline: 'none' }}
                >
                  <option value="Hadir">Hadir</option>
                  <option value="Izin">Izin</option>
                  <option value="Sakit">Sakit</option>
                  <option value="Mangkir">Mangkir</option>
                </select>
              </div>

            </div>

            <div style={{ display: 'flex', gap: '12px', justifyContent: 'flex-end', marginTop: '24px' }}>
              <button type="button" className="btn" onClick={() => setIsEditModalOpen(false)} style={{ background: 'transparent', color: 'var(--text-main)', border: '1px solid var(--border-color)' }}>Batal</button>
              <button type="button" className="btn btn-primary" onClick={saveEdit}>Simpan Perubahan</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
