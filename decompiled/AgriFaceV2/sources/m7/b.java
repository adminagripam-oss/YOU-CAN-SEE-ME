package m7;

import androidx.appcompat.app.w;
import d7.v;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import k7.z1;
import p7.e0;
import p7.f0;
import p7.g0;
import p7.h0;
import p7.q0;
import p7.z;
import r6.k;
/* loaded from: classes.dex */
public class b implements d {

    /* renamed from: h  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f9642h = AtomicLongFieldUpdater.newUpdater(b.class, "sendersAndCloseStatus");

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f9643i = AtomicLongFieldUpdater.newUpdater(b.class, "receivers");

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f9644j = AtomicLongFieldUpdater.newUpdater(b.class, "bufferEnd");

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicLongFieldUpdater f9645k = AtomicLongFieldUpdater.newUpdater(b.class, "completedExpandBuffersAndPauseFlag");

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f9646l = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "sendSegment");

    /* renamed from: m  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f9647m = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "receiveSegment");

    /* renamed from: n  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f9648n = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "bufferEndSegment");

    /* renamed from: o  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f9649o = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_closeCause");

    /* renamed from: p  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f9650p = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "closeHandler");
    private volatile Object _closeCause;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;

    /* renamed from: e  reason: collision with root package name */
    private final int f9651e;

    /* renamed from: f  reason: collision with root package name */
    public final c7.l f9652f;

    /* renamed from: g  reason: collision with root package name */
    private final c7.q f9653g;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class a implements f, z1 {

        /* renamed from: e  reason: collision with root package name */
        private Object f9654e;

        /* renamed from: f  reason: collision with root package name */
        private k7.k f9655f;

        public a() {
            h0 h0Var;
            h0Var = c.f9675p;
            this.f9654e = h0Var;
        }

        private final Object f(j jVar, int i8, long j8, t6.d dVar) {
            t6.d b8;
            h0 h0Var;
            h0 h0Var2;
            Boolean a8;
            h0 h0Var3;
            h0 h0Var4;
            h0 h0Var5;
            Object c8;
            b bVar = b.this;
            b8 = u6.c.b(dVar);
            k7.k a9 = k7.m.a(b8);
            try {
                this.f9655f = a9;
                Object u02 = bVar.u0(jVar, i8, j8, this);
                h0Var = c.f9672m;
                if (u02 == h0Var) {
                    bVar.h0(this, jVar, i8);
                } else {
                    h0Var2 = c.f9674o;
                    c7.l lVar = null;
                    if (u02 == h0Var2) {
                        if (j8 < bVar.L()) {
                            jVar.b();
                        }
                        j jVar2 = (j) b.f9647m.get(bVar);
                        while (true) {
                            if (bVar.S()) {
                                h();
                                break;
                            }
                            long andIncrement = b.f9643i.getAndIncrement(bVar);
                            int i9 = c.f9661b;
                            long j9 = andIncrement / i9;
                            int i10 = (int) (andIncrement % i9);
                            if (jVar2.f10143g != j9) {
                                j E = bVar.E(j9, jVar2);
                                if (E != null) {
                                    jVar2 = E;
                                }
                            }
                            Object u03 = bVar.u0(jVar2, i10, andIncrement, this);
                            h0Var3 = c.f9672m;
                            if (u03 == h0Var3) {
                                bVar.h0(this, jVar2, i10);
                                break;
                            }
                            h0Var4 = c.f9674o;
                            if (u03 != h0Var4) {
                                h0Var5 = c.f9673n;
                                if (u03 == h0Var5) {
                                    throw new IllegalStateException("unexpected".toString());
                                }
                                jVar2.b();
                                this.f9654e = u03;
                                this.f9655f = null;
                                a8 = v6.b.a(true);
                                c7.l lVar2 = bVar.f9652f;
                                if (lVar2 != null) {
                                    lVar = z.a(lVar2, u03, a9.getContext());
                                }
                            } else if (andIncrement < bVar.L()) {
                                jVar2.b();
                            }
                        }
                    } else {
                        jVar.b();
                        this.f9654e = u02;
                        this.f9655f = null;
                        a8 = v6.b.a(true);
                        c7.l lVar3 = bVar.f9652f;
                        if (lVar3 != null) {
                            lVar = z.a(lVar3, u02, a9.getContext());
                        }
                    }
                    a9.J(a8, lVar);
                }
                Object w7 = a9.w();
                c8 = u6.d.c();
                if (w7 == c8) {
                    v6.h.c(dVar);
                }
                return w7;
            } catch (Throwable th) {
                a9.H();
                throw th;
            }
        }

        private final boolean g() {
            this.f9654e = c.z();
            Throwable H = b.this.H();
            if (H == null) {
                return false;
            }
            throw g0.a(H);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void h() {
            Object a8;
            k7.k kVar = this.f9655f;
            d7.k.b(kVar);
            this.f9655f = null;
            this.f9654e = c.z();
            Throwable H = b.this.H();
            if (H == null) {
                k.a aVar = r6.k.f11363e;
                a8 = Boolean.FALSE;
            } else {
                k.a aVar2 = r6.k.f11363e;
                a8 = r6.l.a(H);
            }
            kVar.i(r6.k.a(a8));
        }

        @Override // k7.z1
        public void a(e0 e0Var, int i8) {
            k7.k kVar = this.f9655f;
            if (kVar != null) {
                kVar.a(e0Var, i8);
            }
        }

        @Override // m7.f
        public Object b(t6.d dVar) {
            boolean g8;
            j jVar;
            h0 h0Var;
            h0 h0Var2;
            h0 h0Var3;
            b bVar = b.this;
            j jVar2 = (j) b.f9647m.get(bVar);
            while (true) {
                if (bVar.S()) {
                    g8 = g();
                    break;
                }
                long andIncrement = b.f9643i.getAndIncrement(bVar);
                int i8 = c.f9661b;
                long j8 = andIncrement / i8;
                int i9 = (int) (andIncrement % i8);
                if (jVar2.f10143g != j8) {
                    j E = bVar.E(j8, jVar2);
                    if (E == null) {
                        continue;
                    } else {
                        jVar = E;
                    }
                } else {
                    jVar = jVar2;
                }
                Object u02 = bVar.u0(jVar, i9, andIncrement, null);
                h0Var = c.f9672m;
                if (u02 == h0Var) {
                    throw new IllegalStateException("unreachable".toString());
                }
                h0Var2 = c.f9674o;
                if (u02 == h0Var2) {
                    if (andIncrement < bVar.L()) {
                        jVar.b();
                    }
                    jVar2 = jVar;
                } else {
                    h0Var3 = c.f9673n;
                    if (u02 == h0Var3) {
                        return f(jVar, i9, andIncrement, dVar);
                    }
                    jVar.b();
                    this.f9654e = u02;
                    g8 = true;
                }
            }
            return v6.b.a(g8);
        }

        public final boolean i(Object obj) {
            boolean B;
            k7.k kVar = this.f9655f;
            d7.k.b(kVar);
            this.f9655f = null;
            this.f9654e = obj;
            Boolean bool = Boolean.TRUE;
            c7.l lVar = b.this.f9652f;
            B = c.B(kVar, bool, lVar != null ? z.a(lVar, obj, kVar.getContext()) : null);
            return B;
        }

        public final void j() {
            Object a8;
            k7.k kVar = this.f9655f;
            d7.k.b(kVar);
            this.f9655f = null;
            this.f9654e = c.z();
            Throwable H = b.this.H();
            if (H == null) {
                k.a aVar = r6.k.f11363e;
                a8 = Boolean.FALSE;
            } else {
                k.a aVar2 = r6.k.f11363e;
                a8 = r6.l.a(H);
            }
            kVar.i(r6.k.a(a8));
        }

        @Override // m7.f
        public Object next() {
            h0 h0Var;
            h0 h0Var2;
            Object obj = this.f9654e;
            h0Var = c.f9675p;
            if (obj != h0Var) {
                h0Var2 = c.f9675p;
                this.f9654e = h0Var2;
                if (obj != c.z()) {
                    return obj;
                }
                throw g0.a(b.this.I());
            }
            throw new IllegalStateException("`hasNext()` has not been invoked".toString());
        }
    }

    /* renamed from: m7.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static final class C0129b extends d7.l implements c7.q {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: m7.b$b$a */
        /* loaded from: classes.dex */
        public static final class a extends d7.l implements c7.l {

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ Object f9658f;

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ b f9659g;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Object obj, b bVar, s7.a aVar) {
                super(1);
                this.f9658f = obj;
                this.f9659g = bVar;
            }

            public final void b(Throwable th) {
                if (this.f9658f == c.z()) {
                    return;
                }
                c7.l lVar = this.f9659g.f9652f;
                throw null;
            }

            @Override // c7.l
            public /* bridge */ /* synthetic */ Object g(Object obj) {
                b((Throwable) obj);
                return r6.r.f11372a;
            }
        }

        C0129b() {
            super(3);
        }

        public final c7.l b(s7.a aVar, Object obj, Object obj2) {
            return new a(obj2, b.this, aVar);
        }

        @Override // c7.q
        public /* bridge */ /* synthetic */ Object d(Object obj, Object obj2, Object obj3) {
            w.a(obj);
            return b(null, obj2, obj3);
        }
    }

    public b(int i8, c7.l lVar) {
        long A;
        h0 h0Var;
        this.f9651e = i8;
        this.f9652f = lVar;
        if (!(i8 >= 0)) {
            throw new IllegalArgumentException(("Invalid channel capacity: " + i8 + ", should be >=0").toString());
        }
        A = c.A(i8);
        this.bufferEnd = A;
        this.completedExpandBuffersAndPauseFlag = G();
        j jVar = new j(0L, null, this, 3);
        this.sendSegment = jVar;
        this.receiveSegment = jVar;
        if (W()) {
            jVar = c.f9660a;
            d7.k.c(jVar, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment = jVar;
        this.f9653g = lVar != null ? new C0129b() : null;
        h0Var = c.f9678s;
        this._closeCause = h0Var;
    }

    private final void A() {
        o();
    }

    private final void C() {
        if (W()) {
            return;
        }
        j jVar = (j) f9648n.get(this);
        while (true) {
            long andIncrement = f9644j.getAndIncrement(this);
            int i8 = c.f9661b;
            long j8 = andIncrement / i8;
            int i9 = (L() > andIncrement ? 1 : (L() == andIncrement ? 0 : -1));
            long j9 = jVar.f10143g;
            if (i9 <= 0) {
                if (j9 < j8 && jVar.e() != null) {
                    b0(j8, jVar);
                }
                O(this, 0L, 1, null);
                return;
            }
            if (j9 != j8) {
                j D = D(j8, jVar, andIncrement);
                if (D == null) {
                    continue;
                } else {
                    jVar = D;
                }
            }
            boolean s02 = s0(jVar, (int) (andIncrement % i8), andIncrement);
            O(this, 0L, 1, null);
            if (s02) {
                return;
            }
        }
    }

    private final j D(long j8, j jVar, long j9) {
        Object c8;
        boolean z7;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9648n;
        c7.p pVar = (c7.p) c.y();
        do {
            c8 = p7.d.c(jVar, j8, pVar);
            if (f0.c(c8)) {
                break;
            }
            e0 b8 = f0.b(c8);
            while (true) {
                e0 e0Var = (e0) atomicReferenceFieldUpdater.get(this);
                if (e0Var.f10143g >= b8.f10143g) {
                    break;
                } else if (!b8.q()) {
                    z7 = false;
                    continue;
                    break;
                } else if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, e0Var, b8)) {
                    if (e0Var.m()) {
                        e0Var.k();
                    }
                } else if (b8.m()) {
                    b8.k();
                }
            }
            z7 = true;
            continue;
        } while (!z7);
        if (f0.c(c8)) {
            A();
            b0(j8, jVar);
        } else {
            j jVar2 = (j) f0.b(c8);
            long j10 = jVar2.f10143g;
            if (j10 <= j8) {
                return jVar2;
            }
            int i8 = c.f9661b;
            if (f9644j.compareAndSet(this, j9 + 1, i8 * j10)) {
                N((jVar2.f10143g * i8) - j9);
                return null;
            }
        }
        O(this, 0L, 1, null);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final j E(long j8, j jVar) {
        Object c8;
        boolean z7;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9647m;
        c7.p pVar = (c7.p) c.y();
        do {
            c8 = p7.d.c(jVar, j8, pVar);
            if (f0.c(c8)) {
                break;
            }
            e0 b8 = f0.b(c8);
            while (true) {
                e0 e0Var = (e0) atomicReferenceFieldUpdater.get(this);
                z7 = true;
                if (e0Var.f10143g < b8.f10143g) {
                    if (!b8.q()) {
                        z7 = false;
                        continue;
                        break;
                    } else if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, e0Var, b8)) {
                        if (e0Var.m()) {
                            e0Var.k();
                            continue;
                        } else {
                            continue;
                        }
                    } else if (b8.m()) {
                        b8.k();
                    }
                }
            }
        } while (!z7);
        if (f0.c(c8)) {
            A();
            if (jVar.f10143g * c.f9661b >= L()) {
                return null;
            }
        } else {
            jVar = (j) f0.b(c8);
            if (!W() && j8 <= G() / c.f9661b) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f9648n;
                while (true) {
                    e0 e0Var2 = (e0) atomicReferenceFieldUpdater2.get(this);
                    if (e0Var2.f10143g >= jVar.f10143g || !jVar.q()) {
                        break;
                    } else if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater2, this, e0Var2, jVar)) {
                        if (e0Var2.m()) {
                            e0Var2.k();
                        }
                    } else if (jVar.m()) {
                        jVar.k();
                    }
                }
            }
            long j9 = jVar.f10143g;
            if (j9 <= j8) {
                return jVar;
            }
            int i8 = c.f9661b;
            y0(j9 * i8);
            if (jVar.f10143g * i8 >= L()) {
                return null;
            }
        }
        jVar.b();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final j F(long j8, j jVar) {
        Object c8;
        boolean z7;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9646l;
        c7.p pVar = (c7.p) c.y();
        do {
            c8 = p7.d.c(jVar, j8, pVar);
            if (f0.c(c8)) {
                break;
            }
            e0 b8 = f0.b(c8);
            while (true) {
                e0 e0Var = (e0) atomicReferenceFieldUpdater.get(this);
                z7 = true;
                if (e0Var.f10143g < b8.f10143g) {
                    if (!b8.q()) {
                        z7 = false;
                        continue;
                        break;
                    } else if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, e0Var, b8)) {
                        if (e0Var.m()) {
                            e0Var.k();
                            continue;
                        } else {
                            continue;
                        }
                    } else if (b8.m()) {
                        b8.k();
                    }
                }
            }
        } while (!z7);
        if (f0.c(c8)) {
            A();
            if (jVar.f10143g * c.f9661b >= J()) {
                return null;
            }
        } else {
            jVar = (j) f0.b(c8);
            long j9 = jVar.f10143g;
            if (j9 <= j8) {
                return jVar;
            }
            int i8 = c.f9661b;
            z0(j9 * i8);
            if (jVar.f10143g * i8 >= J()) {
                return null;
            }
        }
        jVar.b();
        return null;
    }

    private final long G() {
        return f9644j.get(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable I() {
        Throwable H = H();
        return H == null ? new m("Channel was closed") : H;
    }

    private final void N(long j8) {
        boolean z7;
        if ((f9645k.addAndGet(this, j8) & 4611686018427387904L) != 0) {
            do {
                if ((f9645k.get(this) & 4611686018427387904L) != 0) {
                    z7 = true;
                    continue;
                } else {
                    z7 = false;
                    continue;
                }
            } while (z7);
        }
    }

    static /* synthetic */ void O(b bVar, long j8, int i8, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
        }
        if ((i8 & 1) != 0) {
            j8 = 1;
        }
        bVar.N(j8);
    }

    private final void P() {
        Object obj;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9650p;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
        } while (!androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, obj, obj == null ? c.f9676q : c.f9677r));
        if (obj == null) {
            return;
        }
        c7.l lVar = (c7.l) v.a(obj, 1);
        ((c7.l) obj).g(H());
    }

    private final boolean Q(j jVar, int i8, long j8) {
        Object w7;
        h0 h0Var;
        h0 h0Var2;
        h0 h0Var3;
        h0 h0Var4;
        h0 h0Var5;
        h0 h0Var6;
        h0 h0Var7;
        do {
            w7 = jVar.w(i8);
            if (w7 != null) {
                h0Var2 = c.f9664e;
                if (w7 != h0Var2) {
                    if (w7 == c.f9663d) {
                        return true;
                    }
                    h0Var3 = c.f9669j;
                    if (w7 == h0Var3 || w7 == c.z()) {
                        return false;
                    }
                    h0Var4 = c.f9668i;
                    if (w7 == h0Var4) {
                        return false;
                    }
                    h0Var5 = c.f9667h;
                    if (w7 == h0Var5) {
                        return false;
                    }
                    h0Var6 = c.f9666g;
                    if (w7 == h0Var6) {
                        return true;
                    }
                    h0Var7 = c.f9665f;
                    return w7 != h0Var7 && j8 == J();
                }
            }
            h0Var = c.f9667h;
        } while (!jVar.r(i8, w7, h0Var));
        C();
        return false;
    }

    private final boolean R(long j8, boolean z7) {
        int i8 = (int) (j8 >> 60);
        if (i8 == 0 || i8 == 1) {
            return false;
        }
        if (i8 == 2) {
            z(j8 & 1152921504606846975L);
            if (z7 && M()) {
                return false;
            }
        } else if (i8 != 3) {
            throw new IllegalStateException(("unexpected close status: " + i8).toString());
        } else {
            y(j8 & 1152921504606846975L);
        }
        return true;
    }

    private final boolean T(long j8) {
        return R(j8, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean U(long j8) {
        return R(j8, false);
    }

    private final boolean W() {
        long G = G();
        return G == 0 || G == Long.MAX_VALUE;
    }

    private final long X(j jVar) {
        h0 h0Var;
        do {
            int i8 = c.f9661b;
            while (true) {
                i8--;
                if (-1 < i8) {
                    long j8 = (jVar.f10143g * c.f9661b) + i8;
                    if (j8 >= J()) {
                        while (true) {
                            Object w7 = jVar.w(i8);
                            if (w7 != null) {
                                h0Var = c.f9664e;
                                if (w7 != h0Var) {
                                    if (w7 == c.f9663d) {
                                        return j8;
                                    }
                                }
                            }
                            if (jVar.r(i8, w7, c.z())) {
                                jVar.p();
                                break;
                            }
                        }
                    } else {
                        return -1L;
                    }
                } else {
                    jVar = (j) jVar.g();
                }
            }
        } while (jVar != null);
        return -1L;
    }

    private final void Y() {
        long j8;
        long w7;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f9642h;
        do {
            j8 = atomicLongFieldUpdater.get(this);
            if (((int) (j8 >> 60)) != 0) {
                return;
            }
            w7 = c.w(1152921504606846975L & j8, 1);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j8, w7));
    }

    private final void Z() {
        long j8;
        long w7;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f9642h;
        do {
            j8 = atomicLongFieldUpdater.get(this);
            w7 = c.w(1152921504606846975L & j8, 3);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j8, w7));
    }

    private final void a0() {
        long j8;
        long j9;
        int i8;
        long w7;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f9642h;
        do {
            j8 = atomicLongFieldUpdater.get(this);
            int i9 = (int) (j8 >> 60);
            if (i9 == 0) {
                j9 = j8 & 1152921504606846975L;
                i8 = 2;
            } else if (i9 != 1) {
                return;
            } else {
                j9 = j8 & 1152921504606846975L;
                i8 = 3;
            }
            w7 = c.w(j9, i8);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j8, w7));
    }

    private final void b0(long j8, j jVar) {
        boolean z7;
        j jVar2;
        j jVar3;
        while (jVar.f10143g < j8 && (jVar3 = (j) jVar.e()) != null) {
            jVar = jVar3;
        }
        while (true) {
            if (!jVar.h() || (jVar2 = (j) jVar.e()) == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9648n;
                while (true) {
                    e0 e0Var = (e0) atomicReferenceFieldUpdater.get(this);
                    z7 = true;
                    if (e0Var.f10143g >= jVar.f10143g) {
                        break;
                    } else if (!jVar.q()) {
                        z7 = false;
                        break;
                    } else if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, e0Var, jVar)) {
                        if (e0Var.m()) {
                            e0Var.k();
                        }
                    } else if (jVar.m()) {
                        jVar.k();
                    }
                }
                if (z7) {
                    return;
                }
            } else {
                jVar = jVar2;
            }
        }
    }

    private final Object d0(Object obj, t6.d dVar) {
        t6.d b8;
        Throwable K;
        Object c8;
        Object c9;
        b8 = u6.c.b(dVar);
        k7.k kVar = new k7.k(b8, 1);
        kVar.z();
        c7.l lVar = this.f9652f;
        if (lVar == null || (K = z.d(lVar, obj, null, 2, null)) == null) {
            K = K();
        } else {
            r6.b.a(K, K());
        }
        k.a aVar = r6.k.f11363e;
        kVar.i(r6.k.a(r6.l.a(K)));
        Object w7 = kVar.w();
        c8 = u6.d.c();
        if (w7 == c8) {
            v6.h.c(dVar);
        }
        c9 = u6.d.c();
        return w7 == c9 ? w7 : r6.r.f11372a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void e0(Object obj, k7.j jVar) {
        c7.l lVar = this.f9652f;
        if (lVar != null) {
            z.b(lVar, obj, jVar.getContext());
        }
        Throwable K = K();
        k.a aVar = r6.k.f11363e;
        jVar.i(r6.k.a(r6.l.a(K)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h0(z1 z1Var, j jVar, int i8) {
        g0();
        z1Var.a(jVar, i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i0(z1 z1Var, j jVar, int i8) {
        z1Var.a(jVar, i8 + c.f9661b);
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00aa, code lost:
        r12 = (m7.j) r12.g();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void j0(m7.j r12) {
        /*
            r11 = this;
            c7.l r0 = r11.f9652f
            r1 = 0
            r2 = 1
            java.lang.Object r3 = p7.n.b(r1, r2, r1)
        L8:
            int r4 = m7.c.f9661b
            int r4 = r4 - r2
        Lb:
            r5 = -1
            if (r5 >= r4) goto Laa
            long r6 = r12.f10143g
            int r8 = m7.c.f9661b
            long r8 = (long) r8
            long r6 = r6 * r8
            long r8 = (long) r4
            long r6 = r6 + r8
        L16:
            java.lang.Object r8 = r12.w(r4)
            p7.h0 r9 = m7.c.f()
            if (r8 == r9) goto Lb2
            p7.h0 r9 = m7.c.f9663d
            if (r8 != r9) goto L47
            long r9 = r11.J()
            int r9 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r9 < 0) goto Lb2
            p7.h0 r9 = m7.c.z()
            boolean r8 = r12.r(r4, r8, r9)
            if (r8 == 0) goto L16
            if (r0 == 0) goto L40
            java.lang.Object r5 = r12.v(r4)
            p7.q0 r1 = p7.z.c(r0, r5, r1)
        L40:
            r12.s(r4)
        L43:
            r12.p()
            goto La6
        L47:
            p7.h0 r9 = m7.c.k()
            if (r8 == r9) goto L9b
            if (r8 != 0) goto L50
            goto L9b
        L50:
            boolean r9 = r8 instanceof k7.z1
            if (r9 != 0) goto L6d
            boolean r9 = r8 instanceof m7.u
            if (r9 == 0) goto L59
            goto L6d
        L59:
            p7.h0 r9 = m7.c.p()
            if (r8 == r9) goto Lb2
            p7.h0 r9 = m7.c.q()
            if (r8 != r9) goto L66
            goto Lb2
        L66:
            p7.h0 r9 = m7.c.p()
            if (r8 == r9) goto L16
            goto La6
        L6d:
            long r9 = r11.J()
            int r9 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r9 < 0) goto Lb2
            boolean r9 = r8 instanceof m7.u
            if (r9 == 0) goto L7f
            r9 = r8
            m7.u r9 = (m7.u) r9
            k7.z1 r9 = r9.f9696a
            goto L82
        L7f:
            r9 = r8
            k7.z1 r9 = (k7.z1) r9
        L82:
            p7.h0 r10 = m7.c.z()
            boolean r8 = r12.r(r4, r8, r10)
            if (r8 == 0) goto L16
            if (r0 == 0) goto L96
            java.lang.Object r5 = r12.v(r4)
            p7.q0 r1 = p7.z.c(r0, r5, r1)
        L96:
            java.lang.Object r3 = p7.n.c(r3, r9)
            goto L40
        L9b:
            p7.h0 r9 = m7.c.z()
            boolean r8 = r12.r(r4, r8, r9)
            if (r8 == 0) goto L16
            goto L43
        La6:
            int r4 = r4 + (-1)
            goto Lb
        Laa:
            p7.e r12 = r12.g()
            m7.j r12 = (m7.j) r12
            if (r12 != 0) goto L8
        Lb2:
            if (r3 == 0) goto Ld8
            boolean r12 = r3 instanceof java.util.ArrayList
            if (r12 != 0) goto Lbe
            k7.z1 r3 = (k7.z1) r3
            r11.l0(r3)
            goto Ld8
        Lbe:
            java.lang.String r12 = "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }"
            d7.k.c(r3, r12)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r12 = r3.size()
            int r12 = r12 - r2
        Lca:
            if (r5 >= r12) goto Ld8
            java.lang.Object r0 = r3.get(r12)
            k7.z1 r0 = (k7.z1) r0
            r11.l0(r0)
            int r12 = r12 + (-1)
            goto Lca
        Ld8:
            if (r1 != 0) goto Ldb
            return
        Ldb:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: m7.b.j0(m7.j):void");
    }

    private final void k0(z1 z1Var) {
        m0(z1Var, true);
    }

    private final void l0(z1 z1Var) {
        m0(z1Var, false);
    }

    private final void m0(z1 z1Var, boolean z7) {
        if (z1Var instanceof k7.j) {
            t6.d dVar = (t6.d) z1Var;
            k.a aVar = r6.k.f11363e;
            dVar.i(r6.k.a(r6.l.a(z7 ? I() : K())));
        } else if (z1Var instanceof a) {
            ((a) z1Var).j();
        } else {
            throw new IllegalStateException(("Unexpected waiter: " + z1Var).toString());
        }
    }

    static /* synthetic */ Object n0(b bVar, Object obj, t6.d dVar) {
        j jVar;
        Object c8;
        Object c9;
        Object c10;
        Object c11;
        j jVar2 = (j) f9646l.get(bVar);
        while (true) {
            long andIncrement = f9642h.getAndIncrement(bVar);
            long j8 = andIncrement & 1152921504606846975L;
            boolean U = bVar.U(andIncrement);
            int i8 = c.f9661b;
            long j9 = j8 / i8;
            int i9 = (int) (j8 % i8);
            if (jVar2.f10143g != j9) {
                j F = bVar.F(j9, jVar2);
                if (F != null) {
                    jVar = F;
                } else if (U) {
                    Object d02 = bVar.d0(obj, dVar);
                    c11 = u6.d.c();
                    if (d02 == c11) {
                        return d02;
                    }
                }
            } else {
                jVar = jVar2;
            }
            int w02 = bVar.w0(jVar, i9, obj, j8, null, U);
            if (w02 == 0) {
                jVar.b();
                break;
            } else if (w02 == 1) {
                break;
            } else if (w02 != 2) {
                if (w02 == 3) {
                    Object o02 = bVar.o0(jVar, i9, obj, j8, dVar);
                    c9 = u6.d.c();
                    if (o02 == c9) {
                        return o02;
                    }
                } else if (w02 != 4) {
                    if (w02 == 5) {
                        jVar.b();
                    }
                    jVar2 = jVar;
                } else {
                    if (j8 < bVar.J()) {
                        jVar.b();
                    }
                    Object d03 = bVar.d0(obj, dVar);
                    c10 = u6.d.c();
                    if (d03 == c10) {
                        return d03;
                    }
                }
            } else if (U) {
                jVar.p();
                Object d04 = bVar.d0(obj, dVar);
                c8 = u6.d.c();
                if (d04 == c8) {
                    return d04;
                }
            }
        }
        return r6.r.f11372a;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0122 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Object o0(m7.j r21, int r22, java.lang.Object r23, long r24, t6.d r26) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: m7.b.o0(m7.j, int, java.lang.Object, long, t6.d):java.lang.Object");
    }

    private final boolean p0(long j8) {
        if (U(j8)) {
            return false;
        }
        return !t(j8 & 1152921504606846975L);
    }

    private final boolean q0(Object obj, Object obj2) {
        boolean B;
        if (obj instanceof a) {
            d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            return ((a) obj).i(obj2);
        } else if (!(obj instanceof k7.j)) {
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        } else {
            d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            k7.j jVar = (k7.j) obj;
            c7.l lVar = this.f9652f;
            B = c.B(jVar, obj2, lVar != null ? z.a(lVar, obj2, jVar.getContext()) : null);
            return B;
        }
    }

    private final boolean r0(Object obj, j jVar, int i8) {
        if (obj instanceof k7.j) {
            d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return c.C((k7.j) obj, r6.r.f11372a, null, 2, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    private final boolean s0(j jVar, int i8, long j8) {
        h0 h0Var;
        h0 h0Var2;
        Object w7 = jVar.w(i8);
        if ((w7 instanceof z1) && j8 >= f9643i.get(this)) {
            h0Var = c.f9666g;
            if (jVar.r(i8, w7, h0Var)) {
                if (r0(w7, jVar, i8)) {
                    jVar.A(i8, c.f9663d);
                    return true;
                }
                h0Var2 = c.f9669j;
                jVar.A(i8, h0Var2);
                jVar.x(i8, false);
                return false;
            }
        }
        return t0(jVar, i8, j8);
    }

    private final boolean t(long j8) {
        return j8 < G() || j8 < J() + ((long) this.f9651e);
    }

    private final boolean t0(j jVar, int i8, long j8) {
        h0 h0Var;
        h0 h0Var2;
        h0 h0Var3;
        h0 h0Var4;
        h0 h0Var5;
        h0 h0Var6;
        h0 h0Var7;
        h0 h0Var8;
        while (true) {
            Object w7 = jVar.w(i8);
            if (!(w7 instanceof z1)) {
                h0Var3 = c.f9669j;
                if (w7 != h0Var3) {
                    if (w7 != null) {
                        if (w7 != c.f9663d) {
                            h0Var5 = c.f9667h;
                            if (w7 == h0Var5) {
                                break;
                            }
                            h0Var6 = c.f9668i;
                            if (w7 == h0Var6) {
                                break;
                            }
                            h0Var7 = c.f9670k;
                            if (w7 == h0Var7 || w7 == c.z()) {
                                return true;
                            }
                            h0Var8 = c.f9665f;
                            if (w7 != h0Var8) {
                                throw new IllegalStateException(("Unexpected cell state: " + w7).toString());
                            }
                        } else {
                            return true;
                        }
                    } else {
                        h0Var4 = c.f9664e;
                        if (jVar.r(i8, w7, h0Var4)) {
                            return true;
                        }
                    }
                } else {
                    return false;
                }
            } else if (j8 >= f9643i.get(this)) {
                h0Var = c.f9666g;
                if (jVar.r(i8, w7, h0Var)) {
                    if (r0(w7, jVar, i8)) {
                        jVar.A(i8, c.f9663d);
                        return true;
                    }
                    h0Var2 = c.f9669j;
                    jVar.A(i8, h0Var2);
                    jVar.x(i8, false);
                    return false;
                }
            } else if (jVar.r(i8, w7, new u((z1) w7))) {
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object u0(j jVar, int i8, long j8, Object obj) {
        h0 h0Var;
        h0 h0Var2;
        h0 h0Var3;
        Object w7 = jVar.w(i8);
        if (w7 == null) {
            if (j8 >= (f9642h.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    h0Var3 = c.f9673n;
                    return h0Var3;
                } else if (jVar.r(i8, w7, obj)) {
                    C();
                    h0Var2 = c.f9672m;
                    return h0Var2;
                }
            }
        } else if (w7 == c.f9663d) {
            h0Var = c.f9668i;
            if (jVar.r(i8, w7, h0Var)) {
                C();
                return jVar.y(i8);
            }
        }
        return v0(jVar, i8, j8, obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
        r0 = p7.n.c(r0, r4);
        r9.x(r3, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void v(m7.j r9, long r10) {
        /*
            r8 = this;
            r0 = 0
            r1 = 1
            java.lang.Object r0 = p7.n.b(r0, r1, r0)
        L6:
            r2 = -1
            if (r9 == 0) goto L67
            int r3 = m7.c.f9661b
            int r3 = r3 - r1
        Lc:
            if (r2 >= r3) goto L60
            long r4 = r9.f10143g
            int r6 = m7.c.f9661b
            long r6 = (long) r6
            long r4 = r4 * r6
            long r6 = (long) r3
            long r4 = r4 + r6
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 < 0) goto L67
        L1a:
            java.lang.Object r4 = r9.w(r3)
            if (r4 == 0) goto L50
            p7.h0 r5 = m7.c.k()
            if (r4 != r5) goto L27
            goto L50
        L27:
            boolean r5 = r4 instanceof m7.u
            if (r5 == 0) goto L41
            p7.h0 r5 = m7.c.z()
            boolean r5 = r9.r(r3, r4, r5)
            if (r5 == 0) goto L1a
            m7.u r4 = (m7.u) r4
            k7.z1 r4 = r4.f9696a
        L39:
            java.lang.Object r0 = p7.n.c(r0, r4)
            r9.x(r3, r1)
            goto L5d
        L41:
            boolean r5 = r4 instanceof k7.z1
            if (r5 == 0) goto L5d
            p7.h0 r5 = m7.c.z()
            boolean r5 = r9.r(r3, r4, r5)
            if (r5 == 0) goto L1a
            goto L39
        L50:
            p7.h0 r5 = m7.c.z()
            boolean r4 = r9.r(r3, r4, r5)
            if (r4 == 0) goto L1a
            r9.p()
        L5d:
            int r3 = r3 + (-1)
            goto Lc
        L60:
            p7.e r9 = r9.g()
            m7.j r9 = (m7.j) r9
            goto L6
        L67:
            if (r0 == 0) goto L8d
            boolean r9 = r0 instanceof java.util.ArrayList
            if (r9 != 0) goto L73
            k7.z1 r0 = (k7.z1) r0
            r8.k0(r0)
            goto L8d
        L73:
            java.lang.String r9 = "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }"
            d7.k.c(r0, r9)
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r9 = r0.size()
            int r9 = r9 - r1
        L7f:
            if (r2 >= r9) goto L8d
            java.lang.Object r10 = r0.get(r9)
            k7.z1 r10 = (k7.z1) r10
            r8.k0(r10)
            int r9 = r9 + (-1)
            goto L7f
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: m7.b.v(m7.j, long):void");
    }

    private final Object v0(j jVar, int i8, long j8, Object obj) {
        h0 h0Var;
        h0 h0Var2;
        h0 h0Var3;
        h0 h0Var4;
        h0 h0Var5;
        h0 h0Var6;
        h0 h0Var7;
        h0 h0Var8;
        h0 h0Var9;
        h0 h0Var10;
        h0 h0Var11;
        h0 h0Var12;
        h0 h0Var13;
        h0 h0Var14;
        h0 h0Var15;
        h0 h0Var16;
        while (true) {
            Object w7 = jVar.w(i8);
            if (w7 != null) {
                h0Var5 = c.f9664e;
                if (w7 != h0Var5) {
                    if (w7 == c.f9663d) {
                        h0Var6 = c.f9668i;
                        if (jVar.r(i8, w7, h0Var6)) {
                            C();
                            return jVar.y(i8);
                        }
                    } else {
                        h0Var7 = c.f9669j;
                        if (w7 == h0Var7) {
                            h0Var8 = c.f9674o;
                            return h0Var8;
                        }
                        h0Var9 = c.f9667h;
                        if (w7 == h0Var9) {
                            h0Var10 = c.f9674o;
                            return h0Var10;
                        } else if (w7 == c.z()) {
                            C();
                            h0Var11 = c.f9674o;
                            return h0Var11;
                        } else {
                            h0Var12 = c.f9666g;
                            if (w7 != h0Var12) {
                                h0Var13 = c.f9665f;
                                if (jVar.r(i8, w7, h0Var13)) {
                                    boolean z7 = w7 instanceof u;
                                    if (z7) {
                                        w7 = ((u) w7).f9696a;
                                    }
                                    if (r0(w7, jVar, i8)) {
                                        h0Var16 = c.f9668i;
                                        jVar.A(i8, h0Var16);
                                        C();
                                        return jVar.y(i8);
                                    }
                                    h0Var14 = c.f9669j;
                                    jVar.A(i8, h0Var14);
                                    jVar.x(i8, false);
                                    if (z7) {
                                        C();
                                    }
                                    h0Var15 = c.f9674o;
                                    return h0Var15;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
            if (j8 < (f9642h.get(this) & 1152921504606846975L)) {
                h0Var = c.f9667h;
                if (jVar.r(i8, w7, h0Var)) {
                    C();
                    h0Var2 = c.f9674o;
                    return h0Var2;
                }
            } else if (obj == null) {
                h0Var3 = c.f9673n;
                return h0Var3;
            } else if (jVar.r(i8, w7, obj)) {
                C();
                h0Var4 = c.f9672m;
                return h0Var4;
            }
        }
    }

    private final j w() {
        j jVar = f9648n.get(this);
        j jVar2 = (j) f9646l.get(this);
        if (jVar2.f10143g > ((j) jVar).f10143g) {
            jVar = jVar2;
        }
        j jVar3 = (j) f9647m.get(this);
        if (jVar3.f10143g > ((j) jVar).f10143g) {
            jVar = jVar3;
        }
        return (j) p7.d.b((p7.e) jVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int w0(j jVar, int i8, Object obj, long j8, Object obj2, boolean z7) {
        h0 h0Var;
        h0 h0Var2;
        h0 h0Var3;
        jVar.B(i8, obj);
        if (z7) {
            return x0(jVar, i8, obj, j8, obj2, z7);
        }
        Object w7 = jVar.w(i8);
        if (w7 == null) {
            if (t(j8)) {
                if (jVar.r(i8, null, c.f9663d)) {
                    return 1;
                }
            } else if (obj2 == null) {
                return 3;
            } else {
                if (jVar.r(i8, null, obj2)) {
                    return 2;
                }
            }
        } else if (w7 instanceof z1) {
            jVar.s(i8);
            if (q0(w7, obj)) {
                h0Var3 = c.f9668i;
                jVar.A(i8, h0Var3);
                f0();
                return 0;
            }
            h0Var = c.f9670k;
            Object t8 = jVar.t(i8, h0Var);
            h0Var2 = c.f9670k;
            if (t8 != h0Var2) {
                jVar.x(i8, true);
            }
            return 5;
        }
        return x0(jVar, i8, obj, j8, obj2, z7);
    }

    private final int x0(j jVar, int i8, Object obj, long j8, Object obj2, boolean z7) {
        h0 h0Var;
        h0 h0Var2;
        h0 h0Var3;
        h0 h0Var4;
        h0 h0Var5;
        h0 h0Var6;
        h0 h0Var7;
        while (true) {
            Object w7 = jVar.w(i8);
            if (w7 != null) {
                h0Var2 = c.f9664e;
                if (w7 != h0Var2) {
                    h0Var3 = c.f9670k;
                    if (w7 == h0Var3) {
                        jVar.s(i8);
                        return 5;
                    }
                    h0Var4 = c.f9667h;
                    if (w7 == h0Var4) {
                        jVar.s(i8);
                        return 5;
                    }
                    h0 z8 = c.z();
                    jVar.s(i8);
                    if (w7 == z8) {
                        A();
                        return 4;
                    }
                    if (w7 instanceof u) {
                        w7 = ((u) w7).f9696a;
                    }
                    if (q0(w7, obj)) {
                        h0Var7 = c.f9668i;
                        jVar.A(i8, h0Var7);
                        f0();
                        return 0;
                    }
                    h0Var5 = c.f9670k;
                    Object t8 = jVar.t(i8, h0Var5);
                    h0Var6 = c.f9670k;
                    if (t8 != h0Var6) {
                        jVar.x(i8, true);
                    }
                    return 5;
                } else if (jVar.r(i8, w7, c.f9663d)) {
                    return 1;
                }
            } else if (!t(j8) || z7) {
                if (z7) {
                    h0Var = c.f9669j;
                    if (jVar.r(i8, null, h0Var)) {
                        jVar.x(i8, false);
                        return 4;
                    }
                } else if (obj2 == null) {
                    return 3;
                } else {
                    if (jVar.r(i8, null, obj2)) {
                        return 2;
                    }
                }
            } else if (jVar.r(i8, null, c.f9663d)) {
                return 1;
            }
        }
    }

    private final void y(long j8) {
        j0(z(j8));
    }

    private final void y0(long j8) {
        long j9;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f9643i;
        do {
            j9 = atomicLongFieldUpdater.get(this);
            if (j9 >= j8) {
                return;
            }
        } while (!f9643i.compareAndSet(this, j9, j8));
    }

    private final j z(long j8) {
        j w7 = w();
        if (V()) {
            long X = X(w7);
            if (X != -1) {
                B(X);
            }
        }
        v(w7, j8);
        return w7;
    }

    private final void z0(long j8) {
        long j9;
        long w7;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f9642h;
        do {
            j9 = atomicLongFieldUpdater.get(this);
            long j10 = 1152921504606846975L & j9;
            if (j10 >= j8) {
                return;
            }
            w7 = c.w(j10, (int) (j9 >> 60));
        } while (!f9642h.compareAndSet(this, j9, w7));
    }

    public final void A0(long j8) {
        int i8;
        long j9;
        long v7;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long v8;
        long j10;
        long v9;
        if (W()) {
            return;
        }
        do {
        } while (G() <= j8);
        i8 = c.f9662c;
        for (int i9 = 0; i9 < i8; i9++) {
            long G = G();
            if (G == (4611686018427387903L & f9645k.get(this)) && G == G()) {
                return;
            }
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = f9645k;
        do {
            j9 = atomicLongFieldUpdater2.get(this);
            v7 = c.v(j9 & 4611686018427387903L, true);
        } while (!atomicLongFieldUpdater2.compareAndSet(this, j9, v7));
        while (true) {
            long G2 = G();
            atomicLongFieldUpdater = f9645k;
            long j11 = atomicLongFieldUpdater.get(this);
            long j12 = j11 & 4611686018427387903L;
            boolean z7 = (4611686018427387904L & j11) != 0;
            if (G2 == j12 && G2 == G()) {
                break;
            } else if (!z7) {
                v8 = c.v(j12, true);
                atomicLongFieldUpdater.compareAndSet(this, j11, v8);
            }
        }
        do {
            j10 = atomicLongFieldUpdater.get(this);
            v9 = c.v(j10 & 4611686018427387903L, false);
        } while (!atomicLongFieldUpdater.compareAndSet(this, j10, v9));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void B(long j8) {
        h0 h0Var;
        q0 d8;
        j jVar = (j) f9647m.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f9643i;
            long j9 = atomicLongFieldUpdater.get(this);
            if (j8 < Math.max(this.f9651e + j9, G())) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j9, j9 + 1)) {
                int i8 = c.f9661b;
                long j10 = j9 / i8;
                int i9 = (int) (j9 % i8);
                if (jVar.f10143g != j10) {
                    j E = E(j10, jVar);
                    if (E == null) {
                        continue;
                    } else {
                        jVar = E;
                    }
                }
                Object u02 = u0(jVar, i9, j9, null);
                h0Var = c.f9674o;
                if (u02 != h0Var) {
                    jVar.b();
                    c7.l lVar = this.f9652f;
                    if (lVar != null && (d8 = z.d(lVar, u02, null, 2, null)) != null) {
                        throw d8;
                    }
                } else if (j9 < L()) {
                    jVar.b();
                }
            }
        }
    }

    protected final Throwable H() {
        return (Throwable) f9649o.get(this);
    }

    public final long J() {
        return f9643i.get(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Throwable K() {
        Throwable H = H();
        return H == null ? new n("Channel was closed") : H;
    }

    public final long L() {
        return f9642h.get(this) & 1152921504606846975L;
    }

    public final boolean M() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9647m;
            j jVar = (j) atomicReferenceFieldUpdater.get(this);
            long J = J();
            if (L() <= J) {
                return false;
            }
            int i8 = c.f9661b;
            long j8 = J / i8;
            if (jVar.f10143g == j8 || (jVar = E(j8, jVar)) != null) {
                jVar.b();
                if (Q(jVar, (int) (J % i8), J)) {
                    return true;
                }
                f9643i.compareAndSet(this, J, J + 1);
            } else if (((j) atomicReferenceFieldUpdater.get(this)).f10143g < j8) {
                return false;
            }
        }
    }

    public boolean S() {
        return T(f9642h.get(this));
    }

    protected boolean V() {
        return false;
    }

    @Override // m7.t
    public void a(c7.l lVar) {
        h0 h0Var;
        h0 h0Var2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        h0 h0Var3;
        h0 h0Var4;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f9650p;
        if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater2, this, null, lVar)) {
            return;
        }
        do {
            Object obj = atomicReferenceFieldUpdater2.get(this);
            h0Var = c.f9676q;
            if (obj != h0Var) {
                h0Var2 = c.f9677r;
                if (obj == h0Var2) {
                    throw new IllegalStateException("Another handler was already registered and successfully invoked".toString());
                }
                throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
            }
            atomicReferenceFieldUpdater = f9650p;
            h0Var3 = c.f9676q;
            h0Var4 = c.f9677r;
        } while (!androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, h0Var3, h0Var4));
        lVar.g(H());
    }

    protected void c0() {
    }

    @Override // m7.s
    public final void d(CancellationException cancellationException) {
        u(cancellationException);
    }

    @Override // m7.t
    public boolean f(Throwable th) {
        return x(th, false);
    }

    protected void f0() {
    }

    protected void g0() {
    }

    @Override // m7.s
    public f iterator() {
        return new a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00bb, code lost:
        return m7.h.f9684a.c(r6.r.f11372a);
     */
    @Override // m7.t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object l(java.lang.Object r15) {
        /*
            r14 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = m7.b.f9642h
            long r0 = r0.get(r14)
            boolean r0 = r14.p0(r0)
            if (r0 == 0) goto L13
            m7.h$b r15 = m7.h.f9684a
            java.lang.Object r15 = r15.b()
            return r15
        L13:
            p7.h0 r8 = m7.c.j()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = i()
            java.lang.Object r0 = r0.get(r14)
            m7.j r0 = (m7.j) r0
        L21:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = j()
            long r1 = r1.getAndIncrement(r14)
            r3 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r9 = r1 & r3
            boolean r11 = k(r14, r1)
            int r1 = m7.c.f9661b
            long r2 = (long) r1
            long r2 = r9 / r2
            long r4 = (long) r1
            long r4 = r9 % r4
            int r12 = (int) r4
            long r4 = r0.f10143g
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L4e
            m7.j r1 = c(r14, r2, r0)
            if (r1 != 0) goto L4c
            if (r11 == 0) goto L21
            goto L8e
        L4c:
            r13 = r1
            goto L4f
        L4e:
            r13 = r0
        L4f:
            r0 = r14
            r1 = r13
            r2 = r12
            r3 = r15
            r4 = r9
            r6 = r8
            r7 = r11
            int r0 = s(r0, r1, r2, r3, r4, r6, r7)
            if (r0 == 0) goto Lb0
            r1 = 1
            if (r0 == r1) goto Lb3
            r1 = 2
            if (r0 == r1) goto L89
            r1 = 3
            if (r0 == r1) goto L7d
            r1 = 4
            if (r0 == r1) goto L71
            r1 = 5
            if (r0 == r1) goto L6c
            goto L6f
        L6c:
            r13.b()
        L6f:
            r0 = r13
            goto L21
        L71:
            long r0 = r14.J()
            int r15 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r15 >= 0) goto L8e
            r13.b()
            goto L8e
        L7d:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "unexpected"
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        L89:
            if (r11 == 0) goto L99
            r13.p()
        L8e:
            m7.h$b r15 = m7.h.f9684a
            java.lang.Throwable r0 = r14.K()
            java.lang.Object r15 = r15.a(r0)
            goto Lbb
        L99:
            boolean r15 = r8 instanceof k7.z1
            if (r15 == 0) goto La0
            k7.z1 r8 = (k7.z1) r8
            goto La1
        La0:
            r8 = 0
        La1:
            if (r8 == 0) goto La6
            q(r14, r8, r13, r12)
        La6:
            r13.p()
            m7.h$b r15 = m7.h.f9684a
            java.lang.Object r15 = r15.b()
            goto Lbb
        Lb0:
            r13.b()
        Lb3:
            m7.h$b r15 = m7.h.f9684a
            r6.r r0 = r6.r.f11372a
            java.lang.Object r15 = r15.c(r0)
        Lbb:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: m7.b.l(java.lang.Object):java.lang.Object");
    }

    @Override // m7.t
    public Object m(Object obj, t6.d dVar) {
        return n0(this, obj, dVar);
    }

    @Override // m7.t
    public boolean o() {
        return U(f9642h.get(this));
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x01b1, code lost:
        r3 = (m7.j) r3.e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01b8, code lost:
        if (r3 != null) goto L94;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01b1 A[EDGE_INSN: B:98:0x01b1->B:80:0x01b1 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 483
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: m7.b.toString():java.lang.String");
    }

    public boolean u(Throwable th) {
        if (th == null) {
            th = new CancellationException("Channel was cancelled");
        }
        return x(th, true);
    }

    protected boolean x(Throwable th, boolean z7) {
        h0 h0Var;
        if (z7) {
            Y();
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9649o;
        h0Var = c.f9678s;
        boolean a8 = androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, h0Var, th);
        if (z7) {
            Z();
        } else {
            a0();
        }
        A();
        c0();
        if (a8) {
            P();
        }
        return a8;
    }
}
