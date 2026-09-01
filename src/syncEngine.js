import { supabase } from './supabaseClient';
import { getUnsyncedLogs, removeSyncedLogs, db, writeToBackupStorage } from './db';
import { Capacitor } from '@capacitor/core';
import { Network } from '@capacitor/network';

let isSyncing = false;

/**
 * Checks if the device is currently online (handles native and web fallback)
 */
async function checkOnline() {
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
    const pendingLogs = await getUnsyncedLogs();
    if (!pendingLogs || pendingLogs.length === 0) {
      return { count: 0 };
    }

    isSyncing = true;
    console.log(`[Auto-Sync] Attempting to sync ${pendingLogs.length} pending offline attendance logs to server...`);

    const logsToInsert = pendingLogs.map(log => {
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

    const { data, error } = await supabase
      .from('attendance_logs')
      .insert(logsToInsert)
      .select();

    if (error) throw error;

    const syncedIds = pendingLogs.map(log => log.id);

    // Remove synced records from local DB queue
    await removeSyncedLogs(syncedIds);

    // Update local attendance logs table: remove offline entries and put synced ones
    try {
      for (let i = 0; i < pendingLogs.length; i++) {
        const oldId = pendingLogs[i].id;
        // Delete the temporary offline log from the logs table using correct prefix
        await db.attendance_logs.delete('offline_' + oldId);
      }
      
      if (data && data.length > 0) {
        const localRecords = data.map(record => ({
          id: String(record.id),
          employee_id: record.employee_id,
          nik: record.nik,
          name: record.name,
          department: record.department,
          afdeling: record.afdeling || null,
          kebun: record.kebun || null,
          timestamp: record.timestamp,
          location: record.location,
          lat: record.latitude !== undefined && record.latitude !== null ? record.latitude : (record.lat !== undefined ? record.lat : null),
          lng: record.longitude !== undefined && record.longitude !== null ? record.longitude : (record.lng !== undefined ? record.lng : null),
          status: record.status,
          attendance_type: record.attendance_type,
          euclidean_distance: record.euclidean_distance,
          is_synced: true,
          created_at: record.created_at
        }));
        await db.attendance_logs.bulkPut(localRecords);
      }
    } catch (dbErr) {
      console.warn('[Sync Engine] Failed to update local attendance_logs table:', dbErr);
    }
    console.log(`[Auto-Sync Success] Successfully synced ${syncedIds.length} records!`);

    // Write sync action to public backup log
    if (Capacitor.isNativePlatform()) {
      const timestamp = new Date().toISOString();
      const syncLine = `[${timestamp}] [SYNC SUCCESS] Successfully uploaded ${syncedIds.length} offline attendance logs to cloud database.\n`;
      await writeToBackupStorage(syncLine);
    }

    if (showToast) {
      showToast(
        'Auto-Sync Berhasil',
        `Berhasil mengunggah ${syncedIds.length} data absensi offline!`,
        'success'
      );
    }

    if (onSyncComplete) {
      onSyncComplete();
    }

    return { count: syncedIds.length };
  } catch (err) {
    console.error('[Auto-Sync Error]:', err);
  } finally {
    isSyncing = false;
  }

  return { count: 0 };
}

/**
 * Executes Auto-Sync of offline admin approval requests to Supabase
 */
export async function syncPendingAttendanceRequests() {
  const isOnline = await checkOnline();
  if (!isOnline) return { count: 0 };

  try {
    const { db } = await import('./db');
    const allReqs = await db.attendance_requests.toArray();
    const unsyncedReqs = allReqs.filter(r => !r.is_synced);

    if (unsyncedReqs.length === 0) return { count: 0 };

    console.log(`[Auto-Sync Requests] Attempting to sync ${unsyncedReqs.length} pending offline admin requests...`);

    const reqsToInsert = unsyncedReqs.map(r => ({
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
        return { count: 0 };
      }
      throw error;
    }

    // Mark as synced locally
    for (const r of unsyncedReqs) {
      await db.attendance_requests.put({ ...r, is_synced: true });
    }

    console.log(`[Auto-Sync Requests Success] Successfully synced ${unsyncedReqs.length} admin requests!`);
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
    const { db, cacheUserMasterVector } = await import('./db');
    const pendingEmps = await db.employee_sync_queue.toArray();
    if (!pendingEmps || pendingEmps.length === 0) return { count: 0 };

    console.log(`[Auto-Sync Employees] Attempting to sync ${pendingEmps.length} offline registered employees...`);
    let syncedCount = 0;

    for (const emp of pendingEmps) {
      try {
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
            status_perkawinan: emp.status_perkawinan
          }])
          .select()
          .single();

        if (empErr) {
          console.warn(`[Sync Employee Fail] Gagal sync karyawan ${emp.name}:`, empErr.message);
          continue;
        }

        const realEmpId = createdEmp.id;

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
            nik: createdEmp.nik,
            name: createdEmp.name,
            department: createdEmp.department || createdEmp.jabatan,
            afdeling: createdEmp.afdeling,
            nama_kebun: createdEmp.nama_kebun,
            status_tk: createdEmp.status_tk,
            jabatan: createdEmp.jabatan,
            status_perkawinan: createdEmp.status_perkawinan,
            descriptor_json: descObj
          });
        }

        // 3. Update any pending offline attendance logs that used this temporary employee ID
        try {
          const allPendingLogs = await getUnsyncedLogs();
          for (const pendingLog of allPendingLogs) {
            if (String(pendingLog.employee_id) === String(emp.id)) {
              console.log(`[Sync Engine FK Update] Updating pending log #${pendingLog.id} employee_id from ${emp.id} to ${realEmpId}`);
              pendingLog.employee_id = realEmpId;
              if (Capacitor.isNativePlatform()) {
                const { dbConnection } = await import('./services/sqliteService');
                if (dbConnection) {
                  await dbConnection.run(
                    `UPDATE local_attendance_queue SET employee_id = ? WHERE id = ?`,
                    [String(realEmpId), pendingLog.id]
                  );
                }
              } else {
                await db.attendance_sync_queue.put(pendingLog);
              }
            }
          }
        } catch (fkErr) {
          console.warn('[Sync Engine FK Mapping Error]:', fkErr);
        }

        // 4. Remove temp ID from local employee sync queue
        await db.employee_sync_queue.delete(emp.id);

        syncedCount++;
        console.log(`[Sync Employee Success] Karyawan ${emp.name} synced dengan ID real ${realEmpId}`);
      } catch (singleErr) {
        console.error(`[Sync Single Employee Exception]`, singleErr);
      }
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

/**
 * Setup Realtime Online Network Listener for Sequential 3-Tier Auto-Sync
 */
export function initAutoSyncListener(showToast, onSyncComplete) {
  const handleOnline = async () => {
    console.log('[Network Status] Device is ONLINE. Triggering Sequential 3-Tier Auto-Sync...');
    // Tier 1: Upload offline employees first & update FKs
    await syncPendingEmployees(showToast, onSyncComplete);
    // Tier 2: Upload offline attendance logs
    await syncPendingAttendanceLogs(showToast, onSyncComplete);
    // Tier 3: Upload offline admin requests
    await syncPendingAttendanceRequests();
  };

  let networkListener = null;

  if (Capacitor.isNativePlatform()) {
    Network.addListener('networkStatusChange', async (status) => {
      if (status.connected) {
        console.log('[Network Status] Device is ONLINE (Native). Triggering Sequential 3-Tier Auto-Sync...');
        await syncPendingEmployees(showToast, onSyncComplete);
        await syncPendingAttendanceLogs(showToast, onSyncComplete);
        await syncPendingAttendanceRequests();
      }
    }).then(handle => {
      networkListener = handle;
    });
  } else {
    window.addEventListener('online', handleOnline);
  }

  // Periodic fallback check every 20 seconds if online and items exist
  const intervalId = setInterval(async () => {
    const isOnline = await checkOnline();
    if (isOnline) {
      await syncPendingEmployees(showToast, onSyncComplete);
      await syncPendingAttendanceLogs(showToast, onSyncComplete);
      await syncPendingAttendanceRequests();
    }
  }, 20000);

  return () => {
    if (Capacitor.isNativePlatform()) {
      if (networkListener) {
        networkListener.remove();
      }
    } else {
      window.removeEventListener('online', handleOnline);
    }
    clearInterval(intervalId);
  };
}
