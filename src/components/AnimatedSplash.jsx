import React, { useEffect, useState } from 'react';

export default function AnimatedSplash({ isAppReady }) {
  const [show, setShow] = useState(true);
  const [fadeOut, setFadeOut] = useState(false);

  useEffect(() => {
    if (isAppReady) {
      // Start fade out transition
      setFadeOut(true);
      // Remove from DOM after transition completes (0.5s matching CSS)
      const timer = setTimeout(() => {
        setShow(false);
      }, 500);
      return () => clearTimeout(timer);
    }
  }, [isAppReady]);

  if (!show) return null;

  return (
    <div className={`fake-splash-screen ${fadeOut ? 'fade-out' : ''}`}>
      <img src="/icon-foreground.png" alt="AgriFace Logo" className="splash-logo" />
    </div>
  );
}
