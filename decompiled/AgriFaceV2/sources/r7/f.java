package r7;

import k7.w0;
/* loaded from: classes.dex */
public abstract class f extends w0 {

    /* renamed from: h  reason: collision with root package name */
    private final int f11405h;

    /* renamed from: i  reason: collision with root package name */
    private final int f11406i;

    /* renamed from: j  reason: collision with root package name */
    private final long f11407j;

    /* renamed from: k  reason: collision with root package name */
    private final String f11408k;

    /* renamed from: l  reason: collision with root package name */
    private a f11409l = S();

    public f(int i8, int i9, long j8, String str) {
        this.f11405h = i8;
        this.f11406i = i9;
        this.f11407j = j8;
        this.f11408k = str;
    }

    private final a S() {
        return new a(this.f11405h, this.f11406i, this.f11407j, this.f11408k);
    }

    @Override // k7.a0
    public void P(t6.g gVar, Runnable runnable) {
        a.q(this.f11409l, runnable, null, false, 6, null);
    }

    public final void T(Runnable runnable, i iVar, boolean z7) {
        this.f11409l.n(runnable, iVar, z7);
    }
}
