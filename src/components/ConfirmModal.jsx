import React from 'react';

export default function ConfirmModal({ isOpen, title, message, onConfirm, onCancel, confirmText = 'Ya, Lanjutkan' }) {
  if (!isOpen) return null;

  return (
    <>
      <div className="shadcn-overlay" onClick={onCancel} />
      <div
        role="alertdialog"
        className="shadcn-content"
      >
        <div className="shadcn-header">
          <h2 className="shadcn-title">
            <i className="fa-solid fa-circle-exclamation" style={{ color: 'var(--accent-error)' }}></i>
            {title}
          </h2>
          <p className="shadcn-description">
            {message}
          </p>
        </div>

        <div className="shadcn-footer">
          <button
            type="button"
            className="shadcn-btn shadcn-btn-outline"
            onClick={onCancel}
          >
            Batal
          </button>
          <button
            type="button"
            className="shadcn-btn shadcn-btn-destructive"
            onClick={onConfirm}
          >
            {confirmText}
          </button>
        </div>
      </div>
    </>
  );
}
