package k7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/* loaded from: classes.dex */
public class u {

    /* renamed from: b  reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f8788b = AtomicIntegerFieldUpdater.newUpdater(u.class, "_handled");
    private volatile int _handled;

    /* renamed from: a  reason: collision with root package name */
    public final Throwable f8789a;

    public u(Throwable th, boolean z7) {
        this.f8789a = th;
        this._handled = z7 ? 1 : 0;
    }

    public final boolean a() {
        return f8788b.get(this) != 0;
    }

    public final boolean b() {
        return f8788b.compareAndSet(this, 0, 1);
    }

    public String toString() {
        return h0.a(this) + '[' + this.f8789a + ']';
    }

    public /* synthetic */ u(Throwable th, boolean z7, int i8, d7.g gVar) {
        this(th, (i8 & 2) != 0 ? false : z7);
    }
}
