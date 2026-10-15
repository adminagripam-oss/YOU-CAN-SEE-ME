-- Migrasi: Perbaikan Kolom Telemetri attendance_logs
-- Berkas: supabase/migrations/20261015_fix_attendance_logs_columns.sql
-- Keterangan:
-- Pada migrasi sebelumnya (20261015_create_scan_attempts.sql baris 33-36), perintah ALTER TABLE
-- untuk tabel attendance_logs mungkin terlewat atau hanya dieksekusi sebagian pada database produksi Supabase.
-- Akibatnya, klien yang mencatat metrik biometrik (match_cosine, match_threshold, template_version, facing_mode)
-- mendapatkan error Postgres 42703 (undefined column) saat auto-sync mengirim kolom telemetri tersebut.
-- Berkas migrasi ini idempotent (menggunakan IF NOT EXISTS) dan menyelaraskan skema attendance_logs
-- dengan tipe data yang ditentukan di 20261015_create_scan_attempts.sql.

ALTER TABLE attendance_logs ADD COLUMN IF NOT EXISTS match_cosine REAL;
ALTER TABLE attendance_logs ADD COLUMN IF NOT EXISTS match_threshold REAL;
ALTER TABLE attendance_logs ADD COLUMN IF NOT EXISTS template_version INTEGER;
ALTER TABLE attendance_logs ADD COLUMN IF NOT EXISTS facing_mode TEXT;

COMMENT ON COLUMN attendance_logs.match_cosine IS 'Nilai kemiripan kosinus wajah saat verifikasi (0.0 - 1.0)';
COMMENT ON COLUMN attendance_logs.match_threshold IS 'Ambang batas verifikasi yang berlaku saat absensi (standar: 0.92)';
COMMENT ON COLUMN attendance_logs.template_version IS 'Versi template master wajah yang digunakan saat scan: 0 (upload foto), 1 (frontal kamera), 2 (multi-pose)';
COMMENT ON COLUMN attendance_logs.facing_mode IS 'Arah kamera saat pemindaian absensi: user (depan) atau environment (belakang)';
