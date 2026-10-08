import React, { useState, useEffect, useRef } from 'react';
import { isLowEndDevice, easeOutCubic } from '../config/animation';

export function AnimatedNumber({ value, duration = 800, formatValue = (v) => v }) {
  const [currentValue, setCurrentValue] = useState(value);
  const startValueRef = useRef(value);
  const endValueRef = useRef(value);
  const rafRef = useRef(null);

  useEffect(() => {
    if (isLowEndDevice()) {
      setCurrentValue(value);
      startValueRef.current = value;
      endValueRef.current = value;
      return;
    }

    if (value === endValueRef.current) {
      return;
    }

    startValueRef.current = currentValue;
    endValueRef.current = value;

    let startTime = null;

    const animate = (time) => {
      if (!startTime) startTime = time;
      const progress = time - startTime;
      
      if (progress < duration) {
        const t = progress / duration;
        const easedT = easeOutCubic(t);
        const nextVal = Math.round(startValueRef.current + (endValueRef.current - startValueRef.current) * easedT);
        setCurrentValue(nextVal);
        rafRef.current = requestAnimationFrame(animate);
      } else {
        setCurrentValue(endValueRef.current);
        startValueRef.current = endValueRef.current;
      }
    };

    if (rafRef.current) {
      cancelAnimationFrame(rafRef.current);
    }
    rafRef.current = requestAnimationFrame(animate);

    return () => {
      if (rafRef.current) {
        cancelAnimationFrame(rafRef.current);
      }
    };
  }, [value, duration]);

  return <>{formatValue(currentValue)}</>;
}
