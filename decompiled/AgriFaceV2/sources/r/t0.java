package r;

import b0.s0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class t0 implements s0.b {

    /* renamed from: a  reason: collision with root package name */
    static final t0 f10789a = new t0();

    @Override // b0.s0.b
    public void a(b0.g3 g3Var, s0.a aVar) {
        b0.s0 K = g3Var.K(null);
        b0.u0 a02 = b0.f2.a0();
        int k8 = b0.s0.b().k();
        if (K != null) {
            k8 = K.k();
            aVar.a(K.c());
            a02 = K.g();
        }
        aVar.s(a02);
        q.a aVar2 = new q.a(g3Var);
        aVar.v(aVar2.b0(k8));
        aVar.c(p2.e(aVar2.e0(s0.c())));
        aVar.e(aVar2.a0());
    }
}
