import React, { useState, useEffect } from 'react';
import { Bar, BarChart, CartesianGrid, XAxis, YAxis, LabelList, Tooltip } from 'recharts';
import { ChevronLeft, ChevronRight } from 'lucide-react';

import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import { ChartContainer } from '@/components/ui/chart';

const CustomXAxisTick = (props) => {
  const { x, y, payload } = props;
  const rawText = payload?.value || '';

  // Smart line wrapping by words for neat downward centered presentation without slanting
  const words = rawText.split(' ');
  const lines = [];
  let currentLine = '';

  words.forEach((w) => {
    if ((currentLine + ' ' + w).trim().length > 10) {
      if (currentLine) lines.push(currentLine);
      currentLine = w;
    } else {
      currentLine = currentLine ? currentLine + ' ' + w : w;
    }
  });
  if (currentLine) lines.push(currentLine);

  return (
    <g transform={`translate(${x},${y})`}>
      <text textAnchor="middle" fill="var(--text-main)" fontSize={9.5} fontWeight="900" fontFamily="inherit">
        {lines.map((line, i) => (
          <tspan x={0} dy={i === 0 ? 12 : 11} key={i}>
            {line}
          </tspan>
        ))}
      </text>
    </g>
  );
};

export function KebunAttendanceBarChart({ kebunSummary = [], dateStr }) {
  const [currentPage, setCurrentPage] = useState(0);
  const [isMobileScreen, setIsMobileScreen] = useState(() => typeof window !== 'undefined' && window.innerWidth < 640);

  useEffect(() => {
    const handleResize = () => {
      setIsMobileScreen(window.innerWidth < 640);
    };
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  // Show 3 items per page on mobile view, 5 on desktop
  const itemsPerPage = isMobileScreen ? 3 : 5;

  useEffect(() => {
    setCurrentPage(0);
  }, [itemsPerPage]);

  const totalPages = Math.ceil(kebunSummary.length / itemsPerPage);
  const startIndex = currentPage * itemsPerPage;
  const paginatedData = kebunSummary.slice(startIndex, startIndex + itemsPerPage);

  const handleNext = () => {
    if (currentPage < totalPages - 1) setCurrentPage(currentPage + 1);
  };

  const handlePrev = () => {
    if (currentPage > 0) setCurrentPage(currentPage - 1);
  };

  const chartConfig = {
    totalEmployees: { label: 'Total TK', color: '#15803d' },
  };

  return (
    <Card className="flex flex-col w-full h-full border-none shadow-none bg-transparent" style={{ padding: 0, margin: 0 }}>
      <CardHeader className="items-center pb-0" style={{ padding: '0.75rem 1.25rem 0.25rem', marginBottom: '0.5rem', display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
        <div>
          <CardTitle style={{ fontSize: '1rem', fontWeight: 800, color: 'var(--text-main)' }}>TK All Kebun</CardTitle>
          <CardDescription style={{ fontSize: '0.8rem', fontWeight: 600, marginTop: '2px', textDecoration: 'underline' }}>
            {dateStr}
          </CardDescription>
        </div>
        
        {totalPages > 1 && (
          <div style={{ display: 'flex', gap: '8px', alignItems: 'center' }}>
            <span style={{ fontSize: '0.75rem', fontWeight: 700, color: 'var(--text-muted)' }}>
              {startIndex + 1}–{Math.min(startIndex + itemsPerPage, kebunSummary.length)} / {kebunSummary.length}
            </span>
            <button
              onClick={handlePrev}
              disabled={currentPage === 0}
              style={{
                width: '28px', height: '28px', display: 'flex', alignItems: 'center', justifyContent: 'center',
                borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-primary)',
                cursor: currentPage === 0 ? 'not-allowed' : 'pointer', opacity: currentPage === 0 ? 0.4 : 1
              }}
            >
              <ChevronLeft size={14} />
            </button>
            <button
              onClick={handleNext}
              disabled={currentPage >= totalPages - 1}
              style={{
                width: '28px', height: '28px', display: 'flex', alignItems: 'center', justifyContent: 'center',
                borderRadius: '6px', border: '1px solid var(--border-color)', background: 'var(--bg-primary)',
                cursor: currentPage >= totalPages - 1 ? 'not-allowed' : 'pointer', opacity: currentPage >= totalPages - 1 ? 0.4 : 1
              }}
            >
              <ChevronRight size={14} />
            </button>
          </div>
        )}
      </CardHeader>
      
      <CardContent className="flex-1 pb-0 flex flex-col" style={{ padding: 0 }}>
        {kebunSummary.length === 0 ? (
          <div style={{ textAlign: 'center', color: 'var(--text-muted)', fontSize: '0.85rem', fontWeight: 600, marginTop: '2rem' }}>
            Belum ada data kebun
          </div>
        ) : (
          <ChartContainer config={chartConfig} style={{ width: '100%', height: '290px' }}>
            <BarChart
              accessibilityLayer
              data={paginatedData}
              margin={{ left: 0, right: 10, top: 25, bottom: 55 }}
            >
              <CartesianGrid vertical={false} stroke="var(--border-color)" strokeDasharray="3 3" />
              <XAxis
                dataKey="nama_kebun"
                tickLine={false}
                axisLine={{ stroke: 'var(--border-color)' }}
                interval={0}
                height={60}
                tick={<CustomXAxisTick />}
              />
              <YAxis
                tickLine={false}
                axisLine={false}
                tick={{ fill: 'var(--text-muted)', fontSize: 11, fontWeight: 'bold', fontFamily: 'inherit' }}
              />
              <Tooltip
                cursor={{ fill: 'var(--bg-primary)', opacity: 0.5 }}
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
                labelStyle={{ color: 'var(--text-muted)', fontWeight: 700, fontSize: '0.78rem', marginBottom: '4px' }}
                itemStyle={{ color: 'var(--text-main)', fontWeight: 700, fontSize: '0.82rem' }}
                formatter={(value, name, props) => [
                  `${value} org (Hadir: ${props.payload.hadirCount})`,
                  'Total TK'
                ]}
              />
              <Bar dataKey="totalEmployees" name="Total TK" fill="#15803d" radius={[4, 4, 0, 0]} barSize={38}>
                <LabelList
                  dataKey="totalEmployees"
                  position="top"
                  formatter={(val) => `${val !== undefined && val !== null ? val : 0}`}
                  style={{ fill: 'var(--text-main)', fontSize: 11, fontWeight: 900 }}
                />
              </Bar>
            </BarChart>
          </ChartContainer>
        )}
      </CardContent>
    </Card>
  );
}
