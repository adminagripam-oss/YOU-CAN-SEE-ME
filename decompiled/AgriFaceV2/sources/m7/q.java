package m7;

import k7.c0;
import m7.t;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class q extends e implements r {
    public q(t6.g gVar, d dVar) {
        super(gVar, dVar, true, true);
    }

    @Override // k7.a
    protected void E0(Throwable th, boolean z7) {
        if (H0().f(th) || z7) {
            return;
        }
        c0.a(getContext(), th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k7.a
    /* renamed from: I0 */
    public void F0(r6.r rVar) {
        t.a.a(H0(), null, 1, null);
    }

    @Override // k7.a, k7.l1, k7.e1
    public boolean b() {
        return super.b();
    }
}
