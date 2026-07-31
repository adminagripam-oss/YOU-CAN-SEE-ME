import React, { useState } from 'react';
import { API_BASE_URL } from '../config';
import { cacheUserMasterVector } from '../db';

export default function LoginPage({ employees, currentUser, onLoginSuccess, showToast, onNavigateToApp }) {
  const [selectedEmpId, setSelectedEmpId] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!selectedEmpId) {
      showToast('Pilih Karyawan', 'Silakan pilih nama karyawan untuk login!', 'error');
      return;
    }

    const emp = employees.find((item) => String(item.id) === String(selectedEmpId));
    if (!emp) return;

    setIsLoading(true);
    try {
      // Fetch & Cache Master Vector for offline use if online
      if (navigator.onLine) {
        try {
          const res = await fetch(`${API_BASE_URL}/api/attendance/verify`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
              employee_id: emp.id,
              scan_descriptor: new Array(128).fill(0),
            }),
          });
          const data = await res.json();
          console.log('[Login Route] Vector probe data:', data);
        } catch (fetchErr) {
          console.warn('[Login Route] Probe fetch warn:', fetchErr);
        }

        await cacheUserMasterVector({
          employee_id: emp.id,
          nik: emp.nik,
          name: emp.name,
          department: emp.department,
          descriptor_json: emp.descriptor_json || null,
        });
      }

      onLoginSuccess(emp);
      showToast(
        'Login Berhasil',
        `Selamat datang kembali, ${emp.name}! Profil & Vektor Biometrik di-cache untuk Mode Offline.`,
        'success'
      );
    } catch (err) {
      console.error('[Login Route Error]:', err);
      onLoginSuccess(emp);
      showToast('Login Offline', `Masuk sebagai ${emp.name} dalam mode lokal HP.`, 'info');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="login-page-container">
      <div className="glass-card login-page-card">
        <div className="login-header">
          <div className="login-icon">
            <i className="fa-solid fa-user-shield"></i>
          </div>
          <h2>Portal Login Absensi Biometrik</h2>
          <p>Pilih akun karyawan Anda untuk masuk &amp; aktifkan Pre-Caching Vektor Wajah Offline</p>
        </div>

        {currentUser && (
          <div className="login-active-alert">
            <i className="fa-solid fa-circle-check"></i>
            <div>
              <strong>Akun Aktif Sekarang:</strong> {currentUser.name} ({currentUser.nik})
            </div>
            <button
              type="button"
              className="btn-action edit"
              onClick={onNavigateToApp}
              style={{ marginLeft: 'auto' }}
            >
              Masuk ke Aplikasi <i className="fa-solid fa-arrow-right"></i>
            </button>
          </div>
        )}

        <form onSubmit={handleLogin} className="login-form">
          <div className="form-group">
            <label htmlFor="login-page-select">Pilih Karyawan / Scan NIK</label>
            <select
              id="login-page-select"
              value={selectedEmpId}
              onChange={(e) => setSelectedEmpId(e.target.value)}
              disabled={isLoading}
            >
              <option value="">-- Pilih Akun Karyawan --</option>
              {employees.map((emp) => (
                <option key={emp.id} value={emp.id}>
                  {emp.name} ({emp.nik}) - {emp.department}
                </option>
              ))}
            </select>
          </div>

          <div style={{ display: 'flex', flexDirection: 'column', gap: '10px', marginTop: '1.5rem' }}>
            <button type="submit" className="btn btn-primary" disabled={isLoading || !selectedEmpId}>
              {isLoading ? (
                <>
                  <i className="fa-solid fa-spinner fa-spin"></i> Menyiapkan Cache IndexedDB...
                </>
              ) : (
                <>
                  <i className="fa-solid fa-right-to-bracket"></i> Masuk &amp; Cache Vector Vektor
                </>
              )}
            </button>

            <button
              type="button"
              className="btn-action edit"
              onClick={onNavigateToApp}
              style={{ width: '100%', justifyContent: 'center', padding: '12px', fontSize: '0.9rem' }}
            >
              <i className="fa-solid fa-camera"></i> Langsung ke Scanner Absensi
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
