package r;

import b0.s0;
import q.a;
/* loaded from: classes.dex */
final class b4 extends t0 {

    /* renamed from: c  reason: collision with root package name */
    static final b4 f10402c = new b4(new v.j());

    /* renamed from: b  reason: collision with root package name */
    private final v.j f10403b;

    private b4(v.j jVar) {
        this.f10403b = jVar;
    }

    @Override // r.t0, b0.s0.b
    public void a(b0.g3 g3Var, s0.a aVar) {
        super.a(g3Var, aVar);
        if (!(g3Var instanceof b0.n1)) {
            throw new IllegalArgumentException("config is not ImageCaptureConfig");
        }
        b0.n1 n1Var = (b0.n1) g3Var;
        a.C0140a c0140a = new a.C0140a();
        if (n1Var.h0()) {
            this.f10403b.a(n1Var.a0(), c0140a);
        }
        aVar.e(c0140a.a());
    }
}
