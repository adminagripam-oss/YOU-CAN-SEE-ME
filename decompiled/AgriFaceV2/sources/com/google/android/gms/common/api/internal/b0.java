package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.c;
/* loaded from: classes.dex */
public final class b0 extends a0 {

    /* renamed from: c  reason: collision with root package name */
    public final j3.t f4997c;

    public b0(j3.t tVar, f4.k kVar) {
        super(3, kVar);
        this.f4997c = tVar;
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final /* bridge */ /* synthetic */ void d(j jVar, boolean z7) {
    }

    @Override // j3.r
    public final boolean f(q qVar) {
        return this.f4997c.f8238a.f();
    }

    @Override // j3.r
    public final h3.c[] g(q qVar) {
        return this.f4997c.f8238a.c();
    }

    @Override // com.google.android.gms.common.api.internal.a0
    public final void h(q qVar) {
        this.f4997c.f8238a.d(qVar.v(), this.f4977b);
        c.a b8 = this.f4997c.f8238a.b();
        if (b8 != null) {
            qVar.x().put(b8, this.f4997c);
        }
    }
}
