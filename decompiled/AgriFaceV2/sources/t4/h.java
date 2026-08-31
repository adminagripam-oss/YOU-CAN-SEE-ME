package t4;

import com.google.crypto.tink.shaded.protobuf.x0;
import y4.y;
/* loaded from: classes.dex */
public class h implements j4.i {

    /* renamed from: a  reason: collision with root package name */
    final String f11847a;

    /* renamed from: b  reason: collision with root package name */
    final Class f11848b;

    /* renamed from: c  reason: collision with root package name */
    final y.c f11849c;

    /* renamed from: d  reason: collision with root package name */
    final x0 f11850d;

    h(String str, Class cls, y.c cVar, x0 x0Var) {
        this.f11850d = x0Var;
        this.f11847a = str;
        this.f11848b = cls;
        this.f11849c = cVar;
    }

    public static j4.i e(String str, Class cls, y.c cVar, x0 x0Var) {
        return new h(str, cls, cVar, x0Var);
    }

    @Override // j4.i
    public final Class a() {
        return this.f11848b;
    }

    @Override // j4.i
    public Object b(com.google.crypto.tink.shaded.protobuf.h hVar) {
        return r.b().a(u.c().f(d0.b(this.f11847a, hVar, this.f11849c, y4.i0.RAW, null), j4.g.a()), this.f11848b);
    }

    @Override // j4.i
    public final y4.y c(com.google.crypto.tink.shaded.protobuf.h hVar) {
        d0 d0Var = (d0) u.c().n(o.f().c(u.c().h(e0.b((y4.a0) y4.a0.c0().r(this.f11847a).s(hVar).q(y4.i0.RAW).h())), null), d0.class, j4.g.a());
        return (y4.y) y4.y.c0().r(d0Var.f()).s(d0Var.g()).q(d0Var.d()).h();
    }

    @Override // j4.i
    public final String d() {
        return this.f11847a;
    }
}
