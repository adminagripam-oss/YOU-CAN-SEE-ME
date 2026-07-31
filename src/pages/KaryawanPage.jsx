import React from 'react';
import TabEmployeeManagement from '../components/TabEmployeeManagement';

export default function KaryawanPage({ employees, modelsLoaded, showToast, refreshEmployees, openConfirmModal }) {
  return (
    <TabEmployeeManagement
      employees={employees}
      modelsLoaded={modelsLoaded}
      showToast={showToast}
      refreshEmployees={refreshEmployees}
      openConfirmModal={openConfirmModal}
    />
  );
}
