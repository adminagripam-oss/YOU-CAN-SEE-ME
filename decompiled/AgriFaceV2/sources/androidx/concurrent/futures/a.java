package androidx.concurrent.futures;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public abstract class a implements i4.d {

    /* renamed from: h  reason: collision with root package name */
    static final boolean f2780h = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* renamed from: i  reason: collision with root package name */
    private static final Logger f2781i = Logger.getLogger(a.class.getName());

    /* renamed from: j  reason: collision with root package name */
    static final b f2782j;

    /* renamed from: k  reason: collision with root package name */
    private static final Object f2783k;

    /* renamed from: e  reason: collision with root package name */
    volatile Object f2784e;

    /* renamed from: f  reason: collision with root package name */
    volatile e f2785f;

    /* renamed from: g  reason: collision with root package name */
    volatile h f2786g;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class b {
        private b() {
        }

        abstract boolean a(a aVar, e eVar, e eVar2);

        abstract boolean b(a aVar, Object obj, Object obj2);

        abstract boolean c(a aVar, h hVar, h hVar2);

        abstract void d(h hVar, h hVar2);

        abstract void e(h hVar, Thread thread);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: c  reason: collision with root package name */
        static final c f2787c;

        /* renamed from: d  reason: collision with root package name */
        static final c f2788d;

        /* renamed from: a  reason: collision with root package name */
        final boolean f2789a;

        /* renamed from: b  reason: collision with root package name */
        final Throwable f2790b;

        static {
            if (a.f2780h) {
                f2788d = null;
                f2787c = null;
                return;
            }
            f2788d = new c(false, null);
            f2787c = new c(true, null);
        }

        c(boolean z7, Throwable th) {
            this.f2789a = z7;
            this.f2790b = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: b  reason: collision with root package name */
        static final d f2791b = new d(new C0017a("Failure occurred while trying to finish a future."));

        /* renamed from: a  reason: collision with root package name */
        final Throwable f2792a;

        /* renamed from: androidx.concurrent.futures.a$d$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0017a extends Throwable {
            C0017a(String str) {
                super(str);
            }

            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        }

        d(Throwable th) {
            this.f2792a = (Throwable) a.e(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class e {

        /* renamed from: d  reason: collision with root package name */
        static final e f2793d = new e(null, null);

        /* renamed from: a  reason: collision with root package name */
        final Runnable f2794a;

        /* renamed from: b  reason: collision with root package name */
        final Executor f2795b;

        /* renamed from: c  reason: collision with root package name */
        e f2796c;

        e(Runnable runnable, Executor executor) {
            this.f2794a = runnable;
            this.f2795b = executor;
        }
    }

    /* loaded from: classes.dex */
    private static final class f extends b {

        /* renamed from: a  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f2797a;

        /* renamed from: b  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f2798b;

        /* renamed from: c  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f2799c;

        /* renamed from: d  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f2800d;

        /* renamed from: e  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f2801e;

        f(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super();
            this.f2797a = atomicReferenceFieldUpdater;
            this.f2798b = atomicReferenceFieldUpdater2;
            this.f2799c = atomicReferenceFieldUpdater3;
            this.f2800d = atomicReferenceFieldUpdater4;
            this.f2801e = atomicReferenceFieldUpdater5;
        }

        @Override // androidx.concurrent.futures.a.b
        boolean a(a aVar, e eVar, e eVar2) {
            return androidx.concurrent.futures.b.a(this.f2800d, aVar, eVar, eVar2);
        }

        @Override // androidx.concurrent.futures.a.b
        boolean b(a aVar, Object obj, Object obj2) {
            return androidx.concurrent.futures.b.a(this.f2801e, aVar, obj, obj2);
        }

        @Override // androidx.concurrent.futures.a.b
        boolean c(a aVar, h hVar, h hVar2) {
            return androidx.concurrent.futures.b.a(this.f2799c, aVar, hVar, hVar2);
        }

        @Override // androidx.concurrent.futures.a.b
        void d(h hVar, h hVar2) {
            this.f2798b.lazySet(hVar, hVar2);
        }

        @Override // androidx.concurrent.futures.a.b
        void e(h hVar, Thread thread) {
            this.f2797a.lazySet(hVar, thread);
        }
    }

    /* loaded from: classes.dex */
    private static final class g extends b {
        g() {
            super();
        }

        @Override // androidx.concurrent.futures.a.b
        boolean a(a aVar, e eVar, e eVar2) {
            synchronized (aVar) {
                if (aVar.f2785f == eVar) {
                    aVar.f2785f = eVar2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.concurrent.futures.a.b
        boolean b(a aVar, Object obj, Object obj2) {
            synchronized (aVar) {
                if (aVar.f2784e == obj) {
                    aVar.f2784e = obj2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.concurrent.futures.a.b
        boolean c(a aVar, h hVar, h hVar2) {
            synchronized (aVar) {
                if (aVar.f2786g == hVar) {
                    aVar.f2786g = hVar2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.concurrent.futures.a.b
        void d(h hVar, h hVar2) {
            hVar.f2804b = hVar2;
        }

        @Override // androidx.concurrent.futures.a.b
        void e(h hVar, Thread thread) {
            hVar.f2803a = thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class h {

        /* renamed from: c  reason: collision with root package name */
        static final h f2802c = new h(false);

        /* renamed from: a  reason: collision with root package name */
        volatile Thread f2803a;

        /* renamed from: b  reason: collision with root package name */
        volatile h f2804b;

        h() {
            a.f2782j.e(this, Thread.currentThread());
        }

        h(boolean z7) {
        }

        void a(h hVar) {
            a.f2782j.d(this, hVar);
        }

        void b() {
            Thread thread = this.f2803a;
            if (thread != null) {
                this.f2803a = null;
                LockSupport.unpark(thread);
            }
        }
    }

    static {
        b gVar;
        try {
            gVar = new f(AtomicReferenceFieldUpdater.newUpdater(h.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(h.class, h.class, "b"), AtomicReferenceFieldUpdater.newUpdater(a.class, h.class, "g"), AtomicReferenceFieldUpdater.newUpdater(a.class, e.class, "f"), AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "e"));
            th = null;
        } catch (Throwable th) {
            th = th;
            gVar = new g();
        }
        f2782j = gVar;
        if (th != null) {
            f2781i.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f2783k = new Object();
    }

    private void a(StringBuilder sb) {
        String str = "]";
        try {
            Object j8 = j(this);
            sb.append("SUCCESS, result=[");
            sb.append(q(j8));
            sb.append("]");
        } catch (CancellationException unused) {
            str = "CANCELLED";
            sb.append(str);
        } catch (RuntimeException e8) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e8.getClass());
            str = " thrown from get()]";
            sb.append(str);
        } catch (ExecutionException e9) {
            sb.append("FAILURE, cause=[");
            sb.append(e9.getCause());
            sb.append(str);
        }
    }

    private static CancellationException c(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    static Object e(Object obj) {
        obj.getClass();
        return obj;
    }

    private e f(e eVar) {
        e eVar2;
        do {
            eVar2 = this.f2785f;
        } while (!f2782j.a(this, eVar2, e.f2793d));
        e eVar3 = eVar;
        e eVar4 = eVar2;
        while (eVar4 != null) {
            e eVar5 = eVar4.f2796c;
            eVar4.f2796c = eVar3;
            eVar3 = eVar4;
            eVar4 = eVar5;
        }
        return eVar3;
    }

    static void g(a aVar) {
        aVar.m();
        aVar.b();
        e f8 = aVar.f(null);
        while (f8 != null) {
            e eVar = f8.f2796c;
            h(f8.f2794a, f8.f2795b);
            f8 = eVar;
        }
    }

    private static void h(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e8) {
            Logger logger = f2781i;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e8);
        }
    }

    private Object i(Object obj) {
        if (obj instanceof c) {
            throw c("Task was cancelled.", ((c) obj).f2790b);
        }
        if (obj instanceof d) {
            throw new ExecutionException(((d) obj).f2792a);
        }
        if (obj == f2783k) {
            return null;
        }
        return obj;
    }

    static Object j(Future future) {
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

    private void m() {
        h hVar;
        do {
            hVar = this.f2786g;
        } while (!f2782j.c(this, hVar, h.f2802c));
        while (hVar != null) {
            hVar.b();
            hVar = hVar.f2804b;
        }
    }

    private void n(h hVar) {
        hVar.f2803a = null;
        while (true) {
            h hVar2 = this.f2786g;
            if (hVar2 == h.f2802c) {
                return;
            }
            h hVar3 = null;
            while (hVar2 != null) {
                h hVar4 = hVar2.f2804b;
                if (hVar2.f2803a != null) {
                    hVar3 = hVar2;
                } else if (hVar3 != null) {
                    hVar3.f2804b = hVar4;
                    if (hVar3.f2803a == null) {
                        break;
                    }
                } else if (!f2782j.c(this, hVar2, hVar4)) {
                    break;
                }
                hVar2 = hVar4;
            }
            return;
        }
    }

    private String q(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    protected void b() {
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z7) {
        Object obj = this.f2784e;
        if ((obj == null) | false) {
            if (f2782j.b(this, obj, f2780h ? new c(z7, new CancellationException("Future.cancel() was called.")) : z7 ? c.f2787c : c.f2788d)) {
                if (z7) {
                    k();
                }
                g(this);
                return true;
            }
        }
        return false;
    }

    @Override // i4.d
    public final void d(Runnable runnable, Executor executor) {
        e(runnable);
        e(executor);
        e eVar = this.f2785f;
        if (eVar == e.f2793d) {
            h(runnable, executor);
        }
        e eVar2 = new e(runnable, executor);
        do {
            eVar2.f2796c = eVar;
            if (f2782j.a(this, eVar, eVar2)) {
                return;
            }
            eVar = this.f2785f;
        } while (eVar != e.f2793d);
        h(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.f2784e;
        if ((obj2 != null) && true) {
            return i(obj2);
        }
        h hVar = this.f2786g;
        if (hVar != h.f2802c) {
            h hVar2 = new h();
            do {
                hVar2.a(hVar);
                if (f2782j.c(this, hVar, hVar2)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            n(hVar2);
                            throw new InterruptedException();
                        }
                        obj = this.f2784e;
                    } while (!((obj != null) & true));
                    return i(obj);
                }
                hVar = this.f2786g;
            } while (hVar != h.f2802c);
            return i(this.f2784e);
        }
        return i(this.f2784e);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f2784e instanceof c;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (this.f2784e != null) & true;
    }

    protected void k() {
    }

    protected String l() {
        if (this instanceof ScheduledFuture) {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean o(Object obj) {
        if (obj == null) {
            obj = f2783k;
        }
        if (f2782j.b(this, null, obj)) {
            g(this);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean p(Throwable th) {
        if (f2782j.b(this, null, new d((Throwable) e(th)))) {
            g(this);
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (!isCancelled()) {
            if (!isDone()) {
                try {
                    str = l();
                } catch (RuntimeException e8) {
                    str = "Exception thrown from implementation: " + e8.getClass();
                }
                if (str != null && !str.isEmpty()) {
                    sb.append("PENDING, info=[");
                    sb.append(str);
                    sb.append("]");
                    sb.append("]");
                    return sb.toString();
                }
                str2 = isDone() ? "PENDING" : "PENDING";
            }
            a(sb);
            sb.append("]");
            return sb.toString();
        }
        str2 = "CANCELLED";
        sb.append(str2);
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j8, TimeUnit timeUnit) {
        Locale locale;
        long nanos = timeUnit.toNanos(j8);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.f2784e;
        if ((obj != null) && true) {
            return i(obj);
        }
        long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            h hVar = this.f2786g;
            if (hVar != h.f2802c) {
                h hVar2 = new h();
                do {
                    hVar2.a(hVar);
                    if (f2782j.c(this, hVar, hVar2)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (!Thread.interrupted()) {
                                Object obj2 = this.f2784e;
                                if ((obj2 != null) && true) {
                                    return i(obj2);
                                }
                                nanos = nanoTime - System.nanoTime();
                            } else {
                                n(hVar2);
                                throw new InterruptedException();
                            }
                        } while (nanos >= 1000);
                        n(hVar2);
                    } else {
                        hVar = this.f2786g;
                    }
                } while (hVar != h.f2802c);
                return i(this.f2784e);
            }
            return i(this.f2784e);
        }
        while (nanos > 0) {
            Object obj3 = this.f2784e;
            if ((obj3 != null) && true) {
                return i(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = nanoTime - System.nanoTime();
        }
        String aVar = toString();
        String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
        String str = "Waited " + j8 + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String str2 = str + " (plus ";
            long j9 = -nanos;
            long convert = timeUnit.convert(j9, TimeUnit.NANOSECONDS);
            long nanos2 = j9 - timeUnit.toNanos(convert);
            int i8 = (convert > 0L ? 1 : (convert == 0L ? 0 : -1));
            boolean z7 = i8 == 0 || nanos2 > 1000;
            if (i8 > 0) {
                String str3 = str2 + convert + " " + lowerCase;
                if (z7) {
                    str3 = str3 + ",";
                }
                str2 = str3 + " ";
            }
            if (z7) {
                str2 = str2 + nanos2 + " nanoseconds ";
            }
            str = str2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(str + " but future completed as timeout expired");
        }
        throw new TimeoutException(str + " for " + aVar);
    }
}
