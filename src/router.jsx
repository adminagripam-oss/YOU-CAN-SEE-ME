/**
 * Static router configuration. Route elements use wrapper components that
 * source their props from AppDataContext — keeping page components unchanged.
 *
 * Route hierarchy:
 *   /                     → HomeRedirect (role-aware)
 *   /login                → LoginPage  (PublicRoute + AuthLayout)
 *   /absensi              → AbsensiPage (PUBLIC — no auth, no sidebar, kiosk mode)
 *   /analytics            → EnterpriseAnalyticsPage (no role guard)
 *   /order-form           → OfflineOrderForm
 *   /dashboard            → DashboardLayout (ProtectedRoute)
 *   /logs                 → ...
 *   /logs/monitoring      → ...
 *   /daftar-karyawan      → ...
 *   /daftar-karyawan/mutasi → ...
 *   /karyawan             → (estate_admin only)
 *   /sinkronisasi         → Placeholder (estate_admin only)
 *   /hasil-panen          → Placeholder (estate_admin only)
 *   /pengajuan            → Placeholder (estate_admin only)
 *   /persetujuan/inbox    → Placeholder (headoffice_admin only)
 *   /persetujuan/riwayat  → Placeholder (headoffice_admin only)
 */

import React, { lazy, Suspense } from 'react';
import { createBrowserRouter, Navigate, useRouteError } from 'react-router-dom';
import { useAuth } from './context/AuthContext';
import { useAppData } from './context/AppDataContext';
import { firstPath, MENU } from './config/menu';
import ProtectedRoute from './components/ProtectedRoute';
import PublicRoute from './components/PublicRoute';
import AuthLayout from './layouts/AuthLayout';
import DashboardLayout from './layouts/DashboardLayout';
import RoleGuard from './components/layout/RoleGuard';
import Placeholder from './pages/Placeholder';
import OfflineOrderForm from './components/OfflineOrderForm';

// Lazy-loaded pages
const LoginPage             = lazy(() => import('./pages/LoginPage'));
const DashboardPage         = lazy(() => import('./pages/DashboardPage'));
const AbsensiPage           = lazy(() => import('./pages/AbsensiPage'));
const KaryawanPage          = lazy(() => import('./pages/KaryawanPage'));
const DaftarKaryawanPage    = lazy(() => import('./pages/DaftarKaryawanPage'));
const MutasiKaryawanPage    = lazy(() => import('./pages/MutasiKaryawanPage'));
const LogsPage              = lazy(() => import('./pages/LogsPage'));
const MonitoringAbsensiPage = lazy(() => import('./pages/MonitoringAbsensiPage'));
const EnterpriseAnalyticsPage = lazy(() => import('./pages/EnterpriseAnalyticsPage'));

function PageFallback() {
  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '60vh', color: 'var(--text-muted)' }}>
      <span style={{ fontSize: '0.875rem' }}>Memuat halaman...</span>
    </div>
  );
}

function wrap(el) {
  return <Suspense fallback={<PageFallback />}>{el}</Suspense>;
}

// ── Error Boundary ──────────────────────────────────────────────────────────

function AppErrorBoundary() {
  const error = useRouteError();
  console.error('[AppErrorBoundary]', error);
  return (
    <div style={{
      padding: '3rem 1.5rem', textAlign: 'center', color: 'var(--text-main)',
      display: 'flex', flexDirection: 'column', alignItems: 'center',
      justifyContent: 'center', minHeight: '60vh',
    }}>
      <div style={{ fontSize: '2.5rem', marginBottom: '1rem' }}>⚠️</div>
      <h2 style={{ fontSize: '1.25rem', fontWeight: 800, marginBottom: '0.5rem', color: 'var(--text-main)' }}>
        Terjadi Kesalahan Aplikasi
      </h2>
      <p style={{ color: 'var(--text-muted)', fontSize: '0.85rem', maxWidth: '450px', marginBottom: '1.5rem', lineHeight: 1.5 }}>
        {error?.message || 'Aplikasi mengalami kendala teknis tak terduga. Silakan muat ulang halaman.'}
      </p>
      <button
        onClick={() => window.location.reload()}
        style={{
          padding: '10px 20px', borderRadius: '8px', background: 'var(--accent-primary)',
          color: '#ffffff', border: 'none', fontWeight: 700, fontSize: '0.85rem', cursor: 'pointer',
          boxShadow: '0 2px 8px rgba(0,0,0,0.15)',
        }}
      >
        Muat Ulang Halaman
      </button>
    </div>
  );
}

// ── Utility: Role-aware home redirect ───────────────────────────────────────

function HomeRedirect() {
  const { user } = useAuth();
  if (!user) return <Navigate to="/login" replace />;
  return <Navigate to={firstPath(MENU, user.role) ?? '/dashboard'} replace />;
}

// ── Auth layout wrapper (reads theme from context) ──────────────────────────

function AuthLayoutWrapper() {
  const { theme, toggleTheme } = useAppData();
  return <AuthLayout theme={theme} toggleTheme={toggleTheme} />;
}

// ── Route-level wrappers: bridge AppDataContext → existing page props ────────

function LoginRoute() {
  const { employees, showToast, theme, toggleTheme, fetchEmployees } = useAppData();
  return wrap(
    <LoginPage
      employees={employees}
      showToast={showToast}
      theme={theme}
      toggleTheme={toggleTheme}
      refreshEmployees={fetchEmployees}
    />
  );
}

function AbsensiRoute() {
  const { employees, modelsLoaded, modelStatusText, showToast, fetchLogs, fetchEmployees } = useAppData();
  return wrap(
    <AbsensiPage
      employees={employees}
      modelsLoaded={modelsLoaded}
      modelStatusText={modelStatusText}
      showToast={showToast}
      refreshLogs={fetchLogs}
      refreshEmployees={fetchEmployees}
    />
  );
}

function DashboardRoute() {
  const { employees, logs, modelsLoaded } = useAppData();
  return wrap(<DashboardPage employees={employees} logs={logs} modelsLoaded={modelsLoaded} />);
}

function KaryawanRoute() {
  const { employees, modelsLoaded, showToast, fetchEmployees, openConfirmModal } = useAppData();
  return wrap(
    <KaryawanPage
      employees={employees}
      modelsLoaded={modelsLoaded}
      showToast={showToast}
      refreshEmployees={fetchEmployees}
      openConfirmModal={openConfirmModal}
    />
  );
}

function DaftarKaryawanRoute() {
  const { isOnline, employees, modelsLoaded, showToast, fetchEmployees, fetchLogs, openConfirmModal } = useAppData();
  return wrap(
    <DaftarKaryawanPage
      isOnline={isOnline}
      employees={employees}
      modelsLoaded={modelsLoaded}
      showToast={showToast}
      refreshEmployees={fetchEmployees}
      refreshLogs={fetchLogs}
      openConfirmModal={openConfirmModal}
    />
  );
}

function MutasiRoute() {
  const { employees, showToast } = useAppData();
  return wrap(<MutasiKaryawanPage employees={employees} showToast={showToast} />);
}

function LogsRoute() {
  const { logs, fetchLogs, showToast, openConfirmModal } = useAppData();
  return wrap(
    <LogsPage
      logs={logs}
      refreshLogs={fetchLogs}
      showToast={showToast}
      openConfirmModal={openConfirmModal}
    />
  );
}

function MonitoringRoute() {
  const { employees, logs } = useAppData();
  return wrap(<MonitoringAbsensiPage employees={employees} logs={logs} />);
}

function AnalyticsRoute() {
  const { employees, logs } = useAppData();
  return wrap(<EnterpriseAnalyticsPage employees={employees} logs={logs} />);
}

// ── Router definition (static — created once at module load) ─────────────────

const appRouter = createBrowserRouter([
  {
    path: '/',
    errorElement: <AppErrorBoundary />,
    children: [
      // Public: login
      {
        element: <PublicRoute />,
        children: [
          {
            element: <AuthLayoutWrapper />,
            children: [
              { path: 'login', element: <LoginRoute /> },
            ],
          },
        ],
      },



      // Misc public
      { path: 'analytics',   element: <AnalyticsRoute /> },
      { path: 'order-form',  element: <OfflineOrderForm /> },

      // Protected dashboard routes
      {
        element: <ProtectedRoute />,
        children: [
          {
            element: <DashboardLayout />,
            children: [
              { index: true,                    element: <HomeRedirect /> },
              { path: 'dashboard',              element: <DashboardRoute /> },
              { path: 'logs',                   element: <LogsRoute /> },
              { path: 'logs/monitoring',        element: <MonitoringRoute /> },
              { path: 'daftar-karyawan',        element: <DaftarKaryawanRoute /> },
              { path: 'daftar-karyawan/mutasi', element: <MutasiRoute /> },

              // Estate-admin-only routes
              {
                element: <RoleGuard allowedRoles={['estate_admin']} />,
                children: [
                  { path: 'absensi',     element: <AbsensiRoute /> },
                  { path: 'karyawan',    element: <KaryawanRoute /> },
                  { path: 'sinkronisasi', element: <Placeholder /> },
                  { path: 'hasil-panen', element: <Placeholder /> },
                  { path: 'pengajuan',   element: <Placeholder /> },
                ],
              },

              // HQ-only routes
              {
                element: <RoleGuard allowedRoles={['headoffice_admin']} />,
                children: [
                  { path: 'persetujuan/inbox',   element: <Placeholder /> },
                  { path: 'persetujuan/riwayat', element: <Placeholder /> },
                ],
              },
            ],
          },
        ],
      },

      { path: '',  element: <HomeRedirect /> },
      { path: '*', element: <Navigate to="/login" replace /> },
    ],
  },
]);

export default appRouter;
