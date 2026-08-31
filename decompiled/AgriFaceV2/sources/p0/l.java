package p0;

import android.graphics.Rect;
import android.util.Pair;
import android.util.Size;
import b0.b1;
import b0.g3;
import b0.h0;
import b0.h3;
import b0.o1;
import b0.p1;
import b0.q2;
import b0.x;
import b0.z1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import l0.l0;
import p0.h;
import y.a0;
import y.h2;
import y.u0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l implements h2.b {

    /* renamed from: e  reason: collision with root package name */
    final Set f9958e;

    /* renamed from: i  reason: collision with root package name */
    private final h3 f9962i;

    /* renamed from: j  reason: collision with root package name */
    private final h0 f9963j;

    /* renamed from: k  reason: collision with root package name */
    private final h0 f9964k;

    /* renamed from: m  reason: collision with root package name */
    private final Set f9966m;

    /* renamed from: n  reason: collision with root package name */
    private final Map f9967n;

    /* renamed from: o  reason: collision with root package name */
    private final b f9968o;

    /* renamed from: p  reason: collision with root package name */
    private b f9969p;

    /* renamed from: f  reason: collision with root package name */
    final Map f9959f = new HashMap();

    /* renamed from: g  reason: collision with root package name */
    private final Map f9960g = new HashMap();

    /* renamed from: h  reason: collision with root package name */
    final Map f9961h = new HashMap();

    /* renamed from: l  reason: collision with root package name */
    private final b0.n f9965l = s();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends b0.n {
        a() {
        }

        @Override // b0.n
        public void b(int i8, x xVar) {
            super.b(i8, xVar);
            for (h2 h2Var : l.this.f9958e) {
                l.J(xVar, h2Var.x(), i8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(h0 h0Var, h0 h0Var2, Set set, h3 h3Var, h.a aVar) {
        this.f9963j = h0Var;
        this.f9964k = h0Var2;
        this.f9962i = h3Var;
        this.f9958e = set;
        Map L = L(h0Var, set, h3Var);
        this.f9967n = L;
        HashSet hashSet = new HashSet(L.values());
        this.f9966m = hashSet;
        this.f9968o = new b(h0Var, hashSet);
        if (h0Var2 != null) {
            this.f9969p = new b(h0Var2, hashSet);
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            this.f9961h.put(h2Var, Boolean.FALSE);
            this.f9960g.put(h2Var, new k(h0Var, this, aVar));
        }
    }

    private static int B(Set set) {
        Iterator it = set.iterator();
        int i8 = 0;
        while (it.hasNext()) {
            i8 = Math.max(i8, ((g3) it.next()).v(0));
        }
        return i8;
    }

    private l0 D(h2 h2Var) {
        l0 l0Var = (l0) this.f9959f.get(h2Var);
        Objects.requireNonNull(l0Var);
        return l0Var;
    }

    private boolean E(h2 h2Var) {
        Boolean bool = (Boolean) this.f9961h.get(h2Var);
        Objects.requireNonNull(bool);
        return bool.booleanValue();
    }

    static void J(x xVar, q2 q2Var, int i8) {
        for (b0.n nVar : q2Var.i()) {
            nVar.b(i8, new m(q2Var.j().j(), xVar));
        }
    }

    private static Map L(h0 h0Var, Set set, h3 h3Var) {
        HashMap hashMap = new HashMap();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            hashMap.put(h2Var, h2Var.E(h0Var.l(), null, h2Var.k(true, h3Var)));
        }
        return hashMap;
    }

    private n0.f r(h2 h2Var, b bVar, h0 h0Var, l0 l0Var, int i8, boolean z7) {
        int i9 = h0Var.b().i(i8);
        boolean k8 = d0.r.k(l0Var.r());
        g3 g3Var = (g3) this.f9967n.get(h2Var);
        Objects.requireNonNull(g3Var);
        Pair s8 = bVar.s(g3Var, l0Var.n(), d0.r.f(l0Var.r()), z7);
        Rect rect = (Rect) s8.first;
        int v7 = v(h2Var, this.f9963j);
        k kVar = (k) this.f9960g.get(h2Var);
        Objects.requireNonNull(kVar);
        kVar.o(v7);
        int u7 = d0.r.u((l0Var.q() + v7) - i9);
        return n0.f.h(x(h2Var), u(h2Var), rect, d0.r.o((Size) s8.second, u7), u7, h2Var.D(h0Var) ^ k8);
    }

    private static void t(l0 l0Var, b1 b1Var, q2 q2Var) {
        l0Var.v();
        try {
            l0Var.C(b1Var);
        } catch (b1.a unused) {
            if (q2Var.d() != null) {
                q2Var.d().a(q2Var, q2.g.SESSION_ERROR_SURFACE_NEEDS_RESET);
            }
        }
    }

    private static int u(h2 h2Var) {
        return h2Var instanceof u0 ? 256 : 34;
    }

    private int v(h2 h2Var, h0 h0Var) {
        return h0Var.b().i(((p1) h2Var.j()).V(0));
    }

    static b1 w(h2 h2Var) {
        boolean z7 = h2Var instanceof u0;
        q2 x7 = h2Var.x();
        List n8 = z7 ? x7.n() : x7.j().i();
        g1.e.l(n8.size() <= 1);
        if (n8.size() == 1) {
            return (b1) n8.get(0);
        }
        return null;
    }

    private static int x(h2 h2Var) {
        if (h2Var instanceof y.p1) {
            return 1;
        }
        return h2Var instanceof u0 ? 4 : 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map A(l0 l0Var, l0 l0Var2, int i8, boolean z7) {
        HashMap hashMap = new HashMap();
        for (h2 h2Var : this.f9958e) {
            n0.f r8 = r(h2Var, this.f9968o, this.f9963j, l0Var, i8, z7);
            b bVar = this.f9969p;
            h0 h0Var = this.f9964k;
            Objects.requireNonNull(h0Var);
            hashMap.put(h2Var, m0.d.c(r8, r(h2Var, bVar, h0Var, l0Var2, i8, z7)));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0.n C() {
        return this.f9965l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F(z1 z1Var) {
        z1Var.Y(p1.f4318s, this.f9968o.o(z1Var));
        z1Var.Y(g3.f4204x, Integer.valueOf(B(this.f9966m)));
        a0 d8 = p0.a.d(this.f9966m);
        if (d8 == null) {
            throw new IllegalArgumentException("Failed to merge child dynamic ranges, can not find a dynamic range that satisfies all children.");
        }
        z1Var.Y(o1.f4300i, d8);
        for (h2 h2Var : this.f9958e) {
            if (h2Var.j().n() != 0) {
                z1Var.Y(g3.D, Integer.valueOf(h2Var.j().n()));
            }
            if (h2Var.j().D() != 0) {
                z1Var.Y(g3.C, Integer.valueOf(h2Var.j().D()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G() {
        for (h2 h2Var : this.f9958e) {
            h2Var.N();
            h2Var.L();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H() {
        for (h2 h2Var : this.f9958e) {
            h2Var.O();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I() {
        d0.q.a();
        for (h2 h2Var : this.f9958e) {
            n(h2Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void K(Map map) {
        this.f9959f.clear();
        this.f9959f.putAll(map);
        for (Map.Entry entry : this.f9959f.entrySet()) {
            h2 h2Var = (h2) entry.getKey();
            l0 l0Var = (l0) entry.getValue();
            h2Var.W(l0Var.n());
            h2Var.U(l0Var.r());
            h2Var.Z(l0Var.s(), null);
            h2Var.I();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void M() {
        for (h2 h2Var : this.f9958e) {
            k kVar = (k) this.f9960g.get(h2Var);
            Objects.requireNonNull(kVar);
            h2Var.X(kVar);
        }
    }

    @Override // y.h2.b
    public void d(h2 h2Var) {
        d0.q.a();
        if (E(h2Var)) {
            this.f9961h.put(h2Var, Boolean.FALSE);
            D(h2Var).m();
        }
    }

    @Override // y.h2.b
    public void f(h2 h2Var) {
        d0.q.a();
        if (E(h2Var)) {
            l0 D = D(h2Var);
            b1 w7 = w(h2Var);
            if (w7 != null) {
                t(D, w7, h2Var.x());
            } else {
                D.m();
            }
        }
    }

    @Override // y.h2.b
    public void m(h2 h2Var) {
        d0.q.a();
        if (E(h2Var)) {
            return;
        }
        this.f9961h.put(h2Var, Boolean.TRUE);
        b1 w7 = w(h2Var);
        if (w7 != null) {
            t(D(h2Var), w7, h2Var.x());
        }
    }

    @Override // y.h2.b
    public void n(h2 h2Var) {
        b1 w7;
        d0.q.a();
        l0 D = D(h2Var);
        if (E(h2Var) && (w7 = w(h2Var)) != null) {
            t(D, w7, h2Var.x());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o() {
        for (h2 h2Var : this.f9958e) {
            k kVar = (k) this.f9960g.get(h2Var);
            Objects.requireNonNull(kVar);
            h2Var.b(kVar, null, null, h2Var.k(true, this.f9962i));
        }
    }

    b0.n s() {
        return new a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set y() {
        return this.f9958e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map z(l0 l0Var, int i8, boolean z7) {
        HashMap hashMap = new HashMap();
        for (h2 h2Var : this.f9958e) {
            hashMap.put(h2Var, r(h2Var, this.f9968o, this.f9963j, l0Var, i8, z7));
        }
        return hashMap;
    }
}
