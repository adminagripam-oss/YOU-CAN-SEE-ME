import React, { createContext, useContext, useState, useEffect, useCallback } from 'react';
import { API_BASE_URL } from '../config';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('logged_in_employee');
    return saved ? JSON.parse(saved) : null;
  });
  const [loading, setLoading] = useState(true);

  // Check auth session on startup / page refresh (GET /api/auth/me)
  const checkAuth = useCallback(async () => {
    try {
      setLoading(true);
      const res = await fetch(`${API_BASE_URL}/api/auth/me`, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
      });
      const data = await res.json();

      if (data.success && data.user) {
        setUser(data.user);
        localStorage.setItem('logged_in_employee', JSON.stringify(data.user));
      } else {
        const saved = localStorage.getItem('logged_in_employee');
        if (saved) {
          setUser(JSON.parse(saved));
        } else {
          setUser(null);
        }
      }
    } catch (err) {
      console.warn('[CHECK AUTH WARN]:', err.message);
      const saved = localStorage.getItem('logged_in_employee');
      if (saved) {
        setUser(JSON.parse(saved));
      } else {
        setUser(null);
      }
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    checkAuth();
  }, [checkAuth]);

  // Login handler (POST /api/auth/login)
  const login = async (credentials) => {
    try {
      const res = await fetch(`${API_BASE_URL}/api/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify(credentials),
      });

      const data = await res.json();
      if (data.success && data.user) {
        setUser(data.user);
        localStorage.setItem('logged_in_employee', JSON.stringify(data.user));
        return { success: true, user: data.user, message: data.message };
      }
      return { success: false, message: data.message || 'Login gagal' };
    } catch (err) {
      console.error('[AUTH LOGIN ERROR]:', err);
      // Fallback local login if offline
      if (credentials.employee) {
        setUser(credentials.employee);
        localStorage.setItem('logged_in_employee', JSON.stringify(credentials.employee));
        return { success: true, user: credentials.employee, message: 'Login Mode Offline' };
      }
      return { success: false, message: err.message };
    }
  };

  // Logout handler (POST /api/auth/logout)
  const logout = async () => {
    try {
      await fetch(`${API_BASE_URL}/api/auth/logout`, {
        method: 'POST',
        credentials: 'include',
      });
    } catch {
      // Ignore network errors on logout
    } finally {
      setUser(null);
      localStorage.removeItem('logged_in_employee');
    }
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
