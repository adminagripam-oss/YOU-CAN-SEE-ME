import React, { useMemo } from 'react';
import { Label, Pie, PieChart, Sector } from 'recharts';

import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import {
  ChartContainer,
  ChartTooltip,
  ChartTooltipContent,
} from '@/components/ui/chart';

const LEGEND_ITEMS = [
  { key: 'hadir',   label: 'Hadir',   color: '#15803d' },
  { key: 'izin',    label: 'Izin',    color: '#4b5563' },
  { key: 'sakit',   label: 'Sakit',   color: '#b45309' },
  { key: 'mangkir', label: 'Mangkir', color: '#b91c1c' },
];

export function AttendanceDonutChart({ verifiedCount = 0, izinCount = 0, sakitCount = 0, mangkirCount = 0, totalEmployees = 0, dateStr = '' }) {
  const chartData = useMemo(() => [
    { status: 'hadir',   count: verifiedCount,  fill: 'var(--color-hadir)' },
    { status: 'izin',    count: izinCount,       fill: 'var(--color-izin)' },
    { status: 'sakit',   count: sakitCount,      fill: 'var(--color-sakit)' },
    { status: 'mangkir', count: mangkirCount,    fill: 'var(--color-mangkir)' },
  ].filter(d => d.count > 0), [verifiedCount, izinCount, sakitCount, mangkirCount]);

  const total = chartData.reduce((sum, d) => sum + d.count, 0);
  const hadirPct = total > 0 ? ((verifiedCount / total) * 100).toFixed(1) : 0;

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
                <ChartTooltip
                  cursor={false}
                  content={
                    <ChartTooltipContent
                      className="min-w-32 gap-2.5"
                      formatter={(value, name, props) => {
                        const cfg = chartConfig[name];
                        const color = cfg?.color || 'var(--text-main)';
                        return (
                          <div style={{ display: 'flex', width: '100%', alignItems: 'center', justifyContent: 'space-between', gap: '8px' }}>
                            <div style={{ display: 'flex', alignItems: 'center', gap: '6px' }}>
                              <div style={{ width: '10px', height: '10px', borderRadius: '4px', background: color }} />
                              <span style={{ color: 'var(--text-muted)', fontWeight: 600, fontSize: '0.8rem' }}>
                                {cfg?.label || name}
                              </span>
                            </div>
                            <span style={{ color: 'var(--text-main)', fontWeight: 800, fontSize: '0.85rem' }}>
                              {Number(value).toLocaleString()}
                            </span>
                          </div>
                        );
                      }}
                    />
                  }
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
                  activeShape={({ outerRadius = 0, ...props }) => (
                    <Sector {...props} outerRadius={outerRadius + 8} />
                  )}
                >
                  <Label
                    content={({ viewBox }) => {
                      if (viewBox && 'cx' in viewBox && 'cy' in viewBox) {
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
                              style={{ fill: 'var(--text-main)', fontSize: '2rem', fontWeight: 900 }}
                            >
                              {verifiedCount.toLocaleString()}
                            </tspan>
                            <tspan
                              x={viewBox.cx}
                              y={(viewBox.cy || 0) + 18}
                              style={{ fill: 'var(--text-muted)', fontSize: '0.72rem', fontWeight: 700 }}
                            >
                              TK Hadir ({hadirPct}%)
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
              {LEGEND_ITEMS.map(item => (
                <div key={item.key} style={{ display: 'flex', alignItems: 'center', gap: '5px' }}>
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
              ))}
            </div>
          </>
        )}
      </CardContent>
    </Card>
  );
}
