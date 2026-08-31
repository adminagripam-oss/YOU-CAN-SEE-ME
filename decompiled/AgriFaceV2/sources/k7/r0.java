package k7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* loaded from: classes.dex */
public abstract class r0 extends s0 implements k0 {

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f8781j = AtomicReferenceFieldUpdater.newUpdater(r0.class, Object.class, "_queue");

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f8782k = AtomicReferenceFieldUpdater.newUpdater(r0.class, Object.class, "_delayed");

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f8783l = AtomicIntegerFieldUpdater.newUpdater(r0.class, "_isCompleted");
    private volatile Object _delayed;
    private volatile int _isCompleted = 0;
    private volatile Object _queue;

    /* loaded from: classes.dex */
    public static final class a extends p7.n0 {
    }

    private final void d0() {
        p7.h0 h0Var;
        p7.h0 h0Var2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8781j;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f8781j;
                h0Var = u0.f8791b;
                if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater2, this, null, h0Var)) {
                    return;
                }
            } else if (obj instanceof p7.u) {
                ((p7.u) obj).d();
                return;
            } else {
                h0Var2 = u0.f8791b;
                if (obj == h0Var2) {
                    return;
                }
                p7.u uVar = new p7.u(8, true);
                d7.k.c(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                uVar.a((Runnable) obj);
                if (androidx.concurrent.futures.b.a(f8781j, this, obj, uVar)) {
                    return;
                }
            }
        }
    }

    private final Runnable e0() {
        p7.h0 h0Var;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8781j;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                return null;
            }
            if (obj instanceof p7.u) {
                d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                p7.u uVar = (p7.u) obj;
                Object j8 = uVar.j();
                if (j8 != p7.u.f10190h) {
                    return (Runnable) j8;
                }
                androidx.concurrent.futures.b.a(f8781j, this, obj, uVar.i());
            } else {
                h0Var = u0.f8791b;
                if (obj == h0Var) {
                    return null;
                }
                if (androidx.concurrent.futures.b.a(f8781j, this, obj, null)) {
                    d7.k.c(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                    return (Runnable) obj;
                }
            }
        }
    }

    private final boolean g0(Runnable runnable) {
        p7.h0 h0Var;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8781j;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (h0()) {
                return false;
            }
            if (obj == null) {
                if (androidx.concurrent.futures.b.a(f8781j, this, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof p7.u) {
                d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                p7.u uVar = (p7.u) obj;
                int a8 = uVar.a(runnable);
                if (a8 == 0) {
                    return true;
                }
                if (a8 == 1) {
                    androidx.concurrent.futures.b.a(f8781j, this, obj, uVar.i());
                } else if (a8 == 2) {
                    return false;
                }
            } else {
                h0Var = u0.f8791b;
                if (obj == h0Var) {
                    return false;
                }
                p7.u uVar2 = new p7.u(8, true);
                d7.k.c(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                uVar2.a((Runnable) obj);
                uVar2.a(runnable);
                if (androidx.concurrent.futures.b.a(f8781j, this, obj, uVar2)) {
                    return true;
                }
            }
        }
    }

    private final boolean h0() {
        return f8783l.get(this) != 0;
    }

    private final void k0() {
        c.a();
        System.nanoTime();
        a aVar = (a) f8782k.get(this);
        if (aVar != null) {
            aVar.d();
        }
    }

    private final void m0(boolean z7) {
        f8783l.set(this, z7 ? 1 : 0);
    }

    @Override // k7.a0
    public final void P(t6.g gVar, Runnable runnable) {
        f0(runnable);
    }

    @Override // k7.q0
    protected long V() {
        p7.h0 h0Var;
        if (super.V() == 0) {
            return 0L;
        }
        Object obj = f8781j.get(this);
        if (obj != null) {
            if (!(obj instanceof p7.u)) {
                h0Var = u0.f8791b;
                return obj == h0Var ? Long.MAX_VALUE : 0L;
            } else if (!((p7.u) obj).g()) {
                return 0L;
            }
        }
        a aVar = (a) f8782k.get(this);
        if (aVar != null) {
            aVar.c();
        }
        return Long.MAX_VALUE;
    }

    public void f0(Runnable runnable) {
        if (g0(runnable)) {
            c0();
        } else {
            i0.f8737m.f0(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean i0() {
        p7.h0 h0Var;
        if (Z()) {
            a aVar = (a) f8782k.get(this);
            if (aVar == null || aVar.b()) {
                Object obj = f8781j.get(this);
                if (obj != null) {
                    if (obj instanceof p7.u) {
                        return ((p7.u) obj).g();
                    }
                    h0Var = u0.f8791b;
                    if (obj != h0Var) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public long j0() {
        if (a0()) {
            return 0L;
        }
        a aVar = (a) f8782k.get(this);
        if (aVar != null && !aVar.b()) {
            c.a();
            System.nanoTime();
            synchronized (aVar) {
                aVar.a();
            }
        }
        Runnable e02 = e0();
        if (e02 != null) {
            e02.run();
            return 0L;
        }
        return V();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void l0() {
        f8781j.set(this, null);
        f8782k.set(this, null);
    }

    @Override // k7.q0
    public void shutdown() {
        v1.f8794a.b();
        m0(true);
        d0();
        do {
        } while (j0() <= 0);
        k0();
    }
}
