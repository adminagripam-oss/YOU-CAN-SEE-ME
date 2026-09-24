import React from 'react';
import { useLocation, Link } from 'react-router-dom';

// Map URL segments to human-readable labels
const routeLabels = {
  'dashboard':       'Dashboard',
  'absensi':         'Scanner Absensi',
  'karyawan':        'Input Karyawan',
  'daftar-karyawan': 'Daftar Karyawan',
  'logs':            'Log Absensi',
  'login':           'Login',
};

function toLabel(segment) {
  return (
    routeLabels[segment] ??
    segment.charAt(0).toUpperCase() + segment.slice(1).replace(/-/g, ' ')
  );
}

export default function DynamicBreadcrumb() {
  const { pathname } = useLocation();
  const segments = pathname.split('/').filter(Boolean);

  const crumbs = segments.map((seg, idx) => ({
    label: toLabel(seg),
    to: '/' + segments.slice(0, idx + 1).join('/'),
    isLast: idx === segments.length - 1,
  }));

  return (
    <nav aria-label="breadcrumb" className="dynamic-breadcrumb">
      <ol className="breadcrumb-list">
        {/* Always show Beranda root */}
        <li className="breadcrumb-item">
          {crumbs.length === 0 ? (
            <span className="breadcrumb-current" aria-current="page">Beranda</span>
          ) : (
            <Link to="/" className="breadcrumb-link">Beranda</Link>
          )}
        </li>

        {/* Show segments */}
        {crumbs.map((crumb, idx) => (
          <React.Fragment key={crumb.to}>
            <li className="breadcrumb-separator" aria-hidden="true">
              <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                <polyline points="9 18 15 12 9 6" />
              </svg>
            </li>
            <li className="breadcrumb-item">
              {crumb.isLast ? (
                <span className="breadcrumb-current" aria-current="page">
                  {crumb.label}
                </span>
              ) : (
                <Link to={crumb.to} className="breadcrumb-link">
                  {crumb.label}
                </Link>
              )}
            </li>
          </React.Fragment>
        ))}
      </ol>
    </nav>
  );
}
