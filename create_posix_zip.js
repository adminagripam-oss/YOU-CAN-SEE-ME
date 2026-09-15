import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';
import JSZip from 'jszip';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const version = process.argv[2] || '1.7.22';
const distDir = path.join(__dirname, 'dist');
const outPath = path.join(__dirname, `dist-${version}.zip`);

const zip = new JSZip();

function addFilesToZip(dir, zipDir) {
  const files = fs.readdirSync(dir);
  for (const file of files) {
    const fullPath = path.join(dir, file);
    const stat = fs.statSync(fullPath);
    
    // CRITICAL: Always use POSIX forward slashes for the zip path,
    // otherwise Android Capacitor unzip will fail with "windows path not supported"
    const zipPath = zipDir ? `${zipDir}/${file}` : file;

    if (stat.isDirectory()) {
      addFilesToZip(fullPath, zipPath);
    } else {
      const data = fs.readFileSync(fullPath);
      zip.file(zipPath, data);
    }
  }
}

console.log(`Packaging ${distDir} into POSIX-compliant ZIP...`);
addFilesToZip(distDir, '');

zip.generateAsync({
  type: 'nodebuffer',
  compression: 'DEFLATE',
  compressionOptions: {
    level: 9
  }
}).then(content => {
  fs.writeFileSync(outPath, content);
  console.log(`Successfully created ${outPath} with POSIX paths!`);
}).catch(err => {
  console.error('Error creating zip:', err);
  process.exit(1);
});
