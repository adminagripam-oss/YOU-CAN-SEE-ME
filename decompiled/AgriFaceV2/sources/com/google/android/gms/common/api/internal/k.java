package com.google.android.gms.common.api.internal;

import android.app.Activity;
/* loaded from: classes.dex */
public final class k extends i0 {

    /* renamed from: f  reason: collision with root package name */
    private final b1.b f5044f;

    /* renamed from: g  reason: collision with root package name */
    private final b f5045g;

    k(j3.f fVar, b bVar, h3.d dVar) {
        super(fVar, dVar);
        this.f5044f = new b1.b();
        this.f5045g = bVar;
        this.f4971a.a("ConnectionlessLifecycleHelper", this);
    }

    public static void u(Activity activity, b bVar, j3.b bVar2) {
        j3.f c8 = LifecycleCallback.c(activity);
        k kVar = (k) c8.b("ConnectionlessLifecycleHelper", k.class);
        if (kVar == null) {
            kVar = new k(c8, bVar, h3.d.m());
        }
        k3.q.h(bVar2, "ApiKey cannot be null");
        kVar.f5044f.add(bVar2);
        bVar.a(kVar);
    }

    private final void v() {
        if (this.f5044f.isEmpty()) {
            return;
        }
        this.f5045g.a(this);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void h() {
        super.h();
        v();
    }

    @Override // com.google.android.gms.common.api.internal.i0, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void j() {
        super.j();
        v();
    }

    @Override // com.google.android.gms.common.api.internal.i0, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void k() {
        super.k();
        this.f5045g.b(this);
    }

    @Override // com.google.android.gms.common.api.internal.i0
    protected final void m(h3.a aVar, int i8) {
        this.f5045g.D(aVar, i8);
    }

    @Override // com.google.android.gms.common.api.internal.i0
    protected final void n() {
        this.f5045g.E();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final b1.b t() {
        return this.f5044f;
    }
}
