# Tampilan Mobile Lebih Penuh agar Tabel Terlihat: Rencana dan Prompt

Berlaku untuk halaman data di mobile: Log Absensi (+ tab Persetujuan Edit/Hapus), Monitoring Absensi, Daftar Karyawan, dan Mutasi Karyawan.

Dokumen ini terdiri dari:
- **Bagian A**: temuan dari screenshot dan CSS Anda.
- **Bagian B**: keputusan desain dan asumsi default.
- **Bagian C**: rencana kerja bertahap.
- **Bagian D**: prompt lengkap untuk AI coding assistant.
- **Bagian E**: kode yang saya butuhkan agar bisa memberi patch konkret.

---

# BAGIAN A. TEMUAN

## A1. Dari screenshot (angka adalah perkiraan dari gambar)
| Halaman | Tabel mulai di sekitar | Yang memakan ruang di atas tabel |
| :-- | :-- | :-- |
| Log Absensi | ±63% dari atas layar, jadi tabel hanya mendapat ±37% tinggi layar | Judul 3 baris + deskripsi + 2 tombol Export bertumpuk, tab 2 tombol besar, kartu filter berisi 4 kontrol bertumpuk (cari, tanggal, kebun, afdeling) |
| Monitoring Absensi | ±40% | Judul, kartu dengan filter bulan, cari, kebun, afdeling |
| Daftar Karyawan | ±70% | Judul, 3 baris tombol aksi (Riwayat Mutasi, Tambah, Sync/Excel/PDF/Import), 4 filter bertumpuk |
| Mutasi Karyawan | ±55% | Judul + tombol, 3 filter bertumpuk, 3 tombol aksi |

Selain itu:
- Tabel dibungkus berlapis: kartu filter, kartu tabel, lalu `.table-container` dengan border sendiri. Lebar efektif tabel sekitar 340 dari 440px.
- Hanya sekitar 3–4 kolom yang terlihat. Kolom NAMA terpotong ("KARYAWAI", "WARUV").
- **Daftar Karyawan** punya dua filter berlabel sama, "Afdeling (Semua)". Kemungkinan salah satunya seharusnya "Kebun". Perlu dicek, bukan diperbaiki diam-diam.
- Screenshot terakhir (`/absensi`, 440×956) diambil pada zoom 50% sehingga terlihat sangat kecil. Nilai tampilan harus dinilai pada zoom 100%.

## A2. Dari `index.css`
| # | Temuan | Dampak |
| :-- | :-- | :-- |
| 1 | `.table-container table { min-width: 850px }` (definisi kedua, mengalahkan yang 650px) dan `th, td { white-space: nowrap }`. | Tabel dipaksa selebar 850px di HP, sehingga hanya sebagian kolom terlihat. |
| 2 | `.table-container { overflow-x: auto }` tanpa batas tinggi. | Karena `overflow-x:auto` membuat kontainer menjadi konteks scroll, `position: sticky` pada `thead` (aturan `.enterprise-table thead th`) tidak menempel saat halaman di-scroll. Header tabel ikut hilang ke atas. |
| 3 | Padding berlapis: `.dashboard-content-outlet` 1rem, `.page-layout` .75rem, `.toolbar-card` .85rem 1rem, tabel di dalam kartu. | Tepi kiri-kanan terbuang. |
| 4 | `.compact-mobile-table` (≤768px) sudah ada, tetapi memakai font 0,65rem (±10px). | Terlalu kecil. Batas keterbacaan yang saya usulkan: 12px untuk isi sel. |
| 5 | `.app-topbar` tinggi tetap 52px (`!important`), `[data-slot="sidebar-wrapper"] { min-height:100vh }`, `body` flex kolom. | Tinggi halaman tidak terbatas. Halaman data harus diberi tinggi eksplisit agar tabel bisa mengisi sisa layar. |
| 6 | Ada kelas `.pull-to-refresh-indicator`. | Bila pull-to-refresh bergantung pada scroll halaman, tabel yang menjadi area scroll sendiri bisa mengganggunya. Harus diperiksa. |
| 7 | CSS sudah memakai `:has()` dan `100dvh`. | Aman dipakai di target Anda (WebView modern). |

## A3. Yang belum bisa saya pastikan
- Isi keempat halaman, layout dashboard (outlet, topbar), komponen pull-to-refresh, dan komponen `ui/sheet`.
- Apakah tabel punya pagination, jumlah baris tipikal, dan kolom apa saja yang ada di tiap halaman.
- Cara ekspor Excel/PDF bekerja (tidak boleh diubah).

Karena itu prompt di Bagian D dimulai dengan **Fase 0 (baca saja)**.

---

# BAGIAN B. KEPUTUSAN DESAIN DAN ASUMSI DEFAULT

Semua asumsi ini bisa Anda ubah sebelum prompt dijalankan.

| # | Keputusan | Default |
| :-- | :-- | :-- |
| 1 | Cakupan | Hanya layar ≤ 768px. Tablet dan desktop (>768px) tidak berubah. |
| 2 | Konsep layout | "Halaman penuh": tinggi halaman = tinggi layar dikurangi topbar. Header ringkas di atas, **tabel mengisi sisa layar** dan menjadi satu-satunya area scroll (vertikal + horizontal). |
| 3 | Filter | Satu baris: kotak cari + tombol **Filter** (dengan lencana jumlah filter aktif). Kontrol kebun/afdeling/tanggal/status pindah ke **bottom sheet**. Filter aktif tampil sebagai chip satu baris yang bisa digeser. Filter tetap berlaku seketika seperti sekarang, tanpa tombol "Terapkan". |
| 4 | Tombol aksi (Export, Sync, Import, dst.) | Dipadatkan jadi tombol ikon 40×40 dengan `aria-label` dan tooltip. Tombol utama ("Tambah ...") tetap berteks. Logikanya tidak diubah. |
| 5 | Judul | Satu baris. Eyebrow dan deskripsi disembunyikan di mobile. |
| 6 | Tab (mis. Riwayat Log / Persetujuan) | Segmented control satu baris, tinggi 36–40px. |
| 7 | Tabel | Tepi ke tepi (tanpa kartu/border/radius samping), header menempel (sticky), **1 kolom identitas menempel di kiri** (default: NAMA), kolom "NO." disembunyikan di mobile, padding sel 7×10px, font isi 12px, `min-width` 850px dihapus untuk tabel mobile. |
| 8 | Layar penuh khusus tabel | Opsional (Fase 5): tombol "Layar Penuh" pada tabel yang membuka tampilan tabel saja, mendukung landscape. Default: tidak dikerjakan sebelum Fase 1–4 disetujui. |
| 9 | Data dan logika | Tidak ada perubahan pada pengambilan data, filter, ekspor, offline, sinkronisasi, atau hak akses. |

Target ukur (pada viewport 390×844, portrait, tanpa scroll):
- Header tabel berada di ≤ 200px dari atas layar.
- Area tabel ≥ 65% tinggi layar.
- ≥ 12 baris data terlihat.
- Isi sel ≥ 12px.

---

# BAGIAN C. RENCANA

| Fase | Isi | Gerbang sebelum lanjut |
| :-- | :-- | :-- |
| **0. Discovery** | Baca semua file terkait, jawab pertanyaan, **tanpa mengubah file**. | Laporan ke Anda. Anda konfirmasi asumsi di Bagian B. |
| **1. Fondasi bersama** | CSS mobile baru (terbatas `@media (max-width:768px)`), komponen `MobileDataPage`, `MobileFilterSheet`, `MobileTableViewport`. | `npm run build` lulus. Desktop tidak berubah. |
| **2. Log Absensi** | Terapkan ke halaman Log + tab Persetujuan Edit/Hapus (halaman acuan). | Anda cek di HP/emulator. Kriteria ukur terpenuhi. |
| **3. Monitoring Absensi** | Terapkan pola yang sama. | Uji. |
| **4. Daftar Karyawan dan Mutasi Karyawan** | Terapkan pola yang sama. Laporkan filter "Afdeling" ganda. | Uji. |
| **5. Opsional** | Tombol "Layar Penuh" pada tabel (landscape) dan petunjuk geser horizontal. | Hanya bila diminta. |
| **6. QA** | Matriks uji, regresi desktop, laporan. | Semua kriteria terjawab (lulus/gagal/tidak bisa diuji). |

Cadangan mundur: satu flag `MOBILE_DATA_LAYOUT_ENABLED` (konstanta di satu file). Bila `false`, semua halaman kembali ke tampilan lama.

---

# BAGIAN D. PROMPT (SALIN DARI SINI)

Lampirkan: `index.css`, layout dashboard (outlet + topbar), keempat file halaman, komponen pull-to-refresh, `src/components/ui/sheet.*` (bila ada), `package.json`, screenshot keempat halaman.

```
PERAN
Kamu adalah senior frontend engineer (React 19, Vite 8, Tailwind v4, shadcn/ui, Capacitor 8).
Tugas: membuat tampilan MOBILE halaman data lebih "penuh" sehingga TABEL terlihat jauh lebih luas, tanpa mengubah logika data.

HALAMAN YANG DICAKUP
  1. Log Absensi (tab "Riwayat Log Absensi" dan tab "Persetujuan Edit/Hapus")
  2. Monitoring Absensi (Monitoring Perhari)
  3. Daftar Karyawan (Data Master Karyawan)
  4. Mutasi Karyawan (Riwayat Mutasi)
Hanya layar <= 768px. Layar > 768px TIDAK BOLEH berubah sama sekali (markup tetap, CSS baru hanya di dalam @media (max-width: 768px)).

ATURAN WAJIB
1. Kerjakan per FASE. Setelah Fase 0 BERHENTI dan laporkan. Jangan lanjut sebelum saya menjawab.
2. Jangan menebak. Jika sesuatu tidak ditemukan atau berbeda dari yang tertulis, laporkan dan tanyakan.
3. Jangan ubah logika: pengambilan data, filter, pencarian, pagination (bila ada), ekspor Excel/PDF, import CSV + foto, sinkronisasi,
   mode offline, hak akses per peran, handler tombol. Memindahkan kontrol ke tempat lain diperbolehkan; mengubah perilakunya tidak.
4. Jangan ubah aturan CSS lama (.page-layout, .toolbar-card, .table-card, .enterprise-table, .table-container, .compact-mobile-table, dst.).
   Tambahkan kelas BARU berawalan `mobile-` di akhir index.css, seluruhnya di dalam @media (max-width: 768px).
   Bila aturan lama harus dinetralkan di mobile, gunakan selektor lebih spesifik dari kelas baru (mis. `.mobile-table-viewport .table-container table`),
   bukan mengedit aturan lama.
5. Jangan menambah dependensi baru. Gunakan komponen yang sudah ada (shadcn Sheet bila ada, lucide-react untuk ikon).
6. Gunakan token tema yang ada (var(--bg-card), var(--border-color), dst.). Tidak boleh ada warna keras baru. Harus benar di tema terang dan gelap.
7. Jangan ubah header tabel menjadi lebih rendah dari keterbacaan: font isi sel minimal 12px (0.75rem), font header minimal 11px.
8. Jangan mengklaim "sudah diuji di perangkat" bila tidak bisa dijalankan. Tulis "tidak bisa diuji" beserta alasannya.
9. Verifikasi dengan `npm run build` dan `npm test`. Tidak ada script lint.
10. Ubah seminimal mungkin. Jangan merapikan kode di luar cakupan.

FAKTA DARI CSS SAAT INI (verifikasi di Fase 0)
- `.table-container table { min-width: 850px }` (definisi terakhir menang) dan `th, td { white-space: nowrap }`. Ini memaksa tabel lebar di HP.
- `.table-container { overflow-x: auto }` tanpa batas tinggi -> menjadi konteks scroll, sehingga `position: sticky` pada thead
  (`.enterprise-table thead th { position: sticky; top: 0; z-index: 5 !important; background: var(--bg-secondary) !important }`)
  tidak menempel saat halaman di-scroll.
- Padding berlapis: `.dashboard-content-outlet` (1.25rem 1.5rem; <=640px: 1rem), `.page-layout` (1.5rem; <=640px: .75rem),
  `.toolbar-card` (1.25rem 1.5rem; <=640px: .85rem 1rem).
- `.app-topbar` tinggi tetap 52px (!important). `[data-slot="sidebar-wrapper"] { min-height: 100vh }`. `body` = flex kolom, min-height 100vh.
- `.compact-mobile-table` (<=768px) memakai font .65rem, terlalu kecil.
- Ada kelas `.pull-to-refresh-indicator` (kemungkinan fitur tarik-untuk-refresh).
- CSS sudah memakai `100dvh` dan `:has()`.

====================================================================
FASE 0. DISCOVERY (BACA SAJA, JANGAN UBAH APA PUN)
====================================================================
Laporkan hasilnya dengan nama file dan nomor baris:
  grep -rnE "table-container|enterprise-table|compact-mobile-table|toolbar-card|table-card|page-layout" src --include=*.jsx --include=*.tsx
  grep -rniE "pull.?to.?refresh|touchstart|touchmove|PullToRefresh" src
  grep -rnE "Sheet|SheetContent|DialogContent|Popover|DayPicker|react-day-picker" src --include=*.jsx --include=*.tsx
  grep -rnE "dashboard-content-outlet|app-topbar|SidebarInset|Outlet" src --include=*.jsx --include=*.tsx

Jawab setiap poin (jangan dilewati):
 a. File komponen untuk tiap halaman: Log Absensi (+ tab Persetujuan), Monitoring Absensi, Daftar Karyawan, Mutasi Karyawan.
    Apakah ada komponen tabel bersama, atau tiap halaman punya markup tabel sendiri?
 b. Untuk tiap halaman: daftar kolom (urutan, nama, mana yang identitas: NIK/NAMA), apakah ada kolom aksi, apakah ada pagination,
    jumlah baris tipikal dan maksimum, dan apakah baris dirender semua sekaligus.
 c. Untuk tiap halaman: filter apa saja (nama, tipe kontrol, state-nya), dan apakah filter diterapkan seketika atau dengan tombol.
 d. Daftar Karyawan: ada dua filter berlabel "Afdeling (Semua)". Apa isi masing-masing? Apakah salah satunya seharusnya "Kebun"?
    LAPORKAN SAJA, JANGAN diubah.
 e. Bagaimana layout dashboard mengatur outlet, topbar, dan sidebar? Apakah tinggi 52px topbar konsisten di mobile?
 f. Bagaimana pull-to-refresh bekerja (event pada window/document atau pada elemen tertentu)? Apakah akan terganggu bila tabel menjadi area scroll sendiri?
 g. Apakah ada komponen shadcn Sheet (src/components/ui/sheet.*) yang bisa dipakai sebagai bottom sheet? Apa z-indexnya (CSS lama menimpa sheet ke 100/110)?
 h. Bagaimana tab Riwayat Log / Persetujuan diimplementasikan (state lokal atau route)?
 i. Cara tombol Export/Sync/Import dipicu, dan apakah ada ketergantungan pada layout (mis. ref ke tabel untuk cetak/PDF).
 j. Apakah tabel dipakai di tempat lain (dashboard, modal) dengan kelas yang sama sehingga perubahan CSS bisa berdampak?
 k. Perbedaan antara fakta di bagian "FAKTA DARI CSS SAAT INI" dan kode sebenarnya.
Setelah itu BERHENTI. Tulis laporan Fase 0 dan tunggu jawaban saya.

====================================================================
FASE 1. FONDASI BERSAMA
====================================================================
1.1 Flag dan helper
  - src/hooks/useIsMobileLayout.js (BARU): MOBILE_DATA_LAYOUT_ENABLED = true; hook mengembalikan true bila flag aktif dan
    matchMedia('(max-width: 768px)').matches (dengarkan perubahan, bersihkan saat unmount). Dipakai hanya untuk memilih markup mobile.
    Layout tidak boleh bergantung pada hook saja: CSS tetap dibatasi @media agar tidak ada kedipan.

1.2 Komponen (src/components/mobile/)
  MobileDataPage.jsx
    Props: title, titleExtra, actions (node, tombol ikon), tabs (node, opsional), toolbar (node), chips (node), children (viewport tabel).
    Render: <div className="mobile-data-page"> + header ringkas + toolbar + chips + children.
    Hanya dirender pada layar mobile; pada desktop halaman memakai markup lamanya (JANGAN ubah markup desktop).
  MobileFilterSheet.jsx
    Bottom sheet (shadcn Sheet side="bottom" bila ada; jika tidak ada, portal sederhana dengan z-index di atas topbar (20) dan sidebar (80),
    sebelum dialog (50000)). Props: open, onOpenChange, title, children, onReset, activeCount.
    Berisi kontrol filter yang SAMA dengan yang ada (state tetap di halaman). Tombol "Reset" dan "Selesai". Area sentuh min 44px.
    Fokus dikelola (trap), Esc dan tombol Back menutup sheet saja.
  MobileTableViewport.jsx
    Props: children (tabel), stickyFirstCol (default true), className.
    Render: <div className="mobile-table-viewport"> yang menjadi SATU-SATUNYA area scroll (vertikal + horizontal) halaman data.

1.3 CSS (akhir index.css, SELURUHNYA di dalam @media (max-width: 768px)) — acuan, sesuaikan dengan temuan Fase 0
  :root { --topbar-h: 52px; }   (jangan menimpa nilai bila sudah ada)
  .dashboard-content-outlet:has(.mobile-data-page) { padding: 0 !important; }
  .mobile-data-page {
    display: flex; flex-direction: column; min-height: 0;
    height: calc(100vh - var(--topbar-h));
    background: var(--bg-primary);
  }
  @supports (height: 100dvh) { .mobile-data-page { height: calc(100dvh - var(--topbar-h)); } }
  .mobile-data-header { flex: none; display: flex; flex-direction: column; gap: 8px; padding: 8px 12px 8px; }
  .mobile-data-titlerow { display: flex; align-items: center; justify-content: space-between; gap: 8px; min-height: 40px; }
  .mobile-data-title { font-size: 1.05rem; font-weight: 800; line-height: 1.2; margin: 0; color: var(--text-main); }
  .mobile-data-actions { display: flex; align-items: center; gap: 6px; }
  .mobile-icon-btn { width: 40px; height: 40px; display: inline-flex; align-items: center; justify-content: center;
    border: 1px solid var(--border-color); border-radius: 8px; background: var(--bg-card); color: var(--text-main); }
  .mobile-data-toolbar { display: flex; align-items: center; gap: 8px; }
  .mobile-data-search { flex: 1; min-width: 0; height: 40px; }
  .mobile-filter-btn { position: relative; height: 40px; min-width: 44px; }
  .mobile-filter-badge { position: absolute; top: -4px; right: -4px; min-width: 18px; height: 18px; border-radius: 9999px;
    font-size: 0.65rem; font-weight: 800; background: var(--accent-primary); color: #fff; display: flex; align-items: center; justify-content: center; }
  .mobile-chip-row { display: flex; gap: 6px; overflow-x: auto; scrollbar-width: none; }
  .mobile-chip-row::-webkit-scrollbar { display: none; }
  .mobile-tabs { display: flex; gap: 4px; padding: 3px; border: 1px solid var(--border-color); border-radius: 10px; background: var(--bg-card); }
  .mobile-tabs > * { flex: 1; min-height: 36px; font-size: 0.8rem; font-weight: 700; }
  .mobile-table-viewport {
    flex: 1 1 auto; min-height: 0; overflow: auto; -webkit-overflow-scrolling: touch;
    overscroll-behavior: contain; background: var(--bg-card);
    border-top: 1px solid var(--border-color);
    padding-bottom: env(safe-area-inset-bottom, 0px);
  }
  .mobile-table-viewport .table-container { margin: 0; border: 0; border-radius: 0; overflow: visible; background: transparent; }
  .mobile-table-viewport table { width: max-content; min-width: 100% !important; border-collapse: separate; border-spacing: 0; font-size: 0.75rem; }
  .mobile-table-viewport th, .mobile-table-viewport td { padding: 7px 10px; white-space: nowrap; border-bottom: 1px solid var(--border-color); background: var(--bg-card); }
  .mobile-table-viewport thead th { position: sticky; top: 0; z-index: 6; font-size: 0.68rem; background: var(--bg-secondary) !important; }
  .mobile-table-viewport .col-sticky { position: sticky; left: 0; z-index: 4; box-shadow: 1px 0 0 var(--border-color); }
  .mobile-table-viewport thead th.col-sticky { z-index: 7; }
  .mobile-table-viewport .col-hide-mobile { display: none; }
  Catatan: sel yang menempel harus punya latar belakang OPAK (var(--bg-card)), bukan transparan, agar teks di belakangnya tidak tembus.
  Jangan beri tinggi ke .mobile-table-viewport selain lewat flex (halaman sudah punya tinggi eksplisit).

1.4 Gerbang Fase 1: `npm run build` lulus. Jelaskan bukti bahwa desktop tidak berubah (semua CSS baru berada dalam @media (max-width: 768px);
    markup desktop tidak disentuh).

====================================================================
FASE 2. LOG ABSENSI (HALAMAN ACUAN)
====================================================================
 1. Judul satu baris. Eyebrow dan deskripsi tidak ditampilkan di mobile.
 2. Tombol Export Excel dan Export PDF menjadi tombol ikon (mobile-icon-btn), `aria-label` dan `title` tetap. Handler tidak diubah.
 3. Tab "Riwayat Log Absensi" / "Persetujuan Edit/Hapus" menjadi `mobile-tabs` satu baris. State/route tab tidak diubah.
 4. Toolbar: kotak cari (state yang sama) + tombol Filter (lencana = jumlah filter aktif: tanggal, kebun, afdeling).
    Kontrol tanggal, kebun, afdeling dipindah ke MobileFilterSheet. State dan efeknya tetap seperti sekarang.
    Chip filter aktif (satu baris geser): "Tanggal: ...", "Kebun: ...", "Afdeling: ..." dengan tombol hapus per chip (memanggil setter yang sudah ada).
 5. Tabel dibungkus MobileTableViewport. Kolom NO. diberi `col-hide-mobile` (default; bisa diubah setelah Fase 0). Satu kolom identitas
    (default NAMA KARYAWAN) diberi `col-sticky`. Hapus pengaruh `min-width: 850px` hanya untuk mobile.
 6. Tab Persetujuan Edit/Hapus memakai pola yang sama (daftar atau tabel, sesuai isi aslinya).
 7. Pull-to-refresh (bila ada): harus tetap berfungsi. Bila bergantung pada scroll window, ubah pemicunya agar aktif saat
    .mobile-table-viewport berada di scrollTop = 0, tanpa mengubah logika refresh. Bila tidak aman, laporkan dan jangan memaksakan.
 8. Hasil ukur pada 390x844: header tabel <= 200px dari atas; area tabel >= 65% tinggi layar; >= 12 baris terlihat.

Gerbang Fase 2: `npm run build` lulus; laporkan angka ukur; tunggu persetujuan saya.

====================================================================
FASE 3. MONITORING ABSENSI
====================================================================
Pola sama dengan Fase 2. Khusus: bulan (picker) dan filter kebun/afdeling masuk ke sheet; kotak cari NIK/Nama tetap terlihat.
Bila tabel berbentuk matriks harian, jadikan 2 kolom pertama (NIK dan NAMA) menempel (col-sticky dengan offset left yang sesuai lebar kolom pertama),
dan pastikan latar belakang opak. Jangan mengubah logika pewarnaan/perhitungan status.

====================================================================
FASE 4. DAFTAR KARYAWAN DAN MUTASI KARYAWAN
====================================================================
Pola sama.
 - Daftar Karyawan: tombol "Tambah Karyawan" tetap berteks (aksi utama). "Riwayat Mutasi", Sync, Excel, PDF, Import CSV + Foto menjadi tombol ikon
   atau masuk satu menu aksi ("..."), tanpa mengubah handler. Dua filter "Afdeling (Semua)": JANGAN diubah, laporkan temuan Fase 0 (d).
 - Mutasi Karyawan: tombol "Tambah Mutasi" tetap berteks; Excel, PDF, Refresh menjadi tombol ikon.
 - Jangan mengubah modal/dialog (Tambah/Edit/Re-Scan) atau scanner layar penuh yang sudah ada.

====================================================================
FASE 5. OPSIONAL (HANYA BILA DIMINTA)
====================================================================
 - Tombol "Layar Penuh" pada tabel: portal layar penuh berisi tabel + kotak cari + tombol tutup, mendukung landscape. Tutup lewat tombol atau Back
   (pola sama dengan FullscreenScanShell, z-index di atas topbar dan sidebar).
 - Bayangan tepi kanan sebagai petunjuk ada kolom lain di sebelah kanan.

====================================================================
FASE 6. QA
====================================================================
KRITERIA PENERIMAAN (laporkan LULUS / GAGAL / TIDAK BISA DIUJI + alasan)
  1. Desktop (>768px): tampilan identik dengan sebelum perubahan di keempat halaman.
  2. Pada 390x844 portrait, tanpa scroll: header tabel <= 200px dari atas; area tabel >= 65% tinggi layar; >= 12 baris terlihat.
  3. Pada 360x640, 412x915, 440x956: tabel tetap mengisi sisa layar tanpa elemen terpotong.
  4. Header tabel tetap terlihat saat baris di-scroll. Kolom identitas tetap terlihat saat tabel digeser ke kanan.
  5. Tidak ada scroll horizontal pada halaman (body); hanya area tabel yang bisa digeser.
  6. Isi sel >= 12px, header >= 11px. Kontras baik di tema terang dan gelap.
  7. Semua filter bekerja seperti sebelumnya (hasil dan jumlah baris sama untuk kombinasi filter yang sama). Chip dan lencana sesuai filter aktif.
     Menutup-membuka sheet tidak mereset filter.
  8. Export Excel/PDF, Sync, Import, Tambah, Edit, Hapus, Re-Scan: handler tidak berubah (tunjukkan dari diff).
  9. Pull-to-refresh (bila ada) tetap berfungsi, atau dilaporkan sebagai batasan yang diketahui.
 10. Sidebar (sheet) dan scanner layar penuh tidak tertutup atau menutup elemen baru; urutan z-index benar.
 11. Landscape (844x390): tabel masih dapat dipakai; header halaman tidak memakan lebih dari ±25% tinggi layar.
 12. Tab Persetujuan Edit/Hapus memakai pola yang sama dan berfungsi.
 13. `npm run build` dan `npm test` lulus. Tidak ada dependensi baru. Tidak ada error baru di konsol.
 14. Jika jumlah baris besar (Fase 0 b) membuat scroll berat di HP menengah-bawah, laporkan angkanya. Jangan menambah pustaka virtualisasi tanpa persetujuan.

MATRIKS UJI (tandai yang tidak bisa dijalankan)
  APK Android | Chrome Android | Safari iOS | Chrome DevTools (zoom 100%) x portrait/landscape x tema terang/gelap x 4 halaman

FORMAT LAPORAN
  1. Laporan Fase 0 (sebelum mengubah apa pun), lalu tunggu jawaban saya.
  2. Setiap akhir fase: file yang diubah/dibuat (satu baris tiap file), diff, angka ukur, dan hasil gerbang.
  3. Daftar hal yang tidak ditemukan atau butuh keputusan saya.
  4. Tabel kriteria penerimaan akhir.
  5. Daftar perubahan perilaku yang DISENGAJA.
```

---

# BAGIAN E. KODE YANG SAYA BUTUHKAN (BILA ANDA INGIN PATCH KONKRET DARI SAYA)
Prioritas dari yang paling berguna:
1. Halaman **Log Absensi** (komponen utama + tab Persetujuan). Ini halaman acuan.
2. **Layout dashboard** (komponen yang merender topbar, outlet, dan sidebar).
3. Komponen **pull-to-refresh** (bila ada).
4. `src/components/ui/sheet.*` (bila ada).
5. Halaman Monitoring, Daftar Karyawan, dan Mutasi (untuk fase berikutnya).

Dengan 1–4 saya bisa menulis `MobileDataPage`, `MobileFilterSheet`, `MobileTableViewport`, dan blok CSS final yang sudah disesuaikan, seperti perbaikan `FaceScanSurface` sebelumnya.

# BAGIAN F. CHECKLIST MANUAL ANDA SETELAH AI SELESAI
1. Chrome DevTools, zoom **100%**, 390×844: buka tiap halaman, tabel mengisi sebagian besar layar.
2. Geser tabel ke kanan: kolom identitas tetap terlihat. Scroll ke bawah: header tetap terlihat.
3. Buka Filter, pilih kebun dan afdeling, tutup: jumlah baris berubah sama seperti sebelumnya, chip muncul.
4. Putar ke landscape: tabel tetap nyaman.
5. Bandingkan desktop dengan sebelumnya: tidak ada perubahan.
6. Coba Export Excel dan PDF, Sync, dan tarik-untuk-refresh.
