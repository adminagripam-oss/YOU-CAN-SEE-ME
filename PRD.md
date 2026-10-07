# Product Requirement Document (PRD) & System Architecture Analysis
## AgriFace: Sistem Absensi Biometrik Wajah Perkebunan Berbasis 1-to-1 Verification Engine, Capgo OTA Updater & Hybrid Offline-First Architecture

- **Nama Proyek**: AgriFace (AgriFace Biometric Attendance System)
- **Versi Dokumen & Proyek**: 2.1.4-biometric-patch
- **Database Engine**: Supabase Cloud PostgreSQL (JSONB Vector Storage, RPC), Dexie.js (Web IndexedDB), `@capacitor-community/sqlite` (Android Native SQLite)
- **Biometric Engine**: `@vladmandic/human` (1024-dim Embedding Vector, FP16 WebGL Precision) + MediaPipe Face Mesh + EAR Liveness Engine + Cosine Similarity
- **Update System**: `@capgo/capacitor-updater` Over-The-Air (OTA) Bundle Update Engine
- **Target Kompleksitas**: $O(1)$ Time Complexity Direct Lookup Matching

---

## 1. Pendahuluan & Latar Belakang

> **Patch Note (v2.1.4-biometric-patch)**: Perbaikan signifikan pada akurasi Face Recognition 1-to-1. Pengumpulan sampel pada saat pendaftaran (Enrollment) kini mengambil 7 sampel dan menggunakan rata-rata vektor (mean template) dengan Outlier Rejection. Pada saat absensi, sistem menggunakan windowing (median filter) dari 5 sampel. Ambang batas (threshold) `MATCH_COSINE_THRESHOLD` telah dikalibrasi ke **0.90** pada skala Cosine Similarity asli, menggantikan kurva pangkat eksponensial lama yang menyebabkan skor terlalu rendah pada kondisi pencahayaan kurang optimal. Karyawan yang absen kini akan lebih mudah dikenali asalkan wajah dalam keadaan stabil (Liveness Verified).


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
