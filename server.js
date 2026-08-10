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

// Handle malformed JSON body to prevent server crash
app.use((err, req, res, next) => {
  if (err instanceof SyntaxError && err.status === 400 && 'body' in err) {
    console.error('[SERVER ERROR] Malformed JSON detected:', err.message);
    return res.status(400).json({ success: false, message: 'Invalid JSON format in request body' });
  }
  next();
});
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
 * Validates descriptor format: must be an array of numbers
 */
function isValidDescriptor(descriptor) {
  if (!Array.isArray(descriptor) || descriptor.length === 0) {
    return false;
  }
  return descriptor.every((val) => typeof val === 'number');
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
    const { nik, name, department, face_vector } = req.body;

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

    // Optional: Update Biometrics if face_vector is provided
    if (face_vector) {
      const { error: bioErr } = await supabase
        .from('master_biometrics')
        .update({ face_vector })
        .eq('employee_id', id);

      if (bioErr) {
        console.warn('[WARN] Failed to update biometrics for employee', id, bioErr);
      }
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

// 3. POST /api/biometrics/register - Save / update master biometric descriptor
apiRouter.post('/biometrics/register', async (req, res) => {
  try {
    const { employee_id, descriptor } = req.body;

    if (!employee_id) {
      return res.status(400).json({ success: false, message: 'ID Karyawan wajib diisi!' });
    }

    // Validation: Array of numbers
    if (!isValidDescriptor(descriptor)) {
      return res.status(400).json({
        success: false,
        message: 'Validasi Gagal: Descriptor biometrik harus berupa Array angka float!'
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

    // Upsert Master Descriptor into Supabase (using master_biometrics)
    const { error: upsertErr } = await supabase
      .from('master_biometrics')
      .upsert({
        employee_id: parseInt(employee_id),
        face_vector: JSON.stringify(descriptor)
      }, { onConflict: 'employee_id' });

    if (upsertErr) throw upsertErr;

    // Update has_master_biometric flag
    await supabase.from('employees').update({ has_master_biometric: true }).eq('id', employee_id);

    res.json({
      success: true,
      message: `Master Biometrik Wajah untuk ${employee.name} (NIK: ${employee.nik}) berhasil disimpan di Supabase Database!`,
      data: { employee_id }
    });

  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
});

// 3b. GET /api/biometrics/master/:employeeId - Get master face descriptor for matching
apiRouter.get('/biometrics/master/:employeeId', async (req, res) => {
  try {
    const { employeeId } = req.params;

    const { data: master } = await supabase
      .from('master_descriptors')
      .select('descriptor_json')
      .eq('employee_id', employeeId)
      .maybeSingle();

    const { data: emp } = await supabase
      .from('employees')
      .select('descriptor_json, geometric_descriptor_json')
      .eq('id', employeeId)
      .maybeSingle();

    let descriptor_json = master?.descriptor_json || emp?.descriptor_json || null;
    let geometric_descriptor_json = emp?.geometric_descriptor_json || null;

    if (typeof descriptor_json === 'string') {
      try { descriptor_json = JSON.parse(descriptor_json); } catch {}
    }
    if (typeof geometric_descriptor_json === 'string') {
      try { geometric_descriptor_json = JSON.parse(geometric_descriptor_json); } catch {}
    }

    res.json({
      success: true,
      employee_id: parseInt(employeeId),
      descriptor_json,
      geometric_descriptor_json
    });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
});

// 4. POST /api/attendance/verify - Core 1-to-1 Matching Endpoint (Check-In / Check-Out)
apiRouter.post('/attendance/verify', async (req, res) => {
  try {
    const { employee_id, nik, name, department, scan_descriptor, location = 'Kantor Pusat', attendance_type = 'CHECK_IN', status: customStatus } = req.body;

    // Check if status is manual assignment (Izin, Sakit, Mangkir)
    const isManualStatus = customStatus && ['Izin', 'Sakit', 'Mangkir'].includes(customStatus);

    // 1. Input Validation: Check Descriptor Format (Only required for Hadir)
    if (!isManualStatus && !isValidDescriptor(scan_descriptor)) {
      return res.status(400).json({
        success: false,
        message: 'Validasi Payload Gagal: scan_descriptor harus berupa Array bertipe Float32 berjumlah 128 elemen!'
      });
    }

    // 2. Fetch Employee Record with Smart Fallback & Auto-Sync
    let employee = null;

    if (employee_id) {
      const { data } = await supabase.from('employees').select('*').eq('id', employee_id).single();
      employee = data;
    }

    if (!employee && nik) {
      const { data } = await supabase.from('employees').select('*').ilike('nik', nik.trim()).single();
      employee = data;
    }

    // Auto-Sync Employee if missing in Supabase
    if (!employee && (name || nik)) {
      const { data: newEmp } = await supabase
        .from('employees')
        .insert({
          nik: nik || `EMP-${Date.now().toString().slice(-4)}`,
          name: name || 'Karyawan',
          department: department || 'Umum',
          created_at: new Date().toISOString()
        })
        .select()
        .single();

      if (newEmp) {
        employee = newEmp;
      }
    }

    if (!employee) {
      return res.status(404).json({
        success: false,
        message: 'Error 404: Data Karyawan tidak ditemukan di Supabase. Harap pilih karyawan dari daftar.'
      });
    }

    // Handle Manual Non-Face Status (Izin, Sakit, Mangkir)
    if (isManualStatus) {
      const nowTs = new Date().toISOString();
      const logPayload = {
        employee_id: employee.id,
        location: `${location} [${customStatus}]`,
        status: customStatus,
        timestamp: nowTs,
        euclidean_distance: 0
      };

      await supabase.from('attendance_logs').insert(logPayload);

      return res.json({
        success: true,
        status: customStatus,
        message: `Absensi Karyawan ${employee.name} (${employee.nik}) dengan status [${customStatus}] berhasil dicatat!`,
        employee: {
          id: employee.id,
          nik: employee.nik,
          name: employee.name,
          department: employee.department
        },
        metrics: {
          euclidean_distance: 0,
          threshold: 0.55,
          attendance_type: customStatus,
          complexity: 'Manual Status Record'
        },
        timestamp: nowTs
      });
    }

    // 3. 1-to-1 Fetch Master Descriptor with Auto-Register Fallback
    let { data: masterRecord } = await supabase
      .from('master_descriptors')
      .select('descriptor_json')
      .eq('employee_id', employee.id)
      .single();

    // Auto-Register Master Descriptor in Supabase if missing
    if (!masterRecord || !masterRecord.descriptor_json) {
      if (isValidDescriptor(scan_descriptor)) {
        await supabase.from('master_descriptors').upsert({
          employee_id: employee.id,
          descriptor_json: scan_descriptor,
          updated_at: new Date().toISOString()
        }, { onConflict: 'employee_id' });

        masterRecord = { descriptor_json: scan_descriptor };
      } else {
        return res.status(404).json({
          success: false,
          message: `Error 404: Data Biometrik Master Wajah untuk karyawan "${employee.name}" (${employee.nik}) belum didaftarkan!`
        });
      }
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
    const status = isVerified ? `Hadir (Verified)` : `VERIFIKASI GAGAL (${typeLabel})`;
    const nowTs = new Date().toISOString();

    // 6. Log Attendance to Supabase Database (Pencatatan Audit Audit Log)
    const logPayload = {
      employee_id: employee.id,
      location: `${location} [${typeLabel}]`,
      status,
      timestamp: nowTs,
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
        check_in_time: attendance_type === 'CHECK_IN' ? nowTs : null,
        check_out_time: attendance_type === 'CHECK_OUT' ? nowTs : null,
        timestamp: nowTs
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
      if (log.attendance_type === 'CHECK-OUT' && !log.status.includes('GAGAL')) {
        const checkoutTime = new Date(log.timestamp || log.created_at);
        // Look for most recent CHECK-IN by same employee before this checkout
        const paired = enrichedLogs.find(l =>
          l.employee_id === log.employee_id &&
          l.attendance_type === 'CHECK-IN' &&
          !l.status.includes('GAGAL') &&
          new Date(l.timestamp || l.created_at) < checkoutTime
        );
        if (paired) {
          const checkinTime = new Date(paired.timestamp || paired.created_at);
          const diffMs = checkoutTime - checkinTime;
          const diffHrs = Math.floor(diffMs / 3600000);
          const diffMins = Math.floor((diffMs % 3600000) / 60000);
          log.durasi = `${diffHrs}j ${diffMins}m`;
        }
      }
    }

    res.json({ success: true, data: enrichedLogs });
  } catch (error) {
    console.error('[ERROR /api/attendance/logs]:', error);
    res.status(500).json({ success: false, message: error.message });
  }
});

// 6. GET /api/attendance/status/:employeeId - Check today's attendance state (Timezone Safe)
apiRouter.get('/attendance/status/:employeeId', async (req, res) => {
  try {
    const { employeeId } = req.params;

    // Setel rentang waktu lokal 00:00:00 s/d 23:59:59 lalu konversi ke ISOString (UTC)
    const now = new Date();
    const startOfDay = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 0, 0, 0, 0);
    const endOfDay = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 23, 59, 59, 999);

    const startOfDayIso = startOfDay.toISOString();
    const endOfDayIso = endOfDay.toISOString();

    // Query Supabase dengan filter .gte() dan .lte() pada kolom TIMESTAMPTZ 'timestamp'
    const { data: logs, error } = await supabase
      .from('attendance_logs')
      .select('*')
      .eq('employee_id', parseInt(employeeId))
      .gte('timestamp', startOfDayIso)
      .lte('timestamp', endOfDayIso)
      .order('timestamp', { ascending: true });

    if (error) throw error;

    const validLogs = (logs || []).filter(l => {
      const s = (l.status || '').toUpperCase();
      return !s.includes('GAGAL') && !s.includes('REJECT');
    });

    const checkIns = validLogs.filter(l => {
      const t = (l.attendance_type || '').toUpperCase();
      const loc = (l.location || '').toUpperCase();
      const st = (l.status || '').toUpperCase();
      const isOut = t.includes('CHECK_OUT') || t.includes('CHECK-OUT') || loc.includes('CHECK_OUT') || loc.includes('CHECK-OUT') || st.includes('CHECK_OUT') || st.includes('CHECK-OUT');
      const isIn = t.includes('CHECK_IN') || t.includes('CHECK-IN') || loc.includes('CHECK_IN') || loc.includes('CHECK-IN') || st.includes('HADIR') || st.includes('VERIFIED');
      return isIn && !isOut;
    });

    const checkOuts = validLogs.filter(l => {
      const t = (l.attendance_type || '').toUpperCase();
      const loc = (l.location || '').toUpperCase();
      const st = (l.status || '').toUpperCase();
      return t.includes('CHECK_OUT') || t.includes('CHECK-OUT') || loc.includes('CHECK_OUT') || loc.includes('CHECK-OUT') || st.includes('CHECK_OUT') || st.includes('CHECK-OUT');
    });

    const lastCheckIn = checkIns.length > 0 ? checkIns[checkIns.length - 1] : null;
    const lastCheckOut = checkOuts.length > 0 ? checkOuts[checkOuts.length - 1] : null;

    const hasCheckedIn = checkIns.length > 0;
    const hasCheckedOut = checkOuts.length > 0;

    res.json({
      success: true,
      employee_id: parseInt(employeeId),
      hasCheckedIn,
      hasCheckedOut,
      status: {
        hasCheckedIn,
        hasCheckedOut
      },
      checked_in: hasCheckedIn && !hasCheckedOut,
      check_in_time: lastCheckIn ? (lastCheckIn.timestamp || lastCheckIn.created_at) : null,
      check_out_time: lastCheckOut ? (lastCheckOut.timestamp || lastCheckOut.created_at) : null
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
      const typeLabel = item.attendance_type || (item.status?.includes('CHECK-OUT') ? 'CHECK-OUT' : 'CHECK-IN');
      insertPayloads.push({
        employee_id: item.employee_id,
        location: item.location ? `${item.location} (Sync Offline) [${typeLabel}]` : `HP Mobile (Sync Offline) [${typeLabel}]`,
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
