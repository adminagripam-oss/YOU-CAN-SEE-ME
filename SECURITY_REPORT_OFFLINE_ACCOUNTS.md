# Laporan Analisis Risiko Keamanan: Akun Fallback Offline (`OFFLINE_FALLBACK_ACCOUNTS`)

**Tanggal:** 16 Oktober 2026  
**Status:** DOKUMENTASI RISIKO & USULAN ARSITEKTUR (Tanpa Perubahan Kode Produksi)  
**Tujuan:** Memberikan analisis risiko mendalam kepada Pemilik Proyek terkait array `OFFLINE_FALLBACK_ACCOUNTS` pada `src/context/AuthContext.jsx`.

---

## 1. Ringkasan Eksekutif

Pada implementasi saat ini, aplikasi AgriFace menyertakan daftar akun fallback offline (`OFFLINE_FALLBACK_ACCOUNTS`) sebanyak ~35 akun (termasuk peran `estate_admin`, `mandor`, dan `headoffice_admin`) yang disematkan langsung di dalam kode JavaScript frontend (`src/context/AuthContext.jsx`).

Sesuai aturan keras instruksi review 3.2.7 (Temuan 6), daftar akun ini **tidak diubah atau dihapus** tanpa keputusan eksplisit pemilik proyek. Dokumen ini merinci risiko nyata yang timbul serta usulan arsitektur pengganti yang aman.

---

## 2. Temuan Risiko Keamanan

### Risiko A: Kata Sandi Polos Terkemas dalam Bundle APK dan Web (High Severity)
- **Bukti:** Berkas `src/context/AuthContext.jsx` baris 20-56 memuat array objek akun dengan field `username` dan `password` dalam format teks polos (*plaintext*).
- **Dampak:** Saat aplikasi dikompilasi menjadi APK Android atau bundle JavaScript web/PWA melalui Vite, seluruh string kata sandi ini ikut terkompilasi ke dalam aset statis. Siapa pun yang mengekstrak berkas `.apk` (misal dengan `apktool` atau `unzip`) atau menginspeksi file JavaScript di browser dapat membaca seluruh username dan password admin dan mandor.

### Risiko B: Fallback Login Berlaku pada Error Apa Pun (Medium-High Severity)
- **Bukti:** Blok `catch (err)` pada fungsi login `AuthContext.jsx` langsung mencoba pencocokan ke `OFFLINE_FALLBACK_ACCOUNTS` setiap kali permintaan login Supabase menghasilkan error.
- **Dampak:** Fallback ini tidak hanya aktif saat perangkat benar-benar luring (offline), tetapi juga terpicu saat:
  1. Server Supabase sedang mengalami downtime atau degradasi (500/503).
  2. Terjadi timeout jaringan atau DNS failure.
  3. Pengguna salah mengetik kredensial online namun cocok dengan entri fallback lokal lama.
  Hal ini menciptakan ketidakkonsistenan status otentikasi antara server dan klien.

### Risiko C: Otorisasi Berdasarkan `localStorage` Tanpa Validasi Tanda Tangan (Medium Severity)
- **Bukti:** Sesi login disimpan ke dalam `localStorage.setItem('logged_in_admin', JSON.stringify(adminUser))`. Komponen `RoleGuard` dan `App.jsx` membaca role langsung dari objek JSON ini tanpa verifikasi JWT signature dari server.
- **Dampak:** Pada lingkungan browser web, pengguna lokal yang memiliki akses ke Chrome DevTools dapat mengubah nilai `role` di `localStorage` (misal dari `mandor` menjadi `headoffice_admin`), membuka akses menu administratif tingkat tinggi secara tidak sah pada tampilan UI lokal.

---

## 3. Usulan Rancangan Pengganti (Zero-Plaintext Architecture)

Untuk mempertahankan kapabilitas login offline tanpa mengekspos kredensial polos ke dalam bundle aplikasi, diusulkan arsitektur berikut:

```
[Pengguna Login Pertama Kali (Online)]
                   │
                   ▼
       Validasi ke Supabase Auth
                   │
         Sukses Verifikasi
                   │
                   ▼
  Hash Kata Sandi secara Lokal (Argon2 / PBKDF2 + Unique Salt)
                   │
                   ▼
  Simpan ke SQLite / Dexie: `local_admins`
  (id, username, password_hash, salt, role, kebun, region, last_login)

──────────────────────────────────────────────────────────────────────────

[Pengguna Login Berikutnya (Kondisi Offline)]
                   │
                   ▼
  Periksa Status Jaringan (Network.getStatus().connected === false)
                   │
         HANYA BILA OFFLINE
                   │
                   ▼
  Cari username di tabel `local_admins` lokal
                   │
  Verifikasi hash kata sandi input dengan salt tersimpan
                   │
     Cocok -> Berikan Akses Sesi Offline
```

### Prinsip Utama Usulan:
1. **Tidak Ada Kredensial di Kode Sumber:** Hapus seluruh array `OFFLINE_FALLBACK_ACCOUNTS` dari bundle JavaScript.
2. **First-Login Online Seed:** Perangkat harus pernah berhasil login online minimal satu kali untuk mengunduh identitas dan menyimpan hash kredensial admin ke SQLite lokal (`local_admins`).
3. **Strict Offline Gate:** Fallback login lokal HANYA boleh dipicu jika `Network.getStatus().connected === false`. Jika perangkat online tetapi server menolak (kredensial salah / user disabled), sistem tidak boleh memberikan akses fallback.
4. **Local Hash Standard:** Gunakan algoritma *cryptographic key derivation* standar (seperti Web Crypto API `crypto.subtle.deriveBits` dengan PBKDF2-SHA256 minimal 100.000 iterasi) untuk memverifikasi kata sandi di perangkat lokal.

---

## 4. Rekomendasi Langkah

1. **Rilis 3.2.8:** Pertahankan kode fallback akun apa adanya sesuai instruksi pemilik proyek, dengan perbaikan komentar cut-off dan penanganan logout tertunda.
2. **Rilis Mendatang (3.3.0):** Agendakan migrasi ke sistem hash `local_admins` berbasis online-seed setelah disetujui secara tertulis oleh pemilik proyek.
