import 'dart:async';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;
import '../providers/auth_provider.dart';
import '../services/database_helper.dart';
import '../theme.dart';

class DashboardScreen extends StatefulWidget {
  const DashboardScreen({super.key});

  @override
  State<DashboardScreen> createState() => _DashboardScreenState();
}

class _DashboardScreenState extends State<DashboardScreen> {
  bool _isOnline = true;
  bool _isSyncing = false;
  int _employeeCount = 0;
  int _unsyncedCount = 0;
  int _logCount = 0;
  Timer? _statusTimer;

  @override
  void initState() {
    super.initState();
    _checkConnection();
    _fetchStats();
    // Refresh connection and statistics every 10 seconds
    _statusTimer = Timer.periodic(const Duration(seconds: 10), (timer) {
      if (mounted) {
        _checkConnection();
        _fetchStats();
      }
    });
  }

  @override
  void dispose() {
    _statusTimer?.cancel();
    super.dispose();
  }

  Future<void> _checkConnection() async {
    try {
      final response = await http
          .head(Uri.parse('https://qrtvawixmlekbitvfuav.supabase.co'))
          .timeout(const Duration(seconds: 3));
      if (response.statusCode >= 200 && response.statusCode < 400) {
        if (mounted && !_isOnline) {
          setState(() => _isOnline = true);
        }
      } else {
        if (mounted && _isOnline) {
          setState(() => _isOnline = false);
        }
      }
    } catch (_) {
      if (mounted && _isOnline) {
        setState(() => _isOnline = false);
      }
    }
  }

  Future<void> _fetchStats() async {
    final auth = Provider.of<AuthProvider>(context, listen: false);
    final user = auth.user;
    if (user == null) return;

    try {
      final db = DatabaseHelper.instance;
      final role = user['role'] ?? 'estate_admin';
      final kebun = user['kebun'];

      final emps = await db.getEmployees(role: role, kebun: kebun);
      final logs = await db.getAttendanceLogs(role: role, kebun: kebun);
      final queue = await db.getUnsyncedAttendanceQueue();

      if (mounted) {
        setState(() {
          _employeeCount = emps.length;
          _logCount = logs.length;
          _unsyncedCount = queue.length;
        });
      }
    } catch (e) {
      debugPrint('[STATS FETCH ERROR]: $e');
    }
  }

  Future<void> _handleManualSync() async {
    if (!_isOnline) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Koneksi offline, tidak dapat melakukan sinkronisasi.'),
          backgroundColor: AgriFaceTheme.accentRed,
        ),
      );
      return;
    }

    setState(() => _isSyncing = true);

    try {
      final db = DatabaseHelper.instance;
      final unsynced = await db.getUnsyncedAttendanceQueue();

      if (unsynced.isEmpty) {
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(
            content: Text('Semua log absensi sudah tersinkronisasi.'),
            backgroundColor: AgriFaceTheme.accentEmerald,
          ),
        );
        setState(() => _isSyncing = false);
        return;
      }

      int successCount = 0;
      for (final log in unsynced) {
        // Send to Supabase attendance_logs via REST API
        final body = {
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
        };

        final res = await http.post(
          Uri.parse('${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'),
          headers: {
            'apikey': AuthProvider.supabaseAnonKey,
            'Authorization': 'Bearer ${AuthProvider.supabaseAnonKey}',
            'Content-Type': 'application/json',
            'Prefer': 'return=minimal',
          },
          body: jsonEncode(body),
        );

        if (res.statusCode == 201 || res.statusCode == 200) {
          successCount++;
          // Save to local logs as synced
          await db.saveAttendanceLog({
            ...log,
            'is_synced': 1,
          });
          // Remove from sync queue
          await db.removeSyncedAttendanceLogs([log['id']]);
        }
      }

      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Berhasil mensinkronisasi $successCount dari ${unsynced.length} log absensi.'),
          backgroundColor: AgriFaceTheme.accentEmerald,
        ),
      );
      _fetchStats();
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Error sinkronisasi: $e'),
          backgroundColor: AgriFaceTheme.accentRed,
        ),
      );
    } finally {
      setState(() => _isSyncing = false);
    }
  }

  void _handleLogout() async {
    final auth = Provider.of<AuthProvider>(context, listen: false);
    await auth.logout();
    if (mounted) {
      Navigator.pushReplacementNamed(context, '/login');
    }
  }

  @override
  Widget build(BuildContext context) {
    final auth = context.watch<AuthProvider>();
    final user = auth.user;

    final isEstateAdmin = user?['role'] == 'estate_admin';
    final locationName = isEstateAdmin ? (user?['kebun'] ?? 'Kebun') : (user?['region'] ?? 'Regional');

    return Scaffold(
      appBar: AppBar(
        backgroundColor: AgriFaceTheme.darkBg,
        title: const Row(
          children: [
            FaIcon(FontAwesomeIcons.fingerprint, color: AgriFaceTheme.accentCyan, size: 22),
            SizedBox(width: 8),
            Text('AgriFace Dashboard', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 18)),
          ],
        ),
        actions: [
          IconButton(
            icon: Icon(
              _isOnline ? Icons.wifi : Icons.wifi_off,
              color: _isOnline ? AgriFaceTheme.accentEmerald : AgriFaceTheme.accentRed,
            ),
            tooltip: _isOnline ? 'Online' : 'Offline',
            onPressed: _checkConnection,
          ),
          IconButton(
            icon: const Icon(Icons.logout, color: AgriFaceTheme.accentRed),
            onPressed: _handleLogout,
            tooltip: 'Keluar Sesi',
          ),
        ],
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            // Connection Status Banner
            Container(
              padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
              decoration: BoxDecoration(
                color: _isOnline
                    ? AgriFaceTheme.accentEmerald.withOpacity(0.1)
                    : AgriFaceTheme.accentRed.withOpacity(0.1),
                borderRadius: BorderRadius.circular(10),
                border: Border.all(
                  color: _isOnline
                      ? AgriFaceTheme.accentEmerald.withOpacity(0.3)
                      : AgriFaceTheme.accentRed.withOpacity(0.3),
                ),
              ),
              child: Row(
                children: [
                  Icon(
                    _isOnline ? Icons.check_circle : Icons.warning,
                    color: _isOnline ? AgriFaceTheme.accentEmerald : AgriFaceTheme.accentRed,
                    size: 18,
                  ),
                  const SizedBox(width: 8),
                  Text(
                    _isOnline
                        ? 'Tersambung Ke Internet - Cloud Sync Aktif'
                        : 'Aplikasi Berjalan Luring (Offline Mode)',
                    style: TextStyle(
                      color: _isOnline ? AgriFaceTheme.accentEmerald : AgriFaceTheme.accentRed,
                      fontWeight: FontWeight.bold,
                      fontSize: 13,
                    ),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 24),

            // Profile Card (Glassmorphic)
            Container(
              padding: const EdgeInsets.all(20),
              decoration: AgriFaceTheme.glassBox(),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        user?['name']?.toUpperCase() ?? 'ADMINISTRATOR',
                        style: const TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                      Container(
                        padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
                        decoration: BoxDecoration(
                          color: AgriFaceTheme.accentCyan.withOpacity(0.1),
                          borderRadius: BorderRadius.circular(6),
                          border: Border.all(color: AgriFaceTheme.accentCyan.withOpacity(0.3)),
                        ),
                        child: Text(
                          user?['role'] == 'estate_admin' ? 'KEBUN' : 'REGIONAL',
                          style: const TextStyle(
                            color: AgriFaceTheme.accentCyan,
                            fontSize: 11,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 8),
                  Text(
                    'Wilayah Kerja: $locationName',
                    style: const TextStyle(color: AgriFaceTheme.textMuted, fontSize: 14),
                  ),
                  Text(
                    'NIK Admin: ${user?['nik'] ?? '-'}',
                    style: const TextStyle(color: AgriFaceTheme.textMuted, fontSize: 14),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 24),

            // Quick Stats Grid
            Row(
              children: [
                Expanded(
                  child: _buildStatCard(
                    title: 'Karyawan',
                    value: _employeeCount.toString(),
                    icon: FontAwesomeIcons.users,
                    color: AgriFaceTheme.accentCyan,
                  ),
                ),
                const SizedBox(width: 16),
                Expanded(
                  child: _buildStatCard(
                    title: 'Total Log',
                    value: _logCount.toString(),
                    icon: FontAwesomeIcons.clockRotateLeft,
                    color: AgriFaceTheme.textMuted,
                  ),
                ),
              ],
            ),
            const SizedBox(height: 16),
            if (_unsyncedCount > 0) ...[
              GestureDetector(
                onTap: _isSyncing ? null : _handleManualSync,
                child: Container(
                  padding: const EdgeInsets.all(16),
                  decoration: BoxDecoration(
                    color: AgriFaceTheme.accentRed.withOpacity(0.08),
                    borderRadius: BorderRadius.circular(16),
                    border: Border.all(color: AgriFaceTheme.accentRed.withOpacity(0.2)),
                  ),
                  child: Row(
                    children: [
                      const FaIcon(FontAwesomeIcons.cloudArrowUp, color: AgriFaceTheme.accentRed),
                      const SizedBox(width: 12),
                      Expanded(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              '$_unsyncedCount Log Belum Sinkron',
                              style: const TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.bold,
                                fontSize: 14,
                              ),
                            ),
                            const Text(
                              'Ketuk di sini untuk sinkronisasi paksa ke server.',
                              style: TextStyle(
                                color: AgriFaceTheme.textMuted,
                                fontSize: 12,
                              ),
                            ),
                          ],
                        ),
                      ),
                      if (_isSyncing)
                        const SizedBox(
                          width: 20,
                          height: 20,
                          child: CircularProgressIndicator(strokeWidth: 2, color: AgriFaceTheme.accentRed),
                        )
                      else
                        const Icon(Icons.arrow_forward_ios, size: 14, color: AgriFaceTheme.accentRed),
                    ],
                  ),
                ),
              ),
              const SizedBox(height: 24),
            ],

            // Navigation Grid Menus
            const Text(
              'MENU UTAMA APLIKASI',
              style: TextStyle(
                color: AgriFaceTheme.accentCyan,
                fontWeight: FontWeight.bold,
                fontSize: 12,
                letterSpacing: 1,
              ),
            ),
            const SizedBox(height: 12),

            _buildMenuButton(
              title: 'Scanner Absensi Wajah',
              subtitle: 'Lakukan pemindaian absensi luring/daring',
              icon: FontAwesomeIcons.cameraRetro,
              color: AgriFaceTheme.accentCyan,
              onTap: () {
                // Navigate to Scanner screen
                Navigator.pushNamed(context, '/absensi');
              },
            ),
            const SizedBox(height: 12),

            if (isEstateAdmin) ...[
              _buildMenuButton(
                title: 'Pendaftaran Karyawan',
                subtitle: 'Tambah data karyawan kebun baru',
                icon: FontAwesomeIcons.userPlus,
                color: AgriFaceTheme.accentEmerald,
                onTap: () {
                  // Navigate to Employee Add screen
                  Navigator.pushNamed(context, '/karyawan/tambah');
                },
              ),
              const SizedBox(height: 12),
            ],

            _buildMenuButton(
              title: 'Daftar Karyawan',
              subtitle: 'Lihat data biometrik & informasi staff',
              icon: FontAwesomeIcons.users,
              color: Colors.purpleAccent,
              onTap: () {
                Navigator.pushNamed(context, '/karyawan');
              },
            ),
            const SizedBox(height: 12),

            _buildMenuButton(
              title: 'Riwayat Log Absensi',
              subtitle: 'Tinjau riwayat check-in & check-out',
              icon: FontAwesomeIcons.solidClock,
              color: Colors.orangeAccent,
              onTap: () {
                Navigator.pushNamed(context, '/logs');
              },
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildStatCard({
    required String title,
    required String value,
    required IconData icon,
    required Color color,
  }) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: AgriFaceTheme.glassBox(),
      child: Row(
        children: [
          Container(
            padding: const EdgeInsets.all(10),
            decoration: BoxDecoration(
              color: color.withOpacity(0.1),
              shape: BoxShape.circle,
            ),
            child: FaIcon(icon, color: color, size: 20),
          ),
          const SizedBox(width: 12),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                value,
                style: const TextStyle(
                  fontSize: 22,
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                ),
              ),
              Text(
                title,
                style: const TextStyle(
                  fontSize: 12,
                  color: AgriFaceTheme.textMuted,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }

  Widget _buildMenuButton({
    required String title,
    required String subtitle,
    required IconData icon,
    required Color color,
    required VoidCallback onTap,
  }) {
    return InkWell(
      onTap: onTap,
      borderRadius: BorderRadius.circular(16),
      child: Container(
        padding: const EdgeInsets.all(16),
        decoration: AgriFaceTheme.glassBox(),
        child: Row(
          children: [
            Container(
              padding: const EdgeInsets.all(12),
              decoration: BoxDecoration(
                color: color.withOpacity(0.1),
                borderRadius: BorderRadius.circular(12),
              ),
              child: FaIcon(icon, color: color, size: 22),
            ),
            const SizedBox(width: 16),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    title,
                    style: const TextStyle(
                      color: Colors.white,
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 2),
                  Text(
                    subtitle,
                    style: const TextStyle(
                      color: AgriFaceTheme.textMuted,
                      fontSize: 12,
                    ),
                  ),
                ],
              ),
            ),
            const Icon(Icons.arrow_forward_ios, size: 16, color: AgriFaceTheme.textMuted),
          ],
        ),
      ),
    );
  }
}
