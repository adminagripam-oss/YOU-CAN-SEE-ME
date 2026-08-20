/**
 * humanSingleton.js
 *
 * Singleton instance of @vladmandic/human untuk seluruh aplikasi AgriFace.
 * WAJIB hanya ada SATU instance Human yang aktif di waktu bersamaan.
 *
 * ROOT FIX untuk error "Cannot read properties of undefined (reading 'inputs')":
 * TF.js backend harus diinisialisasi SEPENUHNYA (tf.setBackend + tf.ready)
 * sebelum human.load() dipanggil. Jika tidak, WebGL di Android WebView belum
 * siap saat TF.js membangun graph model → tensor undefined → crash 'inputs'.
 */

import { Human } from '@vladmandic/human';

const humanConfig = {
  modelBasePath: window.location.origin + '/models',

  face: {
    enabled: true,
    detector: {
      enabled: true,
      rotation: true,
      maxDetected: 1,
      skipFrames: 0,
      minConfidence: 0.15,
    },
    mesh: { enabled: true },
    iris: { enabled: false },
    description: { enabled: true },
  },

  body: { enabled: false },
  hand: { enabled: false },
  object: { enabled: false },
  gesture: { enabled: false },

  // Backend di-set secara dinamis oleh loadHumanWithFallback()
  backend: 'webgl',
  warmup: 'none',
};

// SATU-SATUNYA instance Human di seluruh aplikasi
export const human = new Human(humanConfig);

/**
 * Step 1: Inisialisasi TF.js backend secara eksplisit SEBELUM load model.
 * Ini adalah FIX UTAMA untuk error "inputs" di Android WebView.
 *
 * Tanpa ini: human.load() memulai inisialisasi WebGL secara ASYNC bersamaan
 * dengan pengunduhan model → race condition → tensor undefined → crash.
 *
 * Dengan ini: kita memastikan WebGL (atau CPU jika WebGL gagal) SUDAH SIAP
 * 100% sebelum satu baris model loading dijalankan.
 */
async function initBackend() {
  const candidates = ['webgl', 'cpu'];

  for (const backend of candidates) {
    try {
      console.log('[TF.js Backend] Mencoba inisialisasi:', backend);
      await human.tf.setBackend(backend);
      await human.tf.ready();           // Blokir hingga GPU/CPU benar-benar siap
      human.config.backend = backend;   // Sync ke human config
      console.log('[TF.js Backend] ✅ Siap menggunakan:', backend);
      return backend;
    } catch (e) {
      console.warn('[TF.js Backend] ❌ Gagal:', backend, '-', e?.message);
    }
  }

  throw new Error('Tidak ada backend TF.js (webgl/cpu) yang berhasil diinisialisasi.');
}

/**
 * Pemuatan model tangguh:
 * 1. Inisialisasi backend TF.js terlebih dahulu (webgl → cpu fallback)
 * 2. Lalu load model dari: Origin Domain → Relatif → CDN
 */
export async function loadHumanWithFallback() {
  // ── Step 1: Pastikan TF.js backend SIAP sebelum apapun ──────────────────
  await initBackend();

  // ── Step 2: Load model dari path yang berhasil ───────────────────────────
  const pathsToTry = [
    window.location.origin + '/models',
    './models',
    'https://cdn.jsdelivr.net/npm/@vladmandic/human/models',
  ];

  let lastErr = null;

  for (const basePath of pathsToTry) {
    try {
      console.log('[Human Singleton] Mencoba load model dari:', basePath);
      human.config.modelBasePath = basePath;
      await human.load();
      console.log('[Human Singleton] ✅ Model berhasil dimuat dari:', basePath);
      return true;
    } catch (err) {
      console.warn('[Human Singleton] ❌ Gagal dari:', basePath, '-', err?.message || err);
      lastErr = err;
    }
  }

  throw lastErr || new Error('Gagal memuat model AI dari semua lokasi yang tersedia.');
}

