import React, { useState, useEffect, useCallback } from 'react';
import Header from './components/Header';
import TabFaceVerification from './components/TabFaceVerification';
import TabEmployeeManagement from './components/TabEmployeeManagement';
import TabAttendanceLogs from './components/TabAttendanceLogs';
import ShadcnToast from './components/ShadcnToast';
import ConfirmModal from './components/ConfirmModal';

export default function App() {
  const [activeTab, setActiveTab] = useState('tab-verify');
  const [employees, setEmployees] = useState([]);
  const [logs, setLogs] = useState([]);
  const [modelsLoaded, setModelsLoaded] = useState(false);
  const [modelStatusText, setModelStatusText] = useState('Memuat Model AI Biometrik Wajah...');
  const [toasts, setToasts] = useState([]);
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
      const res = await fetch('/api/employees');
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
      const res = await fetch('/api/attendance/logs');
      const data = await res.json();
      if (data.success) {
        setLogs(data.data || []);
      }
    } catch (err) {
      console.error('[FETCH LOGS ERROR]:', err);
    }
  }, []);

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

      {/* Navigation Header */}
      <Header activeTab={activeTab} setActiveTab={setActiveTab} />

      {/* Main Tab Content */}
      <main>
        {activeTab === 'tab-verify' && (
          <section className="tab-content active">
            <TabFaceVerification
              employees={employees}
              modelsLoaded={modelsLoaded}
              modelStatusText={modelStatusText}
              showToast={showToast}
              onVerificationSuccess={fetchLogs}
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
          Sistem Absensi Biometrik Wajah berbasis 1-to-1 Verification Engine &copy; 2026. Powered by React, face-api.js &amp; Supabase Cloud.
        </p>
      </footer>
    </>
  );
}
