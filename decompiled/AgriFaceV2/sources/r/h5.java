package r;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Looper;
import android.util.Range;
import androidx.concurrent.futures.c;
import java.util.concurrent.Executor;
import q.a;
import r.t;
import y.k;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h5 {

    /* renamed from: a  reason: collision with root package name */
    private final t f10510a;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f10511b;

    /* renamed from: c  reason: collision with root package name */
    private final i5 f10512c;

    /* renamed from: d  reason: collision with root package name */
    private final androidx.lifecycle.s f10513d;

    /* renamed from: e  reason: collision with root package name */
    final b f10514e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f10515f = false;

    /* renamed from: g  reason: collision with root package name */
    private t.c f10516g = new a();

    /* loaded from: classes.dex */
    class a implements t.c {
        a() {
        }

        @Override // r.t.c
        public boolean a(TotalCaptureResult totalCaptureResult) {
            h5.this.f10514e.a(totalCaptureResult);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        void a(TotalCaptureResult totalCaptureResult);

        void b(float f8, c.a aVar);

        Rect c();

        float d();

        void e(a.C0140a c0140a);

        float f();

        void g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h5(t tVar, s.b0 b0Var, Executor executor) {
        this.f10510a = tVar;
        this.f10511b = executor;
        b d8 = d(b0Var);
        this.f10514e = d8;
        i5 i5Var = new i5(d8.d(), d8.f());
        this.f10512c = i5Var;
        i5Var.f(1.0f);
        this.f10513d = new androidx.lifecycle.s(g0.g.f(i5Var));
        tVar.A(this.f10516g);
    }

    private static b d(s.b0 b0Var) {
        return i(b0Var) ? new c(b0Var) : new c3(b0Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static y.j2 f(s.b0 b0Var) {
        b d8 = d(b0Var);
        i5 i5Var = new i5(d8.d(), d8.f());
        i5Var.f(1.0f);
        return g0.g.f(i5Var);
    }

    private static Range g(s.b0 b0Var) {
        CameraCharacteristics.Key key;
        try {
            key = CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE;
            return (Range) b0Var.a(key);
        } catch (AssertionError e8) {
            y.f1.m("ZoomControl", "AssertionError, fail to get camera characteristic.", e8);
            return null;
        }
    }

    static boolean i(s.b0 b0Var) {
        return Build.VERSION.SDK_INT >= 30 && g(b0Var) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object k(final y.j2 j2Var, final c.a aVar) {
        this.f10511b.execute(new Runnable() { // from class: r.g5
            @Override // java.lang.Runnable
            public final void run() {
                h5.this.j(aVar, j2Var);
            }
        });
        return "setZoomRatio";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public void j(c.a aVar, y.j2 j2Var) {
        y.j2 f8;
        if (this.f10515f) {
            this.f10514e.b(j2Var.b(), aVar);
            this.f10510a.u0();
            return;
        }
        synchronized (this.f10512c) {
            this.f10512c.f(1.0f);
            f8 = g0.g.f(this.f10512c);
        }
        o(f8);
        aVar.f(new k.a("Camera is not active."));
    }

    private void o(y.j2 j2Var) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.f10513d.o(j2Var);
        } else {
            this.f10513d.l(j2Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(a.C0140a c0140a) {
        this.f10514e.e(c0140a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Rect e() {
        return this.f10514e.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.lifecycle.p h() {
        return this.f10513d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(boolean z7) {
        y.j2 f8;
        if (this.f10515f == z7) {
            return;
        }
        this.f10515f = z7;
        if (z7) {
            return;
        }
        synchronized (this.f10512c) {
            this.f10512c.f(1.0f);
            f8 = g0.g.f(this.f10512c);
        }
        o(f8);
        this.f10514e.g();
        this.f10510a.u0();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d m(float f8) {
        final y.j2 f9;
        synchronized (this.f10512c) {
            try {
                this.f10512c.f(f8);
                f9 = g0.g.f(this.f10512c);
            } catch (IllegalArgumentException e8) {
                return f0.n.n(e8);
            }
        }
        o(f9);
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.f5
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object k8;
                k8 = h5.this.k(f9, aVar);
                return k8;
            }
        });
    }
}
