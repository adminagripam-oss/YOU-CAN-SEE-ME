import React from 'react';
import TabAttendanceLogs from '../components/TabAttendanceLogs';

export default function LogsPage({ logs, refreshLogs, showToast, openConfirmModal }) {
  return (
    <TabAttendanceLogs
      logs={logs}
      onRefreshLogs={refreshLogs}
      showToast={showToast}
      openConfirmModal={openConfirmModal}
      refreshLogs={refreshLogs}
    />
  );
}
