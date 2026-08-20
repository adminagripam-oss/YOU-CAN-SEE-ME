-- ============================================================
-- MIGRATION: RLS Policies untuk employees dan master_descriptors
-- Jalankan di: Supabase Dashboard → SQL Editor → New Query
-- ============================================================

-- 1. AKTIFKAN RLS (jika belum aktif)
ALTER TABLE employees ENABLE ROW LEVEL SECURITY;
ALTER TABLE master_descriptors ENABLE ROW LEVEL SECURITY;

-- 2. RESET POLICIES LAMA
DROP POLICY IF EXISTS "Allow anon read employees" ON employees;
DROP POLICY IF EXISTS "Allow anon insert employees" ON employees;
DROP POLICY IF EXISTS "Allow anon update employees" ON employees;
DROP POLICY IF EXISTS "Allow anon delete employees" ON employees;

DROP POLICY IF EXISTS "Allow anon read master_descriptors" ON master_descriptors;
DROP POLICY IF EXISTS "Allow anon insert master_descriptors" ON master_descriptors;
DROP POLICY IF EXISTS "Allow anon update master_descriptors" ON master_descriptors;
DROP POLICY IF EXISTS "Allow anon delete master_descriptors" ON master_descriptors;

-- 3. BUAT POLICIES UNTUK TABEL: employees (SELECT, INSERT, UPDATE, DELETE)
CREATE POLICY "Allow anon read employees"
  ON employees FOR SELECT
  TO anon
  USING (true);

CREATE POLICY "Allow anon insert employees"
  ON employees FOR INSERT
  TO anon
  WITH CHECK (true);

CREATE POLICY "Allow anon update employees"
  ON employees FOR UPDATE
  TO anon
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Allow anon delete employees"
  ON employees FOR DELETE
  TO anon
  USING (true);

-- 4. BUAT POLICIES UNTUK TABEL: master_descriptors (SELECT, INSERT, UPDATE, DELETE)
CREATE POLICY "Allow anon read master_descriptors"
  ON master_descriptors FOR SELECT
  TO anon
  USING (true);

CREATE POLICY "Allow anon insert master_descriptors"
  ON master_descriptors FOR INSERT
  TO anon
  WITH CHECK (true);

CREATE POLICY "Allow anon update master_descriptors"
  ON master_descriptors FOR UPDATE
  TO anon
  USING (true)
  WITH CHECK (true);

CREATE POLICY "Allow anon delete master_descriptors"
  ON master_descriptors FOR DELETE
  TO anon
  USING (true);

-- 5. VERIFIKASI POLICIES BERHASIL
SELECT tablename, policyname, cmd, roles, qual
FROM pg_policies
WHERE tablename IN ('employees', 'master_descriptors')
ORDER BY tablename, cmd;
