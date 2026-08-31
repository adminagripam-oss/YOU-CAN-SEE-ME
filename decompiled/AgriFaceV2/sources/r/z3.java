package r;

import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.os.Build;
import android.util.Log;
import android.util.Rational;
import androidx.concurrent.futures.c;
import b0.d0;
import b0.s0;
import b0.u0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import q.a;
import r.t;
import y.k;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class z3 {

    /* renamed from: x  reason: collision with root package name */
    private static final MeteringRectangle[] f10935x = new MeteringRectangle[0];

    /* renamed from: a  reason: collision with root package name */
    private final t f10936a;

    /* renamed from: b  reason: collision with root package name */
    final Executor f10937b;

    /* renamed from: c  reason: collision with root package name */
    private final ScheduledExecutorService f10938c;

    /* renamed from: f  reason: collision with root package name */
    private final v.l f10941f;

    /* renamed from: i  reason: collision with root package name */
    private ScheduledFuture f10944i;

    /* renamed from: j  reason: collision with root package name */
    private ScheduledFuture f10945j;

    /* renamed from: q  reason: collision with root package name */
    private MeteringRectangle[] f10952q;

    /* renamed from: r  reason: collision with root package name */
    private MeteringRectangle[] f10953r;

    /* renamed from: s  reason: collision with root package name */
    private MeteringRectangle[] f10954s;

    /* renamed from: t  reason: collision with root package name */
    c.a f10955t;

    /* renamed from: u  reason: collision with root package name */
    c.a f10956u;

    /* renamed from: v  reason: collision with root package name */
    private boolean f10957v;

    /* renamed from: w  reason: collision with root package name */
    private t.c f10958w;

    /* renamed from: d  reason: collision with root package name */
    private volatile boolean f10939d = false;

    /* renamed from: e  reason: collision with root package name */
    private volatile Rational f10940e = null;

    /* renamed from: g  reason: collision with root package name */
    private boolean f10942g = false;

    /* renamed from: h  reason: collision with root package name */
    Integer f10943h = 0;

    /* renamed from: k  reason: collision with root package name */
    long f10946k = 0;

    /* renamed from: l  reason: collision with root package name */
    boolean f10947l = false;

    /* renamed from: m  reason: collision with root package name */
    boolean f10948m = false;

    /* renamed from: n  reason: collision with root package name */
    private int f10949n = 1;

    /* renamed from: o  reason: collision with root package name */
    private t.c f10950o = null;

    /* renamed from: p  reason: collision with root package name */
    private t.c f10951p = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends b0.n {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c.a f10959a;

        a(c.a aVar) {
            this.f10959a = aVar;
        }

        @Override // b0.n
        public void a(int i8) {
            c.a aVar = this.f10959a;
            if (aVar != null) {
                aVar.f(new k.a("Camera is closed"));
            }
        }

        @Override // b0.n
        public void b(int i8, b0.x xVar) {
            c.a aVar = this.f10959a;
            if (aVar != null) {
                aVar.c(xVar);
            }
        }

        @Override // b0.n
        public void c(int i8, b0.p pVar) {
            c.a aVar = this.f10959a;
            if (aVar != null) {
                aVar.f(new d0.c(pVar));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends b0.n {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c.a f10961a;

        b(c.a aVar) {
            this.f10961a = aVar;
        }

        @Override // b0.n
        public void a(int i8) {
            c.a aVar = this.f10961a;
            if (aVar != null) {
                aVar.f(new k.a("Camera is closed"));
            }
        }

        @Override // b0.n
        public void b(int i8, b0.x xVar) {
            if (this.f10961a != null) {
                y.f1.a("FocusMeteringControl", "triggerAePrecapture: triggering capture request completed");
                this.f10961a.c(null);
            }
        }

        @Override // b0.n
        public void c(int i8, b0.p pVar) {
            c.a aVar = this.f10961a;
            if (aVar != null) {
                aVar.f(new d0.c(pVar));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public z3(t tVar, ScheduledExecutorService scheduledExecutorService, Executor executor, b0.l2 l2Var) {
        MeteringRectangle[] meteringRectangleArr = f10935x;
        this.f10952q = meteringRectangleArr;
        this.f10953r = meteringRectangleArr;
        this.f10954s = meteringRectangleArr;
        this.f10955t = null;
        this.f10956u = null;
        this.f10957v = false;
        this.f10958w = null;
        this.f10936a = tVar;
        this.f10937b = executor;
        this.f10938c = scheduledExecutorService;
        this.f10941f = new v.l(l2Var);
    }

    private void A(MeteringRectangle[] meteringRectangleArr, MeteringRectangle[] meteringRectangleArr2, MeteringRectangle[] meteringRectangleArr3, y.d0 d0Var, long j8) {
        final long u02;
        this.f10936a.m0(this.f10950o);
        x();
        u();
        this.f10952q = meteringRectangleArr;
        this.f10953r = meteringRectangleArr2;
        this.f10954s = meteringRectangleArr3;
        if (e0()) {
            this.f10942g = true;
            this.f10947l = false;
            this.f10948m = false;
            u02 = this.f10936a.u0();
            k0(null, true);
        } else {
            this.f10942g = false;
            this.f10947l = true;
            this.f10948m = false;
            u02 = this.f10936a.u0();
        }
        this.f10943h = 0;
        final boolean I = I();
        t.c cVar = new t.c() { // from class: r.u3
            @Override // r.t.c
            public final boolean a(TotalCaptureResult totalCaptureResult) {
                boolean T;
                T = z3.this.T(I, u02, totalCaptureResult);
                return T;
            }
        };
        this.f10950o = cVar;
        this.f10936a.A(cVar);
        final long j9 = this.f10946k + 1;
        this.f10946k = j9;
        Runnable runnable = new Runnable() { // from class: r.v3
            @Override // java.lang.Runnable
            public final void run() {
                z3.this.V(j9);
            }
        };
        ScheduledExecutorService scheduledExecutorService = this.f10938c;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.f10945j = scheduledExecutorService.schedule(runnable, j8, timeUnit);
        if (d0Var.e()) {
            this.f10944i = this.f10938c.schedule(new Runnable() { // from class: r.w3
                @Override // java.lang.Runnable
                public final void run() {
                    z3.this.S(j9);
                }
            }, d0Var.a(), timeUnit);
        }
    }

    private void B(String str) {
        this.f10936a.m0(this.f10950o);
        c.a aVar = this.f10955t;
        if (aVar != null) {
            aVar.f(new k.a(str));
            this.f10955t = null;
        }
    }

    private void C(String str) {
        this.f10936a.m0(this.f10951p);
        c.a aVar = this.f10956u;
        if (aVar != null) {
            aVar.f(new k.a(str));
            this.f10956u = null;
        }
    }

    private Rational E() {
        if (this.f10940e != null) {
            return this.f10940e;
        }
        Rect F = this.f10936a.F();
        return new Rational(F.width(), F.height());
    }

    private static PointF F(y.i1 i1Var, Rational rational, Rational rational2, int i8, v.l lVar) {
        if (i1Var.b() != null) {
            rational2 = i1Var.b();
        }
        PointF a8 = lVar.a(i1Var, i8);
        if (!rational2.equals(rational)) {
            if (rational2.compareTo(rational) > 0) {
                float doubleValue = (float) (rational2.doubleValue() / rational.doubleValue());
                a8.y = (((float) ((doubleValue - 1.0d) / 2.0d)) + a8.y) * (1.0f / doubleValue);
            } else {
                float doubleValue2 = (float) (rational.doubleValue() / rational2.doubleValue());
                a8.x = (((float) ((doubleValue2 - 1.0d) / 2.0d)) + a8.x) * (1.0f / doubleValue2);
            }
        }
        return a8;
    }

    private static MeteringRectangle G(y.i1 i1Var, PointF pointF, Rect rect) {
        int width = (int) (rect.left + (pointF.x * rect.width()));
        int height = (int) (rect.top + (pointF.y * rect.height()));
        int a8 = ((int) (i1Var.a() * rect.width())) / 2;
        int a9 = ((int) (i1Var.a() * rect.height())) / 2;
        Rect rect2 = new Rect(width - a8, height - a9, width + a8, height + a9);
        rect2.left = a0(rect2.left, rect.right, rect.left);
        rect2.right = a0(rect2.right, rect.right, rect.left);
        rect2.top = a0(rect2.top, rect.bottom, rect.top);
        rect2.bottom = a0(rect2.bottom, rect.bottom, rect.top);
        return new MeteringRectangle(rect2, 1000);
    }

    private List H(List list, int i8, Rational rational, Rect rect, int i9) {
        if (list.isEmpty() || i8 == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Rational rational2 = new Rational(rect.width(), rect.height());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            y.i1 i1Var = (y.i1) it.next();
            if (arrayList.size() == i8) {
                break;
            } else if (K(i1Var)) {
                MeteringRectangle G = G(i1Var, F(i1Var, rational2, rational, i9, this.f10941f), rect);
                if (G.getWidth() != 0 && G.getHeight() != 0) {
                    arrayList.add(G);
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private boolean I() {
        return this.f10936a.R(1) == 1;
    }

    private static boolean K(y.i1 i1Var) {
        return i1Var.c() >= 0.0f && i1Var.c() <= 1.0f && i1Var.d() >= 0.0f && i1Var.d() <= 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object M(final c.a aVar) {
        this.f10937b.execute(new Runnable() { // from class: r.s3
            @Override // java.lang.Runnable
            public final void run() {
                z3.this.L(aVar);
            }
        });
        return "cancelFocusAndMetering";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean N(int i8, long j8, TotalCaptureResult totalCaptureResult) {
        if (((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE)).intValue() == i8 && t.b0(totalCaptureResult, j8)) {
            w();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(boolean z7, c.a aVar) {
        this.f10936a.m0(this.f10958w);
        this.f10957v = z7;
        z(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object P(final boolean z7, final c.a aVar) {
        this.f10937b.execute(new Runnable() { // from class: r.m3
            @Override // java.lang.Runnable
            public final void run() {
                z3.this.O(z7, aVar);
            }
        });
        return "enableExternalFlashAeMode";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean Q(long j8, c.a aVar, TotalCaptureResult totalCaptureResult) {
        boolean z7 = ((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_MODE)).intValue() == 5;
        y.f1.a("FocusMeteringControl", "enableExternalFlashAeMode: isAeModeExternalFlash = " + z7);
        if (z7 == this.f10957v && t.b0(totalCaptureResult, j8)) {
            y.f1.a("FocusMeteringControl", "enableExternalFlashAeMode: session updated with isAeModeExternalFlash = " + z7);
            if (aVar != null) {
                aVar.c(null);
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void R(long j8) {
        if (j8 == this.f10946k) {
            t();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S(final long j8) {
        this.f10937b.execute(new Runnable() { // from class: r.x3
            @Override // java.lang.Runnable
            public final void run() {
                z3.this.R(j8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean T(boolean z7, long j8, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
        if (e0()) {
            if (z7 && num != null) {
                if (this.f10943h.intValue() == 3) {
                    if (num.intValue() != 4) {
                        if (num.intValue() == 5) {
                            this.f10948m = false;
                            this.f10947l = true;
                        }
                    }
                }
            }
            this.f10948m = true;
            this.f10947l = true;
        }
        if (this.f10947l && t.b0(totalCaptureResult, j8)) {
            v(this.f10948m);
            return true;
        }
        if (!this.f10943h.equals(num) && num != null) {
            this.f10943h = num;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void U(long j8) {
        if (j8 == this.f10946k) {
            this.f10948m = false;
            v(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void V(final long j8) {
        this.f10937b.execute(new Runnable() { // from class: r.l3
            @Override // java.lang.Runnable
            public final void run() {
                z3.this.U(j8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object X(final y.d0 d0Var, final long j8, final c.a aVar) {
        this.f10937b.execute(new Runnable() { // from class: r.r3
            @Override // java.lang.Runnable
            public final void run() {
                z3.this.W(aVar, d0Var, j8);
            }
        });
        return "startFocusAndMetering";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object Z(final c.a aVar) {
        this.f10937b.execute(new Runnable() { // from class: r.o3
            @Override // java.lang.Runnable
            public final void run() {
                z3.this.Y(aVar);
            }
        });
        return "triggerAePrecapture";
    }

    private static int a0(int i8, int i9, int i10) {
        return Math.min(Math.max(i8, i10), i9);
    }

    private boolean e0() {
        return this.f10952q.length > 0;
    }

    private void u() {
        ScheduledFuture scheduledFuture = this.f10945j;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.f10945j = null;
        }
    }

    private void w() {
        c.a aVar = this.f10956u;
        if (aVar != null) {
            aVar.c(null);
            this.f10956u = null;
        }
    }

    private void x() {
        ScheduledFuture scheduledFuture = this.f10944i;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.f10944i = null;
        }
    }

    private void z(final c.a aVar) {
        if (!this.f10939d) {
            if (aVar != null) {
                aVar.f(new k.a("Camera is not active."));
                return;
            }
            return;
        }
        final long u02 = this.f10936a.u0();
        t.c cVar = new t.c() { // from class: r.p3
            @Override // r.t.c
            public final boolean a(TotalCaptureResult totalCaptureResult) {
                boolean Q;
                Q = z3.this.Q(u02, aVar, totalCaptureResult);
                return Q;
            }
        };
        this.f10958w = cVar;
        this.f10936a.A(cVar);
    }

    int D() {
        return this.f10949n != 3 ? 4 : 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean J() {
        return this.f10957v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b0(boolean z7) {
        if (z7 == this.f10939d) {
            return;
        }
        this.f10939d = z7;
        if (this.f10939d) {
            return;
        }
        t();
    }

    public void c0(Rational rational) {
        this.f10940e = rational;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d0(int i8) {
        this.f10949n = i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d f0(y.d0 d0Var) {
        return g0(d0Var, 5000L);
    }

    i4.d g0(final y.d0 d0Var, final long j8) {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.k3
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object X;
                X = z3.this.X(d0Var, j8, aVar);
                return X;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h0 */
    public void W(c.a aVar, y.d0 d0Var, long j8) {
        if (!this.f10939d) {
            aVar.f(new k.a("Camera is not active."));
            return;
        }
        Rect F = this.f10936a.F();
        Rational E = E();
        List H = H(d0Var.c(), this.f10936a.K(), E, F, 1);
        List H2 = H(d0Var.b(), this.f10936a.J(), E, F, 2);
        List H3 = H(d0Var.d(), this.f10936a.L(), E, F, 4);
        if (H.isEmpty() && H2.isEmpty() && H3.isEmpty()) {
            aVar.f(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints is supported on this camera."));
            return;
        }
        B("Cancelled by another startFocusAndMetering()");
        C("Cancelled by another startFocusAndMetering()");
        x();
        this.f10955t = aVar;
        MeteringRectangle[] meteringRectangleArr = f10935x;
        A((MeteringRectangle[]) H.toArray(meteringRectangleArr), (MeteringRectangle[]) H2.toArray(meteringRectangleArr), (MeteringRectangle[]) H3.toArray(meteringRectangleArr), d0Var, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d i0() {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.n3
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object Z;
                Z = z3.this.Z(aVar);
                return Z;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j0 */
    public void Y(c.a aVar) {
        y.f1.a("FocusMeteringControl", "triggerAePrecapture");
        if (!this.f10939d) {
            if (aVar != null) {
                aVar.f(new k.a("Camera is not active."));
                return;
            }
            return;
        }
        s0.a aVar2 = new s0.a();
        aVar2.v(this.f10949n);
        aVar2.w(true);
        a.C0140a c0140a = new a.C0140a();
        c0140a.f(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
        aVar2.e(c0140a.a());
        aVar2.c(new b(aVar));
        this.f10936a.s0(Collections.singletonList(aVar2.h()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k0(c.a aVar, boolean z7) {
        if (!this.f10939d) {
            if (aVar != null) {
                aVar.f(new k.a("Camera is not active."));
                return;
            }
            return;
        }
        s0.a aVar2 = new s0.a();
        aVar2.v(this.f10949n);
        aVar2.w(true);
        a.C0140a c0140a = new a.C0140a();
        c0140a.f(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        if (z7) {
            c0140a.g(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(this.f10936a.P(1)), u0.c.HIGH_PRIORITY_REQUIRED);
        }
        aVar2.e(c0140a.a());
        aVar2.c(new a(aVar));
        this.f10936a.s0(Collections.singletonList(aVar2.h()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(a.C0140a c0140a) {
        int D = this.f10942g ? 1 : D();
        CaptureRequest.Key key = CaptureRequest.CONTROL_AF_MODE;
        Object valueOf = Integer.valueOf(this.f10936a.R(D));
        u0.c cVar = u0.c.REQUIRED;
        c0140a.g(key, valueOf, cVar);
        MeteringRectangle[] meteringRectangleArr = this.f10952q;
        if (meteringRectangleArr.length != 0) {
            c0140a.g(CaptureRequest.CONTROL_AF_REGIONS, meteringRectangleArr, cVar);
        }
        MeteringRectangle[] meteringRectangleArr2 = this.f10953r;
        if (meteringRectangleArr2.length != 0) {
            c0140a.g(CaptureRequest.CONTROL_AE_REGIONS, meteringRectangleArr2, cVar);
        }
        MeteringRectangle[] meteringRectangleArr3 = this.f10954s;
        if (meteringRectangleArr3.length != 0) {
            c0140a.g(CaptureRequest.CONTROL_AWB_REGIONS, meteringRectangleArr3, cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(boolean z7, boolean z8) {
        if (this.f10939d) {
            s0.a aVar = new s0.a();
            aVar.w(true);
            aVar.v(this.f10949n);
            a.C0140a c0140a = new a.C0140a();
            if (z7) {
                c0140a.f(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            }
            if (z8) {
                c0140a.f(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 2);
            }
            aVar.e(c0140a.a());
            this.f10936a.s0(Collections.singletonList(aVar.h()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d r() {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.q3
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object M;
                M = z3.this.M(aVar);
                return M;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: s */
    public void L(c.a aVar) {
        C("Cancelled by another cancelFocusAndMetering()");
        B("Cancelled by cancelFocusAndMetering()");
        this.f10956u = aVar;
        x();
        u();
        if (e0()) {
            q(true, false);
        }
        MeteringRectangle[] meteringRectangleArr = f10935x;
        this.f10952q = meteringRectangleArr;
        this.f10953r = meteringRectangleArr;
        this.f10954s = meteringRectangleArr;
        this.f10942g = false;
        final long u02 = this.f10936a.u0();
        if (this.f10956u != null) {
            final int R = this.f10936a.R(D());
            t.c cVar = new t.c() { // from class: r.t3
                @Override // r.t.c
                public final boolean a(TotalCaptureResult totalCaptureResult) {
                    boolean N;
                    N = z3.this.N(R, u02, totalCaptureResult);
                    return N;
                }
            };
            this.f10951p = cVar;
            this.f10936a.A(cVar);
        }
    }

    void t() {
        L(null);
    }

    void v(boolean z7) {
        u();
        c.a aVar = this.f10955t;
        if (aVar != null) {
            aVar.c(y.e0.a(z7));
            this.f10955t = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d y(final boolean z7) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 28) {
            Log.d("FocusMeteringControl", "CONTROL_AE_MODE_ON_EXTERNAL_FLASH is not supported in API " + i8);
            return f0.n.p(null);
        } else if (this.f10936a.P(5) != 5) {
            Log.d("FocusMeteringControl", "CONTROL_AE_MODE_ON_EXTERNAL_FLASH is not supported in this device");
            return f0.n.p(null);
        } else {
            Log.d("FocusMeteringControl", "enableExternalFlashAeMode: CONTROL_AE_MODE_ON_EXTERNAL_FLASH supported");
            return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.y3
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object P;
                    P = z3.this.P(z7, aVar);
                    return P;
                }
            });
        }
    }
}
