package s6;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
public final class e extends c {

    /* renamed from: h  reason: collision with root package name */
    public static final a f11595h = new a(null);

    /* renamed from: i  reason: collision with root package name */
    private static final Object[] f11596i = new Object[0];

    /* renamed from: e  reason: collision with root package name */
    private int f11597e;

    /* renamed from: f  reason: collision with root package name */
    private Object[] f11598f = f11596i;

    /* renamed from: g  reason: collision with root package name */
    private int f11599g;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }
    }

    private final void j(int i8, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f11598f.length;
        while (i8 < length && it.hasNext()) {
            this.f11598f[i8] = it.next();
            i8++;
        }
        int i9 = this.f11597e;
        for (int i10 = 0; i10 < i9 && it.hasNext(); i10++) {
            this.f11598f[i10] = it.next();
        }
        this.f11599g = size() + collection.size();
    }

    private final void k(int i8) {
        Object[] objArr = new Object[i8];
        Object[] objArr2 = this.f11598f;
        i.c(objArr2, objArr, 0, this.f11597e, objArr2.length);
        Object[] objArr3 = this.f11598f;
        int length = objArr3.length;
        int i9 = this.f11597e;
        i.c(objArr3, objArr, length - i9, 0, i9);
        this.f11597e = 0;
        this.f11598f = objArr;
    }

    private final int l(int i8) {
        return i8 == 0 ? j.j(this.f11598f) : i8 - 1;
    }

    private final void m(int i8) {
        int a8;
        if (i8 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f11598f;
        if (i8 <= objArr.length) {
            return;
        }
        if (objArr != f11596i) {
            k(b.f11586e.d(objArr.length, i8));
            return;
        }
        a8 = g7.f.a(i8, 10);
        this.f11598f = new Object[a8];
    }

    private final int n(int i8) {
        if (i8 == j.j(this.f11598f)) {
            return 0;
        }
        return i8 + 1;
    }

    private final int o(int i8) {
        return i8 < 0 ? i8 + this.f11598f.length : i8;
    }

    private final int p(int i8) {
        Object[] objArr = this.f11598f;
        return i8 >= objArr.length ? i8 - objArr.length : i8;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i8, Object obj) {
        b.f11586e.b(i8, size());
        if (i8 == size()) {
            addLast(obj);
        } else if (i8 == 0) {
            addFirst(obj);
        } else {
            m(size() + 1);
            int p8 = p(this.f11597e + i8);
            if (i8 < ((size() + 1) >> 1)) {
                int l8 = l(p8);
                int l9 = l(this.f11597e);
                int i9 = this.f11597e;
                if (l8 >= i9) {
                    Object[] objArr = this.f11598f;
                    objArr[l9] = objArr[i9];
                    i.c(objArr, objArr, i9, i9 + 1, l8 + 1);
                } else {
                    Object[] objArr2 = this.f11598f;
                    i.c(objArr2, objArr2, i9 - 1, i9, objArr2.length);
                    Object[] objArr3 = this.f11598f;
                    objArr3[objArr3.length - 1] = objArr3[0];
                    i.c(objArr3, objArr3, 0, 1, l8 + 1);
                }
                this.f11598f[l8] = obj;
                this.f11597e = l9;
            } else {
                int p9 = p(this.f11597e + size());
                Object[] objArr4 = this.f11598f;
                if (p8 < p9) {
                    i.c(objArr4, objArr4, p8 + 1, p8, p9);
                } else {
                    i.c(objArr4, objArr4, 1, 0, p9);
                    Object[] objArr5 = this.f11598f;
                    objArr5[0] = objArr5[objArr5.length - 1];
                    i.c(objArr5, objArr5, p8 + 1, p8, objArr5.length - 1);
                }
                this.f11598f[p8] = obj;
            }
            this.f11599g = size() + 1;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i8, Collection collection) {
        d7.k.e(collection, "elements");
        b.f11586e.b(i8, size());
        if (collection.isEmpty()) {
            return false;
        }
        if (i8 == size()) {
            return addAll(collection);
        }
        m(size() + collection.size());
        int p8 = p(this.f11597e + size());
        int p9 = p(this.f11597e + i8);
        int size = collection.size();
        if (i8 < ((size() + 1) >> 1)) {
            int i9 = this.f11597e;
            int i10 = i9 - size;
            if (p9 < i9) {
                Object[] objArr = this.f11598f;
                i.c(objArr, objArr, i10, i9, objArr.length);
                Object[] objArr2 = this.f11598f;
                if (size >= p9) {
                    i.c(objArr2, objArr2, objArr2.length - size, 0, p9);
                } else {
                    i.c(objArr2, objArr2, objArr2.length - size, 0, size);
                    Object[] objArr3 = this.f11598f;
                    i.c(objArr3, objArr3, 0, size, p9);
                }
            } else if (i10 >= 0) {
                Object[] objArr4 = this.f11598f;
                i.c(objArr4, objArr4, i10, i9, p9);
            } else {
                Object[] objArr5 = this.f11598f;
                i10 += objArr5.length;
                int i11 = p9 - i9;
                int length = objArr5.length - i10;
                if (length >= i11) {
                    i.c(objArr5, objArr5, i10, i9, p9);
                } else {
                    i.c(objArr5, objArr5, i10, i9, i9 + length);
                    Object[] objArr6 = this.f11598f;
                    i.c(objArr6, objArr6, 0, this.f11597e + length, p9);
                }
            }
            this.f11597e = i10;
            j(o(p9 - size), collection);
        } else {
            int i12 = p9 + size;
            if (p9 < p8) {
                int i13 = size + p8;
                Object[] objArr7 = this.f11598f;
                if (i13 > objArr7.length) {
                    if (i12 >= objArr7.length) {
                        i12 -= objArr7.length;
                    } else {
                        int length2 = p8 - (i13 - objArr7.length);
                        i.c(objArr7, objArr7, 0, length2, p8);
                        Object[] objArr8 = this.f11598f;
                        i.c(objArr8, objArr8, i12, p9, length2);
                    }
                }
                i.c(objArr7, objArr7, i12, p9, p8);
            } else {
                Object[] objArr9 = this.f11598f;
                i.c(objArr9, objArr9, size, 0, p8);
                Object[] objArr10 = this.f11598f;
                if (i12 >= objArr10.length) {
                    i.c(objArr10, objArr10, i12 - objArr10.length, p9, objArr10.length);
                } else {
                    i.c(objArr10, objArr10, 0, objArr10.length - size, objArr10.length);
                    Object[] objArr11 = this.f11598f;
                    i.c(objArr11, objArr11, i12, p9, objArr11.length - size);
                }
            }
            j(p9, collection);
        }
        return true;
    }

    public final void addFirst(Object obj) {
        m(size() + 1);
        int l8 = l(this.f11597e);
        this.f11597e = l8;
        this.f11598f[l8] = obj;
        this.f11599g = size() + 1;
    }

    public final void addLast(Object obj) {
        m(size() + 1);
        this.f11598f[p(this.f11597e + size())] = obj;
        this.f11599g = size() + 1;
    }

    @Override // s6.c
    public int b() {
        return this.f11599g;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int p8 = p(this.f11597e + size());
        int i8 = this.f11597e;
        if (i8 < p8) {
            i.e(this.f11598f, null, i8, p8);
        } else if (!isEmpty()) {
            Object[] objArr = this.f11598f;
            i.e(objArr, null, this.f11597e, objArr.length);
            i.e(this.f11598f, null, 0, p8);
        }
        this.f11597e = 0;
        this.f11599g = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // s6.c
    public Object f(int i8) {
        b.f11586e.a(i8, size());
        if (i8 == n.g(this)) {
            return removeLast();
        }
        if (i8 == 0) {
            return removeFirst();
        }
        int p8 = p(this.f11597e + i8);
        Object obj = this.f11598f[p8];
        if (i8 < (size() >> 1)) {
            int i9 = this.f11597e;
            if (p8 >= i9) {
                Object[] objArr = this.f11598f;
                i.c(objArr, objArr, i9 + 1, i9, p8);
            } else {
                Object[] objArr2 = this.f11598f;
                i.c(objArr2, objArr2, 1, 0, p8);
                Object[] objArr3 = this.f11598f;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i10 = this.f11597e;
                i.c(objArr3, objArr3, i10 + 1, i10, objArr3.length - 1);
            }
            Object[] objArr4 = this.f11598f;
            int i11 = this.f11597e;
            objArr4[i11] = null;
            this.f11597e = n(i11);
        } else {
            int p9 = p(this.f11597e + n.g(this));
            Object[] objArr5 = this.f11598f;
            if (p8 <= p9) {
                i.c(objArr5, objArr5, p8, p8 + 1, p9 + 1);
            } else {
                i.c(objArr5, objArr5, p8, p8 + 1, objArr5.length);
                Object[] objArr6 = this.f11598f;
                objArr6[objArr6.length - 1] = objArr6[0];
                i.c(objArr6, objArr6, 0, 1, p9 + 1);
            }
            this.f11598f[p9] = null;
        }
        this.f11599g = size() - 1;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i8) {
        b.f11586e.a(i8, size());
        return this.f11598f[p(this.f11597e + i8)];
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int p8 = p(this.f11597e + size());
        int i8 = this.f11597e;
        if (i8 < p8) {
            while (i8 < p8) {
                if (!d7.k.a(obj, this.f11598f[i8])) {
                    i8++;
                }
            }
            return -1;
        } else if (i8 < p8) {
            return -1;
        } else {
            int length = this.f11598f.length;
            while (true) {
                if (i8 >= length) {
                    for (int i9 = 0; i9 < p8; i9++) {
                        if (d7.k.a(obj, this.f11598f[i9])) {
                            i8 = i9 + this.f11598f.length;
                        }
                    }
                    return -1;
                } else if (d7.k.a(obj, this.f11598f[i8])) {
                    break;
                } else {
                    i8++;
                }
            }
        }
        return i8 - this.f11597e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int j8;
        int p8 = p(this.f11597e + size());
        int i8 = this.f11597e;
        if (i8 < p8) {
            j8 = p8 - 1;
            if (i8 <= j8) {
                while (!d7.k.a(obj, this.f11598f[j8])) {
                    if (j8 != i8) {
                        j8--;
                    }
                }
                return j8 - this.f11597e;
            }
            return -1;
        }
        if (i8 > p8) {
            int i9 = p8 - 1;
            while (true) {
                if (-1 >= i9) {
                    j8 = j.j(this.f11598f);
                    int i10 = this.f11597e;
                    if (i10 <= j8) {
                        while (!d7.k.a(obj, this.f11598f[j8])) {
                            if (j8 != i10) {
                                j8--;
                            }
                        }
                    }
                } else if (d7.k.a(obj, this.f11598f[i9])) {
                    j8 = i9 + this.f11598f.length;
                    break;
                } else {
                    i9--;
                }
            }
        }
        return -1;
    }

    public final Object q() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection collection) {
        int p8;
        d7.k.e(collection, "elements");
        boolean z7 = false;
        z7 = false;
        z7 = false;
        if (!isEmpty()) {
            if ((this.f11598f.length == 0 ? 1 : null) == null) {
                int p9 = p(this.f11597e + size());
                int i8 = this.f11597e;
                if (i8 < p9) {
                    p8 = i8;
                    while (i8 < p9) {
                        Object obj = this.f11598f[i8];
                        if (!collection.contains(obj)) {
                            this.f11598f[p8] = obj;
                            p8++;
                        } else {
                            z7 = true;
                        }
                        i8++;
                    }
                    i.e(this.f11598f, null, p8, p9);
                } else {
                    int length = this.f11598f.length;
                    boolean z8 = false;
                    int i9 = i8;
                    while (i8 < length) {
                        Object[] objArr = this.f11598f;
                        Object obj2 = objArr[i8];
                        objArr[i8] = null;
                        if (!collection.contains(obj2)) {
                            this.f11598f[i9] = obj2;
                            i9++;
                        } else {
                            z8 = true;
                        }
                        i8++;
                    }
                    p8 = p(i9);
                    for (int i10 = 0; i10 < p9; i10++) {
                        Object[] objArr2 = this.f11598f;
                        Object obj3 = objArr2[i10];
                        objArr2[i10] = null;
                        if (!collection.contains(obj3)) {
                            this.f11598f[p8] = obj3;
                            p8 = n(p8);
                        } else {
                            z8 = true;
                        }
                    }
                    z7 = z8;
                }
                if (z7) {
                    this.f11599g = o(p8 - this.f11597e);
                }
            }
        }
        return z7;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.f11598f;
        int i8 = this.f11597e;
        Object obj = objArr[i8];
        objArr[i8] = null;
        this.f11597e = n(i8);
        this.f11599g = size() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int p8 = p(this.f11597e + n.g(this));
        Object[] objArr = this.f11598f;
        Object obj = objArr[p8];
        objArr[p8] = null;
        this.f11599g = size() - 1;
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection collection) {
        int p8;
        d7.k.e(collection, "elements");
        boolean z7 = false;
        z7 = false;
        z7 = false;
        if (!isEmpty()) {
            if ((this.f11598f.length == 0 ? 1 : null) == null) {
                int p9 = p(this.f11597e + size());
                int i8 = this.f11597e;
                if (i8 < p9) {
                    p8 = i8;
                    while (i8 < p9) {
                        Object obj = this.f11598f[i8];
                        if (collection.contains(obj)) {
                            this.f11598f[p8] = obj;
                            p8++;
                        } else {
                            z7 = true;
                        }
                        i8++;
                    }
                    i.e(this.f11598f, null, p8, p9);
                } else {
                    int length = this.f11598f.length;
                    boolean z8 = false;
                    int i9 = i8;
                    while (i8 < length) {
                        Object[] objArr = this.f11598f;
                        Object obj2 = objArr[i8];
                        objArr[i8] = null;
                        if (collection.contains(obj2)) {
                            this.f11598f[i9] = obj2;
                            i9++;
                        } else {
                            z8 = true;
                        }
                        i8++;
                    }
                    p8 = p(i9);
                    for (int i10 = 0; i10 < p9; i10++) {
                        Object[] objArr2 = this.f11598f;
                        Object obj3 = objArr2[i10];
                        objArr2[i10] = null;
                        if (collection.contains(obj3)) {
                            this.f11598f[p8] = obj3;
                            p8 = n(p8);
                        } else {
                            z8 = true;
                        }
                    }
                    z7 = z8;
                }
                if (z7) {
                    this.f11599g = o(p8 - this.f11597e);
                }
            }
        }
        return z7;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i8, Object obj) {
        b.f11586e.a(i8, size());
        int p8 = p(this.f11597e + i8);
        Object[] objArr = this.f11598f;
        Object obj2 = objArr[p8];
        objArr[p8] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection collection) {
        d7.k.e(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        m(size() + collection.size());
        j(p(this.f11597e + size()), collection);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] objArr) {
        d7.k.e(objArr, "array");
        if (objArr.length < size()) {
            objArr = g.a(objArr, size());
        }
        int p8 = p(this.f11597e + size());
        int i8 = this.f11597e;
        if (i8 < p8) {
            i.d(this.f11598f, objArr, 0, i8, p8, 2, null);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.f11598f;
            i.c(objArr2, objArr, 0, this.f11597e, objArr2.length);
            Object[] objArr3 = this.f11598f;
            i.c(objArr3, objArr, objArr3.length - this.f11597e, 0, p8);
        }
        return m.c(size(), objArr);
    }
}
