import React from 'react';

export default function Header({ activeTab, setActiveTab }) {
  return (
    <header>
      <div className="brand">
        <div className="brand-icon">
          <i className="fa-solid fa-face-smile"></i>
        </div>
        <div className="brand-text">
          <h1>Absensi Biometrik Wajah</h1>
        </div>
      </div>

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
    </header>
  );
}
