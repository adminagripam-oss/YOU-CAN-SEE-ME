-- ==============================================================================
-- DOKUMEN USULAN: Pengetatan Kebijakan Row Level Security (RLS) & Storage
-- Berkas: docs/USULAN_POLICY_STORAGE_RLS.sql
-- Status: HANYA USULAN / TIDAK AKTIF (Semua pernyataan SQL dikomentari)
-- PERINGATAN: JANGAN JALANKAN LANGSUNG KE PRODUKSI TANPA PENGUJIAN LENGKAP
-- ==============================================================================
--
-- 1. LATAR BELAKANG DAN AUDIT PEMAKAIAN KODE
-- Berdasarkan hasil audit kode klien AgriFace:
--
-- A. Storage bucket 'attendance-photos':
--    - Upload absensi: storagePath = absensi_harian/{nik}/{uuid}.jpg (upsert: true) -> butuh INSERT
--    - Upload master:  storagePath = faces/{empId}/master.jpg (upsert: true)        -> butuh INSERT dan UPDATE
--    - Tampilan foto:  getPublicUrl(path) -> URL CDN publik (tidak butuh SELECT listing)
--    - Operasi DELETE: TIDAK PERNAH dipanggil dari aplikasi klien!
--    - Operasi LIST:   TIDAK PERNAH dipanggil dari aplikasi klien!
--
-- B. Tabel 'scan_attempts' (Audit Telemetri):
--    - Klien hanya memanggil supabase.from('scan_attempts').insert(rows) di syncEngine.js:1079
--    - Bersifat append-only. Tidak pernah melakukan SELECT, UPDATE, atau DELETE.
--
-- C. Tabel 'employee_face_photos' (Thumbnail Wajah):
--    - Klien memanggil upsert({ ... }, { onConflict: 'employee_id' }) di syncEngine.js:1145 -> butuh INSERT dan UPDATE
--    - Klien memanggil select(...) di syncEngine.js:1204 dan EmployeeFacePreviewModal.jsx:74, 101 -> butuh SELECT
--    - Tidak pernah melakukan DELETE.
--
-- ==============================================================================
-- 2. BATASAN KEAMANAN SUPABASE TANPA SUPABASE AUTH (PENTING DIBACA)
-- ==============================================================================
-- Karena AgriFace saat ini menggunakan autentikasi aplikasi internal (role kebun dan
-- username/password tersimpan di tabel app_users/employees) dan klien berkomunikasi
-- menggunakan 'anon key' Supabase tanpa JWT Supabase Auth (auth.uid() bernilai NULL):
--
-- 1. Pengetatan RLS dengan 'TO anon' atau 'TO public' HANYA membatasi jenis operasi
--    (misalnya: mengizinkan INSERT tapi melarang DELETE).
-- 2. Pengetatan berbasis baris kepemilikan pengguna (misal: "hanya kebun X yang boleh ubah data kebun X")
--    TIDAK DAPAT ditegakkan secara murni di level database RLS jika semua perangkat memakai anon key
--    yang sama, KECUALI jika:
--    a. Arsitektur beralih ke Supabase Auth resmi (email/password atau custom token JWT); ATAU
--    b. Menggunakan Backend Proxy (seperti backend/server.js) yang memvalidasi session token
--       sebelum meneruskan query ke Supabase dengan Service Role Key; ATAU
--    c. Menggunakan Postgres Function (RPC) dengan security definer yang memvalidasi token sesi.
--
-- ==============================================================================
-- 3. USULAN PERUBAHAN & ANALISIS RISIKO (SEMUA SQL DIKOMENTARI)
-- ==============================================================================

--------------------------------------------------------------------------------
-- USULAN 1: Hapus Kebijakan DELETE pada Storage Bucket 'attendance-photos'
--------------------------------------------------------------------------------
-- Alasan: Aplikasi tidak pernah menghapus foto di bucket. Memberikan policy DELETE
-- kepada role anon membuka risiko fatal penghapusan seluruh arsip foto oleh pihak luar.
-- Risiko memutus fungsi: RENDAH. Aplikasi tidak memiliki fitur hapus foto di storage.

-- DROP POLICY IF EXISTS "Anon Delete Attendance Photos" ON storage.objects;
-- DROP POLICY IF EXISTS "Public Delete Attendance Photos" ON storage.objects;


--------------------------------------------------------------------------------
-- USULAN 2: Batasi Operasi Storage Bucket 'attendance-photos' Menjadi INSERT, UPDATE, dan SELECT
--------------------------------------------------------------------------------
-- Catatan Kritis:
-- - Foto master wajah (faces/{empId}/master.jpg) di-upsert setiap kali ada rescan atau edit foto.
--   Oleh karena itu, policy UPDATE WAJIB dipertahankan untuk bucket ini. Jika UPDATE dihapus,
--   rescan master foto karyawan akan GAGAL (error: 403 / new row violates row-level security policy).
-- - Policy SELECT dipertahankan agar getPublicUrl / CDN publik dapat mengunduh gambar saat preview.

-- CREATE POLICY "Allow Anon Insert Attendance Photos" ON storage.objects
--   FOR INSERT TO anon WITH CHECK (bucket_id = 'attendance-photos');

-- CREATE POLICY "Allow Anon Update Master Photos" ON storage.objects
--   FOR UPDATE TO anon USING (bucket_id = 'attendance-photos')
--   WITH CHECK (bucket_id = 'attendance-photos');

-- CREATE POLICY "Allow Public Select Attendance Photos" ON storage.objects
--   FOR SELECT TO public USING (bucket_id = 'attendance-photos');


--------------------------------------------------------------------------------
-- USULAN 3: Aktifkan RLS pada Tabel 'scan_attempts' (Append-Only)
--------------------------------------------------------------------------------
-- Alasan: Saat ini relrowsecurity = false. Tabel ini menyimpan log deteksi biometrik.
-- Karena klien hanya mengirim log (insert), kita dapat membatasi akses anon hanya untuk INSERT.
-- Risiko memutus fungsi: RENDAH jika policy INSERT diizinkan untuk anon.
-- Jika admin HQ di kemudian hari ingin melihat laporan scan_attempts lewat dashboard web,
-- query SELECT dari akun admin kebun/HQ akan membutuhkan policy SELECT atau RPC.

-- ALTER TABLE scan_attempts ENABLE ROW LEVEL SECURITY;

-- CREATE POLICY "Allow Anon Insert Scan Attempts" ON scan_attempts
--   FOR INSERT TO anon WITH CHECK (true);

-- -- Hanya berikan SELECT jika dashboard klien membutuhkan pembacaan:
-- -- CREATE POLICY "Allow Anon Select Scan Attempts" ON scan_attempts
-- --   FOR SELECT TO anon USING (true);


--------------------------------------------------------------------------------
-- USULAN 4: Aktifkan RLS pada Tabel 'employee_face_photos'
--------------------------------------------------------------------------------
-- Alasan: Tabel ini menyimpan thumbnail base64 wajah. Saat ini relrowsecurity = false.
-- Klien butuh INSERT (saat pendaftaran), UPDATE (saat rescan/edit foto via upsert),
-- dan SELECT (saat modal preview wajah dibuka oleh Admin HQ / Admin Kebun).
-- Risiko memutus fungsi: TINGGI jika salah satu dari INSERT, UPDATE, atau SELECT terlewat.
-- Jika RLS diaktifkan, KETIGA operasi ini harus memiliki policy aktif untuk anon.

-- ALTER TABLE employee_face_photos ENABLE ROW LEVEL SECURITY;

-- CREATE POLICY "Allow Anon Select Face Photos" ON employee_face_photos
--   FOR SELECT TO anon USING (true);

-- CREATE POLICY "Allow Anon Insert Face Photos" ON employee_face_photos
--   FOR INSERT TO anon WITH CHECK (true);

-- CREATE POLICY "Allow Anon Update Face Photos" ON employee_face_photos
--   FOR UPDATE TO anon USING (true) WITH CHECK (true);

-- -- Larang DELETE: Tidak ada policy DELETE yang dibuat untuk anon.
