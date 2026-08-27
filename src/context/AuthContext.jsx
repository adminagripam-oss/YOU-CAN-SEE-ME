import React, { createContext, useContext, useState, useEffect, useCallback } from 'react';
import { API_BASE_URL } from '../config';
import { supabase } from '../supabaseClient';
import { db } from '../db';

// Helper function to hash a string to SHA-256 for local offline credential matching
async function hashPassword(password) {
  const msgBuffer = new TextEncoder().encode(password);
  const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);
  const hashArray = Array.from(new Uint8Array(hashBuffer));
  return hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
}

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('logged_in_employee');
    return saved ? JSON.parse(saved) : null;
  });
  const [loading, setLoading] = useState(true);

  // Check auth session on startup / page refresh
  const checkAuth = useCallback(() => {
    try {
      setLoading(true);
      const saved = localStorage.getItem('logged_in_admin');
      if (saved) {
        setUser(JSON.parse(saved));
      } else {
        setUser(null);
      }
    } catch (err) {
      console.warn('[CHECK AUTH WARN]:', err.message);
      setUser(null);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    checkAuth();
  }, [checkAuth]);

  // Login handler
  const login = async (credentials) => {
    try {
      const { username, password } = credentials;
      
      const { data, error } = await supabase.rpc('verify_admin_login', {
        p_username: username,
        p_password: password
      });

      if (error) throw error;

      const result = data && data[0];
      if (result && result.is_valid) {
        const localHash = await hashPassword(password);
        const adminUser = {
          id: 'admin-' + username,
          username,
          role: result.u_role || 'estate_admin',
          region: result.u_region || null,
          kebun: result.u_kebun || null,
          name: result.u_name || 'Administrator',
          nik: result.u_nik || null
        };

        // Cache credentials locally for offline usage
        try {
          await db.local_admins.put({
            username,
            password_hash: localHash,
            role: adminUser.role,
            region: adminUser.region,
            kebun: adminUser.kebun,
            name: adminUser.name,
            nik: adminUser.nik
          });
        } catch (dbErr) {
          console.warn('[Offline Cache Error] Gagal menyimpan login lokal:', dbErr);
        }

        setUser(adminUser);
        localStorage.setItem('logged_in_admin', JSON.stringify(adminUser));
        return { success: true, user: adminUser, message: 'Login Berhasil' };
      } else {
        return { success: false, message: 'Username atau Password salah!' };
      }
    } catch (err) {
      console.error('[AUTH LOGIN ERROR]:', err);
      // Fallback local login if offline
      try {
        const cachedAdmin = await db.local_admins.get(credentials.username);
        if (cachedAdmin) {
          const enteredHash = await hashPassword(credentials.password);
          if (enteredHash === cachedAdmin.password_hash) {
            const adminUser = {
              id: 'admin-' + credentials.username,
              username: credentials.username,
              role: cachedAdmin.role,
              region: cachedAdmin.region,
              kebun: cachedAdmin.kebun,
              name: cachedAdmin.name + ' (Offline)',
              nik: cachedAdmin.nik || null
            };
            setUser(adminUser);
            localStorage.setItem('logged_in_admin', JSON.stringify(adminUser));
            return { success: true, user: adminUser, message: 'Login Mode Offline Berhasil' };
          }
        }
      } catch (localErr) {
        console.error('[OFFLINE AUTH ERROR]:', localErr);
      }

      // Hardcoded default fallback (failsafe)
      if (credentials.username === 'admin' && credentials.password === 'tanaman') {
        const adminUser = {
          id: 'admin-01',
          username: 'admin',
          role: 'headoffice_admin',
          region: null,
          kebun: null,
          name: 'Administrator (Offline Failsafe)'
        };
        setUser(adminUser);
        localStorage.setItem('logged_in_admin', JSON.stringify(adminUser));
        return { success: true, user: adminUser, message: 'Login Mode Offline Failsafe' };
      }
      return { success: false, message: 'Akun tidak ditemukan secara offline atau koneksi bermasalah.' };
    }
  };

  // Logout handler
  const logout = async () => {
    setUser(null);
    localStorage.removeItem('logged_in_admin');
    
    // Failsafe: Hapus service worker PWA agar update terbaru bisa masuk
    if ('serviceWorker' in navigator) {
      try {
        const registrations = await navigator.serviceWorker.getRegistrations();
        for (let registration of registrations) {
          await registration.unregister();
        }
      } catch (err) {
        console.warn('Gagal unregister SW saat logout', err);
      }
    }

    // Force redirect ke login untuk menimpa state router
    window.location.href = '/login';
  };

  const value = {
    user,
    loading,
    isAuthenticated: !!user,
    login,
    logout,
    checkAuth,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
}
