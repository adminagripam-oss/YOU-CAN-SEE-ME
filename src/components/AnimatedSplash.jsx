import React, { useEffect, useState } from 'react';
import './AnimatedSplash.css';
import splashAnimation from '../assets/splash.webp';

export default function AnimatedSplash({ isAppReady, onAnimationComplete }) {
  const [shouldRender, setShouldRender] = useState(true);

  useEffect(() => {
    // Timer 3.5 detik untuk durasi GIF sebelum aplikasi dinyatakan siap
    const timer = setTimeout(() => {
      if (onAnimationComplete) {
        onAnimationComplete();
      }
    }, 3500);

    return () => clearTimeout(timer);
  }, [onAnimationComplete]);

  useEffect(() => {
    if (isAppReady) {
      const cleanupTimer = setTimeout(() => {
        setShouldRender(false);
      }, 500); // 500ms untuk efek fade-out CSS
      return () => clearTimeout(cleanupTimer);
    }
  }, [isAppReady]);

  if (!shouldRender) return null;

  return (
    <div className={`agri-splash-container ${isAppReady ? 'fade-out' : ''}`}>
      <img 
        className="agri-splash-image"
        src={splashAnimation}
        alt="Agriface Splash Screen"
      />
    </div>
  );
}
