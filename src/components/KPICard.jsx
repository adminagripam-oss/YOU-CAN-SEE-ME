import React, { useState, useEffect } from 'react';
import { AnimatedNumber } from './AnimatedNumber';
import { ANIM, isLowEndDevice } from '../config/animation';
import { ResponsiveContainer, AreaChart, Area } from 'recharts';

export function KPICard({ 
  index = 0, 
  title, 
  value, 
  subtitle, 
  color, 
  percentage, 
  trendData = [], 
  isMultiDay = false,
  formatValue = (val) => val.toLocaleString('id-ID')
}) {
  const [isMounted, setIsMounted] = useState(false);
  // Removed useCountUp, localized animation inside AnimatedNumber
  
  useEffect(() => {
    // Slight delay so css animations take effect
    const t = setTimeout(() => setIsMounted(true), 10);
    return () => clearTimeout(t);
  }, []);

  const lowEnd = isLowEndDevice();

  // If we have trend data, calculate diff vs yesterday
  let trendDirection = null; // 'up', 'down', 'flat'
  let trendDiff = 0;
  if (trendData.length >= 2) {
    const todayVal = trendData[trendData.length - 1].val;
    const yesterdayVal = trendData[trendData.length - 2].val;
    trendDiff = todayVal - yesterdayVal;
    if (trendDiff > 0) trendDirection = 'up';
    else if (trendDiff < 0) trendDirection = 'down';
    else trendDirection = 'flat';
  }

  // Animation classes / inline styles
  const animStyles = lowEnd ? {} : {
    opacity: isMounted ? 1 : 0,
    transform: isMounted ? 'translateY(0)' : 'translateY(8px)',
    transition: `opacity ${ANIM.duration.normal}ms ${ANIM.easing}, transform ${ANIM.duration.normal}ms ${ANIM.easing}`,
    transitionDelay: `${index * ANIM.stagger}ms`
  };

  return (
    <div className="glass-card kpi-card-hover" style={{
      position: 'relative',
      overflow: 'hidden',
      marginBottom: 0,
      padding: '1.25rem',
      border: '1px solid var(--border-color)',
      borderTop: `4px solid ${color}`,
      borderRadius: '8px',
      background: 'var(--bg-card)',
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'flex-start',
      justifyContent: 'center',
      minHeight: '124px',
      ...animStyles
    }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%', alignItems: 'flex-start' }}>
        <span style={{ fontSize: '0.72rem', fontWeight: 800, color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>
          {title}
        </span>
        {trendDirection && trendDirection !== 'flat' && (
          <span style={{ 
            fontSize: '0.65rem', 
            fontWeight: 800, 
            color: trendDirection === 'up' ? '#15803d' : '#b91c1c',
            background: trendDirection === 'up' ? 'rgba(21,128,61,0.1)' : 'rgba(185,28,28,0.1)',
            padding: '2px 6px',
            borderRadius: '4px',
            display: 'flex',
            alignItems: 'center',
            gap: '2px'
          }}>
            {trendDirection === 'up' ? '▲' : '▼'} {Math.abs(trendDiff)}
          </span>
        )}
      </div>

      <div style={{ fontFamily: 'Inter, sans-serif', fontSize: '2.2rem', fontWeight: 900, color: 'var(--text-main)', margin: '0.5rem 0', zIndex: 2 }}>
        <AnimatedNumber value={value} duration={ANIM.duration.normal} formatValue={formatValue} />
      </div>
      
      <div style={{ display: 'flex', zIndex: 2, transition: 'opacity 0.4s ease', opacity: lowEnd ? 1 : 1 }}>
        {!isMultiDay && percentage !== undefined ? (
          <span style={{ 
            fontSize: '0.68rem', 
            fontWeight: 700, 
            color: color, 
            background: `${color}1A`, // hex opacity approx 10%
            padding: '2px 7px', 
            borderRadius: '4px' 
          }}>
            {percentage}% Aktual
          </span>
        ) : (
          <span style={{ fontSize: '0.68rem', fontWeight: 600, color: 'var(--text-muted)' }}>{subtitle}</span>
        )}
      </div>

      {/* Sparkline background */}
      {trendData.length > 0 && (
        <div style={{ position: 'absolute', bottom: 0, left: 0, right: 0, height: '40px', opacity: 0.15, zIndex: 1, pointerEvents: 'none' }}>
          <ResponsiveContainer width="100%" height="100%">
            <AreaChart data={trendData} margin={{ top: 5, right: 0, left: 0, bottom: 0 }}>
              <defs>
                <linearGradient id={`spark-fill-${title.replace(/\s+/g,'')}`} x1="0" y1="0" x2="0" y2="1">
                  <stop offset="0%" stopColor={color} stopOpacity={1} />
                  <stop offset="100%" stopColor={color} stopOpacity={0} />
                </linearGradient>
              </defs>
              <Area 
                type="monotone" 
                dataKey="val" 
                stroke={color} 
                strokeWidth={2} 
                fill={`url(#spark-fill-${title.replace(/\s+/g,'')})`}
                isAnimationActive={!lowEnd}
                animationDuration={ANIM.duration.normal}
              />
            </AreaChart>
          </ResponsiveContainer>
        </div>
      )}
    </div>
  );
}
