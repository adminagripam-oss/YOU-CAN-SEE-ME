import React, { useState } from 'react';
import { API_BASE_URL } from '../config';
import { cacheUserMasterVector } from '../db';

export default function LoginModal({ employees, currentUser, onLoginSuccess, showToast }) {
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
      // 1. Fetch Master Descriptor for this user if online
      if (navigator.onLine) {
        const res = await fetch(`${API_BASE_URL}/api/attendance/verify`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            employee_id: emp.id,
            scan_descriptor: new Array(128).fill(0), // probe to fetch master descriptor
          }),
        });

        // Try to fetch master descriptor directly or cache existing profile
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
        `Selamat datang, ${emp.name}! Profile & Vektor Biometrik telah di-cache untuk Mode Offline.`,
        'success'
      );
    } catch (err) {
      console.error('[Login Error]:', err);
      // Fallback: Proceed with local profile
      onLoginSuccess(emp);
      showToast('Login Offline', `Masuk sebagai ${emp.name} dalam mode lokal.`, 'info');
    } finally {
      setIsLoading(false);
    }
  };

  if (currentUser) {
    return null; // Already logged in
  }

  return (
    <div className="login-modal-overlay">
      <div className="login-modal-card">
        <div className="login-header">
          <div className="login-icon">
            <i className="fa-solid fa-user-shield"></i>
          </div>
          <h2>Login Absensi Mobile</h2>
          <p>Pilih akun karyawan untuk aktifkan Mode Pre-Caching Offline</p>
        </div>

        <form onSubmit={handleLogin} className="login-form">
          <div className="form-group">
            <label htmlFor="login-employee-select">Pilih Karyawan / NIK</label>
            <select
              id="login-employee-select"
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

          <button type="submit" className="btn btn-primary" disabled={isLoading || !selectedEmpId}>
            {isLoading ? (
              <>
                <i className="fa-solid fa-spinner fa-spin"></i> Menyiapkan Cache Local DB...
              </>
            ) : (
              <>
                <i className="fa-solid fa-right-to-bracket"></i> Masuk & Pre-Cache Pre-Vector
              </>
            )}
          </button>
        </form>
      </div>
    </div>
  );
}
