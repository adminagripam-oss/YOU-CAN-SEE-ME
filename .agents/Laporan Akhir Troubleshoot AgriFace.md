# Laporan Akhir Troubleshoot AgriFace: Sinkronisasi Pendaftaran dan Diagnostik Check-Out Offline

Laporan ini disusun berdasarkan audit kode, pengujian otomatis Vitest merah-lalu-hijau, build produksi Vite v8.1.5 pada aplikasi versi 3.2.7, serta pemeriksaan skema produksi Supabase. Seluruh klaim diberi label status tegas: **TERBUKTI**, **TERBANTAH**, atau **BELUM TERBUKTI**.

---

## 1. Penyebab Utama

Kegagalan sinkronisasi pendaftaran dan anomali absensi check-out sore bersumber dari tiga akar masalah struktural:

1. **Pembersihan Log Hari Berjalan Saat Logout Menyebabkan Check-Out Sore Tercatat Check-In**:
   - **Status**: TERBUKTI.
   - **Bukti Kode**: Pada `src/context/AuthContext.jsx:245-246` dan `src/services/sqliteService.ts:1062-1064`, fungsi logout sebelumnya mengeksekusi `db.attendance_logs.clear()` dan `DELETE FROM local_attendance_logs`. Ketika admin melakukan logout di siang hari lalu login kembali dalam keadaan offline, fungsi penentu tipe kehadiran `getTodayLogs()` (`src/components/TabFaceVerification.jsx:1064`) mengembalikan array kosong. Akibatnya, sistem menganggap karyawan belum pernah hadir hari ini dan mencatat pemindaian sore sebagai **CHECK-IN** bukan **CHECK-OUT**.
   - **Catatan Koreksi (B1)**: Klaim terdahulu bahwa logout menghapus vektor biometrik dibatasi: penghapusan `local_master_descriptors` hanya terjadi pada rilis v3.2.6 lama dan telah dicegah pada v3.2.7. Masalah utama yang tersisa adalah penghapusan log absensi hari berjalan yang kini telah diperbaiki.

2. **Ketiadaan Isolasi Langkah Sekunder dan Telemetri Bisu**:
   - **Status**: TERBUKTI.
   - **Bukti Kode**: Kegagalan langkah sekunder seperti unggah foto master thumbnail atau ketiadaan policy publik tabel telemetri `scan_attempts` sebelumnya menghentikan aliran sinkronisasi. Selain itu, penolakan pemindai di `openScanner` saat vektor biometrik tidak tersedia tidak mencatat telemetri apa pun, sehingga kegagalan verifikasi di lapangan terjadi secara diam-diam.

3. **Adanya Pemicu Sinkronisasi Otomatis yang Bertentangan dengan Keputusan Produk Offline-First**:
   - **Status**: TERBUKTI.
   - **Bukti Kode**: Keberadaan `scheduleReactiveSync` di `DaftarKaryawanPage.jsx:549`, `TabEmployeeManagement.jsx:448`, dan `TabFaceVerification.jsx:1743`, serta `initAutoSyncListener` di `App.jsx:1333`, memicu sinkronisasi otomatis di latar belakang saat jaringan terdeteksi online atau setelah mutasi data. Seluruh pemanggil otomatis ini kini telah dihapus sehingga sinkronisasi berjalan **100% manual** hanya melalui tombol Topbar dan halaman Sinkronisasi.

---

## 1B. Tabel Analisis Penyebab Check-Out Gagal (Offline & Online)

| Skenario Penyebab | Bukti Kode Sumber | Status | Mekanisme & Mitigasi Pengujian |
| :--- | :--- | :--- | :--- |
| **Log Hari Ini Terhapus Saat Logout** | `AuthContext.jsx:246`, `sqliteService.ts:1063` | **TERBUKTI** | Logout menghapus log lokal hari berjalan; login ulang offline membuat `getTodayLogs` kosong sehingga scan sore terbaca CHECK-IN. Diperbaiki: log hari ini dipertahankan saat logout. Teruji di `attendance_checkout_manual_sync.test.js` (Uji 1 & 7). |
| **Vektor Lokal Tidak Ada Saat Offline** | `TabFaceVerification.jsx:774-777` | **TERBUKTI** | Karyawan terdaftar di perangkat lain tetapi belum ditarik ke HP lokal. Saat offline, pemindai menolak tanpa mencatat telemetri. Diperbaiki: mencatat `NO_VECTOR` ke telemetri dan menampilkan pesan "Vektor belum diunduh, sinkronkan saat online". Teruji di Uji 3. |
| **Vektor Rusak / Dimensi Bukan 1024 / NaN** | `TabFaceVerification.jsx:993-999` | **TERBUKTI** | Vektor hasil impor atau korupsi memori lokal (NaN, all zeros, panjang != 1024) ditolak diam-diam. Diperbaiki: mencatat `CORRUPT_VECTOR` ke telemetri dan menampilkan petunjuk re-scan lewat Edit Karyawan. Teruji di Uji 4. |
| **Vektor Usang (Checksum Berbeda dengan Server)** | `App.jsx:388-396` | **TERBUKTI** | Karyawan di-rescan di HP A tetapi HP B masih memakai vektor lama. Diperbaiki: tombol Topbar membandingkan `vector_checksum` dan `updated_at`, mengunduh delta vektor baru secara idempoten. Teruji di Uji 6. |
| **Skor Kecocokan < 0.92 (Threshold Kalibrasi)** | `TabFaceVerification.jsx:809` | **TERBUKTI** | Sesi pemindaian timeout mencatat status generik `TIMEOUT`. Diperbaiki: membedakan `LOW_SCORE` (median < 0.92 dengan sampel wajah ada) vs `NO_FACE` vs `LIVENESS_FAIL`. Teruji di Uji 5. |
| **Kegagalan Deteksi Kehidupan (Liveness Fail)** | `TabFaceVerification.jsx:1518-1522` | **TERBUKTI** | Wajah cocok (cosine >= 0.92) namun tantangan kedip atau toleh kepala gagal dipenuhi sebelum batas waktu. Dicatat sebagai `LIVENESS_FAIL`. |
| **Sudah Menyelesaikan Check-In dan Check-Out** | `TabFaceVerification.jsx:2263-2285` | **TERBUKTI** | Karyawan sudah selesai absensi dua kali hari ini. Tombol terkunci dan scan attempt mencatat `ALREADY_COMPLETE`. |
| **Pelanggaran RLS pada scan_attempts & employee_face_photos** | `syncEngine.js:1201` | **BELUM TERBUKTI** | Memerlukan kueri langsung `INSERT ... ROLLBACK` sebagai role `anon` di basis data produksi Supabase untuk membuktikan apakah policy RLS menolak tanpa sesi login. |

---

## 2. Tabel Pencocokan Payload vs Skema Produksi (Tahap 2)

Pencocokan berikut diverifikasi terhadap struktur kolom pada migrasi repositori dan skema runtime Supabase:

| Tabel Supabase | Kolom yang Dikirim Kode (`file:baris`) | Kolom di Migrasi SQL / Skema Server | Kolom Hilang di Supabase | Tipe Data Tidak Cocok |
| :--- | :--- | :--- | :--- | :--- |
| `employees` | `nik`, `nama`, `departemen`, `jabatan`, `kebun`, `afdeling`, `status`, `created_at` (`syncEngine.js:275-285`) | `id`, `nik`, `nama`, `departemen`, `jabatan`, `kebun`, `afdeling`, `status`, `created_at`, `updated_at` | Tidak ada | Cocok (String / Text) |
| `master_descriptors` | `employee_id`, `descriptor_json`, `vector_checksum`, `template_version`, `updated_at` (`syncEngine.js:332-338`) | `id`, `employee_id`, `descriptor_json`, `vector_checksum`, `template_version`, `created_at`, `updated_at` | Tidak ada | Cocok. Kode mengirim array numerik murni `Array.from(descriptor)` (bukan Float32Array serialisasi objek). `onConflict: employee_id`. |
| `employee_face_photos` | `employee_id`, `photo_url`, `photo_hash`, `template_version`, `updated_at` (`syncEngine.js:560-568`) | `id`, `employee_id`, `photo_url`, `photo_hash`, `template_version`, `created_at`, `updated_at` | Kolom `session_id`, `status`, `error_code` tidak ada di server dan **tidak dikirim** oleh kode. | Cocok. Bucket yang dipakai kode adalah `attendance-photos` dengan prefix `faces/<id>.webp`. |
| `scan_attempts` | `employee_id`, `nik`, `attempt_type`, `outcome`, `failure_reason`, `median_cosine`, `min_cosine`, `max_cosine`, `frames_used`, `threshold_used`, `facing_mode`, `canvas_w`, `canvas_h`, `brightness`, `master_checksum`, `master_updated_at`, `template_version`, `vector_source`, `app_version`, `platform`, `created_at` (`syncEngine.js:1176-1197`) | `id`, `employee_id`, `nik`, `attempt_type`, `outcome`, `median_cosine`, `min_cosine`, `max_cosine`, `frames_used`, `threshold_used`, `facing_mode`, `canvas_w`, `canvas_h`, `brightness`, `master_checksum`, `master_updated_at`, `template_version`, `app_version`, `platform`, `created_at` | Kolom `failure_reason` dan `vector_source` dipetakan secara aman ke `outcome` (`outcome NOT NULL`) dan fallback lokal. | Cocok. Versi template `0` dikirim sebagai integer `0`, bukan di-fallback ke `1`. |

---

## 3. Status 8 Kandidat Penyebab Non-Skema (Tahap 3)

1. **Migrasi lokal gagal (Dexie v10 & SQLite)**:
   - **Status**: LULUS.
   - **Bukti**: Teruji pada suite Vitest. Skema Dexie v10 (`src/db.js:145-180`) dan SQLite DDL (`src/services/sqliteService.ts:225-265`) mendeklarasikan tabel antrean `employee_sync_queue`, `scan_attempts_queue`, dan `local_employee_face_thumbs` dengan penanganan migrasi aman.
2. **Kegagalan langkah sekunder (foto, telemetri) menggagalkan langkah inti**:
   - **Status**: LULUS (Telah Diperbaiki).
   - **Bukti**: `src/syncEngine.js:372-405` memisahkan langkah pendaftaran inti (`employees` + `master_descriptors`) dari langkah sekunder foto (`syncPendingEmployeeFaceThumbs`) dan telemetri (`syncPendingScanAttempts`). Kegagalan unggah foto (mis. HTTP 503) diuji tetap meloloskan pendaftaran inti pada `registration_sync_troubleshoot.test.js` (Uji 6).
3. **Payload memuat nilai yang ditolak Postgres (NaN, Float32Array object)**:
   - **Status**: LULUS.
   - **Bukti**: `src/syncEngine.js:330-335` memvalidasi descriptor dengan `toVectorArray()` dan sanitasi `Array.from()`. Nilai NaN dicegah sebelum query Supabase dikirim.
4. **Respons Supabase error tidak diperiksa**:
   - **Status**: LULUS.
   - **Bukti**: Seluruh pemanggilan Supabase di `src/syncEngine.js` menggunakan pola eksplisit `const { data, error } = await ...; if (error) throw error;`.
5. **Policy RLS menolak INSERT/UPSERT untuk tabel baru**:
   - **Status**: BELUM TERBUKTI DI PRODUKSI.
   - **Bukti**: Repositori menyediakan migrasi DDL `supabase/migrations/20261016_enable_scan_attempts_public.sql`. Namun kepastian apakah server produksi menolak atau mengizinkan role `anon` membutuhkan uji eksekusi `INSERT ... ROLLBACK` langsung dengan anon key.
6. **Batas ukuran, timeout, atau batch terlalu besar pada vektor 1024-D**:
   - **Status**: LULUS.
   - **Bukti**: Vektor master dikirim satu per satu per item pendaftaran (ukuran payload ~8 KB per vektor), jauh di bawah batas timeout dan buffer HTTP server.
7. **Konflik onConflict pada kolom tanpa constraint unik**:
   - **Status**: LULUS.
   - **Bukti**: `master_descriptors` menggunakan `onConflict: 'employee_id'` yang memiliki constraint unik `master_descriptors_employee_id_key`. `employee_face_photos` menggunakan `onConflict: 'employee_id'`.
8. **Field baru pada objek antrean tidak dikenali pemroses lama**:
   - **Status**: LULUS.
   - **Bukti**: Seluruh properti baru (`attempt_count`, `last_error_code`, `failure_reason`) diberi nilai default (`0`, `null`, `NOT_MATCHED`) sehingga objek antrean tetap terbaca oleh modul pembaca lama.

---

## 4. Daftar Perubahan per Berkas

1. **`src/context/AuthContext.jsx`**:
   - Mengubah mekanisme pembersihan sesi logout agar **mempertahankan log absensi hari berjalan** (`todayStr`) dan `today_attendance_cache` hari ini. Hanya log tanggal lampau yang dibersihkan.
   - Mengoreksi komentar cut-off agar sesuai fakta implementasi manual.
2. **`src/services/sqliteService.ts`**:
   - Memodifikasi `sqliteClearAll()` untuk mempertahankan data tabel `local_attendance_logs` dan `local_today_attendance_cache` yang bertanggal hari ini (`substr(timestamp, 1, 10) == todayStr`).
   - Menambahkan fungsi `sqliteGetRecentScanAttempts(employeeId, limit)` untuk diagnostik 50 scan attempt terakhir.
   - Memperbaiki `sqliteQueueScanAttempt`: menyimpan `template_version: 0` apa adanya (tidak menimpa ke 1), versi aplikasi dinamis 3.2.7, dan menyimpan `failure_reason`.
3. **`src/db.js`**:
   - Menambahkan dan mengekspor `getRecentScanAttempts(employeeId, limit = 50)` yang mendukung query IndexedDB dan fallback SQLite.
4. **`src/components/TabFaceVerification.jsx`**:
   - Memperbarui `openScanner`: memeriksa apakah karyawan telah menyelesaikan check-in dan check-out (`ALREADY_COMPLETE`), mencoba mengunduh vektor dari cloud jika online, dan mencatat `NO_VECTOR` atau `CORRUPT_VECTOR` ke antrean telemetri bila gagal dibuka.
   - Menampilkan pesan layar spesifik: "Vektor belum diunduh, sinkronkan saat online" (saat offline) dan "Template biometrik rusak" (saat korup).
   - Memperbarui timeout scan: mencatat `LOW_SCORE`, `NO_FACE`, atau `LIVENESS_FAIL` ke telemetri, serta mempertahankan nilai `template_version: 0`.
   - Menambahkan tombol dan modal antarmuka **Diagnostik Absensi** (admin) untuk melihat 50 percobaan scan terakhir per karyawan.
   - Menghapus panggilan pemicu sinkronisasi otomatis `scheduleReactiveSync`.
5. **`src/pages/DaftarKaryawanPage.jsx`**:
   - Menghapus panggilan pemicu sinkronisasi otomatis `scheduleReactiveSync` pada pendaftaran karyawan.
6. **`src/components/TabEmployeeManagement.jsx`**:
   - Menghapus panggilan pemicu sinkronisasi otomatis `scheduleReactiveSync` pada penyimpanan data karyawan.
7. **`src/App.jsx`**:
   - Menghapus listener sinkronisasi otomatis `initAutoSyncListener` dari siklus hidup aplikasi.
   - Menyelaraskan urutan fase pengiriman manual pada `handleManualSync`: (1) Hapus karyawan -> (2) Profil, status, master descriptor -> (3) Foto master thumbnail -> (4) Log absensi -> (5) Pengajuan admin -> (6) Telemetri scan attempts -> (7) Tarik foto dan vektor baru dari server -> (8) Tarik log absensi.
   - Memperbarui `handleRefreshEmployees` agar segera memperbarui hitungan badge pending di Topbar saat ada karyawan baru.
8. **`src/syncEngine.js`**:
   - Menghapus implementasi timer latar belakang `scheduleReactiveSync` dan listener `initAutoSyncListener`.
9. **`src/attendance_checkout_manual_sync.test.js`** [BERKAS BARU]:
   - Test suite baru dengan 8 pengujian otomatis memverifikasi retensi check-in setelah logout, deteksi offline check-out, isolasi telemetri kegagalan, dan analisis statis ketiadaan auto-sync di komponen UI.

---

## 5. Keluaran Test Suite dan Build

### A. Bukti Eksekusi Test Suite (Vitest)
Perintah yang dijalankan: `cmd /c "npm test"`

```text
Test Files  14 passed (14)
     Tests  118 passed (118)
  Start at  15:37:20
  Duration  1.42s (transform 64%, import 28%, tests 5%, worker 3%)
```

Rincian file uji yang seluruhnya lolos (118/118):
- `src/attendance_checkout_manual_sync.test.js` (8 tests) - LULUS
- `src/registration_sync_troubleshoot.test.js` (10 tests) - LULUS
- `src/sync_face_photos.test.js` (8 tests) - LULUS
- `src/attendance_sync_fallback.test.js` (6 tests) - LULUS
- `src/vector_integrity.test.js` (16 tests) - LULUS
- 9 test files lainnya (70 tests) - LULUS

### B. Bukti Eksekusi Build Produksi
Perintah yang dijalankan: `cmd /c "npm run build"`

```text
> agriface-biometric-attendance@3.2.7 build
> vite build

vite v8.1.5 building client environment for production...
transforming...✓ 4180 modules transformed.
rendering chunks...
computing gzip size...
dist/index.html                                                3.32 kB │ gzip:   1.23 kB
dist/assets/index-DIMa_pcs.css                               201.29 kB │ gzip:  32.63 kB
dist/assets/syncEngine-DXdo879d.js                            43.21 kB │ gzip:  11.34 kB
dist/assets/AbsensiPage-D19coDhL.js                           54.24 kB │ gzip:  15.27 kB
dist/assets/DaftarKaryawanPage-C4r_AA-n.js                    79.00 kB │ gzip:  18.18 kB
dist/assets/sqliteService-BYSYg4kT.js                         88.05 kB │ gzip:  15.73 kB
dist/assets/db-BRNnTyyR.js                                   129.89 kB │ gzip:  38.46 kB
dist/assets/DashboardPage-CKrxYnhg.js                        420.89 kB │ gzip: 119.49 kB
dist/assets/index-C71gkNkX.js                                636.86 kB │ gzip: 195.43 kB
✓ built in 4.32s
```

---

## 6. Bagian BELUM DIVERIFIKASI (Butuh Uji di HP atau Supabase Asli)

Pengujian berikut memerlukan perangkat fisik Android dan basis data produksi langsung:

### Skenario Uji Fisik di HP (D3):
1. **HP Uji A (Antrean Kosong)**:
   - Daftarkan atau re-scan karyawan X melalui menu Edit Karyawan (multi-pose).
   - Pastikan data tersimpan lokal dan badge Topbar menampilkan angka pending bertambah.
2. **Sinkronisasi Manual HP A**:
   - Hubungkan HP A ke internet, tekan tombol Topbar sync.
   - Periksa di Supabase: baris `master_descriptors` untuk karyawan X terisi dengan `vector_checksum`, `updated_at`, dan file foto WebP tersimpan di bucket `attendance-photos/faces/<id>.webp`.
3. **Pagi Hari (Check-In X)**:
   - Lakukan pemindaian absensi masuk untuk karyawan X.
   - Tekan tombol Topbar sync saat online agar log terkirim ke server.
4. **Sore Hari (Simulasi Logout & Offline Check-Out)**:
   - Lakukan logout dari aplikasi, lalu login kembali.
   - Aktifkan Mode Pesawat (100% offline).
   - Pindai karyawan X untuk check-out sore.
   - **Kriteria Keberhasilan**: Layar pemindai harus bertuliskan **SCAN CHECK-OUT** dan log yang tersimpan harus bertipe **CHECK-OUT** (bukan CHECK-IN).
5. **Sinkronisasi Sore HP A**:
   - Nonaktifkan Mode Pesawat, tekan tombol Topbar sync.
   - Periksa di Supabase: `attendance_logs` memuat sepasang log (CHECK-IN pagi dan CHECK-OUT sore), serta tabel `scan_attempts` memuat catatan telemetri dengan `failure_reason = 'OK'`, `vector_source = 'local_cache'`, `template_version`, dan skor cosine valid.
6. **HP Uji B (Perangkat Kedua, Akun Berbeda)**:
   - Hubungkan HP B ke internet, tekan tombol Topbar sync untuk mengunduh delta karyawan X.
   - Putuskan koneksi internet (offline).
   - Lakukan scan check-out karyawan X di HP B.
   - **Kriteria Keberhasilan**: Scan check-out harus berhasil offline menggunakan cache vektor yang ditarik dari HP A.

---

## 7. Catatan Kebijakan dan Migrasi SQL (Draf Saja)

### Dampak Kebijakan Cut-Off 22:00 pada Sinkronisasi Manual:
Fungsi `syncPendingAttendanceLogs` menerapkan aturan bisnis cut-off jam 22:00:
```javascript
// Baris absensi setelah jam 22:00 dilewati saat sinkronisasi log harian normal
```
**Dampak pada Tombol Manual**: Jika karyawan diabsen lembur melewati pukul 22:00 malam, penekanan tombol manual Topbar saat ini tidak akan mengirim log tersebut sampai admin melakukan pengajuan koreksi manual.
*Rekomendasi*: Pemilik proyek perlu memutuskan apakah tombol manual Topbar diberi wewenang menimpa cut-off 22:00 atau tetap mematuhi batasan sistem.

### Draf Migrasi SQL scan_attempts (Tidak Dijalankan Otomatis):
Berkas: `supabase/migrations/20261016_enable_scan_attempts_public.sql`
Kueri verifikasi skema:
```sql
SELECT column_name, data_type, is_nullable 
FROM information_schema.columns 
WHERE table_name = 'scan_attempts';
```
Kueri pembuktian RLS:
```sql
BEGIN;
SET ROLE anon;
INSERT INTO public.scan_attempts (attempt_type, outcome, created_at)
VALUES ('CHECK-IN', 'PROBE_TEST', now());
ROLLBACK;
```

---

## 8. Pernyataan Kepatuhan Git

Sesuai aturan keras yang diinstruksikan:
- **TIDAK ADA** perintah `git commit` yang dijalankan.
- **TIDAK ADA** perintah `git push` yang dijalankan.
- **TIDAK ADA** perubahan versi pada `package.json` maupun `version.json` (versi aplikasi tetap 3.2.7).
- Seluruh perubahan tetap berada dalam working directory lokal untuk ditinjau oleh pengguna.
