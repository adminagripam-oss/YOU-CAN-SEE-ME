package y;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import b0.g3;
import b0.h3;
import b0.p1;
import b0.q2;
import b0.u0;
import b0.u2;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import o0.c;
import y.p1;
/* loaded from: classes.dex */
public final class p1 extends h2 {

    /* renamed from: x  reason: collision with root package name */
    public static final b f12869x = new b();

    /* renamed from: y  reason: collision with root package name */
    private static final Executor f12870y = e0.c.e();

    /* renamed from: p  reason: collision with root package name */
    private c f12871p;

    /* renamed from: q  reason: collision with root package name */
    private Executor f12872q;

    /* renamed from: r  reason: collision with root package name */
    q2.b f12873r;

    /* renamed from: s  reason: collision with root package name */
    private b0.b1 f12874s;

    /* renamed from: t  reason: collision with root package name */
    private l0.l0 f12875t;

    /* renamed from: u  reason: collision with root package name */
    g2 f12876u;

    /* renamed from: v  reason: collision with root package name */
    private l0.t0 f12877v;

    /* renamed from: w  reason: collision with root package name */
    private q2.c f12878w;

    /* loaded from: classes.dex */
    public static final class a implements g3.a, p1.a {

        /* renamed from: a  reason: collision with root package name */
        private final b0.a2 f12879a;

        public a() {
            this(b0.a2.d0());
        }

        static a f(b0.u0 u0Var) {
            return new a(b0.a2.e0(u0Var));
        }

        @Override // y.c0
        public b0.z1 c() {
            return this.f12879a;
        }

        public p1 e() {
            b0.g2 d8 = d();
            b0.p1.j(d8);
            return new p1(d8);
        }

        @Override // b0.g3.a
        /* renamed from: g */
        public b0.g2 d() {
            return new b0.g2(b0.f2.b0(this.f12879a));
        }

        public a h(h3.b bVar) {
            c().Y(g3.B, bVar);
            return this;
        }

        public a i(a0 a0Var) {
            c().Y(b0.o1.f4300i, a0Var);
            return this;
        }

        public a j(o0.c cVar) {
            c().Y(b0.p1.f4317r, cVar);
            return this;
        }

        public a k(int i8) {
            c().Y(g3.f4204x, Integer.valueOf(i8));
            return this;
        }

        public a l(int i8) {
            if (i8 == -1) {
                i8 = 0;
            }
            c().Y(b0.p1.f4309j, Integer.valueOf(i8));
            return this;
        }

        public a m(Class cls) {
            c().Y(g0.m.G, cls);
            if (c().f(g0.m.F, null) == null) {
                n(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public a n(String str) {
            c().Y(g0.m.F, str);
            return this;
        }

        @Override // b0.p1.a
        /* renamed from: o */
        public a b(Size size) {
            c().Y(b0.p1.f4313n, size);
            return this;
        }

        @Override // b0.p1.a
        /* renamed from: p */
        public a a(int i8) {
            c().Y(b0.p1.f4310k, Integer.valueOf(i8));
            c().Y(b0.p1.f4311l, Integer.valueOf(i8));
            return this;
        }

        private a(b0.a2 a2Var) {
            this.f12879a = a2Var;
            Class cls = (Class) a2Var.f(g0.m.G, null);
            if (cls != null && !cls.equals(p1.class)) {
                throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
            }
            h(h3.b.PREVIEW);
            m(p1.class);
            u0.a aVar = b0.p1.f4312m;
            if (((Integer) a2Var.f(aVar, -1)).intValue() == -1) {
                a2Var.Y(aVar, 2);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private static final o0.c f12880a;

        /* renamed from: b  reason: collision with root package name */
        private static final b0.g2 f12881b;

        /* renamed from: c  reason: collision with root package name */
        private static final a0 f12882c;

        static {
            o0.c a8 = new c.a().d(o0.a.f9805c).f(o0.d.f9817c).a();
            f12880a = a8;
            a0 a0Var = a0.f12729c;
            f12882c = a0Var;
            f12881b = new a().k(2).l(0).j(a8).i(a0Var).d();
        }

        public b0.g2 a() {
            return f12881b;
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(g2 g2Var);
    }

    p1(b0.g2 g2Var) {
        super(g2Var);
        this.f12872q = f12870y;
    }

    private void d0(q2.b bVar, u2 u2Var) {
        if (this.f12871p != null) {
            bVar.m(this.f12874s, u2Var.b(), p(), n());
        }
        q2.c cVar = this.f12878w;
        if (cVar != null) {
            cVar.b();
        }
        q2.c cVar2 = new q2.c(new q2.d() { // from class: y.o1
            @Override // b0.q2.d
            public final void a(q2 q2Var, q2.g gVar) {
                p1.this.i0(q2Var, gVar);
            }
        });
        this.f12878w = cVar2;
        bVar.t(cVar2);
    }

    private void e0() {
        q2.c cVar = this.f12878w;
        if (cVar != null) {
            cVar.b();
            this.f12878w = null;
        }
        b0.b1 b1Var = this.f12874s;
        if (b1Var != null) {
            b1Var.d();
            this.f12874s = null;
        }
        l0.t0 t0Var = this.f12877v;
        if (t0Var != null) {
            t0Var.i();
            this.f12877v = null;
        }
        l0.l0 l0Var = this.f12875t;
        if (l0Var != null) {
            l0Var.i();
            this.f12875t = null;
        }
        this.f12876u = null;
    }

    private q2.b f0(b0.g2 g2Var, u2 u2Var) {
        d0.q.a();
        b0.h0 g8 = g();
        Objects.requireNonNull(g8);
        b0.h0 h0Var = g8;
        e0();
        g1.e.l(this.f12875t == null);
        Matrix w7 = w();
        boolean j8 = h0Var.j();
        Rect g02 = g0(u2Var.e());
        Objects.requireNonNull(g02);
        this.f12875t = new l0.l0(1, 34, u2Var, w7, j8, g02, r(h0Var, D(h0Var)), d(), p0(h0Var));
        l();
        this.f12875t.e(new Runnable() { // from class: y.m1
            @Override // java.lang.Runnable
            public final void run() {
                p1.this.H();
            }
        });
        g2 k8 = this.f12875t.k(h0Var);
        this.f12876u = k8;
        this.f12874s = k8.l();
        if (this.f12871p != null) {
            k0();
        }
        q2.b q8 = q2.b.q(g2Var, u2Var.e());
        q8.u(u2Var.c());
        q8.y(g2Var.D());
        if (u2Var.d() != null) {
            q8.g(u2Var.d());
        }
        d0(q8, u2Var);
        return q8;
    }

    private Rect g0(Size size) {
        if (B() != null) {
            return B();
        }
        if (size != null) {
            return new Rect(0, 0, size.getWidth(), size.getHeight());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i0(q2 q2Var, q2.g gVar) {
        if (g() == null) {
            return;
        }
        q0((b0.g2) j(), e());
        H();
    }

    private void k0() {
        l0();
        final c cVar = (c) g1.e.j(this.f12871p);
        final g2 g2Var = (g2) g1.e.j(this.f12876u);
        this.f12872q.execute(new Runnable() { // from class: y.n1
            @Override // java.lang.Runnable
            public final void run() {
                p1.c.this.a(g2Var);
            }
        });
    }

    private void l0() {
        b0.h0 g8 = g();
        l0.l0 l0Var = this.f12875t;
        if (g8 == null || l0Var == null) {
            return;
        }
        l0Var.D(r(g8, D(g8)), d());
    }

    private boolean p0(b0.h0 h0Var) {
        return h0Var.j() && D(h0Var);
    }

    private void q0(b0.g2 g2Var, u2 u2Var) {
        List a8;
        q2.b f02 = f0(g2Var, u2Var);
        this.f12873r = f02;
        a8 = h0.a(new Object[]{f02.o()});
        Y(a8);
    }

    @Override // y.h2
    public g3.a A(b0.u0 u0Var) {
        return a.f(u0Var);
    }

    @Override // y.h2
    protected g3 M(b0.g0 g0Var, g3.a aVar) {
        aVar.c().Y(b0.o1.f4299h, 34);
        return aVar.d();
    }

    @Override // y.h2
    protected u2 P(b0.u0 u0Var) {
        List a8;
        this.f12873r.g(u0Var);
        a8 = h0.a(new Object[]{this.f12873r.o()});
        Y(a8);
        return e().g().d(u0Var).a();
    }

    @Override // y.h2
    protected u2 Q(u2 u2Var, u2 u2Var2) {
        q0((b0.g2) j(), u2Var);
        return u2Var;
    }

    @Override // y.h2
    public void R() {
        e0();
    }

    @Override // y.h2
    public void W(Rect rect) {
        super.W(rect);
        l0();
    }

    public q1 h0() {
        return s();
    }

    @Override // y.h2
    public g3 k(boolean z7, h3 h3Var) {
        b bVar = f12869x;
        b0.u0 a8 = h3Var.a(bVar.a().m(), 1);
        if (z7) {
            a8 = b0.u0.u(a8, bVar.a());
        }
        if (a8 == null) {
            return null;
        }
        return A(a8).d();
    }

    public void m0(Executor executor, c cVar) {
        d0.q.a();
        if (cVar == null) {
            this.f12871p = null;
            G();
            return;
        }
        this.f12871p = cVar;
        this.f12872q = executor;
        if (f() != null) {
            q0((b0.g2) j(), e());
            H();
        }
        F();
    }

    public void n0(c cVar) {
        m0(f12870y, cVar);
    }

    public void o0(int i8) {
        if (V(i8)) {
            l0();
        }
    }

    public String toString() {
        return "Preview:" + o();
    }

    @Override // y.h2
    public Set y() {
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        return hashSet;
    }
}
