import React from 'react';
import { API_BASE_URL } from '../config';

/**
 * Converts a duration string or seconds into "Xj Ym Zd" (jam, menit, detik) format
 * Accepts:
 *  - "2j 30m" style strings (passthrough, enhanced)
 *  - "2h 30m" style
 *  - raw seconds number
 *  - "0j 0m" → still show as "0j 0m 0d"
 */
function formatDurasi(durasi) {
  if (!durasi) return null;

  // If already a number (seconds), convert directly
  if (typeof durasi === 'number') {
    const totalSec = Math.round(durasi);
    const jam = Math.floor(totalSec / 3600);
    const menit = Math.floor((totalSec % 3600) / 60);
    const detik = totalSec % 60;
    return `${jam}j ${menit}m ${detik}d`;
  }

  const str = String(durasi).trim();

  // Match "Xj Ym" or "Xj Ym Zd" pattern already in Indonesian
  const idMatch = str.match(/^(\d+)j\s*(\d+)m(?:\s*(\d+)d)?$/);
  if (idMatch) {
    const jam = parseInt(idMatch[1], 10);
    const menit = parseInt(idMatch[2], 10);
    const detik = idMatch[3] ? parseInt(idMatch[3], 10) : 0;
    return `${jam}j ${menit}m ${detik}d`;
  }

  // Match "Xh Ym" or "X hours Y minutes" style
  const enMatch = str.match(/(\d+)\s*h(?:ours?)?\s*(\d+)\s*m(?:in(?:utes?)?)?/i);
  if (enMatch) {
    const jam = parseInt(enMatch[1], 10);
    const menit = parseInt(enMatch[2], 10);
    return `${jam}j ${menit}m 0d`;
  }

  // Match pure seconds "3600s" or "3600 seconds"
  const secMatch = str.match(/^(\d+)\s*(?:s|sec(?:onds?)?)?$/i);
  if (secMatch) {
    const totalSec = parseInt(secMatch[1], 10);
    const jam = Math.floor(totalSec / 3600);
    const menit = Math.floor((totalSec % 3600) / 60);
    const detik = totalSec % 60;
    return `${jam}j ${menit}m ${detik}d`;
  }

  // Fallback: return as is but append "d" if possible
  return str;
}

export default function TabAttendanceLogs({
  logs,
  onRefreshLogs,
  showToast,
  openConfirmModal,
  refreshLogs,
}) {
  // Single Log Delete Trigger
  const handleDeleteSingleLog = (log) => {
    const tsDate = new Date(log.timestamp);
    const timePart = tsDate.toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit', second: '2-digit' });

    openConfirmModal({
      title: 'Hapus Session Log Absensi?',
      message: `Apakah Anda yakin ingin menghapus catatan log absensi untuk "${log.name}" (${timePart}) dari database Supabase?`,
      confirmText: 'Hapus Session',
      onConfirm: async () => {
        try {
          const res = await fetch(`${API_BASE_URL}/api/attendance/logs/${log.id}`, { method: 'DELETE' });
          const data = await res.json();
          if (data.success) {
            showToast('Log Dihapus', data.message, 'success');
            refreshLogs();
          } else {
            showToast('Gagal Menghapus Log', data.message, 'error');
          }
        } catch (err) {
          console.error('[DELETE LOG ERROR]:', err);
          showToast('Error Sistem', err.message, 'error');
        }
      },
    });
  };

  // Clear All Logs Trigger
  const handleClearAllLogs = () => {
    openConfirmModal({
      title: 'Hapus SELURUH Riwayat Log Absensi?',
      message:
        'PERINGATAN HAPUS TOTAL: Apakah Anda benar-benar yakin ingin menghapus SELURUH riwayat log absensi di database Supabase? Tindakan ini tidak dapat dibatalkan!',
      confirmText: 'Hapus Semua Log',
      onConfirm: async () => {
        try {
          const res = await fetch(`${API_BASE_URL}/api/attendance/logs`, { method: 'DELETE' });
          const data = await res.json();
          if (data.success) {
            showToast('Seluruh Log Berhasil Dihapus', data.message, 'success');
            refreshLogs();
          } else {
            showToast('Gagal Menghapus Log', data.message, 'error');
          }
        } catch (err) {
          console.error('[CLEAR ALL LOGS ERROR]:', err);
          showToast('Error Sistem', err.message, 'error');
        }
      },
    });
  };

  return (
    <div className="glass-card" style={{ padding: 0, overflow: 'hidden' }}>
      {/* Table Header Area */}
      <div
        style={{
          padding: '1.25rem 1.5rem',
          borderBottom: '1px solid var(--border-color)',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          flexWrap: 'wrap',
          gap: '12px',
        }}
      >
        <div>
          <h3 style={{ fontSize: '1rem', fontWeight: 700, color: 'var(--text-main)', display: 'flex', gap: '8px', alignItems: 'center' }}>
            <i className="fa-solid fa-list-check" style={{ color: 'var(--accent-cyan)' }}></i>
            Riwayat Log Absensi Biometrik
          </h3>
          <p style={{ fontSize: '0.8rem', color: 'var(--text-muted)', marginTop: '2px' }}>
            Pencatatan seluruh percobaan absensi Check-In &amp; Check-Out beserta durasi kerja.
          </p>
        </div>

        <div style={{ display: 'flex', gap: '8px' }}>
          <button
            type="button"
            className="btn-action edit"
            style={{ padding: '8px 14px', fontSize: '0.82rem' }}
            onClick={onRefreshLogs}
          >
            <i className="fa-solid fa-arrows-rotate"></i> Refresh Log
          </button>
          <button
            type="button"
            className="btn-action delete"
            style={{ padding: '8px 14px', fontSize: '0.82rem' }}
            onClick={handleClearAllLogs}
          >
            <i className="fa-solid fa-trash-can"></i> Hapus Semua Log
          </button>
        </div>
      </div>

      {/* Shadcn UI Table */}
      <div className="table-container" style={{ marginTop: 0 }}>
        <table className="shadcn-table">
          <thead>
            <tr>
              <th>Waktu</th>
              <th>Tipe</th>
              <th>NIK</th>
              <th>Nama Karyawan</th>
              <th>Departemen</th>
              <th>Durasi Kerja</th>
              <th>Status</th>
              <th>Lokasi</th>
              <th>Aksi</th>
            </tr>
          </thead>
          <tbody>
            {!logs || logs.length === 0 ? (
              <tr className="shadcn-tr-empty">
                <td colSpan="9">
                  <div
                    style={{
                      display: 'flex',
                      flexDirection: 'column',
                      alignItems: 'center',
                      gap: '8px',
                      padding: '2.5rem 0',
                      color: 'var(--text-muted)',
                    }}
                  >
                    <i className="fa-regular fa-clock" style={{ fontSize: '2rem', opacity: 0.35 }}></i>
                    <span style={{ fontSize: '0.9rem' }}>Belum ada log absensi yang tercatat.</span>
                  </div>
                </td>
              </tr>
            ) : (
              logs.map((log) => {
                const isSuccess = log.status && log.status.includes('BERHASIL');
                const badgeClass = isSuccess ? 'success' : 'fail';

                const tsDate = new Date(log.timestamp);
                const datePart = tsDate.toLocaleDateString('id-ID', {
                  day: '2-digit',
                  month: 'short',
                  year: 'numeric',
                });
                const timePart = tsDate.toLocaleTimeString('id-ID', {
                  hour: '2-digit',
                  minute: '2-digit',
                  second: '2-digit',
                });

                const isCheckOut =
                  log.attendance_type === 'CHECK-OUT' ||
                  (log.status && log.status.includes('CHECK-OUT')) ||
                  (log.location && log.location.includes('CHECK-OUT'));

                const typeBadge = isCheckOut ? (
                  <span
                    className="status-badge"
                    style={{
                      background: 'rgba(245,158,11,0.18)',
                      color: '#fbbf24',
                      border: '1px solid rgba(245,158,11,0.35)',
                      gap: '4px',
                    }}
                  >
                    <i className="fa-solid fa-right-from-bracket"></i> OUT
                  </span>
                ) : (
                  <span className="status-badge success" style={{ gap: '4px' }}>
                    <i className="fa-solid fa-right-to-bracket"></i> IN
                  </span>
                );

                // Build durasi cell with jam/menit/detik format
                let durasiCell = <span style={{ color: 'var(--text-muted)', fontSize: '0.8rem' }}>—</span>;
                if (isCheckOut && log.durasi) {
                  const formatted = formatDurasi(log.durasi);
                  durasiCell = (
                    <span className="durasi-badge">
                      <i className="fa-regular fa-clock"></i> {formatted}
                    </span>
                  );
                } else if (!isCheckOut && isSuccess) {
                  durasiCell = (
                    <span className="durasi-badge checkin">
                      <i className="fa-solid fa-right-to-bracket"></i> Sedang Bekerja
                    </span>
                  );
                }

                const cleanLocation = (log.location || '').replace(/\s*\[CHECK-(IN|OUT)\]/g, '');

                return (
                  <tr key={log.id}>
                    <td>
                      <div className="time-cell">
                        <span className="date-part">{datePart}</span>
                        <span className="time-part">{timePart}</span>
                      </div>
                    </td>
                    <td>{typeBadge}</td>
                    {/* NIK uses CSS variable for theme-aware color */}
                    <td className="nik-cell">{log.nik}</td>
                    <td>{log.name}</td>
                    <td style={{ color: 'var(--text-muted)', fontSize: '0.82rem' }}>{log.department}</td>
                    <td>{durasiCell}</td>
                    <td>
                      <span className={`status-badge ${badgeClass}`} style={{ fontSize: '0.72rem' }}>
                        {isSuccess ? 'BERHASIL' : 'GAGAL'}
                      </span>
                    </td>
                    <td style={{ color: 'var(--text-muted)', fontSize: '0.8rem' }}>{cleanLocation}</td>
                    <td>
                      <button
                        type="button"
                        className="btn-action delete"
                        style={{ padding: '4px 8px', fontSize: '0.75rem' }}
                        onClick={() => handleDeleteSingleLog(log)}
                      >
                        <i className="fa-solid fa-trash"></i> Hapus
                      </button>
                    </td>
                  </tr>
                );
              })
            )}
          </tbody>
          {logs && logs.length > 0 && (
            <tfoot>
              <tr>
                <td
                  colSpan="9"
                  style={{
                    padding: '10px 16px',
                    fontSize: '0.8rem',
                    color: 'var(--text-muted)',
                    borderTop: '1px solid var(--border-color)',
                    background: 'var(--bg-secondary)',
                  }}
                >
                  Total <strong>{logs.length}</strong> entri tercatat hari ini &amp; historis.
                </td>
              </tr>
            </tfoot>
          )}
        </table>
      </div>
    </div>
  );
}
