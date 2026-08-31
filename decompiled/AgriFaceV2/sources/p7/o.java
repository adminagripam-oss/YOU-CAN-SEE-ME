package p7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/* loaded from: classes.dex */
public final class o extends k7.a0 implements k7.k0 {

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f10168l = AtomicIntegerFieldUpdater.newUpdater(o.class, "runningWorkers");

    /* renamed from: g  reason: collision with root package name */
    private final k7.a0 f10169g;

    /* renamed from: h  reason: collision with root package name */
    private final int f10170h;

    /* renamed from: i  reason: collision with root package name */
    private final /* synthetic */ k7.k0 f10171i;

    /* renamed from: j  reason: collision with root package name */
    private final t f10172j;

    /* renamed from: k  reason: collision with root package name */
    private final Object f10173k;
    private volatile int runningWorkers;

    /* loaded from: classes.dex */
    private final class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private Runnable f10174e;

        public a(Runnable runnable) {
            this.f10174e = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i8 = 0;
            while (true) {
                try {
                    this.f10174e.run();
                } catch (Throwable th) {
                    k7.c0.a(t6.h.f11916e, th);
                }
                Runnable U = o.this.U();
                if (U == null) {
                    return;
                }
                this.f10174e = U;
                i8++;
                if (i8 >= 16 && o.this.f10169g.Q(o.this)) {
                    o.this.f10169g.P(o.this, this);
                    return;
                }
            }
        }
    }

    public o(k7.a0 a0Var, int i8) {
        this.f10169g = a0Var;
        this.f10170h = i8;
        k7.k0 k0Var = a0Var instanceof k7.k0 ? (k7.k0) a0Var : null;
        this.f10171i = k0Var == null ? k7.j0.a() : k0Var;
        this.f10172j = new t(false);
        this.f10173k = new Object();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Runnable U() {
        while (true) {
            Runnable runnable = (Runnable) this.f10172j.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.f10173k) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f10168l;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f10172j.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    private final boolean V() {
        synchronized (this.f10173k) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f10168l;
            if (atomicIntegerFieldUpdater.get(this) >= this.f10170h) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    @Override // k7.a0
    public void P(t6.g gVar, Runnable runnable) {
        Runnable U;
        this.f10172j.a(runnable);
        if (f10168l.get(this) >= this.f10170h || !V() || (U = U()) == null) {
            return;
        }
        this.f10169g.P(this, new a(U));
    }
}
