import { createClient } from '@supabase/supabase-js';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Import from .env manually since dotenv might not be available
const envFile = fs.readFileSync(path.join(__dirname, '.env'), 'utf-8');
const env = {};
envFile.split('\n').forEach(line => {
  const [key, ...values] = line.split('=');
  if (key && values.length > 0) {
    env[key.trim()] = values.join('=').trim().replace(/['"]/g, '');
  }
});

const SUPABASE_URL = env.VITE_SUPABASE_URL || env.SUPABASE_URL;
const SUPABASE_KEY = env.SUPABASE_SERVICE_ROLE_KEY;

if (!SUPABASE_URL || !SUPABASE_KEY) {
  console.error("Missing Supabase credentials in .env");
  process.exit(1);
}

const supabase = createClient(SUPABASE_URL, SUPABASE_KEY);

async function uploadOta(version) {
  const fileName = `dist-${version}.zip`;
  const filePath = path.join(__dirname, fileName);
  
  if (!fs.existsSync(filePath)) {
    console.error(`File ${filePath} not found!`);
    process.exit(1);
  }

  const fileBuffer = fs.readFileSync(filePath);
  
  console.log(`Uploading ${fileName} to Supabase Storage (ota-updates)...`);
  const { data, error } = await supabase.storage
    .from('ota-updates')
    .upload(fileName, fileBuffer, {
      contentType: 'application/zip',
      upsert: true
    });

  if (error) {
    console.error('Upload failed:', error);
    process.exit(1);
  }

  console.log('Upload successful!', data);
  
  // Update version.json locally
  const versionObj = {
    version: version,
    url: `${SUPABASE_URL}/storage/v1/object/public/ota-updates/${fileName}`
  };
  
  const versionJsonStr = JSON.stringify(versionObj, null, 2);
  const versionPath = path.join(__dirname, 'version.json');
  fs.writeFileSync(versionPath, versionJsonStr);
  console.log('Updated local version.json');

  // Upload version.json to Supabase
  console.log('Uploading version.json to Supabase Storage...');
  const { data: vData, error: vError } = await supabase.storage
    .from('ota-updates')
    .upload('version.json', fs.readFileSync(versionPath), {
      contentType: 'application/json',
      upsert: true
    });

  if (vError) {
    console.error('Failed to upload version.json:', vError);
  } else {
    console.log('Successfully updated remote version.json', vData);
  }
}

const version = process.argv[2];
if (!version) {
  console.error('Please provide a version number (e.g. 1.7.19)');
  process.exit(1);
}

uploadOta(version);
