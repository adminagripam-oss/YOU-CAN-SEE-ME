import Dexie from 'dexie';

/**
 * Dexie.js IndexedDB Database for Offline-First Face Attendance App
 * v2: adds geometric_descriptor_json (40-d GFV) alongside 128-d face embedding
 */
export const db = new Dexie('FaceAttendanceOfflineDB');

// v1: original schema (kept for upgrade chain)
db.version(1).stores({
  user_master: '++id, employee_id, nik, name, department, updated_at',
  attendance_sync_queue: '++id, employee_id, nik, name, timestamp, status, attendance_type, is_synced, created_at',
  employees_cache: 'id, nik, name, department, has_master_biometric'
});

// v2: geometric_descriptor_json column added to user_master
db.version(2).stores({
  user_master: '++id, employee_id, nik, name, department, updated_at',
  attendance_sync_queue: '++id, employee_id, nik, name, timestamp, status, attendance_type, is_synced, created_at',
  employees_cache: 'id, nik, name, department, has_master_biometric'
}).upgrade(tx => {
  return tx.table('user_master').toCollection().modify(row => {
    if (row.geometric_descriptor_json === undefined) {
      row.geometric_descriptor_json = null;
    }
  });
});

/**
 * Save / Cache Master Descriptor for a user in IndexedDB.
 * Accepts both 128-d embedding (descriptor_json) and 40-d GFV (geometric_descriptor_json).
 */
export async function cacheUserMasterVector(user) {
  try {
    const empId = user.employee_id || user.id;
    if (!empId) return;

    const allMasters = await db.user_master.toArray();
    const existing = allMasters.find((m) => String(m.employee_id) === String(empId));

    const payload = {
      nik: user.nik,
      name: user.name,
      department: user.department || user.jabatan,
      afdeling: user.afdeling || null,
      nama_kebun: user.nama_kebun || null,
      status_tk: user.status_tk || null,
      jabatan: user.jabatan || null,
      status_perkawinan: user.status_perkawinan || null,
      descriptor_json: user.descriptor_json || user.descriptor || user.face_vector || null,
      face_vector: user.face_vector || user.descriptor_json || user.descriptor || null,
      geometric_descriptor_json: user.geometric_descriptor_json || null,
      updated_at: new Date().toISOString(),
    };

    if (existing) {
      await db.user_master.update(existing.id, payload);
    } else {
      await db.user_master.add({ employee_id: empId, ...payload });
    }
    console.log(`[IndexedDB] Cached master for ${user.name} | GFV: ${payload.geometric_descriptor_json ? 'YES' : 'NO'}`);
  } catch (err) {
    console.error('[IndexedDB Cache Master Error]:', err);
  }
}

/**
 * Update ONLY the geometric feature vector for an existing cached employee.
 */
export async function cacheGeometricVector(employeeId, gfv) {
  try {
    if (!employeeId || !gfv) return;
    const allMasters = await db.user_master.toArray();
    const existing = allMasters.find((m) => String(m.employee_id) === String(employeeId));
    if (existing) {
      await db.user_master.update(existing.id, {
        geometric_descriptor_json: gfv,
        updated_at: new Date().toISOString(),
      });
    }
  } catch (err) {
    console.error('[IndexedDB cacheGeometricVector Error]:', err);
  }
}

/**
 * Get Cached Master Descriptor from IndexedDB.
 * Returns: { descriptor_json, geometric_descriptor_json, ... }
 */
export async function getCachedUserMasterVector(employeeId) {
  try {
    if (!employeeId) return null;
    const allMasters = await db.user_master.toArray();
    return allMasters.find((m) => String(m.employee_id) === String(employeeId)) || null;
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
    const allQueue = await db.attendance_sync_queue.toArray();
    return allQueue.filter((item) => !item.is_synced || item.is_synced === 0);
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
    if (!ids || ids.length === 0) return;
    await db.attendance_sync_queue.bulkDelete(ids);
  } catch (err) {
    console.error('[IndexedDB Delete Synced Error]:', err);
  }
}
