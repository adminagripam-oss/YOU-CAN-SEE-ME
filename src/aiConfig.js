// Konfigurasi Standar AI (Human.js) untuk Seluruh Aplikasi Agriface
// Tujuannya agar hasil perhitungan matematika di Laptop (FP32) dan Tablet (FP16) 100% konsisten.

export const getUnifiedHumanConfig = (enableDescriptionAtStart = false) => ({
  modelBasePath: window.location.origin + '/models',

  // Environment flags untuk TensorFlow.js
  env: {
    // Memaksa WebGL menggunakan presisi 16-bit (FP16) di semua perangkat, 
    // termasuk laptop/PC. Ini sangat penting agar vektor 1024-dimensi 
    // yang dihasilkan Laptop dan Tablet bernilai persis sama!
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

    // Iris DINONAKTIFKAN secara global. Selain membebani RAM/GPU tablet (hingga 40%),
    // iris memodifikasi bounding box. Harus seragam mati agar crop wajah sama!
    iris: { enabled: false },

    // Description (penghasil embedding 1024-dimensi).
    // Di tab Absensi, ini mati di awal (Phased Flow) untuk meringankan beban GPU.
    // Di tab Pendaftaran, ini hidup dari awal.
    description: { enabled: enableDescriptionAtStart },
  },

  body: { enabled: false },
  hand: { enabled: false },
  object: { enabled: false },
  gesture: { enabled: false },

  // Paksa menggunakan GPU WebGL (WASM terlalu lambat untuk tablet)
  backend: 'webgl',

  // Warmup sebelum render agar tidak lag di frame pertama
  warmup: 'face',
});
