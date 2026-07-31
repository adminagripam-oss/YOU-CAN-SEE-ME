import React from 'react';

export default function Header({ activeTab, setActiveTab, currentUser, onLogout, theme, toggleTheme }) {
  return (
    <header className="app-header">
      <div className="brand">
        <div className="brand-icon">
          <i className="fa-solid fa-face-smile"></i>
        </div>
        <div className="brand-text">
          <h1>Absensi Biometrik Wajah</h1>
          <p>Offline-First &amp; Auto-Sync Mobile Engine</p>
        </div>
      </div>

      <div className="header-actions">
        {currentUser && (
          <div className="user-badge">
            <i className="fa-solid fa-user-check"></i>
            <span className="user-name">{currentUser.name} ({currentUser.nik})</span>
            {onLogout && (
              <button
                className="btn-icon-logout"
                onClick={onLogout}
                title="Keluar / Ganti Akun"
              >
                <i className="fa-solid fa-right-from-bracket"></i>
              </button>
            )}
          </div>
        )}

        {/* Dark / Light Mode Toggle Button */}
        <button
          className="theme-toggle-btn"
          onClick={toggleTheme}
          title={theme === 'dark' ? 'Ganti ke Mode Terang (Light)' : 'Ganti ke Mode Gelap (Dark)'}
        >
          <i className={`fa-solid ${theme === 'dark' ? 'fa-sun' : 'fa-moon'}`}></i>
          <span className="theme-toggle-label">{theme === 'dark' ? 'Light' : 'Dark'}</span>
        </button>

        <div className="nav-tabs">
          <button
            className={`tab-btn ${activeTab === 'tab-login' ? 'active' : ''}`}
            onClick={() => setActiveTab('tab-login')}
          >
            <i className="fa-solid fa-user-shield"></i>
            <span className="tab-label">{currentUser ? 'Akun Saya' : 'Login'}</span>
          </button>
          <button
            className={`tab-btn ${activeTab === 'tab-verify' ? 'active' : ''}`}
            onClick={() => setActiveTab('tab-verify')}
          >
            <i className="fa-solid fa-camera-retro"></i>
            <span className="tab-label">Absensi</span>
          </button>
          <button
            className={`tab-btn ${activeTab === 'tab-employees' ? 'active' : ''}`}
            onClick={() => setActiveTab('tab-employees')}
          >
            <i className="fa-solid fa-users"></i>
            <span className="tab-label">Karyawan</span>
          </button>
          <button
            className={`tab-btn ${activeTab === 'tab-logs' ? 'active' : ''}`}
            onClick={() => setActiveTab('tab-logs')}
          >
            <i className="fa-solid fa-clock-rotate-left"></i>
            <span className="tab-label">Log Absensi</span>
          </button>
        </div>
      </div>
    </header>
  );
}
