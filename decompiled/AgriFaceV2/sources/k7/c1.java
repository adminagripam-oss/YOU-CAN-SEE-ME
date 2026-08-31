package k7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/* loaded from: classes.dex */
final class c1 extends g1 {

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f8722j = AtomicIntegerFieldUpdater.newUpdater(c1.class, "_invoked");
    private volatile int _invoked;

    /* renamed from: i  reason: collision with root package name */
    private final c7.l f8723i;

    public c1(c7.l lVar) {
        this.f8723i = lVar;
    }

    @Override // c7.l
    public /* bridge */ /* synthetic */ Object g(Object obj) {
        w((Throwable) obj);
        return r6.r.f11372a;
    }

    @Override // k7.w
    public void w(Throwable th) {
        if (f8722j.compareAndSet(this, 0, 1)) {
            this.f8723i.g(th);
        }
    }
}
