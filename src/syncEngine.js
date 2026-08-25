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

    const { error } = await supabase
      .from('attendance_logs')
      .insert(logsToInsert);

    if (error) throw error;

    const syncedIds = pendingLogs.map(log => log.id);

    // Remove synced records from local DB
    await removeSyncedLogs(syncedIds);
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
 * Setup Realtime Online Network Listener for Auto-Sync
 */
export function initAutoSyncListener(showToast, onSyncComplete) {
  const handleOnline = () => {
    console.log('[Network Status] Device is ONLINE. Triggering Auto-Sync...');
    syncPendingAttendanceLogs(showToast, onSyncComplete);
  };

  let networkListener = null;

  if (Capacitor.isNativePlatform()) {
    Network.addListener('networkStatusChange', (status) => {
      if (status.connected) {
        console.log('[Network Status] Device is ONLINE (Native). Triggering Auto-Sync...');
        syncPendingAttendanceLogs(showToast, onSyncComplete);
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
