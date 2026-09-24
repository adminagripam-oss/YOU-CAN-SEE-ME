import React from 'react';

export default function Header({ activeTab, setActiveTab, currentUser, onLogout, theme, toggleTheme }) {
  return (
    <header className="app-header">
      <div className="brand">
        <div className="brand-icon">
          <i className="fa-solid fa-face-smile"></i>
        </div>
        <div className="brand-text">
          <h1>AgriFace</h1>
          <p>Biometric Face Attendance Engine</p>
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
          className="theme-toggle-btn icon-only"
          onClick={toggleTheme}
          title={theme === 'dark' ? 'Ganti ke Mode Terang (Light)' : 'Ganti ke Mode Gelap (Dark)'}
          style={{ width: '32px', height: '32px', padding: 0, borderRadius: '50%', display: 'inline-flex', alignItems: 'center', justifyContent: 'center' }}
        >
          <i className={`fa-solid ${theme === 'dark' ? 'fa-sun' : 'fa-moon'}`}></i>
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
