/**
 * humanSingleton.js
 *
 * Singleton instance of @vladmandic/human untuk seluruh aplikasi AgriFace.
 * WAJIB hanya ada SATU instance Human yang aktif di waktu bersamaan.
 * Beberapa instance bersamaan menyebabkan konflik backend WebGL TensorFlow.js
 * yang menghasilkan error: "Cannot read properties of undefined (reading 'inputs')".
 */

import { Human } from '@vladmandic/human';

const humanConfig = {
  // Jalur model absolut berbasis origin domain saat ini (https://localhost di APK)
  modelBasePath: window.location.origin + '/models',

  env: {
    // Biarkan Human AI mendeteksi presisi WebGL GPU secara otomatis per-device
  },

  face: {
    enabled: true,
    detector: {
      enabled: true,
      rotation: true,
      maxDetected: 1,
      skipFrames: 5,
      minConfidence: 0.2,
    },
    mesh: { enabled: true },
    iris: { enabled: false },      // Dinonaktifkan: membebani GPU & mengubah bounding box
    description: { enabled: true }, // Diaktifkan default, bisa di-override saat runtime
  },

  body: { enabled: false },
  hand: { enabled: false },
  object: { enabled: false },
  gesture: { enabled: false },

  backend: 'webgl',   // Paksa GPU WebGL (WASM terlalu lambat)
  warmup: 'face',     // Warmup sebelum render agar frame pertama tidak lag
};

// SATU-SATUNYA instance Human di seluruh aplikasi
export const human = new Human(humanConfig);

/**
 * Pemuatan model tangguh (3-Tier Fallback):
 * 1. Coba dari Domain Origin Asli (https://localhost/models)
 * 2. Coba dari Jalur Relatif (./models)
 * 3. Coba dari Cloud CDN JSDelivr (Online Fallback)
 */
export async function loadHumanWithFallback() {
  const pathsToTry = [
    window.location.origin + '/models',
    './models',
    'https://cdn.jsdelivr.net/npm/@vladmandic/human/models'
  ];

  let lastErr = null;
  for (const basePath of pathsToTry) {
    try {
      console.log('[Human Singleton] Attempting model load from:', basePath);
      human.config.modelBasePath = basePath;
      await human.load();
      console.log('[Human Singleton] Models successfully loaded from:', basePath);
      return true;
    } catch (err) {
      console.warn('[Human Singleton] Model load failed from:', basePath, err?.message || err);
      lastErr = err;
    }
  }

  console.error('[Human Singleton] All model load attempts failed. Last error:', lastErr);
  throw lastErr || new Error('Gagal mengunduh berkas model AI dari seluruh lokasi');
}
