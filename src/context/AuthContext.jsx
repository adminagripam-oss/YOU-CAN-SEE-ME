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
    const saved = localStorage.getItem('logged_in_admin');
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

      // Hardcoded offline fallback list for all regional & kebun accounts from DAFTAR_AKUN_AGRIFACE.csv
      const OFFLINE_FALLBACK_ACCOUNTS = [
        { username: 'admin', password: 'tanaman', role: 'headoffice_admin', region: null, kebun: null, name: 'Administrator (Offline Failsafe)', nik: 'KB-BHI-01' },
        { username: 'admin.hq', password: 'tanamanhead', role: 'headoffice_admin', region: null, kebun: null, name: 'Head Office Admin (Offline)', nik: 'KB-BHI-01' },
        { username: 'admin.sumut2', password: 'tanamansumut2', role: 'regional_admin', region: 'Sumut 2', kebun: null, name: 'Regional Admin Sumut 2 (Offline)', nik: 'KB-BHI-02' },
        { username: 'admin.riau1', password: 'tanamanriau1', role: 'regional_admin', region: 'Riau 1', kebun: null, name: 'Regional Admin Riau 1 (Offline)', nik: 'KB-PAR-01' },
        { username: 'admin.kalbar1a', password: 'tanamankalbar1a', role: 'regional_admin', region: 'Kalbar 1A', kebun: null, name: 'Regional Admin Kalbar 1A (Offline)', nik: 'KB-PJA-01' },
        { username: 'admin.kalbar1b', password: 'tanamankalbar1b', role: 'regional_admin', region: 'Kalbar 1B', kebun: null, name: 'Regional Admin Kalbar 1B (Offline)', nik: 'KB-PAL-01' },
        { username: 'admin.bukitharapani', password: 'tanamanbukitharapani', role: 'estate_admin', region: 'Sumut 2', kebun: 'Bukit Harapan I', name: 'Admin Bukit Harapan I (Offline)', nik: 'KB-WJT-01' },
        { username: 'admin.bukitharapanii', password: 'tanamanbukitharapanii', role: 'estate_admin', region: 'Sumut 2', kebun: 'Bukit Harapan II', name: 'Admin Bukit Harapan II (Offline)' },
        { username: 'admin.parsub', password: 'tanamanparsub', role: 'estate_admin', region: 'Sumut 2', kebun: 'Parsub', name: 'Admin Parsub (Offline)' },
        { username: 'admin.patogujanji', password: 'tanamanpatogujanji', role: 'estate_admin', region: 'Sumut 2', kebun: 'Patogu Janji', name: 'Admin Patogu Janji (Offline)' },
        { username: 'admin.pancaagrolestaripal', password: 'tanamanpancaagrolestaripal', role: 'estate_admin', region: 'Riau 1', kebun: 'Panca Agro Lestari (Pal)', name: 'Admin Panca Agro Lestari (Pal) (Offline)' },
        { username: 'admin.wanajinggatimurwjt', password: 'tanamanwanajinggatimurwjt', role: 'estate_admin', region: 'Riau 1', kebun: 'Wana Jingga Timur (Wjt)', name: 'Admin Wana Jingga Timur (Wjt) (Offline)' },
        { username: 'admin.dutapalmanusantaradpni', password: 'tanamandutapalmanusantaradpni', role: 'estate_admin', region: 'Riau 1', kebun: 'Duta Palma Nusantara (Dpn) I', name: 'Admin Duta Palma Nusantara (Dpn) I (Offline)', nik: 'KB-PIL-01' },
        { username: 'admin.dutapalmanusantaradpnii', password: 'tanamandutapalmanusantaradpnii', role: 'estate_admin', region: 'Riau 1', kebun: 'Duta Palma Nusantara (Dpn) II', name: 'Admin Duta Palma Nusantara (Dpn) II (Offline)' },
        { username: 'admin.dutapalmanusantaradpniii', password: 'tanamandutapalmanusantaradpniii', role: 'estate_admin', region: 'Riau 1', kebun: 'Duta Palma Nusantara (Dpn) III', name: 'Admin Duta Palma Nusantara (Dpn) III (Offline)', nik: 'KB-TAG-866' },
        { username: 'admin.eluanmahkotaemakt', password: 'tanamaneluanmahkotaemakt', role: 'estate_admin', region: 'Riau 1', kebun: 'Eluan Mahkota (EMA) - KT', name: 'Admin Eluan Mahkota (EMA) - KT (Offline)' },
        { username: 'admin.johansentosa', password: 'tanamanjohansentosa', role: 'estate_admin', region: 'Riau 1', kebun: 'Johan Sentosa', name: 'Admin Johan Sentosa (Offline)' },
        { username: 'admin.palmaintilestaripil', password: 'tanamanpalmaintilestaripil', role: 'estate_admin', region: 'Riau 1', kebun: 'Palma Inti Lestari (PIL)', name: 'Admin Palma Inti Lestari (PIL) (Offline)' },
        { username: 'admin.bukitjagoindahbji', password: 'tanamanbukitjagoindahbji', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Bukit Jago Indah (BJI)', name: 'Admin Bukit Jago Indah (BJI) (Offline)' },
        { username: 'admin.kaliaumasperkasaakmpa', password: 'tanamankaliaumasperkasaakmpa', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Kaliau Mas Perkasa A (KMP A)', name: 'Admin Kaliau Mas Perkasa A (KMP A) (Offline)' },
        { username: 'admin.kaliaumasperkasabkmpb', password: 'tanamankaliaumasperkasabkmpb', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Kaliau Mas Perkasa B (KMP B)', name: 'Admin Kaliau Mas Perkasa B (KMP B) (Offline)' },
        { username: 'admin.telukkeramattkr', password: 'tanamantelukkeramattkr', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Teluk Keramat (TKR)', name: 'Admin Teluk Keramat (TKR) (Offline)' },
        { username: 'admin.wanahijausemestaiwhsi', password: 'tanamanwanahijausemestaiwhsi', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Wana Hijau Semesta I (WHS I)', name: 'Admin Wana Hijau Semesta I (WHS I) (Offline)', nik: 'KB-TAG-867' },
        { username: 'admin.wanahijausemestaiil1', password: 'tanamanwanahijausemestaiil1', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Wana Hijau Semesta II (L1)', name: 'Admin Wana Hijau Semesta II (L1) (Offline)' },
        { username: 'admin.wanahijausemestaiil2', password: 'tanamanwanahijausemestaiil2', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Wana Hijau Semesta II (L2)', name: 'Admin Wana Hijau Semesta II (L2) (Offline)' },
        { username: 'admin.wanahijausemestaiiwhsii', password: 'tanamanwanahijausemestaiiwhsii', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Wana Hijau Semesta II (WHS II)', name: 'Admin Wana Hijau Semesta II (WHS II) (Offline)', nik: 'KB-DIA-01' },
        { username: 'admin.wanahijausemestaiiiwhsiii', password: 'tanamanwanahijausemestaiiiwhsiii', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Wana Hijau Semesta III (WHS III)', name: 'Admin Wana Hijau Semesta III (WHS III) (Offline)', nik: 'KB-DIA-02' },
        { username: 'admin.wanahijausemestaiv', password: 'tanamanwanahijausemestaiv', role: 'estate_admin', region: 'Kalbar 1A', kebun: 'Wana Hijau Semesta IV', name: 'Admin Wana Hijau Semesta IV (Offline)', nik: 'KB-DXA-01' },
        { username: 'admin.mitrawawasanmws', password: 'tanamanmitrawawasanmws', role: 'estate_admin', region: 'Kalbar 1B', kebun: 'Mitra Wawasan (MWS)', name: 'Admin Mitra Wawasan (MWS) (Offline)' },
        { username: 'admin.persadaalampa', password: 'tanamanpersadaalampa', role: 'estate_admin', region: 'Kalbar 1B', kebun: 'Persada Alam (PA)', name: 'Admin Persada Alam (PA) (Offline)' },
        { username: 'admin.darmexi', password: 'tanamandarmexi', role: 'estate_admin', region: 'Kalbar 1B', kebun: 'Darmex - I', name: 'Admin Darmex - I (Offline)' },
        { username: 'admin.darmexii', password: 'tanamandarmexii', role: 'estate_admin', region: 'Kalbar 1B', kebun: 'Darmex - II', name: 'Admin Darmex - II (Offline)' },
        { username: 'admin.darmexx', password: 'tanamandarmexx', role: 'estate_admin', region: 'Kalbar 1B', kebun: 'Darmex - X', name: 'Admin Darmex - X (Offline)' }
      ];

      const matchedAccount = OFFLINE_FALLBACK_ACCOUNTS.find(
        acc => acc.username.toLowerCase() === credentials.username.toLowerCase() && acc.password === credentials.password
      );

      if (matchedAccount) {
        const localHash = await hashPassword(matchedAccount.password);
        const adminUser = {
          id: 'admin-' + matchedAccount.username,
          username: matchedAccount.username,
          role: matchedAccount.role,
          region: matchedAccount.region,
          kebun: matchedAccount.kebun,
          name: matchedAccount.name,
          nik: matchedAccount.nik || null
        };

        // Cache to Dexie local_admins for future local lookup
        try {
          await db.local_admins.put({
            username: matchedAccount.username,
            password_hash: localHash,
            role: adminUser.role,
            region: adminUser.region,
            kebun: adminUser.kebun,
            name: adminUser.name,
            nik: adminUser.nik
          });
        } catch (dbErr) {
          console.warn('[Offline Fallback DB Cache Warning]:', dbErr);
        }

        setUser(adminUser);
        localStorage.setItem('logged_in_admin', JSON.stringify(adminUser));
        return { success: true, user: adminUser, message: `Login Mode Offline Failsafe (${matchedAccount.name})` };
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
