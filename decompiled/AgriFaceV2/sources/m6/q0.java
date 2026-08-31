package m6;

import m6.t0;
/* loaded from: classes.dex */
public class q0 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9433a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9434b;

    /* renamed from: c  reason: collision with root package name */
    private t0.f0 f9435c;

    public q0(i6.b bVar, v4 v4Var) {
        this.f9433a = bVar;
        this.f9434b = v4Var;
        this.f9435c = new t0.f0(bVar);
    }

    public void a(y.e0 e0Var, t0.f0.a aVar) {
        if (this.f9434b.e(e0Var)) {
            return;
        }
        this.f9435c.b(Long.valueOf(this.f9434b.b(e0Var)), aVar);
    }
}
