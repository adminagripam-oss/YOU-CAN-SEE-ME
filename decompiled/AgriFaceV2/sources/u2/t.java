package u2;
/* loaded from: classes.dex */
public final class t implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f12093a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f12094b;

    /* renamed from: c  reason: collision with root package name */
    private final q6.a f12095c;

    /* renamed from: d  reason: collision with root package name */
    private final q6.a f12096d;

    /* renamed from: e  reason: collision with root package name */
    private final q6.a f12097e;

    public t(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4, q6.a aVar5) {
        this.f12093a = aVar;
        this.f12094b = aVar2;
        this.f12095c = aVar3;
        this.f12096d = aVar4;
        this.f12097e = aVar5;
    }

    public static t a(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4, q6.a aVar5) {
        return new t(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static r c(d3.a aVar, d3.a aVar2, z2.e eVar, a3.j jVar, a3.n nVar) {
        return new r(aVar, aVar2, eVar, jVar, nVar);
    }

    @Override // q6.a
    /* renamed from: b */
    public r get() {
        return c((d3.a) this.f12093a.get(), (d3.a) this.f12094b.get(), (z2.e) this.f12095c.get(), (a3.j) this.f12096d.get(), (a3.n) this.f12097e.get());
    }
}
