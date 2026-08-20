-- ====================================================================
-- MIGRATION: Menambahkan Kolom Koordinat GPS pada Tabel attendance_logs
-- Jalankan di: Supabase Dashboard → SQL Editor → New Query
-- ====================================================================

-- 1. Tambahkan kolom latitude dan longitude (tipe DOUBLE PRECISION / float8)
ALTER TABLE public.attendance_logs
  ALTER COLUMN location DROP DEFAULT;

ALTER TABLE public.attendance_logs
  ADD COLUMN IF NOT EXISTS latitude DOUBLE PRECISION DEFAULT NULL,
  ADD COLUMN IF NOT EXISTS longitude DOUBLE PRECISION DEFAULT NULL;

-- 2. Berikan deskripsi komentar pada kolom untuk dokumentasi database
COMMENT ON COLUMN public.attendance_logs.latitude IS 'Koordinat Lintang (latitude) dari GPS perangkat saat absensi';
COMMENT ON COLUMN public.attendance_logs.longitude IS 'Koordinat Bujur (longitude) dari GPS perangkat saat absensi';

-- 3. Verifikasi struktur tabel terbaru
SELECT column_name, data_type, is_nullable
FROM information_schema.columns
WHERE table_name = 'attendance_logs'
ORDER BY ordinal_position;
