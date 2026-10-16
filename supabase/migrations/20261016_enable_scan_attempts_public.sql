-- Migrasi: Izinkan insert dan select telemetri scan_attempts dari role anon
-- Tanggal: 2026-10-16
-- Idempotent (aman dijalankan berulang)

ALTER TABLE scan_attempts ENABLE ROW LEVEL SECURITY;

DROP POLICY IF EXISTS "Allow anon insert scan_attempts" ON scan_attempts;
CREATE POLICY "Allow anon insert scan_attempts" ON scan_attempts
    FOR INSERT TO anon WITH CHECK (true);

DROP POLICY IF EXISTS "Allow anon select scan_attempts" ON scan_attempts;
CREATE POLICY "Allow anon select scan_attempts" ON scan_attempts
    FOR SELECT TO anon USING (true);

DROP POLICY IF EXISTS "Allow authenticated all scan_attempts" ON scan_attempts;
CREATE POLICY "Allow authenticated all scan_attempts" ON scan_attempts
    FOR ALL TO authenticated USING (true) WITH CHECK (true);
