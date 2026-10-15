# Panduan Uji Lapangan Biometrik AgriFace (Untuk Mandor & Admin Kebun)

Dokumen ini merupakan panduan praktis pelaksanaan uji verifikasi wajah di lapangan untuk memastikan akurasi absensi pagi dan sore hari setelah penerapan pembaruan template multi-pose v2.

---

## Langkah 1: Mengaktifkan Mode Debug Biometrik
Untuk melihat angka kecocokan (*cosine similarity*) dan sudut wajah secara langsung:
1. Buka aplikasi AgriFace di browser HP atau WebView Android.
2. Buka DevTools/Console (atau via menu Pengaturan Pengembang) dan jalankan perintah:
   ```javascript
   localStorage.setItem('agriface_debug', '1');
   ```
3. Muat ulang (*refresh*) halaman aplikasi.
4. Sekarang setiap frame wajah yang dievaluasi akan mencatat log detil (`[FASE 0 LOG]`) memuat nilai `cosSim`, `yaw`, `pitch`, dan `backend`.

---

## Langkah 2: Pendaftaran Wajah Multi-Pose (Pagi Hari)
1. Pilih satu orang karyawan panen sebagai sampel uji.
2. Buka menu **Karyawan** → Klik tombol **Re-Scan Wajah** pada baris karyawan tersebut.
3. Posisikan kamera sejajar dengan wajah karyawan. Ikuti 3 instruksi di layar:
   - **Tahap 1 (1–3 frame):** Tatap lurus ke arah kamera.
   - **Tahap 2 (4–6 frame):** Putar/tolehkan kepala sedikit ke kiri atau kanan (~15°–20°).
   - **Tahap 3 (7–8 frame):** Kembalikan wajah menatap lurus ke depan.
4. Tunggu hingga muncul tanda hijau `✓ Sampel Lengkap (8/8)`.
5. Catat kondisi fisik saat pendaftaran:
   - Waktu pendaftaran (contoh: 07:15 WIB).
   - Kondisi cahaya (contoh: di bawah teras kantor afdeling / luar ruangan teduh).
   - Lokasi & tipe HP (contoh: Samsung A14, kamera belakang).

---

## Langkah 3: Uji Scan Absensi Pagi Hari
1. Buka menu **Absensi** → Pilih nama karyawan sampel tersebut.
2. Lakukan pemindaian wajah (*Check-In*).
3. Pastikan proses verifikasi lolos (hijau / `MATCHED`) dalam rentang waktu < 3 detik.
4. Catat waktu keberhasilan (misal: 07:30 WIB).

---

## Langkah 4: Uji Scan Absensi Sore Hari (Hari yang Sama)
1. Pada pukul 14:30 – 16:30 WIB (saat karyawan pulang kerja/apel sore), lakukan pemindaian ulang pada **HP yang sama**:
   - Catat kondisi cahaya sore (apakah ada sinar matahari miring / silau / bayangan topi).
   - Amati apakah verifikasi wajah langsung lolos atau membutuhkan penyesuaian sudut.
2. **Uji Lintas Perangkat (Cross-Device):**
   - Jika tersedia HP operasional mandor lain, buka aplikasi di HP kedua.
   - Pastikan HP kedua sudah melakukan sinkronisasi data master (buka menu *Sinkronisasi Data Offline* → tab *Integritas Vektor*).
   - Lakukan scan absensi sore hari untuk karyawan yang sama di HP kedua tersebut.

---

## Langkah 5: Pembacaan Hasil melalui Supabase
Buka **Supabase Dashboard** → **SQL Editor**, lalu jalankan kueri analitik dari berkas `docs/diagnostics.sql`:
- **Kueri No. 3:** Melihat statistik sesi scan per jam (rata-rata cosine dan jumlah outcome `MATCHED` vs `TIMEOUT` vs `CANCELLED`).
- **Kueri No. 5:** Membandingkan nilai `max_cosine` pagi vs sore pada hari yang sama beserta selisih penurunannya (*selisih_drop_sore*).

---

## Tabel Tafsir Hasil Lapangan

| Temuan Angka Cosine / Status | Kemungkinan Penyebab | Tindakan Rekomendasi |
|---|---|---|
| **Cosine sore tepat di bawah 0.92** (misal: 0.89 – 0.91) | Variasi pose/ekspresi sore belum sepenuhnya terwakili di template, atau ambang 0.92 terlalu ketat untuk kondisi cahaya ekstrim sore. | Daftarkan ulang dengan memastikan saat tahap toleh kepala dilakukan dengan wajar. Laporkan nilai median cosine ke tim engineering untuk kalibrasi ambang telemetri. |
| **Cosine sore jauh di bawah 0.92** (misal: < 0.80) | Vektor master di HP berbeda dari cloud (cache usang), kamera kotor/blur, orientasi terbalik, atau orang yang berbeda. | Buka tab *Integritas Vektor*, cocokkan *Checksum Lokal* vs *Checksum Cloud*. Bersihkan lensa kamera HP. |
| **Status "Beda Checksum" di tab Integritas Vektor** | Cache lokal di HP tertinggal / belum tersinkronisasi sempurna dari cloud. | Tekan tombol **"Tarik Cloud"** pada baris karyawan tersebut di tab Integritas Vektor. |
| **Cosine tinggi (>= 0.92) tetapi absensi tidak tersimpan** | Kendala jaringan, timeout pengiriman log, atau karyawan sudah memiliki absensi lengkap hari ini. | Periksa apakah pesan "Absensi Hari Ini Sudah Lengkap" muncul. Pastikan antrean lokal tersimpan di menu *Sinkronisasi Offline*. |
