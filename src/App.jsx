import React, { useState, useEffect, useCallback, useMemo } from 'react';
import { human, loadHumanWithFallback } from './humanSingleton';
import { createBrowserRouter, RouterProvider, Navigate, Outlet } from 'react-router-dom';
import { API_BASE_URL, fetchWithTimeout } from './config';

import { supabase } from './supabaseClient';
import { db, getUnsyncedLogs, cacheUserMasterVector, getAllMasterVectors } from './db';
import { syncPendingAttendanceLogs, initAutoSyncListener } from './syncEngine';
import { AuthProvider, useAuth } from './context/AuthContext';
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
  const { user } = useAuth();
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
    // Tunggu admin user terotentikasi
    const savedAdmin = localStorage.getItem('logged_in_admin');
    if (!savedAdmin) return;
    const adminObj = JSON.parse(savedAdmin);

    let empData = null;
    let dataSource = 'supabase';

    // Tier 1: Direct Supabase Cloud Database Query (HTTPS)
    if (!empData) {
      try {
        let query = supabase.from('employees').select('*');

        // Saring berdasarkan role
        if (adminObj.role === 'estate_admin' && adminObj.kebun) {
          query = query.eq('nama_kebun', adminObj.kebun);
        } else if (adminObj.role === 'regional_admin' && adminObj.region) {
          query = query.eq('region', adminObj.region);
        }

        const { data, error } = await query.order('created_at', { ascending: false });

        if (!error && data) {
          let descData = [];
          let allMasters = [];
          try {
            allMasters = await getAllMasterVectors();
            const cachedEmpIds = new Set(allMasters.map(m => String(m.employee_id)));
            const missingEmpIds = data.filter(e => e.has_master_biometric && !cachedEmpIds.has(String(e.id))).map(e => e.id);

            if (missingEmpIds.length > 0) {
              console.log(`[Sync Pull] Mengunduh biometrics untuk ${missingEmpIds.length} karyawan baru/belum ter-cache dari cloud...`);
              const { data: d, error: descErr } = await supabase
                .from('master_descriptors')
                .select('employee_id, descriptor_json, geometric_descriptor_json')
                .in('employee_id', missingEmpIds);
              if (!descErr && d) {
                descData = d;
              }
            } else {
              console.log('[Sync Pull] Seluruh biometrics karyawan sudah ter-cache secara lokal (0 byte diunduh).');
            }
          } catch (e) {
            console.warn('[FETCH DESCRIPTORS ERROR]:', e.message);
          }
          
          const descMap = new Map(descData.map(d => [String(d.employee_id), d]));

          empData = data.map(emp => {
            const biometrics = descMap.get(String(emp.id));
            const localMaster = allMasters.find(m => String(m.employee_id) === String(emp.id));
            
            return {
              ...emp,
              has_master_biometric: emp.has_master_biometric === true || !!biometrics || !!localMaster,
              descriptor_json: biometrics ? biometrics.descriptor_json : (localMaster ? localMaster.descriptor_json : null),
              geometric_descriptor_json: biometrics ? biometrics.geometric_descriptor_json : null
            };
          });
          console.log('[SUPABASE DIRECT] Fetched and resolved biometrics bulk for', empData.length, 'employees directly from cloud');
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
          // Saring secara lokal di tablet offline
          if (adminObj.role === 'estate_admin' && adminObj.kebun) {
            empData = cached.filter(e => e.nama_kebun === adminObj.kebun);
          } else if (adminObj.role === 'regional_admin' && adminObj.region) {
            empData = cached.filter(e => e.region === adminObj.region);
          } else {
            empData = cached;
          }
          console.log(`[${Capacitor.isNativePlatform() ? 'SQLITE DATABASE' : 'INDEXEDDB CACHE'}] Loaded`, empData.length, 'cached employees offline');
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

    // Step 1: Fetch latest online logs from Supabase and cache them in local database
    try {
      let query = supabase.from('attendance_logs').select('*');

      // Saring log online berdasarkan karyawan yang berada di lingkup admin aktif
      const savedAdmin = localStorage.getItem('logged_in_admin');
      if (savedAdmin) {
        const adminObj = JSON.parse(savedAdmin);
        if (adminObj.role !== 'headoffice_admin') {
          const empIds = localEmployees.map(e => e.id);
          if (empIds.length > 0) {
            query = query.in('employee_id', empIds);
          } else {
            query = query.eq('employee_id', -1); // query kosong jika tidak ada karyawan
          }
        }
      }

      const { data: rawLogs, error } = await query
        .order('timestamp', { ascending: false });

      if (!error && rawLogs) {
        const empIds = [...new Set(rawLogs.map((l) => l.employee_id))].filter(Boolean);
        if (empIds.length > 0) {
          const { data: empData } = await supabase
            .from('employees')
            .select('id, nik, name, department, afdeling, nama_kebun')
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
            id: String(log.id),
            employee_id: log.employee_id,
            nik: emp.nik || log.nik || '-',
            name: emp.name || log.name || `Karyawan #${log.employee_id}`,
            department: emp.department || log.department || '-',
            afdeling: emp.afdeling || log.afdeling || '-',
            nama_kebun: emp.nama_kebun || log.nama_kebun || '-',
            timestamp: log.timestamp,
            location: log.location,
            lat: log.latitude !== undefined && log.latitude !== null ? log.latitude : (log.lat !== undefined ? log.lat : null),
            lng: log.longitude !== undefined && log.longitude !== null ? log.longitude : (log.lng !== undefined ? log.lng : null),
            status: log.status,
            attendance_type: typeLabel,
            euclidean_distance: log.euclidean_distance,
            is_synced: true,
            created_at: log.created_at
          };
        });

        // Save fresh online logs to local database
        try {
          const localLogs = await db.attendance_logs.toArray();
          for (let i = 0; i < localLogs.length; i++) {
            if (localLogs[i].is_synced) {
              await db.attendance_logs.delete(localLogs[i].id);
            }
          }
          if (onlineLogs.length > 0) {
            await db.attendance_logs.bulkPut(onlineLogs);
          }
          console.log(`[Local Database] Synchronized ${onlineLogs.length} online logs to local storage`);
        } catch (dbErr) {
          console.warn('[Local Database] Failed to cache online logs:', dbErr);
        }
      }
    } catch (err) {
      console.warn('[FETCH LOGS SUPABASE DIRECT WARN]:', err.message);
    }

    // Step 2: Query all logs from local database
    let allLocalLogs = [];
    try {
      const rawLocal = await db.attendance_logs.toArray();
      const savedAdmin = localStorage.getItem('logged_in_admin');
      if (savedAdmin) {
        const adminObj = JSON.parse(savedAdmin);
        if (adminObj.role !== 'headoffice_admin') {
          const empIds = new Set(localEmployees.map(e => String(e.id)));
          allLocalLogs = rawLocal.filter(l => empIds.has(String(l.employee_id)));
        } else {
          allLocalLogs = rawLocal;
        }
      } else {
        allLocalLogs = rawLocal;
      }
    } catch (e) {
      console.warn('[Local Database] Failed to load attendance logs:', e);
    }

    // Step 3: De-duplicate and resolve metadata
    const getLocalDateString = (ts) => {
      if (!ts) return '';
      const d = new Date(ts);
      if (isNaN(d.getTime())) return '';
      const year = d.getFullYear();
      const month = String(d.getMonth() + 1).padStart(2, '0');
      const day = String(d.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    };

    const finalLogs = [];
    const seen = new Set();
    const onlineSignatures = new Set();

    allLocalLogs.forEach(log => {
      if (log.is_synced) {
        const localDate = getLocalDateString(log.timestamp);
        if (localDate) {
          const signature = `${log.employee_id}_${localDate}_${log.attendance_type}`;
          onlineSignatures.add(signature);
        }
      }
    });

    allLocalLogs.forEach(log => {
      const localDate = getLocalDateString(log.timestamp);
      if (!localDate) return;
      const signature = `${log.employee_id}_${localDate}_${log.attendance_type}`;

      // Skip unsynced log if its signature is already in online logs
      if (!log.is_synced && onlineSignatures.has(signature)) {
        return;
      }

      if (!seen.has(signature)) {
        seen.add(signature);
        
        let resolvedLog = { ...log };
        if (!resolvedLog.name || resolvedLog.name === 'Karyawan' || resolvedLog.name.includes('#')) {
          const emp = masterEmpMap.get(String(resolvedLog.employee_id)) || {};
          resolvedLog.nik = emp.nik || resolvedLog.nik || '-';
          resolvedLog.name = emp.name || resolvedLog.name || `Karyawan #${resolvedLog.employee_id}`;
          resolvedLog.department = emp.department || resolvedLog.department || '-';
          resolvedLog.afdeling = emp.afdeling || resolvedLog.afdeling || '-';
          resolvedLog.nama_kebun = emp.nama_kebun || resolvedLog.nama_kebun || '-';
        }
        finalLogs.push(resolvedLog);
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

  // Count active employees today who haven't checked out yet
  const pendingCheckOutsCount = useMemo(() => {
    const todayStr = new Date().toLocaleDateString('sv-SE');
    const todayLogs = logs.filter(l => {
      if (!l.timestamp) return false;
      return new Date(l.timestamp).toLocaleDateString('sv-SE') === todayStr;
    });
    
    const checkIns = new Set(todayLogs.filter(l => l.attendance_type === 'CHECK-IN').map(l => l.employee_id));
    const checkOuts = new Set(todayLogs.filter(l => l.attendance_type === 'CHECK-OUT').map(l => l.employee_id));
    
    let count = 0;
    checkIns.forEach(id => {
      if (!checkOuts.has(id)) {
        count++;
      }
    });
    return count;
  }, [logs]);

  // Track if current time is past standard shift end (17:00)
  const [isPastShiftEnd, setIsPastShiftEnd] = useState(() => {
    return new Date().getHours() >= 17;
  });

  useEffect(() => {
    const timer = setInterval(() => {
      setIsPastShiftEnd(new Date().getHours() >= 17);
    }, 60000);
    return () => clearInterval(timer);
  }, []);

  // Manual Trigger Auto-Sync (Bidirectional: Push local logs & requests + Pull cloud employees & logs)
  const handleManualSync = async () => {
    if (!isOnline) {
      showToast('Gagal Sinkronisasi', 'Aplikasi berada dalam mode luring (offline). Silakan hubungkan ke internet.', 'error');
      return;
    }

    setIsSyncing(true);
    showToast('Sinkronisasi Dimulai', 'Mengirim data offline dan memuat ulang data terbaru dari cloud...', 'info');
    
    // 1. Push pending offline logs
    await syncPendingAttendanceLogs(showToast, async () => {
      refreshUnsyncedCount();
    });

    // 2. Push pending offline requests (edit/hapus)
    try {
      const { syncPendingRequests } = await import('./syncEngine');
      await syncPendingRequests();
    } catch (e) {
      console.warn('[Manual Sync Requests Error]:', e);
    }

    // 3. Pull fresh employees & missing biometrics descriptors
    try {
      await fetchEmployees();
    } catch (e) {
      console.warn('[Manual Sync Pull Employees Error]:', e);
    }

    // 4. Pull fresh attendance logs
    try {
      await fetchLogs();
    } catch (e) {
      console.warn('[Manual Sync Pull Logs Error]:', e);
    }

    showToast('Sinkronisasi Selesai', 'Data berhasil diselaraskan secara penuh dengan cloud.', 'success');
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

  // Auto Check-out for previous days' orphaned check-ins
  useEffect(() => {
    if (!dbReady) return;
    
    const autoFlagOrphanedLogs = async () => {
      try {
        const localLogs = await db.attendance_logs.toArray();
        const checkIns = localLogs.filter(l => l.attendance_type === 'CHECK-IN');
        const checkOuts = localLogs.filter(l => l.attendance_type === 'CHECK-OUT');
        
        const checkOutKeys = new Set(checkOuts.map(l => {
          const d = new Date(l.timestamp);
          const dateStr = `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`;
          return `${l.employee_id}_${dateStr}`;
        }));

        const todayStr = new Date().toLocaleDateString('sv-SE'); // YYYY-MM-DD
        
        const newAutoCheckOuts = [];
        
        for (const ci of checkIns) {
          const ciDate = new Date(ci.timestamp);
          const dateStr = `${ciDate.getFullYear()}-${String(ciDate.getMonth() + 1).padStart(2, '0')}-${String(ciDate.getDate()).padStart(2, '0')}`;
          const key = `${ci.employee_id}_${dateStr}`;
          
          // Jika log check-in berasal dari HARI SEBELUMNYA dan tidak memiliki check-out
          if (dateStr < todayStr && !checkOutKeys.has(key)) {
            // Buat log check-out otomatis pukul 17:00 pada tanggal tersebut
            const defaultTime = new Date(ci.timestamp);
            defaultTime.setHours(23, 59, 0, 0); // 23:59 default
            
            const newLog = {
              id: 'auto_out_' + ci.id + '_' + Date.now(),
              employee_id: ci.employee_id,
              nik: ci.nik,
              name: ci.name,
              department: ci.department,
              afdeling: ci.afdeling,
              nama_kebun: ci.nama_kebun,
              timestamp: defaultTime.toISOString(),
              location: '[SISTEM] Lupa Check-out',
              lat: ci.lat,
              lng: ci.lng,
              status: 'LUPA_CHECKOUT',
              attendance_type: 'CHECK-OUT',
              is_synced: false
            };
            
            newAutoCheckOuts.push(newLog);
          }
        }
        
        if (newAutoCheckOuts.length > 0) {
          console.log(`[Auto Check-Out] Menemukan ${newAutoCheckOuts.length} absensi tanpa check-out dari hari sebelumnya. Membuat check-out otomatis.`);
          await db.attendance_logs.bulkPut(newAutoCheckOuts);
          
          // Jika online, sinkronkan ke Supabase
          if (isOnline) {
            for (const log of newAutoCheckOuts) {
              const payload = {
                id: log.id,
                employee_id: log.employee_id,
                nik: log.nik,
                name: log.name,
                department: log.department,
                afdeling: log.afdeling,
                nama_kebun: log.nama_kebun,
                timestamp: log.timestamp,
                location: log.location,
                latitude: log.lat,
                longitude: log.lng,
                status: log.status,
                attendance_type: log.attendance_type
              };
              const { error } = await supabase.from('attendance_logs').insert(payload);
              if (!error) {
                log.is_synced = true;
                await db.attendance_logs.put(log);
              }
            }
          }
          
          // Muat ulang log ke UI
          fetchLogs();
        }
      } catch (err) {
        console.warn('[Auto Check-Out Error]:', err);
      }
    };
    
    // Tunggu sebentar setelah startup agar data employees cache & log awal termuat
    const timer = setTimeout(autoFlagOrphanedLogs, 2000);
    return () => clearTimeout(timer);
  }, [dbReady, isOnline, fetchLogs]);

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

  const router = useMemo(() => {
    return createBrowserRouter([
      {
        path: "/",
        element: (
          <>
            <div className={`app-container ${theme}-theme`} style={{ display: 'none' }}></div>
            {toasts.length > 0 && <ShadcnToast toasts={toasts} />}
            <ConfirmModal
              isOpen={confirmModalConfig.isOpen}
              title={confirmModalConfig.title}
              message={confirmModalConfig.message}
              confirmText={confirmModalConfig.confirmText}
              onConfirm={confirmModalConfig.onConfirm}
              onCancel={closeConfirmModal}
            />
            <Outlet />
          </>
        ),
        children: [
          // PUBLIC ROUTES (Auth Layout)
          {
            element: <PublicRoute />,
            children: [
              {
                element: <AuthLayout theme={theme} toggleTheme={toggleTheme} />,
                children: [
                  {
                    path: "login",
                    element: (
                      <LoginPage
                        employees={employees}
                        showToast={showToast}
                        theme={theme}
                        toggleTheme={toggleTheme}
                        refreshEmployees={fetchEmployees}
                      />
                    )
                  }
                ]
              }
            ]
          },
          // Analytics Page
          {
            path: "analytics",
            element: <EnterpriseAnalyticsPage employees={employees} logs={logs} />
          },
          // Order Form Page
          {
            path: "order-form",
            element: <OfflineOrderForm />
          },
          // PUBLIC ROUTES DENGAN DASHBOARD LAYOUT (Tanpa Login)
          {
            element: (
              <DashboardLayout
                isOnline={isOnline}
                unsyncedCount={unsyncedCount}
                isSyncing={isSyncing}
                onManualSync={handleManualSync}
                theme={theme}
                toggleTheme={toggleTheme}
                pendingCheckOutsCount={pendingCheckOutsCount}
                isPastShiftEnd={isPastShiftEnd}
              />
            ),
            children: [
              {
                path: "absensi",
                element: (
                  <AbsensiPage
                    employees={employees}
                    modelsLoaded={modelsLoaded}
                    modelStatusText={modelStatusText}
                    showToast={showToast}
                    refreshLogs={fetchLogs}
                  />
                )
              }
            ]
          },
          // PROTECTED ROUTES (Hanya Admin)
          {
            element: <ProtectedRoute />,
            children: [
              {
                element: (
                  <DashboardLayout
                    isOnline={isOnline}
                    unsyncedCount={unsyncedCount}
                    isSyncing={isSyncing}
                    onManualSync={handleManualSync}
                    theme={theme}
                    toggleTheme={toggleTheme}
                    pendingCheckOutsCount={pendingCheckOutsCount}
                    isPastShiftEnd={isPastShiftEnd}
                  />
                ),
                children: [
                  {
                    path: "dashboard",
                    element: (
                      <DashboardPage
                        employees={employees}
                        logs={logs}
                        modelsLoaded={modelsLoaded}
                      />
                    )
                  },
                  {
                    path: "karyawan",
                    element: (
                      <KaryawanPage
                        employees={employees}
                        modelsLoaded={modelsLoaded}
                        showToast={showToast}
                        refreshEmployees={fetchEmployees}
                        openConfirmModal={openConfirmModal}
                      />
                    )
                  },
                  {
                    path: "daftar-karyawan",
                    element: (
                      <DaftarKaryawanPage
                        employees={employees}
                        modelsLoaded={modelsLoaded}
                        showToast={showToast}
                        refreshEmployees={fetchEmployees}
                        refreshLogs={fetchLogs}
                        openConfirmModal={openConfirmModal}
                      />
                    )
                  },
                  {
                    path: "logs",
                    element: (
                      <LogsPage
                        logs={logs}
                        refreshLogs={fetchLogs}
                        showToast={showToast}
                        openConfirmModal={openConfirmModal}
                      />
                    )
                  }
                ]
              }
            ]
          },
          // Fallbacks
          {
            path: "",
            element: <Navigate to="/login" replace />
          },
          {
            path: "*",
            element: <Navigate to="/absensi" replace />
          }
        ]
      }
    ]);
  }, [
    theme, toasts, confirmModalConfig, employees, modelsLoaded, modelStatusText,
    isOnline, unsyncedCount, isSyncing, logs, fetchEmployees, fetchLogs, handleManualSync, openConfirmModal
  ]);

  return <RouterProvider router={router} />;
}

export default function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}
