package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.z;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class f1 {

    /* renamed from: a  reason: collision with root package name */
    private static final Class f6069a = C();

    /* renamed from: b  reason: collision with root package name */
    private static final j1 f6070b = D();

    /* renamed from: c  reason: collision with root package name */
    private static final j1 f6071c = new l1();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int A(List list) {
        int i8;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof g0) {
            g0 g0Var = (g0) list;
            i8 = 0;
            while (i9 < size) {
                i8 += k.N(g0Var.k(i9));
                i9++;
            }
        } else {
            i8 = 0;
            while (i9 < size) {
                i8 += k.N(((Long) list.get(i9)).longValue());
                i9++;
            }
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object B(Object obj, int i8, List list, z.c cVar, Object obj2, j1 j1Var) {
        if (cVar == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i9 = 0;
            for (int i10 = 0; i10 < size; i10++) {
                int intValue = ((Integer) list.get(i10)).intValue();
                if (cVar.a(intValue)) {
                    if (i10 != i9) {
                        list.set(i9, Integer.valueOf(intValue));
                    }
                    i9++;
                } else {
                    obj2 = K(obj, i8, intValue, obj2, j1Var);
                }
            }
            if (i9 != size) {
                list.subList(i9, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!cVar.a(intValue2)) {
                    obj2 = K(obj, i8, intValue2, obj2, j1Var);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    private static Class C() {
        if (z0.f6299d) {
            return null;
        }
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static j1 D() {
        try {
            Class E = E();
            if (E == null) {
                return null;
            }
            return (j1) E.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class E() {
        if (z0.f6299d) {
            return null;
        }
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void F(q qVar, Object obj, Object obj2) {
        t c8 = qVar.c(obj2);
        if (c8.d()) {
            return;
        }
        qVar.d(obj).h(c8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void G(k0 k0Var, Object obj, Object obj2, long j8) {
        m1.R(obj, j8, k0Var.a(m1.C(obj, j8), m1.C(obj2, j8)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void H(j1 j1Var, Object obj, Object obj2) {
        j1Var.p(obj, j1Var.k(j1Var.g(obj), j1Var.g(obj2)));
    }

    public static void I(Class cls) {
        Class cls2;
        if (!x.class.isAssignableFrom(cls) && !z0.f6299d && (cls2 = f6069a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean J(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static Object K(Object obj, int i8, int i9, Object obj2, j1 j1Var) {
        if (obj2 == null) {
            obj2 = j1Var.f(obj);
        }
        j1Var.e(obj2, i8, i9);
        return obj2;
    }

    public static j1 L() {
        return f6070b;
    }

    public static j1 M() {
        return f6071c;
    }

    public static void N(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.u(i8, list, z7);
    }

    public static void O(int i8, List list, p1 p1Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.O(i8, list);
    }

    public static void P(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.L(i8, list, z7);
    }

    public static void Q(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.K(i8, list, z7);
    }

    public static void R(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.r(i8, list, z7);
    }

    public static void S(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.l(i8, list, z7);
    }

    public static void T(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.c(i8, list, z7);
    }

    public static void U(int i8, List list, p1 p1Var, d1 d1Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.a(i8, list, d1Var);
    }

    public static void V(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.b(i8, list, z7);
    }

    public static void W(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.J(i8, list, z7);
    }

    public static void X(int i8, List list, p1 p1Var, d1 d1Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.g(i8, list, d1Var);
    }

    public static void Y(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.m(i8, list, z7);
    }

    public static void Z(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.q(i8, list, z7);
    }

    static int a(int i8, a aVar, d1 d1Var) {
        return k.J(i8) + b(aVar, d1Var);
    }

    public static void a0(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.G(i8, list, z7);
    }

    static int b(a aVar, d1 d1Var) {
        return k.x(aVar.b(d1Var));
    }

    public static void b0(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.y(i8, list, z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return z7 ? k.J(i8) + k.x(size) : size * k.d(i8, true);
    }

    public static void c0(int i8, List list, p1 p1Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.D(i8, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(List list) {
        return list.size();
    }

    public static void d0(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.w(i8, list, z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(int i8, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int J = size * k.J(i8);
        for (int i9 = 0; i9 < list.size(); i9++) {
            J += k.g((h) list.get(i9));
        }
        return J;
    }

    public static void e0(int i8, List list, p1 p1Var, boolean z7) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p1Var.t(i8, list, z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int g8 = g(list);
        int J = k.J(i8);
        return z7 ? J + k.x(g8) : g8 + (size * J);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(List list) {
        int i8;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof y) {
            y yVar = (y) list;
            i8 = 0;
            while (i9 < size) {
                i8 += k.k(yVar.k(i9));
                i9++;
            }
        } else {
            i8 = 0;
            while (i9 < size) {
                i8 += k.k(((Integer) list.get(i9)).intValue());
                i9++;
            }
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return z7 ? k.J(i8) + k.x(size * 4) : size * k.l(i8, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return z7 ? k.J(i8) + k.x(size * 8) : size * k.n(i8, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int l(int i8, List list, d1 d1Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i9 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            i9 += k.r(i8, (p0) list.get(i10), d1Var);
        }
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int n8 = n(list);
        int J = k.J(i8);
        return z7 ? J + k.x(n8) : n8 + (size * J);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int n(List list) {
        int i8;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof y) {
            y yVar = (y) list;
            i8 = 0;
            while (i9 < size) {
                i8 += k.u(yVar.k(i9));
                i9++;
            }
        } else {
            i8 = 0;
            while (i9 < size) {
                i8 += k.u(((Integer) list.get(i9)).intValue());
                i9++;
            }
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int o(int i8, List list, boolean z7) {
        if (list.size() == 0) {
            return 0;
        }
        int p8 = p(list);
        return z7 ? k.J(i8) + k.x(p8) : p8 + (list.size() * k.J(i8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int p(List list) {
        int i8;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof g0) {
            g0 g0Var = (g0) list;
            i8 = 0;
            while (i9 < size) {
                i8 += k.w(g0Var.k(i9));
                i9++;
            }
        } else {
            i8 = 0;
            while (i9 < size) {
                i8 += k.w(((Long) list.get(i9)).longValue());
                i9++;
            }
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int q(int i8, Object obj, d1 d1Var) {
        return a(i8, (a) obj, d1Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int r(int i8, List list, d1 d1Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int J = k.J(i8) * size;
        for (int i9 = 0; i9 < size; i9++) {
            J += b((a) list.get(i9), d1Var);
        }
        return J;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int s(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int t8 = t(list);
        int J = k.J(i8);
        return z7 ? J + k.x(t8) : t8 + (size * J);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int t(List list) {
        int i8;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof y) {
            y yVar = (y) list;
            i8 = 0;
            while (i9 < size) {
                i8 += k.E(yVar.k(i9));
                i9++;
            }
        } else {
            i8 = 0;
            while (i9 < size) {
                i8 += k.E(((Integer) list.get(i9)).intValue());
                i9++;
            }
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int u(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int v7 = v(list);
        int J = k.J(i8);
        return z7 ? J + k.x(v7) : v7 + (size * J);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int v(List list) {
        int i8;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof g0) {
            g0 g0Var = (g0) list;
            i8 = 0;
            while (i9 < size) {
                i8 += k.G(g0Var.k(i9));
                i9++;
            }
        } else {
            i8 = 0;
            while (i9 < size) {
                i8 += k.G(((Long) list.get(i9)).longValue());
                i9++;
            }
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int w(int i8, List list) {
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        int J = k.J(i8) * size;
        if (list instanceof c0) {
            c0 c0Var = (c0) list;
            while (i9 < size) {
                Object h8 = c0Var.h(i9);
                J += h8 instanceof h ? k.g((h) h8) : k.I((String) h8);
                i9++;
            }
        } else {
            while (i9 < size) {
                Object obj = list.get(i9);
                J += obj instanceof h ? k.g((h) obj) : k.I((String) obj);
                i9++;
            }
        }
        return J;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int y7 = y(list);
        int J = k.J(i8);
        return z7 ? J + k.x(y7) : y7 + (size * J);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int y(List list) {
        int i8;
        int size = list.size();
        int i9 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof y) {
            y yVar = (y) list;
            i8 = 0;
            while (i9 < size) {
                i8 += k.L(yVar.k(i9));
                i9++;
            }
        } else {
            i8 = 0;
            while (i9 < size) {
                i8 += k.L(((Integer) list.get(i9)).intValue());
                i9++;
            }
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int z(int i8, List list, boolean z7) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int A = A(list);
        int J = k.J(i8);
        return z7 ? J + k.x(A) : A + (size * J);
    }
}
