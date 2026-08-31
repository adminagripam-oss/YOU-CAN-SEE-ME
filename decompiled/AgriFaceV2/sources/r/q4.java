package r;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.concurrent.futures.c;
import b0.b1;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import r.k4;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class q4 extends k4.c implements k4, k4.a {

    /* renamed from: b  reason: collision with root package name */
    final b3 f10712b;

    /* renamed from: c  reason: collision with root package name */
    final Handler f10713c;

    /* renamed from: d  reason: collision with root package name */
    final Executor f10714d;

    /* renamed from: e  reason: collision with root package name */
    private final ScheduledExecutorService f10715e;

    /* renamed from: f  reason: collision with root package name */
    k4.c f10716f;

    /* renamed from: g  reason: collision with root package name */
    s.h f10717g;

    /* renamed from: h  reason: collision with root package name */
    i4.d f10718h;

    /* renamed from: i  reason: collision with root package name */
    c.a f10719i;

    /* renamed from: j  reason: collision with root package name */
    private i4.d f10720j;

    /* renamed from: a  reason: collision with root package name */
    final Object f10711a = new Object();

    /* renamed from: k  reason: collision with root package name */
    private List f10721k = null;

    /* renamed from: l  reason: collision with root package name */
    private boolean f10722l = false;

    /* renamed from: m  reason: collision with root package name */
    private boolean f10723m = false;

    /* renamed from: n  reason: collision with root package name */
    private boolean f10724n = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {
        a() {
        }

        @Override // f0.c
        public void b(Throwable th) {
            q4.this.b();
            q4 q4Var = q4.this;
            q4Var.f10712b.i(q4Var);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r12) {
        }
    }

    /* loaded from: classes.dex */
    class b extends CameraCaptureSession.StateCallback {
        b() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onActive(CameraCaptureSession cameraCaptureSession) {
            q4.this.B(cameraCaptureSession);
            q4 q4Var = q4.this;
            q4Var.o(q4Var);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onCaptureQueueEmpty(CameraCaptureSession cameraCaptureSession) {
            q4.this.B(cameraCaptureSession);
            q4 q4Var = q4.this;
            q4Var.p(q4Var);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(CameraCaptureSession cameraCaptureSession) {
            q4.this.B(cameraCaptureSession);
            q4 q4Var = q4.this;
            q4Var.q(q4Var);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            c.a aVar;
            try {
                q4.this.B(cameraCaptureSession);
                q4 q4Var = q4.this;
                q4Var.r(q4Var);
                synchronized (q4.this.f10711a) {
                    g1.e.k(q4.this.f10719i, "OpenCaptureSession completer should not null");
                    q4 q4Var2 = q4.this;
                    aVar = q4Var2.f10719i;
                    q4Var2.f10719i = null;
                }
                aVar.f(new IllegalStateException("onConfigureFailed"));
            } catch (Throwable th) {
                synchronized (q4.this.f10711a) {
                    g1.e.k(q4.this.f10719i, "OpenCaptureSession completer should not null");
                    q4 q4Var3 = q4.this;
                    c.a aVar2 = q4Var3.f10719i;
                    q4Var3.f10719i = null;
                    aVar2.f(new IllegalStateException("onConfigureFailed"));
                    throw th;
                }
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            c.a aVar;
            try {
                q4.this.B(cameraCaptureSession);
                q4 q4Var = q4.this;
                q4Var.s(q4Var);
                synchronized (q4.this.f10711a) {
                    g1.e.k(q4.this.f10719i, "OpenCaptureSession completer should not null");
                    q4 q4Var2 = q4.this;
                    aVar = q4Var2.f10719i;
                    q4Var2.f10719i = null;
                }
                aVar.c(null);
            } catch (Throwable th) {
                synchronized (q4.this.f10711a) {
                    g1.e.k(q4.this.f10719i, "OpenCaptureSession completer should not null");
                    q4 q4Var3 = q4.this;
                    c.a aVar2 = q4Var3.f10719i;
                    q4Var3.f10719i = null;
                    aVar2.c(null);
                    throw th;
                }
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onReady(CameraCaptureSession cameraCaptureSession) {
            q4.this.B(cameraCaptureSession);
            q4 q4Var = q4.this;
            q4Var.t(q4Var);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onSurfacePrepared(CameraCaptureSession cameraCaptureSession, Surface surface) {
            q4.this.B(cameraCaptureSession);
            q4 q4Var = q4.this;
            q4Var.v(q4Var, surface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q4(b3 b3Var, Executor executor, ScheduledExecutorService scheduledExecutorService, Handler handler) {
        this.f10712b = b3Var;
        this.f10713c = handler;
        this.f10714d = executor;
        this.f10715e = scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E() {
        u(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(k4 k4Var) {
        this.f10712b.g(this);
        u(k4Var);
        if (this.f10717g != null) {
            Objects.requireNonNull(this.f10716f);
            this.f10716f.q(k4Var);
            return;
        }
        y.f1.l("SyncCaptureSessionBase", "[" + this + "] Cannot call onClosed() when the CameraCaptureSession is not correctly configured.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G(k4 k4Var) {
        Objects.requireNonNull(this.f10716f);
        this.f10716f.u(k4Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object H(List list, s.c0 c0Var, t.q qVar, c.a aVar) {
        String str;
        synchronized (this.f10711a) {
            C(list);
            g1.e.m(this.f10719i == null, "The openCaptureSessionCompleter can only set once!");
            this.f10719i = aVar;
            c0Var.a(qVar);
            str = "openCaptureSession[session=" + this + "]";
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ i4.d I(List list, List list2) {
        y.f1.a("SyncCaptureSessionBase", "[" + this + "] getSurface done with results: " + list2);
        return list2.isEmpty() ? f0.n.n(new IllegalArgumentException("Unable to open capture session without surfaces")) : list2.contains(null) ? f0.n.n(new b1.a("Surface closed", (b0.b1) list.get(list2.indexOf(null)))) : f0.n.p(list2);
    }

    void B(CameraCaptureSession cameraCaptureSession) {
        if (this.f10717g == null) {
            this.f10717g = s.h.d(cameraCaptureSession, this.f10713c);
        }
    }

    void C(List list) {
        synchronized (this.f10711a) {
            J();
            b0.e1.d(list);
            this.f10721k = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean D() {
        boolean z7;
        synchronized (this.f10711a) {
            z7 = this.f10718h != null;
        }
        return z7;
    }

    void J() {
        synchronized (this.f10711a) {
            List list = this.f10721k;
            if (list != null) {
                b0.e1.c(list);
                this.f10721k = null;
            }
        }
    }

    @Override // r.k4
    public k4.c a() {
        return this;
    }

    @Override // r.k4
    public void b() {
        J();
    }

    @Override // r.k4.a
    public Executor c() {
        return this.f10714d;
    }

    @Override // r.k4
    public void close() {
        g1.e.k(this.f10717g, "Need to call openCaptureSession before using this API.");
        this.f10712b.h(this);
        this.f10717g.c().close();
        c().execute(new Runnable() { // from class: r.p4
            @Override // java.lang.Runnable
            public final void run() {
                q4.this.E();
            }
        });
    }

    @Override // r.k4
    public void d() {
        g1.e.k(this.f10717g, "Need to call openCaptureSession before using this API.");
        this.f10717g.c().stopRepeating();
    }

    @Override // r.k4.a
    public i4.d e(final List list, long j8) {
        synchronized (this.f10711a) {
            if (this.f10723m) {
                return f0.n.n(new CancellationException("Opener is disabled"));
            }
            f0.d f8 = f0.d.a(b0.e1.g(list, false, j8, c(), this.f10715e)).f(new f0.a() { // from class: r.l4
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d I;
                    I = q4.this.I(list, (List) obj);
                    return I;
                }
            }, c());
            this.f10720j = f8;
            return f0.n.B(f8);
        }
    }

    @Override // r.k4
    public int f(List list, CameraCaptureSession.CaptureCallback captureCallback) {
        g1.e.k(this.f10717g, "Need to call openCaptureSession before using this API.");
        return this.f10717g.a(list, c(), captureCallback);
    }

    @Override // r.k4
    public s.h h() {
        g1.e.j(this.f10717g);
        return this.f10717g;
    }

    @Override // r.k4.a
    public i4.d i(CameraDevice cameraDevice, final t.q qVar, final List list) {
        synchronized (this.f10711a) {
            if (this.f10723m) {
                return f0.n.n(new CancellationException("Opener is disabled"));
            }
            this.f10712b.k(this);
            final s.c0 b8 = s.c0.b(cameraDevice, this.f10713c);
            i4.d a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.o4
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object H;
                    H = q4.this.H(list, b8, qVar, aVar);
                    return H;
                }
            });
            this.f10718h = a8;
            f0.n.j(a8, new a(), e0.c.b());
            return f0.n.B(this.f10718h);
        }
    }

    @Override // r.k4
    public void j(int i8) {
    }

    @Override // r.k4
    public void k() {
        g1.e.k(this.f10717g, "Need to call openCaptureSession before using this API.");
        this.f10717g.c().abortCaptures();
    }

    @Override // r.k4.a
    public t.q l(int i8, List list, k4.c cVar) {
        this.f10716f = cVar;
        return new t.q(i8, list, c(), new b());
    }

    @Override // r.k4
    public CameraDevice m() {
        g1.e.j(this.f10717g);
        return this.f10717g.c().getDevice();
    }

    @Override // r.k4
    public int n(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) {
        g1.e.k(this.f10717g, "Need to call openCaptureSession before using this API.");
        return this.f10717g.b(captureRequest, c(), captureCallback);
    }

    @Override // r.k4.c
    public void o(k4 k4Var) {
        Objects.requireNonNull(this.f10716f);
        this.f10716f.o(k4Var);
    }

    @Override // r.k4.c
    public void p(k4 k4Var) {
        Objects.requireNonNull(this.f10716f);
        this.f10716f.p(k4Var);
    }

    @Override // r.k4.c
    public void q(final k4 k4Var) {
        i4.d dVar;
        synchronized (this.f10711a) {
            if (this.f10722l) {
                dVar = null;
            } else {
                this.f10722l = true;
                g1.e.k(this.f10718h, "Need to call openCaptureSession before using this API.");
                dVar = this.f10718h;
            }
        }
        b();
        if (dVar != null) {
            dVar.d(new Runnable() { // from class: r.m4
                @Override // java.lang.Runnable
                public final void run() {
                    q4.this.F(k4Var);
                }
            }, e0.c.b());
        }
    }

    @Override // r.k4.c
    public void r(k4 k4Var) {
        Objects.requireNonNull(this.f10716f);
        b();
        this.f10712b.i(this);
        this.f10716f.r(k4Var);
    }

    @Override // r.k4.c
    public void s(k4 k4Var) {
        Objects.requireNonNull(this.f10716f);
        this.f10712b.j(this);
        this.f10716f.s(k4Var);
    }

    @Override // r.k4.a
    public boolean stop() {
        boolean z7;
        try {
            synchronized (this.f10711a) {
                if (!this.f10723m) {
                    i4.d dVar = this.f10720j;
                    r1 = dVar != null ? dVar : null;
                    this.f10723m = true;
                }
                z7 = !D();
            }
            return z7;
        } finally {
            if (r1 != null) {
                r1.cancel(true);
            }
        }
    }

    @Override // r.k4.c
    public void t(k4 k4Var) {
        Objects.requireNonNull(this.f10716f);
        this.f10716f.t(k4Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.k4.c
    public void u(final k4 k4Var) {
        i4.d dVar;
        synchronized (this.f10711a) {
            if (this.f10724n) {
                dVar = null;
            } else {
                this.f10724n = true;
                g1.e.k(this.f10718h, "Need to call openCaptureSession before using this API.");
                dVar = this.f10718h;
            }
        }
        if (dVar != null) {
            dVar.d(new Runnable() { // from class: r.n4
                @Override // java.lang.Runnable
                public final void run() {
                    q4.this.G(k4Var);
                }
            }, e0.c.b());
        }
    }

    @Override // r.k4.c
    public void v(k4 k4Var, Surface surface) {
        Objects.requireNonNull(this.f10716f);
        this.f10716f.v(k4Var, surface);
    }
}
