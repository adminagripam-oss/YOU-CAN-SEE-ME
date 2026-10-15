# Kalkulasi Kapasitas & Konsumsi Data Server Supabase — AgriFace (Skala 12.000 TK Panen)

Dokumen ini menyajikan kalkulasi matematis berbasis parameter operasional aktual proyek **AgriFace** untuk menentukan kebutuhan kapasitas Database, File Storage, Bandwidth (Egress), dan spesifikasi paket Supabase yang diperlukan.

---

## 1. Parameter Dasar Operasional

Berdasarkan hasil verifikasi kebutuhan bisnis:
- **Total Tenaga Kerja (TK)**: $12.000$ TK Panen aktif per bulan.
- **Frekuensi Absensi**: $2 \times$ sehari (Check-In & Check-Out) pada $26$ hari kerja per bulan.
  - **Volume Harian**: $12.000 \times 2 = 24.000$ log absensi/hari.
  - **Volume Bulanan**: $24.000 \times 26 = 624.000$ log absensi/bulan.
  - **Volume Tahunan (12 bulan)**: $624.000 \times 12 = 7.488.000$ (~$7,5$ juta) log absensi/tahun.
- **Periode Retensi Database**: $1$ Tahun Penuh ($12$ bulan berjalan).
- **Jumlah Perangkat Mandor/Admin**: $> 500$ perangkat (asumsi model: $500$ HP Mandor).
- **Format Media**:
  - Data teks & vektor biometrik 1024-D di PostgreSQL.
  - Snapshot foto wajah terkompresi ($30 - 50\text{ KB}$, rata-rata $40\text{ KB}$) di Supabase Storage Bucket.
  - Bundle OTA Update App ($\sim 18\text{ MB}$ per rilis) di Supabase Storage Bucket.

---

## 2. Kalkulasi Penyimpanan Database PostgreSQL (DB Size)

### A. Tabel Master Karyawan & Biometrik 1024-D
1. **Tabel `employees`** ($12.000$ baris):
   - Kolom data identitas (`nik`, `name`, `department`, `afdeling`, `nama_kebun`, `jabatan`, dll.): $\sim 350\text{ bytes}$/baris.
   - Total ukuran data: $12.000 \times 350\text{ bytes} \approx 4,2\text{ MB}$.
2. **Tabel `master_descriptors`** ($12.000$ baris):
   - Vektor 1024-D Float32 tersimpan dalam format JSONB array: $1024 \times \sim 9\text{ karakter} \approx 9,2\text{ KB}$/baris.
   - Total ukuran data: $12.000 \times 9,2\text{ KB} \approx 110,4\text{ MB}$.
3. **Index & PostgreSQL Page Overhead ($1,4\times$)**:
   - Total Master Data: $(4,2\text{ MB} + 110,4\text{ MB}) \times 1,4 \approx \mathbf{160\text{ MB}}$.

---

### B. Tabel Riwayat Log Absensi (`attendance_logs`)
Setiap baris log memuat: `id` (BigInt), `employee_id`, `nik`, `name`, `department`, `location`, `status`, `attendance_type`, `euclidean_distance`, `latitude`, `longitude`, `durasi`, `timestamp`, `created_at`.
- **Rata-rata payload per baris**: $\sim 300\text{ bytes}$.
- **PostgreSQL Tuples & Index Overhead**: Tabel log memiliki indeks pada `timestamp`, `employee_id`, dan `nik`. Total ukuran efektif per baris menjadi $\sim 500\text{ bytes}$ ($0,5\text{ KB}$).

**Akumulasi Penyimpanan Log:**
- **Per Bulan ($624.000$ log)**:
  $$624.000 \times 0,5\text{ KB} \approx 312.000\text{ KB} \approx \mathbf{305\text{ MB / bulan}}$$
- **Per Tahun ($7.488.000$ log - 12 bulan)**:
  $$7.488.000 \times 0,5\text{ KB} \approx 3.744.000\text{ KB} \approx \mathbf{3,66\text{ GB / tahun}}$$

---

### C. Tabel Pendukung (`attendance_requests`, `admin_auth`)
- Pengajuan koreksi log ($500 - 1.000$ transaksi/bulan) + tabel admin: $\approx \mathbf{50\text{ MB / tahun}}$.

---

### 📌 Ringkasan Kebutuhan Database PostgreSQL:
| Komponen | Kapasitas Bulanan | Kapasitas Akumulasi 1 Tahun |
|---|---|---|
| Master Karyawan & Vektor 1024-D | $160\text{ MB}$ (statis) | $160\text{ MB}$ |
| Log Absensi ($7,5$ juta transaksi) | $+305\text{ MB / bulan}$ | $3,66\text{ GB}$ |
| Index, WAL, & System Overhead | $+40\text{ MB / bulan}$ | $\sim 500\text{ MB}$ |
| **TOTAL DATABASE STORAGE** | **$\sim 500\text{ MB}$ (Bulan ke-1)** | **$\approx \mathbf{4,3\text{ GB}}$ (Akhir Tahun ke-1)** |

> [!NOTE]
> Database PostgreSQL Supabase Pro Tier menyediakan kuota dasar **8 GB**, sehingga kapasitas **4,3 GB** untuk 1 tahun penuh masih berada **aman di bawah batas kuota dasar tanpa perlu membayar biaya overage storage database**.

---

## 3. Kalkulasi Supabase File Storage (Bucket Storage)

Kebutuhan bucket storage terbagi menjadi dua: **Foto Snapshot Absensi** dan **Paket Bundle OTA**.

### A. Foto Snapshot Wajah Karyawan
Setiap verifikasi absensi menyertakan foto wajah terkompresi $40\text{ KB}$ ($0,04\text{ MB}$):
- **Volume Foto Harian ($24.000$ foto)**:
  $$24.000 \times 40\text{ KB} \approx 960.000\text{ KB} \approx \mathbf{0,94\text{ GB / hari}}$$
- **Volume Foto Bulanan ($624.000$ foto)**:
  $$624.000 \times 40\text{ KB} \approx 24.960.000\text{ KB} \approx \mathbf{24,38\text{ GB / bulan}}$$
- **Volume Foto 1 Tahun ($7.488.000$ foto)**:
  $$24,38\text{ GB} \times 12 \approx \mathbf{292,5\text{ GB / tahun}}$$

### B. Paket Rilis OTA (`dist-X.X.X.zip`)
- Asumsi $2 \times$ rilis update per bulan: $2 \times 18\text{ MB} = 36\text{ MB/bulan} \approx \mathbf{0,43\text{ GB / tahun}}$.

---

### 📌 Ringkasan Kebutuhan File Storage:
- **Bulan ke-1**: $\sim 24,5\text{ GB}$ (Masuk kuota dasar Pro Tier 100 GB).
- **Bulan ke-4**: $\sim 98\text{ GB}$ (Mendekati kuota dasar 100 GB).
- **Akhir Tahun (Bulan ke-12)**: **$\approx \mathbf{293\text{ GB}}$**.
  - Kuota dasar Supabase Pro: $100\text{ GB}$.
  - Kelebihan (*Overage Storage*): $293\text{ GB} - 100\text{ GB} = 193\text{ GB}$.
  - Biaya tambahan storage: $193\text{ GB} \times \$0,021/\text{GB} \approx \mathbf{\$4,05/\text{bulan}}$.

---

## 4. Kalkulasi Bandwidth Network (Egress)

Network Egress adalah data yang **dikirim keluar dari Supabase ke klien** (Download). Data upload (Ingress) ke Supabase **GRATIS**.

### A. Initial Sync Master Karyawan & Vektor ke HP Mandor
- Setiap HP Mandor mendownload master karyawan ($12.000$ data + vektor): $\approx 115\text{ MB}$ per perangkat.
- Jika $500$ HP Mandor melakukan sync awal / refresh total $1 \times$ per bulan:
  $$500 \times 115\text{ MB} \approx 57.500\text{ MB} \approx \mathbf{56,1\text{ GB / bulan}}$$
*(Jika menggunakan Delta Sync `.gt('created_at', lastSync)`, konsumsi bulanan turun ke $<10\text{ GB}$)*.

### B. Download Pembaruan OTA (Over-The-Air)
- Setiap kali ada rilis OTA ($18\text{ MB}$), $500$ perangkat mengunduh update:
  $$500 \times 18\text{ MB} = 9.000\text{ MB} \approx 8,8\text{ GB per rilis}$$
- Jika ada $2 \times$ rilis per bulan: $8,8\text{ GB} \times 2 \approx \mathbf{17,6\text{ GB / bulan}}$.

### C. Monitoring Dashboard & Audit Foto (Head Office / Region)
- Pimpinan & Admin Region membuka dashboard untuk audit kehadiran:
  - Pembacaan data log teks tabel: $\approx 15\text{ GB / bulan}$.
  - Peninjauan sampel foto snapshot (asumsi sampel audit $5\%$ dari total foto per bulan):
    $$5\% \times 624.000 \times 40\text{ KB} = 31.200 \times 40\text{ KB} \approx \mathbf{1,22\text{ GB / bulan}}$$

---

### 📌 Ringkasan Kebutuhan Bandwidth Egress Bulanan:
- Master Sync: $\sim 56\text{ GB}$
- OTA Updates: $\sim 18\text{ GB}$
- Dashboard Queries: $\sim 15\text{ GB}$
- Photo Auditing: $\sim 2\text{ GB}$
- **TOTAL EGRESS BULANAN**: **$\approx \mathbf{91\text{ GB / bulan}}$**.

> [!NOTE]
> Kuota dasar Egress Supabase Pro Tier adalah **250 GB / bulan**. Total estimasi konsumsi kita ($\sim 91\text{ GB}$) berada **jauh di bawah batas kuota gratis**, sehingga **tidak ada biaya tambahan untuk bandwidth**.

---

## 5. Ringkasan Kebutuhan & Rekomendasi Paket Supabase

| Parameter Kapasitas | Kebutuhan AgriFace (12.000 TK) | Kuota Supabase Free | Kuota Supabase Pro ($25/bln) | Status Kelayakan |
|---|---|---|---|---|
| **Database Disk** | $4,3\text{ GB}$ (1 Tahun) | $500\text{ MB}$ ❌ | $8\text{ GB}$ ✅ | **Sangat Aman (Tercover)** |
| **File Storage** | $24,5\text{ GB}$ / bln ($\sim 293\text{ GB}$ / thn) | $1\text{ GB}$ ❌ | $100\text{ GB}$ (Overage $\$0,021$/GB) | **Perlu Overage Storage** |
| **Egress (Bandwidth)**| $\sim 91\text{ GB}$ / bulan | $5\text{ GB}$ ❌ | $250\text{ GB}$ / bulan ✅ | **Sangat Aman (Tercover)** |
| **Active Projects** | 24/7 High-Availability | Di-pause jika pasif ❌ | Aktif tanpa jeda ✅ | **Wajib Pro Tier** |
| **Daily Backups** | Wajib untuk data absensi | Tidak ada ❌ | Otomatis 7 hari ✅ | **Wajib Pro Tier** |

---

## 6. Estimasi Rincian Biaya (Cost Breakdown)

### Rekomendasi: **Supabase Pro Tier ($25/bulan)**

1. **Biaya Langganan Pro Tier Pokok**: **$\$25,00$ / bulan**
   - Termasuk Database $8\text{ GB}$ (kita pakai $4,3\text{ GB}$).
   - Termasuk Bandwidth Egress $250\text{ GB}$ (kita pakai $\sim 91\text{ GB}$).
   - Termasuk Storage $100\text{ GB}$ (cukup untuk 4 bulan pertama).

2. **Biaya Tambahan Storage Foto (Mulai Bulan ke-5 s/d Bulan ke-12)**:
   - Bulan ke-1 s/d ke-4: $0 - 98\text{ GB}$ $\to$ **$\$0$** (termasuk kuota).
   - Bulan ke-6 ($\sim 146\text{ GB}$): Kelebihan $46\text{ GB} \times \$0,021 \approx \mathbf{+\$0,96 / \text{bulan}}$.
   - Bulan ke-12 ($\sim 293\text{ GB}$): Kelebihan $193\text{ GB} \times \$0,021 \approx \mathbf{+\$4,05 / \text{bulan}}$.
   - *Rata-rata overage storage per bulan sepanjang tahun pertama*: $\approx \mathbf{\$2,00 / \text{bulan}}$.

3. **Compute Add-on (Opsional untuk Lonjakan Absensi Pukul 06:00 - 07:00 Pagi)**:
   - Default Compute Pro Tier: **Micro (2 Core, 1 GB RAM)**.
   - Dengan $500$ HP Mandor melakukan auto-sync bersamaan pada pagi hari, jika terjadi antrean koneksi, disarankan upgrade ke **Small Compute (2 Core, 2 GB RAM)**: **$+\$10,00 / \text{bulan}$**.

---

### 💵 Total Estimasi Biaya Bulanan & Tahunan:
- **Biaya Bulanan**:
  - Tanpa Compute Add-on: $\approx \mathbf{\$27,00 - \$29,00}$ (sekitar **Rp 435.000 – Rp 465.000 / bulan**).
  - Dengan Small Compute Add-on (Disarankan): $\approx \mathbf{\$37,00 - \$39,00}$ (sekitar **Rp 590.000 – Rp 625.000 / bulan**).
- **Biaya 1 Tahun Penuh**:
  - $\approx \mathbf{\$440 - \$460}$ per tahun (sekitar **Rp 7.000.000 – Rp 7.400.000 / tahun**) untuk melayani **12.000 karyawan panen, 7,5 juta log absensi, dan 500 perangkat mandor**.

---

## 7. Strategi Optimasi & Efisiensi Data

1. **Kompresi Gambar di Sisi HP Klien**:
   - Foto snapshot kamera sebelum dikirim dikompresi ke format **WebP / JPEG (Quality 60%, resolusi $480 \times 640\text{px}$)**. Ukuran file akan turun dari $150\text{ KB}$ menjadi hanya $\sim 35\text{ KB}$, menghemat $70\%$ storage dan bandwidth.
2. **Delta Sync Filter per Kebun**:
   - Mandor unit "Bukit Harapan I" tidak perlu mendownload seluruh $12.000$ data TK grup, melainkan difilter via Supabase query `.eq('nama_kebun', user.kebun)` sehingga per HP hanya mendownload $\sim 400 - 500$ karyawan kebunnya sendiri ($\sim 5\text{ MB}$ per HP).
3. **Automated Archival Job (pg_cron)**:
   - Setelah 12 bulan, buat Supabase pg_cron function yang mengekspor log lama ke cold storage atau menghapus log yang sudah direkap ke laporan bulanan agar database tetap ramping di bawah $5\text{ GB}$ secara berkelanjutan.
