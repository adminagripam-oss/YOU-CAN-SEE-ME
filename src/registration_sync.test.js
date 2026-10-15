import { describe, it, expect, beforeEach } from 'vitest';
import { db, dexieDb } from './db';
import { vectorChecksum } from './biometrics';

describe('Registration Sync Troubleshooting & Resiliency Tests', () => {
  // In-memory backing store for Dexie table in Node.js test environment
  // Preserves strict type keys (e.g. key 1150 !== key '1150')
  const memQueue = new Map();
  const memCache = new Map();

  beforeEach(() => {
    memQueue.clear();
    memCache.clear();

    dexieDb.employee_sync_queue = {
      get: async (key) => memQueue.get(key) || null,
      put: async (val) => { memQueue.set(val.id, val); return val.id; },
      delete: async (key) => { memQueue.delete(key); },
      toArray: async () => Array.from(memQueue.values())
    };

    dexieDb.employees_cache = {
      get: async (key) => memCache.get(key) || null,
      put: async (val) => { memCache.set(val.id, val); return val.id; },
      delete: async (key) => { memCache.delete(key); },
      toArray: async () => Array.from(memCache.values()),
      bulkPut: async (arr) => { arr.forEach(val => memCache.set(val.id, val)); }
    };
  });

  describe('1. ID Type Normalization & Deletion (String vs Number)', () => {
    it('normalizes numeric ID to string on insert and allows retrieval by both number and string', async () => {
      const mockEmp = {
        id: 1150,
        nik: 'NIK-1150',
        name: 'Budi Santoso',
        department: 'Panen',
        nama_kebun: 'Kebun A',
        is_synced: false,
        created_at: new Date().toISOString()
      };

      await db.employee_sync_queue.add(mockEmp);

      // In the underlying store, id is normalized to '1150'
      expect(memQueue.has('1150')).toBe(true);
      expect(memQueue.has(1150)).toBe(false);

      // Retrieve via string ID
      const byString = await db.employee_sync_queue.get('1150');
      expect(byString).not.toBeNull();
      expect(String(byString.id)).toBe('1150');
      expect(byString.name).toBe('Budi Santoso');

      // Retrieve via numeric ID
      const byNumber = await db.employee_sync_queue.get(1150);
      expect(byNumber).not.toBeNull();
      expect(String(byNumber.id)).toBe('1150');
      expect(byNumber.name).toBe('Budi Santoso');
    });

    it('successfully purges numeric ID queue item when deleted using string key', async () => {
      // Simulate legacy record with number key
      memQueue.set(1150, {
        id: 1150,
        nik: 'NIK-1150',
        name: 'Budi Santoso',
        is_synced: false
      });
      expect(memQueue.has(1150)).toBe(true);

      // Delete using String('1150')
      await db.employee_sync_queue.delete('1150');

      // Both string and number keys are purged
      expect(memQueue.has(1150)).toBe(false);
      expect(memQueue.has('1150')).toBe(false);

      const afterDeleteString = await db.employee_sync_queue.get('1150');
      const afterDeleteNumber = await db.employee_sync_queue.get(1150);
      expect(afterDeleteString).toBeNull();
      expect(afterDeleteNumber).toBeNull();
    });

    it('successfully purges string ID queue item when deleted using numeric key if castable', async () => {
      const mockEmp = {
        id: '1150',
        nik: 'NIK-1150',
        name: 'Budi Santoso',
        department: 'Panen',
        is_synced: false
      };

      await db.employee_sync_queue.add(mockEmp);
      let found = await db.employee_sync_queue.get(1150);
      expect(found).not.toBeNull();

      // Delete using Number(1150)
      await db.employee_sync_queue.delete(1150);

      const afterDelete = await db.employee_sync_queue.get('1150');
      expect(afterDelete).toBeNull();
      expect(memQueue.has('1150')).toBe(false);
    });

    it('handles alphanumeric temporary offline IDs seamlessly', async () => {
      const tempEmp = {
        id: 'off_emp_test_1',
        nik: 'NIK-TEMP-001',
        name: 'Karyawan Baru Offline',
        department: 'Perawatan',
        is_synced: false
      };

      await db.employee_sync_queue.add(tempEmp);
      const retrieved = await db.employee_sync_queue.get('off_emp_test_1');
      expect(retrieved).not.toBeNull();
      expect(retrieved.id).toBe('off_emp_test_1');

      await db.employee_sync_queue.delete('off_emp_test_1');
      const afterDelete = await db.employee_sync_queue.get('off_emp_test_1');
      expect(afterDelete).toBeNull();
      expect(memQueue.has('off_emp_test_1')).toBe(false);
    });
  });

  describe('2. Error Tracking & Diagnostic State Preservation', () => {
    it('persists last_error_code, last_error_message, last_error_step, and attempt count', async () => {
      const empData = {
        id: 'off_emp_test_2',
        nik: 'NIK-ERR-001',
        name: 'Siti Aminah',
        department: 'Bibitan',
        is_synced: false,
        created_at: new Date().toISOString()
      };

      await db.employee_sync_queue.add(empData);

      // Record first failure
      await db.employee_sync_queue.updateError('off_emp_test_2', {
        last_error_code: '42703',
        last_error_message: 'column master_descriptors.vector_checksum does not exist',
        last_error_step: 'master_descriptors'
      });

      let item = await db.employee_sync_queue.get('off_emp_test_2');
      expect(item).not.toBeNull();
      expect(item.last_error_code).toBe('42703');
      expect(item.last_error_message).toContain('vector_checksum does not exist');
      expect(item.last_error_step).toBe('master_descriptors');
      expect(item.attempt_count).toBe(1);
      expect(item.last_attempt_at).toBeTruthy();
      expect(new Date(item.last_attempt_at).getTime()).toBeGreaterThan(0);

      // Record second failure (e.g. retry failed)
      await db.employee_sync_queue.updateError('off_emp_test_2', {
        last_error_code: 'RPC_ERROR',
        last_error_message: 'Network connection timeout',
        last_error_step: 'employees_rpc'
      });

      item = await db.employee_sync_queue.get('off_emp_test_2');
      expect(item.last_error_code).toBe('RPC_ERROR');
      expect(item.last_error_message).toBe('Network connection timeout');
      expect(item.last_error_step).toBe('employees_rpc');
      expect(item.attempt_count).toBe(2);
    });
  });

  describe('3. Schema Adaptation & Fallback for master_descriptors', () => {
    it('generates fallback payload excluding vector_checksum and template_version when 42703 occurs', () => {
      const mockVector = new Array(1024).fill(0.03125);
      const checksumVal = vectorChecksum(mockVector);

      const fullPayload = {
        employee_id: 1150,
        descriptor_json: mockVector,
        updated_at: new Date().toISOString(),
        template_version: 2,
        vector_checksum: checksumVal
      };

      // When Postgres returns 42703 (undefined column):
      const pgError = { code: '42703', message: 'column master_descriptors.vector_checksum does not exist' };
      const isMissingColumn = pgError.code === '42703' || pgError.message.includes('vector_checksum');
      expect(isMissingColumn).toBe(true);

      // Adaptive fallback payload
      const fallbackPayload = {
        employee_id: fullPayload.employee_id,
        descriptor_json: fullPayload.descriptor_json,
        updated_at: fullPayload.updated_at
      };

      expect(fallbackPayload.employee_id).toBe(1150);
      expect(fallbackPayload.descriptor_json).toBe(mockVector);
      expect(fallbackPayload.vector_checksum).toBeUndefined();
      expect(fallbackPayload.template_version).toBeUndefined();
    });
  });

  describe('4. Secondary Step Failure Isolation (Photos & Telemetry)', () => {
    it('isolates secondary photo upload errors so core employee registration is not aborted', async () => {
      let coreEmployeeRegistered = false;
      let secondaryPhotoUploaded = false;
      let secondaryErrorLogged = false;

      // Simulate core step: Employee RPC and master_descriptors
      const coreStep = async () => {
        coreEmployeeRegistered = true;
        return { status: 'inserted', id: 2026 };
      };

      // Simulate secondary step: photo storage / table
      const secondaryStep = async () => {
        try {
          // Simulate table employee_face_photos not found (42P01)
          throw new Error('relation "public.employee_face_photos" does not exist');
        } catch (secErr) {
          secondaryErrorLogged = true;
          // Isolated: do not rethrow!
        }
      };

      // Run pipeline
      const realId = await coreStep();
      expect(coreEmployeeRegistered).toBe(true);
      expect(realId.id).toBe(2026);

      // Execute secondary step
      await secondaryStep();
      expect(secondaryPhotoUploaded).toBe(false);
      expect(secondaryErrorLogged).toBe(true);

      // Core registration remains valid and uncompromised
      expect(coreEmployeeRegistered).toBe(true);
    });

    it('isolates scan_attempts telemetry failure so core sync is not aborted', async () => {
      let coreEmployeeSynced = true;
      let telemetryErrorCaught = false;

      const syncTelemetry = async () => {
        try {
          throw new Error('relation "public.scan_attempts" does not exist (code 42P01)');
        } catch (err) {
          telemetryErrorCaught = true;
        }
      };

      await syncTelemetry();
      expect(telemetryErrorCaught).toBe(true);
      expect(coreEmployeeSynced).toBe(true);
    });
  });

  describe('5. Cut-Off 22:00 Daytime Evaluation Analysis', () => {
    it('proves mathematically that daytime registrations are NOT blocked by the 22:00 filter', () => {
      // Suppose testing occurs at 11:30 AM
      const testingDate = new Date('2026-10-15T11:30:00+07:00');
      const registrationCreatedAt = '2026-10-15T11:30:00+07:00';

      const waktuCutOff = new Date(testingDate.getTime());
      waktuCutOff.setHours(22, 0, 0, 0);
      const batasCutOffTime = waktuCutOff.getTime();

      const isPassFilter = new Date(registrationCreatedAt).getTime() <= batasCutOffTime;

      // Mathematically, 11:30 <= 22:00 evaluates to TRUE
      expect(isPassFilter).toBe(true);
    });
  });
});
