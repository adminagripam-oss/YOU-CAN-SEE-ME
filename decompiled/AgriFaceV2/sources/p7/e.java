package p7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10140e = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "_next");

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10141f = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "_prev");
    private volatile Object _next;
    private volatile Object _prev;

    public e(e eVar) {
        this._prev = eVar;
    }

    private final e c() {
        e g8 = g();
        while (g8 != null && g8.h()) {
            g8 = (e) f10141f.get(g8);
        }
        return g8;
    }

    private final e d() {
        e e8;
        e e9 = e();
        d7.k.b(e9);
        while (e9.h() && (e8 = e9.e()) != null) {
            e9 = e8;
        }
        return e9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object f() {
        return f10140e.get(this);
    }

    public final void b() {
        f10141f.lazySet(this, null);
    }

    public final e e() {
        Object f8 = f();
        if (f8 == d.a()) {
            return null;
        }
        return (e) f8;
    }

    public final e g() {
        return (e) f10141f.get(this);
    }

    public abstract boolean h();

    public final boolean i() {
        return e() == null;
    }

    public final boolean j() {
        return androidx.concurrent.futures.b.a(f10140e, this, null, d.a());
    }

    public final void k() {
        Object obj;
        if (i()) {
            return;
        }
        while (true) {
            e c8 = c();
            e d8 = d();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10141f;
            do {
                obj = atomicReferenceFieldUpdater.get(d8);
            } while (!androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, d8, obj, ((e) obj) == null ? null : c8));
            if (c8 != null) {
                f10140e.set(c8, d8);
            }
            if (!d8.h() || d8.i()) {
                if (c8 == null || !c8.h()) {
                    return;
                }
            }
        }
    }

    public final boolean l(e eVar) {
        return androidx.concurrent.futures.b.a(f10140e, this, null, eVar);
    }
}
