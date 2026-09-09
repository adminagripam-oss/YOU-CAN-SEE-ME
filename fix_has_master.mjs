import { createClient } from '@supabase/supabase-js';
import dotenv from 'dotenv';
import { readFileSync } from 'fs';

// Load .env
const env = dotenv.parse(readFileSync('.env'));
const supabaseUrl = env.VITE_SUPABASE_URL;
const supabaseKey = env.VITE_SUPABASE_ANON_KEY;

const supabase = createClient(supabaseUrl, supabaseKey);

async function run() {
  console.log('Fetching all master descriptors...');
  const { data: masters, error: err1 } = await supabase.from('master_descriptors').select('employee_id');
  if (err1) throw err1;

  console.log(`Found ${masters.length} master descriptors.`);
  const employeeIds = masters.map(m => m.employee_id);

  if (employeeIds.length === 0) {
    console.log('No descriptors found. Nothing to update.');
    return;
  }

  // Supabase IN clause has a limit, let's chunk it if needed. For now assume < 1000.
  console.log('Updating has_master_biometric to true for these employees...');
  const { error: err2 } = await supabase
    .from('employees')
    .update({ has_master_biometric: true })
    .in('id', employeeIds);
    
  if (err2) throw err2;
  console.log('Successfully updated existing employees in Supabase!');
}

run().catch(console.error);
