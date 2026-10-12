# Laporan Perbaikan Bug & Upgrade Dashboard Executive (v3.1.3)

## 1. Akar Masalah (Root Cause) Bug Animasi (Bagian A)
Bug yang menyebabkan animasi tidak pernah berhenti dan grafik *Donut* tidak me-render *ring* utamanya bersumber dari `useCountUp` hook.
- **Penyebab Infinite Re-render**: Hook `useCountUp` menyertakan `value` ke dalam *dependency array* dari `useEffect`. Akibatnya, setiap kali *frame* animasi di-*update* (mengubah `value`), `useEffect` tertrigger ulang, membatalkan dan menginisialisasi ulang *loop* `requestAnimationFrame`. Hal ini menyebabkan komponen induk (`KPICard`, `AttendanceDonutChart`) terus-menerus melakukan re-render sebanyak 60 kali per detik.
- **Efek pada Donut Chart**: Karena `AttendanceDonutChart` terus dirender ulang, komponen `PieChart` dari Recharts terus-menerus memicu animasi *entrance* dari titik 0, yang membuat cincin (ring) donat selalu berada pada posisi "invisible" atau awal animasi, sehingga tidak terlihat.

**Solusi**:
1. Merubah ketergantungan `useCountUp` agar tidak memiliki loop pada array-nya.
2. Memisahkan *state* animasi dari induk komponen dengan membuat komponen terisolasi `<AnimatedNumber />`. Komponen ini bertugas menyimpan nilai transisi secara internal. Dengan demikian, ketika nilai sedang dianimasikan dari 0 ke target, komponen induk (`KPICard`, SVG `<text>` pada *Donut*) tidak perlu dirender ulang sama sekali.

## 2. Definisi Persentase (Bagian A.3 & A.5)
Sebelumnya, ketika melakukan filter lebih dari 1 hari, chart donat menjumlahkan seluruh Kehadiran (HK), namun persentase dihitung dengan membaginya menggunakan `totalEmployees` (jumlah fisik orang unik).
- **Perbaikan**: Persentase *TK Hadir* kini dibagi berdasarkan `totalAllCategories` (total jumlah seluruh record Hadir + Izin + Sakit + Mangkir untuk rentang waktu tersebut). Ini menjamin bahwa persentase logis (total persentase selalu ≤ 100%), sekalipun dalam rentang 1 minggu.

## 3. ComboBarLineChart (Bagian B)
Grafik *Mingguan* dan *Bulanan* (Ringkasan HK Per Kebun) kini menggunakan komponen baru yang dapat dipakai ulang (`ComboBarLineChart`).
- **Definisi Garis Target/Potensi HK**: Diambil dari formulasi: `Total Karyawan × Jumlah Hari pada Periode (Minggu/Bulan)`.
  - W1-W4 = 7 hari × Total TK.
  - W5 = sisa hari × Total TK.
  - Bulanan = jumlah hari pada bulan tersebut × Total TK.
- **Fitur**: Menggabungkan batang untuk *Realisasi HK*, Garis melengkung halus (*monotone*) warna oranye untuk *Tren*, dan garis titik-titik warna merah untuk *Target/Potensi HK*. Masing-masing metrik bisa ditutup sementara dengan *interactive legend*.

## 4. Rilis OTA
Versi aplikasi telah diperbarui ke `3.1.3` pada `package.json` dan `version.json`. Build terkompresi ZIP (`dist-3.1.3.zip`) berhasil diekspor menggunakan standar perintah asli OS Windows (`tar -a -c -f`) tanpa overwriting.
