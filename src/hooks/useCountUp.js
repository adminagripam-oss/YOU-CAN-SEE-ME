import { useState, useEffect, useRef } from 'react';
import { isLowEndDevice, easeOutCubic } from '../config/animation';

export function useCountUp(endValue, duration = 800) {
  const [value, setValue] = useState(0);
  const startValueRef = useRef(0);
  const endValueRef = useRef(endValue);
  const rafRef = useRef(null);

  useEffect(() => {
    // If device is low-end or reduced motion is preferred, skip animation
    if (isLowEndDevice()) {
      setValue(endValue);
      startValueRef.current = endValue;
      endValueRef.current = endValue;
      return;
    }

    // Only animate if target actually changed
    if (endValue !== endValueRef.current) {
      startValueRef.current = value;
      endValueRef.current = endValue;
    } else if (value === endValue) {
      return; // Already there
    }

    let startTime = null;

    const animate = (time) => {
      if (!startTime) startTime = time;
      const progress = time - startTime;
      
      if (progress < duration) {
        const t = progress / duration;
        const easedT = easeOutCubic(t);
        const currentVal = Math.round(startValueRef.current + (endValueRef.current - startValueRef.current) * easedT);
        setValue(currentVal);
        rafRef.current = requestAnimationFrame(animate);
      } else {
        setValue(endValueRef.current);
        startValueRef.current = endValueRef.current;
      }
    };

    rafRef.current = requestAnimationFrame(animate);

    return () => {
      if (rafRef.current) {
        cancelAnimationFrame(rafRef.current);
      }
    };
  }, [endValue, duration]);

  return value;
}
