import { describe, it, expect, vi, beforeEach } from 'vitest';
import { db, dexieDb } from './db';
import { syncPendingEmployees } from './syncEngine';

// Global navigator mock for test environment
if (typeof navigator === 'undefined') {
  globalThis.navigator = { onLine: true };
} else {
  Object.defineProperty(navigator, 'onLine', { value: true, configurable: true });
}

describe('Tugas Regresi: syncPendingEmployees Full Vector Formats & Error Isolation', () => {
  const dummy1024 = Array.from({ length: 1024 }, (_, i) => Math.sin(i * 0.05));
  const dummyF32 = new Float32Array(dummy1024);
  const dummyJsonStr = JSON.stringify(dummy1024);

  // In-memory queue map
  const memQueue = new Map();
  const memCache = new Map();
  const capturedMasterDescriptors = [];

  beforeEach(async () => {
    memQueue.clear();
    memCache.clear();
    capturedMasterDescriptors.length = 0;

    // Mock Dexie table in Node environment
    dexieDb.employee_sync_queue = {
      get: async (key) => memQueue.get(String(key)) || null,
      put: async (val) => { memQueue.set(String(val.id), val); return val.id; },
      add: async (val) => { memQueue.set(String(val.id), val); return val.id; },
      delete: async (key) => { memQueue.delete(String(key)); },
      toArray: async () => Array.from(memQueue.values())
    };

    dexieDb.employees_cache = {
      get: async (key) => memCache.get(String(key)) || null,
      put: async (val) => { memCache.set(String(val.id), val); return val.id; },
      delete: async (key) => { memCache.delete(String(key)); },
      toArray: async () => Array.from(memCache.values()),
      bulkPut: async (arr) => { arr.forEach(val => memCache.set(val.id, val)); }
    };

    dexieDb.user_master = {
      toArray: async () => [],
      get: async () => null,
      put: async () => {},
      add: async () => {},
      delete: async () => {}
    };

    dexieDb.attendance_logs = {
      toArray: async () => [],
      orderBy: () => ({
        reverse: () => ({
          toArray: async () => []
        })
      }),
      put: async () => {},
      delete: async () => {},
      bulkDelete: async () => {}
    };

    dexieDb.attendance_sync_queue = {
      toArray: async () => [],
      put: async () => {},
      delete: async () => {},
      bulkDelete: async () => {}
    };

    dexieDb.employee_face_thumbs = {
      get: async () => null,
      put: async () => {},
      delete: async () => {},
      toArray: async () => [],
      bulkPut: async () => {},
      update: async () => {},
      filter: () => ({ toArray: async () => [] })
    };
  });

  it('runs syncPendingEmployees with array, JSON string, and Float32Array vectors without throwing descObj ReferenceError', async () => {
    const { supabase } = await import('./supabaseClient');

    let currentEmpIdCounter = 7000;

    // Spy / mock supabase RPC and master_descriptors table
    vi.spyOn(supabase, 'rpc').mockImplementation(async (fnName, { payload }) => {
      if (payload.name === 'Error Employee') {
        return { data: { status: 'error', message: 'Simulated RPC duplicate rejection' }, error: null };
      }
      currentEmpIdCounter++;
      return {
        data: {
          status: 'inserted',
          id: currentEmpIdCounter
        },
        error: null
      };
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        upsert: async (payload, opts) => {
          if (table === 'master_descriptors') {
            capturedMasterDescriptors.push(payload);
          }
          return { data: null, error: null };
        },
        insert: async (payload) => ({ data: payload, error: null }),
        select: () => ({
          eq: () => ({
            limit: async () => ({ data: [], error: null })
          })
        })
      };
    });

    // Populate queue with diverse items
    // Item 1: Standard Array, template_version: 2
    memQueue.set('off_1', {
      id: 'off_1',
      nik: 'NIK-001',
      name: 'Budi (Array)',
      department: 'Panen',
      descriptor_json: dummy1024,
      template_version: 2,
      is_synced: false
    });

    // Item 2: String JSON, template_version: 0 (upload foto)
    memQueue.set('off_2', {
      id: 'off_2',
      nik: 'NIK-002',
      name: 'Siti (JSON String)',
      department: 'Pabrik',
      descriptor_json: dummyJsonStr,
      template_version: 0,
      is_synced: false
    });

    // Item 3: Float32Array, template_version undefined/null (legacy)
    memQueue.set('off_3', {
      id: 'off_3',
      nik: 'NIK-003',
      name: 'Agus (Float32Array)',
      department: 'Mandor',
      descriptor_json: dummyF32,
      template_version: null,
      is_synced: false
    });

    // Item 4: Error Employee (fails RPC to test isolation)
    memQueue.set('off_4', {
      id: 'off_4',
      nik: 'NIK-004',
      name: 'Error Employee',
      department: 'Rawat',
      descriptor_json: dummy1024,
      template_version: 2,
      is_synced: false
    });

    // Item 5: Valid employee queued AFTER error employee (must still sync)
    memQueue.set('off_5', {
      id: 'off_5',
      nik: 'NIK-005',
      name: 'Dewi (Post-Error)',
      department: 'Panen',
      descriptor_json: dummy1024,
      template_version: 1,
      is_synced: false
    });

    // Execute the real syncPendingEmployees function once
    await syncPendingEmployees();

    // 1. Verify master_descriptors payloads
    expect(capturedMasterDescriptors.length).toBe(4); // off_1, off_2, off_3, off_5 (off_4 failed before descriptor step)

    // off_1 (Array -> Array 1024, template_version 2)
    const p1 = capturedMasterDescriptors.find(p => p.employee_id === 7001);
    expect(p1).toBeDefined();
    expect(Array.isArray(p1.descriptor_json)).toBe(true);
    expect(p1.descriptor_json.length).toBe(1024);
    expect(p1.template_version).toBe(2);

    // off_2 (JSON string -> Array 1024, template_version 0)
    const p2 = capturedMasterDescriptors.find(p => p.employee_id === 7002);
    expect(p2).toBeDefined();
    expect(Array.isArray(p2.descriptor_json)).toBe(true);
    expect(p2.descriptor_json.length).toBe(1024);
    expect(p2.template_version).toBe(0);

    // off_3 (Float32Array -> Array 1024, template_version null)
    const p3 = capturedMasterDescriptors.find(p => p.employee_id === 7003);
    expect(p3).toBeDefined();
    expect(Array.isArray(p3.descriptor_json)).toBe(true);
    expect(p3.descriptor_json.length).toBe(1024);
    expect(p3.template_version).toBeNull();

    // off_5 (Post-error employee -> Array 1024, template_version 1)
    const p5 = capturedMasterDescriptors.find(p => p.employee_id === 7004);
    expect(p5).toBeDefined();
    expect(Array.isArray(p5.descriptor_json)).toBe(true);
    expect(p5.template_version).toBe(1);

    // 2. Verify Fault Tolerance / Isolation
    // off_1, off_2, off_3, off_5 were deleted from queue on success
    expect(memQueue.has('off_1')).toBe(false);
    expect(memQueue.has('off_2')).toBe(false);
    expect(memQueue.has('off_3')).toBe(false);
    expect(memQueue.has('off_5')).toBe(false);

    // off_4 remains in queue with error details recorded in last_error_*
    expect(memQueue.has('off_4')).toBe(true);
    const errItem = memQueue.get('off_4');
    expect(errItem.last_error_code).toBe('RPC_RETURN_ERROR');
    expect(errItem.last_error_step).toBe('employees_rpc');
    expect(errItem.last_error_message).toContain('Simulated RPC duplicate rejection');
  });
});
