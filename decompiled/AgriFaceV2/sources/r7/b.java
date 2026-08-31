package r7;

import java.util.concurrent.Executor;
import k7.a0;
import k7.w0;
import p7.i0;
import p7.k0;
/* loaded from: classes.dex */
public final class b extends w0 implements Executor {

    /* renamed from: h  reason: collision with root package name */
    public static final b f11401h = new b();

    /* renamed from: i  reason: collision with root package name */
    private static final a0 f11402i;

    static {
        int a8;
        int e8;
        m mVar = m.f11422g;
        a8 = g7.f.a(64, i0.a());
        e8 = k0.e("kotlinx.coroutines.io.parallelism", a8, 0, 0, 12, null);
        f11402i = mVar.R(e8);
    }

    private b() {
    }

    @Override // k7.a0
    public void P(t6.g gVar, Runnable runnable) {
        f11402i.P(gVar, runnable);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        P(t6.h.f11916e, runnable);
    }

    @Override // k7.a0
    public String toString() {
        return "Dispatchers.IO";
    }
}
