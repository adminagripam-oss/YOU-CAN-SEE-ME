package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ImageWriter;
import androidx.camera.core.f;
import androidx.concurrent.futures.c;
import b0.q1;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import y.c1;
import y.f1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class i implements q1.a {

    /* renamed from: a  reason: collision with root package name */
    private f.a f2707a;

    /* renamed from: b  reason: collision with root package name */
    private volatile int f2708b;

    /* renamed from: c  reason: collision with root package name */
    private volatile int f2709c;

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f2711e;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f2712f;

    /* renamed from: g  reason: collision with root package name */
    private Executor f2713g;

    /* renamed from: h  reason: collision with root package name */
    private q f2714h;

    /* renamed from: i  reason: collision with root package name */
    private ImageWriter f2715i;

    /* renamed from: n  reason: collision with root package name */
    ByteBuffer f2720n;

    /* renamed from: o  reason: collision with root package name */
    ByteBuffer f2721o;

    /* renamed from: p  reason: collision with root package name */
    ByteBuffer f2722p;

    /* renamed from: q  reason: collision with root package name */
    ByteBuffer f2723q;

    /* renamed from: d  reason: collision with root package name */
    private volatile int f2710d = 1;

    /* renamed from: j  reason: collision with root package name */
    private Rect f2716j = new Rect();

    /* renamed from: k  reason: collision with root package name */
    private Rect f2717k = new Rect();

    /* renamed from: l  reason: collision with root package name */
    private Matrix f2718l = new Matrix();

    /* renamed from: m  reason: collision with root package name */
    private Matrix f2719m = new Matrix();

    /* renamed from: r  reason: collision with root package name */
    private final Object f2724r = new Object();

    /* renamed from: s  reason: collision with root package name */
    protected boolean f2725s = true;

    private void h(n nVar) {
        if (this.f2710d != 1) {
            if (this.f2710d == 2 && this.f2720n == null) {
                this.f2720n = ByteBuffer.allocateDirect(nVar.getWidth() * nVar.getHeight() * 4);
                return;
            }
            return;
        }
        if (this.f2721o == null) {
            this.f2721o = ByteBuffer.allocateDirect(nVar.getWidth() * nVar.getHeight());
        }
        this.f2721o.position(0);
        if (this.f2722p == null) {
            this.f2722p = ByteBuffer.allocateDirect((nVar.getWidth() * nVar.getHeight()) / 4);
        }
        this.f2722p.position(0);
        if (this.f2723q == null) {
            this.f2723q = ByteBuffer.allocateDirect((nVar.getWidth() * nVar.getHeight()) / 4);
        }
        this.f2723q.position(0);
    }

    private static q i(int i8, int i9, int i10, int i11, int i12) {
        boolean z7 = i10 == 90 || i10 == 270;
        int i13 = z7 ? i9 : i8;
        if (!z7) {
            i8 = i9;
        }
        return new q(o.a(i13, i8, i11, i12));
    }

    static Matrix k(int i8, int i9, int i10, int i11, int i12) {
        Matrix matrix = new Matrix();
        if (i12 > 0) {
            matrix.setRectToRect(new RectF(0.0f, 0.0f, i8, i9), d0.r.f6420a, Matrix.ScaleToFit.FILL);
            matrix.postRotate(i12);
            matrix.postConcat(d0.r.b(new RectF(0.0f, 0.0f, i10, i11)));
        }
        return matrix;
    }

    static Rect l(Rect rect, Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        Rect rect2 = new Rect();
        rectF.round(rect2);
        return rect2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(n nVar, Matrix matrix, n nVar2, Rect rect, f.a aVar, c.a aVar2) {
        if (!this.f2725s) {
            aVar2.f(new androidx.core.os.k("ImageAnalysis is detached"));
            return;
        }
        r rVar = new r(nVar2, c1.d(nVar.p().b(), nVar.p().c(), this.f2711e ? 0 : this.f2708b, matrix));
        if (!rect.isEmpty()) {
            rVar.l(rect);
        }
        aVar.a(rVar);
        aVar2.c(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object n(Executor executor, final n nVar, final Matrix matrix, final n nVar2, final Rect rect, final f.a aVar, final c.a aVar2) {
        executor.execute(new Runnable() { // from class: androidx.camera.core.h
            @Override // java.lang.Runnable
            public final void run() {
                i.this.m(nVar, matrix, nVar2, rect, aVar, aVar2);
            }
        });
        return "analyzeImage";
    }

    private void p(int i8, int i9, int i10, int i11) {
        Matrix k8 = k(i8, i9, i10, i11, this.f2708b);
        this.f2717k = l(this.f2716j, k8);
        this.f2719m.setConcat(this.f2718l, k8);
    }

    private void q(n nVar, int i8) {
        q qVar = this.f2714h;
        if (qVar == null) {
            return;
        }
        qVar.k();
        this.f2714h = i(nVar.getWidth(), nVar.getHeight(), i8, this.f2714h.a(), this.f2714h.e());
        if (this.f2710d == 1) {
            ImageWriter imageWriter = this.f2715i;
            if (imageWriter != null) {
                h0.a.a(imageWriter);
            }
            this.f2715i = h0.a.c(this.f2714h.getSurface(), this.f2714h.e());
        }
    }

    @Override // b0.q1.a
    public void a(q1 q1Var) {
        try {
            n d8 = d(q1Var);
            if (d8 != null) {
                o(d8);
            }
        } catch (IllegalStateException e8) {
            f1.d("ImageAnalysisAnalyzer", "Failed to acquire image.", e8);
        }
    }

    abstract n d(q1 q1Var);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public i4.d e(final androidx.camera.core.n r15) {
        /*
            Method dump skipped, instructions count: 195
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.i.e(androidx.camera.core.n):i4.d");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.f2725s = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void g();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        this.f2725s = false;
        g();
    }

    abstract void o(n nVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(Executor executor, f.a aVar) {
        if (aVar == null) {
            g();
        }
        synchronized (this.f2724r) {
            this.f2707a = aVar;
            this.f2713g = executor;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(boolean z7) {
        this.f2712f = z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(int i8) {
        this.f2710d = i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u(boolean z7) {
        this.f2711e = z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(q qVar) {
        synchronized (this.f2724r) {
            this.f2714h = qVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(int i8) {
        this.f2708b = i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(Matrix matrix) {
        synchronized (this.f2724r) {
            this.f2718l = matrix;
            this.f2719m = new Matrix(this.f2718l);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(Rect rect) {
        synchronized (this.f2724r) {
            this.f2716j = rect;
            this.f2717k = new Rect(this.f2716j);
        }
    }
}
