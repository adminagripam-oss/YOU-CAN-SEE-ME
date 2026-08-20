# Rancangan Anggaran Biaya (RAB) - Fase Pembuatan & Development Aplikasi
## AgriFace: Biaya Inisiasi MVP (Server, Domain, AI Claude, & Perangkat Tablet Demo)

- **Nama Proyek**: AgriFace (AgriFace Biometric Attendance System)
- **Judul Proyek**: Pembuatan Application Absensi Biometrik Wajah 1-to-1 Pemanen Kebun (MVP Development Stage)
- **Fokus Anggaran**: Server Hosting, Custom Domain Resmi, AI Claude API Credits, & Perangkat Tablet Lapangan
- **Periode Estimasi**: 1 Tahun Operasional Development & Testing
- **Tanggal Dokumen**: 3 Agustus 2026

---

## 1. Rincian Anggaran Biaya Pembuatan Application (MVP Stage)

Berikut adalah rincian kebutuhan biaya langsung untuk pembuatan, hosting, integrasi AI, dan pengadaan tablet pengujian:

### 1. Biaya Infrastruktur Server & Database Cloud

| No | Komponen Infrastruktur | Spesifikasi / Tier | Durasi | Biaya / Bulan (IDR) | Total Biaya 1 Tahun (IDR) |
| :-: | :--- | :--- | :-: | :-: | :-: |
| 1.1 | **Supabase Cloud Database** | Pro Tier Plan *(Vector JSONB Storage, 8GB DB Storage, Daily Auto Backup)* | 12 Bulan | Rp 450.000 | Rp 5.400.000 |
| 1.2 | **VPS Cloud Server Node.js Backend** | DigitalOcean / Railway / Linode *(Specs: 2 vCPU, 4GB RAM, SSD 80GB, Express API)* | 12 Bulan | Rp 350.000 | Rp 4.200.000 |
| 1.3 | **CDN & Storage Cloud (Aset PWA)** | Vercel / Cloudflare Pro *(Fast CDN Delivery & SSL)* | 12 Bulan | Rp 150.000 | Rp 1.800.000 |
| **SUBTOTAL 1 (SERVER & DB)** | | | | | **Rp 11.400.000** |

---

### 2. Biaya Domain & Keamanan (Domain & Security)

| No | Komponen Domain | Deskripsi | Durasi | Total Biaya (IDR) |
| :-: | :--- | :--- | :-: | :-: |
| 2.1 | **Custom Domain Resmi (.co.id / .com)** | Pendaftaran Domain Perusahaan (Contoh: `agriface.co.id` / `agriface.id` / `agriface.app`) | 1 Tahun | Rp 350.000 |
| 2.2 | **Sertifikat SSL Wildcard (HTTPS)** | Enkripsi SSL Keamanan Transaksi Biometrik & Cookie JWT | 1 Tahun | Rp 500.000 |
| **SUBTOTAL 2 (DOMAIN & SSL)** | | | | **Rp 850.000** |

---

### 3. Biaya Layanan AI (Claude AI API & AI Tools Support)

Lisensi & API credits untuk bantuan pengembangan sistem, otomatisasi analisis log absensi, serta pengolahan AI:

| No | Komponen AI Services | Deskripsi / Peruntukan | Durasi / Volume | Biaya (IDR) | Total Biaya (IDR) |
| :-: | :--- | :--- | :-: | :-: | :-: |
| 3.1 | **Anthropic Claude AI API Credits** | API Credits (Claude 3.5 Sonnet / Haiku) untuk integrasi AI analytics, verifikasi cerdas, & ekstraksi data | 1 Tahun (Usage-based) | Rp 500.000 / bln | Rp 6.000.000 |
| 3.2 | **Claude AI Pro Subscription** | Akses Claude Pro (Pengembangan Codebase, Arsitektur Sistem, & Trouble-shooting) | 12 Bulan | $20 USD (~Rp 320.000 / bln) | Rp 3.840.000 |
| **SUBTOTAL 3 (AI CLAUDE SERVICES)** | | | | | **Rp 9.840.000** |

---

### 4. Biaya Pengadaan Perangkat Tablet (Hardware Development & Testing)

Perangkat Tablet Android untuk pengujian scan biometrik, registrasi master wajah karyawan, dan demo lapangan:

| No | Nama Perangkat Hardware | Spesifikasi Utama | Qty | Harga Satuan (IDR) | Total Biaya (IDR) |
| :-: | :--- | :--- | :-: | :-: | :-: |
| 4.1 | **Tablet Android Testing & Demo** | Samsung Galaxy Tab A9+ / Xiaomi Pad 6 *(RAM 8GB, Screen 11", Cam High Res)* | 2 Unit | Rp 3.800.000 | Rp 7.600.000 |
| 4.2 | **Case Anti-Drop & Stand Tablet** | Casing Pelindung Meja Registrasi & Stand Kiosk | 2 Unit | Rp 250.000 | Rp 500.000 |
| **SUBTOTAL 4 (TABLET TESTING)** | | | | | **Rp 8.100.000** |

---

### 5. Biaya Jasa Development & Integrasi Sistem (System Engineering)

Biaya jasa riset, pengerjaan koding, integrasi database Supabase, dan pengujian PWA Offline-First:

| No | Tahapan Pekerjaan Development | Output / Deliverables | Total Biaya (IDR) |
| :-: | :--- | :--- | :-: |
| 5.1 | **Pengembangan Frontend & PWA Offline Engine** | Single Page Application (React 19), IndexedDB Dexie.js Engine, Service Worker PWA | Rp 15.000.000 |
| 5.2 | **Pengembangan Backend REST API & Supabase DB** | Express Server, Otentikasi JWT Cookie, Core 1-to-1 Verification Engine ($O(1)$) | Rp 12.000.000 |
| 5.3 | **Integrasi Biometrik Face-API & AI Analytics** | Integrasi Model ResNet-34 128-vector, Dashboard Analytics & Tab Absensi | Rp 10.000.000 |
| **SUBTOTAL 5 (JASA DEVELOPMENT)** | | | **Rp 37.000.000** |

---

## 2. Rekapitulasi Biaya Tahap Pembuatan (Total RAB MVP)

```
+-----------------------------------------------------------------------------------+
|              REKAPITULASI BIAYA TAHAP PEMBUATAN APLIKASI (MVP STAGE)              |
+-----------------------------------------------------------------------------------+
| 1. Server & Cloud Database Supabase (1 Tahun)             : Rp 11.400.000         |
| 2. Custom Domain & SSL Certificate (1 Tahun)              : Rp    850.000         |
| 3. Claude AI API & Subscription Tools (1 Tahun)           : Rp  9.840.000         |
| 4. Tablet Android Development & Demo (2 Unit)             : Rp  8.100.000         |
| 5. Jasa Development & Engineering Aplikasi                : Rp 37.000.000         |
+-----------------------------------------------------------------------------------+
| **TOTAL BIAYA TAHAP PEMBUATAN APLIKASI (ALL IN)**          : **Rp 67.190.000**     |
+-----------------------------------------------------------------------------------+
```

---

## 3. Catatan Ringkas Ringkasan Kebutuhan Minimal (Opsional Minimalist MVP)

Jika Anda ingin memulai dengan **anggaran paling minimal (Minimalist Testing)** untuk uji coba 1 tablet saja tanpa jasa penuh:

- **Server & Cloud DB**: Rp 900.000 / 3 bulan (Supabase Free/Pro + Mini VPS)
- **Domain**: Rp 150.000 (.com 1 tahun)
- **Claude AI**: Rp 320.000 / bulan (Claude Pro Subscription)
- **Tablet**: Rp 3.800.000 (1 Unit Tablet Samsung Galaxy Tab A9+)
- **Estimasi Minimal Modal Awal Setup**: **`~Rp 5.170.000`** *(diluar jasa pengembangan)*.
