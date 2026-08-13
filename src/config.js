/**
 * API Base URL Configuration
 * Automatically resolves to 'http://localhost:8080' when running on GitHub Pages
 * or file:// protocol, allowing the static GitHub Pages UI to talk to the backend server.
 */
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || (
  typeof window !== 'undefined'
    ? (window.location.hostname.includes('github.io') || window.location.hostname.includes('vercel.app')
        ? '' // Kosongkan agar request menjadi relative path (/api/...) yang akan cepat mengembalikan 404/fallback di Vercel/GitHub Pages
        : (window.location.protocol === 'file:' 
            ? 'http://localhost:8080' 
            : `${window.location.protocol}//${window.location.hostname}:8080`))
    : 'http://localhost:8080'
);

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


