-- ============================================================
-- MIGRATION: Tambah kolom durasi ke tabel attendance_logs
-- Jalankan script ini di Supabase SQL Editor:
-- https://supabase.com/dashboard → SQL Editor → New Query
-- ============================================================

-- Tambah kolom durasi (dalam detik) ke attendance_logs
-- NULL = belum CHECK-OUT / data lama sebelum migrasi ini
ALTER TABLE attendance_logs
  ADD COLUMN IF NOT EXISTS durasi INTEGER DEFAULT NULL;

-- Tambah kolom attendance_type jika belum ada
ALTER TABLE attendance_logs
  ADD COLUMN IF NOT EXISTS attendance_type VARCHAR(20) DEFAULT NULL;

-- Verifikasi perubahan berhasil
SELECT column_name, data_type, is_nullable
FROM information_schema.columns
WHERE table_name = 'attendance_logs'
ORDER BY ordinal_position;
