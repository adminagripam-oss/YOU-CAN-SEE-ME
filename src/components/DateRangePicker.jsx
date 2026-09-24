import React, { useState, useRef, useEffect } from 'react';
import { format, addMonths, subMonths } from 'date-fns';
import { DayPicker } from 'react-day-picker';
import { CalendarIcon, ChevronLeft, ChevronRight } from 'lucide-react';
import 'react-day-picker/style.css';
import './DateRangePicker.css';

export function DateRangePicker({ dateRange, setDateRange }) {
  const [isOpen, setIsOpen] = useState(false);
  const [isMobile, setIsMobile] = useState(window.innerWidth < 600);
  // Controlled month state — the FIRST month shown in the picker
  const [currentMonth, setCurrentMonth] = useState(new Date());
  const containerRef = useRef(null);

  // Close when clicking outside
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (containerRef.current && !containerRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    };
    if (isOpen) {
      document.addEventListener('mousedown', handleClickOutside);
    }
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, [isOpen]);

  // Responsive check
  useEffect(() => {
    const handleResize = () => setIsMobile(window.innerWidth < 600);
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  // Convert string state → Date objects (guard for null values)
  const selectedRange = {
    from: dateRange?.start ? new Date(dateRange.start + 'T00:00:00') : undefined,
    to:   dateRange?.end   ? new Date(dateRange.end   + 'T00:00:00') : undefined,
  };

  const handleSelect = (range) => {
    if (!range) {
      // When deselecting, clear completely → show all dates
      setDateRange({ start: null, end: null });
      return;
    }
    setDateRange({
      start: range.from ? format(range.from, 'yyyy-MM-dd') : (dateRange?.start ?? null),
      end:   range.to   ? format(range.to,   'yyyy-MM-dd')
           : range.from ? format(range.from, 'yyyy-MM-dd')
           : (dateRange?.end ?? null),
    });
  };

  // Display text
  const displayString = () => {
    if (selectedRange.from && selectedRange.to) {
      if (selectedRange.from.getTime() === selectedRange.to.getTime()) {
        return format(selectedRange.from, 'dd LLL yyyy');
      }
      return `${format(selectedRange.from, 'dd LLL yyyy')} - ${format(selectedRange.to, 'dd LLL yyyy')}`;
    }
    if (selectedRange.from) {
      return format(selectedRange.from, 'dd LLL yyyy');
    }
    return 'Semua Tanggal';
  };

  const hasDate = !!(selectedRange.from || selectedRange.to);

  // Navigate months
  const goToPrevMonth = () => setCurrentMonth(prev => subMonths(prev, 1));
  const goToNextMonth = () => setCurrentMonth(prev => addMonths(prev, 1));

  // The second month for the dual-month view
  const secondMonth = addMonths(currentMonth, 1);

  // Custom Caption component — shows month/year centered, NO nav arrows from library
  const CustomCaption = ({ displayMonth }) => (
    <div className="drp-caption-custom">
      <span className="drp-caption-label">
        {format(displayMonth, 'MMMM yyyy')}
      </span>
    </div>
  );

  return (
    <div className="drp-root" ref={containerRef}>

      {/* ── Trigger button ── */}
      <button
        type="button"
        id="date-range-trigger"
        className={`drp-trigger${isOpen ? ' drp-trigger--open' : ''}`}
        onClick={() => setIsOpen(!isOpen)}
        aria-haspopup="dialog"
        aria-expanded={isOpen}
      >
        <span className={`drp-trigger__label${!hasDate ? ' drp-trigger__label--placeholder' : ''}`}>
          {displayString()}
        </span>
        <CalendarIcon size={15} className="drp-trigger__icon" aria-hidden="true" />
      </button>

      {/* ── Popover ── */}
      {isOpen && (
        <div
          className="drp-popover"
          role="dialog"
          aria-label="Date range picker"
        >
          {/* Preset Buttons Header */}
          <div style={{ 
            display: 'flex', 
            gap: '16px', 
            paddingBottom: '8px', 
            marginBottom: '8px', 
            borderBottom: '1px solid var(--border-color)',
            paddingLeft: '8px',
            paddingRight: '8px'
          }}>
            {(() => {
              const today = new Date();
              const todayStr = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;
              
              const yesterday = new Date();
              yesterday.setDate(yesterday.getDate() - 1);
              const yesterdayStr = `${yesterday.getFullYear()}-${String(yesterday.getMonth() + 1).padStart(2, '0')}-${String(yesterday.getDate()).padStart(2, '0')}`;

              const isToday = dateRange?.start === todayStr && dateRange?.end === todayStr;
              const isYesterday = dateRange?.start === yesterdayStr && dateRange?.end === yesterdayStr;
              const isAll = !dateRange?.start && !dateRange?.end;

              return (
                <>
                  <button
                    type="button"
                    onClick={() => {
                      setDateRange({ start: todayStr, end: todayStr });
                      setIsOpen(false);
                    }}
                    style={{
                      padding: '4px 4px',
                      fontSize: '0.8rem',
                      fontWeight: 700,
                      background: 'transparent',
                      color: isToday ? 'var(--text-main)' : 'var(--text-muted)',
                      border: 'none',
                      borderBottom: isToday ? '2px solid var(--accent-primary)' : '2px solid transparent',
                      cursor: 'pointer',
                      transition: 'all 0.2s ease'
                    }}
                  >
                    Hari Ini
                  </button>
                  <button
                    type="button"
                    onClick={() => {
                      setDateRange({ start: yesterdayStr, end: yesterdayStr });
                      setIsOpen(false);
                    }}
                    style={{
                      padding: '4px 4px',
                      fontSize: '0.8rem',
                      fontWeight: 700,
                      background: 'transparent',
                      color: isYesterday ? 'var(--text-main)' : 'var(--text-muted)',
                      border: 'none',
                      borderBottom: isYesterday ? '2px solid var(--accent-primary)' : '2px solid transparent',
                      cursor: 'pointer',
                      transition: 'all 0.2s ease'
                    }}
                  >
                    Kemarin
                  </button>
                  <button
                    type="button"
                    onClick={() => {
                      setDateRange({ start: null, end: null });
                      setIsOpen(false);
                    }}
                    style={{
                      padding: '4px 4px',
                      fontSize: '0.8rem',
                      fontWeight: 700,
                      background: 'transparent',
                      color: isAll ? 'var(--text-main)' : 'var(--text-muted)',
                      border: 'none',
                      borderBottom: isAll ? '2px solid var(--accent-primary)' : '2px solid transparent',
                      cursor: 'pointer',
                      transition: 'all 0.2s ease'
                    }}
                  >
                    Semua Tanggal
                  </button>
                </>
              );
            })()}
          </div>

          {/* ── Custom Nav Header (Arrows + Month Labels) ── */}
          <div className="drp-nav-header">
            {/* Prev Arrow */}
            <button
              type="button"
              className="drp-nav-btn"
              onClick={goToPrevMonth}
              aria-label="Previous month"
            >
              <ChevronLeft size={16} />
            </button>

            {/* Month labels */}
            <div className="drp-nav-months-label">
              <span className="drp-nav-month-label">
                {format(currentMonth, 'MMMM yyyy')}
              </span>
              {/* Only show second month label on desktop */}
              {!isMobile && (
                <span className="drp-nav-month-label">
                  {format(secondMonth, 'MMMM yyyy')}
                </span>
              )}
            </div>

            {/* Next Arrow */}
            <button
              type="button"
              className="drp-nav-btn"
              onClick={goToNextMonth}
              aria-label="Next month"
            >
              <ChevronRight size={16} />
            </button>
          </div>

          {/* ── DayPicker (no nav, no default caption) ── */}
          <div className={`drp-card${isMobile ? ' drp-card--mobile' : ''}`}>
            {isMobile ? (
              /* Mobile: show only current month */
              <DayPicker
                mode="range"
                month={currentMonth}
                onMonthChange={setCurrentMonth}
                selected={selectedRange}
                onSelect={handleSelect}
                numberOfMonths={1}
                showOutsideDays
                components={{ Caption: () => null, MonthCaption: () => null, Nav: () => null }}
                classNames={{ 
                  nav: 'drp-hidden-nav',
                  caption: 'drp-hidden-nav',
                  month_caption: 'drp-hidden-nav',
                  caption_label: 'drp-hidden-nav'
                }}
              />
            ) : (
              /* Desktop: show 2 months side by side */
              <div className="drp-dual-months">
                <DayPicker
                  mode="range"
                  month={currentMonth}
                  onMonthChange={setCurrentMonth}
                  selected={selectedRange}
                  onSelect={handleSelect}
                  numberOfMonths={1}
                  showOutsideDays
                  components={{ Caption: () => null, MonthCaption: () => null, Nav: () => null }}
                  classNames={{ 
                    nav: 'drp-hidden-nav',
                    caption: 'drp-hidden-nav',
                    month_caption: 'drp-hidden-nav',
                    caption_label: 'drp-hidden-nav'
                  }}
                />
                <DayPicker
                  mode="range"
                  month={secondMonth}
                  onMonthChange={() => {}}
                  selected={selectedRange}
                  onSelect={handleSelect}
                  numberOfMonths={1}
                  showOutsideDays
                  components={{ Caption: () => null, MonthCaption: () => null, Nav: () => null }}
                  classNames={{ 
                    nav: 'drp-hidden-nav',
                    caption: 'drp-hidden-nav',
                    month_caption: 'drp-hidden-nav',
                    caption_label: 'drp-hidden-nav'
                  }}
                />
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
}
