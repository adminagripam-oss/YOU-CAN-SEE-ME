import 'dart:convert';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class SecureStorageHelper {
  static final SecureStorageHelper instance = SecureStorageHelper._privateConstructor();
  final _storage = const FlutterSecureStorage();

  SecureStorageHelper._privateConstructor();

  static const _keyUserSession = 'active_user_session';

  Future<void> saveSession(Map<String, dynamic> user) async {
    final jsonStr = jsonEncode(user);
    await _storage.write(key: _keyUserSession, value: jsonStr);
  }

  Future<Map<String, dynamic>?> getSession() async {
    final jsonStr = await _storage.read(key: _keyUserSession);
    if (jsonStr != null) {
      try {
        return jsonDecode(jsonStr) as Map<String, dynamic>;
      } catch (e) {
        return null;
      }
    }
    return null;
  }

  Future<void> clearSession() async {
    await _storage.delete(key: _keyUserSession);
  }
}
