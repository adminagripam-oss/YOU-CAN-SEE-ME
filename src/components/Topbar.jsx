import React from 'react';
import { useAuth } from '../context/AuthContext';

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
  const { user, logout } = useAuth();

  return (
    <header className="app-topbar">
      <div className="topbar-left">
        {/* Sidebar Hamburger Toggle Button */}
        <button
          className="sidebar-toggle-btn"
          onClick={onToggleSidebar}
          title={sidebarOpen ? 'Tutup Sidebar' : 'Buka Sidebar'}
          aria-label="Toggle Sidebar"
        >
          <i className={`fa-solid ${sidebarOpen ? 'fa-xmark' : 'fa-bars'}`}></i>
        </button>

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
