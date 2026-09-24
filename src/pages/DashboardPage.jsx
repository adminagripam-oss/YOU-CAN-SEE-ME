import React, { useState, useMemo } from 'react';
import { useAuth } from '../context/AuthContext';
import { Link } from 'react-router-dom';
import { ChevronLeft, ChevronRight, Users, UserCheck, FileText, Stethoscope, UserX, Clock, Calendar, Building2, ChevronDown, ListFilter } from 'lucide-react';
import { AreaChart, Area, BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, LabelList } from 'recharts';
import { DateRangePicker } from '../components/DateRangePicker';
import { AttendanceDonutChart } from '../components/AttendanceDonutChart';
import { KebunAttendanceBarChart } from '../components/KebunAttendanceBarChart';

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
    const listToUse = availableKebuns.length > 0 ? availableKebuns : allKebunsFromCSV;

    return listToUse.map(kebunName => {
      const kebunEmployees = employees.filter(e => e.nama_kebun === kebunName);
      const kebunEmpIds = new Set(kebunEmployees.map(e => String(e.id)));
      const kebunEmpNiks = new Set(kebunEmployees.map(e => String(e.nik)));

      const kebunGroupedLogs = groupedLogs.filter(g =>
        g.nama_kebun === kebunName || kebunEmpIds.has(String(g.employee_id)) || kebunEmpNiks.has(String(g.nik))
      );
      const hadirCount = kebunGroupedLogs.filter(g => g.finalStatus === 'Hadir').length;
      const totalEmpCount = kebunEmployees.length;
      const percent = totalEmpCount > 0 ? ((hadirCount / totalEmpCount) * 100).toFixed(1) : '0,0';

      return {
        nama_kebun: kebunName,
        regional: KEBUN_TO_REGION[kebunName] || '-',
        totalEmployees: totalEmpCount,
        hadirCount,
        percentage: percent
      };
    }).sort((a, b) => b.hadirCount - a.hadirCount || b.totalEmployees - a.totalEmployees);
  }, [availableKebuns, allKebunsFromCSV, employees, groupedLogs]);

  // Grouping data by date for trend chart (dynamic based on dateRange)
  const trendChartData = useMemo(() => {
    if (!dateRange.start || !dateRange.end) return [];
    const endDate = new Date(dateRange.end);
    const startDate = new Date(dateRange.start);
    if (isNaN(endDate.getTime()) || isNaN(startDate.getTime())) return [];

    const diffTime = Math.abs(endDate - startDate);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    // If single day, show 7-day trend curve leading up to date (loopDays = 6)
    const loopDays = diffDays === 0 ? 6 : Math.min(diffDays, 90);

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
    { name: 'Izin', count: izinCount, percentage: izinPct, color: '#4b5563', bgTag: 'rgba(75, 85, 99, 0.12)' },
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
      {/* ENTERPRISE DASHBOARD HEADER */}
      <div style={{
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        flexWrap: 'wrap',
        gap: '16px',
        width: '100%',
        marginBottom: '0.5rem'
      }}>
        {/* Left Side: Title & Status */}
        <div style={{ display: 'flex', flexDirection: 'column', gap: '6px' }}>
          <h1 style={{ fontSize: '1.5rem', fontWeight: 900, color: 'var(--text-main)', margin: 0 }}>
            Dashboard AgriFace
          </h1>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
            <span style={{ fontSize: '0.65rem', fontWeight: 800, color: '#15803d', background: 'rgba(34, 197, 94, 0.15)', padding: '2px 8px', borderRadius: '12px', display: 'flex', alignItems: 'center', gap: '4px', border: '1px solid rgba(34, 197, 94, 0.3)' }}>
              <span style={{ width: '6px', height: '6px', borderRadius: '50%', background: '#15803d' }} />
              LIVE SYSTEM
            </span>
            <span style={{ fontSize: '0.7rem', fontWeight: 600, color: 'var(--text-muted)', display: 'flex', alignItems: 'center', gap: '4px' }}>
              Terakhir diperbarui: {new Date().toLocaleTimeString('id-ID', { hour: '2-digit', minute: '2-digit' })}
            </span>
          </div>
        </div>

        {/* Right Side: Filters */}
        <div style={{ display: 'flex', alignItems: 'center', gap: '8px', flexWrap: 'wrap' }}>
          {/* Date Picker */}
          <div style={{ background: 'var(--bg-card)', borderRadius: '8px' }}>
            <DateRangePicker dateRange={dateRange} setDateRange={setDateRange} />
          </div>

          {/* Kebun Select */}
          {user?.role !== 'estate_admin' && (
            <div className="custom-select-wrapper" style={{ position: 'relative', display: 'inline-flex', alignItems: 'center' }}>
              <select
                value={selectedKebun}
                onChange={(e) => setSelectedKebun(e.target.value)}
                style={{
                  appearance: 'none',
                  WebkitAppearance: 'none',
                  padding: '8px 36px 8px 12px',
                  borderRadius: '8px',
                  border: '1px solid var(--border-color)',
                  background: 'var(--bg-card)',
                  color: 'var(--text-main)',
                  fontSize: '0.82rem',
                  fontWeight: 600,
                  outline: 'none',
                  cursor: 'pointer',
                  minWidth: '160px'
                }}
              >
                <option value="All">Semua Kebun</option>
                {availableKebuns.map(k => (
                  <option key={k} value={k}>{k}</option>
                ))}
              </select>
              <ChevronDown size={14} style={{ position: 'absolute', right: 10, pointerEvents: 'none', color: 'var(--text-muted)' }} />
            </div>
          )}
        </div>
      </div>

      {/* 1. TOP SECTION: 6 SYMMETRICAL WORKFORCE KPI CARDS */}
      <div className="dashboard-kpi-grid">

        {/* KPI 1: Total Tenaga Kerja */}
        <div className="glass-card" style={{
          position: 'relative',
          overflow: 'hidden',
          marginBottom: 0,
          padding: '1.25rem',
          border: '1px solid var(--border-color)',
          borderTop: '4px solid #3b82f6',
          borderRadius: '8px',
          background: 'var(--bg-card)',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'flex-start',
          justifyContent: 'center',
          minHeight: '124px'
        }}>
          <span style={{ fontSize: '0.75rem', fontWeight: 800, color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>
            Total TK
          </span>
          <div style={{ fontFamily: 'Inter, sans-serif', fontSize: '2.5rem', fontWeight: 900, color: 'var(--text-main)', margin: '0.5rem 0 0.2rem', lineHeight: 1 }}>
            {totalEmployees.toLocaleString('id-ID')}
          </div>
          <span style={{ fontSize: '0.72rem', fontWeight: 600, color: 'var(--text-muted)' }}>
            Seluruh Tenaga Kerja
          </span>
        </div>

        {/* KPI 2: TK Hadir */}
        <div className="glass-card" style={{
          position: 'relative',
          overflow: 'hidden',
          marginBottom: 0,
          padding: '1.25rem',
          border: '1px solid var(--border-color)',
          borderTop: '4px solid #15803d',
          borderRadius: '8px',
          background: 'var(--bg-card)',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'flex-start',
          justifyContent: 'center',
          minHeight: '124px'
        }}>
          <span style={{ fontSize: '0.72rem', fontWeight: 800, color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>
            TK Hadir
          </span>
          <div style={{ fontFamily: 'Inter, sans-serif', fontSize: '2.2rem', fontWeight: 900, color: 'var(--text-main)', margin: '0.5rem 0' }}>
            {verifiedCount.toLocaleString('id-ID')}
          </div>
          <div style={{ display: 'flex' }}>
            {!isMultiDay ? (
              <span style={{ fontSize: '0.68rem', fontWeight: 700, color: '#15803d', background: 'rgba(21,128,61,0.12)', padding: '2px 7px', borderRadius: '4px' }}>
                {hadirPct}% Aktual
              </span>
            ) : (
              <span style={{ fontSize: '0.68rem', fontWeight: 600, color: 'var(--text-muted)' }}>Verified Scan</span>
            )}
          </div>
        </div>

        {/* KPI 3: Izin */}
        <div className="glass-card" style={{
          position: 'relative',
          overflow: 'hidden',
          marginBottom: 0,
          padding: '1.25rem',
          border: '1px solid var(--border-color)',
          borderTop: '4px solid #4b5563',
          borderRadius: '8px',
          background: 'var(--bg-card)',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'flex-start',
          justifyContent: 'center',
          minHeight: '124px'
        }}>
          <span style={{ fontSize: '0.72rem', fontWeight: 800, color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>
            Izin
          </span>
          <div style={{ fontFamily: 'Inter, sans-serif', fontSize: '2.2rem', fontWeight: 900, color: 'var(--text-main)', margin: '0.5rem 0' }}>
            {izinCount}
          </div>
          <div style={{ display: 'flex' }}>
            {!isMultiDay ? (
              <span style={{ fontSize: '0.68rem', fontWeight: 700, color: '#4b5563', background: 'rgba(75,85,99,0.12)', padding: '2px 7px', borderRadius: '4px' }}>
                {izinPct}% Resmi
              </span>
            ) : (
              <span style={{ fontSize: '0.68rem', fontWeight: 600, color: 'var(--text-muted)' }}>Izin Resmi</span>
            )}
          </div>
        </div>

        {/* KPI 4: Sakit */}
        <div className="glass-card" style={{
          position: 'relative',
          overflow: 'hidden',
          marginBottom: 0,
          padding: '1.25rem',
          border: '1px solid var(--border-color)',
          borderTop: '4px solid #b45309',
          borderRadius: '8px',
          background: 'var(--bg-card)',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'flex-start',
          justifyContent: 'center',
          minHeight: '124px'
        }}>
          <span style={{ fontSize: '0.72rem', fontWeight: 800, color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>
            Sakit
          </span>
          <div style={{ fontFamily: 'Inter, sans-serif', fontSize: '2.2rem', fontWeight: 900, color: 'var(--text-main)', margin: '0.5rem 0' }}>
            {sakitCount}
          </div>
          <div style={{ display: 'flex' }}>
            {!isMultiDay ? (
              <span style={{ fontSize: '0.68rem', fontWeight: 700, color: '#b45309', background: 'rgba(180,83,9,0.12)', padding: '2px 7px', borderRadius: '4px' }}>
                {sakitPct}% Aktual
              </span>
            ) : (
              <span style={{ fontSize: '0.68rem', fontWeight: 600, color: 'var(--text-muted)' }}>Ket. Dokter</span>
            )}
          </div>
        </div>

        {/* KPI 5: Mangkir */}
        <div className="glass-card" style={{
          position: 'relative',
          overflow: 'hidden',
          marginBottom: 0,
          padding: '1.25rem',
          border: '1px solid var(--border-color)',
          borderTop: '4px solid #b91c1c',
          borderRadius: '8px',
          background: 'var(--bg-card)',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'flex-start',
          justifyContent: 'center',
          minHeight: '124px'
        }}>
          <span style={{ fontSize: '0.72rem', fontWeight: 800, color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>
            Mangkir
          </span>
          <div style={{ fontFamily: 'Inter, sans-serif', fontSize: '2.2rem', fontWeight: 900, color: 'var(--text-main)', margin: '0.5rem 0' }}>
            {mangkirCount}
          </div>
          <div style={{ display: 'flex' }}>
            {!isMultiDay ? (
              <span style={{ fontSize: '0.68rem', fontWeight: 700, color: '#b91c1c', background: 'rgba(185,28,28,0.12)', padding: '2px 7px', borderRadius: '4px' }}>
                {mangkirPct}% Aktual
              </span>
            ) : (
              <span style={{ fontSize: '0.68rem', fontWeight: 600, color: 'var(--text-muted)' }}>Tanpa Keterangan</span>
            )}
          </div>
        </div>

        {/* KPI 6: Lupa Check-out */}
        <div className="glass-card" style={{
          position: 'relative',
          overflow: 'hidden',
          marginBottom: 0,
          padding: '1.25rem',
          border: '1px solid var(--border-color)',
          borderTop: '4px solid #f97316',
          borderRadius: '8px',
          background: 'var(--bg-card)',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'flex-start',
          justifyContent: 'center',
          minHeight: '124px'
        }}>
          <span style={{ fontSize: '0.72rem', fontWeight: 800, color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>
            Lupa Checkout
          </span>
          <div style={{ fontFamily: 'Inter, sans-serif', fontSize: '2.2rem', fontWeight: 900, color: 'var(--text-main)', margin: '0.5rem 0' }}>
            {lupaCheckoutCount}
          </div>
          <div style={{ display: 'flex' }}>
            {!isMultiDay ? (
              <span style={{ fontSize: '0.68rem', fontWeight: 700, color: '#f97316', background: 'rgba(249,115,22,0.12)', padding: '2px 7px', borderRadius: '4px' }}>
                {lupaCheckoutPct}% Aktual
              </span>
            ) : (
              <span style={{ fontSize: '0.68rem', fontWeight: 600, color: 'var(--text-muted)' }}>Butuh Koreksi</span>
            )}
          </div>
        </div>

      </div>

      {/* 2. CHARTS SECTION — RESPONSIVE 40:60 GRID */}
      <div className="dashboard-charts-grid">
        <div className="glass-card" style={{ padding: '1.25rem 1.25rem 1rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)', marginBottom: 0, display: 'flex', flexDirection: 'column' }}>
          <AttendanceDonutChart
            verifiedCount={verifiedCount}
            izinCount={izinCount}
            sakitCount={sakitCount}
            mangkirCount={mangkirCount}
            totalEmployees={totalEmployees}
            dateStr={dateRange.start === dateRange.end ? dateRange.start : `${dateRange.start} s/d ${dateRange.end}`}
          />
        </div>

        <div className="glass-card" style={{ padding: '1.25rem 1.25rem 1rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)', marginBottom: 0, display: 'flex', flexDirection: 'column' }}>
          <KebunAttendanceBarChart
            kebunSummary={kebunSummary}
            dateStr={dateRange.start === dateRange.end ? dateRange.start : `${dateRange.start} s/d ${dateRange.end}`}
          />
        </div>
      </div>

      {/* 3. DYNAMIC LOGS OR KEBUN HK SUMMARY TABLE FOR SELECTED DATE */}
      {isReadOnlyMonitor ? (
        <div className="glass-card" style={{ padding: '1.25rem 1.5rem', border: '1px solid var(--border-color)', borderRadius: 'var(--radius-lg)', background: 'var(--bg-card)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', paddingBottom: '0.75rem', borderBottom: '1px solid var(--border-color)', marginBottom: '1rem', flexWrap: 'wrap', gap: '10px' }}>
            <h3 style={{ fontSize: '1rem', fontWeight: 800, color: 'var(--text-main)' }}>
              Ringkasan HK (Hari Kerja) Per Kebun ({dateRange.start === dateRange.end ? dateRange.start : `${dateRange.start} s/d ${dateRange.end}`})
            </h3>
            <div style={{ display: 'flex', gap: '16px', alignItems: 'center', paddingBottom: '4px', borderBottom: '1px solid var(--border-color)' }}>
              {['Harian', 'Mingguan', 'Bulanan'].map(view => (
                <button
                  key={view}
                  onClick={() => setChartView(view)}
                  style={{
                    padding: '4px 4px',
                    fontSize: '0.8rem',
                    fontWeight: 700,
                    border: 'none',
                    borderBottom: chartView === view ? '2px solid var(--accent-primary)' : '2px solid transparent',
                    cursor: 'pointer',
                    background: 'transparent',
                    color: chartView === view ? 'var(--text-main)' : 'var(--text-muted)',
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
            <div style={{ width: '100%', height: '230px', marginBottom: '2rem', marginTop: '0.5rem' }}>
              <ResponsiveContainer width="100%" height="100%">
                <AreaChart
                  data={trendChartData}
                  margin={{ top: 25, right: 15, left: -20, bottom: 0 }}
                >
                  <defs>
                    <linearGradient id="chart-glow-fill" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="5%" stopColor="#15803d" stopOpacity={0.4} />
                      <stop offset="95%" stopColor="#15803d" stopOpacity={0} />
                    </linearGradient>
                    <filter id="chart-dot-glow" x="-50%" y="-50%" width="200%" height="200%">
                      <feGaussianBlur stdDeviation="3" result="blur" />
                      <feComposite in="SourceGraphic" in2="blur" operator="over" />
                    </filter>
                    <filter id="chart-line-glow" x="-10%" y="-20%" width="120%" height="140%">
                      <feGaussianBlur stdDeviation="5" result="blur" />
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
                    allowEscapeViewBox={{ x: true, y: true }}
                    wrapperStyle={{ zIndex: 100 }}
                    contentStyle={{ backgroundColor: 'var(--bg-card)', borderColor: 'var(--border-color)', borderRadius: '4px', color: 'var(--text-main)', boxShadow: '0 4px 12px rgba(0,0,0,0.5)', whiteSpace: 'nowrap' }}
                    itemStyle={{ color: '#15803d', fontWeight: 'bold' }}
                    cursor={{ stroke: 'rgba(255,255,255,0.1)', strokeWidth: 1, strokeDasharray: '4 4' }}
                  />
                  <Area
                    type="monotone"
                    dataKey="signups"
                    name="TK Hadir"
                    fill="url(#chart-glow-fill)"
                    stroke="#15803d"
                    strokeWidth={3}
                    isAnimationActive={true}
                    animationDuration={1200}
                    animationEasing="ease-out"
                    filter="url(#chart-line-glow)"
                    dot={{
                      r: 4,
                      fill: "#15803d",
                      strokeWidth: 2,
                      stroke: "var(--bg-card)",
                      filter: "url(#chart-dot-glow)",
                    }}
                    activeDot={{ r: 6, strokeWidth: 3, stroke: "var(--bg-card)" }}
                  >
                    <LabelList
                      dataKey="signups"
                      position="top"
                      formatter={(val) => `${val}`}
                      style={{ fill: 'var(--text-main)', fontSize: 11, fontWeight: 900 }}
                    />
                  </Area>
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
                    allowEscapeViewBox={{ x: true, y: true }}
                    wrapperStyle={{ zIndex: 100 }}
                    contentStyle={{ backgroundColor: 'var(--bg-card)', borderColor: 'var(--border-color)', borderRadius: '4px', color: 'var(--text-main)', boxShadow: '0 4px 12px rgba(0,0,0,0.5)', whiteSpace: 'nowrap' }}
                    itemStyle={{ color: '#10b981', fontWeight: 'bold' }}
                    cursor={{ fill: 'rgba(255,255,255,0.05)' }}
                  />
                  <Bar dataKey="desktop" name="TK Hadir" fill="#15803d" radius={4}>
                    <LabelList
                      dataKey="desktop"
                      position="top"
                      formatter={(val) => `${val}`}
                      style={{ fill: 'var(--text-main)', fontSize: 11, fontWeight: 900 }}
                    />
                  </Bar>
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
                    allowEscapeViewBox={{ x: true, y: true }}
                    wrapperStyle={{ zIndex: 100 }}
                    contentStyle={{ backgroundColor: 'var(--bg-card)', borderColor: 'var(--border-color)', borderRadius: '4px', color: 'var(--text-main)', boxShadow: '0 4px 12px rgba(0,0,0,0.5)', whiteSpace: 'nowrap' }}
                    itemStyle={{ color: '#10b981', fontWeight: 'bold' }}
                    cursor={{ fill: 'rgba(255,255,255,0.05)' }}
                  />
                  <Bar dataKey="desktop" name="TK Hadir" fill="#15803d" radius={4}>
                    <LabelList
                      dataKey="desktop"
                      position="top"
                      formatter={(val) => `${val}`}
                      style={{ fill: 'var(--text-main)', fontSize: 11, fontWeight: 900 }}
                    />
                  </Bar>
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
