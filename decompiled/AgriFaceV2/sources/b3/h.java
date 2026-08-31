package b3;
/* loaded from: classes.dex */
public final class h implements w2.b {

    /* loaded from: classes.dex */
    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        private static final h f4587a = new h();
    }

    public static h a() {
        return a.f4587a;
    }

    public static d c() {
        return (d) w2.d.c(e.c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // q6.a
    /* renamed from: b */
    public d get() {
        return c();
    }
}
