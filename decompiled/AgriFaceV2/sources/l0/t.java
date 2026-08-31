package l0;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ImageProcessingUtil;
import androidx.concurrent.futures.c;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import n0.d;
import y.f1;
import y.g2;
import y.v1;
/* loaded from: classes.dex */
public class t implements p0, SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    private final x f8925a;

    /* renamed from: b  reason: collision with root package name */
    final HandlerThread f8926b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f8927c;

    /* renamed from: d  reason: collision with root package name */
    final Handler f8928d;

    /* renamed from: e  reason: collision with root package name */
    private final AtomicBoolean f8929e;

    /* renamed from: f  reason: collision with root package name */
    private final float[] f8930f;

    /* renamed from: g  reason: collision with root package name */
    private final float[] f8931g;

    /* renamed from: h  reason: collision with root package name */
    final Map f8932h;

    /* renamed from: i  reason: collision with root package name */
    private int f8933i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f8934j;

    /* renamed from: k  reason: collision with root package name */
    private final List f8935k;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static m.a f8936a = new m.a() { // from class: l0.s
            @Override // m.a
            public final Object apply(Object obj) {
                return new t((y.a0) obj);
            }
        };

        public static p0 a(y.a0 a0Var) {
            return (p0) f8936a.apply(a0Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class b {
        static l0.a d(int i8, int i9, c.a aVar) {
            return new l0.a(i8, i9, aVar);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract c.a a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int b();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(y.a0 a0Var) {
        this(a0Var, Collections.emptyMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(g2 g2Var, g2.h hVar) {
        d.e eVar = d.e.DEFAULT;
        if (g2Var.m().d() && hVar.e()) {
            eVar = d.e.YUV;
        }
        this.f8925a.o(eVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(g2 g2Var, SurfaceTexture surfaceTexture, Surface surface, g2.g gVar) {
        g2Var.j();
        surfaceTexture.setOnFrameAvailableListener(null);
        surfaceTexture.release();
        surface.release();
        this.f8933i--;
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C(final g2 g2Var) {
        this.f8933i++;
        final SurfaceTexture surfaceTexture = new SurfaceTexture(this.f8925a.g());
        surfaceTexture.setDefaultBufferSize(g2Var.o().getWidth(), g2Var.o().getHeight());
        final Surface surface = new Surface(surfaceTexture);
        g2Var.D(this.f8927c, new g2.i() { // from class: l0.f
            @Override // y.g2.i
            public final void a(g2.h hVar) {
                t.this.A(g2Var, hVar);
            }
        });
        g2Var.C(surface, this.f8927c, new g1.a() { // from class: l0.g
            @Override // g1.a
            public final void accept(Object obj) {
                t.this.B(g2Var, surfaceTexture, surface, (g2.g) obj);
            }
        });
        surfaceTexture.setOnFrameAvailableListener(this, this.f8928d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D(v1 v1Var, v1.b bVar) {
        v1Var.close();
        Surface surface = (Surface) this.f8932h.remove(v1Var);
        if (surface != null) {
            this.f8925a.r(surface);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(final v1 v1Var) {
        Surface N = v1Var.N(this.f8927c, new g1.a() { // from class: l0.h
            @Override // g1.a
            public final void accept(Object obj) {
                t.this.D(v1Var, (v1.b) obj);
            }
        });
        this.f8925a.j(N);
        this.f8932h.put(v1Var, N);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F() {
        this.f8934j = true;
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G(b bVar) {
        this.f8935k.add(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void H(c.a aVar) {
        aVar.f(new Exception("Failed to snapshot: OpenGLRenderer not ready."));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object I(int i8, int i9, final c.a aVar) {
        final l0.a d8 = b.d(i8, i9, aVar);
        s(new Runnable() { // from class: l0.d
            @Override // java.lang.Runnable
            public final void run() {
                t.this.G(d8);
            }
        }, new Runnable() { // from class: l0.j
            @Override // java.lang.Runnable
            public final void run() {
                t.H(c.a.this);
            }
        });
        return "DefaultSurfaceProcessor#snapshot";
    }

    private void J(r6.n nVar) {
        if (this.f8935k.isEmpty()) {
            return;
        }
        if (nVar == null) {
            t(new Exception("Failed to snapshot: no JPEG Surface."));
            return;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterator it = this.f8935k.iterator();
            int i8 = -1;
            int i9 = -1;
            Bitmap bitmap = null;
            byte[] bArr = null;
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (i8 != bVar.c() || bitmap == null) {
                    i8 = bVar.c();
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    bitmap = u((Size) nVar.b(), (float[]) nVar.c(), i8);
                    i9 = -1;
                }
                if (i9 != bVar.b()) {
                    byteArrayOutputStream.reset();
                    i9 = bVar.b();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i9, byteArrayOutputStream);
                    bArr = byteArrayOutputStream.toByteArray();
                }
                Objects.requireNonNull(bArr);
                ImageProcessingUtil.q((Surface) nVar.a(), bArr);
                bVar.a().c(null);
                it.remove();
            }
            byteArrayOutputStream.close();
        } catch (IOException e8) {
            t(e8);
        }
    }

    private void q() {
        if (this.f8934j && this.f8933i == 0) {
            for (v1 v1Var : this.f8932h.keySet()) {
                v1Var.close();
            }
            for (b bVar : this.f8935k) {
                bVar.a().f(new Exception("Failed to snapshot: DefaultSurfaceProcessor is released."));
            }
            this.f8932h.clear();
            this.f8925a.k();
            this.f8926b.quit();
        }
    }

    private void r(Runnable runnable) {
        s(runnable, new Runnable() { // from class: l0.e
            @Override // java.lang.Runnable
            public final void run() {
                t.w();
            }
        });
    }

    private void s(final Runnable runnable, final Runnable runnable2) {
        try {
            this.f8927c.execute(new Runnable() { // from class: l0.q
                @Override // java.lang.Runnable
                public final void run() {
                    t.this.x(runnable2, runnable);
                }
            });
        } catch (RejectedExecutionException e8) {
            f1.m("DefaultSurfaceProcessor", "Unable to executor runnable", e8);
            runnable2.run();
        }
    }

    private void t(Throwable th) {
        for (b bVar : this.f8935k) {
            bVar.a().f(th);
        }
        this.f8935k.clear();
    }

    private Bitmap u(Size size, float[] fArr, int i8) {
        float[] fArr2 = (float[]) fArr.clone();
        d0.n.c(fArr2, i8, 0.5f, 0.5f);
        d0.n.d(fArr2, 0.5f);
        return this.f8925a.p(d0.r.o(size, i8), fArr2);
    }

    private void v(final y.a0 a0Var, final Map map) {
        try {
            androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: l0.k
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object y7;
                    y7 = t.this.y(a0Var, map, aVar);
                    return y7;
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
    public static /* synthetic */ void w() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(Runnable runnable, Runnable runnable2) {
        if (this.f8934j) {
            runnable.run();
        } else {
            runnable2.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object y(final y.a0 a0Var, final Map map, final c.a aVar) {
        r(new Runnable() { // from class: l0.r
            @Override // java.lang.Runnable
            public final void run() {
                t.this.z(a0Var, map, aVar);
            }
        });
        return "Init GlRenderer";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(y.a0 a0Var, Map map, c.a aVar) {
        try {
            this.f8925a.h(a0Var, map);
            aVar.c(null);
        } catch (RuntimeException e8) {
            aVar.f(e8);
        }
    }

    @Override // y.w1
    public void a(final v1 v1Var) {
        if (this.f8929e.get()) {
            v1Var.close();
            return;
        }
        Runnable runnable = new Runnable() { // from class: l0.l
            @Override // java.lang.Runnable
            public final void run() {
                t.this.E(v1Var);
            }
        };
        Objects.requireNonNull(v1Var);
        s(runnable, new m(v1Var));
    }

    @Override // y.w1
    public void b(final g2 g2Var) {
        if (this.f8929e.get()) {
            g2Var.F();
            return;
        }
        Runnable runnable = new Runnable() { // from class: l0.n
            @Override // java.lang.Runnable
            public final void run() {
                t.this.C(g2Var);
            }
        };
        Objects.requireNonNull(g2Var);
        s(runnable, new o(g2Var));
    }

    @Override // l0.p0
    public i4.d c(final int i8, final int i9) {
        return f0.n.B(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: l0.i
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object I;
                I = t.this.I(i8, i9, aVar);
                return I;
            }
        }));
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (this.f8929e.get()) {
            return;
        }
        surfaceTexture.updateTexImage();
        surfaceTexture.getTransformMatrix(this.f8930f);
        r6.n nVar = null;
        for (Map.Entry entry : this.f8932h.entrySet()) {
            Surface surface = (Surface) entry.getValue();
            v1 v1Var = (v1) entry.getKey();
            v1Var.s(this.f8931g, this.f8930f);
            if (v1Var.e() == 34) {
                try {
                    this.f8925a.n(surfaceTexture.getTimestamp(), this.f8931g, surface);
                } catch (RuntimeException e8) {
                    f1.d("DefaultSurfaceProcessor", "Failed to render with OpenGL.", e8);
                }
            } else {
                boolean z7 = v1Var.e() == 256;
                g1.e.m(z7, "Unsupported format: " + v1Var.e());
                g1.e.m(nVar == null, "Only one JPEG output is supported.");
                nVar = new r6.n(surface, v1Var.O(), (float[]) this.f8931g.clone());
            }
        }
        try {
            J(nVar);
        } catch (RuntimeException e9) {
            t(e9);
        }
    }

    @Override // l0.p0
    public void release() {
        if (this.f8929e.getAndSet(true)) {
            return;
        }
        r(new Runnable() { // from class: l0.p
            @Override // java.lang.Runnable
            public final void run() {
                t.this.F();
            }
        });
    }

    t(y.a0 a0Var, Map map) {
        this.f8929e = new AtomicBoolean(false);
        this.f8930f = new float[16];
        this.f8931g = new float[16];
        this.f8932h = new LinkedHashMap();
        this.f8933i = 0;
        this.f8934j = false;
        this.f8935k = new ArrayList();
        HandlerThread handlerThread = new HandlerThread("GL Thread");
        this.f8926b = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.f8928d = handler;
        this.f8927c = e0.c.f(handler);
        this.f8925a = new x();
        try {
            v(a0Var, map);
        } catch (RuntimeException e8) {
            release();
            throw e8;
        }
    }
}
