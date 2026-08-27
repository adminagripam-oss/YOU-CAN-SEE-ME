import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Sidebar({ isOpen, onClose }) {
  const { isAuthenticated, logout, user } = useAuth();
  const navigate = useNavigate();

  const isEstateAdmin = !user || user.role === 'estate_admin';

  return (
    <>
      <aside className={`app-sidebar ${isOpen ? 'sidebar-open' : 'sidebar-closed'}`}>
        <div className="sidebar-brand" style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
          <i className="fa-solid fa-fingerprint" style={{ fontSize: '1.5rem', color: 'var(--accent-cyan)', flexShrink: 0 }}></i>
          <div className="brand-text" style={{ transition: 'opacity 0.2s ease' }}>
            <h2>AgriFace</h2>
            <p>1-to-1 Biometric Engine</p>
          </div>
        </div>

        <nav className="sidebar-nav">
          {isAuthenticated && (
            <NavLink
              to="/dashboard"
              className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
              onClick={() => window.innerWidth < 768 && onClose && onClose()}
            >
              <i className="fa-solid fa-chart-line"></i>
              <span>Dashboard</span>
            </NavLink>
          )}

          {isEstateAdmin && (
            <NavLink
              to="/absensi"
              className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
              onClick={() => window.innerWidth < 768 && onClose && onClose()}
            >
              <i className="fa-solid fa-camera-retro"></i>
              <span>Scanner Absensi</span>
            </NavLink>
          )}

          {isAuthenticated && (
            <>
              {isEstateAdmin && (
                <NavLink
                  to="/karyawan"
                  className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
                  onClick={() => window.innerWidth < 768 && onClose && onClose()}
                >
                  <i className="fa-solid fa-user-plus"></i>
                  <span>Input Karyawan</span>
                </NavLink>
              )}

              <NavLink
                to="/daftar-karyawan"
                className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
                onClick={() => window.innerWidth < 768 && onClose && onClose()}
              >
                <i className="fa-solid fa-users"></i>
                <span>Daftar Karyawan</span>
              </NavLink>

              <NavLink
                to="/logs"
                className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
                onClick={() => window.innerWidth < 768 && onClose && onClose()}
              >
                <i className="fa-solid fa-clock-rotate-left"></i>
                <span>Log Absensi</span>
              </NavLink>

              <button
                type="button"
                className="sidebar-link sidebar-logout-btn"
                style={{
                  background: 'transparent',
                  border: 'none',
                  cursor: 'pointer',
                  width: '100%',
                  textAlign: 'left',
                  marginTop: '0.75rem',
                  color: 'var(--accent-red, #ef4444)',
                }}
                onClick={async () => {
                  await logout();
                  navigate('/login');
                }}
                title="Keluar dari Sesi Login"
              >
                <i className="fa-solid fa-right-from-bracket" style={{ color: 'var(--accent-red, #ef4444)' }}></i>
                <span>Logout</span>
              </button>
            </>
          )}
        </nav>
      </aside>
    </>
  );
}
