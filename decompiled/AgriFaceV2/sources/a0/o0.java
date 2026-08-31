package a0;

import a0.a0;
import a0.i;
import a0.o0;
import a0.w;
import android.graphics.Bitmap;
import androidx.camera.core.internal.compat.quirk.IncorrectJpegMetadataQuirk;
import androidx.camera.core.internal.compat.quirk.LowMemoryQuirk;
import b0.l2;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import y.f1;
import y.u0;
/* loaded from: classes.dex */
public class o0 {

    /* renamed from: a  reason: collision with root package name */
    final Executor f65a;

    /* renamed from: b  reason: collision with root package name */
    final l0.w f66b;

    /* renamed from: c  reason: collision with root package name */
    private a f67c;

    /* renamed from: d  reason: collision with root package name */
    private l0.y f68d;

    /* renamed from: e  reason: collision with root package name */
    private l0.y f69e;

    /* renamed from: f  reason: collision with root package name */
    private l0.y f70f;

    /* renamed from: g  reason: collision with root package name */
    private l0.y f71g;

    /* renamed from: h  reason: collision with root package name */
    private l0.y f72h;

    /* renamed from: i  reason: collision with root package name */
    private l0.y f73i;

    /* renamed from: j  reason: collision with root package name */
    private l0.y f74j;

    /* renamed from: k  reason: collision with root package name */
    private l0.y f75k;

    /* renamed from: l  reason: collision with root package name */
    private l0.y f76l;

    /* renamed from: m  reason: collision with root package name */
    private final l2 f77m;

    /* renamed from: n  reason: collision with root package name */
    private final boolean f78n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static a e(int i8, int i9) {
            return new e(new l0.u(), new l0.u(), i8, i9);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract l0.u a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int b();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int c();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract l0.u d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class b {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static b c(p0 p0Var, androidx.camera.core.n nVar) {
            return new f(p0Var, nVar);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract androidx.camera.core.n a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract p0 b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0(Executor executor, l0.w wVar) {
        this(executor, wVar, androidx.camera.core.internal.compat.quirk.a.c());
    }

    private l0.z i(l0.z zVar, int i8) {
        g1.e.l(k0.b.i(zVar.e()));
        l0.z zVar2 = (l0.z) this.f72h.apply(zVar);
        l0.y yVar = this.f76l;
        if (yVar != null) {
            zVar2 = (l0.z) yVar.apply(zVar2);
        }
        return (l0.z) this.f70f.apply(i.b.c(zVar2, i8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(final b bVar) {
        if (bVar.b().j()) {
            bVar.a().close();
        } else {
            this.f65a.execute(new Runnable() { // from class: a0.k0
                @Override // java.lang.Runnable
                public final void run() {
                    o0.this.n(bVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(final b bVar) {
        if (!bVar.b().j()) {
            this.f65a.execute(new Runnable() { // from class: a0.j0
                @Override // java.lang.Runnable
                public final void run() {
                    o0.this.p(bVar);
                }
            });
            return;
        }
        f1.l("ProcessingNode", "The postview image is closed due to request aborted");
        bVar.a().close();
    }

    private static void w(final p0 p0Var, final y.v0 v0Var) {
        e0.c.e().execute(new Runnable() { // from class: a0.g0
            @Override // java.lang.Runnable
            public final void run() {
                p0.this.s(v0Var);
            }
        });
    }

    androidx.camera.core.n r(b bVar) {
        p0 b8 = bVar.b();
        l0.z zVar = (l0.z) this.f68d.apply(bVar);
        if ((zVar.e() == 35 || this.f76l != null || this.f78n) && this.f67c.c() == 256) {
            l0.z zVar2 = (l0.z) this.f69e.apply(w.a.c(zVar, b8.c()));
            if (this.f76l != null) {
                zVar2 = i(zVar2, b8.c());
            }
            zVar = (l0.z) this.f74j.apply(zVar2);
        }
        return (androidx.camera.core.n) this.f73i.apply(zVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: s */
    public void n(b bVar) {
        y.v0 v0Var;
        ScheduledExecutorService e8;
        Runnable runnable;
        final p0 b8 = bVar.b();
        try {
            if (bVar.b().k()) {
                final androidx.camera.core.n r8 = r(bVar);
                e8 = e0.c.e();
                runnable = new Runnable() { // from class: a0.m0
                    @Override // java.lang.Runnable
                    public final void run() {
                        p0.this.o(r8);
                    }
                };
            } else {
                final u0.h t8 = t(bVar);
                e8 = e0.c.e();
                runnable = new Runnable() { // from class: a0.n0
                    @Override // java.lang.Runnable
                    public final void run() {
                        p0.this.p(t8);
                    }
                };
            }
            e8.execute(runnable);
        } catch (OutOfMemoryError e9) {
            v0Var = new y.v0(0, "Processing failed due to low memory.", e9);
            w(b8, v0Var);
        } catch (RuntimeException e10) {
            v0Var = new y.v0(0, "Processing failed.", e10);
            w(b8, v0Var);
        } catch (y.v0 e11) {
            w(b8, e11);
        }
    }

    u0.h t(b bVar) {
        int c8 = this.f67c.c();
        g1.e.b(k0.b.i(c8), String.format("On-disk capture only support JPEG and JPEG/R output formats. Output format: %s", Integer.valueOf(c8)));
        p0 b8 = bVar.b();
        l0.z zVar = (l0.z) this.f69e.apply(w.a.c((l0.z) this.f68d.apply(bVar), b8.c()));
        if (zVar.i() || this.f76l != null) {
            zVar = i(zVar, b8.c());
        }
        l0.y yVar = this.f71g;
        u0.g d8 = b8.d();
        Objects.requireNonNull(d8);
        return (u0.h) yVar.apply(a0.a.c(zVar, d8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: u */
    public void p(b bVar) {
        int c8 = this.f67c.c();
        g1.e.b(c8 == 35 || c8 == 256, String.format("Postview only support YUV and JPEG output formats. Output format: %s", Integer.valueOf(c8)));
        final p0 b8 = bVar.b();
        try {
            final Bitmap bitmap = (Bitmap) this.f75k.apply((l0.z) this.f68d.apply(bVar));
            e0.c.e().execute(new Runnable() { // from class: a0.l0
                @Override // java.lang.Runnable
                public final void run() {
                    p0.this.r(bitmap);
                }
            });
        } catch (Exception e8) {
            bVar.a().close();
            f1.d("ProcessingNode", "process postview input packet failed.", e8);
        }
    }

    public void v() {
    }

    public Void x(a aVar) {
        this.f67c = aVar;
        aVar.a().a(new g1.a() { // from class: a0.h0
            @Override // g1.a
            public final void accept(Object obj) {
                o0.this.o((o0.b) obj);
            }
        });
        aVar.d().a(new g1.a() { // from class: a0.i0
            @Override // g1.a
            public final void accept(Object obj) {
                o0.this.q((o0.b) obj);
            }
        });
        this.f68d = new f0();
        this.f69e = new w(this.f77m);
        this.f72h = new z();
        this.f70f = new i();
        this.f71g = new a0();
        this.f73i = new c0();
        this.f75k = new v();
        if (aVar.b() == 35 || this.f66b != null || this.f78n) {
            this.f74j = new b0();
        }
        l0.w wVar = this.f66b;
        if (wVar != null) {
            this.f76l = new j(wVar);
            return null;
        }
        return null;
    }

    o0(Executor executor, l0.w wVar, l2 l2Var) {
        this.f65a = androidx.camera.core.internal.compat.quirk.a.b(LowMemoryQuirk.class) != null ? e0.c.g(executor) : executor;
        this.f66b = wVar;
        this.f77m = l2Var;
        this.f78n = l2Var.a(IncorrectJpegMetadataQuirk.class);
    }
}
