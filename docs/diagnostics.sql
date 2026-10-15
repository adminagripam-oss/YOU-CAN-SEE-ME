-- ==============================================================================
-- AgriFace Biometric Diagnostics — Kueri Analitik Lapangan
-- Tanggal: 2026-10-15
-- Target Database: Supabase PostgreSQL
-- ==============================================================================
-- Catatan Kolom:
-- Kolom skor tertinggi pada `scan_attempts` tersimpan di `max_cosine` (atau `median_cosine`).
-- Digunakan ekspresi `COALESCE(max_cosine, median_cosine)` untuk kompatibilitas penuh.

-- ------------------------------------------------------------------------------
-- 1. Distribusi Tipe Data Vektor (Harus 100% 'array', bukan 'string')
-- ------------------------------------------------------------------------------
SELECT 
    jsonb_typeof(descriptor_json) AS tipe_data_vektor,
    count(*) AS jumlah_baris
FROM public.master_descriptors
GROUP BY 1
ORDER BY jumlah_baris DESC;

-- ------------------------------------------------------------------------------
-- 2. Status Checksum & Distribusi Template Version (v1 Legacy vs v2 Multi-Pose)
-- ------------------------------------------------------------------------------
SELECT 
    count(*) AS total_master_descriptors,
    count(*) FILTER (WHERE vector_checksum IS NULL) AS checksum_belum_dihitung,
    count(*) FILTER (WHERE vector_checksum IS NOT NULL) AS checksum_terhitung,
    count(*) FILTER (WHERE template_version = 1 OR template_version IS NULL) AS template_v1_legacy,
    count(*) FILTER (WHERE template_version = 2) AS template_v2_multipose
FROM public.master_descriptors;

-- ------------------------------------------------------------------------------
-- 3. Percobaan Scan 7 Hari Terakhir per Jam (WIB / Asia/Jakarta)
--    Menampilkan Total, Rata-rata Cosine, P10, P50 (Median), dan Breakdown Outcome
-- ------------------------------------------------------------------------------
SELECT 
    to_char(date_trunc('hour', created_at AT TIME ZONE 'Asia/Jakarta'), 'YYYY-MM-DD HH24:00') AS jam_wib,
    count(*) AS total_scan,
    round(avg(COALESCE(max_cosine, median_cosine))::numeric, 4) AS avg_cosine,
    round(percentile_cont(0.10) WITHIN GROUP (ORDER BY COALESCE(max_cosine, median_cosine))::numeric, 4) AS p10_cosine,
    round(percentile_cont(0.50) WITHIN GROUP (ORDER BY COALESCE(max_cosine, median_cosine))::numeric, 4) AS median_cosine,
    count(*) FILTER (WHERE outcome = 'MATCHED') AS outcome_matched,
    count(*) FILTER (WHERE outcome = 'TIMEOUT') AS outcome_timeout,
    count(*) FILTER (WHERE outcome = 'CANCELLED') AS outcome_cancelled,
    count(*) FILTER (WHERE outcome = 'NOT_MATCHED') AS outcome_not_matched
FROM public.scan_attempts
WHERE created_at >= (now() - interval '7 days')
  AND attempt_type = 'ATTENDANCE-SCAN'
GROUP BY date_trunc('hour', created_at AT TIME ZONE 'Asia/Jakarta')
ORDER BY jam_wib DESC;

-- ------------------------------------------------------------------------------
-- 4. Karyawan dengan Percobaan Gagal Terbanyak (Top 20) Beserta Cosine Tertinggi
-- ------------------------------------------------------------------------------
SELECT 
    sa.employee_id,
    e.name AS nama_karyawan,
    e.nik,
    count(*) AS total_gagal,
    count(*) FILTER (WHERE sa.outcome = 'TIMEOUT') AS gagal_timeout,
    count(*) FILTER (WHERE sa.outcome = 'CANCELLED') AS batal_cancelled,
    round(max(COALESCE(sa.max_cosine, sa.median_cosine))::numeric, 4) AS highest_failed_cosine,
    max(sa.template_version) AS template_version
FROM public.scan_attempts sa
LEFT JOIN public.employees e ON e.id::text = sa.employee_id
WHERE sa.outcome IN ('TIMEOUT', 'CANCELLED', 'NOT_MATCHED')
  AND sa.attempt_type = 'ATTENDANCE-SCAN'
GROUP BY sa.employee_id, e.name, e.nik
ORDER BY total_gagal DESC
LIMIT 20;

-- ------------------------------------------------------------------------------
-- 5. Pasangan Percobaan Pagi (< 10:00) vs Sore (>= 14:00) pada Hari yang Sama
--    Analisa Kunci Drop Cosine Sore Hari
-- ------------------------------------------------------------------------------
WITH morning_scans AS (
    SELECT 
        employee_id,
        (created_at AT TIME ZONE 'Asia/Jakarta')::date AS scan_date,
        max(COALESCE(max_cosine, median_cosine)) AS morning_max_cos
    FROM public.scan_attempts
    WHERE attempt_type = 'ATTENDANCE-SCAN'
      AND extract(hour from created_at AT TIME ZONE 'Asia/Jakarta') < 10
    GROUP BY employee_id, (created_at AT TIME ZONE 'Asia/Jakarta')::date
),
afternoon_scans AS (
    SELECT 
        employee_id,
        (created_at AT TIME ZONE 'Asia/Jakarta')::date AS scan_date,
        max(COALESCE(max_cosine, median_cosine)) AS afternoon_max_cos
    FROM public.scan_attempts
    WHERE attempt_type = 'ATTENDANCE-SCAN'
      AND extract(hour from created_at AT TIME ZONE 'Asia/Jakarta') >= 14
    GROUP BY employee_id, (created_at AT TIME ZONE 'Asia/Jakarta')::date
)
SELECT 
    m.scan_date,
    m.employee_id,
    e.name AS nama_karyawan,
    round(m.morning_max_cos::numeric, 4) AS max_cosine_pagi,
    round(a.afternoon_max_cos::numeric, 4) AS max_cosine_sore,
    round((m.morning_max_cos - a.afternoon_max_cos)::numeric, 4) AS selisih_drop_sore
FROM morning_scans m
JOIN afternoon_scans a ON m.employee_id = a.employee_id AND m.scan_date = a.scan_date
LEFT JOIN public.employees e ON e.id::text = m.employee_id
ORDER BY m.scan_date DESC, selisih_drop_sore DESC;

-- ------------------------------------------------------------------------------
-- 6. Jumlah Penolakan ENROLL-DUPLICATE per Hari
-- ------------------------------------------------------------------------------
SELECT 
    (created_at AT TIME ZONE 'Asia/Jakarta')::date AS tanggal_wib,
    count(*) AS total_duplikasi_ditolak,
    round(avg(COALESCE(max_cosine, median_cosine))::numeric, 4) AS avg_duplicate_cosine,
    round(max(COALESCE(max_cosine, median_cosine))::numeric, 4) AS highest_duplicate_cosine
FROM public.scan_attempts
WHERE attempt_type = 'ENROLL-DUPLICATE'
GROUP BY 1
ORDER BY tanggal_wib DESC;
