import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;
import '../providers/auth_provider.dart';
import '../services/database_helper.dart';
import '../theme.dart';

class DaftarKaryawanScreen extends StatefulWidget {
  const DaftarKaryawanScreen({super.key});

  @override
  State<DaftarKaryawanScreen> createState() => _DaftarKaryawanScreenState();
}

class _DaftarKaryawanScreenState extends State<DaftarKaryawanScreen> {
  List<Map<String, dynamic>> _employees = [];
  bool _isLoading = false;
  String _searchQuery = '';

  @override
  void initState() {
    super.initState();
    _loadEmployees();
  }

  Future<void> _loadEmployees() async {
    setState(() => _isLoading = true);
    try {
      final auth = Provider.of<AuthProvider>(context, listen: false);
      final user = auth.user;
      if (user == null) return;

      final db = DatabaseHelper.instance;
      final role = user['role'] ?? 'estate_admin';
      final kebun = user['kebun'];

      final data = await db.getEmployees(role: role, kebun: kebun);
      setState(() {
        _employees = data;
      });
    } catch (e) {
      debugPrint('[LOAD EMPLOYEES ERROR]: $e');
    } finally {
      setState(() => _isLoading = false);
    }
  }

  Future<void> _handleRefresh() async {
    // If online, fetch fresh employees from Supabase and rebuild SQLite cache
    final auth = Provider.of<AuthProvider>(context, listen: false);
    final user = auth.user;
    if (user == null) return;

    final role = user['role'] ?? 'estate_admin';
    final kebun = user['kebun'];

    try {
      // Check online status by performing a light fetch
      final onlineCheck = await http
          .head(Uri.parse('https://qrtvawixmlekbitvfuav.supabase.co'))
          .timeout(const Duration(seconds: 3));

      if (onlineCheck.statusCode >= 200 && onlineCheck.statusCode < 400) {
        // Fetch from Supabase
        String url = '${AuthProvider.supabaseUrl}/rest/v1/employees?select=*';
        if (role == 'estate_admin' && kebun != null) {
          url += '&nama_kebun=eq.${Uri.encodeComponent(kebun)}';
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
          final employeesList = data.map((it) => it as Map<String, dynamic>).toList();

          // Sync biometric descriptors from master_descriptors table
          final empIds = employeesList.map((e) => e['id']).toList();
          List<dynamic> descData = [];
          if (empIds.isNotEmpty) {
            final descUrl = '${AuthProvider.supabaseUrl}/rest/v1/master_descriptors?employee_id=in.(${empIds.join(",")})';
            final descRes = await http.get(
              Uri.parse(descUrl),
              headers: {
                'apikey': AuthProvider.supabaseAnonKey,
                'Authorization': 'Bearer ${AuthProvider.supabaseAnonKey}',
              },
            );
            if (descRes.statusCode == 200) {
              descData = jsonDecode(descRes.body);
            }
          }

          final descMap = {
            for (var d in descData) d['employee_id'].toString(): d
          };

          final db = DatabaseHelper.instance;
          await db.clearEmployeesCache();

          // Save employees bulk
          final mappedEmps = employeesList.map((emp) {
            final match = descMap[emp['id'].toString()];
            return {
              ...emp,
              'has_master_biometric': match != null ? 1 : 0
            };
          }).toList();
          await db.bulkSaveEmployees(mappedEmps);

          // Save biometrics descriptors bulk
          for (final d in descData) {
            await db.saveMasterDescriptor(
              d['employee_id'],
              d['descriptor_json'],
              d['geometric_descriptor_json'],
            );
          }
        }
      }
    } catch (e) {
      debugPrint('[ONLINE REFRESH ERROR]: $e');
    }

    await _loadEmployees();
  }

  void _openAddEmployeeDialog() {
    final auth = Provider.of<AuthProvider>(context, listen: false);
    final user = auth.user;
    final kebunName = user?['kebun'] ?? '';

    final formKey = GlobalKey<FormState>();
    final nikController = TextEditingController();
    final nameController = TextEditingController();
    final deptController = TextEditingController();
    final afdController = TextEditingController();
    final statusTkController = TextEditingController(text: 'SKU');
    final jabatanController = TextEditingController();
    final statusNikahController = TextEditingController(text: 'K/0');

    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: AgriFaceTheme.darkCard,
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
      ),
      builder: (context) {
        return Padding(
          padding: EdgeInsets.only(
            bottom: MediaQuery.of(context).viewInsets.bottom,
            top: 20,
            left: 20,
            right: 20,
          ),
          child: SingleChildScrollView(
            child: Form(
              key: formKey,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                mainAxisSize: MainAxisSize.min,
                children: [
                  const Text(
                    'TAMBAH KARYAWAN BARU',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      color: AgriFaceTheme.accentCyan,
                      fontWeight: FontWeight.bold,
                      fontSize: 16,
                    ),
                  ),
                  const SizedBox(height: 20),
                  
                  // Locked Kebun input field
                  TextFormField(
                    initialValue: kebunName,
                    enabled: false,
                    decoration: const InputDecoration(
                      labelText: 'Nama Kebun (Terkunci)',
                      prefixIcon: Icon(Icons.lock, color: AgriFaceTheme.accentCyan),
                    ),
                    style: const TextStyle(color: Colors.white),
                  ),
                  const SizedBox(height: 12),

                  TextFormField(
                    controller: nameController,
                    decoration: const InputDecoration(
                      labelText: 'Nama Lengkap',
                      prefixIcon: Icon(Icons.badge),
                    ),
                    validator: (v) => v == null || v.isEmpty ? 'Nama tidak boleh kosong' : null,
                  ),
                  const SizedBox(height: 12),

                  TextFormField(
                    controller: nikController,
                    decoration: const InputDecoration(
                      labelText: 'NIK',
                      prefixIcon: Icon(Icons.credit_card),
                    ),
                    validator: (v) => v == null || v.isEmpty ? 'NIK tidak boleh kosong' : null,
                  ),
                  const SizedBox(height: 12),

                  TextFormField(
                    controller: deptController,
                    decoration: const InputDecoration(
                      labelText: 'Departemen / Divisi',
                      prefixIcon: Icon(Icons.work),
                    ),
                  ),
                  const SizedBox(height: 12),

                  TextFormField(
                    controller: afdController,
                    decoration: const InputDecoration(
                      labelText: 'Afdeling (contoh: OA, OB)',
                      prefixIcon: Icon(Icons.map),
                    ),
                  ),
                  const SizedBox(height: 12),

                  Row(
                    children: [
                      Expanded(
                        child: TextFormField(
                          controller: statusTkController,
                          decoration: const InputDecoration(
                            labelText: 'Status TK (SKU/BHL)',
                          ),
                        ),
                      ),
                      const SizedBox(width: 12),
                      Expanded(
                        child: TextFormField(
                          controller: statusNikahController,
                          decoration: const InputDecoration(
                            labelText: 'Status Nikah',
                          ),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),

                  ElevatedButton(
                    onPressed: () async {
                      if (!formKey.currentState!.validate()) return;

                      try {
                        final db = DatabaseHelper.instance;

                        // Save to Supabase first if online
                        final newEmployee = {
                          'nik': nikController.text.trim(),
                          'name': nameController.text.trim(),
                          'department': deptController.text.trim(),
                          'afdeling': afdController.text.trim(),
                          'nama_kebun': kebunName,
                          'status_tk': statusTkController.text.trim(),
                          'jabatan': jabatanController.text.trim(),
                          'status_perkawinan': statusNikahController.text.trim(),
                          'has_master_biometric': 0
                        };

                        // 1. Write locally
                        // Generate a unique negative ID for offline creations
                        final offlineId = DateTime.now().millisecondsSinceEpoch ~/ 1000;
                        await db.bulkSaveEmployees([
                          {
                            'id': offlineId,
                            ...newEmployee,
                          }
                        ]);

                        // 2. Attempt online write
                        try {
                          final res = await http.post(
                            Uri.parse('${AuthProvider.supabaseUrl}/rest/v1/employees'),
                            headers: {
                              'apikey': AuthProvider.supabaseAnonKey,
                              'Authorization': 'Bearer ${AuthProvider.supabaseAnonKey}',
                              'Content-Type': 'application/json',
                              'Prefer': 'return=minimal',
                            },
                            body: jsonEncode(newEmployee),
                          );
                          if (res.statusCode == 201 || res.statusCode == 200) {
                            debugPrint('[Employee Add] Online sync success.');
                          }
                        } catch (_) {}

                        if (context.mounted) {
                          Navigator.pop(context);
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text('Karyawan berhasil didaftarkan.'),
                              backgroundColor: AgriFaceTheme.accentEmerald,
                            ),
                          );
                          _loadEmployees();
                        }
                      } catch (e) {
                        debugPrint('[ADD EMPLOYEE ERROR]: $e');
                      }
                    },
                    child: const Text('SIMPAN DATA KARYAWAN'),
                  ),
                  const SizedBox(height: 20),
                ],
              ),
            ),
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    final auth = context.watch<AuthProvider>();
    final isEstateAdmin = auth.user?['role'] == 'estate_admin';

    final filteredEmployees = _employees.where((emp) {
      final name = emp['name']?.toString().toLowerCase() ?? '';
      final nik = emp['nik']?.toString().toLowerCase() ?? '';
      final q = _searchQuery.toLowerCase();
      return name.contains(q) || nik.contains(q);
    }).toList();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Daftar Karyawan', style: TextStyle(fontWeight: FontWeight.bold)),
        backgroundColor: AgriFaceTheme.darkBg,
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh, color: AgriFaceTheme.accentCyan),
            onPressed: _handleRefresh,
            tooltip: 'Tarik Data Awan',
          ),
        ],
      ),
      floatingActionButton: isEstateAdmin
          ? FloatingActionButton(
              backgroundColor: AgriFaceTheme.accentCyan,
              foregroundColor: Colors.black,
              onPressed: _openAddEmployeeDialog,
              child: const Icon(Icons.add),
            )
          : null,
      body: Column(
        children: [
          // Search Box
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: TextField(
              onChanged: (v) => setState(() => _searchQuery = v),
              decoration: InputDecoration(
                hintText: 'Cari berdasarkan nama atau NIK...',
                prefixIcon: const Icon(Icons.search, color: AgriFaceTheme.textMuted),
                suffixIcon: _searchQuery.isNotEmpty
                    ? IconButton(
                        icon: const Icon(Icons.clear, color: AgriFaceTheme.textMuted),
                        onPressed: () => setState(() => _searchQuery = ''),
                      )
                    : null,
              ),
            ),
          ),

          // Employees List
          Expanded(
            child: _isLoading
                ? const Center(child: CircularProgressIndicator(color: AgriFaceTheme.accentCyan))
                : RefreshIndicator(
                    onRefresh: _handleRefresh,
                    color: AgriFaceTheme.accentCyan,
                    child: filteredEmployees.isEmpty
                        ? const Center(
                            child: Text(
                              'Tidak ada data karyawan.',
                              style: TextStyle(color: AgriFaceTheme.textMuted),
                            ),
                          )
                        : ListView.builder(
                            itemCount: filteredEmployees.length,
                            padding: const EdgeInsets.symmetric(horizontal: 16),
                            itemBuilder: (context, index) {
                              final emp = filteredEmployees[index];
                              final hasBio = emp['has_master_biometric'] == true;

                              return Card(
                                margin: const EdgeInsets.only(bottom: 12),
                                child: ListTile(
                                  contentPadding: const EdgeInsets.all(16),
                                  title: Text(
                                    emp['name'] ?? 'TANPA NAMA',
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
                                      Text('NIK: ${emp['nik'] ?? '-'}',
                                          style: const TextStyle(color: AgriFaceTheme.textMuted, fontSize: 13)),
                                      Text('Divisi: ${emp['department'] ?? emp['jabatan'] ?? '-'}',
                                          style: const TextStyle(color: AgriFaceTheme.textMuted, fontSize: 13)),
                                      Text('Afdeling: ${emp['afdeling'] ?? '-'}',
                                          style: const TextStyle(color: AgriFaceTheme.textMuted, fontSize: 13)),
                                    ],
                                  ),
                                  trailing: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: [
                                      FaIcon(
                                        hasBio ? FontAwesomeIcons.faceSmile : FontAwesomeIcons.faceFrown,
                                        color: hasBio ? AgriFaceTheme.accentEmerald : AgriFaceTheme.accentRed,
                                      ),
                                      const SizedBox(height: 4),
                                      Text(
                                        hasBio ? 'Terdaftar' : 'Belum Ada',
                                        style: TextStyle(
                                          color: hasBio ? AgriFaceTheme.accentEmerald : AgriFaceTheme.accentRed,
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
          ),
        ],
      ),
    );
  }
}
