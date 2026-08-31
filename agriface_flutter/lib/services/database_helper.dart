import 'dart:convert';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class DatabaseHelper {
  static final DatabaseHelper instance = DatabaseHelper._privateConstructor();
  static Database? _database;

  DatabaseHelper._privateConstructor();

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDatabase();
    return _database!;
  }

  Future<Database> _initDatabase() async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, 'agriface.db');

    return await openDatabase(
      path,
      version: 1,
      onCreate: _onCreate,
      onUpgrade: _onUpgrade,
    );
  }

  Future<void> _onCreate(Database db, int version) async {
    // 1. local_admins
    await db.execute('''
      CREATE TABLE local_admins (
        username TEXT PRIMARY KEY,
        password_hash TEXT NOT NULL,
        role TEXT,
        region TEXT,
        kebun TEXT,
        name TEXT,
        nik TEXT,
        last_login TEXT
      )
    ''');

    // 2. local_employees
    await db.execute('''
      CREATE TABLE local_employees (
        id INTEGER PRIMARY KEY,
        nik TEXT,
        name TEXT,
        department TEXT,
        afdeling TEXT,
        nama_kebun TEXT,
        status_tk TEXT,
        jabatan TEXT,
        status_perkawinan TEXT,
        has_master_biometric INTEGER DEFAULT 0
      )
    ''');

    // 3. local_master_descriptors
    await db.execute('''
      CREATE TABLE local_master_descriptors (
        employee_id INTEGER PRIMARY KEY,
        descriptor_json TEXT, -- 1024-float array
        geometric_descriptor_json TEXT,
        updated_at TEXT
      )
    ''');

    // 4. local_attendance_queue (sync queue)
    await db.execute('''
      CREATE TABLE local_attendance_queue (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        employee_id INTEGER NOT NULL,
        nik TEXT,
        name TEXT,
        department TEXT,
        afdeling TEXT,
        timestamp TEXT,
        location TEXT,
        lat REAL,
        lng REAL,
        status TEXT,
        attendance_type TEXT,
        euclidean_distance REAL,
        is_synced INTEGER DEFAULT 0,
        created_at TEXT
      )
    ''');

    // 5. local_attendance_logs (permanent history)
    await db.execute('''
      CREATE TABLE local_attendance_logs (
        id TEXT PRIMARY KEY,
        employee_id INTEGER NOT NULL,
        nik TEXT,
        name TEXT,
        department TEXT,
        afdeling TEXT,
        timestamp TEXT,
        location TEXT,
        lat REAL,
        lng REAL,
        status TEXT,
        attendance_type TEXT,
        euclidean_distance REAL,
        is_synced INTEGER DEFAULT 0,
        created_at TEXT
      )
    ''');
  }

  Future<void> _onUpgrade(Database db, int oldVersion, int newVersion) async {
    // Implement migrations if version increments in the future
  }

  // ═══════════════════════════════════════════════════════════════════════════
  // ADMIN AUTH LAYER (local_admins)
  // ═══════════════════════════════════════════════════════════════════════════

  Future<void> saveAdmin(Map<String, dynamic> admin) async {
    final db = await database;
    await db.insert(
      'local_admins',
      {
        'username': admin['username'],
        'password_hash': admin['password_hash'],
        'role': admin['role'],
        'region': admin['region'],
        'kebun': admin['kebun'],
        'name': admin['name'],
        'nik': admin['nik'],
        'last_login': admin['last_login'] ?? DateTime.now().toIso8601String(),
      },
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
  }

  Future<Map<String, dynamic>?> getAdmin(String username) async {
    final db = await database;
    final res = await db.query(
      'local_admins',
      where: 'username = ?',
      whereArgs: [username],
      limit: 1,
    );
    if (res.isNotEmpty) return res.first;
    return null;
  }

  // ═══════════════════════════════════════════════════════════════════════════
  // EMPLOYEE LAYER (local_employees)
  // ═══════════════════════════════════════════════════════════════════════════

  Future<void> clearEmployeesCache() async {
    final db = await database;
    await db.delete('local_employees');
  }

  Future<void> bulkSaveEmployees(List<Map<String, dynamic>> emps) async {
    final db = await database;
    final batch = db.batch();
    for (final emp in emps) {
      batch.insert(
        'local_employees',
        {
          'id': emp['id'],
          'nik': emp['nik'],
          'name': emp['name'],
          'department': emp['department'] ?? emp['jabatan'],
          'afdeling': emp['afdeling'],
          'nama_kebun': emp['nama_kebun'],
          'status_tk': emp['status_tk'],
          'jabatan': emp['jabatan'],
          'status_perkawinan': emp['status_perkawinan'],
          'has_master_biometric': emp['has_master_biometric'] == true || emp['has_master_biometric'] == 1 ? 1 : 0,
        },
        conflictAlgorithm: ConflictAlgorithm.replace,
      );
    }
    await batch.commit(noResult: true);
  }

  Future<List<Map<String, dynamic>>> getEmployees({
    required String role,
    String? region,
    String? kebun,
  }) async {
    final db = await database;
    String? whereClause;
    List<dynamic>? whereArgs;

    if (role == 'estate_admin' && kebun != null) {
      whereClause = 'nama_kebun = ?';
      whereArgs = [kebun];
    }

    final List<Map<String, dynamic>> maps = await db.query(
      'local_employees',
      where: whereClause,
      whereArgs: whereArgs,
      orderBy: 'name ASC',
    );

    return maps.map((row) {
      return {
        ...row,
        'has_master_biometric': row['has_master_biometric'] == 1,
      };
    }).toList();
  }

  // ═══════════════════════════════════════════════════════════════════════════
  // BIOMETRICS LAYER (local_master_descriptors)
  // ═══════════════════════════════════════════════════════════════════════════

  Future<void> saveMasterDescriptor(
    int employeeId,
    String descriptorJson,
    String? geometricDescriptorJson,
  ) async {
    final db = await database;
    await db.insert(
      'local_master_descriptors',
      {
        'employee_id': employeeId,
        'descriptor_json': descriptorJson,
        'geometric_descriptor_json': geometricDescriptorJson,
        'updated_at': DateTime.now().toIso8601String(),
      },
      conflictAlgorithm: ConflictAlgorithm.replace,
    );

    // Update has_master_biometric inside local_employees
    await db.update(
      'local_employees',
      {'has_master_biometric': 1},
      where: 'id = ?',
      whereArgs: [employeeId],
    );
  }

  Future<Map<String, dynamic>?> getMasterDescriptor(int employeeId) async {
    final db = await database;
    final res = await db.rawQuery('''
      SELECT md.*, e.nik, e.name, e.department, e.afdeling, e.nama_kebun
      FROM local_master_descriptors md
      LEFT JOIN local_employees e ON md.employee_id = e.id
      WHERE md.employee_id = ?
    ''', [employeeId]);

    if (res.isNotEmpty) return res.first;
    return null;
  }

  Future<List<Map<String, dynamic>>> getAllMasterDescriptors() async {
    final db = await database;
    final res = await db.rawQuery('''
      SELECT md.employee_id, md.descriptor_json, e.nik, e.name
      FROM local_master_descriptors md
      LEFT JOIN local_employees e ON md.employee_id = e.id
      WHERE md.descriptor_json IS NOT NULL
    ''');
    return res;
  }

  // ═══════════════════════════════════════════════════════════════════════════
  // ATTENDANCE QUEUE LAYER (local_attendance_queue)
  // ═══════════════════════════════════════════════════════════════════════════

  Future<Map<String, dynamic>> queueOfflineAttendance(Map<String, dynamic> log) async {
    final db = await database;
    final createdAt = DateTime.now().toIso8601String();
    final timestamp = log['timestamp'] ?? createdAt;

    final id = await db.insert('local_attendance_queue', {
      'employee_id': log['employee_id'],
      'nik': log['nik'],
      'name': log['name'],
      'department': log['department'],
      'afdeling': log['afdeling'],
      'timestamp': timestamp,
      'location': log['location'] ?? 'HP Mobile (Offline)',
      'lat': log['lat'],
      'lng': log['lng'],
      'status': log['status'],
      'attendance_type': log['attendance_type'] ?? 'CHECK_IN',
      'euclidean_distance': log['euclidean_distance'],
      'is_synced': 0,
      'created_at': createdAt,
    });

    return {
      ...log,
      'id': id,
      'timestamp': timestamp,
      'is_synced': 0,
      'created_at': createdAt,
    };
  }

  Future<List<Map<String, dynamic>>> getUnsyncedAttendanceQueue() async {
    final db = await database;
    return await db.query(
      'local_attendance_queue',
      where: 'is_synced = 0',
      orderBy: 'id ASC',
    );
  }

  Future<void> removeSyncedAttendanceLogs(List<int> ids) async {
    final db = await database;
    final batch = db.batch();
    for (final id in ids) {
      batch.delete(
        'local_attendance_queue',
        where: 'id = ?',
        whereArgs: [id],
      );
    }
    await batch.commit(noResult: true);
  }

  // ═══════════════════════════════════════════════════════════════════════════
  // PERMANENT LOGS LAYER (local_attendance_logs)
  // ═══════════════════════════════════════════════════════════════════════════

  Future<void> saveAttendanceLog(Map<String, dynamic> log) async {
    final db = await database;
    await db.insert(
      'local_attendance_logs',
      {
        'id': log['id'].toString(),
        'employee_id': log['employee_id'],
        'nik': log['nik'],
        'name': log['name'],
        'department': log['department'],
        'afdeling': log['afdeling'],
        'timestamp': log['timestamp'],
        'location': log['location'],
        'lat': log['lat'],
        'lng': log['lng'],
        'status': log['status'],
        'attendance_type': log['attendance_type'],
        'euclidean_distance': log['euclidean_distance'],
        'is_synced': log['is_synced'] == true || log['is_synced'] == 1 ? 1 : 0,
        'created_at': log['created_at'] ?? DateTime.now().toIso8601String(),
      },
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
  }

  Future<void> bulkSaveAttendanceLogs(List<Map<String, dynamic>> logs) async {
    final db = await database;
    final batch = db.batch();
    for (final log in logs) {
      batch.insert(
        'local_attendance_logs',
        {
          'id': log['id'].toString(),
          'employee_id': log['employee_id'],
          'nik': log['nik'],
          'name': log['name'],
          'department': log['department'],
          'afdeling': log['afdeling'],
          'timestamp': log['timestamp'],
          'location': log['location'],
          'lat': log['lat'],
          'lng': log['lng'],
          'status': log['status'],
          'attendance_type': log['attendance_type'],
          'euclidean_distance': log['euclidean_distance'],
          'is_synced': log['is_synced'] == true || log['is_synced'] == 1 ? 1 : 0,
          'created_at': log['created_at'] ?? DateTime.now().toIso8601String(),
        },
        conflictAlgorithm: ConflictAlgorithm.replace,
      );
    }
    await batch.commit(noResult: true);
  }

  Future<List<Map<String, dynamic>>> getAttendanceLogs({
    required String role,
    String? region,
    String? kebun,
  }) async {
    final db = await database;
    List<Map<String, dynamic>> serverLogs;

    if (role == 'estate_admin' && kebun != null) {
      // Akun Kebun: hanya log dari kebun ini
      serverLogs = await db.rawQuery('''
        SELECT al.*
        FROM local_attendance_logs al
        LEFT JOIN local_employees e ON CAST(al.employee_id AS TEXT) = CAST(e.id AS TEXT)
        WHERE e.nama_kebun = ?
        ORDER BY al.timestamp DESC
      ''', [kebun]);
    } else if (role == 'regional_admin' && region != null) {
      // Akun Regional: ambil semua kebun di region ini, lalu filter log berdasarkan itu
      final kebunList = await db.query(
        'local_employees',
        columns: ['DISTINCT nama_kebun'],
        where: 'nama_kebun IS NOT NULL',
      );
      // Since local_employees doesn't have a region column, we get all logs
      // that belong to employees stored locally (which were filtered by region at sync time)
      serverLogs = await db.rawQuery('''
        SELECT DISTINCT al.*
        FROM local_attendance_logs al
        INNER JOIN local_employees e ON CAST(al.employee_id AS TEXT) = CAST(e.id AS TEXT)
        ORDER BY al.timestamp DESC
      ''');
    } else {
      // Akun HO: tampilkan semua log
      serverLogs = await db.query(
        'local_attendance_logs',
        orderBy: 'timestamp DESC',
      );
    }

    // Gabungkan juga dengan unsynced queue (log offline yang belum diupload)
    final queueLogs = await db.rawQuery('''
      SELECT 
        'offline_' || CAST(q.id AS TEXT) AS id,
        q.employee_id, q.nik, q.name, q.department, q.afdeling,
        q.timestamp, q.location, q.lat, q.lng, q.status,
        q.attendance_type, q.euclidean_distance,
        0 AS is_synced, q.created_at
      FROM local_attendance_queue q
      WHERE q.is_synced = 0
      ORDER BY q.timestamp DESC
    ''');

    // Gabungkan server logs + offline queue, hindari duplikat
    final allLogs = [...serverLogs, ...queueLogs];
    return allLogs;
  }

  /// Hapus semua cache log dan karyawan lokal — dipanggil saat logout
  /// untuk mencegah data akun lama terlihat oleh akun berikutnya.
  Future<void> clearAllLogsCache() async {
    final db = await database;
    await db.delete('local_attendance_logs');
    await db.delete('local_employees');
    await db.delete('local_master_descriptors');
    debugPrint('[DatabaseHelper] Semua cache log, karyawan, dan biometrik lokal telah dibersihkan.');
  }

  /// Ambil semua item dari antrian offline yang belum diupload ke Supabase
  Future<List<Map<String, dynamic>>> getUnsyncedQueue() async {
    final db = await database;
    return await db.query(
      'local_attendance_queue',
      where: 'is_synced = 0',
      orderBy: 'id ASC',
    );
  }

  /// Tandai item antrian sebagai sudah disinkronkan
  Future<void> markQueueItemSynced(int queueId, String serverAssignedId) async {
    final db = await database;
    await db.update(
      'local_attendance_queue',
      {'is_synced': 1},
      where: 'id = ?',
      whereArgs: [queueId],
    );
  }

  Future<List<Map<String, dynamic>>> getTodayAttendanceLogs(int employeeId, String dateStr) async {
    final db = await database;
    return await db.query(
      'local_attendance_logs',
      where: 'employee_id = ? AND timestamp LIKE ?',
      whereArgs: [employeeId, '$dateStr%'],
    );
  }

  Future<void> deleteAttendanceLog(String id) async {
    final db = await database;
    await db.delete(
      'local_attendance_logs',
      where: 'id = ?',
      whereArgs: [id],
    );
  }
}
