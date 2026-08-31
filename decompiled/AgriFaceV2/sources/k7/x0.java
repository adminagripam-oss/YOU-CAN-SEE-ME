package k7;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public final class x0 extends w0 implements k0 {

    /* renamed from: h  reason: collision with root package name */
    private final Executor f8799h;

    public x0(Executor executor) {
        this.f8799h = executor;
        p7.c.a(T());
    }

    private final void S(t6.g gVar, RejectedExecutionException rejectedExecutionException) {
        i1.c(gVar, v0.a("The task was rejected", rejectedExecutionException));
    }

    @Override // k7.a0
    public void P(t6.g gVar, Runnable runnable) {
        try {
            Executor T = T();
            c.a();
            T.execute(runnable);
        } catch (RejectedExecutionException e8) {
            c.a();
            S(gVar, e8);
            n0.b().P(gVar, runnable);
        }
    }

    public Executor T() {
        return this.f8799h;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor T = T();
        ExecutorService executorService = T instanceof ExecutorService ? (ExecutorService) T : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof x0) && ((x0) obj).T() == T();
    }

    public int hashCode() {
        return System.identityHashCode(T());
    }

    @Override // k7.a0
    public String toString() {
        return T().toString();
    }
}
