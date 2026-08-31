package p7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* loaded from: classes.dex */
public class t {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10186a = AtomicReferenceFieldUpdater.newUpdater(t.class, Object.class, "_cur");
    private volatile Object _cur;

    public t(boolean z7) {
        this._cur = new u(8, z7);
    }

    public final boolean a(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10186a;
        while (true) {
            u uVar = (u) atomicReferenceFieldUpdater.get(this);
            int a8 = uVar.a(obj);
            if (a8 == 0) {
                return true;
            }
            if (a8 == 1) {
                androidx.concurrent.futures.b.a(f10186a, this, uVar, uVar.i());
            } else if (a8 == 2) {
                return false;
            }
        }
    }

    public final void b() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10186a;
        while (true) {
            u uVar = (u) atomicReferenceFieldUpdater.get(this);
            if (uVar.d()) {
                return;
            }
            androidx.concurrent.futures.b.a(f10186a, this, uVar, uVar.i());
        }
    }

    public final int c() {
        return ((u) f10186a.get(this)).f();
    }

    public final Object d() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10186a;
        while (true) {
            u uVar = (u) atomicReferenceFieldUpdater.get(this);
            Object j8 = uVar.j();
            if (j8 != u.f10190h) {
                return j8;
            }
            androidx.concurrent.futures.b.a(f10186a, this, uVar, uVar.i());
        }
    }
}
