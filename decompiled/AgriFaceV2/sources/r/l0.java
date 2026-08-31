package r;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.CamcorderProfile;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.quirk.LegacyCameraOutputConfigNullPointerQuirk;
import androidx.camera.camera2.internal.compat.quirk.LegacyCameraSurfaceCleanupQuirk;
import androidx.concurrent.futures.c;
import b0.b1;
import b0.d0;
import b0.f3;
import b0.h0;
import b0.h3;
import b0.o0;
import b0.q2;
import b0.s0;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.face.ModuleDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import r.f4;
import r.k4;
import r.l0;
import y.r;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l0 implements b0.h0 {
    private final boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private f4 E;
    private final b3 F;
    private final k4.b G;
    private final Set H;
    private b0.z I;
    final Object J;
    boolean K;
    private final d3 L;
    private final s.b0 M;
    private final t.g N;
    private final j4 O;
    private final h P;

    /* renamed from: e  reason: collision with root package name */
    private final b0.f3 f10587e;

    /* renamed from: f  reason: collision with root package name */
    private final s.o0 f10588f;

    /* renamed from: g  reason: collision with root package name */
    private final Executor f10589g;

    /* renamed from: h  reason: collision with root package name */
    private final ScheduledExecutorService f10590h;

    /* renamed from: i  reason: collision with root package name */
    volatile i f10591i = i.INITIALIZED;

    /* renamed from: j  reason: collision with root package name */
    private final b0.y1 f10592j;

    /* renamed from: k  reason: collision with root package name */
    private final m2 f10593k;

    /* renamed from: l  reason: collision with root package name */
    private final t f10594l;

    /* renamed from: m  reason: collision with root package name */
    private final j f10595m;

    /* renamed from: n  reason: collision with root package name */
    final r0 f10596n;

    /* renamed from: o  reason: collision with root package name */
    CameraDevice f10597o;

    /* renamed from: p  reason: collision with root package name */
    int f10598p;

    /* renamed from: q  reason: collision with root package name */
    y2 f10599q;

    /* renamed from: r  reason: collision with root package name */
    final AtomicInteger f10600r;

    /* renamed from: s  reason: collision with root package name */
    c.a f10601s;

    /* renamed from: t  reason: collision with root package name */
    final Map f10602t;

    /* renamed from: u  reason: collision with root package name */
    private int f10603u;

    /* renamed from: v  reason: collision with root package name */
    final e f10604v;

    /* renamed from: w  reason: collision with root package name */
    final f f10605w;

    /* renamed from: x  reason: collision with root package name */
    final z.a f10606x;

    /* renamed from: y  reason: collision with root package name */
    final b0.o0 f10607y;

    /* renamed from: z  reason: collision with root package name */
    private final boolean f10608z;

    /* loaded from: classes.dex */
    class a implements r.f {
        a() {
        }

        @Override // r.f
        public CamcorderProfile a(int i8, int i9) {
            return CamcorderProfile.get(i8, i9);
        }

        @Override // r.f
        public boolean b(int i8, int i9) {
            return CamcorderProfile.hasProfile(i8, i9);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends CameraDevice.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c.a f10610a;

        b(c.a aVar) {
            this.f10610a = aVar;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            l0.this.T("openCameraConfigAndClose camera closed");
            this.f10610a.c(null);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            l0.this.T("openCameraConfigAndClose camera disconnected");
            this.f10610a.c(null);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i8) {
            l0 l0Var = l0.this;
            l0Var.T("openCameraConfigAndClose camera error " + i8);
            this.f10610a.c(null);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(final CameraDevice cameraDevice) {
            l0.this.T("openCameraConfigAndClose camera opened");
            i4.d Q = l0.this.Q(cameraDevice);
            Objects.requireNonNull(cameraDevice);
            Q.d(new Runnable() { // from class: r.m0
                @Override // java.lang.Runnable
                public final void run() {
                    cameraDevice.close();
                }
            }, l0.this.f10589g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ y2 f10612a;

        c(y2 y2Var) {
            this.f10612a = y2Var;
        }

        @Override // f0.c
        public void b(Throwable th) {
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r22) {
            l0.this.f10602t.remove(this.f10612a);
            int ordinal = l0.this.f10591i.ordinal();
            if (ordinal != 1 && ordinal != 4) {
                if (ordinal != 5 && (ordinal != 6 || l0.this.f10598p == 0)) {
                    return;
                }
                l0.this.T("Camera reopen required. Checking if the current camera can be closed safely.");
            }
            if (l0.this.d0()) {
                l0 l0Var = l0.this;
                if (l0Var.f10597o != null) {
                    l0Var.T("closing camera");
                    s.a.a(l0.this.f10597o);
                    l0.this.f10597o = null;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ y2 f10614a;

        d(y2 y2Var) {
            this.f10614a = y2Var;
        }

        @Override // f0.c
        public void b(Throwable th) {
            if (th instanceof b1.a) {
                b0.q2 V = l0.this.V(((b1.a) th).a());
                if (V != null) {
                    l0.this.B0(V);
                }
            } else if (th instanceof CancellationException) {
                l0.this.T("Unable to configure camera cancelled");
            } else {
                i iVar = l0.this.f10591i;
                i iVar2 = i.OPENED;
                if (iVar == iVar2) {
                    l0.this.H0(iVar2, r.a.b(4, th));
                }
                y.f1.d("Camera2CameraImpl", "Unable to configure camera " + l0.this, th);
                l0 l0Var = l0.this;
                if (l0Var.f10599q == this.f10614a) {
                    l0Var.E0(false);
                }
            }
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r22) {
            if (l0.this.f10606x.a() == 2 && l0.this.f10591i == i.OPENED) {
                l0.this.G0(i.CONFIGURED);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class e extends CameraManager.AvailabilityCallback implements o0.c {

        /* renamed from: a  reason: collision with root package name */
        private final String f10616a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f10617b = true;

        e(String str) {
            this.f10616a = str;
        }

        @Override // b0.o0.c
        public void a() {
            if (l0.this.f10591i == i.PENDING_OPEN) {
                l0.this.P0(false);
            }
        }

        boolean b() {
            return this.f10617b;
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(String str) {
            if (this.f10616a.equals(str)) {
                this.f10617b = true;
                if (l0.this.f10591i == i.PENDING_OPEN) {
                    l0.this.P0(false);
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(String str) {
            if (this.f10616a.equals(str)) {
                this.f10617b = false;
            }
        }
    }

    /* loaded from: classes.dex */
    final class f implements o0.b {
        f() {
        }

        @Override // b0.o0.b
        public void a() {
            if (l0.this.f10591i == i.OPENED) {
                l0.this.z0();
            }
        }
    }

    /* loaded from: classes.dex */
    final class g implements d0.d {
        g() {
        }

        @Override // b0.d0.d
        public void a() {
            l0.this.Q0();
        }

        @Override // b0.d0.d
        public void b(List list) {
            l0.this.J0((List) g1.e.j(list));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class h {

        /* renamed from: a  reason: collision with root package name */
        private a f10621a;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class a {

            /* renamed from: a  reason: collision with root package name */
            private final ScheduledFuture f10623a;

            /* renamed from: b  reason: collision with root package name */
            private final AtomicBoolean f10624b = new AtomicBoolean(false);

            a() {
                this.f10623a = l0.this.f10590h.schedule(new Runnable() { // from class: r.n0
                    @Override // java.lang.Runnable
                    public final void run() {
                        l0.h.a.this.d();
                    }
                }, 2000L, TimeUnit.MILLISECONDS);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void d() {
                if (this.f10624b.getAndSet(true)) {
                    return;
                }
                l0.this.f10589g.execute(new Runnable() { // from class: r.o0
                    @Override // java.lang.Runnable
                    public final void run() {
                        l0.h.a.this.e();
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void e() {
                if (l0.this.f10591i == i.OPENING) {
                    l0.this.T("Camera onError timeout, reopen it.");
                    l0.this.G0(i.REOPENING);
                    l0.this.f10595m.e();
                    return;
                }
                l0 l0Var = l0.this;
                l0Var.T("Camera skip reopen at state: " + l0.this.f10591i);
            }

            public void c() {
                this.f10624b.set(true);
                this.f10623a.cancel(true);
            }

            public boolean f() {
                return this.f10624b.get();
            }
        }

        private h() {
            this.f10621a = null;
        }

        public void a() {
            a aVar = this.f10621a;
            if (aVar != null) {
                aVar.c();
            }
            this.f10621a = null;
        }

        public void b() {
            l0.this.T("Camera receive onErrorCallback");
            a();
        }

        public boolean c() {
            a aVar = this.f10621a;
            return (aVar == null || aVar.f()) ? false : true;
        }

        public void d() {
            if (l0.this.f10591i != i.OPENING) {
                l0.this.T("Don't need the onError timeout handler.");
                return;
            }
            l0.this.T("Camera waiting for onError.");
            a();
            this.f10621a = new a();
        }

        /* synthetic */ h(l0 l0Var, a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum i {
        RELEASED,
        RELEASING,
        INITIALIZED,
        PENDING_OPEN,
        CLOSING,
        REOPENING_QUIRK,
        REOPENING,
        OPENING,
        OPENED,
        CONFIGURED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class j extends CameraDevice.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        private final Executor f10637a;

        /* renamed from: b  reason: collision with root package name */
        private final ScheduledExecutorService f10638b;

        /* renamed from: c  reason: collision with root package name */
        private b f10639c;

        /* renamed from: d  reason: collision with root package name */
        ScheduledFuture f10640d;

        /* renamed from: e  reason: collision with root package name */
        private final a f10641e;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a {

            /* renamed from: a  reason: collision with root package name */
            private final long f10643a;

            /* renamed from: b  reason: collision with root package name */
            private long f10644b = -1;

            a(long j8) {
                this.f10643a = j8;
            }

            boolean a() {
                if (b() >= ((long) d())) {
                    e();
                    return false;
                }
                return true;
            }

            long b() {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (this.f10644b == -1) {
                    this.f10644b = uptimeMillis;
                }
                return uptimeMillis - this.f10644b;
            }

            int c() {
                if (j.this.f()) {
                    long b8 = b();
                    if (b8 <= 120000) {
                        return 1000;
                    }
                    return b8 <= 300000 ? 2000 : 4000;
                }
                return 700;
            }

            int d() {
                boolean f8 = j.this.f();
                long j8 = this.f10643a;
                if (!f8) {
                    return j8 > 0 ? Math.min((int) j8, (int) ModuleDescriptor.MODULE_VERSION) : ModuleDescriptor.MODULE_VERSION;
                } else if (j8 > 0) {
                    return Math.min((int) j8, 1800000);
                } else {
                    return 1800000;
                }
            }

            void e() {
                this.f10644b = -1L;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            private Executor f10646e;

            /* renamed from: f  reason: collision with root package name */
            private boolean f10647f = false;

            b(Executor executor) {
                this.f10646e = executor;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void c() {
                if (this.f10647f) {
                    return;
                }
                g1.e.l(l0.this.f10591i == i.REOPENING || l0.this.f10591i == i.REOPENING_QUIRK);
                if (j.this.f()) {
                    l0.this.O0(true);
                } else {
                    l0.this.P0(true);
                }
            }

            void b() {
                this.f10647f = true;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f10646e.execute(new Runnable() { // from class: r.p0
                    @Override // java.lang.Runnable
                    public final void run() {
                        l0.j.b.this.c();
                    }
                });
            }
        }

        j(Executor executor, ScheduledExecutorService scheduledExecutorService, long j8) {
            this.f10637a = executor;
            this.f10638b = scheduledExecutorService;
            this.f10641e = new a(j8);
        }

        private void b(CameraDevice cameraDevice, int i8) {
            boolean z7 = l0.this.f10591i == i.OPENING || l0.this.f10591i == i.OPENED || l0.this.f10591i == i.CONFIGURED || l0.this.f10591i == i.REOPENING || l0.this.f10591i == i.REOPENING_QUIRK;
            g1.e.m(z7, "Attempt to handle open error from non open state: " + l0.this.f10591i);
            if (i8 == 1 || i8 == 2 || i8 == 4) {
                y.f1.a("Camera2CameraImpl", String.format("Attempt to reopen camera[%s] after error[%s]", cameraDevice.getId(), l0.Z(i8)));
                c(i8);
                return;
            }
            y.f1.c("Camera2CameraImpl", "Error observed on open (or opening) camera device " + cameraDevice.getId() + ": " + l0.Z(i8) + " closing camera.");
            l0.this.H0(i.CLOSING, r.a.a(i8 == 3 ? 5 : 6));
            l0.this.O(false);
        }

        private void c(int i8) {
            int i9 = 1;
            g1.e.m(l0.this.f10598p != 0, "Can only reopen camera device after error if the camera device is actually in an error state.");
            if (i8 == 1) {
                i9 = 2;
            } else if (i8 != 2) {
                i9 = 3;
            }
            l0.this.H0(i.REOPENING, r.a.a(i9));
            l0.this.O(false);
        }

        boolean a() {
            if (this.f10640d != null) {
                l0 l0Var = l0.this;
                l0Var.T("Cancelling scheduled re-open: " + this.f10639c);
                this.f10639c.b();
                this.f10639c = null;
                this.f10640d.cancel(false);
                this.f10640d = null;
                return true;
            }
            return false;
        }

        void d() {
            this.f10641e.e();
        }

        void e() {
            g1.e.l(this.f10639c == null);
            g1.e.l(this.f10640d == null);
            if (!this.f10641e.a()) {
                y.f1.c("Camera2CameraImpl", "Camera reopening attempted for " + this.f10641e.d() + "ms without success.");
                l0.this.I0(i.PENDING_OPEN, null, false);
                return;
            }
            this.f10639c = new b(this.f10637a);
            l0 l0Var = l0.this;
            l0Var.T("Attempting camera re-open in " + this.f10641e.c() + "ms: " + this.f10639c + " activeResuming = " + l0.this.K);
            this.f10640d = this.f10638b.schedule(this.f10639c, (long) this.f10641e.c(), TimeUnit.MILLISECONDS);
        }

        boolean f() {
            int i8;
            l0 l0Var = l0.this;
            return l0Var.K && ((i8 = l0Var.f10598p) == 1 || i8 == 2);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            l0.this.T("CameraDevice.onClosed()");
            boolean z7 = l0.this.f10597o == null;
            g1.e.m(z7, "Unexpected onClose callback on camera device: " + cameraDevice);
            int ordinal = l0.this.f10591i.ordinal();
            if (ordinal == 1 || ordinal == 4) {
                g1.e.l(l0.this.d0());
                l0.this.R();
            } else if (ordinal != 5 && ordinal != 6) {
                throw new IllegalStateException("Camera closed while in state: " + l0.this.f10591i);
            } else {
                l0 l0Var = l0.this;
                if (l0Var.f10598p == 0) {
                    l0Var.P0(false);
                    return;
                }
                l0Var.T("Camera closed due to error: " + l0.Z(l0.this.f10598p));
                e();
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            l0.this.T("CameraDevice.onDisconnected()");
            onError(cameraDevice, 1);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i8) {
            l0 l0Var = l0.this;
            l0Var.f10597o = cameraDevice;
            l0Var.f10598p = i8;
            l0Var.P.b();
            int ordinal = l0.this.f10591i.ordinal();
            if (ordinal != 1) {
                switch (ordinal) {
                    case 4:
                        break;
                    case 5:
                    case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    case 7:
                    case 8:
                    case 9:
                        y.f1.a("Camera2CameraImpl", String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will attempt recovering from error.", cameraDevice.getId(), l0.Z(i8), l0.this.f10591i.name()));
                        b(cameraDevice, i8);
                        return;
                    default:
                        throw new IllegalStateException("onError() should not be possible from state: " + l0.this.f10591i);
                }
            }
            y.f1.c("Camera2CameraImpl", String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will finish closing camera.", cameraDevice.getId(), l0.Z(i8), l0.this.f10591i.name()));
            l0.this.O(false);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            l0.this.T("CameraDevice.onOpened()");
            l0 l0Var = l0.this;
            l0Var.f10597o = cameraDevice;
            l0Var.f10598p = 0;
            d();
            int ordinal = l0.this.f10591i.ordinal();
            if (ordinal == 1 || ordinal == 4) {
                g1.e.l(l0.this.d0());
                l0.this.f10597o.close();
                l0.this.f10597o = null;
            } else if (ordinal != 5 && ordinal != 6 && ordinal != 7) {
                throw new IllegalStateException("onOpened() should not be possible from state: " + l0.this.f10591i);
            } else {
                l0.this.G0(i.OPENED);
                b0.o0 o0Var = l0.this.f10607y;
                String id = cameraDevice.getId();
                l0 l0Var2 = l0.this;
                if (o0Var.j(id, l0Var2.f10606x.b(l0Var2.f10597o.getId()))) {
                    l0.this.z0();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class k {
        static k a(String str, Class cls, b0.q2 q2Var, b0.g3 g3Var, Size size, b0.u2 u2Var, List list) {
            return new r.d(str, cls, q2Var, g3Var, size, u2Var, list);
        }

        static k b(y.h2 h2Var, boolean z7) {
            return a(l0.b0(h2Var), h2Var.getClass(), z7 ? h2Var.x() : h2Var.v(), h2Var.j(), h2Var.f(), h2Var.e(), l0.Y(h2Var));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract List c();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract b0.q2 d();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract b0.u2 e();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Size f();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract b0.g3 g();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String h();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Class i();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0(Context context, s.o0 o0Var, String str, r0 r0Var, z.a aVar, b0.o0 o0Var2, Executor executor, Handler handler, d3 d3Var, long j8) {
        b0.y1 y1Var = new b0.y1();
        this.f10592j = y1Var;
        this.f10598p = 0;
        this.f10600r = new AtomicInteger(0);
        this.f10602t = new LinkedHashMap();
        this.f10603u = 0;
        this.B = false;
        this.C = false;
        this.D = true;
        this.H = new HashSet();
        this.I = b0.c0.a();
        this.J = new Object();
        this.K = false;
        this.P = new h(this, null);
        this.f10588f = o0Var;
        this.f10606x = aVar;
        this.f10607y = o0Var2;
        ScheduledExecutorService f8 = e0.c.f(handler);
        this.f10590h = f8;
        Executor g8 = e0.c.g(executor);
        this.f10589g = g8;
        this.f10595m = new j(g8, f8, j8);
        this.f10587e = new b0.f3(str);
        y1Var.m(h0.a.CLOSED);
        m2 m2Var = new m2(o0Var2);
        this.f10593k = m2Var;
        b3 b3Var = new b3(g8);
        this.F = b3Var;
        this.L = d3Var;
        try {
            s.b0 c8 = o0Var.c(str);
            this.M = c8;
            t tVar = new t(c8, f8, g8, new g(), r0Var.k());
            this.f10594l = tVar;
            this.f10596n = r0Var;
            r0Var.t(tVar);
            r0Var.w(m2Var.a());
            this.N = t.g.a(c8);
            this.f10599q = u0();
            this.G = new k4.b(g8, f8, handler, b3Var, r0Var.k(), androidx.camera.camera2.internal.compat.quirk.b.c());
            this.f10608z = r0Var.k().a(LegacyCameraOutputConfigNullPointerQuirk.class);
            this.A = r0Var.k().a(LegacyCameraSurfaceCleanupQuirk.class);
            e eVar = new e(str);
            this.f10604v = eVar;
            f fVar = new f();
            this.f10605w = fVar;
            o0Var2.g(this, g8, fVar, eVar);
            o0Var.g(g8, eVar);
            this.O = new j4(context, str, o0Var, new a());
        } catch (s.g e8) {
            throw n2.a(e8);
        }
    }

    private void A0() {
        int ordinal = this.f10591i.ordinal();
        if (ordinal == 2 || ordinal == 3) {
            O0(false);
        } else if (ordinal != 4) {
            T("open() ignored due to being in state: " + this.f10591i);
        } else {
            G0(i.REOPENING);
            if (d0() || this.C || this.f10598p != 0) {
                return;
            }
            g1.e.m(this.f10597o != null, "Camera Device should be open if session close is not complete");
            G0(i.OPENED);
            z0();
        }
    }

    private void D0() {
        if (this.E != null) {
            b0.f3 f3Var = this.f10587e;
            f3Var.w(this.E.f() + this.E.hashCode());
            b0.f3 f3Var2 = this.f10587e;
            f3Var2.x(this.E.f() + this.E.hashCode());
            this.E.c();
            this.E = null;
        }
    }

    private void F0(final String str, final b0.q2 q2Var, final b0.g3 g3Var, final b0.u2 u2Var, final List list) {
        this.f10589g.execute(new Runnable() { // from class: r.h0
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.s0(str, q2Var, g3Var, u2Var, list);
            }
        });
    }

    private Collection K0(Collection collection) {
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(k.b((y.h2) it.next(), this.D));
        }
        return arrayList;
    }

    private void L() {
        f4 f4Var = this.E;
        if (f4Var != null) {
            String a02 = a0(f4Var);
            b0.f3 f3Var = this.f10587e;
            b0.q2 h8 = this.E.h();
            b0.g3 i8 = this.E.i();
            h3.b bVar = h3.b.METERING_REPEATING;
            f3Var.v(a02, h8, i8, null, Collections.singletonList(bVar));
            this.f10587e.u(a02, this.E.h(), this.E.i(), null, Collections.singletonList(bVar));
        }
    }

    private void M() {
        b0.q2 c8 = this.f10587e.g().c();
        b0.s0 j8 = c8.j();
        int size = j8.i().size();
        int size2 = c8.n().size();
        if (c8.n().isEmpty()) {
            return;
        }
        if (j8.i().isEmpty()) {
            if (this.E == null) {
                this.E = new f4(this.f10596n.q(), this.L, new f4.c() { // from class: r.x
                    @Override // r.f4.c
                    public final void a() {
                        l0.this.f0();
                    }
                });
            }
            if (e0()) {
                L();
            } else {
                y.f1.c("Camera2CameraImpl", "Failed to add a repeating surface, CameraControl and ImageCapture may encounter issues due to the absence of repeating surface. Please add a UseCase (Preview or ImageAnalysis) that can provide a repeating surface for CameraControl and ImageCapture to function properly.");
            }
        } else if ((size2 == 1 && size == 1) || size >= 2 || (this.E != null && !e0())) {
            D0();
        } else {
            y.f1.a("Camera2CameraImpl", "No need to remove a previous mMeteringRepeating, SessionConfig Surfaces: " + size2 + ", CaptureConfig Surfaces: " + size);
        }
    }

    private void M0(Collection collection) {
        Size f8;
        boolean isEmpty = this.f10587e.h().isEmpty();
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        Rational rational = null;
        while (it.hasNext()) {
            k kVar = (k) it.next();
            if (!this.f10587e.o(kVar.h())) {
                this.f10587e.v(kVar.h(), kVar.d(), kVar.g(), kVar.e(), kVar.c());
                arrayList.add(kVar.h());
                if (kVar.i() == y.p1.class && (f8 = kVar.f()) != null) {
                    rational = new Rational(f8.getWidth(), f8.getHeight());
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        T("Use cases [" + TextUtils.join(", ", arrayList) + "] now ATTACHED");
        if (isEmpty) {
            this.f10594l.o0(true);
            this.f10594l.X();
        }
        M();
        R0();
        Q0();
        E0(false);
        if (this.f10591i == i.OPENED) {
            z0();
        } else {
            A0();
        }
        if (rational != null) {
            this.f10594l.p0(rational);
        }
    }

    private boolean N(s0.a aVar) {
        String str;
        if (aVar.m().isEmpty()) {
            for (b0.q2 q2Var : this.f10587e.f()) {
                b0.s0 j8 = q2Var.j();
                List<b0.b1> i8 = j8.i();
                if (!i8.isEmpty()) {
                    if (j8.h() != 0) {
                        aVar.u(j8.h());
                    }
                    if (j8.l() != 0) {
                        aVar.x(j8.l());
                    }
                    for (b0.b1 b1Var : i8) {
                        aVar.f(b1Var);
                    }
                }
            }
            if (!aVar.m().isEmpty()) {
                return true;
            }
            str = "Unable to find a repeating surface to attach to CaptureConfig";
        } else {
            str = "The capture config builder already has surface inside.";
        }
        y.f1.l("Camera2CameraImpl", str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public void k0(Collection collection) {
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        boolean z7 = false;
        while (it.hasNext()) {
            k kVar = (k) it.next();
            if (this.f10587e.o(kVar.h())) {
                this.f10587e.t(kVar.h());
                arrayList.add(kVar.h());
                if (kVar.i() == y.p1.class) {
                    z7 = true;
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        T("Use cases [" + TextUtils.join(", ", arrayList) + "] now DETACHED for camera");
        if (z7) {
            this.f10594l.p0(null);
        }
        M();
        if (this.f10587e.i().isEmpty()) {
            this.f10594l.r0(false);
        } else {
            R0();
        }
        if (this.f10587e.h().isEmpty()) {
            this.f10594l.C();
            E0(false);
            this.f10594l.o0(false);
            this.f10599q = u0();
            P();
            return;
        }
        Q0();
        E0(false);
        if (this.f10591i == i.OPENED) {
            z0();
        }
    }

    private void P() {
        T("Closing camera.");
        switch (this.f10591i.ordinal()) {
            case 3:
                g1.e.l(this.f10597o == null);
                G0(i.INITIALIZED);
                return;
            case 4:
            default:
                T("close() ignored due to being in state: " + this.f10591i);
                return;
            case 5:
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
            case 7:
                if (!this.f10595m.a() && !this.P.c()) {
                    r1 = false;
                }
                this.P.a();
                G0(i.CLOSING);
                if (r1) {
                    g1.e.l(d0());
                    R();
                    return;
                }
                return;
            case 8:
            case 9:
                G0(i.CLOSING);
                O(false);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public i4.d Q(CameraDevice cameraDevice) {
        final x2 x2Var = new x2(this.N);
        final SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(640, 480);
        final Surface surface = new Surface(surfaceTexture);
        final b0.r1 r1Var = new b0.r1(surface);
        r1Var.k().d(new Runnable() { // from class: r.y
            @Override // java.lang.Runnable
            public final void run() {
                l0.h0(surface, surfaceTexture);
            }
        }, e0.c.b());
        q2.b bVar = new q2.b();
        bVar.h(r1Var);
        bVar.z(1);
        T("Start configAndClose.");
        return f0.d.a(f0.n.I(x2Var.i(bVar.o(), cameraDevice, this.G.a()))).f(new f0.a() { // from class: r.z
            @Override // f0.a
            public final i4.d apply(Object obj) {
                i4.d i02;
                i02 = l0.i0(x2.this, r1Var, (Void) obj);
                return i02;
            }
        }, this.f10589g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        g1.e.l(this.f10591i == i.RELEASING || this.f10591i == i.CLOSING);
        g1.e.l(this.f10602t.isEmpty());
        if (!this.B) {
            W();
        } else if (this.C) {
            T("Ignored since configAndClose is processing");
        } else if (!this.f10604v.b()) {
            this.B = false;
            W();
            T("Ignore configAndClose and finish the close flow directly since camera is unavailable.");
        } else {
            T("Open camera to configAndClose");
            i4.d x02 = x0();
            this.C = true;
            x02.d(new Runnable() { // from class: r.d0
                @Override // java.lang.Runnable
                public final void run() {
                    l0.this.j0();
                }
            }, this.f10589g);
        }
    }

    private void R0() {
        boolean z7 = false;
        for (b0.g3 g3Var : this.f10587e.i()) {
            z7 |= g3Var.O(false);
        }
        this.f10594l.r0(z7);
    }

    private CameraDevice.StateCallback S() {
        ArrayList arrayList = new ArrayList(this.f10587e.g().c().c());
        arrayList.add(this.F.c());
        arrayList.add(this.f10595m);
        return j2.a(arrayList);
    }

    private void U(String str, Throwable th) {
        y.f1.b("Camera2CameraImpl", String.format("{%s} %s", toString(), str), th);
    }

    private int X() {
        synchronized (this.J) {
            return this.f10606x.a() == 2 ? 1 : 0;
        }
    }

    static List Y(y.h2 h2Var) {
        if (h2Var.g() == null) {
            return null;
        }
        return p0.h.j0(h2Var);
    }

    static String Z(int i8) {
        return i8 != 0 ? i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? i8 != 5 ? "UNKNOWN ERROR" : "ERROR_CAMERA_SERVICE" : "ERROR_CAMERA_DEVICE" : "ERROR_CAMERA_DISABLED" : "ERROR_MAX_CAMERAS_IN_USE" : "ERROR_CAMERA_IN_USE" : "ERROR_NONE";
    }

    static String a0(f4 f4Var) {
        return f4Var.f() + f4Var.hashCode();
    }

    static String b0(y.h2 h2Var) {
        return h2Var.o() + h2Var.hashCode();
    }

    private boolean e0() {
        ArrayList arrayList = new ArrayList();
        int X = X();
        for (f3.b bVar : this.f10587e.j()) {
            if (bVar.c() == null || bVar.c().get(0) != h3.b.METERING_REPEATING) {
                if (bVar.e() == null || bVar.c() == null) {
                    y.f1.l("Camera2CameraImpl", "Invalid stream spec or capture types in " + bVar);
                    return false;
                }
                b0.q2 d8 = bVar.d();
                b0.g3 f8 = bVar.f();
                for (b0.b1 b1Var : d8.n()) {
                    arrayList.add(b0.a.a(this.O.M(X, f8.B(), b1Var.h()), f8.B(), b1Var.h(), bVar.e().b(), bVar.c(), bVar.e().d(), f8.p(null)));
                }
            }
        }
        g1.e.j(this.E);
        HashMap hashMap = new HashMap();
        hashMap.put(this.E.i(), Collections.singletonList(this.E.e()));
        try {
            this.O.A(X, arrayList, hashMap, false, false);
            T("Surface combination with metering repeating supported!");
            return true;
        } catch (IllegalArgumentException e8) {
            U("Surface combination with metering repeating  not supported!", e8);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f0() {
        if (c0()) {
            F0(a0(this.E), this.E.h(), this.E.i(), null, Collections.singletonList(h3.b.METERING_REPEATING));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g0(List list) {
        try {
            M0(list);
        } finally {
            this.f10594l.C();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h0(Surface surface, SurfaceTexture surfaceTexture) {
        surface.release();
        surfaceTexture.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ i4.d i0(x2 x2Var, b0.b1 b1Var, Void r22) {
        x2Var.close();
        b1Var.d();
        return x2Var.a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j0() {
        this.C = false;
        this.B = false;
        T("OpenCameraConfigAndClose is done, state: " + this.f10591i);
        int ordinal = this.f10591i.ordinal();
        if (ordinal == 1 || ordinal == 4) {
            g1.e.l(d0());
            W();
        } else if (ordinal != 6) {
            T("OpenCameraConfigAndClose finished while in state: " + this.f10591i);
        } else if (this.f10598p == 0) {
            P0(false);
        } else {
            T("OpenCameraConfigAndClose in error: " + Z(this.f10598p));
            this.f10595m.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l0(c.a aVar) {
        Boolean valueOf;
        f4 f4Var = this.E;
        if (f4Var == null) {
            valueOf = Boolean.FALSE;
        } else {
            valueOf = Boolean.valueOf(this.f10587e.o(a0(f4Var)));
        }
        aVar.c(valueOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object m0(final c.a aVar) {
        try {
            this.f10589g.execute(new Runnable() { // from class: r.b0
                @Override // java.lang.Runnable
                public final void run() {
                    l0.this.l0(aVar);
                }
            });
            return "isMeteringRepeatingAttached";
        } catch (RejectedExecutionException unused) {
            aVar.f(new RuntimeException("Unable to check if MeteringRepeating is attached. Camera executor shut down."));
            return "isMeteringRepeatingAttached";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n0(String str, b0.q2 q2Var, b0.g3 g3Var, b0.u2 u2Var, List list) {
        T("Use case " + str + " ACTIVE");
        this.f10587e.u(str, q2Var, g3Var, u2Var, list);
        this.f10587e.y(str, q2Var, g3Var, u2Var, list);
        Q0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o0(String str) {
        T("Use case " + str + " INACTIVE");
        this.f10587e.x(str);
        Q0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p0(String str, b0.q2 q2Var, b0.g3 g3Var, b0.u2 u2Var, List list) {
        T("Use case " + str + " UPDATED");
        this.f10587e.y(str, q2Var, g3Var, u2Var, list);
        Q0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object q0(c.a aVar) {
        try {
            ArrayList arrayList = new ArrayList(this.f10587e.g().c().c());
            arrayList.add(this.F.c());
            arrayList.add(new b(aVar));
            this.f10588f.f(this.f10596n.g(), this.f10589g, j2.a(arrayList));
            return "configAndCloseTask";
        } catch (SecurityException | s.g e8) {
            U("Unable to open camera for configAndClose: " + e8.getMessage(), e8);
            aVar.f(e8);
            return "configAndCloseTask";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void r0(q2.d dVar, b0.q2 q2Var) {
        dVar.a(q2Var, q2.g.SESSION_ERROR_SURFACE_NEEDS_RESET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s0(String str, b0.q2 q2Var, b0.g3 g3Var, b0.u2 u2Var, List list) {
        T("Use case " + str + " RESET");
        this.f10587e.y(str, q2Var, g3Var, u2Var, list);
        M();
        E0(false);
        Q0();
        if (this.f10591i == i.OPENED) {
            z0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t0(boolean z7) {
        this.K = z7;
        if (z7 && this.f10591i == i.PENDING_OPEN) {
            O0(false);
        }
    }

    private y2 u0() {
        x2 x2Var;
        synchronized (this.J) {
            x2Var = new x2(this.N, this.f10596n.k());
        }
        return x2Var;
    }

    private void v0(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            y.h2 h2Var = (y.h2) it.next();
            String b02 = b0(h2Var);
            if (!this.H.contains(b02)) {
                this.H.add(b02);
                h2Var.N();
                h2Var.L();
            }
        }
    }

    private void w0(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            y.h2 h2Var = (y.h2) it.next();
            String b02 = b0(h2Var);
            if (this.H.contains(b02)) {
                h2Var.O();
                this.H.remove(b02);
            }
        }
    }

    private i4.d x0() {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.k0
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object q02;
                q02 = l0.this.q0(aVar);
                return q02;
            }
        });
    }

    private void y0(boolean z7) {
        if (!z7) {
            this.f10595m.d();
        }
        this.f10595m.a();
        this.P.a();
        T("Opening camera.");
        G0(i.OPENING);
        try {
            this.f10588f.f(this.f10596n.g(), this.f10589g, S());
        } catch (SecurityException e8) {
            T("Unable to open camera due to " + e8.getMessage());
            G0(i.REOPENING);
            this.f10595m.e();
        } catch (s.g e9) {
            T("Unable to open camera due to " + e9.getMessage());
            if (e9.d() != 10001) {
                this.P.d();
            } else {
                H0(i.INITIALIZED, r.a.b(7, e9));
            }
        }
    }

    void B0(final b0.q2 q2Var) {
        ScheduledExecutorService e8 = e0.c.e();
        final q2.d d8 = q2Var.d();
        if (d8 != null) {
            U("Posting surface closed", new Throwable());
            e8.execute(new Runnable() { // from class: r.f0
                @Override // java.lang.Runnable
                public final void run() {
                    l0.r0(q2.d.this, q2Var);
                }
            });
        }
    }

    i4.d C0(y2 y2Var, boolean z7) {
        y2Var.close();
        i4.d a8 = y2Var.a(z7);
        T("Releasing session in state " + this.f10591i.name());
        this.f10602t.put(y2Var, a8);
        f0.n.j(a8, new c(y2Var), e0.c.b());
        return a8;
    }

    void E0(boolean z7) {
        g1.e.l(this.f10599q != null);
        T("Resetting Capture Session");
        y2 y2Var = this.f10599q;
        b0.q2 e8 = y2Var.e();
        List b8 = y2Var.b();
        y2 u02 = u0();
        this.f10599q = u02;
        u02.c(e8);
        this.f10599q.d(b8);
        if (this.f10591i.ordinal() != 8) {
            T("Skipping Capture Session state check due to current camera state: " + this.f10591i + " and previous session status: " + y2Var.g());
        } else if (this.f10608z && y2Var.g()) {
            T("Close camera before creating new session");
            G0(i.REOPENING_QUIRK);
        }
        if (this.A && y2Var.g()) {
            T("ConfigAndClose is required when close the camera.");
            this.B = true;
        }
        C0(y2Var, z7);
    }

    void G0(i iVar) {
        H0(iVar, null);
    }

    void H0(i iVar, r.a aVar) {
        I0(iVar, aVar, true);
    }

    void I0(i iVar, r.a aVar, boolean z7) {
        h0.a aVar2;
        T("Transitioning camera internal state: " + this.f10591i + " --> " + iVar);
        L0(iVar, aVar);
        this.f10591i = iVar;
        switch (iVar.ordinal()) {
            case 0:
                aVar2 = h0.a.RELEASED;
                break;
            case 1:
                aVar2 = h0.a.RELEASING;
                break;
            case 2:
                aVar2 = h0.a.CLOSED;
                break;
            case 3:
                aVar2 = h0.a.PENDING_OPEN;
                break;
            case 4:
            case 5:
                aVar2 = h0.a.CLOSING;
                break;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
            case 7:
                aVar2 = h0.a.OPENING;
                break;
            case 8:
                aVar2 = h0.a.OPEN;
                break;
            case 9:
                aVar2 = h0.a.CONFIGURED;
                break;
            default:
                throw new IllegalStateException("Unknown state: " + iVar);
        }
        this.f10607y.e(this, aVar2, z7);
        this.f10592j.m(aVar2);
        this.f10593k.c(aVar2, aVar);
    }

    void J0(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b0.s0 s0Var = (b0.s0) it.next();
            s0.a k8 = s0.a.k(s0Var);
            if (s0Var.k() == 5 && s0Var.d() != null) {
                k8.p(s0Var.d());
            }
            if (!s0Var.i().isEmpty() || !s0Var.m() || N(k8)) {
                arrayList.add(k8.h());
            }
        }
        T("Issue capture request");
        this.f10599q.d(arrayList);
    }

    void L0(i iVar, r.a aVar) {
        if (z1.a.h()) {
            z1.a.j("CX:C2State[" + this + "]", iVar.ordinal());
            if (aVar != null) {
                this.f10603u++;
            }
            if (this.f10603u > 0) {
                z1.a.j("CX:C2StateErrorCode[" + this + "]", aVar != null ? aVar.d() : 0);
            }
        }
    }

    void O(boolean z7) {
        boolean z8 = this.f10591i == i.CLOSING || this.f10591i == i.RELEASING || (this.f10591i == i.REOPENING && this.f10598p != 0);
        g1.e.m(z8, "closeCamera should only be called in a CLOSING, RELEASING or REOPENING (with error) state. Current state: " + this.f10591i + " (error: " + Z(this.f10598p) + ")");
        E0(z7);
        this.f10599q.f();
    }

    void O0(boolean z7) {
        T("Attempting to force open the camera.");
        if (this.f10607y.i(this)) {
            y0(z7);
            return;
        }
        T("No cameras available. Waiting for available camera before opening camera.");
        G0(i.PENDING_OPEN);
    }

    void P0(boolean z7) {
        T("Attempting to open the camera.");
        if (this.f10604v.b() && this.f10607y.i(this)) {
            y0(z7);
            return;
        }
        T("No cameras available. Waiting for available camera before opening camera.");
        G0(i.PENDING_OPEN);
    }

    void Q0() {
        q2.h e8 = this.f10587e.e();
        if (!e8.e()) {
            this.f10594l.n0();
            this.f10599q.c(this.f10594l.N());
            return;
        }
        this.f10594l.q0(e8.c().o());
        e8.b(this.f10594l.N());
        this.f10599q.c(e8.c());
    }

    void T(String str) {
        U(str, null);
    }

    b0.q2 V(b0.b1 b1Var) {
        for (b0.q2 q2Var : this.f10587e.h()) {
            if (q2Var.n().contains(b1Var)) {
                return q2Var;
            }
        }
        return null;
    }

    void W() {
        g1.e.l(this.f10591i == i.RELEASING || this.f10591i == i.CLOSING);
        g1.e.l(this.f10602t.isEmpty());
        this.f10597o = null;
        if (this.f10591i == i.CLOSING) {
            G0(i.INITIALIZED);
            return;
        }
        this.f10588f.h(this.f10604v);
        G0(i.RELEASED);
        c.a aVar = this.f10601s;
        if (aVar != null) {
            aVar.c(null);
            this.f10601s = null;
        }
    }

    @Override // b0.h0
    public void c(final boolean z7) {
        this.f10589g.execute(new Runnable() { // from class: r.c0
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.t0(z7);
            }
        });
    }

    boolean c0() {
        try {
            return ((Boolean) androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.a0
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object m02;
                    m02 = l0.this.m0(aVar);
                    return m02;
                }
            }).get()).booleanValue();
        } catch (InterruptedException | ExecutionException e8) {
            throw new RuntimeException("Unable to check if MeteringRepeating is attached.", e8);
        }
    }

    @Override // y.h2.b
    public void d(y.h2 h2Var) {
        g1.e.j(h2Var);
        final String b02 = b0(h2Var);
        this.f10589g.execute(new Runnable() { // from class: r.w
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.o0(b02);
            }
        });
    }

    boolean d0() {
        return this.f10602t.isEmpty();
    }

    @Override // b0.h0
    public void e(Collection collection) {
        ArrayList arrayList = new ArrayList(collection);
        if (arrayList.isEmpty()) {
            return;
        }
        this.f10594l.X();
        v0(new ArrayList(arrayList));
        final ArrayList arrayList2 = new ArrayList(K0(arrayList));
        try {
            this.f10589g.execute(new Runnable() { // from class: r.j0
                @Override // java.lang.Runnable
                public final void run() {
                    l0.this.g0(arrayList2);
                }
            });
        } catch (RejectedExecutionException e8) {
            U("Unable to attach use cases.", e8);
            this.f10594l.C();
        }
    }

    @Override // y.h2.b
    public void f(y.h2 h2Var) {
        g1.e.j(h2Var);
        final String b02 = b0(h2Var);
        final b0.q2 x7 = this.D ? h2Var.x() : h2Var.v();
        final b0.g3 j8 = h2Var.j();
        final b0.u2 e8 = h2Var.e();
        final List Y = Y(h2Var);
        this.f10589g.execute(new Runnable() { // from class: r.g0
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.p0(b02, x7, j8, e8, Y);
            }
        });
    }

    @Override // b0.h0
    public void g(b0.z zVar) {
        if (zVar == null) {
            zVar = b0.c0.a();
        }
        zVar.s(null);
        this.I = zVar;
        synchronized (this.J) {
        }
    }

    @Override // b0.h0
    public void h(Collection collection) {
        ArrayList arrayList = new ArrayList(collection);
        if (arrayList.isEmpty()) {
            return;
        }
        final ArrayList arrayList2 = new ArrayList(K0(arrayList));
        w0(new ArrayList(arrayList));
        this.f10589g.execute(new Runnable() { // from class: r.i0
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.k0(arrayList2);
            }
        });
    }

    @Override // b0.h0
    public void k(boolean z7) {
        this.D = z7;
    }

    @Override // b0.h0
    public b0.g0 l() {
        return this.f10596n;
    }

    @Override // y.h2.b
    public void m(y.h2 h2Var) {
        g1.e.j(h2Var);
        final String b02 = b0(h2Var);
        final b0.q2 x7 = this.D ? h2Var.x() : h2Var.v();
        final b0.g3 j8 = h2Var.j();
        final b0.u2 e8 = h2Var.e();
        final List Y = Y(h2Var);
        this.f10589g.execute(new Runnable() { // from class: r.e0
            @Override // java.lang.Runnable
            public final void run() {
                l0.this.n0(b02, x7, j8, e8, Y);
            }
        });
    }

    @Override // y.h2.b
    public void n(y.h2 h2Var) {
        g1.e.j(h2Var);
        b0.q2 x7 = this.D ? h2Var.x() : h2Var.v();
        F0(b0(h2Var), x7, h2Var.j(), h2Var.e(), Y(h2Var));
    }

    @Override // b0.h0
    public b0.d0 p() {
        return this.f10594l;
    }

    @Override // b0.h0
    public b0.z q() {
        return this.I;
    }

    public String toString() {
        return String.format(Locale.US, "Camera@%x[id=%s]", Integer.valueOf(hashCode()), this.f10596n.g());
    }

    void z0() {
        g1.e.l(this.f10591i == i.OPENED);
        q2.h g8 = this.f10587e.g();
        if (!g8.e()) {
            T("Unable to create capture session due to conflicting configurations");
        } else if (!this.f10607y.j(this.f10597o.getId(), this.f10606x.b(this.f10597o.getId()))) {
            T("Unable to create capture session in camera operating mode = " + this.f10606x.a());
        } else {
            HashMap hashMap = new HashMap();
            h4.m(this.f10587e.h(), this.f10587e.i(), hashMap);
            this.f10599q.h(hashMap);
            y2 y2Var = this.f10599q;
            f0.n.j(y2Var.i(g8.c(), (CameraDevice) g1.e.j(this.f10597o), this.G.a()), new d(y2Var), this.f10589g);
        }
    }
}
