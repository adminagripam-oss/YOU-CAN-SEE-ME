import React from 'react';

export default function OTAUpdateModal({ isOpen, version, progress, onUpdate }) {
  if (!isOpen) return null;

  return (
    <div style={{
      position: 'fixed',
      top: 0, left: 0, width: '100vw', height: '100vh',
      backgroundColor: 'rgba(0, 0, 0, 0.7)',
      backdropFilter: 'blur(8px)',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      zIndex: 99999,
      padding: '20px'
    }}>
      <div style={{
        background: 'var(--bg-card, #ffffff)',
        padding: '30px',
        borderRadius: '16px',
        maxWidth: '400px',
        width: '100%',
        boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.5)',
        textAlign: 'center',
        border: '1px solid var(--border-color, #e2e8f0)',
        color: 'var(--text-main, #0f172a)'
      }}>
        
        <div style={{
          width: '64px', height: '64px',
          background: 'rgba(99, 102, 241, 0.1)',
          color: '#6366f1',
          borderRadius: '50%',
          display: 'flex', alignItems: 'center', justifyContent: 'center',
          fontSize: '28px',
          margin: '0 auto 20px auto'
        }}>
          <i className="fa-solid fa-rocket"></i>
        </div>

        <h2 style={{ margin: '0 0 10px 0', fontSize: '1.4rem', fontWeight: 700 }}>
          Pembaruan Tersedia
        </h2>
        <p style={{ margin: '0 0 20px 0', fontSize: '0.95rem', opacity: 0.8, lineHeight: 1.5 }}>
          Versi <strong>{version}</strong> telah tersedia. Harap perbarui aplikasi Anda sekarang untuk mendapatkan fitur terbaru dan perbaikan sistem.
        </p>

        {progress > 0 && progress < 100 && (
          <div style={{ marginBottom: '20px' }}>
            <div style={{
              width: '100%', height: '8px',
              background: 'var(--bg-main, #f1f5f9)',
              borderRadius: '4px',
              overflow: 'hidden'
            }}>
              <div style={{
                width: `${progress}%`,
                height: '100%',
                background: '#6366f1',
                transition: 'width 0.3s ease'
              }}></div>
            </div>
            <p style={{ marginTop: '8px', fontSize: '0.85rem', fontWeight: 600 }}>
              Mengunduh: {progress}%
            </p>
          </div>
        )}

        <button
          onClick={onUpdate}
          disabled={progress > 0}
          style={{
            width: '100%',
            padding: '12px',
            background: progress > 0 ? '#94a3b8' : '#6366f1',
            color: 'white',
            border: 'none',
            borderRadius: '8px',
            fontSize: '1rem',
            fontWeight: 600,
            cursor: progress > 0 ? 'not-allowed' : 'pointer',
            transition: 'background 0.2s ease',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            gap: '8px'
          }}
        >
          {progress > 0 ? (
            <>
              <i className="fa-solid fa-spinner fa-spin"></i>
              <span>Memproses...</span>
            </>
          ) : (
            <>
              <i className="fa-solid fa-download"></i>
              <span>Perbarui Sekarang</span>
            </>
          )}
        </button>

        <p style={{ fontSize: '0.75rem', opacity: 0.5, margin: '16px 0 0 0' }}>
          *Aplikasi akan otomatis dimuat ulang setelah selesai.
        </p>
      </div>
    </div>
  );
}
