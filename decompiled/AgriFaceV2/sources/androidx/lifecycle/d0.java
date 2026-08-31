package androidx.lifecycle;

import androidx.lifecycle.i;
/* loaded from: classes.dex */
public final class d0 implements k {

    /* renamed from: e  reason: collision with root package name */
    private final String f3752e;

    /* renamed from: f  reason: collision with root package name */
    private final b0 f3753f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f3754g;

    public d0(String str, b0 b0Var) {
        d7.k.e(str, "key");
        d7.k.e(b0Var, "handle");
        this.f3752e = str;
        this.f3753f = b0Var;
    }

    public final void a(w1.d dVar, i iVar) {
        d7.k.e(dVar, "registry");
        d7.k.e(iVar, "lifecycle");
        if (!(!this.f3754g)) {
            throw new IllegalStateException("Already attached to lifecycleOwner".toString());
        }
        this.f3754g = true;
        iVar.a(this);
        dVar.h(this.f3752e, this.f3753f.c());
    }

    public final b0 b() {
        return this.f3753f;
    }

    public final boolean c() {
        return this.f3754g;
    }

    @Override // androidx.lifecycle.k
    public void f(m mVar, i.a aVar) {
        d7.k.e(mVar, "source");
        d7.k.e(aVar, "event");
        if (aVar == i.a.ON_DESTROY) {
            this.f3754g = false;
            mVar.getLifecycle().c(this);
        }
    }
}
