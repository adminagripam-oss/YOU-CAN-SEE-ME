# PROMPT LANJUTAN — Audit Perbaikan, Pendaftaran Multi-Pose, dan Persiapan Uji Lapangan

Salin seluruh isi di bawah garis ini ke AI coding assistant.

---

## Konteks

Anda baru menyelesaikan perbaikan integritas vektor dan fitur preview foto karyawan (checksum FNV-1a, perbaikan `finalTemplate`/`avgVec`, `scan_attempts`, `employee_face_photos`, `EmployeeFacePreviewModal`, tab "Integritas Vektor"). Laporan Anda menyatakan "diverifikasi 100%", tetapi yang terbukti hanya 40 unit test dan build yang lolos. Sekarang lakukan audit atas pekerjaan Anda sendiri, perbaiki yang kurang, dan siapkan uji lapangan.

Masalah asli yang belum terbukti selesai: wajah didaftarkan pagi hari dan check-in berhasil, tetapi sore hari tidak match untuk orang yang sama.

## Aturan keras

1. Jangan commit, push, bump versi, atau membuat paket ZIP/OTA. Biarkan di working tree.
2. Jangan mengubah `MATCH_COSINE_THRESHOLD` (0.92) atau `DUPLICATE_COSINE_THRESHOLD` (0.88). Ambang hanya boleh diubah setelah ada data telemetri.
3. Jangan menyebut sesuatu "terverifikasi" bila hanya dibaca dan belum dijalankan. Untuk setiap temuan, tulis bukti berupa `file:baris` atau keluaran perintah. Bagian yang butuh perangkat asli ditulis sebagai "BELUM DIVERIFIKASI, butuh uji di HP".
4. Jangan mengimplementasikan JWT, Supabase Auth, atau mengubah model akses. Itu keputusan manajemen. Untuk soal akses cukup laporkan keadaan sebenarnya.
5. Kerjakan berurutan: Tugas A (audit, baca saja), lalu perbaikan, lalu B, C, D. Setelah tiap tugas jalankan `cmd /c "npm test"` dan di akhir `cmd /c "npm run build"`.

---

## TUGAS A — Audit mandiri (8 butir)

Untuk tiap butir keluarkan baris: `PASS` atau `FAIL` atau `BELUM DIVERIFIKASI`, disertai bukti. Baru setelah semuanya dilaporkan, perbaiki yang `FAIL`.

**A1. Keputusan unduh ulang vektor (`App.jsx`, sekitar baris 344–380).**
- Keputusan unduh harus berdasarkan `vector_checksum` lokal ≠ `vector_checksum` cloud.
- `updated_at` tidak boleh dipakai sebagai "lokal lebih tua dari server", karena jam HP dan jam server berbeda. Bila dipakai, hanya untuk perbandingan sama-dengan antara dua nilai yang sama-sama berasal dari server.
- Bila checksum cloud `NULL`, perlakukan sebagai "tidak diketahui": jangan unduh ulang massal. Lihat A3.
- Bila checksum lokal `NULL` dan cloud ada, unduh (cache lokal dianggap usang).

**A2. Konversi vektor yang tersimpan sebagai string JSON.**
- Pastikan `20261015_alter_master_descriptors.sql` memuat konversi berikut, idempotent dan aman dijalankan berulang:
  ```sql
  UPDATE master_descriptors
  SET descriptor_json = (descriptor_json #>> '{}')::jsonb
  WHERE jsonb_typeof(descriptor_json) = 'string';
  ```
  `descriptor_json::jsonb` saja tidak mengubah apa pun, jadi jangan dipakai.
- Tambahkan di berkas yang sama sebuah komentar berisi kueri verifikasi:
  `SELECT jsonb_typeof(descriptor_json), count(*) FROM master_descriptors GROUP BY 1;` (hasil yang benar: hanya `array`).
- Pastikan juga kode klien yang membaca `descriptor_json` bisa menerima keduanya (array atau string yang di-`JSON.parse`), selama masa transisi.

**A3. Baris lama tanpa checksum.**
- Hitung ulang secara lazy di klien: saat vektor cloud dimuat dan `vector_checksum` kosong, hitung dengan `vectorChecksum()` lalu `update` kolom `vector_checksum` (dan `template_version` bila kosong). Hanya satu kali per baris, tanpa memicu unduh massal.
- Tab "Integritas Vektor" harus menampilkan baris ber-checksum kosong dengan status "belum dihitung", bukan "tidak cocok".

**A4. Akses foto di Storage (hanya laporkan).**
- Buka `20261015_create_employee_face_photos.sql`. Tuliskan apa adanya: bucket public atau private, policy `SELECT/INSERT/UPDATE/DELETE` untuk role `anon` dan `authenticated`, dan path objek.
- Jelaskan dalam satu paragraf: kunci anon tertanam di APK, jadi policy yang mengizinkan `anon` membuat signed URL berarti foto dapat diakses siapa pun yang memiliki kunci itu.
- Jangan ubah model akses. Cukup tambahkan komentar di berkas SQL yang menandai ini sebagai "keputusan terbuka: Pilihan A atau JWT".

**A5. Label fallback foto di modal.**
- Bila modal memakai fallback foto absensi terakhir, tampilkan label jelas "Bukan foto master (foto absensi terakhir)" beserta tanggalnya, dan jangan tampilkan seolah foto resmi.
- Bila tidak ada foto sama sekali, tampilkan keadaan kosong dengan tombol Re-Scan Wajah.

**A6. Crop foto pendaftaran (`enrollmentPhoto.js`).**
- Ekstrak perhitungan kotak crop menjadi fungsi murni `computeCropRect(boundingBox, canvasW, canvasH, margin)` dan tulis unit test: margin 25% diterapkan, hasil di-clamp ke batas kanvas, mode landscape (640×480) dan portrait (480×640), kotak di tepi kanvas, kotak lebih besar dari kanvas.
- Pastikan `boundingBox` dipakai langsung terhadap kanvas hasil center-crop 4:3 tanpa pemetaan ulang, dan foto yang disimpan tidak di-flip. Mirror kamera depan hanya CSS.
- Bila kode melakukan flip atau pemetaan koordinat tambahan, hapus.

**A7. Telemetri `scan_attempts`.**
- Pastikan percobaan scan yang berakhir karena timeout atau gagal match juga dicatat, bukan hanya `CANCELLED` yang ditutup manual. Setiap percobaan yang gagal wajib mencatat `matched_cosine` tertinggi yang terbaca, `match_threshold`, `template_version`, `facing_mode`, dan stempel waktu perangkat.
- `match_threshold` harus dibaca dari `BIOMETRIC_CONFIG.MATCH_COSINE_THRESHOLD`, bukan angka 0.92 yang ditulis langsung.
- Tambahkan catatan jumlah frame yang valid dan `consistency` bila tersedia pada percobaan itu.
- Untuk percobaan scan 1-to-1, `employee_id` yang diklaim boleh tercatat. Untuk `ENROLL-DUPLICATE`, tetap tanpa identitas karyawan yang mirip.

**A8. Migrasi skema lokal.**
- Dexie: pastikan nomor versi dinaikkan (dari v9) dan tabel `employee_face_thumbs` ditambahkan lewat `.version(n).stores(...)` tanpa menghapus tabel lain.
- SQLite (`sqliteService.ts`): `local_employee_face_thumbs` dan kolom baru harus dibuat lewat jalur migrasi yang sudah ada dengan `CREATE TABLE IF NOT EXISTS` / `ALTER TABLE ... ADD COLUMN` yang dibungkus pemeriksaan kolom sudah ada. Aplikasi lama yang menerima OTA tidak boleh crash saat dibuka.
- Tulis unit test untuk jalur migrasi bila memungkinkan (mis. skema lama → skema baru), atau jelaskan mengapa tidak bisa diuji otomatis.

---

## TUGAS B — Pendaftaran wajah multi-pose (dugaan penyebab utama masalah asli)

**Temuan:** pendaftaran mengambil 5 frame dalam waktu kurang dari 200 ms, sehingga template hanya mewakili satu kondisi (satu cahaya, satu sudut, satu ekspresi). Template sempit ini cocok dengan scan pagi, tetapi mudah gagal ketika cahaya atau sudut berubah.

Larangan sebelumnya untuk mengubah alur pengambilan sampel pendaftaran dicabut khusus untuk tugas ini.

1. Baca `buildTemplateDetailed` di `biometrics.js` dan kode pemanggilnya, lalu laporkan dulu: dari mana 5 frame diambil, selang waktunya, dan di mana `consistency` dihitung.
2. Ubah pengambilan sampel menjadi tersebar waktu:
   - Kumpulkan sedikitnya 8 frame valid dalam kira-kira 2–3 detik, dengan jarak minimal antar-frame yang diterima 250 ms (konstanta di `BIOMETRIC_CONFIG`, mis. `ENROLL_MIN_FRAME_GAP_MS`, `ENROLL_TARGET_FRAMES`, `ENROLL_MAX_DURATION_MS`).
   - Tiap frame harus lolos gerbang kualitas yang sudah ada (ukuran wajah, kepercayaan deteksi, wajah tunggal). Bila gerbang kualitas yang ada kurang, tambahkan batas sudut kepala yang longgar (yaw/pitch).
   - Instruksi di UI selama pengambilan: "Tatap lurus", lalu "Putar kepala sedikit", lalu "Kembali lurus", dengan indikator kemajuan.
   - Agregasi tetap median per dimensi (seperti sekarang), kemudian normalisasi L2.
   - `ENROLL_MIN_CONSISTENCY` 0.90 tetap berlaku. Bila konsistensi gagal, minta ulang pengambilan.
3. Template baru diberi `template_version: 2`. Template versi 1 yang sudah ada tetap valid dan tidak dimigrasi paksa. Pencocokan tidak berubah (1-to-1, cosine, ambang 0.92).
4. Pisahkan logika pemilihan frame menjadi fungsi murni (mis. `selectEnrollmentFrames`) agar dapat diuji dengan fake timer. Test: frame terlalu rapat ditolak, frame berkualitas rendah ditolak, batas durasi menghentikan pengambilan, jumlah frame kurang menghasilkan hasil "tidak cukup", konsistensi di bawah 0.90 menghasilkan permintaan ulang.
5. Telemetri: catat `template_version` pada setiap `scan_attempts` dan pendaftaran, agar data v1 dan v2 dapat dibandingkan di lapangan.

---

## TUGAS C — Alat diagnostik untuk uji lapangan

Buat `docs/diagnostics.sql` berisi kueri baca-saja berikut (sesuaikan nama kolom dengan migrasi sebenarnya; bila berbeda, laporkan perbedaannya):

1. Distribusi tipe data vektor: `jsonb_typeof(descriptor_json)` per jumlah.
2. Jumlah baris dengan `vector_checksum IS NULL`, dan jumlah baris dengan `template_version` 1 dan 2.
3. Percobaan scan 7 hari terakhir per jam (zona waktu Asia/Jakarta): jumlah, rata-rata `matched_cosine`, persentil 10 dan 50, jumlah `outcome` per jenis.
4. Karyawan dengan percobaan gagal terbanyak, beserta `matched_cosine` tertingginya.
5. Pasangan percobaan pagi (sebelum 10:00) dan sore (setelah 14:00) untuk karyawan yang sama pada hari yang sama, beserta selisih `matched_cosine`.
6. Jumlah penolakan `ENROLL-DUPLICATE` per hari.

Di tab "Integritas Vektor" pada `SinkronisasiPage.jsx`, tambahkan ringkasan lokal bila belum ada: jumlah vektor lokal, jumlah yang checksumnya berbeda dari cloud, dan jumlah yang belum dihitung.

---

## TUGAS D — Panduan uji lapangan

Buat `docs/UJI_LAPANGAN.md` berbahasa Indonesia, singkat, untuk mandor. Isinya langkah berikut:

1. Aktifkan debug: `localStorage.setItem('agriface_debug','1')`, lalu muat ulang.
2. Daftarkan ulang satu karyawan di pagi hari dengan alur multi-pose baru. Catat kondisi cahaya dan lokasi.
3. Scan check-in di pagi hari. Lalu scan lagi di sore hari pada HP yang sama.
4. Ulangi sore hari di HP lain (bila ada) untuk membandingkan perangkat.
5. Hasilnya dibaca dengan kueri nomor 3 dan 5 dari `docs/diagnostics.sql`.
6. Tabel tafsir singkat:
   - nilai `cosSim` sore tepat di bawah 0.92 → template masih terlalu sempit atau ambang terlalu ketat;
   - nilai jauh di bawah 0.92 (mis. di bawah 0.8) → curigai vektor berbeda antar perangkat (cek checksum) atau kamera/orientasi berbeda;
   - checksum lokal dan cloud berbeda → sinkronisasi vektor bermasalah;
   - nilai tinggi tetapi gagal → periksa alur lain (ambang, kualitas frame, kondisi jaringan).

---

## Format laporan akhir

1. Tabel A1–A8: status, bukti `file:baris`, dan perbaikan yang dilakukan.
2. Daftar berkas yang diubah atau dibuat per tugas.
3. Keluaran `npm test` (jumlah test) dan `npm run build`.
4. Bagian "BELUM DIVERIFIKASI, butuh uji di HP": semua hal yang hanya bisa dipastikan di perangkat asli.
5. Daftar migrasi SQL yang harus dijalankan di Supabase beserta urutannya, dan konfigurasi Storage yang harus dibuat manual.
6. Tidak ada commit, push, atau bump versi.
