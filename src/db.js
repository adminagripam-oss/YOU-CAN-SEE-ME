import Dexie from 'dexie';
import { Capacitor } from '@capacitor/core';
import { Filesystem, Directory, Encoding } from '@capacitor/filesystem';
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
  sqliteGetAllMasterVectors,
  sqliteDeleteEmployeeBiometrics,
  sqliteCacheTodayAttendance,
  sqliteGetTodayAttendance,
  sqliteClearTodayAttendanceCache
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

dexieDb.version(3).stores({
  user_master: '++id, employee_id, nik, name, department, updated_at',
  attendance_sync_queue: '++id, employee_id, nik, name, timestamp, status, attendance_type, is_synced, created_at',
  employees_cache: 'id, nik, name, department, has_master_biometric',
  today_attendance_cache: 'employee_id, hasCheckedIn, hasCheckedOut, checked_in, check_in_time, check_out_time, cached_date'
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
    },
    async delete(id) {
      const idStr = String(id);
      // Bersihkan prefix "offline_" jika ada
      const cleanId = idStr.startsWith('offline_') ? idStr.replace('offline_', '') : idStr;
      const cleanIdInt = parseInt(cleanId, 10);

      if (Capacitor.isNativePlatform()) {
        if (!isNaN(cleanIdInt)) {
          await sqliteRemoveSyncedLogs([cleanIdInt]);
          // Write deletion action to public backup log
          const timestamp = new Date().toISOString();
          const deleteLine = `[${timestamp}] [DELETED BY ADMIN] Queue Log ID: ${cleanIdInt} deleted from local offline queue.\n`;
          await writeToBackupStorage(deleteLine);
        }
      } else {
        if (!isNaN(cleanIdInt)) {
          await dexieDb.attendance_sync_queue.delete(cleanIdInt);
        }
      }
    }
  },
  today_attendance_cache: {
    async put(statusMap, cachedDate) {
      if (Capacitor.isNativePlatform()) {
        try {
          await sqliteCacheTodayAttendance(statusMap, cachedDate);
        } catch (e) {
          console.error('[db.js put error]:', e, e?.stack || '');
          throw e;
        }
      } else {
        try {
          // Clear older dates in Dexie
          await dexieDb.today_attendance_cache.where('cached_date').notEqual(cachedDate).delete();
          
          const records = [];
          const keys = Object.keys(statusMap);
          for (let i = 0; i < keys.length; i++) {
            const empId = keys[i];
            const status = statusMap[empId];
            if (!status) continue;
            records.push({
              employee_id: parseInt(empId),
              hasCheckedIn: status.hasCheckedIn,
              hasCheckedOut: status.hasCheckedOut,
              checked_in: status.checked_in,
              check_in_time: status.check_in_time,
              check_out_time: status.check_out_time,
              cached_date: cachedDate
            });
          }

          if (records.length > 0) {
            await dexieDb.today_attendance_cache.bulkPut(records);
          }
        } catch (e) {
          console.warn('[Dexie Status Cache Put Error]:', e);
        }
      }
    },
    async get(empId, cachedDate) {
      if (Capacitor.isNativePlatform()) {
        try {
          return await sqliteGetTodayAttendance(Number(empId), cachedDate);
        } catch (e) {
          console.error('[db.js get error]:', e, e?.stack || '');
          return null;
        }
      } else {
        try {
          const row = await dexieDb.today_attendance_cache.get(Number(empId));
          if (row && row.cached_date === cachedDate) {
            return {
              hasCheckedIn: row.hasCheckedIn,
              hasCheckedOut: row.hasCheckedOut,
              checked_in: row.checked_in,
              check_in_time: row.check_in_time,
              check_out_time: row.check_out_time
            };
          }
        } catch (e) {
          console.warn('[Dexie Status Cache Get Error]:', e);
        }
        return null;
      }
    },
    async clear() {
      if (Capacitor.isNativePlatform()) {
        await sqliteClearTodayAttendanceCache();
      } else {
        await dexieDb.today_attendance_cache.clear();
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
    const result = await sqliteQueueOfflineAttendance(logData);
    if (result) {
      const timestamp = result.timestamp || new Date().toISOString();
      const logLine = `[${timestamp}] [OFFLINE QUEUED] [${result.attendance_type || 'CHECK_IN'}] NIK: ${result.nik || '-'} - ${result.name || 'Karyawan'} (Loc: ${result.location || 'Mobile'}, Dist: ${result.euclidean_distance || 0})\n`;
      await writeToBackupStorage(logLine);
    }
    return result;
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

/**
 * Purges deleted employee master vectors and cache records from local IndexedDB or SQLite.
 */
export async function deleteLocalEmployee(employeeId) {
  if (Capacitor.isNativePlatform()) {
    await sqliteDeleteEmployeeBiometrics(Number(employeeId));
    return;
  }

  try {
    const allMasters = await dexieDb.user_master.toArray();
    const existing = allMasters.find((m) => String(m.employee_id) === String(employeeId));
    if (existing) {
      await dexieDb.user_master.delete(existing.id);
      console.log(`[IndexedDB] Deleted master vector for employee ID: ${employeeId}`);
    }
    // Also remove from employees_cache table if it exists
    await dexieDb.employees_cache.delete(Number(employeeId));
  } catch (err) {
    console.error('[IndexedDB deleteLocalEmployee Error]:', err);
  }
}

/**
 * Appends plain text audit logs directly to a public file inside the device's Documents folder.
 * This guarantees the user's offline logs are never lost even if the app's cache or storage is cleared.
 */
export async function writeToBackupStorage(logLine) {
  if (!Capacitor.isNativePlatform()) return;
  const path = 'AgriFace_Offline_Backup.txt';
  const directory = Directory.External;
  const encoding = Encoding?.UTF8 || 'utf8';

  try {
    // Append to file in user's public external app directory (bypasses Scoped Storage restrictions)
    await Filesystem.appendFile({
      path,
      data: logLine,
      directory,
      encoding
    });

    // Resolve and print the exact file path on the device
    try {
      const uriResult = await Filesystem.getUri({ directory, path });
      console.log('[Storage Backup] Appended log to:', uriResult.uri);
    } catch (_) {
      console.log('[Storage Backup] Appended log to public external storage');
    }
  } catch (err) {
    console.warn('[Storage Backup] appendFile failed, falling back to read-modify-write:', err);
    try {
      let existing = '';
      try {
        const readResult = await Filesystem.readFile({
          path,
          directory,
          encoding
        });
        existing = readResult.data || '';
      } catch (readErr) {
        // File doesn't exist yet, ignore
      }

      await Filesystem.writeFile({
        path,
        data: existing + logLine,
        directory,
        encoding
      });

      try {
        const uriResult = await Filesystem.getUri({ directory, path });
        console.log('[Storage Backup] Fallback write successful to:', uriResult.uri);
      } catch (_) {
        console.log('[Storage Backup] Fallback write successful');
      }
    } catch (writeErr) {
      console.error('[Storage Backup] Fallback write failed:', writeErr);
    }
  }
}


