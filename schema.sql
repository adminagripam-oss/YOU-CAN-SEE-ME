-- Database Schema: Sistem Absensi Biometrik Wajah (1-to-1 Verification)

-- 1. Table Karyawan (Employees)
CREATE TABLE IF NOT EXISTS employees (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nik VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Table Biometrik Master (Master Descriptors 128 Float32)
CREATE TABLE IF NOT EXISTS master_descriptors (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    employee_id INTEGER UNIQUE NOT NULL,
    descriptor_json TEXT NOT NULL, -- Stored as JSON Stringified Float32 Array [0.123, -0.456, ...]
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- 3. Table Log Absensi (Attendance Logs)
CREATE TABLE IF NOT EXISTS attendance_logs (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    employee_id INTEGER NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    location VARCHAR(255) DEFAULT 'Kantor Pusat',
    status VARCHAR(50) NOT NULL, -- 'VERIFIKASI BERHASIL' / 'VERIFIKASI GAGAL'
    euclidean_distance REAL NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- Sample Data (Karyawan Awal)
INSERT OR IGNORE INTO employees (nik, name, department) VALUES 
('EMP-001', 'Budi Santoso', 'IT & Software Development'),
('EMP-002', 'Siti Rahma', 'Human Resources'),
('EMP-003', 'Andi Wijaya', 'Finance & Accounting');
