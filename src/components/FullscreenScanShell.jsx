import React, { useEffect, useRef } from 'react';
import { createPortal } from 'react-dom';
import { X, SwitchCamera } from 'lucide-react';

export default function FullscreenScanShell({
  open,
  onClose,
  closeDisabled = false,
  mode = 'checkin',
  title = 'Scan Wajah',
  subtitle = '',
  hint = '',
  hintTone = 'info', // 'info' | 'warn' | 'error' | 'success'
  headerExtra = null,
  onToggleFacing = null,
  facingMode = 'environment',
  footer = null,
  children,
}) {
  const closeBtnRef = useRef(null);

  // Kunci scroll body saat terbuka dan tangani visibilitychange
  useEffect(() => {
    if (!open) return;

    const originalOverflow = document.body.style.overflow;
    document.body.style.overflow = 'hidden';

    // Fokus awal pada tombol tutup untuk aksesibilitas
    if (closeBtnRef.current && !closeDisabled) {
      closeBtnRef.current.focus();
    }

    const handleVisibilityChange = () => {
      if (document.visibilityState === 'hidden' && !closeDisabled) {
        onClose?.();
      }
    };

    document.addEventListener('visibilitychange', handleVisibilityChange);

    return () => {
      document.body.style.overflow = originalOverflow;
      document.removeEventListener('visibilitychange', handleVisibilityChange);
    };
  }, [open, closeDisabled, onClose]);

  if (!open || typeof document === 'undefined') {
    return null;
  }

  const hintBgColors = {
    info: 'rgba(255, 255, 255, 0.12)',
    warn: 'rgba(245, 158, 11, 0.22)',
    error: 'rgba(239, 68, 68, 0.25)',
    success: 'rgba(34, 197, 94, 0.25)',
  };

  const hintTextColors = {
    info: '#ffffff',
    warn: '#fbbf24',
    error: '#f87171',
    success: '#4ade80',
  };

  const shellContent = (
    <div
      className="scan-shell"
      role="dialog"
      aria-modal="true"
      aria-label={title}
    >
      {/* ── Header ── */}
      <div className="scan-shell-header">
        <button
          ref={closeBtnRef}
          type="button"
          onClick={onClose}
          disabled={closeDisabled}
          aria-label="Tutup Scanner"
          style={{
            minWidth: '44px',
            minHeight: '44px',
            width: '44px',
            height: '44px',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            background: 'rgba(255, 255, 255, 0.12)',
            border: 'none',
            borderRadius: '50%',
            color: '#fff',
            cursor: closeDisabled ? 'not-allowed' : 'pointer',
            opacity: closeDisabled ? 0.35 : 1,
            touchAction: 'manipulation',
          }}
        >
          <X size={22} />
        </button>

        <div style={{ flex: 1, textAlign: 'center', padding: '0 8px' }}>
          <div style={{ fontSize: '1rem', fontWeight: 700, color: '#fff', lineHeight: 1.2 }}>
            {title}
          </div>
          {subtitle && (
            <div style={{ fontSize: '0.8rem', color: '#94a3b8', marginTop: '2px' }}>
              {subtitle}
            </div>
          )}
        </div>

        <div style={{ minWidth: '44px', display: 'flex', alignItems: 'center', justifyContent: 'flex-end', gap: '8px' }}>
          {headerExtra}
          {onToggleFacing && (
            <button
              type="button"
              onClick={onToggleFacing}
              aria-label={`Ganti ke kamera ${facingMode === 'user' ? 'belakang' : 'depan'}`}
              title={`Ganti ke kamera ${facingMode === 'user' ? 'belakang' : 'depan'}`}
              style={{
                minWidth: '44px',
                minHeight: '44px',
                width: '44px',
                height: '44px',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                background: 'rgba(255, 255, 255, 0.12)',
                border: 'none',
                borderRadius: '50%',
                color: '#fff',
                cursor: 'pointer',
                touchAction: 'manipulation',
              }}
            >
              <SwitchCamera size={22} />
            </button>
          )}
        </div>
      </div>

      {/* ── Stage (Area Kamera + Canvas) ── */}
      <div className="scan-shell-stage">
        {children}
      </div>

      {/* ── Footer ── */}
      <div className="scan-shell-footer">
        {hint && (
          <div
            className="scan-shell-hint"
            style={{
              background: hintBgColors[hintTone] || hintBgColors.info,
              color: hintTextColors[hintTone] || hintTextColors.info,
              border: `1px solid ${hintTextColors[hintTone] || hintTextColors.info}44`,
            }}
          >
            {hint}
          </div>
        )}
        {footer}
      </div>
    </div>
  );

  return createPortal(shellContent, document.body);
}
