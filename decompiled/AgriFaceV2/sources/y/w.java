package y;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.camera.core.impl.MetadataHolderService;
import androidx.concurrent.futures.c;
import b0.e0;
import b0.f0;
import b0.h3;
import b0.k2;
import b0.q0;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import y.s1;
import y.x;
/* loaded from: classes.dex */
public final class w {

    /* renamed from: o  reason: collision with root package name */
    private static final Object f12965o = new Object();

    /* renamed from: p  reason: collision with root package name */
    private static final SparseArray f12966p = new SparseArray();

    /* renamed from: a  reason: collision with root package name */
    final b0.l0 f12967a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f12968b;

    /* renamed from: c  reason: collision with root package name */
    private final x f12969c;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f12970d;

    /* renamed from: e  reason: collision with root package name */
    private final Handler f12971e;

    /* renamed from: f  reason: collision with root package name */
    private final HandlerThread f12972f;

    /* renamed from: g  reason: collision with root package name */
    private b0.f0 f12973g;

    /* renamed from: h  reason: collision with root package name */
    private b0.e0 f12974h;

    /* renamed from: i  reason: collision with root package name */
    private h3 f12975i;

    /* renamed from: j  reason: collision with root package name */
    private final s1 f12976j;

    /* renamed from: k  reason: collision with root package name */
    private final i4.d f12977k;

    /* renamed from: l  reason: collision with root package name */
    private a f12978l;

    /* renamed from: m  reason: collision with root package name */
    private i4.d f12979m;

    /* renamed from: n  reason: collision with root package name */
    private final Integer f12980n;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum a {
        UNINITIALIZED,
        INITIALIZING,
        INITIALIZING_ERROR,
        INITIALIZED,
        SHUTDOWN
    }

    public w(Context context, x.b bVar) {
        this(context, bVar, new k2());
    }

    private static x.b g(Context context) {
        Application b8 = d0.f.b(context);
        if (b8 instanceof x.b) {
            return (x.b) b8;
        }
        try {
            Context a8 = d0.f.a(context);
            Bundle bundle = a8.getPackageManager().getServiceInfo(new ComponentName(a8, MetadataHolderService.class), 640).metaData;
            String string = bundle != null ? bundle.getString("androidx.camera.core.impl.MetadataHolderService.DEFAULT_CONFIG_PROVIDER") : null;
            if (string == null) {
                f1.c("CameraX", "No default CameraXConfig.Provider specified in meta-data. The most likely cause is you did not include a default implementation in your build such as 'camera-camera2'.");
                return null;
            }
            return (x.b) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e8) {
            f1.d("CameraX", "Failed to retrieve default CameraXConfig.Provider from meta-data", e8);
            return null;
        }
    }

    private static void j(Integer num) {
        synchronized (f12965o) {
            if (num == null) {
                return;
            }
            g1.e.e(num.intValue(), 3, 6, "minLogLevel");
            SparseArray sparseArray = f12966p;
            sparseArray.put(num.intValue(), Integer.valueOf(sparseArray.get(num.intValue()) != null ? 1 + ((Integer) sparseArray.get(num.intValue())).intValue() : 1));
            r();
        }
    }

    private void k(final Executor executor, final long j8, final int i8, final Context context, final c.a aVar) {
        executor.execute(new Runnable() { // from class: y.u
            @Override // java.lang.Runnable
            public final void run() {
                w.this.n(context, executor, i8, aVar, j8);
            }
        });
    }

    private i4.d l(final Context context) {
        i4.d a8;
        synchronized (this.f12968b) {
            g1.e.m(this.f12978l == a.UNINITIALIZED, "CameraX.initInternal() should only be called once per instance");
            this.f12978l = a.INITIALIZING;
            a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: y.t
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object o8;
                    o8 = w.this.o(context, aVar);
                    return o8;
                }
            });
        }
        return a8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(Executor executor, long j8, int i8, Context context, c.a aVar) {
        k(executor, j8, i8 + 1, context, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(Context context, final Executor executor, final int i8, final c.a aVar, final long j8) {
        f0.a b02;
        z1.a.c("CX:initAndRetryRecursively");
        final Context a8 = d0.f.a(context);
        try {
            try {
                b02 = this.f12969c.b0(null);
            } finally {
                z1.a.f();
            }
        } catch (q0.b | RuntimeException | d1 e8) {
            e = e8;
            b0.j0 j0Var = new b0.j0(j8, i8, e);
            s1.c b8 = this.f12976j.b(j0Var);
            q(j0Var);
            if (!b8.d() || i8 >= Integer.MAX_VALUE) {
                synchronized (this.f12968b) {
                    this.f12978l = a.INITIALIZING_ERROR;
                }
                if (b8.c()) {
                    p();
                    aVar.c(null);
                } else {
                    if (e instanceof q0.b) {
                        String str = "Device reporting less cameras than anticipated. On real devices: Retrying initialization might resolve temporary camera errors. On emulators: Ensure virtual camera configuration matches supported camera features as reported by PackageManager#hasSystemFeature. Available cameras: " + ((q0.b) e).a();
                        f1.d("CameraX", str, e);
                        e = new d1(new s(3, str));
                    } else if (!(e instanceof d1)) {
                        aVar.f(new d1(e));
                    }
                    aVar.f(e);
                }
            } else {
                f1.m("CameraX", "Retry init. Start time " + j8 + " current time " + SystemClock.elapsedRealtime(), e);
                androidx.core.os.g.b(this.f12971e, new Runnable() { // from class: y.v
                    @Override // java.lang.Runnable
                    public final void run() {
                        w.this.m(executor, j8, i8, a8, aVar);
                    }
                }, "retry_token", b8.b());
            }
        }
        if (b02 == null) {
            throw new d1(new IllegalArgumentException("Invalid app configuration provided. Missing CameraFactory."));
        }
        b0.p0 a9 = b0.p0.a(this.f12970d, this.f12971e);
        q Z = this.f12969c.Z(null);
        this.f12973g = b02.a(a8, a9, Z, this.f12969c.c0());
        e0.a e02 = this.f12969c.e0(null);
        if (e02 == null) {
            throw new d1(new IllegalArgumentException("Invalid app configuration provided. Missing CameraDeviceSurfaceManager."));
        }
        this.f12974h = e02.a(a8, this.f12973g.b(), this.f12973g.c());
        h3.c h02 = this.f12969c.h0(null);
        if (h02 == null) {
            throw new d1(new IllegalArgumentException("Invalid app configuration provided. Missing UseCaseConfigFactory."));
        }
        this.f12975i = h02.a(a8);
        if (executor instanceof n) {
            ((n) executor).c(this.f12973g);
        }
        this.f12967a.b(this.f12973g);
        b0.q0.a(a8, this.f12967a, Z);
        if (i8 > 1) {
            q(null);
        }
        p();
        aVar.c(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object o(Context context, c.a aVar) {
        k(this.f12970d, SystemClock.elapsedRealtime(), 1, context, aVar);
        return "CameraX initInternal";
    }

    private void p() {
        synchronized (this.f12968b) {
            this.f12978l = a.INITIALIZED;
        }
    }

    private void q(s1.b bVar) {
        if (z1.a.h()) {
            z1.a.j("CX:CameraProvider-RetryStatus", bVar != null ? bVar.b() : -1);
        }
    }

    private static void r() {
        SparseArray sparseArray = f12966p;
        if (sparseArray.size() == 0) {
            f1.i();
            return;
        }
        int i8 = 3;
        if (sparseArray.get(3) == null) {
            i8 = 4;
            if (sparseArray.get(4) == null) {
                i8 = 5;
                if (sparseArray.get(5) == null) {
                    i8 = 6;
                    if (sparseArray.get(6) == null) {
                        return;
                    }
                }
            }
        }
        f1.j(i8);
    }

    private static void s(Context context, b0.i2 i2Var, m.a aVar) {
        StringBuilder sb;
        String str;
        if (i2Var != null) {
            sb = new StringBuilder();
            str = "QuirkSettings from CameraXConfig: ";
        } else {
            i2Var = (b0.i2) aVar.apply(context);
            sb = new StringBuilder();
            str = "QuirkSettings from app metadata: ";
        }
        sb.append(str);
        sb.append(i2Var);
        f1.a("CameraX", sb.toString());
        if (i2Var == null) {
            i2Var = b0.j2.f4252b;
            f1.a("CameraX", "QuirkSettings by default: " + i2Var);
        }
        b0.j2.b().d(i2Var);
    }

    public b0.e0 d() {
        b0.e0 e0Var = this.f12974h;
        if (e0Var != null) {
            return e0Var;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    public b0.f0 e() {
        b0.f0 f0Var = this.f12973g;
        if (f0Var != null) {
            return f0Var;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    public b0.l0 f() {
        return this.f12967a;
    }

    public h3 h() {
        h3 h3Var = this.f12975i;
        if (h3Var != null) {
            return h3Var;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    public i4.d i() {
        return this.f12977k;
    }

    w(Context context, x.b bVar, m.a aVar) {
        this.f12967a = new b0.l0();
        this.f12968b = new Object();
        this.f12978l = a.UNINITIALIZED;
        this.f12979m = f0.n.p(null);
        if (bVar == null && (bVar = g(context)) == null) {
            throw new IllegalStateException("CameraX is not configured properly. The most likely cause is you did not include a default implementation in your build such as 'camera-camera2'.");
        }
        this.f12969c = bVar.getCameraXConfig();
        s(context, this.f12969c.f0(), aVar);
        Executor a02 = this.f12969c.a0(null);
        Handler g02 = this.f12969c.g0(null);
        this.f12970d = a02 == null ? new n() : a02;
        if (g02 == null) {
            HandlerThread handlerThread = new HandlerThread("CameraX-scheduler", 10);
            this.f12972f = handlerThread;
            handlerThread.start();
            this.f12971e = androidx.core.os.g.a(handlerThread.getLooper());
        } else {
            this.f12972f = null;
            this.f12971e = g02;
        }
        Integer num = (Integer) this.f12969c.f(x.O, null);
        this.f12980n = num;
        j(num);
        this.f12976j = new s1.a(this.f12969c.d0()).a();
        this.f12977k = l(context);
    }
}
