package c2;

import androidx.webkit.internal.q0;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class e {

    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        static final e f4755a = new q0();
    }

    public static e b() {
        if (t.a("PROXY_OVERRIDE")) {
            return a.f4755a;
        }
        throw new UnsupportedOperationException("Proxy override not supported");
    }

    public abstract void a(Executor executor, Runnable runnable);

    public abstract void c(d dVar, Executor executor, Runnable runnable);
}
