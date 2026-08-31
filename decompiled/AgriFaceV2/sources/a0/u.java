package a0;

import a0.o0;
import a0.u;
import a0.y0;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.e;
import b0.q1;
import b0.r1;
import java.util.Objects;
import y.f1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class u {

    /* renamed from: b  reason: collision with root package name */
    androidx.camera.core.q f119b;

    /* renamed from: c  reason: collision with root package name */
    androidx.camera.core.q f120c;

    /* renamed from: d  reason: collision with root package name */
    private o0.a f121d;

    /* renamed from: e  reason: collision with root package name */
    private c f122e;

    /* renamed from: a  reason: collision with root package name */
    p0 f118a = null;

    /* renamed from: f  reason: collision with root package name */
    private e0 f123f = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends b0.n {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f() {
            p0 p0Var = u.this.f118a;
            if (p0Var != null) {
                p0Var.n();
            }
        }

        @Override // b0.n
        public void d(int i8) {
            e0.c.e().execute(new Runnable() { // from class: a0.t
                @Override // java.lang.Runnable
                public final void run() {
                    u.a.this.f();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ p0 f125a;

        b(p0 p0Var) {
            this.f125a = p0Var;
        }

        @Override // f0.c
        public void b(Throwable th) {
            d0.q.a();
            if (this.f125a == u.this.f118a) {
                f1.l("CaptureNode", "request aborted, id=" + u.this.f118a.e());
                if (u.this.f123f != null) {
                    u.this.f123f.h();
                }
                u.this.f118a = null;
            }
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r12) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class c {

        /* renamed from: b  reason: collision with root package name */
        private b0.b1 f128b;

        /* renamed from: a  reason: collision with root package name */
        private b0.n f127a = new a();

        /* renamed from: c  reason: collision with root package name */
        private b0.b1 f129c = null;

        /* loaded from: classes.dex */
        class a extends b0.n {
            a() {
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static c m(Size size, int i8, int i9, boolean z7, y.b1 b1Var, Size size2, int i10) {
            return new a0.b(size, i8, i9, z7, b1Var, size2, i10, new l0.u(), new l0.u());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b0.n a() {
            return this.f127a;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract l0.u b();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract y.b1 c();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int d();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int e();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int f();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Size g();

        /* JADX INFO: Access modifiers changed from: package-private */
        public b0.b1 h() {
            return this.f129c;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract l0.u i();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Size j();

        /* JADX INFO: Access modifiers changed from: package-private */
        public b0.b1 k() {
            b0.b1 b1Var = this.f128b;
            Objects.requireNonNull(b1Var);
            return b1Var;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean l();

        void n(b0.n nVar) {
            this.f127a = nVar;
        }

        void o(Surface surface, Size size, int i8) {
            this.f129c = new r1(surface, size, i8);
        }

        void p(Surface surface) {
            g1.e.m(this.f128b == null, "The surface is already set.");
            this.f128b = new r1(surface, j(), d());
        }
    }

    private static q1 g(y.b1 b1Var, int i8, int i9, int i10) {
        return b1Var != null ? b1Var.a(i8, i9, i10, 4, 0L) : androidx.camera.core.o.a(i8, i9, i10, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void j(androidx.camera.core.q qVar) {
        if (qVar != null) {
            qVar.k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(p0 p0Var) {
        p(p0Var);
        this.f123f.g(p0Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(q1 q1Var) {
        try {
            androidx.camera.core.n acquireLatestImage = q1Var.acquireLatestImage();
            if (acquireLatestImage != null) {
                o(acquireLatestImage);
            } else {
                p0 p0Var = this.f118a;
                if (p0Var != null) {
                    t(y0.b.c(p0Var.e(), new y.v0(2, "Failed to acquire latest image", null)));
                }
            }
        } catch (IllegalStateException e8) {
            p0 p0Var2 = this.f118a;
            if (p0Var2 != null) {
                t(y0.b.c(p0Var2.e(), new y.v0(2, "Failed to acquire latest image", e8)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(q1 q1Var) {
        try {
            androidx.camera.core.n acquireLatestImage = q1Var.acquireLatestImage();
            if (acquireLatestImage != null) {
                q(acquireLatestImage);
            }
        } catch (IllegalStateException e8) {
            f1.d("CaptureNode", "Failed to acquire latest image of postview", e8);
        }
    }

    private void n(androidx.camera.core.n nVar) {
        d0.q.a();
        o0.a aVar = this.f121d;
        Objects.requireNonNull(aVar);
        aVar.a().accept(o0.b.c(this.f118a, nVar));
        p0 p0Var = this.f118a;
        this.f118a = null;
        p0Var.q();
    }

    private void q(androidx.camera.core.n nVar) {
        if (this.f118a == null) {
            f1.l("CaptureNode", "Postview image is closed due to request completed or aborted");
            nVar.close();
            return;
        }
        o0.a aVar = this.f121d;
        Objects.requireNonNull(aVar);
        aVar.d().accept(o0.b.c(this.f118a, nVar));
    }

    private void s(c cVar, final androidx.camera.core.q qVar, final androidx.camera.core.q qVar2) {
        cVar.k().d();
        cVar.k().k().d(new Runnable() { // from class: a0.r
            @Override // java.lang.Runnable
            public final void run() {
                androidx.camera.core.q.this.k();
            }
        }, e0.c.e());
        if (cVar.h() != null) {
            cVar.h().d();
            cVar.h().k().d(new Runnable() { // from class: a0.s
                @Override // java.lang.Runnable
                public final void run() {
                    u.j(androidx.camera.core.q.this);
                }
            }, e0.c.e());
        }
    }

    public int h() {
        d0.q.a();
        g1.e.m(this.f119b != null, "The ImageReader is not initialized.");
        return this.f119b.h();
    }

    void o(androidx.camera.core.n nVar) {
        d0.q.a();
        if (this.f118a == null) {
            f1.l("CaptureNode", "Discarding ImageProxy which was inadvertently acquired: " + nVar);
            nVar.close();
        } else if (((Integer) nVar.p().b().d(this.f118a.i())) != null) {
            n(nVar);
        } else {
            f1.l("CaptureNode", "Discarding ImageProxy which was acquired for aborted request");
            nVar.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(p0 p0Var) {
        d0.q.a();
        g1.e.m(p0Var.h().size() == 1, "only one capture stage is supported.");
        g1.e.m(h() > 0, "Too many acquire images. Close image to be able to process next.");
        this.f118a = p0Var;
        f0.n.j(p0Var.a(), new b(p0Var), e0.c.b());
    }

    public void r() {
        d0.q.a();
        c cVar = this.f122e;
        Objects.requireNonNull(cVar);
        androidx.camera.core.q qVar = this.f119b;
        Objects.requireNonNull(qVar);
        s(cVar, qVar, this.f120c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(y0.b bVar) {
        d0.q.a();
        p0 p0Var = this.f118a;
        if (p0Var == null || p0Var.e() != bVar.b()) {
            return;
        }
        this.f118a.l(bVar.a());
    }

    public void u(e.a aVar) {
        d0.q.a();
        g1.e.m(this.f119b != null, "The ImageReader is not initialized.");
        this.f119b.l(aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public o0.a v(c cVar) {
        g1.a aVar;
        e0 e0Var;
        g1.e.m(this.f122e == null && this.f119b == null, "CaptureNode does not support recreation yet.");
        this.f122e = cVar;
        Size j8 = cVar.j();
        int d8 = cVar.d();
        boolean z7 = !cVar.l();
        b0.n aVar2 = new a();
        if (z7) {
            cVar.c();
            androidx.camera.core.p pVar = new androidx.camera.core.p(j8.getWidth(), j8.getHeight(), d8, 4);
            aVar2 = b0.o.b(aVar2, pVar.l());
            aVar = new g1.a() { // from class: a0.m
                @Override // g1.a
                public final void accept(Object obj) {
                    u.this.p((p0) obj);
                }
            };
            e0Var = pVar;
        } else {
            cVar.c();
            e0 e0Var2 = new e0(g(null, j8.getWidth(), j8.getHeight(), d8));
            this.f123f = e0Var2;
            aVar = new g1.a() { // from class: a0.n
                @Override // g1.a
                public final void accept(Object obj) {
                    u.this.k((p0) obj);
                }
            };
            e0Var = e0Var2;
        }
        cVar.n(aVar2);
        Surface surface = e0Var.getSurface();
        Objects.requireNonNull(surface);
        cVar.p(surface);
        this.f119b = new androidx.camera.core.q(e0Var);
        e0Var.d(new q1.a() { // from class: a0.o
            @Override // b0.q1.a
            public final void a(q1 q1Var) {
                u.this.l(q1Var);
            }
        }, e0.c.e());
        if (cVar.g() != null) {
            cVar.c();
            q1 g8 = g(null, cVar.g().getWidth(), cVar.g().getHeight(), cVar.f());
            g8.d(new q1.a() { // from class: a0.p
                @Override // b0.q1.a
                public final void a(q1 q1Var) {
                    u.this.m(q1Var);
                }
            }, e0.c.e());
            this.f120c = new androidx.camera.core.q(g8);
            cVar.o(g8.getSurface(), cVar.g(), cVar.f());
        }
        cVar.i().a(aVar);
        cVar.b().a(new g1.a() { // from class: a0.q
            @Override // g1.a
            public final void accept(Object obj) {
                u.this.t((y0.b) obj);
            }
        });
        o0.a e8 = o0.a.e(cVar.d(), cVar.e());
        this.f121d = e8;
        return e8;
    }
}
