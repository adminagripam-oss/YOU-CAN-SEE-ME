# Product Requirement Document (PRD) & System Architecture Analysis
## AgriFace: Sistem Absensi Biometrik Wajah Perkebunan Berbasis 1-to-1 Verification Engine, Capgo OTA Updater & Hybrid Offline-First Architecture

- **Nama Proyek**: AgriFace (AgriFace Biometric Attendance System)
- **Versi Dokumen & Proyek**: 3.2.3-minor-release (Oktober 2026)
- **Database Engine**: Supabase Cloud PostgreSQL (JSONB Vector Storage, RPC), Dexie.js v9 (Web IndexedDB - 9 Object Stores), `@capacitor-community/sqlite` (Android Native SQLite - 10 Tables, TEXT Primary Key)
- **Biometric Engine**: `@vladmandic/human` (1024-dim Embedding Vector, FP16 WebGL Precision) + MediaPipe Face Mesh (468 landmarks) + EAR Liveness Engine + Cosine Similarity + Piecewise Linear Display Score + 2-Pass Outlier Filter (`buildTemplateDetailed`)
- **Update System**: `@capgo/capacitor-updater` Over-The-Air (OTA) Bundle Update Engine with POSIX-compliant ZIP
- **Target Kompleksitas**: $O(1)$ Time Complexity Direct Lookup Matching

---

## 1. Pendahuluan & Latar Belakang

> **Minor Release Note (v3.2.3)**: Sinkronisasi Terpadu Foto Master Wajah, Scoping Autorisasi Unit Kebun, dan Diagnostik Integritas Vektor Offline-First:
> 1) **Alur Penyimpanan & Resolusi Foto Master Wajah Pemanen (`attendance-photos/faces/` & `employee_face_photos`)**: Standarisasi alur sinkronisasi upload foto master pendaftaran karyawan ke cloud storage Supabase pada bucket `attendance-photos` (sub-folder `faces/${empId}/master.jpg`) dan tabel metadata `public.employee_face_photos`. Implementasi 3-tier resolusi cerdas di `EmployeeFacePreviewModal.jsx` (Lokal SQLite/Dexie -> Cloud Public URL & Thumbnail Base64 -> Fallback Foto Absensi Terakhir) serta penegasan badge visual status sumber foto yang jelas. Akun `admin.hq` (Head Office Admin) kini dapat melihat foto pemanen dari seluruh kebun secara global via tombol Mata (`<Eye>`) di Daftar Karyawan.
> 2) **Scoping Autorisasi Unit Kebun pada Halaman Sinkronisasi (`/sinkronisasi`)**: Penegakan hak akses berbasis unit kebun pada antrean Log Absensi (`attendanceQueue`), antrean Pendaftaran Karyawan (`employeeQueue`), dan diagnostik Integritas Vektor. Akun `estate_admin` dibatasi ketat hanya pada data dari kebunnnya (`user.kebun`), akun `regional_admin` pada wilayahnya (`user.region`), dan akun `admin.hq` memiliki akses global seluruh unit kebun.
> 3) **Dropdown Filter Kebun & Kolom Kebun Khusus `admin.hq`**: Penambahan filter unit kebun (A-Z) yang dibungkus dengan standar desain proyek (`div.custom-select-wrapper` + `ChevronDown`) serta penambahan kolom "Kebun" pada tabel diagnostik integritas vektor untuk transparansi audit per unit.
> 4) **Ketahanan Mode Luring pada Diagnostik Integritas Vektor**: Penonaktifan tombol audit saat perangkat sedang luring (offline) disertai banner peringatan informatif, memastikan tidak ada unhandled network error saat mandor berada di area *blank-spot* kebun.

> **Minor Release Note (v3.2.1 - v3.2.2)**: Peningkatan Akurasi Biometrik, Eliminasi Outlier Pendaftaran Wajah, Penyelarasan Kurva Tampilan Skor, dan Stabilisasi Antrean Pendaftaran:
> 1) **Algoritma Eliminasi Outlier Pendaftaran Wajah (`buildTemplateDetailed`)**: Implementasi kalkulasi centroid 1024-dimensi dan eliminasi matematis satu sampel terburuk (deviasi cosine terjauh dari centroid) saat pengambilan 7 frame diskrit pendaftaran karyawan baru.
> 2) **Gate Konsistensi Biometrik (`ENROLL_MIN_CONSISTENCY: 0.90`)**: Perlindungan template master terhadap gerakan kepala mendadak saat enrollment. Jika konsistensi antar-sampel $< 0.90$, proses otomatis diulang dengan pesan edukatif: *"Wajah terlalu banyak bergerak. Tahan posisi..."*.
> 3) **Penyelarasan Skor Tampilan Piecewise Linear (`displayScore`)**: Menggantikan kurva eksponensial lama dengan kurva Piecewise Linear dua-fase terkalibrasi, memastikan nilai ambang batas kelulusan cosine $0.92$ tepat tampil sebagai **`82.0%`** di layar scanner demi transparansi UX mandor dan pekerja.
> 4) **Pengetatan Threshold Cosine Match**: Default threshold kelulusan pencocokan wajah dinaikkan dari $0.90$ menjadi **`0.92`**, menekan *False Accept Rate (FAR)* hingga $< 0.1\%$.
> 5) **Stabilisasi Normalisasi ID Antrean Pendaftaran Offline**: Pemisahan eksekusi pendaftaran inti (`employees` + `master_descriptors`) dari langkah sekunder (foto master & telemetri scan attempts), serta pencatatan audit kegagalan (`last_error_code`, `last_error_message`, `attempt_count`).

> **Major Release Note (v3.2.0)**: Major update arsitektur Full-Bleed Mobile Data Tables, Eliminasi Gutter Layout, Pembersihan Hierarki Tag Semantic Main, Smooth Stacking Context Sliding Header, Anti-Distortion Biometric Canvas, dan Dual-Camera Scanner.
> 1) **Full-Bleed Mobile Data Tables (100% Viewport)**: Transformasi menyeluruh antarmuka tabel data pada 4 modul utama (Log Absensi, Monitoring Absensi, Daftar Karyawan, dan Mutasi Karyawan) untuk layar ponsel ($\le 768\text{px}$). Menggunakan arsitektur `MobileDataPage`, `MobileFilterSheet`, `MobileTableViewport`, dan hook `useIsMobileLayout` dengan pemenuhan standar metrik UX: eliminasi total margin/gutter ancestor, lebar 100% sejajar topbar, tinggi header $\le 200\text{px}$, area tabel $\ge 65\%$ tinggi layar, baris tampak $\ge 12$ baris, dan nol horizontal scroll pada `body`.
> 2) **Smooth Stacking Context & Zero Collision Sliding Header**: Perbaikan stacking context z-index berjenjang pada tabel Monitoring Absensi mobile (`th.col-sticky-nama` z-index: 35 dengan solid background & box-shadow; kolom NIK/Kebun/Afdeling z-index: 6) sehingga saat digeser horizontal ke kanan seluruh kolom identitas lain meluncur mulus (*smooth glide*) di bawah kolom Nama Karyawan tanpa tabrakan.
> 3) **Pembersihan Toolbar & Pemusatan Registrasi Wajah**: Tombol "Tambah" dihapus dari halaman Daftar Karyawan (`/daftar-karyawan`) untuk seluruh akun. Pendaftaran karyawan terpusat eksklusif di menu mandiri "Registrasi Wajah" (`/karyawan`), sekaligus mencegah tabrakan/tumpang tindih dengan judul halaman pada tampilan mobile.
> 4) **Anti-Distortion Canvas & Mesh Gepeng Fix**: Sinkronisasi dimensi fisik display box canvas dengan resolusi internal detektor AI via `ResizeObserver` berbasis $\min(\text{stageW}/\text{targetW}, \text{stageH}/\text{targetH})$ untuk mengeliminasi distorsi non-uniform ("mesh gepeng") pada perangkat mobile.
> 5) **Dual-Camera Rolling & Default Back Camera**: Penambahan kontrol tukar kamera (*front/environment*) di seluruh modal pemindai biometrik (Absensi, Pendaftaran, Rescan Wajah) dengan *default facingMode* kamera belakang (*environment*) untuk perangkat bergerak demi kepraktisan operasional mandor di kebun.
> 
> *(Catatan Patch Sebelumnya: v3.1.4–v3.1.9)*: Implementasi `FullscreenScanShell`, Face Frame Guidance (`FaceFrameTemplate`), carousel fix, serta peningkatan toleransi liveness biometrik.
> *(Catatan V3.1.3)*: Pembaruan visual Executive Dashboard (isolasi `AnimatedNumber`, koreksi formula TK Hadir, dan `ComboBarLineChart`).
> *(Catatan V3.1.2)*: Safe SQLite Migration, pembaruan cache sync, dan validasi ketat Array Kosong/NaN pada biometrik.

Sistem absensi biometrik wajah konvensional umumnya mengabaikan efisiensi dengan menggunakan pendekatan **1-to-N (Verifikasi 1-ke-Banyak)**. Skema $O(N)$ ini menimbulkan kendala fatal saat jumlah karyawan membengkak, termasuk lonjakan latensi server dan tingkat *false positive* yang tinggi.

Sistem AgriFace menerapkan arsitektur **1-to-1 Direct Lookup ($O(1)$)**, **Offline-First Hybrid Database (IndexedDB + Native SQLite)**, serta **Hybrid 3-Tier Data Resiliency Engine** yang menjamin ketersediaan sistem 99.9% meskipun diakses dari perangkat mobile dengan jaringan tidak stabil atau dalam kondisi luring (offline) di tengah perkebunan.

---

## 2. Arsitektur Sistem (Tech Stack & Architecture)

```
                                  +-------------------------------------------------+
                                  |         CLIENT FRONTEND (PWA & NATIVE APK)      |
                                  |  React 19 + Vite 8 + React Router v7 + Capgo    |
                                  +-----------------------+-------------------------+
                                                          |
                 Tier 1: Express API (Local Server)       |       Tier 2: Direct Supabase SDK
                 (Hybrid Server-First Flow)               |       (HTTPS / CORS Safe Fallback)
                 +-------------------+                    |       +-------------------+
                 |                   |                    |       |                   |
                 v                   |                    v       v                   |
  +---------------------------+      |      +---------------------------+             |
  |    EXPRESS BACKEND API    |      +----->|   SUPABASE CLOUD DATABASE |             |
  |  - Endpoint /api/verify   |             |   PostgreSQL Engine       |             |
  |  - Timeout 3 Detik        |             |   (JSONB Vector Storage)  |<------------+
  +---------------------------+             +---------------------------+
                                                          ^
                              Tier 3: Hybrid Offline Storage (Queue & Sync Engine)
                +-----------------------------------------------------------------------+
                |        NATIVE SQLITE (APK)         /       INDEXEDDB DEXIE v9 (WEB)   |
                |  - local_employees                 /  - employees_cache               |
                |  - local_master_descriptors        /  - user_master                   |
                |  - local_attendance_queue          /  - attendance_sync_queue         |
                |  - local_today_attendance_cache    /  - today_attendance_cache        |
                |  - local_attendance_logs           /  - attendance_logs               |
                |  - local_admins                    /  - local_admins                  |
                |  - local_employee_sync_queue       /  - employee_sync_queue           |
                |  - local_attendance_requests       /  - attendance_requests           |
                |  - local_employee_delete_queue     /  - employee_delete_queue         |
                +-----------------------------------------------------------------------+
```

### 2.1 Frontend (Client-Side SPA, PWA & Native Android Shell)
- **Framework & UI Engine**: React v19.2.8 & Vite v8.1.5.
- **Routing & Role Guard**: React Router v7 (`createBrowserRouter`) dengan hierarki route terisolasi melalui `ProtectedRoute`, `PublicRoute`, dan `RoleGuard` (`estate_admin`, `regional_admin`, `headoffice_admin`).
- **Mobile UI & Full-Bleed Table Architecture**: Modul tabel data mobile diisolasi secara kondisional melalui hook `useIsMobileLayout` ($\le 768\text{px}$) menggunakan komponen `MobileDataPage`, `MobileFilterSheet` (bottom sheet dialog), dan `MobileTableViewport` dengan *zero-regression* terhadap tata letak desktop.
- **Biometric Viewport & Camera Controller**: Hook `useScanViewport` dan komponen `FaceScanSurface` mengintegrasikan kalkulasi aspek rasio dinamis berbasis `ResizeObserver` serta kontrol pergantian stream kamera (depan/belakang).
- **OTA Update Engine**: `@capgo/capacitor-updater` terintegrasi dengan bucket Supabase `ota-updates` (menggunakan POSIX-compliant zip) dan modal *blocking* pembaruan saat boot aplikasi.
- **Service Worker & Caching**: Bypass caching eksplisit (`cache: 'no-store'`) untuk domain `*.supabase.co` di `sw.js` agar data dari server selalu segar tanpa fenomena data stagnan (*stale data*).
- **Biometric Processing Engine**: Pustaka `@vladmandic/human` dijalankan lokal di klien dengan paksaan *FP16 WebGL Precision* untuk menjamin standar nilai desimal 1024-dimensi yang identik secara universal melintasi HP, Tablet, maupun Laptop. Model caching IndexedDB dinonaktifkan (`cacheModels: false`) untuk mencegah korupsi executor pada WebView Android dan mode PWA.

### 2.2 Backend & Database Infrastructure
- **Serverless / Hybrid Node.js**: Sistem mendukung akses API lokal (Express.js) dengan batas *timeout* dinamis 3 detik. Jika backend lokal offline atau lambat, klien secara *fallback* langsung mengeksekusi tulis/baca ke SDK Supabase Cloud.
- **Database Terpusat**: Supabase PostgreSQL dengan pengamanan *Row Level Security* (RLS) serta fungsi *Remote Procedure Call* (RPC) seperti `verify_admin_login` (pgcrypto auth) dan `sync_offline_employee` untuk penyelesaian konflik *(Server-Wins Conflict Resolution)*.

---

## 3. Analisis Mendalam & Fitur Utama Arsitektur

### 3.1 Arsitektur Absensi Hybrid Server-First (3-Tier Submission Flow)
Untuk menjamin pengiriman log absensi yang andal di area minim sinyal perkebunan:
1. **Tier 1 (Server-Authoritative)**: Mengirim data (termasuk GPS dan Euclidean distance) ke endpoint `/api/attendance/verify`.
2. **Tier 2 (Direct Supabase Fallback)**: Jika server backend *timeout* (3 detik) atau gagal diakses, sistem memotong jalur dan menulis data absensi langsung ke Supabase Cloud DB melalui Supabase JavaScript SDK.
3. **Tier 3 (Local SQLite/Dexie Offline Buffer)**: Jika perangkat sepenuhnya luring (offline / blank spot), log masuk ke antrean `local_attendance_queue` (SQLite pada APK) atau `attendance_sync_queue` (Dexie pada browser) untuk disinkronisasi otomatis saat jaringan tersedia.

### 3.2 Algoritma Pemrosesan Biometrik & Pengenalan Wajah

Sistem menggunakan model `@vladmandic/human` (`blazeface` + `facemesh` + `faceres`) untuk mengekstraksi **1024-Dimensional FaceRes Embedding Vector** pada presisi *FP16 WebGL*. Pendekatan yang digunakan adalah **1-to-1 O(1) Direct Lookup** antara wajah di kamera dengan vektor master karyawan yang dipilih.

#### 1. Komputasi Pencocokan (Cosine Similarity)
Komputasi kedekatan antar wajah murni menggunakan **Cosine Similarity** (Dot Product dari dua vektor yang telah dinormalisasi L2):
$$\text{Cosine}(A, B) = \frac{A \cdot B}{\|A\|_2 \|B\|_2} = \sum_{i=1}^{1024} A'_i B'_i$$

#### 2. Penyelarasan Kurva Tampilan Skor (Piecewise Linear Display Alignment)
Pada ruang vektor 1024-D, pasangan impostor (wajah berbeda) berkumpul di rentang cosine $0.60 - 0.84$, sedangkan genuine pairs berkumpul di $0.88 - 0.98$. Untuk menciptakan ambang kelulusan yang transparan dan intuitif bagi pengguna lapangan, sistem memetakan raw cosine ke persentase tampilan visual ($0 - 100\%$) menggunakan **Piecewise Linear Curve** dua fase:
- $\text{FLOOR} = 0.70$ (di bawah 0.70 bernilai $0\%$)
- $\text{THRESHOLD} = 0.92$ (titik ambang batas kelulusan verifikasi)
- $\text{DISPLAY\_TARGET} = 82\%$ (skor visual pada titik ambang batas kelulusan)
- $\text{CEIL} = 0.98$ (di atas 0.98 bernilai $100\%$)

Formula matematis:
$$\text{displayScore}(\text{cos}) = \begin{cases} 
0 & \text{jika } \text{cos} \le 0.70 \\
\left(\frac{\text{cos} - 0.70}{0.92 - 0.70}\right) \times 82\% & \text{jika } 0.70 < \text{cos} < 0.92 \\
82\% + \left(\frac{\text{cos} - 0.92}{0.98 - 0.92}\right) \times (100 - 82)\% & \text{jika } 0.92 \le \text{cos} < 0.98 \\
100\% & \text{jika } \text{cos} \ge 0.98
\end{cases}$$

Dengan kalibrasi ini:
- Cosine $0.70 \to 0.0\%$
- Cosine $0.90 \to 73.6\%$ (Belum Lulus)
- **Cosine $0.92 \to 82.0\%$ (Tepat Garis Kelulusan Absensi)**
- Cosine $0.95 \to 91.0\%$
- Cosine $0.98 \to 100.0\%$

#### 3. Eliminasi Outlier Pendaftaran Wajah (`buildTemplateDetailed`)
Saat pendaftaran karyawan baru, sistem mengambil $N = 7$ sampel frame diskrit (interval minimal $150\text{ms}$). Untuk menghasilkan vektor master yang murni dan bebas dari blur/kedipan:
1. **Pass 1 (Centroid Kasar)**: Hitung titik tengah (centroid) dari seluruh sampel:
   $$\bar{C} = \frac{1}{N} \sum_{k=1}^N V_k$$
2. **Perangkingan & Drop Outlier**: Setiap sampel diperingkat berdasarkan kedekatan cosine-nya terhadap centroid:
   $$\text{sim}_k = \text{Cosine}(V_k, \bar{C})$$
   Jika $N > 3$, sistem **membuang 1 sampel dengan nilai $\text{sim}_k$ terendah** (outlier frame yang bergoyang atau memalingkan wajah).
3. **Validasi Konsistensi (`ENROLL_MIN_CONSISTENCY: 0.90`)**: Hitung konsistensi sebagai cosine terendah di antara sampel yang tersisa:
   $$\text{Consistency} = \min_{k \in \text{Kept}} \text{sim}_k$$
   Jika $\text{Consistency} < 0.90$, seluruh batch dibatalkan dan sistem meminta pengambilan ulang.
4. **Pass 2 (Vektor Master L2-Normalized)**: Rata-ratakan sampel terpilih yang telah di-normalisasi L2, lalu lakukan normalisasi L2 akhir:
   $$V_{\text{master}} = \text{L2Norm}\left(\frac{1}{|K|} \sum_{k \in \text{Kept}} \text{L2Norm}(V_k)\right)$$

#### 4. Anti-Duplikasi Registrasi (O(N) Local Scan)
Pada tahap pendaftaran karyawan, algoritma melakukan *looping* O(N) singkat terhadap basis data master lokal (`local_master_descriptors`). Jika wajah baru memiliki tingkat kecocokan *raw cosine* **`>= 0.88`** terhadap karyawan manapun, registrasi **ditolak** untuk mencegah karyawan ganda.

#### 5. Liveness Detection (EAR)
Pendeteksian interaksi *(Liveness)* dijalankan dengan membedah 468 titik Face Mesh. Sistem menghitung *Eye Aspect Ratio (EAR)* dari jarak Euclidean vertikal dan horizontal pada kelopak mata untuk mendeteksi kedipan nyata demi menggagalkan penyalahgunaan foto 2D:
$$\text{EAR} = \frac{\|p_2 - p_6\| + \|p_3 - p_5\|}{2 \|p_1 - p_4\|}$$

#### 6. Kestabilan Koordinat & Normalisasi
- **Peredam Getaran One-Euro Filter**: Filter matematika adaptif mengurangi *jitter* tanpa *lag*, sehingga grafik *mesh* tampak halus.
- **Normalisasi Bebas Perangkat**: Hook `useNormalizedFaceMesh` membingkai koordinat wajah agar tidak terdistorsi layar, dengan *Center Crop 4:3* statis sebelum proses deteksi AI.

### 3.3 Engine Sinkronisasi (Auto-Sync) & Cut-Off 22:00
- **Cut-Off 22:00 Strict Mode**: Aplikasi menyimpan absen secara persisten secara lokal saat offline, dan mengalokasikan siklus *upload* massal (Sinkronisasi Otomatis) secara berkala (utamanya di ujung jam kerja pukul 22:00) atau ketika tombol **Sinkronisasi Manual** diklik. Data absensi setelah pukul 22:00 tidak dipaksakan keluar saat proses logout harian.
- **Auto-Recovery untuk Orphaned Logs**: Jika ada log absensi luring yang dibuat sebelum data karyawan barunya disinkronkan ke cloud (`employee_id` masih string sementara `off_emp_xxx`), `syncEngine.js` secara otomatis mencocokkan NIK log dengan data karyawan di Supabase dan mengupdate foreign key `employee_id` ke BigInt server secara transparan tanpa kehilangan log.
- **Stabilisasi & Pemisahan Transaksi Pendaftaran Karyawan Offline**:
  1. *Normalisasi Tipe ID*: Sistem menormalisasi format ID offline (`off_emp_xxx` maupun string angka `"123"`) menjadi tipe data yang kompatibel dengan skema Supabase Cloud sebelum pengiriman payload.
  2. *Pemisahan Eksekusi Inti dan Sekunder*: Operasi sinkronisasi memisahkan langkah inti (insert ke `employees` dan upsert ke `master_descriptors`) dari langkah sekunder (upload foto master ke storage `attendance-photos/faces/` dan pengiriman telemetri scan attempts). Kegagalan pada langkah sekunder tidak membatalkan registrasi inti karyawan.
  3. *Audit & Telemetri Kegagalan Detail*: Antrean mencatat `last_error_code`, `last_error_message`, `last_error_step`, `attempt_count`, dan `last_attempt_at`. Error Supabase asli ditampilkan langsung pada antarmuka Halaman Sinkronisasi (`/sinkronisasi`) untuk mempermudah diagnosa mandor.
- **Delta Sync**: Penarikan data (karyawan & log) hanya memuat perubahan baris baru (*Delta*) via `.gt('created_at', lastSyncTime)`, menghemat bandwidth dan memori secara masif.

### 3.4 Arsitektur Autentikasi 3-Tier & Failsafe Blank-Spot
Untuk memastikan mandor di perkebunan terpencil dapat selalu membuka aplikasi dan melakukan absensi:
1. **Tier 1 (Cloud RPC)**: Memanggil RPC Supabase `verify_admin_login(p_username, p_password)` yang memvalidasi hash pgcrypto di tabel `admin_auth`.
2. **Tier 2 (Local Cached Credential)**: Ketika online, hasil verifikasi di-cache ke tabel SQLite `local_admins` (atau Dexie `local_admins`) menggunakan Web Crypto API SHA-256 hash. Saat luring, login diverifikasi lokal terhadap hash ini.
3. **Tier 3 (Hardcoded Failsafe Offline Directory)**: Jika aplikasi baru di-install atau cache terhapus di tengah area *blank-spot*, sistem menyediakan fallback 33 akun terdaftar (Head Office, 4 Regional Admin, dan 28 Estate Admin unit kebun dari `DAFTAR_AKUN_AGRIFACE.csv`).
4. **Logout Guard (Pencegahan Kehilangan Data)**: Pada perangkat tablet bersama (*Shared Device*), mekanisme logout secara otomatis menghitung `getUnsyncedDataSummary()`. Jika masih ada log absensi, karyawan baru, atau pengajuan pengeditan yang belum tersinkronkan, tombol Logout akan **memblokir aksi keluar** dan mewajibkan sinkronisasi terlebih dahulu untuk melindungi data admin kebun lain.

### 3.5 Arsitektur Full-Bleed Mobile Data Table & Adaptive Bottom Sheet Filters
Untuk memaksimalkan produktivitas operasional di lapangan melalui smartphone dengan layar terbatas:
1. **Pemisahan Layout Responsif Tanpa Regresi Desktop**: Sistem memisahkan rendering halaman data antara Desktop Layout dan Mobile Layout secara bersih melalui conditional branch `isMobile ? <MobileDataPage> : <DesktopLayout>`.
2. **Ergonomic Header Isolation**: Seluruh kontrol filter sekunder yang kompleks (Kebun, Afdeling, Status TK, Rentang Tanggal) dipindahkan dari header layar ke dalam drawer **Bottom Sheet** (`MobileFilterSheet`) yang dibuka via tombol Filter dengan badge counter filter aktif.
3. **Independent Viewport Scrolling**: Tabel dibungkus di dalam `MobileTableViewport` dengan properti `overscroll-behavior: contain` dan momentum scrolling iOS, memastikan gestur horizontal pada tabel tidak memicu scroll horizontal pada kontainer aplikasi utama (`body`).
4. **Column Freezing Strategis**: Kolom identitas utama (seperti *Nama Karyawan*) dikunci (*sticky*) pada sisi kiri tabel mobile, sementara kolom pelengkap diizinkan scroll horizontal untuk menghemat ruang pandang.

### 3.6 Dual-Camera Rolling & Biometric Canvas Aspect Ratio Synchronization
Menjawab tantangan operasional absensi di lapangan perkebunan:
1. **Dynamic Camera Switching**: Komponen `FaceScanSurface` dan `FullscreenScanShell` menyediakan tombol toggle kamera instan (`user` vs `environment`) dengan touch target $\ge 44\times 44\text{px}$. Efek mirror CSS (`transform: scaleX(-1)`) hanya diterapkan saat kamera depan aktif.
2. **Default Facing Mode Lapangan**: Mode kamera *default* untuk perangkat bergerak diarahkan ke kamera belakang (`environment`), memudahkan mandor/admin mengarahkan lensa ke wajah pekerja panen tanpa memutar gawai.
3. **Anti-Distortion Canvas Resizing**: Mengatasi masalah "mesh gepeng" akibat kontainer flex 100% height, sistem menggunakan kalkulasi penyesuaian berbasis `ResizeObserver`:
   $$\text{scale} = \min\left(\frac{\text{stageW}}{\text{targetW}}, \frac{\text{stageH}}{\text{targetH}}\right)$$
   $$\text{boxW} = \text{round}(\text{targetW} \times \text{scale}), \quad \text{boxH} = \text{round}(\text{targetH} \times \text{scale})$$
   Kalkulasi ini menjamin deviasi aspek rasio canvas internal terhadap display box $< 0.1\%$, sehingga titik mesh wajah 468 node tidak terdistorsi secara aksial.

### 3.7 Capgo Over-The-Air (OTA) Live Update & POSIX Deployment Engine
- **In-App Dynamic Update**: Aplikasi memeriksa ketersediaan versi baru di bucket Supabase `ota-updates` via `version.json` saat peluncuran atau saat kembali aktif dari background.
- **POSIX-Compliant ZIP Packaging**: Pemaketan bundle rilis menggunakan script kustom `create_posix_zip.js` yang memaksa seluruh path internal zip menggunakan forward-slash (`/`). Hal ini krusial untuk mencegah kegagalan ekstraksi path Windows pada sistem Android WebView.

### 3.8 Arsitektur Penyimpanan & Resolusi Foto Master Wajah 3-Tier
Untuk memungkinkan pengawasan visual kualitas wajah karyawan tanpa membebani memori perangkat lapangan:
1. **Tier 1 (Cache Thumbnail Lokal)**: Saat pendaftaran di HP kebun, foto master dikompresi ke thumbnail Base64 JPEG dan disimpan ke tabel SQLite `local_employee_face_thumbs` (atau Dexie `employee_face_thumbs`). Resolusi ini langsung terbuka dalam $< 5\text{ ms}$ secara luring.
2. **Tier 2 (Cloud Public Storage & Metadata)**: Saat auto-sync aktif, file biner diunggah ke Supabase Storage bucket `attendance-photos` pada path `faces/${empId}/master.jpg`, dan metadata dicatat ke tabel `employee_face_photos`. Ketika dibuka di perangkat lain (misal PC Head Office), foto dimuat via Public URL dan otomatis disimpan ke cache lokal perangkat tersebut.
3. **Tier 3 (Fallback Foto Absensi Lapangan)**: Jika master foto belum pernah tersinkronkan ke server, sistem cerdas melakukan fallback ke foto bukti absensi terakhir dari tabel `attendance_logs` (`photo_url`).
4. **Indikator Badge Sumber Foto**: Antarmuka modal secara tegas membedakan sumber: badge hijau untuk *"Foto Master Pendaftaran Resmi"* dan badge amber untuk *"Foto Absensi Lapangan Terakhir (Foto Master Belum Disinkronkan)"*.

### 3.9 Diagnostik Integritas Vektor & Sidik Vektor Deterministik (Vector Checksum)
Untuk memvalidasi kesehatan vektor biometrik 1024-D yang tidak dapat dibaca kasat mata:
1. **Formula Sidik Vektor (Checksum)**: Fungsi deterministik menggabungkan panjang vektor, norma L2, FNV-1a hash 8-digit hex, dan 4 elemen desimal pertama:
   $$\text{Checksum} = \text{Length} \parallel \text{L2Norm} \parallel \text{FNV1aHex} \parallel \text{Sample4}$$
2. **Audit Komparasi Cloud vs Lokal**: Membandingkan checksum seluruh karyawan antara memori SQLite lokal dengan tabel `master_descriptors` di Supabase. Status audit terbagi menjadi: `MATCH` (Selaras), `LOCAL_ONLY` (Hanya Lokal), `REMOTE_ONLY` (Hanya Cloud), `MISMATCH` (Beda Template), dan `UNCOMPUTED` (Belum Dihitung).
3. **Mekanisme Pemulihan Sekali Klik (Self-Healing)**: Tombol *"Tarik Cloud"* dan *"Hitung & Sync"* memungkinkan mandor memperbarui template biometrik yang usang tanpa perlu pemindaian ulang di lapangan.
4. **Scoping Autorisasi & Penanganan Luring**: Query cloud menyaring karyawan berdasarkan unit kebun (`employees.nama_kebun`) untuk peran `estate_admin`, dan membuka seluruh kebun dengan kontrol dropdown A-Z untuk `admin.hq`. Tombol audit otomatis dinonaktifkan saat offline guna mencegah unhandled network error.

---

## 4. Peran Pengguna & Proses Bisnis

### 4.1 Tabel Peran Pengguna & Hak Akses

| Peran Pengguna | Identitas Role | Akses & Otoritas |
| :--- | :--- | :--- |
| **Akun Kebun (Estate Admin)** | `estate_admin` | Akses operasional penuh untuk satu unit kebun. Membuka pemindai absensi biometrik (`/absensi`), mendaftarkan wajah karyawan baru (`/karyawan`), menginput hasil panen (`/hasil-panen`), dan mengajukan koreksi log absensi (`/pengajuan`). Mendukung operasional luring (*offline-first*). |
| **Akun Region (Regional Admin)**| `regional_admin` | Akses pengawasan (*read-only*) untuk mengawasi seluruh unit kebun dalam satu region teritorial (Sumut 2, Riau 1, Kalbar 1A, Kalbar 1B). Memantau tren absensi dan kepatuhan karyawan. |
| **Akun Head Office (HQ Admin)** | `headoffice_admin` | Akses pengawasan skala nasional (*read-only* operasional harian), dengan wewenang eksklusif menyetujui (**Approve**) atau menolak (**Reject**) pengajuan edit/hapus log absensi dari level kebun di modul `/persetujuan/inbox`, serta hak eksekusi *force delete* log. |

### 4.2 Alur Proses Bisnis Absensi di Lapangan
1. **Login Mandor**: Mandor/Admin Kebun melakukan login di awal jam kerja menggunakan kredensial kebunnya (baik online maupun via cached/failsafe offline credentials).
2. **Buka Scanner Absensi**: Mandor masuk ke menu **Absensi > Scanner Absensi** (`/absensi`). Kamera belakang aktif secara otomatis.
3. **Pencarian / Pemilihan Pekerja**: Mandor memilih nama/NIK pekerja panen yang akan diabsen (pencarian alfabetis A-Z).
4. **Verifikasi Biometrik Wajah 1-to-1**:
   - Pekerja berdiri di depan kamera.
   - Detektor mengekstrak vektor wajah dan membandingkannya dengan master biometrik pekerja terpilih ($O(1)$).
   - Pekerja mengedipkan mata untuk lolos uji keaslian (*Liveness EAR*).
   - Jika kecocokan mencapai $\ge 0.92$ (skor $\ge 82.0\%$), sistem mengunci sesi dan membunyikan konfirmasi suara/haptic.
5. **Pencatatan Log**: Absensi Check-In tersimpan seketika (ke backend Express/Supabase jika online, atau antrean lokal SQLite/Dexie jika offline).
6. **Check-Out & Hasil Panen**: Di akhir hari, pekerja melakukan Check-Out dengan alur serupa, dan admin melengkapi catatan jumlah hasil panen (Kg) atau luas ancak (Ha).
7. **Sinkronisasi Akhir Hari**: Sebelum jam 22:00, Mandor memastikan *Unsynced Badge* di Topbar bernilai `0`.

> [!IMPORTANT]
> **Klarifikasi Penting**: Karyawan panen lapangan **TIDAK PERNAH DIWAJIBKAN LOGIN** ke aplikasi. Aplikasi sepenuhnya dioperasikan oleh Mandor/Admin Kebun yang memiliki akun resmi. Frasa "tanpa mewajibkan karyawan login" menegaskan sifat operasional lapangan di mana 1 perangkat mandor memindai puluhan karyawan secara beruntun.

---

## 5. Persyaratan Fungsional Detail (Functional Requirements)

### FR-1: Scanner Absensi Terproteksi Mandor (`/absensi`)
- Halaman Scanner Absensi (`/absensi`) **bukan public route**. Halaman ini selalu berada di dalam `DashboardLayout` dan diproteksi oleh `RoleGuard allowedRoles={['estate_admin']}`.
- Antarmuka Kamera dirancang bersih tanpa teks debug, mempertahankan kontur Face Mesh 468 titik yang elegan, tombol toggle kamera depan/belakang, dan selector karyawan yang terurut alfabetis (A-Z).

### FR-2: Deduplikasi Verifikasi (Auto-Submit Sekali Saja)
- Saat pemindaian biometrik valid (skor visual $\ge 82\%$, raw cosine $\ge 0.92$) dan tes *Liveness* (kedip mata) berhasil, sistem langsung mengunci state (*lock state*).
- Absensi dikirim tepat **satu kali**, dan kalkulasi frame berikutnya dihentikan untuk mencegah rekam log ganda (*Double Attendance Logs*).

### FR-3: Anti-Duplikasi Registrasi Master (Anti-Spoofing & Duplicate Guard)
- Pendaftaran wajah Master (baik Live Kamera maupun Upload Gambar) divalidasi ke seluruh arsip lokal SQLite/Dexie sebelum disimpan.
- Jika vektor baru memiliki kemiripan raw cosine $\ge 0.88$ terhadap karyawan yang sudah ada, registrasi otomatis **ditolak**: *"Registrasi Gagal: Wajah sudah terdaftar atas nama [Nama Karyawan]"*.

### FR-4: Manajemen Data Karyawan (Soft Delete & Fallback ID)
- Data karyawan menerapkan **Soft Delete** (`deleted_at`), memastikan riwayat absensi lampau tetap sah dan terlacak.
- Karyawan baru yang didaftarkan secara luring (Offline) diberi ID string sementara (contoh: `off_emp_1712345678`). Mesin `syncEngine.js` akan mendamaikan (*auto-recovery*) ID ini menjadi BigInt server setelah terhubung ke Supabase.

### FR-5: Live Dynamic Unsynced Badge Counter
- Topbar aplikasi memuat indikator lencana (*badge*) merah yang menghitung jumlah total absensi, karyawan baru, dan pengajuan offline yang belum tersinkronisasi.
- Badge diperbarui otomatis setiap 2.5 detik dengan membaca basis data lokal perangkat.

### FR-6: Full-Bleed Mobile Data Tables & Bottom Sheet Filters
- Seluruh 4 modul data tabular (Log Absensi, Monitoring Absensi, Daftar Karyawan, Mutasi Karyawan) wajib menyediakan tampilan layar penuh (*full-bleed*) pada perangkat $\le 768\text{px}$.
- Kontrol filter sekunder dikelompokkan ke dalam modal Bottom Sheet (`MobileFilterSheet`) dengan touch target $\ge 44\text{px}$ dan badge counter filter aktif.
- Area tabel mobile wajib mendominasi $\ge 65\%$ tinggi layar dengan kapasitas minimal 12 baris tampak tanpa scroll horizontal pada `body`.

### FR-7: Dual-Camera Switching & Native Aspect-Ratio Scaling
- Seluruh antarmuka pemindai wajah (Absensi, Registrasi, Rescan) wajib mendukung pergantian stream kamera depan dan belakang dengan transisi mulus tanpa memory leak.
- Kanvas overlay biometrik wajib mempertahankan aspek rasio sensor video menggunakan kalkulasi `ResizeObserver` dinamis untuk mengeliminasi distorsi "mesh gepeng".

### FR-8: Penataan Akses Toolbar & Pemisahan Registrasi Karyawan
- Halaman Daftar Karyawan (`/daftar-karyawan`) berfokus penuh pada penyajian dan penelusuran data master karyawan. Tombol "Tambah Karyawan" ditiadakan dari toolbar tabel guna menjaga kelapangan baris judul dan mencegah tumpang tindih UI pada perangkat mobile.
- Hak akses penambahan/registrasi biometrik karyawan baru terisolasi secara aman pada rute mandiri `/karyawan` ("Registrasi Wajah") khusus untuk peran `estate_admin`.

### FR-9: Biometric Master Template Outlier Filtering & Consistency Gate
- Pendaftaran master biometrik wajib menggunakan algoritma 2-pass `buildTemplateDetailed`.
- Sistem mengambil 7 frame sampel, menghitung centroid, membuang 1 sampel dengan deviasi cosine terbesar, dan memverifikasi batas ambang konsistensi minimal $\ge 0.90$.

### FR-10: Piecewise Linear Score Display Calibration
- Sistem wajib menggunakan fungsi kalibrasi `displayScore` Piecewise Linear untuk memetakan cosine raw $0.92$ tepat ke nilai tampilan **`82.0%`**.
- Visual indikator batas kelulusan verifikasi menggunakan ambang $82\%$.

### FR-11: Multi-Tier Authentication & Shared Device Logout Guard
- Sistem login wajib mendukung 3 tier: Supabase Cloud RPC, Local SHA-256 Hash Cache, dan Fallback 33 Akun Kebun.
- Aksi logout pada perangkat bersama wajib diblokir jika terdapat antrean data offline yang belum tersinkronisasi (`unsynced > 0`).

### FR-12: Over-The-Air (OTA) Live Update & POSIX Deployment Engine
- Sistem native Android wajib mengintegrasikan `@capgo/capacitor-updater` untuk pembaruan OTA instan tanpa perlu re-install APK.
- Paket ZIP OTA wajib dikompilasi menggunakan format POSIX forward-slash.

### FR-13: Resolusi & Pratinjau Foto Master Wajah 3-Tier (`/daftar-karyawan`)
- Halaman Daftar Karyawan menyediakan tombol preview Mata (`<Eye>`) untuk memanggil modal `EmployeeFacePreviewModal`.
- Modal wajib menyelesaikan foto menggunakan arsitektur 3-tier: (1) Cache thumbnail lokal di SQLite/Dexie, (2) Public URL & Thumbnail Base64 dari Supabase Storage `attendance-photos/faces/${empId}/master.jpg` dan tabel `employee_face_photos`, (3) Fallback foto absensi terakhir (`attendance_logs.photo_url`).
- Foto yang berhasil diunduh dari cloud wajib disimpan ke basis data lokal perangkat agar selanjutnya dapat dibuka instan dan luring.
- Antarmuka modal wajib menampilkan badge status sumber foto yang jelas: *"Foto Master Pendaftaran Resmi (Cloud / Tersimpan di Perangkat)"* (hijau) atau *"Foto Absensi Lapangan Terakhir (Foto Master Belum Disinkronkan)"* (amber).
- Akun `admin.hq` (Head Office Admin) memiliki visibilitas global untuk melihat foto pemanen dari seluruh unit kebun.

### FR-14: Scoping Autorisasi Unit Kebun & Filter Head Office Halaman Sinkronisasi (`/sinkronisasi`)
- Halaman Sinkronisasi (`/sinkronisasi`) wajib menerapkan pembatasan data berbasis peran (*role-based authorization*):
  - **Estate Admin (`estate_admin`)**: Antrean absensi, antrean pendaftaran, dan diagnostik integritas vektor secara ketat disaring hanya untuk pemanen dari unit kebunnnya (`user.kebun`).
  - **Regional Admin (`regional_admin`)**: Disaring untuk pemanen dalam teritorialnya (`user.region`).
  - **Head Office Admin (`admin.hq`)**: Diberikan akses global untuk memantau seluruh kebun, dilengkapi dropdown pemilih unit kebun (terurut A-Z, dibungkus `div.custom-select-wrapper` dengan ikon `ChevronDown`) dan kolom unit kebun pada tabel diagnostik.

### FR-15: Proteksi Mode Luring pada Diagnostik Integritas Vektor
- Tab Diagnostik Integritas Vektor wajib mendeteksi status konektivitas jaringan secara reaktif.
- Saat luring (offline), tombol *"Periksa Integritas"* dinonaktifkan (`disabled`) dan sistem menampilkan banner peringatan informatif bahwa audit komparasi cloud memerlukan koneksi internet, mencegah unhandled network exception dan menjaga kestabilan aplikasi saat mandor berada di area perkebunan tanpa sinyal.

---

## 6. Ringkasan REST API & RPC Backend Specification

| Method / Tipe | Endpoint / RPC Name | Deskripsi & Fungsi |
| :--- | :--- | :--- |
| `RPC` | `verify_admin_login` | Validasi kredensial login admin/mandor terhadap tabel `admin_auth` (pgcrypto). |
| `RPC` | `sync_offline_employee` | Menjalankan resolusi sinkronisasi karyawan luring secara aman dari bentrok *(Server-Wins)*. |
| `POST` | `/api/auth/login` | Endpoint Express login fallback (menghasilkan JWT token 24 jam & HTTP-Only cookie). |
| `GET` | `/api/auth/me` | Verifikasi status sesi admin aktif saat refresh halaman (F5). |
| `GET` | `/api/employees` | Mengambil daftar seluruh karyawan aktif beserta status kepemilikan biometrik master. |
| `POST` | `/api/employees` | Menambahkan karyawan baru ke database Supabase (menghasilkan BigInt ID). |
| `PUT` | `/api/employees/:id` | Memperbarui data detail dan/atau vektor biometrik karyawan tertentu. |
| `DELETE`| `/api/employees/:id` | Menghapus data karyawan beserta data relasi biometriknya di Supabase. |
| `POST` | `/api/biometrics/register` | Mendaftarkan atau memperbarui vektor master biometrik karyawan. |
| `GET` | `/api/biometrics/master/:id`| Menarik data 1024-dimensi master wajah karyawan untuk verifikasi 1-to-1. |
| `POST` | `/api/attendance/verify` | Engine verifikasi 1-to-1 utama (Check-In/Out) berikut validasi Geofencing GPS. |
| `GET` | `/api/attendance/logs` | Penarikan historis log absensi terbaru (dengan kalkulasi durasi kerja). |
| `GET` | `/api/attendance/status/:id` | Pengecekan status absensi karyawan hari ini (*Timezone-Safe*: hasCheckedIn, hasCheckedOut). |
| `DELETE`| `/api/attendance/logs/:id` | Menghapus satu entri log absensi spesifik. |
| `DELETE`| `/api/attendance/logs/clear` | Mengosongkan seluruh log absensi dari database Supabase (HQ Admin). |
| `POST` | `/api/attendance/logs/delete`| Menghapus sejumlah ID log absensi secara massal (HQ Force Delete bypass RLS). |
| `POST` | `/api/attendance/sync` | Batch Auto-Sync untuk menerima antrean log absensi offline dari IndexedDB/SQLite. |

---

## 7. Skema Database (Database Schemas)

### 7.1 Skema Relasional Supabase (Cloud PostgreSQL)

```sql
-- Tabel Karyawan
CREATE TABLE employees (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nik VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    kebun VARCHAR(100),
    afdeling VARCHAR(100),
    nama_kebun VARCHAR(100),
    status_tk VARCHAR(50),
    jabatan VARCHAR(100),
    status_perkawinan VARCHAR(50),
    has_master_biometric BOOLEAN DEFAULT false,
    region VARCHAR(100),
    descriptor_json JSONB,
    geometric_descriptor_json JSONB,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    deleted_at TIMESTAMPTZ -- Soft Delete Flag
);

-- Tabel Biometrik Master (Vector Storage)
CREATE TABLE master_descriptors (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT UNIQUE NOT NULL REFERENCES employees(id) ON DELETE CASCADE,
    descriptor_json JSONB NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- Tabel Log Absensi
CREATE TABLE attendance_logs (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    nik VARCHAR(50),
    name VARCHAR(100),
    department VARCHAR(100),
    location TEXT,
    status VARCHAR(100) NOT NULL,
    attendance_type VARCHAR(20) DEFAULT 'CHECK-IN',
    euclidean_distance DOUBLE PRECISION NOT NULL,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    durasi VARCHAR(50),
    timestamp TIMESTAMPTZ DEFAULT NOW(),
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- Tabel Pengajuan Edit/Hapus Log (Approval Queue)
CREATE TABLE attendance_requests (
    id VARCHAR(100) PRIMARY KEY,
    request_type VARCHAR(20) NOT NULL, -- 'EDIT' atau 'DELETE'
    log_id VARCHAR(100) NOT NULL,
    nik VARCHAR(50),
    name VARCHAR(100),
    nama_kebun VARCHAR(100),
    requested_by VARCHAR(100),
    requested_at TIMESTAMPTZ DEFAULT NOW(),
    status VARCHAR(20) DEFAULT 'PENDING', -- 'PENDING', 'APPROVED', 'REJECTED'
    old_value JSONB,
    new_value JSONB
);

-- Tabel Autentikasi Admin
CREATE TABLE admin_auth (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- 'estate_admin', 'regional_admin', 'headoffice_admin'
    region VARCHAR(100),
    kebun VARCHAR(100),
    name VARCHAR(100),
    nik VARCHAR(50)
);

-- Tabel Foto Master Wajah Karyawan
CREATE TABLE IF NOT EXISTS public.employee_face_photos (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL REFERENCES public.employees(id) ON DELETE CASCADE,
    storage_path TEXT NOT NULL,
    thumbnail_base64 TEXT,
    captured_at TIMESTAMPTZ DEFAULT now(),
    is_primary BOOLEAN DEFAULT true,
    photo_version INTEGER DEFAULT 1,
    photo_checksum TEXT,
    updated_at TIMESTAMPTZ DEFAULT now(),
    created_at TIMESTAMPTZ DEFAULT now(),
    CONSTRAINT uq_emp_face_photos UNIQUE (employee_id)
);
```

### 7.2 Skema SQLite Native (Android APK Shell - 10 Tabel)

Dikelola di `src/services/sqliteService.ts` dengan Primary Key bertipe `TEXT` untuk menampung format ID offline `off_emp_xxx`:

```sql
-- 1. Cache Master Karyawan
CREATE TABLE IF NOT EXISTS local_employees (
  id TEXT PRIMARY KEY,
  nik TEXT NOT NULL,
  name TEXT NOT NULL,
  department TEXT,
  afdeling TEXT,
  nama_kebun TEXT,
  status_tk TEXT,
  jabatan TEXT,
  status_perkawinan TEXT,
  has_master_biometric INTEGER DEFAULT 0,
  region TEXT,
  is_synced INTEGER DEFAULT 1,
  syncStatus TEXT DEFAULT NULL
);

-- 2. Cache Vektor Biometrik Master 1024-D
CREATE TABLE IF NOT EXISTS local_master_descriptors (
  employee_id TEXT PRIMARY KEY,
  descriptor_json TEXT,
  geometric_descriptor_json TEXT,
  updated_at TEXT
);

-- 3. Antrean Sinkronisasi Absensi Luring
CREATE TABLE IF NOT EXISTS local_attendance_queue (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  employee_id TEXT NOT NULL,
  nik TEXT,
  name TEXT,
  department TEXT,
  timestamp TEXT,
  location TEXT,
  lat REAL,
  lng REAL,
  status TEXT,
  attendance_type TEXT,
  euclidean_distance REAL,
  path_foto_lokal TEXT,
  path_foto_storage TEXT,
  status_sync_teks TEXT DEFAULT 'pending',
  status_sync_foto TEXT DEFAULT 'pending',
  is_synced INTEGER DEFAULT 0,
  created_at TEXT,
  kebun TEXT,
  durasi INTEGER
);

-- 4. Cache Status Absensi Hari Ini
CREATE TABLE IF NOT EXISTS local_today_attendance_cache (
  employee_id TEXT PRIMARY KEY,
  has_checked_in INTEGER DEFAULT 0,
  has_checked_out INTEGER DEFAULT 0,
  checked_in INTEGER DEFAULT 0,
  check_in_time TEXT,
  check_out_time TEXT,
  cached_date TEXT
);

-- 5. Cache Riwayat Log Absensi Lokal
CREATE TABLE IF NOT EXISTS local_attendance_logs (
  id TEXT PRIMARY KEY,
  employee_id TEXT NOT NULL,
  nik TEXT,
  name TEXT,
  department TEXT,
  afdeling TEXT,
  timestamp TEXT,
  location TEXT,
  lat REAL,
  lng REAL,
  status TEXT,
  attendance_type TEXT,
  euclidean_distance REAL,
  path_foto_lokal TEXT,
  path_foto_storage TEXT,
  status_sync_teks TEXT DEFAULT 'pending',
  status_sync_foto TEXT DEFAULT 'pending',
  is_synced INTEGER DEFAULT 0,
  created_at TEXT,
  kebun TEXT,
  durasi INTEGER,
  syncStatus TEXT DEFAULT NULL
);

-- 6. Cache Kredensial Login Admin Lokal
CREATE TABLE IF NOT EXISTS local_admins (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT UNIQUE NOT NULL,
  password_hash TEXT NOT NULL,
  role TEXT,
  region TEXT,
  kebun TEXT,
  name TEXT,
  nik TEXT,
  last_login TEXT
);

-- 7. Antrean Karyawan Baru yang Dibuat Saat Offline
CREATE TABLE IF NOT EXISTS local_employee_sync_queue (
  id TEXT PRIMARY KEY,
  nik TEXT,
  name TEXT,
  department TEXT,
  afdeling TEXT,
  nama_kebun TEXT,
  status_tk TEXT,
  jabatan TEXT,
  status_perkawinan TEXT,
  has_master_biometric INTEGER DEFAULT 0,
  region TEXT,
  descriptor_json TEXT,
  geometric_descriptor_json TEXT,
  is_synced INTEGER DEFAULT 0,
  created_at TEXT
);

-- 8. Antrean Pengajuan Edit/Hapus Log Offline
CREATE TABLE IF NOT EXISTS local_attendance_requests (
  id TEXT PRIMARY KEY,
  request_type TEXT,
  log_id TEXT,
  nik TEXT,
  name TEXT,
  nama_kebun TEXT,
  requested_by TEXT,
  requested_at TEXT,
  status TEXT,
  old_value TEXT,
  new_value TEXT,
  is_synced INTEGER DEFAULT 0
);

-- 9. Antrean Penghapusan Karyawan Offline
CREATE TABLE IF NOT EXISTS local_employee_delete_queue (
  id TEXT PRIMARY KEY
);

-- 10. Cache Thumbnail Foto Wajah Master Karyawan
CREATE TABLE IF NOT EXISTS local_employee_face_thumbs (
  employee_id TEXT PRIMARY KEY,
  thumb_base64 TEXT NOT NULL,
  captured_at TEXT,
  photo_version INTEGER DEFAULT 1,
  photo_checksum TEXT,
  updated_at TEXT,
  is_synced INTEGER DEFAULT 0
);
```

### 7.3 Skema IndexedDB (Dexie.js v9 Web Fallback - 9 Object Stores)

Dikelola di `src/db.js` untuk fallback operasional pada browser web desktop/mobile:

```javascript
dexieDb.version(9).stores({
  user_master: '++id, employee_id, nik, name, department, updated_at',
  attendance_sync_queue: '++id, employee_id, nik, name, timestamp, status, attendance_type, is_synced, created_at',
  employees_cache: 'id, nik, name, department, has_master_biometric, is_synced',
  today_attendance_cache: 'employee_id, hasCheckedIn, hasCheckedOut, checked_in, check_in_time, check_out_time, cached_date',
  attendance_logs: 'id, employee_id, timestamp, attendance_type, is_synced',
  local_admins: 'username, password_hash, role, region, kebun, name, nik, last_login',
  attendance_requests: 'id, request_type, log_id, status, is_synced',
  employee_delete_queue: 'id',
  employee_face_thumbs: 'employee_id, photo_version, photo_checksum, is_synced'
});
```

---

## 8. Persyaratan Non-Fungsional (Non-Functional Requirements)

1. **Performa Edge (Kecepatan Pemindaian)**: Pencocokan Cosine Similarity diselesaikan di bawah $<15\text{ ms}$. Respons kamera tetap berada pada ambang stabil $>15\text{ FPS}$ di HP menengah-bawah.
2. **Offline-First Resilience**: Aplikasi 100% fungsional saat sinyal seluler mati total (*Blank Spot*). Asset statis Web Bundle dikontrol lewat `sw.js`, dan transaksi luring dijadwalkan secara aman ke *Write-Ahead Log* (WAL) SQLite lokal Android.
3. **Keamanan & Efisiensi Citra Biometrik**: Citra wajah untuk operasional pencocokan absensi diurai seketika menjadi larik Float32 1024-D di memori klien tanpa menyimpan frame mentah pada alur verifikasi. Untuk keperluan audit visual pengawasan oleh Head Office, foto pendaftaran master dikompresi menjadi thumbnail Base64 ultra-ringan ($< 25\text{ KB}$) pada basis data lokal dan resolusi penuh disimpan secara aman di Cloud Storage Supabase (`attendance-photos/faces/`).
4. **Ergonomi & Utilisasi Layar Mobile**: Ketinggian total header pada tampilan tabel mobile dibatasi maksimal $\le 200\text{px}$ dari tepi atas layar. Area pandang tabel wajib mendominasi $\ge 65\%$ tinggi layar dengan kapasitas minimal 12 baris tampak. Ukuran font sel tabel dijaga minimal $12\text{px}$ ($0.75\text{rem}$) dan header kolom minimal $11\text{px}$ ($0.68\text{rem}$) untuk keterbacaan optimal di bawah terik matahari kebun. Zero-scroll horizontal pada dokumen root (`body`).
5. **Standar Tipografi & Visual UI Enterprise**: Menggunakan font sans-serif modern (Inter / Geist). Skema warna UI elegan mengutamakan palet netral monokromatik (putih, abu-abu muda, dark gray, slate). Warna biru dilarang pada elemen layout/sidebar. Warna semantik (hijau, merah, oranye) digunakan murni untuk indikator status (Hadir, Gagal, Pending).
6. **AI Model Resilience & Cache Guard**: Penggunaan `@vladmandic/human` wajib mengatur `cacheModels: false` untuk mencegah korupsi IndexedDB model caching pada WebView Android. File `public/human.esm.js` wajib memiliki null-guard pada GraphModel executor.
7. **Ketahanan Jaringan & Degradasi Anggun Diagnostik (Graceful Degradation)**: Modul diagnostik integritas vektor dan peninjauan foto master menerapkan degradasi anggun saat konektivitas terputus. Sistem mendeteksi `navigator.onLine` dan status koneksi Supabase secara reaktif, menonaktifkan trigger audit komparasi cloud saat luring, serta menyajikan data dari cache SQLite/Dexie lokal dengan latensi $< 5\text{ ms}$ tanpa memicu unhandled network exception.

---

## 9. Peta Navigasi & Struktur Rute Sistem

Didefinisikan secara deklaratif di `src/router.jsx` dan `src/config/menu.js`:

```
/
├── /login (PublicRoute)
├── /analytics (Public Enterprise Analytics View)
├── /order-form (Public Offline Order Form)
└── /dashboard (ProtectedRoute -> DashboardLayout)
    ├── /dashboard (Executive Dashboard - All Roles)
    ├── /logs (Log Absensi - All Roles)
    ├── /logs/monitoring (Monitoring Absensi - All Roles)
    ├── /daftar-karyawan (Daftar Karyawan - All Roles)
    ├── /daftar-karyawan/mutasi (Mutasi Karyawan - All Roles)
    │
    ├── [RoleGuard: estate_admin]
    │   ├── /absensi (Scanner Biometrik Wajah)
    │   ├── /karyawan (Registrasi Wajah & Karyawan Baru)
    │   ├── /sinkronisasi (Monitoring & Trigger Antrean Offline)
    │   ├── /hasil-panen (Input & Rekapitulasi Hasil Panen)
    │   └── /pengajuan (Pengajuan Koreksi Log Absensi)
    │
    └── [RoleGuard: headoffice_admin]
        ├── /persetujuan/inbox (Inbox Persetujuan Pengajuan Edit/Hapus)
        └── /persetujuan/riwayat (Riwayat Audit Persetujuan)
```
