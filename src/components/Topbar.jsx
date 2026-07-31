import React from 'react';
import { useAuth } from '../context/AuthContext';

export default function Topbar({ theme, toggleTheme }) {
  const { user, logout } = useAuth();

  return (
    <header className="app-topbar">
      <div className="topbar-left">
        <div className="brand-text-mobile">
          <i className="fa-solid fa-face-smile" style={{ color: 'var(--accent-primary)', fontSize: '20px' }}></i>
          <h1 style={{ fontSize: '1.05rem', fontWeight: 800 }}>Absensi Biometrik Wajah</h1>
        </div>
      </div>

      <div className="topbar-right">
        {user && (
          <div className="user-badge">
            <i className="fa-solid fa-user-check"></i>
            <span className="user-name">{user.name} ({user.nik})</span>
          </div>
        )}

        {/* Theme Toggle Button */}
        <button
          className="theme-toggle-btn"
          onClick={toggleTheme}
          title={theme === 'dark' ? 'Ganti ke Light Mode' : 'Ganti ke Dark Mode'}
        >
          <i className={`fa-solid ${theme === 'dark' ? 'fa-sun' : 'fa-moon'}`}></i>
          <span className="theme-toggle-label">{theme === 'dark' ? 'Light' : 'Dark'}</span>
        </button>

        {/* Logout Button */}
        <button
          className="btn-logout"
          onClick={logout}
          title="Keluar dari Sesi Login"
        >
          <i className="fa-solid fa-right-from-bracket"></i>
          <span>Logout</span>
        </button>
      </div>
    </header>
  );
}
