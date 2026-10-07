import React from 'react';
import { Outlet, useLocation } from 'react-router-dom';
import { AnimatePresence, motion } from 'framer-motion';
import { SidebarInset, SidebarProvider } from '@/components/ui/sidebar';
import { AppSidebar } from '../components/AppSidebar';
import Topbar from '../components/Topbar';
import { useAppData } from '../context/AppDataContext';

export default function DashboardLayout() {
  const location = useLocation();
  const {
    theme, toggleTheme,
    isOnline, unsyncedCount, isSyncing, handleManualSync,
    pendingCheckOutsCount, isPastShiftEnd,
    checkForUpdates, hasOTAUpdate,
  } = useAppData();

  const getDefaultOpen = () => {
    const match = document.cookie.match(/sidebar_state=([^;]+)/);
    if (match) return match[1] === 'true';
    return true;
  };

  return (
    <SidebarProvider 
      defaultOpen={getDefaultOpen()} 
      style={{ '--sidebar-width': '18rem', '--sidebar-width-icon': '3.5rem' }}
    >
      <AppSidebar />

      {/* SidebarInset auto-adjusts margin-left in sync with SidebarProvider state */}
      <SidebarInset>
        <Topbar
          theme={theme}
          toggleTheme={toggleTheme}
          isOnline={isOnline}
          unsyncedCount={unsyncedCount}
          isSyncing={isSyncing}
          onManualSync={handleManualSync}
          onCheckUpdate={checkForUpdates}
          pendingCheckOutsCount={pendingCheckOutsCount}
          isPastShiftEnd={isPastShiftEnd}
          hasOTAUpdate={hasOTAUpdate}
        />

        <main style={{ flex: 1, display: 'flex', flexDirection: 'column', minWidth: 0 }}>
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
      </SidebarInset>
    </SidebarProvider>
  );
}
