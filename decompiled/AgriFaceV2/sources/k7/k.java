package k7;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import k7.e1;
/* loaded from: classes.dex */
public class k extends l0 implements j, v6.e, z1 {

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f8741j = AtomicIntegerFieldUpdater.newUpdater(k.class, "_decisionAndIndex");

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f8742k = AtomicReferenceFieldUpdater.newUpdater(k.class, Object.class, "_state");

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f8743l = AtomicReferenceFieldUpdater.newUpdater(k.class, Object.class, "_parentHandle");
    private volatile int _decisionAndIndex;
    private volatile Object _parentHandle;
    private volatile Object _state;

    /* renamed from: h  reason: collision with root package name */
    private final t6.d f8744h;

    /* renamed from: i  reason: collision with root package name */
    private final t6.g f8745i;

    public k(t6.d dVar, int i8) {
        super(i8);
        this.f8744h = dVar;
        this.f8745i = dVar.getContext();
        this._decisionAndIndex = 536870911;
        this._state = d.f8724e;
    }

    private final o0 A() {
        e1 e1Var = (e1) getContext().c(e1.f8727b);
        if (e1Var == null) {
            return null;
        }
        o0 d8 = e1.a.d(e1Var, true, false, new o(this), 2, null);
        androidx.concurrent.futures.b.a(f8743l, this, null, d8);
        return d8;
    }

    private final void B(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8742k;
        while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof d) {
                if (androidx.concurrent.futures.b.a(f8742k, this, obj2, obj)) {
                    return;
                }
            } else if (obj2 instanceof p7.e0) {
                E(obj, obj2);
            } else {
                boolean z7 = obj2 instanceof u;
                if (z7) {
                    u uVar = (u) obj2;
                    if (!uVar.b()) {
                        E(obj, obj2);
                    }
                    if (obj2 instanceof n) {
                        if (!z7) {
                            uVar = null;
                        }
                        Throwable th = uVar != null ? uVar.f8789a : null;
                        d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                        n((p7.e0) obj, th);
                        return;
                    }
                    return;
                } else if (obj2 instanceof t) {
                    t tVar = (t) obj2;
                    tVar.getClass();
                    if (obj instanceof p7.e0) {
                        return;
                    }
                    d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                    androidx.appcompat.app.w.a(obj);
                    if (tVar.c()) {
                        androidx.appcompat.app.w.a(obj);
                        l(null, tVar.f8787d);
                        return;
                    }
                    androidx.appcompat.app.w.a(obj);
                    if (androidx.concurrent.futures.b.a(f8742k, this, obj2, t.b(tVar, null, null, null, null, null, 29, null))) {
                        return;
                    }
                } else if (obj instanceof p7.e0) {
                    return;
                } else {
                    d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                    androidx.appcompat.app.w.a(obj);
                    androidx.appcompat.app.w.a(obj);
                    if (androidx.concurrent.futures.b.a(f8742k, this, obj2, new t(obj2, null, null, null, null, 28, null))) {
                        return;
                    }
                }
            }
        }
    }

    private final boolean D() {
        if (m0.c(this.f8748g)) {
            t6.d dVar = this.f8744h;
            d7.k.c(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (((p7.j) dVar).n()) {
                return true;
            }
        }
        return false;
    }

    private final void E(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    private final void K(Object obj, int i8, c7.l lVar) {
        Object obj2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8742k;
        do {
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof r1)) {
                if (obj2 instanceof n) {
                    n nVar = (n) obj2;
                    if (nVar.c()) {
                        if (lVar != null) {
                            m(lVar, nVar.f8789a);
                            return;
                        }
                        return;
                    }
                }
                j(obj);
                throw new r6.d();
            }
        } while (!androidx.concurrent.futures.b.a(f8742k, this, obj2, M((r1) obj2, obj, i8, lVar, null)));
        s();
        t(i8);
    }

    static /* synthetic */ void L(k kVar, Object obj, int i8, c7.l lVar, int i9, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if ((i9 & 4) != 0) {
            lVar = null;
        }
        kVar.K(obj, i8, lVar);
    }

    private final Object M(r1 r1Var, Object obj, int i8, c7.l lVar, Object obj2) {
        return obj instanceof u ? obj : (m0.b(i8) || obj2 != null) ? (lVar == null && obj2 == null) ? obj : new t(obj, null, lVar, obj2, null, 16, null) : obj;
    }

    private final boolean N() {
        int i8;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f8741j;
        do {
            i8 = atomicIntegerFieldUpdater.get(this);
            int i9 = i8 >> 29;
            if (i9 != 0) {
                if (i9 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!f8741j.compareAndSet(this, i8, 1073741824 + (536870911 & i8)));
        return true;
    }

    private final p7.h0 O(Object obj, Object obj2, c7.l lVar) {
        Object obj3;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8742k;
        do {
            obj3 = atomicReferenceFieldUpdater.get(this);
            if (!(obj3 instanceof r1)) {
                if ((obj3 instanceof t) && obj2 != null && ((t) obj3).f8786c == obj2) {
                    return l.f8747a;
                }
                return null;
            }
        } while (!androidx.concurrent.futures.b.a(f8742k, this, obj3, M((r1) obj3, obj, this.f8748g, lVar, obj2)));
        s();
        return l.f8747a;
    }

    private final boolean P() {
        int i8;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f8741j;
        do {
            i8 = atomicIntegerFieldUpdater.get(this);
            int i9 = i8 >> 29;
            if (i9 != 0) {
                if (i9 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!f8741j.compareAndSet(this, i8, 536870912 + (536870911 & i8)));
        return true;
    }

    private final Void j(Object obj) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
    }

    private final void n(p7.e0 e0Var, Throwable th) {
        int i8 = f8741j.get(this) & 536870911;
        if (!(i8 != 536870911)) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken".toString());
        }
        try {
            e0Var.o(i8, th, getContext());
        } catch (Throwable th2) {
            t6.g context = getContext();
            c0.a(context, new x("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    private final boolean q(Throwable th) {
        if (D()) {
            t6.d dVar = this.f8744h;
            d7.k.c(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            return ((p7.j) dVar).o(th);
        }
        return false;
    }

    private final void s() {
        if (D()) {
            return;
        }
        r();
    }

    private final void t(int i8) {
        if (N()) {
            return;
        }
        m0.a(this, i8);
    }

    private final o0 v() {
        return (o0) f8743l.get(this);
    }

    private final String y() {
        Object x7 = x();
        return x7 instanceof r1 ? "Active" : x7 instanceof n ? "Cancelled" : "Completed";
    }

    public boolean C() {
        return !(x() instanceof r1);
    }

    protected String F() {
        return "CancellableContinuation";
    }

    public final void G(Throwable th) {
        if (q(th)) {
            return;
        }
        o(th);
        s();
    }

    public final void H() {
        Throwable r8;
        t6.d dVar = this.f8744h;
        p7.j jVar = dVar instanceof p7.j ? (p7.j) dVar : null;
        if (jVar == null || (r8 = jVar.r(this)) == null) {
            return;
        }
        r();
        o(r8);
    }

    public final boolean I() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8742k;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if ((obj instanceof t) && ((t) obj).f8786c != null) {
            r();
            return false;
        }
        f8741j.set(this, 536870911);
        atomicReferenceFieldUpdater.set(this, d.f8724e);
        return true;
    }

    public void J(Object obj, c7.l lVar) {
        K(obj, this.f8748g, lVar);
    }

    @Override // k7.z1
    public void a(p7.e0 e0Var, int i8) {
        int i9;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f8741j;
        do {
            i9 = atomicIntegerFieldUpdater.get(this);
            if (!((i9 & 536870911) == 536870911)) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once".toString());
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i9, ((i9 >> 29) << 29) + i8));
        B(e0Var);
    }

    @Override // k7.l0
    public void b(Object obj, Throwable th) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8742k;
        while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof r1) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (obj2 instanceof u) {
                return;
            }
            if (obj2 instanceof t) {
                t tVar = (t) obj2;
                if (!(!tVar.c())) {
                    throw new IllegalStateException("Must be called at most once".toString());
                }
                if (androidx.concurrent.futures.b.a(f8742k, this, obj2, t.b(tVar, null, null, null, null, th, 15, null))) {
                    tVar.d(this, th);
                    return;
                }
            } else if (androidx.concurrent.futures.b.a(f8742k, this, obj2, new t(obj2, null, null, null, th, 14, null))) {
                return;
            }
        }
    }

    @Override // k7.l0
    public final t6.d c() {
        return this.f8744h;
    }

    @Override // k7.l0
    public Throwable d(Object obj) {
        Throwable d8 = super.d(obj);
        if (d8 != null) {
            return d8;
        }
        return null;
    }

    @Override // v6.e
    public v6.e e() {
        t6.d dVar = this.f8744h;
        if (dVar instanceof v6.e) {
            return (v6.e) dVar;
        }
        return null;
    }

    @Override // k7.l0
    public Object f(Object obj) {
        return obj instanceof t ? ((t) obj).f8784a : obj;
    }

    @Override // t6.d
    public t6.g getContext() {
        return this.f8745i;
    }

    @Override // k7.l0
    public Object h() {
        return x();
    }

    @Override // t6.d
    public void i(Object obj) {
        L(this, y.c(obj, this), this.f8748g, null, 4, null);
    }

    @Override // k7.j
    public Object k(Object obj, Object obj2, c7.l lVar) {
        return O(obj, obj2, lVar);
    }

    public final void l(h hVar, Throwable th) {
        try {
            throw null;
        } catch (Throwable th2) {
            t6.g context = getContext();
            c0.a(context, new x("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void m(c7.l lVar, Throwable th) {
        try {
            lVar.g(th);
        } catch (Throwable th2) {
            t6.g context = getContext();
            c0.a(context, new x("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public boolean o(Throwable th) {
        Object obj;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8742k;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof r1)) {
                return false;
            }
        } while (!androidx.concurrent.futures.b.a(f8742k, this, obj, new n(this, th, obj instanceof p7.e0)));
        if (((r1) obj) instanceof p7.e0) {
            n((p7.e0) obj, th);
        }
        s();
        t(this.f8748g);
        return true;
    }

    @Override // k7.j
    public void p(Object obj) {
        t(this.f8748g);
    }

    public final void r() {
        o0 v7 = v();
        if (v7 == null) {
            return;
        }
        v7.dispose();
        f8743l.set(this, q1.f8780e);
    }

    public String toString() {
        return F() + '(' + h0.c(this.f8744h) + "){" + y() + "}@" + h0.b(this);
    }

    public Throwable u(e1 e1Var) {
        return e1Var.E();
    }

    public final Object w() {
        e1 e1Var;
        Object c8;
        boolean D = D();
        if (P()) {
            if (v() == null) {
                A();
            }
            if (D) {
                H();
            }
            c8 = u6.d.c();
            return c8;
        }
        if (D) {
            H();
        }
        Object x7 = x();
        if (x7 instanceof u) {
            throw ((u) x7).f8789a;
        }
        if (!m0.b(this.f8748g) || (e1Var = (e1) getContext().c(e1.f8727b)) == null || e1Var.b()) {
            return f(x7);
        }
        CancellationException E = e1Var.E();
        b(x7, E);
        throw E;
    }

    public final Object x() {
        return f8742k.get(this);
    }

    public void z() {
        o0 A = A();
        if (A != null && C()) {
            A.dispose();
            f8743l.set(this, q1.f8780e);
        }
    }
}
