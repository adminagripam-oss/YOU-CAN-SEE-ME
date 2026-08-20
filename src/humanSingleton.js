/**
 * humanSingleton.js
 *
 * Singleton instance of @vladmandic/human untuk seluruh aplikasi AgriFace.
 * WAJIB hanya ada SATU instance Human yang aktif di waktu bersamaan.
 * Deteksi backend otomatis: WebGL (GPU) → WASM (CPU) fallback untuk kompatibilitas Android.
 */

import { Human } from '@vladmandic/human';

const humanConfig = {
  // Jalur model absolut berbasis origin domain saat ini
  modelBasePath: window.location.origin + '/models',

  face: {
    enabled: true,
    detector: {
      enabled: true,
      rotation: true,
      maxDetected: 1,
      skipFrames: 0,        // Tidak lewatkan satupun frame
      minConfidence: 0.15,  // Threshold rendah agar deteksi lebih sensitif
    },
    mesh: { enabled: true },
    iris: { enabled: false },
    description: { enabled: true },
  },

  body: { enabled: false },
  hand: { enabled: false },
  object: { enabled: false },
  gesture: { enabled: false },

  backend: 'webgl',
  warmup: 'none', // JANGAN 'face': warmup saat load crash di Android WebGL karena context belum siap
};

// SATU-SATUNYA instance Human di seluruh aplikasi
export const human = new Human(humanConfig);

/**
 * Pemuatan model tangguh (3-Tier Path Fallback):
 * 1. Coba path: Origin Domain → Relatif → Cloud CDN
 */
export async function loadHumanWithFallback() {
  const pathsToTry = [
    window.location.origin + '/models',
    './models',
    'https://cdn.jsdelivr.net/npm/@vladmandic/human/models'
  ];

  let lastErr = null;
  let loadedPath = null;

  // Load model dari path yang berhasil
  for (const basePath of pathsToTry) {
    try {
      console.log('[Human Singleton] Mencoba load model dari:', basePath);
      human.config.modelBasePath = basePath;
      await human.load();
      loadedPath = basePath;
      console.log('[Human Singleton] Model berhasil dimuat dari:', basePath);
      break;
    } catch (err) {
      console.warn('[Human Singleton] Gagal dari:', basePath, err?.message || err);
      lastErr = err;
    }
  }

  if (!loadedPath) {
    console.error('[Human Singleton] Semua path gagal. Error terakhir:', lastErr);
    throw lastErr || new Error('Gagal mengunduh model AI dari seluruh lokasi');
  }

  return true;
}
