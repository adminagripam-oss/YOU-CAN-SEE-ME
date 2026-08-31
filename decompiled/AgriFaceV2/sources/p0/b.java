package p0;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import b0.g0;
import b0.g3;
import b0.h0;
import b0.p1;
import b0.z1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import y.f1;
/* loaded from: classes.dex */
public class b {

    /* renamed from: h  reason: collision with root package name */
    private static final double f9925h = Math.sqrt(2.3703703703703702d);

    /* renamed from: a  reason: collision with root package name */
    private final Size f9926a;

    /* renamed from: b  reason: collision with root package name */
    private final Rational f9927b;

    /* renamed from: c  reason: collision with root package name */
    private final Rational f9928c;

    /* renamed from: d  reason: collision with root package name */
    private final Set f9929d;

    /* renamed from: e  reason: collision with root package name */
    private final g0.k f9930e;

    /* renamed from: f  reason: collision with root package name */
    private final g0 f9931f;

    /* renamed from: g  reason: collision with root package name */
    private final Map f9932g;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements Comparator {

        /* renamed from: a  reason: collision with root package name */
        private final Rational f9933a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f9934b;

        a(Rational rational, boolean z7) {
            this.f9933a = rational;
            this.f9934b = z7;
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Rational rational, Rational rational2) {
            float c8 = b.c(rational, this.f9933a);
            float c9 = b.c(rational2, this.f9933a);
            return this.f9934b ? Float.compare(c9, c8) : Float.compare(c8, c9);
        }
    }

    private b(Size size, g0 g0Var, Set set) {
        this(size, g0Var, set, new g0.k(g0Var, size));
    }

    private static boolean A(Collection collection, Size size) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!y((Size) it.next(), size)) {
                return true;
            }
        }
        return false;
    }

    private boolean B(Rational rational, Size size) {
        if (this.f9927b.equals(rational) || d0.a.a(size, rational)) {
            return false;
        }
        return b(this.f9927b.floatValue(), rational.floatValue(), N(size).floatValue());
    }

    private boolean C(Size size, Size size2) {
        return B(N(size), size2);
    }

    private boolean D() {
        for (Size size : l()) {
            if (!d0.a.a(size, this.f9928c)) {
                return true;
            }
        }
        return false;
    }

    private static List E(List list) {
        return list.isEmpty() ? list : new ArrayList(new LinkedHashSet(list));
    }

    static Rect F(Rect rect) {
        return new Rect(rect.top, rect.left, rect.bottom, rect.right);
    }

    private List G(List list, boolean z7) {
        Map x7 = x(list);
        ArrayList<Rational> arrayList = new ArrayList(x7.keySet());
        K(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Rational rational : arrayList) {
            if (!rational.equals(d0.a.f6353c) && !rational.equals(d0.a.f6351a)) {
                List list2 = (List) x7.get(rational);
                Objects.requireNonNull(list2);
                arrayList2.addAll(I(rational, list2, z7));
            }
        }
        return arrayList2;
    }

    private List H(List list) {
        ArrayList arrayList = new ArrayList();
        if (D()) {
            arrayList.addAll(I(this.f9927b, list, false));
        }
        arrayList.addAll(I(this.f9928c, list, false));
        arrayList.addAll(G(list, false));
        if (arrayList.isEmpty()) {
            f1.l("ResolutionsMerger", "Failed to find a parent resolution that does not result in double-cropping, this might due to camera not supporting 4:3 and 16:9resolutions or a strict ResolutionSelector settings. Starting resolution selection process with resolutions that might have a smaller FOV.");
            arrayList.addAll(G(list, true));
        }
        f1.a("ResolutionsMerger", "Parent resolutions: " + arrayList);
        return arrayList;
    }

    private List I(Rational rational, List list, boolean z7) {
        List<Size> g8 = g(rational, list);
        L(g8);
        HashSet hashSet = new HashSet(g8);
        for (g3 g3Var : this.f9929d) {
            List v7 = v(g3Var);
            if (!z7) {
                v7 = d(rational, v7);
            }
            if (v7.isEmpty()) {
                return new ArrayList();
            }
            g8 = f(v7, g8);
            hashSet.retainAll(p(v7, g8));
        }
        ArrayList arrayList = new ArrayList();
        for (Size size : g8) {
            if (!hashSet.contains(size)) {
                arrayList.add(size);
            }
        }
        return arrayList;
    }

    private boolean J() {
        boolean z7;
        o0.c E;
        Iterator it = this.f9929d.iterator();
        while (true) {
            z7 = false;
            if (!it.hasNext()) {
                break;
            }
            g3 g3Var = (g3) it.next();
            if (!g3Var.N(false) && (g3Var instanceof p1) && (E = ((p1) g3Var).E(null)) != null) {
                z7 = true;
                if (E.a() == 1) {
                    break;
                }
            }
        }
        return z7;
    }

    private void K(List list) {
        Collections.sort(list, new a(M(this.f9926a), true));
    }

    static void L(List list) {
        Collections.sort(list, new d0.e(true));
    }

    private static Rational M(Size size) {
        return new Rational(size.getWidth(), size.getHeight());
    }

    private static Rational N(Size size) {
        Rational rational = d0.a.f6351a;
        if (d0.a.a(size, rational)) {
            return rational;
        }
        Rational rational2 = d0.a.f6353c;
        return d0.a.a(size, rational2) ? rational2 : M(size);
    }

    private boolean b(float f8, float f9, float f10) {
        int i8;
        int i9 = (f8 > f9 ? 1 : (f8 == f9 ? 0 : -1));
        if (i9 == 0 || f9 == f10) {
            return false;
        }
        return i9 > 0 ? f9 < f10 : i8 > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float c(Rational rational, Rational rational2) {
        float floatValue = rational.floatValue();
        float floatValue2 = rational2.floatValue();
        return floatValue > floatValue2 ? floatValue2 / floatValue : floatValue / floatValue2;
    }

    private List d(Rational rational, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            if (!B(rational, size)) {
                arrayList.add(size);
            }
        }
        return arrayList;
    }

    private static List e(List list) {
        Rational rational;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            Iterator it2 = hashMap.keySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    rational = null;
                    break;
                }
                rational = (Rational) it2.next();
                if (d0.a.a(size, rational)) {
                    break;
                }
            }
            if (rational != null) {
                Size size2 = (Size) hashMap.get(rational);
                Objects.requireNonNull(size2);
                if (size.getHeight() <= size2.getHeight()) {
                    if (size.getWidth() <= size2.getWidth()) {
                        if (size.getWidth() == size2.getWidth() && size.getHeight() == size2.getHeight()) {
                        }
                    }
                }
            } else {
                rational = M(size);
            }
            arrayList.add(size);
            hashMap.put(rational, size);
        }
        return arrayList;
    }

    static List f(Collection collection, List list) {
        if (collection.isEmpty() || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            if (A(collection, size)) {
                arrayList.add(size);
            }
        }
        return arrayList;
    }

    static List g(Rational rational, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            if (d0.a.a(size, rational)) {
                arrayList.add(size);
            }
        }
        return arrayList;
    }

    private static Rational h(Size size) {
        return ((double) size.getWidth()) / ((double) size.getHeight()) > f9925h ? d0.a.f6353c : d0.a.f6351a;
    }

    private List i() {
        return this.f9931f.h(34);
    }

    private List j() {
        return this.f9931f.l(34);
    }

    private static Rect k(Rational rational, Size size) {
        RectF rectF;
        RectF rectF2;
        int width = size.getWidth();
        int height = size.getHeight();
        Rational M = M(size);
        if (rational.floatValue() == M.floatValue()) {
            rectF2 = new RectF(0.0f, 0.0f, width, height);
        } else {
            if (rational.floatValue() > M.floatValue()) {
                float f8 = width;
                float floatValue = f8 / rational.floatValue();
                float f9 = (height - floatValue) / 2.0f;
                rectF = new RectF(0.0f, f9, f8, floatValue + f9);
            } else {
                float f10 = height;
                float floatValue2 = rational.floatValue() * f10;
                float f11 = (width - floatValue2) / 2.0f;
                rectF = new RectF(f11, 0.0f, floatValue2 + f11, f10);
            }
            rectF2 = rectF;
        }
        Rect rect = new Rect();
        rectF2.round(rect);
        return rect;
    }

    private Set l() {
        HashSet hashSet = new HashSet();
        for (g3 g3Var : this.f9929d) {
            hashSet.addAll(v(g3Var));
        }
        return hashSet;
    }

    static Rect m(Size size, Size size2) {
        return k(M(size2), size);
    }

    private static Rational n(Rational rational) {
        Rational rational2 = d0.a.f6351a;
        if (rational.equals(rational2)) {
            return d0.a.f6353c;
        }
        if (rational.equals(d0.a.f6353c)) {
            return rational2;
        }
        throw new IllegalArgumentException("Invalid sensor aspect-ratio: " + rational);
    }

    static List p(Collection collection, List list) {
        if (collection.isEmpty() || list.isEmpty()) {
            return new ArrayList();
        }
        List<Size> E = E(list);
        ArrayList arrayList = new ArrayList();
        for (Size size : E) {
            if (z(collection, size)) {
                arrayList.add(size);
            }
        }
        if (!arrayList.isEmpty()) {
            arrayList.remove(arrayList.size() - 1);
        }
        return arrayList;
    }

    private Pair t(Rect rect, g3 g3Var, boolean z7) {
        Size q8;
        if (z7) {
            q8 = r(d0.r.l(rect), g3Var);
        } else {
            Size l8 = d0.r.l(rect);
            q8 = q(l8, g3Var);
            rect = m(l8, q8);
        }
        return new Pair(rect, q8);
    }

    private static Rational u(Size size) {
        Rational h8 = h(size);
        f1.a("ResolutionsMerger", "The closer aspect ratio to the sensor size (" + size + ") is " + h8 + ".");
        return h8;
    }

    private List v(g3 g3Var) {
        if (!this.f9929d.contains(g3Var)) {
            throw new IllegalArgumentException("Invalid child config: " + g3Var);
        } else if (this.f9932g.containsKey(g3Var)) {
            List list = (List) this.f9932g.get(g3Var);
            Objects.requireNonNull(list);
            return list;
        } else {
            List e8 = e(this.f9930e.m(g3Var));
            this.f9932g.put(g3Var, e8);
            return e8;
        }
    }

    private static List w(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (((Integer) pair.first).equals(34)) {
                return Arrays.asList((Size[]) pair.second);
            }
        }
        return new ArrayList();
    }

    private Map x(List list) {
        List list2;
        HashMap hashMap = new HashMap();
        Rational rational = d0.a.f6351a;
        hashMap.put(rational, new ArrayList());
        Rational rational2 = d0.a.f6353c;
        hashMap.put(rational2, new ArrayList());
        ArrayList arrayList = new ArrayList();
        arrayList.add(rational);
        arrayList.add(rational2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            if (size.getHeight() > 0) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        list2 = null;
                        break;
                    }
                    Rational rational3 = (Rational) it2.next();
                    if (d0.a.a(size, rational3)) {
                        list2 = (List) hashMap.get(rational3);
                        break;
                    }
                }
                if (list2 == null) {
                    list2 = new ArrayList();
                    Rational M = M(size);
                    arrayList.add(M);
                    hashMap.put(M, list2);
                }
                list2.add(size);
            }
        }
        return hashMap;
    }

    static boolean y(Size size, Size size2) {
        return size.getHeight() > size2.getHeight() || size.getWidth() > size2.getWidth();
    }

    private static boolean z(Collection collection, Size size) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (y((Size) it.next(), size)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List o(z1 z1Var) {
        List j8 = j();
        if (J()) {
            ArrayList arrayList = new ArrayList(j8);
            arrayList.addAll(i());
            j8 = arrayList;
        }
        List list = (List) z1Var.f(p1.f4316q, null);
        if (list != null) {
            j8 = w(list);
        }
        return H(j8);
    }

    Size q(Size size, g3 g3Var) {
        List<Size> v7 = v(g3Var);
        for (Size size2 : v7) {
            if (!C(size, size2) && !y(size2, size)) {
                return size2;
            }
        }
        for (Size size3 : v7) {
            if (!y(size3, size)) {
                return size3;
            }
        }
        return size;
    }

    Size r(Size size, g3 g3Var) {
        for (Size size2 : v(g3Var)) {
            Size l8 = d0.r.l(m(size2, size));
            if (!y(l8, size)) {
                return l8;
            }
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Pair s(g3 g3Var, Rect rect, int i8, boolean z7) {
        boolean z8;
        if (d0.r.h(i8)) {
            rect = F(rect);
            z8 = true;
        } else {
            z8 = false;
        }
        Pair t8 = t(rect, g3Var, z7);
        Rect rect2 = (Rect) t8.first;
        Size size = (Size) t8.second;
        if (z8) {
            size = d0.r.n(size);
            rect2 = F(rect2);
        }
        return new Pair(rect2, size);
    }

    b(Size size, g0 g0Var, Set set, g0.k kVar) {
        this.f9932g = new HashMap();
        this.f9926a = size;
        Rational u7 = u(size);
        this.f9927b = u7;
        this.f9928c = n(u7);
        this.f9931f = g0Var;
        this.f9929d = set;
        this.f9930e = kVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(h0 h0Var, Set set) {
        this(d0.r.l(h0Var.p().m()), h0Var.l(), set);
    }
}
