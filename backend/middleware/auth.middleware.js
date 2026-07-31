const jwt = require('jsonwebtoken');

const JWT_SECRET = process.env.JWT_SECRET || 'biometric-face-attendance-secret-key-2026';

/**
 * Authentication Guard Middleware for Protected API Endpoints
 * Verifies JWT Token from Cookie or Authorization Header
 */
function authMiddleware(req, res, next) {
  try {
    let token = null;

    // 1. Check HTTP-Only Cookie
    if (req.cookies && req.cookies.token) {
      token = req.cookies.token;
    }

    // 2. Check Authorization Header (Bearer <token>)
    if (!token && req.headers.authorization) {
      const authHeader = req.headers.authorization;
      if (authHeader.startsWith('Bearer ')) {
        token = authHeader.substring(7);
      } else {
        token = authHeader;
      }
    }

    if (!token) {
      return res.status(401).json({
        success: false,
        message: 'Akses ditolak - Token otentikasi tidak ditemukan. Silakan login terlebih dahulu.',
      });
    }

    // Verify JWT Token
    const decoded = jwt.verify(token, JWT_SECRET);
    req.user = decoded;
    next();
  } catch (err) {
    console.warn('[AUTH MIDDLEWARE INVALID TOKEN]:', err.message);
    return res.status(401).json({
      success: false,
      message: 'Sesi otentikasi telah kadaluarsa atau tidak valid. Silakan login ulang.',
    });
  }
}

module.exports = { authMiddleware, JWT_SECRET };
