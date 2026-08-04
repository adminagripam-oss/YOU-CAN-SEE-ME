# Rancangan Anggaran Biaya (RAB)
## AgriFace: Implementasi Sistem Absensi Biometrik Wajah Pemanen Kebun Berbasis Offline-First PWA

- **Nama Proyek**: AgriFace (AgriFace Biometric Attendance System)
- **Judul Proyek**: Pengadaan & Implementasi Sistem Absensi Biometrik Wajah Pemanen Kelapa Sawit / Agrikultur
- **Lokasi Target**: Seluruh Afdeling & Kebun Perusahaan
- **Pendekatan Arsitektur**: Offline-First PWA (Smartphone Mandor) + Cloud Auto-Sync
- **Target Pengguna**: Mandor Panen (User Scan) & Pemanen Kebun (Objek Absensi)
- **Tanggal Dokumen**: 3 Agustus 2026

---

## 1. Asumsi Skala Implementasi & Parameter Operasional

Guna memberikan estimasi biaya yang presisi, RAB ini disusun berdasarkan **Model Kebun Standar (1 Perusahaan / 5 Kebun / 25 Afdeling)** dengan asumsi parameter berikut:

| Parameter Operasional | Jumlah / Nilai Asumsi | Keterangan |
| :--- | :--- | :--- |
| **Total Kebun (Estate)** | 5 Kebun | Kebun A, B, C, D, E |
| **Total Afdeling** | 25 Afdeling | @ 5 Afdeling per Kebun |
| **Jumlah Mandor Panen** | 50 Mandor | Perangkat Android dipegang oleh Mandor Panen |
| **Jumlah Pemanen (Workers)** | 1.500 Pemanen | @ 30 Pemanen di bawah 1 Mandor |
| **Kondisi Jaringan Field** | 80% Blank Spot / Offline | Menggunakan teknologi **IndexedDB Offline Cache** |
| **Lokasi Sinkronisasi** | Kantor Afdeling / Basecamp | Sync otomatis saat HP Mandor terhubung Wi-Fi/Cellular Basecamp |

---

## 2. Rincian Biaya Investasi Awal (CAPEX - Capital Expenditure)

### A. Pengadaan Perangkat Keras Lapangan (Field Hardware & Accessories)

Mandor membutuhkan perangkat Android yang tahan debu, hujan, benturan di lapangan (Ruggedized), serta memiliki daya tahan baterai tinggi.

| No | Komponen / Spesifikasi Hardware | Satuan | Qty | Harga Satuan (IDR) | Total Biaya (IDR) |
| :-: | :--- | :-: | :-: | :-: | :-: |
| A1 | **Ruggedized Android Smartphone**<br>*(Specs: IP68 Waterproof/Dustproof, Octa-Core, RAM 6GB, Battery 6000mAh, Cam 16MP)* | Unit | 55 | Rp 3.500.000 | Rp 192.500.000 |
| A2 | **Heavy Duty Powerbank Lapangan (20.000 mAh IP67)** | Unit | 55 | Rp 450.000 | Rp 24.750.000 |
| A3 | **Casing Heavy Duty + Lanyard / Harness Dada Mandor** | Pcs | 55 | Rp 150.000 | Rp 8.250.000 |
| A4 | **Perangkat Backup / Cadangan Kebun (Spare Devices)** | Unit | 5 | Rp 3.500.000 | Rp 17.500.000 |
| **SUBTOTAL A (HARDWARE)** | | | | | **Rp 243.000.000** |

*Catatan: 55 unit mencakup 50 Mandor Panen + 5 unit cadangan di Kantor Kebun Central.*

---

### B. Kustomisasi Sistem & Fitur Khusus Perkebunan (Field Adaptation Software)

Penyesuaian sistem absensi eksisting agar selaras dengan operasional perkebunan:

| No | Deskripsi Pekerjaan / Fitur Tambahan | Qty | Harga (IDR) | Total Biaya (IDR) |
| :-: | :--- | :-: | :-: | :-: |
| B1 | **Integrasi Geofencing & GPS Location Tagging**<br>*(Mencatat koordinat Latitude/Longitude Blok & TPH saat scan)* | 1 Paket | Rp 15.000.000 | Rp 15.000.000 |
| B2 | **Modul Input Hasil Panen / TJJ (Tandan Jadi Janjang)**<br>*(Add-on modul memasukkan estimasi janjar bersamaan dengan absensi)* | 1 Paket | Rp 20.000.000 | Rp 20.000.000 |
| B3 | **Peningkatan Anti-Spoofing & Liveness Detection Lapangan**<br>*(Deteksi gerakan mikro wajah untuk cegah foto cetak di kebun)* | 1 Paket | Rp 12.500.000 | Rp 12.500.000 |
| B4 | **Uji Coba Lapangan (Pilot Project Test) di 1 Kebun Sample** | 1 Paket | Rp 10.000.000 | Rp 10.000.000 |
| **SUBTOTAL B (SOFTWARE ADAPTATION)** | | | | **Rp 57.500.000** |

---

### C. Implementasi, Master Data Enrollment & Pelatihan (Deployment & Training)

Proses mendaftarkan 1.500 pemanen dan melatih 50 Mandor serta Staf Afdeling:

| No | Kegiatan Implementasi | Volume | Satuan | Harga Satuan (IDR) | Total Biaya (IDR) |
| :-: | :--- | :-: | :-: | :-: | :-: |
| C1 | **Enrollment Biometrik Master 1.500 Pemanen**<br>*(Pengambilan sampel foto & ekstrak 128-vector di seluruh afdeling)* | 1.500 | Orang | Rp 10.000 | Rp 15.000.000 |
| C2 | **Pelatihan Operasional Mandor (ToT - Training of Trainers)**<br>*(Pelatihan 50 Mandor & 25 Asisten Afdeling)* | 5 | Kebun | Rp 3.000.000 | Rp 15.000.000 |
| C3 | **Akomodasi & Biaya Perjalanan Tim Rollout Lapangan** | 1 | Paket | Rp 12.000.000 | Rp 12.000.000 |
| **SUBTOTAL C (DEPLOYMENT & TRAINING)** | | | | | **Rp 42.000.000** |

---

## 3. Rincian Biaya Operasional & Pemeliharaan Tahunan (OPEX - Operational Expenditure)

Biaya rutin tahunan untuk menjaga server cloud, konektivitas, serta dukungan teknis:

| No | Komponen OPEX (Rutin Tahunan) | Periode | Biaya / Bulan (IDR) | Total Biaya / Tahun (IDR) |
| :-: | :--- | :-: | :-: | :-: |
| D1 | **Supabase Cloud Database Pro Tier Plan**<br>*(Penyimpanan data 1.500 pemanen, unlimited API requests, daily backup)* | 12 Bulan | Rp 450.000 | Rp 5.400.000 |
| D2 | **VPS / Cloud Server Backend Hosting (API Engine)**<br>*(Specs: 4 vCPU, 8GB RAM, High Availability VPS)* | 12 Bulan | Rp 750.000 | Rp 9.000.000 |
| D3 | **Paket Data SIM Card M2M / Telkomsel Enterprise (55 HP Mandor)**<br>*(Quota 10GB/bulan per HP untuk auto-sync)* | 12 Bulan | Rp 2.750.000 | Rp 33.000.000 |
| D4 | **Pemeliharaan Sistem & Support SLA (System Maintenance)**<br>*(Bug fixes, update keamanan, bantuan teknis 24/7)* | 12 Bulan | Rp 2.500.000 | Rp 30.000.000 |
| **SUBTOTAL D (OPEX TAHUNAN)** | | | | **Rp 77.400.000** |

---

## 4. Rekapitulasi Total Anggaran Biaya (Grand Total RAB)

```
+-----------------------------------------------------------------------------------+
|                        REKAPITULASI RANCANGAN ANGGARAN BIAYA                      |
+-----------------------------------------------------------------------------------+
| A. Pengadaan Hardware Lapangan (55 HP Rugged + Acc)        : Rp 243.000.000       |
| B. Kustomisasi Software & Fitur Perkebunan                 : Rp  57.500.000       |
| C. Enrollment Master Data, Deployment & Pelatihan          : Rp  42.000.000       |
+-----------------------------------------------------------------------------------+
| **TOTAL INVESTASI AWAL (CAPEX TAHUN KE-1)**                : **Rp 342.500.000**   |
+-----------------------------------------------------------------------------------+
| D. Biaya Operasional & Server (OPEX Rutin per Tahun)       : **Rp  77.400.000**   |
+-----------------------------------------------------------------------------------+
| **GRAND TOTAL BIAYA TAHUN PERTAMA (CAPEX + OPEX TAHUN 1)** : **Rp 419.900.000**   |
+-----------------------------------------------------------------------------------+
```

---

## 5. Analisis Kelayakan & Return on Investment (ROI)

Implementasi Biometrik Wajah di Perkebunan memberikan dampak efisiensi finansial yang signifikan:

### 1. Eliminasi Absensi Fiktif / *Ghost Worker*
- **Masalah**: Pada absensi manual (buku/kertas), risiko pekerja fiktif (*titip absen*) diperkirakan berkisar **3% - 5%** dari total biaya tenaga kerja.
- **Penghematan**: 
  - Jika gaji rata-rata pemanen = **Rp 3.500.000 / bulan**.
  - Kebocoran 3% dari 1.500 pemanen = 45 pekerja fiktif $\times$ Rp 3.500.000 = **Rp 157.500.000 / bulan** (**Rp 1,89 Miliar / tahun**).
  - Dengan biometrik wajah $O(1)$, kebocoran ini **dihapus hingga 0%**.

### 2. Efisiensi Waktu & Akurasi Rekapitulasi Gaji (Payroll)
- Dulu butuh waktu **3-5 hari** di akhir bulan untuk input manual ribu lembar kertas absensi dari afdeling.
- Sekarang rekapitulasi data absensi & durasi kerja tersedia secara **real-time** begitu Mandor terhubung ke jaringan internet Basecamp (Auto-Sync).

### 3. Projected Payback Period (Masa Pengembalian Modal)
- Total Investasi Tahun Pertama = **Rp 419.900.000**.
- Penghematan Kebocoran Absensi per Bulan = **Rp 157.500.000**.
- **Estimasi Payback Period**: **$< 3 \text{ Bulan}$** operasional.

---

## 6. Jadwal Pelaksanaan (Timeline Implementasi)

| Minggu | Kegiatan Utama | Output Target |
| :-: | :--- | :--- |
| **M1 - M2** | Pengadaan Hardware (HP Rugged + Acc) & Setup Cloud Server | Hardware siap & Supabase Cloud aktif |
| **M3 - M4** | Development Fitur Geofencing GPS & Testing Field Offline Mode | Aplikasi Siap Diinstall ke HP Mandor |
| **M5 - M6** | Pilot Project di 1 Kebun (Sample 300 Pemanen & 10 Mandor) | Evaluasi & Feedback Lapangan |
| **M7 - M8** | Enrollment Biometrik Master 1.500 Pemanen di 5 Kebun | Master Data 100% Terdaftar |
| **M9** | Pelatihan Mandor & Launching Full Production | Go-Live 100% Kebun |
