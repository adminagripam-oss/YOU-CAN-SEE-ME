import { supabase } from './supabaseClient';
import { getUnsyncedLogs, removeSyncedLogs, db, writeToBackupStorage, updateSyncStatus } from './db';
import { Capacitor } from '@capacitor/core';
import { Network } from '@capacitor/network';
import { Filesystem, Directory } from '@capacitor/filesystem';

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
  if (isSyncing || !isOnline) return { count: 0 };

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
                 console.warn(`[Auto-Sync] Karyawan dgn NIK ${log.nik} tidak ada di server. Log dihapus agar tidak nyangkut.`);
                 await removeSyncedLogs([log.id]);
                 log._discard = true;
              }
            } else {
               console.warn(`[Auto-Sync] Log offline tanpa NIK tidak bisa di-recovery. Menghapus log.`);
               await removeSyncedLogs([log.id]);
               log._discard = true;
            }
          }
        }
      }
    } catch (recoveryErr) {
      console.warn('[Auto-Sync] Auto-recovery gagal:', recoveryErr);
    }

    // FILTER: Tahan log yang masih menggunakan ID temporary (masih ada di antrean karyawan)
    pendingLogs = pendingLogs.filter(log => {
      if (log._discard) return false;
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
          department: log.department || null
        };
      });

    let syncedIds = [];
    let successfulData = [];

    const { data, error } = await supabase
      .from('attendance_logs')
      .insert(logsToInsert)
      .select();

    if (error) {
      console.warn('[Auto-Sync] Bulk insert failed (likely due to FK violation/409 Conflict). Falling back to individual inserts:', error.message || error);
      // Fallback to one-by-one insert so valid logs can still sync
      // Fallback to one-by-one insert concurrently so valid logs can still sync faster
      const fallbackPromises = logsToInsert.map(async (singleLog, i) => {
        const { data: singleData, error: singleError } = await supabase
          .from('attendance_logs')
          .insert([singleLog])
          .select();
        
        if (singleError) {
          console.error(`[Auto-Sync] Failed to sync log for employee_id ${singleLog.employee_id}:`, singleError.message || singleError);
          // If the employee doesn't exist on the server (Foreign Key Violation 23503), 
          // we must discard this log from the queue otherwise it will block sync forever.
          if (singleError.code === '23503') {
            console.warn(`[Auto-Sync] Discarding invalid log for non-existent employee_id: ${singleLog.employee_id}`);
            return { id: textPendingLogs[i].id, discard: true };
          }
          if (singleError.code === '23505' || singleError.message?.includes('duplicate key')) {
            console.warn(`[Auto-Sync] Log already exists on server, discarding local queue item to prevent getting stuck.`);
            return { id: textPendingLogs[i].id, discard: true };
          }
          return null;
        } else if (singleData && singleData.length > 0) {
          return { id: textPendingLogs[i].id, data: singleData[0] };
        }
        return null;
      });

      const fallbackResults = await Promise.all(fallbackPromises);
      fallbackResults.forEach(res => {
        if (res) {
          syncedIds.push(res.id);
          if (res.data) successfulData.push(res.data);
        }
      });
    } else {
      syncedIds = textPendingLogs.map(log => log.id);
      successfulData = data || [];
    }

    syncedTextIds = syncedIds;

    if (syncedTextIds.length > 0) {
      console.log(`[Auto-Sync Step A] Successfully synced ${syncedTextIds.length} text records!`);
      // Update status text to 'done' in local DB
      for (const id of syncedTextIds) {
        await updateSyncStatus(id, 'done', null);
        const logToPhoto = textPendingLogs.find(l => l.id === id);
        if (logToPhoto && logToPhoto.path_foto_lokal) {
          photoPendingLogs.push(logToPhoto); // Push to photo queue for Step B
        }
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
            // Read base64 from Capacitor Filesystem (native platform)
            const readResult = await Filesystem.readFile({
              path: log.path_foto_lokal
            });
            base64String = readResult.data;
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

          // Update text log in server with photo url (we must find the server ID or match by timestamp/employee_id)
          // Since we might not have the server ID, we update by timestamp and employee_id
          await supabase
            .from('attendance_logs')
            .update({ path_foto: publicUrl })
            .eq('employee_id', log.employee_id)
            .eq('timestamp', log.timestamp);

          // Step C: Garbage Collection (Delete local file and set status_sync_foto = 'done')
          await updateSyncStatus(log.id, 'done', 'done');
          
          try {
            await Filesystem.deleteFile({
              path: log.path_foto_lokal
            });
            console.log(`[Auto-Sync Step C] Garbage collected local photo for log ${log.id}`);
          } catch (delErr) {
            console.warn(`[Auto-Sync Step C] Failed to delete local photo:`, delErr);
          }

          syncedPhotoCount++;

        } catch (photoErr) {
          console.warn(`[Auto-Sync Step B Error] Processing photo for log ${log.id}:`, photoErr);
        }
      }
    }

    // Clean up local queue ONLY if is_synced is 1 (done by getUnsyncedLogs but let's clear them)
    // Actually we don't need to manually delete from attendance_sync_queue because
    // next time getUnsyncedLogs is called, it will filter out is_synced === true
    // Wait, let's remove from sync_queue fully synced items
    const fullySyncedLogs = await getUnsyncedLogs(); // wait, they are already synced so they won't be here
    // Let's manually remove those we just synced fully
    const fullySyncedIds = [...syncedTextIds, ...photoPendingLogs.map(p => p.id)];
    
    // Write sync action to public backup log
    if (Capacitor.isNativePlatform() && (syncedTextIds.length > 0 || syncedPhotoCount > 0)) {
      const timestamp = new Date().toISOString();
      const syncLine = `[${timestamp}] [SYNC SUCCESS] Uploaded ${syncedTextIds.length} texts and ${syncedPhotoCount} photos to cloud.\n`;
      await writeToBackupStorage(syncLine);
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

    // ── Pisahkan DELETE requests dari approval requests biasa ──
    const deleteLogReqs = unsyncedReqs.filter(r => r.request_type === 'DELETE');
    const approvalReqs = unsyncedReqs.filter(r => r.request_type !== 'DELETE');

    // 1. Proses semua permintaan penghapusan log ke Supabase
    if (deleteLogReqs.length > 0) {
      const deletePromises = deleteLogReqs.map(async (r) => {
        try {
          const logId = r.log_id;
          if (!logId) return;
          const { error: delErr } = await supabase
            .from('attendance_logs')
            .delete()
            .eq('id', logId);
          if (delErr) {
            console.error(`[Sync Engine] Failed to delete log #${logId} from Supabase:`, delErr.message);
          } else {
            console.log(`[Sync Engine] Log #${logId} berhasil dihapus dari Supabase.`);
            await db.attendance_requests.put({ ...r, is_synced: true });
          }
        } catch (e) {
          console.error('[Sync Engine DELETE_LOG Error]:', e);
        }
      });
      await Promise.all(deletePromises);
    }

    // 2. Proses approval requests biasa (INSERT ke attendance_requests)
    if (approvalReqs.length > 0) {
      const reqsToInsert = approvalReqs.map(r => ({
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
      }));

      const { error } = await supabase
        .from('attendance_requests')
        .insert(reqsToInsert);

      if (error) {
        if (error.message.includes('relation "public.attendance_requests" does not exist')) {
          console.warn('[Sync Engine] attendance_requests table does not exist in Supabase yet.');
        } else {
          throw error;
        }
      } else {
        // Mark as synced locally
        await Promise.all(approvalReqs.map(r => 
          db.attendance_requests.put({ ...r, is_synced: true })
        ));
      }
    }

    console.log(`[Auto-Sync Requests Success] Processed ${unsyncedReqs.length} admin requests!`);
    return { count: unsyncedReqs.length };
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

    // --- LOGIKA CUT-OFF 22:00 ---
    const waktuCutOff = new Date();
    waktuCutOff.setHours(22, 0, 0, 0);
    const batasCutOffTime = waktuCutOff.getTime();

    const pendingEmps = pendingEmpsRaw.filter(emp => {
      // employee queue table di db.js (employee_sync_queue) dan local_employee_sync_queue (SQLite)
      // memiliki atribut created_at
      if (!emp.created_at) return true;
      return new Date(emp.created_at).getTime() <= batasCutOffTime;
    });

    if (pendingEmpsRaw.length > 0 && pendingEmps.length === 0) {
      console.log(`[Auto-Sync Employees] ${pendingEmpsRaw.length} employee pending berada di luar cut-off (setelah 22:00). Ditunda hingga besok.`);
      return { count: 0 };
    }
    // ----------------------------

    const { cacheUserMasterVector, db } = await import('./db');

    console.log(`[Auto-Sync Employees] Attempting to sync ${pendingEmps.length} offline registered employees...`);
    
    // FETCH PENDING LOGS ONCE OUTSIDE THE LOOP! (Massive performance boost)
    const allPendingLogs = await getUnsyncedLogs();

    const employeePromises = pendingEmps.map(async (emp) => {
      try {
        let realEmpId = null;

        // 1. Insert employee record to Supabase
        const { data: createdEmp, error: empErr } = await supabase
          .from('employees')
          .insert([{
            nik: emp.nik,
            name: emp.name,
            department: emp.department || emp.jabatan,
            afdeling: emp.afdeling,
            nama_kebun: emp.nama_kebun,
            status_tk: emp.status_tk,
            jabatan: emp.jabatan,
            status_perkawinan: emp.status_perkawinan,
            has_master_biometric: !!emp.descriptor_json || emp.has_master_biometric === true
          }])
          .select()
          .single();

        if (empErr) {
          if (empErr.code === '23505' || empErr.message?.includes('duplicate key') || empErr.message?.includes('already exists')) {
            console.warn(`[Sync Employee] NIK ${emp.nik} sudah ada di Supabase. Mengambil ID karyawan yang ada...`);
            const { data: existingEmp } = await supabase
              .from('employees')
              .select('id')
              .eq('nik', emp.nik)
              .single();
            if (existingEmp) {
              realEmpId = existingEmp.id;
            } else {
              console.warn(`[Sync Employee Fail] Gagal menemukan ID untuk NIK duplikat ${emp.nik}`);
              return false;
            }
          } else {
            console.warn(`[Sync Employee Fail] Gagal sync karyawan ${emp.name}:`, empErr.message);
            return false;
          }
        } else {
          realEmpId = createdEmp.id;
        }

        // 2. Insert master biometrics descriptor if present
        let descObj = emp.descriptor_json;
        if (typeof descObj === 'string') {
          try { descObj = JSON.parse(descObj); } catch (_) {}
        }

        if (descObj && Array.isArray(descObj)) {
          const descStr = JSON.stringify(descObj);
          await supabase
            .from('master_descriptors')
            .upsert({
              employee_id: realEmpId,
              descriptor_json: descStr
            }, { onConflict: 'employee_id' });

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
            descriptor_json: descObj
          });
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
            }
          });
          await Promise.all(updateLogPromises);
        } catch (fkErr) {
          console.warn('[Sync Engine FK Mapping Error]:', fkErr);
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
          const { deleteLocalEmployee } = await import('./db');
          await deleteLocalEmployee(emp.id);
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
              has_master_biometric: !!emp.descriptor_json || emp.has_master_biometric === true,
              is_synced: true
            };
            await db.employees_cache.put(realEmpEntry);
            console.log(`[Sync Employee] employees_cache diperbarui: ${tempIdStr} → ${realEmpId} untuk ${emp.name}`);
          } catch (cacheErr) {
            console.warn(`[Sync Employee] Gagal update employees_cache untuk ${emp.name}:`, cacheErr);
          }

          // JUGA: Update attendance_logs lokal (display cache) dari ID offline ke ID resmi
          try {
            const offlineLogs = await db.attendance_logs.filter(
              l => String(l.employee_id) === tempIdStr
            ).toArray();
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
        return false;
      }
    });

    const syncResults = await Promise.all(employeePromises);
    const syncedCount = syncResults.filter(Boolean).length;

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

export async function triggerAutoSync(showToast, onSyncComplete) {
  // Tier 1: Upload offline employees first & update FKs
  await syncPendingEmployees(showToast, onSyncComplete);
  // Tier 2: Upload offline attendance logs
  await syncPendingAttendanceLogs(showToast, onSyncComplete);
  // Tier 3: Upload offline admin requests
  await syncPendingAttendanceRequests();
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
