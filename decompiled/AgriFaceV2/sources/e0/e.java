package e0;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.concurrent.futures.c;
import f0.n;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class e extends AbstractExecutorService implements ScheduledExecutorService {

    /* renamed from: f  reason: collision with root package name */
    private static ThreadLocal f6526f = new a();

    /* renamed from: e  reason: collision with root package name */
    private final Handler f6527e;

    /* loaded from: classes.dex */
    class a extends ThreadLocal {
        a() {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ScheduledExecutorService initialValue() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                return e0.c.e();
            }
            if (Looper.myLooper() != null) {
                return new e(new Handler(Looper.myLooper()));
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    class b implements Callable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Runnable f6528a;

        b(Runnable runnable) {
            this.f6528a = runnable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            this.f6528a.run();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c implements RunnableScheduledFuture {

        /* renamed from: e  reason: collision with root package name */
        final AtomicReference f6530e = new AtomicReference(null);

        /* renamed from: f  reason: collision with root package name */
        private final long f6531f;

        /* renamed from: g  reason: collision with root package name */
        private final Callable f6532g;

        /* renamed from: h  reason: collision with root package name */
        private final i4.d f6533h;

        /* loaded from: classes.dex */
        class a implements c.InterfaceC0018c {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Handler f6534a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ Callable f6535b;

            /* renamed from: e0.e$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            class RunnableC0082a implements Runnable {
                RunnableC0082a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (c.this.f6530e.getAndSet(null) != null) {
                        a aVar = a.this;
                        aVar.f6534a.removeCallbacks(c.this);
                    }
                }
            }

            a(Handler handler, Callable callable) {
                this.f6534a = handler;
                this.f6535b = callable;
            }

            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public Object a(c.a aVar) {
                aVar.a(new RunnableC0082a(), e0.c.b());
                c.this.f6530e.set(aVar);
                return "HandlerScheduledFuture-" + this.f6535b.toString();
            }
        }

        c(Handler handler, long j8, Callable callable) {
            this.f6531f = j8;
            this.f6532g = callable;
            this.f6533h = androidx.concurrent.futures.c.a(new a(handler, callable));
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z7) {
            return this.f6533h.cancel(z7);
        }

        @Override // java.lang.Comparable
        /* renamed from: e */
        public int compareTo(Delayed delayed) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            return Long.compare(getDelay(timeUnit), delayed.getDelay(timeUnit));
        }

        @Override // java.util.concurrent.Future
        public Object get() {
            return this.f6533h.get();
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit timeUnit) {
            return timeUnit.convert(this.f6531f - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.f6533h.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.f6533h.isDone();
        }

        @Override // java.util.concurrent.RunnableScheduledFuture
        public boolean isPeriodic() {
            return false;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public void run() {
            c.a aVar = (c.a) this.f6530e.getAndSet(null);
            if (aVar != null) {
                try {
                    aVar.c(this.f6532g.call());
                } catch (Exception e8) {
                    aVar.f(e8);
                }
            }
        }

        @Override // java.util.concurrent.Future
        public Object get(long j8, TimeUnit timeUnit) {
            return this.f6533h.get(j8, timeUnit);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(Handler handler) {
        this.f6527e = handler;
    }

    private RejectedExecutionException b() {
        return new RejectedExecutionException(this.f6527e + " is shutting down");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j8, TimeUnit timeUnit) {
        throw new UnsupportedOperationException(e.class.getSimpleName() + " cannot be shut down. Use Looper.quitSafely().");
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (!this.f6527e.post(runnable)) {
            throw b();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return false;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture schedule(Runnable runnable, long j8, TimeUnit timeUnit) {
        return schedule(new b(runnable), j8, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j8, long j9, TimeUnit timeUnit) {
        throw new UnsupportedOperationException(e.class.getSimpleName() + " does not yet support fixed-rate scheduling.");
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j8, long j9, TimeUnit timeUnit) {
        throw new UnsupportedOperationException(e.class.getSimpleName() + " does not yet support fixed-delay scheduling.");
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException(e.class.getSimpleName() + " cannot be shut down. Use Looper.quitSafely().");
    }

    @Override // java.util.concurrent.ExecutorService
    public List shutdownNow() {
        throw new UnsupportedOperationException(e.class.getSimpleName() + " cannot be shut down. Use Looper.quitSafely().");
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture schedule(Callable callable, long j8, TimeUnit timeUnit) {
        long uptimeMillis = SystemClock.uptimeMillis() + TimeUnit.MILLISECONDS.convert(j8, timeUnit);
        c cVar = new c(this.f6527e, uptimeMillis, callable);
        return this.f6527e.postAtTime(cVar, uptimeMillis) ? cVar : n.o(b());
    }
}
