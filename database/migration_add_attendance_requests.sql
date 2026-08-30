-- ============================================================
-- MIGRATION: Membuat Tabel attendance_requests untuk Persetujuan Admin
-- Jalankan script ini di Supabase SQL Editor:
-- https://supabase.com/dashboard → SQL Editor → New Query
-- ============================================================

CREATE TABLE IF NOT EXISTS attendance_requests (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  request_type VARCHAR(20) NOT NULL, -- 'EDIT' | 'DELETE'
  log_id VARCHAR(100) NOT NULL, -- ID log absensi asal
  nik VARCHAR(50),
  name VARCHAR(100),
  nama_kebun VARCHAR(100),
  requested_by VARCHAR(100) NOT NULL, -- Username admin pemohon
  requested_at TIMESTAMP WITH TIME ZONE DEFAULT timezone('utc'::text, now()),
  status VARCHAR(20) DEFAULT 'PENDING', -- 'PENDING' | 'APPROVED' | 'REJECTED'
  old_value JSONB, -- Nilai lama (untuk edit)
  new_value JSONB -- Nilai baru (untuk edit)
);

-- Buat index untuk mempercepat pencarian data persetujuan
CREATE INDEX IF NOT EXISTS idx_attendance_requests_status ON attendance_requests(status);
CREATE INDEX IF NOT EXISTS idx_attendance_requests_requested_by ON attendance_requests(requested_by);

-- Matikan RLS atau izinkan akses untuk kebutuhan demo/operasional
ALTER TABLE attendance_requests DISABLE ROW LEVEL SECURITY;
