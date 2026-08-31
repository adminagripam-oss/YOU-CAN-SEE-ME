package com.google.mlkit.vision.face.bundled.internal;

import android.content.Context;
import com.google.android.gms.internal.mlkit_vision_face_bundled.li;
import com.google.android.gms.internal.mlkit_vision_face_bundled.qi;
import com.google.android.gms.internal.mlkit_vision_face_bundled.ti;
import com.google.android.gms.vision.face.FaceDetectorV2Jni;
import t3.b;
/* loaded from: classes.dex */
public class ThickFaceDetectorCreator extends ti {
    static {
        System.loadLibrary("face_detector_v2_jni");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ui
    public qi newFaceDetector(t3.a aVar, li liVar) {
        return new a((Context) b.f(aVar), liVar, new FaceDetectorV2Jni());
    }
}
