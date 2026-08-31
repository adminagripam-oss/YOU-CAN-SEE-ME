package y;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.location.Location;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import b0.g3;
import b0.h3;
import b0.p1;
import b0.q1;
import b0.q2;
import b0.s2;
import b0.u0;
import b0.u2;
import java.io.File;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import o0.c;
/* loaded from: classes.dex */
public final class u0 extends h2 {
    public static final c B = new c();
    static final j0.b C = new j0.b();
    private final a0.x A;

    /* renamed from: p  reason: collision with root package name */
    private final q1.a f12923p;

    /* renamed from: q  reason: collision with root package name */
    private final int f12924q;

    /* renamed from: r  reason: collision with root package name */
    private final AtomicReference f12925r;

    /* renamed from: s  reason: collision with root package name */
    private final int f12926s;

    /* renamed from: t  reason: collision with root package name */
    private int f12927t;

    /* renamed from: u  reason: collision with root package name */
    private Rational f12928u;

    /* renamed from: v  reason: collision with root package name */
    private g0.j f12929v;

    /* renamed from: w  reason: collision with root package name */
    q2.b f12930w;

    /* renamed from: x  reason: collision with root package name */
    private a0.y f12931x;

    /* renamed from: y  reason: collision with root package name */
    private a0.y0 f12932y;

    /* renamed from: z  reason: collision with root package name */
    private q2.c f12933z;

    /* loaded from: classes.dex */
    class a implements a0.x {
        a() {
        }

        @Override // a0.x
        public i4.d a(List list) {
            return u0.this.G0(list);
        }

        @Override // a0.x
        public void b() {
            u0.this.z0();
        }

        @Override // a0.x
        public void c() {
            u0.this.K0();
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements g3.a, p1.a {

        /* renamed from: a  reason: collision with root package name */
        private final b0.a2 f12935a;

        public b() {
            this(b0.a2.d0());
        }

        public static b f(b0.u0 u0Var) {
            return new b(b0.a2.e0(u0Var));
        }

        @Override // y.c0
        public b0.z1 c() {
            return this.f12935a;
        }

        public u0 e() {
            b0.z1 c8;
            u0.a aVar;
            int i8;
            Integer num = (Integer) c().f(b0.n1.M, null);
            if (num != null) {
                c().Y(b0.o1.f4299h, num);
            } else {
                if (u0.t0(c())) {
                    c().Y(b0.o1.f4299h, 4101);
                    c8 = c();
                    aVar = b0.o1.f4300i;
                    i8 = a0.f12729c;
                } else {
                    c8 = c();
                    aVar = b0.o1.f4299h;
                    i8 = 256;
                }
                c8.Y(aVar, i8);
            }
            b0.n1 d8 = d();
            b0.p1.j(d8);
            u0 u0Var = new u0(d8);
            Size size = (Size) c().f(b0.p1.f4313n, null);
            if (size != null) {
                u0Var.B0(new Rational(size.getWidth(), size.getHeight()));
            }
            g1.e.k((Executor) c().f(g0.h.E, e0.c.d()), "The IO executor can't be null");
            b0.z1 c9 = c();
            u0.a aVar2 = b0.n1.K;
            if (c9.a(aVar2)) {
                Integer num2 = (Integer) c().b(aVar2);
                if (num2 == null || !(num2.intValue() == 0 || num2.intValue() == 1 || num2.intValue() == 3 || num2.intValue() == 2)) {
                    throw new IllegalArgumentException("The flash mode is not allowed to set: " + num2);
                } else if (num2.intValue() == 3 && c().f(b0.n1.T, null) == null) {
                    throw new IllegalArgumentException("The flash mode is not allowed to set to FLASH_MODE_SCREEN without setting ScreenFlash");
                }
            }
            return u0Var;
        }

        @Override // b0.g3.a
        /* renamed from: g */
        public b0.n1 d() {
            return new b0.n1(b0.f2.b0(this.f12935a));
        }

        public b h(h3.b bVar) {
            c().Y(g3.B, bVar);
            return this;
        }

        public b i(a0 a0Var) {
            c().Y(b0.o1.f4300i, a0Var);
            return this;
        }

        public b j(int i8) {
            c().Y(b0.n1.K, Integer.valueOf(i8));
            return this;
        }

        public b k(int i8) {
            c().Y(b0.n1.N, Integer.valueOf(i8));
            return this;
        }

        public b l(o0.c cVar) {
            c().Y(b0.p1.f4317r, cVar);
            return this;
        }

        public b m(int i8) {
            c().Y(g3.f4204x, Integer.valueOf(i8));
            return this;
        }

        public b n(int i8) {
            if (i8 == -1) {
                i8 = 0;
            }
            c().Y(b0.p1.f4309j, Integer.valueOf(i8));
            return this;
        }

        public b o(Class cls) {
            c().Y(g0.m.G, cls);
            if (c().f(g0.m.F, null) == null) {
                p(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public b p(String str) {
            c().Y(g0.m.F, str);
            return this;
        }

        @Override // b0.p1.a
        /* renamed from: q */
        public b b(Size size) {
            c().Y(b0.p1.f4313n, size);
            return this;
        }

        @Override // b0.p1.a
        /* renamed from: r */
        public b a(int i8) {
            c().Y(b0.p1.f4310k, Integer.valueOf(i8));
            return this;
        }

        private b(b0.a2 a2Var) {
            this.f12935a = a2Var;
            Class cls = (Class) a2Var.f(g0.m.G, null);
            if (cls == null || cls.equals(u0.class)) {
                h(h3.b.IMAGE_CAPTURE);
                o(u0.class);
                return;
            }
            throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        private static final o0.c f12936a;

        /* renamed from: b  reason: collision with root package name */
        private static final b0.n1 f12937b;

        /* renamed from: c  reason: collision with root package name */
        private static final a0 f12938c;

        static {
            o0.c a8 = new c.a().d(o0.a.f9805c).f(o0.d.f9817c).a();
            f12936a = a8;
            a0 a0Var = a0.f12730d;
            f12938c = a0Var;
            f12937b = new b().m(4).n(0).l(a8).k(0).i(a0Var).d();
        }

        public b0.n1 a() {
            return f12937b;
        }
    }

    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12939a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f12940b = false;

        /* renamed from: c  reason: collision with root package name */
        private boolean f12941c;

        /* renamed from: d  reason: collision with root package name */
        private Location f12942d;

        public Location a() {
            return this.f12942d;
        }

        public boolean b() {
            return this.f12939a;
        }

        public boolean c() {
            return this.f12941c;
        }

        public String toString() {
            return "Metadata{mIsReversedHorizontal=" + this.f12939a + ", mIsReversedVertical=" + this.f12941c + ", mLocation=" + this.f12942d + "}";
        }
    }

    /* loaded from: classes.dex */
    public static abstract class e {
    }

    /* loaded from: classes.dex */
    public interface f {
        default void a(Bitmap bitmap) {
        }

        default void b(int i8) {
        }

        default void c() {
        }

        void d(h hVar);

        void e(v0 v0Var);
    }

    /* loaded from: classes.dex */
    public static final class g {

        /* renamed from: a  reason: collision with root package name */
        private final File f12943a;

        /* renamed from: b  reason: collision with root package name */
        private final ContentResolver f12944b;

        /* renamed from: c  reason: collision with root package name */
        private final Uri f12945c;

        /* renamed from: d  reason: collision with root package name */
        private final ContentValues f12946d;

        /* renamed from: e  reason: collision with root package name */
        private final OutputStream f12947e;

        /* renamed from: f  reason: collision with root package name */
        private final d f12948f;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private File f12949a;

            /* renamed from: b  reason: collision with root package name */
            private ContentResolver f12950b;

            /* renamed from: c  reason: collision with root package name */
            private Uri f12951c;

            /* renamed from: d  reason: collision with root package name */
            private ContentValues f12952d;

            /* renamed from: e  reason: collision with root package name */
            private OutputStream f12953e;

            /* renamed from: f  reason: collision with root package name */
            private d f12954f;

            public a(File file) {
                this.f12949a = file;
            }

            public g a() {
                return new g(this.f12949a, this.f12950b, this.f12951c, this.f12952d, this.f12953e, this.f12954f);
            }
        }

        g(File file, ContentResolver contentResolver, Uri uri, ContentValues contentValues, OutputStream outputStream, d dVar) {
            this.f12943a = file;
            this.f12944b = contentResolver;
            this.f12945c = uri;
            this.f12946d = contentValues;
            this.f12947e = outputStream;
            this.f12948f = dVar == null ? new d() : dVar;
        }

        public ContentResolver a() {
            return this.f12944b;
        }

        public ContentValues b() {
            return this.f12946d;
        }

        public File c() {
            return this.f12943a;
        }

        public d d() {
            return this.f12948f;
        }

        public OutputStream e() {
            return this.f12947e;
        }

        public Uri f() {
            return this.f12945c;
        }

        public String toString() {
            return "OutputFileOptions{mFile=" + this.f12943a + ", mContentResolver=" + this.f12944b + ", mSaveCollection=" + this.f12945c + ", mContentValues=" + this.f12946d + ", mOutputStream=" + this.f12947e + ", mMetadata=" + this.f12948f + "}";
        }
    }

    /* loaded from: classes.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        private final Uri f12955a;

        public h(Uri uri) {
            this.f12955a = uri;
        }
    }

    /* loaded from: classes.dex */
    public interface i {
        void a(long j8, j jVar);

        void clear();
    }

    /* loaded from: classes.dex */
    public interface j {
        void a();
    }

    u0(b0.n1 n1Var) {
        super(n1Var);
        this.f12923p = new q1.a() { // from class: y.q0
            @Override // b0.q1.a
            public final void a(b0.q1 q1Var) {
                u0.w0(q1Var);
            }
        };
        this.f12925r = new AtomicReference(null);
        this.f12927t = -1;
        this.f12928u = null;
        this.A = new a();
        b0.n1 n1Var2 = (b0.n1) j();
        this.f12924q = n1Var2.a(b0.n1.J) ? n1Var2.a0() : 1;
        this.f12926s = n1Var2.c0(0);
        this.f12929v = g0.j.g(n1Var2.g0());
    }

    private void A0(Executor executor, e eVar, f fVar) {
        v0 v0Var = new v0(4, "Not bound to a valid Camera [" + this + "]", null);
        if (fVar == null) {
            throw new IllegalArgumentException("Must have either in-memory or on-disk callback.");
        }
        fVar.e(v0Var);
    }

    private void D0() {
        E0(this.f12929v);
    }

    private void E0(i iVar) {
        h().c(iVar);
    }

    private void I0(Executor executor, e eVar, f fVar, g gVar) {
        d0.q.a();
        if (n0() == 3 && this.f12929v.h() == null) {
            throw new IllegalArgumentException("ScreenFlash not set for FLASH_MODE_SCREEN");
        }
        Log.d("ImageCapture", "takePictureInternal");
        b0.h0 g8 = g();
        if (g8 == null) {
            A0(executor, eVar, fVar);
            return;
        }
        a0.y0 y0Var = this.f12932y;
        Objects.requireNonNull(y0Var);
        y0Var.j(a0.e1.v(executor, eVar, fVar, gVar, q0(), w(), q(g8), o0(), m0(), this.f12930w.r()));
    }

    private void J0() {
        synchronized (this.f12925r) {
            if (this.f12925r.get() != null) {
                return;
            }
            h().n(n0());
        }
    }

    private void f0() {
        this.f12929v.f();
        a0.y0 y0Var = this.f12932y;
        if (y0Var != null) {
            y0Var.e();
        }
    }

    private void h0() {
        i0(false);
    }

    private void i0(boolean z7) {
        a0.y0 y0Var;
        Log.d("ImageCapture", "clearPipeline");
        d0.q.a();
        q2.c cVar = this.f12933z;
        if (cVar != null) {
            cVar.b();
            this.f12933z = null;
        }
        a0.y yVar = this.f12931x;
        if (yVar != null) {
            yVar.a();
            this.f12931x = null;
        }
        if (z7 || (y0Var = this.f12932y) == null) {
            return;
        }
        y0Var.e();
        this.f12932y = null;
    }

    private q2.b j0(String str, b0.n1 n1Var, u2 u2Var) {
        d0.q.a();
        Log.d("ImageCapture", String.format("createPipeline(cameraId: %s, streamSpec: %s)", str, u2Var));
        Size e8 = u2Var.e();
        b0.h0 g8 = g();
        Objects.requireNonNull(g8);
        boolean z7 = !g8.j();
        if (this.f12931x != null) {
            g1.e.l(z7);
            this.f12931x.a();
        }
        if (((Boolean) j().f(b0.n1.V, Boolean.FALSE)).booleanValue()) {
            p0();
        }
        l();
        this.f12931x = new a0.y(n1Var, e8, null, z7, null, 35);
        if (this.f12932y == null) {
            this.f12932y = new a0.y0(this.A);
        }
        this.f12932y.m(this.f12931x);
        q2.b f8 = this.f12931x.f(u2Var.e());
        if (m0() == 2 && !u2Var.f()) {
            h().b(f8);
        }
        if (u2Var.d() != null) {
            f8.g(u2Var.d());
        }
        q2.c cVar = this.f12933z;
        if (cVar != null) {
            cVar.b();
        }
        q2.c cVar2 = new q2.c(new q2.d() { // from class: y.s0
            @Override // b0.q2.d
            public final void a(q2 q2Var, q2.g gVar) {
                u0.this.v0(q2Var, gVar);
            }
        });
        this.f12933z = cVar2;
        f8.t(cVar2);
        return f8;
    }

    private int l0() {
        b0.h0 g8 = g();
        if (g8 != null) {
            return g8.b().d();
        }
        return -1;
    }

    private int o0() {
        b0.n1 n1Var = (b0.n1) j();
        if (n1Var.a(b0.n1.S)) {
            return n1Var.f0();
        }
        int i8 = this.f12924q;
        if (i8 != 0) {
            if (i8 == 1 || i8 == 2) {
                return 95;
            }
            throw new IllegalStateException("CaptureMode " + this.f12924q + " is invalid");
        }
        return 100;
    }

    private s2 p0() {
        g().q().s(null);
        return null;
    }

    private Rect q0() {
        Rect B2 = B();
        Size f8 = f();
        Objects.requireNonNull(f8);
        if (B2 != null) {
            return B2;
        }
        if (k0.b.h(this.f12928u)) {
            b0.h0 g8 = g();
            Objects.requireNonNull(g8);
            int q8 = q(g8);
            Rational rational = new Rational(this.f12928u.getDenominator(), this.f12928u.getNumerator());
            if (!d0.r.h(q8)) {
                rational = this.f12928u;
            }
            Rect a8 = k0.b.a(f8, rational);
            Objects.requireNonNull(a8);
            return a8;
        }
        return new Rect(0, 0, f8.getWidth(), f8.getHeight());
    }

    private static boolean s0(List list, int i8) {
        if (list == null) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((Integer) ((Pair) it.next()).first).equals(Integer.valueOf(i8))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean t0(b0.z1 z1Var) {
        return Objects.equals(z1Var.f(b0.n1.N, null), 1);
    }

    private boolean u0() {
        if (g() == null) {
            return false;
        }
        g().q().s(null);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v0(q2 q2Var, q2.g gVar) {
        List a8;
        if (g() == null) {
            return;
        }
        this.f12932y.k();
        i0(true);
        q2.b j02 = j0(i(), (b0.n1) j(), (u2) g1.e.j(e()));
        this.f12930w = j02;
        a8 = h0.a(new Object[]{j02.o()});
        Y(a8);
        H();
        this.f12932y.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void w0(b0.q1 q1Var) {
        try {
            androidx.camera.core.n acquireLatestImage = q1Var.acquireLatestImage();
            Log.d("ImageCapture", "Discarding ImageProxy which was inadvertently acquired: " + acquireLatestImage);
            if (acquireLatestImage != null) {
                acquireLatestImage.close();
            }
        } catch (IllegalStateException e8) {
            Log.e("ImageCapture", "Failed to acquire latest image.", e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void x0(List list) {
        return null;
    }

    @Override // y.h2
    public g3.a A(b0.u0 u0Var) {
        return b.f(u0Var);
    }

    public void B0(Rational rational) {
        this.f12928u = rational;
    }

    public void C0(int i8) {
        f1.a("ImageCapture", "setFlashMode: flashMode = " + i8);
        if (i8 != 0 && i8 != 1 && i8 != 2) {
            if (i8 != 3) {
                throw new IllegalArgumentException("Invalid flash mode: " + i8);
            } else if (this.f12929v.h() == null) {
                throw new IllegalArgumentException("ScreenFlash not set for FLASH_MODE_SCREEN");
            } else {
                if (g() != null && l0() != 0) {
                    throw new IllegalArgumentException("Not a front camera despite setting FLASH_MODE_SCREEN");
                }
            }
        }
        synchronized (this.f12925r) {
            this.f12927t = i8;
            J0();
        }
    }

    public void F0(int i8) {
        int r02 = r0();
        if (!V(i8) || this.f12928u == null) {
            return;
        }
        this.f12928u = k0.b.f(Math.abs(d0.c.b(i8) - d0.c.b(r02)), this.f12928u);
    }

    i4.d G0(List list) {
        d0.q.a();
        return f0.n.G(h().h(list, this.f12924q, this.f12926s), new m.a() { // from class: y.t0
            @Override // m.a
            public final Object apply(Object obj) {
                Void x02;
                x02 = u0.x0((List) obj);
                return x02;
            }
        }, e0.c.b());
    }

    /* renamed from: H0 */
    public void y0(final g gVar, final Executor executor, final f fVar) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            e0.c.e().execute(new Runnable() { // from class: y.r0
                @Override // java.lang.Runnable
                public final void run() {
                    u0.this.y0(gVar, executor, fVar);
                }
            });
        } else {
            I0(executor, null, fVar, gVar);
        }
    }

    @Override // y.h2
    public void K() {
        g1.e.k(g(), "Attached camera cannot be null");
        if (n0() == 3 && l0() != 0) {
            throw new IllegalArgumentException("Not a front camera despite setting FLASH_MODE_SCREEN in ImageCapture");
        }
    }

    void K0() {
        synchronized (this.f12925r) {
            Integer num = (Integer) this.f12925r.getAndSet(null);
            if (num == null) {
                return;
            }
            if (num.intValue() != n0()) {
                J0();
            }
        }
    }

    @Override // y.h2
    public void L() {
        f1.a("ImageCapture", "onCameraControlReady");
        J0();
        D0();
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00cf, code lost:
        if (s0(r5, 35) != false) goto L27;
     */
    @Override // y.h2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected b0.g3 M(b0.g0 r5, b0.g3.a r6) {
        /*
            r4 = this;
            b0.l2 r5 = r5.k()
            java.lang.Class<androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk> r0 = androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk.class
            boolean r5 = r5.a(r0)
            if (r5 == 0) goto L34
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            b0.z1 r0 = r6.c()
            b0.u0$a r1 = b0.n1.Q
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            java.lang.Object r0 = r0.f(r1, r2)
            boolean r5 = r5.equals(r0)
            java.lang.String r0 = "ImageCapture"
            if (r5 == 0) goto L28
            java.lang.String r5 = "Device quirk suggests software JPEG encoder, but it has been explicitly disabled."
            y.f1.l(r0, r5)
            goto L34
        L28:
            java.lang.String r5 = "Requesting software JPEG due to device quirk."
            y.f1.e(r0, r5)
            b0.z1 r5 = r6.c()
            r5.Y(r1, r2)
        L34:
            b0.z1 r5 = r6.c()
            boolean r5 = r4.k0(r5)
            b0.z1 r0 = r6.c()
            b0.u0$a r1 = b0.n1.M
            r2 = 0
            java.lang.Object r0 = r0.f(r1, r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r1 = 35
            r3 = 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L79
            boolean r2 = r4.u0()
            if (r2 == 0) goto L5e
            int r2 = r0.intValue()
            if (r2 != r3) goto L5c
            goto L5e
        L5c:
            r2 = 0
            goto L5f
        L5e:
            r2 = 1
        L5f:
            java.lang.String r3 = "Cannot set non-JPEG buffer format with Extensions enabled."
            g1.e.b(r2, r3)
            b0.z1 r2 = r6.c()
            b0.u0$a r3 = b0.o1.f4299h
            if (r5 == 0) goto L6d
            goto L71
        L6d:
            int r1 = r0.intValue()
        L71:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            r2.Y(r3, r5)
            goto Ld2
        L79:
            b0.z1 r0 = r6.c()
            boolean r0 = t0(r0)
            if (r0 == 0) goto L9e
            b0.z1 r5 = r6.c()
            b0.u0$a r0 = b0.o1.f4299h
            r1 = 4101(0x1005, float:5.747E-42)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r5.Y(r0, r1)
            b0.z1 r5 = r6.c()
            b0.u0$a r0 = b0.o1.f4300i
            y.a0 r1 = y.a0.f12729c
        L9a:
            r5.Y(r0, r1)
            goto Ld2
        L9e:
            if (r5 == 0) goto Lab
        La0:
            b0.z1 r5 = r6.c()
            b0.u0$a r0 = b0.o1.f4299h
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L9a
        Lab:
            b0.z1 r5 = r6.c()
            b0.u0$a r0 = b0.p1.f4316q
            java.lang.Object r5 = r5.f(r0, r2)
            java.util.List r5 = (java.util.List) r5
            if (r5 != 0) goto Lc4
        Lb9:
            b0.z1 r5 = r6.c()
            b0.u0$a r0 = b0.o1.f4299h
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            goto L9a
        Lc4:
            boolean r0 = s0(r5, r3)
            if (r0 == 0) goto Lcb
            goto Lb9
        Lcb:
            boolean r5 = s0(r5, r1)
            if (r5 == 0) goto Ld2
            goto La0
        Ld2:
            b0.g3 r5 = r6.d()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: y.u0.M(b0.g0, b0.g3$a):b0.g3");
    }

    @Override // y.h2
    public void O() {
        f0();
    }

    @Override // y.h2
    protected u2 P(b0.u0 u0Var) {
        List a8;
        this.f12930w.g(u0Var);
        a8 = h0.a(new Object[]{this.f12930w.o()});
        Y(a8);
        return e().g().d(u0Var).a();
    }

    @Override // y.h2
    protected u2 Q(u2 u2Var, u2 u2Var2) {
        List a8;
        q2.b j02 = j0(i(), (b0.n1) j(), u2Var);
        this.f12930w = j02;
        a8 = h0.a(new Object[]{j02.o()});
        Y(a8);
        F();
        return u2Var;
    }

    @Override // y.h2
    public void R() {
        f0();
        h0();
        E0(null);
    }

    @Override // y.h2
    public g3 k(boolean z7, h3 h3Var) {
        c cVar = B;
        b0.u0 a8 = h3Var.a(cVar.a().m(), m0());
        if (z7) {
            a8 = b0.u0.u(a8, cVar.a());
        }
        if (a8 == null) {
            return null;
        }
        return A(a8).d();
    }

    boolean k0(b0.z1 z1Var) {
        boolean z7;
        Boolean bool = Boolean.TRUE;
        u0.a aVar = b0.n1.Q;
        Boolean bool2 = Boolean.FALSE;
        boolean z8 = false;
        if (bool.equals(z1Var.f(aVar, bool2))) {
            if (u0()) {
                f1.l("ImageCapture", "Software JPEG cannot be used with Extensions.");
                z7 = false;
            } else {
                z7 = true;
            }
            Integer num = (Integer) z1Var.f(b0.n1.M, null);
            if (num == null || num.intValue() == 256) {
                z8 = z7;
            } else {
                f1.l("ImageCapture", "Software JPEG cannot be used with non-JPEG output buffer format.");
            }
            if (!z8) {
                f1.l("ImageCapture", "Unable to support software JPEG. Disabling.");
                z1Var.Y(aVar, bool2);
            }
        }
        return z8;
    }

    public int m0() {
        return this.f12924q;
    }

    public int n0() {
        int i8;
        synchronized (this.f12925r) {
            i8 = this.f12927t;
            if (i8 == -1) {
                i8 = ((b0.n1) j()).b0(2);
            }
        }
        return i8;
    }

    public int r0() {
        return z();
    }

    public String toString() {
        return "ImageCapture:" + o();
    }

    @Override // y.h2
    public Set y() {
        HashSet hashSet = new HashSet();
        hashSet.add(4);
        return hashSet;
    }

    void z0() {
        synchronized (this.f12925r) {
            if (this.f12925r.get() != null) {
                return;
            }
            this.f12925r.set(Integer.valueOf(n0()));
        }
    }
}
