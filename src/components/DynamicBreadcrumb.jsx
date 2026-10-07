import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { MENU, findTrail } from '../config/menu';

export default function DynamicBreadcrumb() {
  const { pathname } = useLocation();
  const trail = findTrail(MENU, pathname);

  return (
    <nav aria-label="Navigasi breadcrumb" className="dynamic-breadcrumb">
      <ol className="breadcrumb-list">
        {/* Root: Beranda */}
        <li className={`breadcrumb-item ${trail.length > 0 ? 'breadcrumb-hide-mobile' : ''}`}>
          {trail.length === 0 ? (
            <span className="breadcrumb-current" aria-current="page">Beranda</span>
          ) : (
            <Link to="/" className="breadcrumb-link">Beranda</Link>
          )}
        </li>

        {trail.map((item, idx) => {
          const isLast = idx === trail.length - 1;
          return (
            <React.Fragment key={item.title}>
              <li className={`breadcrumb-separator ${!isLast ? 'breadcrumb-hide-mobile' : ''}`} aria-hidden="true">
                <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                  <polyline points="9 18 15 12 9 6" />
                </svg>
              </li>
              <li className={`breadcrumb-item ${!isLast ? 'breadcrumb-hide-mobile' : ''}`}>
                {isLast ? (
                  <span className="breadcrumb-current" aria-current="page">{item.title}</span>
                ) : item.path ? (
                  <Link to={item.path} className="breadcrumb-link">{item.title}</Link>
                ) : (
                  /* Parent group: no path — render as plain text */
                  <span className="breadcrumb-group-label">{item.title}</span>
                )}
              </li>
            </React.Fragment>
          );
        })}
      </ol>
    </nav>
  );
}
