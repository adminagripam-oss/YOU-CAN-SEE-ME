import React, { useState, useEffect } from 'react';
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from './ui/dialog';
import { supabase } from '../supabaseClient';
import { getEmployeeFaceThumb, getEmployeeFaceThumbInfo, saveEmployeeFaceThumb } from '../db';
import { User, RefreshCw, AlertCircle, Camera, CheckCircle2 } from 'lucide-react';

export default function EmployeeFacePreviewModal({
  isOpen,
  onClose,
  employee,
  onReScan
}) {
  const [photoUrl, setPhotoUrl] = useState(null);
  const [loading, setLoading] = useState(true);
  const [sourceType, setSourceType] = useState(''); // 'cloud' | 'local_thumb' | 'attendance' | 'offline_empty' | 'none'
  const [savedDate, setSavedDate] = useState(null);
  const [attendanceDate, setAttendanceDate] = useState(null);
  const [errorMsg, setErrorMsg] = useState('');

  // Handle Android Back Button via History API
  useEffect(() => {
    if (!isOpen) return;

    window.history.pushState({ modal: 'face_preview' }, '');
    const handlePopState = () => {
      onClose();
    };

    window.addEventListener('popstate', handlePopState);
    return () => {
      window.removeEventListener('popstate', handlePopState);
    };
  }, [isOpen, onClose]);

  useEffect(() => {
    if (!isOpen || !employee) {
      setPhotoUrl(null);
      setLoading(false);
      setErrorMsg('');
      setSourceType('');
      setSavedDate(null);
      setAttendanceDate(null);
      return;
    }

    let isMounted = true;
    setLoading(true);
    setErrorMsg('');
    setSavedDate(null);
    setAttendanceDate(null);

    async function loadFacePhoto() {
      try {
        // Tier 1: Check offline local thumbnail cache
        const localInfo = await getEmployeeFaceThumbInfo(employee.id);
        if (localInfo?.thumb_base64 && isMounted) {
          setPhotoUrl(localInfo.thumb_base64);
          setSourceType('local_thumb');
          if (localInfo.updated_at || localInfo.captured_at) {
            setSavedDate(localInfo.updated_at || localInfo.captured_at);
          }
          setLoading(false);

          // Background revalidate if online
          if (navigator.onLine && !String(employee.id).startsWith('off_') && !String(employee.id).startsWith('tmp_')) {
            (async () => {
              try {
                const { data: remoteRec } = await supabase
                  .from('employee_face_photos')
                  .select('thumbnail_base64, photo_version, photo_checksum, updated_at')
                  .eq('employee_id', employee.id)
                  .maybeSingle();

                if (remoteRec?.thumbnail_base64 && remoteRec.photo_version > (localInfo.photo_version || 1)) {
                  await saveEmployeeFaceThumb(
                    employee.id,
                    remoteRec.thumbnail_base64,
                    remoteRec.photo_version,
                    remoteRec.photo_checksum
                  );
                  if (isMounted) {
                    setPhotoUrl(remoteRec.thumbnail_base64);
                    setSavedDate(remoteRec.updated_at);
                  }
                }
              } catch (_) {}
            })();
          }
          return;
        }

        // Tier 2: Check Supabase Storage via public URL or thumbnail if online
        const targetId = !isNaN(Number(employee.id)) ? Number(employee.id) : employee.id;
        if (navigator.onLine && employee.id && !String(employee.id).startsWith('off_') && !String(employee.id).startsWith('tmp_')) {
          try {
            const { data: photoRec } = await supabase
              .from('employee_face_photos')
              .select('storage_path, thumbnail_base64, photo_version, photo_checksum, updated_at')
              .eq('employee_id', targetId)
              .maybeSingle();

            if (photoRec?.thumbnail_base64) {
              // Cache locally so it is available offline next time
              await saveEmployeeFaceThumb(
                String(employee.id),
                photoRec.thumbnail_base64,
                photoRec.photo_version || 1,
                photoRec.photo_checksum || null
              ).catch(() => {});

              if (isMounted) {
                setPhotoUrl(photoRec.thumbnail_base64);
                setSourceType('cloud');
                setSavedDate(photoRec.updated_at);
                setLoading(false);
              }
              return;
            }

            if (photoRec?.storage_path) {
              const { data: pubData } = supabase
                .storage
                .from('attendance-photos')
                .getPublicUrl(photoRec.storage_path);

              if (pubData?.publicUrl && isMounted) {
                setPhotoUrl(pubData.publicUrl);
                setSourceType('cloud');
                setSavedDate(photoRec.updated_at);
                setLoading(false);
                return;
              }
            }
          } catch (storageErr) {
            console.warn('[FacePreview] Storage fetch error:', storageErr);
          }
        }

        // Tier 3: Fallback to last verified attendance photo
        if (navigator.onLine && employee.id && !String(employee.id).startsWith('off_') && !String(employee.id).startsWith('tmp_')) {
          try {
            const { data: logRec } = await supabase
              .from('attendance_logs')
              .select('photo_url, timestamp')
              .eq('employee_id', targetId)
              .not('photo_url', 'is', null)
              .order('timestamp', { ascending: false })
              .limit(1)
              .maybeSingle();

            if (logRec?.photo_url && isMounted) {
              setPhotoUrl(logRec.photo_url);
              setAttendanceDate(logRec.timestamp);
              setSourceType('attendance');
              setLoading(false);
              return;
            }
          } catch (logErr) {
            console.warn('[FacePreview] Attendance log fallback error:', logErr);
          }
        }

        if (isMounted) {
          if (!navigator.onLine) {
            setSourceType('offline_empty');
          } else {
            setSourceType('none');
          }
          setLoading(false);
        }
      } catch (err) {
        if (isMounted) {
          setErrorMsg(err.message || 'Gagal memuat foto');
          setLoading(false);
        }
      }
    }

    loadFacePhoto();

    return () => {
      isMounted = false;
    };
  }, [isOpen, employee]);

  if (!isOpen || !employee) return null;

  const hasMaster = employee.has_master_biometric;
  const checksum = employee.vector_checksum;

  return (
    <Dialog open={isOpen} onOpenChange={(open) => { if (!open) onClose(); }}>
      <DialogContent className="sm:max-w-md p-6 bg-[var(--bg-card)] border border-[var(--border-color)] text-[var(--text-main)] rounded-2xl shadow-2xl">
        <DialogHeader className="pb-2 border-b border-[var(--border-color)]">
          <DialogTitle className="text-base font-bold text-[var(--text-main)] flex items-center justify-between">
            <span>Foto Master Wajah</span>
            <span className="text-xs px-2.5 py-0.5 rounded-full font-medium"
              style={{
                background: hasMaster ? 'rgba(16, 185, 129, 0.15)' : 'rgba(239, 68, 68, 0.15)',
                color: hasMaster ? 'var(--accent-success)' : 'var(--accent-error)'
              }}>
              {hasMaster ? 'Biometrik Siap' : 'Belum Ada Biometrik'}
            </span>
          </DialogTitle>
        </DialogHeader>

        <div className="flex flex-col items-center gap-4 py-3">
          {/* Photo Display Card */}
          <div className="relative w-52 h-52 rounded-xl overflow-hidden border-2 border-[var(--border-color)] bg-[var(--bg-input)] flex items-center justify-center shadow-inner">
            {loading ? (
              <div className="flex flex-col items-center gap-2 text-[var(--text-muted)]">
                <RefreshCw className="animate-spin" size={28} />
                <span className="text-xs">Memuat foto...</span>
              </div>
            ) : photoUrl ? (
              <img
                src={photoUrl}
                alt={employee.name}
                className="w-full h-full object-cover"
                onError={() => {
                  setPhotoUrl(null);
                  setErrorMsg('Gagal menampilkan file gambar');
                }}
              />
            ) : sourceType === 'offline_empty' ? (
              <div className="flex flex-col items-center gap-2 p-4 text-center text-[var(--text-muted)]">
                <AlertCircle size={36} className="text-amber-500 opacity-90" />
                <span className="text-xs font-semibold text-[var(--text-main)]">
                  Foto Belum Tersedia di Perangkat Ini
                </span>
                <span className="text-[11px] text-[var(--text-muted)] max-w-[210px] leading-relaxed">
                  Hubungkan ke internet untuk mengunduh master foto wajah.
                </span>
              </div>
            ) : (
              <div className="flex flex-col items-center gap-2 p-4 text-center text-[var(--text-muted)]">
                <User size={40} strokeWidth={1.5} className="opacity-50" />
                <span className="text-xs font-semibold text-[var(--text-main)]">
                  {errorMsg || 'Foto master belum didaftarkan'}
                </span>
                <span className="text-[11px] text-[var(--text-muted)] max-w-[210px] leading-relaxed">
                  Lakukan rescan dari kamera untuk mendaftarkan foto master wajah.
                </span>
                <button
                  type="button"
                  onClick={() => {
                    onClose();
                    if (onReScan) onReScan(employee);
                  }}
                  className="mt-1 flex items-center justify-center gap-1.5 py-1 px-3 rounded-lg bg-[var(--accent-primary)] text-white font-medium text-[11px] shadow hover:opacity-90 transition-opacity"
                >
                  <Camera size={13} />
                  <span>Daftarkan Wajah</span>
                </button>
              </div>
            )}
          </div>

          {/* Source Indicator / Warning */}
          {sourceType === 'attendance' && (
            <div className="text-[11px] px-3 py-1.5 rounded-lg flex items-center gap-1.5 text-amber-700 dark:text-amber-300 bg-amber-500/10 border border-amber-500/25 text-center font-medium max-w-sm">
              <AlertCircle size={14} className="shrink-0 text-amber-600 dark:text-amber-400" />
              <span>
                Foto Absensi Lapangan (Bukan Foto Master{attendanceDate ? ` - ${new Date(attendanceDate).toLocaleDateString('id-ID', { day: 'numeric', month: 'short', year: 'numeric' })}` : ''})
              </span>
            </div>
          )}
          {sourceType === 'local_thumb' && (
            <div className="text-[11px] px-3 py-1.5 rounded-lg flex items-center gap-1.5 text-emerald-700 dark:text-emerald-300 bg-emerald-500/10 border border-emerald-500/25 font-medium">
              <CheckCircle2 size={14} className="shrink-0 text-emerald-600 dark:text-emerald-400" />
              <span>
                Foto Master Pendaftaran Resmi (Tersimpan di Perangkat{savedDate ? ` - ${new Date(savedDate).toLocaleDateString('id-ID', { day: 'numeric', month: 'short', year: 'numeric' })}` : ''})
              </span>
            </div>
          )}
          {sourceType === 'cloud' && (
            <div className="text-[11px] px-3 py-1.5 rounded-lg flex items-center gap-1.5 text-emerald-700 dark:text-emerald-300 bg-emerald-500/10 border border-emerald-500/25 font-medium">
              <CheckCircle2 size={14} className="shrink-0 text-emerald-600 dark:text-emerald-400" />
              <span>
                Foto Master Pendaftaran Resmi (Cloud Server{savedDate ? ` - ${new Date(savedDate).toLocaleDateString('id-ID', { day: 'numeric', month: 'short', year: 'numeric' })}` : ''})
              </span>
            </div>
          )}

          {/* Employee Info Box */}
          <div className="w-full bg-[var(--bg-input)] rounded-lg p-3 text-xs flex flex-col gap-1.5 border border-[var(--border-color)]">
            <div className="flex justify-between items-center">
              <span className="text-[var(--text-muted)] font-medium">Nama</span>
              <span className="font-bold text-[var(--text-main)]">{employee.name}</span>
            </div>
            <div className="flex justify-between items-center">
              <span className="text-[var(--text-muted)] font-medium">NIK</span>
              <span className="font-semibold text-[var(--text-main)] font-mono">{employee.nik}</span>
            </div>
            <div className="flex justify-between items-center">
              <span className="text-[var(--text-muted)] font-medium">Afdeling / Kebun</span>
              <span className="text-[var(--text-main)]">{employee.afdeling || '-'} / {employee.nama_kebun || '-'}</span>
            </div>
            {employee.template_version !== undefined && employee.template_version !== null && (
              <div className="flex justify-between items-center pt-1 border-t border-[var(--border-color)] mt-1">
                <span className="text-[var(--text-muted)] font-medium">Tipe Template</span>
                {Number(employee.template_version) <= 1 ? (
                  <span className="px-2 py-0.5 rounded text-[10px] font-bold bg-amber-500/15 text-amber-700 dark:text-amber-400 border border-amber-500/30">
                    Template Sempit ({Number(employee.template_version) === 0 ? 'Foto Statis' : 'Frontal Saja'})
                  </span>
                ) : (
                  <span className="px-2 py-0.5 rounded text-[10px] font-bold bg-emerald-500/15 text-emerald-700 dark:text-emerald-400 border border-emerald-500/30">
                    Multi-Pose (Versi 2)
                  </span>
                )}
              </div>
            )}
            {checksum && (
              <div className="flex justify-between items-center pt-1 border-t border-[var(--border-color)] mt-1">
                <span className="text-[var(--text-muted)] font-medium">Vector Checksum</span>
                <span className="font-mono text-[10px] text-[var(--accent-primary)] truncate max-w-[180px]" title={checksum}>
                  {checksum}
                </span>
              </div>
            )}
          </div>

          {/* Action Buttons */}
          <div className="flex items-center gap-2 w-full pt-2">
            <button
              type="button"
              onClick={() => {
                onClose();
                if (onReScan) onReScan(employee);
              }}
              className="flex-1 flex items-center justify-center gap-2 py-2.5 px-4 rounded-lg bg-[var(--accent-primary)] text-white font-medium text-xs shadow hover:opacity-90 transition-opacity"
            >
              <Camera size={15} />
              <span>Re-Scan Wajah</span>
            </button>
            <button
              type="button"
              onClick={onClose}
              className="py-2.5 px-4 rounded-lg border border-[var(--border-color)] bg-[var(--bg-card)] text-[var(--text-main)] font-medium text-xs hover:bg-[var(--bg-input)] transition-colors"
            >
              Tutup
            </button>
          </div>
        </div>
      </DialogContent>
    </Dialog>
  );
}
