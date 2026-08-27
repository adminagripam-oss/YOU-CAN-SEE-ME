-- Database Schema Upgrade: Add NIK Column to admin_auth & update verify_admin_login RPC

ALTER TABLE admin_auth ADD COLUMN IF NOT EXISTS nik VARCHAR(50);

DROP FUNCTION IF EXISTS verify_admin_login;

CREATE OR REPLACE FUNCTION verify_admin_login(p_username TEXT, p_password TEXT)
RETURNS TABLE (
    is_valid BOOLEAN,
    u_role VARCHAR,
    u_region VARCHAR,
    u_kebun VARCHAR,
    u_name VARCHAR,
    u_nik VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        (crypt(p_password, password_hash) = password_hash) AS is_valid,
        role,
        region,
        kebun,
        name,
        nik
    FROM admin_auth
    WHERE username = p_username AND is_active = true;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

GRANT EXECUTE ON FUNCTION verify_admin_login(TEXT, TEXT) TO anon;
GRANT EXECUTE ON FUNCTION verify_admin_login(TEXT, TEXT) TO authenticated;
