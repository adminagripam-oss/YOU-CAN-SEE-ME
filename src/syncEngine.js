import { API_BASE_URL } from './config';
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

    const response = await fetch(`${API_BASE_URL}/api/attendance/sync`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ items: pendingLogs }),
    });

    const data = await response.json();

    if (data.success && data.synced_ids && data.synced_ids.length > 0) {
      // Remove synced records from IndexedDB
      await removeSyncedLogs(data.synced_ids);
      console.log(`[Auto-Sync Success] Successfully synced ${data.synced_ids.length} records!`);

      if (showToast) {
        showToast(
          'Auto-Sync Berhasil',
          `${data.synced_ids.length} log absensi offline berhasil terkirim ke server!`,
          'success'
        );
      }

      if (onSyncComplete) {
        onSyncComplete();
      }

      return { count: data.synced_ids.length };
    }
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
