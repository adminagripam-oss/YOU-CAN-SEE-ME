package androidx.camera.core;

import b0.q1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class j extends i {

    /* loaded from: classes.dex */
    class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ n f2735a;

        a(n nVar) {
            this.f2735a = nVar;
        }

        @Override // f0.c
        public void b(Throwable th) {
            this.f2735a.close();
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r12) {
        }
    }

    @Override // androidx.camera.core.i
    n d(q1 q1Var) {
        return q1Var.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.core.i
    public void g() {
    }

    @Override // androidx.camera.core.i
    void o(n nVar) {
        f0.n.j(e(nVar), new a(nVar), e0.c.b());
    }
}
