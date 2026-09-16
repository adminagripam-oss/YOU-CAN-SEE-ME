const CACHE_NAME = 'agriface-v3.3.0';

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

// Fetch Event - Strict BYPASS CACHE for Supabase Cloud and API Endpoints
self.addEventListener('fetch', (event) => {
  const url = new URL(event.request.url);

  // 1. Explicitly BYPASS Service Worker cache for all Supabase Cloud requests (*.supabase.co)
  // and local Express/Serverless backend API requests (/api/) to guarantee fresh cloud data
  if (url.hostname.includes('supabase.co') || url.pathname.startsWith('/api/')) {
    event.respondWith(
      fetch(event.request, { cache: 'no-store' }).catch((err) => {
        console.warn('[SW Network Fallback] Cloud endpoint unreachable (offline):', url.href);
        return new Response(JSON.stringify({ offline: true, error: 'Network unavailable' }), {
          status: 503,
          headers: { 'Content-Type': 'application/json' }
        });
      })
    );
    return;
  }

  // 2. Network-First for HTML navigation
  if (event.request.mode === 'navigate' || (event.request.headers.get('accept') && event.request.headers.get('accept').includes('text/html'))) {
    event.respondWith(
      fetch(event.request, { cache: 'no-cache' }).catch(() => caches.match(event.request))
    );
    return;
  }

  // 3. Stale-While-Revalidate for static assets (js, css, models, images)
  event.respondWith(
    caches.match(event.request).then((cachedResponse) => {
      const fetchPromise = fetch(event.request).then((networkResponse) => {
        if (event.request.method === 'GET' && networkResponse && networkResponse.status === 200 && networkResponse.type === 'basic') {
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
