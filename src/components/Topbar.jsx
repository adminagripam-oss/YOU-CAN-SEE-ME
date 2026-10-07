import React from 'react';
import { useAuth } from '../context/AuthContext';
import { useOnlineStatus } from '../hooks/useOnlineStatus';
import { SidebarTrigger } from '@/components/ui/sidebar';
import DynamicBreadcrumb from './DynamicBreadcrumb';
import {
  Sun,
  Moon,
  WifiOff,
  CloudUpload,
  CloudDownload,
  Loader2,
  TriangleAlert,
} from 'lucide-react';


export default function Topbar({
  theme,
  toggleTheme,
  isOnline,
  unsyncedCount,
  isSyncing,
  onManualSync,
  pendingCheckOutsCount = 0,
  isPastShiftEnd = false,
  onCheckUpdate,
  hasOTAUpdate,
}) {
  const { user } = useAuth();
  const isOnlineLocal = useOnlineStatus();

  return (
    <header className="app-topbar">
      {/* ── LEFT: SidebarTrigger + Separator + Breadcrumb + Badges ── */}
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          gap: '8px',
          flexWrap: 'nowrap',
          flex: 1,
          minWidth: 0,
          marginRight: '8px',
        }}
      >
        {/* Shadcn SidebarTrigger — self-contained toggle */}
        <SidebarTrigger />

        {/* Thin vertical separator */}
        <span className="topbar-sep" aria-hidden="true" />

        {/* Dynamic Breadcrumb */}
        <DynamicBreadcrumb />

        {/* Badges container (horizontal scroll on mobile) */}
        <div
          className="topbar-badges-wrapper"
          style={{
            display: 'flex',
            alignItems: 'center',
            gap: '8px',
            flexShrink: 0,
            marginLeft: '4px',
          }}
        >
          {/* Offline indicator — only visible when offline */}
          {!isOnlineLocal && (
            <span className="topbar-offline-badge" role="status" aria-live="polite">
              <WifiOff size={12} aria-hidden="true" />
              Offline
            </span>
          )}

          {/* Pending Sync Badge */}
          {unsyncedCount > 0 && (
            <button
              type="button"
              className="topbar-sync-btn"
              onClick={onManualSync}
              disabled={isSyncing}
              title={`${unsyncedCount} log pending sync ke server`}
            >
              {isSyncing ? <Loader2 size={13} className="spin" /> : <CloudUpload size={13} />}
              <span>{isSyncing ? 'Syncing...' : `${unsyncedCount} Pending`}</span>
            </button>
          )}

          {/* OTA Update Button (Theme-Adaptive for Light & Dark Mode) */}
          {isOnline && onCheckUpdate && (
            <button
              type="button"
              className="topbar-sync-btn"
              onClick={onCheckUpdate}
              title={hasOTAUpdate ? 'Pembaruan Tersedia!' : 'Cek Pembaruan Aplikasi (OTA)'}
              style={{
                background: 'var(--bg-card)',
                color: 'var(--text-main)',
                border: '1px solid var(--border-color)',
                position: 'relative',
                borderRadius: '8px',
                padding: '4px 10px',
                fontSize: '0.78rem',
                fontWeight: 700,
                display: 'inline-flex',
                alignItems: 'center',
                cursor: 'pointer',
                whiteSpace: 'nowrap',
              }}
            >
              <CloudDownload size={13} style={{ color: 'var(--accent-primary)' }} />
              <span style={{ marginLeft: '4px' }}>{hasOTAUpdate ? 'Update Baru!' : 'Cek Update'}</span>
              {hasOTAUpdate && (
                <span style={{ position: 'absolute', top: '-4px', right: '-4px', display: 'flex', height: '12px', width: '12px' }}>
                  <span style={{ animation: 'ping 1s cubic-bezier(0,0,0.2,1) infinite', position: 'absolute', display: 'inline-flex', height: '100%', width: '100%', borderRadius: '9999px', backgroundColor: '#f87171', opacity: 0.75 }} />
                  <span style={{ position: 'relative', display: 'inline-flex', borderRadius: '9999px', height: '12px', width: '12px', backgroundColor: '#ef4444' }} />
                </span>
              )}
            </button>
          )}

          {/* Pending Check-Out Warning */}
          {pendingCheckOutsCount > 0 && isPastShiftEnd && (
            <div
              style={{
                display: 'flex',
                alignItems: 'center',
                gap: '5px',
                padding: '5px 10px',
                borderRadius: '8px',
                background: 'rgba(249, 115, 22, 0.12)',
                border: '1px solid rgba(249, 115, 22, 0.3)',
                color: '#f97316',
                fontSize: '0.78rem',
                fontWeight: 700,
                flexShrink: 0,
                whiteSpace: 'nowrap',
              }}
              title={`${pendingCheckOutsCount} karyawan belum check-out setelah jam pulang kerja (17:00)`}
            >
              <TriangleAlert size={13} />
              <span>{pendingCheckOutsCount} Lupa Out</span>
            </div>
          )}
        </div>
      </div>

      {/* ── RIGHT: User Info + Theme Toggle ── */}
      <div style={{ display: 'flex', alignItems: 'center', gap: '10px', flexShrink: 0 }}>
        {/* Dark / Light Mode Toggle (Icon-only to prevent mobile view overlap) */}
        <button
          className="theme-toggle-btn icon-only"
          onClick={toggleTheme}
          title={theme === 'dark' ? 'Ganti ke Light Mode' : 'Ganti ke Dark Mode'}
          aria-label={theme === 'dark' ? 'Switch to Light Mode' : 'Switch to Dark Mode'}
          style={{
            flexShrink: 0,
            width: '32px',
            height: '32px',
            padding: 0,
            borderRadius: '50%',
            display: 'inline-flex',
            alignItems: 'center',
            justifyContent: 'center',
          }}
        >
          {theme === 'dark' ? <Sun size={15} /> : <Moon size={15} />}
        </button>
      </div>
    </header>
  );
}
