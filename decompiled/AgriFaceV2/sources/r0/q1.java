package r0;

import android.graphics.Rect;
import android.media.MediaCodec;
import android.os.SystemClock;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
import androidx.camera.video.internal.compat.quirk.SizeCannotEncodeVideoQuirk;
import androidx.concurrent.futures.c;
import b0.d2;
import b0.f2;
import b0.g3;
import b0.h1;
import b0.h3;
import b0.p1;
import b0.q2;
import b0.u2;
import b0.z2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import l0.t;
import l0.t0;
import r0.b2;
import r0.c1;
import r0.q1;
import y.g2;
import y.h2;
/* loaded from: classes.dex */
public final class q1 extends h2 {
    private static final e D = new e();
    private f A;
    private q2.c B;
    private final d2.a C;

    /* renamed from: p  reason: collision with root package name */
    b0.b1 f11220p;

    /* renamed from: q  reason: collision with root package name */
    private l0.l0 f11221q;

    /* renamed from: r  reason: collision with root package name */
    c1 f11222r;

    /* renamed from: s  reason: collision with root package name */
    q2.b f11223s;

    /* renamed from: t  reason: collision with root package name */
    i4.d f11224t;

    /* renamed from: u  reason: collision with root package name */
    private g2 f11225u;

    /* renamed from: v  reason: collision with root package name */
    b2.a f11226v;

    /* renamed from: w  reason: collision with root package name */
    private l0.t0 f11227w;

    /* renamed from: x  reason: collision with root package name */
    private Rect f11228x;

    /* renamed from: y  reason: collision with root package name */
    private int f11229y;

    /* renamed from: z  reason: collision with root package name */
    private boolean f11230z;

    /* loaded from: classes.dex */
    class a implements d2.a {
        a() {
        }

        @Override // b0.d2.a
        public void a(Throwable th) {
            y.f1.m("VideoCapture", "Receive onError from StreamState observer", th);
        }

        @Override // b0.d2.a
        /* renamed from: c */
        public void b(c1 c1Var) {
            List a8;
            List a9;
            if (c1Var == null) {
                throw new IllegalArgumentException("StreamInfo can't be null");
            }
            if (q1.this.f11226v == b2.a.INACTIVE) {
                return;
            }
            y.f1.a("VideoCapture", "Stream info update: old: " + q1.this.f11222r + " new: " + c1Var);
            q1 q1Var = q1.this;
            c1 c1Var2 = q1Var.f11222r;
            q1Var.f11222r = c1Var;
            u2 u2Var = (u2) g1.e.j(q1Var.e());
            if (q1.this.I0(c1Var2.a(), c1Var.a()) || q1.this.e1(c1Var2, c1Var)) {
                q1.this.R0();
            } else if ((c1Var2.a() != -1 && c1Var.a() == -1) || (c1Var2.a() == -1 && c1Var.a() != -1)) {
                q1 q1Var2 = q1.this;
                q1Var2.u0(q1Var2.f11223s, c1Var, u2Var);
                q1 q1Var3 = q1.this;
                a9 = y.h0.a(new Object[]{q1Var3.f11223s.o()});
                q1Var3.Y(a9);
                q1.this.H();
            } else if (c1Var2.c() != c1Var.c()) {
                q1 q1Var4 = q1.this;
                q1Var4.u0(q1Var4.f11223s, c1Var, u2Var);
                q1 q1Var5 = q1.this;
                a8 = y.h0.a(new Object[]{q1Var5.f11223s.o()});
                q1Var5.Y(a8);
                q1.this.J();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends b0.n {

        /* renamed from: a  reason: collision with root package name */
        private boolean f11232a = true;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f11233b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ c.a f11234c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ q2.b f11235d;

        b(AtomicBoolean atomicBoolean, c.a aVar, q2.b bVar) {
            this.f11233b = atomicBoolean;
            this.f11234c = aVar;
            this.f11235d = bVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(q2.b bVar) {
            bVar.s(this);
        }

        @Override // b0.n
        public void b(int i8, b0.x xVar) {
            Object d8;
            super.b(i8, xVar);
            if (this.f11232a) {
                this.f11232a = false;
                y.f1.a("VideoCapture", "cameraCaptureResult timestampNs = " + xVar.c() + ", current system uptimeMs = " + SystemClock.uptimeMillis() + ", current system realtimeMs = " + SystemClock.elapsedRealtime());
            }
            if (this.f11233b.get() || (d8 = xVar.b().d("androidx.camera.video.VideoCapture.streamUpdate")) == null || ((Integer) d8).intValue() != this.f11234c.hashCode() || !this.f11234c.c(null) || this.f11233b.getAndSet(true)) {
                return;
            }
            ScheduledExecutorService e8 = e0.c.e();
            final q2.b bVar = this.f11235d;
            e8.execute(new Runnable() { // from class: r0.r1
                @Override // java.lang.Runnable
                public final void run() {
                    q1.b.this.f(bVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ i4.d f11237a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ boolean f11238b;

        c(i4.d dVar, boolean z7) {
            this.f11237a = dVar;
            this.f11238b = z7;
        }

        @Override // f0.c
        public void b(Throwable th) {
            if (th instanceof CancellationException) {
                return;
            }
            y.f1.d("VideoCapture", "Surface update completed with unexpected exception", th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r32) {
            i4.d dVar = this.f11237a;
            q1 q1Var = q1.this;
            if (dVar != q1Var.f11224t || q1Var.f11226v == b2.a.INACTIVE) {
                return;
            }
            q1Var.W0(this.f11238b ? b2.a.ACTIVE_STREAMING : b2.a.ACTIVE_NON_STREAMING);
        }
    }

    /* loaded from: classes.dex */
    public static final class d implements g3.a, p1.a {

        /* renamed from: a  reason: collision with root package name */
        private final b0.a2 f11240a;

        private d(b0.a2 a2Var) {
            this.f11240a = a2Var;
            if (!a2Var.a(s0.a.J)) {
                throw new IllegalArgumentException("VideoOutput is required");
            }
            Class cls = (Class) a2Var.f(g0.m.G, null);
            if (cls == null || cls.equals(q1.class)) {
                i(h3.b.VIDEO_CAPTURE);
                l(q1.class);
                return;
            }
            throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
        }

        private static b0.a2 f(b2 b2Var) {
            b0.a2 d02 = b0.a2.d0();
            d02.Y(s0.a.J, b2Var);
            return d02;
        }

        static d g(b0.u0 u0Var) {
            return new d(b0.a2.e0(u0Var));
        }

        @Override // y.c0
        public b0.z1 c() {
            return this.f11240a;
        }

        public q1 e() {
            return new q1(d());
        }

        @Override // b0.g3.a
        /* renamed from: h */
        public s0.a d() {
            return new s0.a(f2.b0(this.f11240a));
        }

        public d i(h3.b bVar) {
            c().Y(g3.B, bVar);
            return this;
        }

        public d j(y.a0 a0Var) {
            c().Y(b0.o1.f4300i, a0Var);
            return this;
        }

        public d k(int i8) {
            c().Y(g3.f4204x, Integer.valueOf(i8));
            return this;
        }

        public d l(Class cls) {
            c().Y(g0.m.G, cls);
            if (c().f(g0.m.F, null) == null) {
                m(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public d m(String str) {
            c().Y(g0.m.F, str);
            return this;
        }

        @Override // b0.p1.a
        /* renamed from: n */
        public d b(Size size) {
            throw new UnsupportedOperationException("setTargetResolution is not supported.");
        }

        @Override // b0.p1.a
        /* renamed from: o */
        public d a(int i8) {
            c().Y(b0.p1.f4310k, Integer.valueOf(i8));
            return this;
        }

        d p(m.a aVar) {
            c().Y(s0.a.K, aVar);
            return this;
        }

        public d(b2 b2Var) {
            this(f(b2Var));
        }
    }

    /* loaded from: classes.dex */
    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        private static final b2 f11241a;

        /* renamed from: b  reason: collision with root package name */
        private static final s0.a f11242b;

        /* renamed from: c  reason: collision with root package name */
        private static final m.a f11243c;

        /* renamed from: d  reason: collision with root package name */
        static final Range f11244d;

        /* renamed from: e  reason: collision with root package name */
        static final y.a0 f11245e;

        static {
            b2 b2Var = new b2() { // from class: r0.s1
                @Override // r0.b2
                public final void a(g2 g2Var) {
                    g2Var.F();
                }
            };
            f11241a = b2Var;
            m.a aVar = y0.s1.f13176d;
            f11243c = aVar;
            f11244d = new Range(30, 30);
            y.a0 a0Var = y.a0.f12730d;
            f11245e = a0Var;
            f11242b = new d(b2Var).k(5).p(aVar).j(a0Var).d();
        }

        public s0.a a() {
            return f11242b;
        }
    }

    /* loaded from: classes.dex */
    static class f implements d2.a {

        /* renamed from: a  reason: collision with root package name */
        private b0.d0 f11246a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f11247b = false;

        f(b0.d0 d0Var) {
            this.f11246a = d0Var;
        }

        private void e(boolean z7) {
            if (this.f11247b == z7) {
                return;
            }
            this.f11247b = z7;
            b0.d0 d0Var = this.f11246a;
            if (d0Var == null) {
                y.f1.a("VideoCapture", "SourceStreamRequirementObserver#isSourceStreamRequired: Received new data despite being closed already");
            } else if (z7) {
                d0Var.p();
            } else {
                d0Var.f();
            }
        }

        @Override // b0.d2.a
        public void a(Throwable th) {
            y.f1.m("VideoCapture", "SourceStreamRequirementObserver#onError", th);
        }

        public void c() {
            g1.e.m(d0.q.c(), "SourceStreamRequirementObserver can be closed from main thread only");
            y.f1.a("VideoCapture", "SourceStreamRequirementObserver#close: mIsSourceStreamRequired = " + this.f11247b);
            if (this.f11246a == null) {
                y.f1.a("VideoCapture", "SourceStreamRequirementObserver#close: Already closed!");
                return;
            }
            e(false);
            this.f11246a = null;
        }

        @Override // b0.d2.a
        /* renamed from: d */
        public void b(Boolean bool) {
            g1.e.m(d0.q.c(), "SourceStreamRequirementObserver can be updated from main thread only");
            e(Boolean.TRUE.equals(bool));
        }
    }

    q1(s0.a aVar) {
        super(aVar);
        this.f11222r = c1.f11016a;
        this.f11223s = new q2.b();
        this.f11224t = null;
        this.f11226v = b2.a.INACTIVE;
        this.f11230z = false;
        this.C = new a();
    }

    private static List A0(s0.a aVar, r rVar, y.a0 a0Var, d1 d1Var, List list, Map map) {
        t0.g a8;
        if (list.isEmpty()) {
            return list;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            if (!map.containsValue(size) && (a8 = d1Var.a(size, a0Var)) != null) {
                m.a Z = aVar.Z();
                Range p8 = aVar.p(e.f11244d);
                Objects.requireNonNull(p8);
                y0.q1 B0 = B0(Z, a8, a0Var, rVar, size, p8);
                if (B0 != null && !B0.a(size.getWidth(), size.getHeight())) {
                    it.remove();
                }
            }
        }
        return list;
    }

    private static y0.q1 B0(m.a aVar, t0.g gVar, y.a0 a0Var, r rVar, Size size, Range range) {
        y0.q1 U0;
        int b8;
        if (a0Var.e()) {
            return U0(aVar, gVar, rVar, size, a0Var, range);
        }
        y0.q1 q1Var = null;
        int i8 = Integer.MIN_VALUE;
        for (h1.c cVar : gVar.c()) {
            if (z0.b.f(cVar, a0Var) && (U0 = U0(aVar, gVar, rVar, size, new y.a0(z0.b.h(cVar.g()), z0.b.g(cVar.b())), range)) != null && (b8 = k0.d.b(((Integer) U0.g().getUpper()).intValue(), ((Integer) U0.j().getUpper()).intValue())) > i8) {
                q1Var = U0;
                i8 = b8;
            }
        }
        return q1Var;
    }

    private int C0(b0.h0 h0Var) {
        boolean D2 = D(h0Var);
        int r8 = r(h0Var, D2);
        if (Z0()) {
            g2.h b8 = this.f11222r.b();
            Objects.requireNonNull(b8);
            int b9 = b8.b();
            if (D2 != b8.f()) {
                b9 = -b9;
            }
            return d0.r.u(r8 - b9);
        }
        return r8;
    }

    private r E0() {
        return (r) z0(F0().g(), null);
    }

    private d1 G0(y.p pVar) {
        return F0().e(pVar);
    }

    private boolean H0(b0.h0 h0Var, s0.a aVar, Rect rect, Size size) {
        l();
        return b1(h0Var, aVar) || c1(h0Var) || a1(rect, size) || d1(h0Var) || Z0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int J0(Rect rect, Size size, Size size2) {
        return (Math.abs(size.getWidth() - rect.width()) + Math.abs(size.getHeight() - rect.height())) - (Math.abs(size2.getWidth() - rect.width()) + Math.abs(size2.getHeight() - rect.height()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M0(b0.b1 b1Var) {
        if (b1Var == this.f11220p) {
            w0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N0(q2 q2Var, q2.g gVar) {
        R0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void O0(AtomicBoolean atomicBoolean, q2.b bVar, b0.n nVar) {
        g1.e.m(d0.q.c(), "Surface update cancellation should only occur on main thread.");
        atomicBoolean.set(true);
        bVar.s(nVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object P0(final q2.b bVar, c.a aVar) {
        bVar.n("androidx.camera.video.VideoCapture.streamUpdate", Integer.valueOf(aVar.hashCode()));
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final b bVar2 = new b(atomicBoolean, aVar, bVar);
        aVar.a(new Runnable() { // from class: r0.i1
            @Override // java.lang.Runnable
            public final void run() {
                q1.O0(atomicBoolean, bVar, bVar2);
            }
        }, e0.c.b());
        bVar.j(bVar2);
        return String.format("%s[0x%x]", "androidx.camera.video.VideoCapture.streamUpdate", Integer.valueOf(aVar.hashCode()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q0 */
    public void L0(l0.l0 l0Var, b0.h0 h0Var, s0.a aVar, z2 z2Var) {
        if (h0Var == g()) {
            this.f11225u = l0Var.k(h0Var);
            aVar.a0().b(this.f11225u, z2Var);
            V0();
        }
    }

    private static Range S0(u2 u2Var) {
        Range c8 = u2Var.c();
        return Objects.equals(c8, u2.f4433a) ? e.f11244d : c8;
    }

    private static z2 T0(b0.h0 h0Var, l0.t0 t0Var) {
        return (t0Var == null && h0Var.j()) ? z2.UPTIME : h0Var.l().e();
    }

    private static y0.q1 U0(m.a aVar, t0.g gVar, r rVar, Size size, y.a0 a0Var, Range range) {
        y0.q1 q1Var = (y0.q1) aVar.apply(x0.k.c(x0.k.d(rVar, a0Var, gVar), z2.UPTIME, rVar.d(), size, a0Var, range));
        if (q1Var != null) {
            return a1.e.l(q1Var, gVar != null ? new Size(gVar.k().k(), gVar.k().h()) : null);
        }
        y.f1.l("VideoCapture", "Can't find videoEncoderInfo");
        return null;
    }

    private void V0() {
        b0.h0 g8 = g();
        l0.l0 l0Var = this.f11221q;
        if (g8 == null || l0Var == null) {
            return;
        }
        int C0 = C0(g8);
        this.f11229y = C0;
        l0Var.D(C0, d());
    }

    private void Y0(final q2.b bVar, boolean z7) {
        i4.d dVar = this.f11224t;
        if (dVar != null && dVar.cancel(false)) {
            y.f1.a("VideoCapture", "A newer surface update is requested. Previous surface update cancelled.");
        }
        i4.d a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r0.h1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object P0;
                P0 = q1.this.P0(bVar, aVar);
                return P0;
            }
        });
        this.f11224t = a8;
        f0.n.j(a8, new c(a8, z7), e0.c.e());
    }

    private boolean Z0() {
        return this.f11222r.b() != null;
    }

    private static boolean a1(Rect rect, Size size) {
        return (size.getWidth() == rect.width() && size.getHeight() == rect.height()) ? false : true;
    }

    private static boolean b1(b0.h0 h0Var, s0.a aVar) {
        return h0Var.j() && aVar.b0();
    }

    private static boolean c1(b0.h0 h0Var) {
        return h0Var.j() && (SurfaceProcessingQuirk.b(androidx.camera.video.internal.compat.quirk.a.c()) || SurfaceProcessingQuirk.b(h0Var.l().k()));
    }

    private boolean d1(b0.h0 h0Var) {
        return h0Var.j() && D(h0Var);
    }

    private void f1(b0.g0 g0Var, g3.a aVar) {
        r E0 = E0();
        g1.e.b(E0 != null, "Unable to update target resolution by null MediaSpec.");
        y.a0 D0 = D0();
        d1 G0 = G0(g0Var);
        List d8 = G0.d(D0);
        if (d8.isEmpty()) {
            y.f1.l("VideoCapture", "Can't find any supported quality on the device.");
            return;
        }
        d2 d9 = E0.d();
        y e8 = d9.e();
        List<v> h8 = e8.h(d8);
        y.f1.a("VideoCapture", "Found selectedQualities " + h8 + " by " + e8);
        if (h8.isEmpty()) {
            throw new IllegalArgumentException("Unable to find supported quality by QualitySelector");
        }
        int b8 = d9.b();
        Map j8 = y.j(G0, D0);
        x xVar = new x(g0Var.l(m()), j8);
        ArrayList arrayList = new ArrayList();
        for (v vVar : h8) {
            arrayList.addAll(xVar.g(vVar, b8));
        }
        List A0 = A0((s0.a) aVar.d(), E0, D0, G0, arrayList, j8);
        y.f1.a("VideoCapture", "Set custom ordered resolutions = " + A0);
        aVar.c().Y(b0.p1.f4318s, A0);
    }

    public static q1 g1(b2 b2Var) {
        return new d((b2) g1.e.j(b2Var)).e();
    }

    private static void m0(Set set, int i8, int i9, Size size, y0.q1 q1Var) {
        if (i8 > size.getWidth() || i9 > size.getHeight()) {
            return;
        }
        try {
            set.add(new Size(i8, ((Integer) q1Var.e(i8).clamp(Integer.valueOf(i9))).intValue()));
        } catch (IllegalArgumentException e8) {
            y.f1.m("VideoCapture", "No supportedHeights for width: " + i8, e8);
        }
        try {
            set.add(new Size(((Integer) q1Var.d(i9).clamp(Integer.valueOf(i8))).intValue(), i9));
        } catch (IllegalArgumentException e9) {
            y.f1.m("VideoCapture", "No supportedWidths for height: " + i9, e9);
        }
    }

    private static Rect n0(Rect rect, int i8, boolean z7, y0.q1 q1Var) {
        SizeCannotEncodeVideoQuirk sizeCannotEncodeVideoQuirk = (SizeCannotEncodeVideoQuirk) androidx.camera.video.internal.compat.quirk.a.b(SizeCannotEncodeVideoQuirk.class);
        if (sizeCannotEncodeVideoQuirk != null) {
            if (!z7) {
                i8 = 0;
            }
            return sizeCannotEncodeVideoQuirk.f(rect, i8, q1Var);
        }
        return rect;
    }

    private static Rect o0(final Rect rect, Size size, y0.q1 q1Var) {
        y.f1.a("VideoCapture", String.format("Adjust cropRect %s by width/height alignment %d/%d and supported widths %s / supported heights %s", d0.r.m(rect), Integer.valueOf(q1Var.b()), Integer.valueOf(q1Var.f()), q1Var.g(), q1Var.j()));
        boolean z7 = true;
        if (!(q1Var.g().contains((Range) Integer.valueOf(rect.width())) && q1Var.j().contains((Range) Integer.valueOf(rect.height()))) && q1Var.i() && q1Var.j().contains((Range) Integer.valueOf(rect.width())) && q1Var.g().contains((Range) Integer.valueOf(rect.height()))) {
            q1Var = new y0.l1(q1Var);
        }
        int b8 = q1Var.b();
        int f8 = q1Var.f();
        Range g8 = q1Var.g();
        Range j8 = q1Var.j();
        int s02 = s0(rect.width(), b8, g8);
        int t02 = t0(rect.width(), b8, g8);
        int s03 = s0(rect.height(), f8, j8);
        int t03 = t0(rect.height(), f8, j8);
        HashSet hashSet = new HashSet();
        m0(hashSet, s02, s03, size, q1Var);
        m0(hashSet, s02, t03, size, q1Var);
        m0(hashSet, t02, s03, size, q1Var);
        m0(hashSet, t02, t03, size, q1Var);
        if (hashSet.isEmpty()) {
            y.f1.l("VideoCapture", "Can't find valid cropped size");
            return rect;
        }
        ArrayList arrayList = new ArrayList(hashSet);
        y.f1.a("VideoCapture", "candidatesList = " + arrayList);
        Collections.sort(arrayList, new Comparator() { // from class: r0.n1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int J0;
                J0 = q1.J0(rect, (Size) obj, (Size) obj2);
                return J0;
            }
        });
        y.f1.a("VideoCapture", "sorted candidatesList = " + arrayList);
        Size size2 = (Size) arrayList.get(0);
        int width = size2.getWidth();
        int height = size2.getHeight();
        if (width == rect.width() && height == rect.height()) {
            y.f1.a("VideoCapture", "No need to adjust cropRect because crop size is valid.");
            return rect;
        }
        if (width % 2 != 0 || height % 2 != 0 || width > size.getWidth() || height > size.getHeight()) {
            z7 = false;
        }
        g1.e.l(z7);
        Rect rect2 = new Rect(rect);
        if (width != rect.width()) {
            int max = Math.max(0, rect.centerX() - (width / 2));
            rect2.left = max;
            int i8 = max + width;
            rect2.right = i8;
            if (i8 > size.getWidth()) {
                int width2 = size.getWidth();
                rect2.right = width2;
                rect2.left = width2 - width;
            }
        }
        if (height != rect.height()) {
            int max2 = Math.max(0, rect.centerY() - (height / 2));
            rect2.top = max2;
            int i9 = max2 + height;
            rect2.bottom = i9;
            if (i9 > size.getHeight()) {
                int height2 = size.getHeight();
                rect2.bottom = height2;
                rect2.top = height2 - height;
            }
        }
        y.f1.a("VideoCapture", String.format("Adjust cropRect from %s to %s", d0.r.m(rect), d0.r.m(rect2)));
        return rect2;
    }

    private Rect p0(Rect rect, int i8) {
        return Z0() ? d0.r.p(d0.r.e(((g2.h) g1.e.j(this.f11222r.b())).a(), i8)) : rect;
    }

    private Size q0(Size size, Rect rect, Rect rect2) {
        if (!Z0() || rect2.equals(rect)) {
            return size;
        }
        float height = rect2.height() / rect.height();
        return new Size((int) Math.ceil(size.getWidth() * height), (int) Math.ceil(size.getHeight() * height));
    }

    private static int r0(boolean z7, int i8, int i9, Range range) {
        int i10 = i8 % i9;
        if (i10 != 0) {
            i8 = z7 ? i8 - i10 : i8 + (i9 - i10);
        }
        return ((Integer) range.clamp(Integer.valueOf(i8))).intValue();
    }

    private static int s0(int i8, int i9, Range range) {
        return r0(true, i8, i9, range);
    }

    private static int t0(int i8, int i9, Range range) {
        return r0(false, i8, i9, range);
    }

    private Rect v0(Size size, y0.q1 q1Var) {
        Rect B = B() != null ? B() : new Rect(0, 0, size.getWidth(), size.getHeight());
        return (q1Var == null || q1Var.a(B.width(), B.height())) ? B : o0(B, size, q1Var);
    }

    private void w0() {
        d0.q.a();
        q2.c cVar = this.B;
        if (cVar != null) {
            cVar.b();
            this.B = null;
        }
        b0.b1 b1Var = this.f11220p;
        if (b1Var != null) {
            b1Var.d();
            this.f11220p = null;
        }
        l0.t0 t0Var = this.f11227w;
        if (t0Var != null) {
            t0Var.i();
            this.f11227w = null;
        }
        l0.l0 l0Var = this.f11221q;
        if (l0Var != null) {
            l0Var.i();
            this.f11221q = null;
        }
        this.f11228x = null;
        this.f11225u = null;
        this.f11222r = c1.f11016a;
        this.f11229y = 0;
        this.f11230z = false;
    }

    private l0.t0 x0(b0.h0 h0Var, s0.a aVar, Rect rect, Size size, y.a0 a0Var) {
        if (H0(h0Var, aVar, rect, size)) {
            y.f1.a("VideoCapture", "Surface processing is enabled.");
            b0.h0 g8 = g();
            Objects.requireNonNull(g8);
            l();
            return new l0.t0(g8, t.a.a(a0Var));
        }
        return null;
    }

    private q2.b y0(final s0.a aVar, u2 u2Var) {
        d0.q.a();
        final b0.h0 h0Var = (b0.h0) g1.e.j(g());
        Size e8 = u2Var.e();
        Runnable runnable = new Runnable() { // from class: r0.j1
            @Override // java.lang.Runnable
            public final void run() {
                q1.this.H();
            }
        };
        Range S0 = S0(u2Var);
        r E0 = E0();
        Objects.requireNonNull(E0);
        d1 G0 = G0(h0Var.b());
        y.a0 b8 = u2Var.b();
        y0.q1 U0 = U0(aVar.Z(), G0.a(e8, b8), E0, e8, b8, S0);
        this.f11229y = C0(h0Var);
        Rect v02 = v0(e8, U0);
        Rect p02 = p0(v02, this.f11229y);
        this.f11228x = p02;
        Size q02 = q0(e8, v02, p02);
        if (Z0()) {
            this.f11230z = true;
        }
        Rect rect = this.f11228x;
        Rect n02 = n0(rect, this.f11229y, H0(h0Var, aVar, rect, e8), U0);
        this.f11228x = n02;
        l0.t0 x02 = x0(h0Var, aVar, n02, e8, b8);
        this.f11227w = x02;
        final z2 T0 = T0(h0Var, x02);
        y.f1.a("VideoCapture", "camera timebase = " + h0Var.l().e() + ", processing timebase = " + T0);
        u2 a8 = u2Var.g().e(q02).c(S0).a();
        g1.e.l(this.f11221q == null);
        l0.l0 l0Var = new l0.l0(2, 34, a8, w(), h0Var.j(), this.f11228x, this.f11229y, d(), d1(h0Var));
        this.f11221q = l0Var;
        l0Var.e(runnable);
        if (this.f11227w != null) {
            n0.f j8 = n0.f.j(this.f11221q);
            final l0.l0 l0Var2 = (l0.l0) this.f11227w.m(t0.b.c(this.f11221q, Collections.singletonList(j8))).get(j8);
            Objects.requireNonNull(l0Var2);
            l0Var2.e(new Runnable() { // from class: r0.k1
                @Override // java.lang.Runnable
                public final void run() {
                    q1.this.L0(l0Var2, h0Var, aVar, T0);
                }
            });
            this.f11225u = l0Var2.k(h0Var);
            final b0.b1 o8 = this.f11221q.o();
            this.f11220p = o8;
            o8.k().d(new Runnable() { // from class: r0.l1
                @Override // java.lang.Runnable
                public final void run() {
                    q1.this.M0(o8);
                }
            }, e0.c.e());
        } else {
            g2 k8 = this.f11221q.k(h0Var);
            this.f11225u = k8;
            this.f11220p = k8.l();
        }
        aVar.a0().b(this.f11225u, T0);
        V0();
        this.f11220p.s(MediaCodec.class);
        q2.b q8 = q2.b.q(aVar, u2Var.e());
        q8.u(u2Var.c());
        q8.A(aVar.n());
        q2.c cVar = this.B;
        if (cVar != null) {
            cVar.b();
        }
        q2.c cVar2 = new q2.c(new q2.d() { // from class: r0.m1
            @Override // b0.q2.d
            public final void a(q2 q2Var, q2.g gVar) {
                q1.this.N0(q2Var, gVar);
            }
        });
        this.B = cVar2;
        q8.t(cVar2);
        if (u2Var.d() != null) {
            q8.g(u2Var.d());
        }
        return q8;
    }

    private static Object z0(b0.d2 d2Var, Object obj) {
        i4.d a8 = d2Var.a();
        if (a8.isDone()) {
            try {
                return a8.get();
            } catch (InterruptedException | ExecutionException e8) {
                throw new IllegalStateException(e8);
            }
        }
        return obj;
    }

    @Override // y.h2
    public g3.a A(b0.u0 u0Var) {
        return d.g(u0Var);
    }

    public y.a0 D0() {
        return j().I() ? j().o() : e.f11245e;
    }

    public b2 F0() {
        return ((s0.a) j()).a0();
    }

    boolean I0(int i8, int i9) {
        Set set = c1.f11017b;
        return (set.contains(Integer.valueOf(i8)) || set.contains(Integer.valueOf(i9)) || i8 == i9) ? false : true;
    }

    @Override // y.h2
    protected g3 M(b0.g0 g0Var, g3.a aVar) {
        f1(g0Var, aVar);
        return aVar.d();
    }

    @Override // y.h2
    public void N() {
        List a8;
        super.N();
        y.f1.a("VideoCapture", "VideoCapture#onStateAttached: cameraID = " + i());
        if (e() == null || this.f11225u != null) {
            return;
        }
        u2 u2Var = (u2) g1.e.j(e());
        this.f11222r = (c1) z0(F0().c(), c1.f11016a);
        q2.b y02 = y0((s0.a) j(), u2Var);
        this.f11223s = y02;
        u0(y02, this.f11222r, u2Var);
        a8 = y.h0.a(new Object[]{this.f11223s.o()});
        Y(a8);
        F();
        F0().c().d(e0.c.e(), this.C);
        f fVar = this.A;
        if (fVar != null) {
            fVar.c();
        }
        this.A = new f(h());
        F0().d().d(e0.c.e(), this.A);
        W0(b2.a.ACTIVE_NON_STREAMING);
    }

    @Override // y.h2
    public void O() {
        y.f1.a("VideoCapture", "VideoCapture#onStateDetached");
        g1.e.m(d0.q.c(), "VideoCapture can only be detached on the main thread.");
        if (this.A != null) {
            F0().d().b(this.A);
            this.A.c();
            this.A = null;
        }
        W0(b2.a.INACTIVE);
        F0().c().b(this.C);
        i4.d dVar = this.f11224t;
        if (dVar != null && dVar.cancel(false)) {
            y.f1.a("VideoCapture", "VideoCapture is detached from the camera. Surface update cancelled.");
        }
        w0();
    }

    @Override // y.h2
    protected u2 P(b0.u0 u0Var) {
        List a8;
        this.f11223s.g(u0Var);
        a8 = y.h0.a(new Object[]{this.f11223s.o()});
        Y(a8);
        u2 e8 = e();
        Objects.requireNonNull(e8);
        return e8.g().d(u0Var).a();
    }

    @Override // y.h2
    protected u2 Q(u2 u2Var, u2 u2Var2) {
        y.f1.a("VideoCapture", "onSuggestedStreamSpecUpdated: " + u2Var);
        List A = ((s0.a) j()).A(null);
        if (A != null && !A.contains(u2Var.e())) {
            y.f1.l("VideoCapture", "suggested resolution " + u2Var.e() + " is not in custom ordered resolutions " + A);
        }
        return u2Var;
    }

    void R0() {
        List a8;
        if (g() == null) {
            return;
        }
        w0();
        q2.b y02 = y0((s0.a) j(), (u2) g1.e.j(e()));
        this.f11223s = y02;
        u0(y02, this.f11222r, e());
        a8 = y.h0.a(new Object[]{this.f11223s.o()});
        Y(a8);
        H();
    }

    @Override // y.h2
    public void W(Rect rect) {
        super.W(rect);
        V0();
    }

    void W0(b2.a aVar) {
        if (aVar != this.f11226v) {
            this.f11226v = aVar;
            F0().f(aVar);
        }
    }

    public void X0(int i8) {
        if (V(i8)) {
            V0();
        }
    }

    boolean e1(c1 c1Var, c1 c1Var2) {
        return this.f11230z && c1Var.b() != null && c1Var2.b() == null;
    }

    @Override // y.h2
    public g3 k(boolean z7, h3 h3Var) {
        e eVar = D;
        b0.u0 a8 = h3Var.a(eVar.a().m(), 1);
        if (z7) {
            a8 = b0.u0.u(a8, eVar.a());
        }
        if (a8 == null) {
            return null;
        }
        return A(a8).d();
    }

    public String toString() {
        return "VideoCapture:" + o();
    }

    void u0(q2.b bVar, c1 c1Var, u2 u2Var) {
        b0.b1 b1Var;
        boolean z7 = c1Var.a() == -1;
        boolean z8 = c1Var.c() == c1.a.ACTIVE;
        if (z7 && z8) {
            throw new IllegalStateException("Unexpected stream state, stream is error but active");
        }
        bVar.p();
        y.a0 b8 = u2Var.b();
        if (!z7 && (b1Var = this.f11220p) != null) {
            if (z8) {
                bVar.m(b1Var, b8, null, -1);
            } else {
                bVar.i(b1Var, b8);
            }
        }
        Y0(bVar, z8);
    }

    @Override // y.h2
    public Set y() {
        HashSet hashSet = new HashSet();
        hashSet.add(2);
        return hashSet;
    }
}
