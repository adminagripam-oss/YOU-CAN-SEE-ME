package r;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import androidx.camera.camera2.internal.compat.quirk.CaptureSessionStuckQuirk;
import androidx.camera.camera2.internal.compat.quirk.IncorrectCaptureStateQuirk;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import v.h;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class u4 extends q4 {

    /* renamed from: o  reason: collision with root package name */
    private final ScheduledExecutorService f10805o;

    /* renamed from: p  reason: collision with root package name */
    private final Object f10806p;

    /* renamed from: q  reason: collision with root package name */
    private List f10807q;

    /* renamed from: r  reason: collision with root package name */
    i4.d f10808r;

    /* renamed from: s  reason: collision with root package name */
    private final v.i f10809s;

    /* renamed from: t  reason: collision with root package name */
    private final v.h f10810t;

    /* renamed from: u  reason: collision with root package name */
    private final v.s f10811u;

    /* renamed from: v  reason: collision with root package name */
    private final v.u f10812v;

    /* renamed from: w  reason: collision with root package name */
    private final AtomicBoolean f10813w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u4(b0.l2 l2Var, b0.l2 l2Var2, b3 b3Var, Executor executor, ScheduledExecutorService scheduledExecutorService, Handler handler) {
        super(b3Var, executor, scheduledExecutorService, handler);
        this.f10806p = new Object();
        boolean z7 = false;
        this.f10813w = new AtomicBoolean(false);
        this.f10809s = new v.i(l2Var, l2Var2);
        this.f10811u = new v.s((l2Var.a(CaptureSessionStuckQuirk.class) || l2Var.a(IncorrectCaptureStateQuirk.class)) ? true : true);
        this.f10810t = new v.h(l2Var2);
        this.f10812v = new v.u(l2Var2);
        this.f10805o = scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E() {
        O("Session call super.close()");
        super.close();
    }

    private void N() {
        for (k4 k4Var : this.f10712b.d()) {
            k4Var.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(k4 k4Var) {
        super.s(k4Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ i4.d Q(CameraDevice cameraDevice, t.q qVar, List list, List list2) {
        if (this.f10812v.a()) {
            N();
        }
        O("start openCaptureSession");
        return super.i(cameraDevice, qVar, list);
    }

    void O(String str) {
        y.f1.a("SyncCaptureSessionImpl", "[" + this + "] " + str);
    }

    @Override // r.q4, r.k4
    public void b() {
        super.b();
        this.f10811u.i();
    }

    @Override // r.q4, r.k4
    public void close() {
        if (!this.f10813w.compareAndSet(false, true)) {
            O("close() has been called. Skip this invocation.");
            return;
        }
        if (this.f10812v.a()) {
            try {
                O("Call abortCaptures() before closing session.");
                k();
            } catch (Exception e8) {
                O("Exception when calling abortCaptures()" + e8);
            }
        }
        O("Session call close()");
        this.f10811u.e().d(new Runnable() { // from class: r.r4
            @Override // java.lang.Runnable
            public final void run() {
                u4.this.E();
            }
        }, c());
    }

    @Override // r.q4, r.k4.a
    public i4.d e(List list, long j8) {
        i4.d e8;
        synchronized (this.f10806p) {
            this.f10807q = list;
            e8 = super.e(list, j8);
        }
        return e8;
    }

    @Override // r.q4, r.k4
    public int f(List list, CameraCaptureSession.CaptureCallback captureCallback) {
        return super.f(list, this.f10811u.d(captureCallback));
    }

    @Override // r.k4
    public i4.d g() {
        return f0.n.z(1500L, this.f10805o, this.f10811u.e());
    }

    @Override // r.q4, r.k4.a
    public i4.d i(final CameraDevice cameraDevice, final t.q qVar, final List list) {
        i4.d B;
        synchronized (this.f10806p) {
            List<k4> d8 = this.f10712b.d();
            ArrayList arrayList = new ArrayList();
            for (k4 k4Var : d8) {
                arrayList.add(k4Var.g());
            }
            i4.d F = f0.n.F(arrayList);
            this.f10808r = F;
            B = f0.n.B(f0.d.a(F).f(new f0.a() { // from class: r.s4
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d Q;
                    Q = u4.this.Q(cameraDevice, qVar, list, (List) obj);
                    return Q;
                }
            }, c()));
        }
        return B;
    }

    @Override // r.q4, r.k4
    public void j(int i8) {
        super.j(i8);
        if (i8 == 5) {
            synchronized (this.f10806p) {
                if (D() && this.f10807q != null) {
                    O("Close DeferrableSurfaces for CameraDevice error.");
                    for (b0.b1 b1Var : this.f10807q) {
                        b1Var.d();
                    }
                }
            }
        }
    }

    @Override // r.q4, r.k4
    public int n(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) {
        return super.n(captureRequest, this.f10811u.d(captureCallback));
    }

    @Override // r.q4, r.k4.c
    public void q(k4 k4Var) {
        synchronized (this.f10806p) {
            this.f10809s.a(this.f10807q);
        }
        O("onClosed()");
        super.q(k4Var);
    }

    @Override // r.q4, r.k4.c
    public void s(k4 k4Var) {
        O("Session onConfigured()");
        this.f10810t.c(k4Var, this.f10712b.e(), this.f10712b.d(), new h.a() { // from class: r.t4
            @Override // v.h.a
            public final void a(k4 k4Var2) {
                u4.this.P(k4Var2);
            }
        });
    }

    @Override // r.q4, r.k4.a
    public boolean stop() {
        boolean stop;
        synchronized (this.f10806p) {
            if (D()) {
                this.f10809s.a(this.f10807q);
            } else {
                i4.d dVar = this.f10808r;
                if (dVar != null) {
                    dVar.cancel(true);
                }
            }
            stop = super.stop();
        }
        return stop;
    }
}
