package p0;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;
import android.util.Size;
import b0.a2;
import b0.f2;
import b0.g0;
import b0.g3;
import b0.h0;
import b0.h3;
import b0.o1;
import b0.p1;
import b0.q2;
import b0.u0;
import b0.u2;
import b0.z1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import l0.l0;
import l0.t;
import l0.t0;
import m0.o;
import m0.r;
import y.e1;
import y.h2;
/* loaded from: classes.dex */
public class h extends h2 {
    q2.b A;
    q2.b B;
    private q2.c C;

    /* renamed from: p  reason: collision with root package name */
    private final j f9942p;

    /* renamed from: q  reason: collision with root package name */
    private final l f9943q;

    /* renamed from: r  reason: collision with root package name */
    private final e1 f9944r;

    /* renamed from: s  reason: collision with root package name */
    private final e1 f9945s;

    /* renamed from: t  reason: collision with root package name */
    private t0 f9946t;

    /* renamed from: u  reason: collision with root package name */
    private t0 f9947u;

    /* renamed from: v  reason: collision with root package name */
    private m0.r f9948v;

    /* renamed from: w  reason: collision with root package name */
    private l0 f9949w;

    /* renamed from: x  reason: collision with root package name */
    private l0 f9950x;

    /* renamed from: y  reason: collision with root package name */
    private l0 f9951y;

    /* renamed from: z  reason: collision with root package name */
    private l0 f9952z;

    /* loaded from: classes.dex */
    interface a {
        i4.d a(int i8, int i9);
    }

    public h(h0 h0Var, h0 h0Var2, e1 e1Var, e1 e1Var2, Set set, h3 h3Var) {
        super(n0(set));
        this.f9942p = n0(set);
        this.f9944r = e1Var;
        this.f9945s = e1Var2;
        this.f9943q = new l(h0Var, h0Var2, set, h3Var, new a() { // from class: p0.f
            @Override // p0.h.a
            public final i4.d a(int i8, int i9) {
                i4.d t02;
                t02 = h.this.t0(i8, i9);
                return t02;
            }
        });
    }

    private void d0(q2.b bVar, final String str, final String str2, final g3 g3Var, final u2 u2Var, final u2 u2Var2) {
        q2.c cVar = this.C;
        if (cVar != null) {
            cVar.b();
        }
        q2.c cVar2 = new q2.c(new q2.d() { // from class: p0.g
            @Override // b0.q2.d
            public final void a(q2 q2Var, q2.g gVar) {
                h.this.s0(str, str2, g3Var, u2Var, u2Var2, q2Var, gVar);
            }
        });
        this.C = cVar2;
        bVar.t(cVar2);
    }

    private void e0() {
        q2.c cVar = this.C;
        if (cVar != null) {
            cVar.b();
            this.C = null;
        }
        l0 l0Var = this.f9949w;
        if (l0Var != null) {
            l0Var.i();
            this.f9949w = null;
        }
        l0 l0Var2 = this.f9950x;
        if (l0Var2 != null) {
            l0Var2.i();
            this.f9950x = null;
        }
        l0 l0Var3 = this.f9951y;
        if (l0Var3 != null) {
            l0Var3.i();
            this.f9951y = null;
        }
        l0 l0Var4 = this.f9952z;
        if (l0Var4 != null) {
            l0Var4.i();
            this.f9952z = null;
        }
        t0 t0Var = this.f9947u;
        if (t0Var != null) {
            t0Var.i();
            this.f9947u = null;
        }
        m0.r rVar = this.f9948v;
        if (rVar != null) {
            rVar.f();
            this.f9948v = null;
        }
        t0 t0Var2 = this.f9946t;
        if (t0Var2 != null) {
            t0Var2.i();
            this.f9946t = null;
        }
    }

    private List f0(String str, String str2, g3 g3Var, u2 u2Var, u2 u2Var2) {
        List a8;
        List a9;
        d0.q.a();
        if (u2Var2 != null) {
            g0(str, str2, g3Var, u2Var, u2Var2);
            h0(str, str2, g3Var, u2Var, u2Var2);
            this.f9948v = o0(g(), t(), u2Var, this.f9944r, this.f9945s);
            Map A = this.f9943q.A(this.f9951y, this.f9952z, z(), B() != null);
            r.c i8 = this.f9948v.i(r.b.d(this.f9951y, this.f9952z, new ArrayList(A.values())));
            HashMap hashMap = new HashMap();
            for (Map.Entry entry : A.entrySet()) {
                hashMap.put((h2) entry.getKey(), (l0) i8.get(entry.getValue()));
            }
            this.f9943q.K(hashMap);
            a8 = y.h0.a(new Object[]{this.A.o(), this.B.o()});
            return a8;
        }
        g0(str, str2, g3Var, u2Var, null);
        h0 g8 = g();
        Objects.requireNonNull(g8);
        this.f9947u = q0(g8, u2Var);
        Map z7 = this.f9943q.z(this.f9951y, z(), B() != null);
        t0.c m8 = this.f9947u.m(t0.b.c(this.f9951y, new ArrayList(z7.values())));
        HashMap hashMap2 = new HashMap();
        for (Map.Entry entry2 : z7.entrySet()) {
            hashMap2.put((h2) entry2.getKey(), (l0) m8.get(entry2.getValue()));
        }
        this.f9943q.K(hashMap2);
        a9 = y.h0.a(new Object[]{this.A.o()});
        return a9;
    }

    private void g0(String str, String str2, g3 g3Var, u2 u2Var, u2 u2Var2) {
        Matrix w7 = w();
        h0 g8 = g();
        Objects.requireNonNull(g8);
        boolean j8 = g8.j();
        Rect m02 = m0(u2Var.e());
        Objects.requireNonNull(m02);
        h0 g9 = g();
        Objects.requireNonNull(g9);
        int q8 = q(g9);
        h0 g10 = g();
        Objects.requireNonNull(g10);
        l0 l0Var = new l0(3, 34, u2Var, w7, j8, m02, q8, -1, D(g10));
        this.f9949w = l0Var;
        h0 g11 = g();
        Objects.requireNonNull(g11);
        this.f9951y = p0(l0Var, g11);
        q2.b i02 = i0(this.f9949w, g3Var, u2Var);
        this.A = i02;
        d0(i02, str, str2, g3Var, u2Var, u2Var2);
    }

    private void h0(String str, String str2, g3 g3Var, u2 u2Var, u2 u2Var2) {
        Matrix w7 = w();
        h0 t8 = t();
        Objects.requireNonNull(t8);
        boolean j8 = t8.j();
        Rect m02 = m0(u2Var2.e());
        Objects.requireNonNull(m02);
        h0 t9 = t();
        Objects.requireNonNull(t9);
        int q8 = q(t9);
        h0 t10 = t();
        Objects.requireNonNull(t10);
        l0 l0Var = new l0(3, 34, u2Var2, w7, j8, m02, q8, -1, D(t10));
        this.f9950x = l0Var;
        h0 t11 = t();
        Objects.requireNonNull(t11);
        this.f9952z = p0(l0Var, t11);
        q2.b i02 = i0(this.f9950x, g3Var, u2Var2);
        this.B = i02;
        d0(i02, str, str2, g3Var, u2Var, u2Var2);
    }

    private q2.b i0(l0 l0Var, g3 g3Var, u2 u2Var) {
        q2.b q8 = q2.b.q(g3Var, u2Var.e());
        v0(q8);
        u0(u2Var.e(), q8);
        q8.m(l0Var.o(), u2Var.b(), null, -1);
        q8.j(this.f9943q.C());
        if (u2Var.d() != null) {
            q8.g(u2Var.d());
        }
        return q8;
    }

    public static List j0(h2 h2Var) {
        ArrayList arrayList = new ArrayList();
        if (r0(h2Var)) {
            for (h2 h2Var2 : ((h) h2Var).l0()) {
                arrayList.add(h2Var2.j().m());
            }
        } else {
            arrayList.add(h2Var.j().m());
        }
        return arrayList;
    }

    private static int k0(h2 h2Var) {
        return h2Var.j().M().o();
    }

    private Rect m0(Size size) {
        return B() != null ? B() : new Rect(0, 0, size.getWidth(), size.getHeight());
    }

    private static j n0(Set set) {
        z1 c8 = new i().c();
        c8.Y(o1.f4299h, 34);
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            if (h2Var.j().a(g3.B)) {
                arrayList.add(h2Var.j().m());
            } else {
                Log.e("StreamSharing", "A child does not have capture type.");
            }
        }
        c8.Y(j.J, arrayList);
        c8.Y(p1.f4312m, 2);
        return new j(f2.b0(c8));
    }

    private m0.r o0(h0 h0Var, h0 h0Var2, u2 u2Var, e1 e1Var, e1 e1Var2) {
        return new m0.r(h0Var, h0Var2, o.a.a(u2Var.b(), e1Var, e1Var2));
    }

    private l0 p0(l0 l0Var, h0 h0Var) {
        l();
        return l0Var;
    }

    private t0 q0(h0 h0Var, u2 u2Var) {
        l();
        return new t0(h0Var, t.a.a(u2Var.b()));
    }

    public static boolean r0(h2 h2Var) {
        return h2Var instanceof h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s0(String str, String str2, g3 g3Var, u2 u2Var, u2 u2Var2, q2 q2Var, q2.g gVar) {
        if (g() == null) {
            return;
        }
        e0();
        Y(f0(str, str2, g3Var, u2Var, u2Var2));
        H();
        this.f9943q.I();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ i4.d t0(int i8, int i9) {
        t0 t0Var = this.f9947u;
        return t0Var != null ? t0Var.e().c(i8, i9) : f0.n.n(new Exception("Failed to take picture: pipeline is not ready."));
    }

    private void u0(Size size, q2.b bVar) {
        for (h2 h2Var : l0()) {
            q2 o8 = q2.b.q(h2Var.j(), size).o();
            bVar.c(o8.i());
            bVar.a(o8.m());
            bVar.d(o8.k());
            bVar.b(o8.c());
            bVar.g(o8.f());
        }
    }

    private void v0(q2.b bVar) {
        int i8 = -1;
        for (h2 h2Var : l0()) {
            i8 = q2.e(i8, k0(h2Var));
        }
        if (i8 != -1) {
            bVar.z(i8);
        }
    }

    @Override // y.h2
    public g3.a A(u0 u0Var) {
        return new i(a2.e0(u0Var));
    }

    @Override // y.h2
    public void K() {
        super.K();
        this.f9943q.o();
    }

    @Override // y.h2
    protected g3 M(g0 g0Var, g3.a aVar) {
        this.f9943q.F(aVar.c());
        return aVar.d();
    }

    @Override // y.h2
    public void N() {
        super.N();
        this.f9943q.G();
    }

    @Override // y.h2
    public void O() {
        super.O();
        this.f9943q.H();
    }

    @Override // y.h2
    protected u2 P(u0 u0Var) {
        List a8;
        this.A.g(u0Var);
        a8 = y.h0.a(new Object[]{this.A.o()});
        Y(a8);
        return e().g().d(u0Var).a();
    }

    @Override // y.h2
    protected u2 Q(u2 u2Var, u2 u2Var2) {
        Y(f0(i(), u(), j(), u2Var, u2Var2));
        F();
        return u2Var;
    }

    @Override // y.h2
    public void R() {
        super.R();
        e0();
        this.f9943q.M();
    }

    @Override // y.h2
    public g3 k(boolean z7, h3 h3Var) {
        u0 a8 = h3Var.a(this.f9942p.m(), 1);
        if (z7) {
            a8 = u0.u(a8, this.f9942p.z());
        }
        if (a8 == null) {
            return null;
        }
        return A(a8).d();
    }

    public Set l0() {
        return this.f9943q.y();
    }

    @Override // y.h2
    public Set y() {
        HashSet hashSet = new HashSet();
        hashSet.add(3);
        return hashSet;
    }
}
