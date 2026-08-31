package g0;

import android.util.Rational;
import android.util.Size;
import b0.g0;
import b0.g3;
import b0.p1;
import d0.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
class l {

    /* renamed from: a  reason: collision with root package name */
    private final int f7095a;

    /* renamed from: b  reason: collision with root package name */
    private final int f7096b;

    /* renamed from: c  reason: collision with root package name */
    private final Rational f7097c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f7098d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(g0 g0Var, Rational rational) {
        this.f7095a = g0Var.c();
        this.f7096b = g0Var.d();
        this.f7097c = rational;
        boolean z7 = true;
        if (rational != null && rational.getNumerator() < rational.getDenominator()) {
            z7 = false;
        }
        this.f7098d = z7;
    }

    private static Size a(Size size, int i8, int i9, int i10) {
        return (size == null || !e(i8, i9, i10)) ? size : new Size(size.getHeight(), size.getWidth());
    }

    private static Rational b(Size size, List list) {
        if (size == null) {
            return null;
        }
        for (Rational rational : k.k(list)) {
            if (d0.a.a(size, rational)) {
                return rational;
            }
        }
        return new Rational(size.getWidth(), size.getHeight());
    }

    private Rational c(p1 p1Var, List list) {
        if (p1Var.t()) {
            return k.n(p1Var.w(), this.f7098d);
        }
        Size d8 = d(p1Var);
        if (d8 != null) {
            return b(d8, list);
        }
        return null;
    }

    private Size d(p1 p1Var) {
        return a(p1Var.S(null), p1Var.V(0), this.f7096b, this.f7095a);
    }

    private static boolean e(int i8, int i9, int i10) {
        int a8 = d0.c.a(d0.c.b(i8), i10, 1 == i9);
        return a8 == 90 || a8 == 270;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List f(List list, g3 g3Var) {
        if (list.isEmpty()) {
            return list;
        }
        ArrayList<Size> arrayList = new ArrayList(list);
        Collections.sort(arrayList, new d0.e(true));
        ArrayList arrayList2 = new ArrayList();
        p1 p1Var = (p1) g3Var;
        Size i8 = p1Var.i(null);
        Size size = (Size) arrayList.get(0);
        if (i8 == null || k0.d.c(size) < k0.d.c(i8)) {
            i8 = size;
        }
        Size d8 = d(p1Var);
        Size size2 = k0.d.f8305c;
        int c8 = k0.d.c(size2);
        if (k0.d.c(i8) < c8) {
            size2 = k0.d.f8303a;
        } else if (d8 != null && k0.d.c(d8) < c8) {
            size2 = d8;
        }
        for (Size size3 : arrayList) {
            if (k0.d.c(size3) <= k0.d.c(i8) && k0.d.c(size3) >= k0.d.c(size2) && !arrayList2.contains(size3)) {
                arrayList2.add(size3);
            }
        }
        if (arrayList2.isEmpty()) {
            throw new IllegalArgumentException("All supported output sizes are filtered out according to current resolution selection settings. \nminSize = " + size2 + "\nmaxSize = " + i8 + "\ninitial size list: " + arrayList);
        }
        Rational c9 = c(p1Var, arrayList2);
        if (d8 == null) {
            d8 = p1Var.L(null);
        }
        ArrayList arrayList3 = new ArrayList();
        new HashMap();
        if (c9 == null) {
            arrayList3.addAll(arrayList2);
            if (d8 != null) {
                k.q(arrayList3, d8, true);
            }
        } else {
            Map o8 = k.o(arrayList2);
            if (d8 != null) {
                for (Rational rational : o8.keySet()) {
                    k.q((List) o8.get(rational), d8, true);
                }
            }
            ArrayList<Rational> arrayList4 = new ArrayList(o8.keySet());
            Collections.sort(arrayList4, new a.C0076a(c9, this.f7097c));
            for (Rational rational2 : arrayList4) {
                for (Size size4 : (List) o8.get(rational2)) {
                    if (!arrayList3.contains(size4)) {
                        arrayList3.add(size4);
                    }
                }
            }
        }
        return arrayList3;
    }
}
