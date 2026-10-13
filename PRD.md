# Product Requirement Document (PRD) & System Architecture Analysis
## AgriFace: Sistem Absensi Biometrik Wajah Perkebunan Berbasis 1-to-1 Verification Engine, Capgo OTA Updater & Hybrid Offline-First Architecture

- **Nama Proyek**: AgriFace (AgriFace Biometric Attendance System)
- **Versi Dokumen & Proyek**: 3.2.0-major-release
- **Database Engine**: Supabase Cloud PostgreSQL (JSONB Vector Storage, RPC), Dexie.js (Web IndexedDB), `@capacitor-community/sqlite` (Android Native SQLite)
- **Biometric Engine**: `@vladmandic/human` (1024-dim Embedding Vector, FP16 WebGL Precision) + MediaPipe Face Mesh + EAR Liveness Engine + Cosine Similarity
- **Update System**: `@capgo/capacitor-updater` Over-The-Air (OTA) Bundle Update Engine
- **Target Kompleksitas**: $O(1)$ Time Complexity Direct Lookup Matching

---

## 1. Pendahuluan & Latar Belakang

> **Major Release Note (v3.2.0)**: Major update arsitektur Full-Bleed Mobile Data Tables, Eliminasi Gutter Layout, Pembersihan Hierarki Tag Semantic Main, Smooth Stacking Context Sliding Header, Anti-Distortion Biometric Canvas, dan Dual-Camera Scanner.
> 1) **Full-Bleed Mobile Data Tables (100% Viewport)**: Transformasi menyeluruh antarmuka tabel data pada 4 modul utama (Log Absensi, Monitoring Absensi, Daftar Karyawan, dan Mutasi Karyawan) untuk layar ponsel ($\le 768\text{px}$). Menggunakan arsitektur `MobileDataPage`, `MobileFilterSheet`, `MobileTableViewport`, dan hook `useIsMobileLayout` dengan pemenuhan standar metrik UX: eliminasi total margin/gutter ancestor, lebar 100% sejajar topbar, tinggi header $\le 200\text{px}$, area tabel $\ge 65\%$ tinggi layar, baris tampak $\ge 12$ baris, dan nol horizontal scroll pada `body`.
> 2) **Smooth Stacking Context & Zero Collision Sliding Header**: Perbaikan stacking context z-index berjenjang pada tabel Monitoring Absensi mobile (`th.col-sticky-nama` z-index: 35 dengan solid background & box-shadow; kolom NIK/Kebun/Afdeling z-index: 6) sehingga saat digeser horizontal ke kanan seluruh kolom identitas lain meluncur mulus (*smooth glide*) di bawah kolom Nama Karyawan tanpa tabrakan.
> 3) **Pembersihan Toolbar & Penghapusan Tombol "Tambah" di Daftar Karyawan**: Tombol "Tambah" dihapus dari halaman Daftar Karyawan (baik Mobile maupun Desktop) untuk seluruh akun karena pendaftaran karyawan terpusat di menu mandiri "Registrasi Wajah" (`/karyawan`), sekaligus mencegah tabrakan/tumpang tindih dengan judul "Daftar Karyawan" pada tampilan mobile.
> 4) **Anti-Distortion Canvas & Mesh Gepeng Fix**: Sinkronisasi dimensi fisik display box canvas dengan resolusi internal detektor AI via `ResizeObserver` berbasis $\min(\text{stageW}/\text{targetW}, \text{stageH}/\text{targetH})$ untuk mengeliminasi distorsi non-uniform ("mesh gepeng") pada perangkat mobile.
> 5) **Dual-Camera Rolling & Default Back Camera**: Penambahan kontrol tukar kamera (*front/environment*) di seluruh modal pemindai biometrik (Absensi, Pendaftaran, Rescan Wajah) dengan *default facingMode* kamera belakang (*environment*) untuk perangkat bergerak demi kepraktisan operasional mandor di kebun.
> 
> *(Catatan Patch Sebelumnya: v3.1.4–v3.1.8)*: Implementasi `FullscreenScanShell`, Face Frame Guidance (`FaceFrameTemplate`), carousel fix, serta peningkatan toleransi liveness biometrik.
> *(Catatan V3.1.3)*: Pembaruan visual Executive Dashboard (isolasi `AnimatedNumber`, koreksi formula TK Hadir, dan `ComboBarLineChart`).
> *(Catatan V3.1.2)*: Safe SQLite Migration, pembaruan cache sync, dan validasi ketat Array Kosong/NaN pada biometrik.


Sistem absensi biometrik wajah konvensional umumnya mengabaikan efisiensi dengan menggunakan pendekatan **1-to-N (Verifikasi 1-ke-Banyak)**. Skema $O(N)$ ini menimbulkan kendala fatal saat jumlah karyawan membengkak, termasuk lonjakan latensi server dan tingkat *false positive* yang tinggi.

Sistem AgriFace menerapkan arsitektur **1-to-1 Direct Lookup ($O(1)$)**, **Offline-First PWA (IndexedDB + SQLite)**, serta **Hybrid 3-Tier Data Resiliency Engine** yang menjamin ketersediaan sistem 99.9% meskipun diakses dari perangkat mobile dengan jaringan tidak stabil atau dalam kondisi luring (offline) di tengah perkebunan.

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
                |          NATIVE SQLITE (APK)  /  INDEXEDDB DEXIE (WEB)                |
                |  - user_master / local_master_descriptors                             |
                |  - attendance_sync_queue / local_attendance_queue                     |
                |  - employees_cache / local_employees                                  |
                |  - attendance_requests                                                |
                +-----------------------------------------------------------------------+
```

### 2.1 Frontend (Client-Side SPA, PWA & Native Android Shell)
- **Framework & UI Engine**: React v19.2.8 & Vite v8.1.5.
- **Mobile UI & Full-Bleed Table Architecture**: Modul tabel data mobile diisolasi secara kondisional melalui hook `useIsMobileLayout` ($\le 768\text{px}$) menggunakan komponen `MobileDataPage`, `MobileFilterSheet` (bottom sheet dialog), dan `MobileTableViewport` dengan *zero-regression* terhadap tata letak desktop.
- **Biometric Viewport & Camera Controller**: Hook `useScanViewport` dan komponen `FaceScanSurface` mengintegrasikan kalkulasi aspek rasio dinamis berbasis `ResizeObserver` serta kontrol pergantian stream kamera (depan/belakang).
- **OTA Update Engine**: `@capgo/capacitor-updater` terintegrasi dengan bucket Supabase `ota-updates` (menggunakan POSIX-compliant zip) dan modal *blocking* pembaruan.
- **Service Worker & Caching**: Bypass caching eksplisit (`cache: 'no-store'`) untuk domain `*.supabase.co` di `sw.js` agar data dari server selalu segar tanpa fenomena data stagnan (*stale data*).
- **Biometric Processing**: Pustaka AI dijalankan secara lokal di klien dengan paksaan *FP16 WebGL Precision* untuk menjamin standar nilai desimal 1024-dimensi yang identik secara universal melintasi HP, Tablet, maupun Laptop.

### 2.2 Backend & Database Infrastructure
- **Serverless / Hybrid Node.js**: Sistem mendukung akses API lokal (Express.js) dengan batas *timeout* dinamis 3 detik. Jika gagal, klien secara *fallback* langsung mengeksekusi tulis/baca ke SDK Supabase.
- **Database Terpusat**: Supabase PostgreSQL dengan pengamanan *Row Level Security* (RLS) serta fungsi *Remote Procedure Call* (RPC) seperti `sync_offline_employee` untuk penyelesaian konflik *(Server-Wins Conflict Resolution)*.

---

## 3. Analisis Mendalam & Fitur Utama Arsitektur

### 3.1 Arsitektur Absensi Hybrid Server-First (3-Tier Submission Flow)
Untuk menjamin pengiriman log absensi yang andal di area minim sinyal:
1. **Tier 1 (Server-Authoritative)**: Mengirim data (termasuk GPS dan Euclidean distance) ke endpoint `/api/attendance/verify`.
2. **Tier 2 (Direct Supabase Fallback)**: Jika server backend *timeout* (3 detik), sistem memotong jalur dan menulis data absensi langsung ke Supabase Cloud DB.
3. **Tier 3 (Local SQLite/Dexie Offline Buffer)**: Jika perangkat sepenuhnya luring (offline), log masuk ke antrean SQLite/IndexedDB untuk disinkronisasi otomatis nanti.

### 3.2 Algoritma Pemrosesan Biometrik & Pengenalan Wajah

Sistem menggunakan model `@vladmandic/human` (`blazeface` + `facemesh` + `faceres`) untuk mengekstraksi **1024-Dimensional FaceRes Embedding Vector** pada presisi *FP16 WebGL*. Pendekatan yang digunakan adalah **1-to-1 O(1) Direct Lookup** antara wajah di kamera dengan vektor master karyawan yang dipilih.

1. **Komputasi Pencocokan (Cosine Similarity)**
   Komputasi kedekatan antar wajah murni menggunakan **Cosine Similarity** (Dot Product dari dua vektor yang dinormalisasi L2).
   
2. **Power Curve Calibration (Skala Verifikasi 1-to-1)**
   Pada ruang vektor 1024-D, pasangan impostor (wajah berbeda) berkumpul di nilai *cosine* 0.60–0.84, sementara wajah asli (genuine) berada di 0.88–0.98. Untuk menghasilkan persentase kecocokan yang wajar secara UX, nilai *raw cosine* dikalibrasi menggunakan **Power Curve** (Eksponen `1.8`, `COSINE_FLOOR = 0.70`, `COSINE_CEIL = 0.98`).
   - **Threshold Kelulusan Absensi**: Nilai kalibrasi **`>= 82.0%`** (setara dengan nilai *raw cosine* **`~0.92`**). Target *False Accept Rate (FAR)* ditekan hingga `< 0.1%`.

3. **Anti-Duplikasi Registrasi (O(N) Local Scan)**
   Pada tahap pendaftaran karyawan, algoritma melakukan *looping* O(N) singkat terhadap basis data master lokal (`local_master_descriptors`). Jika wajah baru memiliki tingkat kecocokan *raw cosine* **`>= 0.88`** terhadap karyawan manapun, registrasi **ditolak** untuk mencegah karyawan ganda.

4. **Liveness Detection (EAR)**
   Pendeteksian interaksi *(Liveness)* dijalankan dengan membedah 468 titik Face Mesh. Sistem menghitung *Eye Aspect Ratio (EAR)* dari jarak Euclidean vertikal dan horizontal pada kelopak mata untuk mendeteksi kedipan nyata demi menggagalkan penyalahgunaan foto 2D.

5. **Kestabilan Koordinat & Normalisasi**
   - **Peredam Getaran One-Euro Filter**: Filter matematika adaptif mengurangi *jitter* tanpa *lag*, sehingga grafik *mesh* tampak halus.
   - **Normalisasi Bebas Perangkat**: Hook `useNormalizedFaceMesh` membingkai kordinat wajah agar tidak terdistorsi layar, dengan *Center Crop 4:3* statis sebelum proses deteksi AI.

### 3.3 Engine Sinkronisasi (Auto-Sync) & Cut-Off 22:00
- **Cut-Off 22:00 Strict Mode**: Aplikasi menyimpan absen secara persisten secara lokal saat offline, dan mengalokasikan siklus *upload* massal (Sinkronisasi Otomatis) secara berkala (utamanya di ujung jam kerja pukul 22:00) atau ketika tombol **Sinkronisasi Manual** diklik.
- **Delta Sync**: Penarikan data (karyawan & log) hanya memuat perubahan baris baru (*Delta*) via `.gt('created_at', lastSyncTime)`, menghemat bandwidth dan memori secara masif.
- **Multi-Account Shared Device Handling**: Pada perangkat tablet bersama (*Shared Device*), mekanisme *Logout Guard* mencegah keluarnya sesi jika ada absensi admin yang belum ter-*push* ke awan. Database lokal tidak lagi dihancurkan asal-asalan, mengamankan data admin lain.

### 3.4 Arsitektur Full-Bleed Mobile Data Table & Adaptive Bottom Sheet Filters
Untuk memaksimalkan produktivitas operasional di lapangan melalui smartphone dengan layar terbatas:
1. **Pemisahan Layout Responsif Tanpa Regresi Desktop**: Sistem memisahkan rendering halaman data antara Desktop Layout dan Mobile Layout secara bersih melalui conditional branch `isMobile ? <MobileDataPage> : <DesktopLayout>`.
2. **Ergonomic Header Isolation**: Seluruh kontrol filter sekunder yang kompleks (Kebun, Afdeling, Status TK, Rentang Tanggal) dipindahkan dari header layar ke dalam drawer **Bottom Sheet** (`MobileFilterSheet`) yang dibuka via tombol Filter dengan badge counter filter aktif.
3. **Independent Viewport Scrolling**: Tabel dibungkus di dalam `MobileTableViewport` dengan properti `overscroll-behavior: contain` dan momentum scrolling iOS, memastikan gestur horizontal pada tabel tidak memicu scroll horizontal pada kontainer aplikasi utama (`body`).
4. **Column Freezing Strategis**: Kolom identitas utama (seperti *Nama Karyawan*) dikunci (*sticky*) pada sisi kiri tabel mobile, sementara kolom pelengkap diizinkan scroll horizontal untuk menghemat ruang pandang.

### 3.5 Dual-Camera Rolling & Biometric Canvas Aspect Ratio Synchronization
Menjawab tantangan operasional absensi di lapangan perkebunan:
1. **Dynamic Camera Switching**: Komponen `FaceScanSurface` dan `FullscreenScanShell` menyediakan tombol toggle kamera instan (`user` vs `environment`) dengan touch target $\ge 44\times 44\text{px}$. Efek mirror CSS (`transform: scaleX(-1)`) hanya diterapkan saat kamera depan aktif.
2. **Default Facing Mode Lapangan**: Mode kamera *default* untuk perangkat bergerak diarahkan ke kamera belakang (`environment`), memudahkan mandor/admin mengarahkan lensa ke wajah pekerja panen tanpa memutar gawai.
3. **Anti-Distortion Canvas Resizing**: Mengatasi masalah "mesh gepeng" akibat kontainer flex 100% height, sistem menggunakan kalkulasi penyesuaian berbasis `ResizeObserver`:
   $$\text{scale} = \min\left(\frac{\text{stageW}}{\text{targetW}}, \frac{\text{stageH}}{\text{targetH}}\right)$$
   $$\text{boxW} = \text{round}(\text{targetW} \times \text{scale}), \quad \text{boxH} = \text{round}(\text{targetH} \times \text{scale})$$
   Kalkulasi ini menjamin deviasi aspek rasio canvas internal terhadap display box $< 0.1\%$, sehingga titik mesh wajah 468 node tidak terdistorsi secara aksial.

---

## 4. Peran Pengguna & Proses Bisnis

### 4.1 Tabel Peran Pengguna & Hak Akses
| Peran Pengguna | Akses (Otoritas) |
| :--- | :--- |
| **Akun Kebun (Estate Admin)** | Full CRUD access untuk satu unit kebun. Menambah karyawan dan melakukan verifikasi wajah pemanen (*Offline-ready*). |
| **Akun Region (Regional Admin)**| Read-only access untuk mengawasi kumpulan kebun di suatu region. |
| **Akun Head Office (HQ Admin)** | Read-only access skala nasional, dengan wewenang utama **menyetujui (Approve)** atau **menolak (Reject)** pengajuan edit/hapus log absensi dari level kebun. |

### 4.2 Alur Proses Bisnis Mandor Panen
1. Mandor login pada pagi hari (atau menggunakan mode kios untuk akses publik).
2. Karyawan melakukan absensi *Check-In* melalui pindai wajah.
3. Karyawan melakukan *Check-Out* setelah selesai, sementara admin melengkapi catatan jumlah hasil panen (Kg) atau luas lahan (Ha).
4. Di akhir hari (atau pukul 22:00), Mandor memastikan *Unsynced Badge* di *Topbar* bernilai `0` (semua data tersinkronisasi ke server pusat).

---

## 5. Persyaratan Fungsional Detail (Functional Requirements)

### FR-1: Mode Kios (Scanner Publik) & Pembersihan UI
- Laman Scanner Absensi (`/absensi`) dibuka tanpa mewajibkan Karyawan untuk login ke portal dashboard, difasilitasi dalam Mode Kios.
- Antarmuka Kamera dirancang polos, membuang semua kotak teks debug (*Base64*, persentase di atas dahi), hanya mempertahankan jaring kontur wajah (Mesh Node) minimalis yang elegan.

### FR-2: Deduplikasi Verifikasi (Auto-Submit Sekali Saja)
- Saat pemindaian biometrik dinyatakan valid (skor $\ge 85\%$) dan tes *Liveness* (kedip mata) berhasil, kamera akan *mengunci* (lock state) sesi secara instan.
- Proses absensi langsung dikirim **satu kali**, dan kalkulasi biometrik lanjutan pada *frame* berikutnya dihentikan untuk mencegah rekam log ganda (*Double Attendance Logs*).

### FR-3: Anti-Duplikasi Registrasi Master (Anti-Spoofing)
- Pendaftaran wajah Master (baik Live Kamera maupun Upload Gambar) divalidasi ke seluruh arsip lokal SQLite (sebelum disinkronkan) menggunakan *Cosine Similarity*.
- Jika wajah yang serupa sudah dimiliki akun karyawan lain ($d < 0.55$), form otomatis ditolak: *"Registrasi Gagal: Wajah sudah terdaftar atas nama ..."*

### FR-4: Manajemen Data Karyawan (Soft Delete & Fallback ID)
- Data karyawan memiliki parameter `deleted_at` untuk implementasi **Soft Delete**, memastikan log absensinya tetap menjadi bukti valid walaupun karyawan telah dihapus di kemudian hari.
- Karyawan baru yang di-input secara luring (Offline) diberi ID string sementara (cth: `off_emp_123`). Mesin `syncEngine.js` akan mendamaikan (*auto-recovery*) ID ini menjadi BigInt setelah terhubung ke Supabase.

### FR-5: Live Dynamic Unsynced Badge Counter
- *Topbar* aplikasi memuat angka dinamis merah indikator total absensi/karyawan luring (Offline) yang belum terkirim.
- Badge diperbarui seketika (*real-time* interval 2.5 detik) dengan membaca status antrean SQLite lokal perangkat.

### FR-6: Full-Bleed Mobile Data Tables & Bottom Sheet Filters
- Seluruh 4 modul data tabular (Log Absensi, Monitoring Absensi, Daftar Karyawan, Mutasi Karyawan) wajib menyediakan tampilan layar penuh (*full-bleed*) pada perangkat $\le 768\text{px}$.
- Filter sekunder dikelompokkan ke dalam modal Bottom Sheet dengan touch target minimal $44\text{px}$ dan badge counter filter aktif.
- Area tampilan tabel mobile wajib mendominasi $\ge 65\%$ tinggi layar dengan kapasitas minimal 12 baris data tampak tanpa scroll vertikal.

### FR-7: Dual-Camera Switching & Native Aspect-Ratio Scaling
- Seluruh antarmuka pemindai wajah (Absensi Check-In/Out, Registrasi Wajah, Rescan Biometrik) wajib mendukung pergantian kamera depan dan belakang dengan transisi mulus tanpa kebocoran memori pada MediaStream.
- Kanvas overlay biometrik wajib mempertahankan aspek rasio sensor video tanpa distorsi peregangan bitmap ("mesh gepeng").

### FR-8: Penataan Akses Toolbar & Pemisahan Registrasi Karyawan
- Halaman Daftar Karyawan (`/daftar-karyawan`) berfokus penuh pada penyajian dan penelusuran data master karyawan. Tombol "Tambah Karyawan" ditiadakan dari toolbar tabel guna menjaga kelapangan baris judul dan mencegah tumpang tindih UI pada perangkat mobile.
- Hak akses penambahan/registrasi biometrik karyawan baru tetap terisolasi secara aman pada rute mandiri `/karyawan` ("Registrasi Wajah") khusus untuk peran `estate_admin`.

---

## 6. Ringkasan REST API & RPC Backend Specification

| Method / Tipe | Endpoint / RPC Name | Description |
| :--- | :--- | :--- |
| `RPC` | `verify_admin_login` | Validasi kredensial pengguna (admin/HQ) terhadap tabel `admin_auth` (pgcrypto). |
| `RPC` | `sync_offline_employee` | Menjalankan logika resolusi sinkronisasi karyawan luring (Offline) secara aman dari bentrok *(Server-Wins)*. |
| `GET` | `/api/employees` | Mengambil rincian data seluruh karyawan beserta indikator kepemilikan biometrik master. |
| `POST`| `/api/employees` | Registrasi karyawan baru (dilengkapi ID BigInt dari Supabase). |
| `GET` | `/api/biometrics/master/:id`| Menarik data 1024-dimensi master wajah karyawan. |
| `POST`| `/api/attendance/verify`| Engine *Hybrid Server-First* untuk melakukan validasi *Check-In*/*Check-Out* ($O(1)$) berikut koordinat GPS-nya. |
| `GET` | `/api/attendance/logs` | Penarikan historis log absensi terbaru. |
| `DELETE`| `/api/attendance/logs/delete` | (Express Route & Supabase Admin SDK) Menghapus baris log tertentu (biasanya oleh HQ). |

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
    deleted_at TIMESTAMPTZ -- Pengganti Cascade Delete (Soft Delete)
);

-- Tabel Biometrik Master (Vector Storage)
CREATE TABLE master_descriptors (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT UNIQUE NOT NULL REFERENCES employees(id),
    descriptor_json JSONB NOT NULL
);

-- Tabel Log Absensi
CREATE TABLE attendance_logs (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    status VARCHAR(100) NOT NULL,
    attendance_type VARCHAR(20) DEFAULT 'CHECK-IN',
    euclidean_distance DOUBLE PRECISION NOT NULL,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    timestamp TIMESTAMPTZ DEFAULT NOW()
);

-- Tabel Pengajuan (Approval Queue)
CREATE TABLE attendance_requests (
    id VARCHAR(100) PRIMARY KEY,
    request_type VARCHAR(20) NOT NULL,
    log_id VARCHAR(100) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    old_value JSONB,
    new_value JSONB
);
```

### 7.2 Skema Local Offline Database (SQLite Native & Dexie IndexedDB)
Menampung ID sebagai `TEXT` untuk mengakomodasi format `off_emp_xxx`.
```javascript
db.version(7).stores({
  user_master: '++id, employee_id, nik, name, department, updated_at',
  attendance_sync_queue: '++id, employee_id, nik, name, timestamp, status, attendance_type, is_synced',
  employees_cache: 'id, nik, name, department, has_master_biometric',
  attendance_requests: 'id, request_type, log_id, status, is_synced'
});
```

---

## 8. Persyaratan Non-Fungsional (Non-Functional Requirements)

1. **Performa Edge (Kecepatan Pemindaian)**: Pencocokan Euclidean Distance diselesaikan di bawah $<15\text{ ms}$. Respons kamera tetap berada pada ambang stabil $>15\text{ FPS}$ di HP menengah-bawah.
2. **Offline-First PWA Standard**: Aplikasi 100% fungsional saat sinyal seluler mati total (Blank Spot). Asset statis Web Bundle dikontrol lewat `sw.js`, dan sinkronisasi dijadwalkan secara aman ke *Write-Ahead Log* luring perangkat Android.
3. **Privasi Absolut (No Raw Imaging)**: Tidak satupun berkas foto `JPG/PNG` mentah diunggah ke server operasional absensi. Semua wajah diurai menjadi larik Array (Float32) seketika langsung di dalam memori browser pengguna.
4. **Ergonomi & Utilisasi Layar Mobile**: Ketinggian total header pada tampilan tabel mobile dibatasi maksimal $\le 200\text{px}$ dari tepi atas layar. Area pandang tabel wajib mendominasi $\ge 65\%$ tinggi layar dengan kapasitas minimal 12 baris tampak. Ukuran font sel tabel dijaga minimal $12\text{px}$ ($0.75\text{rem}$) dan header kolom minimal $11\text{px}$ ($0.68\text{rem}$) untuk keterbacaan optimal di bawah terik matahari kebun. Zero-scroll horizontal pada dokumen root (`body`).
