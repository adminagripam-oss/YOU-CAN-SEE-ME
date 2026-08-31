package t;

import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import b0.u0;
import q.a;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final b f11604a = new b();

    private b() {
    }

    public static final void a(a.C0140a c0140a, u0.c cVar) {
        CaptureRequest.Key key;
        d7.k.e(c0140a, "options");
        d7.k.e(cVar, "priority");
        if (Build.VERSION.SDK_INT >= 34) {
            key = CaptureRequest.CONTROL_SETTINGS_OVERRIDE;
            c0140a.g(key, 1, cVar);
        }
    }
}
