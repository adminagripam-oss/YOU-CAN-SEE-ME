package b1;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class b implements Collection, Set {

    /* renamed from: i  reason: collision with root package name */
    private static final int[] f4500i = new int[0];

    /* renamed from: j  reason: collision with root package name */
    private static final Object[] f4501j = new Object[0];

    /* renamed from: k  reason: collision with root package name */
    private static Object[] f4502k;

    /* renamed from: l  reason: collision with root package name */
    private static int f4503l;

    /* renamed from: m  reason: collision with root package name */
    private static Object[] f4504m;

    /* renamed from: n  reason: collision with root package name */
    private static int f4505n;

    /* renamed from: e  reason: collision with root package name */
    private int[] f4506e;

    /* renamed from: f  reason: collision with root package name */
    Object[] f4507f;

    /* renamed from: g  reason: collision with root package name */
    int f4508g;

    /* renamed from: h  reason: collision with root package name */
    private f f4509h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends f {
        a() {
        }

        @Override // b1.f
        protected void a() {
            b.this.clear();
        }

        @Override // b1.f
        protected Object b(int i8, int i9) {
            return b.this.f4507f[i8];
        }

        @Override // b1.f
        protected Map c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // b1.f
        protected int d() {
            return b.this.f4508g;
        }

        @Override // b1.f
        protected int e(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // b1.f
        protected int f(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // b1.f
        protected void g(Object obj, Object obj2) {
            b.this.add(obj);
        }

        @Override // b1.f
        protected void h(int i8) {
            b.this.n(i8);
        }

        @Override // b1.f
        protected Object i(int i8, Object obj) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public b() {
        this(0);
    }

    private void b(int i8) {
        if (i8 == 8) {
            synchronized (b.class) {
                Object[] objArr = f4504m;
                if (objArr != null) {
                    this.f4507f = objArr;
                    f4504m = (Object[]) objArr[0];
                    this.f4506e = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f4505n--;
                    return;
                }
            }
        } else if (i8 == 4) {
            synchronized (b.class) {
                Object[] objArr2 = f4502k;
                if (objArr2 != null) {
                    this.f4507f = objArr2;
                    f4502k = (Object[]) objArr2[0];
                    this.f4506e = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f4503l--;
                    return;
                }
            }
        }
        this.f4506e = new int[i8];
        this.f4507f = new Object[i8];
    }

    private static void j(int[] iArr, Object[] objArr, int i8) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (f4505n < 10) {
                    objArr[0] = f4504m;
                    objArr[1] = iArr;
                    for (int i9 = i8 - 1; i9 >= 2; i9--) {
                        objArr[i9] = null;
                    }
                    f4504m = objArr;
                    f4505n++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (b.class) {
                if (f4503l < 10) {
                    objArr[0] = f4502k;
                    objArr[1] = iArr;
                    for (int i10 = i8 - 1; i10 >= 2; i10--) {
                        objArr[i10] = null;
                    }
                    f4502k = objArr;
                    f4503l++;
                }
            }
        }
    }

    private f k() {
        if (this.f4509h == null) {
            this.f4509h = new a();
        }
        return this.f4509h;
    }

    private int l(Object obj, int i8) {
        int i9 = this.f4508g;
        if (i9 == 0) {
            return -1;
        }
        int a8 = c.a(this.f4506e, i9, i8);
        if (a8 >= 0 && !obj.equals(this.f4507f[a8])) {
            int i10 = a8 + 1;
            while (i10 < i9 && this.f4506e[i10] == i8) {
                if (obj.equals(this.f4507f[i10])) {
                    return i10;
                }
                i10++;
            }
            for (int i11 = a8 - 1; i11 >= 0 && this.f4506e[i11] == i8; i11--) {
                if (obj.equals(this.f4507f[i11])) {
                    return i11;
                }
            }
            return ~i10;
        }
        return a8;
    }

    private int m() {
        int i8 = this.f4508g;
        if (i8 == 0) {
            return -1;
        }
        int a8 = c.a(this.f4506e, i8, 0);
        if (a8 >= 0 && this.f4507f[a8] != null) {
            int i9 = a8 + 1;
            while (i9 < i8 && this.f4506e[i9] == 0) {
                if (this.f4507f[i9] == null) {
                    return i9;
                }
                i9++;
            }
            for (int i10 = a8 - 1; i10 >= 0 && this.f4506e[i10] == 0; i10--) {
                if (this.f4507f[i10] == null) {
                    return i10;
                }
            }
            return ~i9;
        }
        return a8;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        int i8;
        int l8;
        if (obj == null) {
            l8 = m();
            i8 = 0;
        } else {
            int hashCode = obj.hashCode();
            i8 = hashCode;
            l8 = l(obj, hashCode);
        }
        if (l8 >= 0) {
            return false;
        }
        int i9 = ~l8;
        int i10 = this.f4508g;
        int[] iArr = this.f4506e;
        if (i10 >= iArr.length) {
            int i11 = 8;
            if (i10 >= 8) {
                i11 = (i10 >> 1) + i10;
            } else if (i10 < 4) {
                i11 = 4;
            }
            Object[] objArr = this.f4507f;
            b(i11);
            int[] iArr2 = this.f4506e;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f4507f, 0, objArr.length);
            }
            j(iArr, objArr, this.f4508g);
        }
        int i12 = this.f4508g;
        if (i9 < i12) {
            int[] iArr3 = this.f4506e;
            int i13 = i9 + 1;
            System.arraycopy(iArr3, i9, iArr3, i13, i12 - i9);
            Object[] objArr2 = this.f4507f;
            System.arraycopy(objArr2, i9, objArr2, i13, this.f4508g - i9);
        }
        this.f4506e[i9] = i8;
        this.f4507f[i9] = obj;
        this.f4508g++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        f(this.f4508g + collection.size());
        boolean z7 = false;
        for (Object obj : collection) {
            z7 |= add(obj);
        }
        return z7;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i8 = this.f4508g;
        if (i8 != 0) {
            j(this.f4506e, this.f4507f, i8);
            this.f4506e = f4500i;
            this.f4507f = f4501j;
            this.f4508g = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i8 = 0; i8 < this.f4508g; i8++) {
                try {
                    if (!set.contains(o(i8))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public void f(int i8) {
        int[] iArr = this.f4506e;
        if (iArr.length < i8) {
            Object[] objArr = this.f4507f;
            b(i8);
            int i9 = this.f4508g;
            if (i9 > 0) {
                System.arraycopy(iArr, 0, this.f4506e, 0, i9);
                System.arraycopy(objArr, 0, this.f4507f, 0, this.f4508g);
            }
            j(iArr, objArr, this.f4508g);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f4506e;
        int i8 = this.f4508g;
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            i9 += iArr[i10];
        }
        return i9;
    }

    public int indexOf(Object obj) {
        return obj == null ? m() : l(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f4508g <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        return k().m().iterator();
    }

    public Object n(int i8) {
        Object[] objArr = this.f4507f;
        Object obj = objArr[i8];
        int i9 = this.f4508g;
        if (i9 <= 1) {
            j(this.f4506e, objArr, i9);
            this.f4506e = f4500i;
            this.f4507f = f4501j;
            this.f4508g = 0;
        } else {
            int[] iArr = this.f4506e;
            if (iArr.length <= 8 || i9 >= iArr.length / 3) {
                int i10 = i9 - 1;
                this.f4508g = i10;
                if (i8 < i10) {
                    int i11 = i8 + 1;
                    System.arraycopy(iArr, i11, iArr, i8, i10 - i8);
                    Object[] objArr2 = this.f4507f;
                    System.arraycopy(objArr2, i11, objArr2, i8, this.f4508g - i8);
                }
                this.f4507f[this.f4508g] = null;
            } else {
                b(i9 > 8 ? i9 + (i9 >> 1) : 8);
                this.f4508g--;
                if (i8 > 0) {
                    System.arraycopy(iArr, 0, this.f4506e, 0, i8);
                    System.arraycopy(objArr, 0, this.f4507f, 0, i8);
                }
                int i12 = this.f4508g;
                if (i8 < i12) {
                    int i13 = i8 + 1;
                    System.arraycopy(iArr, i13, this.f4506e, i8, i12 - i8);
                    System.arraycopy(objArr, i13, this.f4507f, i8, this.f4508g - i8);
                }
            }
        }
        return obj;
    }

    public Object o(int i8) {
        return this.f4507f[i8];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            n(indexOf);
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection collection) {
        boolean z7 = false;
        for (Object obj : collection) {
            z7 |= remove(obj);
        }
        return z7;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        boolean z7 = false;
        for (int i8 = this.f4508g - 1; i8 >= 0; i8--) {
            if (!collection.contains(this.f4507f[i8])) {
                n(i8);
                z7 = true;
            }
        }
        return z7;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.f4508g;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i8 = this.f4508g;
        Object[] objArr = new Object[i8];
        System.arraycopy(this.f4507f, 0, objArr, 0, i8);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f4508g * 14);
        sb.append('{');
        for (int i8 = 0; i8 < this.f4508g; i8++) {
            if (i8 > 0) {
                sb.append(", ");
            }
            Object o8 = o(i8);
            if (o8 != this) {
                sb.append(o8);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i8) {
        if (i8 == 0) {
            this.f4506e = f4500i;
            this.f4507f = f4501j;
        } else {
            b(i8);
        }
        this.f4508g = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray(Object[] objArr) {
        if (objArr.length < this.f4508g) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.f4508g);
        }
        System.arraycopy(this.f4507f, 0, objArr, 0, this.f4508g);
        int length = objArr.length;
        int i8 = this.f4508g;
        if (length > i8) {
            objArr[i8] = null;
        }
        return objArr;
    }
}
