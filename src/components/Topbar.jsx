import React from 'react';
import { useAuth } from '../context/AuthContext';
import { Link } from 'react-router-dom';

export default function Topbar({
  theme,
  toggleTheme,
  isOnline,
  unsyncedCount,
  isSyncing,
  onManualSync,
  sidebarOpen,
  onToggleSidebar,
}) {
  const { user } = useAuth();

  return (
    <header className="app-topbar" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '8px', padding: '10px 16px', background: 'var(--bg-card)', borderBottom: '1px solid var(--border-color)' }}>
      <div className="topbar-left" style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
        {/* Sidebar Hamburger Toggle Button */}
        <button
          className="sidebar-toggle-btn"
          onClick={onToggleSidebar}
          title={sidebarOpen ? 'Tutup Sidebar' : 'Buka Sidebar'}
          aria-label="Toggle Sidebar"
        >
          <i className={`fa-solid ${sidebarOpen ? 'fa-xmark' : 'fa-bars'}`}></i>
        </button>

        {/* AgriFace Brand Title in Header */}
        <div style={{ fontSize: '1.15rem', fontWeight: 800, color: 'var(--text-main)', letterSpacing: '-0.01em', display: 'flex', alignItems: 'center', gap: '8px' }}>
          <img src="/logo.png" alt="Logo" style={{ width: '42px', height: '42px', objectFit: 'contain', imageRendering: 'high-quality', filter: 'drop-shadow(0 2px 4px rgba(0,0,0,0.15))' }} />
          <span>AgriFace</span>
        </div>

        {/* Live Status Indicator */}
        <div className={`topbar-live-badge ${isOnline ? 'online' : 'offline'}`}>
          <span className="live-pulse-dot"></span>
          <span className="live-label">{isOnline ? 'LIVE' : 'OFFLINE'}</span>
        </div>

        {/* Pending Sync Badge */}
        {unsyncedCount > 0 && (
          <button
            type="button"
            className="topbar-sync-btn"
            onClick={onManualSync}
            disabled={isSyncing}
            title={`${unsyncedCount} log pending sync ke server`}
          >
            <i className={`fa-solid ${isSyncing ? 'fa-spinner fa-spin' : 'fa-cloud-arrow-up'}`}></i>
            <span>{isSyncing ? 'Syncing...' : `${unsyncedCount} Pending`}</span>
          </button>
        )}
      </div>

      <div className="topbar-right" style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
        {/* Minimalist Black Camera Icon Scanner Button */}
        <Link
          to="/absensi"
          style={{
            width: '38px',
            height: '38px',
            borderRadius: '10px',
            background: '#0f172a',
            color: '#ffffff',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            fontSize: '1.05rem',
            textDecoration: 'none',
            boxShadow: '0 2px 8px rgba(0, 0, 0, 0.3)',
            transition: 'all 0.2s ease',
            border: '1px solid rgba(255, 255, 255, 0.15)',
            flexShrink: 0
          }}
          title="Buka Scanner Absensi Mandor"
          aria-label="Scanner Absensi"
        >
          <i className="fa-solid fa-camera"></i>
        </Link>

        {/* User Account Info - RESPONSIVE FOR MOBILE */}
        {user && (
          <div className="user-badge" style={{ maxWidth: '180px', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>
            <i className="fa-solid fa-user-check"></i>
            <span className="user-name" style={{ overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>
              {user.name} ({user.nik})
            </span>
          </div>
        )}

        {/* Dark / Light Mode Toggle Button (Far Right) */}
        <button
          className="theme-toggle-btn"
          onClick={toggleTheme}
          title={theme === 'dark' ? 'Ganti ke Light Mode' : 'Ganti ke Dark Mode'}
          style={{ flexShrink: 0 }}
        >
          <i className={`fa-solid ${theme === 'dark' ? 'fa-sun' : 'fa-moon'}`}></i>
          <span className="theme-toggle-label">{theme === 'dark' ? 'Light' : 'Dark'}</span>
        </button>
      </div>
    </header>
  );
}
