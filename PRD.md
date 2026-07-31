# Product Requirement Document (PRD)
## Sistem Absensi Biometrik Wajah berbasis 1-to-1 Verification Engine & Hybrid Supabase Cloud

- **Project Title**: Aplikasi Absensi Mobile Biometrik Wajah dengan Fitur Offline-First & Auto-Sync
- **Target Complexity**: $O(1)$ Time Complexity Direct Lookup
- **Database Engine Target**: Supabase Cloud (PostgreSQL Relational Engine dengan JSONB Vector Storage) & Dexie.js (IndexedDB Local)
- **Biometric Library**: `face-api.js` (ResNet-34 Model, SSD MobileNet V1, 68 Landmarks, 128-Float32 Vector Descriptor)
- **Version**: 2.0.0
- **Status**: Production Ready Specifications (Live Supabase Cloud & GitHub Pages PWA Ready)

---

## 1. Pendahuluan & Latar Belakang

Sistem Absensi Biometrik Wajah konvensional umumnya mengabaikan efisiensi dengan menggunakan pencocokan **1-to-N (Verifikasi 1-ke-Banyak)**, di mana wajah scan dibandingkan satu per satu dengan seluruh sampel karyawan di database. Pendekatan $O(N)$ ini menyebabkan penundaan (*latency*) signifikan, lonjakan CPU/RAM server, dan risiko *false positive* yang tinggi saat jumlah karyawan bertambah hingga ribuan.

Versi 2.0.0 memperkenalkan arsitektur **1-to-1 Direct Lookup ($O(1)$)**, **Offline-First PWA (IndexedDB & Service Worker)**, **Otentikasi JWT (Cookie HTTP-Only & Session)**, **Sistem Router SPA dengan Pemisahan Layout**, serta **Hybrid 3-Tier Data Fetcher** yang memungkinkan aplikasi diakses dari perangkat mobile manapun (via GitHub Pages atau local server) tanpa hambatan *Mixed Content*.

---           

## 1.1 Spesifikasi Teknologi & Arsitektur (Tech Stack Specifications)

### 💻 Frontend (Client-Side SPA)
- **Language**: JavaScript (ES6+ / JSX), HTML5, CSS3 (Vanilla Design Tokens)
- **Framework & Routing**: React SPA (v19) & `react-router-dom` (v6) dengan Auth Guard & Layout Outlets.
- **State Management & Auth**: React Context API (`AuthProvider` & `useAuth` hook).
- **Styling & UI System**: Custom Glassmorphism CSS System, Theme Engine (Light Mode & Dark Mode), Shadcn UI Alert & Table Specifications, Google Fonts (Inter).
- **Libraries & Dependencies**:
  - `React` & `React-DOM` (v19.2.8): Core UI Framework Component Engine.
  - `react-router-dom` (v6): Client-side SPA routing & Protected/Public route guards.
  - `face-api.js` (`@vladmandic/face-api` v1.7.15): Deep Learning Neural Networks (ResNet-34 Architecture, SSD MobileNet V1 Face Detector, Tiny Face Detector, 68-Point Face Landmark Predictor, 128-float Vector Descriptor Extractor).
  - `@supabase/supabase-js` (v2.111.0): SDK Resmi Supabase untuk query langsung dari Frontend via HTTPS (bebas *Mixed Content*).
  - `dexie` (v4.4.4): IndexedDB Wrapper untuk offline caching karyawan, master biometrik, dan antrean absensi offline.
  - `FontAwesome 6.4.0` / `Lucide-React`: Iconography UI Library.

### ⚙️ Backend (Server-Side & REST API)
- **Language & Runtime**: JavaScript (Node.js Runtime Environment).
- **Framework**: Express.js (v4.21.2) Web Application Framework.
- **Database Engine**: Supabase Cloud Database (PostgreSQL Relational Engine dengan tipe data `JSONB` untuk penyimpanan 128-float vector array).
- **API Protocol**: RESTful JSON API Protocol (`GET`, `POST`, `PUT`, `DELETE`).
- **Otentikasi & Keamanan**: JWT (`jsonwebtoken`) diset pada HTTP-Only Cookie (`cookie-parser`) & Bearer Header.
- **Libraries & Dependencies**:
  - `@supabase/supabase-js` (v2.111.0): SDK Database Client Supabase Cloud.
  - `jsonwebtoken`: Penandatanganan & verifikasi token JWT.
  - `cookie-parser`: Handler HTTP-Only cookies.
  - `dotenv` (v17.4.2): Environment Variable Manager.
  - `cors` (v2.8.5): Cross-Origin Resource Sharing Middleware dengan `credentials: true`.

---

## 2. Tujuan & Sasaran Produk (Product Objectives)

1. **Performa Tinggi ($O(1)$ Complexity)**: Memastikan waktu respon verifikasi absensi di server $\le 100\text{ ms}$ terlepas dari jumlah karyawan di database.
2. **Akurasi & Keamanan Biometrik**: Menggunakan threshold Euclidean Distance $< 0.55$ pada vector 128-dimensi Float32.
3. **Privasi Data**: Tidak menyimpan foto wajah mentah (*raw image*) di server, melainkan hanya menyimpan **Vector Descriptor Numerical Array (128 float)**.
4. **Offline-First & PWA Support**: Karyawan dapat melakukan absensi di HP tanpa internet. Log disimpan di IndexedDB lokal dan di-sync otomatis saat internet kembali.
5. **Otentikasi Sesi & Proteksi Rute**: Pemisahan tegas antara `AuthLayout` (Public) dan `DashboardLayout` (Protected) menggunakan `<ProtectedRoute>`.
6. **Cross-Device Mobile Compatibility**: Integrasi Direct Supabase Client pada Frontend memungkinkan aplikasi diakses via GitHub Pages dari HP manapun di dunia secara *real-time*.

---

## 3. Peran Pengguna (User Roles & Personas)

| Peran | Deskripsi & Hak Akses |
| :--- | :--- |
| **Karyawan (Employee)** | Melakukan login sesi, verifikasi absensi harian (Check-In / Check-Out) dengan pemindai biometrik wajah, dan melihat durasi kerja. |
| **HR / Admin Biometrik** | Mendaftarkan karyawan baru dan melakukan *enrollment* biometrik master wajah (live webcam / upload foto). |
| **Admin Sistem / IT** | Memantau log absensi real-time, memicu auto-sync, mengedit/menghapus karyawan, serta mengelola log absensi. |

---

## 4. Persyaratan Fungsional (Functional Requirements)

### FR-1: Authentication Flow & Layout Separation
- **Public Layout (`/login`)**:
  - Halaman Login berdesain Glassmorphism penuh (*fullscreen*) tanpa Sidebar atau Topbar.
  - Tabbed Card UI: **Account** (Pilih Akun & Login), **Password** (Ganti PIN/Password), **Settings** (Pengaturan Tema & Status Server).
  - Meng-generate token JWT 24 jam via endpoint `POST /api/auth/login`.
- **Protected Layout (`/dashboard`, `/absensi`, `/karyawan`, `/logs`)**:
  - Dibungkus oleh komponen `<ProtectedRoute>`. Jika sesi kosong, user otomatis di-redirect ke `/login`.
  - Memiliki komponen **Persistent Sidebar** dan **Persistent Topbar**.
  - **Sidebar Collapsible Drawer**: Tombol hamburger `☰` / `✕` di Topbar untuk membuka/menutup Sidebar dengan transisi animasi smooth dan overlay gelap di mobile.
  - **Topbar Live Indicator**: Badge animasi `● LIVE` / `OFFLINE` dan indikator `Pending Sync` di baris header.
- **Session Verification (`GET /api/auth/me`)**:
  - Dieksekusi otomatis saat *refresh* (F5) untuk memvalidasi apakah token JWT masih aktif.
- **Logout (`POST /api/auth/logout`)**:
  - Menghapus cookie otentikasi `token`, membersihkan sesi lokal, dan me-redirect user ke `/login`.

### FR-2: Hybrid 3-Tier Data Resiliency Engine
- Frontend menggunakan logika pengambilan data 3-tier:
  - **Tier 1 (Express REST API)**: Mencoba mengambil data via endpoint `/api/employees` dan `/api/attendance/logs`.
  - **Tier 2 (Direct Supabase Cloud SDK)**: Jika Tier 1 gagal (misal diakses via GitHub Pages di HP karena pemblokiran *Mixed Content*), Frontend otomatis menarik data langsung dari Supabase Cloud (`https://qrtvawixmlekbitvfuav.supabase.co`) via HTTPS.
  - **Tier 3 (IndexedDB Local Cache)**: Jika perangkat offline, data diambil dari cache lokal IndexedDB (`db.employees_cache`).

### FR-3: Capture Wajah & Verification 1-to-1 ($O(1)$ Engine)
- Pemindai webcam mendeteksi landmark 68-titik wajah dan mengekstrak **Float32Array 128 elemen**.
- Overlay canvas menampilkan *bounding box*, landmark mesh 68 titik, dan badge sampel 128-vector preview.
- Backend/Local Engine menghitung Euclidean Distance:
  $$d(p, q) = \sqrt{ \sum_{i=1}^{128} (p_i - q_i)^2 }$$
- Threshold $d < 0.55 \rightarrow$ **VERIFIKASI BERHASIL**.

### FR-4: Registrasi Master Biometrik & Anti-Duplikasi
- Pendaftaran karyawan via Kamera Live atau Upload File Foto.
- Backend / Direct Supabase memverifikasi bahwa wajah belum pernah terdaftar ($d < 0.55$) oleh karyawan lain.
- Vector master (128 float) disimpan ke Supabase Cloud dan di-cache ke IndexedDB HP untuk absensi offline.

### FR-5: Log Absensi & Format Durasi Kerja
- Log absensi mencatat tanggal, jam, NIK, Nama, Departemen, Status (Berhasil/Gagal), dan Lokasi.
- **Kalkulasi Durasi Kerja**: Log Check-Out diformat otomatis menjadi **`Xj Ym Zd`** (Jam, Menit, Detik), contoh: `0j 15m 30d`.
- **Theme-Aware Font Color**: Teks NIK dan konten tabel menggunakan variabel warna tema yang kontras (Hitam murni di Light Mode, Putih murni di Dark Mode).
- **Manajemen Hapus Log**: Menghapus 1 session log (`DELETE /api/attendance/logs/:id`) atau Hapus Semua Log (`DELETE /api/attendance/logs`).

---

## 5. Persyaratan Non-Fungsional (Non-Functional Requirements)

- **Latency**: Pengolahan verifikasi 1-to-1 $< 50\text{ ms}$.
- **Theme Contrast**: Menggunakan variabel CSS `:root, [data-theme="light"]` (`--text-main: #000000`) dan `[data-theme="dark"]` (`--text-main: #ffffff`). Preference tersimpan di `localStorage.getItem('app-theme')`.
- **Mobile Responsive**: Flexbox & CSS Grid adaptif untuk layar HP (320px - 768px).
- **PWA Offline-First**: Service Worker (`public/sw.js`) meng-cache UI asset & AI models.

---

## 6. Ringkasan Endpoint REST API Backend (`server.js`)

| Method | Endpoint | Access | Deskripsi |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Public | Autentikasi user & pengiriman cookie token JWT. |
| `GET` | `/api/auth/me` | Protected | Verifikasi sesi aktif & data profil user. |
| `POST` | `/api/auth/logout` | Protected | Menghapus cookie token JWT & mengakhiri sesi. |
| `GET` | `/api/employees` | Public/Protected | Mengambil daftar karyawan & status biometrik master. |
| `POST` | `/api/employees` | Protected | Menambah data karyawan baru. |
| `PUT` | `/api/employees/:id` | Protected | Mengedit data NIK, Nama, dan Departemen karyawan. |
| `DELETE` | `/api/employees/:id` | Protected | Menghapus karyawan (ON DELETE CASCADE master & log). |
| `POST` | `/api/biometrics/register` | Protected | Menyimpan vector biometrik 128-float master. |
| `POST` | `/api/attendance/verify` | Public/Protected | Verifikasi absensi 1-to-1 ($O(1)$) Check-In / Check-Out. |
| `GET` | `/api/attendance/status/:id` | Public/Protected | Mengecek status absensi hari ini (Check-In). |
| `GET` | `/api/attendance/logs` | Protected | Mengambil riwayat log absensi & durasi kerja. |
| `POST` | `/api/attendance/sync` | Protected | Endpoint batch auto-sync dari IndexedDB HP ke server. |
| `DELETE` | `/api/attendance/logs/:id` | Protected | Menghapus 1 session log absensi spesifik. |
| `DELETE` | `/api/attendance/logs` | Protected | Menghapus SELURUH riwayat log absensi. |

---

## 7. Skema Database Supabase PostgreSQL

```sql
-- 1. Tabel Employees (Karyawan)
CREATE TABLE IF NOT EXISTS public.employees (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nik VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    descriptor_json JSONB,
    has_master_biometric BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. Tabel Attendance Logs
CREATE TABLE IF NOT EXISTS public.attendance_logs (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT NOT NULL REFERENCES public.employees(id) ON DELETE CASCADE,
    name VARCHAR(100),
    nik VARCHAR(50),
    department VARCHAR(100),
    timestamp TIMESTAMPTZ DEFAULT NOW(),
    location VARCHAR(255) DEFAULT 'Kantor Pusat',
    attendance_type VARCHAR(20) DEFAULT 'CHECK-IN', -- 'CHECK-IN' atau 'CHECK-OUT'
    durasi VARCHAR(50),
    status VARCHAR(100) NOT NULL,
    euclidean_distance DOUBLE PRECISION NOT NULL
);

-- Indeks untuk pencarian O(1) cepat
CREATE INDEX IF NOT EXISTS idx_employees_nik ON public.employees(nik);
CREATE INDEX IF NOT EXISTS idx_attendance_emp_timestamp ON public.attendance_logs(employee_id, timestamp DESC);
```
