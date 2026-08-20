# 📋 Rancangan Anggaran Biaya (RAB) & Analisis Skalabilitas AgriFace

Dokumen ini berisi analisis performa sistem, Rancangan Anggaran Biaya (RAB) untuk implementasi skala masif, serta perencanaan teknis untuk pengembangan **Mode Offline penuh (PWA & Service Worker)**.

---

## 🔍 1. Analisis Performa: Kenapa Sistem Terasa Berat & Lag?

Sebelum melihat estimasi biaya, sangat penting untuk meluruskan asumsi performa: **Lag/berat pada kamera scanner bukan disebabkan oleh server atau database Supabase gratisan.**

```
Alur Deteksi Wajah (Client-Side Heavy):
[ Kamera HP ] ──(Frame)──> [ Browser HP / CPU & GPU ] ──(AI Inference)──> [ Gambar Mesh/Nodes ]
                                  ↑
             (Proses ini berjalan 100% di HP Anda!)
```

### Penyebab Utama Lag:
1. **Client-Side AI Inference**: Aplikasi menggunakan pustaka `@vladmandic/human` yang menjalankan model jaringan saraf tiruan (neural network) untuk mendeteksi wajah, mesh landmark, kedipan mata, dan ekstraksi embedding 1024-dimensi **secara langsung di browser HP** pada setiap frame.
2. **Iris Detection Aktif**: Deteksi iris mata (`iris: { enabled: true }`) aktif di konfigurasi. Proses ini memakan komputasi GPU/CPU HP yang cukup besar namun tidak kita gunakan untuk kalkulasi absensi.
3. **Ekstraksi Embedding Setiap Frame**: Proses ekstraksi vektor wajah (1024-d) berjalan terus-menerus setiap frame (30 kali per detik). Ini adalah proses terberat bagi chipset HP.

---

## 💰 2. Rancangan Anggaran Biaya (RAB) Skala Masif
*Estimasi untuk operasional perkebunan skala menengah-besar (misal: 10 afdeling, ~1.000 karyawan aktif).*

### A. Biaya Infrastruktur Cloud (Bulanan)

| No | Komponen | Deskripsi | Estimasi Biaya (IDR) |
| :--- | :--- | :--- | :--- |
| 1 | **Supabase Pro Tier** | Database utama dengan kapasitas storage lebih besar, backup harian otomatis, performa query yang dijamin, serta limit request API yang tinggi. | Rp450.000 / bln |
| 2 | **Supabase Storage Add-on** | Penyimpanan foto wajah master karyawan & log absensi (estimasi 50 GB storage). | Rp150.000 / bln |
| 3 | **Vercel Pro (Frontend)** | Hosting frontend dengan performa CDN global super cepat di seluruh Indonesia, jaminan uptime 99.9%, dan analytics. | Rp300.000 / bln |
| 4 | **Render/Railway (Backend)** | Hosting server Express.js dengan spesifikasi dedicated (2 GB RAM, 1 Shared CPU) untuk menjamin sinkronisasi data lancar. | Rp250.000 / bln |
| 5 | **Claude AI Pro** | Akun asisten AI untuk menunjang kebutuhan maintenance kode, troubleshooting realtime, dan update integrasi sistem (diestimasi bulanan dari total $240 / tahun). | Rp320.000 / bln <br> *(~Rp3.840.000 / thn)* |
| **Total** | **Biaya Cloud Bulanan** | | **Rp1.470.000 / bln** <br> *(~Rp17.640.000 / thn)* |

### B. Biaya Perangkat Keras / Device HP Scanner (Investasi Awal)
Untuk mendapatkan pemindaian yang mulus tanpa lag (minimum 15-20 FPS pada pemindaian wajah), direkomendasikan HP Android kelas menengah dengan GPU yang mendukung WebGL/WebAssembly dengan baik:

| No | Komponen | Rekomendasi Spesifikasi | Harga per Unit | Total (10 Afdeling) |
| :--- | :--- | :--- | :--- | :--- |
| 1 | **Android Device** | Samsung Galaxy A15 / Xiaomi Redmi Note 13 (RAM 6GB/8GB, chipset Helio G99 / Snapdragon 685, support WebGL) | Rp2.300.000 | Rp23.000.000 |
| 2 | **Tripod & Ring Light** | Memastikan HP tegak lurus dan pencahayaan wajah stabil di lapangan/pos absensi. | Rp150.000 | Rp1.500.000 |
| **Total** | **Investasi Perangkat** | | | **Rp24.500.000** |

---

## 🛠️ 3. Rencana Optimasi Performa & Mode Offline PWA

Untuk membuat aplikasi sangat ringan di HP murah dan dapat diakses tanpa internet sama sekali, berikut rencana pengembangan fitur selanjutnya:

### Langkah 1: Optimasi AI (Ringan & Cepat)
* **Nonaktifkan Iris Tracking**: Mengubah `iris: { enabled: false }` di konfigurasi Human.js.
* **Throttling Embedding**: Hanya melakukan ekstraksi biometrik wajah (1024-d) setiap **5 frame sekali** (bukan tiap frame), sementara tracking mesh wajah tetap berjalan mulus. Ini akan mengurangi beban CPU HP hingga **60%**.

### Langkah 2: PWA Offline-First (Service Worker)
* **Model Caching**: Service Worker akan men-cache berkas model AI seberat ~20MB langsung ke memori penyimpanan HP saat pertama kali dibuka dengan internet. Setelah itu, model AI bisa dimuat secara offline tanpa internet.
* **Offline Attendance**: Absensi dicatat ke IndexedDB lokal jika HP tidak memiliki koneksi, dan otomatis disinkronisasikan ke Supabase Cloud ketika HP kembali mendapatkan sinyal internet di kantor/empos.

---

> [!TIP]
> Anda tidak perlu langsung meng-upgrade database Supabase ke versi berbayar sekarang. Versi gratisan Supabase sudah sangat mumpuni untuk tahap uji coba lapangan (hingga 500.000 baris data). Prioritas utama adalah mengoptimalkan performa client-side agar tidak lag di HP dan mengaktifkan fitur caching offline.
