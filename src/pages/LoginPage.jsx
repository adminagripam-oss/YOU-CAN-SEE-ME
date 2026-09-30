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
        if (showToast) showToast('Login Berhasil', '', 'success');
      } else {
        if (showToast) showToast('Login Gagal', res.message, 'error');
      }
    } catch (err) {
      if (showToast) showToast('Error Sistem', err.message, 'error');
    } finally {
      setIsLoading(false);
    }
  };

  // Shared style untuk wrapper input dengan icon (sementara sampai Fase L2)
  const inputWrapperStyle = {
    display: 'flex',
    alignItems: 'center',
    gap: '10px',
    background: theme === 'dark' ? '#000000' : 'rgba(0,0,0,0.04)',
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
    <div className="relative grid min-h-dvh w-full bg-background lg:grid-cols-2">
      {/* Toggle tema: tetap ada di semua ukuran layar */}
      <div className="absolute right-4 top-4 z-20">
        <button
          type="button"
          onClick={toggleTheme}
          className="inline-flex items-center gap-2 rounded-md border bg-background/80 px-3 py-1.5 text-xs font-medium backdrop-blur shadow-sm transition-colors hover:bg-accent hover:text-accent-foreground"
          title={theme === 'dark' ? 'Ganti ke Mode Terang (Light)' : 'Ganti ke Mode Gelap (Dark)'}
        >
          <i className={`fa-solid ${theme === 'dark' ? 'fa-sun' : 'fa-moon'}`}></i>
          <span>{theme === 'dark' ? 'Light' : 'Dark'}</span>
        </button>
      </div>

      {/* KOLOM KIRI: form, center horizontal & vertikal */}
      <section className="flex min-h-dvh items-center justify-center px-6 py-10 sm:px-10">
        <div className="w-full max-w-sm">
          {/* Kepala: logo + judul + subjudul */}
          <div className="mb-8 flex flex-col items-center text-center">
            <img
              src="/logo.png"
              alt="AgriFace Logo"
              className="h-16 w-auto object-contain"
            />
            <h1 className="mt-4 text-2xl font-semibold tracking-tight">
              Selamat datang di AgriFace
            </h1>
            <p className="mt-1 text-sm text-muted-foreground">
              Sistem pencatatan absensi karyawan berbasis wajah.
            </p>
          </div>

          {/* FORM: dipindahkan di Fase L2. Untuk sekarang letakkan form lama apa adanya di sini */}
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
            </div>
          </form>
        </div>
      </section>

      {/* KOLOM KANAN: gambar (disembunyikan di mobile) */}
      <aside className="hidden p-3 lg:block">
        <div className="relative h-full min-h-[calc(100dvh-1.5rem)] overflow-hidden rounded-3xl">
          <img
            src="/BACKGROUNDHARVESTING.jpeg"
            alt="Pekerja perkebunan kelapa sawit"
            className="absolute inset-0 h-full w-full object-cover"
          />
        </div>
      </aside>
    </div>
  );
}
