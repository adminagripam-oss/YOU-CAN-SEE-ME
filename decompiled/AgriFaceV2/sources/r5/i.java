package r5;

import a4.ba;
import a4.ca;
import a4.da;
import a4.e9;
import a4.ea;
import a4.ed;
import a4.n2;
import a4.o2;
import a4.o9;
import a4.od;
import a4.p9;
import a4.q2;
import a4.qd;
import a4.sd;
import a4.t9;
import a4.td;
import a4.ua;
import a4.wa;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import k3.q;
/* loaded from: classes.dex */
public final class i extends m5.f {

    /* renamed from: j  reason: collision with root package name */
    static final AtomicBoolean f11341j = new AtomicBoolean(true);

    /* renamed from: k  reason: collision with root package name */
    private static final p5.d f11342k = p5.d.b();

    /* renamed from: d  reason: collision with root package name */
    private final q5.e f11343d;

    /* renamed from: e  reason: collision with root package name */
    private final qd f11344e;

    /* renamed from: f  reason: collision with root package name */
    private final sd f11345f;

    /* renamed from: g  reason: collision with root package name */
    private final c f11346g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f11347h;

    /* renamed from: i  reason: collision with root package name */
    private final p5.a f11348i = new p5.a();

    public i(qd qdVar, q5.e eVar, c cVar) {
        q.h(eVar, "FaceDetectorOptions can not be null");
        this.f11343d = eVar;
        this.f11344e = qdVar;
        this.f11346g = cVar;
        this.f11345f = sd.a(m5.i.c().b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void m(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((q5.a) it.next()).m(-1);
        }
    }

    private final synchronized void n(final ca caVar, long j8, final o5.a aVar, final int i8, final int i9) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j8;
        this.f11344e.f(new od() { // from class: r5.g
            @Override // a4.od
            public final ed zza() {
                return i.this.j(elapsedRealtime, caVar, i8, i9, aVar);
            }
        }, da.ON_DEVICE_FACE_DETECT);
        o2 o2Var = new o2();
        o2Var.c(caVar);
        o2Var.d(Boolean.valueOf(f11341j.get()));
        o2Var.a(Integer.valueOf(i8));
        o2Var.e(Integer.valueOf(i9));
        o2Var.b(k.a(this.f11343d));
        final q2 f8 = o2Var.f();
        final h hVar = new h(this);
        final qd qdVar = this.f11344e;
        final da daVar = da.AGGREGATED_ON_DEVICE_FACE_DETECTION;
        m5.g.d().execute(new Runnable(daVar, f8, elapsedRealtime, hVar, null) { // from class: a4.ld

            /* renamed from: f  reason: collision with root package name */
            public final /* synthetic */ da f791f;

            /* renamed from: g  reason: collision with root package name */
            public final /* synthetic */ Object f792g;

            /* renamed from: h  reason: collision with root package name */
            public final /* synthetic */ long f793h;

            /* renamed from: i  reason: collision with root package name */
            public final /* synthetic */ r5.h f794i;

            @Override // java.lang.Runnable
            public final void run() {
                qd.this.h(this.f791f, this.f792g, this.f793h, this.f794i);
            }
        });
        long currentTimeMillis = System.currentTimeMillis();
        this.f11345f.c(true != this.f11347h ? 24303 : 24304, caVar.zza(), currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    @Override // m5.k
    public final synchronized void b() {
        this.f11347h = this.f11346g.b();
    }

    @Override // m5.k
    public final synchronized void d() {
        this.f11346g.zzb();
        f11341j.set(true);
        qd qdVar = this.f11344e;
        ea eaVar = new ea();
        eaVar.e(this.f11347h ? ba.TYPE_THICK : ba.TYPE_THIN);
        qdVar.d(td.a(eaVar), da.ON_DEVICE_FACE_CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ ed j(long j8, ca caVar, int i8, int i9, o5.a aVar) {
        ua uaVar = new ua();
        t9 t9Var = new t9();
        t9Var.c(Long.valueOf(j8));
        t9Var.d(caVar);
        t9Var.e(Boolean.valueOf(f11341j.get()));
        Boolean bool = Boolean.TRUE;
        t9Var.a(bool);
        t9Var.b(bool);
        uaVar.g(t9Var.f());
        uaVar.e(k.a(this.f11343d));
        uaVar.d(Integer.valueOf(i8));
        uaVar.h(Integer.valueOf(i9));
        p5.d dVar = f11342k;
        int c8 = dVar.c(aVar);
        int d8 = dVar.d(aVar);
        o9 o9Var = new o9();
        o9Var.a(c8 != -1 ? c8 != 35 ? c8 != 842094169 ? c8 != 16 ? c8 != 17 ? p9.UNKNOWN_FORMAT : p9.NV21 : p9.NV16 : p9.YV12 : p9.YUV_420_888 : p9.BITMAP);
        o9Var.b(Integer.valueOf(d8));
        uaVar.f(o9Var.d());
        wa i10 = uaVar.i();
        ea eaVar = new ea();
        eaVar.e(this.f11347h ? ba.TYPE_THICK : ba.TYPE_THIN);
        eaVar.g(i10);
        return td.a(eaVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ ed k(q2 q2Var, int i8, e9 e9Var) {
        ea eaVar = new ea();
        eaVar.e(this.f11347h ? ba.TYPE_THICK : ba.TYPE_THIN);
        n2 n2Var = new n2();
        n2Var.a(Integer.valueOf(i8));
        n2Var.c(q2Var);
        n2Var.b(e9Var);
        eaVar.d(n2Var.e());
        return td.a(eaVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        r2 = k3.q.g(r0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0108 A[Catch: a -> 0x0127, all -> 0x0146, TryCatch #0 {, blocks: (B:4:0x0005, B:5:0x000e, B:9:0x0021, B:10:0x002a, B:12:0x002d, B:13:0x0031, B:40:0x0102, B:47:0x0116, B:46:0x0111, B:43:0x0108, B:15:0x003a, B:16:0x003f, B:17:0x0048, B:19:0x004e, B:20:0x0059, B:22:0x005f, B:24:0x006b, B:26:0x0071, B:28:0x007f, B:30:0x00a9, B:32:0x00cd, B:34:0x00dc, B:54:0x012c, B:56:0x0134, B:58:0x0139, B:59:0x0145, B:57:0x0137, B:37:0x00ef, B:39:0x00fa), top: B:63:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0111 A[Catch: a -> 0x0127, all -> 0x0146, TryCatch #0 {, blocks: (B:4:0x0005, B:5:0x000e, B:9:0x0021, B:10:0x002a, B:12:0x002d, B:13:0x0031, B:40:0x0102, B:47:0x0116, B:46:0x0111, B:43:0x0108, B:15:0x003a, B:16:0x003f, B:17:0x0048, B:19:0x004e, B:20:0x0059, B:22:0x005f, B:24:0x006b, B:26:0x0071, B:28:0x007f, B:30:0x00a9, B:32:0x00cd, B:34:0x00dc, B:54:0x012c, B:56:0x0134, B:58:0x0139, B:59:0x0145, B:57:0x0137, B:37:0x00ef, B:39:0x00fa), top: B:63:0x0005 }] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.List] */
    @Override // m5.f
    /* renamed from: l */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List i(o5.a r21) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: r5.i.i(o5.a):java.util.List");
    }
}
