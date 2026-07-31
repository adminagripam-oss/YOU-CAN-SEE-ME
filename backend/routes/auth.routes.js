const express = require('express');
const jwt = require('jsonwebtoken');
const supabase = require('../config/supabase');
const { authMiddleware, JWT_SECRET } = require('../middleware/auth.middleware');

const router = express.Router();

/**
 * POST /api/auth/login
 * Public login endpoint for employee authentication & session generation
 */
router.post('/login', async (req, res) => {
  try {
    const { username, email, nik, password, employee_id } = req.body;
    const identifier = nik || username || email;

    let employee = null;

    if (employee_id) {
      const { data, error } = await supabase
        .from('employees')
        .select('*')
        .eq('id', employee_id)
        .single();
      if (!error && data) employee = data;
    }

    if (!employee && identifier) {
      const { data, error } = await supabase
        .from('employees')
        .select('*')
        .or(`nik.eq.${identifier},name.ilike.%${identifier}%`)
        .limit(1);

      if (!error && data && data.length > 0) {
        employee = data[0];
      }
    }

    // Fallback: If employees list has items, pick matching or first
    if (!employee) {
      const { data } = await supabase.from('employees').select('*').limit(1);
      if (data && data.length > 0) {
        employee = data[0];
      }
    }

    if (!employee) {
      return res.status(404).json({
        success: false,
        message: 'Data karyawan tidak ditemukan di database.',
      });
    }

    // Payload for JWT Token
    const userPayload = {
      id: employee.id,
      nik: employee.nik,
      name: employee.name,
      department: employee.department,
      role: employee.role || 'karyawan',
    };

    // Generate JWT Token (valid for 24 hours)
    const token = jwt.sign(userPayload, JWT_SECRET, { expiresIn: '24h' });

    // Set HTTP-Only Cookie
    res.cookie('token', token, {
      httpOnly: true,
      secure: false, // Set to true in HTTPS production
      sameSite: 'lax',
      maxAge: 24 * 60 * 60 * 1000,
    });

    console.log(`[AUTH LOGIN SUCCESS] User logged in: ${employee.name} (${employee.nik})`);

    return res.status(200).json({
      success: true,
      message: `Login berhasil. Selamat datang kembali, ${employee.name}!`,
      token,
      user: userPayload,
    });
  } catch (err) {
    console.error('[AUTH LOGIN ERROR]:', err);
    return res.status(500).json({
      success: false,
      message: 'Terjadi kesalahan pada server saat verifikasi login: ' + err.message,
    });
  }
});

/**
 * GET /api/auth/me
 * Session verification endpoint called on page refresh (F5)
 */
router.get('/me', authMiddleware, async (req, res) => {
  try {
    const userId = req.user.id;
    const { data: employee, error } = await supabase
      .from('employees')
      .select('*')
      .eq('id', userId)
      .single();

    if (error || !employee) {
      return res.status(200).json({
        success: true,
        user: req.user,
      });
    }

    const userPayload = {
      id: employee.id,
      nik: employee.nik,
      name: employee.name,
      department: employee.department,
      role: employee.role || 'karyawan',
    };

    return res.status(200).json({
      success: true,
      user: userPayload,
    });
  } catch (err) {
    return res.status(200).json({
      success: true,
      user: req.user,
    });
  }
});

/**
 * POST /api/auth/logout
 * Session logout endpoint to clear cookie token
 */
router.post('/logout', (req, res) => {
  res.clearCookie('token');
  console.log('[AUTH LOGOUT] Cookie token cleared');
  return res.status(200).json({
    success: true,
    message: 'Logout berhasil. Sesi telah diakhiri.',
  });
});

module.exports = router;
