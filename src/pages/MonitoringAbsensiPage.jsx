import React, { useState, useMemo, useEffect } from 'react';
import { Search, Filter, ChevronLeft, ChevronRight, ChevronDown, Calendar as CalendarIcon, ArrowRight } from 'lucide-react';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "../components/ui/table";
import {
  DateSelector,
  formatDateValue,
  INDONESIAN_DATE_SELECTOR_I18N,
} from "../components/reui/date-selector";
import { Button } from "../components/ui/button";
import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "../components/ui/dialog";

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

export default function MonitoringAbsensiPage({ employees = [], logs = [] }) {
  const [searchQuery, setSearchQuery] = useState('');
  const [filterKebun, setFilterKebun] = useState('');
  const [filterAfdeling, setFilterAfdeling] = useState('');
  
  // Default to current month
  const today = new Date();
  const defaultMonth = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}`;
  const [selectedMonth, setSelectedMonth] = useState(defaultMonth);

  // ReUI DateSelector Dialog States
  const [isDateDialogOpen, setIsDateDialogOpen] = useState(false);
  const [dateSelectorValue, setDateSelectorValue] = useState({
    period: 'month',
    operator: 'is',
    year: today.getFullYear(),
    month: today.getMonth() // 0-indexed for DateSelector
  });
  const [internalDateValue, setInternalDateValue] = useState(dateSelectorValue);

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 25;

  // Sync internalValue when dialog opens
  useEffect(() => {
    if (isDateDialogOpen) {
      setInternalDateValue(dateSelectorValue);
    }
  }, [isDateDialogOpen, dateSelectorValue]);

  // Handle Apply button click in ReUI DateSelector Dialog
  const handleApplyDateSelector = () => {
    if (internalDateValue) {
      setDateSelectorValue(internalDateValue);

      // Extract Year and Month from internalDateValue
      let targetYear = today.getFullYear();
      let targetMonth = today.getMonth() + 1;

      if (internalDateValue.period === 'month') {
        if (internalDateValue.year !== undefined) targetYear = internalDateValue.year;
        if (internalDateValue.month !== undefined) targetMonth = internalDateValue.month + 1; // Convert 0-indexed to 1-indexed
      } else if (internalDateValue.startDate) {
        targetYear = internalDateValue.startDate.getFullYear();
        targetMonth = internalDateValue.startDate.getMonth() + 1;
      } else if (internalDateValue.year !== undefined) {
        targetYear = internalDateValue.year;
        targetMonth = (internalDateValue.month !== undefined) ? internalDateValue.month + 1 : 1;
      }

      const formattedMonthStr = `${targetYear}-${String(targetMonth).padStart(2, '0')}`;
      setSelectedMonth(formattedMonthStr);
      setCurrentPage(1);
    }
    setIsDateDialogOpen(false);
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

      const keys = [log.employee_id, log.nik].filter(Boolean);
      keys.forEach(k => {
        if (!attendanceMap[k]) {
          attendanceMap[k] = new Set();
        }
        attendanceMap[k].add(dateInfo.day);
      });
    });

    return safeEmployees.map(emp => {
      if (!emp) return null;
      
      const empSet = (
        (emp.id && attendanceMap[emp.id]) ||
        (emp.nik && attendanceMap[emp.nik]) ||
        new Set()
      );

      const attendanceRecord = {};
      daysInMonth.forEach(day => {
        attendanceRecord[day] = empSet.has(day) ? 'Hadir' : '';
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

  const getSelectedMonthLabel = () => {
    const formatted = formatDateValue(dateSelectorValue, INDONESIAN_DATE_SELECTOR_I18N);
    if (formatted) return formatted;

    if (!selectedMonth) return 'Pilih Bulan';
    const [yStr, mStr] = selectedMonth.split('-');
    const mIdx = parseInt(mStr, 10) - 1;
    if (mIdx >= 0 && mIdx < 12) {
      return `${MONTH_NAMES[mIdx]} ${yStr}`;
    }
    return selectedMonth;
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

      {/* CARD 1: NAVBAR & FILTERS */}
      <div className="bg-[var(--bg-card)] border-b sm:border border-[var(--border-color)] sm:rounded-md shadow-sm no-print mb-4 p-4 sm:p-6" style={{ margin: '0 0 16px 0' }}>
        <div className="flex flex-col gap-1 mb-4">
          <span className="text-[11px] font-bold tracking-wider text-[var(--text-muted)] uppercase">KEHADIRAN</span>
          <h2 className="text-xl font-black text-[var(--text-main)] m-0">
            Monitoring Perhari Absensi
          </h2>
          <p className="text-sm text-[var(--text-muted)] opacity-80 m-0">
            Pantau kehadiran karyawan setiap hari berdasarkan bulan yang dipilih.
          </p>
        </div>
        
        {/* Filter Toolbar Container */}
        <div className="flex flex-wrap items-center gap-3">
          
          {/* 1. ReUI DateSelector Dialog Pattern (c-date-selector-3) */}
          <div className="filter-input-wrapper filter-input-wrapper-mobile-full flex-none">
            <Dialog open={isDateDialogOpen} onOpenChange={setIsDateDialogOpen}>
              <DialogTrigger
                render={
                  <Button
                    variant="outline"
                    className="w-full md:w-auto justify-start font-semibold border-[var(--border-color)] bg-[var(--bg-input)] text-[var(--text-main)] hover:bg-[var(--bg-hover)] shadow-xs text-sm py-2 px-3 flex items-center gap-2"
                  >
                    <CalendarIcon className="w-4 h-4 text-[var(--text-muted)] flex-shrink-0" />
                    <span className="whitespace-nowrap">{getSelectedMonthLabel()}</span>
                  </Button>
                }
              />
              <DialogContent className="sm:max-w-lg bg-[var(--bg-card)] border-[var(--border-color)] text-[var(--text-main)]" showCloseButton={false}>
                <DialogHeader>
                  <DialogTitle className="text-lg font-bold text-[var(--text-main)]">Pilih Periode Absensi</DialogTitle>
                </DialogHeader>

                <DateSelector
                  value={internalDateValue}
                  onChange={setInternalDateValue}
                  showInput={true}
                  i18n={INDONESIAN_DATE_SELECTOR_I18N}
                />

                <DialogFooter className="mt-4 gap-2 flex justify-end">
                  <DialogClose render={<Button variant="outline" className="border-[var(--border-color)] text-[var(--text-main)]">Batal</Button>} />
                  <Button onClick={handleApplyDateSelector} className="bg-[var(--accent-primary)] text-white hover:bg-[var(--accent-primary-hover)]">Terapkan</Button>
                </DialogFooter>
              </DialogContent>
            </Dialog>
          </div>

          {/* 2. Search Input */}
          <div className="filter-input-wrapper filter-input-wrapper-mobile-full flex-1 min-w-[200px]">
            <Search className="filter-input-icon-left w-4 h-4" />
            <input
              type="text"
              placeholder="Cari NIK, Nama..."
              value={searchQuery}
              onChange={(e) => handleFilterChange(setSearchQuery, e.target.value)}
              className="filter-input-field w-full py-2 bg-[var(--bg-input)] border border-[var(--border-color)] rounded-md text-[var(--text-main)] text-sm focus:outline-none focus:border-[var(--accent-primary)] focus:ring-1 focus:ring-[var(--accent-primary)] shadow-xs"
            />
          </div>

          {/* 3. Kebun Filter Dropdown */}
          <div className="filter-input-wrapper filter-input-wrapper-mobile-half flex-none min-w-[150px]">
            <Filter className="filter-input-icon-left w-4 h-4" />
            <select
              value={filterKebun}
              onChange={(e) => handleFilterChange(setFilterKebun, e.target.value)}
              className="filter-select-field w-full py-2 bg-[var(--bg-input)] border border-[var(--border-color)] rounded-md text-[var(--text-main)] text-sm font-medium appearance-none focus:outline-none focus:border-[var(--accent-primary)] focus:ring-1 focus:ring-[var(--accent-primary)] cursor-pointer shadow-xs"
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
              className="filter-select-field w-full py-2 bg-[var(--bg-input)] border border-[var(--border-color)] rounded-md text-[var(--text-main)] text-sm font-medium appearance-none focus:outline-none focus:border-[var(--accent-primary)] focus:ring-1 focus:ring-[var(--accent-primary)] cursor-pointer shadow-xs"
            >
              <option value="">Semua Afdeling</option>
              {uniqueAfdelings.map(a => (
                <option key={a} value={a}>{a}</option>
              ))}
            </select>
            <ChevronDown className="filter-input-icon-right w-4 h-4" />
          </div>

        </div>
      </div>

      {/* CARD 2: MAIN TABLE GRID */}
      <div className="bg-[var(--bg-card)] sm:border border-[var(--border-color)] sm:rounded-md shadow-sm overflow-hidden flex-1" style={{ padding: 0 }}>
        <div className="w-full overflow-x-auto relative" style={{ WebkitOverflowScrolling: 'touch' }}>
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

                {/* Dynamic Month Days Columns (1 to 28..31) */}
                {daysInMonth.map(day => (
                  <TableHead
                    key={day}
                    className="w-[36px] min-w-[36px] text-center px-1 py-3 text-[11px] font-bold text-[var(--text-muted)] border-r border-[var(--border-color)]"
                  >
                    {day}
                  </TableHead>
                ))}
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

                    {/* Dynamic Attendance Cells */}
                    {daysInMonth.map(day => (
                      <TableCell
                        key={day}
                        className="text-center px-1 py-3 text-xs border-r border-[var(--border-color)]"
                      >
                        {row[day] === 'Hadir' ? (
                          <span className="text-[var(--accent-success)] font-extrabold">H</span>
                        ) : (
                          <span className="text-[var(--text-muted)] opacity-35 font-normal">-</span>
                        )}
                      </TableCell>
                    ))}
                  </TableRow>
                ))
              )}
            </TableBody>
          </Table>
        </div>

        {/* Card 2 Bottom Bar: Shadcn-Style Legend Badges & Helper Indicator */}
        <div className="flex flex-col sm:flex-row items-center justify-between px-4 py-3 border-t border-[var(--border-color)] bg-[var(--bg-card-solid)] gap-3">
          <div className="flex items-center gap-2.5 flex-wrap">
            <span className="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-md bg-[var(--accent-success)]/10 text-[var(--accent-success)] border border-[var(--accent-success)]/25 text-xs font-bold shadow-2xs">
              <span className="w-2 h-2 rounded-full bg-[var(--accent-success)]"></span>
              <span className="font-extrabold">H</span>
              <span className="text-[var(--text-main)] font-semibold ml-0.5">Hadir</span>
            </span>
            <span className="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-md bg-[var(--bg-input)] text-[var(--text-muted)] border border-[var(--border-color)] text-xs font-medium shadow-2xs">
              <span className="w-2 h-2 rounded-full bg-[var(--text-muted)] opacity-50"></span>
              <span className="font-bold opacity-60">-</span>
              <span className="text-[var(--text-muted)] font-normal ml-0.5">Tidak Ada Data</span>
            </span>
          </div>
          <div className="inline-flex items-center gap-1.5 text-xs text-[var(--text-muted)] font-medium opacity-85 bg-[var(--bg-input)] px-3 py-1 rounded-full border border-[var(--border-color)] shadow-2xs">
            <span>Geser ke kanan untuk melihat tanggal lainnya</span>
            <ArrowRight className="w-3.5 h-3.5 text-[var(--text-muted)]" />
          </div>
        </div>

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
                  className={`w-8 h-8 flex items-center justify-center rounded-md text-xs font-bold transition-all cursor-pointer ${
                    currentPage === idx + 1
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
