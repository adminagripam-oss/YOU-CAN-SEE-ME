# PROMPT VERIFIKASI — Buktikan 4 Ketidaksesuaian antara PRD v3.2.3 dan Kode

Salin seluruh isi di bawah garis ini ke AI coding assistant.

---

## Tujuan

PRD v3.2.3 diduga tidak sesuai dengan kode yang sebenarnya pada empat hal. Tugas Anda adalah **membuktikan atau membantah** tiap butir dengan bukti dari kode dan berkas migrasi, bukan dari ingatan atau dari isi PRD. Jangan mengubah kode aplikasi pada tugas ini.

## Aturan keras

1. Mode **baca saja** untuk kode aplikasi dan migrasi. Satu-satunya berkas yang boleh dibuat: `docs/VERIFIKASI_PRD_vs_KODE.md` (laporan) dan `docs/verify_schema.sql` (kueri baca-saja untuk dijalankan pengguna di Supabase).
2. Setiap temuan wajib berstatus **TERBUKTI**, **DIBANTAH**, atau **BELUM DIVERIFIKASI** (butuh Supabase atau HP asli), disertai bukti `file:baris` atau keluaran perintah. Jangan menyatakan sesuatu terbukti hanya dari membaca PRD.
3. Jangan commit, push, bump versi. Jangan menghapus data.
4. Jangan memutuskan kebijakan keamanan (Public/Private bucket). Laporkan fakta dan risiko saja.

---

## BUTIR 1 — Alur pendaftaran wajah di kode vs PRD

PRD (§3.2, FR-9) menyebut: 7 frame, jeda minimal 150 ms, `buildTemplateDetailed`, buang 1 outlier, konsistensi ≥ 0.90. Kode diduga sudah memakai pendaftaran multi-pose v2.

Buktikan dengan `file:baris`:

1. Fungsi mana yang benar-benar dipanggil saat pendaftaran di **ketiga** jalur: `DaftarKaryawanPage.jsx` (Scan/Rescan), `DaftarKaryawanPage.jsx` (Edit Kamera), dan `TabEmployeeManagement.jsx` (Registrasi Wajah `/karyawan`). Apakah `selectEnrollmentFrames`/`createEnrollmentCollector` atau `buildTemplateDetailed`?
2. Nilai konfigurasi aktual di `BIOMETRIC_CONFIG`: jumlah frame target, jeda minimal antar-frame, batas waktu total, batas yaw, `ENROLL_MIN_CONSISTENCY`, `TEMPLATE_VERSION`. Tabelkan nilai kode vs nilai PRD.
3. Apakah `template_version` benar-benar ikut tersimpan ke: antrean lokal (SQLite dan Dexie), payload sinkronisasi, dan `master_descriptors` di server? Telusuri tiap langkah.
4. Apakah jalur "Upload Foto" (bukan kamera) tetap memakai pengambilan satu frame? Jelaskan template yang dihasilkan jalur itu dan `template_version`-nya.
5. Apakah masih ada jalur pendaftaran yang memakai pengambilan sampel cepat lama (frame berdekatan tanpa jeda)? Cari semua pemanggil `buildTemplate`, `buildTemplateDetailed`, `isGoodFrame` dan sebutkan.
6. Kesimpulan satu paragraf: apakah template yang dihasilkan di lapangan sekarang lebih lebar daripada versi 7 frame ≈ 1 detik, dan seberapa yakin (TERBUKTI atau BELUM DIVERIFIKASI karena butuh HP).

## BUTIR 2 — Skema nyata vs PRD §7

PRD §7.1–7.3 menyebut `master_descriptors` tanpa `vector_checksum`/`template_version`, tidak ada `scan_attempts`, dan Dexie v9 dengan 9 store, padahal kode dan laporan memakai kolom/tabel baru dan Dexie v10.

Buktikan dengan `file:baris`:

1. Tabel pencocokan berikut untuk **setiap** tabel dan penyimpanan lokal:

   | Penyimpanan | Kolom menurut PRD | Kolom menurut migrasi SQL/kode | Selisih |
   | --- | --- | --- | --- |

   Cakup: Supabase `master_descriptors`, `employees`, `employee_face_photos`, `scan_attempts`; SQLite `local_master_descriptors`, `local_employee_sync_queue`, `local_employee_face_thumbs`, `local_scan_attempts` (atau nama sebenarnya), `local_attendance_queue`, `local_attendance_logs`; Dexie semua store pada versi tertinggi.
2. Nomor versi Dexie tertinggi di `db.js` dan daftar store-nya; bandingkan dengan PRD (v9, 9 store).
3. Nama kolom `scan_attempts` di SQLite vs di migrasi Supabase vs di pemetaan `syncPendingScanAttempts()`. Tandai tiap perbedaan nama.
4. Buat `docs/verify_schema.sql` berisi kueri baca-saja yang dapat dijalankan pengguna di Supabase SQL Editor untuk membuktikan bahwa migrasi sudah diterapkan:
   - kolom `master_descriptors` (`information_schema.columns`) dan tipe `descriptor_json`;
   - `to_regclass` untuk `scan_attempts` dan `employee_face_photos`;
   - `jsonb_typeof(descriptor_json)` per jumlah;
   - jumlah baris dengan `vector_checksum IS NULL` dan distribusi `template_version`;
   - policy RLS pada tabel baru (`pg_policies`) dan apakah RLS aktif (`pg_class.relrowsecurity`);
   - keberadaan bucket (`storage.buckets`) dan statusnya (`public`).
   Beri komentar pada tiap kueri tentang hasil yang benar vs hasil yang berarti migrasi belum jalan.
5. Daftar migrasi SQL di folder `supabase/migrations` dengan urutan, dan apakah ada ketergantungan antar berkas.

## BUTIR 3 — Foto wajah di bucket publik

PRD §3.8, FR-13, NFR-3 menyebut foto master memakai Public URL di bucket `attendance-photos` dengan path `faces/${empId}/master.jpg`.

Buktikan dengan `file:baris`:

1. Nama bucket dan jenis akses yang benar-benar dipakai di kode (`enrollmentPhoto.js`, `syncEngine.js`, `EmployeeFacePreviewModal.jsx`) dan di SQL/komentar migrasi. Laporkan **semua** variasi nama yang masih tersisa (`employee-faces`, `employee-face-photos`, `attendance-photos`).
2. Apakah URL dibangun dengan `getPublicUrl` atau `createSignedUrl`? Apakah ada kedaluwarsa?
3. Apakah `empId` pada path adalah ID berurutan (BigInt) atau nilai yang tidak bisa ditebak? Tuliskan contoh path nyata dan nilai ID yang dihasilkan kode (untuk karyawan luring: `off_emp_xxx` vs ID server).
4. Apakah folder `faces/` dan foto absensi (`attendance-photos` lain) berada di bucket yang sama, sehingga policy keduanya terikat?
5. Policy Storage yang dibuat di migrasi untuk `anon` dan `authenticated` (SELECT, INSERT, UPDATE, DELETE). Apakah `anon` boleh mengganti atau menghapus `master.jpg`? Itu risiko integritas: foto master bisa ditimpa oleh siapa pun yang memiliki kunci anon.
6. Ringkas risiko dalam satu paragraf faktual: siapa yang dapat membaca, menebak, atau menimpa foto, berdasarkan bukti di atas. Jangan menyatakan kepatuhan hukum; cukup sebutkan bahwa ini data biometrik dan perlu keputusan manajemen dan bagian hukum.
7. Daftar opsi mitigasi beserta konsekuensi implementasinya (bucket privat + signed URL, path tak-tertebak, pembatasan policy), **tanpa menerapkannya**.

## BUTIR 4 — Kontradiksi di dokumen

Buktikan dengan `file:baris` atau kutipan PRD:

1. **Tier 1 Express.** PRD §3.1 dan §6 menyebut `/api/attendance/verify`, `/api/biometrics/register`, `/api/biometrics/master/:id` sebagai jalur aktif. Periksa `server.js` (apakah sekarang `410 Gone`), dan cari semua pemanggil di sisi klien (`fetch`/`axios`, `API_BASE_URL`). Apakah ada jalur kode klien yang masih memanggil endpoint itu dan bergantung pada responsnya? Apa yang terjadi bila menerima 410 (jatuh ke Supabase langsung, atau gagal)?
2. **Hak akses `/sinkronisasi`.** FR-14 menyebut `regional_admin` dan `admin.hq` memakai halaman ini, sedangkan peta rute §9 menaruhnya di bawah `RoleGuard estate_admin`. Periksa `router.jsx`, `config/menu.js`, dan `SinkronisasiPage.jsx`: siapa yang benar-benar dapat membuka halaman ini dan apakah filter peran di dalam halaman bisa tercapai.
3. **Nama peran.** `headoffice_admin` vs `admin.hq`: apakah keduanya merujuk peran yang sama di kode? Tunjukkan pemetaannya.
4. **Ambang duplikasi.** Tabelkan nilai ambang duplikasi di seluruh kode (pendaftaran, rescan, edit, upload foto) vs PRD (0.88). Tandai nilai yang masih berbeda, termasuk konstanta lokal seperti `EDIT_DUPLICATE_THRESHOLD`.
5. Sebutkan kontradiksi lain antara PRD dan kode yang Anda temukan saat menelusuri (maksimal 10, urut dari yang paling berdampak pada masalah check-out sore hari).

---

## Format laporan `docs/VERIFIKASI_PRD_vs_KODE.md`

1. Ringkasan satu halaman: tabel butir 1–4 dengan status, tingkat dampak terhadap masalah "tidak match saat check-out sore hari", dan bukti utama.
2. Rincian per butir dengan semua `file:baris`.
3. Daftar yang harus diverifikasi pengguna di Supabase atau HP (merujuk `docs/verify_schema.sql`), dengan hasil yang diharapkan.
4. Daftar usulan perubahan PRD (teks yang diganti dan teks penggantinya), **tanpa menerapkan** perubahan ke PRD.
5. Tidak ada commit, push, atau bump versi.
