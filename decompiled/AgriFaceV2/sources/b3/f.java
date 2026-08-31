package b3;
/* loaded from: classes.dex */
public final class f implements w2.b {

    /* loaded from: classes.dex */
    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        private static final f f4583a = new f();
    }

    public static f a() {
        return a.f4583a;
    }

    public static String b() {
        return (String) w2.d.c(e.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // q6.a
    /* renamed from: c */
    public String get() {
        return b();
    }
}
