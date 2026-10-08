import React, { useState } from 'react';
import {
  ComposedChart,
  Bar,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  LabelList,
} from 'recharts';
import { isLowEndDevice } from '../config/animation';

const CustomXAxisTick = (props) => {
  const { x, y, payload } = props;
  const rawText = payload?.value || '';
  
  // Split by | to support two-line ticks if provided
  const lines = rawText.split('|');

  return (
    <g transform={`translate(${x},${y})`}>
      <text textAnchor="middle" fill="var(--text-main)" fontSize={11} fontWeight="900" fontFamily="inherit">
        {lines.map((line, i) => (
          <tspan x={0} dy={i === 0 ? 12 : 14} key={i} style={{ fontSize: i > 0 ? 10 : 11, fill: i > 0 ? 'var(--text-muted)' : 'var(--text-main)' }}>
            {line}
          </tspan>
        ))}
      </text>
    </g>
  );
};

export function ComboBarLineChart({ 
  data = [], 
  title, 
  subtitle,
  realisasiKey = 'realisasi',
  trendKey = 'realisasi',
  showYAxis = true
}) {
  const isLowEnd = isLowEndDevice();

  const CustomTooltip = ({ active, payload, label }) => {
    if (active && payload && payload.length) {
      const currentIndex = data.findIndex(d => d.name === label);
      const currentValue = payload.find(p => p.dataKey === realisasiKey)?.value || 0;
      
      let diffText = '0';
      let diffColor = 'var(--text-muted)';
      
      if (currentIndex > 0) {
        const prevValue = data[currentIndex - 1][realisasiKey] || 0;
        const diff = currentValue - prevValue;
        if (diff > 0) {
          diffText = `▲ ${Math.abs(diff).toLocaleString('id-ID')}`;
          diffColor = '#15803d'; // green
        } else if (diff < 0) {
          diffText = `▼ ${Math.abs(diff).toLocaleString('id-ID')}`;
          diffColor = '#b91c1c'; // red
        }
      }

      return (
        <div style={{
          backgroundColor: 'var(--bg-card)',
          border: '1px solid var(--border-color)',
          borderRadius: '8px',
          padding: '8px 12px',
          boxShadow: '0 4px 16px rgba(0,0,0,0.25)',
          whiteSpace: 'nowrap',
        }}>
          <div style={{ color: 'var(--text-muted)', fontWeight: 700, fontSize: '0.78rem', marginBottom: '6px' }}>
            {label}
          </div>
          <div style={{ display: 'flex', flexDirection: 'column', gap: '4px' }}>
            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: '16px' }}>
               <span style={{ fontSize: '0.82rem', fontWeight: 700, color: 'var(--text-main)' }}>Realisasi HK</span>
               <span style={{ fontSize: '0.82rem', fontWeight: 800, color: '#15803d' }}>{currentValue.toLocaleString('id-ID')}</span>
            </div>
            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: '16px' }}>
               <span style={{ fontSize: '0.72rem', fontWeight: 600, color: 'var(--text-muted)' }}>vs Sblm</span>
               <span style={{ fontSize: '0.75rem', fontWeight: 800, color: diffColor }}>{diffText}</span>
            </div>
          </div>
        </div>
      );
    }
    return null;
  };

  return (
    <div className="flex flex-col w-full h-full" style={{ padding: 0, margin: 0 }}>
      {/* Header */}
      {(title || subtitle) && (
        <div style={{ padding: '0 0 1rem 0' }}>
          {title && <h3 style={{ fontSize: '1rem', fontWeight: 800, color: 'var(--text-main)', margin: 0 }}>{title}</h3>}
          {subtitle && <p style={{ fontSize: '0.8rem', fontWeight: 600, marginTop: '2px', color: 'var(--text-muted)', margin: 0 }}>{subtitle}</p>}
        </div>
      )}
      
      <div className="flex-1 flex flex-col" style={{ padding: 0, minHeight: '260px' }}>
        {data.length === 0 ? (
          <div style={{ textAlign: 'center', color: 'var(--text-muted)', fontSize: '0.85rem', fontWeight: 600, marginTop: '2rem' }}>
            Belum ada data
          </div>
        ) : (
          <div style={{ width: '100%', height: '100%' }}>
            <ResponsiveContainer width="100%" height="100%">
              <ComposedChart data={data} margin={{ top: 28, right: 12, left: 12, bottom: 44 }}>
                <XAxis
                  dataKey="name"
                  tickLine={false}
                  axisLine={false}
                  tick={<CustomXAxisTick />}
                  interval={0}
                  tickMargin={8}
                />
                
                {showYAxis && (
                  <YAxis 
                    domain={[0, dataMax => Math.ceil(dataMax * 1.2)]} 
                    axisLine={false}
                    tickLine={false}
                    tick={{ fill: 'var(--text-muted)', fontSize: 11 }}
                    tickCount={5}
                    width={35}
                  />
                )}
                {!showYAxis && <YAxis hide domain={[0, dataMax => Math.ceil(dataMax * 1.2)]} />}
                
                <Tooltip
                  cursor={{ fill: 'var(--bg-primary)', opacity: 0.5 }}
                  allowEscapeViewBox={{ x: true, y: true }}
                  wrapperStyle={{ zIndex: 100 }}
                  content={<CustomTooltip />}
                />

                  <Bar 
                    dataKey={realisasiKey} 
                    name={realisasiKey}
                    fill="#15803d" 
                    radius={[8, 8, 0, 0]} 
                    maxBarSize={48}
                    isAnimationActive={!isLowEnd}
                  >
                    <LabelList
                      dataKey={realisasiKey}
                      position="top"
                      offset={12}
                      formatter={(val) => val > 0 ? val.toLocaleString('id-ID') : ''}
                      style={{ fill: 'var(--text-main)', fontSize: 12, fontWeight: 800 }}
                    />
                  </Bar>

                  <Line 
                    type="linear"
                    connectNulls={false}
                    dataKey={trendKey} 
                    name="trenKey"
                    stroke="#f97316" 
                    strokeWidth={2}
                    strokeDasharray="6 6"
                    dot={{ r: 3, fill: '#ffffff', strokeWidth: 2, stroke: '#f97316' }}
                    activeDot={{ r: 5, stroke: '#f97316', strokeWidth: 2 }}
                    isAnimationActive={!isLowEnd}
                  />

              </ComposedChart>
            </ResponsiveContainer>
          </div>
        )}
      </div>
    </div>
  );
}
