# Wajah Tidak Match di Sore Hari + Preview Foto di Daftar Karyawan

Dokumen ini berisi (1) temuan dari kode yang Anda kirim, (2) rencana kerja, (3) prompt lengkap untuk AI coding assistant, dan (4) keputusan yang harus Anda ambil.

## 1. Temuan dari kode

### 1A. Terbukti dari kode (nomor baris = berkas yang Anda kirim)

| # | Temuan | Lokasi | Dampak |
| :-- | :-- | :-- | :-- |
| T1 | Variabel `finalTemplate` tidak pernah dideklarasikan. Pemeriksaan duplikasi saat **rescan** melempar ReferenceError, tertangkap `catch (_)`, lalu proses lanjut seolah aman. | `DaftarKaryawanPage.jsx` baris 245 | Anti-duplikasi rescan tidak pernah berjalan. |
| T2 | Vektor master yang sudah ada di perangkat **tidak pernah diperbarui**. Scanner membaca cache lokal dulu (Tier 1). Saat sinkronisasi, biometrik hanya diunduh untuk karyawan yang vektornya belum ada di cache (`missingEmpIds`). Penarikan karyawan juga memakai `created_at > lastSyncTime`, yang tidak berubah saat rescan. | `TabFaceVerification.jsx` baris 842-854; `App.jsx` baris 323-324 dan 346-361 | Rescan di perangkat A tidak sampai ke perangkat B. Perangkat B terus memakai vektor lama. |
| T3 | `master_descriptors.updated_at` hanya terisi default saat INSERT. Upsert di rescan dan edit tidak mengisi `updated_at`. | `DaftarKaryawanPage.jsx` baris 361-363 dan 764-766 | Tidak ada penanda versi untuk mendeteksi vektor usang. |
| T4 | `syncPendingEmployees` menyimpan vektor ke `master_descriptors` sebagai **string JSON** (`JSON.stringify`), sedangkan rescan dan edit menyimpan sebagai **array**. Hasil upsert juga tidak diperiksa errornya. | `syncEngine.js` baris 719-726 | Tipe data campur di satu kolom. Kegagalan upsert tidak terlihat. |
| T5 | Kolom `euclidean_distance` di log diisi `1 - skor_tampilan/100`, bukan jarak sebenarnya. Percobaan yang **gagal** tidak tercatat sama sekali. | `TabFaceVerification.jsx` baris 1414 dan 1432-1434 | Tidak ada data untuk menjelaskan kegagalan sore hari. |
| T6 | `server.js` (jalur lama): verifikasi memakai jarak Euclidean dengan ambang 0,55 (untuk vektor 128-D), memakai tabel `master_biometrics` (bukan `master_descriptors`), dan bila master kosong, **vektor hasil scan otomatis dijadikan master**. | `server.js` baris 347, 546-567, 575-579 | Bila jalur ini terpanggil, hasilnya salah dan master bisa tertimpa. Klien saat ini memakai antrean lokal, jadi kemungkinan tidak terpanggil (harus dipastikan). |
| T7 | Foto absensi sudah diunggah ke bucket `attendance-photos` dengan `getPublicUrl` (URL publik). Foto **pendaftaran** tidak ada di mana pun. | `syncEngine.js` baris 340-357 | Preview foto pendaftaran harus dibuat baru. Bucket publik berisiko privasi. |

### 1B. Dugaan penyebab "pagi match, sore tidak" (belum terbukti)

| Peringkat | Dugaan | Bagaimana dibuktikan |
| :-- | :-- | :-- |
| 1 | **Kondisi wajah berbeda dan ambang 0,92 terlalu ketat.** Tujuh sampel pendaftaran diambil dalam sekitar 1 detik, di satu kondisi cahaya (pagi). Cosine sampel genuine lintas kondisi bisa berada di 0,85-0,92. Ambang naik dari 0,90 ke 0,92 pada v3.2.1. | Log `[FASE 0 LOG]` (sudah ada di kode) mencetak `cosSim` per frame. Bila median sore berkisar 0,86-0,91, dugaan ini benar. |
| 2 | **Vektor yang dipakai sore hari bukan vektor pagi** (T2, T3, T4: perangkat berbeda, cache usang, atau tipe data campur). | Bandingkan sidik vektor (panjang, norma, 5 angka pertama) di perangkat dan di Supabase. |
| 3 | **Kamera atau orientasi berbeda** antara pendaftaran dan scan (depan vs belakang, portrait vs landscape). | Catat `facingMode` dan ukuran kanvas pada setiap scan (Fase 1). |

Catatan: berkas `TabEmployeeManagement.jsx` (menu Registrasi Wajah, tempat pendaftaran pertama) **belum saya terima**. Jalur pendaftaran baru belum saya periksa. Fase 0 meminta AI membacanya.

## 2. Rencana kerja

| Fase | Isi | Mengubah perilaku? |
| :-- | :-- | :-- |
| 0 | Baca saja, laporkan | Tidak |
| 1 | Telemetri dan alat diagnostik (catat cosine nyata, sidik vektor, kamera, cahaya) | Tidak |
| 2 | Perbaikan integritas vektor (T1-T4, T6) | Ya, hati-hati |
| 3 | Preview foto: ikon mata dan dialog shadcn di Daftar Karyawan | Ya (fitur baru) |
| 4 | Verifikasi dan laporan | Tidak |

Urutan penting: **Fase 1 dijalankan dan dikumpulkan datanya dulu** (minimal 2-3 hari lapangan) sebelum memutuskan apakah ambang diubah. Fase 2 dan 3 bisa jalan paralel.

---

## 3. PROMPT UNTUK AI (salin dari sini)

```
KONTEKS
AgriFace (React 19, Vite, Capacitor 8, SQLite native / Dexie web, Supabase, @vladmandic/human embedding 1024-D, cosine 1-to-1).
Masalah A: karyawan didaftarkan wajahnya pagi hari dan check-in berhasil, tetapi sore hari wajah yang sama tidak match.
Masalah B: Daftar Karyawan perlu preview foto per karyawan (ikon mata -> dialog shadcn) sebagai validasi bahwa foto itu milik karyawan tersebut.

ATURAN WAJIB
1. Jangan mengubah nilai BIOMETRIC_CONFIG (MATCH_COSINE_THRESHOLD, DUPLICATE_COSINE_THRESHOLD, ENROLL_*), humanSingleton, atau algoritma cosine/median di Fase 1-3. Perubahan ambang adalah keputusan pemilik proyek setelah data telemetri ada.
2. Jangan menyimpan vektor wajah atau foto ke log, console, atau telemetri. Telemetri hanya angka ringkas (lihat Fase 1).
3. Offline-first tetap berlaku: semua perubahan harus berjalan tanpa internet (antrean lokal), dan sinkron saat online.
4. Jangan menambah plugin native Capacitor (tidak bisa lewat OTA). Dependensi JS (mis. komponen shadcn Dialog) boleh, laporkan.
5. Jangan menebak. Bila sebuah berkas yang dibutuhkan tidak ada, katakan berkas apa, jangan mengarang isinya.
6. Jangan mengklaim "sudah diuji di perangkat". Tulis LULUS / GAGAL / TIDAK BISA DIUJI + alasan.
7. Tidak ada script lint di package.json; verifikasi dengan `npm run build` dan `npm test`.
8. Migrasi database Supabase: tulis sebagai berkas SQL terpisah (supabase/migrations/...). Jangan dijalankan otomatis. Semua migrasi harus aman diulang (IF NOT EXISTS).

FASE 0. BACA SAJA (laporkan sebelum mengubah apa pun)
0.1 Baca TabEmployeeManagement.jsx (jalur Registrasi Wajah / pendaftaran pertama). Laporkan: bagaimana 7 sampel diambil (interval, durasi total), siapa yang membuat template (buildTemplateDetailed), format vektor saat disimpan (array / Float32Array / string), ke mana disimpan (Supabase master_descriptors, SQLite/Dexie, antrean), dan apakah ada pemeriksaan duplikasi serta apakah variabelnya terdefinisi.
0.2 Cari semua pemanggilan ke `/api/attendance/verify`, `/api/biometrics/register`, `/api/biometrics/master`, `API_BASE_URL`, `fetchWithTimeout` di src/. Laporkan apakah jalur server lama di server.js benar-benar dipakai klien.
0.3 Konfirmasi dengan kutipan baris: (a) DaftarKaryawanPage.jsx baris ~245 memakai `finalTemplate` yang tidak dideklarasikan; (b) jalur edit (handleEditSubmit dan efek sampel edit sekitar baris 495-520) memakai variabel yang benar atau tidak; (c) App.jsx fetchEmployees hanya mengunduh biometrik untuk karyawan yang belum ter-cache; (d) upsert master_descriptors di syncEngine.js memakai string, sedangkan DaftarKaryawanPage.jsx memakai array.
0.4 Cari seluruh penulisan ke tabel master_descriptors (grep `master_descriptors`) dan tabulasikan: berkas, baris, tipe nilai (array/string), apakah updated_at diisi, apakah error diperiksa.
0.5 Cek apakah komponen shadcn `Dialog` sudah ada di src/components/ui/. Cek komponen mobile (MobileDataPage, MobileTableViewport) untuk tahu bagaimana kolom Aksi dirender di mobile.
0.6 Cek bagaimana foto absensi (path_foto) dan bucket `attendance-photos` dipakai, untuk menjadi acuan Fase 3.
Laporkan semua temuan Fase 0 dalam satu tabel, lalu LANJUT ke Fase 1 tanpa menunggu.

FASE 1. TELEMETRI DAN DIAGNOSTIK (tanpa mengubah keputusan lulus/gagal)
1.1 Tabel baru `scan_attempts` (migrasi SQL + padanan SQLite/Dexie untuk antrean lokal). Kolom:
    id, employee_id, nik, attempt_type ('CHECK-IN'|'CHECK-OUT'|'ENROLL-VERIFY'), outcome ('MATCHED'|'NOT_MATCHED'|'ABORTED'),
    median_cosine (real), min_cosine, max_cosine, frames_used (int), threshold_used (real),
    facing_mode ('user'|'environment'), canvas_w, canvas_h, brightness (angka dari checkLightingQuality bila tersedia),
    master_checksum (text), master_updated_at (timestamptz, nullable), template_version (int), app_version (text), platform ('android'|'web'),
    created_at (timestamptz default now()), is_synced.
    Catat satu baris per sesi scan (bukan per frame): saat MATCHED, dan saat sesi berakhir tanpa match (ganti karyawan, tutup scanner, timeout 20 detik setelah wajah stabil).
    DILARANG menyimpan vektor, foto, atau koordinat GPS di tabel ini.
1.2 Fungsi `vectorChecksum(vec)` di biometrics.js (atau berkas util baru): kembalikan string pendek dari panjang vektor, norma L2 (4 desimal), dan 4 angka pertama dibulatkan 4 desimal, mis. "1024|1.0000|0.0123,-0.0456,...". Bukan hash kriptografis, hanya sidik untuk dibandingkan. Hitung di klien saat master dimuat (loadMasterVectors) dan simpan di ref.
1.3 Di TabFaceVerification.jsx: saat sesi scan berakhir, tulis satu baris scan_attempts ke antrean lokal (pola outbox yang sama dengan queueOfflineAttendance) dan kirim saat sinkronisasi. Gunakan data yang SUDAH ada di scoreHistoryRef, facingMode, canvasSize. Jangan menambah setState per frame.
1.4 Tambah kolom pada attendance_logs (migrasi SQL, nullable, aman diulang): `match_cosine real`, `match_threshold real`, `template_version int`, `facing_mode text`. Isi saat absensi Hadir berhasil, dengan median cosine sebenarnya. JANGAN mengubah kolom `euclidean_distance` yang sudah ada (biarkan untuk kompatibilitas) dan jangan mengubah tampilan tabel Log.
1.5 Panel diagnostik kecil (hanya peran headoffice_admin, di halaman Sinkronisasi atau menu bantuan): untuk satu karyawan terpilih tampilkan sidik vektor lokal vs sidik vektor di Supabase (bila online) dan `updated_at` keduanya, dengan hasil "SAMA" atau "BERBEDA". Tidak menampilkan angka vektor penuh.
1.6 Pastikan log `[FASE 0 LOG]` per frame tetap ada, tetapi bungkus dengan flag debug (localStorage 'agriface_debug' = '1') agar tidak membebani produksi.

FASE 2. INTEGRITAS VEKTOR (perbaikan T1-T4, T6)
2.1 (T1) DaftarKaryawanPage.jsx: ganti `finalTemplate` dengan `templateResult.vector` pada pemeriksaan duplikasi saat rescan. Samakan jalur edit dan rescan agar memakai satu fungsi pemeriksaan duplikasi bersama dan SATU konstanta (BIOMETRIC_CONFIG.DUPLICATE_COSINE_THRESHOLD). Hapus EDIT_DUPLICATE_THRESHOLD lokal (0.88) HANYA setelah pemilik proyek memutuskan nilainya (lihat Keputusan 2); sementara, biarkan nilai yang berlaku dan beri komentar TODO.
    Pemeriksaan duplikasi saat OFFLINE: jangan dilewati secara diam-diam; bila tidak bisa dijalankan, tampilkan peringatan.
2.2 (T3) Setiap upsert ke master_descriptors wajib menyertakan `updated_at: new Date().toISOString()` dan `template_version`. Tambahkan juga kolom `template_version int` dan `vector_checksum text` pada master_descriptors (migrasi), diisi dari vectorChecksum. Tambahkan trigger SQL `BEFORE UPDATE` yang mengisi updated_at = now() sebagai jaring pengaman.
2.3 (T4) Satukan format: master_descriptors.descriptor_json selalu ARRAY angka (jsonb array), bukan string. Perbarui syncEngine.js (syncPendingEmployees) agar mengirim array, memeriksa `error` dari upsert, dan bila gagal MENANDAI karyawan tetap di antrean (jangan dihapus dari antrean) serta melaporkan lewat toast. Pembaca (toVectorArray) tetap menerima kedua format. Sediakan skrip SQL satu kali untuk mengubah baris lama yang bertipe string menjadi array (UPDATE ... WHERE jsonb_typeof(descriptor_json) = 'string'), dalam berkas migrasi terpisah, TIDAK dijalankan otomatis.
2.4 (T2) Revalidasi vektor usang:
    a. Saat fetchEmployees online, ambil ringkasan ringan `select employee_id, updated_at, vector_checksum from master_descriptors` untuk lingkup admin (sesuai peran), bandingkan dengan cache lokal (simpan updated_at dan checksum di local_master_descriptors / user_master; migrasi SQLite aman diulang). Unduh ulang hanya baris yang berbeda.
    b. Saat scanner membuka karyawan tertentu dan perangkat online, lakukan pemeriksaan satu-baris (employee_id) dengan batas waktu 2 detik; bila berbeda, unduh dan pakai vektor terbaru. Bila offline atau timeout, tetap pakai cache dan catat master_updated_at ke scan_attempts.
    c. Jangan mengubah urutan tier loadMasterVectors selain menambah langkah ini. Jangan membuat scanner menunggu lebih dari 2 detik.
    d. Setelah rescan sukses di perangkat ini, vektor lokal harus langsung diganti (sudah dilakukan lewat cacheUserMasterVector; pastikan juga Dexie user_master dan employees_cache ikut diperbarui dan tidak ada salinan lama di antrean).
2.5 (T6) Jalur server lama: bila Fase 0.2 membuktikan tidak dipakai klien, beri respons 410 Gone pada `/attendance/verify` dan `/biometrics/register`, atau hapus, dan hilangkan auto-register master dari scan. Bila ternyata dipakai, JANGAN diubah; laporkan ke pemilik proyek.
2.6 Pendaftaran pertama (TabEmployeeManagement.jsx): terapkan perbaikan yang sama (updated_at, template_version, vector_checksum, array bukan string, pemeriksaan error, pemeriksaan duplikasi yang berjalan benar). Jangan mengubah alur pengambilan 7 sampel pada fase ini.
2.7 Tulis uji unit (vitest) untuk: vectorChecksum (stabil, berbeda untuk vektor berbeda), toVectorArray (array, string JSON, objek {"0":..}, Float32Array), logika "perlu unduh ulang" (updated_at/checksum berbeda atau lokal kosong).

FASE 3. PREVIEW FOTO DI DAFTAR KARYAWAN
Keputusan desain (sudah ditetapkan, jangan diubah tanpa bertanya):
- Foto yang ditampilkan adalah foto PENDAFTARAN/RESCAN, diambil dari frame terbaik yang dipakai membentuk template (frame sampel dengan skor deteksi tertinggi), dipotong di sekitar kotak wajah dengan margin 25 persen, diperkecil maksimal 240 px sisi panjang, JPEG kualitas 0,7 (sekitar 15-30 KB).
- Penyimpanan cloud: bucket Supabase Storage BARU `employee-faces`, PRIVAT (tanpa kebijakan akses anon). Path: `{employee_id}/{timestamp}.jpg`. Metadata di tabel baru `employee_face_photos` (employee_id unique, storage_path, captured_at, template_version, captured_by, facing_mode). Bucket `attendance-photos` yang publik TIDAK dipakai untuk foto ini.
- Karena aplikasi memakai login kustom (bukan Supabase Auth), RLS tidak bisa membedakan peran. Maka URL tertanda (signed URL, berlaku 10 menit) dibuat oleh endpoint Express baru `GET /api/employees/:id/face-photo` yang memeriksa JWT dan peran (estate_admin hanya kebunnya, regional_admin hanya regionnya, headoffice_admin semua) dan memakai service role key di server. Jangan menaruh service role key di klien.
- Cache lokal untuk offline: tabel SQLite/Dexie `employee_face_thumbs (employee_id TEXT PK, thumb_base64 TEXT, captured_at TEXT, is_synced INTEGER)`. Foto yang diambil saat offline disimpan di sini dan diunggah saat sinkronisasi (tambahkan langkah di syncEngine setelah syncPendingEmployees; gagal unggah tidak boleh menggagalkan sinkronisasi karyawan).
- Fallback untuk karyawan lama (belum ada foto pendaftaran): tampilkan keterangan "Belum ada foto pendaftaran. Lakukan Rescan Wajah." dan, sebagai pembanding terpisah berlabel jelas "Foto absensi terakhir (bukan foto pendaftaran)", tampilkan path_foto log absensi terbaru bila ada.
3.1 Tangkap foto: buat util `captureEnrollmentPhoto(video, boundingBox)` yang menghasilkan data URL JPEG sesuai ketentuan di atas dari video yang sedang dipakai kamera (perhatikan mirror: untuk kamera depan, simpan foto TIDAK terbalik / sesuai wajah asli, bukan tampilan cermin). Panggil pada frame sampel dengan skor tertinggi di jalur rescan (onScanFaceProcessed), edit (update biometrik), dan Registrasi Wajah. Pada jalur unggah file foto (editFormMode 'file'), pakai foto yang diunggah setelah diperkecil.
3.2 Simpan foto bersamaan dengan template (satu operasi logis): online -> unggah ke storage + upsert employee_face_photos; offline -> simpan di employee_face_thumbs dan antre. Kegagalan foto TIDAK boleh membatalkan penyimpanan vektor.
3.3 UI (shadcn, gaya sama dengan komponen ui/ yang ada):
    - Di kolom "Aksi" tabel desktop dan tampilan mobile, tambah tombol ikon `Eye` (lucide-react) dengan aria-label "Lihat foto wajah", ukuran target sentuh minimal 44x44 px di mobile. Letakkan sebelum tombol Edit/Rescan. Tidak menambah lebar tabel mobile secara berarti (ikon saja).
    - Klik membuka `Dialog` shadcn (pasang komponen dialog bila belum ada, laporkan dependensi yang ikut terpasang). Isi dialog: Nama dan NIK sebagai judul; gambar foto dengan rasio terjaga (object-contain, latar netral), tampilan memuat (skeleton) dan keadaan error ("Foto tidak dapat dimuat"); di bawahnya metadata: tanggal foto diambil, kamera (depan/belakang) bila ada, versi template, dan status "Biometrik: Siap / Belum".
    - Mobile: dialog lebar penuh dengan margin 16 px, tinggi maksimal 85 persen layar, bisa ditutup dengan tombol Tutup dan tombol Back Android (gunakan pola History API yang sudah dipakai FullscreenScanShell, jangan menutup aplikasi).
    - z-index: di atas topbar dan sidebar, di bawah toast (60000). Jangan mengubah aturan CSS lama (.table-container, .col-sticky, dll.); tambahkan kelas baru.
    - Memuat foto: panggil endpoint face-photo hanya saat dialog dibuka (lazy), jangan memuat foto untuk seluruh baris. Bila offline, tampilkan thumbnail lokal bila ada.
    - Tidak ada tombol unduh. Tambahkan atribut yang mencegah menyeret/menyimpan gambar secara tidak sengaja (draggable=false). Jangan mencatat URL tertanda ke console.
3.4 Hak akses sesuai peran (lihat endpoint). Estate admin tidak boleh melihat foto kebun lain.
3.5 Migrasi SQL (berkas terpisah, aman diulang): bucket tidak bisa dibuat lewat SQL biasa, jadi tulis langkah manual di komentar berkas migrasi ("buat bucket employee-faces, Private"). Tabel employee_face_photos, kolom tambahan, trigger updated_at.
3.6 Uji unit untuk util crop/resize (ukuran keluaran, rasio), pemilihan frame terbaik, dan fungsi izin peran endpoint (bila diuji lewat fungsi murni).

FASE 4. VERIFIKASI
1. `npm run build` dan `npm test` lulus; tidak ada dependensi native baru; laporkan dependensi JS baru.
2. Fase 1: satu sesi scan menghasilkan tepat satu baris scan_attempts (MATCHED atau NOT_MATCHED); tidak ada vektor/foto/GPS di baris itu; log per frame hilang saat flag debug mati.
3. Fase 2: rescan di perangkat A, lalu buka scanner di perangkat B (online): B mengunduh vektor baru (sidik sama). B offline: tetap memakai cache dan mencatat master_updated_at.
4. Fase 2: sinkronisasi karyawan offline menghasilkan master_descriptors bertipe array; kegagalan upsert membiarkan karyawan tetap di antrean.
5. Fase 2: pemeriksaan duplikasi rescan benar-benar berjalan (uji dengan vektor milik karyawan lain).
6. Fase 3: ikon mata muncul di desktop dan mobile; dialog terbuka, menampilkan foto/keadaan kosong/keadaan error; Back Android menutup dialog; estate admin kebun A tidak bisa memuat foto kebun B.
7. Fase 3: kegagalan unggah foto tidak membatalkan penyimpanan vektor.
8. Desktop dan fitur lain (scanner layar penuh, tabel mobile) tidak berubah.

FORMAT LAPORAN
1. Tabel temuan Fase 0 (dengan kutipan baris).
2. Diff per berkas dan daftar migrasi SQL (nama berkas + isi ringkas).
3. Hasil Fase 4 per poin (LULUS / GAGAL / TIDAK BISA DIUJI + alasan).
4. Daftar hal yang tidak bisa Anda verifikasi dan perlu diuji manusia di perangkat.
```

---

## 4. Yang perlu Anda putuskan dan lakukan

### Keputusan
1. **Kebijakan foto wajah.** PRD (NFR 3) menyatakan tidak ada foto mentah diunggah, tetapi aplikasi sudah mengunggah foto absensi ke bucket publik. Foto pendaftaran adalah data pribadi pekerja. Mohon pastikan manager menyetujui, dan revisi NFR 3 di PRD. Saya sarankan bucket publik `attendance-photos` juga dikaji ulang.
2. **Ambang duplikasi.** Kode: 0,93 (rescan) dan 0,88 (edit). PRD: 0,88. Tentukan satu nilai. Catatan: 0,88 menolak lebih banyak wajah sebagai "duplikat" daripada 0,93.
3. **Ambang kecocokan 0,92.** Jangan diubah sebelum data Fase 1 terkumpul. Bila median genuine sore hari banyak di 0,88-0,91, pilihannya: turunkan ambang (risiko FAR naik, harus ditentukan dari data), atau perbaiki cara pendaftaran (ambil sampel lebih beragam). Keduanya keputusan Anda.

### Yang bisa Anda lakukan sekarang (tanpa kode)
- **Supabase SQL editor**, untuk satu pekerja yang gagal sore hari:
  ```sql
  select employee_id, updated_at,
         jsonb_typeof(descriptor_json) as tipe,
         case when jsonb_typeof(descriptor_json)='array'
              then jsonb_array_length(descriptor_json) end as panjang,
         left(descriptor_json::text, 60) as awal
  from master_descriptors
  where employee_id = <ID_PEKERJA>;
  ```
  Kirim hasil `tipe`, `panjang`, dan `updated_at` (bukan seluruh vektor). Bila `tipe` = `string`, itu temuan T4.
- **Ukur cosine sore hari.** Sambungkan HP ke Chrome desktop (`chrome://inspect`), buka scanner pada pekerja yang gagal, dan catat nilai `cosSim` dari log `[FASE 0 LOG]`. Bandingkan dengan nilai pagi hari. Ini menentukan apakah dugaan 1 atau dugaan 2 yang benar.
- **Uji rescan sore hari** pada pekerja yang gagal. Bila setelah rescan langsung lolos, penyebabnya kondisi wajah, bukan salinan vektor.
- Kirim `TabEmployeeManagement.jsx` bila ingin jalur Registrasi Wajah ikut saya tinjau sebelum prompt dijalankan.
