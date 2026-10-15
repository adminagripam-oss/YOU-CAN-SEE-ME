-- ============================================================================
-- AGRIFACE: KUERI VERIFIKASI SKEMA SUPABASE (BACA-SAJA / READ-ONLY)
-- Berkas: docs/verify_schema.sql
-- Tujuan: Membuktikan status migrasi skema tabel, kolom, RLS, dan bucket storage
-- Seluruh kueri di bawah ini 100% aman dijalankan (hanya SELECT, tanpa modifikasi)
-- ============================================================================

-- ----------------------------------------------------------------------------
-- 1. Verifikasi Kolom pada Tabel master_descriptors
-- Menampilkan daftar kolom dan tipe data pada tabel master_descriptors.
-- Hasil yang benar (migrasi sukses):
--   Kolom 'template_version' (integer) dan 'vector_checksum' (text) terdaftar.
-- Hasil yang berarti migrasi belum jalan:
--   Hanya ada kolom id, employee_id, descriptor_json, updated_at.
-- ----------------------------------------------------------------------------
SELECT 
    column_name, 
    data_type, 
    is_nullable,
    column_default
FROM information_schema.columns
WHERE table_schema = 'public' 
  AND table_name = 'master_descriptors'
ORDER BY ordinal_position;


-- ----------------------------------------------------------------------------
-- 2. Verifikasi Keberadaan Tabel Baru (scan_attempts & employee_face_photos)
-- Menggunakan to_regclass untuk mengecek apakah tabel fisik sudah dibuat.
-- Hasil yang benar:
--   Kedua baris menghasilkan nama tabel (bukan NULL).
-- Hasil yang berarti migrasi belum jalan:
--   Salah satu atau kedua baris bernilai NULL.
-- ----------------------------------------------------------------------------
SELECT 
    'public.scan_attempts' AS target_table,
    to_regclass('public.scan_attempts') AS regclass_status
UNION ALL
SELECT 
    'public.employee_face_photos' AS target_table,
    to_regclass('public.employee_face_photos') AS regclass_status;


-- ----------------------------------------------------------------------------
-- 3. Verifikasi Tipe Data Elemen descriptor_json di master_descriptors
-- Memeriksa apakah vektor tersimpan sebagai native array JSONB atau string escape.
-- Hasil yang benar:
--   jsonb_typeof HANYA mengembalikan 'array' (misal: 100 baris tipe 'array').
-- Hasil yang berarti migrasi konversi belum tuntas:
--   Terdapat baris dengan tipe 'string' (vektor tersimpan sebagai string JSON kutip ganda).
-- ----------------------------------------------------------------------------
SELECT 
    jsonb_typeof(descriptor_json) AS vector_storage_type, 
    count(*) AS total_rows
FROM public.master_descriptors
GROUP BY 1;


-- ----------------------------------------------------------------------------
-- 4. Verifikasi Kelengkapan Checksum & Distribusi Template Version
-- Memeriksa berapa banyak master descriptor yang belum memiliki checksum,
-- serta persebaran template_version (v1: frontal legacy, v2: multi-pose).
-- Hasil yang benar:
--   vector_checksum_is_null bernilai 0 (seluruh baris terisi checksum).
--   template_version terdistribusi (1 atau 2).
-- Hasil yang berarti migrasi/sinkronisasi belum lengkap:
--   vector_checksum_is_null bernilai lebih dari 0.
-- ----------------------------------------------------------------------------
SELECT 
    COALESCE(template_version, 0) AS template_version,
    count(*) AS total_rows,
    count(*) FILTER (WHERE vector_checksum IS NULL) AS null_checksum_count,
    count(*) FILTER (WHERE vector_checksum IS NOT NULL) AS valid_checksum_count
FROM public.master_descriptors
GROUP BY COALESCE(template_version, 0)
ORDER BY template_version;


-- ----------------------------------------------------------------------------
-- 5. Verifikasi Kolom Metrik Biometrik Tambahan pada attendance_logs
-- Memeriksa 4 kolom telemetri akurasi yang ditambahkan ke tabel log absensi.
-- Hasil yang benar:
--   Kolom match_cosine, match_threshold, template_version, facing_mode terdaftar.
-- Hasil yang berarti migrasi belum jalan:
--   Keempat kolom tersebut tidak ditemukan (0 baris).
-- ----------------------------------------------------------------------------
SELECT 
    column_name, 
    data_type
FROM information_schema.columns
WHERE table_schema = 'public' 
  AND table_name = 'attendance_logs'
  AND column_name IN ('match_cosine', 'match_threshold', 'template_version', 'facing_mode')
ORDER BY column_name;


-- ----------------------------------------------------------------------------
-- 6. Verifikasi Status Row Level Security (RLS) pada Seluruh Tabel
-- Memeriksa apakah proteksi baris aktif (rowsecurity = true) pada tabel inti dan baru.
-- Hasil yang benar:
--   Seluruh tabel memiliki status rls_enabled = true.
-- Hasil yang rentan:
--   Terdapat tabel dengan rls_enabled = false.
-- ----------------------------------------------------------------------------
SELECT 
    relname AS table_name,
    relrowsecurity AS rls_enabled
FROM pg_class
WHERE relname IN ('master_descriptors', 'employees', 'attendance_logs', 'employee_face_photos', 'scan_attempts')
  AND relnamespace = 'public'::regnamespace
ORDER BY relname;


-- ----------------------------------------------------------------------------
-- 7. Verifikasi Daftar Kebijakan Akses (RLS Policies)
-- Menampilkan seluruh kebijakan akses yang terpasang pada tabel-tabel biometrik.
-- Hasil yang benar:
--   Terdapat policy terdaftar untuk SELECT, INSERT/UPDATE pada tabel yang bersangkutan.
-- ----------------------------------------------------------------------------
SELECT 
    tablename, 
    policyname, 
    roles, 
    cmd, 
    qual
FROM pg_policies
WHERE schemaname = 'public'
  AND tablename IN ('master_descriptors', 'employees', 'attendance_logs', 'employee_face_photos', 'scan_attempts')
ORDER BY tablename, policyname;


-- ----------------------------------------------------------------------------
-- 8. Verifikasi Status Bucket Supabase Storage
-- Memeriksa bucket apa saja yang terdaftar di storage.buckets dan status publiknya.
-- Hasil pengujian:
--   Bucket 'attendance-photos': apakah terdaftar dan bernilai public = true?
--   Bucket 'employee-faces': apakah terdaftar dan bernilai public = false?
-- Catatan fakta implementasi:
--   Kode aplikasi saat ini mengunggah foto master ke bucket 'attendance-photos' (public).
--   Migrasi SQL 20261015 mencoba membuat bucket 'employee-faces' (private).
-- ----------------------------------------------------------------------------
SELECT 
    id AS bucket_id, 
    name AS bucket_name, 
    public AS is_public,
    created_at,
    updated_at
FROM storage.buckets
WHERE id IN ('attendance-photos', 'employee-faces', 'employee-face-photos')
ORDER BY id;
