import React from 'react';

export default function NetworkStatusBar({ isOnline, unsyncedCount, isSyncing, onManualSync }) {
  return (
    <div className={`network-status-bar ${isOnline ? 'online' : 'offline'}`}>
      <div className="status-indicator">
        <span className={`status-dot ${isOnline ? 'green' : 'amber'}`}></span>
        <span className="status-text">
          {isOnline ? 'Terhubung (Online)' : 'Mode Offline (Penyimpanan Lokal)'}
        </span>
      </div>

      <div className="sync-info">
        {unsyncedCount > 0 && (
          <span className="pending-badge">
            <i className="fa-solid fa-cloud-arrow-up"></i> {unsyncedCount} Pending Sync
          </span>
        )}

        {isOnline && unsyncedCount > 0 && (
          <button
            type="button"
            className="btn-sync-now"
            onClick={onManualSync}
            disabled={isSyncing}
          >
            {isSyncing ? (
              <>
                <i className="fa-solid fa-spinner fa-spin"></i> Syncing...
              </>
            ) : (
              <>
                <i className="fa-solid fa-rotate"></i> Sync Sekarang
              </>
            )}
          </button>
        )}
      </div>
    </div>
  );
}
