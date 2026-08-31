package r7;

import d7.r;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import p7.c0;
import p7.h0;
/* loaded from: classes.dex */
public final class a implements Executor, Closeable {

    /* renamed from: l  reason: collision with root package name */
    public static final C0145a f11373l = new C0145a(null);

    /* renamed from: m  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f11374m = AtomicLongFieldUpdater.newUpdater(a.class, "parkedWorkersStack");

    /* renamed from: n  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f11375n = AtomicLongFieldUpdater.newUpdater(a.class, "controlState");

    /* renamed from: o  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f11376o = AtomicIntegerFieldUpdater.newUpdater(a.class, "_isTerminated");

    /* renamed from: p  reason: collision with root package name */
    public static final h0 f11377p = new h0("NOT_IN_STACK");
    private volatile int _isTerminated;
    private volatile long controlState;

    /* renamed from: e  reason: collision with root package name */
    public final int f11378e;

    /* renamed from: f  reason: collision with root package name */
    public final int f11379f;

    /* renamed from: g  reason: collision with root package name */
    public final long f11380g;

    /* renamed from: h  reason: collision with root package name */
    public final String f11381h;

    /* renamed from: i  reason: collision with root package name */
    public final r7.d f11382i;

    /* renamed from: j  reason: collision with root package name */
    public final r7.d f11383j;

    /* renamed from: k  reason: collision with root package name */
    public final c0 f11384k;
    private volatile long parkedWorkersStack;

    /* renamed from: r7.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0145a {
        private C0145a() {
        }

        public /* synthetic */ C0145a(d7.g gVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11385a;

        static {
            int[] iArr = new int[d.values().length];
            try {
                iArr[d.PARKING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[d.BLOCKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[d.CPU_ACQUIRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[d.DORMANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[d.TERMINATED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            f11385a = iArr;
        }
    }

    /* loaded from: classes.dex */
    public final class c extends Thread {

        /* renamed from: m  reason: collision with root package name */
        private static final AtomicIntegerFieldUpdater f11386m = AtomicIntegerFieldUpdater.newUpdater(c.class, "workerCtl");

        /* renamed from: e  reason: collision with root package name */
        public final n f11387e;

        /* renamed from: f  reason: collision with root package name */
        private final r f11388f;

        /* renamed from: g  reason: collision with root package name */
        public d f11389g;

        /* renamed from: h  reason: collision with root package name */
        private long f11390h;

        /* renamed from: i  reason: collision with root package name */
        private long f11391i;
        private volatile int indexInArray;

        /* renamed from: j  reason: collision with root package name */
        private int f11392j;

        /* renamed from: k  reason: collision with root package name */
        public boolean f11393k;
        private volatile Object nextParkedWorker;
        private volatile int workerCtl;

        private c() {
            setDaemon(true);
            this.f11387e = new n();
            this.f11388f = new r();
            this.f11389g = d.DORMANT;
            this.nextParkedWorker = a.f11377p;
            this.f11392j = e7.c.f6621e.b();
        }

        private final void b(int i8) {
            if (i8 == 0) {
                return;
            }
            a.f11375n.addAndGet(a.this, -2097152L);
            if (this.f11389g != d.TERMINATED) {
                this.f11389g = d.DORMANT;
            }
        }

        private final void c(int i8) {
            if (i8 != 0 && u(d.BLOCKING)) {
                a.this.G();
            }
        }

        private final void d(h hVar) {
            int b8 = hVar.f11411f.b();
            k(b8);
            c(b8);
            a.this.C(hVar);
            b(b8);
        }

        private final h e(boolean z7) {
            h o8;
            h o9;
            if (z7) {
                boolean z8 = m(a.this.f11378e * 2) == 0;
                if (z8 && (o9 = o()) != null) {
                    return o9;
                }
                h g8 = this.f11387e.g();
                if (g8 != null) {
                    return g8;
                }
                if (!z8 && (o8 = o()) != null) {
                    return o8;
                }
            } else {
                h o10 = o();
                if (o10 != null) {
                    return o10;
                }
            }
            return v(3);
        }

        private final h f() {
            h h8 = this.f11387e.h();
            if (h8 == null) {
                h hVar = (h) a.this.f11383j.d();
                return hVar == null ? v(1) : hVar;
            }
            return h8;
        }

        public static final AtomicIntegerFieldUpdater j() {
            return f11386m;
        }

        private final void k(int i8) {
            this.f11390h = 0L;
            if (this.f11389g == d.PARKING) {
                this.f11389g = d.BLOCKING;
            }
        }

        private final boolean l() {
            return this.nextParkedWorker != a.f11377p;
        }

        private final void n() {
            if (this.f11390h == 0) {
                this.f11390h = System.nanoTime() + a.this.f11380g;
            }
            LockSupport.parkNanos(a.this.f11380g);
            if (System.nanoTime() - this.f11390h >= 0) {
                this.f11390h = 0L;
                w();
            }
        }

        private final h o() {
            r7.d dVar;
            if (m(2) == 0) {
                h hVar = (h) a.this.f11382i.d();
                if (hVar != null) {
                    return hVar;
                }
                dVar = a.this.f11383j;
            } else {
                h hVar2 = (h) a.this.f11383j.d();
                if (hVar2 != null) {
                    return hVar2;
                }
                dVar = a.this.f11382i;
            }
            return (h) dVar.d();
        }

        private final void p() {
            loop0: while (true) {
                boolean z7 = false;
                while (!a.this.isTerminated() && this.f11389g != d.TERMINATED) {
                    h g8 = g(this.f11393k);
                    if (g8 != null) {
                        this.f11391i = 0L;
                        d(g8);
                    } else {
                        this.f11393k = false;
                        if (this.f11391i == 0) {
                            t();
                        } else if (z7) {
                            u(d.PARKING);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.f11391i);
                            this.f11391i = 0L;
                        } else {
                            z7 = true;
                        }
                    }
                }
            }
            u(d.TERMINATED);
        }

        private final boolean s() {
            boolean z7;
            if (this.f11389g == d.CPU_ACQUIRED) {
                return true;
            }
            a aVar = a.this;
            AtomicLongFieldUpdater atomicLongFieldUpdater = a.f11375n;
            while (true) {
                long j8 = atomicLongFieldUpdater.get(aVar);
                if (((int) ((9223367638808264704L & j8) >> 42)) != 0) {
                    if (a.f11375n.compareAndSet(aVar, j8, j8 - 4398046511104L)) {
                        z7 = true;
                        break;
                    }
                } else {
                    z7 = false;
                    break;
                }
            }
            if (z7) {
                this.f11389g = d.CPU_ACQUIRED;
                return true;
            }
            return false;
        }

        private final void t() {
            if (!l()) {
                a.this.x(this);
                return;
            }
            f11386m.set(this, -1);
            while (l() && f11386m.get(this) == -1 && !a.this.isTerminated() && this.f11389g != d.TERMINATED) {
                u(d.PARKING);
                Thread.interrupted();
                n();
            }
        }

        private final h v(int i8) {
            int i9 = (int) (a.f11375n.get(a.this) & 2097151);
            if (i9 < 2) {
                return null;
            }
            int m8 = m(i9);
            a aVar = a.this;
            long j8 = Long.MAX_VALUE;
            for (int i10 = 0; i10 < i9; i10++) {
                m8++;
                if (m8 > i9) {
                    m8 = 1;
                }
                c cVar = (c) aVar.f11384k.b(m8);
                if (cVar != null && cVar != this) {
                    long n8 = cVar.f11387e.n(i8, this.f11388f);
                    if (n8 == -1) {
                        r rVar = this.f11388f;
                        h hVar = (h) rVar.f6484e;
                        rVar.f6484e = null;
                        return hVar;
                    } else if (n8 > 0) {
                        j8 = Math.min(j8, n8);
                    }
                }
            }
            if (j8 == Long.MAX_VALUE) {
                j8 = 0;
            }
            this.f11391i = j8;
            return null;
        }

        private final void w() {
            a aVar = a.this;
            synchronized (aVar.f11384k) {
                if (aVar.isTerminated()) {
                    return;
                }
                if (((int) (a.f11375n.get(aVar) & 2097151)) <= aVar.f11378e) {
                    return;
                }
                if (f11386m.compareAndSet(this, -1, 1)) {
                    int i8 = this.indexInArray;
                    q(0);
                    aVar.A(this, i8, 0);
                    int andDecrement = (int) (a.f11375n.getAndDecrement(aVar) & 2097151);
                    if (andDecrement != i8) {
                        Object b8 = aVar.f11384k.b(andDecrement);
                        d7.k.b(b8);
                        c cVar = (c) b8;
                        aVar.f11384k.c(i8, cVar);
                        cVar.q(i8);
                        aVar.A(cVar, andDecrement, i8);
                    }
                    aVar.f11384k.c(andDecrement, null);
                    r6.r rVar = r6.r.f11372a;
                    this.f11389g = d.TERMINATED;
                }
            }
        }

        public final h g(boolean z7) {
            return s() ? e(z7) : f();
        }

        public final int h() {
            return this.indexInArray;
        }

        public final Object i() {
            return this.nextParkedWorker;
        }

        public final int m(int i8) {
            int i9 = this.f11392j;
            int i10 = i9 ^ (i9 << 13);
            int i11 = i10 ^ (i10 >> 17);
            int i12 = i11 ^ (i11 << 5);
            this.f11392j = i12;
            int i13 = i8 - 1;
            return (i13 & i8) == 0 ? i12 & i13 : (i12 & Integer.MAX_VALUE) % i8;
        }

        public final void q(int i8) {
            StringBuilder sb = new StringBuilder();
            sb.append(a.this.f11381h);
            sb.append("-worker-");
            sb.append(i8 == 0 ? "TERMINATED" : String.valueOf(i8));
            setName(sb.toString());
            this.indexInArray = i8;
        }

        public final void r(Object obj) {
            this.nextParkedWorker = obj;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            p();
        }

        public final boolean u(d dVar) {
            d dVar2 = this.f11389g;
            boolean z7 = dVar2 == d.CPU_ACQUIRED;
            if (z7) {
                a.f11375n.addAndGet(a.this, 4398046511104L);
            }
            if (dVar2 != dVar) {
                this.f11389g = dVar;
            }
            return z7;
        }

        public c(a aVar, int i8) {
            this();
            q(i8);
        }
    }

    /* loaded from: classes.dex */
    public enum d {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public a(int i8, int i9, long j8, String str) {
        this.f11378e = i8;
        this.f11379f = i9;
        this.f11380g = j8;
        this.f11381h = str;
        if (!(i8 >= 1)) {
            throw new IllegalArgumentException(("Core pool size " + i8 + " should be at least 1").toString());
        }
        if (!(i9 >= i8)) {
            throw new IllegalArgumentException(("Max pool size " + i9 + " should be greater than or equals to core pool size " + i8).toString());
        }
        if (!(i9 <= 2097150)) {
            throw new IllegalArgumentException(("Max pool size " + i9 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (!(j8 > 0)) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j8 + " must be positive").toString());
        }
        this.f11382i = new r7.d();
        this.f11383j = new r7.d();
        this.f11384k = new c0((i8 + 1) * 2);
        this.controlState = i8 << 42;
        this._isTerminated = 0;
    }

    private final void E(long j8, boolean z7) {
        if (z7 || R() || P(j8)) {
            return;
        }
        R();
    }

    private final h M(c cVar, h hVar, boolean z7) {
        if (cVar == null || cVar.f11389g == d.TERMINATED) {
            return hVar;
        }
        if (hVar.f11411f.b() == 0 && cVar.f11389g == d.BLOCKING) {
            return hVar;
        }
        cVar.f11393k = true;
        return cVar.f11387e.a(hVar, z7);
    }

    private final boolean P(long j8) {
        int a8;
        a8 = g7.f.a(((int) (2097151 & j8)) - ((int) ((j8 & 4398044413952L) >> 21)), 0);
        if (a8 < this.f11378e) {
            int d8 = d();
            if (d8 == 1 && this.f11378e > 1) {
                d();
            }
            if (d8 > 0) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ boolean Q(a aVar, long j8, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            j8 = f11375n.get(aVar);
        }
        return aVar.P(j8);
    }

    private final boolean R() {
        c w7;
        do {
            w7 = w();
            if (w7 == null) {
                return false;
            }
        } while (!c.j().compareAndSet(w7, -1, 0));
        LockSupport.unpark(w7);
        return true;
    }

    private final boolean c(h hVar) {
        return (hVar.f11411f.b() == 1 ? this.f11383j : this.f11382i).a(hVar);
    }

    private final int d() {
        int a8;
        synchronized (this.f11384k) {
            if (isTerminated()) {
                return -1;
            }
            AtomicLongFieldUpdater atomicLongFieldUpdater = f11375n;
            long j8 = atomicLongFieldUpdater.get(this);
            int i8 = (int) (j8 & 2097151);
            a8 = g7.f.a(i8 - ((int) ((j8 & 4398044413952L) >> 21)), 0);
            if (a8 >= this.f11378e) {
                return 0;
            }
            if (i8 >= this.f11379f) {
                return 0;
            }
            int i9 = ((int) (f11375n.get(this) & 2097151)) + 1;
            if (i9 > 0 && this.f11384k.b(i9) == null) {
                c cVar = new c(this, i9);
                this.f11384k.c(i9, cVar);
                if (i9 == ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    int i10 = a8 + 1;
                    cVar.start();
                    return i10;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    private final c j() {
        Thread currentThread = Thread.currentThread();
        c cVar = currentThread instanceof c ? (c) currentThread : null;
        if (cVar == null || !d7.k.a(a.this, this)) {
            return null;
        }
        return cVar;
    }

    public static /* synthetic */ void q(a aVar, Runnable runnable, i iVar, boolean z7, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            iVar = l.f11420g;
        }
        if ((i8 & 4) != 0) {
            z7 = false;
        }
        aVar.n(runnable, iVar, z7);
    }

    private final int t(c cVar) {
        int h8;
        do {
            Object i8 = cVar.i();
            if (i8 == f11377p) {
                return -1;
            }
            if (i8 == null) {
                return 0;
            }
            cVar = (c) i8;
            h8 = cVar.h();
        } while (h8 == 0);
        return h8;
    }

    private final c w() {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f11374m;
        while (true) {
            long j8 = atomicLongFieldUpdater.get(this);
            c cVar = (c) this.f11384k.b((int) (2097151 & j8));
            if (cVar == null) {
                return null;
            }
            long j9 = (2097152 + j8) & (-2097152);
            int t8 = t(cVar);
            if (t8 >= 0 && f11374m.compareAndSet(this, j8, t8 | j9)) {
                cVar.r(f11377p);
                return cVar;
            }
        }
    }

    public final void A(c cVar, int i8, int i9) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f11374m;
        while (true) {
            long j8 = atomicLongFieldUpdater.get(this);
            int i10 = (int) (2097151 & j8);
            long j9 = (2097152 + j8) & (-2097152);
            if (i10 == i8) {
                i10 = i9 == 0 ? t(cVar) : i9;
            }
            if (i10 >= 0 && f11374m.compareAndSet(this, j8, j9 | i10)) {
                return;
            }
        }
    }

    public final void C(h hVar) {
        try {
            hVar.run();
        } finally {
            try {
            } finally {
            }
        }
    }

    public final void D(long j8) {
        int i8;
        h hVar;
        if (f11376o.compareAndSet(this, 0, 1)) {
            c j9 = j();
            synchronized (this.f11384k) {
                i8 = (int) (f11375n.get(this) & 2097151);
            }
            if (1 <= i8) {
                int i9 = 1;
                while (true) {
                    Object b8 = this.f11384k.b(i9);
                    d7.k.b(b8);
                    c cVar = (c) b8;
                    if (cVar != j9) {
                        while (cVar.isAlive()) {
                            LockSupport.unpark(cVar);
                            cVar.join(j8);
                        }
                        cVar.f11387e.f(this.f11383j);
                    }
                    if (i9 == i8) {
                        break;
                    }
                    i9++;
                }
            }
            this.f11383j.b();
            this.f11382i.b();
            while (true) {
                if (j9 != null) {
                    hVar = j9.g(true);
                    if (hVar != null) {
                        continue;
                        C(hVar);
                    }
                }
                hVar = (h) this.f11382i.d();
                if (hVar == null && (hVar = (h) this.f11383j.d()) == null) {
                    break;
                }
                C(hVar);
            }
            if (j9 != null) {
                j9.u(d.TERMINATED);
            }
            f11374m.set(this, 0L);
            f11375n.set(this, 0L);
        }
    }

    public final void G() {
        if (R() || Q(this, 0L, 1, null)) {
            return;
        }
        R();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        D(10000L);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        q(this, runnable, null, false, 6, null);
    }

    public final h g(Runnable runnable, i iVar) {
        long a8 = l.f11419f.a();
        if (runnable instanceof h) {
            h hVar = (h) runnable;
            hVar.f11410e = a8;
            hVar.f11411f = iVar;
            return hVar;
        }
        return new k(runnable, a8, iVar);
    }

    public final boolean isTerminated() {
        return f11376o.get(this) != 0;
    }

    public final void n(Runnable runnable, i iVar, boolean z7) {
        k7.c.a();
        h g8 = g(runnable, iVar);
        boolean z8 = false;
        boolean z9 = g8.f11411f.b() == 1;
        long addAndGet = z9 ? f11375n.addAndGet(this, 2097152L) : 0L;
        c j8 = j();
        h M = M(j8, g8, z7);
        if (M != null && !c(M)) {
            throw new RejectedExecutionException(this.f11381h + " was terminated");
        }
        if (z7 && j8 != null) {
            z8 = true;
        }
        if (z9) {
            E(addAndGet, z8);
        } else if (z8) {
        } else {
            G();
        }
    }

    public String toString() {
        StringBuilder sb;
        char c8;
        ArrayList arrayList = new ArrayList();
        int a8 = this.f11384k.a();
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 1; i13 < a8; i13++) {
            c cVar = (c) this.f11384k.b(i13);
            if (cVar != null) {
                int e8 = cVar.f11387e.e();
                int i14 = b.f11385a[cVar.f11389g.ordinal()];
                if (i14 != 1) {
                    if (i14 == 2) {
                        i9++;
                        sb = new StringBuilder();
                        sb.append(e8);
                        c8 = 'b';
                    } else if (i14 == 3) {
                        i8++;
                        sb = new StringBuilder();
                        sb.append(e8);
                        c8 = 'c';
                    } else if (i14 == 4) {
                        i11++;
                        if (e8 > 0) {
                            sb = new StringBuilder();
                            sb.append(e8);
                            c8 = 'd';
                        }
                    } else if (i14 == 5) {
                        i12++;
                    }
                    sb.append(c8);
                    arrayList.add(sb.toString());
                } else {
                    i10++;
                }
            }
        }
        long j8 = f11375n.get(this);
        return this.f11381h + '@' + k7.h0.b(this) + "[Pool Size {core = " + this.f11378e + ", max = " + this.f11379f + "}, Worker States {CPU = " + i8 + ", blocking = " + i9 + ", parked = " + i10 + ", dormant = " + i11 + ", terminated = " + i12 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.f11382i.c() + ", global blocking queue size = " + this.f11383j.c() + ", Control State {created workers= " + ((int) (2097151 & j8)) + ", blocking tasks = " + ((int) ((4398044413952L & j8) >> 21)) + ", CPUs acquired = " + (this.f11378e - ((int) ((9223367638808264704L & j8) >> 42))) + "}]";
    }

    public final boolean x(c cVar) {
        long j8;
        int h8;
        if (cVar.i() != f11377p) {
            return false;
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater = f11374m;
        do {
            j8 = atomicLongFieldUpdater.get(this);
            h8 = cVar.h();
            cVar.r(this.f11384k.b((int) (2097151 & j8)));
        } while (!f11374m.compareAndSet(this, j8, ((2097152 + j8) & (-2097152)) | h8));
        return true;
    }
}
