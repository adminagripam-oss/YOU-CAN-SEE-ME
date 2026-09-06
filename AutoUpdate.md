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
1. Buka terminal proyek di VSCode, pastikan Anda berada di dalam folder proyek (`FACE VERIFICATION`).
2. Jalankan perintah `npm.cmd run build` (gunakan `.cmd` jika Anda menggunakan Windows PowerShell untuk menghindari *error*).
3. Buka File Explorer (Windows), lalu masuk ke dalam folder hasil *build* bernama **`dist`**.
4. Di dalam folder `dist` tersebut, tekan `Ctrl + A` untuk memilih **semua isi file dan folder** (termasuk `index.html` dan `assets`).
5. Klik kanan pada file-file yang terblok tersebut > pilih **"Compress to ZIP file"** (atau "Add to archive..." format ZIP).
6. Beri nama file zip tersebut, misalnya `dist-1.0.1.zip`.

> [!TIP]
> **Di mana sebaiknya menyimpan file ZIP ini?** 
> Anda bisa memindahkannya keluar (misalnya ke folder utama `FACE VERIFICATION` atau folder `Downloads`/`Documents`). Hindari menyimpannya terus-menerus di dalam folder `dist`, karena folder `dist` akan dihapus dan dibuat ulang secara otomatis oleh sistem setiap kali Anda menjalankan perintah `npm.cmd run build` di masa mendatang.

### Langkah 3: Unggah ke Supabase & Ubah Versi
1. Unggah file `dist-1.0.1.zip` yang baru Anda buat ke dalam *bucket* `ota-updates` di Supabase.
2. Salin *URL Public* dari file zip tersebut.
3. Buka file `version.json` di komputer Anda, lalu perbarui `version` dan `url`-nya:
```json
{
  "version": "1.0.1",
  "url": "https://[SUPABASE-PROJECT-ID].supabase.co/storage/v1/object/public/ota-updates/dist-1.0.1.zip"
}
```
4. **PENTING: Unggah/timpa file `version.json` tersebut ke dalam *bucket* `ota-updates`.**

> [!IMPORTANT]
> **RANGKUMAN WAJIB SETIAP KALI ADA PEMBARUAN APLIKASI:**
> <u>Secara konkrit, rutinitas Anda setiap kali merilis fitur baru adalah:</u>
> 1. `npm.cmd run build`
> 2. Blok semua isi folder `dist`, lalu **Compress to ZIP** (Pastikan namanya *tidak* double ekstensi `.zip.zip`).
> 3. Buka file **`version.json`** di VSCode, naikkan angkanya (misal `1.0.2`), dan arahkan URL-nya ke nama file ZIP Anda yang baru.
> 4. Buka **Supabase Storage**, lalu **UNGGAH KEDUA FILE TERSEBUT** (file `.zip` terbaru **DAN** file `version.json` yang baru saja di-save). 
> 
> *Aplikasi di HP hanya akan menyadari ada pembaruan jika file `version.json` di Supabase berhasil ditimpa dengan versi yang lebih tinggi!*

Selesai! Sekarang, saat mandor/pengguna menekan tombol **"Cek Pembaruan"** di aplikasi Android, modal pembaruan akan langsung muncul dan mengunduh ZIP terbaru yang Anda unggah. 🎯
