require('dotenv').config();
const { createClient } = require('@supabase/supabase-js');

const supabaseUrl = process.env.SUPABASE_URL;
const supabaseKey = process.env.SUPABASE_ANON_KEY;

const supabase = createClient(supabaseUrl, supabaseKey);

async function testConnection() {
  console.log('Testing connection to Supabase:', supabaseUrl);
  
  // Test employees table
  const { data: employees, error: empErr } = await supabase.from('employees').select('*').limit(5);
  if (empErr) {
    console.log('Employees table check:', empErr.message);
  } else {
    console.log('Employees table found! Data:', employees);
  }

  // Test master_descriptors table
  const { data: masters, error: masterErr } = await supabase.from('master_descriptors').select('*').limit(5);
  if (masterErr) {
    console.log('Master_descriptors table check:', masterErr.message);
  } else {
    console.log('Master_descriptors table found! Data:', masters);
  }

  // Test attendance_logs table
  const { data: logs, error: logErr } = await supabase.from('attendance_logs').select('*').limit(5);
  if (logErr) {
    console.log('Attendance_logs table check:', logErr.message);
  } else {
    console.log('Attendance_logs table found! Data:', logs);
  }
}

testConnection();
