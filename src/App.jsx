import React, { useState, useEffect, useCallback } from 'react';
import { API_BASE_URL } from './config';
import { getUnsyncedLogs } from './db';
import { syncPendingAttendanceLogs, initAutoSyncListener } from './syncEngine';
import Header from './components/Header';
import NetworkStatusBar from './components/NetworkStatusBar';
import LoginPage from './components/LoginPage';
import TabFaceVerification from './components/TabFaceVerification';
import TabEmployeeManagement from './components/TabEmployeeManagement';
import TabAttendanceLogs from './components/TabAttendanceLogs';
import ShadcnToast from './components/ShadcnToast';
import ConfirmModal from './components/ConfirmModal';

export default function App() {
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

  // User Auth & Page Routing State
  const [currentUser, setCurrentUser] = useState(() => {
    const saved = localStorage.getItem('logged_in_employee');
    return saved ? JSON.parse(saved) : null;
  });

  const [activeTab, setActiveTab] = useState(() => {
    const saved = localStorage.getItem('logged_in_employee');
    return saved ? 'tab-verify' : 'tab-login';
  });

  const [confirmModalConfig, setConfirmModalConfig] = useState({
    isOpen: false,
    title: '',
    message: '',
    confirmText: 'Ya, Lanjutkan',
    onConfirm: null,
  });

  // Floating Toast Notification Helper
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

  // Fetch Employees
  const fetchEmployees = useCallback(async () => {
    try {
      const res = await fetch(`${API_BASE_URL}/api/employees`);
      const data = await res.json();
      if (data.success) {
        setEmployees(data.data || []);
      }
    } catch (err) {
      console.error('[FETCH EMPLOYEES ERROR]:', err);
    }
  }, []);

  // Fetch Attendance Logs
  const fetchLogs = useCallback(async () => {
    try {
      const res = await fetch(`${API_BASE_URL}/api/attendance/logs`);
      const data = await res.json();
      if (data.success) {
        setLogs(data.data || []);
      }
    } catch (err) {
      console.error('[FETCH LOGS ERROR]:', err);
    }
  }, []);

  // Check Unsynced Logs Count from IndexedDB
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

  // Handle Login User Success
  const handleLoginSuccess = (user) => {
    setCurrentUser(user);
    localStorage.setItem('logged_in_employee', JSON.stringify(user));
    setActiveTab('tab-verify');
  };

  // Handle Logout
  const handleLogout = () => {
    setCurrentUser(null);
    localStorage.removeItem('logged_in_employee');
    setActiveTab('tab-login');
    showToast('Logout', 'Anda telah keluar dari akun.', 'info');
  };

  return (
    <>
      {/* Toast Notifications */}
      <ShadcnToast toasts={toasts} />

      {/* Confirmation Modal */}
      <ConfirmModal
        isOpen={confirmModalConfig.isOpen}
        title={confirmModalConfig.title}
        message={confirmModalConfig.message}
        confirmText={confirmModalConfig.confirmText}
        onConfirm={confirmModalConfig.onConfirm}
        onCancel={closeConfirmModal}
      />

      {/* Network Online / Offline Status Pill */}
      <NetworkStatusBar
        isOnline={isOnline}
        unsyncedCount={unsyncedCount}
        isSyncing={isSyncing}
        onManualSync={handleManualSync}
      />

      {/* Navigation Header */}
      <Header
        activeTab={activeTab}
        setActiveTab={setActiveTab}
        currentUser={currentUser}
        onLogout={handleLogout}
        theme={theme}
        toggleTheme={toggleTheme}
      />

      {/* Main Tab Content */}
      <main>
        {activeTab === 'tab-login' && (
          <section className="tab-content active">
            <LoginPage
              employees={employees}
              currentUser={currentUser}
              onLoginSuccess={handleLoginSuccess}
              showToast={showToast}
              onNavigateToApp={() => setActiveTab('tab-verify')}
            />
          </section>
        )}

        {activeTab === 'tab-verify' && (
          <section className="tab-content active">
            <TabFaceVerification
              employees={employees}
              modelsLoaded={modelsLoaded}
              modelStatusText={modelStatusText}
              showToast={showToast}
              onVerificationSuccess={() => {
                fetchLogs();
                refreshUnsyncedCount();
              }}
            />
          </section>
        )}

        {activeTab === 'tab-employees' && (
          <section className="tab-content active">
            <TabEmployeeManagement
              employees={employees}
              modelsLoaded={modelsLoaded}
              showToast={showToast}
              refreshEmployees={fetchEmployees}
              openConfirmModal={openConfirmModal}
            />
          </section>
        )}

        {activeTab === 'tab-logs' && (
          <section className="tab-content active">
            <TabAttendanceLogs
              logs={logs}
              onRefreshLogs={fetchLogs}
              showToast={showToast}
              openConfirmModal={openConfirmModal}
              refreshLogs={fetchLogs}
            />
          </section>
        )}
      </main>

      {/* Footer */}
      <footer>
        <p>
          Aplikasi Absensi Mobile Biometrik Wajah (Offline-First &amp; Auto-Sync PWA) &copy; 2026.
        </p>
      </footer>
    </>
  );
}
