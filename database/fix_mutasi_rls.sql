DROP POLICY IF EXISTS "Allow all authenticated" ON public.employee_mutations;
DROP POLICY IF EXISTS "Allow all" ON public.employee_mutations;

CREATE POLICY "Allow all" ON public.employee_mutations
  FOR ALL USING (true);
