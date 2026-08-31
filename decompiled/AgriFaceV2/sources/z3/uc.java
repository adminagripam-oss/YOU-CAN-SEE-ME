package z3;

import java.util.Iterator;
/* loaded from: classes.dex */
final class uc extends pc {

    /* renamed from: g  reason: collision with root package name */
    private final transient oc f14182g;

    /* renamed from: h  reason: collision with root package name */
    private final transient mc f14183h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public uc(oc ocVar, mc mcVar) {
        this.f14182g = ocVar;
        this.f14183h = mcVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // z3.la
    public final int b(Object[] objArr, int i8) {
        return this.f14183h.b(objArr, 0);
    }

    @Override // z3.la, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f14182g.get(obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.f14183h.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f14182g.size();
    }
}
