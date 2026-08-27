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

    try {
      final onlineCheck = await http
          .head(Uri.parse('https://qrtvawixmlekbitvfuav.supabase.co'))
          .timeout(const Duration(seconds: 3));

      if (onlineCheck.statusCode >= 200 && onlineCheck.statusCode < 400) {
        // Fetch last 100 logs from Supabase
        String url = '${AuthProvider.supabaseUrl}/rest/v1/attendance_logs?select=*&order=timestamp.desc&limit=100';
        if (role == 'estate_admin' && kebun != null) {
          // Inner join / filter in Supabase. For simplicity we can query directly
        }

        final res = await http.get(
          Uri.parse(url),
          headers: {
            'apikey': AuthProvider.supabaseAnonKey,
            'Authorization': 'Bearer ${AuthProvider.supabaseAnonKey}',
            'Content-Type': 'application/json',
          },
        );

        if (res.statusCode == 200) {
          final List<dynamic> data = jsonDecode(res.body);
          final serverLogs = data.map((it) => it as Map<String, dynamic>).toList();

          final db = DatabaseHelper.instance;
          // Bulk save to local SQLite logs
          await db.bulkSaveAttendanceLogs(
            serverLogs.map((log) => {...log, 'is_synced': 1}).toList(),
          );
        }
      }
    } catch (e) {
      debugPrint('[ONLINE LOGS REFRESH ERROR]: $e');
    }

    await _loadLogs();
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
