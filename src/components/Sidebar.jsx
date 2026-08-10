import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Sidebar({ isOpen, onClose }) {
  const { isAuthenticated, logout } = useAuth();
  const navigate = useNavigate();

  return (
    <>
      <aside className={`app-sidebar ${isOpen ? 'sidebar-open' : 'sidebar-closed'}`}>
        <div className="sidebar-brand">
          <div className="brand-text">
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

          <NavLink
            to="/absensi"
            className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
            onClick={() => window.innerWidth < 768 && onClose && onClose()}
          >
            <i className="fa-solid fa-camera-retro"></i>
            <span>Scanner Absensi</span>
          </NavLink>

          {isAuthenticated && (
            <>
              <NavLink
                to="/karyawan"
                className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
                onClick={() => window.innerWidth < 768 && onClose && onClose()}
              >
                <i className="fa-solid fa-users"></i>
                <span>Data Karyawan</span>
              </NavLink>

              <NavLink
                to="/logs"
                className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
                onClick={() => window.innerWidth < 768 && onClose && onClose()}
              >
                <i className="fa-solid fa-clock-rotate-left"></i>
                <span>Log Absensi</span>
              </NavLink>
            </>
          )}
        </nav>

        {/* Sidebar Footer */}
        <div className="sidebar-footer">
          {isAuthenticated && (
            <button
              type="button"
              className="sidebar-logout-btn"
              onClick={async () => {
                await logout();
                navigate('/login');
              }}
              title="Keluar dari Sesi Login"
            >
              <i className="fa-solid fa-right-from-bracket"></i>
              <span>Logout</span>
            </button>
          )}
        </div>
      </aside>
    </>
  );
}
