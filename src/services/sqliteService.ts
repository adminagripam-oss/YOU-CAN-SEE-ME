import { Capacitor } from '@capacitor/core';
import { SQLiteConnection, SQLiteDBConnection, CapacitorSQLite } from '@capacitor-community/sqlite';

const sqlite = new SQLiteConnection(CapacitorSQLite);
let dbConnection: SQLiteDBConnection | null = null;

/**
 * Initializes the SQLite Database connection and creates tables if they don't exist.
 */
export async function initSQLite(): Promise<void> {
  if (!Capacitor.isNativePlatform()) {
    console.log('[SQLite Service] Web platform detected. Skipping SQLite initialization.');
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
        id INTEGER PRIMARY KEY,
        nik TEXT UNIQUE NOT NULL,
        name TEXT NOT NULL,
        department TEXT,
        afdeling TEXT,
        nama_kebun TEXT,
        status_tk TEXT,
        jabatan TEXT,
        status_perkawinan TEXT,
        has_master_biometric INTEGER DEFAULT 0
      );

      CREATE TABLE IF NOT EXISTS local_master_descriptors (
        employee_id INTEGER PRIMARY KEY,
        descriptor_json TEXT,
        geometric_descriptor_json TEXT,
        updated_at TEXT
      );

      CREATE TABLE IF NOT EXISTS local_attendance_queue (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        employee_id INTEGER NOT NULL,
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
        created_at TEXT
      );

      CREATE TABLE IF NOT EXISTS local_today_attendance_cache (
        employee_id INTEGER PRIMARY KEY,
        has_checked_in INTEGER DEFAULT 0,
        has_checked_out INTEGER DEFAULT 0,
        checked_in INTEGER DEFAULT 0,
        check_in_time TEXT,
        check_out_time TEXT,
        cached_date TEXT
      );

      CREATE TABLE IF NOT EXISTS local_attendance_logs (
        id TEXT PRIMARY KEY,
        employee_id INTEGER NOT NULL,
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
        created_at TEXT
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
    `;

    await dbConnection.execute(ddl);

    // Alter tables to add 'afdeling' / 'region' if they don't exist (migrations)
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN afdeling TEXT;`);
      console.log('[SQLite Service] Migrated local_attendance_queue: added afdeling column');
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
      await dbConnection.execute(`ALTER TABLE local_employees ADD COLUMN region TEXT;`);
      console.log('[SQLite Service] Migrated local_employees: added region column');
    } catch (e) {
      // Column might already exist, ignore error
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_admins ADD COLUMN nik TEXT;`);
      console.log('[SQLite Service] Migrated local_admins: added nik column');
    } catch (e) {
      // Column might already exist, ignore error
    }

    console.log('[SQLite Service] SQLite tables verified and ready.');
  } catch (err: any) {
    console.error('[SQLite Service Init Error]:', err?.message || err);
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
  if (!dbConnection) return;
  try {
    const empId = user.employee_id || user.id;
    if (!empId) return;

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
      if (Array.isArray(parsed) && parsed.length === 1024) {
        vectorStr = JSON.stringify(parsed);
      }
    }

    const gfv = user.geometric_descriptor_json || null;
    const gfvStr = gfv ? JSON.stringify(gfv) : null;

    // 1. Cache to local_employees
    await dbConnection.run(
      `INSERT OR REPLACE INTO local_employees (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric)
       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        empId,
        user.nik,
        user.name,
        user.department || user.jabatan || null,
        user.afdeling || null,
        user.nama_kebun || null,
        user.status_tk || null,
        user.jabatan || null,
        user.status_perkawinan || null,
        vectorStr ? 1 : 0
      ]
    );

    // 2. Cache to local_master_descriptors
    await dbConnection.run(
      `INSERT OR REPLACE INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at)
       VALUES (?, ?, ?, ?)`,
      [
        empId,
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
export async function sqliteCacheGeometricVector(employeeId: number, gfv: any): Promise<void> {
  if (!dbConnection) return;
  try {
    if (!employeeId || !gfv) return;
    const gfvStr = JSON.stringify(gfv);
    await dbConnection.run(
      `UPDATE local_master_descriptors SET geometric_descriptor_json = ?, updated_at = ? WHERE employee_id = ?`,
      [gfvStr, new Date().toISOString(), employeeId]
    );
    console.log(`[SQLite Service] Cached geometric vector for employee: ${employeeId}`);
  } catch (err: any) {
    console.error('[SQLite Service cacheGeometricVector Error]:', err?.message || err);
  }
}

/**
 * Retrieves cached master vectors and employee info from SQLite.
 */
export async function sqliteGetCachedUserMasterVector(employeeId: number): Promise<any | null> {
  if (!dbConnection) return null;
  try {
    const res = await dbConnection.query(
      `SELECT md.*, e.nik, e.name, e.department, e.afdeling, e.nama_kebun, e.status_tk, e.jabatan, e.status_perkawinan
       FROM local_master_descriptors md
       LEFT JOIN local_employees e ON md.employee_id = e.id
       WHERE md.employee_id = ?`,
      [employeeId]
    );

    if (!res.values || res.values.length === 0) return null;
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
      descriptor_json: row.descriptor_json ? JSON.parse(row.descriptor_json) : null,
      face_vector: row.descriptor_json ? JSON.parse(row.descriptor_json) : null,
      geometric_descriptor_json: row.geometric_descriptor_json ? JSON.parse(row.geometric_descriptor_json) : null,
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
  if (!dbConnection) throw new Error('Database SQLite tidak terhubung.');
  try {
    const createdAt = new Date().toISOString();
    const timestamp = logData.timestamp || createdAt;
    
    await dbConnection.run(
      `INSERT INTO local_attendance_queue (
        employee_id, nik, name, department, afdeling, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at
       ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?)`,
      [
        logData.employee_id,
        logData.nik,
        logData.name,
        logData.department,
        logData.afdeling || null,
        timestamp,
        logData.location || 'HP Mobile (Offline)',
        logData.lat || null,
        logData.lng || null,
        logData.status,
        logData.attendance_type || 'CHECK_IN',
        logData.euclidean_distance,
        createdAt
      ]
    );

    const lastIdRes = await dbConnection.query('SELECT last_insert_rowid() as id');
    const id = lastIdRes.values?.[0]?.id || null;

    const queuedItem = {
      ...logData,
      id,
      timestamp,
      is_synced: false,
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
  if (!dbConnection) return [];
  try {
    const res = await dbConnection.query(
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
  if (!dbConnection || !ids || ids.length === 0) return;
  try {
    const set = ids.map(id => ({
      statement: `DELETE FROM local_attendance_queue WHERE id = ?`,
      values: [id]
    }));
    await dbConnection.executeSet(set);
    console.log(`[SQLite Service] Successfully removed ${ids.length} synced logs from queue.`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteRemoveSyncedLogs Error]:', err?.message || err);
  }
}

/**
 * Clear the local employees cache.
 */
export async function sqliteClearEmployeesCache(): Promise<void> {
  if (!dbConnection) return;
  try {
    await dbConnection.execute(`DELETE FROM local_employees`);
    console.log('[SQLite Service] Cleared employees cache table.');
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
    const set = empData.map(emp => ({
      statement: `INSERT OR REPLACE INTO local_employees (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric)
                  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      values: [
        emp.id,
        emp.nik,
        emp.name,
        emp.department || emp.jabatan || null,
        emp.afdeling || null,
        emp.nama_kebun || null,
        emp.status_tk || null,
        emp.jabatan || null,
        emp.status_perkawinan || null,
        emp.has_master_biometric ? 1 : 0
      ]
    }));
    await dbConnection.executeSet(set);
    console.log(`[SQLite Service] Bulk put ${empData.length} employees into cache.`);
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
    const res = await dbConnection.query(`SELECT * FROM local_employees`);
    const rows = res.values || [];
    return rows.map(row => ({
      ...row,
      has_master_biometric: row.has_master_biometric === 1
    }));
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
    return rows.map(row => ({
      employee_id: row.employee_id,
      nik: row.nik,
      name: row.name,
      descriptor_json: row.descriptor_json ? JSON.parse(row.descriptor_json) : null,
    }));
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetAllMasterVectors Error]:', err?.message || err);
    return [];
  }
}

/**
 * Deletes local employee data and master biometric descriptors.
 */
export async function sqliteDeleteEmployeeBiometrics(employeeId: number): Promise<void> {
  if (!dbConnection) return;
  try {
    const set = [
      {
        statement: `DELETE FROM local_master_descriptors WHERE employee_id = ?`,
        values: [employeeId]
      },
      {
        statement: `DELETE FROM local_employees WHERE id = ?`,
        values: [employeeId]
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
          parseInt(empId),
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
export async function sqliteGetTodayAttendance(empId: number, cachedDate: string): Promise<any | null> {
  if (!dbConnection) return null;
  try {
    const res = await dbConnection.query(
      `SELECT * FROM local_today_attendance_cache WHERE employee_id = ? AND cached_date = ?`,
      [empId, cachedDate]
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
  if (!dbConnection) return;
  try {
    await dbConnection.run(
      `INSERT OR REPLACE INTO local_attendance_logs 
      (id, employee_id, nik, name, department, afdeling, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        String(log.id),
        Number(log.employee_id),
        log.nik || null,
        log.name || null,
        log.department || null,
        log.afdeling || null,
        log.timestamp || null,
        log.location || null,
        log.lat !== undefined && log.lat !== null ? Number(log.lat) : null,
        log.lng !== undefined && log.lng !== null ? Number(log.lng) : null,
        log.status || null,
        log.attendance_type || null,
        log.euclidean_distance !== undefined && log.euclidean_distance !== null ? Number(log.euclidean_distance) : null,
        log.is_synced ? 1 : 0,
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
  if (!dbConnection) return;
  try {
    const statements = logs.map(log => ({
      statement: `INSERT OR REPLACE INTO local_attendance_logs 
        (id, employee_id, nik, name, department, afdeling, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, is_synced, created_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      values: [
        String(log.id),
        Number(log.employee_id),
        log.nik || null,
        log.name || null,
        log.department || null,
        log.afdeling || null,
        log.timestamp || null,
        log.location || null,
        log.lat !== undefined && log.lat !== null ? Number(log.lat) : null,
        log.lng !== undefined && log.lng !== null ? Number(log.lng) : null,
        log.status || null,
        log.attendance_type || null,
        log.euclidean_distance !== undefined && log.euclidean_distance !== null ? Number(log.euclidean_distance) : null,
        log.is_synced ? 1 : 0,
        log.created_at || null
      ]
    }));
    if (statements.length > 0) {
      await dbConnection.executeSet(statements);
    }
    console.log(`[SQLite Service] Bulk saved ${logs.length} attendance logs to local SQLite`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteBulkSaveAttendanceLogs Error]:', err?.message || err, err?.stack || '');
  }
}

/**
 * Get all attendance logs from local SQLite.
 */
export async function sqliteGetAttendanceLogs(): Promise<any[]> {
  if (!dbConnection) return [];
  try {
    const res = await dbConnection.query(
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
export async function sqliteGetTodayAttendanceLogs(empId: number, dateStr: string): Promise<any[]> {
  if (!dbConnection) return [];
  try {
    const res = await dbConnection.query(
      `SELECT * FROM local_attendance_logs 
       WHERE employee_id = ? AND substr(timestamp, 1, 10) = ? 
       ORDER BY timestamp ASC`,
      [empId, dateStr]
    );
    const rows = res.values || [];
    return rows.map((row: any) => ({
      id: row.id,
      employee_id: row.employee_id,
      nik: row.nik,
      name: row.name,
      department: row.department,
      afdeling: row.afdeling,
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
  if (!dbConnection) return;
  try {
    await dbConnection.run(
      `DELETE FROM local_attendance_logs WHERE id = ?`,
      [id]
    );
    console.log(`[SQLite Service] Deleted local attendance log ID: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteDeleteAttendanceLog Error]:', err?.message || err, err?.stack || '');
  }
}

/**
 * Clear all cached attendance logs from SQLite.
 */
export async function sqliteClearAttendanceLogs(): Promise<void> {
  if (!dbConnection) return;
  try {
    await dbConnection.run(`DELETE FROM local_attendance_logs`, []);
    console.log('[SQLite Service] Cleared all local attendance logs cache');
  } catch (err: any) {
    console.error('[SQLite Service sqliteClearAttendanceLogs Error]:', err?.message || err);
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


