-- CREATE TABLE
CREATE TABLE IF NOT EXISTS public.employee_mutations (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  employee_id TEXT NOT NULL,
  nik TEXT NOT NULL,
  name TEXT NOT NULL,
  mutation_type TEXT NOT NULL CHECK (mutation_type IN ('ONBOARDING', 'PINDAH_KEBUN', 'PINDAH_AFDELING', 'OFFBOARDING')),
  effective_date DATE NOT NULL,
  kebun_asal TEXT,
  kebun_tujuan TEXT,
  afdeling_asal TEXT,
  afdeling_tujuan TEXT,
  keterangan TEXT,
  created_by TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- ENABLE RLS
ALTER TABLE public.employee_mutations ENABLE ROW LEVEL SECURITY;

-- CREATE POLICY
CREATE POLICY "Allow all authenticated" ON public.employee_mutations
  FOR ALL USING (auth.role() = 'authenticated');

-- BACKFILL DATA
INSERT INTO public.employee_mutations (
  employee_id, nik, name, mutation_type, effective_date,
  kebun_tujuan, afdeling_tujuan, keterangan, created_by
)
SELECT
  id::text,
  nik,
  name,
  'ONBOARDING',
  COALESCE(
    (created_at AT TIME ZONE 'UTC')::date,
    CURRENT_DATE
  ),
  nama_kebun,
  afdeling,
  'Data historis saat migrasi sistem',
  'system_migration'
FROM public.employees
ON CONFLICT DO NOTHING;
