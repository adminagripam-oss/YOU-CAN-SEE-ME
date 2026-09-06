# Walkthrough: Fitur OTA Update Berhasil Diimplementasikan 🚀

Seluruh rancangan dari Rencana Implementasi telah sukses ditanamkan ke dalam kode Anda. Aplikasi Android kini memiliki kapabilitas untuk memperbarui antarmuka (Web Bundle) secara **Over-The-Air (OTA)** tanpa perlu *re-install* APK!

## Apa saja yang telah diubah?
1. **Plugin Capgo**: Memasang `@capgo/capacitor-updater` dan menonaktifkan fitur *autoUpdate* standar agar kita bisa mengendalikan alurnya sendiri.
2. **Tombol Baru**: Menambahkan tombol "Cek Pembaruan" di sudut *header* (Top Bar) khusus saat perangkat berada dalam mode Online.
3. **Modal Pembaruan Wajib (*Blocking*)**: Membuat komponen `OTAUpdateModal` bergaya Shadcn UI (bersih, minimalis). Modal ini tidak memiliki tombol *close*, sehingga pengguna diwajibkan melakukan pembaruan saat *update* rilis.
4. **Logika Fetch ke Supabase**: `App.jsx` kini memiliki fungsi `checkForUpdates()` yang akan memeriksa file `version.json` di dalam *bucket* publik Supabase.

---

## Bagaimana Cara Merilis Pembaruan (Menggunakan Supabase)

Karena Anda memilih **Opsi 2 (Self-Hosted Supabase)**, berikut adalah langkah-langkah yang harus Anda lakukan ke depannya saat ingin mengirimkan fitur/update baru ke semua pengguna aplikasi Android:

### Langkah 1: Buat Bucket di Supabase (Sekali Saja)
1. Buka *dashboard* Supabase Anda, masuk ke menu **Storage**.
2. Klik **New Bucket**, beri nama `ota-updates`.
3. **PENTING:** Pastikan Anda mencentang opsi **Public Bucket** agar aplikasi Android bisa membacanya tanpa token otentikasi.

### Langkah 2: Lakukan Build & Zip Kode Terbaru
Setiap kali Anda selesai melakukan *coding* fitur baru, ikuti urutan ini:
1. Buka terminal proyek, jalankan `npm run build`
2. Buka folder proyek Anda di File Explorer, cari folder bernama `dist`.
3. Kompres folder `dist` tersebut menjadi file `.zip` (misalnya: `dist-1.0.1.zip`).

### Langkah 3: Unggah ke Supabase & Ubah Versi
1. Unggah file `dist-1.0.1.zip` yang baru Anda buat ke dalam *bucket* `ota-updates` di Supabase.
2. Salin *URL Public* dari file zip tersebut.
3. Buat file baru di komputer Anda bernama `version.json` yang isinya seperti ini:
```json
{
  "version": "1.0.1",
  "url": "https://[SUPABASE-PROJECT-ID].supabase.co/storage/v1/object/public/ota-updates/dist-1.0.1.zip"
}
```
4. Unggah/timpa file `version.json` tersebut ke dalam *bucket* `ota-updates`.

Selesai! Sekarang, saat mandor/pengguna menekan tombol **"Cek Pembaruan"** di aplikasi Android, modal pembaruan akan langsung muncul dan mengunduh ZIP terbaru yang Anda unggah. 🎯
