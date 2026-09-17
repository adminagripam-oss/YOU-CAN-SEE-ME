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
  pendingCheckOutsCount = 0,
  isPastShiftEnd = false,
  onCheckUpdate,
  hasOTAUpdate,
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

        {/* OTA Update Button */}
        {isOnline && onCheckUpdate && (
          <button
            type="button"
            className="topbar-sync-btn"
            onClick={onCheckUpdate}
            title={hasOTAUpdate ? "Pembaruan Tersedia!" : "Cek Pembaruan Aplikasi (OTA)"}
            style={{ background: 'var(--accent-primary)', color: 'white', border: 'none', position: 'relative' }}
          >
            <i className="fa-solid fa-cloud-arrow-down"></i>
            <span style={{ marginLeft: '6px' }}>{hasOTAUpdate ? 'Update Baru!' : 'Cek Pembaruan'}</span>
            
            {/* Notification Badge */}
            {hasOTAUpdate && (
              <span style={{ position: 'absolute', top: '-4px', right: '-4px', display: 'flex', height: '12px', width: '12px' }}>
                <span style={{ animation: 'ping 1s cubic-bezier(0, 0, 0.2, 1) infinite', position: 'absolute', display: 'inline-flex', height: '100%', width: '100%', borderRadius: '9999px', backgroundColor: '#f87171', opacity: 0.75 }}></span>
                <span style={{ position: 'relative', display: 'inline-flex', borderRadius: '9999px', height: '12px', width: '12px', backgroundColor: '#ef4444' }}></span>
              </span>
            )}
          </button>
        )}

        {/* Pending Check-Out Warning Badge */}
        {pendingCheckOutsCount > 0 && isPastShiftEnd && (
          <div
            style={{
              display: 'flex',
              alignItems: 'center',
              gap: '6px',
              padding: '6px 12px',
              borderRadius: '8px',
              background: 'rgba(249, 115, 22, 0.12)',
              border: '1px solid rgba(249, 115, 22, 0.3)',
              color: '#f97316',
              fontSize: '0.8rem',
              fontWeight: 700
            }}
            title={`${pendingCheckOutsCount} karyawan belum check-out setelah jam pulang kerja (17:00)`}
          >
            <i className="fa-solid fa-triangle-exclamation"></i>
            <span>{pendingCheckOutsCount} Lupa Out</span>
          </div>
        )}
      </div>

      <div className="topbar-right" style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>

        {/* User Account Info - RESPONSIVE FOR MOBILE */}
        {user && (
          <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
            <div style={{ 
              width: '38px', 
              height: '38px', 
              borderRadius: '50%', 
              overflow: 'hidden', 
              border: '1.5px solid var(--border-color)',
              flexShrink: 0,
              background: '#fff',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              boxShadow: '0 2px 5px rgba(0,0,0,0.05)'
            }}>
              <img src="/icon-foreground.png" alt="AgriFace" style={{ width: '100%', height: '100%', objectFit: 'contain' }} />
            </div>

            <div style={{ display: 'flex', flexDirection: 'column' }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
                <span style={{ fontSize: '0.875rem', fontWeight: 700, color: 'var(--text-main)', maxWidth: '110px', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>
                  {user.name}
                </span>
                <span style={{ 
                  background: 'var(--text-main)', 
                  color: 'var(--bg-card)', 
                  fontSize: '0.65rem', 
                  fontWeight: 800, 
                  padding: '2px 6px', 
                  borderRadius: '4px',
                  lineHeight: 1
                }}>
                  APN
                </span>
              </div>
              <span style={{ fontSize: '0.7rem', color: 'var(--text-muted)', fontWeight: 500, maxWidth: '140px', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap', marginTop: '2px' }}>
                {user.kebun || 'Head Office'}
              </span>
            </div>
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
