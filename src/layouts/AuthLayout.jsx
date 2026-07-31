import React from 'react';
import { Outlet } from 'react-router-dom';

export default function AuthLayout({ theme, toggleTheme }) {
  return (
    <div className="auth-layout-container">
      {/* Floating Theme Toggle */}
      <div className="auth-theme-floating">
        <button
          className="theme-toggle-btn"
          onClick={toggleTheme}
          title={theme === 'dark' ? 'Ganti ke Mode Terang (Light)' : 'Ganti ke Mode Gelap (Dark)'}
        >
          <i className={`fa-solid ${theme === 'dark' ? 'fa-sun' : 'fa-moon'}`}></i>
          <span>{theme === 'dark' ? 'Light' : 'Dark'}</span>
        </button>
      </div>

      <main className="auth-layout-main">
        <Outlet />
      </main>

      <footer className="auth-footer">
        <p>Aplikasi Absensi Mobile Biometrik Wajah (Offline-First &amp; Auto-Sync PWA) &copy; 2026</p>
      </footer>
    </div>
  );
}
