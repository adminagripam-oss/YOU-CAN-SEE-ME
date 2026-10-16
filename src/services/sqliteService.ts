import { Capacitor } from '@capacitor/core';
import { SQLiteConnection, SQLiteDBConnection, CapacitorSQLite } from '@capacitor-community/sqlite';
// @ts-ignore
import { vectorChecksum, BIOMETRIC_CONFIG, toVectorArray } from '../biometrics';
// @ts-ignore
import { CURRENT_APP_VERSION } from '../config/version';

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
async function waitForConnection(): Promise<SQLiteDBConnection | null> {
  if (!Capacitor.isNativePlatform()) return null; // web — SQLite not used
  if (dbConnection) return dbConnection;
  try {
    await Promise.race([
      _dbReadyPromise,
      new Promise((_, reject) => setTimeout(() => reject(new Error('Timeout waiting for SQLite init')), 3000))
    ]);
    return dbConnection;
  } catch (err) {
    console.error('[SQLite] waitForConnection timeout or error:', err);
    return null;
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

    // Sinkronisasi koneksi SQLite yang terputus akibat WebView Reload (OTA Update)
    try {
      await sqlite.checkConnectionsConsistency().catch(() => console.warn('[SQLite] Consistency check warning'));
    } catch (e) {
      console.warn('[SQLite] checkConnectionsConsistency err:', e);
    }

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
        is_synced INTEGER DEFAULT 1,
        syncStatus TEXT DEFAULT NULL
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
        kebun TEXT,
        durasi INTEGER
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
        kebun TEXT,
        durasi INTEGER,
        syncStatus TEXT DEFAULT NULL
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
        has_master_biometric INTEGER DEFAULT 0,
        region TEXT,
        descriptor_json TEXT,
        geometric_descriptor_json TEXT,
        template_version INTEGER,
        is_synced INTEGER DEFAULT 0,
        sync_status TEXT DEFAULT 'pending',
        processing_run_id TEXT,
        processing_started_at TEXT,
        processing_heartbeat_at TEXT,
        core_done INTEGER DEFAULT 0,
        descriptor_done INTEGER DEFAULT 0,
        photo_done INTEGER DEFAULT 0,
        server_employee_id TEXT,
        created_at TEXT,
        last_error_code TEXT,
        last_error_message TEXT,
        last_error_hint TEXT,
        last_error_step TEXT,
        attempt_count INTEGER DEFAULT 0,
        last_attempt_at TEXT
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

      CREATE TABLE IF NOT EXISTS local_employee_delete_queue (
        id TEXT PRIMARY KEY
      );

      CREATE TABLE IF NOT EXISTS local_scan_attempts_queue (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        employee_id TEXT,
        nik TEXT,
        attempt_type TEXT NOT NULL,
        outcome TEXT NOT NULL,
        median_cosine REAL,
        min_cosine REAL,
        max_cosine REAL,
        frames_used INTEGER,
        threshold_used REAL,
        facing_mode TEXT,
        canvas_w INTEGER,
        canvas_h INTEGER,
        brightness REAL,
        master_checksum TEXT,
        master_updated_at TEXT,
        template_version INTEGER,
        vector_source TEXT,
        app_version TEXT,
        platform TEXT,
        created_at TEXT,
        is_synced INTEGER DEFAULT 0
      );

      CREATE TABLE IF NOT EXISTS local_employee_face_thumbs (
        employee_id TEXT PRIMARY KEY,
        nik TEXT,
        thumb_base64 TEXT,
        captured_at TEXT,
        photo_version INTEGER DEFAULT 1,
        photo_checksum TEXT,
        updated_at TEXT,
        is_synced INTEGER DEFAULT 0
      );

      CREATE TABLE IF NOT EXISTS vector_audit_log (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        employee_id TEXT NOT NULL,
        nik TEXT,
        event_type TEXT NOT NULL,
        old_checksum TEXT,
        new_checksum TEXT,
        old_version INTEGER,
        new_version INTEGER,
        source TEXT NOT NULL,
        details TEXT,
        created_at TEXT NOT NULL
      );

      CREATE TABLE IF NOT EXISTS vector_backup_local (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        employee_id TEXT NOT NULL,
        nik TEXT,
        descriptor_json TEXT NOT NULL,
        geometric_descriptor_json TEXT,
        template_version INTEGER,
        vector_checksum TEXT,
        backup_reason TEXT NOT NULL,
        created_at TEXT NOT NULL
      );

      CREATE INDEX IF NOT EXISTS idx_val_emp ON vector_audit_log(employee_id);
      CREATE INDEX IF NOT EXISTS idx_vbl_emp ON vector_backup_local(employee_id);
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

    // Missing column migrations
    try {
      const aqInfo = await dbConnection.query(`PRAGMA table_info(local_attendance_queue);`);
      const hasDurasi1 = aqInfo.values?.some((c: any) => c.name === 'durasi');
      if (!hasDurasi1) {
        console.log('[SQLite Service] Adding missing durasi column to local_attendance_queue...');
        await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN durasi INTEGER;`);
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_attendance_queue add durasi warning:', e);
    }

    try {
      const alInfo = await dbConnection.query(`PRAGMA table_info(local_attendance_logs);`);
      const hasDurasi2 = alInfo.values?.some((c: any) => c.name === 'durasi');
      if (!hasDurasi2) {
        console.log('[SQLite Service] Adding missing durasi column to local_attendance_logs...');
        await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN durasi INTEGER;`);
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_attendance_logs add durasi warning:', e);
    }

    try {
      const eqInfo = await dbConnection.query(`PRAGMA table_info(local_employee_sync_queue);`);
      const cols = new Set(eqInfo.values?.map((c: any) => c.name) || []);
      if (!cols.has('has_master_biometric')) {
        console.log('[SQLite Service] Adding missing has_master_biometric column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN has_master_biometric INTEGER DEFAULT 0;`);
      }
      if (!cols.has('region')) {
        console.log('[SQLite Service] Adding missing region column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN region TEXT;`);
      }
      if (!cols.has('last_error_code')) {
        console.log('[SQLite Service] Adding missing last_error_code column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN last_error_code TEXT;`);
      }
      if (!cols.has('last_error_message')) {
        console.log('[SQLite Service] Adding missing last_error_message column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN last_error_message TEXT;`);
      }
      if (!cols.has('last_error_hint')) {
        console.log('[SQLite Service] Adding missing last_error_hint column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN last_error_hint TEXT;`);
      }
      if (!cols.has('last_error_step')) {
        console.log('[SQLite Service] Adding missing last_error_step column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN last_error_step TEXT;`);
      }
      if (!cols.has('attempt_count')) {
        console.log('[SQLite Service] Adding missing attempt_count column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN attempt_count INTEGER DEFAULT 0;`);
      }
      if (!cols.has('last_attempt_at')) {
        console.log('[SQLite Service] Adding missing last_attempt_at column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN last_attempt_at TEXT;`);
      }
      if (!cols.has('sync_status')) {
        console.log('[SQLite Service] Adding missing sync_status column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN sync_status TEXT DEFAULT 'pending';`);
      }
      if (!cols.has('processing_run_id')) {
        console.log('[SQLite Service] Adding missing processing_run_id column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN processing_run_id TEXT;`);
      }
      if (!cols.has('processing_started_at')) {
        console.log('[SQLite Service] Adding missing processing_started_at column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN processing_started_at TEXT;`);
      }
      if (!cols.has('processing_heartbeat_at')) {
        console.log('[SQLite Service] Adding missing processing_heartbeat_at column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN processing_heartbeat_at TEXT;`);
      }
      if (!cols.has('core_done')) {
        console.log('[SQLite Service] Adding missing core_done column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN core_done INTEGER DEFAULT 0;`);
      }
      if (!cols.has('descriptor_done')) {
        console.log('[SQLite Service] Adding missing descriptor_done column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN descriptor_done INTEGER DEFAULT 0;`);
      }
      if (!cols.has('photo_done')) {
        console.log('[SQLite Service] Adding missing photo_done column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN photo_done INTEGER DEFAULT 0;`);
      }
      if (!cols.has('server_employee_id')) {
        console.log('[SQLite Service] Adding missing server_employee_id column to local_employee_sync_queue...');
        await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN server_employee_id TEXT;`);
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_employee_sync_queue add columns warning:', e);
    }

    try {
      const thumbInfo = await dbConnection.query(`PRAGMA table_info(local_employee_face_thumbs);`);
      const cols = new Set((thumbInfo.values || []).map((c: any) => c.name));
      if (!cols.has('nik')) {
        console.log('[SQLite Service] Adding missing nik column to local_employee_face_thumbs...');
        await dbConnection.execute(`ALTER TABLE local_employee_face_thumbs ADD COLUMN nik TEXT;`);
      }
    } catch (e) {
      console.warn('[SQLite Service] Migration local_employee_face_thumbs add nik warning:', e);
    }

    try {
      const aqInfo = await dbConnection.query(`PRAGMA table_info(local_attendance_queue);`);
      const empIdCol = aqInfo.values?.find((c: any) => c.name === 'employee_id');
      if (empIdCol && empIdCol.type && empIdCol.type.toUpperCase().includes('INT')) {
        console.log('[SQLite Service] Migrating local_attendance_queue employee_id column from INTEGER to TEXT...');
        const resQueue = await dbConnection.query(`SELECT COUNT(*) as count FROM local_attendance_queue`);
        const countQueue = resQueue?.values?.[0]?.count || 0;

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
        `);

        const resQueueV2 = await dbConnection.query(`SELECT COUNT(*) as count FROM local_attendance_queue_v2`);
        const countQueueV2 = resQueueV2?.values?.[0]?.count || 0;

        if (countQueueV2 >= countQueue) {
          await dbConnection.execute(`
            DROP TABLE IF EXISTS local_attendance_queue_legacy;
            ALTER TABLE local_attendance_queue RENAME TO local_attendance_queue_legacy;
            ALTER TABLE local_attendance_queue_v2 RENAME TO local_attendance_queue;
          `);
        } else {
          console.error('[SQLite Service] Migration failed row count check for local_attendance_queue.');
        }
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
        const resLogs = await dbConnection.query(`SELECT COUNT(*) as count FROM local_attendance_logs`);
        const countLogs = resLogs?.values?.[0]?.count || 0;

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
        `);

        const resLogsV2 = await dbConnection.query(`SELECT COUNT(*) as count FROM local_attendance_logs_v2`);
        const countLogsV2 = resLogsV2?.values?.[0]?.count || 0;

        if (countLogsV2 >= countLogs) {
          await dbConnection.execute(`
            DROP TABLE IF EXISTS local_attendance_logs_legacy;
            ALTER TABLE local_attendance_logs RENAME TO local_attendance_logs_legacy;
            ALTER TABLE local_attendance_logs_v2 RENAME TO local_attendance_logs;
          `);
        } else {
          console.error('[SQLite Service] Migration failed row count check for local_attendance_logs.');
        }
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

    // Ensure all missing columns exist for local_attendance_queue due to v2 migration
    const missingCols = ['path_foto_lokal', 'path_foto_storage', 'status_sync_teks', 'status_sync_foto', 'durasi'];
    for (const col of missingCols) {
      try {
        await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN ${col} TEXT;`);
      } catch (e) {
        // Ignore if exists
      }
    }
    // Ensure all missing columns exist for local_attendance_logs due to v2 migration
    for (const col of missingCols) {
      try {
        await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN ${col} TEXT;`);
      } catch (e) {
        // Ignore if exists
      }
    }

    // (V3 Migration follows below if any)
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
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN path_foto_storage TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN status_sync_teks TEXT DEFAULT 'pending';`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN status_sync_foto TEXT DEFAULT 'pending';`);
    } catch (e) { }

    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN path_foto_lokal TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN path_foto_storage TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN status_sync_teks TEXT DEFAULT 'pending';`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN status_sync_foto TEXT DEFAULT 'pending';`);
    } catch (e) { }

    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN sync_notes TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN sync_notes TEXT;`);
    } catch (e) { }

    // Soft delete migrations
    try {
      await dbConnection.execute(`ALTER TABLE local_employees ADD COLUMN syncStatus TEXT DEFAULT NULL;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN syncStatus TEXT DEFAULT NULL;`);
    } catch (e) { }

    // Telemetry & Biometrics accuracy migrations
    const logMetricCols = ['match_cosine REAL', 'match_threshold REAL', 'template_version INTEGER', 'facing_mode TEXT'];
    for (const colDef of logMetricCols) {
      try {
        await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN ${colDef};`);
      } catch (e) { }
      try {
        await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN ${colDef};`);
      } catch (e) { }
    }
    try {
      await dbConnection.execute(`ALTER TABLE local_master_descriptors ADD COLUMN template_version INTEGER;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_master_descriptors ADD COLUMN vector_checksum TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN vector_checksum_used TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_logs ADD COLUMN vector_source TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN vector_checksum_used TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_attendance_queue ADD COLUMN vector_source TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_scan_attempts_queue ADD COLUMN vector_source TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_employee_face_thumbs ADD COLUMN photo_version INTEGER DEFAULT 1;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_employee_face_thumbs ADD COLUMN photo_checksum TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_employee_face_thumbs ADD COLUMN updated_at TEXT;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`ALTER TABLE local_employee_sync_queue ADD COLUMN template_version INTEGER;`);
    } catch (e) { }
    try {
      await dbConnection.execute(`
        CREATE TABLE IF NOT EXISTS vector_audit_log (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          employee_id TEXT NOT NULL,
          nik TEXT,
          event_type TEXT NOT NULL,
          old_checksum TEXT,
          new_checksum TEXT,
          old_version INTEGER,
          new_version INTEGER,
          source TEXT NOT NULL,
          details TEXT,
          created_at TEXT NOT NULL
        );
        CREATE TABLE IF NOT EXISTS vector_backup_local (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          employee_id TEXT NOT NULL,
          nik TEXT,
          descriptor_json TEXT NOT NULL,
          geometric_descriptor_json TEXT,
          template_version INTEGER,
          vector_checksum TEXT,
          backup_reason TEXT NOT NULL,
          created_at TEXT NOT NULL
        );
        CREATE INDEX IF NOT EXISTS idx_val_emp ON vector_audit_log(employee_id);
        CREATE INDEX IF NOT EXISTS idx_vbl_emp ON vector_backup_local(employee_id);
      `);
    } catch (e) { }

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
  const db = await waitForConnection();
  if (!db) {
    console.error('[SQLite Service sqliteCacheUserMasterVector] dbConnection null even after wait. Skipping cache.');
    return;
  }
  try {
    const empId = user.employee_id || user.id;
    if (!empId) return;
    const empIdStr = String(empId);

    let vector = user.descriptor_json || user.descriptor || user.face_vector || null;
    let vectorStr: string | null = null;
    let normArr: number[] | null = null;
    if (vector) {
      normArr = toVectorArray(vector);
      if (normArr && normArr.length === 1024) {
        vectorStr = JSON.stringify(normArr);
      }
    }

    const gfv = user.geometric_descriptor_json || null;
    const gfvStr = gfv ? JSON.stringify(gfv) : null;

    // 1. Cache to local_employees
    await db.run(
      `INSERT INTO local_employees (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
       ON CONFLICT(id) DO UPDATE SET 
         has_master_biometric = EXCLUDED.has_master_biometric,
         nik = COALESCE(NULLIF(EXCLUDED.nik, ''), local_employees.nik),
         name = COALESCE(NULLIF(EXCLUDED.name, ''), local_employees.name),
         department = COALESCE(NULLIF(EXCLUDED.department, ''), local_employees.department),
         afdeling = COALESCE(NULLIF(EXCLUDED.afdeling, ''), local_employees.afdeling),
         nama_kebun = COALESCE(NULLIF(EXCLUDED.nama_kebun, ''), local_employees.nama_kebun),
         status_tk = COALESCE(NULLIF(EXCLUDED.status_tk, ''), local_employees.status_tk),
         jabatan = COALESCE(NULLIF(EXCLUDED.jabatan, ''), local_employees.jabatan),
         status_perkawinan = COALESCE(NULLIF(EXCLUDED.status_perkawinan, ''), local_employees.status_perkawinan),
         region = COALESCE(NULLIF(EXCLUDED.region, ''), local_employees.region),
         is_synced = EXCLUDED.is_synced`,
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

    const templateVersion = (user.template_version !== undefined && user.template_version !== null && user.template_version !== '') ? Number(user.template_version) : null;
    const vectorChecksumVal = user.vector_checksum || (normArr ? vectorChecksum(normArr) : null);

    // 2. Cache to local_master_descriptors
    await db.run(
      `INSERT INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at, template_version, vector_checksum)
       VALUES (?, ?, ?, ?, ?, ?)
       ON CONFLICT(employee_id) DO UPDATE SET
         descriptor_json = COALESCE(NULLIF(EXCLUDED.descriptor_json, ''), local_master_descriptors.descriptor_json),
         geometric_descriptor_json = COALESCE(NULLIF(EXCLUDED.geometric_descriptor_json, ''), local_master_descriptors.geometric_descriptor_json),
         updated_at = EXCLUDED.updated_at,
         template_version = COALESCE(EXCLUDED.template_version, local_master_descriptors.template_version),
         vector_checksum = COALESCE(EXCLUDED.vector_checksum, local_master_descriptors.vector_checksum)`,
      [
        empIdStr,
        vectorStr,
        gfvStr,
        user.updated_at || new Date().toISOString(),
        templateVersion,
        vectorChecksumVal
      ]
    );
    console.log(`[SQLite Service] Cached master for ${user.name} | GFV: ${gfvStr ? 'YES' : 'NO'} | Ver: ${templateVersion} | CS: ${vectorChecksumVal ? 'YES' : 'NO'}`);
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
  const db = await waitForConnection();
  if (!db) return null;
  try {
    const empIdStr = String(employeeId);
    const res = await db.query(
      `SELECT md.*, e.nik, e.name, e.department, e.afdeling, e.nama_kebun, e.status_tk, e.jabatan, e.status_perkawinan
       FROM local_master_descriptors md
       LEFT JOIN local_employees e ON CAST(md.employee_id AS TEXT) = CAST(e.id AS TEXT)
       WHERE CAST(md.employee_id AS TEXT) = ? OR md.employee_id = ? OR CAST(e.nik AS TEXT) = ?`,
      [empIdStr, employeeId, empIdStr]
    );

    if (!res.values || res.values.length === 0 || !res.values[0].descriptor_json) {
      // Jika tidak ada di master_descriptors, coba cek apakah ini karyawan yang didaftarkan offline (belum di-sync)
      const qRes = await db.query(
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
            descriptor_json: toVectorArray(row.descriptor_json),
            face_vector: toVectorArray(row.descriptor_json),
            geometric_descriptor_json: row.geometric_descriptor_json ? (typeof row.geometric_descriptor_json === 'string' ? JSON.parse(row.geometric_descriptor_json) : row.geometric_descriptor_json) : null,
            updated_at: row.created_at,
            template_version: (row.template_version !== undefined && row.template_version !== null && row.template_version !== '') ? Number(row.template_version) : null,
            vector_checksum: row.vector_checksum || null
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
      descriptor_json: toVectorArray(row.descriptor_json),
      face_vector: toVectorArray(row.descriptor_json),
      geometric_descriptor_json: row.geometric_descriptor_json ? (typeof row.geometric_descriptor_json === 'string' ? JSON.parse(row.geometric_descriptor_json) : row.geometric_descriptor_json) : null,
      updated_at: row.updated_at,
      template_version: (row.template_version !== undefined && row.template_version !== null && row.template_version !== '') ? Number(row.template_version) : null,
      vector_checksum: row.vector_checksum || null
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
  const db = await waitForConnection();
  if (!db) throw new Error('Database SQLite tidak terhubung.');
  try {
    const createdAt = new Date().toISOString();
    const timestamp = logData.timestamp || createdAt;
    const statusSyncTeks = logData.status_sync_teks || 'pending';
    const statusSyncFoto = logData.status_sync_foto || 'pending';
    const isSynced = (statusSyncTeks === 'done' && statusSyncFoto === 'done') ? 1 : 0;

    const runRes = await db.run(
      `INSERT INTO local_attendance_queue (
        employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at, durasi, match_cosine, match_threshold, template_version, facing_mode, vector_checksum_used, vector_source
       ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        String(logData.employee_id),
        logData.nik || null,
        logData.name || null,
        logData.department || null,
        logData.afdeling || null,
        logData.kebun || null,
        timestamp,
        logData.location || 'HP Mobile (Offline)',
        logData.lat !== undefined && logData.lat !== null ? Number(logData.lat) : null,
        logData.lng !== undefined && logData.lng !== null ? Number(logData.lng) : null,
        logData.status || null,
        logData.attendance_type || 'CHECK_IN',
        logData.euclidean_distance !== undefined && logData.euclidean_distance !== null ? Number(logData.euclidean_distance) : null,
        logData.path_foto_lokal || null,
        logData.path_foto_storage || null,
        statusSyncTeks,
        statusSyncFoto,
        isSynced,
        createdAt,
        logData.durasi !== undefined && logData.durasi !== null ? Number(logData.durasi) : null,
        logData.match_cosine !== undefined && logData.match_cosine !== null ? Number(logData.match_cosine) : null,
        logData.match_threshold !== undefined && logData.match_threshold !== null ? Number(logData.match_threshold) : null,
        (logData.template_version !== undefined && logData.template_version !== null && logData.template_version !== '') ? Number(logData.template_version) : null,
        logData.facing_mode || null,
        logData.vector_checksum_used || logData.master_checksum || null,
        logData.vector_source || null
      ]
    );

    let id = (runRes as any).lastId ?? (runRes.changes as any)?.lastId;
    if (id === null || id === undefined) {
      // Rule: Never allow a fallback to null to prevent 'offline_null' silent overwrites
      id = 'temp_' + Date.now() + '_' + Math.floor(Math.random() * 1000);
    }

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
  const db = await waitForConnection();
  if (!db) return [];
  try {
    const res = await db.query(
      `SELECT * FROM local_attendance_queue WHERE is_synced = 0 AND (status_sync_teks IS NULL OR status_sync_teks != 'done')`
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
  const db = await waitForConnection();
  if (!db) return;
  try {
    const set = ids.map(id => ({
      statement: `DELETE FROM local_attendance_queue WHERE id = ?`,
      values: [id]
    }));
    await db.executeSet(set);
    console.log(`[SQLite Service] Successfully removed ${ids.length} synced logs from queue.`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteRemoveSyncedLogs Error]:', err?.message || err);
  }
}

/**
 * Membersihkan cache kehadiran sesi saat Logout.
 * Menghitung batas awal hari lokal (local midnight ISO) sehingga log absensi pagi (termasuk sebelum 07.00 WIB)
 * dan log yang belum tersinkron (is_synced = 0) tetap dipertahankan.
 */
export async function sqliteClearAll(): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    const d = new Date();
    const localMidnightISO = new Date(d.getFullYear(), d.getMonth(), d.getDate(), 0, 0, 0, 0).toISOString();
    const todayStr = `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`;

    // 1. Bersihkan today_attendance_cache tanggal selain hari ini
    await db.run(`DELETE FROM local_today_attendance_cache WHERE cached_date != ?`, [todayStr]);

    // 2. Bersihkan HANYA log lama (sebelum hari ini lokal) yang SUDAH TERSINKRON
    await db.run(
      `DELETE FROM local_attendance_logs 
       WHERE timestamp < ? 
         AND is_synced = 1 
         AND id NOT IN (SELECT id FROM local_attendance_queue)`,
      [localMidnightISO]
    );

    // 3. Bersihkan HANYA request yang sudah tersinkron
    await db.run(`DELETE FROM local_attendance_requests WHERE is_synced = 1`);

    console.log('[SQLite Service] Cleared past attendance caches for logout (preserved employee, biometric templates, and today/unsynced attendance logs).');
  } catch (err: any) {
    console.error('[SQLite Service sqliteClearAll Error]:', err?.message || err);
  }
}

/**
 * Menghapus log absensi tersinkronisasi yang dibuat sebelum batas waktu ISO tertentu.
 */
export async function sqliteDeleteAttendanceLogsBefore(beforeISO: string): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(
      `DELETE FROM local_attendance_logs 
       WHERE timestamp < ? 
         AND is_synced = 1 
         AND id NOT IN (SELECT id FROM local_attendance_queue)`,
      [beforeISO]
    );
  } catch (err: any) {
    console.error('[SQLite Service sqliteDeleteAttendanceLogsBefore Error]:', err?.message || err);
  }
}

/**
 * Menghapus permintaan kehadiran (attendance requests) yang sudah tersinkronisasi.
 */
export async function sqliteClearSyncedAttendanceRequests(): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(`DELETE FROM local_attendance_requests WHERE is_synced = 1`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteClearSyncedAttendanceRequests Error]:', err?.message || err);
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
        const templateVer = (emp.template_version !== undefined && emp.template_version !== null && emp.template_version !== '') ? Number(emp.template_version) : null;
        const normArr = descJson ? toVectorArray(descJson) : null;
        const cs = emp.vector_checksum || (normArr ? vectorChecksum(normArr) : null);

        set.push({
          statement: `INSERT INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at, template_version, vector_checksum)
                      VALUES (?, ?, ?, ?, ?, ?)
                      ON CONFLICT(employee_id) DO UPDATE SET
                        descriptor_json = COALESCE(NULLIF(EXCLUDED.descriptor_json, ''), local_master_descriptors.descriptor_json),
                        geometric_descriptor_json = COALESCE(NULLIF(EXCLUDED.geometric_descriptor_json, ''), local_master_descriptors.geometric_descriptor_json),
                        updated_at = EXCLUDED.updated_at,
                        template_version = COALESCE(EXCLUDED.template_version, local_master_descriptors.template_version),
                        vector_checksum = COALESCE(EXCLUDED.vector_checksum, local_master_descriptors.vector_checksum)`,
          values: [
            String(emp.id),
            descJson ? (typeof descJson === 'string' ? descJson : JSON.stringify(descJson)) : null,
            geoDescJson ? (typeof geoDescJson === 'string' ? geoDescJson : JSON.stringify(geoDescJson)) : null,
            emp.updated_at || new Date().toISOString(),
            templateVer,
            cs
          ]
        });
      } else if (!emp.has_master_biometric) {
        // PERLINDUNGAN: Jangan hapus jika karyawan sedang berada di antrean pending sync!
        set.push({
          statement: `DELETE FROM local_master_descriptors 
                      WHERE employee_id = ? 
                        AND employee_id NOT IN (SELECT id FROM local_employee_sync_queue)
                        AND employee_id NOT IN (SELECT COALESCE(server_employee_id, '') FROM local_employee_sync_queue)`,
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
      `SELECT md.employee_id, md.descriptor_json, md.vector_checksum, md.template_version, md.updated_at, e.nik, e.name
       FROM local_master_descriptors md
       LEFT JOIN local_employees e ON md.employee_id = e.id
       WHERE md.descriptor_json IS NOT NULL`
    );
    const rows = res.values || [];
    return rows.map(row => {
      const parsedDesc = toVectorArray(row.descriptor_json);
      const cs = row.vector_checksum || (parsedDesc ? vectorChecksum(parsedDesc) : null);
      return {
        employee_id: row.employee_id,
        nik: row.nik,
        name: row.name,
        descriptor_json: parsedDesc,
        vector_checksum: cs,
        template_version: (row.template_version !== undefined && row.template_version !== null && row.template_version !== '') ? Number(row.template_version) : null,
        updated_at: row.updated_at || null,
      };
    });
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetAllMasterVectors Error]:', err?.message || err);
    return [];
  }
}

/**
 * Hard Deletes local employee data and master biometric descriptors.
 */
export async function sqliteHardDeleteEmployeeBiometrics(employeeId: number | string): Promise<void> {
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
      },
      {
        statement: `DELETE FROM local_attendance_logs WHERE employee_id = ?`,
        values: [empIdStr]
      },
      {
        statement: `DELETE FROM local_attendance_queue WHERE employee_id = ?`,
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
  const db = await waitForConnection();
  if (!db) return;
  try {
    const statusSyncTeks = log.status_sync_teks || (log.is_synced ? 'done' : 'pending');
    const statusSyncFoto = log.status_sync_foto || (log.is_synced ? 'done' : 'pending');
    const isSynced = (statusSyncTeks === 'done' && statusSyncFoto === 'done') ? 1 : (log.is_synced ? 1 : 0);

    await db.run(
      `INSERT OR REPLACE INTO local_attendance_logs 
      (id, employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at, durasi, match_cosine, match_threshold, template_version, facing_mode)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
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
        log.created_at || null,
        log.durasi !== undefined && log.durasi !== null ? Number(log.durasi) : null,
        log.match_cosine !== undefined && log.match_cosine !== null ? Number(log.match_cosine) : null,
        log.match_threshold !== undefined && log.match_threshold !== null ? Number(log.match_threshold) : null,
        (log.template_version !== undefined && log.template_version !== null && log.template_version !== '') ? Number(log.template_version) : null,
        log.facing_mode || null
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
  const db = await waitForConnection();
  if (!db) return;
  try {
    const statements = logs.map(log => {
      const statusSyncTeks = log.status_sync_teks || (log.is_synced ? 'done' : 'pending');
      const statusSyncFoto = log.status_sync_foto || (log.is_synced ? 'done' : 'pending');
      const isSynced = (statusSyncTeks === 'done' && statusSyncFoto === 'done') ? 1 : (log.is_synced ? 1 : 0);

      return {
        statement: `INSERT OR REPLACE INTO local_attendance_logs 
          (id, employee_id, nik, name, department, afdeling, kebun, timestamp, location, lat, lng, status, attendance_type, euclidean_distance, path_foto_lokal, path_foto_storage, status_sync_teks, status_sync_foto, is_synced, created_at, durasi)
          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
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
          log.created_at || null,
          log.durasi !== undefined && log.durasi !== null ? Number(log.durasi) : null
        ]
      };
    });
    if (statements.length > 0) {
      await db.executeSet(statements);
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
  const db = await waitForConnection();
  if (!db) return;
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

    if (textStatus === 'done' || (textStatus === 'done' && photoStatus === 'done')) {
      // Set is_synced = 1 demi keamanan segera setelah teks berhasil diupload.
      // Ini memastikan fetchLogs dapat membersihkan ghost display-cache entry
      // (offline entry dengan temp employee_id) saat melakukan full-sync cleanup.
      updates.push('is_synced = 1');
    }

    if (updates.length === 0) return;

    const setClause = updates.join(', ');

    if (!isNaN(cleanIdInt)) {
      await db.run(
        `UPDATE local_attendance_queue SET ${setClause} WHERE id = ?`,
        [...params, cleanIdInt]
      );
    }

    await db.run(
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
  const db = await waitForConnection();
  if (!db) return [];
  try {
    const res = await db.query(
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
      path_foto_lokal: row.path_foto_lokal,
      path_foto_storage: row.path_foto_storage,
      status_sync_teks: row.status_sync_teks,
      status_sync_foto: row.status_sync_foto,
      is_synced: row.is_synced === 1,
      created_at: row.created_at,
      durasi: row.durasi
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
  const db = await waitForConnection();
  if (!db) return [];
  let combinedRows: any[] = [];
  try {
    const empIdStr = String(empId);

    // 1. Fetch from local_attendance_logs
    try {
      const resLogs = await db.query(
        `SELECT * FROM local_attendance_logs 
         WHERE (CAST(employee_id AS TEXT) = ? OR employee_id = ?)`,
        [empIdStr, empId]
      );
      if (resLogs.values) {
        combinedRows = combinedRows.concat(resLogs.values);
      }
    } catch (errLogs: any) {
      console.warn('[SQLite Service] Error querying local_attendance_logs:', errLogs?.message || errLogs);
    }

    // 2. Fetch from local_attendance_queue
    try {
      const resQueue = await db.query(
        `SELECT * FROM local_attendance_queue
         WHERE (CAST(employee_id AS TEXT) = ? OR employee_id = ?)`,
        [empIdStr, empId]
      );
      if (resQueue.values) {
        combinedRows = combinedRows.concat(resQueue.values);
      }
    } catch (errQueue: any) {
      console.warn('[SQLite Service] Error querying local_attendance_queue:', errQueue?.message || errQueue);
    }

    // 3. Filter by local timezone dateStr and Deduplicate
    const uniqueMap = new Map();
    combinedRows.forEach((row: any) => {
      if (!row.timestamp) return;

      // Convert UTC timestamp to local Date object
      const d = new Date(row.timestamp);

      // Extract local YYYY-MM-DD
      const year = d.getFullYear();
      const month = String(d.getMonth() + 1).padStart(2, '0');
      const day = String(d.getDate()).padStart(2, '0');
      const localDateStr = `${year}-${month}-${day}`;

      if (localDateStr === dateStr) {
        uniqueMap.set(row.timestamp, row);
      }
    });

    const uniqueFiltered = Array.from(uniqueMap.values());
    uniqueFiltered.sort((a: any, b: any) => new Date(a.timestamp).getTime() - new Date(b.timestamp).getTime());

    return uniqueFiltered.map((row: any) => ({
      id: row.id,
      employee_id: row.employee_id,
      nik: row.nik,
      name: row.name,
      department: row.department,
      afdeling: row.afdeling || null,
      kebun: row.kebun || null,
      nama_kebun: row.kebun || null,
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
 * Hard Delete a single local attendance log from SQLite.
 */
export async function sqliteHardDeleteAttendanceLog(id: string): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(
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
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.execute(`DELETE FROM local_attendance_logs`);
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
  const db = await waitForConnection();
  if (!db) {
    console.error('[SQLite Service sqliteSavePendingEmployee] dbConnection null even after wait. Skipping save.');
    return;
  }
  try {
    const descJson = empData.descriptor_json ? (typeof empData.descriptor_json === 'string' ? empData.descriptor_json : JSON.stringify(empData.descriptor_json)) : null;
    const geomJson = empData.geometric_descriptor_json ? (typeof empData.geometric_descriptor_json === 'string' ? empData.geometric_descriptor_json : JSON.stringify(empData.geometric_descriptor_json)) : null;

    // 1. Simpan ke Antrean Sync (Outbox)
    const sql = `
      INSERT INTO local_employee_sync_queue 
      (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, descriptor_json, geometric_descriptor_json, template_version, is_synced, sync_status, core_done, descriptor_done, photo_done, server_employee_id, created_at)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, ?, ?, ?)
      ON CONFLICT(id) DO UPDATE SET
         nik = COALESCE(NULLIF(EXCLUDED.nik, ''), local_employee_sync_queue.nik),
         name = COALESCE(NULLIF(EXCLUDED.name, ''), local_employee_sync_queue.name),
         department = COALESCE(NULLIF(EXCLUDED.department, ''), local_employee_sync_queue.department),
         afdeling = COALESCE(NULLIF(EXCLUDED.afdeling, ''), local_employee_sync_queue.afdeling),
         nama_kebun = COALESCE(NULLIF(EXCLUDED.nama_kebun, ''), local_employee_sync_queue.nama_kebun),
         status_tk = COALESCE(NULLIF(EXCLUDED.status_tk, ''), local_employee_sync_queue.status_tk),
         jabatan = COALESCE(NULLIF(EXCLUDED.jabatan, ''), local_employee_sync_queue.jabatan),
         status_perkawinan = COALESCE(NULLIF(EXCLUDED.status_perkawinan, ''), local_employee_sync_queue.status_perkawinan),
         region = COALESCE(NULLIF(EXCLUDED.region, ''), local_employee_sync_queue.region),
         descriptor_json = COALESCE(NULLIF(EXCLUDED.descriptor_json, ''), local_employee_sync_queue.descriptor_json),
         geometric_descriptor_json = COALESCE(NULLIF(EXCLUDED.geometric_descriptor_json, ''), local_employee_sync_queue.geometric_descriptor_json),
         template_version = COALESCE(EXCLUDED.template_version, local_employee_sync_queue.template_version),
         has_master_biometric = EXCLUDED.has_master_biometric,
         sync_status = COALESCE(EXCLUDED.sync_status, local_employee_sync_queue.sync_status),
         core_done = CASE WHEN EXCLUDED.core_done = 1 THEN 1 ELSE local_employee_sync_queue.core_done END,
         descriptor_done = CASE WHEN EXCLUDED.descriptor_done = 1 THEN 1 ELSE local_employee_sync_queue.descriptor_done END,
         photo_done = CASE WHEN EXCLUDED.photo_done = 1 THEN 1 ELSE local_employee_sync_queue.photo_done END,
         server_employee_id = COALESCE(EXCLUDED.server_employee_id, local_employee_sync_queue.server_employee_id),
         is_synced = 0
    `;
    const pendingTemplateVer = empData.template_version !== undefined && empData.template_version !== null ? Number(empData.template_version) : null;
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
      empData.has_master_biometric ? 1 : 0,
      empData.region || null,
      descJson,
      geomJson,
      pendingTemplateVer,
      empData.sync_status || 'pending',
      empData.core_done ? 1 : 0,
      empData.descriptor_done ? 1 : 0,
      empData.photo_done ? 1 : 0,
      empData.server_employee_id ? String(empData.server_employee_id) : null,
      empData.created_at || new Date().toISOString()
    ];
    await db.run(sql, params);

    // 2. Simpan juga ke Cache Karyawan agar langsung muncul di UI dengan status lengkap (konsisten dengan Web/IndexedDB)
    const cacheSql = `
      INSERT INTO local_employees 
      (id, nik, name, department, afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, has_master_biometric, region, is_synced)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
      ON CONFLICT(id) DO UPDATE SET
         nik = COALESCE(NULLIF(EXCLUDED.nik, ''), local_employees.nik),
         name = COALESCE(NULLIF(EXCLUDED.name, ''), local_employees.name),
         department = COALESCE(NULLIF(EXCLUDED.department, ''), local_employees.department),
         afdeling = COALESCE(NULLIF(EXCLUDED.afdeling, ''), local_employees.afdeling),
         nama_kebun = COALESCE(NULLIF(EXCLUDED.nama_kebun, ''), local_employees.nama_kebun),
         status_tk = COALESCE(NULLIF(EXCLUDED.status_tk, ''), local_employees.status_tk),
         jabatan = COALESCE(NULLIF(EXCLUDED.jabatan, ''), local_employees.jabatan),
         status_perkawinan = COALESCE(NULLIF(EXCLUDED.status_perkawinan, ''), local_employees.status_perkawinan),
         region = COALESCE(NULLIF(EXCLUDED.region, ''), local_employees.region),
         has_master_biometric = EXCLUDED.has_master_biometric,
         is_synced = EXCLUDED.is_synced
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
    await db.run(cacheSql, cacheParams);

    // 3. Simpan Vektor Wajah ke local_master_descriptors agar bisa langsung dipakai absen offline
    if (descJson || geomJson) {
      const parsedArr = descJson ? toVectorArray(descJson) : null;
      const cs = parsedArr ? vectorChecksum(parsedArr) : null;
      const descSql = `
        INSERT OR REPLACE INTO local_master_descriptors 
        (employee_id, descriptor_json, geometric_descriptor_json, updated_at, template_version, vector_checksum)
        VALUES (?, ?, ?, ?, ?, ?)
      `;
      const descParams = [
        String(empData.id),
        descJson,
        geomJson,
        new Date().toISOString(),
        pendingTemplateVer !== null ? pendingTemplateVer : 1,
        cs
      ];
      await db.run(descSql, descParams);
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
  const db = await waitForConnection();
  if (!db) return [];
  try {
    const res = await db.query(`SELECT * FROM local_employee_sync_queue WHERE is_synced = 0`);
    return res.values || [];
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetPendingEmployees Error]:', err?.message || err);
    return [];
  }
}

/**
 * Retrieve a single pending employee by ID
 */
export async function sqliteGetPendingEmployee(id: string | number): Promise<any | null> {
  const db = await waitForConnection();
  if (!db) return null;
  try {
    const res = await db.query(`SELECT * FROM local_employee_sync_queue WHERE id = ? LIMIT 1`, [String(id)]);
    return res.values && res.values.length > 0 ? res.values[0] : null;
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetPendingEmployee Error]:', err?.message || err);
    return null;
  }
}

/**
 * Remove a synced employee from pending queue
 */
export async function sqliteRemovePendingEmployee(id: string | number): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    const idStr = String(id);
    await db.run(
      `DELETE FROM local_employee_sync_queue WHERE id = ? OR CAST(id AS TEXT) = ?`,
      [idStr, idStr]
    );
    console.log(`[SQLite Service] Removed pending employee from queue: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteRemovePendingEmployee Error]:', err?.message || err);
  }
}

/**
 * Record failure details on a pending employee queue item
 */
export async function sqliteUpdatePendingEmployeeError(
  id: string | number,
  errorData: {
    last_error_code?: string | null;
    last_error_message?: string | null;
    last_error_hint?: string | null;
    last_error_step?: string | null;
    sync_status?: string | null;
  }
): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    const idStr = String(id);
    await db.run(
      `UPDATE local_employee_sync_queue
       SET last_error_code = ?,
           last_error_message = ?,
           last_error_hint = ?,
           last_error_step = ?,
           sync_status = ?,
           attempt_count = COALESCE(attempt_count, 0) + 1,
           last_attempt_at = ?
       WHERE id = ? OR CAST(id AS TEXT) = ?`,
      [
        errorData.last_error_code || null,
        errorData.last_error_message || null,
        errorData.last_error_hint || null,
        errorData.last_error_step || null,
        errorData.sync_status || 'failed',
        new Date().toISOString(),
        idStr,
        idStr
      ]
    );
  } catch (err: any) {
    console.error('[SQLite Service sqliteUpdatePendingEmployeeError Error]:', err?.message || err);
  }
}

/**
 * Mark a pending employee item as processing with a run lease
 */
export async function sqliteSetPendingEmployeeProcessing(id: string | number, runId: string): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    const idStr = String(id);
    const nowIso = new Date().toISOString();
    await db.run(
      `UPDATE local_employee_sync_queue
       SET sync_status = 'processing',
           processing_run_id = ?,
           processing_started_at = ?,
           processing_heartbeat_at = ?
       WHERE id = ? OR CAST(id AS TEXT) = ?`,
      [runId, nowIso, nowIso, idStr, idStr]
    );
  } catch (err: any) {
    console.error('[SQLite Service sqliteSetPendingEmployeeProcessing Error]:', err?.message || err);
  }
}

/**
 * Update the heartbeat of an active processing lease
 */
export async function sqliteHeartbeatPendingEmployee(id: string | number, runId: string): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    const idStr = String(id);
    const nowIso = new Date().toISOString();
    await db.run(
      `UPDATE local_employee_sync_queue
       SET processing_heartbeat_at = ?
       WHERE (id = ? OR CAST(id AS TEXT) = ?) AND processing_run_id = ?`,
      [nowIso, idStr, idStr, runId]
    );
  } catch (err: any) {
    console.error('[SQLite Service sqliteHeartbeatPendingEmployee Error]:', err?.message || err);
  }
}

/**
 * Update step progress flags (core_done, descriptor_done, photo_done, server_employee_id)
 */
export async function sqliteUpdatePendingEmployeeStepProgress(
  id: string | number,
  progress: {
    core_done?: boolean | number;
    descriptor_done?: boolean | number;
    photo_done?: boolean | number;
    server_employee_id?: string | number | null;
  }
): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    const idStr = String(id);
    const updates: string[] = [];
    const params: any[] = [];
    if (progress.core_done !== undefined) {
      updates.push('core_done = ?');
      params.push(progress.core_done ? 1 : 0);
    }
    if (progress.descriptor_done !== undefined) {
      updates.push('descriptor_done = ?');
      params.push(progress.descriptor_done ? 1 : 0);
    }
    if (progress.photo_done !== undefined) {
      updates.push('photo_done = ?');
      params.push(progress.photo_done ? 1 : 0);
    }
    if (progress.server_employee_id !== undefined) {
      updates.push('server_employee_id = ?');
      params.push(progress.server_employee_id ? String(progress.server_employee_id) : null);
    }
    if (updates.length === 0) return;
    params.push(idStr, idStr);
    await db.run(
      `UPDATE local_employee_sync_queue SET ${updates.join(', ')} WHERE id = ? OR CAST(id AS TEXT) = ?`,
      params
    );
  } catch (err: any) {
    console.error('[SQLite Service sqliteUpdatePendingEmployeeStepProgress Error]:', err?.message || err);
  }
}

/**
 * Instant & safe recovery for stuck processing employees (lease-based)
 */
export async function sqliteRecoverStuckProcessingEmployees(currentRunId: string): Promise<number> {
  const db = await waitForConnection();
  if (!db) return 0;
  try {
    const res = await db.query(
      `SELECT id, attempt_count, processing_run_id, processing_started_at, processing_heartbeat_at
       FROM local_employee_sync_queue
       WHERE is_synced = 0 AND sync_status = 'processing'`
    );
    const rows = res.values || [];
    if (rows.length === 0) return 0;

    let recoveredCount = 0;
    const now = Date.now();

    for (const row of rows) {
      let shouldRecover = false;
      const isOtherProcess = row.processing_run_id && row.processing_run_id !== currentRunId;
      if (isOtherProcess) {
        // Proses sebelumnya mati mendadak saat sync: pulihkan seketika
        shouldRecover = true;
      } else {
        // Proses aktif saat ini: periksa apakah heartbeat usang (> 2 menit) atau waktu mulai > 5 menit
        const hbTime = row.processing_heartbeat_at ? new Date(row.processing_heartbeat_at).getTime() : 0;
        const startTime = row.processing_started_at ? new Date(row.processing_started_at).getTime() : 0;
        const isHeartbeatStale = hbTime > 0 && (now - hbTime) > 120000;
        const isAbsoluteTimeout = startTime > 0 && (now - startTime) > 300000;
        if (isHeartbeatStale || isAbsoluteTimeout || !row.processing_run_id) {
          shouldRecover = true;
        }
      }

      if (shouldRecover) {
        const nextAttempts = (row.attempt_count || 0) + 1;
        const nextStatus = nextAttempts >= 5 ? 'needs_attention' : 'pending';
        await db.run(
          `UPDATE local_employee_sync_queue
           SET sync_status = ?,
               processing_run_id = NULL,
               processing_started_at = NULL,
               processing_heartbeat_at = NULL,
               last_error_code = 'STALE_PROCESSING',
               last_error_message = 'Sinkronisasi terputus sebelum selesai. Dipulihkan ke status ' || ?,
               last_error_step = 'lease_recovery',
               attempt_count = ?,
               last_attempt_at = ?
           WHERE id = ?`,
          [nextStatus, nextStatus, nextAttempts, new Date().toISOString(), String(row.id)]
        );
        recoveredCount++;
      }
    }

    if (recoveredCount > 0) {
      console.log(`[SQLite Service] Dipulihkan ${recoveredCount} antrean pendaftaran yang macet di status processing.`);
    }
    return recoveredCount;
  } catch (err: any) {
    console.error('[SQLite Service sqliteRecoverStuckProcessingEmployees Error]:', err?.message || err);
    return 0;
  }
}

/**
 * Update employee_id in pending attendance queue when temp employee ID is replaced by real ID from Supabase
 */
export async function sqliteUpdatePendingAttendanceEmployeeId(oldTempEmpId: string | number, newRealEmpId: string | number): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(
      `UPDATE local_attendance_queue SET employee_id = ? WHERE employee_id = ?`,
      [String(newRealEmpId), String(oldTempEmpId)]
    );
    // Also update the UI logs display table so that Deduplication works post-sync
    await db.run(
      `UPDATE local_attendance_logs SET employee_id = ? WHERE employee_id = ?`,
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
  const db = await waitForConnection();
  if (!db) return;
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

    await db.run(sql, params);
    console.log(`[SQLite Service] Saved attendance request: ${req.id} (Type: ${req.request_type})`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteSaveAttendanceRequest Error]:', err?.message || err);
  }
}

export async function sqliteGetAttendanceRequests(): Promise<any[]> {
  const db = await waitForConnection();
  if (!db) return [];
  try {
    const res = await db.query(`SELECT * FROM local_attendance_requests`);
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
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(`DELETE FROM local_attendance_requests WHERE id = ?`, [String(id)]);
    console.log(`[SQLite Service] Deleted attendance request: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteDeleteAttendanceRequest Error]:', err?.message || err);
  }
}

export async function sqliteClearAttendanceRequests(): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.execute(`DELETE FROM local_attendance_requests`);
    console.log('[SQLite Service] Cleared all attendance requests.');
  } catch (err: any) {
    console.error('[SQLite Service sqliteClearAttendanceRequests Error]:', err?.message || err);
  }
}

export async function sqliteQueueEmployeeDelete(id: string): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(`INSERT OR REPLACE INTO local_employee_delete_queue (id) VALUES (?)`, [String(id)]);
    console.log(`[SQLite Service] Queued employee delete for ID: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteQueueEmployeeDelete Error]:', err?.message || err);
  }
}

export async function sqliteGetEmployeeDeletes(): Promise<string[]> {
  const db = await waitForConnection();
  if (!db) return [];
  try {
    const res = await db.query(`SELECT id FROM local_employee_delete_queue`);
    return (res.values || []).map((row: any) => row.id);
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetEmployeeDeletes Error]:', err?.message || err);
    return [];
  }
}

export async function sqliteRemoveEmployeeDelete(id: string): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(`DELETE FROM local_employee_delete_queue WHERE id = ?`, [String(id)]);
    console.log(`[SQLite Service] Removed employee delete from queue: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteRemoveEmployeeDelete Error]:', err?.message || err);
  }
}

export async function sqliteSoftDeleteEmployee(id: string): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(`UPDATE local_employees SET syncStatus = 'PENDING_DELETE' WHERE id = ?`, [String(id)]);
    console.log(`[SQLite Service] Soft deleted employee: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteSoftDeleteEmployee Error]:', err?.message || err);
  }
}

export async function sqliteSoftDeleteAttendanceLog(id: string): Promise<void> {
  const db = await waitForConnection();
  if (!db) return;
  try {
    await db.run(`UPDATE local_attendance_logs SET syncStatus = 'PENDING_DELETE' WHERE id = ?`, [String(id)]);
    console.log(`[SQLite Service] Soft deleted attendance log: ${id}`);
  } catch (err: any) {
    console.error('[SQLite Service sqliteSoftDeleteAttendanceLog Error]:', err?.message || err);
  }
}

export async function sqliteQueueScanAttempt(attempt: any): Promise<number | null> {
  const db = await waitForConnection();
  if (!db) return null;
  try {
    const maxCos = attempt.max_cosine !== undefined ? attempt.max_cosine : (attempt.matched_cosine !== undefined ? attempt.matched_cosine : null);
    const frames = attempt.frames_used !== undefined ? attempt.frames_used : (attempt.frames_evaluated !== undefined ? attempt.frames_evaluated : 0);
    const thresh = attempt.threshold_used !== undefined ? attempt.threshold_used : BIOMETRIC_CONFIG.MATCH_COSINE_THRESHOLD;

    const res = await db.run(
      `INSERT INTO local_scan_attempts_queue (
        employee_id, nik, attempt_type, outcome, median_cosine, min_cosine, max_cosine,
        frames_used, threshold_used, facing_mode, canvas_w, canvas_h, brightness,
        master_checksum, master_updated_at, template_version, vector_source, app_version, platform, created_at, is_synced
      ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        attempt.employee_id ? String(attempt.employee_id) : null,
        attempt.nik || null,
        attempt.attempt_type || 'CHECK-IN',
        attempt.failure_reason || attempt.outcome || 'NOT_MATCHED',
        attempt.median_cosine !== undefined ? attempt.median_cosine : null,
        attempt.min_cosine !== undefined ? attempt.min_cosine : null,
        maxCos,
        frames,
        thresh,
        attempt.facing_mode || 'environment',
        attempt.canvas_w || null,
        attempt.canvas_h || null,
        attempt.brightness !== undefined ? attempt.brightness : null,
        attempt.master_checksum || null,
        attempt.master_updated_at || null,
        (attempt.template_version !== undefined && attempt.template_version !== null && attempt.template_version !== '') ? Number(attempt.template_version) : null,
        attempt.vector_source || null,
        attempt.app_version || CURRENT_APP_VERSION,
        attempt.platform || (Capacitor.isNativePlatform() ? 'android' : 'web'),
        attempt.created_at || new Date().toISOString(),
        0
      ]
    );
    return res.changes?.lastId || null;
  } catch (err: any) {
    console.error('[SQLite Service sqliteQueueScanAttempt Error]:', err?.message || err);
    return null;
  }
}

export async function sqliteGetUnsyncedScanAttempts(): Promise<any[]> {
  const db = await waitForConnection();
  if (!db) return [];
  try {
    const res = await db.query(`SELECT * FROM local_scan_attempts_queue WHERE is_synced = 0 ORDER BY id ASC LIMIT 100`);
    return res.values || [];
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetUnsyncedScanAttempts Error]:', err?.message || err);
    return [];
  }
}

export async function sqliteRemoveSyncedScanAttempts(ids: number[]): Promise<void> {
  const db = await waitForConnection();
  if (!db || !ids || ids.length === 0) return;
  try {
    const placeholders = ids.map(() => '?').join(',');
    await db.run(`DELETE FROM local_scan_attempts_queue WHERE id IN (${placeholders})`, ids);
  } catch (err: any) {
    console.error('[SQLite Service sqliteRemoveSyncedScanAttempts Error]:', err?.message || err);
  }
}

export async function sqliteGetRecentScanAttempts(employeeId?: string | number, limit: number = 50): Promise<any[]> {
  const db = await waitForConnection();
  if (!db) return [];
  try {
    if (employeeId) {
      const res = await db.query(
        `SELECT * FROM local_scan_attempts_queue WHERE employee_id = ? ORDER BY id DESC LIMIT ?`,
        [String(employeeId), limit]
      );
      return res.values || [];
    } else {
      const res = await db.query(
        `SELECT * FROM local_scan_attempts_queue ORDER BY id DESC LIMIT ?`,
        [limit]
      );
      return res.values || [];
    }
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetRecentScanAttempts Error]:', err?.message || err);
    return [];
  }
}

export async function sqliteSaveEmployeeFaceThumb(
  employeeId: string,
  thumbBase64: string,
  photoVersion: number = 1,
  photoChecksum: string | null = null,
  nik: string | null = null
): Promise<void> {
  const db = await waitForConnection();
  if (!db || !employeeId) return;
  try {
    const now = new Date().toISOString();
    await db.run(
      `INSERT INTO local_employee_face_thumbs (employee_id, nik, thumb_base64, captured_at, photo_version, photo_checksum, updated_at, is_synced)
       VALUES (?, ?, ?, ?, ?, ?, ?, 0)
       ON CONFLICT(employee_id) DO UPDATE SET
         nik = COALESCE(EXCLUDED.nik, local_employee_face_thumbs.nik),
         thumb_base64 = EXCLUDED.thumb_base64,
         captured_at = EXCLUDED.captured_at,
         photo_version = EXCLUDED.photo_version,
         photo_checksum = EXCLUDED.photo_checksum,
         updated_at = EXCLUDED.updated_at,
         is_synced = 0`,
      [String(employeeId), nik ? String(nik).trim() : null, thumbBase64, now, photoVersion, photoChecksum, now]
    );
  } catch (err: any) {
    console.error('[SQLite Service sqliteSaveEmployeeFaceThumb Error]:', err?.message || err);
  }
}

export async function sqliteSaveEmployeeFaceThumbsBatch(
  thumbs: Array<{
    employee_id: string | number;
    thumb_base64: string;
    photo_version?: number;
    photo_checksum?: string | null;
    captured_at?: string;
    updated_at?: string;
    is_synced?: number;
  }>
): Promise<void> {
  const db = await waitForConnection();
  if (!db || !thumbs || thumbs.length === 0) return;
  try {
    const now = new Date().toISOString();
    for (const t of thumbs) {
      if (!t.employee_id || !t.thumb_base64) continue;
      await db.run(
        `INSERT INTO local_employee_face_thumbs (employee_id, thumb_base64, captured_at, photo_version, photo_checksum, updated_at, is_synced)
         VALUES (?, ?, ?, ?, ?, ?, ?)
         ON CONFLICT(employee_id) DO UPDATE SET
           thumb_base64 = EXCLUDED.thumb_base64,
           captured_at = EXCLUDED.captured_at,
           photo_version = EXCLUDED.photo_version,
           photo_checksum = EXCLUDED.photo_checksum,
           updated_at = EXCLUDED.updated_at,
           is_synced = EXCLUDED.is_synced
         WHERE local_employee_face_thumbs.is_synced != 0`,
        [
          String(t.employee_id),
          t.thumb_base64,
          t.captured_at || now,
          t.photo_version || 1,
          t.photo_checksum || null,
          t.updated_at || now,
          t.is_synced !== undefined ? t.is_synced : 1
        ]
      );
    }
  } catch (err: any) {
    console.error('[SQLite Service sqliteSaveEmployeeFaceThumbsBatch Error]:', err?.message || err);
  }
}

export async function sqliteGetEmployeeFaceThumb(employeeId: string, nik: string | null = null): Promise<string | null> {
  const info = await sqliteGetEmployeeFaceThumbInfo(employeeId, nik);
  return info?.thumb_base64 || null;
}

export async function sqliteGetEmployeeFaceThumbInfo(employeeId: string, nik: string | null = null): Promise<any | null> {
  const db = await waitForConnection();
  if (!db || (!employeeId && !nik)) return null;
  try {
    const idStr = employeeId ? String(employeeId) : '';
    const nikStr = nik ? String(nik).trim() : '';
    let sql = `SELECT employee_id, nik, thumb_base64, captured_at, photo_version, photo_checksum, updated_at
               FROM local_employee_face_thumbs
               WHERE `;
    const params: string[] = [];
    if (idStr && nikStr) {
      sql += `(employee_id = ? OR (nik IS NOT NULL AND nik = ?)) LIMIT 1`;
      params.push(idStr, nikStr);
    } else if (idStr) {
      sql += `employee_id = ? LIMIT 1`;
      params.push(idStr);
    } else {
      sql += `nik = ? LIMIT 1`;
      params.push(nikStr);
    }

    const res = await db.query(sql, params);
    if (res.values && res.values.length > 0) {
      return res.values[0];
    }
    return null;
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetEmployeeFaceThumbInfo Error]:', err?.message || err);
    return null;
  }
}

export async function sqliteGetUnsyncedEmployeeFaceThumbs(): Promise<any[]> {
  const db = await waitForConnection();
  if (!db) return [];
  try {
    const res = await db.query(`SELECT * FROM local_employee_face_thumbs WHERE is_synced = 0 LIMIT 50`);
    return res.values || [];
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetUnsyncedEmployeeFaceThumbs Error]:', err?.message || err);
    return [];
  }
}

export async function sqliteMarkSyncedEmployeeFaceThumbs(employeeIds: string[]): Promise<void> {
  const db = await waitForConnection();
  if (!db || !employeeIds || employeeIds.length === 0) return;
  try {
    const placeholders = employeeIds.map(() => '?').join(',');
    await db.run(`UPDATE local_employee_face_thumbs SET is_synced = 1 WHERE employee_id IN (${placeholders})`, employeeIds);
  } catch (err: any) {
    console.error('[SQLite Service sqliteMarkSyncedEmployeeFaceThumbs Error]:', err?.message || err);
  }
}

/**
 * ── VECTOR AUDIT & BACKUP SERVICES ──────────────────────────────────────
 */

export async function sqliteLogVectorAudit(entry: {
  employee_id: string | number;
  nik?: string | null;
  event_type: string;
  old_checksum?: string | null;
  new_checksum?: string | null;
  old_version?: number | null;
  new_version?: number | null;
  source: string;
  details?: string | null;
  created_at?: string;
}): Promise<number | null> {
  const db = await waitForConnection();
  if (!db || !entry.employee_id) return null;
  try {
    const res = await db.run(
      `INSERT INTO vector_audit_log (
        employee_id, nik, event_type, old_checksum, new_checksum, old_version, new_version, source, details, created_at
      ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        String(entry.employee_id),
        entry.nik || null,
        entry.event_type,
        entry.old_checksum || null,
        entry.new_checksum || null,
        entry.old_version !== undefined && entry.old_version !== null ? Number(entry.old_version) : null,
        entry.new_version !== undefined && entry.new_version !== null ? Number(entry.new_version) : null,
        entry.source || 'unknown',
        entry.details || null,
        entry.created_at || new Date().toISOString()
      ]
    );
    return res.changes?.lastId || null;
  } catch (err: any) {
    console.error('[SQLite Service sqliteLogVectorAudit Error]:', err?.message || err);
    return null;
  }
}

export async function sqliteBackupVector(entry: {
  employee_id: string | number;
  nik?: string | null;
  descriptor_json: any;
  geometric_descriptor_json?: any;
  template_version?: number | null;
  vector_checksum?: string | null;
  backup_reason: string;
}): Promise<number | null> {
  const db = await waitForConnection();
  if (!db || !entry.employee_id || !entry.descriptor_json) return null;
  try {
    const descJson = typeof entry.descriptor_json === 'string' ? entry.descriptor_json : JSON.stringify(entry.descriptor_json);
    const geomJson = entry.geometric_descriptor_json ? (typeof entry.geometric_descriptor_json === 'string' ? entry.geometric_descriptor_json : JSON.stringify(entry.geometric_descriptor_json)) : null;
    const res = await db.run(
      `INSERT INTO vector_backup_local (
        employee_id, nik, descriptor_json, geometric_descriptor_json, template_version, vector_checksum, backup_reason, created_at
      ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)`,
      [
        String(entry.employee_id),
        entry.nik || null,
        descJson,
        geomJson,
        entry.template_version !== undefined && entry.template_version !== null ? Number(entry.template_version) : null,
        entry.vector_checksum || null,
        entry.backup_reason || 'MANUAL_BACKUP',
        new Date().toISOString()
      ]
    );
    return res.changes?.lastId || null;
  } catch (err: any) {
    console.error('[SQLite Service sqliteBackupVector Error]:', err?.message || err);
    return null;
  }
}

export async function sqliteGetVectorAuditLogs(employeeId?: string | number, limit: number = 50): Promise<any[]> {
  const db = await waitForConnection();
  if (!db) return [];
  try {
    if (employeeId) {
      const res = await db.query(
        `SELECT * FROM vector_audit_log WHERE employee_id = ? ORDER BY id DESC LIMIT ?`,
        [String(employeeId), limit]
      );
      return res.values || [];
    } else {
      const res = await db.query(
        `SELECT * FROM vector_audit_log ORDER BY id DESC LIMIT ?`,
        [limit]
      );
      return res.values || [];
    }
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetVectorAuditLogs Error]:', err?.message || err);
    return [];
  }
}

export async function sqliteGetVectorBackups(employeeId: string | number): Promise<any[]> {
  const db = await waitForConnection();
  if (!db || !employeeId) return [];
  try {
    const res = await db.query(
      `SELECT id, employee_id, nik, template_version, vector_checksum, backup_reason, created_at
       FROM vector_backup_local WHERE employee_id = ? ORDER BY id DESC LIMIT 20`,
      [String(employeeId)]
    );
    return res.values || [];
  } catch (err: any) {
    console.error('[SQLite Service sqliteGetVectorBackups Error]:', err?.message || err);
    return [];
  }
}

export async function sqliteRevertVectorBackup(backupId: number | string): Promise<boolean> {
  const db = await waitForConnection();
  if (!db || !backupId) return false;
  try {
    const bRes = await db.query(`SELECT * FROM vector_backup_local WHERE id = ? LIMIT 1`, [Number(backupId)]);
    if (!bRes.values || bRes.values.length === 0) return false;
    const backup = bRes.values[0];

    // Ambil info sekarang untuk audit
    const curRes = await db.query(`SELECT * FROM local_master_descriptors WHERE employee_id = ? LIMIT 1`, [String(backup.employee_id)]);
    const cur = curRes.values && curRes.values.length > 0 ? curRes.values[0] : null;

    // Pulihkan master descriptor
    await db.run(
      `INSERT INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at, template_version, vector_checksum)
       VALUES (?, ?, ?, ?, ?, ?)
       ON CONFLICT(employee_id) DO UPDATE SET
         descriptor_json = EXCLUDED.descriptor_json,
         geometric_descriptor_json = EXCLUDED.geometric_descriptor_json,
         updated_at = EXCLUDED.updated_at,
         template_version = EXCLUDED.template_version,
         vector_checksum = EXCLUDED.vector_checksum`,
      [
        String(backup.employee_id),
        backup.descriptor_json,
        backup.geometric_descriptor_json,
        new Date().toISOString(),
        backup.template_version,
        backup.vector_checksum
      ]
    );

    // Catat ke audit log
    await sqliteLogVectorAudit({
      employee_id: backup.employee_id,
      nik: backup.nik,
      event_type: 'REVERT_BACKUP',
      old_checksum: cur?.vector_checksum || null,
      new_checksum: backup.vector_checksum,
      old_version: cur?.template_version || null,
      new_version: backup.template_version,
      source: 'backup_manager',
      details: `Dipulihkan dari backup snapshot ID #${backupId} (${backup.backup_reason || 'MANUAL'})`
    });

    return true;
  } catch (err: any) {
    console.error('[SQLite Service sqliteRevertVectorBackup Error]:', err?.message || err);
    return false;
  }
}

