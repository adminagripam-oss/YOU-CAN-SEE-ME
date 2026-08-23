@echo off
title Sistem Absensi Biometrik Wajah 1-to-1 Verification
echo ====================================================
echo 🚀 Starting Biometric Face Verification Server...
echo 🌐 Opening Local Host: http://localhost:8081
echo ====================================================

:: Launch default browser automatically
start http://localhost:8081

:: Run Node.js Express Server
node server.js
pause
