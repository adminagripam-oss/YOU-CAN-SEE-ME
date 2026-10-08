import React, { useState, useEffect, useMemo, useCallback } from 'react';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { ChevronLeft, ChevronRight, Play, Pause } from 'lucide-react';

export function KebunAttendanceBarChart({ kebunSummary = [], dateStr, title }) {
  const displayTitle = title || 'Produksi per kebun (TK)';
  
  // Data Sanitization
  const safeData = useMemo(() => {
    if (!Array.isArray(kebunSummary)) return [];
    return kebunSummary.map(item => ({
      ...item,
      nama_kebun: item.nama_kebun || 'Unknown',
      totalEmployees: Number(item.totalEmployees) || 0,
      hadirCount: Number(item.hadirCount) || 0
    }));
  }, [kebunSummary]);

  const itemsPerPage = 5; // User requested fixed 5 per page
  const totalItems = safeData.length;
  const totalPages = Math.max(1, Math.ceil(totalItems / itemsPerPage));

  const [currentPage, setCurrentPage] = useState(0);
  const [prevPage, setPrevPage] = useState(-1);
  const [direction, setDirection] = useState(1);
  
  const [isPlaying, setIsPlaying] = useState(true);
  const [isHoveredOrFocused, setIsHoveredOrFocused] = useState(false);
  const [prefersReducedMotion, setPrefersReducedMotion] = useState(false);
  
  const [timerTick, setTimerTick] = useState(0);

  // Initialize and listen to reduce motion
  useEffect(() => {
    if (typeof window !== 'undefined') {
      const mediaQuery = window.matchMedia('(prefers-reduced-motion: reduce)');
      setPrefersReducedMotion(mediaQuery.matches);
      if (mediaQuery.matches) setIsPlaying(false);
      
      const mqHandler = (e) => setPrefersReducedMotion(e.matches);
      mediaQuery.addEventListener('change', mqHandler);
      return () => mediaQuery.removeEventListener('change', mqHandler);
    }
  }, []);

  // Timer for auto-sliding (setTimeout chain)
  useEffect(() => {
    let timer;
    if (isPlaying && !isHoveredOrFocused && totalPages > 1) {
      timer = setTimeout(() => {
        if (document.visibilityState === 'visible') {
          setPrevPage(currentPage);
          setDirection(1);
          setCurrentPage(p => (p + 1) % totalPages);
        } else {
          // If hidden, just trigger another tick to check later
          setTimerTick(t => t + 1);
        }
      }, 5400); // 5000ms interval + 400ms transition buffer
    }
    return () => clearTimeout(timer);
  }, [isPlaying, isHoveredOrFocused, currentPage, totalPages, timerTick]);

  const handleNext = useCallback(() => {
    setPrevPage(currentPage);
    setDirection(1);
    setCurrentPage(p => (p + 1) % totalPages);
    setTimerTick(t => t + 1); // Reset timer 5s
  }, [currentPage, totalPages]);

  const handlePrev = useCallback(() => {
    setPrevPage(currentPage);
    setDirection(-1);
    setCurrentPage(p => (p - 1 + totalPages) % totalPages);
    setTimerTick(t => t + 1); // Reset timer 5s
  }, [currentPage, totalPages]);

  const togglePlay = () => {
    setIsPlaying(p => !p);
    setTimerTick(t => t + 1); // Reset timer 5s
  };

  const startIndex = currentPage * itemsPerPage + 1;
  const endIndex = Math.min((currentPage + 1) * itemsPerPage, totalItems);
  const globalMax = Math.max(0, ...safeData.map(k => k.totalEmployees));
  
  // Format acronym to a new line manually if matched
  const formatKebunName = (name) => {
    const match = name.match(/^(.*?)\s*(\([^)]+\))$/);
    if (match) {
      return (
        <>
          <span style={{display: 'block'}}>{match[1].trim()}</span>
          <span style={{display: 'block'}}>{match[2]}</span>
        </>
      );
    }
    return name;
  };

  return (
    <Card 
      className="tkc-card flex flex-col w-full h-full border-none shadow-none" 
      style={{ padding: 0, margin: 0, minWidth: 0, maxWidth: '100%', background: 'var(--bg-card)' }}
      onMouseEnter={() => setIsHoveredOrFocused(true)}
      onMouseLeave={() => setIsHoveredOrFocused(false)}
      onFocusCapture={() => setIsHoveredOrFocused(true)}
      onBlurCapture={() => setIsHoveredOrFocused(false)}
    >
      <style>{`
        .tkc-card * {
          box-sizing: border-box;
        }
        .tkc-btn {
          width: 28px;
          height: 28px;
          display: flex;
          align-items: center;
          justify-content: center;
          border-radius: 6px;
          border: 1px solid var(--border-color);
          background: var(--bg-primary);
          color: var(--text-main);
          cursor: pointer;
          transition: all 150ms ease;
        }
        .tkc-btn:hover {
          background: var(--bg-secondary);
        }
        .tkc-btn:focus-visible {
          outline: 2px solid var(--accent-primary);
          outline-offset: 2px;
        }
        .tkc-region {
          flex: 1;
          display: flex;
          flex-direction: column;
          position: relative;
          overflow: hidden;
          width: 100%;
          min-height: 290px;
        }
        .tkc-slides-wrapper {
          position: relative;
          flex: 1;
          width: 100%;
          height: 100%;
        }
        .tkc-slide {
          position: absolute;
          top: 0; left: 0; right: 0; bottom: 0;
          pointer-events: none;
          opacity: 0;
          display: grid;
          grid-template-columns: repeat(5, 1fr);
          gap: 12px;
          transition: opacity 350ms cubic-bezier(0.2, 0, 0, 1), transform 350ms cubic-bezier(0.2, 0, 0, 1);
        }
        .tkc-slide.is-active {
          opacity: 1;
          pointer-events: auto;
          transform: translateX(0);
          z-index: 2;
        }
        .tkc-slide.is-prev-next {
          opacity: 0;
          transform: translateX(-24px);
          z-index: 1;
        }
        .tkc-slide.is-prev-prev {
          opacity: 0;
          transform: translateX(24px);
          z-index: 1;
        }
        .tkc-slide.is-idle-next {
          opacity: 0;
          transform: translateX(24px);
        }
        .tkc-slide.is-idle-prev {
          opacity: 0;
          transform: translateX(-24px);
        }
        
        /* Reduced motion overrides */
        .tkc-reduced-motion .tkc-slide {
          transform: none !important;
          transition: opacity 300ms ease-in-out;
        }
        
        .tkc-col {
          display: flex;
          flex-direction: column;
          height: 100%;
        }
        .tkc-chart-area {
          flex: 1;
          position: relative;
          border-bottom: 1px solid var(--border-color);
          margin-top: 30px; 
        }
        .tkc-bar-wrapper {
          position: absolute;
          bottom: 0;
          left: 12.5%;
          right: 12.5%;
          max-width: 64px;
          margin: 0 auto;
        }
        .tkc-bar-fill {
          position: absolute;
          bottom: 0;
          left: 0;
          width: 100%;
          height: 100%;
          background: linear-gradient(to top, #14532d, #22c55e);
          border-radius: 4px 4px 0 0;
          transform-origin: bottom;
          transform: scaleY(0);
          transition: transform 400ms cubic-bezier(0.2, 0, 0, 1);
          will-change: transform;
        }
        .tkc-slide.is-active .tkc-bar-fill {
          transform: scaleY(1);
        }
        .tkc-reduced-motion .tkc-bar-fill {
          transform: scaleY(1) !important;
          transition: none !important;
        }
        
        .tkc-bar-label {
          position: absolute;
          bottom: 100%;
          left: -100%;
          right: -100%;
          text-align: center;
          padding-bottom: 4px;
          font-size: 11px;
          font-weight: 800;
          color: var(--text-main);
          opacity: 0;
          transform: translateY(8px);
          transition: opacity 300ms ease-out, transform 300ms ease-out;
        }
        .tkc-slide.is-active .tkc-bar-label {
          opacity: 1;
          transform: translateY(0);
        }
        .tkc-reduced-motion .tkc-bar-label {
          transform: none !important;
          transition: opacity 300ms ease-out;
        }
        
        .tkc-xaxis-label {
          height: 42px;
          margin-top: 8px;
          text-align: center;
          font-size: 10px;
          font-weight: 700;
          line-height: 1.2;
          color: var(--text-muted);
          overflow: hidden;
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: flex-start;
          word-wrap: break-word;
          white-space: normal;
        }
      `}</style>
      
      <CardHeader className="items-center pb-0" style={{ padding: '0.75rem 1.25rem 0.25rem', marginBottom: '0.5rem', display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
        <div>
          <CardTitle style={{ fontSize: '1rem', fontWeight: 800, color: 'var(--text-main)' }}>{displayTitle}</CardTitle>
          <CardDescription style={{ fontSize: '0.8rem', fontWeight: 600, marginTop: '2px', textDecoration: 'underline' }}>
            {dateStr}
          </CardDescription>
        </div>
        
        {totalPages > 1 && (
          <div style={{ display: 'flex', gap: '8px', alignItems: 'center' }}>
            <button
              className="sr-only"
              onClick={togglePlay}
              aria-label={isPlaying ? "Hentikan rotasi" : "Mulai rotasi"}
            >
              {isPlaying ? <Pause size={14} fill="currentColor" /> : <Play size={14} fill="currentColor" />}
            </button>
            <span style={{ fontSize: '0.75rem', fontWeight: 700, color: 'var(--text-muted)', whiteSpace: 'nowrap' }}>
              {endIndex === 0 && totalItems === 0 ? 0 : startIndex}–{endIndex === 0 && totalItems === 0 ? 0 : endIndex} / {totalItems}
            </span>
            <button className="tkc-btn" onClick={handlePrev} aria-label="Halaman sebelumnya">
              <ChevronLeft size={14} />
            </button>
            <button className="tkc-btn" onClick={handleNext} aria-label="Halaman berikutnya">
              <ChevronRight size={14} />
            </button>
          </div>
        )}
      </CardHeader>
      
      <CardContent className="flex-1 pb-0 flex flex-col" style={{ padding: '0 10px 10px 10px' }}>
        {totalItems === 0 ? (
          <div style={{ textAlign: 'center', color: 'var(--text-muted)', fontSize: '0.85rem', fontWeight: 600, marginTop: '2rem' }}>
            Belum ada data kebun
          </div>
        ) : (
          <div 
            className="tkc-region"
            role="region" 
            aria-roledescription="carousel" 
            aria-label="Jumlah TK per kebun"
          >
            <div 
              className={`tkc-slides-wrapper ${prefersReducedMotion ? 'tkc-reduced-motion' : ''}`}
              aria-live={isPlaying ? 'off' : 'polite'}
            >
              {Array.from({ length: totalPages }).map((_, pageIdx) => {
                const pageData = safeData.slice(pageIdx * itemsPerPage, (pageIdx + 1) * itemsPerPage);
                
                // Determine CSS classes for animation state
                let slideClass = 'tkc-slide';
                if (pageIdx === currentPage) {
                  slideClass += ' is-active';
                } else if (pageIdx === prevPage) {
                  slideClass += direction === 1 ? ' is-prev-next' : ' is-prev-prev';
                } else {
                  // Setup relative position for seamless crossfade entry
                  let pos = pageIdx - currentPage;
                  if (currentPage === 0 && pageIdx === totalPages - 1) pos = -1;
                  if (currentPage === totalPages - 1 && pageIdx === 0) pos = 1;
                  
                  if (pos > 0) slideClass += ' is-idle-next';
                  else slideClass += ' is-idle-prev';
                }

                return (
                  <div 
                    key={`page-${pageIdx}`}
                    className={slideClass}
                    role="group"
                    aria-label={`Halaman ${pageIdx + 1} dari ${totalPages}`}
                    aria-hidden={pageIdx !== currentPage}
                  >
                    {pageData.map((item, idx) => {
                      const val = item.totalEmployees;
                      const percent = globalMax > 0 ? (val / globalMax) * 100 : 0;
                      return (
                        <div key={`item-${idx}`} className="tkc-col">
                          <div className="tkc-chart-area">
                            <div className="tkc-bar-wrapper" style={{ height: `${percent}%` }}>
                              <div 
                                className="tkc-bar-fill" 
                                role="img"
                                aria-label={`Nama kebun: ${item.nama_kebun}, nilai: ${val}`}
                                style={{ transitionDelay: prefersReducedMotion ? '0ms' : `${idx * 60}ms` }}
                                title={`${val} org (Hadir: ${item.hadirCount})`}
                              ></div>
                              <div 
                                className="tkc-bar-label"
                                style={{ transitionDelay: prefersReducedMotion ? '0ms' : `${idx * 60}ms` }}
                              >
                                {val}
                              </div>
                            </div>
                          </div>
                          <div className="tkc-xaxis-label">
                            {formatKebunName(item.nama_kebun)}
                          </div>
                        </div>
                      );
                    })}
                  </div>
                );
              })}
            </div>
          </div>
        )}
      </CardContent>
    </Card>
  );
}
