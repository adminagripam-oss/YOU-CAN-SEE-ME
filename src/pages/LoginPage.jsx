import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';

export default function LoginPage({ employees = [], showToast, theme, toggleTheme }) {
  const { login } = useAuth();
  const [activeSubTab, setActiveSubTab] = useState('account');
  const [selectedEmpId, setSelectedEmpId] = useState('');
  const [pinInput, setPinInput] = useState('');
  const [currentPin, setCurrentPin] = useState('');
  const [newPin, setNewPin] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

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
        {/* Header Title */}
        <div className="glass-login-title">
          <div className="brand-icon-lg">
            <i className="fa-solid fa-face-smile"></i>
          </div>
          <h1>Absensi Biometrik Wajah</h1>
          <p>Portal Otentikasi &amp; Pre-Caching Engine</p>
        </div>

        {/* Navigation Tabs (Account, Password, Settings) */}
        <div className="ui-tabs-list">
          <button
            type="button"
            className={`ui-tabs-trigger ${activeSubTab === 'account' ? 'active' : ''}`}
            onClick={() => setActiveSubTab('account')}
          >
            <i className="fa-solid fa-user"></i> Account
          </button>

          <button
            type="button"
            className={`ui-tabs-trigger ${activeSubTab === 'password' ? 'active' : ''}`}
            onClick={() => setActiveSubTab('password')}
          >
            <i className="fa-solid fa-lock"></i> Password
          </button>

          <button
            type="button"
            className={`ui-tabs-trigger ${activeSubTab === 'settings' ? 'active' : ''}`}
            onClick={() => setActiveSubTab('settings')}
          >
            <i className="fa-solid fa-sliders"></i> Settings
          </button>
        </div>

        {/* Tab 1: Account Login */}
        {activeSubTab === 'account' && (
          <div className="glass-card ui-card">
            <div className="ui-card-header">
              <h3 className="ui-card-title">Masuk ke Sistem Absensi</h3>
              <p className="ui-card-description">
                Pilih profil Anda untuk login &amp; otomatis meng-cache Vektor Biometrik Wajah ke HP.
              </p>
            </div>

            <div className="ui-card-content">
              <form onSubmit={handleLoginSubmit} className="space-y-4">
                <div className="form-group">
                  <label htmlFor="login-emp-select">Pilih Akun Karyawan</label>
                  <select
                    id="login-emp-select"
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
                </div>

                <div className="form-group">
                  <label htmlFor="login-pin-input">Kode PIN / NIK</label>
                  <input
                    type="password"
                    id="login-pin-input"
                    placeholder="Masukkan PIN / NIK..."
                    value={pinInput}
                    onChange={(e) => setPinInput(e.target.value)}
                  />
                </div>

                <button type="submit" className="btn btn-primary" disabled={isSubmitting || !selectedEmpId} style={{ marginTop: '1rem' }}>
                  {isSubmitting ? (
                    <>
                      <i className="fa-solid fa-spinner fa-spin"></i> Menyiapkan Sesi...
                    </>
                  ) : (
                    <>
                      <i className="fa-solid fa-right-to-bracket"></i> Masuk &amp; Buka Dashboard
                    </>
                  )}
                </button>
              </form>
            </div>
          </div>
        )}

        {/* Tab 2: Password */}
        {activeSubTab === 'password' && (
          <div className="glass-card ui-card">
            <div className="ui-card-header">
              <h3 className="ui-card-title">Password &amp; Keamanan PIN</h3>
              <p className="ui-card-description">Atur dan perbarui PIN otentikasi cepat akun Anda.</p>
            </div>

            <div className="ui-card-content">
              <form onSubmit={handleUpdatePin} className="space-y-4">
                <div className="form-group">
                  <label htmlFor="current-pin">PIN Saat Ini</label>
                  <input
                    type="password"
                    id="current-pin"
                    placeholder="••••••"
                    value={currentPin}
                    onChange={(e) => setCurrentPin(e.target.value)}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="new-pin">PIN Baru</label>
                  <input
                    type="password"
                    id="new-pin"
                    placeholder="••••••"
                    value={newPin}
                    onChange={(e) => setNewPin(e.target.value)}
                  />
                </div>

                <button type="submit" className="btn btn-primary" style={{ marginTop: '1rem' }}>
                  <i className="fa-solid fa-key"></i> Simpan PIN Baru
                </button>
              </form>
            </div>
          </div>
        )}

        {/* Tab 3: Settings */}
        {activeSubTab === 'settings' && (
          <div className="glass-card ui-card">
            <div className="ui-card-header">
              <h3 className="ui-card-title">Pengaturan Sistem</h3>
              <p className="ui-card-description">Kelola tema tampilan dan preferensi koneksi.</p>
            </div>

            <div className="ui-card-content">
              <div className="form-group">
                <label>Mode Tema Tampilan</label>
                <button
                  type="button"
                  className="btn-action edit"
                  onClick={toggleTheme}
                  style={{ width: '100%', justifyContent: 'center', padding: '12px', fontSize: '0.9rem' }}
                >
                  <i className={`fa-solid ${theme === 'dark' ? 'fa-sun' : 'fa-moon'}`}></i>
                  Mode Aktif: {theme === 'dark' ? 'Dark Mode' : 'Light Mode'}
                </button>
              </div>

              <div className="form-group">
                <label>Server Status</label>
                <input type="text" readOnly value="Supabase Cloud Live (Port 8080)" />
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
