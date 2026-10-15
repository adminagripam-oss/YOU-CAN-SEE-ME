-- Migrasi: Telemetri Sesi Scan (scan_attempts) dan Metrik Akurasi Biometrik (attendance_logs)
-- Tanggal: 2026-10-15
-- Aman diulang (Idempotent)

CREATE TABLE IF NOT EXISTS scan_attempts (
    id BIGSERIAL PRIMARY KEY,
    employee_id TEXT,
    nik TEXT,
    attempt_type TEXT NOT NULL, -- 'CHECK-IN', 'CHECK-OUT', 'ENROLL-VERIFY', 'ENROLL-DUPLICATE'
    outcome TEXT NOT NULL,      -- 'MATCHED', 'NOT_MATCHED', 'ABORTED'
    median_cosine REAL,
    min_cosine REAL,
    max_cosine REAL,
    frames_used INTEGER,
    threshold_used REAL,
    facing_mode TEXT,
    canvas_w INTEGER,
    canvas_h INTEGER,
    brightness REAL,
    master_checksum TEXT,
    master_updated_at TIMESTAMPTZ,
    template_version INTEGER,
    app_version TEXT,
    platform TEXT,              -- 'android', 'web'
    created_at TIMESTAMPTZ DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_scan_attempts_employee_id ON scan_attempts(employee_id);
CREATE INDEX IF NOT EXISTS idx_scan_attempts_created_at ON scan_attempts(created_at);
CREATE INDEX IF NOT EXISTS idx_scan_attempts_outcome ON scan_attempts(outcome);

-- Kolom metrik biometrik tambahan pada attendance_logs
ALTER TABLE attendance_logs ADD COLUMN IF NOT EXISTS match_cosine REAL;
ALTER TABLE attendance_logs ADD COLUMN IF NOT EXISTS match_threshold REAL;
ALTER TABLE attendance_logs ADD COLUMN IF NOT EXISTS template_version INTEGER;
ALTER TABLE attendance_logs ADD COLUMN IF NOT EXISTS facing_mode TEXT;
