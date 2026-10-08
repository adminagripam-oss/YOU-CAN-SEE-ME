# AgriFace OTA 3.1.1 — Diagnosis, Plan Perbaikan, dan Prompt untuk AI Agent

Dasar analisis: isi `dist-3.1.1.zip` (hasil build, sudah di-minify), `version.json`, dan `PRD.md` (v2.1.4).
Source code asli tidak saya terima, jadi lokasi di bawah memakai **nama file bundle + nama fungsi**. AI agent harus mencari padanannya di source.

Tingkat keyakinan:
- **[TERVERIFIKASI]** terbaca langsung di kode bundle.
- **[HIPOTESIS]** masuk akal, tetapi harus dibuktikan dulu lewat diagnosis Fase 0.

---

## 1. Ringkasan akar masalah

### Masalah 1 — Setelah rescan wajah, karyawan "hilang" saat absensi

| # | Temuan | Status | Lokasi (bundle → fungsi) |
|---|---|---|---|
| 1.1 | Penyimpanan master ke SQLite memakai `INSERT OR REPLACE INTO local_employees` yang menulis ulang **seluruh baris**. Saat rescan online, pemanggilnya hanya mengirim `nik, name, department, descriptor_json`. Kolom `afdeling, nama_kebun, status_tk, jabatan, status_perkawinan, region` ditimpa **NULL**. Baris juga di-set `is_synced=1`. | TERVERIFIKASI | `sqliteService` → `sqliteCacheUserMasterVector`; dipanggil dari `DaftarKaryawanPage` (cabang online simpan biometrik & edit karyawan) |
| 1.2 | Di halaman absensi, jika vektor master tidak ada di cache lokal tetapi ditemukan di Supabase, kode memanggil fungsi yang sama dengan `r = {}` bila karyawan tak ada di props. Hasilnya `nik=''` dan `name=''` **menimpa baris karyawan yang valid**. | TERVERIFIKASI | `AbsensiPage` → `_t` (load master vector) |
| 1.3 | Akibatnya karyawan tidak lagi cocok dengan filter kebun/afdeling/region atau tampil dengan nama kosong. Mekanisme persis di UI perlu dikonfirmasi. | HIPOTESIS KUAT | Daftar karyawan di `index` (fetch employees) dan dropdown `AbsensiPage` |
| 1.4 | Sinkronisasi cloud ke lokal hanya memakai delta `created_at > lastSync`. Rescan tidak mengubah `created_at`, jadi perubahan template **tidak pernah turun** ke perangkat lain. Kode juga hanya mengunduh descriptor untuk karyawan yang **belum ada** di cache lokal. | TERVERIFIKASI | `index` → fetch employees (delta sync + `[Sync Pull]`) |

### Masalah 2 — Data pendaftaran ulang tidak bisa disimpan

| # | Temuan | Status | Lokasi |
|---|---|---|---|
| 2.1 | Jalur edit online menjalankan `.from('employees').update(t)` dengan `t` berisi `has_master_biometric, jabatan, status_tk, status_perkawinan, nama_kebun`. PRD §7.1 tidak punya kolom-kolom ini di `employees` (PRD memakai `kebun`, bukan `nama_kebun`). Jika kolom tidak ada di DB, PostgREST menolak seluruh update dan karyawan tampil sebagai "gagal simpan". Kode lain di bundle (`.eq('nama_kebun', …)`, `select('*')`, dan `e.has_master_biometric`) mengisyaratkan kolom itu ada. Jadi **schema Supabase dan PRD tidak sinkron**, dan yang benar harus dicek langsung di DB. | HIPOTESIS (cek schema) | `DaftarKaryawanPage` → handler submit edit |
| 2.2 | Jalur simpan biometrik online memanggil `employees.update({has_master_biometric:true})` lalu `master_descriptors.upsert`. Jika langkah pertama gagal, langkah kedua tidak dijalankan. Kode memang menangkap error ini dan hanya menulis ke lokal, lalu toast tetap "Tersimpan Lokal" tanpa antrean sync. **Template tidak pernah naik ke server.** | TERVERIFIKASI | `DaftarKaryawanPage` → simpan biometrik |
| 2.3 | `descriptor_json` dikirim sebagai **string** (`JSON.stringify(array)`) ke kolom JSONB, sehingga tersimpan sebagai string scalar, bukan array. Pengecekan duplikat memakai `Array.isArray(...)` dan **melewati** baris berbentuk string, sehingga cek duplikat tidak efektif. Format di DB juga bercampur. | TERVERIFIKASI | `DaftarKaryawanPage`, sync engine di `index` |
| 2.4 | Sync engine memanggil `master_descriptors.upsert(...)` **tanpa memeriksa `error`**, lalu menghapus item dari antrean lokal. Jika upsert gagal (RLS, kolom, jaringan), template hilang permanen. | TERVERIFIKASI | `index` → auto-sync employees |
| 2.5 | Capture enrollment butuh 7 sampel yang lolos gerbang kualitas: `score ≥ 0.8` dan yaw/pitch/roll `≤ 0.15 rad` (±8.6°). Pada HP kelas menengah-bawah hitungan sampel bisa macet di bawah 7/7, sehingga tombol simpan tidak aktif. | HIPOTESIS | `biometrics` → `isQualityEmbedding`; `DaftarKaryawanPage` |
| 2.6 | Ambang duplikat tidak konsisten: 0.93 saat capture (`DUPLICATE_COSINE_THRESHOLD`), 0.88 saat submit edit (konstanta lokal `B`), dan PRD menyebut 0.88 serta 0.55. Cek saat submit bisa memblokir karyawan berbeda yang mirip. | TERVERIFIKASI (inkonsistensi) | `biometrics`, `DaftarKaryawanPage` |
| 2.7 | Semua insert SQLite ke `local_employees` ditelan `try/catch` yang hanya `console.error`. Jika tabel hasil migrasi dari versi lama tidak punya kolom baru (`nama_kebun`, `status_tk`, `jabatan`, `status_perkawinan`), setiap simpan gagal **tanpa pesan ke user**. Hanya `region`, `is_synced`, dan `syncStatus` yang dijaga `ALTER TABLE ADD COLUMN`. | HIPOTESIS (cek `PRAGMA table_info`) | `sqliteService` → migrasi `local_employees` |

### Masalah 3 — Scan wajah gagal sebelum daftar ulang (template lama)

| # | Temuan | Status | Lokasi |
|---|---|---|---|
| 3.1 | Kode hanya menghitung kecocokan bila `scanVector.length === masterVector.length`. Jika tidak sama (mis. template lama 128-dim), skor tetap 0 dan **tidak ada pesan error**. UI berhenti di "Mencocokkan Wajah…" selamanya. | TERVERIFIKASI | `AbsensiPage` → loop `onFaceProcessed` |
| 3.2 | Template lama tidak punya penanda versi. `TEMPLATE_VERSION: 1` ada di konfigurasi, tetapi tidak pernah ditulis ke penyimpanan dan tidak pernah dibaca. | TERVERIFIKASI | `biometrics`, tabel `local_master_descriptors` |
| 3.3 | Pipeline embedding berubah: PRD menyebut FP16 WebGL dipaksa, tetapi kode hanya **mencatat** `WEBGL_FORCE_F16_TEXTURES` dan tidak menetapkannya. Backend juga bisa jatuh ke `cpu` secara diam-diam, yang menghasilkan angka berbeda. Template lama (preprocessing berbeda) dan scan baru bisa berjarak cosine < 0.90. | TERVERIFIKASI (tidak dipaksa); dampak HIPOTESIS | `index` → `humanSingleton` |
| 3.4 | Gerbang scan ketat dan **mereset jendela 5 sampel** setiap kali satu frame gagal kualitas (sudut > 0.15 rad). Wajah harus lebar 140–360 px. Di HP lemah jendela hampir tidak pernah penuh. | TERVERIFIKASI | `AbsensiPage` |
| 3.5 | Ambang match `0.90` (median 5 sampel). Template lama yang berupa sampel tunggal tidak mungkin sebaik mean-template baru. | HIPOTESIS | `biometrics` |
| 3.6 | Template master yang usang tidak pernah diperbarui di perangkat lain (lihat 1.4), sehingga perangkat tertentu tetap memakai template lama. | TERVERIFIKASI | `index` |

### Temuan pendukung (di luar 3 keluhan, tetapi memperbesar risiko)
- Versi tidak seragam: `version.json` = **3.1.1**, `sw.js` `CACHE_NAME` = **agriface-v3.4.0**, `human.esm.js?v=3.3.6-patch4`, PRD = **2.1.4**. Ini menyulitkan debugging dan rollback.
- `eruda.init()` aktif di `index.html` produksi (konsol debug terbuka untuk semua pengguna). Boleh dipakai sementara untuk diagnosis, lalu harus dimatikan.
- Sync engine memakai `window.alert(...)` untuk error sinkronisasi (memblokir UI di lapangan).
- Skema Dexie: `version(9)` ditulis sebelum `version(8)` dan tidak memuat `employee_sync_queue`. Untuk Web ini tidak fatal, tetapi rawan salah migrasi.
- OTA Capgo hanya mengganti bundle web. **SQLite native tidak ikut direset**, jadi seluruh data dan skema lama ikut terbawa ke 3.1.1. Migrasi di dalam aplikasi wajib aman dan idempoten.

---

## 2. Plan perbaikan (berurutan)

### Fase 0 — Amankan dan diagnosis (jangan ubah kode dulu)
1. **Hentikan distribusi 3.1.1** ke perangkat yang belum ter-update. Naikkan `version.json` kembali ke bundle stabil terakhir (rollback Capgo).
2. Siapkan perangkat uji berisi data lama (DB lama + OTA ke 3.1.1). Aktifkan Eruda sementara.
3. Kumpulkan bukti (ekspor ke file):
   - `PRAGMA table_info(...)` untuk semua tabel lokal, serta `SELECT COUNT(*)`, `nama_kebun IS NULL`, `name=''` pada `local_employees`.
   - Di Supabase: `information_schema.columns` untuk `employees`, `master_descriptors`, dan definisi RPC `sync_offline_employee`.
   - Teks error persis saat gagal simpan (toast dan console).
   - `console.table` "FASE 0 LOG" dari scan karyawan lama: `cosSim, masterLen, scanLen, backend, filterEnabled`.
4. Keluaran Fase 0: tabel "hipotesis → terbukti / terbantah" yang menjadi dasar Fase 1–3.

### Fase 1 — Integritas data karyawan (Masalah 1)
- Ganti `INSERT OR REPLACE` pada penyimpanan master dengan **dua operasi terpisah**: `UPDATE` / upsert yang memakai `COALESCE(new, old)` per kolom untuk data karyawan, dan upsert khusus tabel `local_master_descriptors`. Menyimpan template **tidak boleh** menyentuh kolom profil.
- Larang menulis `nik=''` atau `name=''`. Jika data profil tidak tersedia, hanya update tabel descriptor.
- Perbaiki pemanggil (`DaftarKaryawanPage` online/offline, `AbsensiPage._t`) agar mengirim objek karyawan lengkap atau tidak mengirim profil sama sekali.
- Tambah skrip pemulihan: untuk baris `local_employees` yang rusak (kolom NULL / nama kosong), isi ulang dari Supabase (`employees`) bila online. Jangan hapus data lokal.

### Fase 2 — Simpan pendaftaran ulang yang andal (Masalah 2)
- **Whitelist payload** `employees.update` sesuai kolom yang benar-benar ada (hasil Fase 0). Buat adapter tunggal untuk pemetaan `nama_kebun` ↔ `kebun`.
- Simpan template ke `master_descriptors` **terpisah dari** update `employees`, sebagai **array JSON** (bukan string). Sertakan kolom baru `template_version`, `embedding_dim`, `updated_at`.
- Setiap kegagalan cloud masuk ke **antrean sync** (bukan hanya cache lokal), dengan status yang terlihat user ("Menunggu sinkron").
- Sync engine: periksa `error` dari upsert; hapus item antrean hanya setelah server mengonfirmasi (baca ulang atau `returning`). Ganti `window.alert` dengan toast non-blokir.
- Seragamkan ambang duplikat menjadi **satu konstanta** di `biometrics`, dikalibrasi ulang dari data nyata. Tampilkan juga nilai kemiripan dan nama karyawan yang bentrok, dan izinkan override oleh admin dengan alasan tercatat.
- Cek duplikat harus memakai `toVectorArray` agar baris berformat string ikut dibandingkan.
- Kendurkan gerbang kualitas enrollment secara terukur (mis. 0.25 rad) dengan umpan balik arah ("hadap sedikit ke kiri"), tanpa menurunkan keamanan duplikat.

### Fase 3 — Scan dan template lama (Masalah 3)
- Tambah **deteksi template lama**: dimensi ≠ 1024, `template_version` kosong/lama, atau NaN/semua nol → tampilkan pesan jelas "Template wajah lama, daftar ulang diperlukan" dengan tombol langsung ke pendaftaran ulang. Hilangkan kegagalan senyap.
- Paksa pipeline konsisten: set `WEBGL_FORCE_F16_TEXTURES` sesuai PRD (atau putuskan FP32 lalu dokumentasikan), kunci `backend`, dan **catat backend yang dipakai di setiap template**. Tolak/peringatkan jika backend scan ≠ backend enrollment.
- Perbaiki jendela scan: jangan reset total saat satu frame buruk. Pakai toleransi (mis. buang frame buruk, reset hanya setelah N frame berturut-turut buruk) dan tampilkan panduan penyebab (terlalu jauh, miring, gelap).
- Sinkronisasi template: tarik descriptor berdasarkan `updated_at` (bukan hanya `created_at` dan bukan hanya "belum ada di cache"). Gunakan pull per-karyawan yang `updated_at` server > lokal.
- Pertimbangkan **mode kompatibilitas sementara**: bila template lama bisa dibandingkan (dimensi sama), izinkan match dengan ambang lebih rendah **disertai re-enroll otomatis di balik layar** setelah login admin. Putuskan setelah data Fase 0 (jangan lakukan bila FAR naik).

### Fase 4 — Migrasi dan keamanan OTA
- Tambah `PRAGMA user_version` (atau tabel `schema_meta`) dan migrasi bertahap yang **idempoten**. Semua kolom baru lewat `ADD COLUMN` yang diperiksa dengan `PRAGMA table_info`; migrasi gagal harus mengangkat error ke log dan menahan aplikasi di mode aman, bukan diam.
- Cadangkan `local_employees`, `local_master_descriptors`, dan antrean sebelum migrasi (tabel `_bak_<versi>`).
- Seragamkan versi: satu sumber (`package.json` / `version.json`) untuk `CACHE_NAME`, query `human.esm.js`, dan label UI.
- Panggil `notifyAppReady()` **setelah** health check (DB terbuka, migrasi sukses, model AI termuat), supaya Capgo bisa rollback otomatis bila gagal.
- Matikan Eruda di build produksi (atau batasi lewat gestur tersembunyi).
- Rilis bertahap: 1 perangkat uji → 1 kebun → semua. Jangan timpa nama bundle yang sama.

### Fase 5 — Pengujian dan kriteria penerimaan
Matriks uji minimal (perangkat nyata, bukan emulator):

| Skenario | Hasil yang diharapkan |
|---|---|
| Update dari DB lama ke 3.1.1 (OTA) | Migrasi sukses, jumlah karyawan sama, tidak ada kolom NULL baru |
| Rescan karyawan online, lalu absensi | Karyawan tetap tampil lengkap; absensi sukses |
| Rescan offline, lalu online | Template naik ke server; antrean kosong; server memuat array 1024 |
| Rescan di perangkat A, scan di perangkat B | B menerima template baru setelah sync |
| Scan dengan template lama (dimensi/versi lama) | Pesan jelas "daftar ulang"; tidak ada loading tanpa akhir |
| Dua karyawan mirip (kembar/saudara) | Duplikat terdeteksi sesuai kebijakan; admin bisa override tercatat |
| HP kelas bawah, cahaya redup | Enrollment dan scan selesai < 15 detik |
| Sync error (RLS/kolom/jaringan putus) | Item tetap di antrean; badge benar; tidak ada data hilang |

Kriteria lulus: **0 karyawan hilang**, **0 template tak tersinkron tanpa terlihat**, rescan sukses ≥ 95% percobaan, FAR tidak naik dibanding baseline.

---

## 3. PROMPT UNTUK AI AGENT (salin dari sini)

```
PERAN
Anda adalah senior engineer (React 19 + Vite, Capacitor Android, SQLite native
via @capacitor-community/sqlite, Dexie untuk Web, Supabase, @capgo/capacitor-updater,
@vladmandic/human). Anda memperbaiki regresi serius pada rilis OTA 3.1.1 aplikasi
AgriFace (absensi wajah 1-to-1, offline-first). Bekerja bertahap, hati-hati dengan
data produksi.

GEJALA YANG HARUS DISELESAIKAN
1. Setelah karyawan dari data lama di-rescan wajahnya, data karyawan "hilang"
   saat akan absensi.
2. Data pendaftaran ulang (rescan/update biometrik) tidak bisa disimpan.
3. Sebelum daftar ulang, scan wajah karyawan lama tidak berhasil.

ATURAN KERJA (WAJIB)
- Jangan mengubah desain UI selain pesan/indikator yang diminta.
- Jangan menghapus atau menimpa data lokal/produksi. Semua migrasi harus
  idempoten, non-destruktif, dan didahului cadangan tabel.
- Setiap klaim akar masalah harus dibuktikan (log/query/test) sebelum diperbaiki.
  Jika bukti membantah hipotesis di bawah, katakan dan sesuaikan.
- Jangan menelan error secara diam-diam. Error penyimpanan/sinkronisasi harus
  terlihat user (toast non-blokir) dan terlog dengan konteks.
- Akhiri setiap fase dengan: file yang diubah, alasan, cara uji, dan risiko tersisa.

KONTEKS TEKNIS (hasil analisis bundle dist-3.1.1; cari padanannya di source)
- Penyimpanan master: fungsi sqliteCacheUserMasterVector (cacheUserMasterVector)
  memakai "INSERT OR REPLACE INTO local_employees (...)" yang menimpa SELURUH baris.
  Pemanggil di DaftarKaryawanPage (simpan biometrik online & edit karyawan online)
  hanya mengirim nik, name, department, descriptor_json -> afdeling, nama_kebun,
  status_tk, jabatan, status_perkawinan, region menjadi NULL; is_synced=1.
- AbsensiPage._t (load master vector): saat cache miss tetapi vektor ada di
  Supabase, memanggil fungsi yang sama dengan r = {} sehingga nik='' dan name=''
  dapat menimpa baris karyawan valid.
- Fetch employees (index): delta sync memakai created_at > lastSync dan hanya
  mengunduh master_descriptors untuk karyawan yang belum ada di cache lokal.
  Rescan tidak mengubah created_at -> template baru tidak turun ke perangkat lain.
- DaftarKaryawanPage (edit online): employees.update(t) dengan t berisi
  has_master_biometric, jabatan, status_tk, status_perkawinan, nama_kebun.
  PRD §7.1 tidak mencantumkan kolom-kolom itu (PRD memakai "kebun"). Kode lain
  memakai .eq('nama_kebun', ...). Schema DB vs PRD kemungkinan tidak sinkron.
- DaftarKaryawanPage (simpan biometrik online): employees.update({has_master_biometric})
  lalu master_descriptors.upsert; bila langkah pertama error, kode jatuh ke
  penyimpanan lokal saja dan tidak mengantre sync.
- descriptor_json dikirim sebagai string (JSON.stringify) ke kolom JSONB; cek
  duplikat memakai Array.isArray sehingga melewati baris bertipe string.
- Sync engine employee (index): master_descriptors.upsert tidak memeriksa error
  lalu antrean lokal dihapus. Ada window.alert pada error.
- AbsensiPage (scan): cosine hanya dihitung bila panjang vektor scan === panjang
  master; jika beda skor tetap 0 tanpa pesan. Gerbang kualitas (score>=0.8,
  |yaw|,|pitch|,|roll|<=0.15 rad, lebar wajah 140-360 px) mereset jendela 5 sampel
  tiap frame gagal. MATCH_COSINE_THRESHOLD=0.90 (median 5 sampel).
- biometrics: DUPLICATE_COSINE_THRESHOLD=0.93 saat capture, tetapi konstanta lokal
  0.88 saat submit edit; PRD menyebut 0.88 dan 0.55. TEMPLATE_VERSION=1 tidak
  pernah disimpan/dibaca.
- humanSingleton (index): WEBGL_FORCE_F16_TEXTURES hanya dicatat, tidak dipaksa;
  backend bisa jatuh ke cpu tanpa tanda pada template.
- sqliteService migrasi: hanya region, is_synced, syncStatus yang dijaga ADD COLUMN
  untuk local_employees; kolom lain diasumsikan ada. Insert yang gagal hanya
  console.error.
- Versi tidak seragam: version.json=3.1.1, sw.js CACHE_NAME=agriface-v3.4.0,
  human.esm.js?v=3.3.6-patch4, PRD=2.1.4. Eruda aktif di index.html produksi.
- OTA Capgo hanya mengganti bundle web; SQLite native persisten lintas OTA.

FASE 0 — DIAGNOSIS (JANGAN UBAH LOGIKA DULU)
a. Tambahkan utilitas diagnostik (mode debug) yang mengeluarkan: PRAGMA table_info
   semua tabel lokal, hitungan baris, jumlah local_employees dengan nama_kebun
   NULL / name kosong / nik kosong, dan distribusi panjang vektor di
   local_master_descriptors (1024 / lainnya / null).
b. Tangkap error persis pada alur: (1) rescan online, (2) rescan offline,
   (3) scan karyawan lama. Sertakan nama tabel/kolom/response Supabase.
c. Bandingkan skema Supabase (employees, master_descriptors, RPC
   sync_offline_employee) dengan kolom yang dipakai kode. Buat tabel selisih.
d. Reproduksi masalah 1 secara otomatis (test/skrip): karyawan lengkap -> rescan
   online -> cek baris local_employees -> cek muncul di daftar absensi.
Keluaran: laporan "hipotesis -> terbukti/terbantah" + rencana perubahan. Berhenti
dan tampilkan laporan sebelum lanjut ke Fase 1.

FASE 1 — INTEGRITAS DATA KARYAWAN
- Pisahkan penyimpanan template dari data profil. Template hanya menulis
  local_master_descriptors (+ flag has_master_biometric, template_version,
  embedding_dim, backend, updated_at). Update profil memakai UPDATE per kolom /
  COALESCE(baru, lama); DILARANG INSERT OR REPLACE untuk baris karyawan.
- Tolak penulisan nik='' atau name='' ke local_employees.
- Perbaiki semua pemanggil (DaftarKaryawanPage online/offline, AbsensiPage._t,
  sync engine) agar tidak menimpa profil.
- Buat rutin pemulihan satu kali: untuk baris rusak, isi kolom profil dari Supabase
  bila online; tanpa menghapus apa pun.
- Tambahkan unit test untuk fungsi penyimpanan (profil tidak berubah setelah
  simpan template).

FASE 2 — PENYIMPANAN PENDAFTARAN ULANG
- Buat adapter kolom tunggal (nama_kebun <-> kebun) dan whitelist payload
  employees.update sesuai skema hasil Fase 0. Sediakan SQL migrasi Supabase
  (idempoten, IF NOT EXISTS) untuk kolom yang memang perlu ada, termasuk di
  master_descriptors: template_version INT, embedding_dim INT, backend TEXT,
  updated_at TIMESTAMPTZ DEFAULT now(). Tampilkan SQL untuk saya setujui;
  JANGAN menjalankannya sendiri.
- Simpan template ke master_descriptors sebagai array JSON (bukan string).
  Sediakan skrip normalisasi baris lama bertipe string menjadi array.
- Jangan gabungkan update employees dan master_descriptors dalam satu try yang
  saling membatalkan. Kegagalan cloud -> masuk antrean sync dengan status
  "menunggu sinkron" yang terlihat.
- Sync engine: periksa error upsert, hapus item antrean hanya setelah konfirmasi
  server (read-back), retry dengan backoff, ganti window.alert dengan toast.
- Satukan ambang duplikat di modul biometrics (satu konstanta, dipakai di capture
  dan submit). Pakai toVectorArray saat membandingkan. Tampilkan nama & persentase
  bentrokan; sediakan override admin dengan alasan yang tercatat.
- Longgarkan gerbang kualitas enrollment secara terukur dengan umpan balik arah
  (jangan menurunkan ambang duplikat).

FASE 3 — SCAN & TEMPLATE LAMA
- Saat memuat master: validasi dimensi (=1024), tidak ada NaN/semua nol,
  template_version, dan backend. Jika tidak valid -> tampilkan "Template wajah
  lama. Daftar ulang diperlukan" + tombol menuju pendaftaran ulang. Hilangkan
  kondisi "Mencocokkan Wajah..." tanpa akhir.
- Konsistenkan pipeline embedding: tetapkan dan paksa WEBGL_FORCE_F16_TEXTURES
  sesuai keputusan (PRD: FP16), kunci backend, catat backend di template, dan
  peringatkan bila backend scan berbeda dari backend enrollment.
- Perbaiki jendela scan 5 sampel: buang frame buruk tanpa mereset seluruh jendela;
  reset hanya setelah N frame buruk berturut-turut. Tampilkan alasan spesifik.
- Sinkronisasi template berbasis updated_at: tarik master_descriptors yang
  updated_at server > lokal, bukan hanya karyawan yang belum ada di cache.
- Evaluasi (jangan langsung aktifkan) mode kompatibilitas template lama; sajikan
  data cosine genuine/impostor dari perangkat uji sebelum memutuskan.

FASE 4 — MIGRASI & OTA
- Tambahkan schema_version (PRAGMA user_version atau tabel schema_meta) dan
  migrasi bertahap idempoten; semua ADD COLUMN diperiksa lewat PRAGMA table_info.
  Cadangkan tabel sebelum migrasi. Jika migrasi gagal -> mode aman + log jelas,
  bukan diam.
- Satukan versi dari satu sumber (CACHE_NAME sw.js, query human.esm.js, label UI,
  version.json). Perbarui PRD agar sesuai.
- Panggil notifyAppReady() hanya setelah health check (DB terbuka, migrasi OK,
  model AI termuat).
- Nonaktifkan Eruda di build produksi.
- Perbaiki urutan versi Dexie (v8/v9) dengan benar dan tes upgrade dari v7.

FASE 5 — PENGUJIAN & PENYERAHAN
- Tulis tes otomatis (unit + integrasi dengan SQLite in-memory/mocks Supabase)
  untuk: tidak menimpa profil, simpan template online/offline, antrean sync gagal
  dan retry, template format string vs array, deteksi template lama.
- Sediakan skenario uji manual di perangkat nyata (upgrade dari DB lama via OTA,
  rescan online/offline, scan lintas perangkat, template lama).
- Kriteria lulus: 0 karyawan hilang; tidak ada template yang hilang diam-diam;
  rescan sukses >=95%; FAR tidak naik.
- Hasilkan: ringkasan perubahan, SQL migrasi Supabase untuk saya setujui,
  panduan rilis bertahap (1 perangkat -> 1 kebun -> semua) dan langkah rollback
  Capgo. Naikkan versi bundle baru; JANGAN menimpa nama bundle 3.1.1.

MULAI DARI FASE 0 dan laporkan hasilnya sebelum mengubah logika apa pun.
```

---

## 4. Informasi yang membantu mempercepat (kirim ke agent bila ada)
1. Teks error persis saat "tidak bisa menyimpan" (toast dan console Eruda).
2. Hasil `information_schema.columns` untuk `employees` dan `master_descriptors`, serta definisi RPC `sync_offline_employee`.
3. Versi aplikasi lama (sebelum 3.1.1) yang menjadi asal data karyawan lama, dan cara template lama dibuat (kamera atau upload foto; pustaka face-api atau human).
4. Apakah masalah terjadi di semua perangkat atau hanya sebagian, serta apakah perangkat itu pernah offline saat update.
5. Source code (atau repo) agar agent tidak bekerja dari bundle ter-minify.
