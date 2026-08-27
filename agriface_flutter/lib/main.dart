import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'providers/auth_provider.dart';
import 'services/face_recognition_service.dart';
import 'theme.dart';
import 'screens/login_screen.dart';
import 'screens/dashboard_screen.dart';
import 'screens/daftar_karyawan_screen.dart';
import 'screens/scanner_screen.dart';
import 'screens/logs_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  // Initialize Background MLKit and Vladmandic/human Webview engine
  FaceRecognitionService.instance.init();

  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => AuthProvider()),
      ],
      child: const AgriFaceApp(),
    ),
  );
}

class AgriFaceApp extends StatelessWidget {
  const AgriFaceApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'AgriFace',
      theme: AgriFaceTheme.darkTheme,
      debugShowCheckedModeBanner: false,
      home: const AuthWrapper(),
      routes: {
        '/login': (context) => const LoginScreen(),
        '/dashboard': (context) => const DashboardScreen(),
        '/karyawan': (context) => const DaftarKaryawanScreen(),
        '/absensi': (context) => const ScannerScreen(),
        '/logs': (context) => const LogsScreen(),
      },
    );
  }
}

class AuthWrapper extends StatelessWidget {
  const AuthWrapper({super.key});

  @override
  Widget build(BuildContext context) {
    final auth = context.watch<AuthProvider>();
    
    if (auth.isLoading) {
      return const Scaffold(
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              CircularProgressIndicator(color: AgriFaceTheme.accentCyan),
              SizedBox(height: 16),
              Text(
                'Memuat Sesi Keamanan...',
                style: TextStyle(color: AgriFaceTheme.textMuted),
              ),
            ],
          ),
        ),
      );
    }

    if (auth.isAuthenticated) {
      return const DashboardScreen();
    }

    return const LoginScreen();
  }
}
