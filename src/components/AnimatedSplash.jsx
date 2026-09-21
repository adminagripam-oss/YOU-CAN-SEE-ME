import React, { useEffect, useRef } from 'react';
import './AnimatedSplash.css';
import splashVideo from '../../assets/splash screen agriface.mp4';

export default function AnimatedSplash({ isAppReady, onAnimationComplete }) {
  const videoRef = useRef(null);

  useEffect(() => {
    // Optional fallback timeout in case video fails to load or onEnded doesn't fire
    const fallbackTimer = setTimeout(() => {
      if (onAnimationComplete) {
        onAnimationComplete();
      }
    }, 6000); 

    return () => clearTimeout(fallbackTimer);
  }, [onAnimationComplete]);

  const handleVideoEnded = () => {
    if (onAnimationComplete) {
      onAnimationComplete();
    }
  };

  return (
    <div className={`agri-splash-container ${isAppReady ? 'fade-out' : ''}`}>
      <video 
        ref={videoRef}
        className="agri-splash-video"
        src={splashVideo}
        autoPlay 
        muted 
        playsInline 
        onEnded={handleVideoEnded}
      />
    </div>
  );
}
