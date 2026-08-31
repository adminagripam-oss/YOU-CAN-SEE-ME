package g4;

import com.google.android.gms.internal.mlkit_vision_face_bundled.ac;
import com.google.android.gms.internal.mlkit_vision_face_bundled.li;
import com.google.android.gms.internal.mlkit_vision_face_bundled.tc;
import com.google.android.gms.internal.mlkit_vision_face_bundled.uc;
import com.google.android.gms.internal.mlkit_vision_face_bundled.vc;
import com.google.android.gms.internal.mlkit_vision_face_bundled.wb;
import com.google.android.gms.internal.mlkit_vision_face_bundled.xb;
import com.google.android.gms.internal.mlkit_vision_face_bundled.xh;
import com.google.android.gms.internal.mlkit_vision_face_bundled.yb;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zb;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final xh f7125a;

    public b(xh xhVar, boolean z7) {
        this.f7125a = xhVar;
    }

    public final void a(li liVar, vc vcVar, uc ucVar) {
        wb wbVar = new wb();
        wbVar.a(liVar.b() == 2 ? xb.ALL_CLASSIFICATIONS : xb.NO_CLASSIFICATIONS);
        wbVar.d(liVar.d() == 2 ? zb.ALL_LANDMARKS : zb.NO_LANDMARKS);
        wbVar.b(liVar.c() == 2 ? yb.ALL_CONTOURS : yb.NO_CONTOURS);
        wbVar.f(liVar.e() == 2 ? ac.ACCURATE : ac.FAST);
        wbVar.e(Float.valueOf(liVar.a()));
        wbVar.c(Boolean.valueOf(liVar.f()));
        this.f7125a.c(new a(tc.TYPE_THICK, wbVar.k(), ucVar, vcVar == vc.OPTIONAL_MODULE_FACE_DETECTION_CREATE ? 1 : 0), vcVar);
    }
}
