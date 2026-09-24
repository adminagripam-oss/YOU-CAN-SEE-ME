import React from 'react';
import { Outlet, useLocation } from 'react-router-dom';
import { AnimatePresence, motion } from 'framer-motion';
import { SidebarProvider } from '@/components/ui/sidebar';
import { AppSidebar } from '../components/AppSidebar';
import Topbar from '../components/Topbar';

export default function DashboardLayout({
  isOnline,
  unsyncedCount,
  isSyncing,
  onManualSync,
  onCheckUpdate,
  theme,
  toggleTheme,
  pendingCheckOutsCount = 0,
  isPastShiftEnd = false,
  hasOTAUpdate = false,
}) {
  const location = useLocation();

  // Read initial sidebar state from cookie (set by Shadcn SidebarProvider)
  const getDefaultOpen = () => {
    const match = document.cookie.match(/sidebar_state=([^;]+)/);
    if (match) return match[1] === 'true';
    return true;
  };

  return (
    <SidebarProvider defaultOpen={getDefaultOpen()}>
      {/* Sidebar */}
      <AppSidebar />

      {/* Main area: Topbar + Page Content — fills remaining horizontal space */}
      <div className="main-content-wrapper">
        {/* Sticky Topbar */}
        <Topbar
          theme={theme}
          toggleTheme={toggleTheme}
          isOnline={isOnline}
          unsyncedCount={unsyncedCount}
          isSyncing={isSyncing}
          onManualSync={onManualSync}
          onCheckUpdate={onCheckUpdate}
          pendingCheckOutsCount={pendingCheckOutsCount}
          isPastShiftEnd={isPastShiftEnd}
          hasOTAUpdate={hasOTAUpdate}
        />

        {/* Page Content with animated transitions */}
        <main className="dashboard-content-outlet">
          <AnimatePresence mode="wait">
            <motion.div
              key={location.pathname}
              initial={{ opacity: 0, x: 20 }}
              animate={{ opacity: 1, x: 0 }}
              exit={{ opacity: 0, x: -20 }}
              transition={{ duration: 0.25, ease: 'easeOut' }}
              style={{ width: '100%', minHeight: '100%' }}
            >
              <Outlet />
            </motion.div>
          </AnimatePresence>
        </main>
      </div>
    </SidebarProvider>
  );
}
