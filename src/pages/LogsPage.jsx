import React, { useState } from 'react';
import TabAttendanceLogs from '../components/TabAttendanceLogs';

export default function LogsPage({ logs, refreshLogs, showToast, openConfirmModal }) {
  // Helper to get local date string YYYY-MM-DD in local timezone (e.g. WIB)
  const getLocalDateStr = (d) => {
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  };

  const [dateRange, setDateRange] = useState({ start: null, end: null });

  return (
    <TabAttendanceLogs
      logs={logs}
      onRefreshLogs={refreshLogs}
      showToast={showToast}
      openConfirmModal={openConfirmModal}
      refreshLogs={refreshLogs}
      dateRange={dateRange}
      setDateRange={setDateRange}
    />
  );
}
