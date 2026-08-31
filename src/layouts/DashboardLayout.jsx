import React, { useState, useEffect, useRef } from 'react';
import { Outlet, useLocation } from 'react-router-dom';
import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';

export default function DashboardLayout({ isOnline, unsyncedCount, isSyncing, onManualSync, theme, toggleTheme }) {
  const location = useLocation();
  const [sidebarOpen, setSidebarOpen] = useState(() => {
    const saved = localStorage.getItem('sidebar-open');
    return saved !== null ? saved === 'true' : true;
  });

  // Pull-to-refresh State
  const [pullProgress, setPullProgress] = useState(0);
  const [isPulling, setIsPulling] = useState(false);
  const startYRef = useRef(0);
  const pullThreshold = 75; // px threshold to trigger sync

  useEffect(() => {
    localStorage.setItem('sidebar-open', String(sidebarOpen));
  }, [sidebarOpen]);

  useEffect(() => {
    const handleTouchStart = (e) => {
      // Jangan aktifkan pull-to-refresh pada halaman Absensi
      if (location.pathname.includes('/absensi')) return;

      // Hanya izinkan jika scroll halaman berada di paling atas (top)
      if (window.scrollY === 0) {
        startYRef.current = e.touches[0].pageY;
        setIsPulling(true);
      }
    };

    const handleTouchMove = (e) => {
      if (!isPulling) return;
      if (location.pathname.includes('/absensi')) return;

      const currentY = e.touches[0].pageY;
      const pullDistance = currentY - startYRef.current;

      if (pullDistance > 0 && window.scrollY === 0) {
        // Terapkan resistansi elastis
        const progress = Math.min(pullDistance * 0.4, 100);
        setPullProgress(progress);

        // Mencegah browser scroll / bounce native
        if (progress > 5 && e.cancelable) {
          e.preventDefault();
        }
      } else {
        setIsPulling(false);
        setPullProgress(0);
      }
    };

    const handleTouchEnd = () => {
      if (isPulling) {
        setIsPulling(false);
        if (pullProgress >= pullThreshold) {
          if (onManualSync && !isSyncing) {
            onManualSync();
          }
        }
        setPullProgress(0);
      }
    };

    window.addEventListener('touchstart', handleTouchStart, { passive: false });
    window.addEventListener('touchmove', handleTouchMove, { passive: false });
    window.addEventListener('touchend', handleTouchEnd);

    return () => {
      window.removeEventListener('touchstart', handleTouchStart);
      window.removeEventListener('touchmove', handleTouchMove);
      window.removeEventListener('touchend', handleTouchEnd);
    };
  }, [isPulling, pullProgress, onManualSync, isSyncing, location.pathname]);

  const indicatorStyle = {
    position: 'fixed',
    top: '70px',
    left: '50%',
    transform: `translateX(-50%) translateY(${isSyncing ? '20px' : `${Math.min(pullProgress * 0.8, 60)}px`})`,
    opacity: isSyncing || pullProgress > 0 ? 1 : 0,
    pointerEvents: 'none',
    zIndex: 9999,
    transition: isPulling ? 'none' : 'transform 0.3s cubic-bezier(0.25, 0.1, 0.25, 1), opacity 0.3s ease',
  };

  return (
    <div className="dashboard-layout-container">
      {/* Flutter-style Pull to Refresh Indicator */}
      <div 
        className={`pull-to-refresh-indicator ${isSyncing ? 'refreshing' : ''}`}
        style={indicatorStyle}
      >
        <svg 
          width="20" 
          height="20" 
          viewBox="0 0 24 24" 
          fill="none" 
          stroke="var(--accent-primary, #6366f1)" 
          strokeWidth="3" 
          strokeLinecap="round" 
          strokeLinejoin="round"
          style={{
            transform: isSyncing ? 'none' : `rotate(${pullProgress * 5}deg)`,
            transition: isSyncing ? 'none' : 'transform 0.1s ease-out',
            animation: isSyncing ? 'spin 1s linear infinite' : 'none'
          }}
        >
          <path d="M21.5 2v6h-6M21.34 15.57a10 10 0 1 1-.57-8.38l5.67-5.67" />
        </svg>
      </div>

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
