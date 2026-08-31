package r0;

import android.util.Size;
import b0.h1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class y {

    /* renamed from: a  reason: collision with root package name */
    private final List f11288a;

    /* renamed from: b  reason: collision with root package name */
    private final p f11289b;

    y(List list, p pVar) {
        g1.e.b((list.isEmpty() && pVar == p.f11114a) ? false : true, "No preferred quality and fallback strategy.");
        this.f11288a = Collections.unmodifiableList(new ArrayList(list));
        this.f11289b = pVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.util.List r11, java.util.Set r12) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.y.a(java.util.List, java.util.Set):void");
    }

    private static void b(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            v vVar = (v) it.next();
            boolean a8 = v.a(vVar);
            g1.e.b(a8, "qualities contain invalid quality: " + vVar);
        }
    }

    private static void c(v vVar) {
        boolean a8 = v.a(vVar);
        g1.e.b(a8, "Invalid quality: " + vVar);
    }

    public static y d(v vVar) {
        return e(vVar, p.f11114a);
    }

    public static y e(v vVar, p pVar) {
        g1.e.k(vVar, "quality cannot be null");
        g1.e.k(pVar, "fallbackStrategy cannot be null");
        c(vVar);
        return new y(Collections.singletonList(vVar), pVar);
    }

    public static y f(List list) {
        return g(list, p.f11114a);
    }

    public static y g(List list, p pVar) {
        g1.e.k(list, "qualities cannot be null");
        g1.e.k(pVar, "fallbackStrategy cannot be null");
        g1.e.b(!list.isEmpty(), "qualities cannot be empty");
        b(list);
        return new y(list, pVar);
    }

    private static Size i(t0.g gVar) {
        h1.c k8 = gVar.k();
        return new Size(k8.k(), k8.h());
    }

    public static Map j(d1 d1Var, y.a0 a0Var) {
        HashMap hashMap = new HashMap();
        for (v vVar : d1Var.d(a0Var)) {
            t0.g c8 = d1Var.c(vVar, a0Var);
            Objects.requireNonNull(c8);
            hashMap.put(vVar, i(c8));
        }
        return hashMap;
    }

    public static Size k(y.p pVar, v vVar) {
        c(vVar);
        t0.g c8 = q0.L(pVar).c(vVar, y.a0.f12730d);
        if (c8 != null) {
            return i(c8);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List h(List list) {
        if (list.isEmpty()) {
            y.f1.l("QualitySelector", "No supported quality on the device.");
            return new ArrayList();
        }
        y.f1.a("QualitySelector", "supportedQualities = " + list);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = this.f11288a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            v vVar = (v) it.next();
            if (vVar == v.f11272f) {
                linkedHashSet.addAll(list);
                break;
            } else if (vVar == v.f11271e) {
                ArrayList arrayList = new ArrayList(list);
                Collections.reverse(arrayList);
                linkedHashSet.addAll(arrayList);
                break;
            } else if (list.contains(vVar)) {
                linkedHashSet.add(vVar);
            } else {
                y.f1.l("QualitySelector", "quality is not supported and will be ignored: " + vVar);
            }
        }
        a(list, linkedHashSet);
        return new ArrayList(linkedHashSet);
    }

    public String toString() {
        return "QualitySelector{preferredQualities=" + this.f11288a + ", fallbackStrategy=" + this.f11289b + "}";
    }
}
