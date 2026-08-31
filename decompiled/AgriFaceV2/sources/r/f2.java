package r;

import android.util.Size;
import b0.q2;
/* loaded from: classes.dex */
final class f2 implements q2.e {

    /* renamed from: a  reason: collision with root package name */
    static final f2 f10466a = new f2();

    @Override // b0.q2.e
    public void a(Size size, b0.g3 g3Var, q2.b bVar) {
        b0.q2 C = g3Var.C(null);
        b0.u0 a02 = b0.f2.a0();
        int o8 = b0.q2.b().o();
        if (C != null) {
            o8 = C.o();
            bVar.b(C.c());
            bVar.d(C.k());
            bVar.c(C.i());
            a02 = C.f();
        }
        bVar.v(a02);
        if (g3Var instanceof b0.g2) {
            v.o.b(size, bVar);
        }
        q.a aVar = new q.a(g3Var);
        bVar.z(aVar.b0(o8));
        bVar.f(aVar.c0(j2.b()));
        bVar.k(aVar.f0(i2.b()));
        bVar.e(p2.e(aVar.e0(s0.c())));
        bVar.A(g3Var.n());
        bVar.y(g3Var.D());
        b0.a2 d02 = b0.a2.d0();
        d02.Y(q.a.P, aVar.d0(null));
        d02.Y(q.a.K, Long.valueOf(aVar.g0(-1L)));
        bVar.g(d02);
        bVar.g(aVar.a0());
    }
}
