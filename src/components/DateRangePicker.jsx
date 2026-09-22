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

  // Convert string state → Date objects
  const selectedRange = {
    from: dateRange.start ? new Date(dateRange.start + 'T00:00:00') : undefined,
    to:   dateRange.end   ? new Date(dateRange.end   + 'T00:00:00') : undefined,
  };

  const handleSelect = (range) => {
    if (!range) {
      setDateRange({ start: dateRange.start, end: dateRange.end });
      return;
    }
    setDateRange({
      start: range.from ? format(range.from, 'yyyy-MM-dd') : dateRange.start,
      end:   range.to   ? format(range.to,   'yyyy-MM-dd')
           : range.from ? format(range.from, 'yyyy-MM-dd')
           : dateRange.end,
    });
  };

  // Display text
  const displayString = () => {
    if (selectedRange.from && selectedRange.to) {
      if (selectedRange.from.getTime() === selectedRange.to.getTime()) {
        return format(selectedRange.from, 'LLL dd, y');
      }
      return `${format(selectedRange.from, 'LLL dd, y')} - ${format(selectedRange.to, 'LLL dd, y')}`;
    }
    if (selectedRange.from) {
      return format(selectedRange.from, 'LLL dd, y');
    }
    return 'Pick a date range';
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
            gap: '8px', 
            paddingBottom: '12px', 
            marginBottom: '4px', 
            borderBottom: '1px solid var(--border-color)' 
          }}>
            <button
              type="button"
              onClick={() => {
                const today = new Date();
                const yyyy = today.getFullYear();
                const mm = String(today.getMonth() + 1).padStart(2, '0');
                const dd = String(today.getDate()).padStart(2, '0');
                const str = `${yyyy}-${mm}-${dd}`;
                setDateRange({ start: str, end: str });
                setIsOpen(false);
              }}
              style={{
                padding: '6px 12px',
                borderRadius: '6px',
                fontSize: '0.75rem',
                fontWeight: 600,
                background: 'var(--bg-card)',
                color: 'var(--text-main)',
                border: '1px solid var(--border-color)',
                cursor: 'pointer'
              }}
            >
              Hari Ini
            </button>
            <button
              type="button"
              onClick={() => {
                const yesterday = new Date();
                yesterday.setDate(yesterday.getDate() - 1);
                const yyyy = yesterday.getFullYear();
                const mm = String(yesterday.getMonth() + 1).padStart(2, '0');
                const dd = String(yesterday.getDate()).padStart(2, '0');
                const str = `${yyyy}-${mm}-${dd}`;
                setDateRange({ start: str, end: str });
                setIsOpen(false);
              }}
              style={{
                padding: '6px 12px',
                borderRadius: '6px',
                fontSize: '0.75rem',
                fontWeight: 600,
                background: 'var(--bg-card)',
                color: 'var(--text-main)',
                border: '1px solid var(--border-color)',
                cursor: 'pointer'
              }}
            >
              Kemarin
            </button>
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
                components={{ Caption: () => null }}
                classNames={{ nav: 'drp-hidden-nav' }}
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
                  components={{ Caption: () => null }}
                  classNames={{ nav: 'drp-hidden-nav' }}
                />
                <DayPicker
                  mode="range"
                  month={secondMonth}
                  onMonthChange={() => {}}
                  selected={selectedRange}
                  onSelect={handleSelect}
                  numberOfMonths={1}
                  showOutsideDays
                  components={{ Caption: () => null }}
                  classNames={{ nav: 'drp-hidden-nav' }}
                />
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
}
