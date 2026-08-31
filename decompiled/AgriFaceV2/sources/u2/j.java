package u2;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class j implements w2.b {

    /* loaded from: classes.dex */
    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        private static final j f12076a = new j();
    }

    public static j a() {
        return a.f12076a;
    }

    public static Executor b() {
        return (Executor) w2.d.c(i.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // q6.a
    /* renamed from: c */
    public Executor get() {
        return b();
    }
}
