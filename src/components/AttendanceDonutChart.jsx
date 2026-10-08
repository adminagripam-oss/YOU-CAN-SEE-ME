import React, { useMemo, useState } from 'react';
import { Label, Pie, PieChart, Sector, Tooltip, Cell } from 'recharts';
import { AnimatedNumber } from './AnimatedNumber';
import { ANIM, isLowEndDevice } from '../config/animation';

import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import { ChartContainer } from '@/components/ui/chart';

const LEGEND_ITEMS = [
  { key: 'hadir',   label: 'Hadir',   color: '#15803d' },
  { key: 'izin',    label: 'Izin',    color: '#4b5563' },
  { key: 'sakit',   label: 'Sakit',   color: '#b45309' },
  { key: 'mangkir', label: 'Mangkir', color: '#b91c1c' },
];

export function AttendanceDonutChart({ verifiedCount = 0, izinCount = 0, sakitCount = 0, mangkirCount = 0, totalEmployees = 0, dateStr = '' }) {
  const [hiddenKeys, setHiddenKeys] = useState(new Set());
  const [activeIndex, setActiveIndex] = useState(-1);

  const rawData = useMemo(() => [
    { status: 'hadir',   count: verifiedCount,  fill: 'var(--color-hadir)' },
    { status: 'izin',    count: izinCount,       fill: 'var(--color-izin)' },
    { status: 'sakit',   count: sakitCount,      fill: 'var(--color-sakit)' },
    { status: 'mangkir', count: mangkirCount,    fill: 'var(--color-mangkir)' },
  ], [verifiedCount, izinCount, sakitCount, mangkirCount]);

  const chartData = useMemo(() => 
    rawData.filter(d => d.count > 0 && !hiddenKeys.has(d.status)),
  [rawData, hiddenKeys]);

  const total = chartData.reduce((sum, d) => sum + d.count, 0);
  
  const totalVerified = rawData.find(d => d.status === 'hadir')?.count || 0;
  const totalAllCategories = rawData.reduce((sum, d) => sum + d.count, 0);
  const hadirPct = totalAllCategories > 0 ? ((totalVerified / totalAllCategories) * 100).toFixed(1) : 0;

  // Counts for legend display
  const counts = { hadir: verifiedCount, izin: izinCount, sakit: sakitCount, mangkir: mangkirCount };

  const chartConfig = {
    count:   { label: 'Tenaga Kerja' },
    hadir:   { label: 'Hadir',   color: '#15803d' },
    izin:    { label: 'Izin',    color: '#4b5563' },  // ✅ abu-abu gelap, bukan biru
    sakit:   { label: 'Sakit',   color: '#b45309' },
    mangkir: { label: 'Mangkir', color: '#b91c1c' },
  };

  return (
    <Card className="flex flex-col w-full h-full border-none shadow-none bg-transparent" style={{ padding: 0, margin: 0 }}>
      <CardHeader className="items-center pb-0" style={{ padding: '0.75rem 1rem 0.25rem', marginBottom: '0.5rem' }}>
        <CardTitle style={{ fontSize: '1rem', fontWeight: 800, color: 'var(--text-main)' }}>Distribusi Kehadiran</CardTitle>
        <CardDescription style={{ fontSize: '0.8rem', fontWeight: 600, marginTop: '2px', textDecoration: 'underline' }}>
          {dateStr}
        </CardDescription>
      </CardHeader>

      <CardContent className="flex-1 pb-0 flex flex-col justify-center items-center" style={{ padding: 0 }}>
        {total === 0 ? (
          <div style={{ textAlign: 'center', color: 'var(--text-muted)', fontSize: '0.85rem', fontWeight: 600, marginTop: '2rem' }}>
            Belum ada data absensi
          </div>
        ) : (
          <>
            {/* ── Donut Chart ── */}
            <ChartContainer
              config={chartConfig}
              className="mx-auto aspect-square w-full max-w-[240px]"
            >
              <PieChart accessibilityLayer>
                <Tooltip
                  cursor={false}
                  allowEscapeViewBox={{ x: true, y: true }}
                  wrapperStyle={{ zIndex: 1000 }}
                  contentStyle={{
                    backgroundColor: 'var(--bg-card)',
                    border: '1px solid var(--border-color)',
                    borderRadius: '8px',
                    padding: '8px 12px',
                    boxShadow: '0 4px 16px rgba(0,0,0,0.25)',
                    whiteSpace: 'nowrap',
                    minWidth: 0,
                  }}
                  formatter={(value, name) => {
                    const cfg = chartConfig[name];
                    const color = cfg?.color || 'var(--text-main)';
                    return [
                      <span style={{ display: 'inline-flex', alignItems: 'center', gap: '6px' }}>
                        <span style={{ display: 'inline-block', width: '9px', height: '9px', borderRadius: '3px', background: color, flexShrink: 0 }} />
                        <span style={{ color: 'var(--text-main)', fontWeight: 800, fontSize: '0.85rem' }}>
                          {Number(value).toLocaleString()} orang
                        </span>
                      </span>,
                      <span style={{ color: 'var(--text-muted)', fontWeight: 600, fontSize: '0.8rem' }}>
                        {cfg?.label ?? name}
                      </span>
                    ];
                  }}
                />
                <Pie
                  data={chartData}
                  dataKey="count"
                  nameKey="status"
                  innerRadius={70}
                  outerRadius={110}
                  cornerRadius={5}
                  paddingAngle={3}
                  stroke="var(--bg-card)"
                  strokeWidth={3}
                  isAnimationActive={!isLowEndDevice()}
                  animationDuration={ANIM.duration.slow}
                  animationEasing={ANIM.easing}
                  onMouseEnter={(_, index) => setActiveIndex(index)}
                  onMouseLeave={() => setActiveIndex(-1)}
                  activeShape={({ outerRadius = 0, ...props }) => (
                    <Sector {...props} outerRadius={outerRadius + 6} />
                  )}
                  activeIndex={activeIndex}
                >
                  {chartData.map((entry, index) => (
                    <Cell 
                      key={`cell-${index}`} 
                      fill={entry.fill} 
                      style={{
                        transition: 'opacity 0.3s ease',
                        opacity: (activeIndex === -1 || activeIndex === index) ? 1 : 0.45
                      }}
                    />
                  ))}
                  <Label
                    content={({ viewBox }) => {
                      if (viewBox && 'cx' in viewBox && 'cy' in viewBox) {
                        let centerText = <AnimatedNumber value={total} duration={ANIM.duration.normal} formatValue={v => v.toLocaleString()} />;
                        let subText = "Total Ditampilkan";
                        
                        if (activeIndex !== -1 && chartData[activeIndex]) {
                          const activeEntry = chartData[activeIndex];
                          centerText = activeEntry.count.toLocaleString();
                          const pct = totalAllCategories > 0 ? ((activeEntry.count / totalAllCategories) * 100).toFixed(1) : 0;
                          subText = `${chartConfig[activeEntry.status]?.label} (${pct}%)`;
                        } else if (hiddenKeys.size === 0) {
                          centerText = <AnimatedNumber value={totalVerified} duration={ANIM.duration.normal} formatValue={v => v.toLocaleString()} />;
                          subText = `TK Hadir (${hadirPct}%)`;
                        } else {
                           centerText = <AnimatedNumber value={total} duration={ANIM.duration.normal} formatValue={v => v.toLocaleString()} />;
                           subText = "Total Ditampilkan";
                        }

                        return (
                          <text
                            x={viewBox.cx}
                            y={viewBox.cy}
                            textAnchor="middle"
                            dominantBaseline="middle"
                          >
                            <tspan
                              x={viewBox.cx}
                              y={viewBox.cy - 8}
                              style={{ fill: 'var(--text-main)', fontSize: '2rem', fontWeight: 900, transition: 'all 0.3s ease' }}
                            >
                              {centerText}
                            </tspan>
                            <tspan
                              x={viewBox.cx}
                              y={(viewBox.cy || 0) + 18}
                              style={{ fill: 'var(--text-muted)', fontSize: '0.72rem', fontWeight: 700, transition: 'all 0.3s ease' }}
                            >
                              {subText}
                            </tspan>
                          </text>
                        );
                      }
                    }}
                  />
                </Pie>
              </PieChart>
            </ChartContainer>

            {/* ── Legend Horizontal ── */}
            <div style={{
              display: 'flex',
              flexWrap: 'wrap',
              justifyContent: 'center',
              gap: '10px 16px',
              marginTop: '1.25rem',
              marginBottom: '0.75rem',
              padding: '0 12px',
            }}>
              {LEGEND_ITEMS.map(item => {
                const isHidden = hiddenKeys.has(item.key);
                return (
                  <div 
                    key={item.key} 
                    onClick={() => {
                      setHiddenKeys(prev => {
                        const next = new Set(prev);
                        if (next.has(item.key)) next.delete(item.key);
                        else next.add(item.key);
                        return next;
                      });
                    }}
                    style={{ 
                      display: 'flex', 
                      alignItems: 'center', 
                      gap: '5px',
                      cursor: 'pointer',
                      opacity: isHidden ? 0.4 : 1,
                      transition: 'opacity 0.2s ease'
                    }}
                  >
                    <div style={{
                      width: '9px', height: '9px', borderRadius: '50%',
                      background: item.color, flexShrink: 0
                    }} />
                    <span style={{ fontSize: '0.72rem', fontWeight: 700, color: 'var(--text-muted)', whiteSpace: 'nowrap' }}>
                      {item.label}
                    </span>
                    <span style={{ fontSize: '0.72rem', fontWeight: 800, color: 'var(--text-main)', minWidth: '16px' }}>
                      {counts[item.key]}
                    </span>
                  </div>
                );
              })}
            </div>
          </>
        )}
      </CardContent>
    </Card>
  );
}
