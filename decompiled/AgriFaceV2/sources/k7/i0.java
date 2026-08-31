package k7;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
/* loaded from: classes.dex */
public final class i0 extends r0 implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* renamed from: m  reason: collision with root package name */
    public static final i0 f8737m;

    /* renamed from: n  reason: collision with root package name */
    private static final long f8738n;

    static {
        Long l8;
        i0 i0Var = new i0();
        f8737m = i0Var;
        q0.X(i0Var, false, 1, null);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l8 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l8 = 1000L;
        }
        f8738n = timeUnit.toNanos(l8.longValue());
    }

    private i0() {
    }

    private final synchronized void n0() {
        if (q0()) {
            debugStatus = 3;
            l0();
            d7.k.c(this, "null cannot be cast to non-null type java.lang.Object");
            notifyAll();
        }
    }

    private final synchronized Thread o0() {
        Thread thread;
        thread = _thread;
        if (thread == null) {
            thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
            _thread = thread;
            thread.setDaemon(true);
            thread.start();
        }
        return thread;
    }

    private final boolean p0() {
        return debugStatus == 4;
    }

    private final boolean q0() {
        int i8 = debugStatus;
        return i8 == 2 || i8 == 3;
    }

    private final synchronized boolean r0() {
        if (q0()) {
            return false;
        }
        debugStatus = 1;
        d7.k.c(this, "null cannot be cast to non-null type java.lang.Object");
        notifyAll();
        return true;
    }

    private final void s0() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // k7.s0
    protected Thread b0() {
        Thread thread = _thread;
        return thread == null ? o0() : thread;
    }

    @Override // k7.r0
    public void f0(Runnable runnable) {
        if (p0()) {
            s0();
        }
        super.f0(runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean i02;
        v1.f8794a.c(this);
        c.a();
        try {
            if (!r0()) {
                if (i02) {
                    return;
                }
                return;
            }
            long j8 = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long j02 = j0();
                if (j02 == Long.MAX_VALUE) {
                    c.a();
                    long nanoTime = System.nanoTime();
                    if (j8 == Long.MAX_VALUE) {
                        j8 = f8738n + nanoTime;
                    }
                    long j9 = j8 - nanoTime;
                    if (j9 <= 0) {
                        _thread = null;
                        n0();
                        c.a();
                        if (i0()) {
                            return;
                        }
                        b0();
                        return;
                    }
                    j02 = g7.f.c(j02, j9);
                } else {
                    j8 = Long.MAX_VALUE;
                }
                if (j02 > 0) {
                    if (q0()) {
                        _thread = null;
                        n0();
                        c.a();
                        if (i0()) {
                            return;
                        }
                        b0();
                        return;
                    }
                    c.a();
                    LockSupport.parkNanos(this, j02);
                }
            }
        } finally {
            _thread = null;
            n0();
            c.a();
            if (!i0()) {
                b0();
            }
        }
    }

    @Override // k7.r0, k7.q0
    public void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }
}
