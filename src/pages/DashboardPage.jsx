import React from 'react';
import { useAuth } from '../context/AuthContext';
import { Link } from 'react-router-dom';

export default function DashboardPage({ employees = [], logs = [], modelsLoaded }) {
  const { user } = useAuth();
  const totalEmployees = employees.length;
  const totalLogs = logs.length;
  const todayLogs = logs.filter((l) => {
    if (!l.timestamp) return false;
    const logDate = new Date(l.timestamp).toDateString();
    return logDate === new Date().toDateString();
  });

  return (
    <div className="dashboard-page">
      <div className="glass-card" style={{ marginBottom: '1.5rem' }}>
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '12px' }}>
          <div>
            <h1 style={{ fontSize: '1.35rem', fontWeight: 800 }}>
              Selamat datang kembali, {user ? user.name : 'Pengguna'}! 👋
            </h1>
            <p style={{ color: 'var(--text-muted)', fontSize: '0.88rem', marginTop: '4px' }}>
              Sistem Absensi Biometrik Wajah 1-to-1 Verification Engine &amp; Supabase Cloud Sync.
            </p>
          </div>
          <Link to="/absensi" className="btn btn-primary" style={{ width: 'auto', padding: '10px 18px', fontSize: '0.88rem' }}>
            <i className="fa-solid fa-camera"></i> Buka Scanner Absensi
          </Link>
        </div>
      </div>

      {/* Grid Stat Cards */}
      <div className="grid-2" style={{ gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))', gap: '1rem', marginBottom: '1.5rem' }}>
        <div className="glass-card" style={{ marginBottom: 0 }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '14px' }}>
            <div style={{ width: '48px', height: '48px', borderRadius: '12px', background: 'var(--alert-info-bg)', color: 'var(--accent-primary)', display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: '22px' }}>
              <i className="fa-solid fa-users"></i>
            </div>
            <div>
              <div style={{ fontSize: '0.78rem', color: 'var(--text-muted)', textTransform: 'uppercase', fontWeight: 700 }}>Total Karyawan</div>
              <div style={{ fontSize: '1.4rem', fontWeight: 800 }}>{totalEmployees} Orangnya</div>
            </div>
          </div>
        </div>

        <div className="glass-card" style={{ marginBottom: 0 }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '14px' }}>
            <div style={{ width: '48px', height: '48px', borderRadius: '12px', background: 'var(--alert-success-bg)', color: 'var(--accent-success)', display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: '22px' }}>
              <i className="fa-solid fa-calendar-check"></i>
            </div>
            <div>
              <div style={{ fontSize: '0.78rem', color: 'var(--text-muted)', textTransform: 'uppercase', fontWeight: 700 }}>Absen Hari Ini</div>
              <div style={{ fontSize: '1.4rem', fontWeight: 800 }}>{todayLogs.length} Records</div>
            </div>
          </div>
        </div>

        <div className="glass-card" style={{ marginBottom: 0 }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '14px' }}>
            <div style={{ width: '48px', height: '48px', borderRadius: '12px', background: 'var(--alert-info-bg)', color: 'var(--accent-cyan)', display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: '22px' }}>
              <i className="fa-solid fa-brain"></i>
            </div>
            <div>
              <div style={{ fontSize: '0.78rem', color: 'var(--text-muted)', textTransform: 'uppercase', fontWeight: 700 }}>Model AI Biometrik</div>
              <div style={{ fontSize: '1rem', fontWeight: 800, color: modelsLoaded ? 'var(--accent-success)' : 'var(--accent-warning)' }}>
                {modelsLoaded ? '✓ Model AI Ready' : 'Memuat AI...'}
              </div>
            </div>
          </div>
        </div>

        <div className="glass-card" style={{ marginBottom: 0 }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '14px' }}>
            <div style={{ width: '48px', height: '48px', borderRadius: '12px', background: 'var(--bg-primary)', color: 'var(--accent-primary)', display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: '22px', border: '1px solid var(--border-color)' }}>
              <i className="fa-solid fa-database"></i>
            </div>
            <div>
              <div style={{ fontSize: '0.78rem', color: 'var(--text-muted)', textTransform: 'uppercase', fontWeight: 700 }}>Total Log Absensi</div>
              <div style={{ fontSize: '1.4rem', fontWeight: 800 }}>{totalLogs} History</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
