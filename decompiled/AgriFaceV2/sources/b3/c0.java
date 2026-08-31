package b3;
/* loaded from: classes.dex */
public final class c0 implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f4576a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f4577b;

    /* renamed from: c  reason: collision with root package name */
    private final q6.a f4578c;

    /* renamed from: d  reason: collision with root package name */
    private final q6.a f4579d;

    public c0(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4) {
        this.f4576a = aVar;
        this.f4577b = aVar2;
        this.f4578c = aVar3;
        this.f4579d = aVar4;
    }

    public static c0 a(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4) {
        return new c0(aVar, aVar2, aVar3, aVar4);
    }

    public static b0 c(d3.a aVar, d3.a aVar2, Object obj, Object obj2) {
        return new b0(aVar, aVar2, (d) obj, (h0) obj2);
    }

    @Override // q6.a
    /* renamed from: b */
    public b0 get() {
        return c((d3.a) this.f4576a.get(), (d3.a) this.f4577b.get(), this.f4578c.get(), this.f4579d.get());
    }
}
