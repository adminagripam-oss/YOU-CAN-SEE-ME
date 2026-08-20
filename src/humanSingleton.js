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
    // Paksa FP16 di semua perangkat untuk konsistensi vektor biometrik
    WEBGL_FORCE_F16_TEXTURES: true,
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
