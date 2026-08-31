package androidx.lifecycle;

import androidx.lifecycle.i;
/* loaded from: classes.dex */
public final class c0 implements k {

    /* renamed from: e  reason: collision with root package name */
    private final f0 f3748e;

    public c0(f0 f0Var) {
        d7.k.e(f0Var, "provider");
        this.f3748e = f0Var;
    }

    @Override // androidx.lifecycle.k
    public void f(m mVar, i.a aVar) {
        d7.k.e(mVar, "source");
        d7.k.e(aVar, "event");
        if (aVar == i.a.ON_CREATE) {
            mVar.getLifecycle().c(this);
            this.f3748e.d();
            return;
        }
        throw new IllegalStateException(("Next event must be ON_CREATE, it was " + aVar).toString());
    }
}
