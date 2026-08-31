package b1;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    b f4527a;

    /* renamed from: b  reason: collision with root package name */
    c f4528b;

    /* renamed from: c  reason: collision with root package name */
    e f4529c;

    /* loaded from: classes.dex */
    final class a implements Iterator {

        /* renamed from: e  reason: collision with root package name */
        final int f4530e;

        /* renamed from: f  reason: collision with root package name */
        int f4531f;

        /* renamed from: g  reason: collision with root package name */
        int f4532g;

        /* renamed from: h  reason: collision with root package name */
        boolean f4533h = false;

        a(int i8) {
            this.f4530e = i8;
            this.f4531f = f.this.d();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f4532g < this.f4531f;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (hasNext()) {
                Object b8 = f.this.b(this.f4532g, this.f4530e);
                this.f4532g++;
                this.f4533h = true;
                return b8;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f4533h) {
                throw new IllegalStateException();
            }
            int i8 = this.f4532g - 1;
            this.f4532g = i8;
            this.f4531f--;
            this.f4533h = false;
            f.this.h(i8);
        }
    }

    /* loaded from: classes.dex */
    final class b implements Set {
        b() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection collection) {
            int d8 = f.this.d();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                f.this.g(entry.getKey(), entry.getValue());
            }
            return d8 != f.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        /* renamed from: b */
        public boolean add(Map.Entry entry) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            f.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                int e8 = f.this.e(entry.getKey());
                if (e8 < 0) {
                    return false;
                }
                return b1.c.c(f.this.b(e8, 1), entry.getValue());
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection collection) {
            for (Object obj : collection) {
                if (!contains(obj)) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return f.k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i8 = 0;
            for (int d8 = f.this.d() - 1; d8 >= 0; d8--) {
                Object b8 = f.this.b(d8, 0);
                Object b9 = f.this.b(d8, 1);
                i8 += (b8 == null ? 0 : b8.hashCode()) ^ (b9 == null ? 0 : b9.hashCode());
            }
            return i8;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return f.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new d();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return f.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes.dex */
    final class c implements Set {
        c() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            f.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return f.this.e(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection collection) {
            return f.j(f.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return f.k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i8 = 0;
            for (int d8 = f.this.d() - 1; d8 >= 0; d8--) {
                Object b8 = f.this.b(d8, 0);
                i8 += b8 == null ? 0 : b8.hashCode();
            }
            return i8;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return f.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            int e8 = f.this.e(obj);
            if (e8 >= 0) {
                f.this.h(e8);
                return true;
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection collection) {
            return f.o(f.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection collection) {
            return f.p(f.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return f.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return f.this.q(0);
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return f.this.r(objArr, 0);
        }
    }

    /* loaded from: classes.dex */
    final class d implements Iterator, Map.Entry {

        /* renamed from: e  reason: collision with root package name */
        int f4537e;

        /* renamed from: g  reason: collision with root package name */
        boolean f4539g = false;

        /* renamed from: f  reason: collision with root package name */
        int f4538f = -1;

        d() {
            this.f4537e = f.this.d() - 1;
        }

        @Override // java.util.Iterator
        /* renamed from: b */
        public Map.Entry next() {
            if (hasNext()) {
                this.f4538f++;
                this.f4539g = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (this.f4539g) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    return b1.c.c(entry.getKey(), f.this.b(this.f4538f, 0)) && b1.c.c(entry.getValue(), f.this.b(this.f4538f, 1));
                }
                return false;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            if (this.f4539g) {
                return f.this.b(this.f4538f, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            if (this.f4539g) {
                return f.this.b(this.f4538f, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f4538f < this.f4537e;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (this.f4539g) {
                Object b8 = f.this.b(this.f4538f, 0);
                Object b9 = f.this.b(this.f4538f, 1);
                return (b8 == null ? 0 : b8.hashCode()) ^ (b9 != null ? b9.hashCode() : 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f4539g) {
                throw new IllegalStateException();
            }
            f.this.h(this.f4538f);
            this.f4538f--;
            this.f4537e--;
            this.f4539g = false;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (this.f4539g) {
                return f.this.i(this.f4538f, obj);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* loaded from: classes.dex */
    final class e implements Collection {
        e() {
        }

        @Override // java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            f.this.a();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return f.this.f(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            for (Object obj : collection) {
                if (!contains(obj)) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return f.this.d() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new a(1);
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            int f8 = f.this.f(obj);
            if (f8 >= 0) {
                f.this.h(f8);
                return true;
            }
            return false;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            int d8 = f.this.d();
            int i8 = 0;
            boolean z7 = false;
            while (i8 < d8) {
                if (collection.contains(f.this.b(i8, 1))) {
                    f.this.h(i8);
                    i8--;
                    d8--;
                    z7 = true;
                }
                i8++;
            }
            return z7;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            int d8 = f.this.d();
            int i8 = 0;
            boolean z7 = false;
            while (i8 < d8) {
                if (!collection.contains(f.this.b(i8, 1))) {
                    f.this.h(i8);
                    i8--;
                    d8--;
                    z7 = true;
                }
                i8++;
            }
            return z7;
        }

        @Override // java.util.Collection
        public int size() {
            return f.this.d();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return f.this.q(1);
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return f.this.r(objArr, 1);
        }
    }

    public static boolean j(Map map, Collection collection) {
        for (Object obj : collection) {
            if (!map.containsKey(obj)) {
                return false;
            }
        }
        return true;
    }

    public static boolean k(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static boolean o(Map map, Collection collection) {
        int size = map.size();
        for (Object obj : collection) {
            map.remove(obj);
        }
        return size != map.size();
    }

    public static boolean p(Map map, Collection collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    protected abstract void a();

    protected abstract Object b(int i8, int i9);

    protected abstract Map c();

    protected abstract int d();

    protected abstract int e(Object obj);

    protected abstract int f(Object obj);

    protected abstract void g(Object obj, Object obj2);

    protected abstract void h(int i8);

    protected abstract Object i(int i8, Object obj);

    public Set l() {
        if (this.f4527a == null) {
            this.f4527a = new b();
        }
        return this.f4527a;
    }

    public Set m() {
        if (this.f4528b == null) {
            this.f4528b = new c();
        }
        return this.f4528b;
    }

    public Collection n() {
        if (this.f4529c == null) {
            this.f4529c = new e();
        }
        return this.f4529c;
    }

    public Object[] q(int i8) {
        int d8 = d();
        Object[] objArr = new Object[d8];
        for (int i9 = 0; i9 < d8; i9++) {
            objArr[i9] = b(i9, i8);
        }
        return objArr;
    }

    public Object[] r(Object[] objArr, int i8) {
        int d8 = d();
        if (objArr.length < d8) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), d8);
        }
        for (int i9 = 0; i9 < d8; i9++) {
            objArr[i9] = b(i9, i8);
        }
        if (objArr.length > d8) {
            objArr[d8] = null;
        }
        return objArr;
    }
}
