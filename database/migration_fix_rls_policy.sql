-- ============================================================
-- MIGRATION: Fix RLS Policy untuk DELETE pada attendance_logs
-- Jalankan di: Supabase Dashboard → SQL Editor → New Query
-- ============================================================

-- Aktifkan RLS jika belum aktif
ALTER TABLE attendance_logs ENABLE ROW LEVEL SECURITY;

-- Hapus policy lama jika ada (agar tidak konflik)
DROP POLICY IF EXISTS "Allow anon read attendance_logs" ON attendance_logs;
DROP POLICY IF EXISTS "Allow anon insert attendance_logs" ON attendance_logs;
DROP POLICY IF EXISTS "Allow anon update attendance_logs" ON attendance_logs;
DROP POLICY IF EXISTS "Allow anon delete attendance_logs" ON attendance_logs;
DROP POLICY IF EXISTS "Allow all for anon" ON attendance_logs;

-- Buat policy yang mengizinkan SEMUA operasi untuk anon role
-- (karena aplikasi ini adalah internal system tanpa auth user)
CREATE POLICY "Allow anon read attendance_logs"
  ON attendance_logs FOR SELECT
  TO anon
  USING (true);

CREATE POLICY "Allow anon insert attendance_logs"
  ON attendance_logs FOR INSERT
  TO anon
  WITH CHECK (true);

CREATE POLICY "Allow anon update attendance_logs"
  ON attendance_logs FOR UPDATE
  TO anon
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Allow anon delete attendance_logs"
  ON attendance_logs FOR DELETE
  TO anon
  USING (true);

-- Verifikasi policies berhasil dibuat
SELECT policyname, cmd, roles, qual
FROM pg_policies
WHERE tablename = 'attendance_logs';
