-- SQL Auto-Generated Inserts for AgriFace Multi-Role Accounts
-- Total Accounts: 32

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.hq',
  crypt('tanamanhead', gen_salt('bf')),
  'Head Office Nasional Admin',
  'headoffice_admin',
  NULL,
  NULL
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.sumut2',
  crypt('tanamansumut2', gen_salt('bf')),
  'Regional Sumut 2 Admin',
  'regional_admin',
  'Sumut 2',
  NULL
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.riau1',
  crypt('tanamanriau1', gen_salt('bf')),
  'Regional Riau 1 Admin',
  'regional_admin',
  'Riau 1',
  NULL
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.kalbar1a',
  crypt('tanamankalbar1a', gen_salt('bf')),
  'Regional Kalbar 1A Admin',
  'regional_admin',
  'Kalbar 1A',
  NULL
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.kalbar1b',
  crypt('tanamankalbar1b', gen_salt('bf')),
  'Regional Kalbar 1B Admin',
  'regional_admin',
  'Kalbar 1B',
  NULL
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.bukitharapani',
  crypt('tanamanbukitharapani', gen_salt('bf')),
  'Kebun Bukit Harapan I Admin',
  'estate_admin',
  'Sumut 2',
  'Bukit Harapan I'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.bukitharapanii',
  crypt('tanamanbukitharapanii', gen_salt('bf')),
  'Kebun Bukit Harapan II Admin',
  'estate_admin',
  'Sumut 2',
  'Bukit Harapan II'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.parsub',
  crypt('tanamanparsub', gen_salt('bf')),
  'Kebun Parsub Admin',
  'estate_admin',
  'Sumut 2',
  'Parsub'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.patogujanji',
  crypt('tanamanpatogujanji', gen_salt('bf')),
  'Kebun Patogu Janji Admin',
  'estate_admin',
  'Sumut 2',
  'Patogu Janji'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.pancaagrolestaripal',
  crypt('tanamanpancaagrolestaripal', gen_salt('bf')),
  'Kebun Panca Agro Lestari (Pal) Admin',
  'estate_admin',
  'Riau 1',
  'Panca Agro Lestari (Pal)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.wanajinggatimurwjt',
  crypt('tanamanwanajinggatimurwjt', gen_salt('bf')),
  'Kebun Wana Jingga Timur (Wjt) Admin',
  'estate_admin',
  'Riau 1',
  'Wana Jingga Timur (Wjt)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.dutapalmanusantaradpni',
  crypt('tanamandutapalmanusantaradpni', gen_salt('bf')),
  'Kebun Duta Palma Nusantara (Dpn) I Admin',
  'estate_admin',
  'Riau 1',
  'Duta Palma Nusantara (Dpn) I'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.dutapalmanusantaradpnii',
  crypt('tanamandutapalmanusantaradpnii', gen_salt('bf')),
  'Kebun Duta Palma Nusantara (Dpn) II Admin',
  'estate_admin',
  'Riau 1',
  'Duta Palma Nusantara (Dpn) II'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.dutapalmanusantaradpniii',
  crypt('tanamandutapalmanusantaradpniii', gen_salt('bf')),
  'Kebun Duta Palma Nusantara (Dpn) III Admin',
  'estate_admin',
  'Riau 1',
  'Duta Palma Nusantara (Dpn) III'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.eluanmahkotaemakt',
  crypt('tanamaneluanmahkotaemakt', gen_salt('bf')),
  'Kebun Eluan Mahkota (EMA) - KT Admin',
  'estate_admin',
  'Riau 1',
  'Eluan Mahkota (EMA) - KT'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.johansentosa',
  crypt('tanamanjohansentosa', gen_salt('bf')),
  'Kebun Johan Sentosa Admin',
  'estate_admin',
  'Riau 1',
  'Johan Sentosa'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.palmaintilestaripil',
  crypt('tanamanpalmaintilestaripil', gen_salt('bf')),
  'Kebun Palma Inti Lestari (PIL) Admin',
  'estate_admin',
  'Riau 1',
  'Palma Inti Lestari (PIL)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.bukitjagoindahbji',
  crypt('tanamanbukitjagoindahbji', gen_salt('bf')),
  'Kebun Bukit Jago Indah (BJI) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Bukit Jago Indah (BJI)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.kaliaumasperkasaakmpa',
  crypt('tanamankaliaumasperkasaakmpa', gen_salt('bf')),
  'Kebun Kaliau Mas Perkasa A (KMP A) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Kaliau Mas Perkasa A (KMP A)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.kaliaumasperkasabkmpb',
  crypt('tanamankaliaumasperkasabkmpb', gen_salt('bf')),
  'Kebun Kaliau Mas Perkasa B (KMP B) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Kaliau Mas Perkasa B (KMP B)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.telukkeramattkr',
  crypt('tanamantelukkeramattkr', gen_salt('bf')),
  'Kebun Teluk Keramat (TKR) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Teluk Keramat (TKR)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.wanahijausemestaiwhsi',
  crypt('tanamanwanahijausemestaiwhsi', gen_salt('bf')),
  'Kebun Wana Hijau Semesta I (WHS I) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Wana Hijau Semesta I (WHS I)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.wanahijausemestaiil1',
  crypt('tanamanwanahijausemestaiil1', gen_salt('bf')),
  'Kebun Wana Hijau Semesta II (L1) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Wana Hijau Semesta II (L1)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.wanahijausemestaiil2',
  crypt('tanamanwanahijausemestaiil2', gen_salt('bf')),
  'Kebun Wana Hijau Semesta II (L2) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Wana Hijau Semesta II (L2)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.wanahijausemestaiiwhsii',
  crypt('tanamanwanahijausemestaiiwhsii', gen_salt('bf')),
  'Kebun Wana Hijau Semesta II (WHS II) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Wana Hijau Semesta II (WHS II)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.wanahijausemestaiiiwhsiii',
  crypt('tanamanwanahijausemestaiiiwhsiii', gen_salt('bf')),
  'Kebun Wana Hijau Semesta III (WHS III) Admin',
  'estate_admin',
  'Kalbar 1A',
  'Wana Hijau Semesta III (WHS III)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.wanahijausemestaiv',
  crypt('tanamanwanahijausemestaiv', gen_salt('bf')),
  'Kebun Wana Hijau Semesta IV Admin',
  'estate_admin',
  'Kalbar 1A',
  'Wana Hijau Semesta IV'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.mitrawawasanmws',
  crypt('tanamanmitrawawasanmws', gen_salt('bf')),
  'Kebun Mitra Wawasan (MWS) Admin',
  'estate_admin',
  'Kalbar 1B',
  'Mitra Wawasan (MWS)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.persadaalampa',
  crypt('tanamanpersadaalampa', gen_salt('bf')),
  'Kebun Persada Alam (PA) Admin',
  'estate_admin',
  'Kalbar 1B',
  'Persada Alam (PA)'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.darmexi',
  crypt('tanamandarmexi', gen_salt('bf')),
  'Kebun Darmex - I Admin',
  'estate_admin',
  'Kalbar 1B',
  'Darmex - I'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.darmexii',
  crypt('tanamandarmexii', gen_salt('bf')),
  'Kebun Darmex - II Admin',
  'estate_admin',
  'Kalbar 1B',
  'Darmex - II'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

INSERT INTO admin_auth (username, password_hash, name, role, region, kebun)
VALUES (
  'admin.darmexx',
  crypt('tanamandarmexx', gen_salt('bf')),
  'Kebun Darmex - X Admin',
  'estate_admin',
  'Kalbar 1B',
  'Darmex - X'
)
ON CONFLICT (username) DO UPDATE SET
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  region = EXCLUDED.region,
  kebun = EXCLUDED.kebun;

