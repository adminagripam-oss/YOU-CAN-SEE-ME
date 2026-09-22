import React, { useState, useMemo } from 'react';
import { useAuth } from '../context/AuthContext';
import { Link } from 'react-router-dom';
import { ChevronLeft, ChevronRight } from 'lucide-react';
import { AreaChart, Area, BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import { DateRangePicker } from '../components/DateRangePicker';

const KEBUN_TO_REGION = {
  'Bukit Harapan I': 'Sumut 2',
  'Bukit Harapan II': 'Sumut 2',
  'Parsub': 'Sumut 2',
  'Patogu Janji': 'Sumut 2',
  'Panca Agro Lestari (Pal)': 'Riau 1',
  'Wana Jingga Timur (Wjt)': 'Riau 1',
  'Duta Palma Nusantara (Dpn) I': 'Riau 1',
  'Duta Palma Nusantara (Dpn) II': 'Riau 1',
  'Duta Palma Nusantara (Dpn) III': 'Riau 1',
  'Eluan Mahkota (EMA) - KT': 'Riau 1',
  'Johan Sentosa': 'Riau 1',
  'Palma Inti Lestari (PIL)': 'Riau 1'
};

export default function DashboardPage({ employees = [], logs = [], modelsLoaded }) {
  const { user } = useAuth();

  // Helper to get local date string YYYY-MM-DD in local timezone (e.g. WIB)
  const getLocalDateStr = (d) => {
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  };

  // Date Filter State (default to today YYYY-MM-DD local time)
  const todayStr = getLocalDateStr(new Date());
  const yesterdayStr = (() => {
    const d = new Date();
    d.setDate(d.getDate() - 1);
    return getLocalDateStr(d);
  })();
  const [dateRange, setDateRange] = useState({ start: todayStr, end: todayStr });
  const [selectedSegment, setSelectedSegment] = useState(null);
  const [kebunSearch, setKebunSearch] = useState('');
  const [selectedKebun, setSelectedKebun] = useState('All');
  const [chartView, setChartView] = useState('Harian'); // Harian, Mingguan, Bulanan

  // Pagination States
  const [kebunPage, setKebunPage] = useState(1);
  const [logsPage, setLogsPage] = useState(1);
  const ITEMS_PER_PAGE = 25;

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

  // Filter logs dynamically based on dateRange and selectedKebun
  const filteredLogs = useMemo(() => {
    const kebunEmpIds = new Set(filteredEmployees.map(e => String(e.id)));
    const kebunEmpNiks = new Set(filteredEmployees.map(e => String(e.nik)));

    return logs.filter((l) => {
      if (!l.timestamp) return false;
      const logDate = new Date(l.timestamp);
      const logDateStr = `${logDate.getFullYear()}-${String(logDate.getMonth() + 1).padStart(2, '0')}-${String(logDate.getDate()).padStart(2, '0')}`;

      // Range check
      if (dateRange.start && dateRange.end) {
        if (logDateStr < dateRange.start || logDateStr > dateRange.end) return false;
      } else if (dateRange.start) {
        if (logDateStr !== dateRange.start) return false;
      }

      // Filter by selected kebun
      if (selectedKebun && selectedKebun !== 'All') {
        const empIdStr = String(l.employee_id);
        const nikStr = String(l.nik);
        return kebunEmpIds.has(empIdStr) || kebunEmpNiks.has(nikStr);
      }
      return true;
    });
  }, [logs, dateRange, selectedKebun, filteredEmployees]);

  const isReadOnlyMonitor = user?.role === 'regional_admin' || user?.role === 'headoffice_admin';

  const isMultiDay = useMemo(() => {
    if (!dateRange.start || !dateRange.end) return true;
    return dateRange.start !== dateRange.end;
  }, [dateRange]);

  // Pre-group raw filtered logs by employee_id + date to avoid double counting per day but allow accumulating across days
  const groupedLogs = useMemo(() => {
    const groups = {};
    filteredLogs.forEach(log => {
      if (!log.timestamp) return;
      const logDate = new Date(log.timestamp);
      const logDateStr = `${logDate.getFullYear()}-${String(logDate.getMonth() + 1).padStart(2, '0')}-${String(logDate.getDate()).padStart(2, '0')}`;
      
      const empIdStr = String(log.employee_id);
      const key = `${empIdStr}_${logDateStr}`;

      if (!groups[key]) {
        groups[key] = {
          employee_id: empIdStr,
          date: logDateStr,
          nik: log.nik,
          name: log.name,
          department: log.department,
          nama_kebun: log.nama_kebun || log.kebun || '-',
          afdeling: log.afdeling || '-',
          inLog: null,
          outLog: null,
          keterangan: 'Hadir' // default
        };
      }

      const isCheckOut = log.attendance_type === 'CHECK-OUT' || (log.status && log.status.includes('CHECK-OUT')) || (log.location && log.location.includes('CHECK-OUT'));

      let ket = 'Hadir';
      if (log.status) {
        if (log.status.includes('Izin')) ket = 'Izin';
        else if (log.status.includes('Sakit')) ket = 'Sakit';
        else if (log.status.includes('Mangkir')) ket = 'Mangkir';
        else if (log.status.toLowerCase().includes('lupa_checkout') || log.status.toLowerCase().includes('lupa check-out')) ket = 'Lupa Check-out';
      }

      if (!isCheckOut) {
        groups[key].inLog = log;
        if (ket !== 'Hadir') groups[key].keterangan = ket;
      } else {
        groups[key].outLog = log;
        if (ket !== 'Hadir') groups[key].keterangan = ket;
      }
    });

    // Resolve final status for the employee for this day
    Object.values(groups).forEach(g => {
      // According to rule: 'Lupa Check-out' is counted under 'TK Hadir'
      if (g.keterangan === 'Lupa Check-out') {
        g.finalStatus = 'Hadir';
      } else {
        g.finalStatus = g.keterangan;
      }
    });

    return Object.values(groups);
  }, [filteredLogs]);

  // Grouping data by kebun (for Regional & Head Office dashboards)
  const kebunSummary = useMemo(() => {
    const uniqueKebuns = [...new Set(filteredEmployees.map(e => e.nama_kebun).filter(Boolean))];

    return uniqueKebuns.map(kebunName => {
      const kebunEmployees = filteredEmployees.filter(e => e.nama_kebun === kebunName);
      const kebunEmpIds = new Set(kebunEmployees.map(e => String(e.id)));
      const kebunEmpNiks = new Set(kebunEmployees.map(e => String(e.nik))); // NIK Fallback untuk sinkronisasi offline

      // Hitung HK Hadir (TK Hadir) hari ini dari groupedLogs yang finalStatus-nya 'Hadir'
      const kebunGroupedLogs = groupedLogs.filter(g => kebunEmpIds.has(String(g.employee_id)) || kebunEmpNiks.has(String(g.nik)));
      const hadirCount = kebunGroupedLogs.filter(g => g.finalStatus === 'Hadir').length;

      const totalCount = kebunEmployees.length || 1;
      const percent = ((hadirCount / totalCount) * 100).toFixed(1);

      const firstEmp = kebunEmployees[0];

      return {
        nama_kebun: kebunName,
        regional: KEBUN_TO_REGION[kebunName] || '-',
        totalEmployees: kebunEmployees.length,
        hadirCount,
        percentage: percent
      };
    }).sort((a, b) => b.hadirCount - a.hadirCount);
  }, [filteredEmployees, groupedLogs]);

  // Grouping data by date for trend chart (dynamic based on dateRange)
  const trendChartData = useMemo(() => {
    if (!dateRange.start || !dateRange.end) return [];
    const endDate = new Date(dateRange.end);
    const startDate = new Date(dateRange.start);
    if (isNaN(endDate.getTime()) || isNaN(startDate.getTime())) return [];

    const diffTime = Math.abs(endDate - startDate);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    const loopDays = Math.min(diffDays, 90); // Cap at 90 days to prevent browser freeze if they pick a huge range

    const trendData = [];
    const kebunEmpIds = new Set(filteredEmployees.map(e => String(e.id)));
    const kebunEmpNiks = new Set(filteredEmployees.map(e => String(e.nik)));

    for (let i = loopDays; i >= 0; i--) {
      const d = new Date(endDate);
      d.setDate(d.getDate() - i);

      const year = d.getFullYear();
      const month = String(d.getMonth() + 1).padStart(2, '0');
      const day = String(d.getDate()).padStart(2, '0');
      const dateStr = `${year}-${month}-${day}`;
      const displayDate = `${day}/${month}`;

      const logsForDay = logs.filter((l) => {
        if (!l.timestamp) return false;
        const logDate = new Date(l.timestamp);
        const logDateStr = `${logDate.getFullYear()}-${String(logDate.getMonth() + 1).padStart(2, '0')}-${String(logDate.getDate()).padStart(2, '0')}`;
        if (logDateStr !== dateStr) return false;

        if (selectedKebun && selectedKebun !== 'All') {
          const empIdStr = String(l.employee_id);
          const nikStr = String(l.nik);
          return kebunEmpIds.has(empIdStr) || kebunEmpNiks.has(nikStr);
        }
        return true;
      });

      const groups = {};
      logsForDay.forEach(log => {
        const empIdStr = String(log.employee_id);
        const isCheckOut = log.attendance_type === 'CHECK-OUT' || (log.status && log.status.includes('CHECK-OUT')) || (log.location && log.location.includes('CHECK-OUT'));
        let ket = 'Hadir';
        if (log.status) {
          if (log.status.includes('Izin')) ket = 'Izin';
          else if (log.status.includes('Sakit')) ket = 'Sakit';
          else if (log.status.includes('Mangkir')) ket = 'Mangkir';
          else if (log.status.toLowerCase().includes('lupa_checkout') || log.status.toLowerCase().includes('lupa check-out')) ket = 'Hadir';
        }

        // If employee already had 'Hadir', keep it. Else assign new ket.
        if (!groups[empIdStr] || groups[empIdStr] !== 'Hadir') {
          groups[empIdStr] = ket;
        }
      });

      const hadirCount = Object.values(groups).filter(v => v === 'Hadir').length;

      trendData.push({
        date: displayDate,
        signups: hadirCount
      });
    }
    return trendData;
  }, [logs, dateRange, selectedKebun, filteredEmployees]);

  // Grouping data by Week (W1-W5) for the selected month
  const weeklyChartData = useMemo(() => {
    const targetDate = new Date(dateRange.start);
    if (isNaN(targetDate.getTime())) return [];

    const currentMonth = targetDate.getMonth();
    const currentYear = targetDate.getFullYear();
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate(); // 28, 29, 30, or 31

    const weeklyData = [
      { week: 'W1', desktop: 0 },
      { week: 'W2', desktop: 0 },
      { week: 'W3', desktop: 0 },
      { week: 'W4', desktop: 0 },
      { week: 'W5', desktop: 0 }
    ];

    const kebunEmpIds = new Set(filteredEmployees.map(e => String(e.id)));
    const kebunEmpNiks = new Set(filteredEmployees.map(e => String(e.nik)));

    const logsForMonth = logs.filter((l) => {
      if (!l.timestamp) return false;
      const logDate = new Date(l.timestamp);
      if (logDate.getFullYear() !== currentYear || logDate.getMonth() !== currentMonth) return false;

      if (selectedKebun && selectedKebun !== 'All') {
        const empIdStr = String(l.employee_id);
        const nikStr = String(l.nik);
        return kebunEmpIds.has(empIdStr) || kebunEmpNiks.has(nikStr);
      }
      return true;
    });

    // Process unique attendances per day per employee
    const groups = {};
    logsForMonth.forEach(log => {
      const isCheckOut = log.attendance_type === 'CHECK-OUT' || (log.status && log.status.includes('CHECK-OUT'));
      let ket = 'Hadir';
      if (log.status && (log.status.includes('Izin') || log.status.includes('Sakit') || log.status.includes('Mangkir'))) {
        ket = 'Tidak Hadir';
      }
      if (ket === 'Hadir') {
        const dateStrKey = new Date(log.timestamp).toLocaleDateString();
        groups[`${log.employee_id}_${dateStrKey}`] = new Date(log.timestamp).getDate(); // store the date number (1-31)
      }
    });

    Object.values(groups).forEach(dateNum => {
      if (dateNum >= 1 && dateNum <= 7) weeklyData[0].desktop++;
      else if (dateNum >= 8 && dateNum <= 14) weeklyData[1].desktop++;
      else if (dateNum >= 15 && dateNum <= 21) weeklyData[2].desktop++;
      else if (dateNum >= 22 && dateNum <= 28) weeklyData[3].desktop++;
      else if (dateNum >= 29 && dateNum <= 31) weeklyData[4].desktop++;
    });

    // Optionally remove W5 if there are no days 29-31 in a non-leap February, but usually keeping it constant W1-W5 is better for UI consistency.
    // Let's filter out W5 if daysInMonth < 29
    if (daysInMonth < 29) {
      return weeklyData.slice(0, 4);
    }

    return weeklyData;
  }, [logs, dateRange, selectedKebun, filteredEmployees]);

  // Grouping data by Month (All months this year)
  const monthlyChartData = useMemo(() => {
    const endDate = new Date(dateRange.end || dateRange.start);
    if (isNaN(endDate.getTime())) return [];

    const currentYear = endDate.getFullYear();
    const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

    const monthlyData = [];
    const kebunEmpIds = new Set(filteredEmployees.map(e => String(e.id)));
    const kebunEmpNiks = new Set(filteredEmployees.map(e => String(e.nik)));

    for (let m = 0; m < 12; m++) {
      const logsForMonth = logs.filter((l) => {
        if (!l.timestamp) return false;
        const logDate = new Date(l.timestamp);
        if (logDate.getFullYear() !== currentYear || logDate.getMonth() !== m) return false;

        if (selectedKebun && selectedKebun !== 'All') {
          const empIdStr = String(l.employee_id);
          const nikStr = String(l.nik);
          return kebunEmpIds.has(empIdStr) || kebunEmpNiks.has(nikStr);
        }
        return true;
      });

      const groups = {};
      logsForMonth.forEach(log => {
        const isCheckOut = log.attendance_type === 'CHECK-OUT' || (log.status && log.status.includes('CHECK-OUT'));
        let ket = 'Hadir';
        if (log.status && (log.status.includes('Izin') || log.status.includes('Sakit') || log.status.includes('Mangkir'))) {
          ket = 'Tidak Hadir';
        }
        if (ket === 'Hadir') {
          const dateStrKey = new Date(log.timestamp).toLocaleDateString();
          groups[`${log.employee_id}_${dateStrKey}`] = true;
        }
      });

      const hadirCount = Object.keys(groups).length;

      monthlyData.push({
        month: months[m],
        desktop: hadirCount
      });
    }
    return monthlyData;
  }, [logs, dateRange, selectedKebun, filteredEmployees]);

  const filteredKebunSummary = useMemo(() => {
    return kebunSummary.filter(k => {
      const q = kebunSearch.toLowerCase();
      return (
        k.nama_kebun.toLowerCase().includes(q) ||
        k.regional.toLowerCase().includes(q)
      );
    });
  }, [kebunSummary, kebunSearch]);

  const totalManDays = useMemo(() => {
    if (!dateRange.start || !dateRange.end) {
      if (filteredLogs.length === 0) return totalEmployees;
      const dates = filteredLogs.map(l => new Date(l.timestamp).getTime()).filter(t => !isNaN(t));
      if (dates.length === 0) return totalEmployees;
      const min = Math.min(...dates);
      const max = Math.max(...dates);
      const days = Math.ceil((max - min) / (1000 * 60 * 60 * 24)) + 1;
      return totalEmployees * days;
    }
    const start = new Date(dateRange.start);
    const end = new Date(dateRange.end);
    const diffTime = Math.abs(end - start);
    const days = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
    return totalEmployees * days;
  }, [dateRange, totalEmployees, filteredLogs]);

  // Calculate 100% DYNAMIC real-time attendance counts from groupedLogs
  const verifiedCount = groupedLogs.filter(g => g.finalStatus === 'Hadir').length;
  const izinCount = groupedLogs.filter(g => g.finalStatus === 'Izin').length;
  const sakitCount = groupedLogs.filter(g => g.finalStatus === 'Sakit').length;

  // Count Lupa Check-out (still tracked for display purposes, but already included in verifiedCount)
  const lupaCheckoutCount = groupedLogs.filter(g => g.keterangan === 'Lupa Check-out').length;

  // Mangkir = Total Man Days - (Hadir + Izin + Sakit)
  const mangkirCount = totalManDays > 0 ? Math.max(totalManDays - verifiedCount - izinCount - sakitCount, 0) : 0;

  // Ratios & Percentages
  const totalCountForCalc = totalManDays > 0 ? totalManDays : 1;
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

  // Quick Date Preset Handlers (now delegated to DateRangePicker inside popover)
  // Adjust Date Range by Days (+1 or -1) — kept for potential future use
  const adjustDate = (days) => {
    const current = new Date(dateRange.start);
    if (isNaN(current.getTime())) return;
    current.setDate(current.getDate() + days);
    const newStr = current.toISOString().split('T')[0];
    setDateRange({ start: newStr, end: newStr });
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
          <DateRangePicker dateRange={dateRange} setDateRange={setDateRange} />
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
            {!isMultiDay && (
              <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#15803d', background: 'rgba(21, 128, 61, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(21, 128, 61, 0.25)' }}>
                {hadirPct}%
              </span>
            )}
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
            {!isMultiDay && (
              <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#1d4ed8', background: 'rgba(29, 78, 216, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(29, 78, 216, 0.25)' }}>
                {izinPct}%
              </span>
            )}
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
            {!isMultiDay && (
              <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#b45309', background: 'rgba(180, 83, 9, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(180, 83, 9, 0.25)' }}>
                {sakitPct}%
              </span>
            )}
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
            {!isMultiDay && (
              <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#b91c1c', background: 'rgba(185, 28, 28, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(185, 28, 28, 0.25)' }}>
                {mangkirPct}%
              </span>
            )}
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
            {!isMultiDay && (
              <span style={{ fontSize: '0.7rem', fontWeight: 700, color: '#f97316', background: 'rgba(249, 115, 22, 0.12)', padding: '2px 6px', borderRadius: '6px', border: '1px solid rgba(249, 115, 22, 0.25)' }}>
                {lupaCheckoutPct}%
              </span>
            )}
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
            Tanggal: {dateRange.start === dateRange.end ? dateRange.start : `${dateRange.start} s/d ${dateRange.end}`} ({filteredLogs.length} Log Absensi)
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
                {isMultiDay
                  ? (selectedSegment !== null ? workforceComposition[selectedSegment].count : verifiedCount)
                  : (selectedSegment !== null ? `${workforceComposition[selectedSegment].percentage}%` : `${hadirPct}%`)
                }
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
                    {item.count} {isMultiDay ? 'Hari' : 'Orang'}
                  </span>
                  {!isMultiDay && (
                    <span style={{ fontSize: '0.78rem', color: 'var(--text-muted)', fontWeight: 700 }}>
                      ({item.percentage}%)
                    </span>
                  )}
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
              Ringkasan HK (Hari Kerja) Per Kebun ({dateRange.start === dateRange.end ? dateRange.start : `${dateRange.start} s/d ${dateRange.end}`})
            </h3>
            <div style={{ display: 'flex', gap: '6px', alignItems: 'center', background: 'var(--bg-primary)', padding: '4px', borderRadius: '10px', border: '1px solid var(--border-color)' }}>
              {['Harian', 'Mingguan', 'Bulanan'].map(view => (
                <button
                  key={view}
                  onClick={() => setChartView(view)}
                  style={{
                    padding: '4px 12px',
                    borderRadius: '6px',
                    fontSize: '0.75rem',
                    fontWeight: 700,
                    border: 'none',
                    cursor: 'pointer',
                    background: chartView === view ? '#1e40af' : 'transparent',
                    color: chartView === view ? '#fff' : 'var(--text-muted)',
                    transition: 'all 0.2s ease'
                  }}
                >
                  {view}
                </button>
              ))}
            </div>
          </div>

          {/* DYNAMIC CHARTS BASED ON SELECTED BADGE */}
          {chartView === 'Harian' && trendChartData.length > 0 && (
            <div style={{ width: '100%', height: '220px', marginBottom: '2rem', marginTop: '0.5rem' }}>
              <ResponsiveContainer width="100%" height="100%">
                <AreaChart
                  data={trendChartData}
                  margin={{ top: 20, right: 10, left: -20, bottom: 0 }}
                >
                  <defs>
                    <linearGradient id="chart-glow-fill" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="5%" stopColor="#10b981" stopOpacity={0.45} />
                      <stop offset="95%" stopColor="#10b981" stopOpacity={0} />
                    </linearGradient>
                    <filter id="chart-dot-glow" x="-50%" y="-50%" width="200%" height="200%">
                      <feGaussianBlur stdDeviation="3" result="blur" />
                      <feComposite in="SourceGraphic" in2="blur" operator="over" />
                    </filter>
                    <filter id="chart-line-glow" x="-10%" y="-20%" width="120%" height="140%">
                      <feGaussianBlur stdDeviation="6" result="blur" />
                      <feComposite in="SourceGraphic" in2="blur" operator="over" />
                    </filter>
                  </defs>
                  <CartesianGrid vertical={false} strokeDasharray="3 3" stroke="var(--border-color)" />
                  <XAxis
                    dataKey="date"
                    tick={{ fill: 'var(--text-muted)', fontSize: 11, fontWeight: 'bold', fontFamily: 'inherit' }}
                    tickLine={false}
                    axisLine={{ stroke: 'var(--border-color)' }}
                  />
                  <YAxis
                    tick={{ fill: 'var(--text-muted)', fontSize: 11, fontWeight: 'bold', fontFamily: 'inherit' }}
                    tickLine={false}
                    axisLine={false}
                  />
                  <Tooltip
                    contentStyle={{ backgroundColor: 'var(--bg-card)', borderColor: 'var(--border-color)', borderRadius: '8px', color: 'var(--text-main)', boxShadow: '0 4px 12px rgba(0,0,0,0.5)' }}
                    itemStyle={{ color: '#10b981', fontWeight: 'bold' }}
                    cursor={{ stroke: 'rgba(255,255,255,0.1)', strokeWidth: 1, strokeDasharray: '4 4' }}
                  />
                  <Area
                    type="natural"
                    dataKey="signups"
                    name="TK Hadir"
                    fill="url(#chart-glow-fill)"
                    stroke="#10b981"
                    strokeWidth={3}
                    filter="url(#chart-line-glow)"
                    dot={{
                      r: 4,
                      fill: "#10b981",
                      strokeWidth: 2,
                      stroke: "var(--bg-card)",
                      filter: "url(#chart-dot-glow)",
                    }}
                    activeDot={{ r: 6, strokeWidth: 3, stroke: "var(--bg-card)" }}
                  />
                </AreaChart>
              </ResponsiveContainer>
            </div>
          )}

          {chartView === 'Mingguan' && weeklyChartData.length > 0 && (
            <div style={{ width: '100%', height: '220px', marginBottom: '2rem', marginTop: '0.5rem' }}>
              <ResponsiveContainer width="100%" height="100%">
                <BarChart data={weeklyChartData} margin={{ top: 20, right: 10, left: -20, bottom: 0 }}>
                  <CartesianGrid vertical={false} strokeDasharray="3 3" stroke="var(--border-color)" />
                  <XAxis
                    dataKey="week"
                    tick={{ fill: 'var(--text-muted)', fontSize: 11, fontWeight: 'bold', fontFamily: 'inherit' }}
                    tickLine={false}
                    axisLine={{ stroke: 'var(--border-color)' }}
                  />
                  <YAxis tick={{ fill: 'var(--text-muted)', fontSize: 11, fontWeight: 'bold', fontFamily: 'inherit' }} tickLine={false} axisLine={false} />
                  <Tooltip
                    contentStyle={{ backgroundColor: 'var(--bg-card)', borderColor: 'var(--border-color)', borderRadius: '8px', color: 'var(--text-main)', boxShadow: '0 4px 12px rgba(0,0,0,0.5)' }}
                    itemStyle={{ color: '#10b981', fontWeight: 'bold' }}
                    cursor={{ fill: 'rgba(255,255,255,0.05)' }}
                  />
                  <Bar dataKey="desktop" name="TK Hadir" fill="#10b981" radius={4} />
                </BarChart>
              </ResponsiveContainer>
            </div>
          )}

          {chartView === 'Bulanan' && monthlyChartData.length > 0 && (
            <div style={{ width: '100%', height: '220px', marginBottom: '2rem', marginTop: '0.5rem' }}>
              <ResponsiveContainer width="100%" height="100%">
                <BarChart data={monthlyChartData} margin={{ top: 20, right: 10, left: -20, bottom: 0 }}>
                  <CartesianGrid vertical={false} strokeDasharray="3 3" stroke="var(--border-color)" />
                  <XAxis
                    dataKey="month"
                    tick={{ fill: 'var(--text-muted)', fontSize: 11, fontWeight: 'bold', fontFamily: 'inherit' }}
                    tickLine={false}
                    axisLine={{ stroke: 'var(--border-color)' }}
                  />
                  <YAxis tick={{ fill: 'var(--text-muted)', fontSize: 11, fontWeight: 'bold', fontFamily: 'inherit' }} tickLine={false} axisLine={false} />
                  <Tooltip
                    contentStyle={{ backgroundColor: 'var(--bg-card)', borderColor: 'var(--border-color)', borderRadius: '8px', color: 'var(--text-main)', boxShadow: '0 4px 12px rgba(0,0,0,0.5)' }}
                    itemStyle={{ color: '#10b981', fontWeight: 'bold' }}
                    cursor={{ fill: 'rgba(255,255,255,0.05)' }}
                  />
                  <Bar dataKey="desktop" name="TK Hadir" fill="#10b981" radius={4} />
                </BarChart>
              </ResponsiveContainer>
            </div>
          )}

          {filteredKebunSummary.length === 0 ? (
            <div style={{ padding: '2rem 1rem', textAlign: 'center', color: 'var(--text-muted)', fontSize: '0.88rem' }}>
              <p>Tidak ada data kebun yang cocok untuk tanggal <strong>{dateRange.start === dateRange.end ? dateRange.start : `${dateRange.start} - ${dateRange.end}`}</strong>.</p>
            </div>
          ) : (
            <>
              <div style={{ overflowX: 'auto' }}>
                <table style={{ width: '100%', borderCollapse: 'collapse', fontSize: '0.85rem', textAlign: 'left' }}>
                  <thead>
                    <tr style={{ borderBottom: '1px solid var(--border-color)', color: 'var(--text-muted)' }}>
                      <th style={{ padding: '10px 12px', fontWeight: 700 }}>NAMA KEBUN</th>
                      <th style={{ padding: '10px 12px', fontWeight: 700 }}>REGIONAL</th>
                      <th style={{ padding: '10px 12px', fontWeight: 700, textAlign: 'right' }}>TOTAL TK</th>
                      <th style={{ padding: '10px 12px', fontWeight: 700, textAlign: 'right' }}>HK HADIR</th>
                      <th style={{ padding: '10px 12px', fontWeight: 700, textAlign: 'right' }}>% KEHADIRAN</th>
                    </tr>
                  </thead>
                  <tbody>
                    {filteredKebunSummary.slice((kebunPage - 1) * ITEMS_PER_PAGE, kebunPage * ITEMS_PER_PAGE).map((k, idx) => {
                      const pctNum = parseFloat(k.percentage);
                      const isGood = pctNum >= 85;
                      const isWarn = pctNum < 85 && pctNum >= 50;

                      return (
                        <tr key={idx} style={{ borderBottom: '1px solid var(--border-color)' }}>
                          <td style={{ padding: '12px 12px', fontWeight: 800, color: 'var(--text-main)' }}>{k.nama_kebun}</td>
                          <td style={{ padding: '12px 12px', color: 'var(--text-muted)' }}>{k.regional}</td>
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
              {filteredKebunSummary.length > ITEMS_PER_PAGE && (
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginTop: '1rem', paddingTop: '0.75rem', borderTop: '1px solid var(--border-color)', fontSize: '0.8rem', color: 'var(--text-muted)' }}>
                  <div>
                    Halaman {kebunPage} dari {Math.ceil(filteredKebunSummary.length / ITEMS_PER_PAGE)}
                  </div>
                  <div style={{ display: 'flex', gap: '6px' }}>
                    <button
                      onClick={() => setKebunPage(p => Math.max(p - 1, 1))}
                      disabled={kebunPage === 1}
                      style={{
                        padding: '4px 10px',
                        borderRadius: '6px',
                        border: '1px solid var(--border-color)',
                        background: kebunPage === 1 ? 'var(--bg-primary)' : 'var(--bg-card)',
                        color: kebunPage === 1 ? 'var(--text-muted)' : 'var(--text-main)',
                        cursor: kebunPage === 1 ? 'not-allowed' : 'pointer',
                        fontWeight: 600,
                        opacity: kebunPage === 1 ? 0.6 : 1
                      }}
                    >
                      Prev
                    </button>
                    <button
                      onClick={() => setKebunPage(p => Math.min(p + 1, Math.ceil(filteredKebunSummary.length / ITEMS_PER_PAGE)))}
                      disabled={kebunPage >= Math.ceil(filteredKebunSummary.length / ITEMS_PER_PAGE)}
                      style={{
                        padding: '4px 10px',
                        borderRadius: '6px',
                        border: '1px solid var(--border-color)',
                        background: kebunPage >= Math.ceil(filteredKebunSummary.length / ITEMS_PER_PAGE) ? 'var(--bg-primary)' : 'var(--bg-card)',
                        color: kebunPage >= Math.ceil(filteredKebunSummary.length / ITEMS_PER_PAGE) ? 'var(--text-muted)' : 'var(--text-main)',
                        cursor: kebunPage >= Math.ceil(filteredKebunSummary.length / ITEMS_PER_PAGE) ? 'not-allowed' : 'pointer',
                        fontWeight: 600,
                        opacity: kebunPage >= Math.ceil(filteredKebunSummary.length / ITEMS_PER_PAGE) ? 0.6 : 1
                      }}
                    >
                      Next
                    </button>
                  </div>
                </div>
              )}
            </>
          )}
        </div>
      ) : (
        <div className="glass-card" style={{ padding: '1.25rem 1.5rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', paddingBottom: '0.75rem', borderBottom: '1px solid var(--border-color)', marginBottom: '1rem' }}>
            <h3 style={{ fontSize: '1rem', fontWeight: 800, color: 'var(--text-main)' }}>
              Log Absensi ({dateRange.start === dateRange.end ? dateRange.start : `${dateRange.start} s/d ${dateRange.end}`})
            </h3>
            <Link to="/logs" className="btn btn-secondary" style={{ padding: '6px 12px', fontSize: '0.78rem' }}>
              <i className="fa-solid fa-list-ul"></i> Lihat Semua Log
            </Link>
          </div>

          {filteredLogs.length === 0 ? (
            <div style={{ padding: '2rem 1rem', textAlign: 'center', color: 'var(--text-muted)', fontSize: '0.88rem' }}>
              <i className="fa-solid fa-calendar-xmark" style={{ fontSize: '2rem', marginBottom: '0.5rem', opacity: 0.5 }}></i>
              <p>Belum ada log absensi biometrik terverifikasi untuk tanggal <strong>{dateRange.start === dateRange.end ? dateRange.start : `${dateRange.start} - ${dateRange.end}`}</strong>.</p>
            </div>
          ) : (
            <>
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
                    {filteredLogs.slice((logsPage - 1) * ITEMS_PER_PAGE, logsPage * ITEMS_PER_PAGE).map((l, idx) => {
                      const empMatch = employees.find((e) => String(e.id) === String(l.employee_id) || (l.nik && String(e.nik) === String(l.nik)));
                      const displayName = l.name || l.employee_name || empMatch?.name || (l.employee_id ? `Karyawan #${l.employee_id}` : '-');
                      const displayNik = l.nik || empMatch?.nik || (l.employee_id ? `ID-${l.employee_id}` : '-');

                      const afdeling = l.afdeling || empMatch?.afdeling || '';
                      const namaKebun = l.nama_kebun || l.kebun || empMatch?.nama_kebun || '';
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

              {/* Pagination Controls for Logs */}
              {filteredLogs.length > ITEMS_PER_PAGE && (
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginTop: '1rem', paddingTop: '0.75rem', borderTop: '1px solid var(--border-color)', fontSize: '0.8rem', color: 'var(--text-muted)' }}>
                  <div>
                    Halaman {logsPage} dari {Math.ceil(filteredLogs.length / ITEMS_PER_PAGE)}
                  </div>
                  <div style={{ display: 'flex', gap: '6px' }}>
                    <button
                      onClick={() => setLogsPage(p => Math.max(p - 1, 1))}
                      disabled={logsPage === 1}
                      style={{
                        padding: '4px 10px',
                        borderRadius: '6px',
                        border: '1px solid var(--border-color)',
                        background: logsPage === 1 ? 'var(--bg-primary)' : 'var(--bg-card)',
                        color: logsPage === 1 ? 'var(--text-muted)' : 'var(--text-main)',
                        cursor: logsPage === 1 ? 'not-allowed' : 'pointer',
                        fontWeight: 600,
                        opacity: logsPage === 1 ? 0.6 : 1
                      }}
                    >
                      Prev
                    </button>
                    <button
                      onClick={() => setLogsPage(p => Math.min(p + 1, Math.ceil(filteredLogs.length / ITEMS_PER_PAGE)))}
                      disabled={logsPage >= Math.ceil(filteredLogs.length / ITEMS_PER_PAGE)}
                      style={{
                        padding: '4px 10px',
                        borderRadius: '6px',
                        border: '1px solid var(--border-color)',
                        background: logsPage >= Math.ceil(filteredLogs.length / ITEMS_PER_PAGE) ? 'var(--bg-primary)' : 'var(--bg-card)',
                        color: logsPage >= Math.ceil(filteredLogs.length / ITEMS_PER_PAGE) ? 'var(--text-muted)' : 'var(--text-main)',
                        cursor: logsPage >= Math.ceil(filteredLogs.length / ITEMS_PER_PAGE) ? 'not-allowed' : 'pointer',
                        fontWeight: 600,
                        opacity: logsPage >= Math.ceil(filteredLogs.length / ITEMS_PER_PAGE) ? 0.6 : 1
                      }}
                    >
                      Next
                    </button>
                  </div>
                </div>
              )}

            </>
          )}
        </div>
      )}
    </div>
  );
}
