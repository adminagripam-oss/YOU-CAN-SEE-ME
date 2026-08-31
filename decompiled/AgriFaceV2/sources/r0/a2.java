package r0;

import android.view.Surface;
import androidx.concurrent.futures.c;
import b0.z2;
import java.util.Objects;
import java.util.concurrent.Executor;
import y.g2;
import y0.l;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class a2 {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f10977a;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f10978b;

    /* renamed from: c  reason: collision with root package name */
    private final y0.o f10979c;

    /* renamed from: d  reason: collision with root package name */
    private y0.l f10980d = null;

    /* renamed from: e  reason: collision with root package name */
    private Surface f10981e = null;

    /* renamed from: f  reason: collision with root package name */
    private g2 f10982f = null;

    /* renamed from: g  reason: collision with root package name */
    private Executor f10983g = null;

    /* renamed from: h  reason: collision with root package name */
    private l.c.a f10984h = null;

    /* renamed from: i  reason: collision with root package name */
    private b f10985i = b.NOT_INITIALIZED;

    /* renamed from: j  reason: collision with root package name */
    private i4.d f10986j = f0.n.n(new IllegalStateException("Cannot close the encoder before configuring."));

    /* renamed from: k  reason: collision with root package name */
    private c.a f10987k = null;

    /* renamed from: l  reason: collision with root package name */
    private i4.d f10988l = f0.n.n(new IllegalStateException("Cannot close the encoder before configuring."));

    /* renamed from: m  reason: collision with root package name */
    private c.a f10989m = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {
        a() {
        }

        @Override // f0.c
        public void b(Throwable th) {
            y.f1.m("VideoEncoderSession", "VideoEncoder configuration failed.", th);
            a2.this.x();
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(y0.l lVar) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum b {
        NOT_INITIALIZED,
        INITIALIZING,
        PENDING_RELEASE,
        READY,
        RELEASED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a2(y0.o oVar, Executor executor, Executor executor2) {
        this.f10977a = executor2;
        this.f10978b = executor;
        this.f10979c = oVar;
    }

    private void h() {
        int ordinal = this.f10985i.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            x();
        } else if (ordinal == 2 || ordinal == 3) {
            y.f1.a("VideoEncoderSession", "closeInternal in " + this.f10985i + " state");
            this.f10985i = b.PENDING_RELEASE;
        } else if (ordinal == 4) {
            y.f1.a("VideoEncoderSession", "closeInternal in RELEASED state, No-op");
        } else {
            throw new IllegalStateException("State " + this.f10985i + " is not handled");
        }
    }

    private void j(final g2 g2Var, z2 z2Var, t0.g gVar, r rVar, final c.a aVar) {
        y.a0 m8 = g2Var.m();
        try {
            y0.l a8 = this.f10979c.a(this.f10977a, x0.k.c(x0.k.d(rVar, m8, gVar), z2Var, rVar.d(), g2Var.o(), m8, g2Var.n()));
            this.f10980d = a8;
            l.b c8 = a8.c();
            if (c8 instanceof l.c) {
                ((l.c) c8).e(this.f10978b, new l.c.a() { // from class: r0.z1
                    @Override // y0.l.c.a
                    public final void a(Surface surface) {
                        a2.this.s(aVar, g2Var, surface);
                    }
                });
            } else {
                aVar.f(new AssertionError("The EncoderInput of video isn't a SurfaceInput."));
            }
        } catch (y0.j1 e8) {
            y.f1.d("VideoEncoderSession", "Unable to initialize video encoder.", e8);
            aVar.f(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object o(c.a aVar) {
        this.f10987k = aVar;
        return "ReleasedFuture " + this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object p(c.a aVar) {
        this.f10989m = aVar;
        return "ReadyToReleaseFuture " + this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object q(g2 g2Var, z2 z2Var, t0.g gVar, r rVar, c.a aVar) {
        j(g2Var, z2Var, gVar, rVar, aVar);
        return "ConfigureVideoEncoderFuture " + this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(Surface surface) {
        this.f10984h.a(surface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(c.a aVar, g2 g2Var, final Surface surface) {
        Executor executor;
        int ordinal = this.f10985i.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                if (g2Var.s()) {
                    y.f1.a("VideoEncoderSession", "Not provide surface, " + Objects.toString(g2Var, "EMPTY") + " is already serviced.");
                    aVar.c(null);
                    h();
                    return;
                }
                this.f10981e = surface;
                y.f1.a("VideoEncoderSession", "provide surface: " + surface);
                g2Var.C(surface, this.f10978b, new g1.a() { // from class: r0.t1
                    @Override // g1.a
                    public final void accept(Object obj) {
                        a2.this.u((g2.g) obj);
                    }
                });
                this.f10985i = b.READY;
                aVar.c(this.f10980d);
                return;
            } else if (ordinal != 2) {
                if (ordinal == 3) {
                    if (this.f10984h != null && (executor = this.f10983g) != null) {
                        executor.execute(new Runnable() { // from class: r0.u1
                            @Override // java.lang.Runnable
                            public final void run() {
                                a2.this.r(surface);
                            }
                        });
                    }
                    y.f1.l("VideoEncoderSession", "Surface is updated in READY state: " + surface);
                    return;
                } else if (ordinal != 4) {
                    throw new IllegalStateException("State " + this.f10985i + " is not handled");
                }
            }
        }
        y.f1.a("VideoEncoderSession", "Not provide surface in " + this.f10985i);
        aVar.c(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t() {
        this.f10987k.c(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(g2.g gVar) {
        y.f1.a("VideoEncoderSession", "Surface can be closed: " + gVar.b().hashCode());
        Surface b8 = gVar.b();
        if (b8 != this.f10981e) {
            b8.release();
            return;
        }
        this.f10981e = null;
        this.f10989m.c(this.f10980d);
        h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d i(final g2 g2Var, final z2 z2Var, final r rVar, final t0.g gVar) {
        if (this.f10985i.ordinal() != 0) {
            return f0.n.n(new IllegalStateException("configure() shouldn't be called in " + this.f10985i));
        }
        this.f10985i = b.INITIALIZING;
        this.f10982f = g2Var;
        y.f1.a("VideoEncoderSession", "Create VideoEncoderSession: " + this);
        this.f10986j = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r0.w1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object o8;
                o8 = a2.this.o(aVar);
                return o8;
            }
        });
        this.f10988l = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r0.x1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object p8;
                p8 = a2.this.p(aVar);
                return p8;
            }
        });
        i4.d a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r0.y1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object q8;
                q8 = a2.this.q(g2Var, z2Var, gVar, rVar, aVar);
                return q8;
            }
        });
        f0.n.j(a8, new a(), this.f10978b);
        return f0.n.B(a8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Surface k() {
        if (this.f10985i != b.READY) {
            return null;
        }
        return this.f10981e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d l() {
        return f0.n.B(this.f10988l);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public y0.l m() {
        return this.f10980d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean n(g2 g2Var) {
        int ordinal = this.f10985i.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return false;
                }
                if (ordinal != 3) {
                    if (ordinal == 4) {
                        return false;
                    }
                    throw new IllegalStateException("State " + this.f10985i + " is not handled");
                }
            }
            return this.f10982f == g2Var;
        }
        return false;
    }

    public String toString() {
        return "VideoEncoderSession@" + hashCode() + " for " + Objects.toString(this.f10982f, "SURFACE_REQUEST_NOT_CONFIGURED");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(Executor executor, l.c.a aVar) {
        this.f10983g = executor;
        this.f10984h = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d w() {
        h();
        return f0.n.B(this.f10986j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x() {
        int ordinal = this.f10985i.ordinal();
        if (ordinal == 0) {
            this.f10985i = b.RELEASED;
        } else if (ordinal != 1 && ordinal != 2 && ordinal != 3) {
            if (ordinal != 4) {
                throw new IllegalStateException("State " + this.f10985i + " is not handled");
            }
            y.f1.a("VideoEncoderSession", "terminateNow in " + this.f10985i + ", No-op");
        } else {
            this.f10985i = b.RELEASED;
            this.f10989m.c(this.f10980d);
            this.f10982f = null;
            if (this.f10980d == null) {
                y.f1.l("VideoEncoderSession", "There's no VideoEncoder to release! Finish release completer.");
                this.f10987k.c(null);
                return;
            }
            y.f1.a("VideoEncoderSession", "VideoEncoder is releasing: " + this.f10980d);
            this.f10980d.release();
            this.f10980d.d().d(new Runnable() { // from class: r0.v1
                @Override // java.lang.Runnable
                public final void run() {
                    a2.this.t();
                }
            }, this.f10978b);
            this.f10980d = null;
        }
    }
}
