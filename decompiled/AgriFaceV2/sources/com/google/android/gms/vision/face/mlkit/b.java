package com.google.android.gms.vision.face.mlkit;

import android.content.Context;
import com.google.android.gms.internal.mlkit_vision_face_bundled.hi;
import com.google.android.gms.internal.mlkit_vision_face_bundled.li;
import com.google.android.gms.internal.mlkit_vision_face_bundled.rh;
import com.google.android.gms.internal.mlkit_vision_face_bundled.th;
import com.google.android.gms.internal.mlkit_vision_face_bundled.uc;
import com.google.android.gms.internal.mlkit_vision_face_bundled.vc;
import com.google.android.gms.internal.mlkit_vision_face_bundled.xh;
import java.util.List;
import m5.m;
/* loaded from: classes.dex */
final class b {

    /* renamed from: a  reason: collision with root package name */
    private final g4.b f6031a;

    public b(Context context, boolean z7) {
        rh c8 = rh.d("optional-module-face").c();
        this.f6031a = new g4.b(new xh(context, new m(context), new th(context, c8), c8.b()), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(li liVar, hi hiVar, List list, long j8) {
        this.f6031a.a(liVar, vc.OPTIONAL_MODULE_FACE_DETECTION_INFERENCE, uc.NO_ERROR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(li liVar, String str, long j8) {
        this.f6031a.a(liVar, vc.OPTIONAL_MODULE_FACE_DETECTION_CREATE, str != null ? uc.OPTIONAL_MODULE_CREATE_ERROR : uc.NO_ERROR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(li liVar) {
        this.f6031a.a(liVar, vc.OPTIONAL_MODULE_FACE_DETECTION_INIT, uc.NO_ERROR);
    }
}
