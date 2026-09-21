---
name: upscale-gif-lanczos
description: Gunakan jika pengguna meminta memperbaiki kualitas, resolusi, atau upscale dari sebuah file GIF. Skill ini menggunakan script Python PIL dengan algoritma Lanczos dan mendukung output ke format WebP untuk transparansi halus (anti-aliasing).
---

# Upscale GIF/WebP Menggunakan Lanczos

Skill ini memungkinkan agen AI untuk memperbesar (upscale) resolusi file animasi GIF/WebP menggunakan metode resampling berkualitas tinggi (Lanczos) dari perpustakaan Python Pillow (PIL).

## Kapan Menggunakan Skill Ini
1. Pengguna memiliki file GIF resolusi rendah dan mengeluh bahwa file tersebut terlihat pecah atau blur.
2. Pengguna meminta untuk "upscale", "perjelas", atau "perhalus" file animasi.
3. Pengguna mengeluhkan tepian GIF transparan yang bergerigi (jagged edges) dan membutuhkan format WebP.

## Cara Menggunakan
Eksekusi script Python yang tersedia di dalam folder `scripts` pada skill ini.

Perintah CLI:
```bash
python .agents/skills/upscale_gif_lanczos/scripts/upscale.py <input_file> <output_file> [scale_factor]
```

Catatan Penting:
1. Pastikan lingkungan Python memiliki modul `Pillow` yang terinstal (`pip install Pillow`).
2. Jika input adalah GIF transparan, **SANGAT DISARANKAN** untuk mengarahkan `output_file` dengan ekstensi `.webp`. Format WebP mendukung 8-bit alpha transparency yang membuat tepian gambar hasil upscale menjadi 100% mulus (anti-aliased), tidak seperti GIF yang akan menghasilkan garis tepi bergerigi (jagged).
3. `scale_factor` adalah opsi tambahan berupa float (misal `2.0` untuk memperbesar 2x lipat). Default-nya adalah `2.0`.
