package io.flutter.plugins;

import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import io.flutter.embedding.engine.a;
import m6.n;
import o6.i;
import t5.d;
import u5.w;
import w5.d0;
import z5.b;
/* loaded from: classes.dex */
public final class GeneratedPluginRegistrant {
    private static final String TAG = "GeneratedPluginRegistrant";

    public static void registerWith(a aVar) {
        try {
            aVar.r().e(new n());
        } catch (Exception e8) {
            b.c(TAG, "Error registering plugin camera_android_camerax, io.flutter.plugins.camerax.CameraAndroidCameraxPlugin", e8);
        }
        try {
            aVar.r().e(new InAppWebViewFlutterPlugin());
        } catch (Exception e9) {
            b.c(TAG, "Error registering plugin flutter_inappwebview_android, com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin", e9);
        }
        try {
            aVar.r().e(new n6.a());
        } catch (Exception e10) {
            b.c(TAG, "Error registering plugin flutter_plugin_android_lifecycle, io.flutter.plugins.flutter_plugin_android_lifecycle.FlutterAndroidLifecyclePlugin", e10);
        }
        try {
            aVar.r().e(new w());
        } catch (Exception e11) {
            b.c(TAG, "Error registering plugin flutter_secure_storage, com.it_nomads.fluttersecurestorage.FlutterSecureStoragePlugin", e11);
        }
        try {
            aVar.r().e(new com.baseflow.geolocator.a());
        } catch (Exception e12) {
            b.c(TAG, "Error registering plugin geolocator_android, com.baseflow.geolocator.GeolocatorPlugin", e12);
        }
        try {
            aVar.r().e(new s5.a());
        } catch (Exception e13) {
            b.c(TAG, "Error registering plugin google_mlkit_commons, com.google_mlkit_commons.GoogleMlKitCommonsPlugin", e13);
        }
        try {
            aVar.r().e(new d());
        } catch (Exception e14) {
            b.c(TAG, "Error registering plugin google_mlkit_face_detection, com.google_mlkit_face_detection.GoogleMlKitFaceDetectionPlugin", e14);
        }
        try {
            aVar.r().e(new i());
        } catch (Exception e15) {
            b.c(TAG, "Error registering plugin path_provider_android, io.flutter.plugins.pathprovider.PathProviderPlugin", e15);
        }
        try {
            aVar.r().e(new d0());
        } catch (Exception e16) {
            b.c(TAG, "Error registering plugin sqflite_android, com.tekartik.sqflite.SqflitePlugin", e16);
        }
    }
}
