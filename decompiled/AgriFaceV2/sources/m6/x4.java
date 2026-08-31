package m6;

import m6.t0;
/* loaded from: classes.dex */
public class x4 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9618a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9619b;

    /* renamed from: c  reason: collision with root package name */
    private t0.o0 f9620c;

    public x4(i6.b bVar, v4 v4Var) {
        this.f9618a = bVar;
        this.f9619b = v4Var;
        this.f9620c = new t0.o0(bVar);
    }

    public void a(androidx.lifecycle.p pVar, t0.s0 s0Var, t0.o0.a aVar) {
        if (this.f9619b.e(pVar)) {
            return;
        }
        this.f9620c.b(Long.valueOf(this.f9619b.b(pVar)), new t0.C0128t0.a().b(s0Var).a(), aVar);
    }
}
