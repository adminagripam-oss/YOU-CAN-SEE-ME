package z2;
/* loaded from: classes.dex */
public final class g implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f13396a;

    public g(q6.a aVar) {
        this.f13396a = aVar;
    }

    public static a3.d a(d3.a aVar) {
        return (a3.d) w2.d.c(f.a(aVar), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static g b(q6.a aVar) {
        return new g(aVar);
    }

    @Override // q6.a
    /* renamed from: c */
    public a3.d get() {
        return a((d3.a) this.f13396a.get());
    }
}
