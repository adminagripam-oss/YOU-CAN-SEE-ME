import { useEffect, useState } from 'react';
import { Capacitor } from '@capacitor/core';

/**
 * Reactive network status hook.
 * Uses @capacitor/network on native (Android/iOS), navigator.onLine events on web.
 */
export function useOnlineStatus() {
  const [isOnline, setIsOnline] = useState(() => navigator.onLine);

  useEffect(() => {
    if (Capacitor.isNativePlatform()) {
      let listenerHandle = null;
      import('@capacitor/network').then(({ Network }) => {
        Network.getStatus().then((s) => setIsOnline(s.connected));
        Network.addListener('networkStatusChange', (s) => setIsOnline(s.connected))
          .then((handle) => { listenerHandle = handle; });
      });
      return () => { listenerHandle?.remove(); };
    }

    const goOnline = () => setIsOnline(true);
    const goOffline = () => setIsOnline(false);
    window.addEventListener('online', goOnline);
    window.addEventListener('offline', goOffline);
    return () => {
      window.removeEventListener('online', goOnline);
      window.removeEventListener('offline', goOffline);
    };
  }, []);

  return isOnline;
}
