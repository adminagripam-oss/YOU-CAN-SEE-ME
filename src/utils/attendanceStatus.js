// Colors are identical to the KPI card borderTop values in DashboardPage.jsx
export const STATUS = {
  H:    { label: 'Hadir',          short: 'H',  bg: '#15803d', fg: '#ffffff' },
  I:    { label: 'Izin',           short: 'I',  bg: '#4b5563', fg: '#ffffff' },
  S:    { label: 'Sakit',          short: 'S',  bg: '#b45309', fg: '#ffffff' },
  M:    { label: 'Mangkir',        short: 'M',  bg: '#b91c1c', fg: '#ffffff' },
  LC:   { label: 'Lupa checkout',  short: 'LC', bg: '#f97316', fg: '#431407' },
  NONE: { label: 'Tidak ada data', short: '-',  bg: 'var(--bg-input)',   fg: 'var(--text-muted)' },
};

export const STATUS_ORDER = ['H', 'I', 'S', 'M', 'LC', 'NONE'];

export const getStatus = (code) => STATUS[code] ?? STATUS.NONE;

// Mangkir > Sakit > Izin > LC > Hadir — higher wins when multiple logs exist for the same day
const PRIORITY = { M: 4, S: 3, I: 2, LC: 1, H: 0 };

export function resolveLogStatus(logStatus) {
  if (!logStatus) return 'H';
  if (logStatus.includes('Mangkir')) return 'M';
  if (logStatus.includes('Sakit')) return 'S';
  if (logStatus.includes('Izin')) return 'I';
  if (logStatus.toUpperCase().includes('LUPA_CHECKOUT') || logStatus.includes('Lupa Check-out')) return 'LC';
  return 'H';
}

export function mergeStatus(existing, incoming) {
  return (PRIORITY[incoming] ?? -1) > (PRIORITY[existing] ?? -1) ? incoming : existing;
}
