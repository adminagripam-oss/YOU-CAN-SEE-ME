# Laporan Verifikasi: Bukti 4 Ketidaksesuaian antara PRD v3.2.3 dan Kode Aktual

Dokumen ini memaparkan hasil audit berbasis bukti (*evidence-based code audit*) untuk membuktikan atau membantah 4 ketidaksesuaian antara [PRD.md](file:///d:/FACE%20VERIFICATION/PRD.md) dan implementasi kode aktual, sesuai instruksi [PROMPT Verifikasi PRD vs Kode.md](file:///d:/FACE%20VERIFICATION/.agents/PROMPT%20Verifikasi%20PRD%20vs%20Kode.md).

Pemeriksaan dilakukan dalam mode baca-saja terhadap seluruh basis kode aplikasi, skema basis data, dan berkas migrasi SQL.

---

## 1. Ringkasan Eksekutif Satu Halaman

| Butir Verifikasi | Topik Evaluasi | Status Temuan | Dampak terhadap Gagal Check-Out Sore | Bukti Utama di Kode |
| :--- | :--- | :--- | :--- | :--- |
| **BUTIR 1** | Alur Pendaftaran Wajah (Multi-Pose v2 vs 7 Frame Outlier) | **TERBUKTI** | **TINGGI**: Jalur Upload Foto hanya 1 frame (template sempit), sedangkan kamera memakai Multi-Pose v2 (8 frame, median). `buildTemplateDetailed` (7 frame + eliminasi 1 outlier) tidak pernah dipanggil di UI. | `DaftarKaryawanPage.jsx:239, 566`, `TabEmployeeManagement.jsx:99, 273`, `biometrics.js:150-347, 354`. |
| **BUTIR 2** | Skema Nyata Database vs PRD §7 | **TERBUKTI** | **SEDANG**: PRD tertinggal dari migrasi aktif. SQLite memiliki 11 tabel (PRD: 10). Dexie berada di v10 dengan 11 stores (PRD: v9, 9 stores). `scan_attempts` dan kolom telemetri belum terdokumentasi di PRD §7. | `sqliteService.ts:74-249`, `db.js:133-145`, `supabase/migrations/20261015_create_scan_attempts.sql:5-26`. |
| **BUTIR 3** | Foto Wajah di Bucket Publik & Risiko Keamanan | **TERBUKTI** | **RENDAH (Akurasi)** / **TINGGI (Keamanan)**: Foto master disimpan di bucket publik `attendance-photos` dengan path sekuensial `faces/${empId}/master.jpg`, bertentangan dengan migrasi yang mendefinisikan bucket privat `employee-faces`. | `syncEngine.js:1072`, `EmployeeFacePreviewModal.jsx:127`, `supabase/migrations/20261015_create_employee_face_photos.sql:44`. |
| **BUTIR 4** | Kontradiksi Dokumen & Konfigurasi | **TERBUKTI** | **TINGGI**: Express API Tier 1 sudah 410 Gone; rute `/sinkronisasi` dikunci ke `estate_admin` di router sehingga HQ tidak bisa audit; validasi duplikasi wajah di-bypass saat luring di Daftar Karyawan. | `server.js:84`, `router.jsx:239`, `menu.js:61`, `DaftarKaryawanPage.jsx:296, 736`. |

---

## 2. Rincian BUTIR 1: Alur Pendaftaran Wajah di Kode vs PRD

### 2.1 Pemanggilan Fungsi pada Tiga Jalur Pendaftaran
PRD (§3.2, FR-9) mendokumentasikan bahwa pendaftaran wajah menggunakan algoritma 2-pass `buildTemplateDetailed` dengan 7 frame sampel, jeda 150 ms, kalkulasi centroid kasar, dan pembuangan 1 sampel outlier.

Penelusuran kode membuktikan fakta berikut:
1. **Jalur 1: Rescan / Scan Ulang Karyawan** ([DaftarKaryawanPage.jsx:239](file:///d:/FACE%20VERIFICATION/src/pages/DaftarKaryawanPage.jsx#L239))
   Memanggil `selectEnrollmentFrames(scanCollectorRef.current, detection, now)`. Fungsi `buildTemplateDetailed` tidak dipanggil.
2. **Jalur 2: Edit Karyawan via Kamera** ([DaftarKaryawanPage.jsx:566](file:///d:/FACE%20VERIFICATION/src/pages/DaftarKaryawanPage.jsx#L566))
   Memanggil `selectEnrollmentFrames(editCollectorRef.current, detection, now)`. Fungsi `buildTemplateDetailed` tidak dipanggil.
3. **Jalur 3: Registrasi Karyawan Baru `/karyawan`** ([TabEmployeeManagement.jsx:99](file:///d:/FACE%20VERIFICATION/src/components/TabEmployeeManagement.jsx#L99))
   Memanggil `selectEnrollmentFrames(enrollmentCollectorRef.current, detection, now)`. Fungsi `buildTemplateDetailed` tidak dipanggil.

**Status: TERBUKTI**. Seluruh alur kamera di UI menggunakan state machine `createEnrollmentCollector` dan `selectEnrollmentFrames`. Fungsi `buildTemplateDetailed` hanya diimpor ([DaftarKaryawanPage.jsx:21](file:///d:/FACE%20VERIFICATION/src/pages/DaftarKaryawanPage.jsx#L21), [TabEmployeeManagement.jsx:5](file:///d:/FACE%20VERIFICATION/src/components/TabEmployeeManagement.jsx#L5)) namun tidak pernah dieksekusi di antarmuka pengguna.

### 2.2 Tabel Konfigurasi Aktual Kode vs PRD
Parameter biometrik pendaftaran didefinisikan di [src/biometrics.js:1-19](file:///d:/FACE%20VERIFICATION/src/biometrics.js#L1-L19):

| Parameter Konfigurasi | Nilai di Kode (`BIOMETRIC_CONFIG`) | Nilai di PRD (§3.2, FR-9) | Analisis Selisih |
| :--- | :--- | :--- | :--- |
| Target Frame Sampel | `ENROLL_TARGET_FRAMES: 8` | 7 frame | Kode membutuhkan 8 frame untuk multi-pose lengkap, atau 6 frame frontal jika timeout. |
| Jeda Minimal Antar-Frame | `ENROLL_MIN_FRAME_GAP_MS: 300` | Minimal 150 ms | Jeda aktual dua kali lebih lambat (300 ms) untuk memastikan variasi gerakan kepala nyata. |
| Batas Waktu Total Pendaftaran | `ENROLL_MAX_DURATION_MS: 12000` (12 detik) | Tidak disebutkan | Kode memiliki timer 12 detik. Jika waktu habis namun sudah ada 6 frame frontal, sistem melakukan fallback frontal. |
| Toleransi Sudut Yaw (Tatap Lurus) | `QUALITY_MAX_ANGLE: 0.15` (~8.6°) | Tidak didefinisikan angka | Sudut maksimal untuk kategori frame frontal. |
| Rentang Sudut Yaw Multi-Pose | `TURNED_YAW_MIN: 0.12`, `TURNED_YAW_MAX: 0.30` (~6.9° hingga ~17.2°) | Tidak disebutkan (hanya tatap lurus) | Kode secara oportunistik menangkap hingga 2 frame pose miring (turned). |
| Batas Pitch Kepala | `QUALITY_MAX_PITCH_TURNED: 0.20` (~11.5°) | Tidak disebutkan | Mencegah kepala menunduk atau menengadah ekstrem. |
| Minimal Konsistensi | `ENROLL_MIN_CONSISTENCY: 0.90` | $\ge 0.90$ | Sesuai antara kode dan PRD. |
| Versi Template | `TEMPLATE_VERSION: 2` | Tidak disebutkan di PRD | Kode menandai template multi-pose sebagai versi 2. |

### 2.3 Penelusuran Lifecycle `template_version`
1. **Koleksi di Memori**:
   Saat `selectEnrollmentFrames` selesai ([biometrics.js:317](file:///d:/FACE%20VERIFICATION/src/biometrics.js#L317)), `template_version` dihitung dinamis: bernilai `2` jika terdapat frame miring (`turnedCount > 0`), atau `1` jika hanya frontal.
2. **Penyimpanan Antrean Lokal SQLite**:
   Pada tabel `local_employee_sync_queue` ([sqliteService.ts:174-195](file:///d:/FACE%20VERIFICATION/src/services/sqliteService.ts#L174-L195)), **TIDAK ADA kolom `template_version`**. Objek yang masuk ke antrean kehilangan identitas versi template aslinya.
3. **Penyimpanan Cache Master Lokal**:
   Tabel `local_master_descriptors` ([sqliteService.ts:663, 790](file:///d:/FACE%20VERIFICATION/src/services/sqliteService.ts#L663)) memiliki kolom `template_version` dan menyimpan nilai tersebut via `sqliteCacheUserMasterVector`.
4. **Penyimpanan Antrean Lokal Dexie**:
   Store `employee_sync_queue` ([db.js:142](file:///d:/FACE%20VERIFICATION/src/db.js#L142)) tidak mengindeks `template_version`.
5. **Payload Sinkronisasi ke Server**:
   Di [syncEngine.js:728](file:///d:/FACE%20VERIFICATION/src/syncEngine.js#L728), payload `master_descriptors` yang dikirim ke Supabase menggunakan nilai konstan: `template_version: BIOMETRIC_CONFIG.TEMPLATE_VERSION` (selalu 2).
   Jika Supabase belum dimigrasi (error Postgres 42703), `syncEngine.js:740` menghapus `template_version` dan mengirim fallback payload standar.

### 2.4 Jalur "Upload Foto" (Bukan Kamera)
Pada [TabEmployeeManagement.jsx:272-273](file:///d:/FACE%20VERIFICATION/src/components/TabEmployeeManagement.jsx#L272-L273), saat admin memilih file gambar:
- Sistem mendeteksi wajah dengan `human.detect(img)`.
- Mengambil satu embedding tunggal: `result.face[0].embedding`.
- Tidak ada multi-frame, tidak ada kalkulasi median, dan tidak ada pengujian konsistensi.
- Namun saat disimpan ke cache lokal ([TabEmployeeManagement.jsx:429](file:///d:/FACE%20VERIFICATION/src/components/TabEmployeeManagement.jsx#L429)), data diberi label `template_version: BIOMETRIC_CONFIG.TEMPLATE_VERSION` (2), meskipun secara teknis hanya berasal dari 1 frame statis.

### 2.5 Audit Pemanggil `buildTemplate`, `buildTemplateDetailed`, dan `isGoodFrame`
- `buildTemplate`: Didefinisikan di [biometrics.js:399](file:///d:/FACE%20VERIFICATION/src/biometrics.js#L399). Nol pemanggil di seluruh kode aktif `src/`.
- `buildTemplateDetailed`: Didefinisikan di [biometrics.js:354](file:///d:/FACE%20VERIFICATION/src/biometrics.js#L354). Hanya diuji di [biometrics.test.js:55-100](file:///d:/FACE%20VERIFICATION/src/biometrics.test.js#L55-L100). Nol pemanggil di alur UI.
- `isGoodFrame`: Didefinisikan di [biometrics.js:427](file:///d:/FACE%20VERIFICATION/src/biometrics.js#L427). Hanya dipanggil di scanner absensi [TabFaceVerification.jsx:1293](file:///d:/FACE%20VERIFICATION/src/components/TabFaceVerification.jsx#L1293) untuk mengecek apakah wajah menghadap lurus. Tidak digunakan pada pendaftaran.

### 2.6 Kesimpulan BUTIR 1
**Status: TERBUKTI**. Template biometrik yang dihasilkan melalui kamera di lapangan saat ini adalah **Multi-Pose v2** (8 frame, jeda 300 ms, durasi aktif 2.4 hingga 12 detik, menggabungkan pose frontal dan miring $\le 17.2^\circ$, diagregasi menggunakan median per dimensi). Algoritma 7 frame 1 detik dengan eliminasi 1 outlier (`buildTemplateDetailed`) yang tertulis di PRD FR-9 adalah spesifikasi lama yang sudah ditinggalkan di kode aktif. Namun untuk jalur Upload Foto, template yang dihasilkan murni 1 frame statis yang sangat sempit dan rentan gagal saat diverifikasi di lapangan.

---

## 3. Rincian BUTIR 2: Skema Nyata Database vs PRD §7

### 3.1 Tabel Pencocokan Skema Lengkap

#### A. Supabase Cloud PostgreSQL
| Tabel | Kolom menurut PRD §7.1 | Kolom menurut Migrasi SQL & Kode | Status & Selisih |
| :--- | :--- | :--- | :--- |
| `master_descriptors` | `id`, `employee_id`, `descriptor_json`, `updated_at` | `id`, `employee_id`, `descriptor_json`, `updated_at`, `template_version`, `vector_checksum` | **TERBUKTI**: PRD kehilangan 2 kolom vital (`template_version`, `vector_checksum`). ([20261015_alter_master_descriptors.sql:4-6](file:///d:/FACE%20VERIFICATION/supabase/migrations/20261015_alter_master_descriptors.sql#L4-L6)) |
| `employees` | 15 kolom identitas + status biometrik | 15 kolom identitas + status biometrik | **SESUAI**: Struktur kolom selaras. |
| `employee_face_photos` | 10 kolom metadata foto | 10 kolom metadata foto | **SESUAI**: Sesuai dengan migrasi [20261015_create_employee_face_photos.sql:4-15](file:///d:/FACE%20VERIFICATION/supabase/migrations/20261015_create_employee_face_photos.sql#L4-L15). |
| `scan_attempts` | **TIDAK ADA DI PRD §7.1** | 19 kolom telemetri pemindaian | **TERBUKTI**: Tabel baru ini sepenuhnya absen dari PRD §7.1. ([20261015_create_scan_attempts.sql:5-26](file:///d:/FACE%20VERIFICATION/supabase/migrations/20261015_create_scan_attempts.sql#L5-L26)) |
| `attendance_logs` | 13 kolom dasar absensi | 13 kolom dasar + 4 kolom metrik: `match_cosine`, `match_threshold`, `template_version`, `facing_mode` | **TERBUKTI**: PRD kehilangan 4 kolom metrik biometrik. ([20261015_create_scan_attempts.sql:33-36](file:///d:/FACE%20VERIFICATION/supabase/migrations/20261015_create_scan_attempts.sql#L33-L36)) |

#### B. SQLite Native (Android APK)
| Tabel | Kolom menurut PRD §7.2 | Kolom menurut Kode (`sqliteService.ts`) | Status & Selisih |
| :--- | :--- | :--- | :--- |
| `local_master_descriptors` | 4 kolom dasar | 4 kolom dasar + `template_version`, `vector_checksum` | **TERBUKTI**: Ditambahkan via ALTER TABLE di baris 663-666. |
| `local_attendance_queue` | 18 kolom dasar | 18 kolom dasar + `match_cosine`, `match_threshold`, `template_version`, `facing_mode`, `vector_checksum_used`, `vector_source` | **TERBUKTI**: 6 kolom telemetri akurasi belum tercantum di PRD. |
| `local_attendance_logs` | 19 kolom dasar | 19 kolom dasar + 6 kolom telemetri biometrik | **TERBUKTI**: 6 kolom telemetri akurasi belum tercantum di PRD. |
| `local_employee_sync_queue` | 14 kolom dasar | 14 kolom dasar + `last_error_code`, `last_error_message`, `last_error_step`, `attempt_count`, `last_attempt_at` | **TERBUKTI**: 5 kolom telemetri audit kegagalan sync ada di kode baris 190-194 tapi belum di DDL PRD §7.2. |
| `local_scan_attempts_queue` | **TIDAK ADA DI PRD §7.2** | 21 kolom antrean telemetri scan | **TERBUKTI**: SQLite memiliki **11 tabel**, bukan 10 tabel seperti klaim PRD. |
| `local_employee_face_thumbs` | 7 kolom thumbnail | 7 kolom thumbnail | **SESUAI**: Tercantum di baris 240-248. |

#### C. Dexie.js (Web IndexedDB)
- **PRD §7.3**: Menyebut Dexie v9 dengan 9 object stores.
- **Kode Aktual ([db.js:133-145](file:///d:/FACE%20VERIFICATION/src/db.js#L133-L145))**: Berada pada **`dexieDb.version(10)`** dengan **11 object stores**:
  1. `user_master`
  2. `attendance_sync_queue`
  3. `employees_cache`
  4. `today_attendance_cache`
  5. `attendance_logs`
  6. `local_admins`
  7. `attendance_requests`
  8. `employee_delete_queue`
  9. `employee_sync_queue`
  10. `scan_attempts_queue`
  11. `employee_face_thumbs`

### 3.2 Pemetaan Nama Kolom `scan_attempts`
Perbandingan pemetaan antara SQLite, migrasi Supabase, dan `syncPendingScanAttempts()` ([syncEngine.js:996-1016](file:///d:/FACE%20VERIFICATION/src/syncEngine.js#L996-L1016)):
1. `max_cosine`: Di SQLite disimpan sebagai `max_cosine`. Kode sinkronisasi mendukung alias fallback `a.matched_cosine`.
2. `frames_used`: Di SQLite disimpan sebagai `frames_used`. Kode sinkronisasi mendukung alias fallback `a.frames_evaluated`.
3. `master_checksum`: Di SQLite disimpan sebagai `master_checksum`. Kode sinkronisasi mendukung alias fallback `a.vector_checksum_used`.
4. `app_version`: Kode sinkronisasi menyematkan fallback hardcoded `'3.2.1'` jika tidak diisi oleh pemanggil.

### 3.3 Berkas Kueri Verifikasi
Kueri SQL baca-saja telah dibuat di [docs/verify_schema.sql](file:///d:/FACE%20VERIFICATION/docs/verify_schema.sql) untuk dijalankan pengguna di Supabase SQL Editor guna memvalidasi status tabel, kolom, RLS, dan bucket.

### 3.4 Urutan Berkas Migrasi SQL di `supabase/migrations/`
1. `01_schema.sql`: Migrasi awal baseline (tabel dasar, RLS dasar, fungsi RPC).
2. `20261015_alter_master_descriptors.sql`: Menambah kolom `template_version`, `vector_checksum`, trigger update waktu, dan konversi format JSONB array. Bergantung pada `01_schema.sql`.
3. `20261015_create_employee_face_photos.sql`: Membuat tabel metadata foto wajah dan policy storage. Bergantung pada tabel `employees` di `01_schema.sql`.
4. `20261015_create_scan_attempts.sql`: Membuat tabel telemetri `scan_attempts` dan menambah 4 kolom biometrik pada `attendance_logs`. Bergantung pada `01_schema.sql`.

---

## 4. Rincian BUTIR 3: Foto Wajah di Bucket Publik

### 4.1 Nama Bucket yang Digunakan di Kode vs Migrasi
- **Di Kode Aplikasi**:
  - [syncEngine.js:1072](file:///d:/FACE%20VERIFICATION/src/syncEngine.js#L1072): Mengunggah foto master ke bucket **`attendance-photos`**.
  - [EmployeeFacePreviewModal.jsx:127](file:///d:/FACE%20VERIFICATION/src/components/EmployeeFacePreviewModal.jsx#L127): Mengambil foto master dari bucket **`attendance-photos`**.
  - [vector_integrity.test.js:228-231](file:///d:/FACE%20VERIFICATION/src/vector_integrity.test.js#L228-L231): Menguji bahwa bucket yang digunakan adalah **`attendance-photos`** dan bukan `employee-face-photos`.
  - [enrollmentPhoto.js](file:///d:/FACE%20VERIFICATION/src/utils/enrollmentPhoto.js): Tidak mendefinisikan bucket (murni fungsi cropping canvas lokal).
- **Di Berkas Migrasi**:
  - [20261015_create_employee_face_photos.sql:44](file:///d:/FACE%20VERIFICATION/supabase/migrations/20261015_create_employee_face_photos.sql#L44): Mendaftarkan bucket **`employee-faces`** dengan status `public = false` (privat).
- **Variasi Nama Lain yang Ditemukan di Catatan Task**:
  - `employee-faces` (disebut di rencana awal perbaikan foto)
  - `employee-face-photos` (disebut di instruksi manual lama)
  - `attendance-photos` (bucket riil yang aktif di Supabase dan dipakai oleh kode)

### 4.2 Metode Pembuatan URL Foto
- Di [EmployeeFacePreviewModal.jsx:128](file:///d:/FACE%20VERIFICATION/src/components/EmployeeFacePreviewModal.jsx#L128) dan [syncEngine.js:356](file:///d:/FACE%20VERIFICATION/src/syncEngine.js#L356), URL dibangun menggunakan:
  `supabase.storage.from('attendance-photos').getPublicUrl(storagePath)`
- URL yang dihasilkan adalah URL publik permanen tanpa kedaluwarsa (*no expiration*). Sistem tidak menggunakan `createSignedUrl`.

### 4.3 Prediktabilitas Path Penyimpanan (`storage_path`)
- Foto master diunggah dengan pola ([syncEngine.js:1070](file:///d:/FACE%20VERIFICATION/src/syncEngine.js#L1070)):
  `faces/${empId}/master.jpg`
- Nilai `empId` adalah integer BigInt sekuensial yang digenerate oleh server Supabase (contoh: `faces/1/master.jpg`, `faces/2/master.jpg`, `faces/42/master.jpg`).
- Path ini sangat mudah ditebak (*predictable sequential enumeration*).

### 4.4 Berbagi Bucket Antara Foto Master dan Log Absensi
Folder foto master (`faces/`) dan folder foto bukti absensi harian (`absensi_harian/`) berada di dalam bucket yang sama yaitu **`attendance-photos`**. Akibatnya, seluruh kebijakan akses Storage (publik/privat) berlaku seragam untuk kedua jenis foto tersebut.

### 4.5 Analisis Kebijakan Akses (Storage Policy)
- Bucket `attendance-photos` disetel sebagai bucket publik agar bukti foto absensi dapat dimuat secara instan di web dashboard tanpa proses signing token berulang.
- Kunci `SUPABASE_ANON_KEY` tertanam langsung di dalam bundle klien APK Android dan PWA.
- Pada `syncEngine.js:1075`, upload menggunakan opsi `upsert: true`.
- **Fakta Risiko**: Jika policy storage pada bucket `attendance-photos` mengizinkan role `anon` untuk melakukan operasi `INSERT` atau `UPDATE`, maka siapa pun yang memiliki `anon_key` dapat menimpa berkas `master.jpg` milik karyawan mana pun hanya dengan menebak nomor urut ID karyawannya.

### 4.6 Ringkasan Risiko
Foto pendaftaran master pemanen tersimpan di bucket publik dengan path terstruktur dan ID berurutan (`faces/{id}/master.jpg`). Hal ini memungkinkan pihak luar yang mengetahui URL Supabase untuk melihat seluruh foto wajah karyawan secara berurutan (*enumeration*). Selain itu, bila policy Storage mengizinkan anon-write, terdapat risiko integritas di mana foto master dapat ditimpa oleh entitas tanpa hak. Mengingat foto wajah merupakan data biometrik sensitif, arsitektur penyimpanan ini memerlukan evaluasi dan persetujuan resmi dari pihak manajemen dan bagian hukum perusahaan.

### 4.7 Opsi Mitigasi Arsitektur (Tanpa Menerapkannya)
1. **Opsi 1 (Bucket Privat & Signed URL)**: Memindahkan folder `faces/` ke bucket privat (`employee-faces`) dan memanfaatkan `createSignedUrl` dengan masa berlaku singkat (misal 5 menit) untuk pratinjau di dashboard. Konsekuensi: memerlukan penanganan token refresh dan latensi tambahan saat membuka modal foto.
2. **Opsi 2 (Path Berbasis UUID Acak / Hash Token)**: Mengubah path dari `faces/${empId}/master.jpg` menjadi `faces/${uuid_v4}/master.jpg`. Konsekuensi: mengeliminasi risiko tebakan berurutan, namun URL tetap publik jika bucket tidak diubah.
3. **Opsi 3 (Pengetatan Policy Storage via RLS)**: Membatasi hak `INSERT` dan `UPDATE` pada folder `faces/` hanya untuk role `authenticated` atau melalui service role proxy, sementara hak `SELECT` dikontrol ketat.

---

## 4. Rincian BUTIR 4: Kontradiksi Dokumen

### 4.1 Express API Tier 1: 410 Gone vs PRD Aktif
- **PRD §3.1 & §6**: Mendokumentasikan `/api/attendance/verify`, `/api/biometrics/register`, dan `/api/biometrics/master/:id` sebagai alur aktif Tier 1 dengan timeout 3 detik sebelum fallback ke Supabase.
- **Kode Server ([server.js:84-89](file:///d:/FACE%20VERIFICATION/server.js#L84-L89))**:
  ```javascript
  apiRouter.all(['/attendance/verify', '/biometrics/register', '/biometrics/master/:id'], (req, res) => {
    return res.status(410).json({
      success: false,
      message: 'Endpoint decommissioned. AgriFace uses client-side on-device biometric verification and direct Supabase sync.'
    });
  });
  ```
- **Kode Klien**: Pencarian terhadap pemanggilan endpoint tersebut di folder `src/` menghasilkan **0 hasil**. Variabel `API_BASE_URL` hanya diimpor sebagai *dead import* di beberapa file komponen. Seluruh logika verifikasi biometrik berjalan murni di perangkat klien (*on-device*), dan transaksi data dikirim langsung ke Supabase SDK.
- **Status: TERBUKTI**. Konsep Tier 1 Express API di PRD adalah dokumentasi peninggalan (*phantom documentation*) yang sudah tidak ada implementasi fisiknya.

### 4.2 Hak Akses `/sinkronisasi`: FR-14 vs Peta Rute Router
- **PRD FR-14**: Menyatakan bahwa akun `regional_admin` dan `headoffice_admin` (`admin.hq`) dapat menggunakan halaman `/sinkronisasi` untuk memantau integritas vektor dan antrean per unit kebun.
- **Kode Router ([src/router.jsx:239-247](file:///d:/FACE%20VERIFICATION/src/router.jsx#L239-L247))**:
  ```javascript
  // Estate-admin-only routes
  {
    element: <RoleGuard allowedRoles={['estate_admin']} />,
    children: [
      { path: 'absensi', element: <AbsensiRoute /> },
      { path: 'karyawan', element: <KaryawanRoute /> },
      { path: 'sinkronisasi', element: <SinkronisasiPage /> },
      ...
    ],
  }
  ```
- **Kode Menu ([src/config/menu.js:61](file:///d:/FACE%20VERIFICATION/src/config/menu.js#L61))**:
  Menu "Sinkronisasi" memiliki batasan `roles: ['estate_admin']`.
- **Fakta di Lapangan**: Jika pengguna login sebagai `admin.hq` (`headoffice_admin`), menu Sinkronisasi tidak muncul di sidebar. Jika admin HQ mencoba mengakses URL `/sinkronisasi` secara langsung, `RoleGuard` akan memblokir dan mengarahkan pengguna ke halaman login/dashboard. Akibatnya, fitur dropdown filter kebun untuk HQ di `SinkronisasiPage.jsx` **tidak akan pernah bisa diakses oleh akun Head Office**.
- **Status: TERBUKTI**.

### 4.3 Nama Peran: `headoffice_admin` vs `admin.hq`
- Di database (`admin_auth.role`) dan konfigurasi router (`RoleGuard`), nama peran resmi adalah **`headoffice_admin`**.
- Nilai **`admin.hq`** adalah **username** dari akun bawaan Head Office ([AuthContext.jsx:121](file:///d:/FACE%20VERIFICATION/src/context/AuthContext.jsx#L121)).
- Di beberapa bagian PRD dan kode (misal `App.jsx:258, 531` dan `SinkronisasiPage.jsx:119`), istilah `admin.hq` sering tertukar penyebutannya sebagai peran. Pemetaan yang benar: role = `headoffice_admin`, username default = `admin.hq`.

### 4.4 Ambang Batas Duplikasi Wajah (Duplicate Threshold)
| Lokasi di Kode / Dokumen | Nilai Ambang | Catatan Implementasi |
| :--- | :--- | :--- |
| `src/biometrics.js:16` (`BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD`) | `0.88` | Konstanta acuan terpusat. |
| `src/pages/DaftarKaryawanPage.jsx:160` (`EDIT_DUPLICATE_THRESHOLD`) | `0.88` | Didefinisikan ulang secara lokal sebagai konstanta terpisah. |
| `src/components/TabEmployeeManagement.jsx:66` (`DUPLICATE_THRESHOLD`) | `0.88` | Didefinisikan ulang secara lokal sebagai konstanta terpisah. |
| PRD §3.2 butir 4 & FR-3 | `0.88` | Selaras secara nilai numerik. |

**Temuan Kritis**: Di [DaftarKaryawanPage.jsx:296, 736](file:///d:/FACE%20VERIFICATION/src/pages/DaftarKaryawanPage.jsx#L296), pemeriksaan duplikasi dibungkus oleh kondisi:
`if (isOnlineRef.current && bestSim >= BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD)`
Artinya, ketika mandor melakukan pendaftaran ulang atau rescan dalam kondisi **luring (offline)**, validasi anti-duplikasi wajah **dilewati sama sekali**, bertentangan dengan PRD FR-3 yang menyatakan bahwa pengecekan anti-duplikasi divalidasi ke seluruh arsip lokal SQLite/Dexie.

### 4.5 Sepuluh Kontradiksi Tambahan antara PRD dan Kode
(Diurutkan berdasarkan tingkat dampak terhadap kegagalan absensi / tidak match saat check-out sore hari):

1. **Jalur Upload Foto Menghasilkan Template 1 Frame Statis Tanpa Multi-Pose** (Dampak: SANGAT TINGGI)
   Karyawan yang didaftarkan lewat file gambar di `TabEmployeeManagement.jsx:272` hanya memiliki 1 frame vektor tanpa variasi sudut yaw. Saat pekerja panen pulang sore hari dengan kondisi lelah, sudut kepala miring, atau pencahayaan redup, verifikasi 1-to-1 gagal mencapai ambang 0.92.
2. **Bypass Validasi Anti-Duplikasi Wajah Saat Offline di Daftar Karyawan** (Dampak: TINGGI)
   Pemeriksaan duplikasi di `DaftarKaryawanPage.jsx:296` hanya aktif bila `isOnlineRef.current` bernilai benar. Di lapangan tanpa sinyal, wajah yang mirip atau salah pilih orang bisa tertimpa tanpa peringatan sistem.
3. **Penyimpanan `template_version` Tertimpa Nilai Default Saat Sinkronisasi** (Dampak: TINGGI)
   Antrean `local_employee_sync_queue` tidak menyimpan `template_version`. Di `syncEngine.js:728`, saat dikirim ke server, nilai `template_version` dipaksa bernilai 2, sehingga server menganggap seluruh karyawan memiliki template multi-pose meskipun aslinya berasal dari 1 foto statis.
4. **Pemblokiran Rute `/sinkronisasi` untuk Akun Head Office** (Dampak: SEDANG)
   Fitur diagnostik integritas vektor dan tombol pemulihan mandiri tidak dapat diakses oleh admin pusat karena rute dikunci `estate_admin` di `router.jsx:239`.
5. **Express API Decommissioned (410 Gone) Masih Dicatat Sebagai Tier 1 Aktif** (Dampak: SEDANG)
   PRD §3.1 masih mengklaim alur absensi melewati backend Express API sebelum jatuh ke Supabase, padahal sistem sepenuhnya berkomunikasi via Supabase SDK dan basis data lokal.
6. **Hardcoded Fallback `app_version: '3.2.1'` pada Pengiriman Telemetri** (Dampak: SEDANG)
   Di `syncEngine.js:1013`, data telemetri scan attempts menyematkan versi hardcoded `'3.2.1'` jika tidak diisi, mengaburkan analisis telemetri kegagalan absensi per versi aplikasi.
7. **Ketidaksesuaian Jumlah Tabel SQLite (11 Tabel di Kode vs 10 Tabel di PRD)** (Dampak: RENDAH)
   Tabel `local_scan_attempts_queue` ada di SQLite baris 216 namun diabaikan dari daftar 10 tabel di PRD §7.2.
8. **Ketidaksesuaian Versi Dexie (v10 dengan 11 Stores vs PRD v9 dengan 9 Stores)** (Dampak: RENDAH)
   Kode `src/db.js:133` menggunakan schema version 10 dengan 11 store, sedangkan PRD mencatat version 9 dengan 9 store.
9. **Dualitas Bucket Storage (`attendance-photos` vs `employee-faces`)** (Dampak: RENDAH terhadap absensi, TINGGI terhadap keamanan)
   Kode memakai bucket publik `attendance-photos`, sedangkan migrasi mendefinisikan bucket privat `employee-faces`.
10. **Batas Konsistensi Enrollment dan Formula Eliminasi Outlier** (Dampak: SEDANG)
    PRD FR-9 menyebut pendaftaran 7 frame dengan eliminasi 1 outlier via `buildTemplateDetailed`. Kode aktual memakai 8 frame multi-pose dengan kalkulasi median per dimensi via `selectEnrollmentFrames`.

---

## 5. Panduan Verifikasi Mandiri oleh Pengguna

Untuk membuktikan kebenaran struktur database Supabase yang telah diterapkan di cloud, jalankan kueri pada berkas [docs/verify_schema.sql](file:///d:/FACE%20VERIFICATION/docs/verify_schema.sql) di Supabase SQL Editor:

1. **Uji 1: Kolom `master_descriptors`**:
   - *Ekspektasi Berhasil*: Muncul kolom `template_version` (integer) dan `vector_checksum` (text).
   - *Bila Gagal*: Kolom tersebut tidak ada (error 42703 saat sync).
2. **Uji 2: Keberadaan Tabel `scan_attempts` dan `employee_face_photos`**:
   - *Ekspektasi Berhasil*: Menghasilkan nilai regclass nama tabel (bukan NULL).
3. **Uji 3: Tipe Elemen `descriptor_json`**:
   - *Ekspektasi Berhasil*: `vector_storage_type` hanya menghasilkan nilai `'array'`. Jika ada nilai `'string'`, berarti data vektor lama belum dikonversi.
4. **Uji 4: Checksum & Template Version**:
   - *Ekspektasi Berhasil*: `null_checksum_count` bernilai 0.
5. **Uji 5: Bucket Storage**:
   - *Ekspektasi*: Periksa apakah bucket `attendance-photos` bernilai `public = true`, dan apakah bucket `employee-faces` ada di daftar.

---

## 6. Daftar Usulan Perubahan PRD (Teks Lama vs Teks Pengganti)

Berikut adalah usulan perubahan redaksional pada [PRD.md](file:///d:/FACE%20VERIFICATION/PRD.md) berdasarkan temuan audit di atas. Perubahan ini **tidak diterapkan ke PRD** pada tugas ini:

### Usulan 1: Koreksi Alur Pendaftaran Wajah (PRD §3.2 butir 3 & FR-9)
- **Teks Lama PRD**:
  > "3. Eliminasi Outlier Pendaftaran Wajah (`buildTemplateDetailed`): Saat pendaftaran karyawan baru, sistem mengambil $N = 7$ sampel frame diskrit (interval minimal 150ms). Sistem menghitung centroid kasar, membuang 1 sampel dengan deviasi cosine terbesar, dan menghitung rata-rata L2-normalized..."
- **Usulan Teks Pengganti**:
  > "3. Pendaftaran Multi-Pose v2 (`selectEnrollmentFrames`): Saat pendaftaran via kamera, sistem mengambil $N = 8$ frame diskrit (jeda minimal 300 ms, durasi aktif maksimal 12 detik) yang mencakup pose tatap lurus (frontal, yaw $\le 0.15$) dan oportunistik miring (turned, yaw $0.12 - 0.30$). Agregasi vektor master menggunakan kalkulasi median per dimensi (`medianVectorPerDimension`) yang dinormalisasi L2 dengan syarat konsistensi $\ge 0.90$. Jika pendaftaran dilakukan via Upload Foto statis, sistem mengekstrak 1 frame tunggal tanpa multi-pose."

### Usulan 2: Koreksi Express API Tier 1 (PRD §2, §3.1, §6)
- **Teks Lama PRD**:
  > "Tier 1: Express API (Local Server). Mengirim data ke endpoint `/api/attendance/verify` (timeout 3 detik)..."
- **Usulan Teks Pengganti**:
  > "Arsitektur Klien Murni & Direct Supabase Sync: Endpoint verifikasi backend Express (`/api/attendance/verify`, `/api/biometrics/register`, `/api/biometrics/master/:id`) telah dipensiunkan (HTTP 410 Gone). Seluruh pencocokan biometrik 1-to-1 diproses secara lokal di perangkat klien (*on-device client engine*), dan sinkronisasi data dilakukan langsung via Supabase SDK dan antrean persisten lokal (SQLite/Dexie)."

### Usulan 3: Koreksi Otorisasi Rute `/sinkronisasi` (PRD §5 FR-14 & §9)
- **Teks Lama PRD §9**:
  > `[RoleGuard: estate_admin] ├── /sinkronisasi`
- **Usulan Teks Pengganti**:
  > Di `src/router.jsx` dan `src/config/menu.js`, rute `/sinkronisasi` dipindahkan ke grup bersama atau `RoleGuard allowedRoles={['estate_admin', 'regional_admin', 'headoffice_admin']}` agar fitur filter dan audit kebun untuk akun Head Office (`admin.hq`) dan Regional Admin dapat diakses sesuai spesifikasi FR-14.

### Usulan 4: Pembaruan Skema Database (PRD §7)
- **Teks Lama PRD §7**:
  > Menyebut 10 tabel SQLite dan Dexie v9 (9 stores), tanpa tabel `scan_attempts`.
- **Usulan Teks Pengganti**:
  > Memperbarui daftar skema menjadi **11 tabel SQLite Native** (menambahkan `local_scan_attempts_queue` dan kolom telemetri error pada antrean sync) dan **Dexie.js v10 dengan 11 Object Stores** (menambahkan `scan_attempts_queue` dan `employee_face_thumbs`), serta mendokumentasikan tabel `public.scan_attempts` dan kolom telemetri `attendance_logs` pada skema Supabase.
