package r;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Log;
import androidx.concurrent.futures.c;
import b0.s0;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import q.a;
import r.t;
import r.y0;
import y.u0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class y0 {

    /* renamed from: a  reason: collision with root package name */
    private final t f10878a;

    /* renamed from: b  reason: collision with root package name */
    private final v.b0 f10879b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f10880c;

    /* renamed from: d  reason: collision with root package name */
    private final b0.l2 f10881d;

    /* renamed from: e  reason: collision with root package name */
    private final Executor f10882e;

    /* renamed from: f  reason: collision with root package name */
    private final ScheduledExecutorService f10883f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f10884g;

    /* renamed from: h  reason: collision with root package name */
    private int f10885h = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements e {

        /* renamed from: a  reason: collision with root package name */
        private final t f10886a;

        /* renamed from: b  reason: collision with root package name */
        private final v.n f10887b;

        /* renamed from: c  reason: collision with root package name */
        private final int f10888c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f10889d = false;

        a(t tVar, int i8, v.n nVar) {
            this.f10886a = tVar;
            this.f10888c = i8;
            this.f10887b = nVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object f(c.a aVar) {
            this.f10886a.I().Y(aVar);
            this.f10887b.b();
            return "AePreCapture";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Boolean g(Void r02) {
            return Boolean.TRUE;
        }

        @Override // r.y0.e
        public boolean a() {
            return this.f10888c == 0;
        }

        @Override // r.y0.e
        public i4.d b(TotalCaptureResult totalCaptureResult) {
            if (y0.e(this.f10888c, totalCaptureResult)) {
                y.f1.a("Camera2CapturePipeline", "Trigger AE");
                this.f10889d = true;
                return f0.d.a(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.w0
                    @Override // androidx.concurrent.futures.c.InterfaceC0018c
                    public final Object a(c.a aVar) {
                        Object f8;
                        f8 = y0.a.this.f(aVar);
                        return f8;
                    }
                })).e(new m.a() { // from class: r.x0
                    @Override // m.a
                    public final Object apply(Object obj) {
                        Boolean g8;
                        g8 = y0.a.g((Void) obj);
                        return g8;
                    }
                }, e0.c.b());
            }
            return f0.n.p(Boolean.FALSE);
        }

        @Override // r.y0.e
        public void c() {
            if (this.f10889d) {
                y.f1.a("Camera2CapturePipeline", "cancel TriggerAePreCapture");
                this.f10886a.I().q(false, true);
                this.f10887b.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b implements e {

        /* renamed from: a  reason: collision with root package name */
        private final t f10890a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f10891b = false;

        b(t tVar) {
            this.f10890a = tVar;
        }

        @Override // r.y0.e
        public boolean a() {
            return true;
        }

        @Override // r.y0.e
        public i4.d b(TotalCaptureResult totalCaptureResult) {
            Integer num;
            i4.d p8 = f0.n.p(Boolean.TRUE);
            if (totalCaptureResult == null || (num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE)) == null) {
                return p8;
            }
            int intValue = num.intValue();
            if (intValue == 1 || intValue == 2) {
                y.f1.a("Camera2CapturePipeline", "TriggerAf? AF mode auto");
                Integer num2 = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num2 != null && num2.intValue() == 0) {
                    y.f1.a("Camera2CapturePipeline", "Trigger AF");
                    this.f10891b = true;
                    this.f10890a.I().k0(null, false);
                }
            }
            return p8;
        }

        @Override // r.y0.e
        public void c() {
            if (this.f10891b) {
                y.f1.a("Camera2CapturePipeline", "cancel TriggerAF");
                this.f10890a.I().q(true, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c implements a0.k {

        /* renamed from: a  reason: collision with root package name */
        private final Executor f10892a;

        /* renamed from: b  reason: collision with root package name */
        private final d f10893b;

        /* renamed from: c  reason: collision with root package name */
        private int f10894c;

        c(d dVar, Executor executor, int i8) {
            this.f10893b = dVar;
            this.f10892a = executor;
            this.f10894c = i8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object e(c.a aVar) {
            this.f10893b.j();
            aVar.c(null);
            return "invokePostCaptureFuture";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Void f(TotalCaptureResult totalCaptureResult) {
            return null;
        }

        @Override // a0.k
        public i4.d a() {
            return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.z0
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object e8;
                    e8 = y0.c.this.e(aVar);
                    return e8;
                }
            });
        }

        @Override // a0.k
        public i4.d b() {
            y.f1.a("Camera2CapturePipeline", "invokePreCapture");
            return f0.d.a(this.f10893b.k(this.f10894c)).e(new m.a() { // from class: r.a1
                @Override // m.a
                public final Object apply(Object obj) {
                    Void f8;
                    f8 = y0.c.f((TotalCaptureResult) obj);
                    return f8;
                }
            }, this.f10892a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: j  reason: collision with root package name */
        private static final long f10895j;

        /* renamed from: k  reason: collision with root package name */
        private static final long f10896k;

        /* renamed from: a  reason: collision with root package name */
        private final int f10897a;

        /* renamed from: b  reason: collision with root package name */
        private final Executor f10898b;

        /* renamed from: c  reason: collision with root package name */
        private final ScheduledExecutorService f10899c;

        /* renamed from: d  reason: collision with root package name */
        private final t f10900d;

        /* renamed from: e  reason: collision with root package name */
        private final v.n f10901e;

        /* renamed from: f  reason: collision with root package name */
        private final boolean f10902f;

        /* renamed from: g  reason: collision with root package name */
        private long f10903g = f10895j;

        /* renamed from: h  reason: collision with root package name */
        final List f10904h = new ArrayList();

        /* renamed from: i  reason: collision with root package name */
        private final e f10905i = new a();

        /* loaded from: classes.dex */
        class a implements e {
            a() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static /* synthetic */ Boolean e(List list) {
                return Boolean.valueOf(list.contains(Boolean.TRUE));
            }

            @Override // r.y0.e
            public boolean a() {
                for (e eVar : d.this.f10904h) {
                    if (eVar.a()) {
                        return true;
                    }
                }
                return false;
            }

            @Override // r.y0.e
            public i4.d b(TotalCaptureResult totalCaptureResult) {
                ArrayList arrayList = new ArrayList();
                for (e eVar : d.this.f10904h) {
                    arrayList.add(eVar.b(totalCaptureResult));
                }
                return f0.n.G(f0.n.k(arrayList), new m.a() { // from class: r.h1
                    @Override // m.a
                    public final Object apply(Object obj) {
                        Boolean e8;
                        e8 = y0.d.a.e((List) obj);
                        return e8;
                    }
                }, e0.c.b());
            }

            @Override // r.y0.e
            public void c() {
                for (e eVar : d.this.f10904h) {
                    eVar.c();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b extends b0.n {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ c.a f10907a;

            b(c.a aVar) {
                this.f10907a = aVar;
            }

            @Override // b0.n
            public void a(int i8) {
                this.f10907a.f(new y.v0(3, "Capture request is cancelled because camera is closed", null));
            }

            @Override // b0.n
            public void b(int i8, b0.x xVar) {
                this.f10907a.c(null);
            }

            @Override // b0.n
            public void c(int i8, b0.p pVar) {
                this.f10907a.f(new y.v0(2, "Capture request failed with reason " + pVar.a(), null));
            }
        }

        static {
            TimeUnit timeUnit = TimeUnit.SECONDS;
            f10895j = timeUnit.toNanos(1L);
            f10896k = timeUnit.toNanos(5L);
        }

        d(int i8, Executor executor, ScheduledExecutorService scheduledExecutorService, t tVar, boolean z7, v.n nVar) {
            this.f10897a = i8;
            this.f10898b = executor;
            this.f10899c = scheduledExecutorService;
            this.f10900d = tVar;
            this.f10902f = z7;
            this.f10901e = nVar;
        }

        private void g(s0.a aVar) {
            a.C0140a c0140a = new a.C0140a();
            c0140a.f(CaptureRequest.CONTROL_AE_MODE, 3);
            aVar.e(c0140a.a());
        }

        private void h(s0.a aVar, b0.s0 s0Var) {
            int i8 = (this.f10897a != 3 || this.f10902f) ? (s0Var.k() == -1 || s0Var.k() == 5) ? 2 : -1 : 4;
            if (i8 != -1) {
                aVar.v(i8);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d l(List list, int i8, TotalCaptureResult totalCaptureResult) {
            return r(list, i8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d m(int i8, TotalCaptureResult totalCaptureResult) {
            if (y0.e(i8, totalCaptureResult)) {
                q(f10896k);
            }
            return this.f10905i.b(totalCaptureResult);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d o(Boolean bool) {
            return Boolean.TRUE.equals(bool) ? y0.j(this.f10903g, this.f10899c, this.f10900d, new f.a() { // from class: r.g1
                @Override // r.y0.f.a
                public final boolean a(TotalCaptureResult totalCaptureResult) {
                    boolean d8;
                    d8 = y0.d(totalCaptureResult, false);
                    return d8;
                }
            }) : f0.n.p(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object p(s0.a aVar, c.a aVar2) {
            aVar.c(new b(aVar2));
            return "submitStillCapture";
        }

        private void q(long j8) {
            this.f10903g = j8;
        }

        void f(e eVar) {
            this.f10904h.add(eVar);
        }

        i4.d i(final List list, final int i8) {
            f0.d f8 = f0.d.a(k(i8)).f(new f0.a() { // from class: r.b1
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d l8;
                    l8 = y0.d.this.l(list, i8, (TotalCaptureResult) obj);
                    return l8;
                }
            }, this.f10898b);
            f8.d(new Runnable() { // from class: r.c1
                @Override // java.lang.Runnable
                public final void run() {
                    y0.d.this.j();
                }
            }, this.f10898b);
            return f8;
        }

        public void j() {
            this.f10905i.c();
        }

        public i4.d k(final int i8) {
            i4.d p8 = f0.n.p(null);
            if (this.f10904h.isEmpty()) {
                return p8;
            }
            return f0.d.a(this.f10905i.a() ? y0.k(this.f10900d, null) : f0.n.p(null)).f(new f0.a() { // from class: r.e1
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d m8;
                    m8 = y0.d.this.m(i8, (TotalCaptureResult) obj);
                    return m8;
                }
            }, this.f10898b).f(new f0.a() { // from class: r.f1
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d o8;
                    o8 = y0.d.this.o((Boolean) obj);
                    return o8;
                }
            }, this.f10898b);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x006a  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0075  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0078 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        i4.d r(java.util.List r7, int r8) {
            /*
                r6 = this;
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Iterator r7 = r7.iterator()
            Le:
                boolean r2 = r7.hasNext()
                if (r2 == 0) goto L8c
                java.lang.Object r2 = r7.next()
                b0.s0 r2 = (b0.s0) r2
                b0.s0$a r3 = b0.s0.a.k(r2)
                int r4 = r2.k()
                r5 = 5
                if (r4 != r5) goto L63
                r.t r4 = r6.f10900d
                r.j5 r4 = r4.W()
                boolean r4 = r4.c()
                if (r4 != 0) goto L63
                r.t r4 = r6.f10900d
                r.j5 r4 = r4.W()
                boolean r4 = r4.a()
                if (r4 != 0) goto L63
                r.t r4 = r6.f10900d
                r.j5 r4 = r4.W()
                androidx.camera.core.n r4 = r4.g()
                if (r4 == 0) goto L57
                r.t r5 = r6.f10900d
                r.j5 r5 = r5.W()
                boolean r5 = r5.d(r4)
                if (r5 == 0) goto L57
                r5 = 1
                goto L58
            L57:
                r5 = 0
            L58:
                if (r5 == 0) goto L63
                y.w0 r4 = r4.p()
                b0.x r4 = b0.y.a(r4)
                goto L64
            L63:
                r4 = 0
            L64:
                if (r4 == 0) goto L6a
                r3.p(r4)
                goto L6d
            L6a:
                r6.h(r3, r2)
            L6d:
                v.n r2 = r6.f10901e
                boolean r2 = r2.c(r8)
                if (r2 == 0) goto L78
                r6.g(r3)
            L78:
                r.d1 r2 = new r.d1
                r2.<init>()
                i4.d r2 = androidx.concurrent.futures.c.a(r2)
                r0.add(r2)
                b0.s0 r2 = r3.h()
                r1.add(r2)
                goto Le
            L8c:
                r.t r7 = r6.f10900d
                r7.s0(r1)
                i4.d r7 = f0.n.k(r0)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: r.y0.d.r(java.util.List, int):i4.d");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface e {
        boolean a();

        i4.d b(TotalCaptureResult totalCaptureResult);

        void c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class f implements t.c {

        /* renamed from: a  reason: collision with root package name */
        private c.a f10909a;

        /* renamed from: b  reason: collision with root package name */
        private final i4.d f10910b = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.i1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object d8;
                d8 = y0.f.this.d(aVar);
                return d8;
            }
        });

        /* renamed from: c  reason: collision with root package name */
        private final a f10911c;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public interface a {
            boolean a(TotalCaptureResult totalCaptureResult);
        }

        f(a aVar) {
            this.f10911c = aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object d(c.a aVar) {
            this.f10909a = aVar;
            return "waitFor3AResult";
        }

        @Override // r.t.c
        public boolean a(TotalCaptureResult totalCaptureResult) {
            a aVar = this.f10911c;
            if (aVar == null || aVar.a(totalCaptureResult)) {
                this.f10909a.c(totalCaptureResult);
                return true;
            }
            return false;
        }

        public i4.d c() {
            return this.f10910b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class g implements e {

        /* renamed from: f  reason: collision with root package name */
        private static final long f10912f = TimeUnit.SECONDS.toNanos(2);

        /* renamed from: a  reason: collision with root package name */
        private final t f10913a;

        /* renamed from: b  reason: collision with root package name */
        private final Executor f10914b;

        /* renamed from: c  reason: collision with root package name */
        private final ScheduledExecutorService f10915c;

        /* renamed from: d  reason: collision with root package name */
        private final u0.i f10916d;

        /* renamed from: e  reason: collision with root package name */
        private final v.a0 f10917e;

        g(t tVar, Executor executor, ScheduledExecutorService scheduledExecutorService, v.a0 a0Var) {
            this.f10913a = tVar;
            this.f10914b = executor;
            this.f10915c = scheduledExecutorService;
            this.f10917e = a0Var;
            u0.i M = tVar.M();
            Objects.requireNonNull(M);
            this.f10916d = M;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d A(i4.d dVar, Object obj) {
            return f0.n.A(TimeUnit.SECONDS.toMillis(3L), this.f10915c, null, true, dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d B(Void r12) {
            return this.f10913a.I().i0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void r(c.a aVar) {
            y.f1.a("Camera2CapturePipeline", "ScreenFlashTask#preCapture: UI change applied");
            aVar.c(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Object s(AtomicReference atomicReference, final c.a aVar) {
            atomicReference.set(new u0.j() { // from class: r.k1
                @Override // y.u0.j
                public final void a() {
                    y0.g.r(c.a.this);
                }
            });
            return "OnScreenFlashUiApplied";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d t(Void r52) {
            return y0.j(f10912f, this.f10915c, this.f10913a, new f.a() { // from class: r.n1
                @Override // r.y0.f.a
                public final boolean a(TotalCaptureResult totalCaptureResult) {
                    boolean d8;
                    d8 = y0.d(totalCaptureResult, false);
                    return d8;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Boolean u(TotalCaptureResult totalCaptureResult) {
            return Boolean.FALSE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void v(AtomicReference atomicReference, c.a aVar) {
            y.f1.a("Camera2CapturePipeline", "ScreenFlashTask#preCapture: invoking applyScreenFlashUi");
            this.f10916d.a(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(3L), (u0.j) atomicReference.get());
            aVar.c(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object w(final AtomicReference atomicReference, final c.a aVar) {
            e0.c.e().execute(new Runnable() { // from class: r.m1
                @Override // java.lang.Runnable
                public final void run() {
                    y0.g.this.v(atomicReference, aVar);
                }
            });
            return "OnScreenFlashStart";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d x(Void r22) {
            return this.f10913a.I().y(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object y(c.a aVar) {
            if (!this.f10917e.a()) {
                aVar.c(null);
                return "EnableTorchInternal";
            }
            y.f1.a("Camera2CapturePipeline", "ScreenFlashTask#preCapture: enable torch");
            this.f10913a.D(true);
            aVar.c(null);
            return "EnableTorchInternal";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d z(Void r12) {
            return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.l1
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object y7;
                    y7 = y0.g.this.y(aVar);
                    return y7;
                }
            });
        }

        @Override // r.y0.e
        public boolean a() {
            return false;
        }

        @Override // r.y0.e
        public i4.d b(TotalCaptureResult totalCaptureResult) {
            y.f1.a("Camera2CapturePipeline", "ScreenFlashTask#preCapture");
            final AtomicReference atomicReference = new AtomicReference();
            final i4.d a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.p1
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object s8;
                    s8 = y0.g.s(atomicReference, aVar);
                    return s8;
                }
            });
            return f0.d.a(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.q1
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object w7;
                    w7 = y0.g.this.w(atomicReference, aVar);
                    return w7;
                }
            })).f(new f0.a() { // from class: r.r1
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d x7;
                    x7 = y0.g.this.x((Void) obj);
                    return x7;
                }
            }, this.f10914b).f(new f0.a() { // from class: r.s1
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d z7;
                    z7 = y0.g.this.z((Void) obj);
                    return z7;
                }
            }, this.f10914b).f(new f0.a() { // from class: r.t1
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d A;
                    A = y0.g.this.A(a8, obj);
                    return A;
                }
            }, this.f10914b).f(new f0.a() { // from class: r.u1
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d B;
                    B = y0.g.this.B((Void) obj);
                    return B;
                }
            }, this.f10914b).f(new f0.a() { // from class: r.v1
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d t8;
                    t8 = y0.g.this.t((Void) obj);
                    return t8;
                }
            }, this.f10914b).e(new m.a() { // from class: r.w1
                @Override // m.a
                public final Object apply(Object obj) {
                    Boolean u7;
                    u7 = y0.g.u((TotalCaptureResult) obj);
                    return u7;
                }
            }, e0.c.b());
        }

        @Override // r.y0.e
        public void c() {
            y.f1.a("Camera2CapturePipeline", "ScreenFlashTask#postCapture");
            if (this.f10917e.a()) {
                this.f10913a.D(false);
            }
            this.f10913a.I().y(false).d(new Runnable() { // from class: r.j1
                @Override // java.lang.Runnable
                public final void run() {
                    Log.d("Camera2CapturePipeline", "enableExternalFlashAeMode disabled");
                }
            }, this.f10914b);
            this.f10913a.I().q(false, true);
            ScheduledExecutorService e8 = e0.c.e();
            final u0.i iVar = this.f10916d;
            Objects.requireNonNull(iVar);
            e8.execute(new Runnable() { // from class: r.o1
                @Override // java.lang.Runnable
                public final void run() {
                    u0.i.this.clear();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class h implements e {

        /* renamed from: g  reason: collision with root package name */
        private static final long f10918g = TimeUnit.SECONDS.toNanos(2);

        /* renamed from: a  reason: collision with root package name */
        private final t f10919a;

        /* renamed from: b  reason: collision with root package name */
        private final int f10920b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f10921c = false;

        /* renamed from: d  reason: collision with root package name */
        private final Executor f10922d;

        /* renamed from: e  reason: collision with root package name */
        private final ScheduledExecutorService f10923e;

        /* renamed from: f  reason: collision with root package name */
        private final boolean f10924f;

        h(t tVar, int i8, Executor executor, ScheduledExecutorService scheduledExecutorService, boolean z7) {
            this.f10919a = tVar;
            this.f10920b = i8;
            this.f10922d = executor;
            this.f10923e = scheduledExecutorService;
            this.f10924f = z7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object i(c.a aVar) {
            this.f10919a.T().g(aVar, true);
            return "TorchOn";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d j(Void r12) {
            return this.f10924f ? this.f10919a.I().i0() : f0.n.p(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ i4.d l(Void r52) {
            return y0.j(f10918g, this.f10923e, this.f10919a, new f.a() { // from class: r.b2
                @Override // r.y0.f.a
                public final boolean a(TotalCaptureResult totalCaptureResult) {
                    boolean d8;
                    d8 = y0.d(totalCaptureResult, true);
                    return d8;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Boolean m(TotalCaptureResult totalCaptureResult) {
            return Boolean.FALSE;
        }

        @Override // r.y0.e
        public boolean a() {
            return this.f10920b == 0;
        }

        @Override // r.y0.e
        public i4.d b(TotalCaptureResult totalCaptureResult) {
            boolean e8 = y0.e(this.f10920b, totalCaptureResult);
            y.f1.a("Camera2CapturePipeline", "TorchTask#preCapture: isFlashRequired = " + e8);
            if (y0.e(this.f10920b, totalCaptureResult)) {
                if (!this.f10919a.c0()) {
                    y.f1.a("Camera2CapturePipeline", "Turn on torch");
                    this.f10921c = true;
                    return f0.d.a(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.x1
                        @Override // androidx.concurrent.futures.c.InterfaceC0018c
                        public final Object a(c.a aVar) {
                            Object i8;
                            i8 = y0.h.this.i(aVar);
                            return i8;
                        }
                    })).f(new f0.a() { // from class: r.y1
                        @Override // f0.a
                        public final i4.d apply(Object obj) {
                            i4.d j8;
                            j8 = y0.h.this.j((Void) obj);
                            return j8;
                        }
                    }, this.f10922d).f(new f0.a() { // from class: r.z1
                        @Override // f0.a
                        public final i4.d apply(Object obj) {
                            i4.d l8;
                            l8 = y0.h.this.l((Void) obj);
                            return l8;
                        }
                    }, this.f10922d).e(new m.a() { // from class: r.a2
                        @Override // m.a
                        public final Object apply(Object obj) {
                            Boolean m8;
                            m8 = y0.h.m((TotalCaptureResult) obj);
                            return m8;
                        }
                    }, e0.c.b());
                }
                y.f1.a("Camera2CapturePipeline", "Torch already on, not turn on");
            }
            return f0.n.p(Boolean.FALSE);
        }

        @Override // r.y0.e
        public void c() {
            if (this.f10921c) {
                this.f10919a.T().g(null, false);
                y.f1.a("Camera2CapturePipeline", "Turning off torch");
                if (this.f10924f) {
                    this.f10919a.I().q(false, true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public y0(t tVar, s.b0 b0Var, b0.l2 l2Var, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        boolean z7 = true;
        this.f10878a = tVar;
        Integer num = (Integer) b0Var.a(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        this.f10884g = (num == null || num.intValue() != 2) ? false : false;
        this.f10882e = executor;
        this.f10883f = scheduledExecutorService;
        this.f10881d = l2Var;
        this.f10879b = new v.b0(l2Var);
        this.f10880c = v.g.a(new u0(b0Var));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(TotalCaptureResult totalCaptureResult, boolean z7) {
        if (totalCaptureResult == null) {
            return false;
        }
        return b0.x0.a(new r.g(totalCaptureResult), z7);
    }

    static boolean e(int i8, TotalCaptureResult totalCaptureResult) {
        y.f1.a("Camera2CapturePipeline", "isFlashRequired: flashMode = " + i8);
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 == 2) {
                    return false;
                }
                if (i8 != 3) {
                    throw new AssertionError(i8);
                }
            }
            return true;
        }
        Integer num = totalCaptureResult != null ? (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE) : null;
        y.f1.a("Camera2CapturePipeline", "isFlashRequired: aeState = " + num);
        return num != null && num.intValue() == 4;
    }

    private boolean f(int i8) {
        return this.f10879b.a() || this.f10885h == 3 || i8 == 1;
    }

    static i4.d j(long j8, ScheduledExecutorService scheduledExecutorService, t tVar, f.a aVar) {
        return f0.n.A(TimeUnit.NANOSECONDS.toMillis(j8), scheduledExecutorService, null, true, k(tVar, aVar));
    }

    static i4.d k(final t tVar, f.a aVar) {
        final f fVar = new f(aVar);
        tVar.A(fVar);
        i4.d c8 = fVar.c();
        c8.d(new Runnable() { // from class: r.v0
            @Override // java.lang.Runnable
            public final void run() {
                t.this.m0(fVar);
            }
        }, tVar.f10761c);
        return c8;
    }

    d b(int i8, int i9, int i10) {
        e aVar;
        v.n nVar = new v.n(this.f10881d);
        d dVar = new d(this.f10885h, this.f10882e, this.f10883f, this.f10878a, this.f10884g, nVar);
        if (i8 == 0) {
            dVar.f(new b(this.f10878a));
        }
        if (i9 != 3) {
            if (this.f10880c) {
                if (f(i10)) {
                    aVar = new h(this.f10878a, i9, this.f10882e, this.f10883f, (this.f10879b.a() || this.f10878a.Z()) ? false : true);
                } else {
                    aVar = new a(this.f10878a, i9, nVar);
                }
            }
            y.f1.a("Camera2CapturePipeline", "createPipeline: captureMode = " + i8 + ", flashMode = " + i9 + ", flashType = " + i10 + ", pipeline tasks = " + dVar.f10904h);
            return dVar;
        }
        aVar = new g(this.f10878a, this.f10882e, this.f10883f, new v.a0(this.f10881d));
        dVar.f(aVar);
        y.f1.a("Camera2CapturePipeline", "createPipeline: captureMode = " + i8 + ", flashMode = " + i9 + ", flashType = " + i10 + ", pipeline tasks = " + dVar.f10904h);
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a0.k c(int i8, int i9, int i10) {
        return new c(b(i8, i9, i10), this.f10882e, i9);
    }

    public void h(int i8) {
        this.f10885h = i8;
    }

    public i4.d i(List list, int i8, int i9, int i10) {
        return f0.n.B(b(i8, i9, i10).i(list, i9));
    }
}
