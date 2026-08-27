import 'dart:convert';
import 'package:camera/camera.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_mlkit_face_detection/google_mlkit_face_detection.dart';
import 'package:provider/provider.dart';
import '../providers/auth_provider.dart';
import '../services/database_helper.dart';
import '../services/face_recognition_service.dart';
import '../theme.dart';

class ScannerScreen extends StatefulWidget {
  const ScannerScreen({super.key});

  @override
  State<ScannerScreen> createState() => _ScannerScreenState();
}

class _ScannerScreenState extends State<ScannerScreen> with WidgetsBindingObserver {
  CameraController? _cameraController;
  bool _isCameraInitialized = false;
  bool _isProcessingFrame = false;
  
  // Biometric challenge states
  String _statusText = 'Arahkan wajah Anda ke kamera...';
  bool _blinkDetected = false;
  bool _faceMatchingSuccess = false;
  String? _matchedEmployeeName;
  double _matchedSimilarity = 0.0;
  
  String _attendanceType = 'CHECK_IN'; // CHECK_IN or CHECK_OUT

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
    _initializeCamera();
  }

  @override
  void dispose() {
    WidgetsBinding.instance.removeObserver(this);
    _cameraController?.dispose();
    super.dispose();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (_cameraController == null || !_cameraController!.value.isInitialized) return;

    if (state == AppLifecycleState.inactive || state == AppLifecycleState.paused) {
      _cameraController?.dispose();
      setState(() => _isCameraInitialized = false);
    } else if (state == AppLifecycleState.resumed) {
      _initializeCamera();
    }
  }

  Future<void> _initializeCamera() async {
    try {
      final cameras = await availableCameras();
      if (cameras.isEmpty) {
        setState(() => _statusText = 'Kamera tidak ditemukan.');
        return;
      }

      // Use front-facing camera for biometric authentication
      final frontCamera = cameras.firstWhere(
        (c) => c.lensDirection == CameraLensDirection.front,
        orElse: () => cameras.first,
      );

      _cameraController = CameraController(
        frontCamera,
        ResolutionPreset.medium,
        enableAudio: false,
        imageFormatGroup: ImageFormatGroup.yuv420,
      );

      await _cameraController!.initialize();
      if (!mounted) return;

      setState(() {
        _isCameraInitialized = true;
        _statusText = 'Silakan kedipkan mata Anda (Liveness Check)...';
      });

      // Start stream to process frames locally with MLKit
      _cameraController!.startImageStream(_processCameraFrame);
    } catch (e) {
      setState(() => _statusText = 'Error inisialisasi kamera: $e');
    }
  }

  Future<void> _processCameraFrame(CameraImage image) async {
    if (_isProcessingFrame || _faceMatchingSuccess) return;
    _isProcessingFrame = true;

    try {
      final WriteBuffer allBytes = WriteBuffer();
      for (final Plane plane in image.planes) {
        allBytes.putUint8List(plane.bytes);
      }
      final bytes = allBytes.done().buffer.asUint8List();

      final metadata = InputImageMetadata(
        size: Size(image.width.toDouble(), image.height.toDouble()),
        rotation: InputImageRotation.rotation270deg,
        format: InputImageFormat.nv21, // standard format for camera stream YUV on Android
        bytesPerRow: image.planes[0].bytesPerRow,
      );

      final inputImage = InputImage.fromBytes(
        bytes: bytes,
        metadata: metadata,
      );

      final faces = await FaceRecognitionService.instance.faceDetector.processImage(inputImage);

      if (faces.isEmpty) {
        _updateStatus('Arahkan wajah Anda ke kamera...');
      } else if (faces.length > 1) {
        _updateStatus('Harap hanya ada satu wajah di depan kamera.');
      } else {
        final face = faces.first;

        // 1. Check Liveness Challenge: Blink detection
        // If left and right eye open probability falls below 0.25, blink is registered
        final leftOpen = face.leftEyeOpenProbability ?? 1.0;
        final rightOpen = face.rightEyeOpenProbability ?? 1.0;

        if (leftOpen < 0.25 && rightOpen < 0.25) {
          if (!_blinkDetected) {
            _blinkDetected = true;
            _updateStatus('Kedip terdeteksi! Memproses verifikasi biometrik...');
            
            // Trigger face recognition matcher
            await _runFaceMatching();
          }
        } else {
          if (!_blinkDetected) {
            _updateStatus('Kedipkan mata Anda untuk validasi...');
          }
        }
      }
    } catch (e) {
      debugPrint('[MLKIT PROCESS FRAME ERROR]: $e');
    } finally {
      _isProcessingFrame = false;
    }
  }

  void _updateStatus(String text) {
    if (mounted) {
      setState(() {
        _statusText = text;
      });
    }
  }

  Future<void> _runFaceMatching() async {
    try {
      // 1. Take a snapshot for extraction
      await _cameraController?.stopImageStream();
      final imageFile = await _cameraController?.takePicture();
      if (imageFile == null) return;

      final bytes = await imageFile.readAsBytes();
      final base64Image = base64Encode(bytes);

      // 2. Extract 1024-d embedding vector via JS headless webview
      final extractedVector = await FaceRecognitionService.instance.extractEmbedding(base64Image);

      if (extractedVector == null || extractedVector.isEmpty) {
        _resetScannerWithMessage('Vektor wajah tidak valid. Silakan coba lagi.');
        return;
      }

      // 3. Load local cached descriptors from SQLite
      final db = DatabaseHelper.instance;
      final cachedDescriptors = await db.getAllMasterDescriptors();

      double maxSimilarity = 0.0;
      Map<String, dynamic>? matchedRow;

      // 4. Calculate similarity against all cached employees
      for (final row in cachedDescriptors) {
        final List<dynamic> vecList = jsonDecode(row['descriptor_json']);
        final List<double> vector = vecList.map((e) => double.parse(e.toString())).toList();

        final sim = FaceRecognitionService.instance.calculateCosineSimilarity(extractedVector, vector);
        if (sim > maxSimilarity) {
          maxSimilarity = sim;
          matchedRow = row;
        }
      }

      // Threshold match face-api 1-to-1 is usually >= 0.60
      if (maxSimilarity >= 0.62 && matchedRow != null) {
        // Matched employee found!
        final employeeId = matchedRow['employee_id'];
        final nik = matchedRow['nik'];
        final name = matchedRow['name'];

        final auth = Provider.of<AuthProvider>(context, listen: false);
        final admin = auth.user;

        // Fetch employee details from SQLite to preserve department & afdeling
        final employees = await db.getEmployees(role: admin?['role'] ?? 'estate_admin', kebun: admin?['kebun']);
        final fullEmp = employees.firstWhere((e) => e['id'] == employeeId, orElse: () => {});

        final attendanceLog = {
          'employee_id': employeeId,
          'nik': nik,
          'name': name,
          'department': fullEmp['department'] ?? '',
          'afdeling': fullEmp['afdeling'] ?? '',
          'location': 'HP Mobile (Offline Scanner)',
          'lat': 0.0, // GPS can be populated using geolocator
          'lng': 0.0,
          'status': 'VERIFIED',
          'attendance_type': _attendanceType,
          'euclidean_distance': maxSimilarity,
        };

        // Queue to unsynced local SQLite
        await db.queueOfflineAttendance(attendanceLog);
        
        // Save to local logs history
        final localLogId = 'offline_${DateTime.now().millisecondsSinceEpoch}';
        await db.saveAttendanceLog({
          'id': localLogId,
          ...attendanceLog,
          'is_synced': 0,
        });

        setState(() {
          _faceMatchingSuccess = true;
          _matchedEmployeeName = name;
          _matchedSimilarity = maxSimilarity;
        });

        _showSuccessDialog();
      } else {
        _resetScannerWithMessage('Wajah tidak dikenali (Kecocokan: ${(maxSimilarity * 100).toStringAsFixed(1)}%).');
      }
    } catch (e) {
      debugPrint('[FACE MATCHING ERROR]: $e');
      _resetScannerWithMessage('Error pemrosesan wajah: $e');
    }
  }

  void _resetScannerWithMessage(String message) {
    if (!mounted) return;
    setState(() {
      _blinkDetected = false;
      _statusText = message;
    });
    // Restart image stream
    Future.delayed(const Duration(seconds: 2), () {
      if (mounted && !_faceMatchingSuccess) {
        _cameraController?.startImageStream(_processCameraFrame);
      }
    });
  }

  void _showSuccessDialog() {
    showDialog(
      context: context,
      barrierDismissible: false,
      builder: (context) {
        return AlertDialog(
          backgroundColor: AgriFaceTheme.darkCard,
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
          title: const Row(
            children: [
              Icon(Icons.check_circle, color: AgriFaceTheme.accentEmerald),
              SizedBox(width: 8),
              Text('PRESENSI BERHASIL', style: TextStyle(fontWeight: FontWeight.bold)),
            ],
          ),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                _matchedEmployeeName ?? 'KARYAWAN',
                style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 18, color: Colors.white),
              ),
              const SizedBox(height: 8),
              Text(
                'Tipe: ${_attendanceType == 'CHECK_IN' ? 'CHECK-IN MASUK' : 'CHECK-OUT KELUAR'}',
                style: const TextStyle(color: AgriFaceTheme.accentCyan, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 4),
              Text(
                'Kecocokan Wajah: ${(_matchedSimilarity * 100).toStringAsFixed(1)}%',
                style: const TextStyle(color: AgriFaceTheme.textMuted),
              ),
              const SizedBox(height: 12),
              const Text(
                'Data absensi telah direkam secara offline di antrean handphone.',
                style: TextStyle(fontSize: 12, color: AgriFaceTheme.textMuted),
              ),
            ],
          ),
          actions: [
            ElevatedButton(
              onPressed: () {
                Navigator.pop(context);
                setState(() {
                  _faceMatchingSuccess = false;
                  _blinkDetected = false;
                  _matchedEmployeeName = null;
                  _matchedSimilarity = 0.0;
                });
                _initializeCamera();
              },
              child: const Text('KEMBALI KE SCANNER'),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Scanner Absensi Biometrik'),
        backgroundColor: AgriFaceTheme.darkBg,
      ),
      body: Column(
        children: [
          // Select Check-in / Check-out Tab
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Row(
              children: [
                Expanded(
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: _attendanceType == 'CHECK_IN'
                          ? AgriFaceTheme.accentCyan
                          : Colors.white.withOpacity(0.05),
                      foregroundColor: _attendanceType == 'CHECK_IN' ? Colors.black : Colors.white,
                    ),
                    onPressed: () => setState(() => _attendanceType = 'CHECK_IN'),
                    child: const Text('CHECK-IN (MASUK)'),
                  ),
                ),
                const SizedBox(width: 12),
                Expanded(
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: _attendanceType == 'CHECK_OUT'
                          ? AgriFaceTheme.accentCyan
                          : Colors.white.withOpacity(0.05),
                      foregroundColor: _attendanceType == 'CHECK_OUT' ? Colors.black : Colors.white,
                    ),
                    onPressed: () => setState(() => _attendanceType = 'CHECK_OUT'),
                    child: const Text('CHECK-OUT (PULANG)'),
                  ),
                ),
              ],
            ),
          ),

          // Camera View Box
          Expanded(
            child: Center(
              child: Container(
                margin: const EdgeInsets.symmetric(horizontal: 20),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(20),
                  border: Border.all(
                    color: _faceMatchingSuccess
                        ? AgriFaceTheme.accentEmerald
                        : AgriFaceTheme.accentCyan.withOpacity(0.6),
                    width: 2,
                  ),
                ),
                clipBehavior: Clip.antiAlias,
                child: AspectRatio(
                  aspectRatio: 3 / 4,
                  child: _isCameraInitialized && _cameraController != null
                      ? Stack(
                          fit: StackFit.expand,
                          children: [
                            CameraPreview(_cameraController!),
                            
                            // Futuristic neon green mesh / guidelines
                            Center(
                              child: Container(
                                width: 220,
                                height: 280,
                                decoration: BoxDecoration(
                                  shape: BoxShape.rectangle,
                                  borderRadius: BorderRadius.circular(110),
                                  border: Border.all(
                                    color: _blinkDetected
                                        ? AgriFaceTheme.accentEmerald
                                        : AgriFaceTheme.accentCyan,
                                    width: 1.5,
                                  ),
                                ),
                              ),
                            ),
                          ],
                        )
                      : const Center(
                          child: CircularProgressIndicator(color: AgriFaceTheme.accentCyan),
                        ),
                ),
              ),
            ),
          ),

          // Footer Status Banner
          Container(
            padding: const EdgeInsets.all(24),
            decoration: AgriFaceTheme.glassBox(radius: 0),
            child: Column(
              children: [
                Text(
                  _statusText,
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    color: _blinkDetected ? AgriFaceTheme.accentEmerald : Colors.white,
                    fontWeight: FontWeight.bold,
                    fontSize: 16,
                  ),
                ),
                const SizedBox(height: 6),
                const Text(
                  'Verifikasi absensi 1-to-1 luring terenkripsi.',
                  style: TextStyle(fontSize: 12, color: AgriFaceTheme.textMuted),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
