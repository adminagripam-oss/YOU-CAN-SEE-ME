import Dexie from 'dexie';

/**
 * Dexie.js IndexedDB Database for Offline-First Face Attendance App
 */
export const db = new Dexie('FaceAttendanceOfflineDB');

// Define Database Schema
db.version(1).stores({
  // Master Vector Descriptors cached on HP
  user_master: '++id, employee_id, nik, name, department, updated_at',
  
  // Offline Attendance Queue waiting to sync to server
  attendance_sync_queue: '++id, employee_id, nik, name, timestamp, status, attendance_type, is_synced, created_at',
  
  // Local Cached Employees List
  employees_cache: 'id, nik, name, department, has_master_biometric'
});

/**
 * Save / Cache Master Descriptor for a user in IndexedDB
 */
export async function cacheUserMasterVector(user) {
  try {
    const existing = await db.user_master.where('employee_id').equals(user.employee_id || user.id).first();
    if (existing) {
      await db.user_master.update(existing.id, {
        nik: user.nik,
        name: user.name,
        department: user.department,
        descriptor_json: user.descriptor_json || user.descriptor,
        updated_at: new Date().toISOString()
      });
    } else {
      await db.user_master.add({
        employee_id: user.employee_id || user.id,
        nik: user.nik,
        name: user.name,
        department: user.department,
        descriptor_json: user.descriptor_json || user.descriptor,
        updated_at: new Date().toISOString()
      });
    }
    console.log(`[IndexedDB] Cached master vector for ${user.name}`);
  } catch (err) {
    console.error('[IndexedDB Cache Master Error]:', err);
  }
}

/**
 * Get Cached Master Descriptor from IndexedDB
 */
export async function getCachedUserMasterVector(employeeId) {
  try {
    return await db.user_master.where('employee_id').equals(parseInt(employeeId)).first();
  } catch (err) {
    console.error('[IndexedDB Get Master Error]:', err);
    return null;
  }
}

/**
 * Save Offline Attendance Log to Sync Queue
 */
export async function queueOfflineAttendance(logData) {
  try {
    const queueItem = {
      employee_id: logData.employee_id,
      nik: logData.nik,
      name: logData.name,
      department: logData.department,
      timestamp: logData.timestamp || new Date().toISOString(),
      location: logData.location || 'HP Mobile (Offline)',
      lat: logData.lat || null,
      lng: logData.lng || null,
      status: logData.status,
      attendance_type: logData.attendance_type || 'CHECK_IN',
      euclidean_distance: logData.euclidean_distance,
      is_synced: false,
      created_at: new Date().toISOString()
    };
    const id = await db.attendance_sync_queue.add(queueItem);
    console.log(`[IndexedDB Queue] Attendance log queued offline with ID: ${id}`);
    return { ...queueItem, id };
  } catch (err) {
    console.error('[IndexedDB Queue Add Error]:', err);
    throw err;
  }
}

/**
 * Get all unsynced logs from IndexedDB
 */
export async function getUnsyncedLogs() {
  try {
    return await db.attendance_sync_queue.where('is_synced').equals(0).or('is_synced').equals(false).toArray();
  } catch (err) {
    console.error('[IndexedDB Get Unsynced Error]:', err);
    return [];
  }
}

/**
 * Remove synced items from IndexedDB Queue
 */
export async function removeSyncedLogs(ids) {
  try {
    await db.attendance_sync_queue.bulkDelete(ids);
  } catch (err) {
    console.error('[IndexedDB Delete Synced Error]:', err);
  }
}
