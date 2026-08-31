package m0;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import androidx.concurrent.futures.c;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import l0.p0;
import y.a0;
import y.e1;
import y.f1;
import y.g2;
import y.v1;
/* loaded from: classes.dex */
public class o implements p0, SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    private final c f9103a;

    /* renamed from: b  reason: collision with root package name */
    final HandlerThread f9104b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f9105c;

    /* renamed from: d  reason: collision with root package name */
    final Handler f9106d;

    /* renamed from: e  reason: collision with root package name */
    private int f9107e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f9108f;

    /* renamed from: g  reason: collision with root package name */
    private final AtomicBoolean f9109g;

    /* renamed from: h  reason: collision with root package name */
    final Map f9110h;

    /* renamed from: i  reason: collision with root package name */
    private SurfaceTexture f9111i;

    /* renamed from: j  reason: collision with root package name */
    private SurfaceTexture f9112j;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static c7.q f9113a = new c7.q() { // from class: m0.n
            @Override // c7.q
            public final Object d(Object obj, Object obj2, Object obj3) {
                return new o((a0) obj, (e1) obj2, (e1) obj3);
            }
        };

        public static p0 a(a0 a0Var, e1 e1Var, e1 e1Var2) {
            return (p0) f9113a.d(a0Var, e1Var, e1Var2);
        }
    }

    o(a0 a0Var, Map map, e1 e1Var, e1 e1Var2) {
        this.f9107e = 0;
        this.f9108f = false;
        this.f9109g = new AtomicBoolean(false);
        this.f9110h = new LinkedHashMap();
        HandlerThread handlerThread = new HandlerThread("GL Thread");
        this.f9104b = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.f9106d = handler;
        this.f9105c = e0.c.f(handler);
        this.f9103a = new c(e1Var, e1Var2);
        try {
            p(a0Var, map);
        } catch (RuntimeException e8) {
            release();
            throw e8;
        }
    }

    private void m() {
        if (this.f9108f && this.f9107e == 0) {
            for (v1 v1Var : this.f9110h.keySet()) {
                v1Var.close();
            }
            this.f9110h.clear();
            this.f9103a.k();
            this.f9104b.quit();
        }
    }

    private void n(Runnable runnable) {
        o(runnable, new Runnable() { // from class: m0.l
            @Override // java.lang.Runnable
            public final void run() {
                o.q();
            }
        });
    }

    private void o(final Runnable runnable, final Runnable runnable2) {
        try {
            this.f9105c.execute(new Runnable() { // from class: m0.m
                @Override // java.lang.Runnable
                public final void run() {
                    o.this.r(runnable2, runnable);
                }
            });
        } catch (RejectedExecutionException e8) {
            f1.m("DualSurfaceProcessor", "Unable to executor runnable", e8);
            runnable2.run();
        }
    }

    private void p(final a0 a0Var, final Map map) {
        try {
            androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: m0.h
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object t8;
                    t8 = o.this.t(a0Var, map, aVar);
                    return t8;
                }
            }).get();
        } catch (InterruptedException | ExecutionException e8) {
            e = e8;
            if (e instanceof ExecutionException) {
                e = e.getCause();
            }
            if (!(e instanceof RuntimeException)) {
                throw new IllegalStateException("Failed to create DefaultSurfaceProcessor", e);
            }
            throw ((RuntimeException) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(Runnable runnable, Runnable runnable2) {
        if (this.f9108f) {
            runnable.run();
        } else {
            runnable2.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(a0 a0Var, Map map, c.a aVar) {
        try {
            this.f9103a.h(a0Var, map);
            aVar.c(null);
        } catch (RuntimeException e8) {
            aVar.f(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object t(final a0 a0Var, final Map map, final c.a aVar) {
        n(new Runnable() { // from class: m0.i
            @Override // java.lang.Runnable
            public final void run() {
                o.this.s(a0Var, map, aVar);
            }
        });
        return "Init GlRenderer";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(SurfaceTexture surfaceTexture, Surface surface, g2.g gVar) {
        surfaceTexture.setOnFrameAvailableListener(null);
        surfaceTexture.release();
        surface.release();
        this.f9107e--;
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(g2 g2Var) {
        this.f9107e++;
        final SurfaceTexture surfaceTexture = new SurfaceTexture(this.f9103a.t(g2Var.r()));
        surfaceTexture.setDefaultBufferSize(g2Var.o().getWidth(), g2Var.o().getHeight());
        final Surface surface = new Surface(surfaceTexture);
        g2Var.C(surface, this.f9105c, new g1.a() { // from class: m0.k
            @Override // g1.a
            public final void accept(Object obj) {
                o.this.u(surfaceTexture, surface, (g2.g) obj);
            }
        });
        if (g2Var.r()) {
            this.f9111i = surfaceTexture;
            return;
        }
        this.f9112j = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this, this.f9106d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(v1 v1Var, v1.b bVar) {
        v1Var.close();
        Surface surface = (Surface) this.f9110h.remove(v1Var);
        if (surface != null) {
            this.f9103a.r(surface);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(final v1 v1Var) {
        Surface N = v1Var.N(this.f9105c, new g1.a() { // from class: m0.j
            @Override // g1.a
            public final void accept(Object obj) {
                o.this.w(v1Var, (v1.b) obj);
            }
        });
        this.f9103a.j(N);
        this.f9110h.put(v1Var, N);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y() {
        this.f9108f = true;
        m();
    }

    @Override // y.w1
    public void a(final v1 v1Var) {
        if (this.f9109g.get()) {
            v1Var.close();
            return;
        }
        Runnable runnable = new Runnable() { // from class: m0.f
            @Override // java.lang.Runnable
            public final void run() {
                o.this.x(v1Var);
            }
        };
        Objects.requireNonNull(v1Var);
        o(runnable, new l0.m(v1Var));
    }

    @Override // y.w1
    public void b(final g2 g2Var) {
        if (this.f9109g.get()) {
            g2Var.F();
            return;
        }
        Runnable runnable = new Runnable() { // from class: m0.g
            @Override // java.lang.Runnable
            public final void run() {
                o.this.v(g2Var);
            }
        };
        Objects.requireNonNull(g2Var);
        o(runnable, new l0.o(g2Var));
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        SurfaceTexture surfaceTexture2;
        if (this.f9109g.get() || (surfaceTexture2 = this.f9111i) == null || this.f9112j == null) {
            return;
        }
        surfaceTexture2.updateTexImage();
        this.f9112j.updateTexImage();
        for (Map.Entry entry : this.f9110h.entrySet()) {
            Surface surface = (Surface) entry.getValue();
            v1 v1Var = (v1) entry.getKey();
            if (v1Var.e() == 34) {
                try {
                    this.f9103a.v(surfaceTexture.getTimestamp(), surface, v1Var, this.f9111i, this.f9112j);
                } catch (RuntimeException e8) {
                    f1.d("DualSurfaceProcessor", "Failed to render with OpenGL.", e8);
                }
            }
        }
    }

    @Override // l0.p0
    public void release() {
        if (this.f9109g.getAndSet(true)) {
            return;
        }
        n(new Runnable() { // from class: m0.e
            @Override // java.lang.Runnable
            public final void run() {
                o.this.y();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(a0 a0Var, e1 e1Var, e1 e1Var2) {
        this(a0Var, Collections.emptyMap(), e1Var, e1Var2);
    }
}
