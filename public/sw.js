const CACHE_NAME = 'agriface-v3.4.0';

// Install Event - Activate SW immediately
self.addEventListener('install', (event) => {
  self.skipWaiting();
});

// Activate Event - Clear obsolete caches and claim clients immediately
self.addEventListener('activate', (event) => {
  event.waitUntil(
    caches.keys().then((cacheNames) => {
      return Promise.all(
        cacheNames.map((cacheName) => {
          if (cacheName !== CACHE_NAME) {
            console.log('[SW] Deleting old cache:', cacheName);
            return caches.delete(cacheName);
          }
        })
      );
    }).then(() => self.clients.claim())
  );
});

// Fetch Event - Strict BYPASS CACHE for Supabase Cloud, API Endpoints, models, and human.esm.js
self.addEventListener('fetch', (event) => {
  const url = new URL(event.request.url);

  // Direct network pass-through (do NOT intercept with event.respondWith)
  if (url.hostname.includes('supabase.co') || url.pathname.startsWith('/api/') || url.pathname.includes('/models/') || url.pathname.includes('human.esm.js')) {
    return;
  }

  // Network-First for HTML navigation
  if (event.request.mode === 'navigate' || (event.request.headers.get('accept') && event.request.headers.get('accept').includes('text/html'))) {
    event.respondWith(
      fetch(event.request, { cache: 'no-cache' }).catch(() => caches.match(event.request))
    );
    return;
  }

  // Stale-While-Revalidate for static assets (js, css, images)
  event.respondWith(
    caches.match(event.request).then((cachedResponse) => {
      const fetchPromise = fetch(event.request).then((networkResponse) => {
        const contentLength = networkResponse.headers.get('content-length');
        if (
          event.request.method === 'GET' &&
          networkResponse &&
          networkResponse.status === 200 &&
          networkResponse.type === 'basic' &&
          (!contentLength || parseInt(contentLength, 10) > 0)
        ) {
          const responseToCache = networkResponse.clone();
          caches.open(CACHE_NAME).then((cache) => cache.put(event.request, responseToCache));
        }
        return networkResponse;
      }).catch(() => {
        return cachedResponse || new Response('Offline', { status: 503, statusText: 'Service Unavailable' });
      });
      return cachedResponse || fetchPromise;
    })
  );
});
