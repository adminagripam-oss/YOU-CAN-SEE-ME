package p7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import k7.r1;
/* loaded from: classes.dex */
public abstract class e0 extends e implements r1 {

    /* renamed from: h  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f10142h = AtomicIntegerFieldUpdater.newUpdater(e0.class, "cleanedAndPointers");
    private volatile int cleanedAndPointers;

    /* renamed from: g  reason: collision with root package name */
    public final long f10143g;

    public e0(long j8, e0 e0Var, int i8) {
        super(e0Var);
        this.f10143g = j8;
        this.cleanedAndPointers = i8 << 16;
    }

    @Override // p7.e
    public boolean h() {
        return f10142h.get(this) == n() && !i();
    }

    public final boolean m() {
        return f10142h.addAndGet(this, -65536) == n() && !i();
    }

    public abstract int n();

    public abstract void o(int i8, Throwable th, t6.g gVar);

    public final void p() {
        if (f10142h.incrementAndGet(this) == n()) {
            k();
        }
    }

    public final boolean q() {
        int i8;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f10142h;
        do {
            i8 = atomicIntegerFieldUpdater.get(this);
            if (!(i8 != n() || i())) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i8, 65536 + i8));
        return true;
    }
}
