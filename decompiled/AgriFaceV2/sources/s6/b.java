package s6;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
/* loaded from: classes.dex */
public abstract class b extends s6.a implements List {

    /* renamed from: e  reason: collision with root package name */
    public static final a f11586e = new a(null);

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final void a(int i8, int i9) {
            if (i8 < 0 || i8 >= i9) {
                throw new IndexOutOfBoundsException("index: " + i8 + ", size: " + i9);
            }
        }

        public final void b(int i8, int i9) {
            if (i8 < 0 || i8 > i9) {
                throw new IndexOutOfBoundsException("index: " + i8 + ", size: " + i9);
            }
        }

        public final void c(int i8, int i9, int i10) {
            if (i8 < 0 || i9 > i10) {
                throw new IndexOutOfBoundsException("fromIndex: " + i8 + ", toIndex: " + i9 + ", size: " + i10);
            } else if (i8 <= i9) {
            } else {
                throw new IllegalArgumentException("fromIndex: " + i8 + " > toIndex: " + i9);
            }
        }

        public final int d(int i8, int i9) {
            int i10 = i8 + (i8 >> 1);
            if (i10 - i9 < 0) {
                i10 = i9;
            }
            return i10 - 2147483639 > 0 ? i9 > 2147483639 ? Integer.MAX_VALUE : 2147483639 : i10;
        }

        public final boolean e(Collection collection, Collection collection2) {
            d7.k.e(collection, "c");
            d7.k.e(collection2, "other");
            if (collection.size() != collection2.size()) {
                return false;
            }
            Iterator it = collection2.iterator();
            for (Object obj : collection) {
                if (!d7.k.a(obj, it.next())) {
                    return false;
                }
            }
            return true;
        }

        public final int f(Collection collection) {
            d7.k.e(collection, "c");
            Iterator it = collection.iterator();
            int i8 = 1;
            while (it.hasNext()) {
                Object next = it.next();
                i8 = (i8 * 31) + (next != null ? next.hashCode() : 0);
            }
            return i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s6.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0149b implements Iterator {

        /* renamed from: e  reason: collision with root package name */
        private int f11587e;

        public C0149b() {
        }

        protected final int b() {
            return this.f11587e;
        }

        protected final void c(int i8) {
            this.f11587e = i8;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f11587e < b.this.size();
        }

        @Override // java.util.Iterator
        public Object next() {
            if (hasNext()) {
                b bVar = b.this;
                int i8 = this.f11587e;
                this.f11587e = i8 + 1;
                return bVar.get(i8);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* loaded from: classes.dex */
    private class c extends C0149b implements ListIterator {
        public c(int i8) {
            super();
            b.f11586e.b(i8, b.this.size());
            c(i8);
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return b() > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return b();
        }

        @Override // java.util.ListIterator
        public Object previous() {
            if (hasPrevious()) {
                b bVar = b.this;
                c(b() - 1);
                return bVar.get(b());
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return b() - 1;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* loaded from: classes.dex */
    private static final class d extends b implements RandomAccess {

        /* renamed from: f  reason: collision with root package name */
        private final b f11590f;

        /* renamed from: g  reason: collision with root package name */
        private final int f11591g;

        /* renamed from: h  reason: collision with root package name */
        private int f11592h;

        public d(b bVar, int i8, int i9) {
            d7.k.e(bVar, "list");
            this.f11590f = bVar;
            this.f11591g = i8;
            b.f11586e.c(i8, i9, bVar.size());
            this.f11592h = i9 - i8;
        }

        @Override // s6.a
        public int b() {
            return this.f11592h;
        }

        @Override // s6.b, java.util.List
        public Object get(int i8) {
            b.f11586e.a(i8, this.f11592h);
            return this.f11590f.get(this.f11591g + i8);
        }
    }

    @Override // java.util.List
    public void add(int i8, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i8, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            return f11586e.e(this, (Collection) obj);
        }
        return false;
    }

    @Override // java.util.List
    public abstract Object get(int i8);

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return f11586e.f(this);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        int i8 = 0;
        for (Object obj2 : this) {
            if (d7.k.a(obj2, obj)) {
                return i8;
            }
            i8++;
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new C0149b();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (d7.k.a(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return new c(0);
    }

    @Override // java.util.List
    public Object remove(int i8) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public Object set(int i8, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List subList(int i8, int i9) {
        return new d(this, i8, i9);
    }

    @Override // java.util.List
    public ListIterator listIterator(int i8) {
        return new c(i8);
    }
}
