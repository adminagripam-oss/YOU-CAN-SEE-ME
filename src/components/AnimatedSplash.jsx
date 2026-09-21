import React, { useEffect } from 'react';
import './AnimatedSplash.css';
import splashImage from '../../assets/7.jpg';

export default function AnimatedSplash({ isAppReady, onAnimationComplete }) {
  useEffect(() => {
    // 3 second duration as requested by the user.
    // Adding 0.5s buffer before triggering the completion callback to allow fade out.
    const timer = setTimeout(() => {
      if (onAnimationComplete) {
        onAnimationComplete();
      }
    }, 3500); 

    return () => clearTimeout(timer);
  }, [onAnimationComplete]);

  return (
    <div className={`agri-splash-container ${isAppReady ? 'fade-out' : ''}`}>
      <img 
        className="agri-splash-image"
        src={splashImage}
        alt="Agriface Splash Screen"
      />
    </div>
  );
}
