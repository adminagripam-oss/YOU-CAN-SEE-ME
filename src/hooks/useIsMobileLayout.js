import { useState, useEffect } from 'react';

export const MOBILE_DATA_LAYOUT_ENABLED = true;

export function useIsMobileLayout() {
  const [isMobile, setIsMobile] = useState(() => {
    if (typeof window === 'undefined') return false;
    return MOBILE_DATA_LAYOUT_ENABLED && window.matchMedia('(max-width: 768px)').matches;
  });

  useEffect(() => {
    if (!MOBILE_DATA_LAYOUT_ENABLED || typeof window === 'undefined') {
      setIsMobile(false);
      return;
    }

    const mql = window.matchMedia('(max-width: 768px)');
    const onChange = (e) => setIsMobile(e.matches);

    // Initial sync
    setIsMobile(mql.matches);

    mql.addEventListener('change', onChange);
    return () => mql.removeEventListener('change', onChange);
  }, []);

  return isMobile;
}
