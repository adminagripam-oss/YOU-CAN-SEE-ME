package a4;

import java.util.Iterator;
/* loaded from: classes.dex */
abstract class m1 implements Iterator {

    /* renamed from: e  reason: collision with root package name */
    final Iterator f798e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m1(Iterator it) {
        it.getClass();
        this.f798e = it;
    }

    abstract Object b(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f798e.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return b(this.f798e.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f798e.remove();
    }
}
