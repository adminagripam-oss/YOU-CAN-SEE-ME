import { createClient } from '@supabase/supabase-js';

const SUPABASE_URL = 'https://qrtvawixmlekbitvfuav.supabase.co';
const SUPABASE_ANON_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFydHZhd2l4bWxla2JpdHZmdWF2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODUzNTczNzAsImV4cCI6MjEwMDkzMzM3MH0.VmVPs_YQRPmK073_G7HTCFkH0MT__VVJWPO7OuS2JKU';

/**
 * Direct Supabase Cloud Database Client for Frontend React SPA
 * Enables live data fetching on GitHub Pages & mobile devices anywhere without Mixed Content block
 */
export const supabase = createClient(SUPABASE_URL, SUPABASE_ANON_KEY);
