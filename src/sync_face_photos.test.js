import { describe, it, expect, vi, beforeEach } from 'vitest';
import { db, dexieDb, getEmployeeFaceThumbInfo, normalizeEmployeeFaceThumbKeys } from './db';
import { syncPendingEmployees, syncPendingEmployeeFaceThumbs, syncPullEmployeeFacePhotos } from './syncEngine';

if (typeof navigator === 'undefined') {
  globalThis.navigator = { onLine: true };
} else {
  Object.defineProperty(navigator, 'onLine', { value: true, configurable: true });
}

describe('TAHAP 2: Reproduksi Kegagalan Sinkronisasi Foto Master Wajah (TEST SUITE)', () => {
  const memQueue = new Map();
  const memCache = new Map();
  const memThumbs = new Map();
  const memUploadedPhotos = [];
  const memStorageUploads = [];

  const dummy1024 = Array.from({ length: 1024 }, (_, i) => Math.sin(i * 0.05));
  const dummyBase64 = 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAP//////////////////////////////////////////////////////////////////////////////////////wgALCAABAAEBAREA/8QAFBABAAAAAAAAAAAAAAAAAAAAAP/aAAgBAQABPxA=';

  beforeEach(async () => {
    memQueue.clear();
    memCache.clear();
    memThumbs.clear();
    memUploadedPhotos.length = 0;
    memStorageUploads.length = 0;

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
      put: async () => {},
      delete: async () => {},
      bulkDelete: async () => {}
    };

    // Realistic IndexedDB mock: strict key matching (key type matters!)
    dexieDb.employee_face_thumbs = {
      get: async (key) => {
        return memThumbs.get(key) || null;
      },
      put: async (val) => {
        memThumbs.set(val.employee_id, val);
        return val.employee_id;
      },
      delete: async (key) => {
        memThumbs.delete(key);
      },
      filter: (predicate) => ({
        toArray: async () => Array.from(memThumbs.values()).filter(predicate)
      }),
      bulkPut: async (arr) => {
        arr.forEach(val => memThumbs.set(val.employee_id, val));
      },
      update: async (key, changes) => {
        const item = memThumbs.get(key);
        if (item) {
          memThumbs.set(key, { ...item, ...changes });
        }
      },
      toArray: async () => Array.from(memThumbs.values())
    };
  });

  it('Kasus 1: Pendaftaran baru offline harus mengunggah foto master dengan ID server saat syncPendingEmployees dijalankan', async () => {
    const { supabase } = await import('./supabaseClient');

    vi.spyOn(supabase, 'rpc').mockImplementation(async (fnName, { payload }) => {
      return {
        data: {
          status: 'inserted',
          id: 8801
        },
        error: null
      };
    });

    vi.spyOn(supabase.storage, 'from').mockImplementation((bucket) => {
      return {
        upload: async (path, blob, opts) => {
          memStorageUploads.push({ bucket, path, blob });
          return { data: { path }, error: null };
        },
        getPublicUrl: (path) => ({
          data: { publicUrl: `https://fake.supabase.co/storage/v1/object/public/${bucket}/${path}` }
        })
      };
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        upsert: async (payload, opts) => {
          if (table === 'employee_face_photos') {
            memUploadedPhotos.push(payload);
          }
          return { data: payload, error: null };
        },
        update: async () => ({ eq: async () => ({ error: null }) }),
        delete: async () => ({ eq: async () => ({ error: null }) }),
        select: () => ({
          eq: () => ({ maybeSingle: async () => ({ data: null, error: null }) }),
          in: () => ({ error: null, data: [] })
        })
      };
    });

    await dexieDb.employee_sync_queue.put({
      id: 'off_emp_100',
      nik: 'NIK-TEST-FOTO',
      name: 'Pemanen Foto',
      department: 'Pemanen',
      afdeling: 'AFD 1',
      nama_kebun: 'Bukit Harapan I',
      descriptor_json: dummy1024,
      template_version: 2
    });

    await dexieDb.employee_face_thumbs.put({
      employee_id: 'off_emp_100',
      thumb_base64: dummyBase64,
      photo_version: 1,
      is_synced: false
    });

    await syncPendingEmployees();

    expect(memStorageUploads.length).toBeGreaterThan(0);
    expect(memStorageUploads[0].path).toBe('faces/8801/master.jpg');

    expect(memUploadedPhotos.length).toBeGreaterThan(0);
    expect(memUploadedPhotos[0].employee_id).toBe(8801);

    const thumbInfo = await getEmployeeFaceThumbInfo('8801');
    expect(thumbInfo).not.toBeNull();
    expect(thumbInfo.is_synced).toBe(true);
  });

  it('Kasus 2: Rescan karyawan ber-ID server harus mengunggah foto master dengan versi naik', async () => {
    const { supabase } = await import('./supabaseClient');

    vi.spyOn(supabase.storage, 'from').mockImplementation((bucket) => {
      return {
        upload: async (path, blob, opts) => {
          memStorageUploads.push({ bucket, path, blob });
          return { data: { path }, error: null };
        },
        getPublicUrl: (path) => ({
          data: { publicUrl: `https://fake.supabase.co/storage/v1/object/public/${bucket}/${path}` }
        })
      };
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        upsert: async (payload, opts) => {
          if (table === 'employee_face_photos') {
            memUploadedPhotos.push(payload);
          }
          return { data: payload, error: null };
        },
        update: async () => ({ eq: async () => ({ error: null }) }),
        delete: async () => ({ eq: async () => ({ error: null }) }),
        select: () => ({
          eq: () => ({ maybeSingle: async () => ({ data: null, error: null }) }),
          in: () => ({ error: null, data: [] })
        })
      };
    });

    // Karyawan sudah punya ID server 1202, melakukan rescan
    await dexieDb.employee_face_thumbs.put({
      employee_id: '1202',
      thumb_base64: dummyBase64,
      photo_version: 2,
      is_synced: false
    });

    await syncPendingEmployeeFaceThumbs();

    expect(memStorageUploads.length).toBeGreaterThan(0);
    expect(memStorageUploads[0].path).toBe('faces/1202/master.jpg');
    expect(memUploadedPhotos.length).toBeGreaterThan(0);
    expect(memUploadedPhotos[0].photo_version).toBe(2);

    const thumbInfo = await getEmployeeFaceThumbInfo('1202');
    expect(thumbInfo.is_synced).toBe(true);
  });

  it('Kasus 3: Kegagalan langkah foto tidak menggagalkan pendaftaran inti karyawan', async () => {
    const { supabase } = await import('./supabaseClient');

    vi.spyOn(supabase, 'rpc').mockImplementation(async () => {
      return {
        data: {
          status: 'inserted',
          id: 9901
        },
        error: null
      };
    });

    vi.spyOn(supabase.storage, 'from').mockImplementation(() => {
      return {
        upload: async () => {
          return { data: null, error: new Error('Storage bucket connection failed 503') };
        }
      };
    });

    vi.spyOn(supabase, 'from').mockImplementation(() => {
      return {
        upsert: async () => ({ data: null, error: null }),
        update: async () => ({ eq: async () => ({ error: null }) }),
        delete: async () => ({ eq: async () => ({ error: null }) }),
        select: () => ({
          eq: () => ({ maybeSingle: async () => ({ data: null, error: null }) }),
          in: () => ({ error: null, data: [] })
        })
      };
    });

    await dexieDb.employee_sync_queue.put({
      id: 'off_emp_999',
      nik: 'NIK-CORE-PASS',
      name: 'Inti Berhasil',
      department: 'Pemanen',
      afdeling: 'AFD 2',
      nama_kebun: 'Bukit Harapan I',
      descriptor_json: dummy1024,
      template_version: 2
    });

    await dexieDb.employee_face_thumbs.put({
      employee_id: 'off_emp_999',
      thumb_base64: dummyBase64,
      photo_version: 1,
      is_synced: false
    });

    // Pendaftaran inti harus tetap sukses dan dihapus dari employee_sync_queue
    const res = await syncPendingEmployees();
    expect(res).toBeDefined();

    // Data karyawan inti harus sudah masuk employees_cache dengan ID 9901
    const cached = await dexieDb.employees_cache.get('9901');
    expect(cached).not.toBeNull();

    // Thumbnail lokal tidak boleh hilang saat foto gagal diunggah
    const thumb = await getEmployeeFaceThumbInfo('9901') || await getEmployeeFaceThumbInfo('off_emp_999');
    expect(thumb).not.toBeNull();
    expect(thumb.thumb_base64).toBe(dummyBase64);
  });

  it('Kasus 4: Unduhan server tidak boleh menimpa thumbnail lokal yang berstatus pending (is_synced: false)', async () => {
    const { supabase } = await import('./supabaseClient');

    // Local thumbnail is pending (user just rescanned offline)
    const localNewThumb = dummyBase64 + '_NEW_LOCAL';
    await dexieDb.employee_face_thumbs.put({
      employee_id: '1202',
      thumb_base64: localNewThumb,
      photo_version: 3,
      is_synced: false
    });

    // Server returns older or conflicting photo
    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        select: () => ({
          in: () => ({
            not: () => ({
              data: [{
                employee_id: 1202,
                thumbnail_base64: dummyBase64 + '_OLD_SERVER',
                photo_version: 2,
                photo_checksum: 'cs_old',
                updated_at: new Date().toISOString()
              }],
              error: null
            })
          })
        })
      };
    });

    await syncPullEmployeeFacePhotos([1202]);

    // Local pending thumbnail must WIN and NOT be overwritten
    const current = await getEmployeeFaceThumbInfo('1202');
    expect(current.thumb_base64).toBe(localNewThumb);
    expect(current.is_synced).toBe(false);
  });

  it('Kasus 5: Normalisasi key Dexie employee_face_thumbs lama dari number menjadi string', async () => {
    // Simpan item lama ber-key number
    await dexieDb.employee_face_thumbs.put({
      employee_id: 1202,
      thumb_base64: dummyBase64,
      photo_version: 1,
      is_synced: true
    });

    // Verifikasi sebelum migrasi
    const beforeRaw = await dexieDb.employee_face_thumbs.get(1202);
    expect(beforeRaw).not.toBeNull();
    expect(typeof beforeRaw.employee_id).toBe('number');

    // Jalankan normalisasi
    const result = await normalizeEmployeeFaceThumbKeys();
    expect(result.converted).toBe(1);

    // Key number lama harus sudah dihapus
    const oldKey = await dexieDb.employee_face_thumbs.get(1202);
    expect(oldKey).toBeNull();

    // Key string baru harus ada dan data utuh
    const newKey = await dexieDb.employee_face_thumbs.get('1202');
    expect(newKey).not.toBeNull();
    expect(typeof newKey.employee_id).toBe('string');
    expect(newKey.thumb_base64).toBe(dummyBase64);
  });

  it('Kasus 6: Normalisasi yang gagal di tengah (verifikasi gagal) tidak menghilangkan data lama', async () => {
    await dexieDb.employee_face_thumbs.put({
      employee_id: 5555,
      thumb_base64: dummyBase64,
      photo_version: 1,
      is_synced: true
    });

    // Simulasikan kegagalan baca kembali saat verifikasi
    const originalGet = dexieDb.employee_face_thumbs.get;
    dexieDb.employee_face_thumbs.get = vi.fn().mockImplementation(async (key) => {
      if (key === '5555') return null; // simulasi corrupt/baca gagal
      return originalGet(key);
    });

    const result = await normalizeEmployeeFaceThumbKeys();
    expect(result.converted).toBe(0);
    expect(result.error).toBe('verification_failed');

    // Data lama dengan key number 5555 HARUS TETAP ADA dan TIDAK HILANG
    dexieDb.employee_face_thumbs.get = originalGet;
    const oldStillExists = await dexieDb.employee_face_thumbs.get(5555);
    expect(oldStillExists).not.toBeNull();
    expect(oldStillExists.employee_id).toBe(5555);
  });

  it('Kasus 7: Normalisasi dijalankan dua kali bersifat idempotent (run twice)', async () => {
    await dexieDb.employee_face_thumbs.put({
      employee_id: 7777,
      thumb_base64: dummyBase64,
      photo_version: 1,
      is_synced: true
    });

    // Run 1
    const run1 = await normalizeEmployeeFaceThumbKeys();
    expect(run1.converted).toBe(1);

    // Run 2 (langsung no-op karena sudah string)
    const run2 = await normalizeEmployeeFaceThumbKeys();
    expect(run2.converted).toBe(0);

    // Data tetap utuh
    const item = await dexieDb.employee_face_thumbs.get('7777');
    expect(item).not.toBeNull();
    expect(item.thumb_base64).toBe(dummyBase64);
  });

  it('Kasus 8: Rekonsiliasi NIK berhasil memetakan thumbnail ID sementara ke ID server resmi saat NIK cocok', async () => {
    const { supabase } = await import('./supabaseClient');

    vi.spyOn(supabase.storage, 'from').mockImplementation((bucket) => {
      return {
        upload: async (path, blob) => {
          memStorageUploads.push({ bucket, path, blob });
          return { data: { path }, error: null };
        },
        getPublicUrl: (path) => ({
          data: { publicUrl: `https://fake.supabase.co/storage/v1/object/public/${bucket}/${path}` }
        })
      };
    });

    vi.spyOn(supabase, 'from').mockImplementation((table) => {
      return {
        upsert: async (payload) => {
          if (table === 'employee_face_photos') {
            memUploadedPhotos.push(payload);
          }
          return { data: payload, error: null };
        },
        update: async () => ({ eq: async () => ({ error: null }) }),
        delete: async () => ({ eq: async () => ({ error: null }) }),
        select: () => ({
          eq: () => ({ maybeSingle: async () => ({ data: null, error: null }) }),
          in: () => ({ error: null, data: [] })
        })
      };
    });

    // Simulasi: Karyawan sudah ada di employees_cache dengan ID resmi 1234 dan NIK 'KB-RECON-01'
    await dexieDb.employees_cache.put({
      id: 1234,
      nik: 'KB-RECON-01',
      name: 'Pemanen Rekonsiliasi',
      department: 'Pemanen'
    });

    // Thumbnail tersimpan dengan ID sementara 'off_emp_9999' namun memiliki properti nik 'KB-RECON-01'
    await dexieDb.employee_face_thumbs.put({
      employee_id: 'off_emp_9999',
      nik: 'KB-RECON-01',
      thumb_base64: dummyBase64,
      photo_version: 1,
      is_synced: false
    });

    const res = await syncPendingEmployeeFaceThumbs();
    expect(res.count).toBe(1);
    expect(res.syncedCount).toBe(1);

    expect(memUploadedPhotos.length).toBeGreaterThan(0);
    expect(memUploadedPhotos[0].employee_id).toBe(1234);

    const remapped = await dexieDb.employee_face_thumbs.get('1234');
    expect(remapped).not.toBeNull();
    expect(remapped.is_synced).toBe(true);

    const oldThumb = await dexieDb.employee_face_thumbs.get('off_emp_9999');
    expect(oldThumb).toBeNull();
  });
});


