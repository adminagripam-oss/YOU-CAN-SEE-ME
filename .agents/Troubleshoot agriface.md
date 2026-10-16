# PROMPT TROUBLESHOOTING — Sinkronisasi Pendaftaran Karyawan Tidak Berjalan

Salin seluruh isi di bawah garis ini ke AI coding assistant.

---

## Gejala

Di halaman Sinkronisasi (`SinkronisasiPage.jsx`), bagian **pendaftaran** (antrean pendaftaran karyawan/biometrik) tidak bisa tersinkron ke Supabase. Gejala ini muncul setelah perubahan terbaru: kolom baru `vector_checksum`/`template_version`/`updated_at` pada `master_descriptors`, tabel `scan_attempts`, tabel dan bucket foto `employee_face_photos`, enrollment multi-pose v2, dan Dexie v10.

Jangan menebak. Tugas Anda: temukan penyebab sebenarnya dengan bukti, perbaiki, dan pastikan kegagalan serupa tidak lagi diam-diam.

## Aturan keras

1. Jangan commit, push, bump versi, atau membuat paket ZIP/OTA.
2. Jangan mengubah `MATCH_COSINE_THRESHOLD` (0.92) dan `DUPLICATE_COSINE_THRESHOLD` (0.88).
3. Jangan menyatakan "terverifikasi" untuk hal yang hanya dibaca. Beri bukti `file:baris` atau keluaran perintah, dan tandai "BELUM DIVERIFIKASI, butuh uji di HP" bila perlu perangkat atau Supabase asli.
4. Jangan memilih sendiri model akses (bucket Public/Private, JWT). Cukup laporkan.
5. Jangan menghapus data pengguna. Item antrean yang gagal tidak boleh dibuang atau ditandai sukses untuk "membereskan" masalah.
6. Jalankan `cmd /c "npm test"` setelah tiap tahap dan `cmd /c "npm run build"` di akhir.

---

## TAHAP 1 — Peta alur (baca saja)

Tuliskan alur pendaftaran dari awal sampai Supabase, dengan `file:baris` untuk tiap langkah:

1. Pendaftaran selesai di `DaftarKaryawanPage.jsx` dan `TabEmployeeManagement.jsx`, lalu apa yang ditulis ke lokal (Dexie, SQLite) dan ke antrean mana (nama tabel antrean, kolom status).
2. Fungsi di `syncEngine.js` yang memproses antrean itu: urutan operasinya, mis. (a) insert/upsert `employees`, (b) upsert `master_descriptors`, (c) unggah foto + `employee_face_photos`, (d) `scan_attempts`.
3. Bagaimana item ditandai: `pending`, `processing`, `synced`, `failed`. Apa yang terjadi bila satu langkah gagal: apakah seluruh item gagal, apakah dicoba ulang, apakah ada penghitung percobaan, apakah ada status yang bisa **macet** (mis. `processing` tidak pernah dikembalikan).
4. Bagaimana `SinkronisasiPage.jsx` memanggil sinkronisasi dan menampilkan hasilnya: apakah error dari Supabase ditampilkan atau tertelan (`catch` kosong, `console.log` saja, `return false` tanpa alasan).
5. Apakah ada kunci global (mis. `isSyncing`) yang bisa tertinggal `true` setelah error, sehingga sinkronisasi berikutnya langsung dilewati.

## TAHAP 2 — Cocokkan payload dengan skema (penyebab paling mungkin)

Buat tabel pencocokan, satu baris per tabel Supabase yang ditulis oleh jalur pendaftaran:

| Tabel | Kolom yang dikirim kode (`file:baris`) | Kolom di migrasi SQL | Kolom yang hilang di Supabase | Tipe tidak cocok |
| --- | --- | --- | --- | --- |

Tabel yang wajib diperiksa: `employees`, `master_descriptors`, `employee_face_photos`, `scan_attempts`.

Periksa khusus:
- `master_descriptors`: kolom `vector_checksum`, `template_version`, `updated_at`, tipe `descriptor_json` (array JSON murni vs string), kunci `onConflict`.
- `scan_attempts`: nama kolom di SQLite lokal (`max_cosine`, `frames_used`, `threshold_used`, dan lain-lain) harus sama dengan kolom tabel Supabase. Periksa pemetaan di `syncPendingScanAttempts()`.
- `employee_face_photos`: kolom versi/checksum dan kunci unik; nama bucket di kode vs di SQL vs instruksi manual (`employee-faces` vs `employee-face-photos`). Laporkan semua variasi nama yang ditemukan.
- Kolom wajib (`NOT NULL`) yang tidak diisi kode, constraint `UNIQUE` atau foreign key yang bisa menolak insert (mis. `master_descriptors.employee_id` menunjuk `employees.id` yang belum terkirim).
- Urutan operasi: apakah `master_descriptors` dikirim sebelum baris `employees` ada di server (pelanggaran foreign key).

## TAHAP 3 — Kandidat penyebab non-skema

Periksa dan beri status LULUS/GAGAL/BELUM DIVERIFIKASI dengan bukti untuk tiap butir:

1. Migrasi lokal gagal: Dexie v10 (`db.js`) atau tabel/kolom SQLite baru (`sqliteService.ts`) gagal dibuat, sehingga membaca antrean melempar error.
2. Kegagalan satu langkah sekunder (foto, telemetri) menggagalkan seluruh item pendaftaran.
3. Payload memuat nilai yang ditolak Postgres: `NaN`, `undefined` pada kolom wajib, `Float32Array` yang diserialisasi sebagai objek `{"0":..}` alih-alih array, string `JSON.stringify` ke kolom JSONB.
4. Respons Supabase `error` tidak diperiksa (`const { data } = await ...` tanpa `error`), sehingga item ditandai sukses atau gagal tanpa sebab.
5. Policy RLS yang menolak `INSERT/UPSERT` untuk tabel baru (tabel baru dengan RLS aktif tanpa policy untuk `anon` akan menolak semuanya).
6. Batas ukuran, timeout, atau batch yang terlalu besar pada upsert vektor 1024-D.
7. Konflik `onConflict` pada kolom yang tidak punya constraint unik.
8. Perubahan terbaru di antrean: field baru pada objek antrean yang tidak dikenali pemroses lama (atau sebaliknya).

## TAHAP 4 — Buat kegagalan terlihat (wajib, apa pun penyebabnya)

1. Setiap kegagalan sinkronisasi pendaftaran harus menyimpan pada item antrean: `last_error_code`, `last_error_message`, `last_error_hint`, `last_error_step` (langkah mana yang gagal), `attempt_count`, `last_attempt_at`. Tambahkan kolom/field dengan migrasi lokal yang aman.
2. `SinkronisasiPage.jsx` menampilkan pesan error asli per item (kode HTTP/Postgres, message, hint, langkah), bukan pesan generik. Tambahkan tombol "Salin detail error" dan "Coba lagi".
3. Tambahkan fungsi `diagnoseRegistrationSync()` (tersedia dari tab Integritas Vektor atau tombol "Diagnosa") yang tanpa mengubah data:
   - menghitung item per status;
   - mengirim satu `select ... limit 1` ke tiap tabel (`employees`, `master_descriptors`, `employee_face_photos`, `scan_attempts`) dan melaporkan apakah tabel/kolom ada serta pesan error bila tidak;
   - memeriksa keberadaan bucket Storage dengan nama yang dipakai kode (tanpa membuat bucket);
   - mencetak hasil sebagai tabel yang bisa disalin.
4. Pastikan kunci global sinkronisasi selalu dilepas di blok `finally`.
5. Status `processing` yang lebih lama dari batas waktu wajar (mis. 5 menit) dikembalikan ke `pending` secara otomatis saat aplikasi dibuka.

## TAHAP 5 — Perbaikan penyebab

Setelah penyebab terbukti, perbaiki di sumbernya. Aturan desain yang wajib dipenuhi:

1. **Langkah inti dan langkah sekunder dipisah.** Pendaftaran karyawan dan vektor master (`employees` + `master_descriptors`) disinkron lebih dulu dan dinyatakan sukses sendiri. Foto dan telemetri disinkron sebagai langkah terpisah yang tidak boleh menggagalkan langkah inti; kegagalannya dicatat dan dicoba ulang dengan backoff.
2. Bedakan error sementara (jaringan, 5xx, timeout) dan permanen (skema/kolom tidak ada, pelanggaran constraint, 4xx). Error sementara dicoba ulang otomatis; error permanen berhenti dan terlihat jelas di UI dengan petunjuk perbaikan, tanpa membuang data.
3. Bila penyebabnya migrasi SQL belum dijalankan: jangan "mengakali" di kode dengan menghapus kolom baru dari payload. Cukup tampilkan di UI dan di `diagnoseRegistrationSync()` bahwa migrasi tertentu belum diterapkan, beserta berkas migrasi yang harus dijalankan. Sebagai pengecualian yang disengaja, kirim payload tanpa kolom baru hanya bila ada tanda fitur (feature flag) eksplisit dan terdokumentasi.
4. Bila nama kolom lokal dan server berbeda, samakan ke satu nama di semua tempat (SQLite, Dexie, payload, migrasi SQL) dan tambahkan test pemetaan.

## TAHAP 6 — Test

Tambahkan test dengan Supabase tiruan (mock) yang mengembalikan:
- sukses penuh;
- `42703` (kolom tidak ada) pada `master_descriptors`;
- `42P01` (tabel tidak ada) pada `scan_attempts`;
- `23503` (pelanggaran foreign key);
- `23505` (duplikat) dan `onConflict` yang benar;
- kegagalan unggah foto (bucket tidak ada) dengan langkah inti tetap sukses;
- error jaringan sementara (harus dicoba ulang) vs error permanen (tidak dicoba ulang);
- status `processing` yang macet dipulihkan.

Setiap test memastikan item tidak hilang, status dan pesan error tersimpan benar, dan kunci global dilepas.

---

## Format laporan akhir

1. **Penyebab utama** dalam satu paragraf, dengan bukti `file:baris` atau keluaran, dan tingkat kepastian (TERBUKTI atau DUGAAN).
2. Tabel pencocokan payload vs skema (Tahap 2) yang sudah terisi.
3. Status tiap kandidat Tahap 3.
4. Daftar perubahan per berkas.
5. Keluaran `npm test` dan `npm run build`.
6. Bagian "BELUM DIVERIFIKASI, butuh uji di HP atau Supabase asli".
7. Daftar migrasi SQL yang harus dijalankan (urutan) bila relevan, dan kueri verifikasinya.
8. Tidak ada commit, push, atau bump versi.
