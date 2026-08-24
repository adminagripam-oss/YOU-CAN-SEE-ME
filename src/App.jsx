import React, { useState, useEffect, useCallback } from 'react';
import { human, loadHumanWithFallback } from './humanSingleton';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { API_BASE_URL, fetchWithTimeout } from './config';

import { supabase } from './supabaseClient';
import { db, getUnsyncedLogs, cacheUserMasterVector } from './db';
import { syncPendingAttendanceLogs, initAutoSyncListener } from './syncEngine';
import { AuthProvider } from './context/AuthContext';
import { initSQLite } from './services/sqliteService';
import { Capacitor } from '@capacitor/core';
import { Network } from '@capacitor/network';
import ProtectedRoute from './components/ProtectedRoute';
import PublicRoute from './components/PublicRoute';
import AuthLayout from './layouts/AuthLayout';
import DashboardLayout from './layouts/DashboardLayout';
import LoginPage from './pages/LoginPage';
import DashboardPage from './pages/DashboardPage';
import AbsensiPage from './pages/AbsensiPage';
import KaryawanPage from './pages/KaryawanPage';
import DaftarKaryawanPage from './pages/DaftarKaryawanPage';
import LogsPage from './pages/LogsPage';
import EnterpriseAnalyticsPage from './pages/EnterpriseAnalyticsPage';
import ShadcnToast from './components/ShadcnToast';
import ConfirmModal from './components/ConfirmModal';
import OfflineOrderForm from './components/OfflineOrderForm';

function AppContent() {
  const [employees, setEmployees] = useState([]);
  const [logs, setLogs] = useState([]);
  const [modelsLoaded, setModelsLoaded] = useState(false);
  const [modelStatusText, setModelStatusText] = useState('Memuat Model AI Biometrik Wajah...');
  const [toasts, setToasts] = useState([]);

  // Theme State (Dark / Light)
  const [theme, setTheme] = useState(() => {
    return localStorage.getItem('app-theme') || 'light';
  });

  useEffect(() => {
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('app-theme', theme);
  }, [theme]);

  const toggleTheme = () => {
    setTheme((prev) => (prev === 'dark' ? 'light' : 'dark'));
  };

  // Offline PWA & Auto-Sync State
  const [isOnline, setIsOnline] = useState(typeof navigator !== 'undefined' ? navigator.onLine : true);
  const [unsyncedCount, setUnsyncedCount] = useState(0);
  const [isSyncing, setIsSyncing] = useState(false);
  const [dbReady, setDbReady] = useState(false);

  // SQLite Native Initializer
  useEffect(() => {
    async function setupStorage() {
      try {
        await initSQLite();
      } catch (err) {
        console.error('[App] SQLite initialization error:', err);
      } finally {
        setDbReady(true);
      }
    }
    setupStorage();
  }, []);

  const [confirmModalConfig, setConfirmModalConfig] = useState({
    isOpen: false,
    title: '',
    message: '',
    confirmText: 'Ya, Lanjutkan',
    onConfirm: null,
  });

  // Toast Helper
  const showToast = useCallback((title, description, type = 'info') => {
    const id = Date.now() + Math.random();
    setToasts((prev) => [...prev, { id, title, description, type }]);
    setTimeout(() => {
      setToasts((prev) => prev.filter((item) => item.id !== id));
    }, 4500);
  }, []);

  // Confirm Modal Helper
  const openConfirmModal = useCallback(({ title, message, confirmText, onConfirm }) => {
    setConfirmModalConfig({
      isOpen: true,
      title,
      message,
      confirmText: confirmText || 'Ya, Lanjutkan',
      onConfirm: () => {
        setConfirmModalConfig((prev) => ({ ...prev, isOpen: false }));
        if (onConfirm) onConfirm();
      },
    });
  }, []);

  const closeConfirmModal = () => {
    setConfirmModalConfig((prev) => ({ ...prev, isOpen: false }));
  };

  // Fetch Employees (2-Tier Fallback: Direct Supabase -> IndexedDB Cache)
  const fetchEmployees = useCallback(async () => {
    let empData = null;
    let dataSource = 'supabase';

    // Tier 1: Direct Supabase Cloud Database Query (HTTPS)
    if (!empData) {
      try {
        const { data, error } = await supabase
          .from('employees')
          .select('*')
          .order('created_at', { ascending: false });

        if (!error && data) {
          let descData = [];
          try {
            const { data: d } = await supabase.from('master_descriptors').select('employee_id');
            descData = d || [];
          } catch (e) {
            console.warn('[FETCH DESCRIPTORS ERROR]:', e.message);
          }
          const registeredIds = new Set(descData.map(d => String(d.employee_id)));

          empData = data.map(emp => ({
            ...emp,
            has_master_biometric: emp.has_master_biometric === true || registeredIds.has(String(emp.id))
          }));
          console.log('[SUPABASE DIRECT] Fetched and resolved biometrics for', empData.length, 'employees directly from cloud');
        }
      } catch (err) {
        console.warn('[FETCH EMPLOYEES SUPABASE DIRECT WARN]:', err.message);
        dataSource = 'indexeddb';
      }
    }

    // Tier 3: IndexedDB Local Offline Cache
    if (!empData) {
      try {
        const cached = await db.employees_cache.toArray();
        if (cached) {
          empData = cached;
          console.log('[INDEXEDDB CACHE] Loaded', cached.length, 'cached employees offline');
        }
      } catch (err) {
        console.error('[FETCH EMPLOYEES INDEXEDDB ERROR]:', err.message);
      }
    }

    if (empData) {
      setEmployees(empData);
      
      // Persist to IndexedDB local cache for mobile offline support
      // Only clear and rebuild if we got fresh data from API or Supabase
      if (dataSource !== 'indexeddb') {
        try {
          await db.employees_cache.clear();
          if (empData.length > 0) {
            await db.employees_cache.bulkPut(empData);
            for (const emp of empData) {
              if (emp.descriptor_json || emp.descriptor) {
                await cacheUserMasterVector(emp);
              }
            }
          }
        } catch (cacheErr) {
          console.warn('[INDEXEDDB BULK PUT WARN]:', cacheErr.message);
        }
      }
    }
  }, []);

  // Fetch Attendance Logs (1-Tier: Direct Supabase)
  // Fetch Attendance Logs (2-Tier: Supabase + Offline Local Queue with Deduplication)
  const fetchLogs = useCallback(async () => {
    let onlineLogs = [];

    // Step 1: Query Supabase online logs
    try {
      const { data: rawLogs, error } = await supabase
        .from('attendance_logs')
        .select('*')
        .order('timestamp', { ascending: false });

      if (!error && rawLogs) {
        const empIds = [...new Set(rawLogs.map((l) => l.employee_id))].filter(Boolean);
        let empMap = new Map();
        if (empIds.length > 0) {
          const { data: empData } = await supabase
            .from('employees')
            .select('id, nik, name, department, afdeling')
            .in('id', empIds);
          if (empData) {
            empMap = new Map(empData.map((e) => [e.id, e]));
          }
        }

        onlineLogs = rawLogs.map((log) => {
          const emp = empMap.get(log.employee_id) || {};
          const typeLabel =
            log.attendance_type ||
            (log.status?.includes('CHECK-OUT') || log.location?.includes('CHECK-OUT') ? 'CHECK-OUT' : 'CHECK-IN');
          return {
            ...log,
            attendance_type: typeLabel,
            nik: emp.nik || log.nik || '-',
            name: emp.name || log.name || `Karyawan #${log.employee_id}`,
            department: emp.department || log.department || '-',
            afdeling: emp.afdeling || log.afdeling || '-'
          };
        });
      }
    } catch (err) {
      console.warn('[FETCH LOGS SUPABASE DIRECT WARN]:', err.message);
    }

    // Step 2: Query offline pending logs from SQLite / IndexedDB
    let offlineLogs = [];
    try {
      const pending = await getUnsyncedLogs();
      if (pending && pending.length > 0) {
        offlineLogs = pending.map(log => {
          const typeLabel = log.attendance_type || 'CHECK-IN';
          return {
            id: `offline_${log.id}`,
            employee_id: log.employee_id,
            timestamp: log.timestamp || new Date().toISOString(),
            location: log.location || 'Offline Queue',
            status: log.status || 'Hadir (Offline)',
            euclidean_distance: log.euclidean_distance || 0,
            attendance_type: typeLabel,
            nik: log.nik || '-',
            name: log.name || 'Karyawan',
            department: log.department || '-',
            afdeling: log.afdeling || '-',
            isOfflineQueue: true
          };
        });
      }
    } catch (e) {
      console.warn('[FETCH OFFLINE LOGS ERROR]:', e);
    }

    // Step 3: Merge and de-duplicate based on employee_id and timestamp (to nearest second)
    const mergedLogs = [...offlineLogs];
    const onlineSignatures = new Set();

    onlineLogs.forEach(oLog => {
      if (oLog.timestamp) {
        const dateStr = new Date(oLog.timestamp).toISOString().substring(0, 19);
        onlineSignatures.add(`${oLog.employee_id}_${dateStr}`);
      }
      mergedLogs.push(oLog);
    });

    const finalLogs = [];
    const seen = new Set();

    mergedLogs.forEach(log => {
      if (!log.timestamp) return;
      const dateStr = new Date(log.timestamp).toISOString().substring(0, 19);
      const signature = `${log.employee_id}_${dateStr}`;
      
      if (!seen.has(signature)) {
        seen.add(signature);
        
        // Skip offline log if its signature is already in online logs
        if (log.isOfflineQueue && onlineSignatures.has(signature)) {
          return;
        }
        finalLogs.push(log);
      }
    });

    // Sort by timestamp descending
    finalLogs.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp));
    setLogs(finalLogs);
  }, []);

  // Check Unsynced Count from IndexedDB
  const refreshUnsyncedCount = useCallback(async () => {
    try {
      const items = await getUnsyncedLogs();
      setUnsyncedCount(items ? items.length : 0);
    } catch {
      setUnsyncedCount(0);
    }
  }, []);

  // Manual Trigger Auto-Sync
  const handleManualSync = async () => {
    setIsSyncing(true);
    await syncPendingAttendanceLogs(showToast, () => {
      fetchLogs();
      refreshUnsyncedCount();
    });
    setIsSyncing(false);
  };

  // Network Listener Setup (Supports both native SQLite/Network and web IndexedDB)
  useEffect(() => {
    const handleStatusChange = (isConnected) => {
      setIsOnline(isConnected);
      if (isConnected) {
        showToast('Mode Online', 'Aplikasi terhubung ke internet.', 'success');
      } else {
        showToast('Mode Offline', 'Aplikasi berjalan luring (offline).', 'warning');
      }
    };

    // 1. Initial status detection
    const checkInitialConnection = async () => {
      let isConnected = navigator.onLine;
      if (Capacitor.isNativePlatform()) {
        try {
          const status = await Network.getStatus();
          isConnected = status.connected;
        } catch (e) {
          isConnected = navigator.onLine;
        }
      }
      setIsOnline(isConnected);
      if (isConnected) {
        showToast('Mode Online', 'Aplikasi terhubung ke internet.', 'success');
      } else {
        showToast('Mode Offline', 'Aplikasi berjalan luring (offline).', 'warning');
      }
    };
    checkInitialConnection();

    // 2. Event Listeners
    let networkListener = null;

    if (Capacitor.isNativePlatform()) {
      Network.addListener('networkStatusChange', (status) => {
        handleStatusChange(status.connected);
      }).then(handle => {
        networkListener = handle;
      });
    } else {
      const handleWebOnline = () => handleStatusChange(true);
      const handleWebOffline = () => handleStatusChange(false);
      window.addEventListener('online', handleWebOnline);
      window.addEventListener('offline', handleWebOffline);

      const cleanupSync = initAutoSyncListener(showToast, () => {
        fetchLogs();
        refreshUnsyncedCount();
      });

      refreshUnsyncedCount();

      return () => {
        window.removeEventListener('online', handleWebOnline);
        window.removeEventListener('offline', handleWebOffline);
        cleanupSync();
      };
    }

    const cleanupSync = initAutoSyncListener(showToast, () => {
      fetchLogs();
      refreshUnsyncedCount();
    });

    refreshUnsyncedCount();

    return () => {
      if (networkListener) {
        networkListener.remove();
      }
      cleanupSync();
    };
  }, [fetchLogs, refreshUnsyncedCount, showToast]);

  // Initial Data & face-api Model Loading (waits for dbReady)
  useEffect(() => {
    if (!dbReady) return;

    fetchEmployees();
    fetchLogs();

    async function loadHumanModels() {
      try {
        setModelStatusText('Mendeteksi GPU & Memuat Model AI Biometrik Wajah...');
        await loadHumanWithFallback();
        setModelsLoaded(true);
        setModelStatusText('Model AI Siap!');
        console.log('[App] Model AI berhasil dimuat, backend:', window.__humanBackend || 'webgl');
      } catch (err) {
        console.error('[MODEL LOAD ERROR]:', err);
        setModelStatusText('❌ Gagal memuat model AI: ' + err.message);
        // Jangan set modelsLoaded = true — model benar-benar belum siap
      }
    }

    loadHumanModels();
  }, [dbReady, fetchEmployees, fetchLogs]);

  return (
    <>
      <ShadcnToast toasts={toasts} />
      <ConfirmModal
        isOpen={confirmModalConfig.isOpen}
        title={confirmModalConfig.title}
        message={confirmModalConfig.message}
        confirmText={confirmModalConfig.confirmText}
        onConfirm={confirmModalConfig.onConfirm}
        onCancel={closeConfirmModal}
      />

      <Routes>
        {/* PUBLIC ROUTES (Auth Layout) */}
        <Route element={<PublicRoute />}>
          <Route element={<AuthLayout theme={theme} toggleTheme={toggleTheme} />}>
            <Route
              path="/login"
              element={
                <LoginPage
                  employees={employees}
                  showToast={showToast}
                  theme={theme}
                  toggleTheme={toggleTheme}
                  refreshEmployees={fetchEmployees}
                />
              }
            />
          </Route>
        </Route>

        <Route path="/analytics" element={<EnterpriseAnalyticsPage employees={employees} logs={logs} />} />
        
        {/* Halaman Uji Coba Order Form (Bisa diakses tanpa login) */}
        <Route path="/order-form" element={<OfflineOrderForm />} />

        {/* PUBLIC ROUTES DENGAN DASHBOARD LAYOUT (Tanpa Login) */}
        <Route
          element={
            <DashboardLayout
              isOnline={isOnline}
              unsyncedCount={unsyncedCount}
              isSyncing={isSyncing}
              onManualSync={handleManualSync}
              theme={theme}
              toggleTheme={toggleTheme}
            />
          }
        >
          <Route
            path="/absensi"
            element={
              <AbsensiPage
                employees={employees}
                modelsLoaded={modelsLoaded}
                modelStatusText={modelStatusText}
                showToast={showToast}
                refreshLogs={fetchLogs}
              />
            }
          />
        </Route>

        {/* PROTECTED ROUTES (Hanya Admin) */}
        <Route element={<ProtectedRoute />}>
          <Route
            element={
              <DashboardLayout
                isOnline={isOnline}
                unsyncedCount={unsyncedCount}
                isSyncing={isSyncing}
                onManualSync={handleManualSync}
                theme={theme}
                toggleTheme={toggleTheme}
              />
            }
          >
            <Route
              path="/dashboard"
              element={
                <DashboardPage
                  employees={employees}
                  logs={logs}
                  modelsLoaded={modelsLoaded}
                />
              }
            />

            <Route
              path="/karyawan"
              element={
                <KaryawanPage
                  employees={employees}
                  modelsLoaded={modelsLoaded}
                  showToast={showToast}
                  refreshEmployees={fetchEmployees}
                  openConfirmModal={openConfirmModal}
                />
              }
            />
            <Route
              path="/daftar-karyawan"
              element={
                <DaftarKaryawanPage
                  employees={employees}
                  modelsLoaded={modelsLoaded}
                  showToast={showToast}
                  refreshEmployees={fetchEmployees}
                  refreshLogs={fetchLogs}
                  openConfirmModal={openConfirmModal}
                />
              }
            />
            <Route
              path="/logs"
              element={
                <LogsPage
                  logs={logs}
                  refreshLogs={fetchLogs}
                  showToast={showToast}
                  openConfirmModal={openConfirmModal}
                />
              }
            />
          </Route>
        </Route>

        {/* Fallback Index Route */}
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="*" element={<Navigate to="/absensi" replace />} />
      </Routes>
    </>
  );
}

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <AppContent />
      </BrowserRouter>
    </AuthProvider>
  );
}
