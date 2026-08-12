-- ============================================================
-- MIGRATION: Lengkapi kolom attendance_logs
-- Jalankan script ini SATU KALI di Supabase SQL Editor:
-- https://supabase.com/dashboard → SQL Editor → New Query
-- ============================================================

-- 1. Kolom durasi kerja (detik) — NULL jika belum CHECK-OUT
ALTER TABLE attendance_logs
  ADD COLUMN IF NOT EXISTS durasi INTEGER DEFAULT NULL;

-- 2. Tipe absensi standar ('CHECK-IN' atau 'CHECK-OUT')
ALTER TABLE attendance_logs
  ADD COLUMN IF NOT EXISTS attendance_type VARCHAR(20) DEFAULT NULL;

-- 3. Data karyawan yang di-embed langsung (menghindari JOIN saat query)
ALTER TABLE attendance_logs
  ADD COLUMN IF NOT EXISTS nik VARCHAR(50) DEFAULT NULL;

ALTER TABLE attendance_logs
  ADD COLUMN IF NOT EXISTS name VARCHAR(100) DEFAULT NULL;

ALTER TABLE attendance_logs
  ADD COLUMN IF NOT EXISTS department VARCHAR(100) DEFAULT NULL;

-- Verifikasi — semua kolom harus tampil
SELECT column_name, data_type, is_nullable
FROM information_schema.columns
WHERE table_name = 'attendance_logs'
ORDER BY ordinal_position;
