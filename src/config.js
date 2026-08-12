/**
 * API Base URL Configuration
 * Automatically resolves to 'http://localhost:8080' when running on GitHub Pages
 * or file:// protocol, allowing the static GitHub Pages UI to talk to the backend server.
 */
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || (
  typeof window !== 'undefined'
    ? (window.location.hostname.includes('github.io') || window.location.protocol === 'file:'
        ? 'http://localhost:8080'
        : `${window.location.protocol}//${window.location.hostname}:8080`)
    : 'http://localhost:8080'
);

