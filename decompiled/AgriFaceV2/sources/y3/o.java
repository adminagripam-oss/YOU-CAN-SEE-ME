package y3;

import java.util.Iterator;
/* loaded from: classes.dex */
final class o extends j {

    /* renamed from: g  reason: collision with root package name */
    private final transient i f13257g;

    /* renamed from: h  reason: collision with root package name */
    private final transient f f13258h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(i iVar, f fVar) {
        this.f13257g = iVar;
        this.f13258h = fVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // y3.c
    public final int b(Object[] objArr, int i8) {
        return this.f13258h.b(objArr, 0);
    }

    @Override // y3.c, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f13257g.get(obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.f13258h.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13257g.size();
    }
}
