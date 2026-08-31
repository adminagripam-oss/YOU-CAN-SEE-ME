package p7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* loaded from: classes.dex */
public abstract class b extends a0 {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10135a = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_consensus");
    private volatile Object _consensus = a.f10134a;

    private final Object c(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10135a;
        Object obj2 = atomicReferenceFieldUpdater.get(this);
        Object obj3 = a.f10134a;
        return obj2 != obj3 ? obj2 : androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, obj3, obj) ? obj : atomicReferenceFieldUpdater.get(this);
    }

    @Override // p7.a0
    public final Object a(Object obj) {
        Object obj2 = f10135a.get(this);
        if (obj2 == a.f10134a) {
            obj2 = c(d(obj));
        }
        b(obj, obj2);
        return obj2;
    }

    public abstract void b(Object obj, Object obj2);

    public abstract Object d(Object obj);
}
