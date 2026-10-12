# Perbaikan: Node Mesh Gepeng di Mobile (Fullscreen Scanner)

## Gejala
- Desktop: node mesh mengikuti wajah dengan benar.
- Mobile (mode layar penuh): node mesh terlihat gepeng dan tidak mengikuti bentuk wajah.

## Dugaan penyebab, urut dari yang paling mungkin
Saya belum melihat kode terbaru (`FullscreenScanShell`, `FaceScanSurface`, blok CSS baru di `index.css`, perubahan hook), jadi ini dugaan. Pengukuran pada bagian "Langkah 1" akan memastikannya.

Pada dasarnya mesh digambar di kanvas dengan ukuran internal tertentu (mis. 480×640), lalu CSS meregangkan kanvas itu ke kotak tampilannya. Aturan lama `canvas.overlay-canvas { width:100% !important; height:100% !important }` membuat kanvas selalu mengisi kotaknya. Berbeda dengan `<video>` yang memakai `object-fit: cover` (tidak menggepengkan), kanvas **meregang**. Jadi bila rasio kotak tidak sama dengan rasio kanvas internal, mesh tergepengkan:

| # | Penyebab | Tanda saat diukur |
| :-- | :-- | :-- |
| 1 | Rasio kotak `.scan-surface` tidak terkunci ke rasio kanvas. Spesifikasi CSS saya sebelumnya memakai `max-width` dan `max-height` bersama `aspect-ratio`. Bila keduanya sama-sama membatasi (mis. area stage lebih pendek dari yang dibutuhkan karena header/footer tinggi), rasio kotak rusak. Ini kelemahan spesifikasi saya: "tinggi-tersedia" tidak bisa dihitung dengan CSS murni. | Rasio internal kanvas 0,75 tetapi rasio kotak bukan 0,75. |
| 2 | `canvasSize` tidak sampai ke hook, sehingga kanvas tetap 640×480 landscape sementara kotaknya portrait. | Rasio internal kanvas 1,333 pada kotak portrait. |
| 3 | Kamera dibuat memenuhi seluruh layar HP (mis. 390×844) alih-alih kotak 3:4. | Rasio kotak sekitar 0,46. |
| 4 | Transform sisa (mirror atau scale) pada kanvas atau induknya. | `getComputedStyle(kanvas).transform` bukan identitas atau `scaleX(-1)` sesuai kamera depan. |

## Langkah 1. Ukur dulu (1 menit, tanpa mengubah kode)
Di Chrome desktop: F12, aktifkan Toggle Device Toolbar, atur 390×844, buka scanner, lalu tempel di Console:

```js
(() => {
  const c = document.querySelector('canvas.overlay-canvas');
  const v = document.querySelector('.scan-surface video') || document.querySelector('video');
  const r = c.getBoundingClientRect();
  console.log({
    canvasInternal: [c.width, c.height],
    ratioInternal: +(c.width / c.height).toFixed(3),
    cssBox: [Math.round(r.width), Math.round(r.height)],
    ratioBox: +(r.width / r.height).toFixed(3),
    video: [v?.videoWidth, v?.videoHeight],
    transform: getComputedStyle(c).transform,
  });
})();
```

Cara membaca:
- `ratioInternal` ≈ `ratioBox`: rasio benar, penyebabnya bukan peregangan. Kirim screenshot dan hasilnya.
- `ratioInternal` = 1,333 dan kotak portrait: penyebab #2.
- `ratioInternal` = 0,75 tetapi `ratioBox` jauh dari 0,75: penyebab #1 atau #3.

---

## PROMPT UNTUK AI (salin dari sini)

```
KONTEKS
Scanner layar penuh mobile (FullscreenScanShell + FaceScanSurface) sudah jalan. Masalah baru: node mesh wajah terlihat GEPENG di mobile,
sedangkan di desktop mengikuti wajah dengan benar.

Fakta kode:
- useNormalizedFaceMesh menggambar mesh di kanvas overlay dengan ukuran internal = canvasSize (desktop/landscape 640x480, mobile portrait 480x640).
- index.css punya aturan lama: canvas.overlay-canvas { width:100% !important; height:100% !important; z-index:4 !important }.
  Artinya kanvas SELALU diregangkan mengikuti kotak induknya. <video> memakai object-fit: cover (tidak gepeng), kanvas tidak.
- Karena itu mesh hanya benar bila rasio kotak tampilan == rasio ukuran internal kanvas. Jika berbeda, mesh teregang/gepeng.

ATURAN WAJIB
1. Jangan ubah logika biometrik (embedding, cosine, threshold, median, isGoodFrame, anti-duplikasi, antrean, GPS, sinkronisasi).
2. Jangan ubah aturan CSS lama (.overlay-canvas, .webcam-wrapper, video). Perbaiki di komponen/kelas baru (.scan-surface, .scan-shell-stage).
3. Jangan menambah dependensi. Jangan menambah setState per frame.
4. Desktop (non-fullscreen, inline) tidak boleh berubah.
5. Jangan menebak. Laporkan angka ukur sebelum mengubah apa pun, lalu perbaiki sesuai penyebab yang terbukti.
6. Jangan mengklaim "sudah diuji di perangkat" bila tidak bisa dijalankan; tulis "tidak bisa diuji" beserta alasannya.

LANGKAH 1. DIAGNOSIS (BACA SAJA, laporkan, lalu lanjut ke Langkah 2 sesuai temuan)
Tambahkan sementara log pengukuran (hapus setelah selesai) atau jalankan di konsol saat scanner terbuka pada viewport 390x844 portrait:
  - canvas.width, canvas.height, rasio internal
  - getBoundingClientRect() kanvas overlay dan elemen .scan-surface, rasio kotak
  - video.videoWidth, video.videoHeight
  - getComputedStyle(kanvas).transform dan transform semua induknya sampai .scan-shell
  - nilai canvasSize yang diterima useNormalizedFaceMesh (hook) dan yang dipakai FaceFrameOverlay (width/height)
  - isPortrait dan useFullscreen dari useScanViewport
  - aturan CSS final yang berlaku untuk .scan-surface dan .scan-shell-stage (width, height, max-width, max-height, aspect-ratio)
Laporkan: rasio internal vs rasio kotak, dan mana dari penyebab berikut yang terbukti:
  A. canvasSize tidak sampai ke hook (kanvas tetap 640x480 pada kotak portrait)
  B. rasio .scan-surface tidak terkunci (max-width/max-height + aspect-ratio saling membatasi, atau stage memaksa 100% x 100%)
  C. kamera/kanvas dibuat memenuhi seluruh layar HP (rasio ~0.46) alih-alih kotak dengan rasio kanvas
  D. transform sisa pada kanvas/induknya
  E. penyebab lain (jelaskan)

LANGKAH 2. PERBAIKAN
2.1 Kunci rasio kotak dengan JavaScript (deterministik, tidak bergantung dukungan CSS di WebView lama):
  - Di FaceScanSurface (atau pembungkusnya), ukur area stage dengan ResizeObserver (lebar dan tinggi yang tersedia).
  - Hitung ukuran pas: scale = Math.min(stageW / canvasSize.width, stageH / canvasSize.height);
    boxW = Math.floor(canvasSize.width * scale); boxH = Math.floor(canvasSize.height * scale).
  - Terapkan ke .scan-surface sebagai width/height piksel (inline style) SEKALIGUS. Hapus max-width/max-height dan aspect-ratio yang
    saling membatasi pada .scan-surface. Rasio kotak HARUS sama dengan canvasSize.width / canvasSize.height (selisih < 0,5%).
  - Hasilnya dipusatkan di stage (flex center). Ruang sisa berwarna hitam. Jangan mengisi kotak ke seluruh layar HP.
  - Perbarui saat rotasi layar atau perubahan ukuran viewport (ResizeObserver menangani ini). Gunakan state hanya untuk ukuran kotak
    (berubah jarang), bukan per frame.
2.2 Pastikan canvasSize benar-benar sampai ke tiga tempat yang sama, dengan nilai identik:
  (1) opsi canvasSize pada useNormalizedFaceMesh, (2) width/height FaceFrameOverlay, (3) frameRectRef/getFrameRect.
  Bila penyebab A terbukti, perbaiki jalur props/ref sehingga hook membaca canvasSize terbaru (lewat ref, tanpa restart kamera).
2.3 Pastikan tidak ada transform sisa: kanvas mesh dan video hanya boleh memakai scaleX(-1) saat facingMode === 'user' (dan scaleX(1) selain itu).
    Tidak boleh ada scale/translate lain pada .scan-surface atau induknya yang memengaruhi kanvas (mis. dari animasi masuk shell:
    gunakan opacity saja, bukan transform yang menetap).
2.4 Tambahkan pemeriksaan pengembangan (hanya saat import.meta.env.DEV): bila |rasioKotak - rasioInternal| / rasioInternal > 0,01,
    console.warn('[SCAN] Rasio kotak != rasio kanvas, mesh akan terdistorsi', {...ukuran}). Throttle maksimal sekali per detik.

LANGKAH 3. VERIFIKASI
  1. Pada 390x844 portrait: rasio kotak == rasio internal (0,75) dan mesh mengikuti bentuk wajah (tidak gepeng).
  2. Pada 360x640, 412x915, dan tablet 768x1024: sama.
  3. Landscape 844x390: kanvas 640x480, kotak 4:3, mesh normal.
  4. Rotasi saat scanner terbuka: kanvas beralih ukuran, kotak mengikuti, mesh normal, kamera tidak restart.
  5. Kamera depan (mirror) dan belakang: mesh sejajar dengan wajah di kedua kamera; label bingkai tidak terbalik.
  6. Bingkai tetap (FaceFrameOverlay) tampak persegi sempurna (lebar == tinggi) di layar. Ukur piksel dan laporkan.
  7. Desktop inline: tidak berubah.
  8. `npm run build` dan `npm test` lulus. Tidak ada dependensi baru.

FORMAT LAPORAN
  1. Hasil Langkah 1 (angka ukur dan penyebab yang terbukti).
  2. Diff per file.
  3. Hasil Langkah 3 per poin (LULUS / GAGAL / TIDAK BISA DIUJI + alasan).
```
