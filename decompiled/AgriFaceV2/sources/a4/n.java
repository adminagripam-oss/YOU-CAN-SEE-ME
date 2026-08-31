package a4;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
/* loaded from: classes.dex */
abstract class n extends AbstractCollection {

    /* renamed from: e  reason: collision with root package name */
    final Object f836e;

    /* renamed from: f  reason: collision with root package name */
    Collection f837f;

    /* renamed from: g  reason: collision with root package name */
    final n f838g;

    /* renamed from: h  reason: collision with root package name */
    final Collection f839h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ q f840i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(q qVar, Object obj, Collection collection, n nVar) {
        this.f840i = qVar;
        this.f836e = obj;
        this.f837f = collection;
        this.f838g = nVar;
        this.f839h = nVar == null ? null : nVar.f837f;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        zzb();
        boolean isEmpty = this.f837f.isEmpty();
        boolean add = this.f837f.add(obj);
        if (add) {
            q.i(this.f840i);
            if (isEmpty) {
                b();
                return true;
            }
            return add;
        }
        return add;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.f837f.addAll(collection);
        if (addAll) {
            q.k(this.f840i, this.f837f.size() - size);
            if (size == 0) {
                b();
                return true;
            }
            return addAll;
        }
        return addAll;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        n nVar = this.f838g;
        if (nVar != null) {
            nVar.b();
        } else {
            q.o(this.f840i).put(this.f836e, this.f837f);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        int size = size();
        if (size == 0) {
            return;
        }
        this.f837f.clear();
        q.l(this.f840i, size);
        f();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        zzb();
        return this.f837f.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection collection) {
        zzb();
        return this.f837f.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        zzb();
        return this.f837f.equals(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f() {
        n nVar = this.f838g;
        if (nVar != null) {
            nVar.f();
        } else if (this.f837f.isEmpty()) {
            q.o(this.f840i).remove(this.f836e);
        }
    }

    @Override // java.util.Collection
    public final int hashCode() {
        zzb();
        return this.f837f.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzb();
        return new m(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        zzb();
        boolean remove = this.f837f.remove(obj);
        if (remove) {
            q.j(this.f840i);
            f();
        }
        return remove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.f837f.removeAll(collection);
        if (removeAll) {
            q.k(this.f840i, this.f837f.size() - size);
            f();
        }
        return removeAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        int size = size();
        boolean retainAll = this.f837f.retainAll(collection);
        if (retainAll) {
            q.k(this.f840i, this.f837f.size() - size);
            f();
        }
        return retainAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        zzb();
        return this.f837f.size();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        zzb();
        return this.f837f.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb() {
        Collection collection;
        n nVar = this.f838g;
        if (nVar != null) {
            nVar.zzb();
            if (this.f838g.f837f != this.f839h) {
                throw new ConcurrentModificationException();
            }
        } else if (!this.f837f.isEmpty() || (collection = (Collection) q.o(this.f840i).get(this.f836e)) == null) {
        } else {
            this.f837f = collection;
        }
    }
}
