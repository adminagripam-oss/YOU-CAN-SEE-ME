import 'dart:async';
import 'dart:convert';
import 'dart:math';
import 'package:flutter/material.dart';
import 'package:flutter_inappwebview/flutter_inappwebview.dart';
import 'package:google_mlkit_face_detection/google_mlkit_face_detection.dart';

class FaceRecognitionService {
  static final FaceRecognitionService instance = FaceRecognitionService._privateConstructor();

  FaceRecognitionService._privateConstructor();

  // MLKit Face Detector
  late final FaceDetector faceDetector;

  // Background headless webview for running vladmandic/human JS models
  HeadlessInAppWebView? _headlessWebView;
  InAppWebViewController? _webViewController;
  
  final Completer<void> _engineReadyCompleter = Completer<void>();
  bool _isEngineReady = false;

  Future<void> get engineReady => _engineReadyCompleter.future;
  bool get isEngineReady => _isEngineReady;

  void init() {
    // 1. Initialize MLKit Face Detector
    faceDetector = FaceDetector(
      options: FaceDetectorOptions(
        enableContours: true,
        enableLandmarks: true,
        enableClassification: true, // Required for EAR blink detection
        minFaceSize: 0.15,
        performanceMode: FaceDetectorMode.accurate,
      ),
    );

    // 2. Initialize Headless InAppWebView
    _headlessWebView = HeadlessInAppWebView(
      initialUrlRequest: URLRequest(
        url: WebUri('file:///android_asset/flutter_assets/assets/html/index.html'),
      ),
      initialSettings: InAppWebViewSettings(
        allowFileAccessFromFileURLs: true,
        allowUniversalAccessFromFileURLs: true,
        javaScriptEnabled: true,
        domStorageEnabled: true,
      ),
      onWebViewCreated: (controller) {
        _webViewController = controller;
        
        // Add Javascript Handler for engine ready callback
        controller.addJavaScriptHandler(
          handlerName: 'onEngineReady',
          callback: (args) {
            debugPrint('[FaceRecognitionService] Background biometrics JS engine is READY.');
            _isEngineReady = true;
            if (!_engineReadyCompleter.isCompleted) {
              _engineReadyCompleter.complete();
            }
          },
        );
      },
      onConsoleMessage: (controller, consoleMessage) {
        debugPrint('[JS Console] ${consoleMessage.messageLevel}: ${consoleMessage.message}');
      },
    );

    // Run the headless webview
    _headlessWebView?.run();
  }

  Future<List<double>?> extractEmbedding(String base64Image) async {
    if (!_isEngineReady) {
      debugPrint('[FaceRecognitionService] Warning: JS engine not ready yet, waiting...');
      await engineReady;
    }

    if (_webViewController == null) {
      debugPrint('[FaceRecognitionService] Error: WebViewController is null');
      return null;
    }

    try {
      // Evaluate JavaScript method exposed on window in index.html
      final res = await _webViewController!.callAsyncJavaScript(
        functionBody: "return await window.extractEmbedding(val);",
        arguments: {'val': base64Image},
      );

      if (res != null && res.value != null) {
        final List<dynamic> list = jsonDecode(res.value.toString());
        return list.map((item) => double.parse(item.toString())).toList();
      }
    } catch (e) {
      debugPrint('[FaceRecognitionService extractEmbedding Error]: $e');
    }
    return null;
  }

  double calculateCosineSimilarity(List<double> vecA, List<double> vecB) {
    if (vecA.length != vecB.length || vecA.isEmpty) return 0.0;

    double dotProduct = 0.0;
    double normA = 0.0;
    double normB = 0.0;

    for (int i = 0; i < vecA.length; i++) {
      final a = vecA[i];
      final b = vecB[i];
      dotProduct += a * b;
      normA += a * a;
      normB += b * b;
    }

    if (normA == 0.0 || normB == 0.0) return 0.0;
    return dotProduct / (sqrt(normA) * sqrt(normB));
  }

  void dispose() {
    faceDetector.close();
    _headlessWebView?.dispose();
  }
}
