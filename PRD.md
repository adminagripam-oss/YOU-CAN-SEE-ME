# Product Requirement Document (PRD)
## Sistem Absensi Biometrik Wajah berbasis 1-to-1 Verification Engine

- **Project Title**: Sistem Absensi Biometrik Wajah berbasis 1-to-1 Verification
- **Target Complexity**: $O(1)$ Time Complexity Direct Lookup
- **Database Engine Target**: Supabase (PostgreSQL) / SQLite
- **Biometric Library**: `face-api.js` (ResNet-34 Model, 128-Float32 Vector Descriptor)
- **Version**: 1.2.0
- **Status**: Production Ready Specifications (Full Live Supabase Integration)

---

## 1. Pendahuluan & Latar Belakang

Sistem Absensi Biometrik Wajah konvensional umumnya mengabaikan efisiensi dengan menggunakan pencocokan **1-to-N (Verifikasi 1-ke-Banyak)**, di mana wajah scan dibandingkan satu per satu dengan seluruh sampel karyawan di database. Pendekatan $O(N)$ ini menyebabkan penundaan (*latency*) signifikan, lonjakan CPU/RAM server, dan risiko *false positive* yang tinggi saat jumlah karyawan bertambah hingga ribuan.

---           

## 1.1 Spesifikasi Teknologi & Arsitektur (Tech Stack Specifications)

### 💻 Frontend (Client-Side)
- **Language**: JavaScript (ES6+ / JSX), HTML5, CSS3
- **Framework / Architecture**: React SPA (Single Page Application dengan React Hooks & State Management)
- **Styling & UI System**: Custom Glassmorphism CSS Design Tokens, Shadcn UI Alert & Table Component Specifications, Google Fonts (Inter)
- **Libraries**:
  - `React` & `React-DOM`: Core UI Framework Component State Engine
  - `face-api.js` (v0.22.2 / `@vladmandic/face-api`): Deep Learning Neural Networks (ResNet-34 Architecture, SSD MobileNet V1 Face Detector, 68-Point Face Landmark Predictor, 128-float Vector Descriptor Extractor)
  - `FontAwesome 6.4.0` / `Lucide-React`: Iconography UI Library

### ⚙️ Backend (Server-Side & Database)
- **Language**: JavaScript (Node.js Runtime Environment)
- **Framework**: Express.js (v4.21.2) Web Application Framework
- **Database Engine**: Supabase Cloud Database (PostgreSQL Relational Engine dengan dukungan tipe data `JSONB` untuk penyimpanan 128-float vector array)
- **API Architecture**: RESTful JSON API Protocol (`GET`, `POST`, `PUT`, `DELETE` HTTP Endpoints)
- **Libraries & Dependencies**:
  - `@supabase/supabase-js` (v2.111.0): SDK Resmi Supabase Database Client
  - `dotenv` (v17.4.2): Environment Variable Manager
  - `cors` (v2.8.5): Cross-Origin Resource Sharing Middleware

---

## 2. Tujuan & Sasaran Produk (Product Objectives)

1. **Performa Tinggi ($O(1)$ Complexity)**: Memastikan waktu respon verifikasi absensi di server $\le 100\text{ ms}$ terlepas dari apakah database memiliki 100 atau 100.000 karyawan.
2. **Akurasi & Keamanan Tinggi**: Menggunakan threshold Euclidean Distance $< 0.55$ pada vector 128-dimensi Float32.
3. **Privasi Data Biometrik**: Tidak menyimpan foto wajah mentah (*raw image*) karyawan di server, melainkan hanya menyimpan **Vector Descriptor Numerical Array (128 float)**.
4. **Resiliensi & Penanganan Error**: Menyediakan proteksi data input, validasi skema array 128 elemen, serta penanganan error jika NIK / biometrik master belum terdaftar.
5. **Manajemen Log Absensi & Audit Lanjutan**: Mendukung absensi berpasangan (Check-In & Check-Out), kalkulasi durasi kerja otomatis, serta manajemen penghapusan log (hapus per-session & hapus massal).

---

## 3. Peran Pengguna (User Roles & Personas)

| Peran | Deskripsi & Hak Akses |
| :--- | :--- |
| **Karyawan (Employee)** | Melakukan verifikasi absensi harian (Check-In / Check-Out) dengan memilih NIK dan menghadap kamera. |
| **HR / Admin Biometrik** | Mendaftarkan karyawan baru dan melakukan *enrollment* (capture live webcam / upload foto file) biometrik master wajah. |
| **Admin Sistem / IT** | Memantau log absensi real-time, audit Euclidean Distance, mengedit/menghapus karyawan, serta mengelola/menghapus log absensi. |

---

## 4. Persyaratan Fungsional (Functional Requirements)

### FR-1: Identifikasi NIK & Input Karyawan
- Sistem menyediakan input NIK manual, pencarian dropdown, atau integrasi QR code ID Card.
- Frontend mengirimkan identifier karyawan (`employee_id` atau `nik`) bersama payload absensi.
- Sistem mengecek status absensi hari ini (`GET /api/attendance/status/:employeeId`) untuk secara dinamis menampilkan tombol aksi yang sesuai.

### FR-2: Capture Wajah & Ekstraksi Vector Client-Side
- Frontend membuka webcam (*live video stream*) 640x480 pixel.
- Library `face-api.js` mendeteksi landmark 68-titik wajah dan mengekstrak **Float32Array 128 elemen**.
- Overlay canvas menampilkan *bounding box* dan titik landmark wajah secara real-time.

### FR-3: Registrasi Biometrik Master (Dual Mode & Anti-Duplikasi Wajah)
- Admin memasukkan data NIK, Nama, dan Departemen karyawan.
- Pilihan capture biometrik master:
  1. **Mode Kamera Live**: Menangkap wajah langsung via webcam.
  2. **Mode Upload File Foto**: Mengunggah berkas gambar (JPG/PNG) dan mengekstrak vector 128-float dari berkas tersebut.
- **Proteksi Anti-Duplikasi Wajah Unik**:
  - Sebelum menyimpan master biometrik, backend secara otomatis membandingkan vector calon master dengan seluruh master biometrik karyawan lain di database.
  - Jika terdapat kecocokan Euclidean Distance $d < 0.55$ dengan karyawan yang sudah terdaftar, backend **menolak registrasi** (HTTP 400) dan mengembalikan pesan error: `"Wajah ini SUDAH TERDAFTAR atas nama [Nama Karyawan] (NIK: [NIK])! Satu wajah hanya bisa didaftarkan untuk 1 karyawan."`
  - Frontend secara otomatis melakukan *rollback* (menghapus record karyawan sementara) sehingga tidak meninggalkan data karyawan tanpa biometrik / duplikat.
- Frontend mengirimkan descriptor master (128-float) ke endpoint `/api/biometrics/register`.
- Backend menyimpan/memperbarui descriptor master ke Supabase PostgreSQL (`master_descriptors`).

### FR-4: Engine Pencocokan 1-to-1 (Backend Core)
- Backend menerima payload `{ employee_id / nik, scan_descriptor, location, attendance_type }`.
- Backend melakukan validasi array `scan_descriptor` harus berjumlah tepat 128 elemen numerik.
- Backend mengambil **1 record master descriptor** milik NIK tersebut dari database ($O(1)$ query direct lookup).
- Backend menghitung Euclidean Distance:
  $$d(p, q) = \sqrt{ \sum_{i=1}^{128} (p_i - q_i)^2 }$$
- Evaluasi Threshold:
  - Jika $d < 0.55 \rightarrow$ **VERIFIKASI BERHASIL** (HTTP 200).
  - Jika $d \ge 0.55 \rightarrow$ **VERIFIKASI GAGAL** (HTTP 400).

### FR-5: Smart Action Button & Pesan Feedback (Shadcn UI Alert)
- **Smart Toggle Button**:
  - Belum Check-In $\rightarrow$ Tombol 🟢 **Check-In (Masuk)** aktif.
  - Sudah Check-In $\rightarrow$ Tombol 🟡 **Check-Out (Keluar)** aktif.
- **Card Metrics & Alert**:
  - Bagian **ATAS** container pesan menampilkan grid metrics (Euclidean Distance, Threshold, Tipe Absensi, Timestamp).
  - Bagian **BAWAH** menampilkan Shadcn UI Alert Component (Ikon Checkmark/Cross, AlertTitle, AlertDescription).

### FR-6: Pencatatan Log Absensi, Audit & Manajemen Log
- Setiap percobaan absensi (berhasil/gagal, Check-In/Check-Out) dicatat di tabel `attendance_logs` Supabase.
- **Kalkulasi Durasi Kerja**: Backend secara otomatis memasangkan log Check-Out dengan Check-In terakhir hari itu untuk menghitung durasi kerja (contoh: `2j 30m`).
- **Tabel Log Shadcn UI**: Menampilkan riwayat log dengan timestamp terpisah (tanggal & jam), status badge, durasi kerja, dan kolom Aksi.
- **Manajemen Penghapusan Log**:
  1. **Hapus Session Single**: Tombol 🗑 `Hapus` pada setiap baris log (`DELETE /api/attendance/logs/:id`) dengan konfirmasi Shadcn UI Modal.
  2. **Hapus Semua Log**: Tombol 🗑 `Hapus Semua Log` (`DELETE /api/attendance/logs`) untuk menghapus seluruh riwayat log absensi.

### FR-7: Manajemen Data Karyawan (CRUD Full)
- **Read**: Menampilkan daftar karyawan beserta indikator status biometrik master (Siap / Belum).
- **Update**: Modal Edit Data Karyawan (`PUT /api/employees/:id`) untuk memperbarui NIK, Nama, dan Departemen.
- **Delete**: Tombol Hapus Karyawan (`DELETE /api/employees/:id`) dengan menghapus secara otomatis biometrik master dan log absensi terkait (*ON DELETE CASCADE*).

---

## 5. Persyaratan Non-Fungsional (Non-Functional Requirements)

- **Latency**: Pengolahan API backend $< 50\text{ ms}$.
- **Akurasi**: Model ResNet-34 menawarkan akurasi $> 99.38\%$ pada dataset LFW dengan threshold 0.55.
- **Portabilitas**: Frontend berjalan di browser modern (Chrome, Edge, Safari, Firefox, WebView HP).
- **Keamanan Supabase**: Row Level Security (RLS) dan Service Role API Key untuk akses backend aman.

---

## 6. Persiapan Integrasi ke Database Supabase (PostgreSQL)

Jika Anda ingin beralih dari SQLite lokal ke **Database Supabase (PostgreSQL)**, berikut adalah poin-poin dan prasyarat yang perlu Anda siapkan:

### 🛠️ A. Apa yang Perlu Anda Siapkan?

1. **Akun & Project Supabase**:
   - URL Project Supabase (contoh: `https://xyzcompany.supabase.co`).
   - `SUPABASE_ANON_KEY` atau `SUPABASE_SERVICE_ROLE_KEY` (diambil dari menu **Project Settings -> API** di Dashboard Supabase).
2. **Node.js Package**:
   - Install SDK resmi Supabase & Dotenv:
     ```bash
     npm install @supabase/supabase-js dotenv
     ```
3. **Eksekusi Skema SQL di Supabase**:
   - Jalankan DDL SQL berikut pada menu **SQL Editor** di Dashboard Supabase Anda:

---

### 🗄️ B. Skema Tabel PostgreSQL / Supabase SQL Editor

Salin dan jalankan perintah SQL berikut di **Supabase SQL Editor**:

```sql
-- Enable UUID extension (Opsional jika ingin UUID)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 1. Tabel Employees (Karyawan)
CREATE TABLE IF NOT EXISTS public.employees (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nik VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. Tabel Master Descriptors Biometrik Wajah
CREATE TABLE IF NOT EXISTS public.master_descriptors (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT UNIQUE NOT NULL REFERENCES public.employees(id) ON DELETE CASCADE,
    descriptor_json JSONB NOT NULL, -- Menyimpan 128 Float32 Array sebagai JSONB
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- 3. Tabel Attendance Logs
CREATE TABLE IF NOT EXISTS public.attendance_logs (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    employee_id BIGINT NOT NULL REFERENCES public.employees(id) ON DELETE CASCADE,
    timestamp TIMESTAMPTZ DEFAULT NOW(),
    location VARCHAR(255) DEFAULT 'Kantor Pusat',
    attendance_type VARCHAR(20) DEFAULT 'CHECK_IN', -- 'CHECK_IN' atau 'CHECK_OUT'
    status VARCHAR(100) NOT NULL,
    euclidean_distance DOUBLE PRECISION NOT NULL
);

-- Indeks untuk pencarian O(1) cepat berdasarkan NIK & Employee ID
CREATE INDEX IF NOT EXISTS idx_employees_nik ON public.employees(nik);
CREATE INDEX IF NOT EXISTS idx_master_desc_emp_id ON public.master_descriptors(employee_id);
CREATE INDEX IF NOT EXISTS idx_attendance_emp_timestamp ON public.attendance_logs(employee_id, timestamp DESC);
```

---

## 7. Ringkasan Endpoint REST API Backend (`server.js`)

| Method | Endpoint | Deskripsi |
| :--- | :--- | :--- |
| `GET` | `/api/employees` | Mengambil seluruh daftar karyawan beserta status biometrik master. |
| `POST` | `/api/employees` | Menambah data karyawan baru. |
| `PUT` | `/api/employees/:id` | Mengedit NIK, Nama, dan Departemen karyawan. |
| `DELETE` | `/api/employees/:id` | Menghapus karyawan (beserta master biometrik & log terkait). |
| `POST` | `/api/biometrics/register` | Menyimpan/memperbarui vector biometrik 128-float master. |
| `POST` | `/api/attendance/verify` | Engine verifikasi 1-to-1 ($O(1)$) untuk Check-In / Check-Out. |
| `GET` | `/api/attendance/status/:employeeId` | Mengecek status absensi hari ini (apakah sudah Check-In). |
| `GET` | `/api/attendance/logs` | Mengambil riwayat log absensi terenkripsi beserta durasi kerja. |
| `DELETE` | `/api/attendance/logs/:id` | Menghapus 1 session log absensi spesifik. |
| `DELETE` | `/api/attendance/logs` | Menghapus SELURUH riwayat log absensi dari database. |

---

### 🔌 C. Contoh Konfigurasi Server `server_supabase.js`

Berikut adalah contoh adapter Node.js Express yang terhubung langsung ke **Supabase Database**:

```javascript
require('dotenv').config();
const express = require('express');
const cors = require('cors');
const { createClient } = require('@supabase/supabase-js');

const app = express();
app.use(cors());
app.use(express.json({ limit: '10mb' }));

// Inisialisasi Supabase Client
const supabaseUrl = process.env.SUPABASE_URL;
const supabaseKey = process.env.SUPABASE_SERVICE_ROLE_KEY || process.env.SUPABASE_ANON_KEY;
const supabase = createClient(supabaseUrl, supabaseKey);

// Helper Euclidean Distance
function calculateEuclideanDistance(vecA, vecB) {
  let sum = 0;
  for (let i = 0; i < vecA.length; i++) {
    const diff = vecA[i] - vecB[i];
    sum += diff * diff;
  }
  return Math.sqrt(sum);
}

// Endpoint Verifikasi 1-to-1 dengan Supabase
app.post('/api/attendance/verify', async (req, res) => {
  try {
    const { nik, employee_id, scan_descriptor, location = 'Kantor Pusat' } = req.body;

    if (!Array.isArray(scan_descriptor) || scan_descriptor.length !== 128) {
      return res.status(400).json({ success: false, message: 'Validasi Gagal: Descriptor harus 128 float!' });
    }

    // 1. Fetch Employee Record
    let empQuery = supabase.from('employees').select('*');
    if (employee_id) empQuery = empQuery.eq('id', employee_id);
    else if (nik) empQuery = empQuery.eq('nik', nik.trim());
    
    const { data: employees, error: empErr } = await empQuery.single();
    if (empErr || !employees) {
      return res.status(404).json({ success: false, message: 'Karyawan tidak ditemukan!' });
    }

    // 2. Fetch Master Descriptor (O(1) Direct Lookup)
    const { data: masterData, error: masterErr } = await supabase
      .from('master_descriptors')
      .select('descriptor_json')
      .eq('employee_id', employees.id)
      .single();

    if (masterErr || !masterData) {
      return res.status(404).json({ success: false, message: `Master biometrik untuk ${employees.name} belum didaftarkan!` });
    }

    const masterDescriptor = masterData.descriptor_json; // Parsed JSONB automatically
    const distance = calculateEuclideanDistance(scan_descriptor, masterDescriptor);

    const THRESHOLD = 0.55;
    const isVerified = distance < THRESHOLD;
    const status = isVerified ? 'VERIFIKASI BERHASIL' : 'VERIFIKASI GAGAL (Wajah Tidak Cocok)';

    // 3. Insert Attendance Log
    await supabase.from('attendance_logs').insert({
      employee_id: employees.id,
      location,
      status,
      euclidean_distance: parseFloat(distance.toFixed(4))
    });

    if (isVerified) {
      return res.json({
        success: true,
        status: 'VERIFIKASI BERHASIL',
        message: `Absensi Berhasil! Selamat Datang, ${employees.name}.`,
        metrics: { euclidean_distance: parseFloat(distance.toFixed(4)), threshold: THRESHOLD }
      });
    } else {
      return res.status(400).json({
        success: false,
        status: 'VERIFIKASI GAGAL',
        message: `Verifikasi Gagal: Wajah tidak cocok!`,
        metrics: { euclidean_distance: parseFloat(distance.toFixed(4)), threshold: THRESHOLD }
      });
    }
  } catch (err) {
    res.status(500).json({ success: false, message: err.message });
  }
});
```

---

### 📋 Checklist Ringkas Persiapan Supabase:
- [x] Buat Project di Dashboard Supabase.
- [x] Buka SQL Editor Supabase, tempel skema SQL di atas & jalankan (*Run*).
- [x] Buat berkas `.env` di root project:
  ```env
  PORT=8080
  SUPABASE_URL=https://xxxxxxxxxxxx.supabase.co
  SUPABASE_SERVICE_ROLE_KEY=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
  ```
- [x] Install SDK Supabase: `npm install @supabase/supabase-js dotenv`
