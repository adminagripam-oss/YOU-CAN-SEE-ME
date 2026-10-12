# Scanner Layar Penuh (Fullscreen) untuk Mobile: Rencana dan Prompt

Berlaku untuk: absensi check-in/check-out, pendaftaran karyawan, dan scan ulang wajah.

Dokumen ini terdiri dari tiga bagian:
- **Bagian A**: asumsi default dan temuan dari kode Anda (untuk Anda baca).
- **Bagian B**: rencana kerja bertahap dengan gerbang berhenti.
- **Bagian C**: prompt lengkap untuk disalin ke AI coding assistant Anda.

---

# BAGIAN A. ASUMSI DAN TEMUAN

## A1. Asumsi default (belum Anda jawab, jadi saya tetapkan)
Semua asumsi ini ditulis sebagai konstanta di satu tempat sehingga mudah diubah.

| # | Keputusan | Nilai default |
| :-- | :-- | :-- |
| 1 | Di mana alur layar penuh dipakai | APK Capacitor (native) **atau** lebar layar ≤ 768px. Desktop tidak berubah sama sekali. |
| 2 | Orientasi | Mengikuti perangkat, tidak dikunci. Portrait memakai kanvas 480×640, landscape memakai 640×480. |
| 3 | Setelah berhasil | Bingkai hijau + centang 1,5 detik, lalu scanner menutup otomatis. |
| 4 | Setelah gagal | Scanner tetap terbuka, bingkai merah 1,5 detik, lalu scan ulang otomatis. |
| 5 | Kamera default | Tidak diubah (`environment` di absensi). Tombol "Ganti Kamera" pindah ke header scanner. |
| 6 | Mode kios `/absensi` | Memakai alur tombol yang sama. Properti `autoOpen` opsional, default `false`. Filenya belum saya lihat. |
| 7 | Plugin native baru | **Tidak ada.** Perubahan hanya JS/CSS sehingga bisa dikirim lewat OTA Capgo tanpa rilis APK baru. |

## A2. Temuan dari kode Anda dan dampaknya

| # | Temuan (dari file yang Anda kirim) | Dampak | Penanganan |
| :-- | :-- | :-- | :-- |
| 1 | `useNormalizedFaceMesh.ts` memaksa kanvas 640×480 dan Center Crop 4:3. | Di HP portrait, video tinggi dipotong menjadi pita landscape. Ini sebab utama tampilan terasa kecil dan terpotong. | Tambah opsi `canvasSize` pada hook. Portrait memakai 480×640 (3:4). |
| 2 | `active: modelsLoaded` pada hook. | Kamera dan model menyala terus selama halaman terbuka. | Aktif hanya saat scanner terbuka (di mode layar penuh). |
| 3 | `onFaceProcessed` memakai `useCallback(..., [])` dan `FRAME_RECT` konstanta modul 640×480. | Bila ukuran kanvas berubah, `FRAME_RECT` basi. | Simpan di ref (`frameRectRef`) dan baca dari ref. |
| 4 | Ref sesi (`isMatchedRef`, `livenessVerifiedRef`, `hasBeepedRef`, `hasAutoSubmittedRef`, `scoreHistoryRef`, `isStableRef`) hanya direset di `handleEmployeeSelect`. | Jika scanner dibuka-tutup tanpa ganti karyawan, nilai lama terbawa, sehingga bisa **auto-submit seketika** atau macet. | Ekstrak `resetScanSession()`, panggil saat buka dan tutup scanner. |
| 5 | Setelah submit gagal, `isMatchedRef` tetap `true`, padahal blok pencocokan hanya berjalan bila `!isMatchedRef`. | Tidak ada retry otomatis. Di layar penuh tidak ada tombol manual, jadi pengguna terjebak. | Pada kegagalan, tampilkan bingkai error lalu `resetScanSession()`. |
| 6 | `lightingWarning` diset sama dengan `livenessStatusMsg`, dan keduanya dirender sebagai badge. | Dua badge dengan teks sama (terlihat di screenshot Anda). | Di layar penuh render **satu** petunjuk saja. |
| 7 | z-index di CSS: topbar 20, sidebar mobile 80, sheet 100/110, toast 9999, dialog shadcn 50000/50001. | Scanner di 9000 akan tertutup dialog (mis. form karyawan) dan menutupi toast. | Shell di 50500, `.toast-container` dinaikkan ke 60000. |
| 8 | `@capacitor/app` tidak ada di `package.json`. | Tombol Back Android tidak bisa diintersep lewat plugin tanpa rilis APK baru. | Pakai History API, bersifat best-effort. |
| 9 | `.grid-2` + `.glass-card` (padding 1,5rem) + `.dashboard-content-outlet` (padding 1rem). `.webcam-wrapper` didefinisikan dua kali, plus inline `aspectRatio: '4/3'`. | Lebar kamera di HP hanya sekitar 300px. | Scanner dirender lewat portal ke `document.body` dengan kelas CSS baru, tidak mengubah aturan lama. |
| 10 | Status selain "Hadir" (Izin/Sakit/Mangkir) tidak memakai kamera. | Jika tombol utama selalu membuka scanner, status ini rusak. | Untuk status selain Hadir, tombol tetap submit langsung seperti sekarang. |
| 11 | Impor yang Anda pakai: `'../FaceFrameTemplate'`, jadi file berada di `src/`, bukan `src/components/`. | Jalur impor salah bila AI mengikuti dokumen lama. | Ikuti lokasi sebenarnya di repo. |
| 12 | `package.json` hanya punya script `build` dan `test`, tanpa `lint`. | Prompt sebelumnya menyebut lint, padahal tidak ada. | Verifikasi dengan `npm run build` dan `npm test`. |
| 13 | Ada `@capgo/capacitor-updater`. | Penambahan plugin native tidak bisa lewat OTA. | Jangan menambah dependensi native. |

## A3. Yang belum bisa saya pastikan
- Isi `FaceMeshScanner.tsx` dan layar pendaftaran/scan ulang yang asli.
- `index.html` (apakah ada `viewport-fit=cover`).
- Tipe router (deklaratif atau data router), karena memengaruhi cara menangani tombol Back.
- Isi `biometrics.js` (nama `ENROLL_SAMPLE_COUNT`, `buildTemplate` hanya dari klaim rangkuman AI sebelumnya).
- File mode kios `/absensi`.

Karena itu prompt di Bagian C **dimulai dengan Fase 0 (baca saja)** dan berhenti untuk melapor sebelum mengubah apa pun.

---

# BAGIAN B. RENCANA KERJA

| Fase | Isi | Gerbang sebelum lanjut |
| :-- | :-- | :-- |
| **0. Discovery** | Baca semua file terkait, jawab daftar pertanyaan, **tanpa mengubah file**. | AI melapor. Anda konfirmasi bila ada temuan yang bertentangan dengan asumsi. |
| **1. Fondasi** | Opsi `canvasSize` di hook, `FaceFrameTemplate` dinamis, `useScanViewport`. | `npm run build` lulus. Desktop identik dengan sebelumnya. |
| **2. Komponen bersama** | `FullscreenScanShell`, `FaceScanSurface`, CSS, z-index. | Build lulus. Shell bisa dibuka dan ditutup dengan konten uji. |
| **3. Absensi** | Pasang di `TabFaceVerification.jsx`. | Uji check-in dan check-out di HP/APK. |
| **4. Pendaftaran dan scan ulang** | Pasang di layar asli hasil Fase 0. | Uji daftar dan scan ulang, termasuk penolakan duplikat. |
| **5. Pengerasan dan QA** | Edge case, matriks uji, regresi biometrik, laporan. | Semua kriteria penerimaan terjawab (lulus, gagal, atau tidak bisa diuji). |

Cadangan mundur: satu flag `FULLSCREEN_SCAN_ENABLED` di `useScanViewport.js`. Bila `false`, semua layar kembali ke perilaku inline lama.

---

# BAGIAN C. PROMPT (SALIN DARI SINI)

Lampirkan: `TabFaceVerification.jsx`, `useNormalizedFaceMesh.ts`, `FaceFrameTemplate.jsx`, `index.css`, `package.json`, `PRD.md`, `FaceMeshScanner.tsx`, serta file layar Daftar Karyawan dan Pendaftaran Karyawan bila ada.

```
PERAN
Kamu adalah senior frontend engineer (React 19, Vite 8, Capacitor 8, Canvas 2D, @vladmandic/human).
Kerjakan perubahan UI scan wajah di proyek AgriFace TANPA mengubah logika biometrik.

TUJUAN
Di perangkat mobile (APK Capacitor atau lebar layar <= 768px), scan wajah dimulai dengan sebuah TOMBOL.
Tombol itu membuka scanner LAYAR PENUH. Kamera hanya menyala selama scanner terbuka.
Berlaku untuk empat alur:
  1. Absensi check-in      (TabFaceVerification.jsx, mode "checkin")
  2. Absensi check-out     (TabFaceVerification.jsx, mode "checkout")
  3. Pendaftaran karyawan  (mode "register")
  4. Scan ulang wajah      (mode "rescan")
Semua alur memakai bingkai tetap (FaceFrameOverlay) yang sama dan komponen layar penuh yang sama.
Desktop (layar > 768px, non-APK) TIDAK BOLEH berubah perilakunya.

ATURAN WAJIB
1. Kerjakan per FASE. Setelah Fase 0 BERHENTI dan laporkan. Jangan lanjut ke Fase 1 sebelum saya menjawab.
2. Jangan menebak. Jika sesuatu tidak ditemukan atau berbeda dari yang tertulis di sini, hentikan bagian itu, laporkan, dan tanyakan.
3. Jangan ubah logika biometrik: embedding 1024-D, cosine, BIOMETRIC_CONFIG, median multi-frame, threshold, isGoodFrame,
   presisi FP16 WebGL, anti-duplikasi registrasi, antrean offline, GPS, sinkronisasi, struktur data ke SQLite/Supabase.
4. Jangan ubah perilaku liveness. TabFaceVerification saat ini auto-pass (passed = true). Jangan menambah petunjuk "kedipkan mata".
5. Jangan menambah setState per frame. Status bingkai dikirim lewat useRef (frameStateRef).
6. Jangan menambah dependensi baru, terutama plugin Capacitor/native. Aplikasi memakai @capgo/capacitor-updater (OTA),
   dan plugin native baru tidak bisa dikirim lewat OTA.
7. Jangan ubah aturan CSS lama (.webcam-wrapper, .overlay-canvas, video, .glass-card, dll). Tambahkan kelas BARU di akhir index.css.
8. Ubah seminimal mungkin. Jangan merapikan atau menulis ulang kode di luar cakupan.
9. Ikuti lokasi file yang sebenarnya di repo. Contoh: TabFaceVerification mengimpor '../FaceFrameTemplate', jadi file itu ada di src/.
10. Verifikasi dengan `npm run build` dan `npm test`. Tidak ada script lint, jangan mengklaim lint lulus.
11. Jangan mengklaim sesuatu "sudah diuji" bila tidak bisa dijalankan (kamera, GPS, perangkat fisik). Tulis "tidak bisa diuji" beserta alasannya.

ASUMSI DEFAULT (implementasikan sebagai konstanta yang mudah diubah di src/hooks/useScanViewport.js)
  FULLSCREEN_SCAN_ENABLED = true
  Alur layar penuh aktif bila: Capacitor.isNativePlatform() ATAU window.matchMedia('(max-width: 768px)').matches
  CANVAS_LANDSCAPE = { width: 640, height: 480 }
  CANVAS_PORTRAIT  = { width: 480, height: 640 }
  Kanvas portrait HANYA dipakai bila alur layar penuh aktif DAN orientasi layar portrait. Selain itu selalu landscape 640x480.
  Setelah sukses: tampil sukses 1500 ms lalu scanner menutup otomatis.
  Setelah gagal: tampil error 1500 ms lalu resetScanSession() dan scan ulang, scanner tetap terbuka.
  SCAN_BACK_BUTTON_INTERCEPT = true  (lihat bagian Tombol Back)

FAKTA TENTANG KODE SAAT INI (sudah dibaca dari file; verifikasi ulang di Fase 0)
- useNormalizedFaceMesh.ts: STD_WIDTH=640, STD_HEIGHT=480 (konstanta). processFrame: Center Crop ke offscreen canvas 640x480,
  memanggil detectFaces(offscreen, timestamp, video), memaksa kanvas overlay ke 640x480, lalu memanggil onFaceProcessed
  dengan { detection, smoothedMesh, normalizedMesh, boundingBox, ctx }. boundingBox = {minX,minY,maxX,maxY,width,height,centerX,centerY}
  dihitung dari mesh dalam ruang kanvas. Prop `active` mengontrol getUserMedia. Constraint kamera: width ideal 640, height ideal 480.
  Callback disimpan di callbacksRef sehingga tidak memicu restart kamera.
- TabFaceVerification.jsx: useNormalizedFaceMesh({ active: modelsLoaded, facingMode, detectFaces, onFaceProcessed, onNoFace, onCameraError }).
  onFaceProcessed memakai useCallback(..., []) dan membaca FRAME_RECT = getFrameRect(640,480) (konstanta modul).
  Auto-submit dipicu SEKALI di dalam onFaceProcessed saat matched dan selectedStatusRef === 'Hadir' (hasAutoSubmittedRef).
  Blok pencocokan hanya berjalan bila !isMatchedRef.current.
  frameStateRef + setFrame(state, progress) sudah ada (setFrame tidak menimpa bila hasAutoSubmittedRef.current === true).
- Overlay mesh (canvas.overlay-canvas) di-mirror dengan scaleX(-1) saat facingMode === 'user'. FaceFrameOverlay TIDAK di-mirror.
- z-index di CSS: .app-topbar 20; sidebar mobile 80; [data-slot=sheet-overlay] 100; [data-slot=sheet-content] 110;
  .toast-container 9999; .shadcn-overlay 50000; .shadcn-content 50001; mesh canvas 4; FaceFrameOverlay 5; badge 10; GPS badge 15; .loading-overlay 30.
- body: display flex, flex-direction column, overflow-x hidden. .grid-2 = auto-fit minmax(320px,1fr).

====================================================================
FASE 0. DISCOVERY (BACA SAJA, JANGAN UBAH APA PUN)
====================================================================
Jalankan dan laporkan hasilnya dengan nama file dan nomor baris:
  grep -rnE "useNormalizedFaceMesh|getUserMedia|human\.detect|FaceMeshScanner|FaceFrameOverlay|FaceFrameTemplate" src
  grep -rniE "re-?scan|daftar.?ulang|master_descriptors|descriptor_json|ENROLL_SAMPLE_COUNT|buildTemplate" src
  grep -rnE "BrowserRouter|HashRouter|createBrowserRouter|createHashRouter|RouterProvider" src
  grep -rnE "z-?index|zIndex" src --include=*.jsx --include=*.tsx --include=*.css
  grep -rnE "computeCenterCrop|computeBoundingBox|STD_WIDTH|STD_HEIGHT" src
Baca juga: index.html, src/biometrics.*, route/halaman mode kios (/absensi).

Jawab setiap poin di bawah (jangan dilewati):
 a. Daftar SEMUA komponen yang membuka kamera atau memanggil human.detect. Untuk tiap komponen: route/tab/modal yang
    memakainya, dan apakah benar-benar dirender (bukan kode mati).
 b. Layar mana yang merupakan PENDAFTARAN karyawan, dan mana yang SCAN ULANG di tab Daftar Karyawan? Bagaimana keduanya dipicu
    (tombol, modal, route)? Apakah FaceMeshScanner.tsx dirender di salah satunya?
 c. Apakah layar pendaftaran/scan ulang memakai pipeline yang sama dengan absensi (kanvas hasil crop dari
    useNormalizedFaceMesh + human.detect)? Bila tidak, laporkan perbedaannya. JANGAN menyatukan pipeline tanpa konfirmasi.
 d. Apakah layar-layar itu berada DI DALAM dialog shadcn / Radix / sheet? Sebutkan z-index modal tersebut.
 e. Tipe router: BrowserRouter/HashRouter (deklaratif) atau data router (createBrowserRouter)?
 f. Apakah index.html memuat <meta name="viewport" ... viewport-fit=cover>?
 g. Siapa lagi yang memakai computeCenterCrop, computeBoundingBox, STD_WIDTH, STD_HEIGHT selain hook?
 h. Apakah nama ENROLL_SAMPLE_COUNT, buildTemplate, isGoodFrame, BIOMETRIC_CONFIG.SCAN_SAMPLE_COUNT benar-benar ada, dan di file mana?
 i. Di mana mode kios (/absensi, tanpa login) dan komponen apa yang dipakainya?
 j. Apakah ada perbedaan antara fakta di bagian "FAKTA TENTANG KODE SAAT INI" dan kode sebenarnya? Sebutkan.
Setelah itu BERHENTI. Tulis laporan Fase 0 dan tunggu jawaban saya.

====================================================================
FASE 1. FONDASI (tidak boleh mengubah perilaku desktop)
====================================================================
1.1 useNormalizedFaceMesh.ts
  - Tambah opsi OPSIONAL pada Config: canvasSize?: { width: number; height: number }. Default { width: 640, height: 480 }.
  - Simpan di ref (canvasSizeRef.current = canvasSize || default), diperbarui tiap render.
  - Ganti pemakaian STD_WIDTH, STD_HEIGHT, dan TARGET_RATIO DI DALAM processFrame dengan nilai dari canvasSizeRef.current:
      ukuran offscreen canvas, rasio Center Crop (target = width/height), tujuan drawImage, ukuran kanvas overlay,
      dan semua clearRect(0, 0, w, h).
  - JANGAN menambahkan canvasSize ke dependency processFrame atau useEffect kamera. Kamera tidak boleh restart saat orientasi berubah.
  - Saat ukuran berubah dibanding frame sebelumnya: reset smoothedMeshRef.current = null dan oneEuroFiltersRef.current = [] SEKALI.
  - Constraint getUserMedia dibiarkan (ideal 640x480). Center Crop menangani stream portrait maupun landscape.
  - Konstanta lama (STD_WIDTH/STD_HEIGHT/TARGET_ASPECT) dan fungsi yang diekspor tetap ada (dipakai file lain bila ada).
  - Tanpa canvasSize, hasilnya HARUS identik dengan perilaku sekarang.

1.2 FaceFrameTemplate.jsx (lokasi sesuai repo)
  - getFrameRect(width, height, opts): bila height > width (portrait) default sizeRatio = 0.80 dan offsetY = -0.05;
    selain itu tetap sizeRatio = 0.72 dan offsetY = -0.02. Opsi eksplisit menimpa default.
  - evaluateFaceInFrame: ubah batas ukuran dari rasio ke piksel, mengukur LEBAR boundingBox (sama dengan logika lama
    TabFaceVerification yang memakai boundingBox.width): terlalu jauh bila width < 140; terlalu dekat bila width > rect.size * 0.95.
    Pengecekan off-center dan fullyIn tetap. Tujuannya mempertahankan batas 140px dari kode lama.
  - FaceFrameOverlay dan drawFaceFrame sudah menerima width/height; pastikan semua perhitungan memakai width/height yang diberikan,
    bukan FRAME_W/FRAME_H. FRAME_W/FRAME_H hanya default prop.

1.3 src/hooks/useScanViewport.js (BARU)
  Ekspor: FULLSCREEN_SCAN_ENABLED, CANVAS_LANDSCAPE, CANVAS_PORTRAIT (objek modul konstan agar referensinya stabil),
  dan hook useScanViewport() yang mengembalikan { useFullscreen, isPortrait, canvasSize }.
  - useFullscreen = FULLSCREEN_SCAN_ENABLED && (Capacitor.isNativePlatform() || matchMedia('(max-width: 768px)').matches)
  - isPortrait dari matchMedia('(orientation: portrait)')
  - canvasSize = (useFullscreen && isPortrait) ? CANVAS_PORTRAIT : CANVAS_LANDSCAPE
  - Dengarkan perubahan lewat addEventListener('change', ...) pada MediaQueryList, dan bersihkan saat unmount.
  - Tidak boleh men-trigger render per frame.

Gerbang Fase 1: `npm run build` lulus. Jelaskan bukti bahwa desktop tidak berubah (mis. nilai canvasSize default = 640x480
dan getFrameRect(640,480) menghasilkan nilai yang sama seperti sebelum perubahan).

====================================================================
FASE 2. KOMPONEN BERSAMA
====================================================================
2.1 FullscreenScanShell (src/components/FullscreenScanShell.jsx, BARU)
  Props: open, onClose, closeDisabled, mode ('register'|'rescan'|'checkin'|'checkout'), title, subtitle, hint, hintTone
         ('info'|'warn'|'error'|'success'), headerExtra (node, mis. tombol Ganti Kamera), footer (node), children.
  Perilaku:
  - return null bila !open. Render dengan createPortal(..., document.body). WAJIB sinkron: tidak boleh menunda mount
    lewat useEffect/state, agar <video> sudah ada di DOM pada render yang sama saat `active` hook menjadi true
    (videoRef.current tidak boleh null saat getUserMedia selesai).
  - role="dialog" aria-modal="true", aria-label dari title.
  - Kunci scroll body saat terbuka (document.body.style.overflow = 'hidden'), kembalikan nilai semula saat tutup/unmount.
  - Tutup dengan tombol X (ikon dari lucide-react), tombol dinonaktifkan bila closeDisabled. Tombol min 44x44px.
  - Tutup otomatis bila document.visibilityState menjadi 'hidden'.
  - Tombol Back (bila SCAN_BACK_BUTTON_INTERCEPT): lihat bagian "Tombol Back".
  - Tampilkan hint dalam SATU elemen (bukan dua badge), warna mengikuti hintTone.
  - Latar #000, teks putih, tidak mengikuti tema terang/gelap.
  - Area atas memakai padding env(safe-area-inset-top); area bawah env(safe-area-inset-bottom).
  - z-index 50500. Sesuaikan: harus lebih tinggi dari z-index modal tertinggi yang ditemukan di Fase 0 (d).
  - Naikkan .toast-container ke z-index 60000 agar toast tetap terlihat di atas scanner.

2.2 FaceScanSurface (src/components/FaceScanSurface.jsx, BARU)
  Props: videoRef, canvasRef, canvasSize, facingMode, mode, frameStateRef, modelsLoaded, modelStatusText.
  Render:
    <div className="scan-surface" style={{ aspectRatio: `${w} / ${h}` }}>
      <video ref={videoRef} autoPlay muted playsInline style={{ transform: facingMode === 'user' ? 'scaleX(-1)' : 'scaleX(1)' }} />
      <canvas ref={canvasRef} className="overlay-canvas" style={{ transform: facingMode === 'user' ? 'scaleX(-1)' : 'scaleX(1)' }} />
      <FaceFrameOverlay mode={mode} stateRef={frameStateRef} width={w} height={h} />
      {!modelsLoaded && <div className="loading-overlay"><div className="spinner" /><span>{modelStatusText}</span></div>}
    </div>
  Video dan kanvas mesh di-mirror HANYA bila facingMode === 'user'. FaceFrameOverlay tidak pernah di-mirror.

2.3 CSS (TAMBAHKAN di akhir index.css, jangan ubah aturan lama)
  .scan-shell {
    position: fixed; inset: 0; z-index: 50500; background: #000; color: #fff;
    display: flex; flex-direction: column; height: 100dvh;
    padding-top: env(safe-area-inset-top); padding-bottom: env(safe-area-inset-bottom);
  }
  .scan-shell-header { display: flex; align-items: center; justify-content: space-between; gap: 8px; padding: 8px 12px; min-height: 52px; }
  .scan-shell-stage { flex: 1; min-height: 0; display: flex; align-items: center; justify-content: center; }
  .scan-shell-footer { padding: 10px 14px 14px; display: flex; flex-direction: column; gap: 8px; }
  .scan-surface { position: relative; max-width: 100%; max-height: 100%; background: #000; overflow: hidden; }
  Ukuran .scan-surface: isi layar sebanyak mungkin tanpa memotong kanvas. Gunakan lebar = min(100vw, tinggi-tersedia * rasio)
  dan aspect-ratio dari inline style. Pastikan di HP 390x844 portrait lebarnya penuh (390px) dan tingginya 520px,
  dan di landscape tingginya mengisi area stage.
  Teks hint: min 16px, tebal, kontras tinggi.
  Catatan: aturan global `video { width:100%; height:100%; object-fit: cover }` sudah ada dan cocok karena rasio box = rasio kanvas.

2.4 Tombol Back (best-effort, fail-safe)
  Tidak ada plugin @capacitor/app, jadi pakai History API. Kerjakan HANYA sesuai tipe router hasil Fase 0 (e):
  - Router deklaratif: saat open -> history.pushState({ agrifaceScan: true }, ''). Listener 'popstate': bila scanner terbuka dan
    event berasal dari entri kita, panggil onClose() sekali (gunakan ref penanda agar tidak double-close).
    Saat ditutup lewat X atau sukses: bila history.state?.agrifaceScan masih ada, panggil history.back() dan tandai ref agar listener
    popstate mengabaikan event itu.
  - Data router (createBrowserRouter): JANGAN menerapkan. Laporkan, set SCAN_BACK_BUTTON_INTERCEPT = false.
  - WAJIB diuji: Back menutup scanner TANPA mengubah route dan TANPA me-remount halaman. Bila ada efek samping, matikan flag
    dan biarkan scanner hanya bisa ditutup lewat X (lapor).
  - Esc: bila scanner berada di dalam dialog induk, Esc hanya boleh menutup scanner, bukan dialog induk (uji).

Gerbang Fase 2: `npm run build` lulus; shell dapat dibuka/ditutup dengan konten uji; z-index terbukti lebih tinggi dari dialog.

====================================================================
FASE 3. ABSENSI (TabFaceVerification.jsx)
====================================================================
Ubah SEMINIMAL mungkin. Jangan mengubah logika matching, submit, GPS, antrean, atau perhitungan durasi.

3.1 Viewport dan ukuran
  - const { useFullscreen, canvasSize } = useScanViewport();
  - const [scannerOpen, setScannerOpen] = useState(false);
  - Hapus konstanta modul `FRAME_RECT`. Ganti dengan frameRectRef = useRef(getFrameRect(canvasSize.width, canvasSize.height)),
    diperbarui lewat useEffect saat canvasSize berubah. Di onFaceProcessed baca frameRectRef.current
    (jangan menutup nilai basi, callback itu memakai deps []).
  - Teruskan canvasSize ke useNormalizedFaceMesh({ ..., canvasSize }).
  - active: modelsLoaded && (!useFullscreen || scannerOpen).

3.2 resetScanSession()
  Ekstrak dari handleEmployeeSelect semua reset sesi scan (BUKAN reset data karyawan):
    livenessVerifiedRef=false, hasBeepedRef=false, hasAutoSubmittedRef=false, matchRateRef=0, isMatchedRef=false,
    scoreHistoryRef=[], frameTimesRef=[], adaptiveWindowRef=5, matchTimerStartedRef=false, matchStartTimeRef=0,
    lastDetectTimeRef=0, lastDetectResultRef=null, lastProcessedDetectionRef=null, isStableRef=false,
    currentDescRef=null, frameStateRef={state:'idle',progress:0},
    setIsStable(false), setLivenessVerified(false), setIsLiveHuman(false), setMatchRate(0), setIsMatched(false),
    setLivenessStatusMsg('Harap posisikan wajah Anda di tengah layar'), setLightingWarning('').
  JANGAN reset masterVectorRef, masterGFVRef, challenge, atau data karyawan.
  handleEmployeeSelect memanggil resetScanSession() lalu melakukan reset khususnya sendiri. Perilakunya tidak boleh berubah.

3.3 openScanner / closeScanner
  openScanner(): bila !selectedEmployeeId -> showToast (pilih karyawan) dan return; bila !modelsLoaded -> toast dan return;
    bila !masterVectorRef.current -> toast "Template wajah belum tersedia / rusak" dan return;
    lalu resetScanSession() dan setScannerOpen(true).
  closeScanner(): setScannerOpen(false); resetScanSession(). Tidak boleh dipanggil saat isSubmitting (tombol X dinonaktifkan).
  Efek samping wajib: saat scannerOpen menjadi false, hook mematikan kamera (stream track.stop) lewat prop active.

3.4 Hasil submit di mode layar penuh
  - Sukses: bagian yang sudah ada (frameStateRef success) tetap. Tambahkan setTimeout(closeScanner, 1500).
    Timer reset sesi 2,5 detik milik kode lama tetap.
  - Gagal (queue gagal, GPS tidak didukung, validasi awal yang return): bingkai error 1500 ms, lalu resetScanSession() dalam setTimeout.
    hasAutoSubmittedRef tetap true selama 1500 ms itu agar status error tidak tertimpa, lalu direset oleh resetScanSession().
    Perbaikan ini juga berlaku di desktop inline (retry otomatis setelah gagal). Catat di laporan sebagai perubahan disengaja.
  - Bersihkan semua setTimeout saat komponen unmount atau saat scanner ditutup lebih awal.

3.5 Render
  - Bila useFullscreen && isHadir:
      * GANTI blok kamera inline dengan kartu ringkas: nama karyawan, status template (siap/tidak), dan tombol utama.
      * Tombol utama (menggantikan dua tombol CHECK-IN/CHECK-OUT manual untuk status Hadir):
          sudah lengkap check-in dan check-out -> nonaktif, label "Absensi Selesai Hari Ini";
          sudah check-in -> label "SCAN CHECK-OUT"; belum -> "SCAN CHECK-IN";
          onClick = openScanner. disabled bila !selectedEmployeeId || !modelsLoaded || isSubmitting.
      * <FullscreenScanShell open={scannerOpen} ...> berisi <FaceScanSurface .../>.
        mode = (attendanceStatus.checkedIn || attendanceStatus.checkInTime) ? 'checkout' : 'checkin' (sama dengan logika auto-submit).
        title = "Scan Check-In" atau "Scan Check-Out"; subtitle = nama karyawan.
        headerExtra = tombol "Ganti Kamera" (toggle facingMode seperti sekarang).
        footer = koordinat GPS ringkas (bila liveCoords tersedia) + persentase match.
        hint = SATU teks dengan prioritas: pesan sukses/gagal submit -> lightingWarning -> livenessStatusMsg ->
               "SIAP ABSEN - xx.x% Match" bila isMatched. hintTone mengikuti keadaan.
      * Bilah debug "AI Status" tidak ditampilkan di layar penuh.
  - Bila status BUKAN 'Hadir' (Izin/Sakit/Mangkir): perilaku tombol submit langsung tetap seperti sekarang (tanpa kamera).
  - Bila !useFullscreen: markup inline lama TIDAK diubah, hanya meneruskan canvasSize.

Gerbang Fase 3: build lulus; jalur desktop terbukti tidak berubah; uji manual check-in dan check-out (lihat kriteria).

====================================================================
FASE 4. PENDAFTARAN DAN SCAN ULANG
====================================================================
Gunakan HASIL Fase 0 (a, b, c, d). Pola sama dengan Fase 3. Jangan bergantung pada nama file yang saya duga.
 - Kasus A: layar memakai FaceMeshScanner.tsx yang dirender -> terapkan di sana.
 - Kasus B: tiap layar punya kode kamera sendiri -> terapkan pola yang sama di masing-masing (minimal), laporkan duplikasi.
 - Kasus C: FaceMeshScanner.tsx tidak dipakai layar mana pun -> laporkan, jangan jadikan acuan.
 - Bila pipeline layar tersebut berbeda dari absensi (Fase 0 c): BERHENTI dan tanyakan. Embedding master dan embedding absensi
   harus berasal dari pipeline yang sama.

Persyaratan setiap layar pendaftaran/scan ulang (mode "register" / "rescan"):
 1. Tombol pemicu ("Scan Wajah" / "Scan Ulang Wajah") membuka FullscreenScanShell dengan FaceScanSurface dan mode yang sesuai.
    Kamera aktif hanya saat terbuka (prop active), canvasSize dari useScanViewport.
 2. Pengambilan sampel embedding hanya bila evaluateFaceInFrame(boundingBox, frameRectRef.current).ok DAN isGoodFrame lolos.
    Jangan melonggarkan validasi yang ada (NaN/zero, dimensi 1024, anti-duplikasi FR-3, buildTemplate/median bila memang ada).
 3. frameStateRef: idle (tanpa wajah), misaligned (belum pas atau tidak menghadap lurus), scanning (progress = sampel
    terkumpul / jumlah sampel yang dibutuhkan, memakai konstanta yang sudah ada di kode), success, error.
 4. Sukses: bingkai hijau 1500 ms, panggil callback ke induk seperti sekarang (data yang dikirim tidak berubah), lalu tutup scanner.
 5. Ditolak (mis. "Wajah sudah terdaftar atas nama ..."): bingkai error, tampilkan pesan di hint, sampel direset, scanner tetap terbuka.
 6. Form induk tidak boleh ter-unmount saat scanner terbuka atau tertutup (nilai form tetap). Bila layar berada di dalam dialog,
    menutup scanner TIDAK boleh menutup dialog.
 7. Jalur "Upload Gambar" (bila ada) tidak diubah dan tidak memakai bingkai.
 8. Alur karyawan offline (ID sementara off_emp_xxx), penyimpanan SQLite/Dexie, dan sinkronisasi tidak diubah.

Gerbang Fase 4: build lulus; uji daftar dan scan ulang (lihat kriteria).

====================================================================
FASE 5. PENGERASAN DAN QA
====================================================================
Edge case yang HARUS ditangani dan dibuktikan:
 1. Izin kamera ditolak (onCameraError): tampilkan pesan jelas di hint, tombol tutup tetap bisa dipakai, tidak ada loop error.
 2. Menutup scanner saat model/kamera belum siap: tidak ada kebocoran stream (kamera benar-benar mati; indikator kamera hilang).
 3. Membuka-menutup scanner berulang cepat: tidak ada auto-submit seketika, tidak ada log ganda (hasAutoSubmittedRef bekerja).
 4. Rotasi layar saat scanner terbuka: kanvas beralih 480x640 <-> 640x480, bingkai ikut, kamera tidak restart, tidak ada error.
 5. Aplikasi ke latar belakang (visibilitychange hidden): scanner menutup dan kamera mati.
 6. Ganti kamera (user <-> environment) di dalam scanner: kamera restart sekali, mirror benar, label bingkai tidak terbalik.
 7. Toast (showToast) tetap terlihat di atas scanner.
 8. Status Izin/Sakit/Mangkir tetap bisa disimpan tanpa kamera di mobile.
 9. Karyawan tanpa template wajah/rusak: tombol scan memberi pesan, tidak membuka kamera.
 10. Bila Screen Wake Lock tersedia (navigator.wakeLock), minta saat scanner terbuka dan lepas saat tutup. Fitur ini opsional,
     gagal diam-diam bila tidak didukung (jangan menambah plugin).
 11. Aksesibilitas dasar: fokus ke tombol tutup saat dibuka, area sentuh minimal 44px.

KRITERIA PENERIMAAN (laporkan LULUS / GAGAL / TIDAK BISA DIUJI untuk tiap poin, dengan alasan)
  1. Desktop (>768px, non-APK): tampilan dan perilaku identik dengan sebelum perubahan (kanvas 640x480, bingkai sama).
  2. Mobile portrait: tombol "SCAN CHECK-IN/CHECK-OUT" membuka layar penuh; kamera menyala hanya saat terbuka.
  3. Lebar permukaan kamera mengisi lebar layar (di 390px, kamera 390px lebar), jauh lebih besar dari sebelumnya (±300px).
  4. Bingkai tetap sama ukurannya antara check-in, check-out, daftar, dan scan ulang; hanya warna dan label yang berbeda.
  5. Hanya SATU petunjuk teks yang tampil (tidak ada dua badge duplikat).
  6. Auto-submit terjadi SATU kali per sesi scan. Tidak ada log ganda setelah buka-tutup berulang.
  7. Setelah sukses, scanner menutup sendiri; setelah gagal, bingkai error lalu scan ulang otomatis tanpa perlu menutup.
  8. Regresi biometrik: karyawan yang didaftarkan di desktop (640x480) dapat lolos di mobile portrait (480x640) dan sebaliknya.
     Bandingkan log "[FASE 0 LOG]" cosSim sebelum dan sesudah perubahan pada orang dan kondisi cahaya yang sama.
     Selisih rata-rata di bawah 0,02 adalah tolok ukur kasar, bukan ketentuan sistem. Laporkan angkanya apa adanya.
  9. Threshold, median, dimensi 1024, dan isi data yang dikirim ke antrean/Supabase tidak berubah (tunjukkan dari diff).
 10. Tombol Back: sesuai Bagian 2.4, atau dinyatakan dinonaktifkan dengan alasan.
 11. FPS kamera tidak turun dibanding sebelumnya di HP menengah-bawah (>15 FPS render). Bila tidak bisa diukur: TIDAK BISA DIUJI.
 12. `npm run build` dan `npm test` lulus. Tidak ada dependensi baru di package.json.
 13. Tidak ada error baru di konsol selama alur normal.

MATRIKS UJI (tandai yang tidak bisa dijalankan)
  APK Android (OTA Capgo) | Chrome Android | Safari iOS (PWA) | Desktop Chrome
  x kamera belakang/depan x portrait/landscape x check-in/check-out/daftar/scan ulang

FORMAT LAPORAN AKHIR
  1. Laporan Fase 0 (sebelum mengubah apa pun), lalu tunggu jawaban saya.
  2. Setelah semua fase: daftar file diubah/dibuat (satu baris tiap file), diff lengkap per file.
  3. Daftar hal yang tidak ditemukan atau membutuhkan keputusan saya.
  4. Tabel kriteria penerimaan (LULUS/GAGAL/TIDAK BISA DIUJI + alasan).
  5. Daftar perubahan perilaku yang DISENGAJA (mis. retry otomatis setelah gagal di desktop inline).
```

---

# BAGIAN D. CHECKLIST VERIFIKASI MANUAL (UNTUK ANDA SETELAH AI SELESAI)
1. Buka halaman absensi di HP: halaman tidak lagi menampilkan kamera kecil, hanya kartu dan tombol scan.
2. Tekan tombol scan: layar penuh muncul, kamera nyala. Tutup: indikator kamera HP padam.
3. Pilih karyawan, scan sampai lolos: hijau, tersimpan, tertutup sendiri. Cek log absensi hanya bertambah satu baris.
4. Coba wajah orang lain: tidak lolos, tidak ada log.
5. Tekan Back saat scan: scanner tertutup, halaman tidak berpindah.
6. Putar HP ke landscape saat scan: bingkai menyesuaikan, kamera tidak terputus.
7. Ulangi untuk Pendaftaran dan Scan Ulang di Daftar Karyawan.
8. Daftarkan wajah di desktop, verifikasi di HP (dan sebaliknya).
