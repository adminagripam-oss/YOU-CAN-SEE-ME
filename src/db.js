import Dexie from 'dexie';
import { Capacitor } from '@capacitor/core';
import {
  sqliteCacheUserMasterVector,
  sqliteCacheGeometricVector,
  sqliteGetCachedUserMasterVector,
  sqliteQueueOfflineAttendance,
  sqliteGetUnsyncedLogs,
  sqliteRemoveSyncedLogs,
  sqliteClearEmployeesCache,
  sqliteBulkPutEmployeesCache,
  sqliteGetEmployeesCache,
  sqliteGetAllMasterVectors
} from './services/sqliteService';

/**
 * Dexie.js IndexedDB Database for Offline-First Face Attendance App
 * Used as dynamic fallback when running on Web
 */
const dexieDb = new Dexie('FaceAttendanceOfflineDB');

dexieDb.version(1).stores({
  user_master: '++id, employee_id, nik, name, department, updated_at',
  attendance_sync_queue: '++id, employee_id, nik, name, timestamp, status, attendance_type, is_synced, created_at',
  employees_cache: 'id, nik, name, department, has_master_biometric'
});

dexieDb.version(2).stores({
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
 * Unified Database Wrapper Object (mimics Dexie.js structure for direct table access)
 */
export const db = {
  employees_cache: {
    async toArray() {
      if (Capacitor.isNativePlatform()) {
        return await sqliteGetEmployeesCache();
      } else {
        return await dexieDb.employees_cache.toArray();
      }
    },
    async clear() {
      if (Capacitor.isNativePlatform()) {
        await sqliteClearEmployeesCache();
      } else {
        await dexieDb.employees_cache.clear();
      }
    },
    async bulkPut(data) {
      if (Capacitor.isNativePlatform()) {
        await sqliteBulkPutEmployeesCache(data);
      } else {
        await dexieDb.employees_cache.bulkPut(data);
      }
    }
  },
  attendance_sync_queue: {
    async toArray() {
      if (Capacitor.isNativePlatform()) {
        return await sqliteGetUnsyncedLogs();
      } else {
        return await dexieDb.attendance_sync_queue.toArray();
      }
    }
  }
};

/**
 * Save / Cache Master Descriptor.
 * Automatically delegates to SQLite on native APK, or Dexie on Web.
 */
export async function cacheUserMasterVector(user) {
  if (Capacitor.isNativePlatform()) {
    await sqliteCacheUserMasterVector(user);
    return;
  }

  try {
    const empId = user.employee_id || user.id;
    if (!empId) return;

    let vector = user.descriptor_json || user.descriptor || user.face_vector || null;
    if (vector) {
      let parsed = vector;
      while (typeof parsed === 'string') {
        try {
          parsed = JSON.parse(parsed);
        } catch (e) {
          break;
        }
      }
      if (Array.isArray(parsed)) {
        if (parsed.length !== 1024) {
          console.warn(`[db.js] cacheUserMasterVector: Mencegah caching vector dengan panjang ${parsed.length} (harus 1024) untuk employee ${empId}`);
          vector = null;
        }
      } else {
        vector = null;
      }
    }

    const allMasters = await dexieDb.user_master.toArray();
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
      descriptor_json: vector,
      face_vector: vector,
      geometric_descriptor_json: user.geometric_descriptor_json || null,
      updated_at: new Date().toISOString(),
    };

    if (existing) {
      await dexieDb.user_master.update(existing.id, payload);
    } else {
      await dexieDb.user_master.add({ employee_id: empId, ...payload });
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
  if (Capacitor.isNativePlatform()) {
    await sqliteCacheGeometricVector(employeeId, gfv);
    return;
  }

  try {
    if (!employeeId || !gfv) return;
    const allMasters = await dexieDb.user_master.toArray();
    const existing = allMasters.find((m) => String(m.employee_id) === String(employeeId));
    if (existing) {
      await dexieDb.user_master.update(existing.id, {
        geometric_descriptor_json: gfv,
        updated_at: new Date().toISOString(),
      });
    }
  } catch (err) {
    console.error('[IndexedDB cacheGeometricVector Error]:', err);
  }
}

/**
 * Get Cached Master Descriptor.
 */
export async function getCachedUserMasterVector(employeeId) {
  if (Capacitor.isNativePlatform()) {
    return await sqliteGetCachedUserMasterVector(employeeId);
  }

  try {
    if (!employeeId) return null;
    const allMasters = await dexieDb.user_master.toArray();
    return allMasters.find((m) => String(m.employee_id) === String(employeeId)) || null;
  } catch (err) {
    console.error('[IndexedDB Get Master Error]:', err);
    return null;
  }
}

/**
 * Save Offline Attendance Log to Sync Queue.
 */
export async function queueOfflineAttendance(logData) {
  if (Capacitor.isNativePlatform()) {
    return await sqliteQueueOfflineAttendance(logData);
  }

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
    const id = await dexieDb.attendance_sync_queue.add(queueItem);
    console.log(`[IndexedDB Queue] Attendance log queued offline with ID: ${id}`);
    return { ...queueItem, id };
  } catch (err) {
    console.error('[IndexedDB Queue Add Error]:', err);
    throw err;
  }
}

/**
 * Get all unsynced logs.
 */
export async function getUnsyncedLogs() {
  if (Capacitor.isNativePlatform()) {
    return await sqliteGetUnsyncedLogs();
  }

  try {
    const allQueue = await dexieDb.attendance_sync_queue.toArray();
    return allQueue.filter((item) => !item.is_synced || item.is_synced === 0);
  } catch (err) {
    console.error('[IndexedDB Get Unsynced Error]:', err);
    return [];
  }
}

/**
 * Remove synced items from Queue.
 */
export async function removeSyncedLogs(ids) {
  if (Capacitor.isNativePlatform()) {
    await sqliteRemoveSyncedLogs(ids);
    return;
  }

  try {
    if (!ids || ids.length === 0) return;
    await dexieDb.attendance_sync_queue.bulkDelete(ids);
  } catch (err) {
    console.error('[IndexedDB Delete Synced Error]:', err);
  }
}

/**
 * Cosine similarity between two equal-length numeric vectors.
 * Returns value in [-1, 1]; higher = more similar.
 */
export function cosineSimilarity(a, b) {
  if (!Array.isArray(a) || !Array.isArray(b) || a.length !== b.length) return 0;
  let dot = 0, normA = 0, normB = 0;
  for (let i = 0; i < a.length; i++) {
    dot += a[i] * b[i];
    normA += a[i] * a[i];
    normB += b[i] * b[i];
  }
  const denom = Math.sqrt(normA) * Math.sqrt(normB);
  return denom === 0 ? 0 : dot / denom;
}

/**
 * Get ALL cached master vectors from local storage.
 * Native APK → SQLite (local_master_descriptors JOIN local_employees)
 * Web/Dev    → IndexedDB (Dexie user_master table)
 * Used for one-face-per-employee duplicate check (fully offline-capable).
 */
export async function getAllMasterVectors() {
  if (Capacitor.isNativePlatform()) {
    return await sqliteGetAllMasterVectors();
  }
  try {
    const allMasters = await dexieDb.user_master.toArray();
    return allMasters.map(m => ({
      employee_id: m.employee_id,
      nik: m.nik,
      name: m.name,
      descriptor_json: m.descriptor_json || m.face_vector || null,
    }));
  } catch (err) {
    console.error('[IndexedDB getAllMasterVectors Error]:', err);
    return [];
  }
}
