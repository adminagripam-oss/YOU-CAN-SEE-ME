package a4;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
class m implements Iterator {

    /* renamed from: e  reason: collision with root package name */
    final Iterator f795e;

    /* renamed from: f  reason: collision with root package name */
    final Collection f796f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ n f797g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(n nVar) {
        this.f797g = nVar;
        Collection collection = nVar.f837f;
        this.f796f = collection;
        this.f795e = collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        this.f797g.zzb();
        if (this.f797g.f837f != this.f796f) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        b();
        return this.f795e.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        b();
        return this.f795e.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f795e.remove();
        q.j(this.f797g.f840i);
        this.f797g.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(n nVar, Iterator it) {
        this.f797g = nVar;
        this.f796f = nVar.f837f;
        this.f795e = it;
    }
}
