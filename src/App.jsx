import React, { useState, useEffect, useCallback } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { API_BASE_URL } from './config';
import { supabase } from './supabaseClient';
import { db, getUnsyncedLogs, cacheUserMasterVector } from './db';
import { syncPendingAttendanceLogs, initAutoSyncListener } from './syncEngine';
import { AuthProvider } from './context/AuthContext';
import ProtectedRoute from './components/ProtectedRoute';
import PublicRoute from './components/PublicRoute';
import AuthLayout from './layouts/AuthLayout';
import DashboardLayout from './layouts/DashboardLayout';
import LoginPage from './pages/LoginPage';
import DashboardPage from './pages/DashboardPage';
import AbsensiPage from './pages/AbsensiPage';
import KaryawanPage from './pages/KaryawanPage';
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

  // Fetch Employees (3-Tier Fallback: Express API -> Direct Supabase -> IndexedDB Cache)
  const fetchEmployees = useCallback(async () => {
    let empData = null;

    // Tier 1: Express REST API Endpoint
    try {
      const res = await fetch(`${API_BASE_URL}/api/employees`);
      const data = await res.json();
      if (data.success && data.data && data.data.length > 0) {
        empData = data.data;
      }
    } catch (err) {
      console.warn('[FETCH EMPLOYEES API WARN - FALLING BACK TO SUPABASE DIRECT]:', err.message);
    }

    // Tier 2: Direct Supabase Cloud Database Query (HTTPS)
    if (!empData || empData.length === 0) {
      try {
        const { data, error } = await supabase
          .from('employees')
          .select('*')
          .order('created_at', { ascending: false });

        if (!error && data && data.length > 0) {
          empData = data;
          console.log('[SUPABASE DIRECT] Fetched', data.length, 'employees directly from cloud');
        }
      } catch (err) {
        console.warn('[FETCH EMPLOYEES SUPABASE DIRECT WARN]:', err.message);
      }
    }

    // Tier 3: IndexedDB Local Offline Cache
    if (!empData || empData.length === 0) {
      try {
        const cached = await db.employees_cache.toArray();
        if (cached && cached.length > 0) {
          empData = cached;
          console.log('[INDEXEDDB CACHE] Loaded', cached.length, 'cached employees offline');
        }
      } catch (err) {
        console.error('[FETCH EMPLOYEES INDEXEDDB ERROR]:', err.message);
      }
    }

    if (empData && empData.length > 0) {
      setEmployees(empData);
      // Persist to IndexedDB local cache for mobile offline support
      try {
        await db.employees_cache.bulkPut(empData);
        for (const emp of empData) {
          if (emp.descriptor_json || emp.descriptor) {
            await cacheUserMasterVector(emp);
          }
        }
      } catch (cacheErr) {
        console.warn('[INDEXEDDB BULK PUT WARN]:', cacheErr.message);
      }
    }
  }, []);

  // Fetch Attendance Logs (3-Tier Fallback: Express API -> Direct Supabase)
  const fetchLogs = useCallback(async () => {
    let logData = null;

    // Tier 1: Express REST API Endpoint
    try {
      const res = await fetch(`${API_BASE_URL}/api/attendance/logs`);
      const data = await res.json();
      if (data.success && data.data) {
        logData = data.data;
      }
    } catch (err) {
      console.warn('[FETCH LOGS API WARN - FALLING BACK TO SUPABASE DIRECT]:', err.message);
    }

    // Tier 2: Direct Supabase Cloud Database Query with Employee Detail Enrichment
    if (!logData) {
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
              .select('id, nik, name, department')
              .in('id', empIds);
            if (empData) {
              empMap = new Map(empData.map((e) => [e.id, e]));
            }
          }

          logData = rawLogs.map((log) => {
            const emp = empMap.get(log.employee_id) || {};
            const typeLabel =
              log.attendance_type ||
              (log.status?.includes('CHECK-OUT') || log.location?.includes('CHECK-OUT') ? 'CHECK-OUT' : 'CHECK-IN');
            return {
              ...log,
              attendance_type: typeLabel,
              nik: emp.nik || log.nik || '-',
              name: emp.name || log.name || `Karyawan #${log.employee_id}`,
              department: emp.department || log.department || '-'
            };
          });
        }
      } catch (err) {
        console.warn('[FETCH LOGS SUPABASE DIRECT WARN]:', err.message);
      }
    }

    if (logData) {
      setLogs(logData);
    }
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

  // Service Worker Registration for PWA & Network Listener Setup
  useEffect(() => {
    if ('serviceWorker' in navigator) {
      navigator.serviceWorker
        .register('./sw.js')
        .then((reg) => console.log('[PWA SW Registered]', reg.scope))
        .catch((err) => console.warn('[PWA SW Register Error]', err));
    }

    const updateOnlineStatus = () => {
      setIsOnline(navigator.onLine);
      if (navigator.onLine) {
        showToast('Koneksi Terhubung', 'Internet kembali aktif. Memulai Auto-Sync IndexedDB...', 'info');
      } else {
        showToast('Mode Offline', 'Tidak ada internet. Absensi akan disimpan di IndexedDB HP.', 'info');
      }
    };

    window.addEventListener('online', updateOnlineStatus);
    window.addEventListener('offline', updateOnlineStatus);

    const cleanupSync = initAutoSyncListener(showToast, () => {
      fetchLogs();
      refreshUnsyncedCount();
    });

    refreshUnsyncedCount();

    return () => {
      window.removeEventListener('online', updateOnlineStatus);
      window.removeEventListener('offline', updateOnlineStatus);
      cleanupSync();
    };
  }, [fetchLogs, refreshUnsyncedCount, showToast]);

  // Initial Data & face-api Model Loading
  useEffect(() => {
    fetchEmployees();
    fetchLogs();

    async function loadFaceApiModels() {
      if (!window.faceapi) {
        setModelStatusText('Menunggu library face-api.js...');
        return;
      }

      try {
        setModelStatusText('Memuat Model AI Biometrik Wajah (ResNet-34)...');
        const MODEL_URL = 'https://cdn.jsdelivr.net/npm/@vladmandic/face-api/model/';

        await Promise.all([
          window.faceapi.nets.ssdMobilenetv1.loadFromUri(MODEL_URL),
          window.faceapi.nets.tinyFaceDetector.loadFromUri(MODEL_URL),
          window.faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
          window.faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL),
        ]);

        setModelsLoaded(true);
        setModelStatusText('Model AI Siap!');
      } catch (err) {
        console.error('[MODEL LOAD ERROR]:', err);
        setModelStatusText('Gagal memuat Model AI: ' + err.message);
      }
    }

    loadFaceApiModels();
  }, [fetchEmployees, fetchLogs]);

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
        <Route path="/" element={<Navigate to="/absensi" replace />} />
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
