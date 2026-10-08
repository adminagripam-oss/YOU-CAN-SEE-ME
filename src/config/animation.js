export const ANIM = {
  duration: {
    fast: 250,
    normal: 700,
    slow: 1000
  },
  stagger: 60,
  easing: 'ease-out'
};

export const isLowEndDevice = () => {
  if (typeof window === 'undefined') return false;
  const reduced = window.matchMedia('(prefers-reduced-motion: reduce)').matches;
  const lowCores = navigator.hardwareConcurrency && navigator.hardwareConcurrency <= 4;
  return reduced || lowCores;
};

export const easeOutCubic = (t) => {
  return 1 - Math.pow(1 - t, 3);
};
