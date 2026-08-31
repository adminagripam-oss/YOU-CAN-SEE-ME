package com.google.android.gms.vision.face.mlkit;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.mlkit_vision_face_bundled.li;
import com.google.android.gms.internal.mlkit_vision_face_bundled.qi;
import com.google.android.gms.internal.mlkit_vision_face_bundled.ti;
import com.google.android.gms.vision.face.FaceDetectorV2Jni;
/* loaded from: classes.dex */
public class FaceDetectorCreator extends ti {
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ui
    public qi newFaceDetector(t3.a aVar, li liVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Context context = (Context) t3.b.f(aVar);
        b bVar = new b(context, true);
        try {
            System.loadLibrary("face_detector_v2_jni");
            bVar.b(liVar, null, SystemClock.elapsedRealtime() - elapsedRealtime);
            return new a(context, liVar, new FaceDetectorV2Jni(), bVar);
        } catch (UnsatisfiedLinkError e8) {
            Log.e("FaceDetectorCreator", "Failed to load library face_detector_v2_jni");
            bVar.b(liVar, "Failed to load library face_detector_v2_jni", SystemClock.elapsedRealtime() - elapsedRealtime);
            throw ((RemoteException) c.a("Failed to load library face_detector_v2_jni").initCause(e8));
        }
    }
}
