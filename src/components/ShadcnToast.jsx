import React from 'react';

export default function ShadcnToast({ toasts = [] }) {
  if (!toasts || toasts.length === 0) return null;

  return (
    <div
      style={{
        position: 'fixed',
        top: '20px',
        right: '20px',
        zIndex: 9999,
        display: 'flex',
        flexDirection: 'column',
        gap: '10px',
        maxWidth: '380px',
        width: '100%',
        pointerEvents: 'none',
      }}
    >
      {toasts.map((toast) => {
        const isSuccess = toast.type === 'success';
        const isError = toast.type === 'error';
        const iconClass = isSuccess
          ? 'fa-circle-check'
          : isError
          ? 'fa-circle-xmark'
          : 'fa-circle-info';

        return (
          <div key={toast.id} className={`shadcn-alert ${toast.type || 'info'}`}>
            <div className="alert-icon">
              <i
                className={`fa-solid ${iconClass}`}
                style={{
                  color: isSuccess ? '#34d399' : isError ? '#f87171' : '#818cf8',
                }}
              ></i>
            </div>
            <div className="alert-content">
              <div className="alert-title">{toast.title}</div>
              <div className="alert-description">{toast.description}</div>
            </div>
          </div>
        );
      })}
    </div>
  );
}
