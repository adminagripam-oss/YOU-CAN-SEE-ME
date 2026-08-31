package x3;

import com.google.android.gms.common.api.internal.c;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h implements j3.i, z {

    /* renamed from: a  reason: collision with root package name */
    private final g f12669a;

    /* renamed from: b  reason: collision with root package name */
    private com.google.android.gms.common.api.internal.c f12670b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f12671c = true;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ i f12672d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(i iVar, com.google.android.gms.common.api.internal.c cVar, g gVar) {
        this.f12672d = iVar;
        this.f12670b = cVar;
        this.f12669a = gVar;
    }

    @Override // x3.z
    public final void a() {
        c.a b8;
        synchronized (this) {
            this.f12671c = false;
            b8 = this.f12670b.b();
        }
        if (b8 != null) {
            this.f12672d.k(b8, 2441);
        }
    }

    @Override // x3.z
    public final synchronized void b(com.google.android.gms.common.api.internal.c cVar) {
        com.google.android.gms.common.api.internal.c cVar2 = this.f12670b;
        if (cVar2 != cVar) {
            cVar2.a();
            this.f12670b = cVar;
        }
    }

    @Override // j3.i
    public final /* bridge */ /* synthetic */ void c(Object obj, Object obj2) {
        c.a b8;
        boolean z7;
        e0 e0Var = (e0) obj;
        f4.k kVar = (f4.k) obj2;
        synchronized (this) {
            b8 = this.f12670b.b();
            z7 = this.f12671c;
            this.f12670b.a();
        }
        if (b8 == null) {
            kVar.c(Boolean.FALSE);
        } else {
            this.f12669a.a(e0Var, b8, z7, kVar);
        }
    }

    @Override // x3.z
    public final synchronized com.google.android.gms.common.api.internal.c zza() {
        return this.f12670b;
    }
}
