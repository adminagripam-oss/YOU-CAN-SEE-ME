package p7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import k7.v1;
/* loaded from: classes.dex */
public final class j extends k7.l0 implements v6.e, t6.d {

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10151l = AtomicReferenceFieldUpdater.newUpdater(j.class, Object.class, "_reusableCancellableContinuation");
    private volatile Object _reusableCancellableContinuation;

    /* renamed from: h  reason: collision with root package name */
    public final k7.a0 f10152h;

    /* renamed from: i  reason: collision with root package name */
    public final t6.d f10153i;

    /* renamed from: j  reason: collision with root package name */
    public Object f10154j;

    /* renamed from: k  reason: collision with root package name */
    public final Object f10155k;

    public j(k7.a0 a0Var, t6.d dVar) {
        super(-1);
        this.f10152h = a0Var;
        this.f10153i = dVar;
        this.f10154j = k.a();
        this.f10155k = l0.b(getContext());
    }

    private final k7.k m() {
        Object obj = f10151l.get(this);
        if (obj instanceof k7.k) {
            return (k7.k) obj;
        }
        return null;
    }

    @Override // k7.l0
    public void b(Object obj, Throwable th) {
        if (obj instanceof k7.v) {
            ((k7.v) obj).f8793b.g(th);
        }
    }

    @Override // k7.l0
    public t6.d c() {
        return this;
    }

    @Override // v6.e
    public v6.e e() {
        t6.d dVar = this.f10153i;
        if (dVar instanceof v6.e) {
            return (v6.e) dVar;
        }
        return null;
    }

    @Override // t6.d
    public t6.g getContext() {
        return this.f10153i.getContext();
    }

    @Override // k7.l0
    public Object h() {
        Object obj = this.f10154j;
        this.f10154j = k.a();
        return obj;
    }

    @Override // t6.d
    public void i(Object obj) {
        t6.g context = this.f10153i.getContext();
        Object d8 = k7.y.d(obj, null, 1, null);
        if (this.f10152h.Q(context)) {
            this.f10154j = d8;
            this.f8748g = 0;
            this.f10152h.P(context, this);
            return;
        }
        k7.q0 a8 = v1.f8794a.a();
        if (a8.Y()) {
            this.f10154j = d8;
            this.f8748g = 0;
            a8.U(this);
            return;
        }
        a8.W(true);
        try {
            t6.g context2 = getContext();
            Object c8 = l0.c(context2, this.f10155k);
            this.f10153i.i(obj);
            r6.r rVar = r6.r.f11372a;
            l0.a(context2, c8);
            do {
            } while (a8.a0());
        } finally {
            try {
            } finally {
            }
        }
    }

    public final void j() {
        do {
        } while (f10151l.get(this) == k.f10158b);
    }

    public final k7.k l() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10151l;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                f10151l.set(this, k.f10158b);
                return null;
            } else if (obj instanceof k7.k) {
                if (androidx.concurrent.futures.b.a(f10151l, this, obj, k.f10158b)) {
                    return (k7.k) obj;
                }
            } else if (obj != k.f10158b && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
    }

    public final boolean n() {
        return f10151l.get(this) != null;
    }

    public final boolean o(Throwable th) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10151l;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            h0 h0Var = k.f10158b;
            if (d7.k.a(obj, h0Var)) {
                if (androidx.concurrent.futures.b.a(f10151l, this, h0Var, th)) {
                    return true;
                }
            } else if (obj instanceof Throwable) {
                return true;
            } else {
                if (androidx.concurrent.futures.b.a(f10151l, this, obj, null)) {
                    return false;
                }
            }
        }
    }

    public final void q() {
        j();
        k7.k m8 = m();
        if (m8 != null) {
            m8.r();
        }
    }

    public final Throwable r(k7.j jVar) {
        h0 h0Var;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10151l;
        do {
            Object obj = atomicReferenceFieldUpdater.get(this);
            h0Var = k.f10158b;
            if (obj != h0Var) {
                if (obj instanceof Throwable) {
                    if (androidx.concurrent.futures.b.a(f10151l, this, obj, null)) {
                        return (Throwable) obj;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!androidx.concurrent.futures.b.a(f10151l, this, h0Var, jVar));
        return null;
    }

    public String toString() {
        return "DispatchedContinuation[" + this.f10152h + ", " + k7.h0.c(this.f10153i) + ']';
    }
}
