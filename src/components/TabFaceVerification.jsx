import React, { useState, useEffect, useRef } from 'react';

export default function TabFaceVerification({
  employees,
  modelsLoaded,
  modelStatusText,
  showToast,
  onVerificationSuccess,
}) {
  const [selectedEmployeeId, setSelectedEmployeeId] = useState('');
  const [nikInput, setNikInput] = useState('');
  const [faceDetectBadge, setFaceDetectBadge] = useState('Menunggu Kamera...');
  const [faceBadgeColor, setFaceBadgeColor] = useState('var(--accent-warning)');
  const [attendanceStatus, setAttendanceStatus] = useState({
    checkedIn: false,
    checkInTime: null,
    loaded: false,
  });
  const [verifyMetrics, setVerifyMetrics] = useState(null);
  const [verifyResult, setVerifyResult] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const videoRef = useRef(null);
  const canvasRef = useRef(null);
  const currentDescriptorRef = useRef(null);
  const streamRef = useRef(null);

  // Sync selected employee NIK
  const handleEmployeeSelect = async (e) => {
    const empId = e.target.value;
    setSelectedEmployeeId(empId);

    const emp = employees.find((item) => String(item.id) === String(empId));
    if (emp) {
      setNikInput(emp.nik || '');
      await fetchAttendanceStatus(emp.id);
    } else {
      setNikInput('');
      setAttendanceStatus({ checkedIn: false, checkInTime: null, loaded: false });
    }
  };

  // Fetch today's attendance status for employee
  const fetchAttendanceStatus = async (empId) => {
    try {
      const res = await fetch(`/api/attendance/status/${empId}`);
      const data = await res.json();
      if (data.success) {
        setAttendanceStatus({
          checkedIn: data.checked_in,
          checkInTime: data.check_in_time,
          loaded: true,
        });
      }
    } catch (err) {
      console.error('[STATUS FETCH ERROR]:', err);
    }
  };

  // Start Live Camera
  useEffect(() => {
    let intervalId = null;

    async function startCamera() {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: { width: 640, height: 480, facingMode: 'user' },
          audio: false,
        });
        streamRef.current = stream;
        if (videoRef.current) {
          videoRef.current.srcObject = stream;
        }

        // Detection Loop
        intervalId = setInterval(async () => {
          if (!videoRef.current || !modelsLoaded || !window.faceapi) return;

          const video = videoRef.current;
          const canvas = canvasRef.current;
          if (!video.videoWidth) return;

          const displaySize = { width: video.videoWidth, height: video.videoHeight };
          if (canvas) {
            window.faceapi.matchDimensions(canvas, displaySize);
          }

          const detection = await window.faceapi
            .detectSingleFace(video)
            .withFaceLandmarks()
            .withFaceDescriptor();

          if (canvas) {
            const ctx = canvas.getContext('2d');
            ctx.clearRect(0, 0, canvas.width, canvas.height);
          }

          if (detection) {
            const resizedDetection = window.faceapi.resizeResults(detection, displaySize);
            if (canvas) {
              window.faceapi.draw.drawDetections(canvas, resizedDetection);
              window.faceapi.draw.drawFaceLandmarks(canvas, resizedDetection);
            }
            currentDescriptorRef.current = Array.from(detection.descriptor);
            setFaceDetectBadge('Wajah Terdeteksi (Vector 128-dim Siap)');
            setFaceBadgeColor('var(--accent-success)');
          } else {
            currentDescriptorRef.current = null;
            setFaceDetectBadge('Wajah Tidak Terdeteksi');
            setFaceBadgeColor('var(--accent-error)');
          }
        }, 200);
      } catch (err) {
        console.error('[CAMERA ERROR]:', err);
        setFaceDetectBadge('Gagal Membuka Kamera: ' + err.message);
        setFaceBadgeColor('var(--accent-error)');
      }
    }

    startCamera();

    return () => {
      if (intervalId) clearInterval(intervalId);
      if (streamRef.current) {
        streamRef.current.getTracks().forEach((track) => track.stop());
      }
    };
  }, [modelsLoaded]);

  // Execute Verification (Check-In or Check-Out)
  const handleVerify = async (attendanceType) => {
    if (!selectedEmployeeId && !nikInput.trim()) {
      showToast('Peringatan Absensi', 'Mohon pilih karyawan dari dropdown atau masukkan NIK!', 'error');
      return;
    }

    if (!currentDescriptorRef.current || currentDescriptorRef.current.length !== 128) {
      showToast(
        'Deteksi Wajah Gagal',
        'Wajah tidak terdeteksi oleh kamera! Pastikan wajah Anda terlihat jelas.',
        'error'
      );
      return;
    }

    setIsSubmitting(true);
    const typeLabel = attendanceType === 'CHECK_OUT' ? 'CHECK-OUT' : 'CHECK-IN';

    try {
      const payload = {
        employee_id: selectedEmployeeId ? parseInt(selectedEmployeeId) : null,
        nik: nikInput.trim() || null,
        scan_descriptor: currentDescriptorRef.current,
        location: 'Kantor Pusat - Lobby Absensi',
        attendance_type: attendanceType,
      };

      const res = await fetch('/api/attendance/verify', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      const data = await res.json();
      const isSuccess = data.success;

      if (data.metrics) {
        setVerifyMetrics({
          distance: data.metrics.euclidean_distance.toFixed(4),
          threshold: data.metrics.threshold.toFixed(4),
          type: typeLabel,
          time: new Date().toLocaleTimeString('id-ID'),
        });
      }

      setVerifyResult({
        success: isSuccess,
        title: isSuccess ? `VERIFIKASI BERHASIL (${typeLabel})` : `VERIFIKASI GAGAL (${typeLabel})`,
        message: data.message,
      });

      showToast(
        isSuccess ? `Absensi ${typeLabel} Berhasil` : `Absensi ${typeLabel} Gagal`,
        data.message,
        isSuccess ? 'success' : 'error'
      );

      if (isSuccess && selectedEmployeeId) {
        await fetchAttendanceStatus(selectedEmployeeId);
      }

      if (onVerificationSuccess) {
        onVerificationSuccess();
      }
    } catch (err) {
      console.error('[VERIFY ERROR]:', err);
      setVerifyResult({
        success: false,
        title: 'ERROR SISTEM',
        message: 'Terjadi kesalahan koneksi ke server API: ' + err.message,
      });
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div style={{ maxWidth: '680px', margin: '0 auto' }}>
      <div className="glass-card">
        <div className="card-title">
          <i className="fa-solid fa-user-check" style={{ color: 'var(--accent-cyan)' }}></i>
          Identifikasi NIK / ID Karyawan &amp; Kamera
        </div>

        {/* Dropdown Karyawan */}
        <div className="form-group">
          <label htmlFor="verify-employee-select">Pilih Karyawan / Scan NIK</label>
          <select
            id="verify-employee-select"
            value={selectedEmployeeId}
            onChange={handleEmployeeSelect}
          >
            <option value="">-- Pilih Karyawan --</option>
            {employees.map((emp) => (
              <option key={emp.id} value={emp.id}>
                {emp.nik} - {emp.name} ({emp.department}){' '}
                {emp.has_master_biometric ? ' (Siap)' : ' (Belum)'}
              </option>
            ))}
          </select>
        </div>

        {/* Manual NIK Input */}
        <div className="form-group">
          <label htmlFor="verify-nik-input">Atau Ketik / Scan NIK Manual</label>
          <input
            type="text"
            id="verify-nik-input"
            placeholder="Contoh: EMP-001"
            value={nikInput}
            onChange={(e) => setNikInput(e.target.value)}
          />
        </div>

        {/* Status Kamera & Wajah */}
        <div
          style={{
            marginBottom: '1rem',
            background: 'rgba(15,23,42,0.6)',
            padding: '10px 14px',
            borderRadius: '8px',
            fontSize: '0.85rem',
            border: '1px solid var(--border-color)',
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center',
          }}
        >
          <span style={{ color: 'var(--text-muted)' }}>Status Kamera:</span>
          <strong style={{ color: faceBadgeColor }}>{faceDetectBadge}</strong>
        </div>

        {/* Camera Stream Video */}
        <div className="form-group">
          <label>Live Camera Capture</label>
          <div className="webcam-wrapper">
            <video ref={videoRef} autoPlay muted playsInline></video>
            <canvas ref={canvasRef} className="overlay-canvas"></canvas>

            {!modelsLoaded && (
              <div className="loading-overlay">
                <div className="spinner"></div>
                <span>{modelStatusText}</span>
              </div>
            )}
          </div>
        </div>

        {/* Status Absensi Hari Ini */}
        {attendanceStatus.loaded && (
          <div
            style={{
              marginTop: '10px',
              padding: '10px 14px',
              borderRadius: '8px',
              fontSize: '0.85rem',
              border: '1px solid rgba(255,255,255,0.08)',
              background: 'rgba(0,0,0,0.3)',
            }}
          >
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', gap: '8px' }}>
              <span style={{ color: 'var(--text-muted)' }}>Status Hari Ini:</span>
              <strong>
                {attendanceStatus.checkedIn ? (
                  <span className="status-badge success">
                    <i className="fa-solid fa-right-to-bracket"></i> Sudah Check-In
                  </span>
                ) : (
                  <span className="status-badge fail">
                    <i className="fa-solid fa-circle-xmark"></i> Belum Check-In Hari Ini
                  </span>
                )}
              </strong>
            </div>

            {attendanceStatus.checkedIn && attendanceStatus.checkInTime && (
              <div style={{ marginTop: '4px', fontSize: '0.8rem', color: 'var(--text-muted)' }}>
                Check-In pukul:{' '}
                <span style={{ color: '#f8fafc', fontWeight: 600 }}>
                  {new Date(attendanceStatus.checkInTime).toLocaleTimeString('id-ID', {
                    hour: '2-digit',
                    minute: '2-digit',
                    second: '2-digit',
                  })}
                </span>
              </div>
            )}
          </div>
        )}

        {/* Smart Button Absensi */}
        <div style={{ marginTop: '10px' }}>
          {!attendanceStatus.checkedIn ? (
            <button
              type="button"
              className="btn btn-success"
              disabled={isSubmitting || !faceDetectBadge.includes('Terdeteksi')}
              onClick={() => handleVerify('CHECK_IN')}
            >
              <i className="fa-solid fa-right-to-bracket"></i> Check-In (Masuk)
            </button>
          ) : (
            <button
              type="button"
              className="btn"
              style={{
                background: 'linear-gradient(135deg, #f59e0b, #d97706)',
                color: '#fff',
              }}
              disabled={isSubmitting || !faceDetectBadge.includes('Terdeteksi')}
              onClick={() => handleVerify('CHECK_OUT')}
            >
              <i className="fa-solid fa-right-from-bracket"></i> Check-Out (Keluar)
            </button>
          )}
        </div>

        {/* Result Metrics & Shadcn Alert */}
        {verifyResult && (
          <div
            className={`shadcn-alert ${verifyResult.success ? 'success' : 'error'}`}
            style={{ marginTop: '1.25rem', flexDirection: 'column', gap: '8px' }}
          >
            {verifyMetrics && (
              <div
                className="result-metrics"
                style={{
                  width: '100%',
                  marginBottom: '6px',
                  marginTop: 0,
                  background: 'rgba(0,0,0,0.3)',
                  border: '1px solid rgba(255,255,255,0.08)',
                  padding: '10px 14px',
                  borderRadius: '8px',
                }}
              >
                <div className="metric-item">
                  <span className="metric-label">Euclidean Distance</span>
                  <span className="metric-value">{verifyMetrics.distance}</span>
                </div>
                <div className="metric-item">
                  <span className="metric-label">Max Threshold</span>
                  <span className="metric-value">{verifyMetrics.threshold}</span>
                </div>
                <div className="metric-item">
                  <span className="metric-label">Tipe Absensi</span>
                  <span className="metric-value">{verifyMetrics.type}</span>
                </div>
                <div className="metric-item">
                  <span className="metric-label">Timestamp</span>
                  <span className="metric-value">{verifyMetrics.time}</span>
                </div>
              </div>
            )}

            <div style={{ display: 'flex', gap: '12px', alignItems: 'flex-start', width: '100%' }}>
              <div className="alert-icon" style={{ fontSize: '1.25rem' }}>
                <i
                  className={`fa-solid ${
                    verifyResult.success ? 'fa-circle-check' : 'fa-circle-xmark'
                  }`}
                  style={{ color: verifyResult.success ? '#34d399' : '#f87171' }}
                ></i>
              </div>
              <div className="alert-content">
                <div className="alert-title" style={{ fontSize: '1.05rem', fontWeight: 800 }}>
                  {verifyResult.title}
                </div>
                <div className="alert-description" style={{ fontSize: '0.92rem', fontWeight: 500, marginTop: '2px' }}>
                  {verifyResult.message}
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
