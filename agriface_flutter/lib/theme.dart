import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class AgriFaceTheme {
  // Theme Color Palette aligned with Capacitor WebView Dark Mode
  static const Color darkBg = Color(0xFF0F0F0F); // Pitch Black
  static const Color darkCard = Color(0xFF1E1E1E); // Dark Gray
  static const Color accentCyan = Color(0xFF6366F1); // Indigo (Primary Accent)
  static const Color accentSecondary = Color(0xFF38BDF8); // Light Cyan
  static const Color accentEmerald = Color(0xFF10B981);
  static const Color accentRed = Color(0xFFEF4444);
  static const Color textMain = Color(0xFFFFFFFF);
  static const Color textMuted = Color(0xFFCBD5E1);

  static ThemeData get darkTheme {
    return ThemeData(
      brightness: Brightness.dark,
      scaffoldBackgroundColor: darkBg,
      primaryColor: accentCyan,
      colorScheme: const ColorScheme.dark(
        primary: accentCyan,
        secondary: accentEmerald,
        surface: darkCard,
        error: accentRed,
      ),
      textTheme: GoogleFonts.interTextTheme(ThemeData.dark().textTheme).copyWith(
        bodyLarge: const TextStyle(color: textMain),
        bodyMedium: const TextStyle(color: textMuted),
      ),
      cardTheme: CardTheme(
        color: darkCard.withOpacity(0.85),
        elevation: 8,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(16),
          side: BorderSide(color: Colors.white.withOpacity(0.08), width: 1),
        ),
      ),
      inputDecorationTheme: InputDecorationTheme(
        filled: true,
        fillColor: Colors.white.withOpacity(0.04),
        hintStyle: const TextStyle(color: textMuted),
        labelStyle: const TextStyle(color: accentCyan),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: Colors.white.withOpacity(0.1)),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: const BorderSide(color: accentCyan, width: 1.5),
        ),
        errorBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: const BorderSide(color: accentRed, width: 1),
        ),
      ),
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          backgroundColor: accentCyan,
          foregroundColor: Colors.black,
          padding: const EdgeInsets.symmetric(vertical: 16),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
          textStyle: const TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 16,
          ),
        ),
      ),
    );
  }

  static BoxDecoration glassBox({double radius = 16}) {
    return BoxDecoration(
      color: darkCard.withOpacity(0.75),
      borderRadius: BorderRadius.circular(radius),
      border: Border.all(
        color: Colors.white.withOpacity(0.08),
        width: 1,
      ),
      boxShadow: [
        BoxShadow(
          color: Colors.black.withOpacity(0.25),
          blurRadius: 16,
          offset: const Offset(0, 8),
        ),
      ],
    );
  }
}
