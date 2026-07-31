import React from 'react';
import TabFaceVerification from '../components/TabFaceVerification';
import { useAuth } from '../context/AuthContext';

export default function AbsensiPage({ employees, modelsLoaded, modelStatusText, showToast, refreshLogs }) {
  const { user } = useAuth();

  return (
    <TabFaceVerification
      employees={employees}
      modelsLoaded={modelsLoaded}
      modelStatusText={modelStatusText}
      showToast={showToast}
      currentUser={user}
      onVerificationSuccess={refreshLogs}
    />
  );
}
