package com.google.android.gms.vision.face;

import android.content.res.AssetManager;
import android.util.Log;
import com.google.android.gms.internal.mlkit_vision_face_bundled.ak;
import com.google.android.gms.internal.mlkit_vision_face_bundled.h6;
import com.google.android.gms.internal.mlkit_vision_face_bundled.i;
import com.google.android.gms.internal.mlkit_vision_face_bundled.om;
import com.google.android.gms.internal.mlkit_vision_face_bundled.rk;
import com.google.android.gms.internal.mlkit_vision_face_bundled.wk;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class FaceDetectorV2Jni {

    /* renamed from: a  reason: collision with root package name */
    private final ak f6022a;

    public FaceDetectorV2Jni() {
        ak a8 = ak.a();
        this.f6022a = a8;
        a8.c(i.f5369a);
    }

    private native void closeDetectorJni(long j8);

    private native byte[] detectFacesImageByteArrayJni(long j8, byte[] bArr, byte[] bArr2);

    private native byte[] detectFacesImageByteArrayMultiPlanesJni(long j8, byte[] bArr, byte[] bArr2, byte[] bArr3, int i8, int i9, int i10, int i11, int i12, int i13, byte[] bArr4);

    private native byte[] detectFacesImageByteBufferJni(long j8, ByteBuffer byteBuffer, byte[] bArr);

    private native byte[] detectFacesImageByteBufferMultiPlanesJni(long j8, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i8, int i9, int i10, int i11, int i12, int i13, byte[] bArr);

    private native long initDetectorJni(byte[] bArr, AssetManager assetManager);

    public final long a(om omVar, AssetManager assetManager) {
        Log.v("FaceDetectorV2Jni", "initialize.start()");
        long initDetectorJni = initDetectorJni(omVar.h(), assetManager);
        Log.v("FaceDetectorV2Jni", "initialize.end()");
        return initDetectorJni;
    }

    public final rk b(long j8, byte[] bArr, h6 h6Var) {
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteArray.start()");
        rk rkVar = null;
        try {
            byte[] detectFacesImageByteArrayJni = detectFacesImageByteArrayJni(j8, bArr, h6Var.h());
            if (detectFacesImageByteArrayJni != null && detectFacesImageByteArrayJni.length > 0) {
                rkVar = rk.B(detectFacesImageByteArrayJni, this.f6022a);
            }
        } catch (wk e8) {
            Log.e("FaceDetectorV2Jni", "detectFacesImageByteArray failed to parse result: ".concat(String.valueOf(e8.getMessage())));
        }
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteArray.end()");
        return rkVar;
    }

    public final rk c(long j8, byte[] bArr, byte[] bArr2, byte[] bArr3, int i8, int i9, int i10, int i11, int i12, int i13, h6 h6Var) {
        byte[] detectFacesImageByteArrayMultiPlanesJni;
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteArrayMultiPlanes.start()");
        rk rkVar = null;
        try {
            detectFacesImageByteArrayMultiPlanesJni = detectFacesImageByteArrayMultiPlanesJni(j8, bArr, bArr2, bArr3, i8, i9, i10, i11, i12, i13, h6Var.h());
        } catch (wk e8) {
            e = e8;
        }
        if (detectFacesImageByteArrayMultiPlanesJni != null) {
            if (detectFacesImageByteArrayMultiPlanesJni.length > 0) {
                try {
                    rkVar = rk.B(detectFacesImageByteArrayMultiPlanesJni, this.f6022a);
                } catch (wk e9) {
                    e = e9;
                    Log.e("FaceDetectorV2Jni", "detectFacesImageByteArrayMultiPlanes failed to parse result: ".concat(String.valueOf(e.getMessage())));
                    Log.v("FaceDetectorV2Jni", "%s detectFacesImageByteArrayMultiPlanes.end()");
                    return rkVar;
                }
                Log.v("FaceDetectorV2Jni", "%s detectFacesImageByteArrayMultiPlanes.end()");
                return rkVar;
            }
        }
        Log.v("FaceDetectorV2Jni", "%s detectFacesImageByteArrayMultiPlanes.end()");
        return rkVar;
    }

    public final rk d(long j8, ByteBuffer byteBuffer, h6 h6Var) {
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteBuffer.start()");
        rk rkVar = null;
        try {
            byte[] detectFacesImageByteBufferJni = detectFacesImageByteBufferJni(j8, byteBuffer, h6Var.h());
            if (detectFacesImageByteBufferJni != null && detectFacesImageByteBufferJni.length > 0) {
                rkVar = rk.B(detectFacesImageByteBufferJni, this.f6022a);
            }
        } catch (wk e8) {
            Log.e("FaceDetectorV2Jni", "detectFacesImageByteBuffer failed to parse result: ".concat(String.valueOf(e8.getMessage())));
        }
        Log.v("FaceDetectorV2Jni", "%s detectFacesImageByteBuffer.end()");
        return rkVar;
    }

    public final rk e(long j8, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i8, int i9, int i10, int i11, int i12, int i13, h6 h6Var) {
        byte[] detectFacesImageByteBufferMultiPlanesJni;
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteBufferMultiPlanes.start()");
        rk rkVar = null;
        try {
            detectFacesImageByteBufferMultiPlanesJni = detectFacesImageByteBufferMultiPlanesJni(j8, byteBuffer, byteBuffer2, byteBuffer3, i8, i9, i10, i11, i12, i13, h6Var.h());
        } catch (wk e8) {
            e = e8;
        }
        if (detectFacesImageByteBufferMultiPlanesJni != null) {
            if (detectFacesImageByteBufferMultiPlanesJni.length > 0) {
                try {
                    rkVar = rk.B(detectFacesImageByteBufferMultiPlanesJni, this.f6022a);
                } catch (wk e9) {
                    e = e9;
                    Log.e("FaceDetectorV2Jni", "detectFacesImageByteBufferMultiPlanes failed to parse result: ".concat(String.valueOf(e.getMessage())));
                    Log.v("FaceDetectorV2Jni", "detectFacesImageByteBuffer.end()");
                    return rkVar;
                }
                Log.v("FaceDetectorV2Jni", "detectFacesImageByteBuffer.end()");
                return rkVar;
            }
        }
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteBuffer.end()");
        return rkVar;
    }

    public final void f(long j8) {
        Log.v("FaceDetectorV2Jni", "closeDetector.start()");
        closeDetectorJni(j8);
        Log.v("FaceDetectorV2Jni", "closeDetector.end()");
    }
}
