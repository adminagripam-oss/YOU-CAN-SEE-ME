import React, { useState, useMemo } from 'react';
import { Search, Filter, ChevronLeft, ChevronRight, ChevronDown } from 'lucide-react';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "../components/ui/table";
import { PeriodePicker } from "../components/PeriodePicker";
import { STATUS, STATUS_ORDER, getStatus, resolveLogStatus, mergeStatus } from '../utils/attendanceStatus';

const MONTH_NAMES = [
  'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni',
  'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'
];

// Helper parsing tanggal defensif dari null/undefined/string tidak teratur
const getLogDateInfo = (ts) => {
  if (!ts) return null;
  const d = new Date(ts);
  if (isNaN(d.getTime())) return null;
  const y = d.getFullYear();
  const m = String(d.getMonth() + 1).padStart(2, '0');
  const day = d.getDate();
  return { year: y, month: m, day, yearMonthStr: `${y}-${m}` };
};

function StatusLegend() {
  return (
    <div className="flex flex-wrap items-center gap-x-5 gap-y-2 px-4 py-3 border-t border-[var(--border-color)] bg-[var(--bg-card-solid)]">
      <span className="text-[11px] font-bold uppercase tracking-wider text-[var(--text-muted)]">Keterangan</span>
      {STATUS_ORDER.map((k) => {
        const s = STATUS[k];
        return (
          <span key={k} className="inline-flex items-center gap-1.5">
            <span
              style={{
                display: 'inline-flex',
                width: '22px',
                height: '22px',
                alignItems: 'center',
                justifyContent: 'center',
                borderRadius: '4px',
                fontSize: '9px',
                fontWeight: 700,
                backgroundColor: s.bg,
                color: s.fg,
                lineHeight: 1,
                flexShrink: 0,
              }}
            >
              {s.short}
            </span>
            <span className="text-xs text-[var(--text-main)] font-medium">
              {s.label}
            </span>
          </span>
        );
      })}
      <span className="ml-auto text-[11px] text-[var(--text-muted)] font-medium whitespace-nowrap">
        Geser ke kanan untuk tanggal lainnya →
      </span>
    </div>
  );
}

function AttendanceCell({ code, dateLabel, nama }) {
  const s = getStatus(code);
  return (
    <span
      title={`${nama} \u00B7 ${dateLabel} \u00B7 ${s.label}`}
      style={{
        display: 'inline-flex',
        width: '28px',
        height: '28px',
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: '5px',
        fontSize: '10px',
        fontWeight: 700,
        backgroundColor: s.bg,
        color: s.fg,
        lineHeight: 1,
        flexShrink: 0,
      }}
    >
      {s.short}
    </span>
  );
}

export default function MonitoringAbsensiPage({ employees = [], logs = [] }) {
  const [searchQuery, setSearchQuery] = useState('');
  const [filterKebun, setFilterKebun] = useState('');
  const [filterAfdeling, setFilterAfdeling] = useState('');


  // Periode state — single source of truth: { month: 0-11, year: number }
  const today = new Date();
  const [periode, setPeriode] = useState({ month: today.getMonth(), year: today.getFullYear() });

  // Derive selectedMonth string (YYYY-MM) from periode for table logic
  const selectedMonth = `${periode.year}-${String(periode.month + 1).padStart(2, '0')}`;

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 25;

  const handleApplyPeriode = (p) => {
    setPeriode(p);
    setCurrentPage(1);
  };


  // Safely extract unique Kebuns (Sorted A-Z)
  const uniqueKebuns = useMemo(() => {
    if (!Array.isArray(employees)) return [];
    return [...new Set(employees.map(e => e?.nama_kebun).filter(Boolean))].sort((a, b) => String(a).localeCompare(String(b)));
  }, [employees]);

  // Safely extract unique Afdelings (Sorted A-Z)
  const uniqueAfdelings = useMemo(() => {
    if (!Array.isArray(employees)) return [];
    return [...new Set(employees.map(e => e?.afdeling).filter(Boolean))].sort((a, b) => String(a).localeCompare(String(b)));
  }, [employees]);

  // Calculate days in selected month
  const daysInMonth = useMemo(() => {
    if (!selectedMonth) return [];
    const parts = selectedMonth.split('-');
    const year = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10);
    if (isNaN(year) || isNaN(month)) return [];
    const daysCount = new Date(year, month, 0).getDate();
    return Array.from({ length: daysCount }, (_, i) => i + 1);
  }, [selectedMonth]);

  // Process logs & attendance per employee
  const processedData = useMemo(() => {
    const safeEmployees = Array.isArray(employees) ? employees : [];
    const safeLogs = Array.isArray(logs) ? logs : [];

    if (!selectedMonth) return [];

    const attendanceMap = {};

    safeLogs.forEach(log => {
      if (!log) return;
      const dateInfo = getLogDateInfo(log.timestamp);
      if (!dateInfo || dateInfo.yearMonthStr !== selectedMonth) return;

      const incoming = resolveLogStatus(log.status);
      const keys = [log.employee_id, log.nik].filter(Boolean);
      keys.forEach(k => {
        if (!attendanceMap[k]) attendanceMap[k] = {};
        const { day } = dateInfo;
        attendanceMap[k][day] = attendanceMap[k][day]
          ? mergeStatus(attendanceMap[k][day], incoming)
          : incoming;
      });
    });

    return safeEmployees.map(emp => {
      if (!emp) return null;

      const empDays = (
        (emp.id && attendanceMap[emp.id]) ||
        (emp.nik && attendanceMap[emp.nik]) ||
        {}
      );

      const attendanceRecord = {};
      daysInMonth.forEach(day => {
        attendanceRecord[day] = empDays[day] || 'NONE';
      });

      return {
        id: emp.id || emp.nik || Math.random().toString(),
        nik: emp.nik || '-',
        name: emp.name || emp.nama || '-',
        nama_kebun: emp.nama_kebun || '-',
        afdeling: emp.afdeling || '-',
        has_master_biometric: !!emp.has_master_biometric,
        ...attendanceRecord
      };
    }).filter(Boolean);
  }, [employees, logs, selectedMonth, daysInMonth]);

  // Filter Data
  const filteredData = useMemo(() => {
    const q = searchQuery.trim().toLowerCase();
    return processedData.filter(item => {
      const matchSearch = !q || (
        String(item.name).toLowerCase().includes(q) ||
        String(item.nik).toLowerCase().includes(q) ||
        String(item.nama_kebun).toLowerCase().includes(q) ||
        String(item.afdeling).toLowerCase().includes(q)
      );
      const matchKebun = !filterKebun || item.nama_kebun === filterKebun;
      const matchAfdeling = !filterAfdeling || item.afdeling === filterAfdeling;
      return matchSearch && matchKebun && matchAfdeling;
    });
  }, [processedData, searchQuery, filterKebun, filterAfdeling]);

  // UI Data Sorting Standard: ALWAYS sort array alphabetically (A-Z) by primary display name before rendering
  const sortedData = useMemo(() => {
    return [...filteredData].sort((a, b) => String(a.name).localeCompare(String(b.name)));
  }, [filteredData]);

  // Pagination Logic
  const totalPages = Math.ceil(sortedData.length / itemsPerPage) || 1;
  const paginatedData = useMemo(() => {
    const start = (currentPage - 1) * itemsPerPage;
    return sortedData.slice(start, start + itemsPerPage);
  }, [sortedData, currentPage, itemsPerPage]);

  const handleFilterChange = (setter, val) => {
    setter(val);
    setCurrentPage(1);
  };

  return (
    <div className="w-full h-full p-0 flex flex-col page-container--logs">
      {/* Scoped Styles for Mobile Reflow & Sticky Column Optimization */}
      <style>{`
        :root, [data-theme="light"] {
          --bg-card-solid: #ffffff;
          --bg-hover-solid: #f1f5f9;
        }
        [data-theme="dark"] {
          --bg-card-solid: #1e1e1e;
          --bg-hover-solid: #2a2d3d;
        }

        .filter-input-wrapper {
          position: relative;
          display: inline-flex;
          align-items: center;
        }

        .filter-input-icon-left {
          position: absolute;
          left: 12px;
          top: 50%;
          transform: translateY(-50%);
          pointer-events: none;
          z-index: 10;
          color: var(--text-muted);
        }

        .filter-input-icon-right {
          position: absolute;
          right: 12px;
          top: 50%;
          transform: translateY(-50%);
          pointer-events: none;
          z-index: 10;
          color: var(--text-muted);
        }

        .filter-input-field {
          padding-left: 38px !important;
          padding-right: 14px !important;
        }

        .filter-select-field {
          padding-left: 38px !important;
          padding-right: 32px !important;
        }

        /* Sticky Columns Solid Hover Rules */
        .monitoring-table-grid {
          border-collapse: separate;
          border-spacing: 0;
          width: max-content;
          min-width: 100%;
        }

        /* Desktop Sticky Layout (>= 768px): All 4 columns sticky */
        @media (min-width: 768px) {
          .col-sticky-nik { position: sticky; left: 0; width: 140px; min-width: 140px; }
          .col-sticky-nama { position: sticky; left: 140px; width: 180px; min-width: 180px; }
          .col-sticky-kebun { position: sticky; left: 320px; width: 140px; min-width: 140px; }
          .col-sticky-afdeling { position: sticky; left: 460px; width: 100px; min-width: 100px; box-shadow: 4px 0 8px -2px rgba(0,0,0,0.12); }
        }

        /* Mobile Layout (< 768px): Reflow sticky columns so only NAMA KARYAWAN is sticky, freeing up 80% screen width for date matrix! */
        @media (max-width: 767px) {
          .col-sticky-nik { position: static !important; width: 110px !important; min-width: 110px !important; }
          .col-sticky-nama { position: sticky !important; left: 0 !important; width: 140px !important; min-width: 140px !important; box-shadow: 4px 0 8px -2px rgba(0,0,0,0.12) !important; }
          .col-sticky-kebun { position: static !important; width: 110px !important; min-width: 110px !important; }
          .col-sticky-afdeling { position: static !important; width: 85px !important; min-width: 85px !important; box-shadow: none !important; }

          .filter-input-wrapper-mobile-full {
            width: 100% !important;
            flex: 1 1 100% !important;
          }
          .filter-input-wrapper-mobile-half {
            flex: 1 1 calc(50% - 6px) !important;
            min-width: 130px !important;
          }
        }

        .monitoring-table-grid th.sticky-col {
          background-color: var(--bg-card-solid) !important;
          z-index: 30 !important;
        }

        .monitoring-table-grid td.sticky-col {
          background-color: var(--bg-card-solid) !important;
          z-index: 20 !important;
        }

        .monitoring-table-grid tr:hover td {
          background-color: var(--bg-hover-solid) !important;
        }

        .monitoring-table-grid tr:hover td.sticky-col {
          background-color: var(--bg-hover-solid) !important;
        }
      `}</style>

      {/* PAGE HEADER: OUTSIDE CARD, NO BORDER */}
      <div className="no-print mb-4 sm:mb-5 px-1">
        <span className="text-[11px] font-bold tracking-wider text-[var(--text-muted)] uppercase block mb-1">KEHADIRAN</span>
        <h2 className="text-xl sm:text-2xl font-black text-[var(--text-main)] m-0 leading-tight">
          Monitoring Perhari Absensi
        </h2>
      </div>

      {/* UNIFIED MAIN CARD: TOOLBAR + TABLE MATRIX + LEGEND */}
      <div className="bg-[var(--bg-card)] border border-[var(--border-color)] sm:rounded-xl shadow-xs overflow-hidden flex-1 flex flex-col" style={{ padding: 0 }}>
        {/* Top Filter Toolbar Header inside Unified Card */}
        <div className="p-4 border-b border-[var(--border-color)] bg-[var(--bg-card-solid)] flex flex-wrap items-center gap-3 no-print">

          {/* 1. PeriodePicker — Dialog + native select Bulan & Tahun */}
          <PeriodePicker value={periode} onApply={handleApplyPeriode} />

          {/* 2. Search Input */}
          <div className="filter-input-wrapper filter-input-wrapper-mobile-full flex-1 min-w-[200px]">
            <Search className="filter-input-icon-left w-4 h-4" />
            <input
              type="text"
              placeholder="Cari NIK, Nama..."
              value={searchQuery}
              onChange={(e) => handleFilterChange(setSearchQuery, e.target.value)}
              className="filter-input-field w-full py-2 bg-[var(--bg-input)] border border-[var(--border-color)] rounded-lg text-[var(--text-main)] text-sm focus:outline-none focus:border-[var(--accent-primary)] focus:ring-1 focus:ring-[var(--accent-primary)] shadow-xs"
            />
          </div>

          {/* 3. Kebun Filter Dropdown */}
          <div className="filter-input-wrapper filter-input-wrapper-mobile-half flex-none min-w-[150px]">
            <Filter className="filter-input-icon-left w-4 h-4" />
            <select
              value={filterKebun}
              onChange={(e) => handleFilterChange(setFilterKebun, e.target.value)}
              className="filter-select-field w-full py-2 bg-[var(--bg-input)] border border-[var(--border-color)] rounded-lg text-[var(--text-main)] text-sm font-medium appearance-none focus:outline-none focus:border-[var(--accent-primary)] focus:ring-1 focus:ring-[var(--accent-primary)] cursor-pointer shadow-xs"
            >
              <option value="">Semua Kebun</option>
              {uniqueKebuns.map(k => (
                <option key={k} value={k}>{k}</option>
              ))}
            </select>
            <ChevronDown className="filter-input-icon-right w-4 h-4" />
          </div>

          {/* 4. Afdeling Filter Dropdown */}
          <div className="filter-input-wrapper filter-input-wrapper-mobile-half flex-none min-w-[150px]">
            <Filter className="filter-input-icon-left w-4 h-4" />
            <select
              value={filterAfdeling}
              onChange={(e) => handleFilterChange(setFilterAfdeling, e.target.value)}
              className="filter-select-field w-full py-2 bg-[var(--bg-input)] border border-[var(--border-color)] rounded-lg text-[var(--text-main)] text-sm font-medium appearance-none focus:outline-none focus:border-[var(--accent-primary)] focus:ring-1 focus:ring-[var(--accent-primary)] cursor-pointer shadow-xs"
            >
              <option value="">Semua Afdeling</option>
              {uniqueAfdelings.map(a => (
                <option key={a} value={a}>{a}</option>
              ))}
            </select>
            <ChevronDown className="filter-input-icon-right w-4 h-4" />
          </div>

        </div>

        <div className="w-full overflow-x-auto relative flex-1" style={{ WebkitOverflowScrolling: 'touch' }}>
          <Table className="monitoring-table-grid compact-mobile-table">
            <TableHeader>
              <TableRow className="border-b border-[var(--border-color)] bg-[var(--bg-card-solid)]">
                {/* COLUMN 1: NIK */}
                <TableHead className="sticky-col col-sticky-nik text-[11px] font-bold uppercase tracking-wider text-[var(--text-muted)] py-3 px-3">
                  NIK
                </TableHead>

                {/* COLUMN 2: NAMA KARYAWAN */}
                <TableHead className="sticky-col col-sticky-nama text-[11px] font-bold uppercase tracking-wider text-[var(--text-muted)] py-3 px-3 border-r border-[var(--border-color)]">
                  NAMA KARYAWAN
                </TableHead>

                {/* COLUMN 3: KEBUN */}
                <TableHead className="sticky-col col-sticky-kebun text-[11px] font-bold uppercase tracking-wider text-[var(--text-muted)] py-3 px-3">
                  KEBUN
                </TableHead>

                {/* COLUMN 4: AFDELING */}
                <TableHead className="sticky-col col-sticky-afdeling text-[11px] font-bold uppercase tracking-wider text-[var(--text-muted)] py-3 px-3 border-r border-[var(--border-color)]">
                  AFDELING
                </TableHead>

                {daysInMonth.map(day => {
                  const isSun = new Date(periode.year, periode.month, day).getDay() === 0;
                  return (
                    <TableHead
                      key={day}
                      className={`w-[36px] min-w-[36px] text-center px-[2px] py-3 text-[11px] font-bold ${isSun ? 'text-[var(--text-muted)] opacity-40' : 'text-[var(--text-muted)]'}`}
                    >
                      {day}
                    </TableHead>
                  );
                })}
              </TableRow>
            </TableHeader>

            <TableBody>
              {paginatedData.length === 0 ? (
                <TableRow>
                  <TableCell
                    colSpan={4 + daysInMonth.length}
                    className="text-center py-12 text-[var(--text-muted)] font-medium"
                  >
                    Tidak ada data absensi untuk filter yang dipilih.
                  </TableCell>
                </TableRow>
              ) : (
                paginatedData.map((row) => (
                  <TableRow
                    key={row.id}
                    className="border-b border-[var(--border-color)] transition-colors group"
                  >
                    {/* CELL 1: NIK */}
                    <TableCell className="sticky-col col-sticky-nik font-mono text-xs font-bold text-[var(--accent-success)] py-3 px-3 truncate">
                      {row.nik}
                    </TableCell>

                    {/* CELL 2: NAMA KARYAWAN */}
                    <TableCell className="sticky-col col-sticky-nama text-sm font-bold text-[var(--text-main)] py-3 px-3 border-r border-[var(--border-color)] truncate">
                      {row.name}
                    </TableCell>

                    {/* CELL 3: KEBUN */}
                    <TableCell className="sticky-col col-sticky-kebun text-xs text-[var(--text-muted)] font-medium py-3 px-3 truncate">
                      {row.nama_kebun}
                    </TableCell>

                    {/* CELL 4: AFDELING */}
                    <TableCell className="sticky-col col-sticky-afdeling text-xs text-[var(--text-muted)] font-medium py-3 px-3 border-r border-[var(--border-color)] truncate">
                      {row.afdeling}
                    </TableCell>

                    {daysInMonth.map(day => (
                      <TableCell
                        key={day}
                        className="p-[2px]"
                        style={{ width: '36px', minWidth: '36px', textAlign: 'center' }}
                      >
                        <AttendanceCell
                          code={row[day]}
                          dateLabel={`${day} ${MONTH_NAMES[periode.month]} ${periode.year}`}
                          nama={row.name}
                        />
                      </TableCell>
                    ))}
                  </TableRow>
                ))
              )}
            </TableBody>
          </Table>
        </div>

        <StatusLegend />

        {/* Standard UI Data Pagination */}
        {totalPages > 1 && (
          <div className="flex flex-col sm:flex-row items-center justify-between px-4 py-3 border-t border-[var(--border-color)] bg-[var(--bg-card-solid)] gap-3">
            <span className="text-xs text-[var(--text-muted)]">
              Menampilkan {((currentPage - 1) * itemsPerPage) + 1} - {Math.min(currentPage * itemsPerPage, sortedData.length)} dari {sortedData.length} entri
            </span>
            <div className="flex items-center gap-1.5" style={{ display: 'flex', gap: '6px' }}>
              <button
                type="button"
                className="w-8 h-8 flex items-center justify-center rounded-md border border-[var(--border-color)] bg-[var(--bg-input)] text-[var(--text-main)] hover:bg-[var(--border-color)] disabled:opacity-40 cursor-pointer"
                disabled={currentPage === 1}
                onClick={() => setCurrentPage(p => p - 1)}
              >
                <ChevronLeft className="w-4 h-4" />
              </button>
              {Array.from({ length: totalPages }).map((_, idx) => (
                <button
                  key={idx}
                  type="button"
                  className={`w-8 h-8 flex items-center justify-center rounded-md text-xs font-bold transition-all cursor-pointer ${currentPage === idx + 1
                      ? 'bg-[var(--accent-primary)] text-white shadow-xs'
                      : 'border border-[var(--border-color)] bg-[var(--bg-input)] text-[var(--text-main)] hover:bg-[var(--border-color)]'
                    }`}
                  onClick={() => setCurrentPage(idx + 1)}
                >
                  {idx + 1}
                </button>
              ))}
              <button
                type="button"
                className="w-8 h-8 flex items-center justify-center rounded-md border border-[var(--border-color)] bg-[var(--bg-input)] text-[var(--text-main)] hover:bg-[var(--border-color)] disabled:opacity-40 cursor-pointer"
                disabled={currentPage === totalPages}
                onClick={() => setCurrentPage(p => p + 1)}
              >
                <ChevronRight className="w-4 h-4" />
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
