package l0;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import android.view.Surface;
import androidx.concurrent.futures.c;
import b0.b1;
import b0.u2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import l0.l0;
import y.g2;
import y.v1;
/* loaded from: classes.dex */
public class l0 {

    /* renamed from: a  reason: collision with root package name */
    private final int f8868a;

    /* renamed from: b  reason: collision with root package name */
    private final Matrix f8869b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f8870c;

    /* renamed from: d  reason: collision with root package name */
    private final Rect f8871d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f8872e;

    /* renamed from: f  reason: collision with root package name */
    private final int f8873f;

    /* renamed from: g  reason: collision with root package name */
    private final u2 f8874g;

    /* renamed from: h  reason: collision with root package name */
    private int f8875h;

    /* renamed from: i  reason: collision with root package name */
    private int f8876i;

    /* renamed from: k  reason: collision with root package name */
    private g2 f8878k;

    /* renamed from: l  reason: collision with root package name */
    private a f8879l;

    /* renamed from: j  reason: collision with root package name */
    private boolean f8877j = false;

    /* renamed from: m  reason: collision with root package name */
    private final Set f8880m = new HashSet();

    /* renamed from: n  reason: collision with root package name */
    private boolean f8881n = false;

    /* renamed from: o  reason: collision with root package name */
    private final List f8882o = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends b1 {

        /* renamed from: o  reason: collision with root package name */
        final i4.d f8883o;

        /* renamed from: p  reason: collision with root package name */
        c.a f8884p;

        /* renamed from: q  reason: collision with root package name */
        private b1 f8885q;

        /* renamed from: r  reason: collision with root package name */
        private o0 f8886r;

        a(Size size, int i8) {
            super(size, i8);
            this.f8883o = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: l0.i0
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object n8;
                    n8 = l0.a.this.n(aVar);
                    return n8;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object n(c.a aVar) {
            this.f8884p = aVar;
            return "SettableFuture hashCode: " + hashCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void w() {
            o0 o0Var = this.f8886r;
            if (o0Var != null) {
                o0Var.q();
            }
            if (this.f8885q == null) {
                this.f8884p.d();
            }
        }

        @Override // b0.b1
        public void d() {
            super.d();
            d0.q.d(new Runnable() { // from class: l0.j0
                @Override // java.lang.Runnable
                public final void run() {
                    l0.a.this.w();
                }
            });
        }

        @Override // b0.b1
        protected i4.d r() {
            return this.f8883o;
        }

        boolean v() {
            d0.q.a();
            return this.f8885q == null && !m();
        }

        public void x(o0 o0Var) {
            g1.e.m(this.f8886r == null, "Consumer can only be linked once.");
            this.f8886r = o0Var;
        }

        public boolean y(final b1 b1Var, Runnable runnable) {
            d0.q.a();
            g1.e.j(b1Var);
            b1 b1Var2 = this.f8885q;
            if (b1Var2 == b1Var) {
                return false;
            }
            g1.e.m(b1Var2 == null, "A different provider has been set. To change the provider, call SurfaceEdge#invalidate before calling SurfaceEdge#setProvider");
            g1.e.b(h().equals(b1Var.h()), String.format("The provider's size(%s) must match the parent(%s)", h(), b1Var.h()));
            g1.e.b(i() == b1Var.i(), String.format("The provider's format(%s) must match the parent(%s)", Integer.valueOf(i()), Integer.valueOf(b1Var.i())));
            g1.e.m(!m(), "The parent is closed. Call SurfaceEdge#invalidate() before setting a new provider.");
            this.f8885q = b1Var;
            f0.n.C(b1Var.j(), this.f8884p);
            b1Var.l();
            k().d(new Runnable() { // from class: l0.k0
                @Override // java.lang.Runnable
                public final void run() {
                    b1.this.e();
                }
            }, e0.c.b());
            b1Var.f().d(runnable, e0.c.e());
            return true;
        }
    }

    public l0(int i8, int i9, u2 u2Var, Matrix matrix, boolean z7, Rect rect, int i10, int i11, boolean z8) {
        this.f8873f = i8;
        this.f8868a = i9;
        this.f8874g = u2Var;
        this.f8869b = matrix;
        this.f8870c = z7;
        this.f8871d = rect;
        this.f8876i = i10;
        this.f8875h = i11;
        this.f8872e = z8;
        this.f8879l = new a(u2Var.e(), i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(int i8, int i9) {
        boolean z7;
        boolean z8 = true;
        if (this.f8876i != i8) {
            this.f8876i = i8;
            z7 = true;
        } else {
            z7 = false;
        }
        if (this.f8875h != i9) {
            this.f8875h = i9;
        } else {
            z8 = z7;
        }
        if (z8) {
            B();
        }
    }

    private void B() {
        d0.q.a();
        g2.h g8 = g2.h.g(this.f8871d, this.f8876i, this.f8875h, u(), this.f8869b, this.f8872e);
        g2 g2Var = this.f8878k;
        if (g2Var != null) {
            g2Var.E(g8);
        }
        for (g1.a aVar : this.f8882o) {
            aVar.accept(g8);
        }
    }

    private void g() {
        g1.e.m(!this.f8877j, "Consumer can only be linked once.");
        this.f8877j = true;
    }

    private void h() {
        g1.e.m(!this.f8881n, "Edge is already closed.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ i4.d x(final a aVar, int i8, v1.a aVar2, v1.a aVar3, Surface surface) {
        g1.e.j(surface);
        try {
            aVar.l();
            o0 o0Var = new o0(surface, t(), i8, this.f8874g.e(), aVar2, aVar3, this.f8869b);
            o0Var.g().d(new Runnable() { // from class: l0.h0
                @Override // java.lang.Runnable
                public final void run() {
                    l0.a.this.e();
                }
            }, e0.c.b());
            aVar.x(o0Var);
            return f0.n.p(o0Var);
        } catch (b1.a e8) {
            return f0.n.n(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y() {
        if (this.f8881n) {
            return;
        }
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z() {
        e0.c.e().execute(new Runnable() { // from class: l0.f0
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.y();
            }
        });
    }

    public void C(b1 b1Var) {
        d0.q.a();
        h();
        a aVar = this.f8879l;
        Objects.requireNonNull(aVar);
        aVar.y(b1Var, new b0(aVar));
    }

    public void D(final int i8, final int i9) {
        d0.q.d(new Runnable() { // from class: l0.e0
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.A(i8, i9);
            }
        });
    }

    public void e(Runnable runnable) {
        d0.q.a();
        h();
        this.f8880m.add(runnable);
    }

    public void f(g1.a aVar) {
        g1.e.j(aVar);
        this.f8882o.add(aVar);
    }

    public final void i() {
        d0.q.a();
        this.f8879l.d();
        this.f8881n = true;
    }

    public i4.d j(final int i8, final v1.a aVar, final v1.a aVar2) {
        d0.q.a();
        h();
        g();
        final a aVar3 = this.f8879l;
        return f0.n.H(aVar3.j(), new f0.a() { // from class: l0.g0
            @Override // f0.a
            public final i4.d apply(Object obj) {
                i4.d x7;
                x7 = l0.this.x(aVar3, i8, aVar, aVar2, (Surface) obj);
                return x7;
            }
        }, e0.c.e());
    }

    public g2 k(b0.h0 h0Var) {
        return l(h0Var, true);
    }

    public g2 l(b0.h0 h0Var, boolean z7) {
        d0.q.a();
        h();
        g2 g2Var = new g2(this.f8874g.e(), h0Var, z7, this.f8874g.b(), this.f8874g.c(), new Runnable() { // from class: l0.c0
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.z();
            }
        });
        try {
            final b1 l8 = g2Var.l();
            a aVar = this.f8879l;
            Objects.requireNonNull(aVar);
            if (aVar.y(l8, new b0(aVar))) {
                i4.d k8 = aVar.k();
                Objects.requireNonNull(l8);
                k8.d(new Runnable() { // from class: l0.d0
                    @Override // java.lang.Runnable
                    public final void run() {
                        b1.this.d();
                    }
                }, e0.c.b());
            }
            this.f8878k = g2Var;
            B();
            return g2Var;
        } catch (b1.a e8) {
            throw new AssertionError("Surface is somehow already closed", e8);
        } catch (RuntimeException e9) {
            g2Var.F();
            throw e9;
        }
    }

    public final void m() {
        d0.q.a();
        h();
        this.f8879l.d();
    }

    public Rect n() {
        return this.f8871d;
    }

    public b1 o() {
        d0.q.a();
        h();
        g();
        return this.f8879l;
    }

    public int p() {
        return this.f8868a;
    }

    public int q() {
        return this.f8876i;
    }

    public Matrix r() {
        return this.f8869b;
    }

    public u2 s() {
        return this.f8874g;
    }

    public int t() {
        return this.f8873f;
    }

    public boolean u() {
        return this.f8870c;
    }

    public void v() {
        d0.q.a();
        h();
        if (this.f8879l.v()) {
            return;
        }
        this.f8877j = false;
        this.f8879l.d();
        this.f8879l = new a(this.f8874g.e(), this.f8868a);
        for (Runnable runnable : this.f8880m) {
            runnable.run();
        }
    }

    public boolean w() {
        return this.f8872e;
    }
}
