package s6;

import java.util.Collection;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d implements Collection {

    /* renamed from: e  reason: collision with root package name */
    private final Object[] f11593e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f11594f;

    public d(Object[] objArr, boolean z7) {
        d7.k.e(objArr, "values");
        this.f11593e = objArr;
        this.f11594f = z7;
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public int b() {
        return this.f11593e.length;
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return j.f(this.f11593e, obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection collection) {
        d7.k.e(collection, "elements");
        Collection<Object> collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        for (Object obj : collection2) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.f11593e.length == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return d7.b.a(this.f11593e);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return b();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return m.a(this.f11593e, this.f11594f);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] objArr) {
        d7.k.e(objArr, "array");
        return d7.f.b(this, objArr);
    }
}
