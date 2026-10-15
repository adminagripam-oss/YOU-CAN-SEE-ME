import { supabase } from './supabaseClient';
import { getUnsyncedLogs, removeSyncedLogs, db, writeToBackupStorage, updateSyncStatus } from './db';
import { Capacitor } from '@capacitor/core';
import { Network } from '@capacitor/network';
import { Filesystem, Directory } from '@capacitor/filesystem';
import { BIOMETRIC_CONFIG, vectorChecksum, toVectorArray } from './biometrics';

export function stripAttendanceTelemetry(payload) {
  if (Array.isArray(payload)) {
    return payload.map(stripAttendanceTelemetry);
  }
  if (!payload || typeof payload !== 'object') return payload;
  const { match_cosine, match_threshold, template_version, facing_mode, ...core } = payload;
  return core;
}

export function isAttendanceMissingColumnError(err) {
  if (!err) return false;
  const msg = String(err.message || '').toLowerCase();
  return err.code === '42703' ||
    msg.includes('match_cosine') ||
    msg.includes('match_threshold') ||
    msg.includes('template_version') ||
    msg.includes('facing_mode');
}

let isSyncing = false;

/**
 * Checks if the device is currently online (handles native and web fallback)
 */
export async function checkOnline() {
  if (Capacitor.isNativePlatform()) {
    try {
      const status = await Network.getStatus();
      return status.connected;
    } catch (e) {
      console.warn('[Sync Engine] Failed to get native network status, falling back to navigator.onLine:', e);
      return navigator.onLine;
    }
  }
  return navigator.onLine;
}

/**
 * Executes Auto-Sync of offline attendance logs stored in local DB to Server
 */
export async function syncPendingAttendanceLogs(showToast = null, onSyncComplete = null) {
  const isOnline = await checkOnline();
  // Guard: jika isSyncing stuck (crash pada siklus sebelumnya), reset otomatis
  if (!isOnline) return { count: 0 };
  if (isSyncing) {
    console.warn('[Auto-Sync] isSyncing masih aktif dari siklus sebelumnya. Reset paksa untuk mencegah log nyangkut selamanya.');
    isSyncing = false;
  }

  try {
    let pendingLogsRaw = await getUnsyncedLogs();
    
    // --- LOGIKA CUT-OFF 22:00 ---
    const waktuCutOff = new Date();
    waktuCutOff.setHours(22, 0, 0, 0);
    const batasCutOffTime = waktuCutOff.getTime();

    let pendingLogs = pendingLogsRaw.filter(log => {
      if (!log.timestamp) return true;
      return new Date(log.timestamp).getTime() <= batasCutOffTime;
    });

    if (pendingLogsRaw.length > 0 && pendingLogs.length === 0) {
      console.log(`[Auto-Sync] Terdapat ${pendingLogsRaw.length} log pending, namun di luar cut-off (setelah 22:00). Ditunda hingga besok.`);
      return { count: 0 };
    }
    // ----------------------------
    
    // PRE-PROCESSING: Auto-Recovery untuk "Orphaned Logs" (Log yang karyawannya hilang dari antrean lokal)
    try {
      const isNative = Capacitor.isNativePlatform();
      let pendingEmps = [];
      if (isNative) {
        const { sqliteGetPendingEmployees } = await import('./services/sqliteService');
        pendingEmps = await sqliteGetPendingEmployees();
      } else {
        pendingEmps = await db.employee_sync_queue.toArray();
      }
      const pendingEmpIds = new Set(pendingEmps.map(e => String(e.id)));

      for (let i = 0; i < pendingLogs.length; i++) {
        const log = pendingLogs[i];
        const empIdStr = String(log.employee_id);
        
        if (isNaN(Number(empIdStr))) {
          // Log ini offline. Apakah karyawannya masih ada di antrean?
          if (!pendingEmpIds.has(empIdStr)) {
            console.warn(`[Auto-Sync] Log orphaned terdeteksi untuk employee_id: ${empIdStr}. Mencoba auto-recovery...`);
            if (log.nik && log.nik !== '-') {
              const { data: existingEmp } = await supabase
                .from('employees')
                .select('id')
                .eq('nik', log.nik)
                .single();
                
              if (existingEmp) {
                 log.employee_id = existingEmp.id; // Auto fix
                 if (isNative) {
                   const { sqliteUpdatePendingAttendanceEmployeeId } = await import('./services/sqliteService');
                   await sqliteUpdatePendingAttendanceEmployeeId(empIdStr, existingEmp.id);
                 } else {
                   await db.attendance_sync_queue.put(log);
                 }
                 console.log(`[Auto-Sync] Auto-recovery berhasil! ID log diupdate ke: ${existingEmp.id}`);
              } else {
                 console.warn(`[Auto-Sync] Karyawan dgn NIK ${log.nik} tidak ada di server. Menandai log butuh resolusi NIK (TIDAK DIHAPUS).`);
                 log.needs_resolution = true;
                 log.sync_notes = 'Membutuhkan Resolusi NIK';
                 await updateSyncStatus(log.id, 'pending', null, 'Membutuhkan Resolusi NIK');
              }
            } else {
               console.warn(`[Auto-Sync] Log offline tanpa NIK tidak bisa di-recovery. Menandai log butuh resolusi NIK (TIDAK DIHAPUS).`);
               log.needs_resolution = true;
               log.sync_notes = 'Membutuhkan Resolusi NIK';
               await updateSyncStatus(log.id, 'pending', null, 'Membutuhkan Resolusi NIK');
            }
          }
        }
      }
    } catch (recoveryErr) {
      console.warn('[Auto-Sync] Auto-recovery gagal:', recoveryErr);
    }

    // FILTER: Tahan log yang masih menggunakan ID temporary atau membutuhkan resolusi NIK manual
    pendingLogs = pendingLogs.filter(log => {
      if (log.needs_resolution || log.sync_notes === 'Membutuhkan Resolusi NIK') {
        console.warn(`[Auto-Sync] Menahan log absensi #${log.id} yang membutuhkan resolusi NIK manual.`);
        return false;
      }
      const empIdStr = String(log.employee_id);
      if (isNaN(Number(empIdStr))) {
        console.warn(`[Auto-Sync] Menahan log absensi untuk employee_id sementara yang belum disync: ${empIdStr}`);
        return false;
      }
      return true;
    });

    if (!pendingLogs || pendingLogs.length === 0) {
      return { count: 0 };
    }

    // Pisahkan logs untuk Step A (Text Push) dan Step B (Photo Push)
    const textPendingLogs = pendingLogs.filter(log => log.status_sync_teks !== 'done');
    const photoPendingLogs = pendingLogsRaw.filter(log => 
      (log.status_sync_teks === 'done' || !textPendingLogs.find(t => t.id === log.id)) && 
      log.status_sync_foto !== 'done' && 
      log.path_foto_lokal
    );

    let syncedTextIds = [];
    let localToRealIdMap = {};

    if (textPendingLogs.length > 0) {
      isSyncing = true;
      console.log(`[Auto-Sync] Step A: Attempting to sync ${textPendingLogs.length} pending text logs...`);

      const logsToInsert = textPendingLogs.map(log => {
        const logKebun = log.kebun || log.nama_kebun || '-';
        const logAfdeling = log.afdeling || '-';
        let formattedLocation = log.location || '';
        if (formattedLocation && !formattedLocation.includes(' | ')) {
          formattedLocation = `${logKebun} | ${logAfdeling} | ${formattedLocation}`;
        }
        return {
          employee_id: log.employee_id,
          timestamp: log.timestamp,
          location: formattedLocation,
          status: log.status,
          euclidean_distance: log.euclidean_distance,
          latitude: log.latitude !== undefined ? log.latitude : (log.lat !== undefined ? log.lat : null),
          longitude: log.longitude !== undefined ? log.longitude : (log.lng !== undefined ? log.lng : null),
          durasi: log.durasi || null,
          attendance_type: (log.attendance_type || 'CHECK-IN').replace('_', '-'),
          nik: log.nik || null,
          name: log.name || null,
          department: log.department || null,
          match_cosine: log.match_cosine !== undefined && log.match_cosine !== null ? Number(log.match_cosine) : null,
          match_threshold: log.match_threshold !== undefined && log.match_threshold !== null ? Number(log.match_threshold) : null,
          template_version: (log.template_version !== undefined && log.template_version !== null && log.template_version !== '') ? Number(log.template_version) : null,
          facing_mode: log.facing_mode || null
        };
      });

    let syncedIds = [];
    let successfulData = [];
    let telemetryExcluded = false;

    let { data, error } = await supabase
      .from('attendance_logs')
      .insert(logsToInsert)
      .select();

    // Adaptive schema matching: if telemetry columns not yet migrated on attendance_logs (42703)
    if (error && isAttendanceMissingColumnError(error)) {
      telemetryExcluded = true;
      console.warn('[Auto-Sync] attendance_logs server belum memiliki kolom telemetri (42703). Mencoba insert ulang tanpa kolom telemetri...');
      if (typeof window !== 'undefined') {
        window.__attendanceLogsSchemaWarning = 'Kolom telemetri attendance_logs belum dimigrasi di server (42703). Log absensi dikirim tanpa kolom telemetri.';
      }
      const retryRes = await supabase
        .from('attendance_logs')
        .insert(stripAttendanceTelemetry(logsToInsert))
        .select();
      data = retryRes.data;
      error = retryRes.error;
    }

    if (error) {
      console.warn('[Auto-Sync] Bulk insert failed (likely due to FK violation/409 Conflict). Falling back to sequential for...of insert:', error.message || error);
      
      // Requirement 2: Sekuensial lambat (for...of) dengan try...catch per iterasi individu
      for (let i = 0; i < textPendingLogs.length; i++) {
        let singleLogPayload = logsToInsert[i];
        if (telemetryExcluded) {
          singleLogPayload = stripAttendanceTelemetry(singleLogPayload);
        }
        const originalLog = textPendingLogs[i];

        try {
          let { data: singleData, error: singleError } = await supabase
            .from('attendance_logs')
            .insert([singleLogPayload])
            .select();
          
          // Adaptive fallback retry for individual item if 42703 occurs
          if (singleError && isAttendanceMissingColumnError(singleError)) {
            telemetryExcluded = true;
            console.warn(`[Auto-Sync Fallback] attendance_logs missing telemetry columns (42703) on log #${originalLog.id}. Retrying without telemetry...`);
            if (typeof window !== 'undefined') {
              window.__attendanceLogsSchemaWarning = 'Kolom telemetri attendance_logs belum dimigrasi di server (42703). Log absensi dikirim tanpa kolom telemetri.';
            }
            singleLogPayload = stripAttendanceTelemetry(singleLogPayload);
            const singleRetry = await supabase
              .from('attendance_logs')
              .insert([singleLogPayload])
              .select();
            singleData = singleRetry.data;
            singleError = singleRetry.error;
          }

          if (singleError) {
            console.error(`[Auto-Sync Fallback Error] Gagal insert log ID #${originalLog.id} (employee_id: ${singleLogPayload.employee_id}):`, singleError.message || singleError);
            
            // Requirement 1: Jika FK violation (23503), JANGAN HAPUS LOG. Tandai butuh resolusi NIK
            if (singleError.code === '23503') {
              console.warn(`[Auto-Sync Fallback] FK violation 23503 untuk log #${originalLog.id}. Menandai butuh resolusi NIK manual.`);
              originalLog.needs_resolution = true;
              originalLog.sync_notes = 'Membutuhkan Resolusi NIK';
              await updateSyncStatus(originalLog.id, 'pending', null, 'Membutuhkan Resolusi NIK');
            } else if (singleError.code === '23505' || singleError.message?.includes('duplicate key')) {
              // Jika log sudah ada di server (duplikat), status diubah ke 'done' agar tidak menggantung
              console.warn(`[Auto-Sync Fallback] Log #${originalLog.id} sudah ada di server (duplicate key). Memperbarui status lokal ke done.`);
              
              // Coba dapatkan real ID dari server untuk duplicate key ini
              const { data: dupData } = await supabase.from('attendance_logs')
                .select('id')
                .eq('employee_id', singleLogPayload.employee_id)
                .eq('timestamp', singleLogPayload.timestamp)
                .limit(1);
                
              if (dupData && dupData.length > 0) {
                localToRealIdMap[originalLog.id] = dupData[0].id;
              }
              
              await updateSyncStatus(originalLog.id, 'done', null);
              syncedIds.push(originalLog.id);
            } else {
              // Requirement 4: Lempar error jika gagal (No Silent Failures)
              throw singleError;
            }
          } else if (singleData && Array.isArray(singleData) && singleData.length > 0) {
            // Requirement 3: Validasi Keberhasilan Absolut (response.data valid & response.error null)
            console.log(`[Auto-Sync Fallback Success] Berhasil insert log #${originalLog.id} ke Supabase.`);
            localToRealIdMap[originalLog.id] = singleData[0].id;
            const syncNote = telemetryExcluded ? 'Log tersimpan (telemetri 42703 dilewati)' : null;
            await updateSyncStatus(originalLog.id, 'done', null, syncNote);
            syncedIds.push(originalLog.id);
            successfulData.push(singleData[0]);
          }
        } catch (singleCatchErr) {
          console.error(`[Auto-Sync Fallback Exception] Exception saat insert log #${originalLog.id}:`, singleCatchErr);
          // Biarkan data tetap berstatus 'pending' di DB lokal untuk di-retry nanti
        }
      }
    } else {
      // Requirement 3: Validasi Keberhasilan Absolut untuk Bulk Insert
      if (data && Array.isArray(data) && data.length > 0) {
        syncedIds = textPendingLogs.map(log => log.id);
        successfulData = data;
        const syncNote = telemetryExcluded ? 'Log tersimpan (telemetri 42703 dilewati)' : null;
        for (let i = 0; i < syncedIds.length; i++) {
          if (data[i]) {
            localToRealIdMap[syncedIds[i]] = data[i].id;
          }
          await updateSyncStatus(syncedIds[i], 'done', null, syncNote);
        }
      }
    }

    // Requirement 5 (Bug Fix Asymmetric Sync untuk Request):
    // Remap log_id pada antrean attendance_requests jika log_id sementara ('offline_x') mendapat ID asli dari Supabase
    const remappedIds = Object.keys(localToRealIdMap);
    if (remappedIds.length > 0) {
      try {
        const localRequests = await db.attendance_requests.toArray();
        for (const localId of remappedIds) {
          const realId = localToRealIdMap[localId];
          if (String(localId) !== String(realId)) {
            const reqsToUpdate = localRequests.filter(req => 
              String(req.log_id) === String(localId) || 
              (req.old_value && String(req.old_value.logId) === String(localId)) ||
              (req.old_value && String(req.old_value.inLogId) === String(localId))
            );

            for (let req of reqsToUpdate) {
              req.log_id = realId;
              if (req.old_value && req.old_value.logId === localId) req.old_value.logId = realId;
              if (req.old_value && req.old_value.inLogId === localId) req.old_value.inLogId = realId;
              await db.attendance_requests.put(req);
              console.log(`[Auto-Sync Remap] Request #${req.id} diperbarui: log_id dari ${localId} menjadi ${realId}`);
            }
          }
        }
      } catch (remapErr) {
        console.error('[Auto-Sync Remap] Gagal me-remap log_id untuk request:', remapErr);
      }
    }

    syncedTextIds = syncedIds;

    if (syncedTextIds.length > 0) {
      console.log(`[Auto-Sync Step A] Successfully synced ${syncedTextIds.length} text records!`);
      const noPhotoIds = [];
      for (const id of syncedTextIds) {
        const logToPhoto = textPendingLogs.find(l => l.id === id);
        if (logToPhoto && logToPhoto.path_foto_lokal) {
          if (!photoPendingLogs.some(p => p.id === logToPhoto.id)) {
            photoPendingLogs.push(logToPhoto); // Push to photo queue for Step B
          }
        } else {
          noPhotoIds.push(id);
        }
      }
      
      if (noPhotoIds.length > 0) {
        await removeSyncedLogs(noPhotoIds);
      }
    }
    } // end if textPendingLogs.length > 0

    // Step B: Sequential Photo Push
    let syncedPhotoCount = 0;
    if (photoPendingLogs.length > 0) {
      isSyncing = true;
      console.log(`[Auto-Sync] Step B: Attempting to sync ${photoPendingLogs.length} pending photos...`);
      for (const log of photoPendingLogs) {
        try {
          let base64String = null;
          if (log.path_foto_lokal && (log.path_foto_lokal.startsWith('data:image/') || log.path_foto_lokal.length > 500)) {
            base64String = log.path_foto_lokal;
          } else if (log.path_foto_lokal) {
            try {
              // Read base64 from Capacitor Filesystem (native platform)
              const readResult = await Filesystem.readFile({
                path: log.path_foto_lokal
              });
              base64String = readResult.data;
            } catch (fsErr) {
              // Fallback to Directory.Data using filename
              const fileName = log.path_foto_lokal.split('/').pop();
              const readResult = await Filesystem.readFile({
                path: fileName,
                directory: Directory.Data
              });
              base64String = readResult.data;
            }
          }

          if (!base64String) {
            throw new Error('Local photo path/data is empty or invalid.');
          }

          // Convert Base64 to Blob / Buffer for Supabase Storage
          const dataUrl = base64String.startsWith('data:image/')
            ? base64String
            : `data:image/jpeg;base64,${base64String}`;

          const response = await fetch(dataUrl);
          const blob = await response.blob();
          
          const uuid = log.id + '_' + Date.now();
          const storagePath = `absensi_harian/${log.nik}/${uuid}.jpg`;

          const { error: uploadError } = await supabase.storage
            .from('attendance-photos')
            .upload(storagePath, blob, {
              contentType: 'image/jpeg',
              upsert: true
            });

          if (uploadError) {
            console.warn(`[Auto-Sync Step B] Upload failed for log ${log.id}:`, uploadError);
            continue; // Skip this one, try again next time
          }

          // Get public URL
          const { data: publicUrlData } = supabase.storage
            .from('attendance-photos')
            .getPublicUrl(storagePath);
          
          const publicUrl = publicUrlData.publicUrl;

          // Requirement 3: Update text log in server with photo url and verify response
          // Resolve real server ID to ensure reliable primary-key match (preventing timestamptz string format mismatch)
          let realServerId = localToRealIdMap[log.id];
          if (!realServerId && log.id && !String(log.id).startsWith('offline_') && !String(log.id).startsWith('temp_') && !isNaN(Number(log.id))) {
            realServerId = Number(log.id);
          }
          if (!realServerId) {
            const { data: existingLog } = await supabase
              .from('attendance_logs')
              .select('id')
              .eq('employee_id', log.employee_id)
              .eq('timestamp', log.timestamp)
              .limit(1);
            if (existingLog && existingLog.length > 0) {
              realServerId = existingLog[0].id;
              localToRealIdMap[log.id] = realServerId;
            }
          }

          let updateQuery = supabase.from('attendance_logs').update({ path_foto: publicUrl });
          if (realServerId) {
            updateQuery = updateQuery.eq('id', realServerId);
          } else {
            updateQuery = updateQuery
              .eq('employee_id', log.employee_id)
              .eq('timestamp', log.timestamp);
          }

          const { data: photoUpdateData, error: photoUpdateErr } = await updateQuery.select();

          if (photoUpdateErr) {
            console.warn(`[Auto-Sync Step B] Failed to update path_foto in cloud for log ${log.id}:`, photoUpdateErr.message || photoUpdateErr);
            continue;
          }

          if (photoUpdateData && Array.isArray(photoUpdateData) && photoUpdateData.length > 0) {
            // Step C: Garbage Collection (Delete local file and set status_sync_foto = 'done' ONLY upon verified response)
            await updateSyncStatus(log.id, null, 'done');
            await removeSyncedLogs([log.id]);
            
            if (Capacitor.isNativePlatform() && log.path_foto_lokal && !log.path_foto_lokal.startsWith('data:image/')) {
              try {
                await Filesystem.deleteFile({
                  path: log.path_foto_lokal
                });
                console.log(`[Auto-Sync Step C] Garbage collected local photo for log ${log.id}`);
              } catch (delErr) {
                try {
                  const fileName = log.path_foto_lokal.split('/').pop();
                  await Filesystem.deleteFile({
                    path: fileName,
                    directory: Directory.Data
                  });
                } catch (delErr2) {
                  console.warn(`[Auto-Sync Step C] Failed to delete local photo:`, delErr2);
                }
              }
            }

            syncedPhotoCount++;
          } else {
            console.warn(`[Auto-Sync Step B] Server returned empty response for photo update of log ${log.id}. Retaining local photo.`);
          }

        } catch (photoErr) {
          console.warn(`[Auto-Sync Step B Error] Processing photo for log ${log.id}:`, photoErr);
        }
      }
    }

    // Purge local queue for all verified synced text logs
    if (syncedTextIds.length > 0) {
      await removeSyncedLogs(syncedTextIds);
    }
    
    // Write sync action to public backup log
    if (Capacitor.isNativePlatform() && (syncedTextIds.length > 0 || syncedPhotoCount > 0)) {
      const timestamp = new Date().toISOString();
      const syncLine = `[${timestamp}] [SYNC SUCCESS] Uploaded ${syncedTextIds.length} texts and ${syncedPhotoCount} photos to cloud.\n`;
      await writeToBackupStorage(syncLine);
    }

    if (syncedTextIds.length > 0 || syncedPhotoCount > 0) {
      if (typeof window !== 'undefined') {
        window.dispatchEvent(new CustomEvent('refresh_logs'));
      }
    }

    if (showToast && (syncedTextIds.length > 0 || syncedPhotoCount > 0)) {
      showToast(
        'Auto-Sync Berhasil',
        `Berhasil mengunggah ${syncedTextIds.length} data dan ${syncedPhotoCount} foto absensi offline!`,
        'success'
      );
    }

    if (onSyncComplete && (syncedTextIds.length > 0 || syncedPhotoCount > 0)) {
      onSyncComplete();
    }

    return { count: syncedTextIds.length + syncedPhotoCount };
  } catch (err) {
    console.error('[Auto-Sync Error]:', err.message || err, err.details || '', err.hint || '', err.code || '');
  } finally {
    isSyncing = false;
  }

  return { count: 0 };
}

/**
 * Executes Auto-Sync of offline admin approval requests to Supabase.
 * Also handles DELETE_LOG requests: deletes from Supabase attendance_logs when online.
 */
export async function syncPendingAttendanceRequests() {
  const isOnline = await checkOnline();
  if (!isOnline) return { count: 0 };

  try {
    const { db } = await import('./db');
    const allReqs = await db.attendance_requests.toArray();
    const unsyncedReqsRaw = allReqs.filter(r => !r.is_synced);

    // --- LOGIKA CUT-OFF 22:00 ---
    const waktuCutOff = new Date();
    waktuCutOff.setHours(22, 0, 0, 0);
    const batasCutOffTime = waktuCutOff.getTime();

    const unsyncedReqs = unsyncedReqsRaw.filter(r => {
      if (!r.requested_at) return true;
      return new Date(r.requested_at).getTime() <= batasCutOffTime;
    });

    if (unsyncedReqsRaw.length > 0 && unsyncedReqs.length === 0) {
      console.log(`[Auto-Sync Requests] ${unsyncedReqsRaw.length} request pending berada di luar cut-off (setelah 22:00). Ditunda hingga besok.`);
      return { count: 0 };
    }
    // ----------------------------

    if (unsyncedReqs.length === 0) return { count: 0 };

    console.log(`[Auto-Sync Requests] Attempting to sync ${unsyncedReqs.length} pending offline admin requests...`);

    let syncedCount = 0;

    for (const r of unsyncedReqs) {
      if (r.status === 'APPROVED') {
        try {
          if (r.request_type === 'DELETE') {
            const inLogId = r.old_value?.inLogId || r.old_value?.logId || r.log_id;
            const outLogId = r.old_value?.outLogId;
            const idsToDelete = [inLogId, outLogId].filter(Boolean);
            
            if (idsToDelete.length > 0) {
              const { error: delErr } = await supabase.from('attendance_logs').delete().in('id', idsToDelete);
              if (delErr) {
                console.warn(`[Auto-Sync] Gagal execute delete untuk request ${r.id}:`, delErr.message);
                continue; // Skip, don't mark as synced
              }
              
              // Skenario Resolution (Success): Execute local Hard Delete
              const { db } = await import('./db');
              for (const id of idsToDelete) {
                await db.attendance_logs.hardDelete(String(id));
              }
            }
          } else if (r.request_type === 'EDIT' && r.new_value) {
            let editSuccess = true;
            const newVal = r.new_value;
            
            if (newVal.inLogId && newVal.checkIn) {
              const [hours, minutes, seconds] = newVal.checkIn.split(':');
              const { data: logData } = await supabase.from('attendance_logs').select('timestamp').eq('id', newVal.inLogId).single();
              if (logData) {
                const oldDate = new Date(logData.timestamp);
                oldDate.setHours(parseInt(hours||0), parseInt(minutes||0), parseInt(seconds||0));
                let newStatus = newVal.keterangan === 'Hadir' ? 'Hadir (Verified)' : newVal.keterangan;
                newStatus = `[CHECK-IN BERHASIL] - ${newStatus}`;
                const { error: updErr } = await supabase.from('attendance_logs').update({ timestamp: oldDate.toISOString(), status: newStatus }).eq('id', newVal.inLogId);
                if (updErr) editSuccess = false;
              }
            }
            if (newVal.outLogId && newVal.checkOut) {
              const [hours, minutes, seconds] = newVal.checkOut.split(':');
              const { data: logData } = await supabase.from('attendance_logs').select('timestamp').eq('id', newVal.outLogId).single();
              if (logData) {
                const oldDate = new Date(logData.timestamp);
                oldDate.setHours(parseInt(hours||0), parseInt(minutes||0), parseInt(seconds||0));
                let newStatus = newVal.keterangan === 'Hadir' ? 'Hadir (Verified)' : newVal.keterangan;
                newStatus = `[CHECK-OUT BERHASIL] - ${newStatus}`;
                const { error: updErr } = await supabase.from('attendance_logs').update({ timestamp: oldDate.toISOString(), status: newStatus }).eq('id', newVal.outLogId);
                if (updErr) editSuccess = false;
              }
            }
            if (!editSuccess) {
              console.warn(`[Auto-Sync] Gagal execute edit untuk request ${r.id}`);
              continue; // Skip
            }
          }
        } catch (execErr) {
          console.error(`[Auto-Sync] Exception executing request ${r.id}:`, execErr);
          continue;
        }
      }

      // After executing, insert into attendance_requests for audit trail
      const reqPayload = {
        id: r.id,
        request_type: r.request_type,
        log_id: r.log_id,
        nik: r.nik || null,
        name: r.name || null,
        nama_kebun: r.nama_kebun || null,
        requested_by: r.requested_by,
        requested_at: r.requested_at || new Date().toISOString(),
        status: r.status || 'PENDING',
        old_value: r.old_value || null,
        new_value: r.new_value || null
      };

      const { error: insErr } = await supabase.from('attendance_requests').upsert([reqPayload]);
      if (insErr && !insErr.message.includes('relation "public.attendance_requests" does not exist')) {
         console.warn(`[Auto-Sync] Failed to insert audit trail for ${r.id}:`, insErr.message);
      }
      
      await db.attendance_requests.put({ ...r, is_synced: true });
      syncedCount++;
    }

    if (syncedCount > 0) {
      console.log(`[Auto-Sync Requests Success] Processed ${syncedCount} admin requests!`);
    }
    return { count: syncedCount };
  } catch (err) {
    console.error('[Sync Engine Requests Error]:', err.message || err);
    return { count: 0 };
  }
}

/**
 * Executes Auto-Sync of offline registered employees & biometrics to Supabase
 */
export async function syncPendingEmployees(showToast = null, onSyncComplete = null) {
  const isOnline = await checkOnline();
  if (!isOnline) return { count: 0 };

  try {
    const isNative = Capacitor.isNativePlatform();
    let pendingEmpsRaw = [];

    if (isNative) {
      const { sqliteGetPendingEmployees } = await import('./services/sqliteService');
      pendingEmpsRaw = await sqliteGetPendingEmployees();
    } else {
      const { db } = await import('./db');
      pendingEmpsRaw = await db.employee_sync_queue.toArray();
    }

    if (!pendingEmpsRaw || pendingEmpsRaw.length === 0) return { count: 0 };

    // Master employee records are not constrained by shift checkout cut-off (PRD 3.3).
    // They should sync whenever network connectivity is available.
    const pendingEmps = pendingEmpsRaw;

    const { cacheUserMasterVector, db } = await import('./db');

    console.log(`[Auto-Sync Employees] Attempting to sync ${pendingEmps.length} offline registered employees...`);
    
    // FETCH PENDING LOGS ONCE OUTSIDE THE LOOP! (Massive performance boost)
    const allPendingLogs = await getUnsyncedLogs();

    const employeePromises = pendingEmps.map(async (emp) => {
      try {
        let realEmpId = null;

        let finalNik = emp.nik ?? '';
        if (finalNik === '' || finalNik === '-' || finalNik === 'Tanpa NIK') {
          // Generate a unique fallback NIK to prevent Supabase 23505 UNIQUE constraint collision
          // which would otherwise cause multiple offline employees to be merged into one.
          finalNik = 'OFF_NIK_' + Date.now() + '_' + Math.random().toString(36).substring(2, 6);
        }

        // 1. Call RPC for conflict resolution
        const payload = {
          nik: finalNik,
          name: emp.name ?? '',
          department: emp.department ?? emp.jabatan ?? '',
          afdeling: emp.afdeling ?? '',
          nama_kebun: emp.nama_kebun ?? '',
          status_tk: emp.status_tk ?? '',
          jabatan: emp.jabatan ?? '',
          status_perkawinan: emp.status_perkawinan ?? '',
          has_master_biometric: !!emp.descriptor_json || emp.has_master_biometric === true || emp.has_master_biometric === 1
        };

        const { data: rpcData, error: rpcError } = await supabase.rpc('sync_offline_employee', { payload });

        if (rpcError) {
          console.warn(`[Sync Employee Fail] Gagal sync karyawan ${emp.name}:`, rpcError.message);
          await db.employee_sync_queue.updateError(emp.id, {
            last_error_code: rpcError.code || 'RPC_ERROR',
            last_error_message: rpcError.message,
            last_error_step: 'employees_rpc'
          });
          if (typeof window !== 'undefined') {
            window.dispatchEvent(new CustomEvent('agriface-toast', {
              detail: { title: 'Error Sinkronisasi', message: `Gagal mengirim karyawan ${emp.name}.\nError: [${rpcError.code || 'RPC'}] ${rpcError.message}`, type: 'error' }
            }));
          }
          return false;
        }

        if (rpcData && rpcData.status === 'rejected_deleted') {
          console.warn(`[Sync Employee Edge Case] Edit ditolak! Karyawan ${emp.nik} sudah dihapus secara permanen di server (Soft Deleted).`);
          // Skip updating server, but we must purge this local ghost edit
          const { deleteLocalEmployee } = await import('./db');
          await deleteLocalEmployee(emp.id);
          if (isNative) {
            const { sqliteRemovePendingEmployee } = await import('./services/sqliteService');
            await sqliteRemovePendingEmployee(emp.id);
          } else {
            await db.employee_sync_queue.delete(emp.id);
          }
          return true; // Return true to mark as "processed" so it doesn't block queue
        }

        if (rpcData && rpcData.status === 'error') {
           console.warn(`[Sync Employee Error] Error dari RPC:`, rpcData.message);
           await db.employee_sync_queue.updateError(emp.id, {
             last_error_code: 'RPC_RETURN_ERROR',
             last_error_message: rpcData.message || 'RPC menolak data karyawan',
             last_error_step: 'employees_rpc'
           });
           if (typeof window !== 'undefined') {
             window.dispatchEvent(new CustomEvent('agriface-toast', {
               detail: { title: 'Error RPC Sinkronisasi', message: `Gagal mengirim karyawan ${emp.name}.\nError: ${rpcData.message}`, type: 'error' }
             }));
           }
           return false;
        }

        if (rpcData && (rpcData.status === 'merged' || rpcData.status === 'inserted')) {
          realEmpId = rpcData.id;
          console.log(`[Sync Employee] Sukses (${rpcData.status}) data karyawan ${emp.nik}`);
        } else {
          console.warn(`[Sync Employee] Status RPC tidak dikenali:`, rpcData);
          await db.employee_sync_queue.updateError(emp.id, {
            last_error_code: 'UNKNOWN_RPC_STATUS',
            last_error_message: `Status RPC tidak dikenali: ${JSON.stringify(rpcData)}`,
            last_error_step: 'employees_rpc'
          });
          return false;
        }

        // 2. Insert master biometrics descriptor if present
        const normalizedVec = toVectorArray(emp.descriptor_json);

        if (normalizedVec && Array.isArray(normalizedVec)) {
          const checksumVal = vectorChecksum(normalizedVec);
          let descUpsertOk = false;

          const queueTemplateVer = (emp.template_version !== undefined && emp.template_version !== null && emp.template_version !== '')
            ? Number(emp.template_version)
            : null;

          const fullPayload = {
            employee_id: realEmpId,
            descriptor_json: normalizedVec,
            updated_at: new Date().toISOString(),
            template_version: queueTemplateVer,
            vector_checksum: checksumVal
          };

          const { error: descErr } = await supabase
            .from('master_descriptors')
            .upsert(fullPayload, { onConflict: 'employee_id' });

          if (descErr) {
            // Adaptive schema matching: if column vector_checksum / template_version not yet migrated (42703)
            if (descErr.code === '42703' || String(descErr.message || '').includes('vector_checksum') || String(descErr.message || '').includes('template_version')) {
              console.warn('[Sync Employee] master_descriptors table missing vector_checksum/template_version (42703). Fallback to standard schema.');
              const fallbackPayload = {
                employee_id: realEmpId,
                descriptor_json: normalizedVec,
                updated_at: new Date().toISOString()
              };
              const { error: fallbackErr } = await supabase
                .from('master_descriptors')
                .upsert(fallbackPayload, { onConflict: 'employee_id' });

              if (fallbackErr) {
                console.error('[Sync Employee] Fallback upsert master_descriptors failed:', fallbackErr);
                await db.employee_sync_queue.updateError(emp.id, {
                  last_error_code: fallbackErr.code || 'DESC_FALLBACK_ERR',
                  last_error_message: fallbackErr.message,
                  last_error_step: 'master_descriptors'
                });
                return false;
              } else {
                descUpsertOk = true;
              }
            } else {
              console.error('[Sync Employee] Failed to sync master descriptor:', descErr.message || descErr);
              await db.employee_sync_queue.updateError(emp.id, {
                last_error_code: descErr.code || 'DESC_ERR',
                last_error_message: descErr.message,
                last_error_step: 'master_descriptors'
              });
              return false;
            }
          } else {
            descUpsertOk = true;
          }

          if (descUpsertOk) {
            await cacheUserMasterVector({
              employee_id: realEmpId,
              nik: emp.nik,
              name: emp.name,
              department: emp.department || emp.jabatan,
              afdeling: emp.afdeling,
              nama_kebun: emp.nama_kebun,
              status_tk: emp.status_tk,
              jabatan: emp.jabatan,
              status_perkawinan: emp.status_perkawinan,
              descriptor_json: normalizedVec,
              template_version: queueTemplateVer !== null ? queueTemplateVer : 1,
              vector_checksum: checksumVal
            });
          }
        }

        // 3. Update any pending offline attendance logs that used this temporary employee ID
        try {
          const logsToUpdate = allPendingLogs.filter(log => String(log.employee_id) === String(emp.id));
          const updateLogPromises = logsToUpdate.map(async (pendingLog) => {
            console.log(`[Sync Engine FK Update] Updating pending log #${pendingLog.id} employee_id from ${emp.id} to ${realEmpId}`);
            pendingLog.employee_id = realEmpId;
            if (isNative) {
              const { sqliteUpdatePendingAttendanceEmployeeId } = await import('./services/sqliteService');
              await sqliteUpdatePendingAttendanceEmployeeId(emp.id, realEmpId);
            } else {
              await db.attendance_sync_queue.put(pendingLog);
              const localLog = await db.attendance_logs.get(pendingLog.id);
              if (localLog) {
                await db.attendance_logs.put({ ...localLog, employee_id: realEmpId });
              }
            }
          });
          await Promise.all(updateLogPromises);
        } catch (fkErr) {
          console.warn('[Sync Engine FK Mapping Error]:', fkErr);
        }

        // 3B. Update pending face thumbs and scan attempts FK if temporary ID was used
        try {
          const tempIdStr = String(emp.id);
          if (isNative) {
            const { waitForConnection } = await import('./services/sqliteService');
            const nativeDb = await waitForConnection();
            if (nativeDb) {
              await nativeDb.run(
                `UPDATE local_employee_face_thumbs SET employee_id = ? WHERE employee_id = ?`,
                [String(realEmpId), tempIdStr]
              );
              await nativeDb.run(
                `UPDATE local_scan_attempts_queue SET employee_id = ? WHERE employee_id = ?`,
                [String(realEmpId), tempIdStr]
              );
            }
          } else {
            const { dexieDb } = await import('./db');
            if (dexieDb?.employee_face_thumbs) {
              const thumbItem = await dexieDb.employee_face_thumbs.get(tempIdStr);
              if (thumbItem) {
                await dexieDb.employee_face_thumbs.delete(tempIdStr);
                await dexieDb.employee_face_thumbs.put({ ...thumbItem, employee_id: String(realEmpId) });
              }
            }
          }
        } catch (secFkErr) {
          console.warn('[Sync Engine Secondary FK Mapping Error]:', secFkErr);
        }

        // 4. Remove temp ID from local employee sync queue
        if (isNative) {
          const { sqliteRemovePendingEmployee } = await import('./services/sqliteService');
          await sqliteRemovePendingEmployee(emp.id);
        } else {
          await db.employee_sync_queue.delete(emp.id);
        }
        
        // PURGE ghost employee dari local cache HANYA jika ID-nya adalah temporary offline ID.
        // Karyawan real (ID numerik dari Supabase) TIDAK boleh dihapus di sini.
        const tempIdStr = String(emp.id);
        if (tempIdStr.startsWith('offline_') || tempIdStr.startsWith('off_emp_') || tempIdStr.startsWith('tmp_') || isNaN(Number(tempIdStr))) {
          const { hardDeleteLocalEmployee } = await import('./db');
          await hardDeleteLocalEmployee(emp.id);
          console.log(`[Sync Employee] Ghost employee ${tempIdStr} dihapus dari cache lokal.`);

          // CRITICAL: Tambahkan ulang karyawan ke employees_cache dengan ID RESMI dari Supabase
          // agar fetchLogs tidak lagi menerima ID offline saat membangun query ke Supabase.
          try {
            const realEmpEntry = {
              id: realEmpId,
              nik: emp.nik,
              name: emp.name,
              department: emp.department || emp.jabatan,
              afdeling: emp.afdeling || null,
              nama_kebun: emp.nama_kebun || null,
              region: emp.region || null,
              status_tk: emp.status_tk || null,
              jabatan: emp.jabatan || null,
              status_perkawinan: emp.status_perkawinan || null,
              has_master_biometric: !!emp.descriptor_json || emp.has_master_biometric === true || emp.has_master_biometric === 1,
              is_synced: true,
              descriptor_json: emp.descriptor_json || null,
              geometric_descriptor_json: emp.geometric_descriptor_json || null
            };
            
            if (isNative) {
              const { sqliteBulkPutEmployeesCache } = await import('./services/sqliteService');
              await sqliteBulkPutEmployeesCache([realEmpEntry]);
            } else {
              await db.employees_cache.put(realEmpEntry);
            }
            console.log(`[Sync Employee] employees_cache diperbarui: ${tempIdStr} → ${realEmpId} untuk ${emp.name}`);
          } catch (cacheErr) {
            console.warn(`[Sync Employee] Gagal update employees_cache untuk ${emp.name}:`, cacheErr);
          }

          // JUGA: Update attendance_logs lokal (display cache) dari ID offline ke ID resmi
          try {
            const allDisplayLogs = await db.attendance_logs.toArray();
            const offlineLogs = allDisplayLogs.filter(l => String(l.employee_id) === tempIdStr);
            for (const ol of offlineLogs) {
              ol.employee_id = realEmpId;
              await db.attendance_logs.put(ol);
            }
            if (offlineLogs.length > 0) {
              console.log(`[Sync Employee] ${offlineLogs.length} attendance_logs lokal diupdate ke employee_id: ${realEmpId}`);
            }
          } catch (logCacheErr) {
            console.warn(`[Sync Employee] Gagal update attendance_logs lokal:`, logCacheErr);
          }
        }

        console.log(`[Sync Employee Success] Karyawan ${emp.name} synced dengan ID real ${realEmpId}`);
        return true;
      } catch (singleErr) {
        console.error(`[Sync Single Employee Exception]`, singleErr);
        try {
          const { db } = await import('./db');
          await db.employee_sync_queue.updateError(emp.id, {
            last_error_code: singleErr.code || 'EXCEPTION',
            last_error_message: singleErr.message || String(singleErr),
            last_error_step: 'exception'
          });
        } catch (_) {}
        return false;
      }
    });

    const syncResults = await Promise.all(employeePromises);
    const syncedCount = syncResults.filter(Boolean).length;

    // Langkah Foto: Unggah foto master wajah untuk karyawan yang telah memiliki ID server
    // Dibungkus dalam try/catch terpisah sehingga kegagalan langkah foto tidak menggagalkan langkah inti
    try {
      await syncPendingEmployeeFaceThumbs();
    } catch (photoErr) {
      console.warn('[Sync Employee Photos Non-blocking Error]:', photoErr);
    }

    // Dispatch reactive events so all open UI views update immediately
    if (typeof window !== 'undefined') {
      window.dispatchEvent(new CustomEvent('refresh_logs'));
      window.dispatchEvent(new CustomEvent('refresh_employees'));
    }

    if (syncedCount > 0) {
      if (showToast) {
        showToast('Auto-Sync Karyawan', `Berhasil mengunggah ${syncedCount} data karyawan offline ke server!`, 'success');
      }
      if (onSyncComplete) {
        onSyncComplete();
      }
    }

    return { count: syncedCount };
  } catch (err) {
    console.error('[Sync Engine Pending Employees Error]:', err.message || err);
    return { count: 0 };
  }
}

export async function syncPendingEmployeeDeletes() {
  const isOnline = await checkOnline();
  if (!isOnline) return { count: 0 };

  try {
    const { getEmployeeDeletes, removeEmployeeDelete } = await import('./db');
    const deleteIds = await getEmployeeDeletes();
    if (!deleteIds || deleteIds.length === 0) return { count: 0 };

    console.log(`[Auto-Sync] Attempting to sync ${deleteIds.length} employee deletions...`);
    let syncedCount = 0;

    for (const id of deleteIds) {
      try {
        const { error } = await supabase.from('employees').delete().eq('id', id);
        if (!error || error.code === 'PGRST116') { // PGRST116 means not found, which is fine for delete
          await removeEmployeeDelete(id);
          const { hardDeleteLocalEmployee } = await import('./db');
          await hardDeleteLocalEmployee(id);
          syncedCount++;
        } else {
          console.warn(`[Auto-Sync] Gagal sync delete karyawan ${id}:`, error.message);
        }
      } catch (err) {
        console.warn(`[Auto-Sync] Exception saat sync delete karyawan ${id}:`, err);
      }
    }
    
    if (syncedCount > 0) {
      console.log(`[Auto-Sync] Berhasil sync ${syncedCount} penghapusan karyawan.`);
    }
    return { count: syncedCount };
  } catch (err) {
    console.error('[Sync Engine Employee Deletes Error]:', err.message || err);
    return { count: 0 };
  }
}

export async function syncPendingScanAttempts() {
  const isOnline = await checkOnline();
  if (!isOnline) return { count: 0 };

  try {
    const { getUnsyncedScanAttempts, removeSyncedScanAttempts } = await import('./db');
    const pendingAttempts = await getUnsyncedScanAttempts();
    if (!pendingAttempts || pendingAttempts.length === 0) return { count: 0 };

    console.log(`[Auto-Sync] Syncing ${pendingAttempts.length} scan attempts telemetry to Supabase...`);

    const rows = pendingAttempts.map(a => ({
      employee_id: a.employee_id ? String(a.employee_id) : null,
      nik: a.nik || null,
      attempt_type: a.attempt_type || 'CHECK-IN',
      outcome: a.outcome || 'NOT_MATCHED',
      median_cosine: a.median_cosine != null ? Number(a.median_cosine) : null,
      min_cosine: a.min_cosine != null ? Number(a.min_cosine) : null,
      max_cosine: a.max_cosine != null ? Number(a.max_cosine) : (a.matched_cosine != null ? Number(a.matched_cosine) : null),
      frames_used: a.frames_used != null ? Number(a.frames_used) : (a.frames_evaluated != null ? Number(a.frames_evaluated) : 0),
      threshold_used: a.threshold_used != null ? Number(a.threshold_used) : null,
      facing_mode: a.facing_mode || null,
      canvas_w: a.canvas_w != null ? Number(a.canvas_w) : null,
      canvas_h: a.canvas_h != null ? Number(a.canvas_h) : null,
      brightness: a.brightness != null ? Number(a.brightness) : null,
      master_checksum: a.master_checksum || a.vector_checksum_used || null,
      master_updated_at: a.master_updated_at || null,
      template_version: a.template_version || 1,
      app_version: a.app_version || '3.2.1',
      platform: a.platform || (Capacitor.isNativePlatform() ? 'android' : 'web'),
      created_at: a.created_at || new Date().toISOString()
    }));

    const { error } = await supabase.from('scan_attempts').insert(rows);
    if (error) {
      console.warn('[Auto-Sync Scan Attempts Error]:', error.message);
      return { count: 0 };
    }

    const idsToRemove = pendingAttempts.map(a => a.id).filter(Boolean);
    await removeSyncedScanAttempts(idsToRemove);
    console.log(`[Auto-Sync] Successfully synced and purged ${idsToRemove.length} scan attempts.`);
    return { count: idsToRemove.length };
  } catch (err) {
    console.error('[Auto-Sync Scan Attempts Exception]:', err);
    return { count: 0 };
  }
}

const photoUploadFailures = new Map();

export async function syncPendingEmployeeFaceThumbs() {
  const isOnline = await checkOnline();
  if (!isOnline) return { count: 0 };

  try {
    const { getUnsyncedEmployeeFaceThumbs, markSyncedEmployeeFaceThumbs, db } = await import('./db');
    const isNative = Capacitor.isNativePlatform();
    const pendingThumbs = await getUnsyncedEmployeeFaceThumbs();
    if (!pendingThumbs || pendingThumbs.length === 0) return { count: 0 };

    console.log(`[Auto-Sync] Syncing ${pendingThumbs.length} employee face thumbnails...`);
    const syncedEmpIds = [];

    // Cache pre-fetch untuk rekonsiliasi NIK presisi
    let cachedEmps = [];
    try {
      cachedEmps = await db.employees_cache.toArray();
    } catch (_) {}

    for (const item of pendingThumbs) {
      let empId = item.employee_id;

      // ─── REKONSILIASI NIK (Hanya Bila Tepat Satu Kecocokan) ───
      if (String(empId).startsWith('off_') || String(empId).startsWith('tmp_')) {
        try {
          // Cari karyawan di cache yang mungkin sudah terunggah dan memiliki ID resmi
          let matched = [];
          if (item.nik) {
            matched = (cachedEmps || []).filter(e => e.nik && String(e.nik).trim() === String(item.nik).trim());
          }
          if (matched.length !== 1) {
            // Coba cari kecocokan offline_id
            matched = (cachedEmps || []).filter(e => String(e.offline_id) === String(empId) || String(e.id) === String(empId));
          }

          // REKONSILIASI HANYA BILA TEPAT SATU KECOCOKAN DAN SUDAH PUNYA ID SERVER
          if (matched.length === 1 && matched[0].id && !String(matched[0].id).startsWith('off_') && !String(matched[0].id).startsWith('tmp_')) {
            const newRealId = String(matched[0].id);
            console.log(`[Sync Face Thumb] Rekonsiliasi NIK berhasil: ${empId} -> ${newRealId}`);
            if (isNative) {
              const { waitForConnection } = await import('./services/sqliteService');
              const nativeDb = await waitForConnection();
              if (nativeDb) {
                await nativeDb.run(
                  `UPDATE local_employee_face_thumbs SET employee_id = ? WHERE employee_id = ?`,
                  [newRealId, String(empId)]
                );
              }
            } else {
              const { dexieDb } = await import('./db');
              if (dexieDb?.employee_face_thumbs) {
                await dexieDb.employee_face_thumbs.delete(String(empId));
                await dexieDb.employee_face_thumbs.put({ ...item, employee_id: newRealId });
              }
            }
            empId = newRealId;
            item.employee_id = newRealId;
          } else {
            // Belum ada ID server resmi atau kecocokan ambigu, tunda sampai karyawan inti terunggah
            continue;
          }
        } catch (recErr) {
          console.warn('[Sync Face Thumb Reconciliation Error]:', recErr);
          continue;
        }
      }

      // Retry backoff bertingkat (5s, 10s, 20s, max 60s)
      const prevFail = photoUploadFailures.get(String(empId));
      const failCount = prevFail?.count || 0;
      const lastAttempt = prevFail?.timestamp || 0;
      const backoffMs = Math.min(60000, 5000 * Math.pow(2, failCount));

      if (failCount > 0 && (Date.now() - lastAttempt) < backoffMs) {
        console.warn(`[Sync Face Thumb] Skipping ${empId} during backoff (${Math.round((backoffMs - (Date.now() - lastAttempt)) / 1000)}s remaining)`);
        continue;
      }

      try {
        const base64Data = (item.thumb_base64 || '').replace(/^data:image\/\w+;base64,/, '');
        if (!base64Data) continue;

        const byteCharacters = atob(base64Data);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'image/jpeg' });

        const storagePath = `faces/${empId}/master.jpg`;
        let storageUploadSuccess = false;

        const { error: uploadErr } = await supabase.storage
          .from('attendance-photos')
          .upload(storagePath, blob, {
            contentType: 'image/jpeg',
            upsert: true
          });

        if (uploadErr) {
          console.warn(`[Sync Face Thumb] Storage upload failed for ${empId}:`, uploadErr.message);
          photoUploadFailures.set(String(empId), { count: failCount + 1, timestamp: Date.now() });

          // Catat error ke antrean jika karyawan masih berada di employee_sync_queue (misal rescan)
          try {
            const qItem = await db.employee_sync_queue.get(String(empId));
            if (qItem) {
              await db.employee_sync_queue.updateError(empId, {
                last_error_code: uploadErr.statusCode || uploadErr.code || 'STORAGE_ERR',
                last_error_message: uploadErr.message || 'Gagal mengunggah foto ke storage',
                last_error_step: 'photo'
              });
            }
          } catch (_) {}
        } else {
          storageUploadSuccess = true;
        }

        // Upsert metadata dan thumbnail base64 ke tabel employee_face_photos
        const { error: upsertErr } = await supabase.from('employee_face_photos').upsert({
          employee_id: Number(empId),
          storage_path: storageUploadSuccess ? storagePath : null,
          thumbnail_base64: item.thumb_base64,
          captured_at: item.captured_at || new Date().toISOString(),
          is_primary: true,
          photo_version: item.photo_version || 1,
          photo_checksum: item.photo_checksum || null,
          updated_at: new Date().toISOString()
        }, { onConflict: 'employee_id' });

        if (upsertErr) {
          console.warn(`[Sync Face Thumb] Upsert employee_face_photos failed for ${empId}:`, upsertErr.message);
          photoUploadFailures.set(String(empId), { count: failCount + 1, timestamp: Date.now() });

          try {
            const qItem = await db.employee_sync_queue.get(String(empId));
            if (qItem) {
              await db.employee_sync_queue.updateError(empId, {
                last_error_code: upsertErr.code || 'DB_UPSERT_ERR',
                last_error_message: upsertErr.message || 'Gagal menyimpan metadata foto di database',
                last_error_step: 'photo'
              });
            }
          } catch (_) {}
          continue;
        }

        // Berhasil: tandai tersinkron dan bersihkan catatan kegagalan
        syncedEmpIds.push(empId);
        photoUploadFailures.delete(String(empId));
      } catch (thumbErr) {
        console.warn(`[Sync Face Thumb Exception for ${item.employee_id}]:`, thumbErr);
        photoUploadFailures.set(String(empId), { count: failCount + 1, timestamp: Date.now() });

        try {
          const qItem = await db.employee_sync_queue.get(String(empId));
          if (qItem) {
            await db.employee_sync_queue.updateError(empId, {
              last_error_code: thumbErr.code || 'PHOTO_EXCEPTION',
              last_error_message: thumbErr.message || String(thumbErr),
              last_error_step: 'photo'
            });
          }
        } catch (_) {}
      }
    }

    if (syncedEmpIds.length > 0) {
      await markSyncedEmployeeFaceThumbs(syncedEmpIds);
      console.log(`[Auto-Sync] Successfully synced ${syncedEmpIds.length} employee face photos.`);
    }
    return { count: syncedEmpIds.length };
  } catch (err) {
    console.error('[Auto-Sync Face Thumbs Exception]:', err);
    return { count: 0 };
  }
}

export async function syncPullEmployeeFacePhotos(targetEmployeeIds = null) {
  const isOnline = await checkOnline();
  if (!isOnline) return { count: 0 };

  try {
    const { saveEmployeeFaceThumbsBatch, getEmployeeFaceThumbInfo, db } = await import('./db');
    let idsToPull = targetEmployeeIds;

    // If targetEmployeeIds not passed, fetch all employee IDs from local cache
    if (!idsToPull || idsToPull.length === 0) {
      const allCached = await db.employees_cache.toArray();
      idsToPull = (allCached || [])
        .map(e => e.id)
        .filter(id => id && !String(id).startsWith('off_') && !String(id).startsWith('tmp_'));
    }

    if (!idsToPull || idsToPull.length === 0) return { count: 0 };

    // Batch by 25 records to keep payload lightweight (<= 600KB)
    const BATCH_SIZE = 25;
    let totalDownloaded = 0;

    for (let i = 0; i < idsToPull.length; i += BATCH_SIZE) {
      const batchIds = idsToPull.slice(i, i + BATCH_SIZE);
      const numIds = batchIds.map(Number).filter(n => !isNaN(n));
      if (numIds.length === 0) continue;

      try {
        const { data: remotePhotos, error: fetchErr } = await supabase
          .from('employee_face_photos')
          .select('employee_id, thumbnail_base64, photo_version, photo_checksum, updated_at, captured_at')
          .in('employee_id', numIds)
          .not('thumbnail_base64', 'is', null);

        if (fetchErr) {
          console.warn('[Sync Pull Photos Batch Error]:', fetchErr.message);
          continue;
        }

        if (remotePhotos && remotePhotos.length > 0) {
          const thumbsToSave = [];
          for (const rp of remotePhotos) {
            if (!rp.thumbnail_base64) continue;
            // Check if local cache already has this exact or newer version
            const localInfo = await getEmployeeFaceThumbInfo(rp.employee_id);
            if (localInfo) {
              // PENGAMAN DATA LOKAL (WAJIB):
              // Unduhan foto dari server tidak boleh menimpa thumbnail lokal yang berstatus belum terunggah (pending).
              // Item lokal yang pending selalu menang sampai terunggah.
              if (localInfo.is_synced === false || localInfo.is_synced === 0) {
                continue;
              }

              const localVer = localInfo.photo_version || 1;
              const remoteVer = rp.photo_version || 1;
              // Bandingkan photo_version / photo_checksum memakai ketidaksamaan (bukan jam perangkat)
              if (localVer >= remoteVer && localInfo.photo_checksum && localInfo.photo_checksum === rp.photo_checksum) {
                continue; // Up-to-date
              }
            }

            thumbsToSave.push({
              employee_id: String(rp.employee_id),
              thumb_base64: rp.thumbnail_base64,
              photo_version: rp.photo_version || 1,
              photo_checksum: rp.photo_checksum || null,
              captured_at: rp.captured_at || rp.updated_at || new Date().toISOString(),
              updated_at: rp.updated_at || new Date().toISOString(),
              is_synced: 1
            });
          }

          if (thumbsToSave.length > 0) {
            await saveEmployeeFaceThumbsBatch(thumbsToSave);
            totalDownloaded += thumbsToSave.length;
          }
        }
      } catch (batchErr) {
        console.warn('[Sync Pull Photos Batch Exception]:', batchErr);
      }
    }

    if (totalDownloaded > 0) {
      console.log(`[Sync Pull Photos] Sukses mengunduh ${totalDownloaded} master thumbnail foto untuk cache offline.`);
    }
    return { count: totalDownloaded };
  } catch (err) {
    console.error('[Sync Pull Face Photos Exception]:', err);
    return { count: 0 };
  }
}

export async function triggerAutoSync(showToast, onSyncComplete) {
  // Tier 1: Upload offline employee deletions FIRST to free up NIKs
  await syncPendingEmployeeDeletes();
  // Tier 2: Upload offline employees & update FKs
  await syncPendingEmployees(showToast, onSyncComplete);
  // Tier 3: Upload offline face photos & thumbnails
  await syncPendingEmployeeFaceThumbs();
  // Tier 4: Upload offline attendance logs
  await syncPendingAttendanceLogs(showToast, onSyncComplete);
  // Tier 5: Upload offline admin requests
  await syncPendingAttendanceRequests();
  // Tier 6: Upload diagnostic scan attempts telemetry
  await syncPendingScanAttempts();
  // Tier 7: Download updated master face thumbnails for offline caching
  await syncPullEmployeeFacePhotos().catch(() => {});

  if (typeof window !== 'undefined') {
    window.dispatchEvent(new CustomEvent('refresh_logs'));
  }
}

/**
 * Setup Realtime Online Network Listener for Sequential 3-Tier Auto-Sync
 */
export function initAutoSyncListener(showToast, onSyncComplete) {
  // DINONAKTIFKAN: Berdasarkan instruksi "Offline-First", dilarang keras menggunakan Network Listener
  // atau interval berkala untuk sinkronisasi otomatis saat online.
  // Sinkronisasi hanya akan dipanggil secara MANUAL via dashboard (Cut-Off) atau OS Background Task.
  console.log('[Network Status] Auto-Sync Listener telah dinonaktifkan (Mode Offline-First aktif).');
  
  return () => {
    // No-op cleanup
  };
}
