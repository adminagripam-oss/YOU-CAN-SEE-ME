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
