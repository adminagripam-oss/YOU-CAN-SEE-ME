-- Migration: Create employee_face_photos and setup storage policies
-- Date: 2026-10-15

CREATE TABLE IF NOT EXISTS public.employee_face_photos (
    id bigserial PRIMARY KEY,
    employee_id bigint NOT NULL REFERENCES public.employees(id) ON DELETE CASCADE,
    storage_path text NOT NULL,
    thumbnail_base64 text,
    captured_at timestamptz DEFAULT now(),
    is_primary boolean DEFAULT true,
    photo_version integer DEFAULT 1,
    photo_checksum text,
    updated_at timestamptz DEFAULT now(),
    created_at timestamptz DEFAULT now()
);

-- Backward compatibility safety if table already exists without newer columns
ALTER TABLE public.employee_face_photos ADD COLUMN IF NOT EXISTS photo_version integer DEFAULT 1;
ALTER TABLE public.employee_face_photos ADD COLUMN IF NOT EXISTS photo_checksum text;
ALTER TABLE public.employee_face_photos ADD COLUMN IF NOT EXISTS updated_at timestamptz DEFAULT now();

CREATE INDEX IF NOT EXISTS idx_employee_face_photos_emp 
ON public.employee_face_photos(employee_id);

-- Enable RLS
ALTER TABLE public.employee_face_photos ENABLE ROW LEVEL SECURITY;

-- Allow authenticated users to view face photos of employees in their scope
DROP POLICY IF EXISTS "Authenticated users can select face photos" ON public.employee_face_photos;
CREATE POLICY "Authenticated users can select face photos"
ON public.employee_face_photos FOR SELECT
TO authenticated
USING (true);

DROP POLICY IF EXISTS "Authenticated users can insert/update face photos" ON public.employee_face_photos;
CREATE POLICY "Authenticated users can insert/update face photos"
ON public.employee_face_photos FOR ALL
TO authenticated
USING (true)
WITH CHECK (true);

-- Insert bucket employee-faces if not already exists
INSERT INTO storage.buckets (id, name, public)
VALUES ('employee-faces', 'employee-faces', false)
ON CONFLICT (id) DO UPDATE SET public = false;

-- CATATAN KEAMANAN & ARSITEKTUR AKSES STORAGE:
-- Kunci `anon_key` Supabase tertanam langsung di dalam bundle JavaScript APK Android.
-- Jika policy Storage dibuat mengizinkan role `anon` untuk membaca atau membuat signed URL,
-- maka siapa pun yang mengekstrak kunci anon dari APK dapat mengakses seluruh foto biometrik.
-- Oleh karena itu, bucket 'employee-faces' disetel private (public = false) dan RLS hanya dibuka untuk authenticated.
-- Ini adalah KEPUTUSAN TERBUKA: Manajemen/Tim Arsitektur perlu memutuskan antara:
-- Pilihan A: Menggunakan backend Express proxy dengan service_role key untuk signing URL foto, ATAU
-- Pilihan B: Mengimplementasikan Supabase Auth (JWT) resmi untuk setiap perangkat mandor sebelum membuka akses Storage langsung.

