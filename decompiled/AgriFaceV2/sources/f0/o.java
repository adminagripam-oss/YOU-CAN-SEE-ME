package f0;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import y.f1;
/* loaded from: classes.dex */
abstract class o implements i4.d {

    /* loaded from: classes.dex */
    static class a extends o {

        /* renamed from: e  reason: collision with root package name */
        private final Throwable f6663e;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(Throwable th) {
            this.f6663e = th;
        }

        @Override // f0.o, java.util.concurrent.Future
        public Object get() {
            throw new ExecutionException(this.f6663e);
        }

        public String toString() {
            return super.toString() + "[status=FAILURE, cause=[" + this.f6663e + "]]";
        }
    }

    /* loaded from: classes.dex */
    static final class b extends a implements ScheduledFuture {
        /* JADX INFO: Access modifiers changed from: package-private */
        public b(Throwable th) {
            super(th);
        }

        @Override // java.lang.Comparable
        /* renamed from: f */
        public int compareTo(Delayed delayed) {
            return -1;
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit timeUnit) {
            return 0L;
        }
    }

    /* loaded from: classes.dex */
    static final class c extends o {

        /* renamed from: f  reason: collision with root package name */
        static final o f6664f = new c(null);

        /* renamed from: e  reason: collision with root package name */
        private final Object f6665e;

        /* JADX INFO: Access modifiers changed from: package-private */
        public c(Object obj) {
            this.f6665e = obj;
        }

        @Override // f0.o, java.util.concurrent.Future
        public Object get() {
            return this.f6665e;
        }

        public String toString() {
            return super.toString() + "[status=SUCCESS, result=[" + this.f6665e + "]]";
        }
    }

    o() {
    }

    public static i4.d e() {
        return c.f6664f;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z7) {
        return false;
    }

    @Override // i4.d
    public void d(Runnable runnable, Executor executor) {
        g1.e.j(runnable);
        g1.e.j(executor);
        try {
            executor.execute(runnable);
        } catch (RuntimeException e8) {
            f1.d("ImmediateFuture", "Experienced RuntimeException while attempting to notify " + runnable + " on Executor " + executor, e8);
        }
    }

    @Override // java.util.concurrent.Future
    public abstract Object get();

    @Override // java.util.concurrent.Future
    public Object get(long j8, TimeUnit timeUnit) {
        g1.e.j(timeUnit);
        return get();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }
}
