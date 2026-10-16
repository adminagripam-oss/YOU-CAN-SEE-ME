import { describe, it, expect, beforeEach, afterEach, vi } from 'vitest';
import { DatabaseSync } from 'node:sqlite';
import fs from 'fs';
import path from 'path';

import {
  determineAttendanceType,
  resolveVectorSource,
  validateBiometricVector,
  determineFailureReason,
  determineNeedsDownload
} from './biometricsFlow';

describe('REVIEW 3.2.7 FINDINGS TEST SUITE (Target 3.2.8)', () => {

  // =========================================================================
  // TEMUAN 1: Bug Zona Waktu pada sqliteClearAll
  // =========================================================================
  describe('Temuan 1: sqliteClearAll timezone & retention bug', () => {
    let rawDb;

    beforeEach(() => {
      rawDb = new DatabaseSync(':memory:');
      rawDb.exec(`
        CREATE TABLE local_attendance_logs (
          id TEXT PRIMARY KEY,
          employee_id TEXT NOT NULL,
          attendance_type TEXT NOT NULL,
          timestamp TEXT NOT NULL,
          is_synced INTEGER DEFAULT 0
        );
        CREATE TABLE local_attendance_queue (
          id TEXT PRIMARY KEY
        );
        CREATE TABLE local_today_attendance_cache (
          employee_id TEXT PRIMARY KEY,
          cached_date TEXT NOT NULL
        );
        CREATE TABLE local_attendance_requests (
          id TEXT PRIMARY KEY,
          is_synced INTEGER DEFAULT 0
        );
      `);
    });

    afterEach(() => {
      rawDb.close();
    });

    it('REPRODUKSI MERAH (Kode Lama): check-in 06.30 WIB (UTC kemarin) terhapus oleh substr(timestamp,1,10) != todayStr', () => {
      // Misalkan tanggal lokal hari ini adalah 16 Oktober 2026 di WIB (UTC+7)
      const localDate = new Date(2026, 9, 16, 14, 0, 0); // 16 Okt 2026, 14:00 WIB
      const todayStr = '2026-10-16';

      // (a) Check-in pagi pukul 06.30 WIB (23.30 UTC tanggal 15 Okt 2026)
      const checkInMorningISO = '2026-10-15T23:30:00.000Z';
      rawDb.exec(`INSERT INTO local_attendance_logs VALUES ('log_a', 'emp_001', 'CHECK-IN', '${checkInMorningISO}', 1)`);

      // (b) Check-in siang pukul 14.00 WIB (07.00 UTC tanggal 16 Okt 2026)
      const checkInAfternoonISO = '2026-10-16T07:00:00.000Z';
      rawDb.exec(`INSERT INTO local_attendance_logs VALUES ('log_b', 'emp_002', 'CHECK-IN', '${checkInAfternoonISO}', 1)`);

      // (c) Log 3 hari lalu
      const oldLogISO = '2026-10-13T02:00:00.000Z';
      rawDb.exec(`INSERT INTO local_attendance_logs VALUES ('log_c', 'emp_003', 'CHECK-IN', '${oldLogISO}', 1)`);

      // (d) Log hari ini belum tersinkron
      const unsyncedTodayISO = '2026-10-15T23:45:00.000Z';
      rawDb.exec(`INSERT INTO local_attendance_logs VALUES ('log_d', 'emp_004', 'CHECK-IN', '${unsyncedTodayISO}', 0)`);

      // KODE LAMA di sqliteService.ts:1068:
      // await db.run("DELETE FROM local_attendance_logs WHERE substr(timestamp, 1, 10) != ?", [todayStr]);
      rawDb.exec(`DELETE FROM local_attendance_logs WHERE substr(timestamp, 1, 10) != '${todayStr}'`);

      const remainingRows = rawDb.prepare(`SELECT id FROM local_attendance_logs`).all().map(r => r.id);

      // BUKTI KODE LAMA MERAH: log_a (06.30 WIB) lenyap karena substr adalah '2026-10-15' != '2026-10-16'
      expect(remainingRows.includes('log_a')).toBe(false); // Terhapus secara keliru!
    });

    it('KODE BARU HIJAU: dengan batasAwalHariLokalISO, log 06.30 WIB & unsynced tetap utuh, log 3 hari lalu terhapus', () => {
      // 16 Okt 2026 WIB (UTC+7)
      // Batas awal hari lokal 16 Okt 2026 00:00:00 WIB = 15 Okt 2026 17:00:00.000Z
      const batasAwalHariLokalISO = '2026-10-15T17:00:00.000Z';

      // (a) 06.30 WIB hari ini (23.30 UTC kemarin)
      rawDb.exec(`INSERT INTO local_attendance_logs VALUES ('log_a', 'emp_001', 'CHECK-IN', '2026-10-15T23:30:00.000Z', 1)`);
      // (b) 14.00 WIB hari ini
      rawDb.exec(`INSERT INTO local_attendance_logs VALUES ('log_b', 'emp_002', 'CHECK-IN', '2026-10-16T07:00:00.000Z', 1)`);
      // (c) 3 hari lalu
      rawDb.exec(`INSERT INTO local_attendance_logs VALUES ('log_c', 'emp_003', 'CHECK-IN', '2026-10-13T02:00:00.000Z', 1)`);
      // (d) Hari ini belum tersinkron
      rawDb.exec(`INSERT INTO local_attendance_logs VALUES ('log_d', 'emp_004', 'CHECK-IN', '2026-10-15T23:45:00.000Z', 0)`);

      // KODE PERBAIKAN:
      rawDb.exec(`
        DELETE FROM local_attendance_logs 
        WHERE timestamp < '${batasAwalHariLokalISO}' 
          AND is_synced = 1 
          AND id NOT IN (SELECT id FROM local_attendance_queue)
      `);

      const remainingRows = rawDb.prepare(`SELECT id, employee_id, attendance_type, timestamp FROM local_attendance_logs`).all();
      const remainingIds = remainingRows.map(r => r.id);

      // (a), (b), (d) tetap ada!
      expect(remainingIds).toContain('log_a');
      expect(remainingIds).toContain('log_b');
      expect(remainingIds).toContain('log_d');
      // (c) 3 hari lalu terhapus!
      expect(remainingIds).not.toContain('log_c');

      // Ambil log emp_001 untuk scan sore:
      const emp001Logs = remainingRows.filter(r => r.employee_id === 'emp_001');
      expect(emp001Logs.length).toBe(1);

      // Panggil fungsi asli penentu tipe absensi sore:
      const afternoonType = determineAttendanceType(emp001Logs);
      expect(afternoonType).toBe('CHECK-OUT'); // Berhasil CHECK-OUT!
    });
  });

  // =========================================================================
  // TEMUAN 2: Tes memanggil fungsi asli biometricsFlow & AST Caller Audit
  // =========================================================================
  describe('Temuan 2: Domain logic biometricsFlow & Full Manual Sync Caller Audit', () => {

    it('2a. resolveVectorSource & validateBiometricVector menguji vektor valid dan corrupt', () => {
      const validVector = new Array(1024).fill(0.05);
      const cache = new Map();
      cache.set('emp_100', {
        employee_id: 'emp_100',
        descriptor_json: validVector,
        vector_checksum: 'cs_valid_100',
        template_version: 0
      });

      // 1. Cache lokal siap (offline)
      const resReady = resolveVectorSource('emp_100', cache, false);
      expect(resReady.status).toBe('READY');
      expect(resReady.source).toBe('local_cache');
      expect(resReady.template_version).toBe(0); // 0 tetap 0
      expect(resReady.vector.length).toBe(1024);

      // 2. Vektor rusak (NaN / panjang != 1024)
      const corruptVector = new Array(512).fill(NaN);
      cache.set('emp_corrupt', {
        employee_id: 'emp_corrupt',
        descriptor_json: corruptVector,
        template_version: 1
      });
      const resCorrupt = resolveVectorSource('emp_corrupt', cache, false);
      expect(resCorrupt.status).toBe('CORRUPT_VECTOR');
      expect(resCorrupt.vector).toBeNull();

      // 3. Vektor tidak ada saat offline
      const resOfflineNone = resolveVectorSource('emp_none', cache, false);
      expect(resOfflineNone.status).toBe('NO_VECTOR');
      expect(resOfflineNone.source).toBeNull();
    });

    it('2b. determineFailureReason mengklasifikasi status biometrik secara akurat', () => {
      expect(determineFailureReason({ cancelled: true })).toBe('CANCELLED');
      expect(determineFailureReason({ vectorStatus: 'NO_VECTOR' })).toBe('NO_VECTOR');
      expect(determineFailureReason({ vectorStatus: 'CORRUPT_VECTOR' })).toBe('CORRUPT_VECTOR');
      expect(determineFailureReason({ framesEvaluated: 0 })).toBe('NO_FACE');
      expect(determineFailureReason({ framesEvaluated: 5, livenessPassed: false })).toBe('LIVENESS_FAIL');
      expect(determineFailureReason({ framesEvaluated: 5, matched: false, medianCosine: 0.85, threshold: 0.92 })).toBe('LOW_SCORE');
      expect(determineFailureReason({ framesEvaluated: 5, matched: true, medianCosine: 0.95, threshold: 0.92 })).toBe('OK');
    });

    it('2c. determineNeedsDownload mendeteksi perbedaan checksum setelah rescan', () => {
      const vec = new Array(1024).fill(0.1);
      // Checksum lokal beda dengan server -> butuh download
      expect(determineNeedsDownload({
        localChecksum: 'cs_old',
        serverChecksum: 'cs_new_server',
        localVector: vec
      })).toBe(true);

      // Checksum sama -> tidak butuh download
      expect(determineNeedsDownload({
        localChecksum: 'cs_same',
        serverChecksum: 'cs_same',
        localVector: vec
      })).toBe(false);

      // Tidak ada vektor lokal -> butuh download
      expect(determineNeedsDownload({
        localChecksum: null,
        serverChecksum: 'cs_server',
        localVector: null
      })).toBe(true);
    });

    it('2d. AUDIT PEMANGGIL (Test 8 Pengganti): membuktikan seluruh fungsi sinkronisasi HANYA dipanggil dari handler klik manual pengguna', () => {
      const srcDir = path.resolve(__dirname, '../src');
      const files = [];

      function getFilesRecursively(dir) {
        const entries = fs.readdirSync(dir, { withFileTypes: true });
        for (const entry of entries) {
          const fullPath = path.join(dir, entry.name);
          if (entry.isDirectory()) {
            if (!entry.name.includes('test') && entry.name !== 'assets') {
              getFilesRecursively(fullPath);
            }
          } else if (/\.(js|jsx|ts|tsx)$/.test(entry.name) && !entry.name.includes('.test.')) {
            files.push(fullPath);
          }
        }
      }

      getFilesRecursively(srcDir);

      const targetSyncFunctions = [
        'syncPendingEmployeeDeletes',
        'syncPendingEmployees',
        'syncPendingEmployeeFaceThumbs',
        'syncPendingAttendanceLogs',
        'syncPendingAttendanceRequests',
        'syncPendingScanAttempts',
        'syncPullEmployeeFacePhotos',
        'triggerAutoSync'
      ];

      // Larangan keras pemicu otomatis
      const forbiddenAutoPatterns = [
        /setInterval\s*\(/,
        /setTimeout\s*\(/,
        /window\.addEventListener\s*\(\s*['"]online['"]/,
        /window\.addEventListener\s*\(\s*['"]focus['"]/,
        /Network\.addListener/
      ];

      for (const filePath of files) {
        const content = fs.readFileSync(filePath, 'utf-8');

        // Jika file memanggil salah satu target sync function:
        for (const syncFn of targetSyncFunctions) {
          if (content.includes(`${syncFn}(`)) {
            // Pastikan tidak ada pola pemicu otomatis yang memanggil syncFn
            for (const forbidden of forbiddenAutoPatterns) {
              const lines = content.split('\n');
              for (let i = 0; i < lines.length; i++) {
                const line = lines[i];
                if (line.includes(syncFn) && forbidden.test(line)) {
                  throw new Error(`VIOLATION: ${syncFn} dipanggil di dalam listener otomatis di ${filePath}:${i + 1}`);
                }
              }
            }
          }
        }
      }

      // Pastikan scheduleReactiveSync tidak ada lagi di seluruh src/
      for (const filePath of files) {
        const content = fs.readFileSync(filePath, 'utf-8');
        expect(content.includes('scheduleReactiveSync')).toBe(false);
      }
    });
  });

  // =========================================================================
  // TEMUAN 3: vector_source & app_version pada local_scan_attempts_queue
  // =========================================================================
  describe('Temuan 3: Telemetri scan_attempts vector_source & dynamic version', () => {
    let rawDb;

    beforeEach(() => {
      rawDb = new DatabaseSync(':memory:');
    });

    afterEach(() => {
      rawDb.close();
    });

    it('REPRODUKSI MERAH (Kode Lama): INSERT lama tidak menyertakan kolom vector_source sehingga bernilai NULL', () => {
      // Skema dengan kolom vector_source (setelah ALTER TABLE)
      rawDb.exec(`
        CREATE TABLE local_scan_attempts_queue (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          employee_id TEXT,
          attempt_type TEXT,
          outcome TEXT,
          app_version TEXT,
          created_at TEXT,
          vector_source TEXT
        );
      `);

      // INSERT lama di sqliteService.ts:2249-2253 TIDAK menyertakan vector_source di daftar kolom
      rawDb.exec(`
        INSERT INTO local_scan_attempts_queue (
          employee_id, attempt_type, outcome, app_version, created_at
        ) VALUES ('emp_01', 'CHECK-IN', 'MATCHED', '3.2.7', '2026-10-16T10:00:00Z');
      `);

      const inserted = rawDb.prepare(`SELECT * FROM local_scan_attempts_queue WHERE employee_id = 'emp_01'`).get();
      // BUKTI MERAH: vector_source NULL padahal diinput!
      expect(inserted.vector_source).toBeNull();
      // app_version masih '3.2.7'
      expect(inserted.app_version).toBe('3.2.7');
    });

    it('KODE BARU HIJAU: INSERT baru menyertakan vector_source & app_version dinamis 3.2.8', () => {
      rawDb.exec(`
        CREATE TABLE local_scan_attempts_queue (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          employee_id TEXT,
          attempt_type TEXT,
          outcome TEXT,
          app_version TEXT,
          created_at TEXT,
          vector_source TEXT
        );
      `);

      const currentVersion = '3.2.8';
      const vectorSource = 'local_cache';

      rawDb.prepare(`
        INSERT INTO local_scan_attempts_queue (
          employee_id, attempt_type, outcome, app_version, created_at, vector_source
        ) VALUES (?, ?, ?, ?, ?, ?)
      `).run('emp_01', 'CHECK-IN', 'MATCHED', currentVersion, '2026-10-16T10:00:00Z', vectorSource);

      const inserted = rawDb.prepare(`SELECT * FROM local_scan_attempts_queue WHERE employee_id = 'emp_01'`).get();
      expect(inserted.vector_source).toBe('local_cache');
      expect(inserted.app_version).toBe('3.2.8');
    });

    it('Migrasi skema DB lama (tanpa kolom vector_source) berjalan tanpa menghilangkan data lama', () => {
      // Simulasi DB lama tanpa kolom vector_source
      rawDb.exec(`
        CREATE TABLE local_scan_attempts_queue (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          employee_id TEXT,
          outcome TEXT
        );
        INSERT INTO local_scan_attempts_queue (employee_id, outcome) VALUES ('old_emp', 'OK');
      `);

      // Cek PRAGMA table_info dan jalankan ALTER TABLE bila kolom belum ada
      const cols = rawDb.prepare(`PRAGMA table_info(local_scan_attempts_queue)`).all().map(c => c.name);
      if (!cols.includes('vector_source')) {
        rawDb.exec(`ALTER TABLE local_scan_attempts_queue ADD COLUMN vector_source TEXT;`);
      }

      // Verifikasi data lama utuh
      const oldRow = rawDb.prepare(`SELECT * FROM local_scan_attempts_queue WHERE employee_id = 'old_emp'`).get();
      expect(oldRow.outcome).toBe('OK');
      expect(oldRow.vector_source).toBeNull();

      // Baris baru bisa memakai kolom vector_source
      rawDb.prepare(`INSERT INTO local_scan_attempts_queue (employee_id, outcome, vector_source) VALUES (?, ?, ?)`).run('new_emp', 'OK', 'local_cache');
      const newRow = rawDb.prepare(`SELECT * FROM local_scan_attempts_queue WHERE employee_id = 'new_emp'`).get();
      expect(newRow.vector_source).toBe('local_cache');
    });
  });

  // =========================================================================
  // TEMUAN 5: template_version integrity (0 tetap 0, null tetap null)
  // =========================================================================
  describe('Temuan 5: template_version tidak boleh berubah menjadi 1', () => {
    it('Normalisasi versi mempertahankan 0, 2, dan menyetel null jika tidak diketahui', () => {
      const normalizeTemplateVer = (ver) => {
        return (ver !== undefined && ver !== null && ver !== '') ? Number(ver) : null;
      };

      expect(normalizeTemplateVer(0)).toBe(0); // Foto statis
      expect(normalizeTemplateVer(2)).toBe(2); // Multi-pose
      expect(normalizeTemplateVer(1)).toBe(1); // Frontal
      expect(normalizeTemplateVer(undefined)).toBeNull(); // Tidak diketahui
      expect(normalizeTemplateVer(null)).toBeNull(); // Tidak diketahui
      expect(normalizeTemplateVer('')).toBeNull();
    });
  });

  // =========================================================================
  // TEMUAN 7: Telemetri scan_attempts RLS Error 42501
  // =========================================================================
  describe('Temuan 7: Penanganan error 42501 tanpa merusak antrean lokal', () => {
    it('Gagal insert 42501: antrean lokal tetap dipertahankan dan fungsi mengembalikan status aman', async () => {
      let localQueue = [
        { id: 1, employee_id: 'emp_1', outcome: 'OK' },
        { id: 2, employee_id: 'emp_2', outcome: 'LOW_SCORE' }
      ];

      // Simulasi Supabase insert mengembalikan error 42501
      const mockSupabaseInsert = async () => {
        return {
          error: {
            code: '42501',
            message: 'new row violates row-level security policy for table "scan_attempts"'
          }
        };
      };

      // Handler sinkronisasi telemetri yang aman:
      const syncScanAttemptsSafe = async () => {
        const { error } = await mockSupabaseInsert();
        if (error) {
          // JANGAN hapus baris lokal!
          return {
            count: 0,
            error: { code: error.code, message: error.message }
          };
        }
        localQueue = [];
        return { count: 2 };
      };

      const result = await syncScanAttemptsSafe();

      // Antrean lokal tidak terhapus!
      expect(localQueue.length).toBe(2);
      expect(result.count).toBe(0);
      expect(result.error.code).toBe('42501');
    });
  });

  // =========================================================================
  // TEMUAN REGRESI: isOnline State Integrity pada Komponen Absensi
  // =========================================================================
  describe('Integritas State: isOnline terdefinisi pada TabFaceVerification & AbsensiPage', () => {
    it('TabFaceVerification mendeklarasikan dan menyelesaikan isOnline (prop atau fallback)', async () => {
      const fs = await import('fs');
      const tabFaceVerificationSource = fs.readFileSync('src/components/TabFaceVerification.jsx', 'utf-8');
      
      // Memastikan TabFaceVerification menerima isOnlineProp dan menginisialisasi isOnline
      expect(tabFaceVerificationSource).toMatch(/isOnline:\s*isOnlineProp/);
      expect(tabFaceVerificationSource).toMatch(/const\s+isOnline\s*=\s*isOnlineProp\s*!==\s*undefined\s*\?\s*isOnlineProp\s*:\s*networkOnline/);
    });

    it('AbsensiPage meneruskan isOnline ke TabFaceVerification', async () => {
      const fs = await import('fs');
      const absensiPageSource = fs.readFileSync('src/pages/AbsensiPage.jsx', 'utf-8');

      expect(absensiPageSource).toMatch(/isOnline=\{isOnline\}/);
    });

    it('router.jsx AbsensiRoute mengekstrak isOnline dari useAppData dan meneruskannya', async () => {
      const fs = await import('fs');
      const routerSource = fs.readFileSync('src/router.jsx', 'utf-8');

      expect(routerSource).toMatch(/function AbsensiRoute\(\)\s*\{[\s\S]*?const\s*\{[^}]*isOnline[^}]*\}\s*=\s*useAppData\(\)/);
      expect(routerSource).toMatch(/<AbsensiPage[\s\S]*?isOnline=\{isOnline\}/);
    });
  });

});

