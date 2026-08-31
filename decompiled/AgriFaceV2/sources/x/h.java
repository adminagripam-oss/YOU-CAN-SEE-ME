package x;

import android.hardware.camera2.CameraCharacteristics;
import b0.g0;
import r.r0;
import y.p;
/* loaded from: classes.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    private r0 f12600a;

    public h(r0 r0Var) {
        this.f12600a = r0Var;
    }

    public static h a(p pVar) {
        g0 a8 = ((g0) pVar).a();
        g1.e.b(a8 instanceof r0, "CameraInfo doesn't contain Camera2 implementation.");
        return ((r0) a8).p();
    }

    public Object b(CameraCharacteristics.Key key) {
        return this.f12600a.q().a(key);
    }

    public String c() {
        return this.f12600a.g();
    }
}
