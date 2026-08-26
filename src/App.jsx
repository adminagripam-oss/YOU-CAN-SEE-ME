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
          console.log(`[${Capacitor.isNativePlatform() ? 'SQLITE DATABASE' : 'INDEXEDDB CACHE'}] Loaded`, cached.length, 'cached employees offline');
        }
      } catch (err) {
        console.error(`[FETCH EMPLOYEES ${Capacitor.isNativePlatform() ? 'SQLITE' : 'INDEXEDDB'} ERROR]:`, err.message);
      }
    }

    if (empData) {
      setEmployees(empData);
      
      // Persist to local cache for mobile offline support
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
          console.warn(`[${Capacitor.isNativePlatform() ? 'SQLITE' : 'INDEXEDDB'} BULK PUT WARN]:`, cacheErr.message);
        }
      }
    }
  }, []);

  // Fetch Attendance Logs (2-Tier: Supabase + Offline Local Queue with Deduplication)
  const fetchLogs = useCallback(async () => {
    let onlineLogs = [];

    // Helper to normalize CHECK_IN / CHECK-IN / CHECKIN to CHECK-IN, etc.
    const normalizeType = (type) => {
      if (!type) return 'CHECK-IN';
      const clean = type.toUpperCase().replace('_', '-');
      return clean === 'CHECKIN' ? 'CHECK-IN' : clean;
    };

    // Step 0: Load local cached employees for offline details mapping (always showing afdeling)
    let localEmployees = [];
    try {
      const cached = await db.employees_cache.toArray();
      if (cached) localEmployees = cached;
    } catch (e) {
      console.warn('[FETCH LOGS LOCAL CACHE WARN]:', e);
    }

    // Map of employees by ID
    const masterEmpMap = new Map(localEmployees.map(e => [String(e.id), e]));

    // Step 1: Query Supabase online logs
    try {
      const { data: rawLogs, error } = await supabase
        .from('attendance_logs')
        .select('*')
        .order('timestamp', { ascending: false });

      if (!error && rawLogs) {
        const empIds = [...new Set(rawLogs.map((l) => l.employee_id))].filter(Boolean);
        if (empIds.length > 0) {
          const { data: empData } = await supabase
            .from('employees')
            .select('id, nik, name, department, afdeling')
            .in('id', empIds);
          if (empData) {
            empData.forEach(e => {
              masterEmpMap.set(String(e.id), e);
            });
          }
        }

        onlineLogs = rawLogs.map((log) => {
          const emp = masterEmpMap.get(String(log.employee_id)) || {};
          const typeLabel = normalizeType(
            log.attendance_type ||
            (log.status?.includes('CHECK-OUT') || log.location?.includes('CHECK-OUT') ? 'CHECK-OUT' : 'CHECK-IN')
          );
          return {
            ...log,
            attendance_type: typeLabel,
            nik: emp.nik || log.nik || '-',
            name: emp.name || log.name || `Karyawan #${log.employee_id}`,
            department: emp.department || log.department || '-',
            afdeling: emp.afdeling || log.afdeling || '-'
          };
        });

        // Save today's online attendance status to local database (SQLite/IndexedDB) for offline access
        try {
          const todayStr = getLocalDateString(new Date());
          const statusMap = {};
          
          // Sort oldest to newest to reconstruct today's final state chronologically
          const todayOnlineLogs = onlineLogs.filter(log => getLocalDateString(log.timestamp) === todayStr);
          const sorted = [...todayOnlineLogs].sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));
          
          sorted.forEach(log => {
            const empId = String(log.employee_id);
            if (!statusMap[empId]) {
              statusMap[empId] = {
                hasCheckedIn: false,
                hasCheckedOut: false,
                checked_in: false,
                check_in_time: null,
                check_out_time: null
              };
            }
            
            if (log.attendance_type === 'CHECK-IN') {
              statusMap[empId].hasCheckedIn = true;
              statusMap[empId].checked_in = true;
              statusMap[empId].check_in_time = log.timestamp;
            } else if (log.attendance_type === 'CHECK-OUT') {
              statusMap[empId].hasCheckedOut = true;
              statusMap[empId].checked_in = false;
              statusMap[empId].check_out_time = log.timestamp;
            }
          });
          
          await db.today_attendance_cache.put(statusMap, todayStr);
          console.log(`[Local Database] Saved today's online attendance status for ${Object.keys(statusMap).length} employees in ${Capacitor.isNativePlatform() ? 'SQLite Database' : 'IndexedDB'}`);
        } catch (e) {
          console.warn('[Local Database] Failed to save today\'s attendance status:', e);
        }
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
          const emp = masterEmpMap.get(String(log.employee_id)) || {};
          const typeLabel = normalizeType(log.attendance_type || 'CHECK-IN');
          return {
            id: `offline_${log.id}`,
            employee_id: log.employee_id,
            timestamp: log.timestamp || new Date().toISOString(),
            location: log.location || 'Offline Queue',
            status: log.status || 'Hadir (Offline)',
            euclidean_distance: log.euclidean_distance || 0,
            attendance_type: typeLabel,
            nik: emp.nik || log.nik || '-',
            name: emp.name || log.name || 'Karyawan',
            department: emp.department || log.department || '-',
            afdeling: emp.afdeling || log.afdeling || '-',
            isOfflineQueue: true
          };
        });
      }
    } catch (e) {
      console.warn('[FETCH OFFLINE LOGS ERROR]:', e);
    }

    // Step 3: Merge and de-duplicate based on employee_id, local date (YYYY-MM-DD), and attendance_type
    const getLocalDateString = (ts) => {
      if (!ts) return '';
      const d = new Date(ts);
      if (isNaN(d.getTime())) return '';
      const year = d.getFullYear();
      const month = String(d.getMonth() + 1).padStart(2, '0');
      const day = String(d.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    };

    const mergedLogs = [...offlineLogs];
    const onlineSignatures = new Set();

    onlineLogs.forEach(oLog => {
      const localDate = getLocalDateString(oLog.timestamp);
      if (localDate) {
        const signature = `${oLog.employee_id}_${localDate}_${oLog.attendance_type}`;
        onlineSignatures.add(signature);
      }
      mergedLogs.push(oLog);
    });

    const finalLogs = [];
    const seen = new Set();

    mergedLogs.forEach(log => {
      const localDate = getLocalDateString(log.timestamp);
      if (!localDate) return;
      const signature = `${log.employee_id}_${localDate}_${log.attendance_type}`;
      
      // Skip offline log if its signature is already in online logs
      if (log.isOfflineQueue && onlineSignatures.has(signature)) {
        return;
      }

      if (!seen.has(signature)) {
        seen.add(signature);
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
        setModelStatusText('\u274c Gagal memuat model AI: ' + err.message);
        // Jangan set modelsLoaded = true — model benar-benar belum siap
      }
    }

    loadHumanModels();
  }, [dbReady, fetchEmployees, fetchLogs]);

  // ─── Supabase Realtime Sync + Polling Fallback ───────────────────────────
  // Mendengarkan perubahan data dari device lain secara real-time.
  // Berlaku HANYA saat online — tidak mempengaruhi sistem offline.
  useEffect(() => {
    if (!dbReady) return;

    // 1. Supabase Realtime: Langganan perubahan tabel employees & attendance_logs
    const realtimeChannel = supabase
      .channel('agriface-realtime-sync')
      .on(
        'postgres_changes',
        { event: '*', schema: 'public', table: 'employees' },
        (payload) => {
          console.log('[Realtime] employees change detected:', payload.eventType);
          fetchEmployees();
        }
      )
      .on(
        'postgres_changes',
        { event: '*', schema: 'public', table: 'attendance_logs' },
        (payload) => {
          console.log('[Realtime] attendance_logs change detected:', payload.eventType);
          fetchLogs();
        }
      )
      .on(
        'postgres_changes',
        { event: '*', schema: 'public', table: 'master_descriptors' },
        (payload) => {
          console.log('[Realtime] master_descriptors change detected:', payload.eventType);
          fetchEmployees(); // refresh biometric status
        }
      )
      .subscribe((status) => {
        if (status === 'SUBSCRIBED') {
          console.log('[Realtime] Connected to Supabase Realtime channel.');
        }
        if (status === 'CHANNEL_ERROR' || status === 'TIMED_OUT') {
          console.warn('[Realtime] Channel error/timeout — falling back to polling.');
        }
      });

    // 2. Fallback Polling 30 detik — backup jika WebSocket tidak tersedia
    const pollingInterval = setInterval(() => {
      if (navigator.onLine) {
        console.log('[Polling] Auto-refresh data setiap 30 detik...');
        fetchEmployees();
        fetchLogs();
      }
    }, 30000);

    return () => {
      supabase.removeChannel(realtimeChannel);
      clearInterval(pollingInterval);
      console.log('[Realtime] Unsubscribed from Supabase Realtime channel.');
    };
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
