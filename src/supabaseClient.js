import { createClient } from '@supabase/supabase-js';

// KEAMANAN: Kunci Supabase WAJIB didefinisikan di file .env (VITE_SUPABASE_URL, VITE_SUPABASE_ANON_KEY).
// Jangan pernah hardcode kunci API di sini — bundle JS bisa diakses siapapun.
const SUPABASE_URL = import.meta.env.VITE_SUPABASE_URL;
const SUPABASE_ANON_KEY = import.meta.env.VITE_SUPABASE_ANON_KEY;

if (!SUPABASE_URL || !SUPABASE_ANON_KEY) {
  console.error(
    '[Supabase Client] KRITIS: VITE_SUPABASE_URL atau VITE_SUPABASE_ANON_KEY tidak ditemukan di .env! ' +
    'Pastikan file .env sudah ada di root proyek dengan kedua variabel tersebut.'
  );
}

/**
 * Direct Supabase Cloud Database Client for Frontend React SPA
 * Enables live data fetching on GitHub Pages & mobile devices anywhere without Mixed Content block
 */
export const supabase = createClient(SUPABASE_URL || '', SUPABASE_ANON_KEY || '');

