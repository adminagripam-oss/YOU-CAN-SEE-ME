require('dotenv').config();
const express = require('express');
const cors = require('cors');
const path = require('path');
const { createClient } = require('@supabase/supabase-js');

const cookieParser = require('cookie-parser');
const authRoutes = require('./backend/routes/auth.routes');

const app = express();
const PORT = process.env.PORT || 8080;

// Middleware
app.use(cors({ origin: true, credentials: true }));
app.use(cookieParser());
app.use(express.json({ limit: '10mb' }));

const apiRouter = express.Router();
apiRouter.use('/auth', authRoutes);

app.use('/api', apiRouter);
app.use('/YOU-CAN-SEE-ME/api', apiRouter);

const fs = require('fs');
const distPath = path.join(__dirname, 'dist');
if (fs.existsSync(distPath)) {
  app.use(express.static(distPath));
}
app.use(express.static(path.join(__dirname, 'public')));

// Supabase Database Connection
const supabaseUrl = process.env.SUPABASE_URL;
const supabaseKey = process.env.SUPABASE_ANON_KEY;

if (!supabaseUrl || !supabaseKey) {
  console.error('[CRITICAL] Missing SUPABASE_URL or SUPABASE_ANON_KEY in .env file!');
  process.exit(1);
}

const supabase = createClient(supabaseUrl, supabaseKey);
console.log(`[SUPABASE] Connected to Supabase Cloud Database at: ${supabaseUrl}`);

// Seed Initial Sample Employees if Table is Empty
async function seedInitialData() {
  try {
    const { count, error } = await supabase.from('employees').select('*', { count: 'exact', head: true });
    if (error) {
      console.warn('[SUPABASE SEED WARN]', error.message);
      return;
    }

    if (count === 0) {
      console.log('[SUPABASE] Seeding initial sample employees into database...');
      await supabase.from('employees').insert([
        { nik: 'EMP-001', name: 'Budi Santoso', department: 'IT & Software Development' },
        { nik: 'EMP-002', name: 'Siti Rahma', department: 'Human Resources' },
        { nik: 'EMP-003', name: 'Andi Wijaya', department: 'Finance & Accounting' }
      ]);
      console.log('[SUPABASE] Seed completed successfully!');
    }
  } catch (err) {
    console.error('[SUPABASE SEED ERROR]', err.message);
  }
}
seedInitialData();

// ==========================================
// HELPER FUNCTIONS & UTILITIES
// ==========================================

/**
 * Calculates Euclidean Distance between two 128-dimensional Float vectors
 * Formula: sqrt( sum( (a[i] - b[i])^2 ) )
 */
function calculateEuclideanDistance(vecA, vecB) {
  if (vecA.length !== vecB.length) {
    throw new Error(`Dimension mismatch: vecA (${vecA.length}) vs vecB (${vecB.length})`);
  }
  let sumSquaredDiffs = 0;
  for (let i = 0; i < vecA.length; i++) {
    const diff = vecA[i] - vecB[i];
    sumSquaredDiffs += diff * diff;
  }
  return Math.sqrt(sumSquaredDiffs);
}

/**
 * Validates descriptor format: must be an array of exactly 128 numbers
 */
function isValidDescriptor(descriptor) {
  if (!Array.isArray(descriptor) || descriptor.length !== 128) {
    return false;
  }
  return descriptor.every(num => typeof num === 'number' && !isNaN(num));
}

// ==========================================
// API ENDPOINTS (SUPABASE BACKEND)
// ==========================================

// 1. GET /api/employees - List all employees with biometric status
apiRouter.get('/employees', async (req, res) => {
  try {
    const { data: employees, error: empErr } = await supabase
      .from('employees')
      .select('*')
      .order('name', { ascending: true });

    if (empErr) throw empErr;

    const { data: masters, error: masterErr } = await supabase
      .from('master_descriptors')
      .select('employee_id');

    if (masterErr) throw masterErr;

    const masterEmpIds = new Set(masters.map(m => m.employee_id));

    const result = employees.map(emp => ({
      ...emp,
      has_master_biometric: masterEmpIds.has(emp.id) ? 1 : 0
    }));

    res.json({ success: true, data: result });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
});

// 2. POST /api/employees - Register new employee
apiRouter.post('/employees', async (req, res) => {
  try {
    const { nik, name, department } = req.body;
    if (!nik || !name || !department) {
      return res.status(400).json({
        success: false,
        message: 'NIK, Nama, dan Departemen wajib diisi!'
      });
    }

    const { data, error } = await supabase
      .from('employees')
      .insert([{ nik: nik.trim(), name: name.trim(), department: department.trim() }])
      .select()
      .single();

    if (error) {
      if (error.code === '23505' || error.message.includes('unique')) {
        return res.status(400).json({ success: false, message: 'NIK sudah terdaftar!' });
      }
      throw error;
    }

    res.status(201).json({
      success: true,
      message: 'Karyawan berhasil ditambahkan ke Supabase.',
      data
    });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
});

// 2b. PUT /api/employees/:id - Update existing employee
apiRouter.put('/employees/:id', async (req, res) => {
  try {
    const { id } = req.params;
    const { nik, name, department } = req.body;

    if (!nik || !name || !department) {
      return res.status(400).json({ success: false, message: 'NIK, Nama, dan Departemen wajib diisi!' });
    }

    const { data, error } = await supabase
      .from('employees')
      .update({ nik: nik.trim(), name: name.trim(), department: department.trim() })
      .eq('id', id)
      .select()
      .single();

    if (error) {
      if (error.code === '23505' || error.message.includes('unique')) {
        return res.status(400).json({ success: false, message: 'NIK sudah digunakan oleh karyawan lain!' });
      }
      throw error;
    }

    res.json({
      success: true,
      message: `Data karyawan ${name} berhasil diperbarui!`,
      data
    });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
});

// 2c. DELETE /api/employees/:id - Delete employee and cascade biometrics & logs
apiRouter.delete('/employees/:id', async (req, res) => {
  try {
    const { id } = req.params;

    // Delete from Supabase
    const { error } = await supabase
      .from('employees')
      .delete()
      .eq('id', id);

    if (error) throw error;

    res.json({
      success: true,
      message: 'Karyawan beserta data biometrik master berhasil dihapus dari Supabase!'
    });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
});

// 3. POST /api/biometrics/register - Save / update master biometric descriptor (128 Float32)
apiRouter.post('/biometrics/register', async (req, res) => {
  try {
    const { employee_id, descriptor } = req.body;

    if (!employee_id) {
      return res.status(400).json({ success: false, message: 'ID Karyawan wajib diisi!' });
    }

    // Validation: 128 elements Float32 vector
    if (!isValidDescriptor(descriptor)) {
      return res.status(400).json({
        success: false,
        message: 'Validasi Gagal: Descriptor biometrik harus berupa Array 128 angka float!'
      });
    }

    // Check if employee exists
    const { data: employee, error: empErr } = await supabase
      .from('employees')
      .select('id, name, nik')
      .eq('id', employee_id)
      .single();

    if (empErr || !employee) {
      return res.status(404).json({ success: false, message: 'Karyawan tidak ditemukan!' });
    }

    // -------------------------------------------------------------
    // Anti-Duplication Check: Verifikasi apakah wajah sudah terdaftar untuk KARYAWAN LAIN
    // -------------------------------------------------------------
    const { data: existingMasters, error: fetchErr } = await supabase
      .from('master_descriptors')
      .select('employee_id, descriptor_json')
      .neq('employee_id', employee_id); // Abaikan jika karyawan meng-update foto masters sendiri

    if (!fetchErr && existingMasters && existingMasters.length > 0) {
      const otherEmpIds = existingMasters.map(m => m.employee_id);
      const { data: otherEmployees } = await supabase
        .from('employees')
        .select('id, nik, name')
        .in('id', otherEmpIds);

      const empMap = new Map((otherEmployees || []).map(e => [e.id, e]));

      const THRESHOLD = 0.55;
      for (const master of existingMasters) {
        if (Array.isArray(master.descriptor_json) && master.descriptor_json.length === 128) {
          const dist = calculateEuclideanDistance(descriptor, master.descriptor_json);
          if (dist < THRESHOLD) {
            const matchedEmp = empMap.get(master.employee_id) || { name: 'Karyawan Lain', nik: '-' };
            console.warn(`[DUPLICATE FACE REJECTED] Face matches existing employee: ${matchedEmp.name} (${matchedEmp.nik}) with distance ${dist.toFixed(4)}`);
            return res.status(400).json({
              success: false,
              message: `Registrasi Gagal: Wajah ini SUDAH TERDAFTAR atas nama karyawan "${matchedEmp.name}" (NIK: ${matchedEmp.nik})`,
              duplicate_employee: {
                name: matchedEmp.name,
                nik: matchedEmp.nik,
                distance: parseFloat(dist.toFixed(4))
              }
            });
          }
        }
      }
    }

    // Upsert Master Descriptor into Supabase
    const { error: upsertErr } = await supabase
      .from('master_descriptors')
      .upsert({
        employee_id: parseInt(employee_id),
        descriptor_json: descriptor, // Stored directly as JSON/JSONB
        updated_at: new Date().toISOString()
      }, { onConflict: 'employee_id' });

    if (upsertErr) throw upsertErr;

    console.log(`[BIOMETRIC / SUPABASE] Master face descriptor registered for: ${employee.name} (ID: ${employee_id})`);

    res.json({
      success: true,
      message: `Master Biometrik Wajah untuk ${employee.name} (NIK: ${employee.nik}) berhasil disimpan di Supabase Database!`,
      employee_id
    });
  } catch (error) {
    console.error('[ERROR /api/biometrics/register]:', error);
    res.status(500).json({ success: false, message: error.message });
  }
});

// 4. POST /api/attendance/verify - Core 1-to-1 Matching Endpoint (Check-In / Check-Out)
apiRouter.post('/attendance/verify', async (req, res) => {
  try {
    const { employee_id, nik, scan_descriptor, location = 'Kantor Pusat', attendance_type = 'CHECK_IN' } = req.body;

    // 1. Input Validation: Check Descriptor Format
    if (!isValidDescriptor(scan_descriptor)) {
      return res.status(400).json({
        success: false,
        message: 'Validasi Payload Gagal: scan_descriptor harus berupa Array bertipe Float32 berjumlah 128 elemen!'
      });
    }

    // 2. Fetch Employee Record
    let empQuery = supabase.from('employees').select('*');
    if (employee_id) {
      empQuery = empQuery.eq('id', employee_id);
    } else if (nik) {
      empQuery = empQuery.eq('nik', nik.trim());
    } else {
      return res.status(400).json({
        success: false,
        message: 'Wajib memberikan employee_id atau NIK untuk verifikasi 1-to-1!'
      });
    }

    const { data: employee, error: empErr } = await empQuery.single();

    if (empErr || !employee) {
      return res.status(404).json({
        success: false,
        message: 'Error 404: ID / NIK Karyawan tidak ditemukan dalam sistem database Supabase.'
      });
    }

    // 3. 1-to-1 Fetch: Backend HANYA mengambil 1 record master descriptor milik employee_id tersebut
    const { data: masterRecord, error: masterErr } = await supabase
      .from('master_descriptors')
      .select('descriptor_json')
      .eq('employee_id', employee.id)
      .single();

    if (masterErr || !masterRecord || !masterRecord.descriptor_json) {
      return res.status(404).json({
        success: false,
        message: `Error 404: Data Biometrik Master Wajah untuk karyawan "${employee.name}" (${employee.nik}) belum didaftarkan!`
      });
    }

    let masterDescriptor = masterRecord.descriptor_json;
    if (typeof masterDescriptor === 'string') {
      masterDescriptor = JSON.parse(masterDescriptor);
    }

    // 4. Calculate Euclidean Distance (1-to-1 Match)
    const distance = calculateEuclideanDistance(scan_descriptor, masterDescriptor);

    // 5. Evaluate Threshold
    const THRESHOLD = 0.55;
    const isVerified = distance < THRESHOLD;

    const typeLabel = attendance_type === 'CHECK_OUT' ? 'CHECK-OUT' : 'CHECK-IN';
    const status = isVerified ? `VERIFIKASI BERHASIL (${typeLabel})` : `VERIFIKASI GAGAL (${typeLabel})`;

    // 6. Log Attendance to Supabase Database (Pencatatan Audit Audit Log)
    const logPayload = {
      employee_id: employee.id,
      location: `${location} [${typeLabel}]`,
      status,
      euclidean_distance: parseFloat(distance.toFixed(4))
    };

    const { error: logInsertErr } = await supabase.from('attendance_logs').insert(logPayload);
    if (logInsertErr) {
      console.error('[SUPABASE ATTENDANCE LOG ERROR]:', logInsertErr.message);
    } else {
      console.log(`[SUPABASE ATTENDANCE LOG OK] ${typeLabel} recorded for ${employee.name} (${employee.nik})`);
    }

    console.log(`[VERIFY 1-to-1 / SUPABASE] Type: ${typeLabel} | Employee: ${employee.name} (${employee.nik}) | Distance: ${distance.toFixed(4)} | Status: ${status}`);

    const welcomeMsg = attendance_type === 'CHECK_OUT'
      ? `Absensi Keluar (Check-Out) Berhasil! Sampai Jumpa, ${employee.name}.`
      : `Absensi Masuk (Check-In) Berhasil! Selamat Datang, ${employee.name}.`;

    if (isVerified) {
      return res.json({
        success: true,
        status: 'VERIFIKASI BERHASIL',
        message: welcomeMsg,
        employee: {
          id: employee.id,
          nik: employee.nik,
          name: employee.name,
          department: employee.department
        },
        metrics: {
          euclidean_distance: parseFloat(distance.toFixed(4)),
          threshold: THRESHOLD,
          attendance_type: typeLabel,
          complexity: '1-to-1 Verification'
        },
        timestamp: new Date().toISOString()
      });
    } else {
      return res.status(400).json({
        success: false,
        status: 'VERIFIKASI GAGAL',
        message: `Verifikasi Gagal: Wajah yang terdeteksi tidak cocok dengan Master Biometrik ${employee.name}!`,
        employee: {
          id: employee.id,
          nik: employee.nik,
          name: employee.name
        },
        metrics: {
          euclidean_distance: parseFloat(distance.toFixed(4)),
          threshold: THRESHOLD,
          attendance_type: typeLabel,
          complexity: '1-to-1 Verification'
        }
      });
    }
  } catch (error) {
    console.error('[ERROR /api/attendance/verify]:', error);
    res.status(500).json({ success: false, message: 'Server error: ' + error.message });
  }
});

// 5. GET /api/attendance/logs - Retrieve attendance history with duration
apiRouter.get('/attendance/logs', async (req, res) => {
  try {
    const { data: logs, error: logsErr } = await supabase
      .from('attendance_logs')
      .select('*')
      .order('timestamp', { ascending: false })
      .limit(100);

    if (logsErr) throw logsErr;

    if (!logs || logs.length === 0) {
      return res.json({ success: true, data: [] });
    }

    // Get Employee details
    const empIds = [...new Set(logs.map(l => l.employee_id))];
    const { data: employees, error: empErr } = await supabase
      .from('employees')
      .select('id, nik, name, department')
      .in('id', empIds);

    if (empErr) throw empErr;

    const empMap = new Map(employees.map(e => [e.id, e]));

    // Build enriched logs with duration calculation
    // Group logs by employee per day to calculate duration between CHECK-IN and CHECK-OUT
    const enrichedLogs = logs.map(log => {
      const emp = empMap.get(log.employee_id) || {};
      const typeLabel = log.attendance_type || (log.status.includes('CHECK-OUT') || log.location.includes('CHECK-OUT') ? 'CHECK-OUT' : 'CHECK-IN');
      return {
        ...log,
        attendance_type: typeLabel,
        nik: emp.nik || '-',
        name: emp.name || 'Unknown',
        department: emp.department || '-',
        durasi: null // will be calculated client-side or via pairing below
      };
    });

    // For each CHECK-OUT, find the paired CHECK-IN of same employee same day
    for (let i = 0; i < enrichedLogs.length; i++) {
      const log = enrichedLogs[i];
      if (log.attendance_type === 'CHECK-OUT' && log.status.includes('BERHASIL')) {
        const checkoutTime = new Date(log.timestamp);
        // Look for most recent CHECK-IN by same employee before this checkout
        const paired = enrichedLogs.find(l =>
          l.employee_id === log.employee_id &&
          l.attendance_type === 'CHECK-IN' &&
          l.status.includes('BERHASIL') &&
          new Date(l.timestamp) < checkoutTime
        );
        if (paired) {
          const checkinTime = new Date(paired.timestamp);
          const diffMs = checkoutTime - checkinTime;
          const diffHrs = Math.floor(diffMs / 3600000);
          const diffMins = Math.floor((diffMs % 3600000) / 60000);
          log.durasi = `${diffHrs}j ${diffMins}m`;
        }
      }
    }

    res.json({ success: true, data: enrichedLogs });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
});

// 6. GET /api/attendance/status/:employeeId - Check today's attendance state
apiRouter.get('/attendance/status/:employeeId', async (req, res) => {
  try {
    const { employeeId } = req.params;

    // Today's date range in UTC
    const now = new Date();
    const todayStart = new Date(now.getFullYear(), now.getMonth(), now.getDate()).toISOString();
    const tomorrowStart = new Date(now.getFullYear(), now.getMonth(), now.getDate() + 1).toISOString();

    const { data: logs, error } = await supabase
      .from('attendance_logs')
      .select('*')
      .eq('employee_id', employeeId)
      .gte('timestamp', todayStart)
      .lt('timestamp', tomorrowStart)
      .order('timestamp', { ascending: true });

    if (error) throw error;

    const successLogs = (logs || []).filter(l => l.status.includes('BERHASIL'));
    const checkIns = successLogs.filter(l => {
      const t = l.attendance_type || '';
      return t.includes('CHECK-IN') || (!t.includes('CHECK-OUT') && !l.location?.includes('CHECK-OUT') && !l.status?.includes('CHECK-OUT'));
    });
    const checkOuts = successLogs.filter(l => {
      const t = l.attendance_type || '';
      return t.includes('CHECK-OUT') || l.location?.includes('CHECK-OUT') || l.status?.includes('CHECK-OUT');
    });

    const lastCheckIn = checkIns.length > 0 ? checkIns[checkIns.length - 1] : null;
    const lastCheckOut = checkOuts.length > 0 ? checkOuts[checkOuts.length - 1] : null;

    // Employee is considered "checked in" if last check-in is more recent than last check-out
    const checkedIn = lastCheckIn !== null && (
      lastCheckOut === null || new Date(lastCheckIn.timestamp) > new Date(lastCheckOut.timestamp)
    );

    res.json({
      success: true,
      employee_id: parseInt(employeeId),
      checked_in: checkedIn,
      check_in_time: lastCheckIn ? lastCheckIn.timestamp : null,
      check_out_time: lastCheckOut ? lastCheckOut.timestamp : null
    });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
});

// 7. DELETE /api/attendance/logs/:id - Delete single attendance log entry
apiRouter.delete('/attendance/logs/:id', async (req, res) => {
  try {
    const { id } = req.params;
    const { error } = await supabase
      .from('attendance_logs')
      .delete()
      .eq('id', id);

    if (error) throw error;

    console.log(`[SUPABASE ATTENDANCE LOG DELETE] Log ID: ${id} deleted successfully`);
    res.json({ success: true, message: `Log absensi (ID ${id}) berhasil dihapus dari database Supabase.` });
  } catch (error) {
    console.error('[ERROR DELETE SINGLE LOG]:', error);
    res.status(500).json({ success: false, message: error.message });
  }
});

// 8. DELETE /api/attendance/logs - Clear ALL attendance logs from Supabase
apiRouter.delete('/attendance/logs', async (req, res) => {
  try {
    // Delete all rows where id is not null (effectively clears all log rows)
    const { error } = await supabase
      .from('attendance_logs')
      .delete()
      .neq('id', 0);

    if (error) throw error;

    console.log(`[SUPABASE ATTENDANCE LOG CLEAR ALL] All attendance logs cleared successfully`);
    res.json({ success: true, message: 'Seluruh riwayat log absensi berhasil dihapus dari database Supabase.' });
  } catch (error) {
    console.error('[ERROR CLEAR ALL LOGS]:', error);
    res.status(500).json({ success: false, message: error.message });
  }
});

// 9. POST /api/attendance/sync - Batch Auto-Sync offline attendance logs from IndexedDB
apiRouter.post('/attendance/sync', async (req, res) => {
  try {
    const { items } = req.body;
    if (!Array.isArray(items) || items.length === 0) {
      return res.json({ success: true, count: 0, synced_ids: [] });
    }

    console.log(`[SUPABASE AUTO-SYNC] Received ${items.length} offline attendance logs to sync...`);

    const syncedIds = [];
    const insertPayloads = [];

    for (const item of items) {
      insertPayloads.push({
        employee_id: item.employee_id,
        location: item.location ? `${item.location} (Sync Offline)` : 'HP Mobile (Sync Offline)',
        status: item.status || 'VERIFIKASI BERHASIL',
        euclidean_distance: item.euclidean_distance || 0,
        timestamp: item.timestamp || new Date().toISOString()
      });
      syncedIds.push(item.id);
    }

    const { error } = await supabase.from('attendance_logs').insert(insertPayloads);
    if (error) throw error;

    console.log(`[SUPABASE AUTO-SYNC OK] ${syncedIds.length} offline records successfully inserted into database!`);
    res.json({
      success: true,
      count: syncedIds.length,
      synced_ids: syncedIds,
      message: `${syncedIds.length} log absensi offline berhasil disinkronkan ke Supabase.`
    });
  } catch (error) {
    console.error('[ERROR /api/attendance/sync]:', error);
    res.status(500).json({ success: false, message: error.message });
  }
});

// SPA Fallback Route: Serve dist/index.html for client-side routing
app.use((req, res, next) => {
  if (req.path.includes('/api/')) {
    return res.status(404).json({ success: false, message: `API Endpoint tidak ditemukan: ${req.method} ${req.path}` });
  }
  next();
});

app.get('*', (req, res, next) => {
  const distIndex = path.join(__dirname, 'dist', 'index.html');
  if (fs.existsSync(distIndex)) {
    return res.sendFile(distIndex);
  }
  const publicIndex = path.join(__dirname, 'public', 'index.html');
  if (fs.existsSync(publicIndex)) {
    return res.sendFile(publicIndex);
  }
  next();
});

// Process Error Handlers for Server Resilience
process.on('uncaughtException', (err) => {
  console.error('[SERVER UNCAUGHT EXCEPTION]:', err);
});

process.on('unhandledRejection', (reason) => {
  console.error('[SERVER UNHANDLED REJECTION]:', reason);
});

// Keep-alive interval to maintain persistent background daemon process
setInterval(() => {}, 1000 * 60 * 60);

// Start Server
app.listen(PORT, () => {
  console.log(`====================================================`);
  console.log(`🚀 Biometric 1-to-1 Face Verification Server (Supabase)`);
  console.log(`🌐 Server running at: http://localhost:${PORT}`);
  console.log(`⚡ Supabase Database Connected & Live!`);
  console.log(`====================================================`);
});
