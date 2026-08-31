package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.f;
import androidx.camera.core.internal.compat.quirk.OnePixelShiftQuirk;
import b0.a2;
import b0.b1;
import b0.f2;
import b0.g0;
import b0.g3;
import b0.h0;
import b0.h3;
import b0.m1;
import b0.o1;
import b0.p1;
import b0.q2;
import b0.r1;
import b0.u0;
import b0.u2;
import b0.z1;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import o0.c;
import y.a0;
import y.h2;
/* loaded from: classes.dex */
public final class f extends h2 {

    /* renamed from: v  reason: collision with root package name */
    public static final d f2680v = new d();

    /* renamed from: w  reason: collision with root package name */
    private static final Boolean f2681w = null;

    /* renamed from: p  reason: collision with root package name */
    final i f2682p;

    /* renamed from: q  reason: collision with root package name */
    private final Object f2683q;

    /* renamed from: r  reason: collision with root package name */
    private a f2684r;

    /* renamed from: s  reason: collision with root package name */
    q2.b f2685s;

    /* renamed from: t  reason: collision with root package name */
    private b1 f2686t;

    /* renamed from: u  reason: collision with root package name */
    private q2.c f2687u;

    /* loaded from: classes.dex */
    public interface a {
        void a(n nVar);

        default Size b() {
            return null;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface b {
    }

    /* loaded from: classes.dex */
    public static final class c implements p1.a, g3.a {

        /* renamed from: a  reason: collision with root package name */
        private final a2 f2688a;

        public c() {
            this(a2.d0());
        }

        static c f(u0 u0Var) {
            return new c(a2.e0(u0Var));
        }

        @Override // y.c0
        public z1 c() {
            return this.f2688a;
        }

        public f e() {
            m1 d8 = d();
            p1.j(d8);
            return new f(d8);
        }

        @Override // b0.g3.a
        /* renamed from: g */
        public m1 d() {
            return new m1(f2.b0(this.f2688a));
        }

        public c h(h3.b bVar) {
            c().Y(g3.B, bVar);
            return this;
        }

        public c i(Size size) {
            c().Y(p1.f4314o, size);
            return this;
        }

        public c j(a0 a0Var) {
            if (Objects.equals(a0.f12730d, a0Var)) {
                c().Y(o1.f4300i, a0Var);
                return this;
            }
            throw new UnsupportedOperationException("ImageAnalysis currently only supports SDR");
        }

        public c k(o0.c cVar) {
            c().Y(p1.f4317r, cVar);
            return this;
        }

        public c l(int i8) {
            c().Y(g3.f4204x, Integer.valueOf(i8));
            return this;
        }

        public c m(int i8) {
            if (i8 == -1) {
                i8 = 0;
            }
            c().Y(p1.f4309j, Integer.valueOf(i8));
            return this;
        }

        public c n(Class cls) {
            c().Y(g0.m.G, cls);
            if (c().f(g0.m.F, null) == null) {
                o(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public c o(String str) {
            c().Y(g0.m.F, str);
            return this;
        }

        @Override // b0.p1.a
        /* renamed from: p */
        public c b(Size size) {
            c().Y(p1.f4313n, size);
            return this;
        }

        @Override // b0.p1.a
        /* renamed from: q */
        public c a(int i8) {
            c().Y(p1.f4310k, Integer.valueOf(i8));
            return this;
        }

        private c(a2 a2Var) {
            this.f2688a = a2Var;
            Class cls = (Class) a2Var.f(g0.m.G, null);
            if (cls == null || cls.equals(f.class)) {
                h(h3.b.IMAGE_ANALYSIS);
                n(f.class);
                return;
            }
            throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
        }
    }

    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        private static final Size f2689a;

        /* renamed from: b  reason: collision with root package name */
        private static final a0 f2690b;

        /* renamed from: c  reason: collision with root package name */
        private static final o0.c f2691c;

        /* renamed from: d  reason: collision with root package name */
        private static final m1 f2692d;

        static {
            Size size = new Size(640, 480);
            f2689a = size;
            a0 a0Var = a0.f12730d;
            f2690b = a0Var;
            o0.c a8 = new c.a().d(o0.a.f9805c).f(new o0.d(k0.d.f8305c, 1)).a();
            f2691c = a8;
            f2692d = new c().i(size).l(1).m(0).k(a8).j(a0Var).d();
        }

        public m1 a() {
            return f2692d;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface e {
    }

    f(m1 m1Var) {
        super(m1Var);
        this.f2683q = new Object();
        if (((m1) j()).Z(0) == 1) {
            this.f2682p = new j();
        } else {
            this.f2682p = new k(m1Var.Q(e0.c.c()));
        }
        this.f2682p.t(l0());
        this.f2682p.u(n0());
    }

    private boolean m0(h0 h0Var) {
        return n0() && q(h0Var) % 180 != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void o0(q qVar, q qVar2) {
        qVar.k();
        if (qVar2 != null) {
            qVar2.k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p0(q2 q2Var, q2.g gVar) {
        List a8;
        if (g() == null) {
            return;
        }
        g0();
        this.f2682p.g();
        q2.b h02 = h0(i(), (m1) j(), (u2) g1.e.j(e()));
        this.f2685s = h02;
        a8 = y.h0.a(new Object[]{h02.o()});
        Y(a8);
        H();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List q0(Size size, List list, int i8) {
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.contains(size)) {
            arrayList.remove(size);
            arrayList.add(0, size);
        }
        return arrayList;
    }

    private void u0() {
        h0 g8 = g();
        if (g8 != null) {
            this.f2682p.w(q(g8));
        }
    }

    @Override // y.h2
    public g3.a A(u0 u0Var) {
        return c.f(u0Var);
    }

    @Override // y.h2
    public void K() {
        this.f2682p.f();
    }

    @Override // y.h2
    protected g3 M(g0 g0Var, g3.a aVar) {
        final Size b8;
        Boolean k02 = k0();
        boolean a8 = g0Var.k().a(OnePixelShiftQuirk.class);
        i iVar = this.f2682p;
        if (k02 != null) {
            a8 = k02.booleanValue();
        }
        iVar.s(a8);
        synchronized (this.f2683q) {
            a aVar2 = this.f2684r;
            b8 = aVar2 != null ? aVar2.b() : null;
        }
        if (b8 == null) {
            return aVar.d();
        }
        if (g0Var.i(((Integer) aVar.c().f(p1.f4310k, 0)).intValue()) % 180 == 90) {
            b8 = new Size(b8.getHeight(), b8.getWidth());
        }
        g3 d8 = aVar.d();
        u0.a aVar3 = p1.f4313n;
        if (!d8.a(aVar3)) {
            aVar.c().Y(aVar3, b8);
        }
        g3 d9 = aVar.d();
        u0.a aVar4 = p1.f4317r;
        if (d9.a(aVar4)) {
            o0.c cVar = (o0.c) c().f(aVar4, null);
            c.a aVar5 = cVar == null ? new c.a() : c.a.b(cVar);
            if (cVar == null || cVar.d() == null) {
                aVar5.f(new o0.d(b8, 1));
            }
            if (cVar == null) {
                aVar5.e(new o0.b() { // from class: y.j0
                    @Override // o0.b
                    public final List a(List list, int i8) {
                        List q02;
                        q02 = androidx.camera.core.f.q0(b8, list, i8);
                        return q02;
                    }
                });
            }
            aVar.c().Y(aVar4, aVar5.a());
        }
        return aVar.d();
    }

    @Override // y.h2
    protected u2 P(u0 u0Var) {
        List a8;
        this.f2685s.g(u0Var);
        a8 = y.h0.a(new Object[]{this.f2685s.o()});
        Y(a8);
        return e().g().d(u0Var).a();
    }

    @Override // y.h2
    protected u2 Q(u2 u2Var, u2 u2Var2) {
        List a8;
        q2.b h02 = h0(i(), (m1) j(), u2Var);
        this.f2685s = h02;
        a8 = y.h0.a(new Object[]{h02.o()});
        Y(a8);
        return u2Var;
    }

    @Override // y.h2
    public void R() {
        g0();
        this.f2682p.j();
    }

    @Override // y.h2
    public void U(Matrix matrix) {
        super.U(matrix);
        this.f2682p.x(matrix);
    }

    @Override // y.h2
    public void W(Rect rect) {
        super.W(rect);
        this.f2682p.y(rect);
    }

    public void f0() {
        synchronized (this.f2683q) {
            this.f2682p.r(null, null);
            if (this.f2684r != null) {
                G();
            }
            this.f2684r = null;
        }
    }

    void g0() {
        d0.q.a();
        q2.c cVar = this.f2687u;
        if (cVar != null) {
            cVar.b();
            this.f2687u = null;
        }
        b1 b1Var = this.f2686t;
        if (b1Var != null) {
            b1Var.d();
            this.f2686t = null;
        }
    }

    q2.b h0(String str, m1 m1Var, u2 u2Var) {
        d0.q.a();
        Size e8 = u2Var.e();
        Executor executor = (Executor) g1.e.j(m1Var.Q(e0.c.c()));
        boolean z7 = true;
        int j02 = i0() == 1 ? j0() : 4;
        m1Var.b0();
        final q qVar = new q(o.a(e8.getWidth(), e8.getHeight(), m(), j02));
        boolean m02 = g() != null ? m0(g()) : false;
        int height = m02 ? e8.getHeight() : e8.getWidth();
        int width = m02 ? e8.getWidth() : e8.getHeight();
        int i8 = l0() == 2 ? 1 : 35;
        boolean z8 = m() == 35 && l0() == 2;
        if (m() != 35 || ((g() == null || q(g()) == 0) && !Boolean.TRUE.equals(k0()))) {
            z7 = false;
        }
        final q qVar2 = (z8 || z7) ? new q(o.a(height, width, i8, qVar.e())) : null;
        if (qVar2 != null) {
            this.f2682p.v(qVar2);
        }
        u0();
        qVar.d(this.f2682p, executor);
        q2.b q8 = q2.b.q(m1Var, u2Var.e());
        if (u2Var.d() != null) {
            q8.g(u2Var.d());
        }
        b1 b1Var = this.f2686t;
        if (b1Var != null) {
            b1Var.d();
        }
        r1 r1Var = new r1(qVar.getSurface(), e8, m());
        this.f2686t = r1Var;
        r1Var.k().d(new Runnable() { // from class: y.l0
            @Override // java.lang.Runnable
            public final void run() {
                androidx.camera.core.f.o0(androidx.camera.core.q.this, qVar2);
            }
        }, e0.c.e());
        q8.u(u2Var.c());
        q8.m(this.f2686t, u2Var.b(), null, -1);
        q2.c cVar = this.f2687u;
        if (cVar != null) {
            cVar.b();
        }
        q2.c cVar2 = new q2.c(new q2.d() { // from class: y.m0
            @Override // b0.q2.d
            public final void a(q2 q2Var, q2.g gVar) {
                androidx.camera.core.f.this.p0(q2Var, gVar);
            }
        });
        this.f2687u = cVar2;
        q8.t(cVar2);
        return q8;
    }

    public int i0() {
        return ((m1) j()).Z(0);
    }

    public int j0() {
        return ((m1) j()).a0(6);
    }

    @Override // y.h2
    public g3 k(boolean z7, h3 h3Var) {
        d dVar = f2680v;
        u0 a8 = h3Var.a(dVar.a().m(), 1);
        if (z7) {
            a8 = u0.u(a8, dVar.a());
        }
        if (a8 == null) {
            return null;
        }
        return A(a8).d();
    }

    public Boolean k0() {
        return ((m1) j()).c0(f2681w);
    }

    public int l0() {
        return ((m1) j()).d0(1);
    }

    public boolean n0() {
        return ((m1) j()).e0(Boolean.FALSE).booleanValue();
    }

    public void s0(Executor executor, final a aVar) {
        synchronized (this.f2683q) {
            this.f2682p.r(executor, new a() { // from class: y.k0
                @Override // androidx.camera.core.f.a
                public final void a(androidx.camera.core.n nVar) {
                    f.a.this.a(nVar);
                }
            });
            if (this.f2684r == null) {
                F();
            }
            this.f2684r = aVar;
        }
    }

    public void t0(int i8) {
        if (V(i8)) {
            u0();
        }
    }

    public String toString() {
        return "ImageAnalysis:" + o();
    }
}
