import React from 'react';

export default function ShadcnToast({ toasts = [] }) {
  if (!toasts || toasts.length === 0) return null;

  return (
    <div className="toast-container">
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
              <i className={`fa-solid ${iconClass}`}></i>
            </div>
            <div className="alert-content">
              <div className="alert-title" style={!toast.description ? { marginBottom: 0 } : {}}>{toast.title}</div>
              {toast.description && <div className="alert-description">{toast.description}</div>}
            </div>
          </div>
        );
      })}
    </div>
  );
}
