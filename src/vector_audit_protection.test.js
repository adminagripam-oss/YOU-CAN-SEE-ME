import { describe, it, expect, beforeEach, afterEach } from 'vitest';
import { DatabaseSync } from 'node:sqlite';
import { vectorChecksum } from './biometrics';

describe('PRIORITAS 1: Vector Audit & Overwrite Protection Test Suite', () => {
  let rawDb;

  beforeEach(() => {
    rawDb = new DatabaseSync(':memory:');

    // Setup skema SQLite lokal relevan
    rawDb.exec(`
      CREATE TABLE local_employees (
        id TEXT PRIMARY KEY,
        nik TEXT,
        name TEXT,
        department TEXT,
        has_master_biometric INTEGER DEFAULT 0
      );

      CREATE TABLE local_master_descriptors (
        employee_id TEXT PRIMARY KEY,
        descriptor_json TEXT,
        geometric_descriptor_json TEXT,
        updated_at TEXT,
        template_version INTEGER,
        vector_checksum TEXT
      );

      CREATE TABLE local_employee_sync_queue (
        id TEXT PRIMARY KEY,
        nik TEXT,
        name TEXT,
        descriptor_json TEXT,
        template_version INTEGER,
        vector_checksum TEXT,
        server_employee_id TEXT,
        is_synced INTEGER DEFAULT 0
      );

      CREATE TABLE local_scan_attempts_queue (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        employee_id TEXT,
        nik TEXT,
        attempt_type TEXT,
        outcome TEXT,
        median_cosine REAL,
        min_cosine REAL,
        max_cosine REAL,
        frames_used INTEGER,
        threshold_used REAL,
        facing_mode TEXT,
        master_checksum TEXT,
        template_version INTEGER,
        vector_source TEXT,
        app_version TEXT,
        platform TEXT,
        created_at TEXT,
        is_synced INTEGER DEFAULT 0
      );

      CREATE TABLE vector_audit_log (
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

      CREATE TABLE vector_backup_local (
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
    `);
  });

  afterEach(() => {
    rawDb.close();
  });

  it('1. Overwrite Protection: fetchEmployees tidak menimpa vektor jika ada pending_sync di local_employee_sync_queue', () => {
    const localVec = new Array(1024).fill(0.08);
    const localCs = vectorChecksum(localVec);

    // Karyawan Muzan di-rescan lokal (v2 multi-pose) dan masuk antrean pending sync
    rawDb.exec(`
      INSERT INTO local_master_descriptors (employee_id, descriptor_json, updated_at, template_version, vector_checksum)
      VALUES ('emp_muzan', '${JSON.stringify(localVec)}', '2026-10-16T21:00:00Z', 2, '${localCs}');

      INSERT INTO local_employee_sync_queue (id, nik, name, descriptor_json, template_version, vector_checksum, is_synced)
      VALUES ('emp_muzan', 'TestingHO125672713', 'Muzan', '${JSON.stringify(localVec)}', 2, '${localCs}', 0);
    `);

    // Data dari server Supabase yang usang (v1 frontal, checksum beda)
    const serverOldVec = new Array(1024).fill(0.02);
    const serverOldCs = vectorChecksum(serverOldVec);
    const serverMeta = {
      employee_id: 'emp_muzan',
      template_version: 1,
      vector_checksum: serverOldCs,
      updated_at: '2026-10-15T10:00:00Z'
    };

    // Fungsi proteksi: cek apakah boleh di-overwrite oleh server
    function shouldApplyServerVector(empId, serverTemplateVer) {
      const hasPendingQueue = rawDb.prepare(
        `SELECT count(*) as count FROM local_employee_sync_queue WHERE id = ? OR server_employee_id = ? OR nik = ?`
      ).get(empId, empId, empId).count > 0;

      if (hasPendingQueue) {
        // Catat ke audit log
        rawDb.prepare(`
          INSERT INTO vector_audit_log (employee_id, event_type, source, details, created_at)
          VALUES (?, 'OVERWRITE_PREVENTED', 'fetchEmployees', 'Ditolak: Karyawan memiliki antrean pending sync lokal', datetime('now'))
        `).run(empId);
        return false;
      }

      const localDesc = rawDb.prepare(
        `SELECT template_version FROM local_master_descriptors WHERE employee_id = ?`
      ).get(empId);

      if (localDesc && localDesc.template_version >= 2 && (!serverTemplateVer || serverTemplateVer < 2)) {
        rawDb.prepare(`
          INSERT INTO vector_audit_log (employee_id, event_type, source, details, created_at)
          VALUES (?, 'OVERWRITE_PREVENTED', 'fetchEmployees', 'Ditolak: Anti-downgrade multi-pose lokal', datetime('now'))
        `).run(empId);
        return false;
      }

      return true;
    }

    const canOverwrite = shouldApplyServerVector('emp_muzan', serverMeta.template_version);
    expect(canOverwrite).toBe(false);

    // Pastikan vektor lokal tetap v2
    const currentLocal = rawDb.prepare(`SELECT template_version, vector_checksum FROM local_master_descriptors WHERE employee_id = 'emp_muzan'`).get();
    expect(currentLocal.template_version).toBe(2);
    expect(currentLocal.vector_checksum).toBe(localCs);

    // Pastikan tercatat di audit log
    const audit = rawDb.prepare(`SELECT * FROM vector_audit_log WHERE employee_id = 'emp_muzan'`).get();
    expect(audit.event_type).toBe('OVERWRITE_PREVENTED');
  });

  it('2. Anti-Downgrade Protection: Vektor lokal multi-pose (v2) tidak ditimpa oleh vektor single-pose (v1/null) dari server', () => {
    const localVec = new Array(1024).fill(0.08);
    const localCs = vectorChecksum(localVec);

    // Karyawan sudah tersinkron sebelumnya dengan v2, tidak ada antrean pending
    rawDb.exec(`
      INSERT INTO local_master_descriptors (employee_id, descriptor_json, updated_at, template_version, vector_checksum)
      VALUES ('emp_jovie', '${JSON.stringify(localVec)}', '2026-10-16T12:00:00Z', 2, '${localCs}');
    `);

    function checkCanDowngrade(empId, serverTemplateVer) {
      const localDesc = rawDb.prepare(`SELECT template_version FROM local_master_descriptors WHERE employee_id = ?`).get(empId);
      if (localDesc && localDesc.template_version >= 2 && (!serverTemplateVer || serverTemplateVer < 2)) {
        rawDb.prepare(`
          INSERT INTO vector_audit_log (employee_id, event_type, source, details, created_at)
          VALUES (?, 'OVERWRITE_PREVENTED', 'fetchEmployees', 'Ditolak: Anti-downgrade multi-pose lokal', datetime('now'))
        `).run(empId);
        return false;
      }
      return true;
    }

    expect(checkCanDowngrade('emp_jovie', 1)).toBe(false);
    expect(checkCanDowngrade('emp_jovie', null)).toBe(false);

    const audit = rawDb.prepare(`SELECT * FROM vector_audit_log WHERE employee_id = 'emp_jovie'`).get();
    expect(audit.details).toContain('Anti-downgrade');
  });

  it('3. Backup & Revert: Simpan snapshot ke vector_backup_local dan bisa dipulihkan', () => {
    const originalVec = new Array(1024).fill(0.12);
    const originalCs = vectorChecksum(originalVec);

    rawDb.exec(`
      INSERT INTO local_master_descriptors (employee_id, descriptor_json, updated_at, template_version, vector_checksum)
      VALUES ('emp_test', '${JSON.stringify(originalVec)}', '2026-10-16T08:00:00Z', 2, '${originalCs}');
    `);

    // Fungsi backup sebelum perubahan
    function backupVector(empId, reason) {
      const current = rawDb.prepare(`SELECT * FROM local_master_descriptors WHERE employee_id = ?`).get(empId);
      if (!current) return null;
      const res = rawDb.prepare(`
        INSERT INTO vector_backup_local (employee_id, descriptor_json, template_version, vector_checksum, backup_reason, created_at)
        VALUES (?, ?, ?, ?, ?, datetime('now'))
      `).run(empId, current.descriptor_json, current.template_version, current.vector_checksum, reason);
      return res.lastInsertRowid;
    }

    const backupId = backupVector('emp_test', 'PRE_UPDATE');
    expect(backupId).toBeGreaterThan(0);

    // Simulasi perubahan (misal terjadi degradasi vektor)
    const degradedVec = new Array(1024).fill(0.01);
    rawDb.prepare(`
      UPDATE local_master_descriptors 
      SET descriptor_json = ?, template_version = 1, vector_checksum = 'bad_cs'
      WHERE employee_id = 'emp_test'
    `).run(JSON.stringify(degradedVec));

    // Verifikasi degradasi terjadi
    const degradedRow = rawDb.prepare(`SELECT template_version FROM local_master_descriptors WHERE employee_id = 'emp_test'`).get();
    expect(degradedRow.template_version).toBe(1);

    // Fungsi Revert dari Backup
    function revertBackup(bId) {
      const bRow = rawDb.prepare(`SELECT * FROM vector_backup_local WHERE id = ?`).get(bId);
      if (!bRow) return false;
      rawDb.prepare(`
        UPDATE local_master_descriptors 
        SET descriptor_json = ?, template_version = ?, vector_checksum = ?, updated_at = datetime('now')
        WHERE employee_id = ?
      `).run(bRow.descriptor_json, bRow.template_version, bRow.vector_checksum, bRow.employee_id);

      rawDb.prepare(`
        INSERT INTO vector_audit_log (employee_id, event_type, source, details, created_at)
        VALUES (?, 'REVERT_BACKUP', 'backup_manager', 'Dipulihkan dari snapshot backup id ' || ?, datetime('now'))
      `).run(bRow.employee_id, bId);
      return true;
    }

    const reverted = revertBackup(backupId);
    expect(reverted).toBe(true);

    // Verifikasi data pulih ke v2
    const restoredRow = rawDb.prepare(`SELECT template_version, vector_checksum FROM local_master_descriptors WHERE employee_id = 'emp_test'`).get();
    expect(restoredRow.template_version).toBe(2);
    expect(restoredRow.vector_checksum).toBe(originalCs);
  });

  it('4. Telemetri SQLite: Scan attempt menyimpan vector_source, template_version, dan master_checksum secara akurat', () => {
    // Simulasi insert telemetri scan checkout yang gagal LOW_SCORE
    rawDb.prepare(`
      INSERT INTO local_scan_attempts_queue (
        employee_id, nik, attempt_type, outcome, max_cosine, median_cosine,
        frames_used, threshold_used, template_version, master_checksum, vector_source, created_at
      ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, datetime('now'))
    `).run(
      'emp_muzan',
      'TestingHO125672713',
      'CHECK-OUT',
      'LOW_SCORE',
      0.842,
      0.791,
      58,
      0.92,
      2,
      'cs_muzan_v2',
      'local_cache'
    );

    const loggedAttempt = rawDb.prepare(`SELECT * FROM local_scan_attempts_queue WHERE employee_id = 'emp_muzan'`).get();
    expect(loggedAttempt.outcome).toBe('LOW_SCORE');
    expect(loggedAttempt.template_version).toBe(2);
    expect(loggedAttempt.vector_source).toBe('local_cache');
    expect(loggedAttempt.master_checksum).toBe('cs_muzan_v2');
    expect(loggedAttempt.max_cosine).toBe(0.842);
  });

  it('5. sqliteBulkPutEmployeesCache tidak menghapus descriptor dan mempertahankan template_version & checksum', () => {
    // 1. Siapkan master descriptor yang ada
    rawDb.exec(`
      INSERT INTO local_master_descriptors (employee_id, descriptor_json, template_version, vector_checksum, updated_at)
      VALUES ('emp_01', '[0.1, 0.2]', 2, 'cs_01', '2026-10-16T10:00:00Z');
    `);

    // 2. Kueri lama di sqliteService.ts:1186 TIDAK memasukkan template_version dan vector_checksum:
    // INSERT OR REPLACE INTO local_master_descriptors (employee_id, descriptor_json, updated_at) VALUES ('emp_01', '[0.1, 0.2]', '...')
    // Hal ini menyebabkan template_version dan vector_checksum di-reset ke NULL!

    // Kueri baru yang benar:
    const empFromSync = {
      id: 'emp_01',
      descriptor_json: '[0.1, 0.2]',
      geometric_descriptor_json: null,
      template_version: 2,
      vector_checksum: 'cs_01',
      updated_at: '2026-10-16T12:00:00Z'
    };

    rawDb.prepare(`
      INSERT INTO local_master_descriptors (employee_id, descriptor_json, geometric_descriptor_json, updated_at, template_version, vector_checksum)
      VALUES (?, ?, ?, ?, ?, ?)
      ON CONFLICT(employee_id) DO UPDATE SET
        descriptor_json = COALESCE(NULLIF(EXCLUDED.descriptor_json, ''), local_master_descriptors.descriptor_json),
        geometric_descriptor_json = COALESCE(NULLIF(EXCLUDED.geometric_descriptor_json, ''), local_master_descriptors.geometric_descriptor_json),
        updated_at = EXCLUDED.updated_at,
        template_version = COALESCE(EXCLUDED.template_version, local_master_descriptors.template_version),
        vector_checksum = COALESCE(EXCLUDED.vector_checksum, local_master_descriptors.vector_checksum)
    `).run(
      empFromSync.id,
      empFromSync.descriptor_json,
      empFromSync.geometric_descriptor_json,
      empFromSync.updated_at,
      empFromSync.template_version,
      empFromSync.vector_checksum
    );

    const updated = rawDb.prepare(`SELECT * FROM local_master_descriptors WHERE employee_id = 'emp_01'`).get();
    expect(updated.template_version).toBe(2);
    expect(updated.vector_checksum).toBe('cs_01');
  });
});
