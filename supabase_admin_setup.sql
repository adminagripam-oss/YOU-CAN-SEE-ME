-- 1. Mengaktifkan ekstensi pgcrypto untuk hashing password
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- 2. Membuat tabel admins jika belum ada
CREATE TABLE IF NOT EXISTS public.admins (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    username text UNIQUE NOT NULL,
    password_hash text NOT NULL,
    created_at timestamptz DEFAULT now()
);

-- 3. Memasukkan akun admin default
-- Username: admin
-- Password: tanaman
INSERT INTO public.admins (username, password_hash)
VALUES ('admin', crypt('tanaman', gen_salt('bf')))
ON CONFLICT (username) DO NOTHING;

-- 4. Membuat fungsi RPC (Remote Procedure Call) untuk validasi login
CREATE OR REPLACE FUNCTION verify_admin_login(p_username text, p_password text)
RETURNS boolean
LANGUAGE plpgsql
SECURITY DEFINER
AS $$
DECLARE
    v_hash text;
BEGIN
    -- Mengambil hash berdasarkan username
    SELECT password_hash INTO v_hash
    FROM public.admins
    WHERE username = p_username;
    
    -- Jika username tidak ditemukan, kembalikan false
    IF v_hash IS NULL THEN
        RETURN false;
    END IF;
    
    -- Mencocokkan password input dengan hash
    RETURN v_hash = crypt(p_password, v_hash);
END;
$$;
