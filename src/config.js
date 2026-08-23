/**
 * API Base URL Configuration
 *
 * Di Hostinger (agriface.agri-pam.id): Express server melayani React build
 * sekaligus sebagai API — satu proses, satu domain → gunakan relative path ''.
 *
 * Di localhost dev: Vite proxy meneruskan /api → Express port 8080.
 */

const hostname = typeof window !== 'undefined' ? window.location.hostname : '';

export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || (() => {
  // Hostinger / production domain → relative path (Express serve sekaligus)
  if (hostname.includes('agri-pam.id') || hostname.includes('agriface')) return '';
  // Vercel (deploy preview) → relative path
  if (hostname.includes('vercel.app')) return '';
  // GitHub Pages / file protocol → arahkan ke server lokal
  if (hostname.includes('github.io') || window.location.protocol === 'file:') return 'http://localhost:8081';
  // Development lokal (localhost) → relative path karena Vite proxy sudah handle
  return '';
})();


/**
 * Custom fetch wrapper with a configurable timeout.
 * Prevents mobile apps on different networks from freezing during TCP handshake timeouts.
 */
export async function fetchWithTimeout(resource, options = {}) {
  const { timeout = 3000 } = options; // Default timeout 3 detik
  
  const controller = new AbortController();
  const id = setTimeout(() => controller.abort(), timeout);
  
  try {
    const response = await fetch(resource, {
      ...options,
      signal: controller.signal
    });
    clearTimeout(id);
    return response;
  } catch (error) {
    clearTimeout(id);
    throw error;
  }
}


