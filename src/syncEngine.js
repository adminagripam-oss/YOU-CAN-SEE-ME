import { supabase } from './supabaseClient';
import { getUnsyncedLogs, removeSyncedLogs, db } from './db';

let isSyncing = false;

/**
 * Executes Auto-Sync of offline attendance logs stored in IndexedDB to Server
 */
export async function syncPendingAttendanceLogs(showToast = null, onSyncComplete = null) {
  if (isSyncing || !navigator.onLine) return { count: 0 };

  try {
    const pendingLogs = await getUnsyncedLogs();
    if (!pendingLogs || pendingLogs.length === 0) {
      return { count: 0 };
    }

    isSyncing = true;
    console.log(`[Auto-Sync] Attempting to sync ${pendingLogs.length} pending offline attendance logs to server...`);

    const logsToInsert = pendingLogs.map(log => {
      const { id, is_synced, created_at, ...rest } = log;
      return rest;
    });

    const { error } = await supabase
      .from('attendance_logs')
      .insert(logsToInsert);

    if (error) throw error;

    const syncedIds = pendingLogs.map(log => log.id);

    // Remove synced records from IndexedDB
    await removeSyncedLogs(syncedIds);
    console.log(`[Auto-Sync Success] Successfully synced ${syncedIds.length} records!`);

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

  window.addEventListener('online', handleOnline);

  // Periodic fallback check every 20 seconds if online and items exist
  const intervalId = setInterval(() => {
    if (navigator.onLine) {
      syncPendingAttendanceLogs(showToast, onSyncComplete);
    }
  }, 20000);

  return () => {
    window.removeEventListener('online', handleOnline);
    clearInterval(intervalId);
  };
}
