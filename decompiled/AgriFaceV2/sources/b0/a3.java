package b0;

import y.s1;
/* loaded from: classes.dex */
public final class a3 implements y.s1 {

    /* renamed from: d  reason: collision with root package name */
    private final long f4128d;

    /* renamed from: e  reason: collision with root package name */
    private final y.s1 f4129e;

    public a3(long j8, y.s1 s1Var) {
        g1.e.b(j8 >= 0, "Timeout must be non-negative.");
        this.f4128d = j8;
        this.f4129e = s1Var;
    }

    @Override // y.s1
    public s1.c b(s1.b bVar) {
        s1.c b8 = this.f4129e.b(bVar);
        return (f() <= 0 || bVar.c() < f() - b8.b()) ? b8 : s1.c.f12907d;
    }

    @Override // y.s1
    public long f() {
        return this.f4128d;
    }
}
