@echo off
title AGRIPAM - Sistem Absensi Biometrik (Development Mode)
echo =====================================================================
echo 🚀 Memulai Backend API Server (Port 8080) dan Frontend Vite (Port 5173)...
echo 🌐 Aplikasi akan terbuka otomatis di http://localhost:5173
echo =====================================================================

:: Jalankan Express Backend Server di window command prompt terpisah
start cmd /k "title AGRIPAM Backend Server && echo Memulai Backend API... && node server.js"

:: Jalankan Vite Frontend Dev Server di window command prompt terpisah
start cmd /k "title AGRIPAM Frontend Vite && echo Memulai Frontend Vite... && cmd /c npm run dev"

echo Menunggu server siap...
timeout /t 3

:: Buka browser di URL Vite dev server (yang memiliki HMR dan Proxy ke API Backend)
start http://localhost:5173

echo =====================================================================
echo 🌟 Server berjalan! Tekan Ctrl+C di masing-masing jendela untuk mematikan.
echo =====================================================================
