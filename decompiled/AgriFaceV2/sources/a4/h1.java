package a4;

import java.util.Iterator;
/* loaded from: classes.dex */
final class h1 extends s0 {

    /* renamed from: g  reason: collision with root package name */
    private final transient r0 f614g;

    /* renamed from: h  reason: collision with root package name */
    private final transient p0 f615h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h1(r0 r0Var, p0 p0Var) {
        this.f614g = r0Var;
        this.f615h = p0Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a4.k0
    public final int b(Object[] objArr, int i8) {
        return this.f615h.b(objArr, 0);
    }

    @Override // a4.k0, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f614g.get(obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.f615h.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f614g.size();
    }
}
