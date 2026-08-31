package x3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
/* loaded from: classes.dex */
public abstract class v0 extends s0 implements List, RandomAccess {

    /* renamed from: f  reason: collision with root package name */
    private static final z0 f12705f = new t0(w0.f12708i, 0);

    public static v0 o() {
        return w0.f12708i;
    }

    public static v0 p(Collection collection) {
        if (collection instanceof s0) {
            v0 k8 = ((s0) collection).k();
            if (k8.l()) {
                Object[] array = k8.toArray();
                return q(array, array.length);
            }
            return k8;
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        for (int i8 = 0; i8 < length; i8++) {
            if (array2[i8] == null) {
                StringBuilder sb = new StringBuilder(String.valueOf(i8).length() + 9);
                sb.append("at index ");
                sb.append(i8);
                throw new NullPointerException(sb.toString());
            }
        }
        return q(array2, length);
    }

    static v0 q(Object[] objArr, int i8) {
        return i8 == 0 ? w0.f12708i : new w0(objArr, i8);
    }

    @Override // java.util.List
    public final void add(int i8, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i8, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i8 = 0; i8 < size; i8++) {
                        if (o0.a(get(i8), list.get(i8))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!o0.a(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i8 = 1;
        for (int i9 = 0; i9 < size; i9++) {
            i8 = (i8 * 31) + get(i9).hashCode();
        }
        return i8;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i8 = 0; i8 < size; i8++) {
            if (obj.equals(get(i8))) {
                return i8;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // x3.s0
    public final v0 k() {
        return this;
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // x3.s0
    int m(Object[] objArr, int i8) {
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            objArr[i9] = get(i9);
        }
        return size;
    }

    @Override // java.util.List
    /* renamed from: n */
    public v0 subList(int i8, int i9) {
        p0.c(i8, i9, size());
        int i10 = i9 - i8;
        return i10 == size() ? this : i10 == 0 ? w0.f12708i : new u0(this, i8, i10);
    }

    @Override // java.util.List
    /* renamed from: r */
    public final z0 listIterator(int i8) {
        p0.b(i8, size(), "index");
        return isEmpty() ? f12705f : new t0(this, i8);
    }

    @Override // java.util.List
    public final Object remove(int i8) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final Object set(int i8, Object obj) {
        throw new UnsupportedOperationException();
    }
}
