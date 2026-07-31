import React from 'react';
import { NavLink } from 'react-router-dom';

export default function Sidebar({ isOpen, onClose }) {
  return (
    <>
      <aside className={`app-sidebar ${isOpen ? 'sidebar-open' : 'sidebar-closed'}`}>
        <div className="sidebar-brand">
          <div className="brand-icon">
            <i className="fa-solid fa-face-smile"></i>
          </div>
          <div className="brand-text">
            <h2>Absensi Biometrik</h2>
            <p>1-to-1 Engine</p>
          </div>
        </div>

        <nav className="sidebar-nav">
          <NavLink
            to="/dashboard"
            className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
            onClick={() => window.innerWidth < 768 && onClose && onClose()}
          >
            <i className="fa-solid fa-chart-line"></i>
            <span>Dashboard</span>
          </NavLink>

          <NavLink
            to="/absensi"
            className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
            onClick={() => window.innerWidth < 768 && onClose && onClose()}
          >
            <i className="fa-solid fa-camera-retro"></i>
            <span>Scanner Absensi</span>
          </NavLink>

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
        </nav>

        <div className="sidebar-footer">
          <p>Biometric 1-to-1 v2.0</p>
        </div>
      </aside>
    </>
  );
}
