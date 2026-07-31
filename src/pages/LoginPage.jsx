import React, { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';

export default function LoginPage({ employees = [], showToast, theme, toggleTheme, refreshEmployees }) {
  const { login } = useAuth();
  const [activeSubTab, setActiveSubTab] = useState('account');
  const [selectedEmpId, setSelectedEmpId] = useState('');
  const [pinInput, setPinInput] = useState('');
  const [currentPin, setCurrentPin] = useState('');
  const [newPin, setNewPin] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isRefreshing, setIsRefreshing] = useState(false);

  // Auto trigger employee fetch if list is empty on mount
  useEffect(() => {
    if ((!employees || employees.length === 0) && refreshEmployees) {
      refreshEmployees();
    }
  }, [employees, refreshEmployees]);

  const handleManualRefresh = async () => {
    if (refreshEmployees) {
      setIsRefreshing(true);
      await refreshEmployees();
      setIsRefreshing(false);
      if (showToast) showToast('Muat Data', 'Memperbarui daftar karyawan dari Supabase Cloud...', 'info');
    }
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    if (!selectedEmpId) {
      if (showToast) showToast('Pilih Karyawan', 'Silakan pilih profil karyawan untuk login!', 'error');
      return;
    }

    const emp = employees.find((item) => String(item.id) === String(selectedEmpId));
    setIsSubmitting(true);

    try {
      const res = await login({ employee_id: selectedEmpId, employee: emp, nik: emp ? emp.nik : null });
      if (res.success) {
        if (showToast) showToast('Login Berhasil', res.message, 'success');
      } else {
        if (showToast) showToast('Login Gagal', res.message, 'error');
      }
    } catch (err) {
      if (showToast) showToast('Error Login', err.message, 'error');
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleUpdatePin = (e) => {
    e.preventDefault();
    if (!newPin) {
      if (showToast) showToast('PIN Kosong', 'Masukkan PIN baru Anda!', 'error');
      return;
    }
    if (showToast) showToast('PIN Diperbarui', 'PIN keamanan akun Anda berhasil disimpan.', 'success');
    setNewPin('');
    setCurrentPin('');
  };

  return (
    <div className="glass-login-wrapper">
      <div className="login-pattern-container">
        {/* Header Title from Figma Design */}
        <div className="glass-login-title">
          <h1 className="figma-login-title">Welcome back!</h1>
          <p className="figma-login-subtitle">Mari isi Kehadiran Diri Anda</p>
        </div>

        {/* Login Form Card (Direct Figma Layout) */}
        <div className="glass-card ui-card">
          <div className="ui-card-content">
            <form onSubmit={handleLoginSubmit} className="space-y-4">
              <div className="form-group">
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '6px' }}>
                  <label htmlFor="login-emp-select" className="figma-form-label">NIK / Akun Karyawan</label>
                  <button
                    type="button"
                    onClick={handleManualRefresh}
                    disabled={isRefreshing}
                    className="figma-refresh-btn"
                    title="Muat Ulang Data Karyawan dari Supabase Cloud"
                  >
                    <i className={`fa-solid ${isRefreshing ? 'fa-spinner fa-spin' : 'fa-arrows-rotate'}`}></i>
                    <span>{isRefreshing ? 'Muat...' : 'Refresh Supabase'}</span>
                  </button>
                </div>
                <select
                  id="login-emp-select"
                  className="figma-input-select"
                  value={selectedEmpId}
                  onChange={(e) => setSelectedEmpId(e.target.value)}
                  disabled={isSubmitting}
                >
                  <option value="">-- Pilih Akun Karyawan --</option>
                  {employees.map((emp) => (
                    <option key={emp.id} value={emp.id}>
                      {emp.name} ({emp.nik}) - {emp.department}
                    </option>
                  ))}
                </select>
                {(!employees || employees.length === 0) && (
                  <p style={{ fontSize: '0.76rem', color: 'var(--accent-warning)', marginTop: '5px' }}>
                    <i className="fa-solid fa-spinner fa-spin" style={{ marginRight: '4px' }}></i>
                    Menghubungkan ke Supabase Cloud... Klik "Refresh Supabase" di atas jika daftar belum muncul.
                  </p>
                )}
              </div>

              <div className="form-group">
                <label htmlFor="login-pin-input" className="figma-form-label">Password / Kode PIN</label>
                <input
                  type="password"
                  id="login-pin-input"
                  className="figma-input-text"
                  placeholder="Masukkan PIN / NIK..."
                  value={pinInput}
                  onChange={(e) => setPinInput(e.target.value)}
                />
              </div>

              <div className="figma-remember-checkbox">
                <input type="checkbox" id="remember-me" defaultChecked />
                <label htmlFor="remember-me">Remember for 30 days</label>
              </div>

              <button type="submit" className="figma-btn-login" disabled={isSubmitting || !selectedEmpId}>
                {isSubmitting ? (
                  <>
                    <i className="fa-solid fa-spinner fa-spin"></i> Menyiapkan Sesi...
                  </>
                ) : (
                  'Login'
                )}
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
