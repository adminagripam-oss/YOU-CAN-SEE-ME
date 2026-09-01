# Product Requirement Document (PRD) & System Architecture Analysis
## AgriFace: Sistem Absensi Biometrik Wajah Perkebunan Berbasis 1-to-1 Verification Engine & Hybrid Cloud Infrastructure

- **Nama Proyek**: AgriFace (AgriFace Biometric Attendance System)
- **Judul Proyek**: Aplikasi Absensi Mobile Biometrik Wajah Pemanen Kebun dengan Offline-First PWA & Auto-Sync Engine
- **Target Kompleksitas**: $O(1)$ Time Complexity Direct Lookup Matching
- **Versi Dokumen**: 3.1.0 (One-Face-Per-Employee Biometric Constraint, Offline-Capable Cosine Similarity Engine, Afdeling Mapping, Duration Calculation Release)
- **Database Engine**: Supabase Cloud PostgreSQL (JSONB Vector Storage & pgcrypto) & Dexie.js v2 (IndexedDB Local Storage)
- **Biometric Engine**: `@vladmandic/human` v3.x (1024-dim Embedding Vector) + MediaPipe Face Mesh 478-Point & EAR Liveness Engine + Cosine Similarity Duplicate-Check Engine (Threshold ≥ 0.85)
- **Status System**: Live Production Specifications & Enterprise-Ready PWA

---

## 1. Pendahuluan & Latar Belakang

Sistem absensi biometrik wajah konvensional umumnya mengabaikan efisiensi dengan menggunakan pendekatan **1-to-N (Verifikasi 1-ke-Banyak)**. Pada pendekatan 1-to-N, setiap scan wajah dibandingkan secara kuadratik/linear terhadap seluruh data karyawan yang tersimpan di database. Skema $O(N)$ ini menimbulkan kendala fatal saat jumlah karyawan membengkak:
- **Lonjakan Latensi & CPU**: Server dipaksa menghitung *Euclidean Distance* ke ribuan sampel data.
- **Tingkat False Positive Tinggi**: Semakin besar sampel $N$, semakin tinggi probabilitas *false match*.
- **Ketergantungan Internet**: Kegagalan koneksi jaringan menghentikan seluruh operasional absensi.

Sistem versi 2.1.0 menerapkan arsitektur **1-to-1 Direct Lookup ($O(1)$)**, **Offline-First PWA (IndexedDB + Service Worker)**, **Otentikasi Keamanan JWT (Cookie HTTP-Only & Session)**, serta **Hybrid 3-Tier Data Resiliency Engine** yang menjamin ketersediaan sistem 99.9% meskipun diakses dari perangkat mobile dengan jaringan tidak stabil atau dalam kondisi luring (offline).

---

## 2. Spesifikasi Teknologi & Arsitektur Sistem (Tech Stack & Architecture)

```
                                  +-------------------------------------------------+
                                  |              CLIENT FRONTEND (PWA)              |
                                  |  React 19 + Vite + React Router v7 + Dexie.js   |
                                  +-----------------------+-------------------------+
                                                          |
                                      +-------------------+-------------------+
                                      |                                       |
                         Tier 1: Express API                     Tier 2: Direct Supabase SDK
                         (http://localhost:8080)                 (HTTPS / CORS Safe)
                                      |                                       |
                                      v                                       v
                        +---------------------------+           +---------------------------+
                        |      EXPRESS SERVER       |           |   SUPABASE CLOUD DATABASE |
                        |   Node.js + JWT Cookie    +---------->|   PostgreSQL Engine       |
                        |   Biometric 1-to-1 Auth   |           |   (JSONB Vector Storage)  |
                        +---------------------------+           +---------------------------+
                                                                              ^
                                      Tier 3: Local Offline Cache             |
                        +-----------------------------------------------------+
                        |          LOCAL SQLITE (APK) / INDEXEDDB (WEB)       |
                        |  - user_master (biometric vector cache)             |
                        |  - attendance_sync_queue (offline logs queue)       |
                        |  - employees_cache (master employee list cache)     |
                        |  - attendance_logs (permanent history log cache)    |
                        +-----------------------------------------------------+
```

### 2.1 Frontend (Client-Side SPA & PWA)
- **Framework & UI Engine**: React v19.2.8 & Vite v8.1.5.
- **Routing & Guard**: `react-router-dom` v7.18.2 dengan proteksi rute `<ProtectedRoute>` dan `<PublicRoute>`.
- **State Management & Auth Context**: React Context API (`AuthProvider` & `useAuth` hook) yang mendukung verifikasi sesi otomatis `/api/auth/me`.
- **Design System & Styling**: Custom CSS Tokens dengan dukungan **Light Mode** & **Dark Mode** (`localStorage.getItem('app-theme')`), Glassmorphism UI, Font Google Inter, Iconography Lucide-React & FontAwesome 6.4.0.
- **Feedback Components**: Shadcn UI Toast System (`ShadcnToast.jsx`) dan Dynamic Modal Dialog (`ConfirmModal.jsx`).
  - **Opaque Dark Mode Background**: Di dark mode, background toast diubah dari semi-transparan menjadi warna solid (opaque) (Success: `#062f21`, Error: `#3f1010`, Info: `#111333`) dengan border solid sesuai warna status.
  - **Minimalis Mobile Size**: Pada layar HP ($\le 600\text{px}$), ukuran kotak toast diminimalisir (maks. width 290px, padding `8px 12px`, dan font kecil) untuk estetika premium dan ringkas.
- **Biometric Library**: `@vladmandic/face-api` v1.7.15 (Model ResNet-34 Deep Neural Network, SSD MobileNet V1 Face Detector, Tiny Face Detector, 68 Landmark Points Predictor, 128-dimensional Float32 Descriptor Extractor).
- **Offline Storage**: Dexie.js v4.4.4 wrapper IndexedDB untuk caching master vector, cache data karyawan, dan antrean absensi offline.

### 2.2 Backend & Database Infrastructure
- **Runtime Environment**: Node.js dengan framework Express.js v4.21.2.
- **Cloud Database Engine**: Supabase Cloud PostgreSQL dengan ekstensi tipe data `JSONB` untuk penyimpanan 128-float vector array.
- **Security & Authentication**:
  - Open Standard JWT (`jsonwebtoken` v9.0.3) terenkripsi.
  - Sesi disimpan secara aman pada HTTP-Only Cookie (`cookie-parser` v1.4.7).
  - Cross-Origin Resource Sharing (`cors` v2.8.5) diset dengan `credentials: true`.
- **Environment Management**: `dotenv` v17.4.2.

---

## 3. Analisis Mendalam Arsitektur & Performa Sistem (System Deep-Dive Analysis)

### 3.1 Analisis Kompleksitas Algoritma: $O(1)$ Direct Lookup vs $O(N)$ Scan
Pada sistem 1-to-1 Matching yang diimplementasikan:
1. User memilih atau menginput NIK Karyawan yang akan diabsensi.
2. Sistem menarik **Tepat 1 Master Descriptor** milik karyawan tersebut dari database Supabase (`master_descriptors.eq('employee_id', id)`).
3. Matriks perhitungan *Euclidean Distance*:
   $$d(p, q) = \sqrt{ \sum_{i=1}^{128} (p_i - q_i)^2 }$$
   dieksekusi hanya **satu kali per proses scan**.
4. **Hasil Evaluasi Performa**:
   - Kompleksitas Waktu: $O(1)$ konstan.
   - Waktu Eksekusi Verifikasi Server: $< 15\text{ ms}$.
   - Pemakaian Memory/RAM: Sangat hemat karena tidak perlu memuat seluruh vektor karyawan ke memori.

### 3.2 Analisis Resiliensi Data 3-Tier (3-Tier Hybrid Data Resiliency Engine)
Untuk menangani situasi jaringan yang beragam (misalnya diakses via GitHub Pages di HP mobile di mana request HTTP ke localhost terblokir *Mixed Content*), sistem menggunakan algoritma bertingkat 3-Tier:
1. **Tier 1 (Express REST API)**: Mencoba mengambil data via `/api/employees` dan `/api/attendance/logs`.
2. **Tier 2 (Direct Supabase Cloud SDK)**: Jika Tier 1 gagal/unreachable, sistem secara otomatis melakukan query HTTPS langsung ke Supabase Cloud DB via `@supabase/supabase-js`.
3. **Tier 3 (IndexedDB Local Offline Cache)**: Jika perangkat dalam keadaan *offline*, data diambil dari cache lokal IndexedDB (`db.employees_cache`).

### 3.3 Analisis Keamanan & Validasi Anti-Duplikasi Master Biometrik
Saat mendaftarkan karyawan baru atau mengunggah master foto wajah:
- Backend mengekstrak vektor 128-float.
- Sistem mengeksekusi **Anti-Duplication Check** dengan membandingkan vektor calon karyawan terhadap seluruh master vektor yang sudah terdaftar milik karyawan lain.
- Jika ditemukan kemiripan dengan jarak Euclidean $d < 0.55$, sistem menolak pendaftaran dan mengembalikan response error:
  `Registrasi Gagal: Wajah ini SUDAH TERDAFTAR atas nama karyawan "Nama" (NIK: XXX)`.
- Hal ini mencegah pembajakan identitas biometrik dan registrasi ganda.

### 3.4 Analisis Kebijakan Privasi Data Biometrik
Sistem **tidak pernah menyimpan foto wajah mentah (*raw image file*)** di server backend maupun cloud storage. Foto wajah hanya diproses di memori browser secara ephemeral untuk diekstrak menjadi **128-element Float32 Array**. Hanya data angka matematis inilah yang disimpan di database `JSONB`. Dengan pendekatan ini, data biometrik karyawan aman dari kebocoran foto identitas.

### 3.5 Analisis Kalkulasi Durasi Kerja & Grouping Check-In / Check-Out
Sistem secara cerdas membedakan status absensi hari ini:
- Jika karyawan belum melakukan absensi hari ini, aksi otomatis diset ke `CHECK-IN`.
- Jika karyawan sudah `CHECK-IN`, sistem mengarahkan aksi ke `CHECK-OUT`.
- Pada tab **Riwayat Absensi**, sistem memasangkan log `CHECK-OUT` dengan `CHECK-IN` terakhir karyawan pada hari yang sama, menghitung selisih waktu, dan memformatnya menjadi format intuitif **`Xj Ym Zd`** (contoh: `8j 15m 30d`).

---

## 4. Peran Pengguna & Proses Bisnis (User Roles & Business Process Architecture)

### 4.1 Tabel Peran Pengguna & Hak Akses
| Peran Pengguna | Hak Akses & Deskripsi Otentikasi |
| :--- | :--- |
| **1. Akun Kebun (Estate Admin)** | Full CRUD access untuk satu unit kebun tertentu. Melakukan registrasi, input, dan scan absensi wajah pemanen secara online/offline. |
| **2. Akun Region (Regional Admin)** | Read-only access untuk kebun-kebun di dalam regionalnya. Memantau data HK harian regional tanpa hak akses CRUD karyawan/kamera scan. |
| **3. Akun Head Office (HQ Admin)** | Read-only access berskala nasional untuk seluruh 800 kebun. Memantau visualisasi statistik HK nasional tanpa hak akses CRUD karyawan/kamera scan. |

---

### 4.2 Alur Proses Bisnis Mandor Panen (Mandor Field Operations Workflow)

```
 [1. Start Day]            [2. Biometric Scan]            [3. Field Output Entry]           [4. End Day Sync]
+---------------+         +-------------------+          +------------------------+        +-----------------+
| Mandor Login  |-------> | Biometric Face    |--------> | Input Hasil Panen      |------> | Auto-Sync Log   |
| App per Kebun |         | Check-In Pemanen  |          | - Tonase (Kg)          |        | Absensi & Output|
| & Afdeling    |         | (Timestamp & HK)  |          | - Luas Area (Hektare)  |        | ke Cloud DB     |
+---------------+         +-------------------+          +------------------------+        +-----------------+
```

1. **Pendataan Hari Kerja & Jam Kerja (Attendance & HK Tracking)**:
   - Mandor melakukan verifikasi absensi biometrik wajah pemanen pada jam masuk (Check-In) dan jam keluar (Check-Out).
   - Sistem secara otomatis menghitung durasi jam kerja dan mencatat nilai **Hari Kerja (HK)** (misal: $1.0\text{ HK}$ untuk jam kerja penuh atau $0.5\text{ HK}$ untuk setengah hari).



3. **Kalkulasi Persentase Kehadiran Afdeling (% Attendance Rate)**:
   - Sistem menghitung persentase tingkat kehadiran pemanen secara *real-time* per Afdeling dan per Kebun dari total kuota pekerja:
     $$\text{\% Kehadiran Pemanen} = \left( \frac{\text{Jumlah Pemanen Hadir (Scan Verified)}}{\text{Total Pekerja Pemanen Terdaftar di Afdeling}} \right) \times 100\%$$

---

## 5. Persyaratan Fungsional Detail (Functional Requirements)

### FR-1: Otentikasi RPC Backend & Separasi Layout SPA
- **Public Layout (`/login` & `/absensi`)**:
  - Halaman login murni digunakan untuk **Admin** dengan form *Username* dan *Password*. 
  - Verifikasi keamanan sangat ketat karena tidak dilakukan di frontend, melainkan dikirim ke backend Supabase (RPC `verify_admin_login`) untuk dicocokkan dengan *hash bcrypt* menggunakan fungsi `pgcrypto`.
  - Halaman **Scanner Absensi (`/absensi`)** bersifat publik (Mode Kios). Karyawan bisa langsung melakukan pemindaian wajah dan absen tanpa harus login ke dashboard.
- **Protected Layout (`/dashboard`, `/karyawan`, `/logs`)**:
  - Dibungkus oleh `<ProtectedRoute>`. Hanya Admin yang bisa mengakses area manajemen ini.
  - Memiliki **Persistent Collapsible Sidebar** dan **Persistent Topbar** dengan tombol hamburger toggle untuk tampilan mobile. Menu navigasi akan beradaptasi secara dinamis menyembunyikan rute yang terproteksi jika pengguna belum login.
  - Badge Status Jaringan Real-Time (`LIVE` / `OFFLINE`) dan indikator antrean `Pending Sync`.
- **Analytics Layout (`/analytics`)**:
  - Dashboard Executive & Manager Kebun yang menyajikan metric produktivitas **Kg/HK**, **Ha/HK**, persentase kehadiran afdeling, serta filter berdasarkan Kebun & Afdeling.

### FR-2: Verification Engine 1-to-1 ($O(1)$) Check-In & Check-Out & Transactional State Guard
- Live webcam mendeteksi bounding box wajah, 68 titik landmark, dan 128-float vector descriptor.
- Overlay canvas interaktif menampilkan visualisasi jaringan landmark wajah dan indikator kualitas pencahayaan.
- Perhitungan jarak Euclidean terhadap master biometrik karyawan yang dipilih.
- Ambang batas (*threshold*) $d < 0.55 \rightarrow$ **VERIFIKASI BERHASIL**.
- **Transactional Async/Await Order:** Tombol UI dan Toast notification 'Absensi Berhasil' **dilarang** berubah/muncul sebelum transaksi database (Express API / Supabase Direct / Dexie.js) secara eksplisit berhasil (`Promise.resolve`). Jika gagal, state tombol dipertahankan dan Toast error ditampilkan.
- **State Guard & Multi-Tier Fallback:** Pengecekan status harian menggabungkan Express REST API, Supabase Cloud Direct, serta pemeriksaan antrean `db.attendance_sync_queue` (Dexie.js). Mengunci state `checkedIn = true` jika terdapat antrean lokal hari ini, serta mencegah *State Revert* oleh re-render/interval.

### FR-3: Registrasi Master Biometrik Wajah
- Pendaftaran master biometrik melalui dua mode: **Kamera Live** atau **Upload Foto (JPG/PNG/WEBP)**.
- Validasi otomatis bahwa foto mengandung tepat 1 wajah yang terdeteksi jelas.
- **Satu Wajah Per Karyawan**: Setiap karyawan hanya boleh memiliki satu profil biometrik wajah yang unik. Sistem menjalankan validasi anti-duplikasi berbasis **Cosine Similarity** dengan threshold $\ge 0.85$ terhadap seluruh vektor master yang tersimpan di cache lokal — baik secara **real-time saat kamera aktif** maupun saat **submit formulir**.
- Validasi duplikasi berlaku sepenuhnya **offline** karena menggunakan data dari SQLite lokal (APK) atau IndexedDB (web browser).
- Penyimpanan ke Supabase DB (`master_descriptors`) dan caching otomatis ke SQLite/IndexedDB HP (`user_master`).

### FR-4: Manajemen Data Karyawan (CRUD)
- Registrasi karyawan baru dengan NIK, Nama, Departemen, Afdeling, dan Nama Kebun.
- **Lock Kebun Otomatis**: Untuk pengguna dengan peran `estate_admin`, input field **Nama Kebun** otomatis terisi (pre-filled) dengan nama kebun terdaftarnya dan terkunci (disabled / read-only). Field ini juga dipertahankan agar tidak ter-reset setelah submit data karyawan berhasil.
- Update data karyawan via modal dialog edit (`PUT /api/employees/:id`).
- Penghapusan data karyawan (`DELETE /api/employees/:id`) secara *cascade* menghapus master biometrik dan log absensinya di Supabase DB.

### FR-5: Offline Attendance Queue & Auto-Sync Engine
- Ketika koneksi terputus (*offline*), proses verifikasi memanfaatkan master biometrik yang di-cache di IndexedDB (`db.user_master`).
- Log absensi disimpan ke antrean IndexedDB (`db.attendance_sync_queue`).
- Event listener `online` dan timer interval 20 detik secara otomatis memicu `syncPendingAttendanceLogs()` untuk mengirim antrean batch ke endpoint `POST /api/attendance/sync`.

### FR-6: Alur Persetujuan (Approval Requests Workflow) & Tree Routing
- **Alur Otoritas Edit/Hapus**: 
  - Akun Head Office (`admin.hq` / `headoffice_admin`) mempertahankan wewenang edit dan hapus langsung.
  - Akun Regional (`regional_admin`) dan Kebun (`estate_admin`) yang melakukan pengeditan atau penghapusan log absensi dialihkan untuk membuat pengajuan request persetujuan (`attendance_requests`).
  - Request ini disimpan secara offline di local Dexie `db.attendance_requests` dengan flag `is_synced: false` jika koneksi terputus, dan disinkronkan secara otomatis ketika online.
  - Akun Head Office mengelola permohonan melalui tab panel "Persetujuan Edit/Hapus" di halaman Logs untuk menyetujui atau menolaknya.
- **Tree Routing & Layout Outlets**:
  - Routing diatur menggunakan React Router v6+ Data API (`createBrowserRouter` & `<RouterProvider>`) untuk struktur bersarang (nested layout) yang lebih modular dan aman.
  - Shared layout (`DashboardLayout`) dan pengaman otentikasi (`ProtectedRoute` / `PublicRoute`) menggunakan komponen `<Outlet />` bawaan.

---

## 6. Ringkasan REST API & RPC Backend Specification

| Method/Tipe | Endpoint / RPC Name | Access Level | Description |
| :--- | :--- | :--- | :--- |
| `RPC` | `verify_admin_login` | Public | Otentikasi admin multi-regional dengan mengembalikan record profile (`is_valid`, `u_role`, `u_region`, `u_kebun`, `u_name`). |
| `GET` | `/api/auth/me` | Protected | Verifikasi integritas sesi aktif saat *refresh* halaman (F5). |
| `GET` | `/api/employees` | Public/Protected | Mengambil daftar seluruh karyawan beserta status biometrik master. |
| `POST` | `/api/employees` | Protected | Menambahkan data karyawan baru ke database Supabase. |
| `PUT` | `/api/employees/:id` | Protected | Mengubah data NIK, Nama, dan Departemen karyawan. |
| `DELETE` | `/api/employees/:id` | Protected | Menghapus karyawan (CASCADE hapus master biometrik & log). |
| `POST` | `/api/biometrics/register` | Protected | Mendaftarkan/memperbarui 128-float vector master biometrik dengan cek anti-duplikasi. |
| `GET` | `/api/biometrics/master/:id` | Public/Protected | Mengambil master vector biometrik (128-d & 40-d GFV) spesifik karyawan untuk matching 1-to-1. |
| `POST` | `/api/attendance/verify` | Public/Protected | Engine verifikasi 1-to-1 ($O(1)$) untuk Check-In & Check-Out dengan urutan transaksi transaksional. |
| `GET` | `/api/attendance/status/:id` | Public/Protected | Mengecek status absensi hari ini (Timezone-safe range `.gte` startOfDay & `.lte` endOfDay). |
| `GET` | `/api/attendance/logs` | Protected | Mengambil riwayat log absensi terbaru beserta kalkulasi durasi kerja. |
| `POST` | `/api/attendance/sync` | Protected | Endpoint batch auto-sync untuk menerima antrean absensi offline dari IndexedDB. |
| `DELETE` | `supabase.from('attendance_logs')` | Protected | (Direct SDK) Menghapus 1 session log absensi spesifik langsung via client Supabase untuk melewati batasan Vercel Serverless. |

---

## 7. Skema Database (Database Schemas)

### 7.1 Skema Relasional Supabase PostgreSQL Cloud

```sql
-- 1. Tabel Master Admin Auth (Multi-Regional)
CREATE TABLE IF NOT EXISTS public.admin_auth (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(30) DEFAULT 'estate_admin', -- 'headoffice_admin', 'regional_admin', 'estate_admin'
    region VARCHAR(50),
    kebun VARCHAR(100),
    nik VARCHAR(50),                         -- Admin NIK (opsional, dari CSV pemetaan)
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. Tabel Master Kebun (800 Kebun Nasional)
CREATE TABLE IF NOT EXISTS public.kebun (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    cro VARCHAR(50),
    regional VARCHAR(50) NOT NULL,
    distrik VARCHAR(50),
    nama_kebun VARCHAR(100) UNIQUE NOT NULL,
    luas_tm_real NUMERIC,
    luas_tm_rkap NUMERIC,
    luas_di_panen NUMERIC,
    jumlah_pokok INTEGER,
    nama_manager VARCHAR(100),
    no_hp VARCHAR(50),
    status_milik VARCHAR(50),
    status_kelola VARCHAR(50),
    tahap_penerimaan VARCHAR(50),
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. Tabel Master Karyawan / Pemanen (Employees)
CREATE TABLE IF NOT EXISTS public.employees (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nik VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL, -- Pemanen, Mandor, dsb.
    kebun VARCHAR(100) DEFAULT 'Kebun A',
    afdeling VARCHAR(100) DEFAULT 'Afdeling 1',
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. Tabel Master Vector Biometrik Wajah (128-Float Array JSONB)
CREATE TABLE IF NOT EXISTS public.master_descriptors (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT UNIQUE NOT NULL REFERENCES public.employees(id) ON DELETE CASCADE,
    descriptor_json JSONB NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- 3. Tabel Riwayat Log Absensi & Output Panen (Attendance & Harvest Logs)
CREATE TABLE IF NOT EXISTS public.attendance_logs (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT NOT NULL REFERENCES public.employees(id) ON DELETE CASCADE,
    kebun VARCHAR(100) DEFAULT 'Kebun A',
    afdeling VARCHAR(100) DEFAULT 'Afdeling 1',
    location VARCHAR(255) DEFAULT 'TPH / Blok Kebun',
    status VARCHAR(100) NOT NULL,
    euclidean_distance DOUBLE PRECISION NOT NULL,
    timestamp TIMESTAMPTZ DEFAULT NOW(),
    attendance_type VARCHAR(20) DEFAULT 'CHECK-IN',
    hk_val DOUBLE PRECISION DEFAULT 1.0, -- Hari Kerja (1.0 HK atau 0.5 HK)
    kg_output DOUBLE PRECISION DEFAULT 0.0, -- Output Hasil Panen (Kg)
    ha_output DOUBLE PRECISION DEFAULT 0.0 -- Output Luas Area Panen (Hektare)
);

-- 6. Tabel Request Edit & Hapus Absensi (Approval Queue)
CREATE TABLE IF NOT EXISTS public.attendance_requests (
    id VARCHAR(100) PRIMARY KEY,
    request_type VARCHAR(20) NOT NULL, -- 'EDIT' atau 'DELETE'
    log_id VARCHAR(100) NOT NULL,
    nik VARCHAR(50),
    name VARCHAR(150),
    nama_kebun VARCHAR(100),
    requested_by VARCHAR(100) NOT NULL,
    requested_at TIMESTAMPTZ DEFAULT NOW(),
    status VARCHAR(20) DEFAULT 'PENDING', -- 'PENDING', 'APPROVED', 'REJECTED'
    old_value JSONB,
    new_value JSONB
);

-- Indeks Performa untuk Query O(1) & Analytics Afdeling
CREATE INDEX IF NOT EXISTS idx_employees_nik ON public.employees(nik);
CREATE INDEX IF NOT EXISTS idx_master_emp_id ON public.master_descriptors(employee_id);
CREATE INDEX IF NOT EXISTS idx_attendance_emp_timestamp ON public.attendance_logs(employee_id, timestamp DESC);
CREATE INDEX IF NOT EXISTS idx_attendance_afdeling ON public.attendance_logs(kebun, afdeling);
```

### 7.2 Skema Local IndexedDB (Dexie.js Engine)

```javascript
// Database Name: FaceAttendanceOfflineDB (Dexie.js v7)
db.version(7).stores({
  // Cache Master Biometrik HP (128-d & 40-d GFV)
  user_master: '++id, employee_id, nik, name, department, updated_at',
  
  // Antrean Log Absensi Offline
  attendance_sync_queue: '++id, employee_id, nik, name, timestamp, status, attendance_type, is_synced, created_at',
  
  // Cache Daftar Karyawan
  employees_cache: 'id, nik, name, department, has_master_biometric',
  
  // Antrean Request Approval Offline
  attendance_requests: 'id, request_type, log_id, status, is_synced'
});
```

---

## 8. Persyaratan Non-Fungsional (Non-Functional Requirements)

1. **Performa & Latensi**: Jarak Euclidean dihitung dalam $< 15\text{ ms}$. Responsivitas pemindaian kamera minimal 5 FPS di browser HP.
2. **Kepatuhan Privasi Data**: Bebas simpan gambar mentah. Penyimpanan terenkripsi hanya untuk angka Float32 128 dimensi.
3. **Cross-Browser & Cross-Device Compatibility**: Responsif penuh pada resolusi layar 320px hingga 4K UHD. Kompatibel dengan Google Chrome, Safari Mobile, Microsoft Edge, dan Mozilla Firefox.
4. **PWA Compliance**: Lolos kualifikasi PWA dengan Service Worker (`public/sw.js`) untuk precaching aset statis dan web manifest.
5. **Theme Engine**: Transisi CSS smooth antara Light Mode dan Dark Mode dengan standar kontras rasio WCAG AAA.

---

## 9. Rencana Pengembangan Masa Depan (Future Roadmap)

1. **PostgreSQL pgvector Extension**: Mengintegrasikan ekstensi `pgvector` di Supabase untuk pencarian kesamaan kosinus (*Cosine Distance Indexing*) jika sistem di masa depan perlu mendukung hybrid 1-to-N search.
2. **Geofencing & GPS Radius Enforcement**: Menambahkan validasi jarak radius geofencing lokasi koordinat kebun (TPH/Blok) berbasis Haversine formula saat Check-In/Check-Out.

---

## 10. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v2.2.0)

Pembaruan versi **v2.2.0** membawa penyempurnaan menyeluruh pada mesin biometrik, arsitektur pencocokan vektor, keamanan anti-spoofing, serta pembersihan antarmuka pengguna (UI/UX):

### 10.1 Mesin Biometrik Mesh & Geometric Feature Vector (GFV 40-d)
- **Modul Triangulasi & Mesh 3D**: Menggunakan pemetaan 68-point facial landmarks yang dinormalisasi secara *scale-invariant* terhadap *Inter-Pupillary Distance* (IPD).
- **Vektor Fitur Geometris (40-d)**: Memetakan rasio dan jarak antar-fitur fisik (lebar/tinggi mata, lebar alis, jarak mata-ke-hidung, kontur rahang, ketebalan bibir, dan rasio simetri wajah).
- **Cosine Similarity Math Engine**:
  $$\text{CosineSimilarity}(A, B) = \frac{A \cdot B}{\|A\| \cdot \|B\|}$$
  - Threshold Kesamaan Kosinus GFV (40-d): $\ge 0.85$ ($85\%$).
  - Threshold Kesamaan Kosinus Embedding (128-d): $\ge 0.80$ ($80\%$).

### 10.2 Anti-Spoofing & Liveness Detection Engine
- **Eye Aspect Ratio (EAR) Blink Detection**:
  $$\text{EAR} = \frac{\|p_2 - p_6\| + \|p_3 - p_5\|}{2 \cdot \|p_1 - p_4\|}$$
  Sistem memverifikasi manusia hidup (*Live Human*) melalui transisi kelopak mata tertutup ($\text{EAR} < 0.21$) ke mata terbuka kembali ($\text{EAR} > 0.24$) guna mencegah spoofing foto/video 2D.
- **Backup 3D Head Rotation**: Verifikasi rotasi sudut kepala (*Yaw left/right*) sebagai alternatif keamanan liveness.

### 10.3 Arsitektur Pemuatan Master Vektor 4-Tier (*4-Tier Load Resiliency*)
Untuk menjamin vektor master selalu tersedia saat karyawan dipilih:
1. **Tier 1 (Local IndexedDB Cache)**: Mengecek data master di Dexie IndexedDB lokal.
2. **Tier 2 (Express REST API)**: Memanggil endpoint `GET /api/biometrics/master/:employeeId`.
3. **Tier 3 (Supabase Cloud Direct)**: Melakukan query langsung ke tabel `master_descriptors` & `employees`.
4. **Tier 4 (Props Fallback)**: Mengambil descriptor dari daftar `employees` di memori frontend.
5. **Auto-Caching**: Vektor master yang berhasil dimuat dari server/cloud otomatis di-cache ke IndexedDB lokal untuk pemindaian offline berikutnya.

### 10.4 Logika Status Absensi & Timezone Guard (*Check-In / Check-Out Lock*)
- **Penanganan Timezone UTC/Lokal (`isSameDay`)**: Query status backend (`GET /api/attendance/status/:employeeId`) membandingkan tanggal secara aman tanpa terpengaruh offset jam UTC vs WIB.
- **State Guard Penguncian Tombol**:
  Begitu **Check-In** sukses dilakukan:
  - State `checkedIn` dikunci pada nilai `true` dan tombol berubah menjadi **`LOG OUT (CHECK-OUT)`** (merah).
  - Status tidak akan kembali ke Check-In secara otomatis sampai pengguna sendiri yang menekan tombol `LOG OUT (CHECK-OUT)`.
  - Setelah Check-Out selesai, tombol terkunci menjadi **`Absensi Selesai Hari Ini`**.

### 10.5 Pembersihan Antarmuka UI/UX (Clean & Minimalist Design)
- **Node-Only Biometric Overlay**: Menampilkan titik node halus (`r = 1.8px`) dan garis kontur tipis yang mengalir natural mengikuti pergerakan kamera tanpa *double mirror transform*.
- **Eliminasi Tampilan Berantakan**: Menghapus teks overlay persen di atas wajah, garis ukur putus-putus, crosshair, serta preview kotak Base64 di bawah kamera.
- **Single Toast Notification**: Menghapus pesan alert ganda di bawah tombol; notifikasi absensi hanya dimunculkan 1 kali melalui Pop-up Toast di sudut kanan atas layar.

### 10.6 Keamanan Admin & Kiosk Mode (Release v2.3.0)
- **Admin RPC Authentication**: Menghapus sistem login karyawan via *dropdown*. Login diubah sepenuhnya menggunakan form Username/Password yang diverifikasi via RPC (`verify_admin_login`) ke *hash* `pgcrypto` di Supabase. Ini memastikan kredensial tidak bocor di frontend.
- **Open Scanner (Mode Kios)**: Layar Scanner Absensi dikeluarkan dari rute yang dilindungi (Protected Route). Hal ini memfasilitasi penggunaan aplikasi dalam bentuk Kios stasioner (seperti di pos penjagaan atau tablet yang disandarkan), di mana karyawan bisa langsung memindai wajah tanpa harus punya akun untuk masuk ke dashboard.
- **Mobile-First Camera UI**: Menambahkan tombol pergantian (*Switch Camera*) antara kamera depan (*user*) dan belakang (*environment*) dengan penyesuaian otomatis cermin (*mirroring/transform scaleX*). Rasio tampilan kamera juga diperbesar menjadi `3:4` pada layar *mobile* sehingga mengisi tinggi layar (mirip *interface* aplikasi kamera bawaan HP).
- **Direct SDK Integration untuk Perintah Hapus**: API untuk menghapus log absensi dipindahkan murni menggunakan pemanggilan SDK Supabase (`supabase.from('attendance_logs').delete()`) di komponen klien untuk menghindari error konektivitas Vercel ke port backend yang terisolasi.
- **Sistem Kunci Wajah Spesifik (Single Face Locking)**: Beralih dari penggunaan `detectSingleFace` ke `detectAllFaces` lalu mengekstrak wajah dengan luas dimensi *(bounding box area)* terbesar. Hal ini mengizinkan sistem untuk secara otomatis dan konsisten mengunci fokus pemrosesan hanya pada satu wajah karyawan yang berdiri paling dekat di depan kamera, dan mengabaikan wajah karyawan lain di *background* meskipun secara tak sengaja tertangkap kamera.
- **UI Re-branding**: Mengintegrasikan logo AGRIFACE secara menonjol pada halaman Login (dimensi resolusi tinggi) dengan gaya desain yang lebih bersih tanpa ikon/kotak yang bertumpuk pada Sidebar maupun Topbar (Text-only brand logo).

---

## 11. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v2.4.0)

Pembaruan versi **v2.4.0** membawa optimalisasi masif pada integrasi jaringan multi-device, peningkatan performa pemrosesan AI di perangkat mobile (HP), penyelarasan keamanan Row Level Security (RLS) Supabase, serta penguatan pondasi Mode Offline PWA:

### 11.1 Dinamisasi API Base URL untuk Koneksi HP (LAN Auto-IP Resolution)
* **Akar Masalah**: Sebelumnya, `API_BASE_URL` di-hardcode ke `localhost:8080`, menyebabkan browser HP yang mengakses aplikasi via jaringan Wi-Fi lokal gagal menghubungi server backend (karena `localhost` di HP merujuk ke HP itu sendiri).
* **Solusi Dinamis**: `API_BASE_URL` kini dikonfigurasi secara dinamis berbasis `window.location.hostname`. Jika aplikasi diakses dari HP via IP lokal laptop (misalnya `192.168.1.15:5173`), API secara otomatis diarahkan ke port backend di host tersebut (`http://192.168.1.15:8080`), sehingga fitur integrasi backend berjalan lancar di HP.

### 11.2 Penyelarasan RLS (Row Level Security) Supabase untuk Vercel Serverless
* **Akar Masalah**: PWA yang dideploy di Vercel online tidak dapat menjangkau port Express API lokal yang berada di belakang jaringan LAN. Ketika frontend melakukan fallback langsung ke Supabase Cloud (`supabase-js`), Supabase menolak operasi penulisan/penghapusan karena RLS aktif di database tanpa policy untuk role `anon`.
* **Solusi Kebijakan RLS**: Menerapkan SQL Migration untuk membuka policy `SELECT`, `INSERT`, `UPDATE`, dan `DELETE` secara eksplisit bagi role `anon` pada tabel:
  - `attendance_logs` (Pencatatan absensi langsung)
  - `employees` (Pendaftaran/pembaruan karyawan)
  - `master_descriptors` (Penyimpanan vektor wajah master)
* **Fallback 3-Tier Hapus Log**: Menambahkan mekanisme fallback penghapusan langsung ke Supabase Cloud di frontend jika Express API terdeteksi offline/unreachable saat diakses via internet Vercel.

### 11.3 Integrasi Limit Waktu API (Quick Timeout 3 Detik)
* **Akar Masalah**: Saat HP berada di luar jaringan lokal (misal menggunakan paket data 4G/5G), browser menunggu proses TCP Handshake ke IP Express lokal hingga 30+ detik sebelum melempar error, membuat UI tampak membeku (stuck) tanpa notifikasi sukses/gagal.
* **Solusi**: Memperkenalkan helper `fetchWithTimeout` dengan batasan **3 detik** di seluruh pemanggilan API lokal (`TabEmployeeManagement`, `TabFaceVerification`, dan `App.jsx`). Jika Express server tidak merespon dalam 3 detik, aplikasi langsung memotong jalur dan melakukan fallback ke Supabase Cloud secara instan.

### 11.4 Optimalisasi Kinerja Kamera & AI Registrasi
* **useNormalizedFaceMesh Integration**: Merefaktor total kamera pada pendaftaran karyawan (`TabEmployeeManagement.jsx`) agar menggunakan hook terstandarisasi `useNormalizedFaceMesh`. Proses ini memotong gambar ke aspect-ratio `4:3` secara independen, menghilangkan distorsi "gepeng" (squashed) pada titik hijau mesh wajah di layar HP, dan menambahkan efek cermin (`scaleX(-1)`) untuk pengalaman pengguna yang lebih baik.

### 11.5 Peta Jalan Mode Offline PWA (Fase Berikutnya)
* **Service Worker Asset Caching**: Mengonfigurasi `sw.js` untuk menyimpan berkas statis (HTML, JS, CSS) beserta bobot model AI dari CDN (`@vladmandic/human/models` seberat ~20MB) ke cache browser HP agar aplikasi dapat dibuka saat tidak ada koneksi sama sekali.
* **IndexedDB Sync Buffer**: Mematangkan alur antrean Dexie.js agar absensi luring (offline) disimpan secara aman dalam antrean, serta otomatis memicu sinkronisasi massal *(background sync)* ke database Supabase Cloud begitu perangkat mendeteksi koneksi internet kembali aktif.

---

## 12. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v2.5.0)

Pembaruan versi **v2.5.0** menandai penyelesaian migrasi arsitektur menjadi *Fully Serverless*, optimalisasi akurasi AI lintas perangkat secara matematis, serta penyempurnaan UI/UX:

### 12.1 Migrasi Arsitektur Fully Serverless & PWA Offline Sync
* **Penghapusan Server Lokal (Express.js)**: Aplikasi tidak lagi bergantung pada server backend lokal (`node server.js`). Seluruh komunikasi data (*read, write, delete*) dialihkan menggunakan *Supabase Client SDK* secara langsung dari frontend. Ini menjamin aplikasi dapat diakses 100% dari mana saja via internet tanpa memusingkan konfigurasi jaringan LAN.
* **PWA Offline Syncing (`syncEngine.js`)**: Diintegrasikan sepenuhnya dengan Dexie.js (IndexedDB lokal). Ketika perangkat dalam kondisi offline, absensi disimpan di *buffer* lokal. Begitu koneksi internet kembali, mesin sinkronisasi secara otomatis mengirim data tertunda ke Supabase Cloud.

### 12.2 Standarisasi Akurasi AI Lintas Perangkat (Tablet vs Laptop)
* **Penyatuan Konfigurasi AI (`aiConfig.js`)**: Pembuatan konfigurasi terpusat untuk pustaka `@vladmandic/human`.
* **FP16 WebGL Forcing**: Memaksa penggunaan presisi *16-bit floating point* (`WEBGL_FORCE_F16_TEXTURES: true`) di seluruh perangkat. Hal ini mengatasi bug "selisih persentase anjlok" yang sebelumnya terjadi saat wajah didaftarkan di laptop (presisi FP32) tetapi discan di tablet (presisi FP16). Vektor wajah 1024-dimensi kini memiliki standar nilai desimal yang identik secara universal.
* **Pembersihan Beban Ganda AI**: Pustaka usang `@vladmandic/face-api` (model lama dengan 128-dimensi) yang sebelumnya digunakan di fitur "Edit Foto Karyawan" telah dibuang seluruhnya. Ini mencegah tabrakan 2 AI raksasa di memori, yang sebelumnya menyebabkan *Memory Leak* (OOM) dan *lagging* kamera parah pada tablet berspesifikasi menengah ke bawah.

### 12.3 Phased Biometric Flow & UI Refinements
* **Alur Ekstraksi Biometrik Bertahap (Phased Flow)**: Model *Description* (penghasil vektor wajah) kini dimatikan secara *default* saat kamera menyala untuk menghemat *resource* GPU tablet secara drastis. Model pendeteksi ini baru dinyalakan secara dinamis (*on-the-fly*) hanya ketika fase uji keamanan Liveness (Kedip/Toleh) berhasil dilalui dan subjek stabil.
* **Perbaikan UI Halaman Login**: Mengganti logo aplikasi dengan AGRIFACE versi resmi dan merapikan tata letaknya (menghapus kompensasi margin negatif `marginBottom: '-60px'`) agar logo tidak lagi saling tumpang tindih (*overlap*) dengan teks "Welcome Admin!".

---

## 13. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v2.6.0)

Pemberitahuan pembaruan versi **v2.6.0** menandai peluncuran arsitektur *Hybrid API-First* yang tangguh, sistem pencegah verifikasi ganda, serta perapian antarmuka (UI) agar lebih bersih dan profesional:

### 13.1 Arsitektur Absensi Hybrid Server-First (3-Tier Submission Flow)
Untuk memenuhi aspek keamanan produksi, seluruh transaksi pencatatan absensi yang awalnya diproses dan ditulis langsung dari sisi klien didelegasikan ulang melalui server backend tepercaya:
* **Tier 1 (Server-Authoritative)**: Mengirimkan data absensi, koordinat GPS, dan descriptor wajah (`scan_descriptor`) melalui POST ke endpoint `/api/attendance/verify` untuk divalidasi dan dicatat oleh server.
* **Tier 2 (Direct Supabase Fallback)**: Jika server backend sibuk atau mengalami timeout (menggunakan batas *timeout* 3 detik), sistem klien secara otomatis memotong jalur untuk melakukan penulisan langsung ke Supabase Cloud.
* **Tier 3 (Local Dexie.js Offline Buffer)**: Jika perangkat dalam kondisi luring (offline) tanpa jaringan seluler sama sekali, data absensi akan langsung disimpan ke antrean IndexedDB lokal untuk disinkronisasikan nanti.

### 13.2 Auto-Submit Sekali & Deduplikasi Verifikasi
* **Deduplikasi Real-time**: Mengintegrasikan state pelacak `hasAutoSubmittedRef` pada kamera absensi. Ketika persentase kemiripan wajah mencapai **>= 80%** dan status liveness sukses, sistem akan langsung mengirim data absensi tepat satu kali.
* **Bypass Komputasi Lanjutan**: Proses perhitungan kesamaan wajah (Cosine Similarity) dan efek bunyi bip peringatan segera dikunci/diabaikan pada frame-frame kamera berikutnya untuk menghindari penulisan log ganda (*double attendance logs*) di database dan kebisingan suara peringatan berulang.

### 13.3 Penyederhanaan Antarmuka Kamera (Minimalist UI)
* **Pembersihan Telemetri Teknis**: Menghilangkan panel penampil persentase kecocokan wajah (*Match Rate Chip*), status pelacakan kedipan mata (*Anti-Spoofing & EAR Status Chips*), dan kartu pratinjau thumbnail wajah (*Base64 Image Preview Card*) dari bagian bawah kamera.
* **Evaluasi Latar Belakang Tetap Aktif**: Meskipun tidak lagi terlihat di layar (agar tampilan terlihat bersih, modern, dan minimalis), proses evaluasi keaktifan wajah dan ekstraksi Base64 tetap dijalankan di latar belakang demi menjaga aspek keamanan sistem absensi.

### 13.4 Penyempurnaan Teks Notifikasi (Brand-Generic Messaging)
* **Penghapusan Terminologi Teknis Internal**: Mengubah pesan notifikasi toast untuk menyembunyikan detail database internal agar tidak membingungkan pengguna umum:
  * Menghapus kata *"Supabase Cloud"* dan mengubahnya menjadi kata umum *"database"*.
  * Menghapus rincian backend seperti *"secara CASCADE (menghapus log & biometrik)"* pada notifikasi penghapusan karyawan.

---

## 14. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v2.7.0)

Pemberitahuan pembaruan versi **v2.7.0** menandai peluncuran pemrosesan Face Mesh yang *Device-Independent*, implementasi peredam getaran (One Euro Filter), penataan tipe data TypeScript (TS/TSX) yang ketat, dan integrasi tipe data editor:

### 14.1 Konversi Penuh ke TypeScript & Tipe Data Ketat (Strictly Typed)
* **Migrasi Hook Inti**: Mengonversi `useNormalizedFaceMesh` ke file TypeScript murni ([useNormalizedFaceMesh.ts](file:///d:/FACE%20VERIFICATION/src/hooks/useNormalizedFaceMesh.ts)) dengan interface yang jelas untuk:
  - `Point3D`: Model koordinat 3D.
  - `BoundingBox`: Kalkulasi geometri area wajah.
  - `NormalizedLandmark`: Koordinat lokal hasil normalisasi.
  - `FaceMeshOutput`: Kontrak keluaran data frame wajah.
* **Komponen Demo Pemindai**: Membuat komponen [FaceMeshScanner.tsx](file:///d:/FACE%20VERIFICATION/src/components/FaceMeshScanner.tsx) berbasis TSX untuk menyajikan interaksi langsung dengan hook pemrosesan wajah.

### 14.2 Normalisasi Koordinat Lokal Bebas Perangkat (Device-Independent)
* **Transformasi Geometri**: Mengubah koordinat piksel mentah (piksel canvas) menjadi koordinat relatif berbasis Bounding Box wajah dengan rentang $[-0.5 \text{ s/d } 0.5]$.
* **Origin di Pusat Wajah**: Menempatkan pusat bounding box sebagai titik koordinat origin $(0,0)$.
* **Skalabilitas Depth ($Z$)**: Menghitung nilai $Z$ secara proporsional terhadap lebar wajah, menjamin integritas data mesh wajah tetap konsisten meskipun wajah mendekat/menjauh dari lensa atau resolusi kamera berbeda.

### 14.3 Standardisasi Potongan Gambar (Center Crop 4:3)
* **Stabilisasi Aspek Rasio**: Mengintegrasikan perhitungan `computeCenterCrop` untuk memotong wilayah tengah video secara dinamis agar pas pada rasio 4:3 sebelum diproses, menghindari distorsi wajah melonjong/gepeng lintas perangkat.
* **Canvas Offscreen Standar**: Frame video terpotong digambar ke offscreen canvas berukuran tetap $640 \times 480$ sebelum dideteksi AI, menyamakan koordinat masukan lintas hardware.

### 14.4 Filter Adaptif Peredam Jitter (One Euro Filter & EMA)
* **One Euro Filter**: Menerapkan filter adaptif low-pass yang menyesuaikan frekuensi cutoff berdasarkan kecepatan gerak. Ini menghilangkan getaran getas (jitter) saat wajah statis tanpa menimbulkan efek lag (latency) ketika wajah berputar cepat.
* **EMA Filter & Fallback**: Menyediakan opsi filter EMA konvensional dan mode tanpa filter bagi pengguna yang menginginkan pemrosesan raw koordinat mentah.

### 14.5 Konfigurasi Tipe Data & Build System
* **tsconfig.json**: Menambahkan berkas konfigurasi [tsconfig.json](file:///d:/FACE%20VERIFICATION/tsconfig.json) dengan opsi `"jsx": "react-jsx"` dan `"allowJs": true` agar editor mengenali sintaks JSX/React dan file JS/TS secara hibrida.
* **Vite DevDependencies**: Mendaftarkan tipe data `@types/react` dan `@types/react-dom` di `package.json` untuk menghilangkan seluruh peringatan error deklarasi tipe di tingkat IDE.

---

## 15. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v2.8.0)

Pemberitahuan pembaruan versi **v2.8.0** menandai penyelesaian masalah caching pada PWA Service Worker, sinkronisasi state relasi log pasca-penghapusan karyawan, navigasi tanggal langkah demi langkah pada dashboard utama, dan pembuatan sidebar mini-ikon responsif (desktop & tablet) yang persisten:

### 15.1 Bypass Caching Service Worker untuk Endpoint Database Supabase
* **Pencegahan Stale Data**: Menambahkan aturan bypass cache pada file [sw.js](file:///d:/FACE%20VERIFICATION/public/sw.js) untuk semua permintaan dengan hostname `*.supabase.co`.
* **Penyelesaian Bug "2 Kali Klik"**: Menghilangkan efek tertundanya pembaruan frontend yang disebabkan oleh strategi caching *Stale-While-Revalidate* milik Service Worker. Data karyawan dan log absensi yang telah dihapus atau diperbarui kini langsung direfleksikan seketika pada pemanggilan pertama.

### 15.2 Sinkronisasi Log Absensi pada Penghapusan Karyawan
* **Sinkronisasi cascading frontend**: Meneruskan fungsi `refreshLogs()` dari [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx) ke [DaftarKaryawanPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/DaftarKaryawanPage.jsx).
* **Real-time State Update**: Secara otomatis memicu penyegaran state logs absensi sesaat setelah penghapusan karyawan berhasil dilakukan di Supabase, memastikan data log terkait (yang terpengaruh aturan `ON DELETE CASCADE` di database cloud) langsung hilang dari UI absensi tanpa perlu melakukan reload halaman browser.

### 15.3 Navigasi Tanggal Langkah Demi Langkah (Date Stepping) pada Dashboard
* **Tombol Navigasi Cepat**: Menambahkan tombol panah navigasi `<` (Hari Sebelumnya) dan `>` (Hari Berikutnya) yang mendampingi pemilih tanggal custom pada file [DashboardPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/DashboardPage.jsx).
* **Pembaruan Metrik Real-time**: Mengatur pemutakhiran state tanggal secara berurutan hari demi hari yang langsung memengaruhi filter log absensi, KPI kepegawaian harian, diagram lingkaran komposisi, dan tabel riwayat transaksi secara real-time.

### 15.4 Collapsible Mini-Icon Sidebar Eksklusif Desktop & Tablet (Layar >= 769px)
* **Mode Ramping 72px**: Mengubah visualisasi sidebar saat ditutup agar menyusut menjadi lebar `72px` (hanya menampilkan ikon menu utama dan logo fingerprint) secara otomatis di desktop, laptop, dan tablet (lebar layar $\ge 769$px) untuk efisiensi ruang kerja.
* **Drawer Overlay untuk Mobile**: Pada ponsel pintar/HP (lebar layar $\le 768$px), sidebar tetap menggunakan visualisasi laci geser asli yang bersembunyi penuh saat ditutup (lebar `0px`, tergeser `-240px` ke luar layar) dan mengambang di atas konten saat dibuka guna memaksimalkan lebar pandang konten utama mobile.

### 15.5 Persistensi Status Sidebar melalui LocalStorage
* **Pencegahan Reset State**: Membaca dan menulis status lipatan sidebar (`sidebarOpen`) ke `localStorage` pada file [DashboardLayout.jsx](file:///d:/FACE%20VERIFICATION/src/layouts/DashboardLayout.jsx).
* **Router Remount Immunity**: Menjamin sidebar tetap mempertahankan bentuk terakhirnya (lebar penuh `240px` atau ramping `72px`) saat pengguna berpindah menu navigasi dari/ke halaman absensi umum yang memicu penggantian (*remounting*) komponen layout dari React Router.

### 15.6 Responsive Horizontal Scrollable Tables pada Tampilan Mobile
* **Wrapper Table-Container**: Membungkus tabel data master karyawan pada [DaftarKaryawanPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/DaftarKaryawanPage.jsx) ke dalam kelas kontainer `.table-container` (seragam dengan tabel riwayat log absensi).
* **Pencegahan Penyusutan Kolom (Squeeze-Free)**: Menambahkan aturan CSS global untuk mengunci lebar tabel minimal ke `850px` dan menonaktifkan pemenggalan baris teks (`white-space: nowrap`) pada kolom sel data.
* **Scroll Horizontal Mandiri**: Mengizinkan pengguna HP untuk menggeser (*swipe*) tabel absensi dan tabel data master karyawan secara horizontal untuk kenyamanan membaca seluruh detail data, tanpa mengacaukan keselarasan responsif halaman utama.

### 15.7 Integrasi CapacitorJS untuk Kompilasi Native Android APK
* **Penyediaan Native Shell**: Menambahkan dependency `@capacitor/core` dan `@capacitor/cli` untuk membangun cangkang native mobile di sekitar aplikasi web React.
* **Konfigurasi Aset Web**: Membuat berkas konfigurasi [capacitor.config.ts](file:///d:/FACE%20VERIFICATION/capacitor.config.ts) yang mengarahkan pembungkus native ke folder keluaran Vite `dist/`.
* **Proyek Android Studio**: Menambahkan platform `@capacitor/android` ke folder `android/` agar dapat di-compile langsung di Android Studio menjadi file `.apk`.
* **Registrasi Izin Hardware & Storage**: Memperbarui file [AndroidManifest.xml](file:///d:/FACE%20VERIFICATION/android/app/src/main/AndroidManifest.xml) untuk mendaftarkan izin Kamera (`CAMERA`), Geolokasi GPS Akurat (`ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`), dan Penyimpanan File Internal (`WRITE_EXTERNAL_STORAGE`, `READ_EXTERNAL_STORAGE`).

### 15.8 Lokalisasi Model AI Biometrik Wajah & Arsitektur Human Singleton
* **Penyimpanan Lokal Model**: Memindahkan model-model wajah biometrik (`blazeface`, `facemesh`, `iris`, `faceres`, `models.json`) dari pustaka `@vladmandic/human` ke dalam folder lokal [public/models/](file:///d:/FACE%20VERIFICATION/public/models) agar terkemas secara fisik di dalam berkas APK.
* **Arsitektur Human Singleton**: Membuat berkas [humanSingleton.js](file:///d:/FACE%20VERIFICATION/src/humanSingleton.js) untuk mengelola satu instansiasi `Human` tunggal di seluruh aplikasi guna mencegah konflik memori GPU WebGL TensorFlow.js.
* **Strategi Pemuatan 3-Tier Fallback**: Mengimplementasikan `loadHumanWithFallback()` yang memuat model secara hierarkis: Origin Domain Lokal (`window.location.origin + '/models'`) -> Jalur Relatif (`./models`) -> Cloud CDN JSDelivr (`https://cdn.jsdelivr.net/...`).
* **Konfigurasi HTTPS WebView**: Mengatur `server.androidScheme: 'https'` pada [capacitor.config.ts](file:///d:/FACE%20VERIFICATION/capacitor.config.ts) untuk menjamin konteks aman (*Secure Context*) sehingga API kamera `navigator.mediaDevices.getUserMedia` diizinkan aktif di Android WebView.

### 15.9 Permintaan Izin Hardware OS Native pada Startup Aplikasi
* **Registrasi OS Native Runtime Permission**: Meng-override metode `onCreate` pada [MainActivity.java](file:///d:/FACE%20VERIFICATION/android/app/src/main/java/com/agriface/app/MainActivity.java) untuk meminta izin Kamera (`CAMERA`) dan Geolokasi GPS (`ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`) secara langsung ke sistem operasi Android saat pertama kali aplikasi dibuka.
* **Pencegahan Pemblokiran Kamera WebView**: Menjamin browser internal Android WebView mendapatkan wewenang native dari OS sebelum halaman absensi memanggil `getUserMedia()`, sehingga pemindai wajah terbuka lancar tanpa terhambat status *Permission Denied*.

### 15.10 Pembekuan Layout Sidebar (*Sticky*) & Relokasi Tombol Logout
* **Pembekuan Kolom Navigasi (Sticky Sidebar)**: Menambahkan aturan CSS `position: sticky; top: 0; height: 100vh; overflow-y: auto;` pada [index.css](file:///d:/FACE%20VERIFICATION/src/index.css) untuk `.app-sidebar`. Kolom ikon navigasi kini tetap diam (*freeze*) melayang di layar saat konten utama di-scroll ke bawah.
* **Relokasi Tombol Logout**: Memindahkan tombol **Logout** pada [Sidebar.jsx](file:///d:/FACE%20VERIFICATION/src/components/Sidebar.jsx) dari bagian footer ke dalam kelompok navigasi utama tepat di bawah menu *Log Absensi*.
* **Penanganan Logout & Proteksi Rute Kunci**: Mengintegrasikan `logout()` di [AuthContext.jsx](file:///d:/FACE%20VERIFICATION/src/context/AuthContext.jsx) dengan [ProtectedRoute.jsx](file:///d:/FACE%20VERIFICATION/src/components/ProtectedRoute.jsx) untuk menjamin pembersihan sesi penuh (`localStorage.removeItem('logged_in_admin')`) dan pengarahan paksa instan dari halaman mana pun ke [LoginPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/LoginPage.jsx).

---

## 16. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v2.9.0)

Pemberitahuan pembaruan versi **v2.9.0** menandai peluncuran dukungan ekspor data offline-first langsung dari APK, standardisasi format cetak PDF lanskap, perbaikan status biometrik dinamis, pembersihan icon yang rusak saat offline, serta penghapusan otomatisasi database seed di backend:

### 16.1 Ekspor Data Offline-First Native Share Dialog dari APK (Android WebView Bypass)
* **Bypass Hambatan WebView**: Mengingat Android WebView secara bawaan memblokir pengunduhan data Blob memori (`blob:`) lokal dan tidak mendukung dialog cetak `window.print()`, sistem kini menggunakan integrasi plugin `@capacitor/filesystem` dan `@capacitor/share`.
* **Ekspor Excel Native Share**: Menghasilkan dokumen Excel (.xls) secara offline, menyimpannya di folder cache perangkat (`Directory.Cache`), lalu memicu *Native Android Share Dialog*. Pengguna dapat mengirim file via WhatsApp, Email, atau menyimpannya di Drive/Lokal HP.
* **Cetak PDF Hibrida via Chrome**: Menghasilkan dokumen cetak mandiri (.html), menyimpannya di cache lokal, lalu membagikannya ke browser Google Chrome HP pengguna. Chrome HP secara otomatis akan langsung memicu dialog pencetakan bawaan sistem OS Android saat halaman dimuat.

### 16.2 Standardisasi Tata Letak Print PDF & Penyaringan Kolom
* **Orientasi Lanskap & Fit-to-Paper**: Mengonfigurasi media `@media print` dengan aturan `@page { size: landscape; margin: 10mm; }` agar orientasi cetak default adalah tidur (lanskap).
* **Penghilangan Limit Scroll Kontainer**: Menonaktifkan batas tinggi scroll `.table-container { max-height: none !important; overflow: visible !important; height: auto !important; }` pada media cetak agar baris data tercetak utuh dari awal sampai akhir tanpa terpotong.
* **Filter Kolom Cetak yang Ditargetkan**:
  - Tabel Karyawan: Hanya mencetak kolom **NIK**, **Nama**, **Afdeling**, **Nama Kebun**, **Jabatan**, **Status TK**, dan **Status Pernikahan** (menyembunyikan kolom Biometrik dan Aksi).
  - Tabel Riwayat Log: Hanya mencetak kolom **Tanggal**, **Check In**, **NIK**, **Nama Karyawan**, **Afdeling**, **Check Out**, **Durasi**, **Keterangan**, dan **Lokasi** (menyembunyikan kolom Aksi).

### 16.3 Peningkatan Tampilan Web UI (Arial Font & Sizing)
* **Arial 14px untuk Web UI**: Mengunci seluruh tampilan tabel data master dan log absensi di browser web dengan font **Arial** ukuran **14px** agar selaras secara responsif di monitor desktop maupun layar tablet.
* **Consolas 12pt untuk Hasil Ekspor**: Font **Consolas** berukuran **12pt** dengan penyejajaran horizontal/vertikal tengah, judul tebal (bold), dan warna latar header hijau tosca (`#46bdc6`) khusus diterapkan pada hasil ekspor file Excel saja.

### 16.4 Resolusi Status Biometrik & Perbaikan Ikon Offline
* **Cross-Reference Biometrics**: Memperbarui pemanggilan data karyawan di `App.jsx` agar secara dinamis mencocokkan ID karyawan dengan data `master_descriptors`. Status kolom biometrik karyawan terjamin selalu akurat ("Siap" atau "Belum") meskipun terdapat record null pada database Supabase.
* **Offline Lucide Clock Icon**: Mengganti pemanggilan icon jam durasi kerja berbasis FontAwesome (`fa-clock`) yang sebelumnya rusak menampilkan kotak kosong `[ ]` saat offline, dengan menggunakan icon `Clock` dari pustaka lokal `lucide-react` agar dapat dimuat sempurna tanpa koneksi internet.

### 16.5 Penonaktifkan Pengisian Database Otomatis (Anti-Seed Backend)
* **User-Authorized CRUD**: Menghapus seluruh logika fungsi `seedInitialData()` di backend server (`server.js`). Tindakan ini mencegah sistem memasukkan kembali data contoh karyawan secara sepihak saat database dinilai kosong (0 records), menjamin daftar karyawan bersih sepenuhnya dikelola oleh pengguna admin.

---

## 17. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v3.0.0)

Pemberitahuan pembaruan versi **v3.0.0** menandai peluncuran dukungan penyimpanan native SQLite lokal di APK, auto-sync terintegrasi sensor koneksi native perangkat, mekanisme toleransi kegagalan GPS multi-stage, serta penyederhanaan notifikasi status mode online/offline:

### 17.1 Integrasi Penyimpanan Lokal Native SQLite untuk Android APK (Hybrid Storage Layer)
* **Bypass Batasan IndexedDB**: Untuk meningkatkan keandalan penyimpanan offline-first pada APK Android, aplikasi kini menggunakan plugin `@capacitor-community/sqlite` yang menggantikan Dexie.js (IndexedDB) di perangkat mobile.
* **Perantara Hibrida Cerdas (db.js)**: Menyediakan abstraction layer dinamis yang mendeteksi platform runtime via `Capacitor.isNativePlatform()`. Jika mendeteksi lingkungan browser web (localhost/PWA), sistem otomatis tetap memakai Dexie.js, sedangkan jika mendeteksi APK Android, sistem otomatis beralih ke SQLite database `AgriFaceLocalDB`.
* **Inisialisasi Tabel SQLite**: Membuat tabel lokal di startup aplikasi (`App.jsx` memicu `initSQLite()`) untuk:
  - `local_employees`: Menyimpan cache data karyawan.
  - `local_master_descriptors`: Menyimpan cache data biometrik wajah (vektor 1024-dimensi dan nilai GFV).
  - `local_attendance_queue`: Menyimpan antrean log kehadiran karyawan yang diambil saat offline.
* **Emulasi Interface Dexie**: Lapisan pembungkus `db` mengemulasikan metode `.toArray()`, `.clear()`, dan `.bulkPut()` pada tabel SQLite agar seluruh komponen UI absensi dapat memproses data lokal tanpa perlu merombak baris kode internal komponen.

### 17.2 Integrasi Sensor Koneksi Native Capacitor Network & Auto-Sync
* **Deteksi Jaringan Native**: Mengganti event listener online/offline bawaan browser web dengan `@capacitor/network` ketika berjalan sebagai APK native. Sistem kini menggunakan `Network.addListener('networkStatusChange', ...)` untuk memicu sinkronisasi secara instan saat koneksi seluler/Wi-Fi terdeteksi kembali.
* **Metode Pengecekan Status Dinamis**: Auto-sync engine di [syncEngine.js](file:///d:/FACE%20VERIFICATION/src/syncEngine.js) menggunakan method `Network.getStatus()` untuk memvalidasi status koneksi sebelum menjalankan sinkronisasi latar belakang log absensi offline ke cloud Supabase.

### 17.3 Mekanisme Toleransi Kegagalan GPS Multi-Stage & Bypass Lokasi Kantor
* **Pengulangan Deteksi Multi-Stage**: Untuk mengatasi kendala sensor GPS perangkat mobile saat offline di perkebunan atau saat diuji coba di dalam ruangan (*indoors*), modul GPS di [AbsensiPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/AbsensiPage.jsx) dan [TabFaceVerification.jsx](file:///d:/FACE%20VERIFICATION/src/components/TabFaceVerification.jsx) dimodifikasi:
  - Tahap 1: Meminta koordinat GPS akurasi tinggi (High Accuracy) dengan timeout longgar 10 detik dan memanfaatkan data cache posisi selama 30 detik (`maximumAge: 30000`).
  - Tahap 2 (Fallback): Jika Tahap 1 gagal karena timeout/sinyal terhalang, sistem mengulangi permintaan dalam mode akurasi standar (Low Accuracy) dengan timeout 10 detik dan cache 5 menit.
  - Tahap 3 (Bypass Kantor): Jika kedua tahap di atas tetap gagal/timeout (misal saat dites di dalam rumah/ruangan tertutup tanpa sinyal satelit), sistem otomatis mem-bypass pembatasan koordinat dengan menggunakan koordinat kantor default (`OFFICE_LAT`, `OFFICE_LON`) disertai dengan peringatan Toast warna kuning. Hal ini mencegah aplikasi terblokir atau macet saat melakukan absensi.

### 17.4 Penyederhanaan Notifikasi Mode Online & Mode Offline (Shadcn Toast Style)
* **Status Ringkas dan Bersih**: Menyederhanakan pesan notifikasi status koneksi yang dipicu saat membuka aplikasi maupun saat terjadi perubahan jaringan menjadi lebih bersih dan terstandardisasi:
  - **Mode Online**: Menampilkan Toast hijau (`success`) berbunyi *"Mode Online - Aplikasi terhubung ke internet."*
  - **Mode Offline**: Menampilkan Toast kuning (`warning`) berbunyi *"Mode Offline - Aplikasi berjalan luring (offline)."*

---

## 18. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v3.1.0)

Pembaruan versi **v3.1.0** menandai implementasi sistem keamanan biometrik *one-face-per-employee*, validasi duplikasi wajah berbasis Cosine Similarity yang beroperasi penuh secara luring (offline), pengayaan tampilan data Afdeling/Kebun di seluruh modul, kalkulasi durasi kerja otomatis, serta penguatan otentikasi admin berbasis PostgreSQL.

### 18.1 Constraint Satu Wajah Per Karyawan (One-Face-Per-Employee Biometric Lock)
* **Kebutuhan Bisnis**: Sebelumnya sistem tidak mencegah satu identitas biometrik (wajah) digunakan untuk mendaftarkan lebih dari satu karyawan. Hal ini membuka celah *identity fraud* pada absensi.
* **Validasi Real-Time di Kamera**: Saat kamera registrasi aktif, setiap frame wajah yang terdeteksi langsung dibandingkan secara asinkron terhadap seluruh vektor master yang ada di storage lokal menggunakan fungsi `cosineSimilarity()`. Jika nilai similaritas $\ge 0.85$, status kamera berubah merah dengan pesan:
  `⚠️ WAJAH SUDAH TERDAFTAR: [Nama Karyawan] (96.2%)`
* **Double-Check Saat Submit**: Validasi yang sama dijalankan ulang pada saat tombol *Simpan* ditekan sebagai lapisan keamanan kedua, memblokir pengiriman data jika wajah terdeteksi duplikasi.
* **Self-Exclusion pada Edit Biometrik**: Di form *edit biometrik* karyawan, karyawan yang sedang diedit dikecualikan dari pemeriksaan duplikasi (*self-exclusion*) agar admin tetap bisa memperbarui wajah karyawan yang sama.

### 18.2 Cosine Similarity Engine Berbasis Cache Lokal (Offline-Capable)
* **Fungsi Utilitas `cosineSimilarity(a, b)`**: Ditambahkan sebagai fungsi ekspor di [db.js](file:///d:/FACE%20VERIFICATION/src/db.js). Menghitung kesamaan vektor dengan formula:
  $$\text{CosineSimilarity}(A, B) = \frac{A \cdot B}{\|A\| \cdot \|B\|}$$
  dengan nilai rentang $[-1, 1]$; semakin mendekati $1.0$ berarti semakin identik.
* **Fungsi `getAllMasterVectors()`**: Ditambahkan di [db.js](file:///d:/FACE%20VERIFICATION/src/db.js) sebagai abstraction layer yang secara otomatis mengambil **semua vektor master** dari:
  - **SQLite** (`local_master_descriptors JOIN local_employees`) — saat berjalan sebagai APK native.
  - **IndexedDB / Dexie.js** (`user_master` table) — saat berjalan di browser web.
* **Fungsi `sqliteGetAllMasterVectors()`**: Ditambahkan di [sqliteService.ts](file:///d:/FACE%20VERIFICATION/src/services/sqliteService.ts) sebagai implementasi query native SQLite yang mengembalikan semua pasang `employee_id, name, nik, descriptor_json` dari tabel lokal.
* **Threshold**: Nilai ambang batas similaritas yang digunakan adalah $\ge 0.85$ (85%).

### 18.3 Mapping Kolom Afdeling/Kebun pada Seluruh Modul
* **Dashboard Log Absensi ([DashboardPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/DashboardPage.jsx))**: Tabel riwayat log absensi di dashboard kini menampilkan kolom **Afdeling** dan **Nama Kebun** yang dipetakan secara dinamis dari daftar master karyawan (`employees` prop) menggunakan `employee_id` atau `nik` sebagai kunci pencarian.
* **Halaman Log Absensi ([LogsPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/LogsPage.jsx))**: Tabel riwayat absensi biometrik menampilkan kolom **Afdeling** yang dikonsumsi dari data karyawan master, berlaku baik dalam **Mode Online** maupun **Mode Offline**.

### 18.4 Kalkulasi Durasi Kerja Otomatis
* **Rumus Durasi**: Kolom **Durasi** pada tabel [LogsPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/LogsPage.jsx) kini dihitung secara otomatis dengan rumus:
  $$\text{Durasi} = \text{Waktu Check-Out} - \text{Waktu Check-In}$$
  dan ditampilkan dalam format intuitif **`Xj Ym`** (contoh: `8j 15m`).
* **Berlaku untuk Semua Mode**: Kalkulasi berjalan secara identik untuk log yang berasal dari mode online (Supabase) maupun mode offline (SQLite queue).

### 18.5 Perbaikan Ikon Mode Offline (Lucide CloudOff)
* **Masalah**: Ikon mode offline yang sebelumnya menggunakan FontAwesome (`fa-wifi-slash`) tidak dapat dimuat saat perangkat offline karena membutuhkan CDN eksternal, menampilkan kotak kosong `[ ]`.
* **Solusi**: Mengganti ikon dengan `CloudOff` dari pustaka `lucide-react` yang di-bundle langsung di dalam APK, sehingga selalu tersedia tanpa koneksi internet.

### 18.6 Penguatan Otentikasi Admin via PostgreSQL (`admin_auth`)
* **Tabel Baru `admin_auth`**: Membuat tabel otentikasi admin di Supabase dengan kolom `username`, `password_hash` (bcrypt via `pgcrypto`), `name`, `is_active`, dan `created_at`.
* **Fungsi RPC `verify_admin_login`**: Menambahkan/memperbarui fungsi RPC di PostgreSQL untuk memverifikasi kredensial admin secara aman menggunakan `crypt(p_password, password_hash)` dari ekstensi `pgcrypto` tanpa mengekspos hash ke sisi klien.
* **Keamanan Data**: Tabel `admin_auth` dilindungi oleh Row Level Security (RLS) dengan policy yang memblokir akses langsung dari klien — seluruh otentikasi hanya dapat dilakukan melalui fungsi RPC `SECURITY DEFINER`.
* **Fallback Offline**: Jika Supabase tidak dapat dijangkau (offline), sistem secara otomatis melakukan validasi kredensial lokal (`username: admin`, `password: tanaman`) untuk menjamin akses dashboard tetap tersedia saat jaringan terputus.

### 18.7 Ringkasan Perubahan File

| File | Jenis | Deskripsi Perubahan |
| :--- | :--- | :--- |
| [sqliteService.ts](file:///d:/FACE%20VERIFICATION/src/services/sqliteService.ts) | ➕ Penambahan / 🔧 Upgrade | Tabel `local_attendance_logs` untuk riwayat log lokal permanen; migrasi skema tabel; fungsi CRUD log SQLite; dan `sqliteQueueOfflineAttendance` dengan `afdeling` |
| [db.js](file:///d:/FACE%20VERIFICATION/src/db.js) | ➕ Penambahan / 🔧 Upgrade | Schema v4 Dexie dengan store `attendance_logs`; pembungkusan CRUD log multi-platform (Dexie & SQLite) |
| [syncEngine.js](file:///d:/FACE%20VERIFICATION/src/syncEngine.js) | 🔧 Upgrade | Rekonsiliasi sinkronisasi dengan menghapus ID berprefix `'offline_'` dan menyisipkan data cloud final, meniadakan 'afdeling' dari upload cloud |
| [TabFaceVerification.jsx](file:///d:/FACE%20VERIFICATION/src/components/TabFaceVerification.jsx) | 🔧 Upgrade | Pengecekan status absensi harian dari tabel `attendance_logs` lokal; penyimpanan instan (dual-write) log online/offline secara lokal |
| [TabAttendanceLogs.jsx](file:///d:/FACE%20VERIFICATION/src/components/TabAttendanceLogs.jsx) | 🔧 Upgrade | Aksi hapus & edit tunggal yang menyinkronkan data di database lokal maupun cloud; pencarian dinamis berdasarkan kolom Afdeling & Department |
| [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx) | 🔧 Upgrade | Mengunduh log dari Supabase, menyinkronkan ke cache lokal, dan memuat riwayat log gabungan secara offline langsung dari database lokal |
| [DaftarKaryawanPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/DaftarKaryawanPage.jsx) | 🔧 Upgrade | Pembersihan cache saat hapus karyawan; pencocokan duplikasi biometrik dengan self-exclusion |
| [DashboardPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/DashboardPage.jsx) | 🔧 Upgrade | Pemetaan afdeling dari master karyawan untuk data statistik log absensi harian dashboard |
| [humanSingleton.js](file:///d:/FACE%20VERIFICATION/src/humanSingleton.js) | 🔧 Upgrade | Konfigurasi `WEBGL_FORCE_F16_TEXTURES: true` dan pinning CDN model ke v3.3.6 |
| Supabase SQL | ➕ Penambahan | Tabel `admin_auth`, RPC `verify_admin_login`, pgcrypto, dan replikasi pub-sub |

### 18.8 Sinkronisasi Data Real-Time Antar-Perangkat (Supabase Realtime Sync)
* **Kebutuhan Konsistensi**: Saat admin melakukan perubahan data (seperti menghapus atau menambah karyawan) di Laptop (Web App), perangkat tablet/HP (APK) tidak mengetahui perubahan tersebut karena data ter-cache di local SQLite/IndexedDB.
* **Solusi Channel Publik**: Memanfaatkan fitur **Supabase Realtime Replication**. Aplikasi di HP/Tablet berlangganan ke saluran `agriface-realtime-sync` berbasis WebSocket.
* **Auto-Refresh**: Begitu terjadi perubahan data (`INSERT`, `UPDATE`, `DELETE`) di database cloud Supabase, event push otomatis memicu fungsi `fetchEmployees()` dan `fetchLogs()` secara instan pada semua perangkat yang aktif secara online.
* **Polling Fallback**: Jika WebSocket terblokir oleh jaringan/firewall, polling latar belakang konvensional setiap **30 detik** diaktifkan sebagai backup.

### 18.9 Standardisasi Presisi WebGL FP16 Lintas Perangkat
* **Akar Masalah Mismatch**: Laptop/PC desktop memproses perhitungan neural network di WebGL menggunakan presisi desimal 32-bit (FP32), sementara mobile WebView pada Android memprosesnya dalam presisi 16-bit (FP16) karena arsitektur GPU mobile. Perbedaan presisi bit ini menyebabkan vektor descriptor 1024-dimensi bergeser nilainya sehingga didapatkan persentase kemiripan sangat rendah/0%.
* **Penyelesaian**: Mengintegrasikan flag `WEBGL_FORCE_F16_TEXTURES: true` ke dalam inisialisasi instance Human singleton. Laptop kini dipaksa memproses ekstraksi wajah menggunakan format FP16 yang identik dengan HP/Tablet, memastikan kompatibilitas pendaftaran biometrik lintas hardware.

### 18.10 Pembersihan Cache Biometrik Lokal Saat Hapus Karyawan
* **Masalah Sisa Cache (Stale Cache Bug)**: Saat karyawan dihapus, database pusat (Supabase) berhasil terhapus, tetapi data biometrik master lama tetap tersimpan di dalam IndexedDB (Web) atau SQLite (APK) lokal. Akibatnya, saat admin mencoba mendaftarkan wajah yang sama lagi, sistem memblokir pendaftaran dengan peringatan "Wajah sudah terdaftar" karena cache biometrik lokal tidak disinkronisasikan.
* **Solusi Pembersihan Hapus**:
  * Menambahkan fungsi `deleteLocalEmployee(employeeId)` di [db.js](file:///d:/FACE%20VERIFICATION/src/db.js).
  * Di browser Web, fungsi ini menghapus baris terkait di tabel IndexedDB `user_master` menggunakan Dexie.js.
  * Di APK Native, fungsi ini memanggil `sqliteDeleteEmployeeBiometrics()` di [sqliteService.ts](file:///d:/FACE%20VERIFICATION/src/services/sqliteService.ts) untuk mengeksekusi `DELETE` dari tabel lokal `local_master_descriptors` dan `local_employees`.
  * Integrasi dipicu langsung di dalam fungsi hapus karyawan pada [DaftarKaryawanPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/DaftarKaryawanPage.jsx).

### 18.11 Pinning Versi Model CDN ke v3.3.6
* **Masalah Silent Update CDN**: Pemuatan fallback dari CDN jsdelivr tanpa versi tetap (menggunakan `@latest` atau `@1.x`) berpotensi mengunduh versi model `faceres` yang lebih baru atau berbeda secara tidak sengaja di kemudian hari. Hal ini merusak validitas pencocokan vektor.
* **Solusi Versi Pinned**: Mengunci jalur CDN secara eksplisit di [humanSingleton.js](file:///d:/FACE%20VERIFICATION/src/humanSingleton.js) ke `https://cdn.jsdelivr.net/npm/@vladmandic/human@3.3.6/models` untuk memastikan versi model neural network yang diunduh selalu konsisten 100% di semua device.

### 18.12 Enforce Absensi Maksimal Sekali Sehari (Check-In & Check-Out Limit)
* **Kebutuhan Bisnis**: Satu karyawan hanya diizinkan melakukan **Check-In tepat 1x** dan **Check-Out tepat 1x** dalam satu hari untuk menghindari redundansi atau spam pencatatan kehadiran ganda.
* **Mekanisme Validasi & UI**:
  * Ketika `fetchAttendanceStatus` mendeteksi bahwa karyawan yang dipilih memiliki log `CHECK_IN` dan `CHECK_OUT` valid untuk hari ini (baik di Supabase online maupun Dexie offline queue), tombol kehadiran diubah menjadi disabled dengan tulisan: `🔒 Absensi Selesai Hari Ini`.
  * Pada kamera face-matching, jika absensi harian terdeteksi sudah lengkap, sistem akan langsung menonaktifkan proses facial matching dan menghentikan pengajuan otomatis (*auto-submit*), serta menampilkan pesan overlay: `✓ Absensi Hari Ini Sudah Lengkap`.
* **Pengecekan Dinamis (Exception Bypass)**: Aturan pembatasan sekali sehari ini dikecualikan secara dinamis jika admin menghapus salah satu log absensi (baik check-in maupun check-out) milik karyawan tersebut dari [TabAttendanceLogs.jsx](file:///d:/FACE%20VERIFICATION/src/components/TabAttendanceLogs.jsx). Begitu log dihapus, status absensi karyawan otomatis di-reset sehingga mereka dapat melakukan scan/absen kembali pada hari yang sama.

### 18.13 Pembersihan Log Offline secara Lokal (IndexedDB & SQLite)
* **Masalah Deletion Mismatch**: Saat menghapus catatan absensi dari logs page, sistem sebelumnya hanya menghapusnya dari database online Supabase. Catatan absensi offline gantung yang masih berada di antrean antarmuka lokal (`offline_` prefix) tidak terhapus dari SQLite (APK) maupun IndexedDB (Web), menyebabkan data tersebut tetap tampil secara redundan (*dual data*).
* **Penyelesaian**:
  * Menambahkan fungsi `delete` pada objek `attendance_sync_queue` di [db.js](file:///d:/FACE%20VERIFICATION/src/db.js).
  * Pada form log absensi [TabAttendanceLogs.jsx](file:///d:/FACE%20VERIFICATION/src/components/TabAttendanceLogs.jsx), proses `handleDeleteGroup` kini memisahkan ID log yang akan dihapus. Log dengan prefix `offline_` akan didelete secara lokal dari SQLite (`local_attendance_queue` via `sqliteRemoveSyncedLogs`) maupun IndexedDB (`attendance_sync_queue`), sedangkan log online dihapus dari Supabase.
  * Memperbaiki logika deduplikasi merge logs pada [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx) dengan memeriksa signature offline log terhadap `onlineSignatures` *sebelum* mendaftarkannya ke dalam set `seen` untuk mencegah tersembunyinya log online akibat tabrakan kunci signature.

### 18.14 Cadangan Audit Log Offline ke Penyimpanan Publik Dokumen (Public Storage Backup)
* **Kebutuhan Keamanan Ekstra**: Pada platform mobile/APK, jika pengguna melakukan pembersihan penuh data aplikasi (*Clear Storage* / *Clear Data*), database SQLite internal `/data/data/...` akan terhapus bersih oleh sistem operasi Android. Hal ini berisiko melenyapkan log kehadiran offline yang belum sempat terunggah ke server cloud.
* **Solusi Pencatatan Eksternal (Write-Ahead Logging Backup)**:
  * Mengintegrasikan modul `@capacitor/filesystem` untuk menulis salinan cadangan (*dual-write*) ke folder **Documents** publik perangkat (`Directory.Documents`).
  * File teks bernama `AgriFace_Offline_Backup.txt` dibuat dan diisi secara kumulatif (*append*) setiap kali:
    * Log absensi offline baru didaftarkan (`queueOfflineAttendance`).
    * Log absensi offline berhasil diunggah/sinkronisasi ke cloud database (`syncPendingAttendanceLogs`).
    * Log absensi offline dihapus secara lokal oleh admin (`delete` queue).
  * File cadangan audit log ini tersimpan di luar sandbox aplikasi (di folder `Documents` eksternal). Sehingga data log offline aman 100% dan **tidak akan terhapus** meskipun aplikasi di-uninstall, di-clear cache, atau di-clear data. Pengguna/admin dapat membuka File Manager perangkat kapan saja untuk membaca file log audit tersebut.

### 18.15 Penyimpanan Permanen Riwayat Log Lokal (Dual-Write SQLite & IndexedDB)
* **Masalah Ketergantungan Internet**: Halaman `LogsPage.jsx` sebelumnya memuat data riwayat absensi secara dinamis langsung dari Supabase Cloud. Saat perangkat tablet dijalankan tanpa internet (offline), halaman logs tidak dapat menampilkan riwayat absensi lama yang pernah dicatat, membuat pemantauan kehadiran di lapangan menjadi tidak mungkin.
* **Solusi Arsitektur Dual-Write**:
  * Membuat tabel **`local_attendance_logs`** di SQLite (APK) dan store **`attendance_logs`** di Dexie (Web) untuk menyimpan seluruh riwayat log secara permanen.
  * Ketika absensi dilakukan secara online, sistem menulis data ke Supabase sekaligus menduplikasi salinannya secara instan ke SQLite lokal dengan status `is_synced = true`.
  * Ketika absensi dilakukan secara offline, log dimasukkan ke antrean sinkronisasi sekaligus disalin ke tabel riwayat lokal dengan status `is_synced = false` dan ID berawalan `offline_`.
  * Halaman logs memuat data gabungan ini langsung secara lokal, menjamin **riwayat log absensi 100% siap diakses offline**.

### 18.16 Sinkronisasi Bersih Bebas Duplikasi (Sync Reconciliation)
* **Masalah Duplikasi Saat Transisi Offline-Online**: Saat log offline diunggah ke Supabase oleh mesin sinkronisasi, log tersebut mendapatkan ID resmi baru dari cloud. Jika log lama dengan ID `'offline_X'` tidak dihapus dari tabel lokal, log tersebut akan terus tampil secara ganda (sebagai log offline terpisah dari log online barunya).
* **Solusi Penghapusan Presisi**:
  * Memperbarui [syncEngine.js](file:///d:/FACE%20VERIFICATION/src/syncEngine.js) agar setelah sukses mengunggah log offline ke cloud, sistem secara eksplisit menghapus log sementara menggunakan format ID yang tepat: `db.attendance_logs.delete('offline_' + oldId)`.
  * Ini secara instan membuang log hantu `'offline_X'` lokal dan menggantikannya dengan catatan sinkronisasi cloud yang sah, meniadakan duplikasi data absensi.

### 18.17 Pemetaan Dinamis Afdeling Tanpa Beban Database
* **Masalah Skema Supabase & Efisiensi**: Data pembagian divisi/kebun karyawan disimpan dalam kolom `afdeling`. Menambahkan kolom `afdeling` ke tabel log absensi cloud (`attendance_logs`) tidak efisien dan menyebabkan error `PGRST204` (kolom tidak ditemukan) jika skema Supabase belum dimigrasi.
* **Solusi Pemetaan Frontend**:
  * Kolom `afdeling` tidak dikirimkan ke tabel `attendance_logs` di Supabase untuk menjaga database ternormalisasi dan mencegah error skema.
  * Di frontend React, fungsi `fetchLogs()` secara dinamis mencocokkan `employee_id` log absensi dengan data master karyawan (`employees` table yang memiliki data afdeling terdaftar hasil input `KaryawanPage.jsx`).
  * Penyatuan data ini terjadi secara lokal di tingkat aplikasi, memastikan kolom Afdeling di Halaman Logs dan Dashboard tetap terisi lengkap tanpa membebani lalu lintas server.
  * Menambahkan fungsi pencarian cerdas di logs page yang mendukung pemfilteran kata kunci afdeling (misal mencari log berdasarkan divisi: "Afd. III" atau "Afd. I").

---

## 19. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v3.2.0)

Pembaruan versi **v3.2.0** berfokus pada penyelesaian masalah ketidakkonsistenan data saat berpindah akun admin pada satu perangkat (multi-account single device), pembersihan cache lokal secara selektif, penanganan *race condition* React state, sinkronisasi reconnect terintegrasi, serta tombol sync khusus pada halaman absensi:

### 19.1 Inisialisasi User State Auth yang Konsisten (localStorage Key Fix)
* **Akar Masalah**: Inisialisasi state awal `user` di [AuthContext.jsx](file:///d:/FACE%20VERIFICATION/src/context/AuthContext.jsx) salah membaca key `logged_in_employee` dari `localStorage`, padahal session login admin disimpan dengan key `logged_in_admin`. Ini menyebabkan `user` bernilai `null` sesaat pada setiap start/reload, memicu pembersihan memori data (`employees = []`, `logs = []`) secara tidak sengaja di [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx).
* **Solusi**: Mengganti inisialisasi state awal di `AuthContext.jsx` agar merujuk ke key `logged_in_admin` secara konsisten, meniadakan *loading flash* dan pembersihan state yang tidak perlu.

### 19.2 Pembersihan Cache Karyawan Selektif Lintas Kebun/Region
* **Akar Masalah**: Sebelumnya, sinkronisasi online memanggil `db.employees_cache.clear()` yang menghapus bersih seluruh data karyawan lokal dari perangkat. Akibatnya, jika admin berganti akun saat offline, data kebun lain sudah terlanjur hilang, mengembalikan 0 karyawan dan memblokir absensi.
* **Pembersihan Selektif**: Meng-upgrade `sqliteClearEmployeesCache` di [sqliteService.ts](file:///d:/FACE%20VERIFICATION/src/services/sqliteService.ts) and method `clear(filter)` di [db.js](file:///d:/FACE%20VERIFICATION/src/db.js) agar menerima filter `{ kebun, region }`. Data karyawan dan deskriptor biometrik (`local_master_descriptors`) yang tidak relevan dengan admin aktif saja yang dihapus, sehingga cache kebun lain yang pernah diunduh saat online tetap tersimpan aman di penyimpanan lokal untuk operasional offline-first mendatang.

### 19.3 Penyelarasan Pemetaan Log Absensi Lokal
* **Penyaringan Sesuai Kebun/Region**: Memodifikasi fungsi `fetchLogs()` di [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx) untuk menyaring array `localEmployees` dari database lokal berdasarkan wilayah admin aktif sebelum melakukan pencocokan atau pengambilan logs. Ini mencegah kebocoran data dan inkonsistensi nama karyawan pada riwayat logs antarkebun di tingkat tampilan luring.

### 19.4 Pencegahan Race Condition Pemuatan Logs (loadedUserRef)
* **Akar Masalah**: Saat admin berubah, `employeesLoaded` dari sesi lama masih bernilai `true` selama siklus render pertama karena batching state React. Ini memicu `fetchLogs()` berjalan prematur dan melakukan query data logs lama di Supabase menggunakan data cache lama sebelum `fetchEmployees()` selesai menulis data cache yang baru.
* **Pencegahan dengan Ref**: Menambahkan `loadedUserRef` berbasis `useRef` di [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx) untuk melacak username admin aktif dari karyawan yang berhasil termuat. `fetchLogs()` kini dicegah berjalan jika `loadedUserRef.current` tidak cocok dengan `user.username`, menjamin logs hanya diunduh setelah database lokal sinkron sepenuhnya dengan data admin yang baru.

### 19.5 Sinkronisasi Otomatis Seluruh Data Saat Reconnect Online
* **Sync Komprehensif**: Memperbarui callback event listener reconnect jaringan `initAutoSyncListener` di [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx). Ketika perangkat beralih dari offline ke online, aplikasi secara berurutan memicu `await fetchEmployees()` terlebih dahulu, baru memanggil `await fetchLogs()`, memastikan seluruh data master dan log absensi diperbarui secara otomatis ke cloud database.

### 19.6 Tombol Sinkronisasi Manual Aman Kamera di Halaman Absensi
* **Desain UI Khusus**: Untuk mematuhi batasan penonaktifkan gesture *pull-to-refresh* di halaman absensi (guna mencegah reload browser tidak sengaja yang mematikan video stream kamera dan membuang RAM AI model), kami menambahkan tombol **Sync Karyawan** pada bar judul [TabFaceVerification.jsx](file:///d:/FACE%20VERIFICATION/src/components/TabFaceVerification.jsx).
* **Sync Tanpa Refresh**: Tombol ini memicu fungsi `fetchEmployees()` dengan indikator berputar (*loading spinner*), memberikan cara aman bagi mandor di lapangan untuk melakukan sinkronisasi data master karyawan secara dinamis tanpa mematikan sesi kamera aktif.

---

## 20. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v3.3.0)

Pembaruan versi **v3.3.0** berfokus pada penyelesaian masalah fatal tipe data primary key SQLite pada pengoperasian luring (*offline-first*), perbaikan nama ekspor fungsi sinkronisasi manual, serta penyelarasan alur registrasi & absensi offline:

### 20.1 Migrasi Tipe Data SQLite Primary Key (TEXT Primary Key for Offline Employees)
* **Akar Masalah**: Saat melakukan penambahan karyawan secara offline di [KaryawanPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/KaryawanPage.jsx), sistem membuat ID sementara berbasis string (`off_emp_1788222532279_pq5h2`). Namun, pada skema SQLite native APK di [sqliteService.ts](file:///d:/FACE%20VERIFICATION/src/services/sqliteService.ts), kolom `id` dan `employee_id` pada tabel `local_employees`, `local_master_descriptors`, `local_today_attendance_cache`, `local_attendance_queue`, dan `local_attendance_logs` didefinisikan sebagai `INTEGER PRIMARY KEY` atau `INTEGER NOT NULL`. Karena SQLite secara ketat memberlakukan aturan angka pada `INTEGER PRIMARY KEY`, percobaan menyimpan ID string memicu error fatal `[SQLite Service cacheUserMasterVector Error]: Run: datatype mismatch (code 20)`.
* **Solusi Skema & Auto-Migration**:
  * Mengubah seluruh definisi DDL kolom primary key dan foreign key ID menjadi `TEXT PRIMARY KEY` dan `TEXT NOT NULL` pada skema SQLite.
  * Menambahkan rutin **Auto-Migration** otomatis berbasis `PRAGMA table_info` di `initSQLite()`. Jika aplikasi dijalankan di perangkat dengan database lama, sistem secara aman membuat tabel versi `v2`, memindahkan data lama dengan `CAST(id AS TEXT)`, membuang tabel lama, dan mengganti namanya ke tabel utama tanpa kehilangan data offline yang sudah tersimpan.
  * Memperbarui seluruh fungsi pendukung (`sqliteCacheUserMasterVector`, `sqliteCacheGeometricVector`, `sqliteQueueOfflineAttendance`, `sqliteBulkPutEmployeesCache`, `sqliteDeleteEmployeeBiometrics`, `sqliteCacheTodayAttendance`, `sqliteGetTodayAttendance`, `sqliteSaveAttendanceLog`, `sqliteBulkSaveAttendanceLogs`, dan `sqliteSavePendingEmployee`) agar memproses ID sebagai `String` tanpa melakukan konversi `parseInt(...)` atau `Number(...)` yang menghasilkan `NaN`.

### 20.2 Alur Registrasi & Absensi Offline 100% Mandiri
* **Registrasi ke Absensi Offline**: Dengan tipe data `TEXT` pada SQLite lokal, karyawan yang didaftarkan saat offline dapat langsung melakukan scan absensi biometrik di [AbsensiPage.jsx](file:///d:/FACE%20VERIFICATION/src/pages/AbsensiPage.jsx) meskipun perangkat tidak terhubung ke internet.
* **Proses Auto-Sync Bertahap**: Saat perangkat terhubung kembali ke internet, [syncEngine.js](file:///d:/FACE%20VERIFICATION/src/syncEngine.js) akan mengunggah data karyawan offline terlebih dahulu ke Supabase untuk mendapatkan ID resmi dari server, memperbarui cache lokal, lalu mengunggah antrean log absensinya.

### 20.3 Pembenahan Panggilan Import Sinkronisasi Manual (`Manual Sync Requests Error`)
* **Akar Masalah**: Peringatan `[Manual Sync Requests Error]` muncul di konsol saat menekan tombol sinkronisasi manual karena di [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx) fungsi sinkronisasi pengajuan di-import dengan nama salah `syncPendingRequests`, padahal nama fungsi asli yang di-export dari [syncEngine.js](file:///d:/FACE%20VERIFICATION/src/syncEngine.js) adalah `syncPendingAttendanceRequests`.
* **Solusi**: Memperbaiki pemanggilan nama fungsi import di `App.jsx` menjadi `syncPendingAttendanceRequests`, serta menambahkan pemanggilan `syncPendingEmployees` pada alur sinkronisasi manual agar pendaftaran karyawan offline & biometrik ikut di-push secara instan ke cloud server.

---

## 21. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v3.4.0)

Pembaruan versi **v3.4.0** berfokus pada optimasi performa tinggi melalui penerapan Delta Sync (pembaruan parsial), penanganan cache Service Worker untuk endpoint Supabase Cloud, dan fitur sinkronisasi latar belakang tanpa reload kamera:

### 21.1 Penerapan Delta Sync (Pembaruan Parsial Hemat Bandwidth)
* **Masalah Query Masif**: Sebelumnya, setiap kali koneksi internet pulih atau sinkronisasi dipanggil, fungsi `fetchEmployees()` dan `fetchLogs()` di [App.jsx](file:///d:/FACE%20VERIFICATION/src/App.jsx) memuat ulang seluruh baris data dari Supabase dari awal (`SELECT *`). Memuat ribuan baris data secara terus-menerus menyebabkan respons aplikasi menjadi lambat dan menghamburkan kuota data.
* **Solusi Delta Sync**:
  * Menyimpan penanda waktu sinkronisasi terakhir (`last_emp_sync_...` dan `last_log_sync_...`) di `localStorage`.
  * Saat sinkronisasi rutin atau reconnect online, kueri Supabase diubah menggunakan filter `.gt('created_at', lastSyncTime)`.
  * Jika tidak ada data baru (0 baris diunduh), aplikasi secara instan menggunakan cache lokal (0 byte data diunduh dari cloud).
  * Jika ada baris data baru/terbaru, hanya delta baris tersebut yang diunduh lalu di-merge secara otomatis ke dalam database lokal (`db.employees_cache` dan `db.attendance_logs`).

### 21.2 Penyelarasan Cache Service Worker (`sw.js` Bypass Cache Supabase)
* **Masalah Cache Stale PWA**: Service Worker pada browser/PWA dapat menyimpan respon HTTP lama dari cloud (efek "harus diklik 2 kali baru data muncul").
* **Solusi Service Worker Dedicated**:
  * Membuat berkas [sw.js](file:///d:/FACE%20VERIFICATION/public/sw.js) dan mendaftarkannya pada [index.html](file:///d:/FACE%20VERIFICATION/index.html).
  * Memasang aturan eksplisit **BYPASS CACHE** (`cache: 'no-store'`) khusus untuk seluruh permintaan jaringan berdomain `*.supabase.co` dan endpoint `/api/`.
  * Menjamin seluruh kueri ke cloud Supabase selalu mengembalikan data segar secara instan pada klik pertama tanpa tertahan oleh cache browser.

### 21.3 Sinkronisasi Latar Belakang Tanpa Reload Kamera
* **Performa Scanner Aman RAM**: Tombol **Sync Karyawan** pada antarmuka scanner di [TabFaceVerification.jsx](file:///d:/FACE%20VERIFICATION/src/components/TabFaceVerification.jsx) memicu penarikan data karyawan terbaru di latar belakang dengan indikator spinner tanpa menghentikan video stream kamera atau memuat ulang model AI biometrik yang berat.

---

## 22. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v4.0.0)

Pembaruan versi **v4.0.0** merupakan rilis arsitektur utama yang mencakup empat tahapan *System Directives*:

### 22.1 Database & RLS Secure Schema (`01_schema.sql`)
* **Soft Delete Pattern**: Menghapus `ON DELETE CASCADE` pada tabel `employees` dan menggantinya dengan kolom `deleted_at TIMESTAMPTZ` di [01_schema.sql](file:///d:/FACE%20VERIFICATION/supabase/migrations/01_schema.sql) untuk mencegah kehilangan data master atau riwayat absensi.
* **RLS Policies & RPC Login**: Menerapkan kebijakan Row Level Security (RLS) pada seluruh tabel utama dan fungsi RPC `verify_admin_login` untuk verifikasi akun admin lokal.

### 22.2 Hybrid Offline Storage Layer (`db.js` & `sqliteService.ts`)
* **Pemetaan ID Text**: Memastikan seluruh kolom ID (`id`, `employee_id`) pada skema SQLite native APK dan Dexie.js terpetakan sebagai `TEXT PRIMARY KEY` / `TEXT NOT NULL` untuk mendukung UUID luring string (`off_emp_...`).
* **Abstraksi Vektor Master**: Implementasi `getAllMasterVectors()` yang konsisten di [sqliteService.ts](file:///d:/FACE%20VERIFICATION/src/services/sqliteService.ts) dan [db.js](file:///d:/FACE%20VERIFICATION/src/db.js).

### 22.3 Auto-Sync Engine & Sequential 3-Tier Sync (`syncEngine.js`)
* **Alur 3-Tier Sequential Push**:
  1. Mengunggah data karyawan luring dari `employee_sync_queue`.
  2. Mengambil ID Cloud resmi dari Supabase dan secara otomatis memperbarui `employee_id` pada antrean log absensi luring yang sebelumnya memakai ID sementara (`off_emp_...`).
  3. Mengunggah antrean log absensi (`attendance_sync_queue`).
  4. Mengunggah pengajuan admin (`attendance_requests`).
* **Write-Ahead Log (WAL) Storage**: Mengintegrasikan `@capacitor/filesystem` untuk menuliskan berkas cadangan log absensi luring secara *append-only* ke `Directory.Documents`.

### 22.4 Presisi WebGL & Penguncian Status Scanner (`humanSingleton.js` & `TabFaceVerification.jsx`)
* **Presisi WebGL Normal**: Menetapkan `WEBGL_FORCE_F16_TEXTURES: true` pada [humanSingleton.js](file:///d:/FACE%20VERIFICATION/src/humanSingleton.js) untuk menjamin nilai vektor biometrik 1024-dimensi konsisten di seluruh perangkat HP/tablet/laptop.
* **Scan Lock**: Mengunci status pencocokan biometrik menggunakan `useRef` saat verifikasi berhasil (threshold >= 0.85) untuk mencegah *double-submission*.

---

## 23. Catatan Pembaruan & Spesifikasi Fitur Terbaru (System Release v4.1.0)

Pembaruan versi **v4.1.0** berfokus pada **Multi-Account Shared Device Handling** untuk keamanan data pada tablet/HP yang dipakai bersama oleh beberapa admin:

### 23.1 Proteksi Pencegahan Kehilangan Data Offline Saat Logout (Data Loss Prevention)
* **Logout Guard**: Sebelum proses logout dijalankan, fungsi `logout()` di [AuthContext.jsx](file:///d:/FACE%20VERIFICATION/src/context/AuthContext.jsx) memeriksa fungsi `getUnsyncedDataSummary()` di [db.js](file:///d:/FACE%20VERIFICATION/src/db.js).
* **Abort Logout & Warning**: Jika ditemukan data offline yang belum tersinkronisasi (log absensi, karyawan baru, atau pengajuan admin), proses logout **dibatalkan secara otomatis** dan sistem memunculkan pesan peringatan eksplisit agar pengguna melakukan sinkronisasi (*Sync*) terlebih dahulu.

### 23.2 Multi-Tenancy Lokal Non-Destruktif (Preserve Local Database)
* **Tanpa Penghapusan Database Global**: Proses logout **tidak lagi menghapus database lokal** (`db.clear()`), sehingga data offline dari admin lain yang belum di-push tetap aman tersimpan di SQLite / Dexie.
* **Isolasi Scope per Akun**: Seluruh kueri lokal dikunci secara ketat berdasarkan scope admin aktif (`kebun`, `region`, atau `role`), mencegah kebocoran data antar-kebun saat beberapa admin bergantian login di satu tablet yang sama.




