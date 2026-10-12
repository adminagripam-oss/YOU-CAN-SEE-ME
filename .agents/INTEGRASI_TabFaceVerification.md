# Integrasi FaceFrameTemplate ke TabFaceVerification.jsx

Letakkan `FaceFrameTemplate.jsx` di `src/components/`. Ada 7 perubahan kecil, semuanya di `TabFaceVerification.jsx`.

## 1. Import (bagian atas file)
```js
import { FaceFrameOverlay, getFrameRect, evaluateFaceInFrame, FRAME_HINTS } from '../components/FaceFrameTemplate';
```

## 2. Konstanta modul (di atas `export default function TabFaceVerification`)
```js
const FRAME_RECT = getFrameRect(640, 480);
```

## 3. Ref + helper (di dalam komponen, dekat `const canvasRef = useRef(null);`)
```js
const frameStateRef = useRef({ state: 'idle', progress: 0 });
// Tidak menimpa status success/error setelah auto-submit berjalan
const setFrame = useCallback((state, progress = 0) => {
  if (hasAutoSubmittedRef.current) return;
  frameStateRef.current = { state, progress };
}, []);
```
`hasAutoSubmittedRef` dideklarasikan lebih bawah dari `canvasRef`. Letakkan blok ini setelah deklarasi `hasAutoSubmittedRef`.

## 4. `drawGeometricMesh`: hapus bracket lama
Hapus blok ini (bingkai baru menggantikannya), titik node tetap:
```js
if (!isNaN(minX) && !isNaN(minY) && !isNaN(maxX) && !isNaN(maxY)) {
  drawScannerCorners(ctx, minX, minY, maxX, maxY, R, G, B, livenessDone);
}
```
Fungsi `drawScannerCorners` boleh dihapus juga.

## 5. `onFaceProcessed`

a) Ganti pemeriksaan ukuran wajah yang lama:
```js
// SEBELUM
const faceWidth = boundingBox?.width || 0;
if (!warningMsg && faceWidth > 0) {
  if (faceWidth < 140) warningMsg = 'Wajah Terlalu Jauh';
  else if (faceWidth > 360) warningMsg = 'Wajah Terlalu Dekat';
}
// SESUDAH
const fit = evaluateFaceInFrame(boundingBox, FRAME_RECT);
if (!warningMsg && !fit.ok) warningMsg = FRAME_HINTS[fit.reason];
```

b) Di dalam `if (warningMsg) { ... }`, sebelum `return;`:
```js
setFrame('misaligned');
```

c) Di blok `else` saat `!isFacingForward` (setelah `scoreHistoryRef.current = []` dst.), tambahkan:
```js
setFrame('misaligned');
```
dan di blok `if (isFacingForward)` bagian `if (!isStableRef.current)`-nya biarkan.

d) Di blok "Absensi Hari Ini Sudah Lengkap", sebelum `return;`:
```js
setFrame('idle');
```

e) Setelah `setIsMatched(matched);`:
```js
setFrame('scanning', matched ? 1 : Math.min(1, scoreHistoryRef.current.length / BIOMETRIC_CONFIG.SCAN_SAMPLE_COUNT));
```

f) Dependency `useCallback(..., [])` pada `onFaceProcessed` tetap boleh `[]`, karena `setFrame` stabil dan ref tidak perlu masuk dependency.

## 6. `onNoFace`
```js
const onNoFace = useCallback(() => {
  currentDescRef.current = null;
  setFrame('idle');
  ...
}, [setFrame]);
```

## 7. `handleVerifySubmit` dan reset

Di blok `if (isSuccess) {` (paling awal):
```js
frameStateRef.current = { state: 'success', progress: 1 };
```

Di dalam `setTimeout(... 2500)` yang mereset sesi, tambahkan:
```js
frameStateRef.current = { state: 'idle', progress: 0 };
```

Di blok `else {` (absensi gagal):
```js
frameStateRef.current = { state: 'error', progress: 0 };
setTimeout(() => { frameStateRef.current = { state: 'idle', progress: 0 }; }, 1500);
```

Di `handleEmployeeSelect`, bersama reset ref lainnya:
```js
frameStateRef.current = { state: 'idle', progress: 0 };
```

## 8. JSX: tambahkan overlay setelah `<canvas ref={canvasRef} .../>`
```jsx
<FaceFrameOverlay
  mode={(attendanceStatus.checkedIn || attendanceStatus.checkInTime) ? 'checkout' : 'checkin'}
  stateRef={frameStateRef}
/>
```
Overlay ini kanvas terpisah dan tidak di-mirror, jadi label tidak terbalik pada `facingMode === 'user'`.

## Catatan temuan di kode saat ini
1. **Liveness sedang auto-pass.** Blok `LOW ACCURACY / FAST MODE` memakai `passed = true`, jadi kedip dan toleh tidak diminta. Karena itu bingkai tidak menampilkan petunjuk "kedipkan mata". Panel "Alur Verifikasi" masih menulis "Toleh Kiri & Kanan" dan PRD menyebut kedip. Sebaiknya samakan salah satunya.
2. **Jalur return awal di `handleVerifySubmit` tidak mereset `hasAutoSubmittedRef`.** Cek wajah, liveness, dan match yang gagal, serta GPS tidak didukung, langsung `return`. Setelah itu `hasAutoSubmittedRef` tetap `true`, auto-submit tidak akan jalan lagi, dan `setFrame` terkunci pada status terakhir. Tambahkan `hasAutoSubmittedRef.current = false;` sebelum setiap `return` itu.
3. **Penentuan mode.** `checkin`/`checkout` mengikuti logika yang sama dengan auto-submit (`checkedIn || checkInTime` berarti check-out), jadi label bingkai selalu sesuai aksi yang akan terjadi.
4. **`BIOMETRIC_CONFIG.SCAN_SAMPLE_COUNT`** dipakai sebagai pembagi progress bar. Saya belum melihat file `biometrics.js`, tapi namanya sudah dipakai di kode Anda.
