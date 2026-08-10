import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function LoginPage({ showToast, theme, toggleTheme }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
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

  return (
    <div className="glass-login-wrapper">
      <div className="login-pattern-container">
        
        {/* Header Title from Figma Design */}
        <div className="glass-login-title" style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <img src="/logo.png" alt="AgriFace Logo" style={{ width: '130px', height: '130px', objectFit: 'contain', marginBottom: '20px', imageRendering: 'high-quality', filter: 'drop-shadow(0 6px 12px rgba(0,0,0,0.15))' }} />
          <h1 className="figma-login-title">Welcome Admin!</h1>
          <p className="figma-login-subtitle">
            Masuk untuk kelola karyawan dan riwayat absensi.
          </p>
        </div>

        <div className="glass-card ui-card" style={{ maxWidth: '400px', margin: '0 auto' }}>
          <div className="ui-card-content">
            <form onSubmit={handleLogin} className="space-y-4">
              <div className="form-group">
                <label htmlFor="login-username">Username</label>
                <input
                  type="text"
                  id="login-username"
                  placeholder="Masukkan username admin..."
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  disabled={isLoading}
                />
              </div>

              <div className="form-group">
                <label htmlFor="login-password">Password</label>
                <input
                  type="password"
                  id="login-password"
                  placeholder="Masukkan password..."
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  disabled={isLoading}
                />
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
