package t0;

import android.util.Size;
import b0.g1;
import b0.h1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import r0.o;
import r0.v;
import x0.k;
import y.a0;
import y0.q1;
/* loaded from: classes.dex */
public class f implements g1 {

    /* renamed from: c  reason: collision with root package name */
    private final g1 f11644c;

    /* renamed from: d  reason: collision with root package name */
    private final Set f11645d;

    /* renamed from: e  reason: collision with root package name */
    private final Set f11646e;

    /* renamed from: f  reason: collision with root package name */
    private final Set f11647f;

    /* renamed from: g  reason: collision with root package name */
    private final m.a f11648g;

    /* renamed from: h  reason: collision with root package name */
    private final Map f11649h = new HashMap();

    /* renamed from: i  reason: collision with root package name */
    private final Map f11650i = new HashMap();

    public f(g1 g1Var, Collection collection, Collection collection2, Collection collection3, m.a aVar) {
        c(collection2);
        this.f11644c = g1Var;
        this.f11645d = new HashSet(collection);
        this.f11647f = new HashSet(collection2);
        this.f11646e = new HashSet(collection3);
        this.f11648g = aVar;
    }

    private static void c(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            a0 a0Var = (a0) it.next();
            if (!a0Var.e()) {
                throw new IllegalArgumentException("Contains non-fully specified DynamicRange: " + a0Var);
            }
        }
    }

    private h1 d(v.b bVar) {
        g b8;
        g1.e.a(this.f11645d.contains(bVar));
        h1 a8 = this.f11644c.a(bVar.e());
        for (Size size : bVar.d()) {
            if (this.f11646e.contains(size)) {
                TreeMap treeMap = new TreeMap(new d0.e());
                ArrayList arrayList = new ArrayList();
                for (a0 a0Var : this.f11647f) {
                    if (!i(a8, a0Var) && (b8 = f(a0Var).b(size)) != null) {
                        h1.c k8 = b8.k();
                        q1 q1Var = (q1) this.f11648g.apply(k.f(k8));
                        if (q1Var != null && q1Var.a(size.getWidth(), size.getHeight())) {
                            treeMap.put(new Size(k8.k(), k8.h()), b8);
                            arrayList.add(z0.c.a(k8, size, q1Var.c()));
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    h1 h1Var = (h1) k0.d.a(size, treeMap);
                    Objects.requireNonNull(h1Var);
                    h1 h1Var2 = h1Var;
                    return h1.b.h(h1Var2.g(), h1Var2.a(), h1Var2.b(), arrayList);
                }
            }
        }
        return null;
    }

    private v.b e(int i8) {
        for (v vVar : this.f11645d) {
            v.b bVar = (v.b) vVar;
            if (bVar.e() == i8) {
                return bVar;
            }
        }
        return null;
    }

    private o f(a0 a0Var) {
        if (this.f11650i.containsKey(a0Var)) {
            o oVar = (o) this.f11650i.get(a0Var);
            Objects.requireNonNull(oVar);
            return oVar;
        }
        o oVar2 = new o(new e(this.f11644c, a0Var));
        this.f11650i.put(a0Var, oVar2);
        return oVar2;
    }

    private h1 g(int i8) {
        if (this.f11649h.containsKey(Integer.valueOf(i8))) {
            return (h1) this.f11649h.get(Integer.valueOf(i8));
        }
        h1 a8 = this.f11644c.a(i8);
        v.b e8 = e(i8);
        if (e8 != null && !h(a8)) {
            a8 = j(a8, d(e8));
        }
        this.f11649h.put(Integer.valueOf(i8), a8);
        return a8;
    }

    private boolean h(h1 h1Var) {
        if (h1Var == null) {
            return false;
        }
        for (a0 a0Var : this.f11647f) {
            if (!i(h1Var, a0Var)) {
                return false;
            }
        }
        return true;
    }

    private static boolean i(h1 h1Var, a0 a0Var) {
        if (h1Var == null) {
            return false;
        }
        for (h1.c cVar : h1Var.c()) {
            if (z0.b.f(cVar, a0Var)) {
                return true;
            }
        }
        return false;
    }

    private static h1 j(h1 h1Var, h1 h1Var2) {
        if (h1Var == null && h1Var2 == null) {
            return null;
        }
        int g8 = h1Var != null ? h1Var.g() : h1Var2.g();
        int a8 = h1Var != null ? h1Var.a() : h1Var2.a();
        List b8 = h1Var != null ? h1Var.b() : h1Var2.b();
        ArrayList arrayList = new ArrayList();
        if (h1Var != null) {
            arrayList.addAll(h1Var.c());
        }
        if (h1Var2 != null) {
            arrayList.addAll(h1Var2.c());
        }
        return h1.b.h(g8, a8, b8, arrayList);
    }

    @Override // b0.g1
    public h1 a(int i8) {
        return g(i8);
    }

    @Override // b0.g1
    public boolean b(int i8) {
        return g(i8) != null;
    }
}
