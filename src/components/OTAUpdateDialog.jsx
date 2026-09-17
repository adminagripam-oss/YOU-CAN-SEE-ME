import React from 'react';

export default function OTAUpdateDialog({ isOpen, version, progress, onUpdate, onCancel }) {
  if (!isOpen) return null;

  const isUpdating = progress > 0;

  return (
    <div
      style={{
        position: 'fixed',
        inset: 0,
        background: 'rgba(0, 0, 0, 0.75)',
        backdropFilter: 'blur(8px)',
        zIndex: 100000,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        padding: '1rem',
        animation: 'fadeIn 0.2s ease-out'
      }}
    >
      <div
        className="glass-card"
        style={{
          maxWidth: '440px',
          width: '100%',
          border: '1px solid var(--border-color)',
          boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.5)',
          padding: '24px',
          animation: 'slideUp 0.3s cubic-bezier(0.16, 1, 0.3, 1)'
        }}
      >
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center', gap: '12px', marginBottom: '20px' }}>
          <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', width: '80px', height: '80px', borderRadius: '50%', background: 'rgba(14, 165, 233, 0.1)', border: '1px solid rgba(14, 165, 233, 0.2)' }}>
            <img src="/assets/icon-foreground.png" alt="AgriFace Logo" style={{ width: '60px', height: '60px', objectFit: 'contain' }} />
          </div>
          <div>
            <h2 style={{ fontSize: '1.25rem', fontWeight: 800, color: 'var(--text-main)', margin: '0 0 6px 0' }}>
              Pembaruan Tersedia
            </h2>
            <p style={{ fontSize: '0.9rem', color: 'var(--text-muted)', margin: 0, lineHeight: 1.5 }}>
              Versi baru AgriFace (v{version}) telah siap untuk diunduh dan dipasang.
            </p>
          </div>
        </div>

        {isUpdating ? (
          <div style={{ margin: '24px 0' }}>
            <div style={{ width: '100%', height: '8px', background: 'var(--bg-body)', borderRadius: '99px', overflow: 'hidden', border: '1px solid var(--border-color)' }}>
              <div
                style={{
                  height: '100%',
                  background: 'var(--accent-primary)',
                  width: `${progress}%`,
                  transition: 'width 0.4s ease'
                }}
              />
            </div>
            <p style={{ textAlign: 'center', marginTop: '12px', fontSize: '0.85rem', fontWeight: 600, color: 'var(--accent-primary)' }}>
              {progress < 100 ? `Mengunduh Pembaruan... ${progress}%` : 'Memasang Pembaruan...'}
            </p>
          </div>
        ) : (
          <div style={{ background: 'rgba(14, 165, 233, 0.05)', border: '1px solid rgba(14, 165, 233, 0.2)', borderRadius: '8px', padding: '12px', marginBottom: '24px' }}>
            <p style={{ fontSize: '0.85rem', color: 'var(--text-main)', margin: 0, display: 'flex', gap: '8px', alignItems: 'flex-start' }}>
              <i className="fa-solid fa-circle-info" style={{ color: 'var(--accent-primary)', marginTop: '2px' }}></i>
              <span>Disarankan untuk melakukan pembaruan saat Anda terhubung ke koneksi Wi-Fi yang stabil.</span>
            </p>
          </div>
        )}

        <div style={{ display: 'flex', gap: '10px', justifyContent: 'flex-end', marginTop: '1.5rem' }}>
          {!isUpdating && (
            <button
              type="button"
              className="btn"
              style={{
                background: 'transparent',
                color: 'var(--text-main)',
                border: '1px solid var(--border-color)',
                flex: 1
              }}
              onClick={onCancel}
            >
              Nanti Saja
            </button>
          )}
          <button
            type="button"
            className="btn"
            style={{
              background: 'var(--accent-primary)',
              color: 'white',
              flex: isUpdating ? '1' : '1.5',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
              gap: '8px'
            }}
            onClick={onUpdate}
            disabled={isUpdating}
          >
            {isUpdating ? (
              <><i className="fa-solid fa-spinner fa-spin"></i> Memproses...</>
            ) : (
              <><i className="fa-solid fa-cloud-arrow-down"></i> Update Sekarang</>
            )}
          </button>
        </div>
      </div>
    </div>
  );
}
