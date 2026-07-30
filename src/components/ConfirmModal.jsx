import React from 'react';

export default function ConfirmModal({ isOpen, title, message, onConfirm, onCancel, confirmText = 'Ya, Lanjutkan' }) {
  if (!isOpen) return null;

  return (
    <div
      style={{
        position: 'fixed',
        inset: 0,
        background: 'rgba(0, 0, 0, 0.75)',
        backdropFilter: 'blur(8px)',
        zIndex: 10000,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        padding: '1rem',
      }}
    >
      <div
        className="glass-card"
        style={{
          maxWidth: '440px',
          width: '100%',
          border: '1px solid rgba(239, 68, 68, 0.4)',
          boxShadow: '0 10px 40px rgba(0,0,0,0.6)',
        }}
      >
        <div
          className="card-title"
          style={{
            color: '#f87171',
            borderBottom: '1px solid rgba(239, 68, 68, 0.2)',
            display: 'flex',
            alignItems: 'center',
            gap: '10px',
          }}
        >
          <i className="fa-solid fa-triangle-exclamation" style={{ fontSize: '1.25rem' }}></i>
          <span>{title}</span>
        </div>

        <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem', lineHeight: '1.5', margin: '1rem 0' }}>
          {message}
        </p>

        <div style={{ display: 'flex', gap: '10px', justifyContent: 'flex-end', marginTop: '1.5rem' }}>
          <button
            type="button"
            className="btn"
            style={{
              background: 'rgba(255,255,255,0.1)',
              color: '#fff',
              padding: '10px 16px',
              fontSize: '0.85rem',
              width: 'auto',
            }}
            onClick={onCancel}
          >
            Batal
          </button>
          <button
            type="button"
            className="btn btn-action delete"
            style={{
              padding: '10px 18px',
              fontSize: '0.85rem',
              background: 'linear-gradient(135deg, #ef4444, #dc2626)',
              color: '#fff',
              border: 'none',
              borderRadius: 'var(--radius-md)',
              fontWeight: 700,
            }}
            onClick={onConfirm}
          >
            {confirmText}
          </button>
        </div>
      </div>
    </div>
  );
}
