package n7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p7.h0;
import r6.k;
import r6.r;
/* loaded from: classes.dex */
final class p extends o7.c {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f9794a = AtomicReferenceFieldUpdater.newUpdater(p.class, Object.class, "_state");
    private volatile Object _state;

    @Override // o7.c
    /* renamed from: d */
    public boolean a(n nVar) {
        h0 h0Var;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9794a;
        if (atomicReferenceFieldUpdater.get(this) != null) {
            return false;
        }
        h0Var = o.f9792a;
        atomicReferenceFieldUpdater.set(this, h0Var);
        return true;
    }

    public final Object e(t6.d dVar) {
        t6.d b8;
        h0 h0Var;
        Object c8;
        Object c9;
        b8 = u6.c.b(dVar);
        k7.k kVar = new k7.k(b8, 1);
        kVar.z();
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9794a;
        h0Var = o.f9792a;
        if (!androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, h0Var, kVar)) {
            k.a aVar = r6.k.f11363e;
            kVar.i(r6.k.a(r.f11372a));
        }
        Object w7 = kVar.w();
        c8 = u6.d.c();
        if (w7 == c8) {
            v6.h.c(dVar);
        }
        c9 = u6.d.c();
        return w7 == c9 ? w7 : r.f11372a;
    }

    @Override // o7.c
    /* renamed from: f */
    public t6.d[] b(n nVar) {
        f9794a.set(this, null);
        return o7.b.f9884a;
    }

    public final void g() {
        h0 h0Var;
        h0 h0Var2;
        h0 h0Var3;
        h0 h0Var4;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9794a;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                return;
            }
            h0Var = o.f9793b;
            if (obj == h0Var) {
                return;
            }
            h0Var2 = o.f9792a;
            if (obj == h0Var2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f9794a;
                h0Var3 = o.f9793b;
                if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater2, this, obj, h0Var3)) {
                    return;
                }
            } else {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = f9794a;
                h0Var4 = o.f9792a;
                if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater3, this, obj, h0Var4)) {
                    k.a aVar = r6.k.f11363e;
                    ((k7.k) obj).i(r6.k.a(r.f11372a));
                    return;
                }
            }
        }
    }

    public final boolean h() {
        h0 h0Var;
        h0 h0Var2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9794a;
        h0Var = o.f9792a;
        Object andSet = atomicReferenceFieldUpdater.getAndSet(this, h0Var);
        d7.k.b(andSet);
        h0Var2 = o.f9793b;
        return andSet == h0Var2;
    }
}
