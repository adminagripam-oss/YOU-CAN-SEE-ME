import { createClient } from '@supabase/supabase-js';
import dotenv from 'dotenv';
import { readFileSync } from 'fs';

const env = dotenv.parse(readFileSync('.env'));
const supabaseUrl = env.VITE_SUPABASE_URL;
const supabaseKey = env.VITE_SUPABASE_ANON_KEY;

const supabase = createClient(supabaseUrl, supabaseKey);

async function run() {
  console.log('Touch updated_at and set has_master_biometric: true for all employees in Supabase...');
  const now = new Date().toISOString();
  const { error } = await supabase
    .from('employees')
    .update({ 
      has_master_biometric: true,
      updated_at: now
    })
    .is('deleted_at', null);

  if (error) throw error;
  console.log('Successfully updated all employees in Supabase with updated_at timestamp!');
}

run().catch(console.error);
