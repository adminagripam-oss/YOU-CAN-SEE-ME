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
      return {
        employee_id: log.employee_id,
        timestamp: log.timestamp,
        location: log.location,
        status: log.status,
        euclidean_distance: log.euclidean_distance,
        latitude: log.latitude !== undefined ? log.latitude : (log.lat !== undefined ? log.lat : null),
        longitude: log.longitude !== undefined ? log.longitude : (log.lng !== undefined ? log.lng : null),
        durasi: log.durasi || null,
        attendance_type: log.attendance_type || 'CHECK_IN',
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
 * Setup Realtime Online Network Listener for Auto-Sync
 */
export function initAutoSyncListener(showToast, onSyncComplete) {
  const handleOnline = () => {
    console.log('[Network Status] Device is ONLINE. Triggering Auto-Sync...');
    syncPendingAttendanceLogs(showToast, onSyncComplete);
    syncPendingAttendanceRequests();
  };

  let networkListener = null;

  if (Capacitor.isNativePlatform()) {
    Network.addListener('networkStatusChange', (status) => {
      if (status.connected) {
        console.log('[Network Status] Device is ONLINE (Native). Triggering Auto-Sync...');
        syncPendingAttendanceLogs(showToast, onSyncComplete);
        syncPendingAttendanceRequests();
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
      syncPendingAttendanceLogs(showToast, onSyncComplete);
      syncPendingAttendanceRequests();
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
