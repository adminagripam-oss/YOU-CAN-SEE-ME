CREATE OR REPLACE FUNCTION public.trg_employee_onboarding()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO public.employee_mutations (
    employee_id, nik, name, mutation_type, effective_date,
    kebun_tujuan, afdeling_tujuan, keterangan, created_by
  ) VALUES (
    NEW.id, NEW.nik, NEW.name, 'ONBOARDING', CURRENT_DATE,
    NEW.nama_kebun, NEW.afdeling, 'Registrasi Karyawan Baru', 'system_auto'
  );
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER trg_employee_onboarding_insert
AFTER INSERT ON public.employees
FOR EACH ROW
EXECUTE FUNCTION public.trg_employee_onboarding();
