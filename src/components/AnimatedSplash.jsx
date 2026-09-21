import React, { useEffect, useState, useRef } from 'react';
import './AnimatedSplash.css';

// Import all SVGs from the sequence folder eagerly
const framesObj = import.meta.glob('../../assets/splash-sequence/*.svg', { eager: true, as: 'url' });

// Sort the extracted frames to guarantee numerical order (e.g. 000.svg, 001.svg, ...)
const frameUrls = Object.keys(framesObj)
  .sort()
  .map((key) => framesObj[key]);

export default function AnimatedSplash({ isAppReady, onAnimationComplete }) {
  const [currentFrameIndex, setCurrentFrameIndex] = useState(0);
  const rafRef = useRef(null);
  const lastTimeRef = useRef(0);
  const currentFrameRef = useRef(0);
  const fps = 30;
  const interval = 1000 / fps;

  useEffect(() => {
    if (frameUrls.length === 0) {
      // Fallback if no frames were loaded
      if (onAnimationComplete) onAnimationComplete();
      return;
    }

    const animate = (time) => {
      if (!lastTimeRef.current) lastTimeRef.current = time;
      
      const deltaTime = time - lastTimeRef.current;
      
      if (deltaTime > interval) {
        lastTimeRef.current = time - (deltaTime % interval);
        currentFrameRef.current += 1;
        
        if (currentFrameRef.current >= frameUrls.length) {
          // Animation finished
          if (onAnimationComplete) {
            onAnimationComplete();
          }
          return; // Stop animation loop
        } else {
          setCurrentFrameIndex(currentFrameRef.current);
        }
      }
      
      rafRef.current = requestAnimationFrame(animate);
    };

    rafRef.current = requestAnimationFrame(animate);

    return () => {
      if (rafRef.current) {
        cancelAnimationFrame(rafRef.current);
      }
    };
  }, [onAnimationComplete, interval]);

  return (
    <div className={`agri-splash-container ${isAppReady ? 'fade-out' : ''}`}>
      {frameUrls.length > 0 && (
        <img 
          className="agri-splash-image"
          src={frameUrls[currentFrameIndex]}
          alt="Agriface Splash Sequence"
        />
      )}
    </div>
  );
}
