import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;
import '../providers/auth_provider.dart';
import '../services/database_helper.dart';
import '../theme.dart';

class LogsScreen extends StatefulWidget {
  const LogsScreen({super.key});

  @override
  State<LogsScreen> createState() => _LogsScreenState();
}

class _LogsScreenState extends State<LogsScreen> {
  List<Map<String, dynamic>> _logs = [];
  bool _isLoading = false;

  @override
  void initState() {
    super.initState();
    _loadLogs();
  }

  Future<void> _loadLogs() async {
    setState(() => _isLoading = true);
    try {
      final auth = Provider.of<AuthProvider>(context, listen: false);
      final user = auth.user;
      if (user == null) return;

      final db = DatabaseHelper.instance;
      final role = user['role'] ?? 'estate_admin';
      final kebun = user['kebun'];

      final data = await db.getAttendanceLogs(role: role, kebun: kebun);
      setState(() {
        _logs = data;
      });
    } catch (e) {
      debugPrint('[LOAD LOGS ERROR]: $e');
    } finally {
      setState(() => _isLoading = false);
    }
  }

  Future<void> _handleRefresh() async {
    final auth = Provider.of<AuthProvider>(context, listen: false);
    final user = auth.user;
    if (user == null) return;

    final role = user['role'] ?? 'estate_admin';
    final kebun = user['kebun'];
    final region = user['region'];

    bool isOnline = false;

    // ─── Cek Koneksi Internet ───────────────────────────────────────────────
    try {
      final onlineCheck = await http
          .head(Uri.parse('${AuthProvider.supabaseUrl}'))
          .timeout(const Duration(seconds: 4));
      isOnline = onlineCheck.statusCode >= 200 && onlineCheck.statusCode < 400;
    } catch (_) {
      isOnline = false;
    }

    if (!isOnline) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(
            content: Text('📡 Offline — menampilkan data lokal'),
            backgroundColor: Colors.orange,
            duration: Duration(seconds: 2),
          ),
        );
      }
      await _loadLogs();
      return;
    }

    // ─── STEP 1: UPLOAD data offline queue ke Supabase ─────────────────────
    if (mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('☁️ Mengunggah data offline ke server...'),
          backgroundColor: Color(0xFF1A6B5A),
          duration: Duration(seconds: 2),
        ),
      );
    }

    final db = DatabaseHelper.instance;
    int uploadedCount = 0;
    int failedCount = 0;

    try {
      final unsyncedQueue = await db.getUnsyncedQueue();
      debugPrint('[Refresh] Ditemukan ${unsyncedQueue.length} item offline yang perlu diupload.');

      for (final item in unsyncedQueue) {
        try {
          final payload = {
            'employee_id': item['employee_id'],
            'nik': item['nik'],
            'name': item['name'],
            'department': item['department'],
            'afdeling': item['afdeling'],
            'timestamp': item['timestamp'],
            'location': item['location'],
            'latitude': item['lat'],
            'longitude': item['lng'],
            'status': item['status'],
            'attendance_type': item['attendance_type'],
            'euclidean_distance': item['euclidean_distance'],
          };

          final uploadRes = await http.post(
            Uri.parse('${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'),
            headers: {
              'apikey': AuthProvider.supabaseAnonKey,
              'Authorization': 'Bearer ${AuthProvider.supabaseAnonKey}',
              'Content-Type': 'application/json',
              'Prefer': 'return=representation',
            },
            body: jsonEncode(payload),
          ).timeout(const Duration(seconds: 10));

          if (uploadRes.statusCode == 201 || uploadRes.statusCode == 200) {
            // Tandai sudah di-sync di queue
            final queueId = item['id'] as int;
            await db.markQueueItemSynced(queueId, '');

            // Simpan juga ke local_attendance_logs sebagai catatan permanen
            try {
              final List<dynamic> uploaded = jsonDecode(uploadRes.body);
              if (uploaded.isNotEmpty) {
                await db.saveAttendanceLog({...uploaded[0], 'is_synced': 1});
              }
            } catch (_) {}

            uploadedCount++;
            debugPrint('[Refresh] Upload berhasil untuk item queue id=${item['id']}');
          } else {
            failedCount++;
            debugPrint('[Refresh] Upload gagal (${uploadRes.statusCode}): ${uploadRes.body}');
          }
        } catch (itemErr) {
          failedCount++;
          debugPrint('[Refresh] Upload error item queue id=${item['id']}: $itemErr');
        }
      }
    } catch (uploadErr) {
      debugPrint('[Refresh] Error upload phase: $uploadErr');
    }

    // ─── STEP 2: DOWNLOAD log terbaru dari Supabase ─────────────────────────
    if (mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(
            uploadedCount > 0
                ? '✅ $uploadedCount data offline terupload. Mengunduh data terbaru...'
                : '🔄 Mengunduh data terbaru dari server...',
          ),
          backgroundColor: const Color(0xFF1A3D5A),
          duration: const Duration(seconds: 2),
        ),
      );
    }

    try {
      // Bangun URL query dengan filter sesuai role
      String url;
      if (role == 'estate_admin' && kebun != null) {
        // Ambil dulu employee_id yang bekerja di kebun ini
        final empRes = await http.get(
          Uri.parse(
              '${AuthProvider.supabaseUrl}/rest/v1/employees?select=id&nama_kebun=eq.${Uri.encodeQueryComponent(kebun)}&limit=500'),
          headers: {
            'apikey': AuthProvider.supabaseAnonKey,
            'Authorization': 'Bearer ${AuthProvider.supabaseAnonKey}',
          },
        ).timeout(const Duration(seconds: 8));

        if (empRes.statusCode == 200) {
          final List<dynamic> emps = jsonDecode(empRes.body);
          if (emps.isNotEmpty) {
            final ids = emps.map((e) => e['id'].toString()).join(',');
            url = '${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'
                '?select=*&employee_id=in.($ids)&order=timestamp.desc&limit=500';
          } else {
            url = '${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'
                '?select=*&employee_id=eq.-1&order=timestamp.desc&limit=1';
          }
        } else {
          url = '${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'
              '?select=*&order=timestamp.desc&limit=100';
        }
      } else if (role == 'regional_admin' && region != null) {
        // Ambil employee_id dari semua kebun di region ini
        final empRes = await http.get(
          Uri.parse(
              '${AuthProvider.supabaseUrl}/rest/v1/employees?select=id&region=eq.${Uri.encodeQueryComponent(region)}&limit=1000'),
          headers: {
            'apikey': AuthProvider.supabaseAnonKey,
            'Authorization': 'Bearer ${AuthProvider.supabaseAnonKey}',
          },
        ).timeout(const Duration(seconds: 8));

        if (empRes.statusCode == 200) {
          final List<dynamic> emps = jsonDecode(empRes.body);
          if (emps.isNotEmpty) {
            final ids = emps.map((e) => e['id'].toString()).join(',');
            url = '${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'
                '?select=*&employee_id=in.($ids)&order=timestamp.desc&limit=500';
          } else {
            url = '${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'
                '?select=*&order=timestamp.desc&limit=200';
          }
        } else {
          url = '${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'
              '?select=*&order=timestamp.desc&limit=200';
        }
      } else {
        // headoffice_admin: ambil semua
        url = '${AuthProvider.supabaseUrl}/rest/v1/attendance_logs'
            '?select=*&order=timestamp.desc&limit=1000';
      }

      final res = await http.get(
        Uri.parse(url),
        headers: {
          'apikey': AuthProvider.supabaseAnonKey,
          'Authorization': 'Bearer ${AuthProvider.supabaseAnonKey}',
          'Content-Type': 'application/json',
        },
      ).timeout(const Duration(seconds: 15));

      if (res.statusCode == 200) {
        final List<dynamic> data = jsonDecode(res.body);
        final serverLogs = data.map((it) => it as Map<String, dynamic>).toList();

        // Hapus cache lama yang sudah sync, simpan yang baru
        await db.bulkSaveAttendanceLogs(
          serverLogs.map((log) => {...log, 'is_synced': 1}).toList(),
        );
        debugPrint('[Refresh] ${serverLogs.length} log berhasil diunduh dari Supabase.');
      }
    } catch (downloadErr) {
      debugPrint('[Refresh] Error download phase: $downloadErr');
    }

    // ─── STEP 3: Muat ulang UI dari SQLite lokal ────────────────────────────
    await _loadLogs();

    if (mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('✅ Sinkronisasi selesai'),
          backgroundColor: Color(0xFF1A6B2A),
          duration: Duration(seconds: 2),
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Log Absensi Karyawan', style: TextStyle(fontWeight: FontWeight.bold)),
        backgroundColor: AgriFaceTheme.darkBg,
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh, color: AgriFaceTheme.accentCyan),
            onPressed: _handleRefresh,
            tooltip: 'Tarik Log Server',
          ),
        ],
      ),
      body: _isLoading
          ? const Center(child: CircularProgressIndicator(color: AgriFaceTheme.accentCyan))
          : RefreshIndicator(
              onRefresh: _handleRefresh,
              color: AgriFaceTheme.accentCyan,
              child: _logs.isEmpty
                  ? const Center(
                      child: Text(
                        'Belum ada riwayat absensi.',
                        style: TextStyle(color: AgriFaceTheme.textMuted),
                      ),
                    )
                  : ListView.builder(
                      itemCount: _logs.length,
                      padding: const EdgeInsets.all(16),
                      itemBuilder: (context, index) {
                        final log = _logs[index];
                        final isSynced = log['is_synced'] == 1 || log['is_synced'] == true;
                        final type = log['attendance_type'] ?? 'CHECK_IN';
                        final timestampStr = log['timestamp']?.toString() ?? '';
                        
                        // Parse timestamp to human readable format
                        String dateFormatted = timestampStr;
                        try {
                          final parsedDate = DateTime.parse(timestampStr);
                          dateFormatted = '${parsedDate.day}/${parsedDate.month}/${parsedDate.year} - ${parsedDate.hour.toString().padLeft(2, '0')}:${parsedDate.minute.toString().padLeft(2, '0')}';
                        } catch (_) {}

                        return Card(
                          margin: const EdgeInsets.only(bottom: 12),
                          child: ListTile(
                            contentPadding: const EdgeInsets.all(16),
                            leading: Container(
                              padding: const EdgeInsets.all(10),
                              decoration: BoxDecoration(
                                color: type == 'CHECK_IN'
                                    ? AgriFaceTheme.accentCyan.withOpacity(0.1)
                                    : Colors.orangeAccent.withOpacity(0.1),
                                shape: BoxShape.circle,
                              ),
                              child: FaIcon(
                                type == 'CHECK_IN' ? FontAwesomeIcons.rightToBracket : FontAwesomeIcons.rightFromBracket,
                                color: type == 'CHECK_IN' ? AgriFaceTheme.accentCyan : Colors.orangeAccent,
                                size: 18,
                              ),
                            ),
                            title: Text(
                              log['name'] ?? 'KARYAWAN',
                              style: const TextStyle(
                                fontWeight: FontWeight.bold,
                                fontSize: 16,
                                color: Colors.white,
                              ),
                            ),
                            subtitle: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                const SizedBox(height: 6),
                                Text('NIK: ${log['nik'] ?? '-'}',
                                    style: const TextStyle(color: AgriFaceTheme.textMuted, fontSize: 13)),
                                Text('Divisi: ${log['department'] ?? '-'}',
                                    style: const TextStyle(color: AgriFaceTheme.textMuted, fontSize: 13)),
                                Text('Waktu: $dateFormatted',
                                    style: const TextStyle(color: AgriFaceTheme.textMuted, fontSize: 13)),
                                if (log['euclidean_distance'] != null)
                                  Text(
                                    'Kecocokan: ${(double.parse(log['euclidean_distance'].toString()) * 100).toStringAsFixed(1)}%',
                                    style: const TextStyle(color: AgriFaceTheme.accentCyan, fontSize: 12),
                                  ),
                              ],
                            ),
                            trailing: Column(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Icon(
                                  isSynced ? Icons.cloud_done : Icons.cloud_off,
                                  color: isSynced ? AgriFaceTheme.accentEmerald : AgriFaceTheme.accentRed,
                                ),
                                const SizedBox(height: 4),
                                Text(
                                  isSynced ? 'Sinkron' : 'Lokal',
                                  style: TextStyle(
                                    color: isSynced ? AgriFaceTheme.accentEmerald : AgriFaceTheme.accentRed,
                                    fontSize: 11,
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                              ],
                            ),
                          ),
                        );
                      },
                    ),
            ),
    );
  }
}
