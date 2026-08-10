import React, { createContext, useContext, useState, useEffect, useCallback } from 'react';
import { API_BASE_URL } from '../config';
import { supabase } from '../supabaseClient';

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
      
      const { data: isValid, error } = await supabase.rpc('verify_admin_login', {
        p_username: username,
        p_password: password
      });

      if (error) throw error;

      if (isValid) {
        const adminUser = { id: 'admin-01', username, role: 'admin', name: 'Administrator' };
        setUser(adminUser);
        localStorage.setItem('logged_in_admin', JSON.stringify(adminUser));
        return { success: true, user: adminUser, message: 'Login Berhasil' };
      } else {
        return { success: false, message: 'Username atau Password salah!' };
      }
    } catch (err) {
      console.error('[AUTH LOGIN ERROR]:', err);
      // Fallback local login if offline
      if (credentials.username === 'admin' && credentials.password === 'tanaman') {
        const adminUser = { id: 'admin-01', username: 'admin', role: 'admin', name: 'Administrator (Offline)' };
        setUser(adminUser);
        localStorage.setItem('logged_in_admin', JSON.stringify(adminUser));
        return { success: true, user: adminUser, message: 'Login Mode Offline' };
      }
      return { success: false, message: err.message };
    }
  };

  // Logout handler
  const logout = async () => {
    setUser(null);
    localStorage.removeItem('logged_in_admin');
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
