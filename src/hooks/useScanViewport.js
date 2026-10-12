import { useState, useEffect } from 'react';
import { Capacitor } from '@capacitor/core';

export const FULLSCREEN_SCAN_ENABLED = true;
export const SCAN_BACK_BUTTON_INTERCEPT = false; // Data router (createBrowserRouter) does not use manual History API pushState

export const CANVAS_LANDSCAPE = Object.freeze({ width: 640, height: 480 });
export const CANVAS_PORTRAIT = Object.freeze({ width: 480, height: 640 });

export function useScanViewport() {
  const getIsNative = () => {
    try {
      return typeof Capacitor !== 'undefined' && typeof Capacitor.isNativePlatform === 'function'
        ? Capacitor.isNativePlatform()
        : false;
    } catch (_) {
      return false;
    }
  };

  const [state, setState] = useState(() => {
    if (typeof window === 'undefined') {
      return {
        useFullscreen: false,
        isPortrait: false,
        canvasSize: CANVAS_LANDSCAPE,
      };
    }

    const isNative = getIsNative();
    const isMobileWidth = window.matchMedia('(max-width: 768px)').matches;
    const useFullscreen = FULLSCREEN_SCAN_ENABLED && (isNative || isMobileWidth);
    const isPortrait = window.matchMedia('(orientation: portrait)').matches;
    const canvasSize = (useFullscreen && isPortrait) ? CANVAS_PORTRAIT : CANVAS_LANDSCAPE;

    return { useFullscreen, isPortrait, canvasSize };
  });

  useEffect(() => {
    if (typeof window === 'undefined') return;

    const widthQuery = window.matchMedia('(max-width: 768px)');
    const orientationQuery = window.matchMedia('(orientation: portrait)');

    const update = () => {
      const isNative = getIsNative();
      const isMobileWidth = widthQuery.matches;
      const useFullscreen = FULLSCREEN_SCAN_ENABLED && (isNative || isMobileWidth);
      const isPortrait = orientationQuery.matches;
      const canvasSize = (useFullscreen && isPortrait) ? CANVAS_PORTRAIT : CANVAS_LANDSCAPE;

      setState(prev => {
        if (
          prev.useFullscreen === useFullscreen &&
          prev.isPortrait === isPortrait &&
          prev.canvasSize.width === canvasSize.width &&
          prev.canvasSize.height === canvasSize.height
        ) {
          return prev;
        }
        return { useFullscreen, isPortrait, canvasSize };
      });
    };

    update();

    widthQuery.addEventListener('change', update);
    orientationQuery.addEventListener('change', update);

    return () => {
      widthQuery.removeEventListener('change', update);
      orientationQuery.removeEventListener('change', update);
    };
  }, []);

  return state;
}
