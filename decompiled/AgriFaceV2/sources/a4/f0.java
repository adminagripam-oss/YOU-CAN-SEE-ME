package a4;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f0 extends AbstractMap implements Serializable {

    /* renamed from: n  reason: collision with root package name */
    private static final Object f537n = new Object();

    /* renamed from: e  reason: collision with root package name */
    private transient Object f538e;

    /* renamed from: f  reason: collision with root package name */
    transient int[] f539f;

    /* renamed from: g  reason: collision with root package name */
    transient Object[] f540g;

    /* renamed from: h  reason: collision with root package name */
    transient Object[] f541h;

    /* renamed from: i  reason: collision with root package name */
    private transient int f542i;

    /* renamed from: j  reason: collision with root package name */
    private transient int f543j;

    /* renamed from: k  reason: collision with root package name */
    private transient Set f544k;

    /* renamed from: l  reason: collision with root package name */
    private transient Set f545l;

    /* renamed from: m  reason: collision with root package name */
    private transient Collection f546m;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f0(int i8) {
        q(12);
    }

    private final void A(int i8) {
        this.f542i = ((32 - Integer.numberOfLeadingZeros(i8)) & 31) | (this.f542i & (-32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int[] B() {
        int[] iArr = this.f539f;
        iArr.getClass();
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] a() {
        Object[] objArr = this.f540g;
        objArr.getClass();
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] b() {
        Object[] objArr = this.f541h;
        objArr.getClass();
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int d(f0 f0Var) {
        int i8 = f0Var.f543j;
        f0Var.f543j = i8 - 1;
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object i(f0 f0Var, int i8) {
        return f0Var.a()[i8];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object l(f0 f0Var, int i8) {
        return f0Var.b()[i8];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object m(f0 f0Var) {
        Object obj = f0Var.f538e;
        obj.getClass();
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void o(f0 f0Var, int i8, Object obj) {
        f0Var.b()[i8] = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int w() {
        return (1 << (this.f542i & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int x(Object obj) {
        if (s()) {
            return -1;
        }
        int a8 = h0.a(obj);
        int w7 = w();
        Object obj2 = this.f538e;
        obj2.getClass();
        int c8 = g0.c(obj2, a8 & w7);
        if (c8 != 0) {
            int i8 = ~w7;
            int i9 = a8 & i8;
            do {
                int i10 = c8 - 1;
                int i11 = B()[i10];
                if ((i11 & i8) == i9 && ze.a(obj, a()[i10])) {
                    return i10;
                }
                c8 = i11 & w7;
            } while (c8 != 0);
            return -1;
        }
        return -1;
    }

    private final int y(int i8, int i9, int i10, int i11) {
        Object d8 = g0.d(i9);
        int i12 = i9 - 1;
        if (i11 != 0) {
            g0.e(d8, i10 & i12, i11 + 1);
        }
        Object obj = this.f538e;
        obj.getClass();
        int[] B = B();
        for (int i13 = 0; i13 <= i8; i13++) {
            int c8 = g0.c(obj, i13);
            while (c8 != 0) {
                int i14 = c8 - 1;
                int i15 = B[i14];
                int i16 = ((~i8) & i15) | i13;
                int i17 = i16 & i12;
                int c9 = g0.c(d8, i17);
                g0.e(d8, i17, c8);
                B[i14] = ((~i12) & i16) | (c9 & i12);
                c8 = i15 & i8;
            }
        }
        this.f538e = d8;
        A(i12);
        return i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object z(Object obj) {
        if (s()) {
            return f537n;
        }
        int w7 = w();
        Object obj2 = this.f538e;
        obj2.getClass();
        int b8 = g0.b(obj, null, w7, obj2, B(), a(), null);
        if (b8 == -1) {
            return f537n;
        }
        Object obj3 = b()[b8];
        r(b8, w7);
        this.f543j--;
        p();
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (s()) {
            return;
        }
        p();
        Map n8 = n();
        if (n8 != null) {
            this.f542i = p1.a(size(), 3, 1073741823);
            n8.clear();
            this.f538e = null;
        } else {
            Arrays.fill(a(), 0, this.f543j, (Object) null);
            Arrays.fill(b(), 0, this.f543j, (Object) null);
            Object obj = this.f538e;
            obj.getClass();
            if (obj instanceof byte[]) {
                Arrays.fill((byte[]) obj, (byte) 0);
            } else if (obj instanceof short[]) {
                Arrays.fill((short[]) obj, (short) 0);
            } else {
                Arrays.fill((int[]) obj, 0);
            }
            Arrays.fill(B(), 0, this.f543j, 0);
        }
        this.f543j = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Map n8 = n();
        return n8 != null ? n8.containsKey(obj) : x(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        Map n8 = n();
        if (n8 == null) {
            for (int i8 = 0; i8 < this.f543j; i8++) {
                if (ze.a(obj, b()[i8])) {
                    return true;
                }
            }
            return false;
        }
        return n8.containsValue(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.f545l;
        if (set == null) {
            z zVar = new z(this);
            this.f545l = zVar;
            return zVar;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int g() {
        return isEmpty() ? -1 : 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Map n8 = n();
        if (n8 != null) {
            return n8.get(obj);
        }
        int x7 = x(obj);
        if (x7 == -1) {
            return null;
        }
        return b()[x7];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int h(int i8) {
        int i9 = i8 + 1;
        if (i9 < this.f543j) {
            return i9;
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.f544k;
        if (set == null) {
            c0 c0Var = new c0(this);
            this.f544k = c0Var;
            return c0Var;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map n() {
        Object obj = this.f538e;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void p() {
        this.f542i += 32;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        int length;
        int min;
        if (s()) {
            c.d(s(), "Arrays already allocated");
            int i8 = this.f542i;
            int max = Math.max(i8 + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > highestOneBit && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = 1073741824;
            }
            int max2 = Math.max(4, highestOneBit);
            this.f538e = g0.d(max2);
            A(max2 - 1);
            this.f539f = new int[i8];
            this.f540g = new Object[i8];
            this.f541h = new Object[i8];
        }
        Map n8 = n();
        if (n8 != null) {
            return n8.put(obj, obj2);
        }
        int[] B = B();
        Object[] a8 = a();
        Object[] b8 = b();
        int i9 = this.f543j;
        int i10 = i9 + 1;
        int a9 = h0.a(obj);
        int w7 = w();
        int i11 = a9 & w7;
        Object obj3 = this.f538e;
        obj3.getClass();
        int c8 = g0.c(obj3, i11);
        if (c8 == 0) {
            if (i10 <= w7) {
                Object obj4 = this.f538e;
                obj4.getClass();
                g0.e(obj4, i11, i10);
                length = B().length;
                if (i10 > length && (min = Math.min(1073741823, (Math.max(1, length >>> 1) + length) | 1)) != length) {
                    this.f539f = Arrays.copyOf(B(), min);
                    this.f540g = Arrays.copyOf(a(), min);
                    this.f541h = Arrays.copyOf(b(), min);
                }
                B()[i9] = (~w7) & a9;
                a()[i9] = obj;
                b()[i9] = obj2;
                this.f543j = i10;
                p();
                return null;
            }
            w7 = y(w7, g0.a(w7), a9, i9);
            length = B().length;
            if (i10 > length) {
                this.f539f = Arrays.copyOf(B(), min);
                this.f540g = Arrays.copyOf(a(), min);
                this.f541h = Arrays.copyOf(b(), min);
            }
            B()[i9] = (~w7) & a9;
            a()[i9] = obj;
            b()[i9] = obj2;
            this.f543j = i10;
            p();
            return null;
        }
        int i12 = ~w7;
        int i13 = a9 & i12;
        int i14 = 0;
        while (true) {
            int i15 = c8 - 1;
            int i16 = B[i15];
            int i17 = i16 & i12;
            if (i17 == i13 && ze.a(obj, a8[i15])) {
                Object obj5 = b8[i15];
                b8[i15] = obj2;
                return obj5;
            }
            int i18 = i16 & w7;
            i14++;
            if (i18 != 0) {
                c8 = i18;
            } else if (i14 >= 9) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(w() + 1, 1.0f);
                int g8 = g();
                while (g8 >= 0) {
                    linkedHashMap.put(a()[g8], b()[g8]);
                    g8 = h(g8);
                }
                this.f538e = linkedHashMap;
                this.f539f = null;
                this.f540g = null;
                this.f541h = null;
                p();
                return linkedHashMap.put(obj, obj2);
            } else if (i10 <= w7) {
                B[i15] = (i10 & w7) | i17;
            }
        }
    }

    final void q(int i8) {
        this.f542i = p1.a(12, 1, 1073741823);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void r(int i8, int i9) {
        Object obj = this.f538e;
        obj.getClass();
        int[] B = B();
        Object[] a8 = a();
        Object[] b8 = b();
        int size = size() - 1;
        if (i8 >= size) {
            a8[i8] = null;
            b8[i8] = null;
            B[i8] = 0;
            return;
        }
        Object obj2 = a8[size];
        a8[i8] = obj2;
        b8[i8] = b8[size];
        a8[size] = null;
        b8[size] = null;
        B[i8] = B[size];
        B[size] = 0;
        int a9 = h0.a(obj2) & i9;
        int c8 = g0.c(obj, a9);
        int i10 = size + 1;
        if (c8 == i10) {
            g0.e(obj, a9, i8 + 1);
            return;
        }
        while (true) {
            int i11 = c8 - 1;
            int i12 = B[i11];
            int i13 = i12 & i9;
            if (i13 == i10) {
                B[i11] = ((i8 + 1) & i9) | (i12 & (~i9));
                return;
            }
            c8 = i13;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Map n8 = n();
        if (n8 != null) {
            return n8.remove(obj);
        }
        Object z7 = z(obj);
        if (z7 == f537n) {
            return null;
        }
        return z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean s() {
        return this.f538e == null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map n8 = n();
        return n8 != null ? n8.size() : this.f543j;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.f546m;
        if (collection == null) {
            e0 e0Var = new e0(this);
            this.f546m = e0Var;
            return e0Var;
        }
        return collection;
    }
}
