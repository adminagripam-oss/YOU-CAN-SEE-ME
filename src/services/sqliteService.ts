import { Capacitor } from '@capacitor/core';
import { SQLiteConnection, SQLiteDBConnection, CapacitorSQLite } from '@capacitor-community/sqlite';

const sqlite = new SQLiteConnection(CapacitorSQLite);
let dbConnection: SQLiteDBConnection | null = null;

// Promise-based init guard — eliminates race condition completely.
// All DB functions await this before executing.
let _initResolve: (() => void) | null = null;
let _initReject: ((e: any) => void) | null = null;
const _dbReadyPromise: Promise<void> = new Promise((res, rej) => {
  _initResolve = res;
  _initReject = rej;
});

/**
 * Await this before any DB operation to guarantee initSQLite has finished.
 * On non-native platforms it resolves immediately (no-op).
 */
async function waitForConnection(): Promise<boolean> {
  if (!Capacitor.isNativePlatform()) return false; // web — SQLite not used
  try {
    await _dbReadyPromise;
    return !!dbConnection;
  } catch {
    return false;
  }
}

/**
 * Initializes the SQLite Database connection and creates tables if they don't exist.
 */
export async function initSQLite(): Promise<void> {
  if (!Capacitor.isNativePlatform()) {
    console.log('[SQLite Service] Web platform detected. Skipping SQLite initialization.');
    _initResolve?.(); // resolve immediately on web so waitForConnection never hangs
    return;
  }

  try {
    console.log('[SQLite Service] Initializing SQLite connection for native APK...');

    // Check if connection already exists
    const isConn = await sqlite.isConnection('AgriFaceLocalDB', false);
    if (isConn.result) {
      dbConnection = await sqlite.retrieveConnection('AgriFaceLocalDB', false);
    } else {
      dbConnection = await sqlite.createConnection(
        'AgriFaceLocalDB',
        false, // encrypted
        'no-encryption',
        1, // version
        false // readonly
      );
    }

    if (!dbConnection) {
      throw new Error('Gagal membuat koneksi SQLite database.');
    }

    // Open connection
    await dbConnection.open();
    console.log('[SQLite Service] Database connection opened successfully.');

    // Execute DDL to verify local tables exist
    const ddl = `
      CREATE TABLE IF NOT EXISTS local_employees (
        id TEXT PRIMARY KEY,
        nik TEXT NOT NULL,
        name TEXT NOT NULL,
        department TEXT,
        afdeling TEXT,
        nama_kebun TEXT,
        status_tk TEXT,
        jabatan TEXT,
        status_perkawinan TEXT,
        has_master_biometric INTEGER DEFAULT 0,
        region TEXT,
        is_synced INTEGER DEFAULT 1
      );

      CREATE TABLE IF NOT EXISTS local_master_descriptors (
        employee_id TEXT PRIMARY KEY,
        descriptor_json TEXT,
        geometric_descriptor_json TEXT,
        updated_at TEXT
      );

      CREATE TABLE IF NOT EXISTS local_attendance_queue (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        employee_id TEXT NOT NULL,
        nik TEXT,
        name TEXT,
        department TEXT,
        timestamp TEXT,
        location TEXT,
        lat REAL,
        lng REAL,
        status TEXT,
        attendance_type TEXT,
        euclidean_distance REAL,
        path_foto_lokal TEXT,
        path_foto_storage TEXT,
        status_sync_teks TEXT DEFAULT 'pending',
        status_sync_foto TEXT DEFAULT 'pending',
        is_synced INTEGER DEFAULT 0,
        created_at TEXT,
        kebun TEXT
      );

      CREATE TABLE IF NOT EXISTS local_today_attendance_cache (
        employee_id TEXT PRIMARY KEY,
        has_checked_in INTEGER DEFAULT 0,
        has_checked_out INTEGER DEFAULT 0,
        checked_in INTEGER DEFAULT 0,
        check_in_time TEXT,
        check_out_time TEXT,
        cached_date TEXT
      );

      CREATE TABLE IF NOT EXISTS local_attendance_logs (
        id TEXT PRIMARY KEY,
        employee_id TEXT NOT NULL,
        nik TEXT,
        name TEXT,
        department TEXT,
        afdeling TEXT,
        timestamp TEXT,
        location TEXT,
        lat REAL,
        lng REAL,
        status TEXT,
        attendance_type TEXT,
        euclidean_distance REAL,
        path_foto_lokal TEXT,
        path_foto_storage TEXT,
        status_sync_teks TEXT DEFAULT 'pending',
        status_sync_foto TEXT DEFAULT 'pending',
        is_synced INTEGER DEFAULT 0,
        created_at TEXT,
        kebun TEXT
      );

      CREATE TABLE IF NOT EXISTS local_admins (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        username TEXT UNIQUE NOT NULL,
        password_hash TEXT NOT NULL,
        role TEXT,
        region TEXT,
        kebun TEXT,
        name TEXT,
        nik TEXT,
        last_login TEXT
      );

      CREATE TABLE IF NOT EXISTS local_employee_sync_queue (
        id TEXT PRIMARY KEY,
        nik TEXT,
        name TEXT,
        department TEXT,
        afdeling TEXT,
        nama_kebun TEXT,
        status_tk TEXT,
        jabatan TEXT,
        status_perkawinan TEXT,
        descriptor_json TEXT,
        geometric_descriptor_json TEXT,
        is_synced INTEGER DEFAULT 0,
        created_at TEXT
      );

      CREATE TABLE IF NOT EXISTS local_attendance_requests (
        id TEXT PRIMARY KEY,
        request_type TEXT,
        log_id TEXT,
        nik TEXT,
        name TEXT,
        nama_kebun TEXT,
        requested_by TEXT,
        requested_at TEXT,
        status TEXT,
        old_value TEXT,
        new_value TEXT,
        is_synced INTEGER DEFAULT 0
      );
    `;

    await dbConnection.execute(ddl);

    // Migration logic for existing databases with old INTEGER primary key schemas
    try {
      const empInfo = await dbConnection.query(`PRAGMA table_info(local_employees);`);
      const idCol = empInfo.values?.find((c: any) => c.name === 'id');
      if (idCol && idCol.type && idCol.type.toUpperCase().includes('INT')) {
        console.log('[SQLite Service] Migrating local_employees id column from INTEGER to TEXT...');
        await dbConnection.execute(`
          CREATE TABLE IF NOT EXISTS local_employees_v2 (
            id TEXT PRIMARY KEY,
            nik TEXT NOT NULL,
            name TEXT NOT NULL,
            department TEXT,
            afdeling TEXT,
            nama_kebun TEXT,
            status_tk TEXT,
            jabatan TEXT,
            status_perkawinan TEXT,
            has_master_biometric INTEGER DEFAULT 0,
            region TEXT,
            is_synced INTEGER DEFAULT 1
          );
          INSERT OR IGNORE INTO local_employees_v2 (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric)
            SELECT CAST(id AS TEXT), nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric FROM local_employees;
          DROP TABLE local_employees;
          ALTER TABLE local_employees_v2 RENAME TO local_employees;
        `);
        console.log('[SQLite Service] Migrated local_employees to TEXT primary key.');
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_employees warning:', e);
    }

    try {
      const mdInfo = await dbConnection.query(`PRAGMA table_info(local_master_descriptors);`);
      const empIdCol = mdInfo.values?.find((c: any) => c.name === 'employee_id');
      if (empIdCol && empIdCol.type && empIdCol.type.toUpperCase().includes('INT')) {
        console.log('[SQLite Service] Migrating local_master_descriptors employee_id column from INTEGER to TEXT...');
        await dbConnection.execute(`
          CREATE TABLE IF NOT EXISTS local_master_descriptors_v2 (
            employee_id TEXT PRIMARY KEY,
            descriptor_json TEXT,
            geometric_descriptor_json TEXT,
            updated_at TEXT
          );
          INSERT OR IGNORE INTO local_master_descriptors_v2 (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
            SELECT CAST(employee_id AS TEXT), descriptor_json, geometric_descriptor_json, updated_at FROM local_master_descriptors;
          DROP TABLE local_master_descriptors;
          ALTER TABLE local_master_descriptors_v2 RENAME TO local_master_descriptors;
        `);
        console.log('[SQLite Service] Migrated local_master_descriptors to TEXT primary key.');
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_master_descriptors warning:', e);
    }

    try {
      const tacInfo = await dbConnection.query(`PRAGMA table_info(local_today_attendance_cache);`);
      const empIdCol = tacInfo.values?.find((c: any) => c.name === 'employee_id');
      if (empIdCol && empIdCol.type && empIdCol.type.toUpperCase().includes('INT')) {
        console.log('[SQLite Service] Migrating local_today_attendance_cache employee_id column from INTEGER to TEXT...');
        await dbConnection.execute(`
          CREATE TABLE IF NOT EXISTS local_today_attendance_cache_v2 (
            employee_id TEXT PRIMARY KEY,
            has_checked_in INTEGER DEFAULT 0,
            has_checked_out INTEGER DEFAULT 0,
            checked_in INTEGER DEFAULT 0,
            check_in_time TEXT,
            check_out_time TEXT,
            cached_date TEXT
          );
          INSERT OR IGNORE INTO local_today_attendance_cache_v2 (employee_id, has_checked_in, has_checked_out, checked_in, check_in_time, check_out_time, cached_date)
            SELECT CAST(employee_id AS TEXT), has_checked_in, has_checked_out, checked_in, check_in_time, check_out_time, cached_date FROM local_today_attendance_cache;
          DROP TABLE local_today_attendance_cache;
          ALTER TABLE local_today_attendance_cache_v2 RENAME TO local_today_attendance_cache;
        `);
        console.log('[SQLite Service] Migrated local_today_attendance_cache to TEXT primary key.');
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_today_attendance_cache warning:', e);
    }

    // Migration to remove UNIQUE constraint from nik (if it exists) and add missing columns
    try {
      const empInfo = await dbConnection.query(`PRAGMA table_info(local_employees);`);
      const hasRegion = empInfo.values?.some((c: any) => c.name === 'region');

      const indexList = await dbConnection.query(`PRAGMA index_list(local_employees);`);
      let hasUniqueNik = false;
      if (indexList.values) {
        for (const idx of indexList.values) {
          if (idx.unique === 1) {
            const indexInfo = await dbConnection.query(`PRAGMA index_info('${idx.name}');`);
            if (indexInfo.values && indexInfo.values.some((col: any) => col.name === 'nik')) {
              hasUniqueNik = true;
              break;
            }
          }
        }
      }

      if (hasUniqueNik || !hasRegion) {
        console.log('[SQLite Service] Migrating local_employees to remove UNIQUE nik and ensure all columns exist...');
        await dbConnection.execute(`
          CREATE TABLE IF NOT EXISTS local_employees_v3 (
            id TEXT PRIMARY KEY,
            nik TEXT NOT NULL,
            name TEXT NOT NULL,
            department TEXT,
            afdeling TEXT,
            nama_kebun TEXT,
            status_tk TEXT,
            jabatan TEXT,
            status_perkawinan TEXT,
            has_master_biometric INTEGER DEFAULT 0,
            region TEXT,
            is_synced INTEGER DEFAULT 1
          );
        `);

        if (!hasRegion) {
          await dbConnection.execute(`
            INSERT OR IGNORE INTO local_employees_v3 (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric)
              SELECT id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric FROM local_employees;
          `);
        } else {
          await dbConnection.execute(`
            INSERT OR IGNORE INTO local_employees_v3 (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
              SELECT id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced FROM local_employees;
          `);
        }

        await dbConnection.execute(`
          DROP TABLE local_employees;
          ALTER TABLE local_employees_v3 RENAME TO local_employees;
        `);
        console.log('[SQLite Service] Migrated local_employees to remove UNIQUE nik successfully.');
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_employees v3 warning:', e);
    }

    try {
      const aqInfo = await dbConnection.query(`PRAGMA table_info(local_attendance_queue);`);
      const empIdCol = aqInfo.values?.find((c: any) => c.name === 'employee_id');
      if (empIdCol && empIdCol.type && empIdCol.type.toUpperCase().includes('INT')) {
        console.log('[SQLite Service] Migrating local_attendance_queue employee_id column from INTEGER to TEXT...');
        await dbConnection.execute(`
          CREATE TABLE IF NOT EXISTS local_attendance_queue_v2 (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            employee_id TEXT NOT NULL,
            nik TEXT,
            name TEXT,
            department TEXT,
            timestamp TEXT,
            location TEXT,
            lat REAL,
            lng REAL,
            status TEXT,
            attendance_type TEXT,
            euclidean_distance REAL,
            is_synced INTEGER DEFAULT 0,
            created_at TEXT,
            kebun TEXT
          );
          INSERT OR IGNORE INTO local_attendance_queue_v2 (id, employee_id, nik, name, department, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at)
            SELECT id, CAST(employee_id AS TEXT), nik, name, department, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at FROM local_attendance_queue;
          DROP TABLE local_attendance_queue;
          ALTER TABLE local_attendance_queue_v2 RENAME TO local_attendance_queue;
        `);
        console.log('[SQLite Service] Migrated local_attendance_queue to TEXT employee_id.');
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_attendance_queue warning:', e);
    }

    try {
      const alInfo = await dbConnection.query(`PRAGMA table_info(local_attendance_logs);`);
      const empIdCol = alInfo.values?.find((c: any) => c.name === 'employee_id');
      if (empIdCol && empIdCol.type && empIdCol.type.toUpperCase().includes('INT')) {
        console.log('[SQLite Service] Migrating local_attendance_logs employee_id column from INTEGER to TEXT...');
        await dbConnection.execute(`
          CREATE TABLE IF NOT EXISTS local_attendance_logs_v2 (
            id TEXT PRIMARY KEY,
            employee_id TEXT NOT NULL,
            nik TEXT,
            name TEXT,
            department TEXT,
            afdeling TEXT,
            timestamp TEXT,
            location TEXT,
            lat REAL,
            lng REAL,
            status TEXT,
            attendance_type TEXT,
            euclidean_distance REAL,
            is_synced INTEGER DEFAULT 0,
            created_at TEXT,
            kebun TEXT
          );
          INSERT OR IGNORE INTO local_attendance_logs_v2 (id, employee_id, nik, name, department, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at)
            SELECT id, CAST(employee_id AS TEXT), nik, name, department, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at FROM local_attendance_logs;
          DROP TABLE local_attendance_logs;
          ALTER TABLE local_attendance_logs_v2 RENAME TO local_attendance_logs;
        `);
        console.log('[SQLite Service] Migrated local_attendance_logs to TEXT employee_id.');
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_attendance_logs warning:', e);
    }

    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN afdeling TEXT;`);
      console.log('[SQLite Service] Migrated local_attendance_queue: added afdeling column');
    } catch (e) {
      // Column might already exist, ignore error
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN kebun TEXT;`);
      console.log('[SQLite Service] Migrated local_attendance_queue: added kebun column');
    } catch (e) {
      // Column might already exist, ignore error
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN afdeling TEXT;`);
      console.log('[SQLite Service] Migrated local_attendance_logs: added afdeling column');
    } catch (e) {
      // Column might already exist, ignore error
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN kebun TEXT;`);
      console.log('[SQLite Service] Migrated local_attendance_logs: added kebun column');
    } catch (e) {
      // Column might already exist, ignore error
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_employees ADD COLUMN region TEXT;`);
      console.log('[SQLite Service] Migrated local_employees: added region column');
    } catch (e) {
      // Column might already exist, ignore error
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_employees ADD COLUMN is_synced INTEGER DEFAULT 1;`);
      console.log('[SQLite Service] Migrated local_employees: added is_synced column');
    } catch (e) {
      // Column might already exist, ignore error
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_admins ADD COLUMN nik TEXT;`);
      console.log('[SQLite Service] Migrated local_admins: added nik column');
    } catch (e) {
      // Column might already exist, ignore error
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN path_foto_lokal TEXT;`);
    } catch (e) {}
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN path_foto_storage TEXT;`);
    } catch (e) {}
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN status_sync_teks TEXT DEFAULT 'pending';`);
    } catch (e) {}
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN status_sync_foto TEXT DEFAULT 'pending';`);
    } catch (e) {}

    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN path_foto_lokal TEXT;`);
    } catch (e) {}
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN path_foto_storage TEXT;`);
    } catch (e) {}
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN status_sync_teks TEXT DEFAULT 'pending';`);
    } catch (e) {}
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN status_sync_foto TEXT DEFAULT 'pending';`);
    } catch (e) {}

    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN sync_notes TEXT;`);
    } catch (e) {}
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN sync_notes TEXT;`);
    } catch (e) {}

    console.log('[SQLite Service] SQLite tables verified and ready.');
    _initResolve?.(); // Signal: DB is ready — unblocks all waitForConnection() callers
  } catch (err: any) {
    console.error('[SQLite Service Init Error]:', err?.message || err);
    _initReject?.(err); // Signal: DB failed — waitForConnection() will return false
  }
}


/**
 * Get active DB connection
 */
export function getSQLiteConnection(): SQLiteDBConnection | null {
  return dbConnection;
}

/**
 * Cache master descriptor for a single employee.
 */
export async function sqliteCacheUserMasterVector(user: any): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) {
      console.error('[SQLite Service sqliteCacheUserMasterVector] dbConnection null even after wait. Skipping cache.');
      return;
    }
  }
  try {
    const empId = user.employee_id || user.id;
    if (!empId) return;
    const empIdStr = String(empId);

    let vector = user.descriptor_json || user.descriptor || user.face_vector || null;
    let vectorStr: string | null = null;
    if (vector) {
      let parsed = vector;
      while (typeof parsed === 'string') {
        try {
          parsed = JSON.parse(parsed);
        } catch (e) {
          break;
        }
      }
      let arr: number[] | null = null;
      if (Array.isArray(parsed)) {
        arr = parsed;
      } else if (parsed instanceof Float32Array || parsed instanceof Float64Array || ArrayBuffer.isView(parsed)) {
        arr = Array.from(parsed as any);
      } else if (typeof parsed === 'object' && parsed !== null) {
        const values = Object.values(parsed);
        if (values.length > 0) {
          arr = Array.from(values as any);
        }
      }
      if (arr && arr.length > 0) {
        vectorStr = JSON.stringify(arr.map(n => Number(n) || 0));
      }
    }

    const gfv = user.geometric_descriptor_json || null;
    const gfvStr = gfv ? JSON.stringify(gfv) : null;

    // 1. Cache to local_employees
    await dbConnection!.run(
      `INSERT OR REPLACE INTO local_employees (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        empIdStr,
        user.nik,
        user.name,
        user.department || user.jabatan || null,
        user.afdeling || null,
        user.nama_kebun || user.kebun || null,
        user.status_tk || null,
        user.jabatan || null,
        user.status_perkawinan || null,
        vectorStr ? 1 : 0,
        user.region || null,
        1
      ]
    );

    // 2. Cache to local_master_descriptors
    await dbConnection!.run(
      `INSERT OR REPLACE INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
       VALUES (?, ?, ?, ?)`,
      [
        empIdStr,
        vectorStr,
        gfvStr,
        new Date().toISOString()
      ]
    );
    console.log(`[SQLite Service] Cached master for ${user.name} | GFV: ${gfvStr ? 'YES' : 'NO'}`);
  } catch (err: any) {
    console.error('[SQLite Service cacheUserMasterVector Error]:', err?.message || err);
  }
}

/**
 * Cache geometric descriptor (GFV 40-d) for an employee.
 */
export async function sqliteCacheGeometricVector(employeeId: number | string, gfv: any): Promise<void> {
  if (!dbConnection) return;
  try {
    if (!employeeId || !gfv) return;
    const gfvStr = JSON.stringify(gfv);
    await dbConnection.run(
      `UPDATE local_master_descriptors SET geometric_descriptor_json = ?, updated_at = ? WHERE employee_id = ?`,
      [gfvStr, new Date().toISOString(), String(employeeId)]
    );
    console.log(`[SQLite Service] Cached geometric vector for employee: ${employeeId}`);
  } catch (err: any) {
    console.error('[SQLite Service cacheGeometricVector Error]:', err?.message || err);
  }
}

/**
 * Retrieves cached master vectors and employee info from SQLite.
 */
export async function sqliteGetCachedUserMasterVector(employeeId: number | string): Promise<any | null> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return null;
  }
  try {
    const empIdStr = String(employeeId);
    const res = await dbConnection!.query(
      `SELECT md.*, e.nik, e.name, e.department, e.afdeling, e.nama_kebun, e.status_tk, e.jabatan, e.status_perkawinan
       FROM local_master_descriptors md
       LEFT JOIN local_employees e ON CAST(md.employee_id AS TEXT) = CAST(e.id AS TEXT)
       WHERE CAST(md.employee_id AS TEXT) = ? OR md.employee_id = ? OR CAST(e.nik AS TEXT) = ?`,
      [empIdStr, employeeId, empIdStr]
    );

    if (!res.values || res.values.length === 0 || !res.values[0].descriptor_json) {
      // Jika tidak ada di master_descriptors, coba cek apakah ini karyawan yang didaftarkan offline (belum di-sync)
      const qRes = await dbConnection!.query(
        `SELECT * FROM local_employee_sync_queue WHERE CAST(id AS TEXT) = ? OR id = ? OR CAST(nik AS TEXT) = ? LIMIT 1`,
        [empIdStr, employeeId, empIdStr]
      );
      if (qRes.values && qRes.values.length > 0) {
        const row = qRes.values[0];
        if (row.descriptor_json) {
          return {
            employee_id: row.id,
            nik: row.nik,
            name: row.name,
            department: row.department,
            afdeling: row.afdeling,
            nama_kebun: row.nama_kebun,
            status_tk: row.status_tk,
            jabatan: row.jabatan,
            status_perkawinan: row.status_perkawinan,
            descriptor_json: typeof row.descriptor_json === 'string' ? JSON.parse(row.descriptor_json) : row.descriptor_json,
            face_vector: typeof row.descriptor_json === 'string' ? JSON.parse(row.descriptor_json) : row.descriptor_json,
            geometric_descriptor_json: row.geometric_descriptor_json ? (typeof row.geometric_descriptor_json === 'string' ? JSON.parse(row.geometric_descriptor_json) : row.geometric_descriptor_json) : null,
            updated_at: row.created_at
          };
        }
      }
      return null;
    }
    const row = res.values[0];

    return {
      employee_id: row.employee_id,
      nik: row.nik,
      name: row.name,
      department: row.department,
      afdeling: row.afdeling,
      nama_kebun: row.nama_kebun,
      status_tk: row.status_tk,
      jabatan: row.jabatan,
      status_perkawinan: row.status_perkawinan,
      descriptor_json: row.descriptor_json ? (typeof row.descriptor_json === 'string' ? JSON.parse(row.descriptor_json) : row.descriptor_json) : null,
      face_vector: row.descriptor_json ? (typeof row.descriptor_json === 'string' ? JSON.parse(row.descriptor_json) : row.descriptor_json) : null,
      geometric_descriptor_json: row.geometric_descriptor_json ? (typeof row.geometric_descriptor_json === 'string' ? JSON.parse(row.geometric_descriptor_json) : row.geometric_descriptor_json) : null,
      updated_at: row.updated_at
    };
  } catch (err: any) {
    console.error('[SQLite Service getCachedUserMasterVector Error]:', err?.message || err);
    return null;
  }
}

/**
 * Queue an offline attendance log.
 */
export async function sqliteQueueOfflineAttendance(logData: any): Promise<any> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) throw new Error('Database SQLite tidak terhubung.');
  }
  try {
    const createdAt = new Date().toISOString();
    const timestamp = logData.timestamp || createdAt;
    const statusSyncTeks = logData.status_sync_teks || 'pending';
    const statusSyncFoto = logData.status_sync_foto || 'pending';
    const isSynced = (statusSyncTeks === 'done' && statusSyncFoto === 'done') ? 1 : 0;

    await dbConnection!.run(
      `INSERT INTO local_attendance_queue (
        employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at
       ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        String(logData.employee_id),
        logData.nik,
        logData.name,
        logData.department,
        logData.afdeling || null,
        logData.kebun || null,
        timestamp,
        logData.location || 'HP Mobile (Offline)',
        logData.lat || null,
        logData.lng || null,
        logData.status,
        logData.attendance_type || 'CHECK_IN',
        logData.euclidean_distance,
        logData.path_foto_lokal || null,
        logData.path_foto_storage || null,
        statusSyncTeks,
        statusSyncFoto,
        isSynced,
        createdAt
      ]
    );

    const lastIdRes = await dbConnection!.query('SELECT last_insert_rowid() as id');
    const id = lastIdRes.values?.[0]?.id || null;

    const queuedItem = {
      ...logData,
      id,
      timestamp,
      path_foto_lokal: logData.path_foto_lokal || null,
      path_foto_storage: logData.path_foto_storage || null,
      status_sync_teks: statusSyncTeks,
      status_sync_foto: statusSyncFoto,
      is_synced: isSynced === 1,
      created_at: createdAt
    };
    console.log(`[SQLite Service Queue] Queued offline attendance with ID: ${id}`);
    return queuedItem;
  } catch (err: any) {
    console.error('[SQLite Service sqliteQueueOfflineAttendance Error]:', err?.message || err);
    throw err;
  }
}

/**
 * Retrieve all unsynced logs.
 */
export async function sqliteGetUnsyncedLogs(): Promise<any[]> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return [];
  }
  try {
    const res = await dbConnection!.query(
      `SELECT * FROM local_attendance_queue WHERE is_synced = 0`
    );
    return res.values || [];
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetUnsyncedLogs Error]:', err?.message || err);
    return [];
  }
}

/**
 * Delete synced logs by IDs.
 */
export async function sqliteRemoveSyncedLogs(ids: number[]): Promise<void> {
  if (!ids || ids.length === 0) return;
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    const set = ids.map(id => ({
      statement: `DELETE FROM local_attendance_queue WHERE id = ?`,
      values: [id]
    }));
    await dbConnection!.executeSet(set);
    console.log(`[SQLite Service] Successfully removed ${ids.length} synced logs from queue.`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteRemoveSyncedLogs Error]:', err?.message || err);
  }
}

/**
 * Clear the local employees cache.
 */
export async function sqliteClearEmployeesCache(filter?: { kebun?: string | null; region?: string | null }): Promise<void> {
  if (!dbConnection) return;
  try {
    if (filter && filter.kebun) {
      await dbConnection.run(
        `DELETE FROM local_master_descriptors WHERE employee_id IN (SELECT id FROM local_employees WHERE nama_kebun = ?)`,
        [filter.kebun]
      );
      await dbConnection.run(`DELETE FROM local_employees WHERE nama_kebun = ?`, [filter.kebun]);
      console.log(`[SQLite Service] Cleared local employees & descriptors cache for kebun: ${filter.kebun}`);
    } else if (filter && filter.region) {
      await dbConnection.run(
        `DELETE FROM local_master_descriptors WHERE employee_id IN (SELECT id FROM local_employees WHERE region = ?)`,
        [filter.region]
      );
      await dbConnection.run(`DELETE FROM local_employees WHERE region = ?`, [filter.region]);
      console.log(`[SQLite Service] Cleared local employees & descriptors cache for region: ${filter.region}`);
    } else {
      await dbConnection.execute(`DELETE FROM local_master_descriptors`);
      await dbConnection.execute(`DELETE FROM local_employees`);
      console.log('[SQLite Service] Cleared all local employees & descriptors cache.');
    }
  } catch (err: any) {
    console.error('[SQLite Service sqliteClearEmployeesCache Error]:', err?.message || err);
  }
}

/**
 * Inserts or updates bulk employees into local cache.
 */
export async function sqliteBulkPutEmployeesCache(empData: any[]): Promise<void> {
  if (!dbConnection || !empData || empData.length === 0) return;
  try {
    const set: any[] = [];
    empData.forEach(emp => {
      // 1. Employee query
      set.push({
        statement: `INSERT OR REPLACE INTO local_employees (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
        values: [
          String(emp.id),
          emp.nik,
          emp.name,
          emp.department || emp.jabatan || null,
          emp.afdeling || null,
          emp.nama_kebun || emp.kebun || null,
          emp.status_tk || null,
          emp.jabatan || null,
          emp.status_perkawinan || null,
          emp.has_master_biometric ? 1 : 0,
          emp.region || null,
          emp.is_synced !== undefined ? (emp.is_synced ? 1 : 0) : 1
        ]
      });

      // 2. Master Descriptor query (jika ada data biometrik yang ikut turun saat sinkronisasi)
      if (emp.descriptor_json || emp.geometric_descriptor_json || emp.face_vector || emp.facial_descriptor) {
        const descJson = emp.descriptor_json || emp.face_vector || emp.facial_descriptor;
        const geoDescJson = emp.geometric_descriptor_json;
        set.push({
          statement: `INSERT OR REPLACE INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
                      VALUES (?, ?, ?, ?)`,
          values: [
            String(emp.id),
            descJson ? (typeof descJson === 'string' ? descJson : JSON.stringify(descJson)) : null,
            geoDescJson ? (typeof geoDescJson === 'string' ? geoDescJson : JSON.stringify(geoDescJson)) : null,
            new Date().toISOString()
          ]
        });
      } else if (!emp.has_master_biometric) {
        // Jika dari cloud menyatakan karyawan ini TIDAK PUNYA biometrik lagi, hapus cache lokalnya
        set.push({
          statement: `DELETE FROM local_master_descriptors WHERE employee_id = ?`,
          values: [String(emp.id)]
        });
      }
    });

    await dbConnection.executeSet(set);
    console.log(`[SQLite Service] Bulk put ${empData.length} employees (and their descriptors) into cache.`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteBulkPutEmployeesCache Error]:', err?.message || err);
  }
}


/**
 * Gets all cached employees.
 */
export async function sqliteGetEmployeesCache(): Promise<any[]> {
  if (!dbConnection) return [];
  try {
    const res = await dbConnection.query(`
      SELECT e.*, md.descriptor_json 
      FROM local_employees e
      LEFT JOIN local_master_descriptors md ON e.id = md.employee_id
    `);
    const rows = res.values || [];
    return rows.map(row => {
      let parsedDesc = null;
      if (row.descriptor_json) {
        try {
          parsedDesc = typeof row.descriptor_json === 'string' ? JSON.parse(row.descriptor_json) : row.descriptor_json;
        } catch (e) { }
      }
      return {
        ...row,
        kebun: row.nama_kebun,
        has_master_biometric: row.has_master_biometric == 1 || row.has_master_biometric === 'true' || !!parsedDesc,
        descriptor_json: parsedDesc
      };
    });
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetEmployeesCache Error]:', err?.message || err);
    return [];
  }
}

/**
 * Gets ALL master descriptors joined with employee info.
 * Used for duplicate face validation (one-face-per-employee check).
 */
export async function sqliteGetAllMasterVectors(): Promise<any[]> {
  if (!dbConnection) return [];
  try {
    const res = await dbConnection.query(
      `SELECT md.employee_id, md.descriptor_json, e.nik, e.name
       FROM local_master_descriptors md
       LEFT JOIN local_employees e ON md.employee_id = e.id
       WHERE md.descriptor_json IS NOT NULL`
    );
    const rows = res.values || [];
    return rows.map(row => {
      let parsedDesc = null;
      if (row.descriptor_json) {
        try {
          parsedDesc = typeof row.descriptor_json === 'string' ? JSON.parse(row.descriptor_json) : row.descriptor_json;
        } catch (e) { }
      }
      return {
        employee_id: row.employee_id,
        nik: row.nik,
        name: row.name,
        descriptor_json: parsedDesc,
      };
    });
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetAllMasterVectors Error]:', err?.message || err);
    return [];
  }
}

/**
 * Deletes local employee data and master biometric descriptors.
 */
export async function sqliteDeleteEmployeeBiometrics(employeeId: number | string): Promise<void> {
  if (!dbConnection) return;
  try {
    const empIdStr = String(employeeId);
    const set = [
      {
        statement: `DELETE FROM local_master_descriptors WHERE employee_id = ?`,
        values: [empIdStr]
      },
      {
        statement: `DELETE FROM local_employees WHERE id = ?`,
        values: [empIdStr]
      }
    ];
    await dbConnection.executeSet(set);
    console.log(`[SQLite Service] Successfully deleted local master biometrics and cache for employee ID: ${employeeId}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteDeleteEmployeeBiometrics Error]:', err?.message || err);
  }
}

export async function sqliteCacheTodayAttendance(statusMap: any, cachedDate: string): Promise<void> {
  if (!dbConnection) return;
  try {
    // Clear old caches for different dates
    await dbConnection.run(
      `DELETE FROM local_today_attendance_cache WHERE cached_date != ?`,
      [cachedDate]
    );

    // Bulk insert/replace using a transaction (executeSet)
    const statements: any[] = [];
    const keys = Object.keys(statusMap);
    for (let i = 0; i < keys.length; i++) {
      const empId = keys[i];
      const status = statusMap[empId];
      if (!status) continue;

      statements.push({
        statement: `INSERT OR REPLACE INTO local_today_attendance_cache 
                    (employee_id, has_checked_in, has_checked_out, checked_in, check_in_time, check_out_time, cached_date)
                    VALUES (?, ?, ?, ?, ?, ?, ?)`,
        values: [
          String(empId),
          status.hasCheckedIn ? 1 : 0,
          status.hasCheckedOut ? 1 : 0,
          status.checked_in ? 1 : 0,
          status.check_in_time || null,
          status.check_out_time || null,
          cachedDate
        ]
      });
    }

    if (statements.length > 0) {
      await dbConnection.executeSet(statements);
    }
    console.log(`[SQLite Service] Cached today's attendance status for ${statements.length} employees in local database`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteCacheTodayAttendance Error]:', err?.message || err, err?.stack || '');
    throw err;
  }
}

/**
 * Retrieves cached today's attendance status for a single employee from SQLite.
 */
export async function sqliteGetTodayAttendance(empId: number | string, cachedDate: string): Promise<any | null> {
  if (!dbConnection) return null;
  try {
    const res = await dbConnection.query(
      `SELECT * FROM local_today_attendance_cache WHERE employee_id = ? AND cached_date = ?`,
      [String(empId), cachedDate]
    );
    const rows = res.values || [];
    if (rows.length > 0) {
      const row = rows[0];
      return {
        hasCheckedIn: row.has_checked_in === 1,
        hasCheckedOut: row.has_checked_out === 1,
        checked_in: row.checked_in === 1,
        check_in_time: row.check_in_time,
        check_out_time: row.check_out_time
      };
    }
    return null;
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetTodayAttendance Error]:', err?.message || err);
    return null;
  }
}

/**
 * Clears today's attendance status cache table.
 */
export async function sqliteClearTodayAttendanceCache(): Promise<void> {
  if (!dbConnection) return;
  try {
    await dbConnection.run(`DELETE FROM local_today_attendance_cache`, []);
    console.log('[SQLite Service] Cleared local today attendance status cache');
  } catch (err: any) {
    console.error('[SQLite Service sqliteClearTodayAttendanceCache Error]:', err?.message || err);
  }
}

/**
 * Save a single attendance log to local SQLite.
 */
export async function sqliteSaveAttendanceLog(log: any): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    const statusSyncTeks = log.status_sync_teks || (log.is_synced ? 'done' : 'pending');
    const statusSyncFoto = log.status_sync_foto || (log.is_synced ? 'done' : 'pending');
    const isSynced = (statusSyncTeks === 'done' && statusSyncFoto === 'done') ? 1 : (log.is_synced ? 1 : 0);

    await dbConnection!.run(
      `INSERT OR REPLACE INTO local_attendance_logs 
      (id, employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        String(log.id),
        String(log.employee_id),
        log.nik || null,
        log.name || null,
        log.department || null,
        log.afdeling || null,
        log.kebun || log.nama_kebun || null,
        log.timestamp || null,
        log.location || null,
        log.lat !== undefined && log.lat !== null ? Number(log.lat) : null,
        log.lng !== undefined && log.lng !== null ? Number(log.lng) : null,
        log.status || null,
        log.attendance_type || null,
        log.euclidean_distance !== undefined && log.euclidean_distance !== null ? Number(log.euclidean_distance) : null,
        log.path_foto_lokal || null,
        log.path_foto_storage || null,
        statusSyncTeks,
        statusSyncFoto,
        isSynced,
        log.created_at || null
      ]
    );
  } catch (err: any) {
    console.error('[SQLite Service sqliteSaveAttendanceLog Error]:', err?.message || err, err?.stack || '');
  }
}

/**
 * Bulk save attendance logs to local SQLite.
 */
export async function sqliteBulkSaveAttendanceLogs(logs: any[]): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    const statements = logs.map(log => {
      const statusSyncTeks = log.status_sync_teks || (log.is_synced ? 'done' : 'pending');
      const statusSyncFoto = log.status_sync_foto || (log.is_synced ? 'done' : 'pending');
      const isSynced = (statusSyncTeks === 'done' && statusSyncFoto === 'done') ? 1 : (log.is_synced ? 1 : 0);

      return {
        statement: `INSERT OR REPLACE INTO local_attendance_logs 
          (id, employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at)
          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
        values: [
          String(log.id),
          String(log.employee_id),
          log.nik || null,
          log.name || null,
          log.department || null,
          log.afdeling || null,
          log.kebun || log.nama_kebun || null,
          log.timestamp || null,
          log.location || null,
          log.lat !== undefined && log.lat !== null ? Number(log.lat) : null,
          log.lng !== undefined && log.lng !== null ? Number(log.lng) : null,
          log.status || null,
          log.attendance_type || null,
          log.euclidean_distance !== undefined && log.euclidean_distance !== null ? Number(log.euclidean_distance) : null,
          log.path_foto_lokal || null,
          log.path_foto_storage || null,
          statusSyncTeks,
          statusSyncFoto,
          isSynced,
          log.created_at || null
        ]
      };
    });
    if (statements.length > 0) {
      await dbConnection!.executeSet(statements);
    }
    console.log(`[SQLite Service] Bulk saved ${logs.length} attendance logs to local SQLite`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteBulkSaveAttendanceLogs Error]:', err?.message || err, err?.stack || '');
  }
}

/**
 * Update sync status for text and photo for a specific log ID.
 */
export async function sqliteUpdateSyncStatus(
  id: number | string,
  textStatus?: string | null,
  photoStatus?: string | null,
  syncNotes?: string | null
): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    const idStr = String(id);
    const cleanId = idStr.startsWith('offline_') ? idStr.replace('offline_', '') : idStr;
    const cleanIdInt = parseInt(cleanId, 10);

    const updates: string[] = [];
    const params: any[] = [];

    if (textStatus) {
      updates.push('status_sync_teks = ?');
      params.push(textStatus);
    }
    if (photoStatus) {
      updates.push('status_sync_foto = ?');
      params.push(photoStatus);
    }
    if (syncNotes !== undefined && syncNotes !== null) {
      updates.push('sync_notes = ?');
      params.push(syncNotes);
    }

    if (textStatus === 'done' && photoStatus === 'done') {
      updates.push('is_synced = 1');
    }

    if (updates.length === 0) return;

    const setClause = updates.join(', ');

    if (!isNaN(cleanIdInt)) {
      await dbConnection!.run(
        `UPDATE local_attendance_queue SET ${setClause} WHERE id = ?`,
        [...params, cleanIdInt]
      );
    }

    await dbConnection!.run(
      `UPDATE local_attendance_logs SET ${setClause} WHERE id = ? OR id = ?`,
      [...params, idStr, `offline_${cleanIdInt}`]
    );
    console.log(`[SQLite Service] Updated sync status for ID: ${id} | Teks: ${textStatus || '-'} | Foto: ${photoStatus || '-'} | Notes: ${syncNotes || '-'}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteUpdateSyncStatus Error]:', err?.message || err);
  }
}

/**
 * Get all attendance logs from local SQLite.
 */
export async function sqliteGetAttendanceLogs(): Promise<any[]> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return [];
  }
  try {
    const res = await dbConnection!.query(
      `SELECT * FROM local_attendance_logs ORDER BY timestamp DESC`
    );
    const rows = res.values || [];
    return rows.map((row: any) => ({
      id: row.id,
      employee_id: row.employee_id,
      nik: row.nik,
      name: row.name,
      department: row.department,
      afdeling: row.afdeling,
      kebun: row.kebun,
      nama_kebun: row.kebun,
      timestamp: row.timestamp,
      location: row.location,
      lat: row.lat,
      lng: row.lng,
      status: row.status,
      attendance_type: row.attendance_type,
      euclidean_distance: row.euclidean_distance,
      is_synced: row.is_synced === 1,
      created_at: row.created_at
    }));
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetAttendanceLogs Error]:', err?.message || err, err?.stack || '');
    return [];
  }
}

/**
 * Get today's attendance logs for a single employee from SQLite.
 */
export async function sqliteGetTodayAttendanceLogs(empId: number | string, dateStr: string): Promise<any[]> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return [];
  }
  try {
    const empIdStr = String(empId);
    const res = await dbConnection!.query(
      `SELECT * FROM local_attendance_logs 
       WHERE (CAST(employee_id AS TEXT) = ? OR employee_id = ?) AND substr(timestamp, 1, 10) = ? 
       ORDER BY timestamp ASC`,
      [empIdStr, empId, dateStr]
    );
    const rows = res.values || [];
    return rows.map((row: any) => ({
      id: row.id,
      employee_id: row.employee_id,
      nik: row.nik,
      name: row.name,
      department: row.department,
      afdeling: row.afdeling,
      kebun: row.kebun,
      nama_kebun: row.kebun,
      timestamp: row.timestamp,
      location: row.location,
      lat: row.lat,
      lng: row.lng,
      status: row.status,
      attendance_type: row.attendance_type,
      euclidean_distance: row.euclidean_distance,
      is_synced: row.is_synced === 1,
      created_at: row.created_at
    }));
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetTodayAttendanceLogs Error]:', err?.message || err, err?.stack || '');
    return [];
  }
}

/**
 * Delete a single local attendance log from SQLite.
 */
export async function sqliteDeleteAttendanceLog(id: string): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    await dbConnection!.run(
      `DELETE FROM local_attendance_logs WHERE id = ?`,
      [id]
    );
    console.log(`[SQLite Service] Deleted local attendance log #${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteDeleteAttendanceLog Error]:', err?.message || err, err?.stack || '');
  }
}

/**
 * Clear all local attendance logs from SQLite.
 */
export async function sqliteClearAttendanceLogs(): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    await dbConnection!.execute(`DELETE FROM local_attendance_logs`);
    console.log('[SQLite Service] Cleared local attendance logs table.');
  } catch (err: any) {
    console.error('[SQLite Service sqliteClearAttendanceLogs Error]:', err?.message || err, err?.stack || '');
  }
}


/**
 * Cache an admin user credentials locally for offline login
 */
export async function sqliteSaveAdmin(admin: {
  username: string;
  password_hash: string;
  role: string;
  region: string;
  kebun: string;
  name: string;
  nik?: string;
}): Promise<void> {
  if (!dbConnection) return;
  try {
    const sql = `
      INSERT OR REPLACE INTO local_admins (username, password_hash, role, region, kebun, name, nik, last_login)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?);
    `;
    const params = [
      admin.username,
      admin.password_hash,
      admin.role,
      admin.region,
      admin.kebun,
      admin.name,
      admin.nik || null,
      new Date().toISOString()
    ];
    await dbConnection.run(sql, params);
    console.log(`[SQLite Service] Cached offline credentials with NIK for admin: ${admin.username}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteSaveAdmin Error]:', err?.message || err);
  }
}

/**
 * Retrieve cached admin details for offline login verification
 */
export async function sqliteGetAdmin(username: string): Promise<any | null> {
  if (!dbConnection) return null;
  try {
    const sql = `SELECT * FROM local_admins WHERE username = ? LIMIT 1;`;
    const res = await dbConnection.query(sql, [username]);
    if (res.values && res.values.length > 0) {
      return res.values[0];
    }
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetAdmin Error]:', err?.message || err);
  }
  return null;
}

/**
 * Save an offline registered employee to pending sync queue
 */
export async function sqliteSavePendingEmployee(empData: any): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) {
      console.error('[SQLite Service sqliteSavePendingEmployee] dbConnection null even after wait. Skipping save.');
      return;
    }
  }
  try {
    const descJson = empData.descriptor_json ? (typeof empData.descriptor_json === 'string' ? empData.descriptor_json : JSON.stringify(empData.descriptor_json)) : null;
    const geomJson = empData.geometric_descriptor_json ? (typeof empData.geometric_descriptor_json === 'string' ? empData.geometric_descriptor_json : JSON.stringify(empData.geometric_descriptor_json)) : null;

    // 1. Simpan ke Antrean Sync (Outbox)
    const sql = `
      INSERT OR REPLACE INTO local_employee_sync_queue 
      (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, descriptor_json, geometric_descriptor_json, is_synced, created_at)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?);
    `;
    const params = [
      String(empData.id),
      empData.nik,
      empData.name,
      empData.department || empData.jabatan || null,
      empData.afdeling || null,
      empData.nama_kebun || null,
      empData.status_tk || null,
      empData.jabatan || null,
      empData.status_perkawinan || null,
      descJson,
      geomJson,
      empData.created_at || new Date().toISOString()
    ];
    await dbConnection!.run(sql, params);

    // 2. Simpan juga ke Cache Karyawan agar langsung muncul di UI dengan status lengkap (konsisten dengan Web/IndexedDB)
    const cacheSql = `
      INSERT OR REPLACE INTO local_employees 
      (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
    `;
    const cacheParams = [
      String(empData.id),
      empData.nik,
      empData.name,
      empData.department || empData.jabatan || null,
      empData.afdeling || null,
      empData.nama_kebun || empData.kebun || null,
      empData.status_tk || null,
      empData.jabatan || null,
      empData.status_perkawinan || null,
      empData.has_master_biometric ? 1 : 0,
      empData.region || null,
      0
    ];
    await dbConnection!.run(cacheSql, cacheParams);

    // 3. Simpan Vektor Wajah ke local_master_descriptors agar bisa langsung dipakai absen offline
    if (descJson || geomJson) {
      const descSql = `
        INSERT OR REPLACE INTO local_master_descriptors 
        (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
        VALUES (?, ?, ?, ?)
      `;
      const descParams = [
        String(empData.id),
        descJson,
        geomJson,
        new Date().toISOString()
      ];
      await dbConnection!.run(descSql, descParams);
    }

    console.log(`[SQLite Service] Saved pending offline employee: ${empData.name} (${empData.id}) into queue and cache.`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteSavePendingEmployee Error]:', err?.message || err);
  }
}

/**
 * Retrieve all unsynced pending employees
 */
export async function sqliteGetPendingEmployees(): Promise<any[]> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return [];
  }
  try {
    const res = await dbConnection!.query(`SELECT * FROM local_employee_sync_queue WHERE is_synced = 0`);
    return res.values || [];
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetPendingEmployees Error]:', err?.message || err);
    return [];
  }
}

/**
 * Remove a synced employee from pending queue
 */
export async function sqliteRemovePendingEmployee(id: string | number): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    await dbConnection!.run(`DELETE FROM local_employee_sync_queue WHERE id = ?`, [String(id)]);
    console.log(`[SQLite Service] Removed pending employee from queue: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteRemovePendingEmployee Error]:', err?.message || err);
  }
}

/**
 * Update employee_id in pending attendance queue when temp employee ID is replaced by real ID from Supabase
 */
export async function sqliteUpdatePendingAttendanceEmployeeId(oldTempEmpId: string | number, newRealEmpId: string | number): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    await dbConnection!.run(
      `UPDATE local_attendance_queue SET employee_id = ? WHERE employee_id = ?`,
      [String(newRealEmpId), String(oldTempEmpId)]
    );
    console.log(`[SQLite Service] Updated pending attendance employee_id from ${oldTempEmpId} to ${newRealEmpId}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteUpdatePendingAttendanceEmployeeId Error]:', err?.message || err);
  }
}

/**
 * Attendance Requests (Offline HQ Admin Operations)
 */
export async function sqliteSaveAttendanceRequest(req: any): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    const oldValJson = req.old_value ? JSON.stringify(req.old_value) : null;
    const newValJson = req.new_value ? JSON.stringify(req.new_value) : null;
    const isSyncedInt = req.is_synced ? 1 : 0;

    const sql = `
      INSERT OR REPLACE INTO local_attendance_requests 
      (id, request_type, log_id, nik, name, nama_kebun, requested_by, requested_at, status, old_value, new_value, is_synced)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    `;
    const params = [
      String(req.id),
      req.request_type || '',
      String(req.log_id || ''),
      req.nik || null,
      req.name || null,
      req.nama_kebun || null,
      req.requested_by || '',
      req.requested_at || new Date().toISOString(),
      req.status || 'PENDING',
      oldValJson,
      newValJson,
      isSyncedInt
    ];

    await dbConnection!.run(sql, params);
    console.log(`[SQLite Service] Saved attendance request: ${req.id} (Type: ${req.request_type})`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteSaveAttendanceRequest Error]:', err?.message || err);
  }
}

export async function sqliteGetAttendanceRequests(): Promise<any[]> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return [];
  }
  try {
    const res = await dbConnection!.query(`SELECT * FROM local_attendance_requests`);
    const rows = res.values || [];
    return rows.map((r: any) => ({
      ...r,
      old_value: r.old_value ? JSON.parse(r.old_value) : null,
      new_value: r.new_value ? JSON.parse(r.new_value) : null,
      is_synced: r.is_synced === 1 || r.is_synced === true
    }));
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetAttendanceRequests Error]:', err?.message || err);
    return [];
  }
}

export async function sqliteDeleteAttendanceRequest(id: string): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    await dbConnection!.run(`DELETE FROM local_attendance_requests WHERE id = ?`, [String(id)]);
    console.log(`[SQLite Service] Deleted attendance request: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteDeleteAttendanceRequest Error]:', err?.message || err);
  }
}

export async function sqliteClearAttendanceRequests(): Promise<void> {
  if (!dbConnection) {
    const ready = await waitForConnection();
    if (!ready) return;
  }
  try {
    await dbConnection!.execute(`DELETE FROM local_attendance_requests`);
    console.log('[SQLite Service] Cleared all attendance requests.');
  } catch (err: any) {
    console.error('[SQLite Service sqliteClearAttendanceRequests Error]:', err?.message || err);
  }
}

