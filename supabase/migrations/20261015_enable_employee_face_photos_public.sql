-- ==============================================================================
-- AGRIFACE: AKTIVASI employee_face_photos & STORAGE BUCKET attendance-photos
-- Jalankan di: Supabase Dashboard -> SQL Editor -> New Query
-- Tanggal: 2026-10-15
-- ==============================================================================

-- 1. Nonaktifkan RLS pada employee_face_photos
ALTER TABLE public.employee_face_photos DISABLE ROW LEVEL SECURITY;

-- 2. Pastikan kolom pendukung tersedia dan storage_path nullable
ALTER TABLE public.employee_face_photos ADD COLUMN IF NOT EXISTS photo_version integer DEFAULT 1;
ALTER TABLE public.employee_face_photos ADD COLUMN IF NOT EXISTS photo_checksum text;
ALTER TABLE public.employee_face_photos ADD COLUMN IF NOT EXISTS updated_at timestamptz DEFAULT now();
ALTER TABLE public.employee_face_photos ALTER COLUMN storage_path DROP NOT NULL;

-- 3. Tambahkan UNIQUE Constraint pada employee_id untuk mendukung upsert(..., { onConflict: 'employee_id' })
CREATE UNIQUE INDEX IF NOT EXISTS idx_employee_face_photos_emp_unique 
  ON public.employee_face_photos(employee_id);

ALTER TABLE public.employee_face_photos 
  DROP CONSTRAINT IF EXISTS employee_face_photos_employee_id_key;

ALTER TABLE public.employee_face_photos 
  ADD CONSTRAINT employee_face_photos_employee_id_key 
  UNIQUE USING INDEX idx_employee_face_photos_emp_unique;

-- 4. Jadikan Storage bucket 'attendance-photos' publik penuh
UPDATE storage.buckets 
SET public = true 
WHERE id = 'attendance-photos';

-- 5. Tambahkan kebijakan akses Storage objects untuk role anon (INSERT, UPDATE, SELECT)
DROP POLICY IF EXISTS "Allow Anon Insert Attendance Photos" ON storage.objects;
CREATE POLICY "Allow Anon Insert Attendance Photos" 
  ON storage.objects FOR INSERT 
  TO anon 
  WITH CHECK (bucket_id = 'attendance-photos');

DROP POLICY IF EXISTS "Allow Anon Update Attendance Photos" ON storage.objects;
CREATE POLICY "Allow Anon Update Attendance Photos" 
  ON storage.objects FOR UPDATE 
  TO anon 
  USING (bucket_id = 'attendance-photos') 
  WITH CHECK (bucket_id = 'attendance-photos');

DROP POLICY IF EXISTS "Allow Anon Select Attendance Photos" ON storage.objects;
CREATE POLICY "Allow Anon Select Attendance Photos" 
  ON storage.objects FOR SELECT 
  TO anon 
  USING (bucket_id = 'attendance-photos');

-- 6. Verifikasi Status Akhir
SELECT 
  c.relname AS table_name,
  c.relrowsecurity AS rls_enabled,
  b.id AS bucket_id,
  b.public AS bucket_is_public
FROM pg_class c
CROSS JOIN storage.buckets b
WHERE c.relname = 'employee_face_photos'
  AND b.id = 'attendance-photos';
