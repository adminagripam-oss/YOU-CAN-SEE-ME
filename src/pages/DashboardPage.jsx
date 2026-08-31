import React, { useState, useMemo } from 'react';
import { useAuth } from '../context/AuthContext';
import { Link } from 'react-router-dom';

export default function DashboardPage({ employees = [], logs = [], modelsLoaded }) {
  const { user } = useAuth();
  
  // Date Filter State (default to today YYYY-MM-DD)
  const todayStr = new Date().toISOString().split('T')[0];
  const yesterdayStr = (() => {
    const d = new Date();
    d.setDate(d.getDate() - 1);
    return d.toISOString().split('T')[0];
  })();
  const [selectedDate, setSelectedDate] = useState(todayStr);
  const [selectedSegment, setSelectedSegment] = useState(null);
  const [kebunSearch, setKebunSearch] = useState('');
  const [selectedKebun, setSelectedKebun] = useState('All');

  // List of all kebuns from regional CSV data
  const allKebunsFromCSV = useMemo(() => [
    'Bukit Harapan I',
    'Bukit Harapan II',
    'Parsub',
    'Patogu Janji',
    'Panca Agro Lestari (Pal)',
    'Wana Jingga Timur (Wjt)',
    'Duta Palma Nusantara (Dpn) I',
    'Duta Palma Nusantara (Dpn) II',
    'Duta Palma Nusantara (Dpn) III',
    'Eluan Mahkota (EMA) - KT',
    'Johan Sentosa',
    'Palma Inti Lestari (PIL)',
    'Bukit Jago Indah (BJI)',
    'Kaliau Mas Perkasa A (KMP A)',
    'Kaliau Mas Perkasa B (KMP B)',
    'Teluk Keramat (TKR)',
    'Wana Hijau Semesta I (WHS I)',
    'Wana Hijau Semesta II (L1)',
    'Wana Hijau Semesta II (L2)',
    'Wana Hijau Semesta II (WHS II)',
    'Wana Hijau Semesta III (WHS III)',
    'Wana Hijau Semesta IV',
    'Mitra Wawasan (MWS)',
    'Persada Alam (PA)',
    'Darmex - I',
    'Darmex - II',
    'Darmex - X'
  ], []);

  // Compute allowed kebuns based on logged-in admin's role & region
  const availableKebuns = useMemo(() => {
    const dynamicKebuns = [...new Set(employees.map(e => e.nama_kebun).filter(Boolean))];
    if (user?.role === 'headoffice_admin') {
      const merged = [...new Set([...dynamicKebuns, ...allKebunsFromCSV])];
      merged.sort();
      return merged;
    } else if (user?.role === 'regional_admin') {
      const regionKebuns = {
        'Sumut 2': ['Bukit Harapan I', 'Bukit Harapan II', 'Parsub', 'Patogu Janji'],
        'Riau 1': ['Panca Agro Lestari (Pal)', 'Wana Jingga Timur (Wjt)', 'Duta Palma Nusantara (Dpn) I', 'Duta Palma Nusantara (Dpn) II', 'Duta Palma Nusantara (Dpn) III', 'Eluan Mahkota (EMA) - KT', 'Johan Sentosa', 'Palma Inti Lestari (PIL)'],
        'Kalbar 1A': ['Bukit Jago Indah (BJI)', 'Kaliau Mas Perkasa A (KMP A)', 'Kaliau Mas Perkasa B (KMP B)', 'Teluk Keramat (TKR)', 'Wana Hijau Semesta I (WHS I)', 'Wana Hijau Semesta II (L1)', 'Wana Hijau Semesta II (L2)', 'Wana Hijau Semesta II (WHS II)', 'Wana Hijau Semesta III (WHS III)', 'Wana Hijau Semesta IV'],
        'Kalbar 1B': ['Mitra Wawasan (MWS)', 'Persada Alam (PA)', 'Darmex - I', 'Darmex - II', 'Darmex - X']
      };
      const allowed = regionKebuns[user.region] || [];
      const merged = [...new Set([...dynamicKebuns, ...allowed])];
      merged.sort();
      return merged;
    }
    dynamicKebuns.sort();
    return dynamicKebuns;
  }, [employees, user, allKebunsFromCSV]);

  // Filter employees based on selected kebun
  const filteredEmployees = useMemo(() => {
    if (!selectedKebun || selectedKebun === 'All') return employees;
    return employees.filter(e => e.nama_kebun === selectedKebun);
  }, [employees, selectedKebun]);

  const totalEmployees = filteredEmployees.length || 0;

  // Filter logs dynamically based on selectedDate and selectedKebun
  const filteredLogs = useMemo(() => {
    const kebunEmpIds = new Set(filteredEmployees.map(e => String(e.id)));
    const kebunEmpNiks = new Set(filteredEmployees.map(e => String(e.nik)));

    return logs.filter((l) => {
      if (!l.timestamp) return false;
      const logDateStr = new Date(l.timestamp).toISOString().split('T')[0];
      if (logDateStr !== selectedDate) return false;

      // Filter by selected kebun
      if (selectedKebun && selectedKebun !== 'All') {
        const empIdStr = String(l.employee_id);
        const nikStr = String(l.nik);
        return kebunEmpIds.has(empIdStr) || kebunEmpNiks.has(nikStr);
      }
      return true;
    });
  }, [logs, selectedDate, selectedKebun, filteredEmployees]);

  const isReadOnlyMonitor = user?.role === 'regional_admin' || user?.role === 'headoffice_admin';

  // Grouping data by kebun (for Regional & Head Office dashboards)
  const kebunSummary = useMemo(() => {
    const uniqueKebuns = [...new Set(filteredEmployees.map(e => e.nama_kebun).filter(Boolean))];
    
    return uniqueKebuns.map(kebunName => {
      const kebunEmployees = filteredEmployees.filter(e => e.nama_kebun === kebunName);
      const kebunEmpIds = new Set(kebunEmployees.map(e => String(e.id)));

      // Hitung HK Hadir (TK Hadir) hari ini
      const kebunFilteredLogs = filteredLogs.filter(l => {
        const empIdStr = String(l.employee_id);
        const isVerified = !(l.status?.toUpperCase().includes('GAGAL') || l.status?.toUpperCase().includes('REJECT'));
        return kebunEmpIds.has(empIdStr) && isVerified;
      });
      
      const uniqueHadirIds = new Set(kebunFilteredLogs.map(l => String(l.employee_id)));
      const hadirCount = uniqueHadirIds.size;
      const totalCount = kebunEmployees.length || 1;
      const percent = ((hadirCount / totalCount) * 100).toFixed(1);

      const firstEmp = kebunEmployees[0];

      return {
        nama_kebun: kebunName,
        regional: firstEmp?.regional || firstEmp?.region || '-',
        distrik: firstEmp?.distrik || '-',
        totalEmployees: kebunEmployees.length,
        hadirCount,
        percentage: percent
      };
    }).sort((a, b) => b.hadirCount - a.hadirCount);
  }, [filteredEmployees, filteredLogs]);

  const filteredKebunSummary = useMemo(() => {
    return kebunSummary.filter(k => {
      const q = kebunSearch.toLowerCase();
      return (
        k.nama_kebun.toLowerCase().includes(q) ||
        k.regional.toLowerCase().includes(q) ||
        k.distrik.toLowerCase().includes(q)
      );
    });
  }, [kebunSummary, kebunSearch]);

  // Calculate 100% DYNAMIC real-time attendance counts from filteredLogs
  // Count unique verified employee check-ins for the selectedDate
  const verifiedEmployeeIds = new Set(
    filteredLogs
      .filter((l) => {
        const statusLower = (l.status || '').toLowerCase();
        return statusLower === '' || statusLower.includes('hadir') || statusLower.includes('verified');
      })
      .map((l) => l.nik || l.employee_id)
  );

  const verifiedCount = filteredLogs.length > 0 ? (verifiedEmployeeIds.size || filteredLogs.length) : 0;
  const izinCount = filteredLogs.filter((l) => l.status === 'Izin').length;
  const sakitCount = filteredLogs.filter((l) => l.status === 'Sakit').length;
  
  // Count Lupa Check-out
  const lupaCheckoutCount = filteredLogs.filter(l => {
    const statusLower = (l.status || '').toLowerCase();
    return statusLower.includes('lupa_checkout') || statusLower.includes('lupa check-out');
  }).length;
  
  // Mangkir = Total Employees - (Hadir + Izin + Sakit)
  const mangkirCount = totalEmployees > 0 ? Math.max(totalEmployees - verifiedCount - izinCount - sakitCount, 0) : 0;

  // Ratios & Percentages
  const totalCountForCalc = totalEmployees > 0 ? totalEmployees : 1;
  const hadirRatio = verifiedCount / totalCountForCalc;
  const izinRatio = izinCount / totalCountForCalc;
  const sakitRatio = sakitCount / totalCountForCalc;
  const mangkirRatio = mangkirCount / totalCountForCalc;
  const lupaCheckoutRatio = lupaCheckoutCount / totalCountForCalc;

  const hadirPct = (hadirRatio * 100).toFixed(1).replace('.', ',');
  const izinPct = (izinRatio * 100).toFixed(1).replace('.', ',');
  const sakitPct = (sakitRatio * 100).toFixed(1).replace('.', ',');
  const mangkirPct = (mangkirRatio * 100).toFixed(1).replace('.', ',');
  const lupaCheckoutPct = (lupaCheckoutRatio * 100).toFixed(1).replace('.', ',');

  // Donut SVG circumference calculation (2 * PI * r=35 = 219.91)
  const C = 219.91;
  const dashHadir = (hadirRatio * C).toFixed(2);
  const dashIzin = (izinRatio * C).toFixed(2);
  const dashSakit = (sakitRatio * C).toFixed(2);
  const dashMangkir = (mangkirRatio * C).toFixed(2);

  const offsetIzin = -(hadirRatio * C).toFixed(2);
  const offsetSakit = -((hadirRatio + izinRatio) * C).toFixed(2);
  const offsetMangkir = -((hadirRatio + izinRatio + sakitRatio) * C).toFixed(2);

  // Dark Muted Color Palette
  const workforceComposition = [
    { name: 'TK Hadir', count: verifiedCount, percentage: hadirPct, color: '#15803d', bgTag: 'rgba(21, 128, 61, 0.12)' },
    { name: 'Izin', count: izinCount, percentage: izinPct, color: '#1d4ed8', bgTag: 'rgba(29, 78, 216, 0.12)' },
    { name: 'Sakit', count: sakitCount, percentage: sakitPct, color: '#b45309', bgTag: 'rgba(180, 83, 9, 0.12)' },
    { name: 'Mangkir', count: mangkirCount, percentage: mangkirPct, color: '#b91c1c', bgTag: 'rgba(185, 28, 28, 0.12)' }
  ];

  // Quick Date Preset Handlers
  const handleSetToday = () => setSelectedDate(todayStr);
  const handleSetYesterday = () => {
    const d = new Date();
    d.setDate(d.getDate() - 1);
    setSelectedDate(d.toISOString().split('T')[0]);
  };

  // Adjust Date by Days (+1 or -1)
  const adjustDate = (days) => {
    const current = new Date(selectedDate);
    if (isNaN(current.getTime())) return;
    current.setDate(current.getDate() + days);
    setSelectedDate(current.toISOString().split('T')[0]);
  };

  return (
    <div className="dashboard-page" style={{ display: 'flex', flexDirection: 'column', gap: '1.25rem', width: '100%' }}>
      {/* DATE FILTER HEADER BAR - DYNAMIC REAL-TIME BINDING */}
      <div className="glass-card" style={{ padding: '0.9rem 1.25rem', marginBottom: 0, display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '12px', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: '16px', flexWrap: 'wrap' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
            <i className="fa-solid fa-calendar-days" style={{ color: 'var(--accent-primary)', fontSize: '1.1rem' }}></i>
            <span style={{ fontSize: '0.9rem', fontWeight: 800, color: 'var(--text-main)', whiteSpace: 'nowrap' }}>
              Navigasi Tanggal Absensi:
            </span>
          </div>

          {/* DYNAMIC KEBUN FILTER SELECTOR */}
          {user?.role !== 'estate_admin' && (
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
              <span style={{ fontSize: '0.85rem', fontWeight: 700, color: 'var(--text-muted)' }}>Kebun:</span>
              <select
                value={selectedKebun}
                onChange={(e) => setSelectedKebun(e.target.value)}
                style={{
                  padding: '6px 12px',
                  borderRadius: '8px',
                  border: '1px solid var(--border-color)',
                  background: 'var(--bg-primary)',
                  color: 'var(--text-main)',
                  fontSize: '0.85rem',
                  fontWeight: 700,
                  outline: 'none',
                  cursor: 'pointer'
                }}
              >
                <option value="All">Semua Kebun</option>
                {availableKebuns.map(k => (
                  <option key={k} value={k}>{k}</option>
                ))}
              </select>
            </div>
          )}
        </div>

        {/* Unified Tab Navigation Group */}
        <div style={{ display: 'flex', gap: '8px', alignItems: 'center', flexWrap: 'wrap' }}>
          <button
            type="button"
            onClick={handleSetToday}
            style={{
              padding: '6px 14px',
              borderRadius: '8px',
              fontSize: '0.8rem',
              fontWeight: 700,
              background: selectedDate === todayStr ? 'var(--accent-primary)' : 'var(--bg-primary)',
              color: selectedDate === todayStr ? '#ffffff' : 'var(--text-muted)',
              border: selectedDate === todayStr ? '1px solid var(--accent-primary)' : '1px solid var(--border-color)',
              cursor: 'pointer',
              transition: 'all 0.2s ease',
              boxShadow: selectedDate === todayStr ? '0 4px 12px rgba(99, 102, 241, 0.2)' : 'none'
            }}
          >
            Hari Ini
          </button>
          <button
            type="button"
            onClick={handleSetYesterday}
            style={{
              padding: '6px 14px',
              borderRadius: '8px',
              fontSize: '0.8rem',
              fontWeight: 700,
              background: selectedDate === yesterdayStr ? 'var(--accent-primary)' : 'var(--bg-primary)',
              color: selectedDate === yesterdayStr ? '#ffffff' : 'var(--text-muted)',
              border: selectedDate === yesterdayStr ? '1px solid var(--accent-primary)' : '1px solid var(--border-color)',
              cursor: 'pointer',
              transition: 'all 0.2s ease',
              boxShadow: selectedDate === yesterdayStr ? '0 4px 12px rgba(99, 102, 241, 0.2)' : 'none'
            }}
          >
            Kemarin
          </button>
          
          <div style={{ display: 'flex', alignItems: 'center', gap: '6px', marginLeft: '6px' }}>
            <span style={{ fontSize: '0.78rem', color: 'var(--text-muted)', fontWeight: 700 }}>Custom:</span>
            
            {/* Previous Day Arrow Button */}
            <button
              type="button"
              onClick={() => adjustDate(-1)}
              style={{
                background: 'var(--bg-primary)',
                border: '1px solid var(--border-color)',
                borderRadius: '8px',
                color: 'var(--text-muted)',
                padding: '6px 10px',
                cursor: 'pointer',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                transition: 'all 0.2s ease'
              }}
              title="Hari Sebelumnya"
            >
              <i className="fa-solid fa-chevron-left" style={{ fontSize: '0.8rem' }}></i>
            </button>

            <input
              type="date"
              value={selectedDate}
              onChange={(e) => setSelectedDate(e.target.value)}
              style={{
                padding: '6px 12px',
                borderRadius: '8px',
                border: (selectedDate !== todayStr && selectedDate !== yesterdayStr) ? '1px solid var(--accent-primary)' : '1px solid var(--border-color)',
                background: (selectedDate !== todayStr && selectedDate !== yesterdayStr) ? 'rgba(99, 102, 241, 0.12)' : 'var(--bg-primary)',
                color: (selectedDate !== todayStr && selectedDate !== yesterdayStr) ? 'var(--text-main)' : 'var(--text-muted)',
                fontSize: '0.85rem',
                fontWeight: 700,
                outline: 'none',
                cursor: 'pointer',
                transition: 'all 0.2s ease'
              }}
            />

            {/* Next Day Arrow Button */}
            <button
              type="button"
              onClick={() => adjustDate(1)}
              style={{
                background: 'var(--bg-primary)',
                border: '1px solid var(--border-color)',
                borderRadius: '8px',
                color: 'var(--text-muted)',
                padding: '6px 10px',
                cursor: 'pointer',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                transition: 'all 0.2s ease'
              }}
              title="Hari Berikutnya"
            >
              <i className="fa-solid fa-chevron-right" style={{ fontSize: '0.8rem' }}></i>
            </button>
          </div>
        </div>
      </div>

      {/* 1. TOP SECTION: 5 DYNAMIC WORKFORCE KPI CARDS */}
      <div className="grid-2" style={{ gridTemplateColumns: 'repeat(auto-fit, minmax(140px, 1fr))', gap: '0.85rem' }}>
        {/* KPI 1: Total Tenaga Kerja */}
        <div className="glass-card" style={{ marginBottom: 0, padding: '1rem 1.15rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '4px' }}>
            <span style={{ fontSize: '0.88rem', fontWeight: 800, color: 'var(--text-muted)' }}>Total TK</span>
            <span style={{ fontSize: '0.7rem', fontWeight: 700, color: 'var(--text-muted)', background: 'var(--bg-primary)', padding: '2px 6px', borderRadius: '6px', border: '1px solid var(--border-color)' }}>
              TK
            </span>
          </div>
          <div style={{ fontSize: '1.85rem', fontWeight: 900, color: 'var(--text-main)', marginTop: '0.5rem', letterSpacing: '-0.02em' }}>
            {totalEmployees.toLocaleString('id-ID')}
          </div>
          <div style={{ fontSize: '0.72rem', color: 'var(--text-muted)', marginTop: '3px', fontWeight: 600 }}>100% Pemanen</div>
        </div>

        {/* KPI 2: TK Hadir */}
        <div className="glass-card" style={{ marginBottom: 0, padding: '1rem 1.15rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '4px' }}>
            <span style={{ fontSize: '0.88rem', fontWeight: 800, color: 'var(--text-muted)' }}>TK Hadir</span>
            <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#15803d', background: 'rgba(21, 128, 61, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(21, 128, 61, 0.25)' }}>
              {hadirPct}%
            </span>
          </div>
          <div style={{ fontSize: '1.85rem', fontWeight: 900, color: '#15803d', marginTop: '0.5rem', letterSpacing: '-0.02em' }}>
            {verifiedCount.toLocaleString('id-ID')}
          </div>
          <div style={{ fontSize: '0.72rem', color: 'var(--text-muted)', marginTop: '3px', fontWeight: 600 }}>Verified Scan</div>
        </div>

        {/* KPI 3: Izin */}
        <div className="glass-card" style={{ marginBottom: 0, padding: '1rem 1.15rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '4px' }}>
            <span style={{ fontSize: '0.88rem', fontWeight: 800, color: 'var(--text-muted)' }}>Izin</span>
            <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#1d4ed8', background: 'rgba(29, 78, 216, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(29, 78, 216, 0.25)' }}>
              {izinPct}%
            </span>
          </div>
          <div style={{ fontSize: '1.85rem', fontWeight: 900, color: '#1d4ed8', marginTop: '0.5rem', letterSpacing: '-0.02em' }}>
            {izinCount}
          </div>
          <div style={{ fontSize: '0.72rem', color: 'var(--text-muted)', marginTop: '3px', fontWeight: 600 }}>Izin Resmi</div>
        </div>

        {/* KPI 4: Sakit */}
        <div className="glass-card" style={{ marginBottom: 0, padding: '1rem 1.15rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '4px' }}>
            <span style={{ fontSize: '0.88rem', fontWeight: 800, color: 'var(--text-muted)' }}>Sakit</span>
            <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#b45309', background: 'rgba(180, 83, 9, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(180, 83, 9, 0.25)' }}>
              {sakitPct}%
            </span>
          </div>
          <div style={{ fontSize: '1.85rem', fontWeight: 900, color: '#b45309', marginTop: '0.5rem', letterSpacing: '-0.02em' }}>
            {sakitCount}
          </div>
          <div style={{ fontSize: '0.72rem', color: 'var(--text-muted)', marginTop: '3px', fontWeight: 600 }}>Keterangan Dokter</div>
        </div>

        {/* KPI 5: Mangkir */}
        <div className="glass-card" style={{ marginBottom: 0, padding: '1rem 1.15rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '4px' }}>
            <span style={{ fontSize: '0.88rem', fontWeight: 800, color: 'var(--text-muted)' }}>Mangkir</span>
            <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#b91c1c', background: 'rgba(185, 28, 28, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(185, 28, 28, 0.25)' }}>
              {mangkirPct}%
            </span>
          </div>
          <div style={{ fontSize: '1.85rem', fontWeight: 900, color: '#b91c1c', marginTop: '0.5rem', letterSpacing: '-0.02em' }}>
            {mangkirCount}
          </div>
          <div style={{ fontSize: '0.72rem', color: 'var(--text-muted)', marginTop: '3px', fontWeight: 600 }}>Tanpa Keterangan</div>
        </div>

        {/* KPI 6: Lupa Check-out */}
        <div className="glass-card" style={{ marginBottom: 0, padding: '1rem 1.15rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '4px' }}>
            <span style={{ fontSize: '0.88rem', fontWeight: 800, color: 'var(--text-muted)' }}>Lupa Check-out</span>
            <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#f97316', background: 'rgba(249, 115, 22, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(249, 115, 22, 0.25)' }}>
              {lupaCheckoutPct}%
            </span>
          </div>
          <div style={{ fontSize: '1.85rem', fontWeight: 900, color: '#f97316', marginTop: '0.5rem', letterSpacing: '-0.02em' }}>
            {lupaCheckoutCount}
          </div>
          <div style={{ fontSize: '0.72rem', color: 'var(--text-muted)', marginTop: '3px', fontWeight: 600 }}>Butuh Koreksi</div>
        </div>
      </div>

      {/* 2. MINIMALIST PIE CHART CARD - DYNAMIC SVG RING ANGLES BINDING */}
      <div className="glass-card" style={{ padding: '1.5rem 1.5rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
        {/* Minimalist Card Header */}
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', paddingBottom: '0.85rem', borderBottom: '1px solid var(--border-color)', marginBottom: '1.25rem', flexWrap: 'wrap', gap: '8px' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
            <i className="fa-solid fa-chart-pie" style={{ color: 'var(--text-main)', fontSize: '1.1rem' }}></i>
            <h3 style={{ fontSize: '1.05rem', fontWeight: 800, color: 'var(--text-main)' }}>
              Komposisi Kehadiran TK
            </h3>
          </div>
          <div style={{ fontSize: '0.82rem', fontWeight: 700, color: 'var(--text-muted)' }}>
            Tanggal: {selectedDate} ({filteredLogs.length} Log Absensi)
          </div>
        </div>

        {/* Chart & Minimalist Legend Responsive Grid */}
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(240px, 1fr))', gap: '1.5rem', alignItems: 'center' }}>
          {/* SVG Pie/Donut Chart Center */}
          <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', position: 'relative', height: '220px' }}>
            <svg viewBox="0 0 100 100" style={{ width: '200px', height: '200px', transform: 'rotate(-90deg)' }}>
              <circle cx="50" cy="50" r="35" fill="none" stroke="var(--bg-primary)" strokeWidth="15" />
              
              {/* Segment 1: TK Hadir */}
              {hadirRatio > 0 && (
                <circle
                  cx="50" cy="50" r="35" fill="none" stroke="#15803d" strokeWidth="15"
                  strokeDasharray={`${dashHadir} ${C}`} strokeDashoffset="0"
                  style={{ cursor: 'pointer', transition: 'all 0.4s ease' }}
                  onClick={() => setSelectedSegment(selectedSegment === 0 ? null : 0)}
                />
              )}
              
              {/* Segment 2: Izin */}
              {izinRatio > 0 && (
                <circle
                  cx="50" cy="50" r="35" fill="none" stroke="#1d4ed8" strokeWidth="15"
                  strokeDasharray={`${dashIzin} ${C}`} strokeDashoffset={`${offsetIzin}`}
                  style={{ cursor: 'pointer', transition: 'all 0.4s ease' }}
                  onClick={() => setSelectedSegment(selectedSegment === 1 ? null : 1)}
                />
              )}
              
              {/* Segment 3: Sakit */}
              {sakitRatio > 0 && (
                <circle
                  cx="50" cy="50" r="35" fill="none" stroke="#b45309" strokeWidth="15"
                  strokeDasharray={`${dashSakit} ${C}`} strokeDashoffset={`${offsetSakit}`}
                  style={{ cursor: 'pointer', transition: 'all 0.4s ease' }}
                  onClick={() => setSelectedSegment(selectedSegment === 2 ? null : 2)}
                />
              )}

              {/* Segment 4: Mangkir */}
              {mangkirRatio > 0 && (
                <circle
                  cx="50" cy="50" r="35" fill="none" stroke="#b91c1c" strokeWidth="15"
                  strokeDasharray={`${dashMangkir} ${C}`} strokeDashoffset={`${offsetMangkir}`}
                  style={{ cursor: 'pointer', transition: 'all 0.4s ease' }}
                  onClick={() => setSelectedSegment(selectedSegment === 3 ? null : 3)}
                />
              )}
            </svg>

            {/* Center Label inside Donut Hole */}
            <div style={{ position: 'absolute', textAlign: 'center' }}>
              <div style={{ fontSize: '1.5rem', fontWeight: 900, color: 'var(--text-main)' }}>
                {selectedSegment !== null ? `${workforceComposition[selectedSegment].percentage}%` : `${hadirPct}%`}
              </div>
              <div style={{ fontSize: '0.72rem', color: 'var(--text-muted)', fontWeight: 700, textTransform: 'uppercase' }}>
                {selectedSegment !== null ? workforceComposition[selectedSegment].name : 'TK Hadir'}
              </div>
            </div>
          </div>

          {/* Minimalist Legend List with Clear Spacing */}
          <div style={{ display: 'flex', flexDirection: 'column', gap: '10px', width: '100%' }}>
            {workforceComposition.map((item, index) => (
              <div
                key={item.name}
                onClick={() => setSelectedSegment(selectedSegment === index ? null : index)}
                style={{
                  display: 'flex',
                  justify: 'space-between',
                  alignItems: 'center',
                  padding: '11px 16px',
                  borderRadius: '10px',
                  cursor: 'pointer',
                  background: selectedSegment === index ? item.bgTag : 'var(--bg-primary)',
                  border: selectedSegment === index ? `1.5px solid ${item.color}` : '1px solid var(--border-color)',
                  transition: 'all 0.2s ease',
                  gap: '16px'
                }}
              >
                {/* Left Side: Dot Indicator + Category Name */}
                <div style={{ display: 'flex', alignItems: 'center', gap: '10px', flexShrink: 0 }}>
                  <span style={{ width: '10px', height: '10px', borderRadius: '50%', background: item.color, display: 'inline-block', flexShrink: 0 }}></span>
                  <span style={{ fontSize: '0.9rem', fontWeight: 800, color: 'var(--text-main)' }}>
                    {item.name}
                  </span>
                </div>

                {/* Right Side: Count + Percentage WITH CLEAR SPACING */}
                <div style={{ textAlign: 'right', display: 'flex', alignItems: 'center', gap: '6px', flexShrink: 0 }}>
                  <span style={{ fontSize: '0.92rem', fontWeight: 900, color: item.color }}>
                    {item.count} Orang
                  </span>
                  <span style={{ fontSize: '0.78rem', color: 'var(--text-muted)', fontWeight: 700 }}>
                    ({item.percentage}%)
                  </span>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* 3. DYNAMIC LOGS OR KEBUN HK SUMMARY TABLE FOR SELECTED DATE */}
      {isReadOnlyMonitor ? (
        <div className="glass-card" style={{ padding: '1.25rem 1.5rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', paddingBottom: '0.75rem', borderBottom: '1px solid var(--border-color)', marginBottom: '1rem', flexWrap: 'wrap', gap: '10px' }}>
            <h3 style={{ fontSize: '1rem', fontWeight: 800, color: 'var(--text-main)' }}>
              Ringkasan HK (Hari Kerja) Per Kebun ({selectedDate})
            </h3>
            <div style={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
              <i className="fa-solid fa-magnifying-glass" style={{ color: 'var(--text-muted)' }}></i>
              <input
                type="text"
                placeholder="Cari Kebun, Distrik, Regional..."
                value={kebunSearch}
                onChange={(e) => setKebunSearch(e.target.value)}
                style={{
                  padding: '6px 12px',
                  borderRadius: '8px',
                  border: '1px solid var(--border-color)',
                  background: 'var(--bg-primary)',
                  color: 'var(--text-main)',
                  fontSize: '0.8rem',
                  outline: 'none'
                }}
              />
            </div>
          </div>

          {filteredKebunSummary.length === 0 ? (
            <div style={{ padding: '2rem 1rem', textAlign: 'center', color: 'var(--text-muted)', fontSize: '0.88rem' }}>
              <p>Tidak ada data kebun yang cocok untuk tanggal <strong>{selectedDate}</strong>.</p>
            </div>
          ) : (
            <div style={{ overflowX: 'auto' }}>
              <table style={{ width: '100%', borderCollapse: 'collapse', fontSize: '0.85rem', textAlign: 'left' }}>
                <thead>
                  <tr style={{ borderBottom: '1px solid var(--border-color)', color: 'var(--text-muted)' }}>
                    <th style={{ padding: '10px 12px', fontWeight: 700 }}>NAMA KEBUN</th>
                    <th style={{ padding: '10px 12px', fontWeight: 700 }}>REGIONAL</th>
                    <th style={{ padding: '10px 12px', fontWeight: 700 }}>DISTRIK</th>
                    <th style={{ padding: '10px 12px', fontWeight: 700, textAlign: 'right' }}>TOTAL TK</th>
                    <th style={{ padding: '10px 12px', fontWeight: 700, textAlign: 'right' }}>HK HADIR</th>
                    <th style={{ padding: '10px 12px', fontWeight: 700, textAlign: 'right' }}>% KEHADIRAN</th>
                  </tr>
                </thead>
                <tbody>
                  {filteredKebunSummary.map((k, idx) => {
                    const pctNum = parseFloat(k.percentage);
                    const isGood = pctNum >= 85;
                    const isWarn = pctNum < 85 && pctNum >= 50;

                    return (
                      <tr key={idx} style={{ borderBottom: '1px solid var(--border-color)' }}>
                        <td style={{ padding: '12px 12px', fontWeight: 800, color: 'var(--text-main)' }}>{k.nama_kebun}</td>
                        <td style={{ padding: '12px 12px', color: 'var(--text-muted)' }}>{k.regional}</td>
                        <td style={{ padding: '12px 12px', color: 'var(--text-muted)' }}>{k.distrik}</td>
                        <td style={{ padding: '12px 12px', color: 'var(--text-main)', textAlign: 'right', fontWeight: 700 }}>{k.totalEmployees} Orang</td>
                        <td style={{ padding: '12px 12px', color: '#15803d', textAlign: 'right', fontWeight: 800 }}>{k.hadirCount} HK</td>
                        <td style={{ padding: '12px 12px', textAlign: 'right' }}>
                          <span
                            style={{
                              padding: '3px 8px',
                              borderRadius: '6px',
                              fontSize: '0.75rem',
                              fontWeight: 800,
                              background: isGood ? 'rgba(21, 128, 61, 0.12)' : isWarn ? 'rgba(180, 83, 9, 0.12)' : 'rgba(185, 28, 28, 0.12)',
                              color: isGood ? '#15803d' : isWarn ? '#b45309' : '#b91c1c',
                              border: isGood ? '1px solid rgba(21, 128, 61, 0.25)' : isWarn ? '1px solid rgba(180, 83, 9, 0.25)' : '1px solid rgba(185, 28, 28, 0.25)'
                            }}
                          >
                            {k.percentage}%
                          </span>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          )}
        </div>
      ) : (
        <div className="glass-card" style={{ padding: '1.25rem 1.5rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', paddingBottom: '0.75rem', borderBottom: '1px solid var(--border-color)', marginBottom: '1rem' }}>
            <h3 style={{ fontSize: '1rem', fontWeight: 800, color: 'var(--text-main)' }}>
              Log Absensi ({selectedDate})
            </h3>
            <Link to="/logs" className="btn btn-secondary" style={{ padding: '6px 12px', fontSize: '0.78rem' }}>
              <i className="fa-solid fa-list-ul"></i> Lihat Semua Log
            </Link>
          </div>

          {filteredLogs.length === 0 ? (
            <div style={{ padding: '2rem 1rem', textAlign: 'center', color: 'var(--text-muted)', fontSize: '0.88rem' }}>
              <i className="fa-solid fa-calendar-xmark" style={{ fontSize: '2rem', marginBottom: '0.5rem', opacity: 0.5 }}></i>
              <p>Belum ada log absensi biometrik terverifikasi untuk tanggal <strong>{selectedDate}</strong>.</p>
            </div>
          ) : (
            <div style={{ overflowX: 'auto' }}>
              <table style={{ width: '100%', borderCollapse: 'collapse', fontSize: '0.85rem', textAlign: 'left' }}>
                <thead>
                  <tr style={{ borderBottom: '1px solid var(--border-color)', color: 'var(--text-muted)' }}>
                    <th style={{ padding: '8px 12px', fontWeight: 700 }}>NAMA</th>
                    <th style={{ padding: '8px 12px', fontWeight: 700 }}>NIK</th>
                    <th style={{ padding: '8px 12px', fontWeight: 700 }}>WAKTU ABSEN</th>
                    <th style={{ padding: '8px 12px', fontWeight: 700 }}>STATUS</th>
                    <th style={{ padding: '8px 12px', fontWeight: 700 }}>AFDELING / KEBUN</th>
                  </tr>
                </thead>
                <tbody>
                  {filteredLogs.slice(0, 10).map((l, idx) => {
                    const empMatch = employees.find((e) => String(e.id) === String(l.employee_id) || (l.nik && String(e.nik) === String(l.nik)));
                    const displayName = l.name || l.employee_name || empMatch?.name || (l.employee_id ? `Karyawan #${l.employee_id}` : '-');
                    const displayNik = l.nik || empMatch?.nik || (l.employee_id ? `ID-${l.employee_id}` : '-');

                    const afdeling = empMatch?.afdeling || l.afdeling || '';
                    const namaKebun = empMatch?.nama_kebun || l.nama_kebun || '';
                    const displayAfdeling =
                      afdeling && namaKebun
                        ? `Afd. ${afdeling} – ${namaKebun}`
                        : afdeling
                        ? `Afd. ${afdeling}`
                        : namaKebun
                        ? namaKebun
                        : l.department || empMatch?.department || 'Kebun / Operational';

                    const isFail = l.status?.includes('GAGAL') || l.status?.includes('REJECT');

                    return (
                      <tr key={l.id || idx} style={{ borderBottom: '1px solid var(--border-color)' }}>
                        <td style={{ padding: '10px 12px', fontWeight: 700 }}>{displayName}</td>
                        <td style={{ padding: '10px 12px', color: 'var(--text-muted)' }}>{displayNik}</td>
                        <td style={{ padding: '10px 12px', fontWeight: 600 }}>
                          {l.timestamp
                            ? new Date(l.timestamp).toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit' }) + ' WIB'
                            : '-'}
                        </td>
                        <td style={{ padding: '10px 12px' }}>
                          <span
                            style={{
                              padding: '3px 8px',
                              borderRadius: '6px',
                              fontSize: '0.75rem',
                              fontWeight: 700,
                              background: isFail ? 'rgba(239, 68, 68, 0.12)' : 'rgba(21, 128, 61, 0.12)',
                              color: isFail ? '#ef4444' : '#15803d',
                              border: isFail ? '1px solid rgba(239, 68, 68, 0.25)' : '1px solid rgba(21, 128, 61, 0.25)'
                            }}
                          >
                            {l.status || 'Hadir'}
                          </span>
                        </td>
                        <td style={{ padding: '10px 12px', color: 'var(--text-muted)' }}>{displayAfdeling}</td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          )}
        </div>
      )}
    </div>
  );
}
