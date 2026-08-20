const CACHE_NAME = 'face-attendance-v5';
const MODEL_CACHE_NAME = 'human-models-v6';

// Static assets to pre-cache on install
const PRECACHE_ASSETS = [
  './',
  './index.html',
  './manifest.json',
  'https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap',
  'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css',
  'https://cdn.jsdelivr.net/npm/@vladmandic/human/dist/human.js',
  'https://cdn.jsdelivr.net/npm/@mediapipe/camera_utils/camera_utils.js',
  'https://cdn.jsdelivr.net/npm/@mediapipe/face_mesh/face_mesh.js',
  './models/models.json',
  './models/blazeface.json',
  './models/blazeface.bin',
  './models/facemesh.json',
  './models/facemesh.bin',
  './models/iris.json',
  './models/iris.bin',
  './models/faceres.json',
  './models/faceres.bin'
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

  // Skip non-GET requests and non-http(s) schemes (e.g. chrome-extension://)
  if (event.request.method !== 'GET' || !url.protocol.startsWith('http')) {
    return;
  }

  // Bypass cache completely for localhost (Dev Mode)
  if (url.hostname === 'localhost' || url.hostname === '127.0.0.1') {
    return;
  }

  // Bypass cache completely for Supabase requests (Direct DB queries)
  if (url.hostname.includes('supabase.co')) {
    return;
  }

  // Strategy 1: Human Models & MediaPipe CDN Cache (Cache-First)
  if (
    url.href.includes('vladmandic/human') ||
    url.href.includes('mediapipe') ||
    url.href.includes('/model/')
  ) {
    event.respondWith(
      caches.open(MODEL_CACHE_NAME).then((cache) => {
        return cache.match(event.request).then((cachedResponse) => {
          if (cachedResponse) {
            return cachedResponse;
          }
          return fetch(event.request).then((networkResponse) => {
            if (networkResponse && networkResponse.status === 200) {
              const responseToCache = networkResponse.clone();
              cache.put(event.request, responseToCache).catch(() => {});
            }
            return networkResponse;
          }).catch(() => {
            return new Response('Model fetch offline fallback', { status: 503 });
          });
        });
      })
    );
    return;
  }

  // Strategy 2: Network-First for Backend API GET Requests
  if (url.pathname.includes('/api/')) {
    event.respondWith(
      fetch(event.request)
        .catch(() => caches.match(event.request))
        .then((response) => {
          return response || new Response(JSON.stringify({ error: 'Offline', success: false }), {
            status: 503,
            headers: { 'Content-Type': 'application/json' },
          });
        })
    );
    return;
  }

  // Strategy 3: Stale-While-Revalidate for UI Static Assets
  event.respondWith(
    caches.match(event.request).then((cachedResponse) => {
      const fetchPromise = fetch(event.request)
        .then((networkResponse) => {
          if (
            networkResponse &&
            networkResponse.status === 200 &&
            (networkResponse.type === 'basic' || networkResponse.type === 'cors')
          ) {
            const responseToCache = networkResponse.clone();
            caches.open(CACHE_NAME).then((cache) => {
              cache.put(event.request, responseToCache).catch(() => {});
            }).catch(() => {});
          }
          return networkResponse;
        })
        .catch((err) => {
          console.log('[SW Fetch Offline Fallback]', err);
          return null;
        });

      return cachedResponse || fetchPromise.then((res) => res || new Response(null, { status: 404 }));
    })
  );
});
