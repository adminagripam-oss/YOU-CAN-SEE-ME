package r;

import android.graphics.Rect;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Rational;
import androidx.concurrent.futures.c;
import b0.d0;
import b0.q2;
import b0.s0;
import b0.u0;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import q.a;
import r.t;
import x.j;
import y.k;
import y.u0;
/* loaded from: classes.dex */
public class t implements b0.d0 {

    /* renamed from: b  reason: collision with root package name */
    final b f10760b;

    /* renamed from: c  reason: collision with root package name */
    final Executor f10761c;

    /* renamed from: d  reason: collision with root package name */
    private final Object f10762d = new Object();

    /* renamed from: e  reason: collision with root package name */
    private final s.b0 f10763e;

    /* renamed from: f  reason: collision with root package name */
    private final d0.d f10764f;

    /* renamed from: g  reason: collision with root package name */
    private final q2.b f10765g;

    /* renamed from: h  reason: collision with root package name */
    private final z3 f10766h;

    /* renamed from: i  reason: collision with root package name */
    private final h5 f10767i;

    /* renamed from: j  reason: collision with root package name */
    private final a5 f10768j;

    /* renamed from: k  reason: collision with root package name */
    private final i3 f10769k;

    /* renamed from: l  reason: collision with root package name */
    j5 f10770l;

    /* renamed from: m  reason: collision with root package name */
    private final x.g f10771m;

    /* renamed from: n  reason: collision with root package name */
    private final y0 f10772n;

    /* renamed from: o  reason: collision with root package name */
    private final e5 f10773o;

    /* renamed from: p  reason: collision with root package name */
    private int f10774p;

    /* renamed from: q  reason: collision with root package name */
    private u0.i f10775q;

    /* renamed from: r  reason: collision with root package name */
    private volatile boolean f10776r;

    /* renamed from: s  reason: collision with root package name */
    private volatile int f10777s;

    /* renamed from: t  reason: collision with root package name */
    private final v.a f10778t;

    /* renamed from: u  reason: collision with root package name */
    private final v.b f10779u;

    /* renamed from: v  reason: collision with root package name */
    private final AtomicLong f10780v;

    /* renamed from: w  reason: collision with root package name */
    private volatile i4.d f10781w;

    /* renamed from: x  reason: collision with root package name */
    private int f10782x;

    /* renamed from: y  reason: collision with root package name */
    private long f10783y;

    /* renamed from: z  reason: collision with root package name */
    private final a f10784z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a extends b0.n {

        /* renamed from: a  reason: collision with root package name */
        Set f10785a = new HashSet();

        /* renamed from: b  reason: collision with root package name */
        Map f10786b = new ArrayMap();

        a() {
        }

        @Override // b0.n
        public void a(final int i8) {
            for (final b0.n nVar : this.f10785a) {
                try {
                    ((Executor) this.f10786b.get(nVar)).execute(new Runnable() { // from class: r.r
                        @Override // java.lang.Runnable
                        public final void run() {
                            b0.n.this.a(i8);
                        }
                    });
                } catch (RejectedExecutionException e8) {
                    y.f1.d("Camera2CameraControlImp", "Executor rejected to invoke onCaptureCancelled.", e8);
                }
            }
        }

        @Override // b0.n
        public void b(final int i8, final b0.x xVar) {
            for (final b0.n nVar : this.f10785a) {
                try {
                    ((Executor) this.f10786b.get(nVar)).execute(new Runnable() { // from class: r.s
                        @Override // java.lang.Runnable
                        public final void run() {
                            b0.n.this.b(i8, xVar);
                        }
                    });
                } catch (RejectedExecutionException e8) {
                    y.f1.d("Camera2CameraControlImp", "Executor rejected to invoke onCaptureCompleted.", e8);
                }
            }
        }

        @Override // b0.n
        public void c(final int i8, final b0.p pVar) {
            for (final b0.n nVar : this.f10785a) {
                try {
                    ((Executor) this.f10786b.get(nVar)).execute(new Runnable() { // from class: r.q
                        @Override // java.lang.Runnable
                        public final void run() {
                            b0.n.this.c(i8, pVar);
                        }
                    });
                } catch (RejectedExecutionException e8) {
                    y.f1.d("Camera2CameraControlImp", "Executor rejected to invoke onCaptureFailed.", e8);
                }
            }
        }

        void h(Executor executor, b0.n nVar) {
            this.f10785a.add(nVar);
            this.f10786b.put(nVar, executor);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends CameraCaptureSession.CaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        final Set f10787a = new HashSet();

        /* renamed from: b  reason: collision with root package name */
        private final Executor f10788b;

        b(Executor executor) {
            this.f10788b = executor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(TotalCaptureResult totalCaptureResult) {
            HashSet hashSet = new HashSet();
            for (c cVar : this.f10787a) {
                if (cVar.a(totalCaptureResult)) {
                    hashSet.add(cVar);
                }
            }
            if (hashSet.isEmpty()) {
                return;
            }
            this.f10787a.removeAll(hashSet);
        }

        void b(c cVar) {
            this.f10787a.add(cVar);
        }

        void d(c cVar) {
            this.f10787a.remove(cVar);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
            this.f10788b.execute(new Runnable() { // from class: r.u
                @Override // java.lang.Runnable
                public final void run() {
                    t.b.this.c(totalCaptureResult);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        boolean a(TotalCaptureResult totalCaptureResult);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(s.b0 b0Var, ScheduledExecutorService scheduledExecutorService, Executor executor, d0.d dVar, b0.l2 l2Var) {
        q2.b bVar = new q2.b();
        this.f10765g = bVar;
        this.f10774p = 0;
        this.f10776r = false;
        this.f10777s = 2;
        this.f10780v = new AtomicLong(0L);
        this.f10781w = f0.n.p(null);
        this.f10782x = 1;
        this.f10783y = 0L;
        a aVar = new a();
        this.f10784z = aVar;
        this.f10763e = b0Var;
        this.f10764f = dVar;
        this.f10761c = executor;
        this.f10773o = new e5(executor);
        b bVar2 = new b(executor);
        this.f10760b = bVar2;
        bVar.z(this.f10782x);
        bVar.j(p2.e(bVar2));
        bVar.j(aVar);
        this.f10769k = new i3(this, b0Var, executor);
        this.f10766h = new z3(this, scheduledExecutorService, executor, l2Var);
        this.f10767i = new h5(this, b0Var, executor);
        this.f10768j = new a5(this, b0Var, executor);
        this.f10770l = new n5(b0Var);
        this.f10778t = new v.a(l2Var);
        this.f10779u = new v.b(l2Var);
        this.f10771m = new x.g(this, executor);
        this.f10772n = new y0(this, b0Var, l2Var, executor, scheduledExecutorService);
    }

    public static int Q(s.b0 b0Var, int i8) {
        int[] iArr = (int[]) b0Var.a(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        return a0(i8, iArr) ? i8 : a0(1, iArr) ? 1 : 0;
    }

    private int S(int i8) {
        int[] iArr = (int[]) this.f10763e.a(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        return a0(i8, iArr) ? i8 : a0(1, iArr) ? 1 : 0;
    }

    private boolean Y() {
        return U() > 0;
    }

    private static boolean a0(int i8, int[] iArr) {
        for (int i9 : iArr) {
            if (i8 == i9) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b0(TotalCaptureResult totalCaptureResult, long j8) {
        Long l8;
        if (totalCaptureResult.getRequest() == null) {
            return false;
        }
        Object tag = totalCaptureResult.getRequest().getTag();
        return (tag instanceof b0.y2) && (l8 = (Long) ((b0.y2) tag).d("CameraControlSessionUpdateId")) != null && l8.longValue() >= j8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d0() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e0(Executor executor, b0.n nVar) {
        this.f10784z.h(executor, nVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f0() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ i4.d g0(int i8, int i9, int i10, Void r42) {
        return f0.n.p(this.f10772n.c(i8, i9, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ i4.d h0(List list, int i8, int i9, int i10, Void r52) {
        return this.f10772n.i(list, i8, i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i0(c.a aVar) {
        f0.n.C(v0(u0()), aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object j0(final c.a aVar) {
        this.f10761c.execute(new Runnable() { // from class: r.n
            @Override // java.lang.Runnable
            public final void run() {
                t.this.i0(aVar);
            }
        });
        return "updateSessionConfigAsync";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean k0(long j8, c.a aVar, TotalCaptureResult totalCaptureResult) {
        if (b0(totalCaptureResult, j8)) {
            aVar.c(null);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object l0(final long j8, final c.a aVar) {
        A(new c() { // from class: r.p
            @Override // r.t.c
            public final boolean a(TotalCaptureResult totalCaptureResult) {
                boolean k02;
                k02 = t.k0(j8, aVar, totalCaptureResult);
                return k02;
            }
        });
        return "waitForSessionUpdateId:" + j8;
    }

    private i4.d v0(final long j8) {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.o
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object l02;
                l02 = t.this.l0(j8, aVar);
                return l02;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(c cVar) {
        this.f10760b.b(cVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B(final Executor executor, final b0.n nVar) {
        this.f10761c.execute(new Runnable() { // from class: r.k
            @Override // java.lang.Runnable
            public final void run() {
                t.this.e0(executor, nVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void C() {
        synchronized (this.f10762d) {
            int i8 = this.f10774p;
            if (i8 == 0) {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
            this.f10774p = i8 - 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D(boolean z7) {
        this.f10776r = z7;
        if (!z7) {
            s0.a aVar = new s0.a();
            aVar.v(this.f10782x);
            aVar.w(true);
            a.C0140a c0140a = new a.C0140a();
            c0140a.f(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(P(1)));
            c0140a.f(CaptureRequest.FLASH_MODE, 0);
            aVar.e(c0140a.a());
            s0(Collections.singletonList(aVar.h()));
        }
        u0();
    }

    public x.g E() {
        return this.f10771m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Rect F() {
        return this.f10767i.e();
    }

    public i3 G() {
        return this.f10769k;
    }

    public int H() {
        return this.f10777s;
    }

    public z3 I() {
        return this.f10766h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int J() {
        Integer num = (Integer) this.f10763e.a(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int K() {
        Integer num = (Integer) this.f10763e.a(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int L() {
        Integer num = (Integer) this.f10763e.a(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public u0.i M() {
        return this.f10775q;
    }

    public b0.q2 N() {
        this.f10765g.z(this.f10782x);
        this.f10765g.v(O());
        this.f10765g.n("CameraControlSessionUpdateId", Long.valueOf(this.f10783y));
        return this.f10765g.o();
    }

    b0.u0 O() {
        a.C0140a c0140a = new a.C0140a();
        CaptureRequest.Key key = CaptureRequest.CONTROL_MODE;
        u0.c cVar = u0.c.REQUIRED;
        c0140a.g(key, 1, cVar);
        this.f10766h.p(c0140a);
        this.f10778t.a(c0140a);
        this.f10767i.c(c0140a);
        int i8 = this.f10766h.J() ? 5 : 1;
        if (this.f10776r) {
            c0140a.g(CaptureRequest.FLASH_MODE, 2, cVar);
        } else {
            int i9 = this.f10777s;
            if (i9 == 0) {
                i8 = this.f10779u.a(2);
            } else if (i9 == 1) {
                i8 = 3;
            } else if (i9 == 2) {
                i8 = 1;
            }
        }
        c0140a.g(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(P(i8)), cVar);
        c0140a.g(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(S(1)), cVar);
        this.f10769k.k(c0140a);
        this.f10771m.i(c0140a);
        return c0140a.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int P(int i8) {
        return Q(this.f10763e, i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int R(int i8) {
        int[] iArr = (int[]) this.f10763e.a(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (a0(i8, iArr)) {
            return i8;
        }
        if (a0(4, iArr)) {
            return 4;
        }
        return a0(1, iArr) ? 1 : 0;
    }

    public a5 T() {
        return this.f10768j;
    }

    int U() {
        int i8;
        synchronized (this.f10762d) {
            i8 = this.f10774p;
        }
        return i8;
    }

    public h5 V() {
        return this.f10767i;
    }

    public j5 W() {
        return this.f10770l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void X() {
        synchronized (this.f10762d) {
            this.f10774p++;
        }
    }

    public boolean Z() {
        int e8 = this.f10773o.e();
        y.f1.a("Camera2CameraControlImp", "isInVideoUsage: mVideoUsageControl value = " + e8);
        return e8 > 0;
    }

    @Override // b0.d0
    public void b(q2.b bVar) {
        this.f10770l.b(bVar);
    }

    @Override // b0.d0
    public void c(u0.i iVar) {
        this.f10775q = iVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c0() {
        return this.f10776r;
    }

    @Override // b0.d0
    public i4.d d(final int i8, final int i9) {
        if (Y()) {
            final int H = H();
            return f0.d.a(f0.n.B(this.f10781w)).f(new f0.a() { // from class: r.i
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d g02;
                    g02 = t.this.g0(i8, H, i9, (Void) obj);
                    return g02;
                }
            }, this.f10761c);
        }
        y.f1.l("Camera2CameraControlImp", "Camera is not active.");
        return f0.n.n(new k.a("Camera is not active."));
    }

    @Override // b0.d0
    public b0.u0 e() {
        return this.f10771m.o();
    }

    @Override // b0.d0
    public void f() {
        this.f10773o.c();
    }

    @Override // y.k
    public i4.d g(int i8) {
        return !Y() ? f0.n.n(new k.a("Camera is not active.")) : this.f10769k.l(i8);
    }

    @Override // b0.d0
    public i4.d h(final List list, final int i8, final int i9) {
        if (Y()) {
            final int H = H();
            return f0.d.a(f0.n.B(this.f10781w)).f(new f0.a() { // from class: r.l
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d h02;
                    h02 = t.this.h0(list, i8, H, i9, (Void) obj);
                    return h02;
                }
            }, this.f10761c);
        }
        y.f1.l("Camera2CameraControlImp", "Camera is not active.");
        return f0.n.n(new k.a("Camera is not active."));
    }

    @Override // y.k
    public i4.d i() {
        return !Y() ? f0.n.n(new k.a("Camera is not active.")) : f0.n.B(this.f10766h.r());
    }

    @Override // b0.d0
    public void j() {
        this.f10771m.j().d(new Runnable() { // from class: r.j
            @Override // java.lang.Runnable
            public final void run() {
                t.f0();
            }
        }, e0.c.b());
    }

    @Override // y.k
    public i4.d k(float f8) {
        return !Y() ? f0.n.n(new k.a("Camera is not active.")) : f0.n.B(this.f10767i.m(f8));
    }

    @Override // b0.d0
    public void l(b0.u0 u0Var) {
        this.f10771m.g(j.a.f(u0Var).b()).d(new Runnable() { // from class: r.m
            @Override // java.lang.Runnable
            public final void run() {
                t.d0();
            }
        }, e0.c.b());
    }

    @Override // b0.d0
    public Rect m() {
        Rect rect = (Rect) this.f10763e.a(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        return ("robolectric".equals(Build.FINGERPRINT) && rect == null) ? new Rect(0, 0, 4000, 3000) : (Rect) g1.e.j(rect);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m0(c cVar) {
        this.f10760b.d(cVar);
    }

    @Override // b0.d0
    public void n(int i8) {
        if (!Y()) {
            y.f1.l("Camera2CameraControlImp", "Camera is not active.");
            return;
        }
        this.f10777s = i8;
        y.f1.a("Camera2CameraControlImp", "setFlashMode: mFlashMode = " + this.f10777s);
        j5 j5Var = this.f10770l;
        boolean z7 = true;
        if (this.f10777s != 1 && this.f10777s != 0) {
            z7 = false;
        }
        j5Var.e(z7);
        this.f10781w = t0();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n0() {
        q0(1);
    }

    @Override // y.k
    public i4.d o(y.d0 d0Var) {
        return !Y() ? f0.n.n(new k.a("Camera is not active.")) : f0.n.B(this.f10766h.f0(d0Var));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o0(boolean z7) {
        y.f1.a("Camera2CameraControlImp", "setActive: isActive = " + z7);
        this.f10766h.b0(z7);
        this.f10767i.l(z7);
        this.f10768j.j(z7);
        this.f10769k.j(z7);
        this.f10771m.u(z7);
        if (z7) {
            return;
        }
        this.f10775q = null;
        this.f10773o.h();
    }

    @Override // b0.d0
    public void p() {
        this.f10773o.f();
    }

    public void p0(Rational rational) {
        this.f10766h.c0(rational);
    }

    @Override // y.k
    public i4.d q(boolean z7) {
        return !Y() ? f0.n.n(new k.a("Camera is not active.")) : f0.n.B(this.f10768j.d(z7));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q0(int i8) {
        this.f10782x = i8;
        this.f10766h.d0(i8);
        this.f10772n.h(this.f10782x);
    }

    public void r0(boolean z7) {
        this.f10770l.f(z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s0(List list) {
        this.f10764f.b(list);
    }

    public i4.d t0() {
        return f0.n.B(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.h
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object j02;
                j02 = t.this.j0(aVar);
                return j02;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long u0() {
        this.f10783y = this.f10780v.getAndIncrement();
        this.f10764f.a();
        return this.f10783y;
    }
}
