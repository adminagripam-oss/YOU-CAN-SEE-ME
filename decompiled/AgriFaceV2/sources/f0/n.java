package f0;

import androidx.concurrent.futures.c;
import f0.o;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    private static final m.a f6656a = new b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ m.a f6657a;

        a(m.a aVar) {
            this.f6657a = aVar;
        }

        @Override // f0.a
        public i4.d apply(Object obj) {
            return n.p(this.f6657a.apply(obj));
        }
    }

    /* loaded from: classes.dex */
    class b implements m.a {
        b() {
        }

        @Override // m.a
        public Object apply(Object obj) {
            return obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c.a f6658a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ m.a f6659b;

        c(c.a aVar, m.a aVar2) {
            this.f6658a = aVar;
            this.f6659b = aVar2;
        }

        @Override // f0.c
        public void a(Object obj) {
            try {
                this.f6658a.c(this.f6659b.apply(obj));
            } catch (Throwable th) {
                this.f6658a.f(th);
            }
        }

        @Override // f0.c
        public void b(Throwable th) {
            this.f6658a.f(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ i4.d f6660e;

        d(i4.d dVar) {
            this.f6660e = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6660e.cancel(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class e implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final Future f6661e;

        /* renamed from: f  reason: collision with root package name */
        final f0.c f6662f;

        e(Future future, f0.c cVar) {
            this.f6661e = future;
            this.f6662f = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f6662f.a(n.l(this.f6661e));
            } catch (Error e8) {
                e = e8;
                this.f6662f.b(e);
            } catch (RuntimeException e9) {
                e = e9;
                this.f6662f.b(e);
            } catch (ExecutionException e10) {
                Throwable cause = e10.getCause();
                if (cause == null) {
                    this.f6662f.b(e10);
                } else {
                    this.f6662f.b(cause);
                }
            }
        }

        public String toString() {
            return e.class.getSimpleName() + "," + this.f6662f;
        }
    }

    public static i4.d A(final long j8, final ScheduledExecutorService scheduledExecutorService, final Object obj, final boolean z7, final i4.d dVar) {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: f0.i
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object v7;
                v7 = n.v(i4.d.this, scheduledExecutorService, obj, z7, j8, aVar);
                return v7;
            }
        });
    }

    public static i4.d B(final i4.d dVar) {
        g1.e.j(dVar);
        return dVar.isDone() ? dVar : androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: f0.m
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object w7;
                w7 = n.w(i4.d.this, aVar);
                return w7;
            }
        });
    }

    public static void C(i4.d dVar, c.a aVar) {
        D(dVar, f6656a, aVar, e0.c.b());
    }

    public static void D(i4.d dVar, m.a aVar, c.a aVar2, Executor executor) {
        E(true, dVar, aVar, aVar2, executor);
    }

    private static void E(boolean z7, i4.d dVar, m.a aVar, c.a aVar2, Executor executor) {
        g1.e.j(dVar);
        g1.e.j(aVar);
        g1.e.j(aVar2);
        g1.e.j(executor);
        j(dVar, new c(aVar2, aVar), executor);
        if (z7) {
            aVar2.a(new d(dVar), e0.c.b());
        }
    }

    public static i4.d F(Collection collection) {
        return new p(new ArrayList(collection), false, e0.c.b());
    }

    public static i4.d G(i4.d dVar, m.a aVar, Executor executor) {
        g1.e.j(aVar);
        return H(dVar, new a(aVar), executor);
    }

    public static i4.d H(i4.d dVar, f0.a aVar, Executor executor) {
        f0.b bVar = new f0.b(aVar, dVar);
        dVar.d(bVar, executor);
        return bVar;
    }

    public static i4.d I(final i4.d dVar) {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: f0.h
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object y7;
                y7 = n.y(i4.d.this, aVar);
                return y7;
            }
        });
    }

    public static void j(i4.d dVar, f0.c cVar, Executor executor) {
        g1.e.j(cVar);
        dVar.d(new e(dVar, cVar), executor);
    }

    public static i4.d k(Collection collection) {
        return new p(new ArrayList(collection), true, e0.c.b());
    }

    public static Object l(Future future) {
        boolean isDone = future.isDone();
        g1.e.m(isDone, "Future was expected to be done, " + future);
        return m(future);
    }

    public static Object m(Future future) {
        Object obj;
        boolean z7 = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z7 = true;
            } catch (Throwable th) {
                if (z7) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z7) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static i4.d n(Throwable th) {
        return new o.a(th);
    }

    public static ScheduledFuture o(Throwable th) {
        return new o.b(th);
    }

    public static i4.d p(Object obj) {
        return obj == null ? o.e() : new o.c(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean q(c.a aVar, i4.d dVar, long j8) {
        return Boolean.valueOf(aVar.f(new TimeoutException("Future[" + dVar + "] is not done within " + j8 + " ms.")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object s(final i4.d dVar, ScheduledExecutorService scheduledExecutorService, final long j8, final c.a aVar) {
        C(dVar, aVar);
        if (!dVar.isDone()) {
            final ScheduledFuture schedule = scheduledExecutorService.schedule(new Callable() { // from class: f0.f
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Boolean q8;
                    q8 = n.q(c.a.this, dVar, j8);
                    return q8;
                }
            }, j8, TimeUnit.MILLISECONDS);
            dVar.d(new Runnable() { // from class: f0.g
                @Override // java.lang.Runnable
                public final void run() {
                    schedule.cancel(true);
                }
            }, e0.c.b());
        }
        return "TimeoutFuture[" + dVar + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void t(c.a aVar, Object obj, boolean z7, i4.d dVar) {
        aVar.c(obj);
        if (z7) {
            dVar.cancel(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object v(final i4.d dVar, ScheduledExecutorService scheduledExecutorService, final Object obj, final boolean z7, long j8, final c.a aVar) {
        C(dVar, aVar);
        if (!dVar.isDone()) {
            final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() { // from class: f0.k
                @Override // java.lang.Runnable
                public final void run() {
                    n.t(c.a.this, obj, z7, dVar);
                }
            }, j8, TimeUnit.MILLISECONDS);
            dVar.d(new Runnable() { // from class: f0.l
                @Override // java.lang.Runnable
                public final void run() {
                    schedule.cancel(true);
                }
            }, e0.c.b());
        }
        return "TimeoutFuture[" + dVar + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object w(i4.d dVar, c.a aVar) {
        E(false, dVar, f6656a, aVar, e0.c.b());
        return "nonCancellationPropagating[" + dVar + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object y(i4.d dVar, final c.a aVar) {
        dVar.d(new Runnable() { // from class: f0.j
            @Override // java.lang.Runnable
            public final void run() {
                c.a.this.c(null);
            }
        }, e0.c.b());
        return "transformVoidFuture [" + dVar + "]";
    }

    public static i4.d z(final long j8, final ScheduledExecutorService scheduledExecutorService, final i4.d dVar) {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: f0.e
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object s8;
                s8 = n.s(i4.d.this, scheduledExecutorService, j8, aVar);
                return s8;
            }
        });
    }
}
