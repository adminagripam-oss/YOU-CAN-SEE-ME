/**
 * humanSingleton.js
 *
 * Singleton instance of @vladmandic/human untuk seluruh aplikasi AgriFace.
 *
 * ROOT FIX — "Cannot read properties of undefined (reading 'inputs')":
 * Vite/Rollup saat production build merusak internal TF.js kernel registry
 * di dalam bundle @vladmandic/human. Solusinya: library dimuat LANGSUNG
 * dari /public/human.esm.js via <script type="module"> di index.html,
 * tanpa disentuh Vite. humanSingleton.js hanya membaca hasilnya dari window.
 */

// Human class diambil dari window.HumanLib yang dimuat via index.html
// (bukan dari npm bundle yang di-transformasi Vite/Rollup)
function getHumanClass() {
  if (window.HumanLib?.Human) return window.HumanLib.Human;
  // Fallback: import langsung jika window.HumanLib belum tersedia
  // (hanya terjadi di dev mode — Vite dev server tidak bundle, jadi aman)
  throw new Error('[humanSingleton] window.HumanLib belum tersedia. Pastikan index.html memuat /human.esm.js.');
}

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
    
    // EXPLICIT FIXED: Nonaktifkan semua sub-model yang tidak dipakai 
    // agar human.load() tidak mencari file model (.json/.bin) yang tidak ada di server/APK
    emotion: { enabled: false },
    liveness: { enabled: false },
    antispoof: { enabled: false },
    age: { enabled: false },
    gender: { enabled: false },
  },

  body: { enabled: false },
  hand: { enabled: false },
  object: { enabled: false },
  gesture: { enabled: false },
  segmentation: { enabled: false },

  backend: 'webgl',
  warmup: 'none',
};

// Inisialisasi singleton — Human class dari window (tidak di-bundle Vite)
let _human = null;

function getHuman() {
  if (!_human) {
    const HumanClass = getHumanClass();
    _human = new HumanClass(humanConfig);
  }
  return _human;
}

// Export lazy proxy agar import { human } di file lain tetap bisa dipakai
export const human = new Proxy({}, {
  get(_, prop) {
    const target = getHuman();
    const value = target[prop];
    // PENTING: Bind 'this' ke instance asli (target) jika properti adalah fungsi.
    // Menghindari error "Cannot read from private field" karena konteks 'this' berubah menjadi Proxy.
    if (typeof value === 'function') {
      return value.bind(target);
    }
    return value;
  },
  set(_, prop, value) {
    getHuman()[prop] = value;
    return true;
  }
});

/**
 * Inisialisasi TF.js backend secara eksplisit SEBELUM load model.
 * Memastikan GPU/CPU benar-benar siap sebelum graph model dibangun.
 */
async function initBackend(humanInstance) {
  const candidates = ['webgl', 'cpu'];

  for (const backend of candidates) {
    try {
      console.log('[TF.js Backend] Mencoba inisialisasi:', backend);
      await humanInstance.tf.setBackend(backend);
      await humanInstance.tf.ready();
      humanInstance.config.backend = backend;
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
 * 1. Tunggu window.HumanLib tersedia (loaded dari /human.esm.js)
 * 2. Inisialisasi backend TF.js (webgl → cpu fallback)
 * 3. Load model dari: Origin Domain → Relatif → CDN
 */
export async function loadHumanWithFallback() {
  // Load /human.esm.js dynamically if not already loaded in window
  if (!window.HumanLib?.Human) {
    try {
      console.log('[Human Singleton] Loading human.esm.js dynamically from root...');
      // Menggunakan new Function('return import(...)') untuk menyembunyikan import dari static analyzer Vite
      const importFunc = new Function('return import("/human.esm.js")');
      const mod = await importFunc();
      window.HumanLib = { Human: mod.Human };
      console.log('[Human Singleton] ✅ human.esm.js successfully loaded dynamically.');
    } catch (err) {
      console.error('[Human Singleton] ❌ Failed to load human.esm.js dynamically:', err);
    }
  }

  // Tunggu window.HumanLib siap (maks 10 detik)
  for (let i = 0; i < 100; i++) {
    if (window.HumanLib?.Human) break;
    await new Promise((r) => setTimeout(r, 100));
  }

  if (!window.HumanLib?.Human) {
    throw new Error('Library human.esm.js gagal dimuat dari /human.esm.js. Periksa file public/human.esm.js.');
  }

  const humanInstance = getHuman();

  // ── Step 1: Pastikan TF.js backend SIAP sebelum apapun ──
  await initBackend(humanInstance);

  // ── Step 2: Load model ──
  const pathsToTry = [
    window.location.origin + '/models',
    './models',
    'https://cdn.jsdelivr.net/npm/@vladmandic/human/models',
  ];

  let lastErr = null;

  for (const basePath of pathsToTry) {
    try {
      console.log('[Human Singleton] Mencoba load model dari:', basePath);
      humanInstance.config.modelBasePath = basePath;
      await humanInstance.load();
      console.log('[Human Singleton] ✅ Model berhasil dimuat dari:', basePath);
      return true;
    } catch (err) {
      console.warn('[Human Singleton] ❌ Gagal dari:', basePath, '-', err?.message || err);
      lastErr = err;
    }
  }

  throw lastErr || new Error('Gagal memuat model AI dari semua lokasi yang tersedia.');
}
