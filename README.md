# Sistem Absensi Biometrik Wajah 1-to-1 Verification

A modern React & Node.js application for face verification attendance powered by Supabase and `@vladmandic/face-api`.

---

## 🚀 Quick Start (Running on Localhost)

### 1. Direct Run (Express Server + Frontend)
If you just want to run the full application on your localhost:

```bash
# Start the Node.js Express server
npm start
```
Or double-click `start.bat` in Windows.

Open your browser and navigate to:
👉 **[http://localhost:8080](http://localhost:8080)**

---

### 2. Development Mode (Hot Reloading / Vite)
For active development with Vite HMR:

1. **Start Backend API Server** (Terminal 1):
   ```bash
   npm run server
   ```
2. **Start Vite Dev Server** (Terminal 2):
   ```bash
   npm run dev
   ```

Open your browser and navigate to:
👉 **[http://localhost:5173](http://localhost:5173)** *(API calls `/api` are automatically proxied to port `8080`)*.

---

### 3. Rebuilding Production Frontend
If you make changes to files under `src/`, rebuild the static distribution assets before running in production mode:

```bash
npm run build
npm start
```

---

## ⚙️ Environment Configuration (`.env`)
Ensure your `.env` file contains:
```env
PORT=8080
SUPABASE_URL=https://<your-supabase-project>.supabase.co
SUPABASE_ANON_KEY=<your-supabase-anon-key>
```
