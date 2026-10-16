-- ==============================================================================
-- DRAF SQL: Kebijakan RLS (Row Level Security) untuk Tabel scan_attempts
-- ==============================================================================
-- CATATAN PERBAIKAN:
-- Error 42P01: relation "public.admins" does not exist terjadi karena pada
-- arsitektur AgriFace, tabel data admin di Supabase adalah "admin_auth",
-- dan otentikasi perangkat lapangan menggunakan Supabase Anon Key via RPC
-- (bukan Supabase Auth GoTrue / auth.uid()).
--
-- Query di bawah ini sudah disesuaikan dan IDEMPOTENT (aman di-run berulang kali).
-- ==============================================================================

-- 1. Pastikan Row Level Security aktif pada tabel scan_attempts
ALTER TABLE public.scan_attempts ENABLE ROW LEVEL SECURITY;

-- 2. Kebijakan INSERT:
-- Mengizinkan klien mobile (role anon & authenticated) mengirimkan telemetri
-- percobaan scan wajah ke tabel scan_attempts (mengatasi error 42501).
DROP POLICY IF EXISTS "Allow anon and authenticated to insert scan_attempts" ON public.scan_attempts;
DROP POLICY IF EXISTS "Allow anon insert scan_attempts" ON public.scan_attempts;
DROP POLICY IF EXISTS "Allow authenticated users to insert scan_attempts" ON public.scan_attempts;

CREATE POLICY "Allow anon and authenticated to insert scan_attempts"
ON public.scan_attempts
FOR INSERT
TO anon, authenticated
WITH CHECK (true);

-- 3. Kebijakan SELECT:
-- Mengizinkan pembacaan catatan telemetri oleh klien yang memiliki anon key / authenticated.
DROP POLICY IF EXISTS "Allow anon and authenticated to select scan_attempts" ON public.scan_attempts;
DROP POLICY IF EXISTS "Allow anon select scan_attempts" ON public.scan_attempts;
DROP POLICY IF EXISTS "Allow only head office admin to select scan_attempts" ON public.scan_attempts;

CREATE POLICY "Allow anon and authenticated to select scan_attempts"
ON public.scan_attempts
FOR SELECT
TO anon, authenticated
USING (true);

-- 4. Kebijakan Penuh untuk Service Role:
-- Service role (dashboard Supabase Studio / backend server) memiliki akses penuh.
DROP POLICY IF EXISTS "Allow service role all scan_attempts" ON public.scan_attempts;
CREATE POLICY "Allow service role all scan_attempts"
ON public.scan_attempts
FOR ALL
TO service_role
USING (true)
WITH CHECK (true);

-- ------------------------------------------------------------------------------
-- ANALISIS RISIKO KEAMANAN & REKOMENDASI MITIGASI:
-- ------------------------------------------------------------------------------
-- 1. Tabel scan_attempts hanya menyimpan telemetri diagnostik (skor kemiripan cosine,
--    jumlah frame dievaluasi, mode kamera, template_version, vector_source).
--    Tabel ini TIDAK menyimpan foto mentah, password, atau embedding 1024-D.
-- 2. Untuk mencegah potensi spam/disk exhaustion pada tabel telemetri, disarankan:
--    Menjalankan query pembersihan berkala (misal log lebih dari 30 hari) via pg_cron:
--    DELETE FROM public.scan_attempts WHERE created_at < NOW() - INTERVAL '30 days';
