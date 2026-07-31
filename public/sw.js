const CACHE_NAME = 'face-attendance-v1';
const MODEL_CACHE_NAME = 'face-api-models-v1';

// Static assets to pre-cache on install
const PRECACHE_ASSETS = [
  './',
  './index.html',
  './manifest.json',
  'https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap',
  'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css',
  'https://cdn.jsdelivr.net/npm/@vladmandic/face-api/dist/face-api.js'
];

// Service Worker Install Event (Pre-caching)
self.addEventListener('install', (event) => {
  console.log('[Service Worker] Installing & Pre-caching UI assets...');
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      return cache.addAll(PRECACHE_ASSETS).catch((err) => {
        console.warn('[SW Precache Warning]', err);
      });
    }).then(() => self.skipWaiting())
  );
});

// Service Worker Activate Event (Cleaning old caches)
self.addEventListener('activate', (event) => {
  console.log('[Service Worker] Activating & claiming clients...');
  event.waitUntil(
    caches.keys().then((cacheNames) => {
      return Promise.all(
        cacheNames.map((cache) => {
          if (cache !== CACHE_NAME && cache !== MODEL_CACHE_NAME) {
            console.log('[Service Worker] Deleting old cache:', cache);
            return caches.delete(cache);
          }
        })
      );
    }).then(() => self.clients.claim())
  );
});

// Fetch Event (Offline-First Cache Strategy)
self.addEventListener('fetch', (event) => {
  const url = new URL(event.request.url);

  // Skip non-GET requests (POST/PUT/DELETE for APIs handled by app & IndexedDB queue)
  if (event.request.method !== 'GET') {
    return;
  }

  // Strategy 1: Face-API Models Cache (Cache-First)
  if (url.href.includes('vladmandic/face-api') || url.href.includes('/model/')) {
    event.respondWith(
      caches.open(MODEL_CACHE_NAME).then((cache) => {
        return cache.match(event.request).then((cachedResponse) => {
          if (cachedResponse) {
            return cachedResponse;
          }
          return fetch(event.request).then((networkResponse) => {
            if (networkResponse.status === 200) {
              cache.put(event.request, networkResponse.clone());
            }
            return networkResponse;
          });
        });
      })
    );
    return;
  }

  // Strategy 2: Network-First with Cache Fallback for API GET requests
  if (url.pathname.includes('/api/')) {
    event.respondWith(
      fetch(event.request).catch(() => {
        return caches.match(event.request);
      })
    );
    return;
  }

  // Strategy 3: Stale-While-Revalidate for UI Static Assets
  event.respondWith(
    caches.match(event.request).then((cachedResponse) => {
      const fetchPromise = fetch(event.request).then((networkResponse) => {
        if (networkResponse && networkResponse.status === 200) {
          caches.open(CACHE_NAME).then((cache) => {
            cache.put(event.request, networkResponse.clone());
          });
        }
        return networkResponse;
      }).catch((err) => {
        console.log('[SW Fetch Offline Fallback]', err);
      });

      return cachedResponse || fetchPromise;
    })
  );
});
