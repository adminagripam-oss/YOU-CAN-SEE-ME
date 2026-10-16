import { describe, it, expect, vi, beforeEach } from 'vitest';
import { db, dexieDb } from './db';
import { supabase } from './supabaseClient';
import {
  syncPendingEmployees,
  syncPendingEmployeeFaceThumbs,
  syncPendingScanAttempts,
  diagnoseRegistrationSync,
  APP_RUN_ID
} from './syncEngine';

if (typeof navigator === 'undefined') {
  globalThis.navigator = { onLine: true };
} else {
  Object.defineProperty(navigator, 'onLine', { value: true, configurable: true });
}

describe('TAHAP 6: Test Suite Troubleshooting Sinkronisasi Pendaftaran Karyawan', () => {
  const memQueue = new Map();
  const memCache = new Map();
  const memThumbs = new Map();
  const memMasterDescriptors = [];
  const memUploadedPhotos = [];
  const memStorageUploads = [];
  const memScanAttempts = [];

  const dummy1024 = Array.from({ length: 1024 }, (_, i) => Math.sin(i * 0.05));
  const dummyBase64 = 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAP//////////////////////////////////////////////////////////////////////////////////////wgALCAABAAEBAREA/8QAFBABAAAAAAAAAAAAAAAAAAAAAP/aAAgBAQABPxA=';

  beforeEach(() => {
    memQueue.clear();
    memCache.clear();
    memThumbs.clear();
    memMasterDescriptors.length = 0;
    memUploadedPhotos.length = 0;
    memStorageUploads.length = 0;
    memScanAttempts.length = 0;

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
      bulkPut: async (arr) => { arr.forEach(val => memCache.set(String(val.id), val)); }
    };

    dexieDb.employee_face_thumbs = {
      get: async (key) => memThumbs.get(String(key)) || null,
      put: async (val) => { memThumbs.set(String(val.employee_id), val); return val.employee_id; },
      add: async (val) => { memThumbs.set(String(val.employee_id), val); return val.employee_id; },
      delete: async (key) => { memThumbs.delete(String(key)); },
      toArray: async () => Array.from(memThumbs.values()),
      filter: (fn) => ({
        toArray: async () => Array.from(memThumbs.values()).filter(fn)
      }),
      update: async (key, patch) => {
        const item = memThumbs.get(String(key));
        if (item) {
          memThumbs.set(String(key), { ...item, ...patch });
        }
      },
      bulkDelete: async (keys) => { keys.forEach(k => memThumbs.delete(String(k))); }
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
      orderBy: () => ({ reverse: () => ({ toArray: async () => [] }) }),
      put: async () => {},
      delete: async () => {},
      bulkDelete: async () => {}
    };

    dexieDb.attendance_sync_queue = {
      toArray: async () => [],
      delete: async () => {}
    };

    dexieDb.scan_attempts = {
      toArray: async () => Array.from(memScanAttempts),
      bulkDelete: async (ids) => {
        const idSet = new Set(ids);
        const remaining = memScanAttempts.filter(a => !idSet.has(a.id));
        memScanAttempts.length = 0;
        memScanAttempts.push(...remaining);
      }
    };
  });

  it('1. Sukses Penuh (Full Success): pendaftaran baru offline tersinkron dan dibersihkan dari antrean', async () => {
    vi.spyOn(supabase, 'rpc').mockResolvedValue({
      data: { status: 'inserted', id: 9101 },
      error: null
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        upsert: async (payload) => {
          if (table === 'master_descriptors') memMasterDescriptors.push(payload);
          if (table === 'employee_face_photos') memUploadedPhotos.push(payload);
          return { data: null, error: null };
        },
        insert: async (payload) => ({ data: payload, error: null }),
        select: () => ({
          limit: async () => ({ data: [], error: null }),
          eq: () => ({
            limit: async () => ({ data: [], error: null })
          })
        })
      };
    });

    vi.spyOn(supabase.storage, 'from').mockImplementation(() => ({
      upload: async (path, blob) => {
        memStorageUploads.push({ path, blob });
        return { data: { path }, error: null };
      },
      list: async () => ({ data: [{ name: 'master.jpg' }], error: null })
    }));

    // Setup pending employee
    memQueue.set('off_101', {
      id: 'off_101',
      nik: 'NIK-SUCCESS-101',
      name: 'Karyawan Sukses',
      department: 'Panen',
      descriptor_json: dummy1024,
      template_version: 2,
      is_synced: false
    });

    // Setup pending thumb
    memThumbs.set('off_101', {
      employee_id: 'off_101',
      nik: 'NIK-SUCCESS-101',
      thumb_base64: dummyBase64,
      captured_at: new Date().toISOString(),
      is_synced: false
    });

    const res = await syncPendingEmployees();
    expect(res.count).toBe(1);

    // Verifikasi item terhapus dari queue pendaftaran
    expect(memQueue.has('off_101')).toBe(false);

    // Verifikasi master descriptor tersimpan dengan ID resmi
    expect(memMasterDescriptors.length).toBe(1);
    expect(memMasterDescriptors[0].employee_id).toBe(9101);
    expect(memMasterDescriptors[0].vector_checksum).toBeDefined();

    // Verifikasi foto tersinkron dengan ID resmi 9101
    expect(memUploadedPhotos.length).toBe(1);
    expect(memUploadedPhotos[0].employee_id).toBe(9101);
  });

  it('2. 42703 (master_descriptors vector_checksum missing): fallback adaptif ke skema standar tanpa gagal', async () => {
    vi.spyOn(supabase, 'rpc').mockResolvedValue({
      data: { status: 'inserted', id: 9102 },
      error: null
    });

    let attemptCount = 0;
    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        upsert: async (payload) => {
          if (table === 'master_descriptors') {
            attemptCount++;
            if (attemptCount === 1) {
              // Simulasi error 42703: kolom vector_checksum belum ada
              return {
                data: null,
                error: {
                  code: '42703',
                  message: 'column "vector_checksum" of relation "master_descriptors" does not exist'
                }
              };
            }
            // Fallback skema standar berhasil
            memMasterDescriptors.push(payload);
            return { data: null, error: null };
          }
          return { data: null, error: null };
        },
        select: () => ({
          limit: async () => ({ data: [], error: null }),
          eq: () => ({ limit: async () => ({ data: [], error: null }) })
        })
      };
    });

    memQueue.set('off_102', {
      id: 'off_102',
      nik: 'NIK-42703-TEST',
      name: 'Karyawan 42703',
      department: 'Pabrik',
      descriptor_json: dummy1024,
      template_version: 1,
      is_synced: false
    });

    const res = await syncPendingEmployees();
    expect(res.count).toBe(1);

    // Item berhasil di-sync dan dibersihkan dari queue
    expect(memQueue.has('off_102')).toBe(false);
    expect(attemptCount).toBe(2); // 1 kali gagal 42703, 1 kali sukses fallback
    expect(memMasterDescriptors.length).toBe(1);
    expect(memMasterDescriptors[0].vector_checksum).toBeUndefined(); // Fallback tidak mengirim vector_checksum
  });

  it('3. 42P01 (scan_attempts table missing): ditangani dengan aman tanpa membatalkan proses', async () => {
    memScanAttempts.push({
      id: 1,
      session_id: 'sess-1',
      employee_id: 1234,
      status: 'rejected',
      error_code: 'FACE_NOT_MATCH',
      created_at: new Date().toISOString()
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        insert: async () => {
          if (table === 'scan_attempts') {
            return {
              data: null,
              error: {
                code: '42P01',
                message: 'relation "scan_attempts" does not exist'
              }
            };
          }
          return { data: null, error: null };
        }
      };
    });

    const res = await syncPendingScanAttempts();
    expect(res.count).toBe(0);
    // Scan attempts lokal tetap aman di IndexedDB, tidak dihapus jika server gagal
    expect(memScanAttempts.length).toBe(1);
  });

  it('4. 23503 (Pelanggaran Foreign Key): item antrean tidak hilang dan status gagal tercatat lengkap', async () => {
    vi.spyOn(supabase, 'rpc').mockResolvedValue({
      data: { status: 'inserted', id: 9104 },
      error: null
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        upsert: async () => {
          if (table === 'master_descriptors') {
            return {
              data: null,
              error: {
                code: '23503',
                message: 'insert or update on table "master_descriptors" violates foreign key constraint "master_descriptors_employee_id_fkey"',
                hint: 'Key (employee_id)=(9104) is not present in table "employees".'
              }
            };
          }
          return { data: null, error: null };
        },
        select: () => ({
          limit: async () => ({ data: [], error: null }),
          eq: () => ({ limit: async () => ({ data: [], error: null }) })
        })
      };
    });

    memQueue.set('off_104', {
      id: 'off_104',
      nik: 'NIK-FK-FAIL',
      name: 'Karyawan FK Fail',
      department: 'Mandor',
      descriptor_json: dummy1024,
      is_synced: false
    });

    const res = await syncPendingEmployees();
    expect(res.count).toBe(0);

    // Item TIDAK boleh hilang dari antrean
    expect(memQueue.has('off_104')).toBe(true);
    const item = memQueue.get('off_104');
    expect(item.sync_status).toBe('failed');
    expect(item.last_error_code).toBe('23503');
    expect(item.last_error_step).toBe('master_descriptors');
    expect(item.last_error_message).toContain('violates foreign key constraint');
    expect(item.last_error_hint).toContain('Key (employee_id)=(9104)');
  });

  it('5. 23505 (Duplikat NIK): auto rekonsiliasi mengambil ID server dan lanjut ke langkah berikutnya', async () => {
    vi.spyOn(supabase, 'rpc').mockResolvedValue({
      data: null,
      error: {
        code: '23505',
        message: 'duplicate key value violates unique constraint "employees_nik_key"'
      }
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        select: (cols) => ({
          eq: (field, val) => ({
            limit: async () => {
              if (table === 'employees' && field === 'nik') {
                return {
                  data: [{
                    id: 7788,
                    nik: val,
                    name: 'Karyawan Duplikat Terkelola',
                    nama_kebun: null,
                    deleted_at: null
                  }],
                  error: null
                };
              }
              return { data: [], error: null };
            }
          })
        }),
        upsert: async (payload) => {
          if (table === 'master_descriptors') {
            memMasterDescriptors.push(payload);
          }
          return { data: null, error: null };
        }
      };
    });

    memQueue.set('off_105', {
      id: 'off_105',
      nik: 'NIK-RECONCILE-105',
      name: 'Karyawan Duplikat Terkelola',
      department: 'Afdeling 1',
      descriptor_json: dummy1024,
      is_synced: false
    });

    const res = await syncPendingEmployees();
    expect(res.count).toBe(1);

    // Berhasil rekonsiliasi ke server id 7788 dan item dibersihkan
    expect(memQueue.has('off_105')).toBe(false);
    expect(memMasterDescriptors.length).toBe(1);
    expect(memMasterDescriptors[0].employee_id).toBe(7788);
  });

  it('5b. 23505 (Duplikat NIK): tolak rekonsiliasi bila nama berbeda dan tandai RECONCILIATION_MISMATCH', async () => {
    vi.spyOn(supabase, 'rpc').mockResolvedValue({
      data: null,
      error: {
        code: '23505',
        message: 'duplicate key value violates unique constraint "employees_nik_key"'
      }
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        select: (cols) => ({
          eq: (field, val) => ({
            limit: async () => {
              if (table === 'employees' && field === 'nik') {
                return {
                  data: [{
                    id: 9999,
                    nik: val,
                    name: 'Orang Lain Yang Berbeda',
                    nama_kebun: 'Kebun A',
                    deleted_at: null
                  }],
                  error: null
                };
              }
              return { data: [], error: null };
            }
          })
        }),
        upsert: async (payload) => {
          if (table === 'master_descriptors') {
            memMasterDescriptors.push(payload);
          }
          return { data: null, error: null };
        }
      };
    });

    memQueue.set('off_105b', {
      id: 'off_105b',
      nik: 'NIK-MISMATCH-105',
      name: 'Karyawan Asli Lokal',
      department: 'Afdeling 1',
      nama_kebun: 'Kebun A',
      descriptor_json: dummy1024,
      is_synced: false
    });

    const res = await syncPendingEmployees();
    expect(res.count).toBe(0);

    // Rekonsiliasi ditolak: item tidak dihapus, descriptor tidak tertimpa
    expect(memQueue.has('off_105b')).toBe(true);
    expect(memMasterDescriptors.length).toBe(0);
    const itemInQueue = memQueue.get('off_105b');
    expect(itemInQueue.last_error_code).toBe('RECONCILIATION_MISMATCH');
  });

  it('6. Kegagalan unggah foto (503 / Storage error) tidak menggagalkan pendaftaran inti karyawan', async () => {
    vi.spyOn(supabase, 'rpc').mockResolvedValue({
      data: { status: 'inserted', id: 9106 },
      error: null
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        upsert: async (payload) => {
          if (table === 'master_descriptors') memMasterDescriptors.push(payload);
          return { data: null, error: null };
        },
        select: () => ({
          limit: async () => ({ data: [], error: null }),
          eq: () => ({ limit: async () => ({ data: [], error: null }) })
        })
      };
    });

    // Storage sengaja dibuat gagal 503
    vi.spyOn(supabase.storage, 'from').mockImplementation(() => ({
      upload: async () => ({
        data: null,
        error: { statusCode: 503, message: 'Service Unavailable: Storage bucket down' }
      })
    }));

    memQueue.set('off_106', {
      id: 'off_106',
      nik: 'NIK-PHOTO-FAIL-106',
      name: 'Karyawan Inti Lolos',
      department: 'Bibitan',
      descriptor_json: dummy1024,
      is_synced: false
    });

    memThumbs.set('off_106', {
      employee_id: 'off_106',
      nik: 'NIK-PHOTO-FAIL-106',
      thumb_base64: dummyBase64,
      is_synced: false
    });

    const res = await syncPendingEmployees();
    // Langkah inti sukses penuh (1 karyawan synced)
    expect(res.count).toBe(1);
    expect(memQueue.has('off_106')).toBe(false);
    expect(memMasterDescriptors.length).toBe(1);
    expect(memMasterDescriptors[0].employee_id).toBe(9106);
  });

  it('7. Error terus-menerus (>= 5 kali) otomatis dialihkan ke status needs_attention tanpa membuang data', async () => {
    // Item antrean yang sudah gagal 4 kali
    memQueue.set('off_107', {
      id: 'off_107',
      nik: 'NIK-STALE-ERR',
      name: 'Karyawan Bermasalah',
      department: 'Bengkel',
      attempt_count: 4,
      sync_status: 'failed',
      is_synced: false
    });

    // Simulasi kegagalan ke-5
    await db.employee_sync_queue.updateError('off_107', {
      last_error_code: 'PERSISTENT_SERVER_ERR',
      last_error_message: 'Database connection failed persistently',
      last_error_hint: 'Periksa status server Supabase',
      last_error_step: 'employees_rpc'
    });

    const item = memQueue.get('off_107');
    expect(item).toBeDefined();
    expect(item.attempt_count).toBe(5);
    // Otomatis dialihkan ke 'needs_attention' agar tidak membebani sync latar belakang
    expect(item.sync_status).toBe('needs_attention');
    expect(item.last_error_hint).toContain('Periksa status server Supabase');
  });

  it('8. Pemulihan status processing yang macet (Crash / Stale Lease Recovery)', async () => {
    // Item 1: Ditinggalkan oleh proses run ID lama (crashed app instance)
    memQueue.set('off_stuck_1', {
      id: 'off_stuck_1',
      name: 'Karyawan Crash Lama',
      sync_status: 'processing',
      processing_run_id: 'OLD_CRASHED_SESSION_RUN_ID',
      processing_started_at: new Date(Date.now() - 3600000).toISOString(),
      attempt_count: 1,
      is_synced: false
    });

    // Item 2: Sesi saat ini tapi heartbeat macet > 2 menit
    memQueue.set('off_stuck_2', {
      id: 'off_stuck_2',
      name: 'Karyawan Heartbeat Macet',
      sync_status: 'processing',
      processing_run_id: APP_RUN_ID,
      processing_started_at: new Date(Date.now() - 300000).toISOString(),
      processing_heartbeat_at: new Date(Date.now() - 150000).toISOString(),
      attempt_count: 1,
      is_synced: false
    });

    const recoveredCount = await db.employee_sync_queue.recoverStuckProcessing(APP_RUN_ID);
    expect(recoveredCount).toBe(2);

    const it1 = memQueue.get('off_stuck_1');
    const it2 = memQueue.get('off_stuck_2');

    expect(it1.sync_status).toBe('pending');
    expect(it1.processing_run_id).toBeNull();

    expect(it2.sync_status).toBe('pending');
    expect(it2.processing_run_id).toBeNull();
  });

  it('9. diagnoseRegistrationSync: menghasilkan laporan tabel, bucket, dan markdown yang bisa disalin', async () => {
    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        select: () => ({
          limit: async () => {
            if (table === 'scan_attempts') {
              return { data: null, error: { code: '42P01', message: 'relation "scan_attempts" does not exist' } };
            }
            return { data: [{ id: 1 }], error: null };
          }
        })
      };
    });

    vi.spyOn(supabase.storage, 'from').mockImplementation((bucket) => ({
      list: async () => {
        if (bucket === 'attendance-photos') {
          return { data: [{ name: 'master.jpg' }], error: null };
        }
        return { data: null, error: { message: 'Bucket not found' } };
      }
    }));

    const diag = await diagnoseRegistrationSync();
    expect(diag).toBeDefined();
    expect(diag.timestamp).toBeDefined();
    expect(diag.tableChecks.length).toBe(4);
    expect(diag.bucketChecks.length).toBe(3);

    // Verifikasi deteksi tabel yang hilang (scan_attempts 42P01)
    const scanAttemptCheck = diag.tableChecks.find(t => t.table === 'scan_attempts');
    expect(scanAttemptCheck.status).toBe('ERROR');
    expect(scanAttemptCheck.code).toBe('42P01');

    // Verifikasi deteksi bucket attendance-photos yang OK
    const photoBucket = diag.bucketChecks.find(b => b.bucket === 'attendance-photos');
    expect(photoBucket.status).toBe('OK');

    // Verifikasi markdownTable tersedia dan memuat format tabel markdown
    expect(diag.markdownTable).toContain('# Laporan Diagnosa Sinkronisasi Pendaftaran');
    expect(diag.markdownTable).toContain('| `employees` |');
    expect(diag.markdownTable).toContain('| `scan_attempts` |');
  });
});
