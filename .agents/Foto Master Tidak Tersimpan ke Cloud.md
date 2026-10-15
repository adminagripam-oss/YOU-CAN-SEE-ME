# PROMPT — Foto Master Wajah Tidak Pernah Tersimpan ke Cloud (employee_face_photos kosong)

Salin seluruh isi di bawah garis ini ke AI coding assistant.

---

## Bukti dari produksi (fakta, bukan dugaan)

Kueri LEFT JOIN `employees` ke `employee_face_photos` pada Supabase produksi menghasilkan 42 karyawan (id 1143–1222), **semuanya** dengan `storage_path` NULL, `captured_at` NULL, dan tidak ada thumbnail. Artinya foto master belum pernah tersimpan di cloud untuk siapa pun, sehingga modal "Foto Master Wajah" di Daftar Karyawan kosong di semua perangkat selain HP pendaftar, dan tidak bisa dicek oleh `admin.hq`.

Fakta tambahan dari pengguna:
- Admin kebun melakukan rescan dan item muncul di halaman Sinkronisasi (bagian pendaftaran), jadi **item inti masuk antrean**. Belum diketahui apakah foto ikut diantrekan atau diunggah.
- Build di HP kebun sudah memuat perbaikan `descObj is not defined`.
- Perilaku yang diharapkan: foto naik saat sinkronisasi dari akun kebun; `admin.hq` dan HP lain dapat melihatnya; bila kebun mengganti foto (rescan), sinkron ulang memperbarui foto di server dan perangkat lain.

## Aturan keras

1. Jangan commit, push, bump versi, atau buat paket ZIP/OTA.
2. Jangan ubah `MATCH_COSINE_THRESHOLD` (0.92) dan `DUPLICATE_COSINE_THRESHOLD` (0.88).
3. Jangan menyatakan "terverifikasi" atau "akar masalah" untuk hal yang hanya dibaca. Beri bukti `file:baris` atau keluaran perintah. Tandai "BELUM DIVERIFIKASI, butuh uji di HP/Supabase" bila perlu.
4. Jangan memilih model akses (bucket Public/Private, JWT) dan jangan menyuruh pengguna membuat bucket baru. Bucket yang dipakai kode saat ini: `attendance-photos`, path `faces/${empId}/master.jpg`. Laporkan saja, tandai "menunggu keputusan manajemen".
5. Item antrean gagal tidak boleh dibuang atau ditandai sukses untuk "membereskan" masalah.
6. Kerjakan berurutan. Setelah tiap tahap jalankan `cmd /c "npm test"`; di akhir `cmd /c "npm run build"`.

---

## TAHAP 1 — Diagnosis (baca kode, jangan ubah dulu)

Jawab dengan `file:baris`. Untuk **tiap jalur pendaftaran/pembaruan wajah** berikut, tabelkan: (a) apakah foto master diambil/disimpan lokal, (b) apakah tugas unggah foto diantrekan, (c) apakah unggah dan upsert `employee_face_photos` benar-benar dipanggil, (d) ID karyawan yang dipakai pada path dan pada kolom `employee_id`.

Jalur wajib:
1. Daftar baru via kamera (`DaftarKaryawanPage.jsx`, Scan).
2. Rescan/Edit via kamera (`DaftarKaryawanPage.jsx`, Edit) — **jalur yang dipakai pengguna saat ini**.
3. Registrasi Wajah di `TabEmployeeManagement.jsx` (`/karyawan`).
4. Upload Foto (bukan kamera).
5. Import Foto Massal.

Lalu jawab:
- Di `syncEngine.js`, di mana langkah foto dijalankan relatif terhadap langkah inti? Apa kondisi yang membuatnya dilewati (flag, `return` awal, status antrean, thumbnail lokal tidak ada, `isOnline`, pengecekan ID `off_`/`tmp_`)?
- Untuk karyawan hasil rescan (ID server sudah ada) maupun karyawan baru luring (ID `off_emp_xxx`): nilai persis `empId` di path Storage dan di `employee_face_photos.employee_id` saat unggah. Apakah ID luring sudah dipetakan ke ID server **sebelum** unggah? Bila tidak, FK ke `employees(id)` menolak upsert.
- Apakah `error` dari `storage.upload` dan upsert `employee_face_photos` diperiksa, dan ke mana jatuhnya (catch kosong, `console.warn`, dicatat ke `last_error_*`)?
- Apakah kegagalan langkah foto membuat item antrean tetap `pending` selamanya, atau ditandai sukses tanpa foto?
- Apakah thumbnail lokal (`local_employee_face_thumbs` / `employee_face_thumbs`) benar-benar ditulis pada tiap jalur di atas? Bila thumbnail tidak ditulis, langkah unggah tidak punya bahan.
- Apakah ada tabel/kolom yang dituju langkah foto yang belum ada di Supabase (bandingkan dengan `docs/verify_schema.sql`)? `employee_face_photos` memang ada (hasil kueri), jadi cek kolom `photo_version`, `photo_checksum`, `storage_path`, `captured_at`, dan unique `employee_id`.

Tuliskan **hipotesis penyebab dengan peringkat** dan sebutkan bukti yang mendukung/menolak masing-masing. Bila ada yang tidak bisa dipastikan dari kode, tulis tepat apa yang harus diperiksa pengguna.

## TAHAP 2 — Reproduksi dengan test (merah dulu)

Dengan Supabase dan Storage tiruan (mock), buat test yang **gagal pada kode sekarang** untuk jalur yang terbukti bermasalah di Tahap 1, misalnya:
- Rescan karyawan ber-ID server → langkah inti sukses → `employee_face_photos` harus terisi (`storage_path`, `photo_version`, `photo_checksum`).
- Karyawan baru luring → ID dipetakan → foto diunggah dengan ID server (bukan `off_`).
- Upload Foto dan Import Foto Massal → foto master tersimpan dan terunggah.
- Langkah foto gagal (bucket tidak ada / 403 / jaringan) → langkah inti tetap sukses, error foto tercatat (`last_error_step = 'photo'`, kode dan pesan), item foto dicoba ulang dengan backoff dan **tidak hilang**.
- Langkah inti gagal → foto tidak diunggah untuk ID yang belum ada di server, tanpa menghilangkan thumbnail lokal.

Laporkan keluaran test merah sebelum perbaikan.

## TAHAP 3 — Perbaikan

1. **Setiap jalur** pada Tahap 1 yang menghasilkan atau mengganti template wajah juga menulis thumbnail lokal (Dexie dan SQLite) dan membuat tugas unggah foto yang tahan offline. Pada rescan, `photo_version` naik dan objek lama diganti (path sama, `upsert: true`).
2. Urutan: langkah inti → pemetaan ID luring→server → langkah foto. Dua langkah ini punya status dan penghitung percobaan sendiri; kegagalan satu tidak menggagalkan yang lain. Error sementara (jaringan/5xx/timeout) dicoba ulang dengan backoff; error permanen berhenti dan tampil jelas dengan petunjuk.
3. **Perangkat lain mengunduh foto** saat sinkronisasi online: bandingkan `photo_version`/`photo_checksum` server dengan lokal memakai **ketidaksamaan** (bukan jam perangkat). Unduh thumbnail ≤ 25 KB, bertahap di latar belakang, paralelisme kecil, tidak memblokir sinkronisasi utama. Simpan ke `local_employee_face_thumbs` dan `employee_face_thumbs`.
4. Modal `EmployeeFacePreviewModal.jsx`: cache lokal → (online) cek versi dan perbarui diam-diam → bila tidak ada cache dan online, ambil dari Storage lalu simpan → bila offline tanpa cache, tampilkan "Foto belum tersedia di perangkat ini. Hubungkan ke internet untuk mengunduh". Fallback foto absensi tetap berlabel "Bukan foto master". Tampilkan indikator "Tersimpan di perangkat" dan tanggal foto.
5. Halaman Sinkronisasi: tampilkan status antrean foto per karyawan (menunggu / berhasil / gagal beserta langkah dan pesan error asli) dan tombol "Coba lagi" untuk foto saja. Tambahkan pada `diagnoseRegistrationSync()` pemeriksaan: jumlah karyawan tanpa baris `employee_face_photos`, dan keberadaan objek `faces/{id}/master.jpg` untuk satu sampel.
6. Karyawan lama tanpa foto **tidak** diisi otomatis (tidak ada sumbernya). Tampilkan di modal keadaan "Foto master belum didaftarkan. Lakukan rescan dari kamera". Jangan membuat foto palsu dari foto absensi.

## TAHAP 4 — Test hijau dan regresi

- Semua test Tahap 2 hijau. Tambahkan: versi berubah → unduh ulang; versi sama → tidak unduh; offline tanpa cache → keadaan kosong; offline dengan cache → foto tampil; rescan → versi naik dan perangkat lain mengunduh ulang.
- `cmd /c "npm test"` seluruhnya hijau dan `cmd /c "npm run build"` lolos. Jalankan juga pemeriksaan identifier tak terdefinisi (`no-undef`) bila skrip tersedia, supaya kasus `descObj` tidak terulang.

---

## Format laporan akhir

1. Tabel jalur × (foto lokal / diantrekan / diunggah / ID yang dipakai) dengan `file:baris`.
2. Penyebab yang **TERBUKTI** vs **DUGAAN** (jangan tulis "akar masalah" tanpa bukti).
3. Keluaran test merah sebelum perbaikan dan hijau sesudahnya, jumlah test, hasil build.
4. Daftar perubahan per berkas.
5. Bagian "BELUM DIVERIFIKASI, butuh uji di HP/Supabase" berisi skenario uji lapangan:
   - HP kebun: rescan 1 karyawan → sinkron → kueri `employee_face_photos` untuk karyawan itu harus berisi `storage_path`, `photo_version`, dan objek ada di Storage `faces/`.
   - HP lain / akun `admin.hq`: sinkron → modal menampilkan foto yang sama → mode pesawat → foto tetap tampil.
   - Rescan ulang → versi naik → HP lain mengunduh ulang.
6. Migrasi SQL yang harus dijalankan (urutan) dan kueri verifikasinya, bila ada.
7. Tidak ada commit, push, atau bump versi. Dan nyatakan jelas: perbaikan baru berlaku setelah build baru dipasang di HP kebun.
