import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function LoginPage({ showToast, theme, toggleTheme }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!username || !password) {
      if (showToast) showToast('Data Tidak Lengkap', 'Masukkan Username dan Password!', 'error');
      return;
    }

    setIsLoading(true);
    try {
      const res = await login({ username, password });
      if (res.success) {
        if (showToast) showToast('Login Berhasil', res.message, 'success');
      } else {
        if (showToast) showToast('Login Gagal', res.message, 'error');
      }
    } catch (err) {
      if (showToast) showToast('Error Sistem', err.message, 'error');
    } finally {
      setIsLoading(false);
    }
  };

  // Shared style untuk wrapper input dengan icon
  const inputWrapperStyle = {
    display: 'flex',
    alignItems: 'center',
    gap: '10px',
    background: theme === 'dark' ? 'rgba(255,255,255,0.06)' : 'rgba(0,0,0,0.04)',
    border: `1px solid ${theme === 'dark' ? 'rgba(255,255,255,0.12)' : 'rgba(0,0,0,0.12)'}`,
    borderRadius: '10px',
    padding: '0 14px',
    height: '48px',
    width: '100%',
    boxSizing: 'border-box',
    transition: 'border-color 0.2s, box-shadow 0.2s',
  };

  const inputStyle = {
    flex: 1,
    border: 'none',
    background: 'transparent',
    color: theme === 'dark' ? '#f1f5f9' : '#1e293b',
    fontSize: '0.9rem',
    outline: 'none',
    height: '100%',
    minWidth: 0,
    colorScheme: theme === 'dark' ? 'dark' : 'light',
  };

  const iconStyle = {
    color: 'var(--text-muted)',
    flexShrink: 0,
    fontSize: '0.95rem',
    width: '16px',
    textAlign: 'center',
  };

  const labelStyle = {
    display: 'block',
    fontSize: '0.78rem',
    fontWeight: 700,
    letterSpacing: '0.06em',
    color: 'var(--text-muted)',
    marginBottom: '8px',
    textTransform: 'uppercase',
  };

  return (
    <div className="glass-login-wrapper">
      <div className="login-pattern-container">
        
        {/* Header Title from Figma Design */}
        <div className="glass-login-title" style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <img src="/logo.png" alt="AgriFace Logo" style={{ width: '400px', height: 'auto', objectFit: 'contain', marginTop: '-20px', marginBottom: '-60px', imageRendering: 'high-quality', filter: 'drop-shadow(0 6px 12px rgba(0,0,0,0.15))' }} />
          <h1 className="figma-login-title" style={{ marginTop: '0px' }}>Welcome Admin!</h1>
          <p className="figma-login-subtitle">
            Masuk untuk kelola karyawan dan riwayat absensi.
          </p>
        </div>

        <div className="glass-card ui-card" style={{ maxWidth: '400px', margin: '0 auto' }}>
          <div className="ui-card-content">
            <form onSubmit={handleLogin} className="space-y-4">

              {/* ── Username Field ─────────────────────────────────────── */}
              <div style={{ marginBottom: '16px' }}>
                <label htmlFor="login-username" style={labelStyle}>
                  Username
                </label>
                <div
                  style={inputWrapperStyle}
                  onFocus={(e) => {
                    e.currentTarget.style.borderColor = 'var(--accent-primary)';
                    e.currentTarget.style.boxShadow = '0 0 0 3px rgba(99,102,241,0.15)';
                  }}
                  onBlur={(e) => {
                    e.currentTarget.style.borderColor = 'var(--border-color)';
                    e.currentTarget.style.boxShadow = 'none';
                  }}
                >
                  <i className="fa-solid fa-user" style={iconStyle}></i>
                  <input
                    type="text"
                    id="login-username"
                    placeholder="Masukkan username..."
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    disabled={isLoading}
                    style={inputStyle}
                  />
                </div>
              </div>

              {/* ── Password Field ─────────────────────────────────────── */}
              <div style={{ marginBottom: '8px' }}>
                <label htmlFor="login-password" style={labelStyle}>
                  Password
                </label>
                <div
                  style={inputWrapperStyle}
                  onFocus={(e) => {
                    e.currentTarget.style.borderColor = 'var(--accent-primary)';
                    e.currentTarget.style.boxShadow = '0 0 0 3px rgba(99,102,241,0.15)';
                  }}
                  onBlur={(e) => {
                    e.currentTarget.style.borderColor = 'var(--border-color)';
                    e.currentTarget.style.boxShadow = 'none';
                  }}
                >
                  <i className="fa-solid fa-lock" style={iconStyle}></i>
                  <input
                    type={showPassword ? 'text' : 'password'}
                    id="login-password"
                    placeholder="Masukkan password..."
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    disabled={isLoading}
                    style={inputStyle}
                  />
                  {/* Toggle Show/Hide Password */}
                  <button
                    type="button"
                    onClick={() => setShowPassword((v) => !v)}
                    style={{
                      background: 'none',
                      border: 'none',
                      cursor: 'pointer',
                      color: 'var(--text-muted)',
                      padding: '0',
                      flexShrink: 0,
                      fontSize: '0.85rem',
                      lineHeight: 1,
                    }}
                    tabIndex={-1}
                    aria-label={showPassword ? 'Sembunyikan password' : 'Tampilkan password'}
                  >
                    <i className={`fa-solid ${showPassword ? 'fa-eye-slash' : 'fa-eye'}`}></i>
                  </button>
                </div>
              </div>

              <div style={{ display: 'flex', flexDirection: 'column', gap: '10px', marginTop: '1.5rem' }}>
                <button type="submit" className="btn btn-primary" disabled={isLoading || !username || !password}>
                  {isLoading ? (
                    <><i className="fa-solid fa-spinner fa-spin"></i> Memverifikasi...</>
                  ) : (
                    <><i className="fa-solid fa-right-to-bracket"></i> Masuk Dashboard</>
                  )}
                </button>

                <button
                  type="button"
                  className="btn-action edit"
                  onClick={() => navigate('/absensi')}
                  style={{ width: '100%', justifyContent: 'center', padding: '12px', fontSize: '0.9rem' }}
                >
                  <i className="fa-solid fa-camera"></i> Buka Layar Absensi Karyawan
                </button>

                <button
                  type="button"
                  onClick={toggleTheme}
                  style={{ background: 'transparent', border: 'none', color: 'var(--text-muted)', cursor: 'pointer', marginTop: '10px', fontSize: '0.8rem' }}
                >
                  <i className={`fa-solid ${theme === 'dark' ? 'fa-sun' : 'fa-moon'}`}></i> Ganti Tema
                </button>
              </div>
            </form>
          </div>
        </div>

      </div>
    </div>
  );
}
