import 'dart:convert';
import 'package:crypto/crypto.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../services/database_helper.dart';
import '../services/secure_storage_helper.dart';

class AuthProvider extends ChangeNotifier {
  static const String supabaseUrl = 'https://qrtvawixmlekbitvfuav.supabase.co';
  static const String supabaseAnonKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFydHZhd2l4bWxla2JpdHZmdWF2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODUzNTczNzAsImV4cCI6MjEwMDkzMzM3MH0.VmVPs_YQRPmK073_G7HTCFkH0MT__VVJWPO7OuS2JKU';

  Map<String, dynamic>? _user;
  bool _isLoading = true;

  Map<String, dynamic>? get user => _user;
  bool get isLoading => _isLoading;
  bool get isAuthenticated => _user != null;

  AuthProvider() {
    checkAuth();
  }

  String _hashPassword(String password) {
    final bytes = utf8.encode(password);
    final digest = sha256.convert(bytes);
    return digest.toString();
  }

  Future<void> checkAuth() async {
    _isLoading = true;
    notifyListeners();
    try {
      final session = await SecureStorageHelper.instance.getSession();
      if (session != null) {
        _user = session;
      } else {
        _user = null;
      }
    } catch (e) {
      _user = null;
    } finally {
      _isLoading = false;
      notifyListeners();
    }
  }

  Future<Map<String, dynamic>> login(String username, String password) async {
    _isLoading = true;
    notifyListeners();

    try {
      // 1. Try Online Login via Supabase verify_admin_login RPC
      final response = await http.post(
        Uri.parse('$supabaseUrl/rest/v1/rpc/verify_admin_login'),
        headers: {
          'apikey': supabaseAnonKey,
          'Authorization': 'Bearer $supabaseAnonKey',
          'Content-Type': 'application/json',
        },
        body: jsonEncode({
          'p_username': username,
          'p_password': password,
        }),
      ).timeout(const Duration(seconds: 10));

      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        if (data.isNotEmpty) {
          final result = data[0] as Map<String, dynamic>;
          if (result['is_valid'] == true) {
            final localHash = _hashPassword(password);
            
            final adminUser = {
              'id': 'admin-$username',
              'username': username,
              'role': result['u_role'] ?? 'estate_admin',
              'region': result['u_region'],
              'kebun': result['u_kebun'],
              'name': result['u_name'] ?? 'Administrator',
              'nik': result['u_nik'],
            };

            // Cache credentials locally for offline usage
            try {
              await DatabaseHelper.instance.saveAdmin({
                'username': username,
                'password_hash': localHash,
                'role': adminUser['role'],
                'region': adminUser['region'],
                'kebun': adminUser['kebun'],
                'name': adminUser['name'],
                'nik': adminUser['nik'],
              });
            } catch (dbErr) {
              debugPrint('[Offline Cache Error] Gagal menyimpan login lokal: $dbErr');
            }

            _user = adminUser;
            await SecureStorageHelper.instance.saveSession(adminUser);
            _isLoading = false;
            notifyListeners();
            return {'success': true, 'message': 'Login Berhasil'};
          }
        }
      }
      
      // If response is not 200 or is_valid is false, check locally before failing
      return await _loginOffline(username, password);

    } catch (err) {
      debugPrint('[AUTH LOGIN ONLINE ERROR]: $err - falling back to offline check');
      return await _loginOffline(username, password);
    }
  }

  Future<Map<String, dynamic>> _loginOffline(String username, String password) async {
    try {
      final cachedAdmin = await DatabaseHelper.instance.getAdmin(username);
      if (cachedAdmin != null) {
        final enteredHash = _hashPassword(password);
        if (enteredHash == cachedAdmin['password_hash']) {
          final adminUser = {
            'id': 'admin-$username',
            'username': username,
            'role': cachedAdmin['role'],
            'region': cachedAdmin['region'],
            'kebun': cachedAdmin['kebun'],
            'name': '${cachedAdmin['name']} (Offline)',
            'nik': cachedAdmin['nik'],
          };

          _user = adminUser;
          await SecureStorageHelper.instance.saveSession(adminUser);
          _isLoading = false;
          notifyListeners();
          return {'success': true, 'message': 'Login Mode Offline Berhasil'};
        }
      }
    } catch (localErr) {
      debugPrint('[OFFLINE AUTH ERROR]: $localErr');
    }

    _isLoading = false;
    notifyListeners();
    return {
      'success': false,
      'message': 'Username atau Password salah, atau akun belum terdaftar secara luring di perangkat ini.'
    };
  }

  Future<void> logout() async {
    _isLoading = true;
    notifyListeners();
    _user = null;
    await SecureStorageHelper.instance.clearSession();
    _isLoading = false;
    notifyListeners();
  }
}
