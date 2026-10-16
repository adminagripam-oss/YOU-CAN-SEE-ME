import { describe, it, expect, beforeEach, vi } from 'vitest';
import fs from 'fs';

// Mock Capacitor to use native SQLite path
vi.mock('@capacitor/core', () => ({
  Capacitor: {
    isNativePlatform: () => true
  }
}));

// Mock sqliteService before importing db
vi.mock('./services/sqliteService', () => {
  const localThumbs = new Map();
  return {
    initSQLite: vi.fn(),
    sqliteSaveEmployeeFaceThumb: vi.fn(async (empId, thumbBase64, version = 1, checksum = null, nik = null) => {
      localThumbs.set(String(empId), {
        employee_id: String(empId),
        nik,
        thumb_base64: thumbBase64,
        photo_version: version,
        photo_checksum: checksum,
        updated_at: new Date().toISOString()
      });
      if (nik) {
        localThumbs.set(`nik_${nik}`, localThumbs.get(String(empId)));
      }
    }),
    sqliteGetEmployeeFaceThumbInfo: vi.fn(async (empId, nik = null) => {
      if (empId && localThumbs.has(String(empId))) {
        return localThumbs.get(String(empId));
      }
      if (nik && localThumbs.has(`nik_${nik}`)) {
        return localThumbs.get(`nik_${nik}`);
      }
      return null;
    }),
    sqliteGetEmployeeFaceThumb: vi.fn(async (empId, nik = null) => {
      const item = (empId && localThumbs.get(String(empId))) || (nik && localThumbs.get(`nik_${nik}`));
      return item ? item.thumb_base64 : null;
    }),
    _clearMockThumbs: () => localThumbs.clear()
  };
});

// Mock Supabase
vi.mock('./supabaseClient', () => {
  return {
    supabase: {
      from: vi.fn((table) => {
        return {
          select: vi.fn().mockReturnThis(),
          eq: vi.fn().mockReturnThis(),
          order: vi.fn().mockReturnThis(),
          limit: vi.fn().mockReturnThis(),
          maybeSingle: vi.fn(async () => ({
            data: {
              photo_url: 'https://supabase.co/storage/v1/object/public/attendance-photos/face_cloud_999.jpg',
              thumbnail_base64: null,
              storage_path: 'face_cloud_999.jpg',
              photo_checksum: 'cs_cloud_999',
              photo_version: 1,
              updated_at: '2026-10-17T00:00:00Z'
            },
            error: null
          }))
        };
      }),
      storage: {
        from: vi.fn(() => ({
          getPublicUrl: vi.fn((path) => ({
            data: { publicUrl: `https://supabase.co/storage/v1/object/public/attendance-photos/${path}` }
          }))
        }))
      }
    }
  };
});

// Import the function under test
import { getEmployeeMasterPhoto, saveEmployeeFaceThumb } from './db';
import { _clearMockThumbs } from './services/sqliteService';

describe('Single Source of Truth Foto Master Wajah', () => {
  beforeEach(() => {
    _clearMockThumbs();
    vi.clearAllMocks();
  });

  it('1. Prioritas 1: Mengambil foto dari penyimpanan lokal jika tersedia', async () => {
    const mockBase64 = 'data:image/jpeg;base64,LOCAL_MOCK_PHOTO_DATA';
    await saveEmployeeFaceThumb('101', mockBase64, 1, 'cs_101', 'NIK101');

    const result = await getEmployeeMasterPhoto('101', 'NIK101');
    expect(result.photoUrl).toBe(mockBase64);
    expect(result.sourceType).toBe('local_thumb');
    expect(result.photoVersion).toBe(1);
  });

  it('2. Rescan Wajah: Pembaruan foto lokal langsung dikembalikan oleh getEmployeeMasterPhoto', async () => {
    const oldBase64 = 'data:image/jpeg;base64,OLD_PHOTO_DATA';
    await saveEmployeeFaceThumb('102', oldBase64, 1, 'cs_old', 'NIK102');

    let current = await getEmployeeMasterPhoto('102', 'NIK102');
    expect(current.photoUrl).toBe(oldBase64);

    // Lakukan rescan wajah di lokal
    const newBase64 = 'data:image/jpeg;base64,NEW_RESCAN_PHOTO_DATA_V2';
    await saveEmployeeFaceThumb('102', newBase64, 2, 'cs_v2', 'NIK102');

    const updated = await getEmployeeMasterPhoto('102', 'NIK102');
    expect(updated.photoUrl).toBe(newBase64);
    expect(updated.photoVersion).toBe(2);
    expect(updated.photoUrl).not.toBe(oldBase64);
  });

  it('3. Lookup via NIK jika employee_id bertipe sementara (offline temporary)', async () => {
    const mockBase64 = 'data:image/jpeg;base64,OFFLINE_ENROLLED_PHOTO';
    await saveEmployeeFaceThumb('off_123', mockBase64, 2, 'cs_off', 'NIK999');

    // Coba ambil menggunakan NIK jika ID server belum sinkron
    const result = await getEmployeeMasterPhoto('off_123', 'NIK999');
    expect(result.photoUrl).toBe(mockBase64);
    expect(result.sourceType).toBe('local_thumb');
  });

  it('4. Prioritas 2 (Cloud Fallback): Jika cache lokal kosong, fallback ke Supabase dengan cache-buster', async () => {
    // ID server 999 tanpa data di lokal
    const result = await getEmployeeMasterPhoto('999', 'NIK999');
    expect(result).not.toBeNull();
    expect(result.sourceType).toBe('cloud_storage');
    expect(result.photoUrl).toContain('face_cloud_999.jpg');
    expect(result.photoUrl).toContain('?v=cs_cloud_999');
  });

  it('5. EmployeeFacePreviewModal adalah 100% Read-Only (TIDAK ADA tombol Re-Scan Wajah)', () => {
    const modalSource = fs.readFileSync('src/components/EmployeeFacePreviewModal.jsx', 'utf-8');
    const daftarKaryawanSource = fs.readFileSync('src/pages/DaftarKaryawanPage.jsx', 'utf-8');

    // Memastikan tidak ada teks tombol Re-Scan Wajah di preview modal
    expect(modalSource).not.toMatch(/<span>Re-Scan Wajah<\/span>/i);
    expect(modalSource).not.toMatch(/<span>Daftarkan Wajah<\/span>/i);
    expect(modalSource).not.toMatch(/onReScan/);

    // Memastikan modal memiliki tombol Tutup
    expect(modalSource).toMatch(/Tutup/);

    // Memastikan tidak ada variabel state yang tak terdefinisi (UI React State Integrity)
    expect(modalSource).not.toMatch(/setAttendanceDate/);
    expect(modalSource).not.toMatch(/attendanceDate/);

    // Memastikan DaftarKaryawanPage tidak lagi meneruskan onReScan
    expect(daftarKaryawanSource).not.toMatch(/<EmployeeFacePreviewModal[\s\S]*?onReScan/);
  });
});

