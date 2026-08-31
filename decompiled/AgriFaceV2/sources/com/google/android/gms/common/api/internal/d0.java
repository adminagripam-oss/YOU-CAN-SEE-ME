package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.c;
/* loaded from: classes.dex */
public final class d0 extends a0 {

    /* renamed from: c  reason: collision with root package name */
    public final c.a f5006c;

    public d0(c.a aVar, f4.k kVar) {
        super(4, kVar);
        this.f5006c = aVar;
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final /* bridge */ /* synthetic */ void d(j jVar, boolean z7) {
    }

    @Override // j3.r
    public final boolean f(q qVar) {
        j3.t tVar = (j3.t) qVar.x().get(this.f5006c);
        return tVar != null && tVar.f8238a.f();
    }

    @Override // j3.r
    public final h3.c[] g(q qVar) {
        j3.t tVar = (j3.t) qVar.x().get(this.f5006c);
        if (tVar == null) {
            return null;
        }
        return tVar.f8238a.c();
    }

    @Override // com.google.android.gms.common.api.internal.a0
    public final void h(q qVar) {
        j3.t tVar = (j3.t) qVar.x().remove(this.f5006c);
        if (tVar == null) {
            this.f4977b.e(Boolean.FALSE);
            return;
        }
        tVar.f8239b.b(qVar.v(), this.f4977b);
        tVar.f8238a.a();
    }
}
