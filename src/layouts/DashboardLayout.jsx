import React, { useState, useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';

export default function DashboardLayout({ isOnline, unsyncedCount, isSyncing, onManualSync, theme, toggleTheme }) {
  const [sidebarOpen, setSidebarOpen] = useState(() => {
    const saved = localStorage.getItem('sidebar-open');
    return saved !== null ? saved === 'true' : true;
  });

  useEffect(() => {
    localStorage.setItem('sidebar-open', String(sidebarOpen));
  }, [sidebarOpen]);

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
        </div>
      </div>
    </div>
  );
}
