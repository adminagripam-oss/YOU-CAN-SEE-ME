import React from 'react';
import { useLocation } from 'react-router-dom';

export default function Placeholder() {
  const { pathname } = useLocation();
  return (
    <div style={{ padding: '2rem 1.5rem' }}>
      <h1 style={{ fontSize: '1.25rem', fontWeight: 800, color: 'var(--text-main)', marginBottom: '0.5rem' }}>
        Halaman dalam Pengembangan
      </h1>
      <p style={{ fontSize: '0.875rem', color: 'var(--text-muted)' }}>
        Fitur untuk <code style={{ background: 'var(--bg-input)', padding: '2px 6px', borderRadius: '4px' }}>{pathname}</code> sedang dibangun.
      </p>
    </div>
  );
}
