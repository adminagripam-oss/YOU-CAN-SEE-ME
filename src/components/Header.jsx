import React from 'react';

export default function Header({ activeTab, setActiveTab, currentUser, onLogout }) {
  return (
    <header>
      <div className="brand">
        <div className="brand-icon">
          <i className="fa-solid fa-face-smile"></i>
        </div>
        <div className="brand-text">
          <h1>Absensi Biometrik Wajah</h1>
          <p>Offline-First &amp; Auto-Sync Mobile Engine</p>
        </div>
      </div>

      <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
        {currentUser && (
          <div className="badge-o1">
            <i className="fa-solid fa-user-check"></i>
            <span>{currentUser.name} ({currentUser.nik})</span>
            {onLogout && (
              <button
                onClick={onLogout}
                style={{
                  background: 'none',
                  border: 'none',
                  color: '#ef4444',
                  cursor: 'pointer',
                  marginLeft: '6px',
                }}
                title="Keluar / Ganti Akun"
              >
                <i className="fa-solid fa-right-from-bracket"></i>
              </button>
            )}
          </div>
        )}

        <div className="nav-tabs">
          <button
            className={`tab-btn ${activeTab === 'tab-verify' ? 'active' : ''}`}
            onClick={() => setActiveTab('tab-verify')}
          >
            <i className="fa-solid fa-camera-retro"></i> Absensi Wajah
          </button>
          <button
            className={`tab-btn ${activeTab === 'tab-employees' ? 'active' : ''}`}
            onClick={() => setActiveTab('tab-employees')}
          >
            <i className="fa-solid fa-users"></i> Data Karyawan
          </button>
          <button
            className={`tab-btn ${activeTab === 'tab-logs' ? 'active' : ''}`}
            onClick={() => setActiveTab('tab-logs')}
          >
            <i className="fa-solid fa-clock-rotate-left"></i> Log Absensi
          </button>
        </div>
      </div>
    </header>
  );
}
