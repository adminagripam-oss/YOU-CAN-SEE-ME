package g0;

import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import b0.g0;
import b0.g3;
import b0.p1;
import d0.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import y.f1;
/* loaded from: classes.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private final g0 f7090a;

    /* renamed from: b  reason: collision with root package name */
    private final int f7091b;

    /* renamed from: c  reason: collision with root package name */
    private final int f7092c;

    /* renamed from: d  reason: collision with root package name */
    private final Rational f7093d;

    /* renamed from: e  reason: collision with root package name */
    private final l f7094e;

    public k(g0 g0Var, Size size) {
        this.f7090a = g0Var;
        this.f7091b = g0Var.c();
        this.f7092c = g0Var.d();
        Rational h8 = size != null ? h(size) : i(g0Var);
        this.f7093d = h8;
        this.f7094e = new l(g0Var, h8);
    }

    private static LinkedHashMap a(List list, o0.a aVar, Rational rational) {
        return b(o(list), aVar, rational);
    }

    private static LinkedHashMap b(Map map, o0.a aVar, Rational rational) {
        boolean z7 = true;
        if (rational != null && rational.getNumerator() < rational.getDenominator()) {
            z7 = false;
        }
        Rational n8 = n(aVar.b(), z7);
        if (aVar.a() == 0) {
            Rational n9 = n(aVar.b(), z7);
            Iterator it = new ArrayList(map.keySet()).iterator();
            while (it.hasNext()) {
                Rational rational2 = (Rational) it.next();
                if (!rational2.equals(n9)) {
                    map.remove(rational2);
                }
            }
        }
        ArrayList<Rational> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList, new a.C0076a(n8, rational));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Rational rational3 : arrayList) {
            linkedHashMap.put(rational3, (List) map.get(rational3));
        }
        return linkedHashMap;
    }

    private List c(List list, o0.c cVar, int i8) {
        if (cVar.a() == 1) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(list);
            arrayList.addAll(this.f7090a.h(i8));
            Collections.sort(arrayList, new d0.e(true));
            return arrayList;
        }
        return list;
    }

    private static void d(LinkedHashMap linkedHashMap, Size size) {
        int c8 = k0.d.c(size);
        for (Rational rational : linkedHashMap.keySet()) {
            List<Size> list = (List) linkedHashMap.get(rational);
            ArrayList arrayList = new ArrayList();
            for (Size size2 : list) {
                if (k0.d.c(size2) <= c8) {
                    arrayList.add(size2);
                }
            }
            list.clear();
            list.addAll(arrayList);
        }
    }

    private static List e(List list, o0.b bVar, int i8, int i9, int i10) {
        if (bVar == null) {
            return list;
        }
        List a8 = bVar.a(new ArrayList(list), d0.c.a(d0.c.b(i8), i9, i10 == 1));
        if (list.containsAll(a8)) {
            return a8;
        }
        throw new IllegalArgumentException("The returned sizes list of the resolution filter must be a subset of the provided sizes list.");
    }

    private static void f(LinkedHashMap linkedHashMap, o0.d dVar) {
        if (dVar == null) {
            return;
        }
        for (Rational rational : linkedHashMap.keySet()) {
            g((List) linkedHashMap.get(rational), dVar);
        }
    }

    private static void g(List list, o0.d dVar) {
        if (list.isEmpty()) {
            return;
        }
        Integer valueOf = Integer.valueOf(dVar.b());
        if (dVar.equals(o0.d.f9817c)) {
            return;
        }
        Size a8 = dVar.a();
        int intValue = valueOf.intValue();
        if (intValue == 0) {
            s(list, a8);
        } else if (intValue == 1) {
            q(list, a8, true);
        } else if (intValue == 2) {
            q(list, a8, false);
        } else if (intValue == 3) {
            r(list, a8, true);
        } else if (intValue != 4) {
        } else {
            r(list, a8, false);
        }
    }

    private Rational h(Size size) {
        return new Rational(size.getWidth(), size.getHeight());
    }

    private Rational i(g0 g0Var) {
        List l8 = g0Var.l(256);
        if (l8.isEmpty()) {
            return null;
        }
        Size size = (Size) Collections.max(l8, new d0.e());
        return new Rational(size.getWidth(), size.getHeight());
    }

    private List j(List list, int i8) {
        List l8 = l(list, i8);
        if (l8 == null) {
            l8 = this.f7090a.l(i8);
        }
        ArrayList arrayList = new ArrayList(l8);
        Collections.sort(arrayList, new d0.e(true));
        if (arrayList.isEmpty()) {
            f1.l("SupportedOutputSizesCollector", "The retrieved supported resolutions from camera info internal is empty. Format is " + i8 + ".");
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List k(List list) {
        boolean z7;
        ArrayList arrayList = new ArrayList();
        arrayList.add(d0.a.f6351a);
        arrayList.add(d0.a.f6353c);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            Rational rational = new Rational(size.getWidth(), size.getHeight());
            if (!arrayList.contains(rational)) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z7 = false;
                        break;
                    } else if (d0.a.a(size, (Rational) it2.next())) {
                        z7 = true;
                        break;
                    }
                }
                if (!z7) {
                    arrayList.add(rational);
                }
            }
        }
        return arrayList;
    }

    private List l(List list, int i8) {
        Size[] sizeArr;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                if (((Integer) pair.first).intValue() == i8) {
                    sizeArr = (Size[]) pair.second;
                    break;
                }
            }
        }
        sizeArr = null;
        if (sizeArr == null) {
            return null;
        }
        return Arrays.asList(sizeArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Rational n(int i8, boolean z7) {
        if (i8 != -1) {
            if (i8 == 0) {
                return z7 ? d0.a.f6351a : d0.a.f6352b;
            } else if (i8 == 1) {
                return z7 ? d0.a.f6353c : d0.a.f6354d;
            } else {
                f1.c("SupportedOutputSizesCollector", "Undefined target aspect ratio: " + i8);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map o(List list) {
        HashMap hashMap = new HashMap();
        for (Rational rational : k(list)) {
            hashMap.put(rational, new ArrayList());
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            for (Rational rational2 : hashMap.keySet()) {
                if (d0.a.a(size, rational2)) {
                    ((List) hashMap.get(rational2)).add(size);
                }
            }
        }
        return hashMap;
    }

    public static List p(o0.c cVar, List list, Size size, int i8, Rational rational, int i9, int i10) {
        LinkedHashMap a8 = a(list, cVar.b(), rational);
        if (size != null) {
            d(a8, size);
        }
        f(a8, cVar.d());
        ArrayList arrayList = new ArrayList();
        for (List<Size> list2 : a8.values()) {
            for (Size size2 : list2) {
                if (!arrayList.contains(size2)) {
                    arrayList.add(size2);
                }
            }
        }
        return e(arrayList, cVar.c(), i8, i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void q(List list, Size size, boolean z7) {
        ArrayList arrayList = new ArrayList();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            Size size3 = (Size) list.get(size2);
            if (size3.getWidth() >= size.getWidth() && size3.getHeight() >= size.getHeight()) {
                break;
            }
            arrayList.add(0, size3);
        }
        list.removeAll(arrayList);
        Collections.reverse(list);
        if (z7) {
            list.addAll(arrayList);
        }
    }

    private static void r(List list, Size size, boolean z7) {
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < list.size(); i8++) {
            Size size2 = (Size) list.get(i8);
            if (size2.getWidth() <= size.getWidth() && size2.getHeight() <= size.getHeight()) {
                break;
            }
            arrayList.add(0, size2);
        }
        list.removeAll(arrayList);
        if (z7) {
            list.addAll(arrayList);
        }
    }

    private static void s(List list, Size size) {
        boolean contains = list.contains(size);
        list.clear();
        if (contains) {
            list.add(size);
        }
    }

    public List m(g3 g3Var) {
        p1 p1Var = (p1) g3Var;
        List A = p1Var.A(null);
        if (A != null) {
            return A;
        }
        o0.c E = p1Var.E(null);
        List j8 = j(p1Var.q(null), g3Var.B());
        if (E == null) {
            return this.f7094e.f(j8, g3Var);
        }
        Size i8 = ((p1) g3Var).i(null);
        int V = p1Var.V(0);
        if (!g3Var.N(false)) {
            j8 = c(j8, E, g3Var.B());
        }
        return p(p1Var.y(), j8, i8, V, this.f7093d, this.f7091b, this.f7092c);
    }
}
