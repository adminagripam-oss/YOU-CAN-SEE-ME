# PROMPT TAMBALAN — Pendaftaran Multi-Pose Gagal, Jalur Rescan ke Check-in/Check-out, Foto Master Offline

Salin seluruh isi di bawah garis ini ke AI coding assistant.

---

## Konteks

Laporan terakhir Anda (A1–A8, Tugas B–D, 57 test lolos, build lolos) sudah diterima. Hasil uji manual pengguna menunjukkan:

1. **Pendaftaran wajah multi-pose (v2) berjalan terlalu cepat lalu gagal** pada tahap 1 sampai 8, sehingga karyawan tidak bisa didaftarkan.
2. Masalah asli belum terbukti selesai: karyawan yang di-rescan tidak bisa check-out karena wajah tidak match.
3. Foto master wajah harus tersedia offline (aplikasi ini offline-first) dan diperbarui otomatis saat sinkronisasi.

Unit test yang hijau tidak membuktikan hal-hal di atas, karena test memakai frame sintetis dengan sudut dan jeda yang ideal.

## Aturan keras

1. Jangan commit, push, bump versi, atau buat paket ZIP/OTA.
2. Jangan ubah `MATCH_COSINE_THRESHOLD` (0.92) dan `DUPLICATE_COSINE_THRESHOLD` (0.88).
3. Jangan menyatakan "terverifikasi" untuk hal yang hanya dibaca. Beri bukti `file:baris` atau keluaran perintah, dan tandai "BELUM DIVERIFIKASI, butuh uji di HP" bila perlu perangkat.
4. Jangan memilih sendiri model akses (Public/Private bucket, JWT). Itu keputusan manajemen.
5. Kerjakan berurutan Tugas 1 → 4. Setelah tiap tugas jalankan `cmd /c "npm test"`; di akhir jalankan `cmd /c "npm run build"`.

---

## TUGAS 1 — Pendaftaran multi-pose gagal dan terlalu cepat (prioritas, memblokir)

### 1a. Diagnosis dulu (baca kode, jangan ubah dulu)

Jawab dengan bukti `file:baris`:

1. Apa arti "tahap 1 sampai 8" di UI? Apa yang memajukan tahap: waktu yang berlalu, atau jumlah frame yang diterima?
2. Kapan pendaftaran dinyatakan gagal, dan apa persis pesan atau kode kegagalannya? Tulis semua jalur gagal (timeout 3500 ms, frame kurang dari target, konsistensi di bawah `ENROLL_MIN_CONSISTENCY`, wajah hilang, yaw tidak tercapai).
3. Fase "miring" meminta yaw ±0.35 rad (≈20°). Periksa:
   - apakah gerbang kualitas yang sudah ada menolak frame dengan yaw sebesar itu (konflik: fase meminta belok, gerbang menolak frame belok);
   - apakah tanda yaw terbalik pada kamera depan yang di-mirror (mirror hanya CSS; nilai yaw dari `@vladmandic/human` tidak ikut terbalik);
   - apakah `face.rotation.angle.yaw` memang tersedia pada konfigurasi Human yang dipakai (modul `description`/`mesh` aktif).
4. Dengan jeda 250 ms dan inferensi 200–250 ms per frame di ponsel kelas bawah, berapa frame yang realistis masuk dalam 3500 ms? Hitung dan tulis.
5. Apakah total waktu pendaftaran cukup bagi manusia membaca instruksi "Lurus → Miringkan sedikit → Lurus kembali" dan melakukannya? Tulis kesimpulan jujur.

### 1b. Reproduksi

Tambahkan test (di `multipose_enrollment.test.js`) yang mensimulasikan aliran frame realistis: 4 fps dan 5 fps, yaw bervariasi kecil (±0.05–0.15 rad), kadang wajah hilang, tanpa belokan sampai 0.35 rad. Buktikan bahwa implementasi sekarang gagal pada kondisi itu.

### 1c. Perbaikan

Rancang ulang agar pendaftaran **tidak bergantung pada belokan besar dan tidak dibatasi waktu sempit**. Semua angka di `BIOMETRIC_CONFIG`:

- Syarat wajib: minimal 6 frame frontal-ish (|yaw| ≤ 0.15 rad) yang diterima dengan jarak minimal 300–400 ms, tersebar minimal 2,5 detik.
- Belokan bersifat **oportunistik**: frame dengan |yaw| antara 0.12 dan 0.30 rad yang lolos gerbang kualitas ikut dihitung bila muncul. Jika tidak muncul, pendaftaran tetap boleh selesai dengan template frontal (versi template tetap dicatat apa adanya, mis. `template_version: 2` hanya bila ada frame belok yang diterima, selain itu `1`).
- Batas waktu total 12 detik. Kemajuan tahap dihitung dari frame yang **diterima**, bukan dari waktu. Tampilkan "Tahap n/8" bertambah hanya saat frame diterima.
- Jika wajah hilang, jeda hitungan (jangan reset), tampilkan "Arahkan wajah ke bingkai".
- Konsistensi `ENROLL_MIN_CONSISTENCY` 0.90 tetap berlaku.
- Pesan gagal harus menyebut **alasan spesifik** (mis. "Wajah terlalu jauh", "Terlalu gelap", "Konsistensi rendah: ulangi dengan cahaya lebih merata") dan jumlah frame yang diterima, bukan pesan umum.
- Setelah 2 kali gagal berturut-turut, tampilkan tombol "Daftar mode lurus saja" yang memakai frame frontal saja dan memberi peringatan bahwa akurasi sore hari bisa lebih rendah.
- Catat tiap percobaan pendaftaran (berhasil atau gagal) ke `scan_attempts` dengan `attempt_type: 'ENROLL'`, kode alasan, jumlah frame diterima/ditolak, durasi, `facing_mode`, dan `template_version`.

Tambahkan test: aliran 4 fps tanpa belokan besar harus **berhasil** dengan template frontal; aliran dengan belokan ikut dihitung; wajah hilang menjeda, bukan mereset; batas 12 detik menghasilkan pesan alasan yang benar.

---

## TUGAS 2 — Buktikan jalur rescan → check-in dan check-out memakai vektor yang baru

Tujuan: memastikan karyawan yang di-rescan benar-benar dipindai terhadap vektor barunya, di perangkat yang melakukan rescan maupun perangkat lain.

1. Telusuri dan tuliskan alur lengkap: rescan di HP X → penyimpanan lokal (Dexie, SQLite) → antrean sinkron → `master_descriptors` Supabase → pengunduhan di HP Y.
2. Telusuri jalur **check-in dan check-out** di `TabFaceVerification.jsx`: dari mana vektor master dibaca (`loadMasterVectors`), dan apakah check-out memakai pemuat yang sama dengan check-in.
3. Periksa **urutan prioritas tier**: Tier 0 props (`off_`/`tmp_`), Tier 1 cache lokal, Tier 2 `master_descriptors`, Tier 4 props legacy. Jika Tier 0 atau Tier 4 mengalahkan cache yang baru, vektor hasil rescan akan diabaikan. Tulis temuan dan bukti. Bila ada, perbaiki: vektor hasil rescan harus menggantikan atau membatalkan entri lama di semua tier.
4. Setelah rescan di perangkat yang sama, pastikan cache lokal (Dexie **dan** SQLite) diperbarui seketika beserta `vector_checksum`, tanpa menunggu sinkronisasi.
5. Tambahkan ke `scan_attempts` dan ke `local_attendance_logs`: `vector_checksum_used` (checksum template yang dipakai saat scan) dan `vector_source` (tier asal: cache, cloud, props). Dengan ini di lapangan bisa dibuktikan vektor mana yang dipakai pada tiap scan.
6. Tulis test dengan penyimpanan tiruan: (a) rescan lalu scan di perangkat yang sama memakai vektor baru; (b) perangkat lain yang punya vektor lama mengunduh yang baru lewat perbedaan checksum; (c) entri Tier 0 lama tidak mengalahkan vektor hasil rescan.
7. Pastikan kolom pada `scan_attempts` di SQLite (`max_cosine`, `frames_used`, `threshold_used`, dan lain-lain) **sama persis dengan kolom tabel Supabase** di `20261015_create_scan_attempts.sql`, dan bahwa `syncPendingScanAttempts()` memetakannya dengan benar. Bila berbeda, sinkronisasi telemetri diam-diam gagal. Tambahkan test pemetaan.

---

## TUGAS 3 — Foto master wajah tersedia offline dan selalu terbaru

Saat ini foto yang diambil di perangkat pendaftar tersimpan lokal dan diunggah saat online, tetapi tidak ada bukti bahwa perangkat lain **mengunduh** foto itu. Perbaiki:

1. Tambahkan versi foto (`photo_version` atau `updated_at` yang berasal dari server, plus `photo_checksum` bila memungkinkan) pada `employee_face_photos`. Bandingkan dengan **ketidaksamaan**, bukan "lebih baru dari" memakai jam perangkat.
2. Saat sinkronisasi (online), unduh foto master (thumbnail ≤ 25 KB) untuk karyawan yang belum punya atau yang versinya berbeda, simpan ke `local_employee_face_thumbs` (SQLite) dan `employee_face_thumbs` (Dexie), dengan unduhan bertahap di latar belakang dan batas paralelisme kecil, serta tidak memblokir sinkronisasi utama.
3. Modal "Foto Master Wajah" memakai urutan: cache lokal (selalu bisa offline) → bila online dan ada versi lebih baru, perbarui diam-diam → bila tidak ada cache dan online, ambil dari Storage lalu simpan → bila offline dan tidak ada cache, tampilkan keadaan kosong yang jelas "Foto belum tersedia di perangkat ini. Hubungkan ke internet untuk mengunduh". Fallback foto absensi tetap diberi label "Bukan foto master".
4. Tampilkan indikator kecil di modal: "Tersimpan di perangkat" dan tanggal foto.
5. Saat rescan, foto lama diganti: versi naik, antrean unggah mengganti objek yang sama, dan perangkat lain mengunduh ulang pada sinkronisasi berikutnya.
6. Antrean unggah foto harus tahan offline: percobaan ulang dengan backoff, tidak menghilangkan foto bila unggah gagal, dan status antrean terlihat di halaman Sinkronisasi.
7. Test: simulasi versi berubah → unduh ulang; versi sama → tidak unduh; offline tanpa cache → keadaan kosong; offline dengan cache → foto tampil.

---

## TUGAS 4 — Konsistensi bucket Storage (cek sebelum pengguna membuat bucket)

Laporan sebelumnya menyebut bucket privat `employee-faces` dengan signed URL, sedangkan instruksi manual terakhir menyebut `employee-face-photos` dengan **Public bucket**. Dua hal ini bertentangan.

1. Cari nama bucket yang benar-benar dipakai di kode (`enrollmentPhoto`, `syncEngine.js`, `EmployeeFacePreviewModal.jsx`) dan di SQL. Laporkan setiap tempat dengan `file:baris`.
2. Samakan seluruhnya ke **satu nama**.
3. Jangan pernah menginstruksikan pengguna membuat bucket Public untuk foto wajah. Tulis di laporan apa yang dibutuhkan kode (private + signed URL, atau public) dan apa konsekuensi keamanannya, lalu tandai "menunggu keputusan manajemen".
4. Perbarui bagian instruksi manual di laporan dan di komentar SQL agar sesuai.

---

## Format laporan akhir

1. Tugas 1: jawaban diagnosis 1a (lima pertanyaan) dengan bukti, daftar perubahan, dan hasil test.
2. Tugas 2: diagram teks alur rescan → scan, daftar temuan tier, perubahan, dan hasil test.
3. Tugas 3 dan 4: perubahan dan hasil test.
4. Keluaran `npm test` (jumlah test) dan `npm run build`.
5. Bagian "BELUM DIVERIFIKASI, butuh uji di HP".
6. Urutan migrasi SQL yang harus dijalankan, dan konfigurasi Storage manual yang benar.
7. Tidak ada commit, push, atau bump versi.
