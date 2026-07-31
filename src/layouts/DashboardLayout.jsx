import React, { useState } from 'react';
import { Outlet } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';

export default function DashboardLayout({ isOnline, unsyncedCount, isSyncing, onManualSync, theme, toggleTheme }) {
  const [sidebarOpen, setSidebarOpen] = useState(true);

  return (
    <div className="dashboard-layout-container">
      <div className={`dashboard-body ${sidebarOpen ? 'sidebar-expanded' : 'sidebar-collapsed'}`}>
        {/* Overlay for mobile when sidebar is open */}
        {sidebarOpen && (
          <div
            className="sidebar-overlay"
            onClick={() => setSidebarOpen(false)}
          />
        )}

        {/* Persistent Sidebar */}
        <Sidebar isOpen={sidebarOpen} onClose={() => setSidebarOpen(false)} />

        {/* Main Workspace Area */}
        <div className="dashboard-main-area">
          <Topbar
            theme={theme}
            toggleTheme={toggleTheme}
            isOnline={isOnline}
            unsyncedCount={unsyncedCount}
            isSyncing={isSyncing}
            onManualSync={onManualSync}
            sidebarOpen={sidebarOpen}
            onToggleSidebar={() => setSidebarOpen((prev) => !prev)}
          />

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
