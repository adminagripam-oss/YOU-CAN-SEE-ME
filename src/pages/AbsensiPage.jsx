import React, { useState, useEffect, useRef } from 'react';
import TabFaceVerification from '../components/TabFaceVerification';
import { useAuth } from '../context/AuthContext';
import { MapPin, MapPinOff, Navigation } from 'lucide-react';

export default function AbsensiPage({ employees, modelsLoaded, modelStatusText, showToast, refreshLogs }) {
  const { user } = useAuth();

  // ── GPS Permission Gate (dijalankan saat halaman Absensi dibuka) ───────────
  // Status: 'checking' | 'granted' | 'denied' | 'prompt' | 'unavailable'
  const [gpsPermission, setGpsPermission] = useState('checking');
  const [liveCoords, setLiveCoords] = useState(null); // { lat, lng, accuracy }
  const gpsWatchIdRef = useRef(null);

  // Mulai watcher GPS aktif
  const startGpsWatcher = () => {
    if (gpsWatchIdRef.current !== null) return;
    gpsWatchIdRef.current = navigator.geolocation.watchPosition(
      (pos) => setLiveCoords({
        lat: pos.coords.latitude,
        lng: pos.coords.longitude,
        accuracy: pos.coords.accuracy,
      }),
      () => {},
      { enableHighAccuracy: true, maximumAge: 10000 }
    );
  };

  // Cek izin GPS begitu halaman dibuka
  useEffect(() => {
    if (!navigator.geolocation) {
      setGpsPermission('unavailable');
      return;
    }

    if (navigator.permissions) {
      navigator.permissions.query({ name: 'geolocation' }).then((result) => {
        const applyState = (state) => {
          if (state === 'granted') {
            setGpsPermission('granted');
            startGpsWatcher();
          } else if (state === 'denied') {
            setGpsPermission('denied');
            setLiveCoords(null);
          } else {
            setGpsPermission('prompt'); // Belum diputuskan → tampilkan gate
          }
        };
        applyState(result.state);
        result.onchange = () => applyState(result.state);
      }).catch(() => {
        setGpsPermission('prompt');
      });
    } else {
      // iOS Safari — langsung tampilkan gate
      setGpsPermission('prompt');
    }

    return () => {
      if (gpsWatchIdRef.current !== null) {
        navigator.geolocation.clearWatch(gpsWatchIdRef.current);
        gpsWatchIdRef.current = null;
      }
    };
  }, []);

  /** Dipanggil saat user tap tombol "Aktifkan Lokasi GPS" */
  const requestGpsPermission = () => {
    if (!navigator.geolocation) return;
    setGpsPermission('checking');
    navigator.geolocation.getCurrentPosition(
      (pos) => {
        setGpsPermission('granted');
        setLiveCoords({
          lat: pos.coords.latitude,
          lng: pos.coords.longitude,
          accuracy: pos.coords.accuracy,
        });
        startGpsWatcher();
        if (showToast) showToast('Lokasi Aktif', 'GPS berhasil diaktifkan. Koordinat Anda siap direkam.', 'success');
      },
      (err) => {
        setGpsPermission('denied');
        setLiveCoords(null);
        if (err.code === 1) {
          if (showToast) showToast('Izin Ditolak', 'Izin lokasi ditolak. Aktifkan di pengaturan browser/HP Anda.', 'error');
        } else {
          if (showToast) showToast('GPS Gagal', 'Tidak dapat mendapatkan lokasi. Pastikan GPS HP aktif.', 'error');
        }
      },
      { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 }
    );
  };

  // ── GPS Gate Screen (tampil sebelum kamera) ────────────────────────────────
  const isBlocked = gpsPermission === 'denied' || gpsPermission === 'unavailable' || gpsPermission === 'prompt';
  const isChecking = gpsPermission === 'checking';

  if (isBlocked || isChecking) {
    const isDenied = gpsPermission === 'denied';
    const isUnavailable = gpsPermission === 'unavailable';

    return (
      <div style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: '70vh',
        padding: '2rem',
        textAlign: 'center',
        gap: '20px',
      }}>
        {/* Icon */}
        <div style={{
          width: '80px',
          height: '80px',
          borderRadius: '50%',
          background: isChecking
            ? 'rgba(99,102,241,0.12)'
            : isDenied
            ? 'rgba(239,68,68,0.12)'
            : 'rgba(245,158,11,0.12)',
          border: `2px solid ${isChecking ? '#6366f1' : isDenied ? '#ef4444' : '#f59e0b'}`,
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          animation: 'pulse 2s infinite',
        }}>
          {isChecking
            ? <Navigation size={38} color="#6366f1" />
            : <MapPinOff size={38} color={isDenied ? '#ef4444' : '#f59e0b'} />
          }
        </div>

        {/* Title */}
        <div>
          <h2 style={{ fontSize: '1.3rem', fontWeight: 800, color: 'var(--text-main)', margin: '0 0 8px' }}>
            {isChecking
              ? '🌐 Mendeteksi Lokasi GPS...'
              : isDenied
              ? '📍 Izin Lokasi Ditolak'
              : isUnavailable
              ? '📍 GPS Tidak Tersedia'
              : '📍 Aktifkan Lokasi untuk Absen'}
          </h2>
          <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem', maxWidth: '340px', lineHeight: 1.6, margin: '0 auto' }}>
            {isChecking
              ? 'Harap tunggu, sistem sedang memeriksa izin lokasi perangkat Anda...'
              : isDenied
              ? 'Izin lokasi telah ditolak. Buka Pengaturan Browser → Izin Lokasi → Izinkan, lalu muat ulang halaman.'
              : isUnavailable
              ? 'Perangkat ini tidak mendukung fitur GPS. Absensi tidak dapat dilakukan.'
              : 'Fitur absensi biometrik memerlukan izin lokasi GPS untuk merekam koordinat kehadiran Anda.'}
          </p>
        </div>

        {/* CTA Button */}
        {!isChecking && !isUnavailable && (
          <button
            onClick={requestGpsPermission}
            style={{
              padding: '14px 32px',
              borderRadius: '32px',
              border: 'none',
              background: 'linear-gradient(135deg, #10b981, #059669)',
              color: '#fff',
              fontWeight: 700,
              fontSize: '1rem',
              cursor: 'pointer',
              display: 'flex',
              alignItems: 'center',
              gap: '10px',
              boxShadow: '0 6px 20px rgba(16,185,129,0.4)',
              transition: 'transform 0.2s, box-shadow 0.2s',
            }}
            onMouseEnter={(e) => { e.currentTarget.style.transform = 'translateY(-2px)'; e.currentTarget.style.boxShadow = '0 10px 28px rgba(16,185,129,0.5)'; }}
            onMouseLeave={(e) => { e.currentTarget.style.transform = 'translateY(0)'; e.currentTarget.style.boxShadow = '0 6px 20px rgba(16,185,129,0.4)'; }}
          >
            <MapPin size={20} />
            {isDenied ? 'Coba Lagi Aktifkan Lokasi' : 'Aktifkan Lokasi GPS'}
          </button>
        )}

        {/* Spinner for checking */}
        {isChecking && (
          <div style={{
            width: '36px', height: '36px', borderRadius: '50%',
            border: '3px solid rgba(99,102,241,0.2)',
            borderTop: '3px solid #6366f1',
            animation: 'spin 0.9s linear infinite',
          }} />
        )}

        {/* Info hint */}
        <p style={{ fontSize: '0.75rem', color: 'var(--text-muted)', marginTop: '4px' }}>
          🔒 Data lokasi hanya digunakan untuk kebutuhan absensi dan tidak disimpan di luar sistem.
        </p>
      </div>
    );
  }

  // ── GPS sudah Granted → tampilkan komponen absensi ─────────────────────────
  return (
    <TabFaceVerification
      employees={employees}
      modelsLoaded={modelsLoaded}
      modelStatusText={modelStatusText}
      showToast={showToast}
      currentUser={user}
      onVerificationSuccess={refreshLogs}
      gpsPermission={gpsPermission}
      liveCoords={liveCoords}
    />
  );
}

