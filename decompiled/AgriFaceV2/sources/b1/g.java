package b1;

import java.util.ConcurrentModificationException;
import java.util.Map;
/* loaded from: classes.dex */
public class g {

    /* renamed from: h  reason: collision with root package name */
    static Object[] f4542h;

    /* renamed from: i  reason: collision with root package name */
    static int f4543i;

    /* renamed from: j  reason: collision with root package name */
    static Object[] f4544j;

    /* renamed from: k  reason: collision with root package name */
    static int f4545k;

    /* renamed from: e  reason: collision with root package name */
    int[] f4546e;

    /* renamed from: f  reason: collision with root package name */
    Object[] f4547f;

    /* renamed from: g  reason: collision with root package name */
    int f4548g;

    public g() {
        this.f4546e = c.f4511a;
        this.f4547f = c.f4513c;
        this.f4548g = 0;
    }

    private void a(int i8) {
        if (i8 == 8) {
            synchronized (g.class) {
                Object[] objArr = f4544j;
                if (objArr != null) {
                    this.f4547f = objArr;
                    f4544j = (Object[]) objArr[0];
                    this.f4546e = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f4545k--;
                    return;
                }
            }
        } else if (i8 == 4) {
            synchronized (g.class) {
                Object[] objArr2 = f4542h;
                if (objArr2 != null) {
                    this.f4547f = objArr2;
                    f4542h = (Object[]) objArr2[0];
                    this.f4546e = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f4543i--;
                    return;
                }
            }
        }
        this.f4546e = new int[i8];
        this.f4547f = new Object[i8 << 1];
    }

    private static int b(int[] iArr, int i8, int i9) {
        try {
            return c.a(iArr, i8, i9);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void d(int[] iArr, Object[] objArr, int i8) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (f4545k < 10) {
                    objArr[0] = f4544j;
                    objArr[1] = iArr;
                    for (int i9 = (i8 << 1) - 1; i9 >= 2; i9--) {
                        objArr[i9] = null;
                    }
                    f4544j = objArr;
                    f4545k++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (g.class) {
                if (f4543i < 10) {
                    objArr[0] = f4542h;
                    objArr[1] = iArr;
                    for (int i10 = (i8 << 1) - 1; i10 >= 2; i10--) {
                        objArr[i10] = null;
                    }
                    f4542h = objArr;
                    f4543i++;
                }
            }
        }
    }

    public void c(int i8) {
        int i9 = this.f4548g;
        int[] iArr = this.f4546e;
        if (iArr.length < i8) {
            Object[] objArr = this.f4547f;
            a(i8);
            if (this.f4548g > 0) {
                System.arraycopy(iArr, 0, this.f4546e, 0, i9);
                System.arraycopy(objArr, 0, this.f4547f, 0, i9 << 1);
            }
            d(iArr, objArr, i9);
        }
        if (this.f4548g != i9) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i8 = this.f4548g;
        if (i8 > 0) {
            int[] iArr = this.f4546e;
            Object[] objArr = this.f4547f;
            this.f4546e = c.f4511a;
            this.f4547f = c.f4513c;
            this.f4548g = 0;
            d(iArr, objArr, i8);
        }
        if (this.f4548g > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return f(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return h(obj) >= 0;
    }

    int e(Object obj, int i8) {
        int i9 = this.f4548g;
        if (i9 == 0) {
            return -1;
        }
        int b8 = b(this.f4546e, i9, i8);
        if (b8 >= 0 && !obj.equals(this.f4547f[b8 << 1])) {
            int i10 = b8 + 1;
            while (i10 < i9 && this.f4546e[i10] == i8) {
                if (obj.equals(this.f4547f[i10 << 1])) {
                    return i10;
                }
                i10++;
            }
            for (int i11 = b8 - 1; i11 >= 0 && this.f4546e[i11] == i8; i11--) {
                if (obj.equals(this.f4547f[i11 << 1])) {
                    return i11;
                }
            }
            return ~i10;
        }
        return b8;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (size() != gVar.size()) {
                return false;
            }
            for (int i8 = 0; i8 < this.f4548g; i8++) {
                try {
                    Object i9 = i(i8);
                    Object l8 = l(i8);
                    Object obj2 = gVar.get(i9);
                    if (l8 == null) {
                        if (obj2 != null || !gVar.containsKey(i9)) {
                            return false;
                        }
                    } else if (!l8.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i10 = 0; i10 < this.f4548g; i10++) {
                try {
                    Object i11 = i(i10);
                    Object l9 = l(i10);
                    Object obj3 = map.get(i11);
                    if (l9 == null) {
                        if (obj3 != null || !map.containsKey(i11)) {
                            return false;
                        }
                    } else if (!l9.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public int f(Object obj) {
        return obj == null ? g() : e(obj, obj.hashCode());
    }

    int g() {
        int i8 = this.f4548g;
        if (i8 == 0) {
            return -1;
        }
        int b8 = b(this.f4546e, i8, 0);
        if (b8 >= 0 && this.f4547f[b8 << 1] != null) {
            int i9 = b8 + 1;
            while (i9 < i8 && this.f4546e[i9] == 0) {
                if (this.f4547f[i9 << 1] == null) {
                    return i9;
                }
                i9++;
            }
            for (int i10 = b8 - 1; i10 >= 0 && this.f4546e[i10] == 0; i10--) {
                if (this.f4547f[i10 << 1] == null) {
                    return i10;
                }
            }
            return ~i9;
        }
        return b8;
    }

    public Object get(Object obj) {
        return getOrDefault(obj, null);
    }

    public Object getOrDefault(Object obj, Object obj2) {
        int f8 = f(obj);
        return f8 >= 0 ? this.f4547f[(f8 << 1) + 1] : obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h(Object obj) {
        int i8 = this.f4548g * 2;
        Object[] objArr = this.f4547f;
        if (obj == null) {
            for (int i9 = 1; i9 < i8; i9 += 2) {
                if (objArr[i9] == null) {
                    return i9 >> 1;
                }
            }
            return -1;
        }
        for (int i10 = 1; i10 < i8; i10 += 2) {
            if (obj.equals(objArr[i10])) {
                return i10 >> 1;
            }
        }
        return -1;
    }

    public int hashCode() {
        int[] iArr = this.f4546e;
        Object[] objArr = this.f4547f;
        int i8 = this.f4548g;
        int i9 = 1;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i8) {
            Object obj = objArr[i9];
            i11 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i10];
            i10++;
            i9 += 2;
        }
        return i11;
    }

    public Object i(int i8) {
        return this.f4547f[i8 << 1];
    }

    public boolean isEmpty() {
        return this.f4548g <= 0;
    }

    public Object j(int i8) {
        Object[] objArr = this.f4547f;
        int i9 = i8 << 1;
        Object obj = objArr[i9 + 1];
        int i10 = this.f4548g;
        int i11 = 0;
        if (i10 <= 1) {
            d(this.f4546e, objArr, i10);
            this.f4546e = c.f4511a;
            this.f4547f = c.f4513c;
        } else {
            int i12 = i10 - 1;
            int[] iArr = this.f4546e;
            if (iArr.length <= 8 || i10 >= iArr.length / 3) {
                if (i8 < i12) {
                    int i13 = i8 + 1;
                    int i14 = i12 - i8;
                    System.arraycopy(iArr, i13, iArr, i8, i14);
                    Object[] objArr2 = this.f4547f;
                    System.arraycopy(objArr2, i13 << 1, objArr2, i9, i14 << 1);
                }
                Object[] objArr3 = this.f4547f;
                int i15 = i12 << 1;
                objArr3[i15] = null;
                objArr3[i15 + 1] = null;
            } else {
                a(i10 > 8 ? i10 + (i10 >> 1) : 8);
                if (i10 != this.f4548g) {
                    throw new ConcurrentModificationException();
                }
                if (i8 > 0) {
                    System.arraycopy(iArr, 0, this.f4546e, 0, i8);
                    System.arraycopy(objArr, 0, this.f4547f, 0, i9);
                }
                if (i8 < i12) {
                    int i16 = i8 + 1;
                    int i17 = i12 - i8;
                    System.arraycopy(iArr, i16, this.f4546e, i8, i17);
                    System.arraycopy(objArr, i16 << 1, this.f4547f, i9, i17 << 1);
                }
            }
            i11 = i12;
        }
        if (i10 == this.f4548g) {
            this.f4548g = i11;
            return obj;
        }
        throw new ConcurrentModificationException();
    }

    public Object k(int i8, Object obj) {
        int i9 = (i8 << 1) + 1;
        Object[] objArr = this.f4547f;
        Object obj2 = objArr[i9];
        objArr[i9] = obj;
        return obj2;
    }

    public Object l(int i8) {
        return this.f4547f[(i8 << 1) + 1];
    }

    public Object put(Object obj, Object obj2) {
        int i8;
        int e8;
        int i9 = this.f4548g;
        if (obj == null) {
            e8 = g();
            i8 = 0;
        } else {
            int hashCode = obj.hashCode();
            i8 = hashCode;
            e8 = e(obj, hashCode);
        }
        if (e8 >= 0) {
            int i10 = (e8 << 1) + 1;
            Object[] objArr = this.f4547f;
            Object obj3 = objArr[i10];
            objArr[i10] = obj2;
            return obj3;
        }
        int i11 = ~e8;
        int[] iArr = this.f4546e;
        if (i9 >= iArr.length) {
            int i12 = 8;
            if (i9 >= 8) {
                i12 = (i9 >> 1) + i9;
            } else if (i9 < 4) {
                i12 = 4;
            }
            Object[] objArr2 = this.f4547f;
            a(i12);
            if (i9 != this.f4548g) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f4546e;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f4547f, 0, objArr2.length);
            }
            d(iArr, objArr2, i9);
        }
        if (i11 < i9) {
            int[] iArr3 = this.f4546e;
            int i13 = i11 + 1;
            System.arraycopy(iArr3, i11, iArr3, i13, i9 - i11);
            Object[] objArr3 = this.f4547f;
            System.arraycopy(objArr3, i11 << 1, objArr3, i13 << 1, (this.f4548g - i11) << 1);
        }
        int i14 = this.f4548g;
        if (i9 == i14) {
            int[] iArr4 = this.f4546e;
            if (i11 < iArr4.length) {
                iArr4[i11] = i8;
                Object[] objArr4 = this.f4547f;
                int i15 = i11 << 1;
                objArr4[i15] = obj;
                objArr4[i15 + 1] = obj2;
                this.f4548g = i14 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? put(obj, obj2) : obj3;
    }

    public Object remove(Object obj) {
        int f8 = f(obj);
        if (f8 >= 0) {
            return j(f8);
        }
        return null;
    }

    public Object replace(Object obj, Object obj2) {
        int f8 = f(obj);
        if (f8 >= 0) {
            return k(f8, obj2);
        }
        return null;
    }

    public int size() {
        return this.f4548g;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f4548g * 28);
        sb.append('{');
        for (int i8 = 0; i8 < this.f4548g; i8++) {
            if (i8 > 0) {
                sb.append(", ");
            }
            Object i9 = i(i8);
            if (i9 != this) {
                sb.append(i9);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object l8 = l(i8);
            if (l8 != this) {
                sb.append(l8);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public g(int i8) {
        if (i8 == 0) {
            this.f4546e = c.f4511a;
            this.f4547f = c.f4513c;
        } else {
            a(i8);
        }
        this.f4548g = 0;
    }

    public boolean remove(Object obj, Object obj2) {
        int f8 = f(obj);
        if (f8 >= 0) {
            Object l8 = l(f8);
            if (obj2 == l8 || (obj2 != null && obj2.equals(l8))) {
                j(f8);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean replace(Object obj, Object obj2, Object obj3) {
        int f8 = f(obj);
        if (f8 >= 0) {
            Object l8 = l(f8);
            if (l8 == obj2 || (obj2 != null && obj2.equals(l8))) {
                k(f8, obj3);
                return true;
            }
            return false;
        }
        return false;
    }
}
