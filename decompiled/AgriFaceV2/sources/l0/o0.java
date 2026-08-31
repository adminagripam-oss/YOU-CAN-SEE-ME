package l0;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Size;
import android.view.Surface;
import androidx.concurrent.futures.c;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import y.f1;
import y.v1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class o0 implements v1 {

    /* renamed from: f  reason: collision with root package name */
    private final Surface f8895f;

    /* renamed from: g  reason: collision with root package name */
    private final int f8896g;

    /* renamed from: h  reason: collision with root package name */
    private final int f8897h;

    /* renamed from: i  reason: collision with root package name */
    private final Size f8898i;

    /* renamed from: j  reason: collision with root package name */
    private final v1.a f8899j;

    /* renamed from: k  reason: collision with root package name */
    private final v1.a f8900k;

    /* renamed from: l  reason: collision with root package name */
    private final float[] f8901l;

    /* renamed from: m  reason: collision with root package name */
    private final float[] f8902m;

    /* renamed from: n  reason: collision with root package name */
    private final float[] f8903n;

    /* renamed from: o  reason: collision with root package name */
    private final float[] f8904o;

    /* renamed from: p  reason: collision with root package name */
    private g1.a f8905p;

    /* renamed from: q  reason: collision with root package name */
    private Executor f8906q;

    /* renamed from: t  reason: collision with root package name */
    private final i4.d f8909t;

    /* renamed from: u  reason: collision with root package name */
    private c.a f8910u;

    /* renamed from: v  reason: collision with root package name */
    private Matrix f8911v;

    /* renamed from: e  reason: collision with root package name */
    private final Object f8894e = new Object();

    /* renamed from: r  reason: collision with root package name */
    private boolean f8907r = false;

    /* renamed from: s  reason: collision with root package name */
    private boolean f8908s = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0(Surface surface, int i8, int i9, Size size, v1.a aVar, v1.a aVar2, Matrix matrix) {
        float[] fArr = new float[16];
        this.f8901l = fArr;
        float[] fArr2 = new float[16];
        this.f8902m = fArr2;
        float[] fArr3 = new float[16];
        this.f8903n = fArr3;
        float[] fArr4 = new float[16];
        this.f8904o = fArr4;
        this.f8895f = surface;
        this.f8896g = i8;
        this.f8897h = i9;
        this.f8898i = size;
        this.f8899j = aVar;
        this.f8900k = aVar2;
        this.f8911v = matrix;
        d(fArr, fArr3, aVar);
        d(fArr2, fArr4, aVar2);
        this.f8909t = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: l0.m0
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar3) {
                Object j8;
                j8 = o0.this.j(aVar3);
                return j8;
            }
        });
    }

    private static void d(float[] fArr, float[] fArr2, v1.a aVar) {
        android.opengl.Matrix.setIdentityM(fArr, 0);
        if (aVar == null) {
            return;
        }
        d0.n.d(fArr, 0.5f);
        d0.n.c(fArr, aVar.e(), 0.5f, 0.5f);
        if (aVar.d()) {
            android.opengl.Matrix.translateM(fArr, 0, 1.0f, 0.0f, 0.0f);
            android.opengl.Matrix.scaleM(fArr, 0, -1.0f, 1.0f, 1.0f);
        }
        Size o8 = d0.r.o(aVar.c(), aVar.e());
        Matrix d8 = d0.r.d(d0.r.r(aVar.c()), d0.r.r(o8), aVar.e(), aVar.d());
        RectF rectF = new RectF(aVar.b());
        d8.mapRect(rectF);
        android.opengl.Matrix.translateM(fArr, 0, rectF.left / o8.getWidth(), ((o8.getHeight() - rectF.height()) - rectF.top) / o8.getHeight(), 0.0f);
        android.opengl.Matrix.scaleM(fArr, 0, rectF.width() / o8.getWidth(), rectF.height() / o8.getHeight(), 1.0f);
        f(fArr2, aVar.a());
        android.opengl.Matrix.multiplyMM(fArr, 0, fArr2, 0, fArr, 0);
    }

    private static void f(float[] fArr, b0.h0 h0Var) {
        android.opengl.Matrix.setIdentityM(fArr, 0);
        d0.n.d(fArr, 0.5f);
        if (h0Var != null) {
            g1.e.m(h0Var.j(), "Camera has no transform.");
            d0.n.c(fArr, h0Var.b().c(), 0.5f, 0.5f);
            if (h0Var.i()) {
                android.opengl.Matrix.translateM(fArr, 0, 1.0f, 0.0f, 0.0f);
                android.opengl.Matrix.scaleM(fArr, 0, -1.0f, 1.0f, 1.0f);
            }
        }
        android.opengl.Matrix.invertM(fArr, 0, fArr, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object j(c.a aVar) {
        this.f8910u = aVar;
        return "SurfaceOutputImpl close future complete";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(AtomicReference atomicReference) {
        ((g1.a) atomicReference.get()).accept(v1.b.c(0, this));
    }

    @Override // y.v1
    public Surface N(Executor executor, g1.a aVar) {
        boolean z7;
        synchronized (this.f8894e) {
            this.f8906q = executor;
            this.f8905p = aVar;
            z7 = this.f8907r;
        }
        if (z7) {
            q();
        }
        return this.f8895f;
    }

    @Override // y.v1
    public Size O() {
        return this.f8898i;
    }

    @Override // y.v1, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.f8894e) {
            if (!this.f8908s) {
                this.f8908s = true;
            }
        }
        this.f8910u.c(null);
    }

    @Override // y.v1
    public int e() {
        return this.f8897h;
    }

    public i4.d g() {
        return this.f8909t;
    }

    public void q() {
        Executor executor;
        g1.a aVar;
        final AtomicReference atomicReference = new AtomicReference();
        synchronized (this.f8894e) {
            if (this.f8906q != null && (aVar = this.f8905p) != null) {
                if (!this.f8908s) {
                    atomicReference.set(aVar);
                    executor = this.f8906q;
                    this.f8907r = false;
                }
                executor = null;
            }
            this.f8907r = true;
            executor = null;
        }
        if (executor != null) {
            try {
                executor.execute(new Runnable() { // from class: l0.n0
                    @Override // java.lang.Runnable
                    public final void run() {
                        o0.this.n(atomicReference);
                    }
                });
            } catch (RejectedExecutionException e8) {
                f1.b("SurfaceOutputImpl", "Processor executor closed. Close request not posted.", e8);
            }
        }
    }

    @Override // y.v1
    public void r(float[] fArr, float[] fArr2, boolean z7) {
        android.opengl.Matrix.multiplyMM(fArr, 0, fArr2, 0, z7 ? this.f8901l : this.f8902m, 0);
    }

    @Override // y.v1
    public void s(float[] fArr, float[] fArr2) {
        r(fArr, fArr2, true);
    }
}
