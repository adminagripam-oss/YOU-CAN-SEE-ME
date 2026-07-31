import React from 'react';
import { Outlet } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import NetworkStatusBar from '../components/NetworkStatusBar';

export default function DashboardLayout({ isOnline, unsyncedCount, isSyncing, onManualSync, theme, toggleTheme }) {
  return (
    <div className="dashboard-layout-container">
      {/* Network Online / Offline Status Banner */}
      <NetworkStatusBar
        isOnline={isOnline}
        unsyncedCount={unsyncedCount}
        isSyncing={isSyncing}
        onManualSync={onManualSync}
      />

      <div className="dashboard-body">
        {/* Persistent Sidebar */}
        <Sidebar />

        {/* Main Workspace Area */}
        <div className="dashboard-main-area">
          <Topbar theme={theme} toggleTheme={toggleTheme} />

          <main className="dashboard-content-outlet">
            <Outlet />
          </main>

          <footer>
            <p>Aplikasi Absensi Mobile Biometrik Wajah (Offline-First &amp; Auto-Sync PWA) &copy; 2026</p>
          </footer>
        </div>
      </div>
    </div>
  );
}
