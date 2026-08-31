package z3;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class wb {

    /* renamed from: k  reason: collision with root package name */
    private static mc f14273k;

    /* renamed from: l  reason: collision with root package name */
    private static final oc f14274l = oc.c("optional-module-barcode", "com.google.android.gms.vision.barcode");

    /* renamed from: a  reason: collision with root package name */
    private final String f14275a;

    /* renamed from: b  reason: collision with root package name */
    private final String f14276b;

    /* renamed from: c  reason: collision with root package name */
    private final pb f14277c;

    /* renamed from: d  reason: collision with root package name */
    private final m5.m f14278d;

    /* renamed from: e  reason: collision with root package name */
    private final f4.j f14279e;

    /* renamed from: f  reason: collision with root package name */
    private final f4.j f14280f;

    /* renamed from: g  reason: collision with root package name */
    private final String f14281g;

    /* renamed from: h  reason: collision with root package name */
    private final int f14282h;

    /* renamed from: i  reason: collision with root package name */
    private final Map f14283i = new HashMap();

    /* renamed from: j  reason: collision with root package name */
    private final Map f14284j = new HashMap();

    public wb(Context context, final m5.m mVar, pb pbVar, String str) {
        this.f14275a = context.getPackageName();
        this.f14276b = m5.c.a(context);
        this.f14278d = mVar;
        this.f14277c = pbVar;
        jc.a();
        this.f14281g = str;
        this.f14279e = m5.g.a().b(new Callable() { // from class: z3.tb
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return wb.this.a();
            }
        });
        m5.g a8 = m5.g.a();
        mVar.getClass();
        this.f14280f = a8.b(new Callable() { // from class: z3.ub
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return m5.m.this.a();
            }
        });
        oc ocVar = f14274l;
        this.f14282h = ocVar.containsKey(str) ? DynamiteModule.b(context, (String) ocVar.get(str)) : -1;
    }

    private static synchronized mc d() {
        synchronized (wb.class) {
            mc mcVar = f14273k;
            if (mcVar != null) {
                return mcVar;
            }
            androidx.core.os.h a8 = androidx.core.os.e.a(Resources.getSystem().getConfiguration());
            mb mbVar = new mb();
            for (int i8 = 0; i8 < a8.f(); i8++) {
                mbVar.c(m5.c.b(a8.c(i8)));
            }
            mc d8 = mbVar.d();
            f14273k = d8;
            return d8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ String a() {
        return k3.n.a().b(this.f14281g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(ob obVar, e8 e8Var, String str) {
        obVar.b(e8Var);
        String a8 = obVar.a();
        ja jaVar = new ja();
        jaVar.b(this.f14275a);
        jaVar.c(this.f14276b);
        jaVar.h(d());
        jaVar.g(Boolean.TRUE);
        jaVar.l(a8);
        jaVar.j(str);
        jaVar.i(this.f14280f.j() ? (String) this.f14280f.g() : this.f14278d.a());
        jaVar.d(10);
        jaVar.k(Integer.valueOf(this.f14282h));
        obVar.d(jaVar);
        this.f14277c.a(obVar);
    }

    public final void c(gc gcVar, final e8 e8Var) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f14283i.get(e8Var) != null && elapsedRealtime - ((Long) this.f14283i.get(e8Var)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.f14283i.put(e8Var, Long.valueOf(elapsedRealtime));
        int i8 = gcVar.f13694a;
        int i9 = gcVar.f13695b;
        int i10 = gcVar.f13696c;
        int i11 = gcVar.f13697d;
        int i12 = gcVar.f13698e;
        long j8 = gcVar.f13699f;
        int i13 = gcVar.f13700g;
        w7 w7Var = new w7();
        w7Var.d(i8 != -1 ? i8 != 35 ? i8 != 842094169 ? i8 != 16 ? i8 != 17 ? r7.UNKNOWN_FORMAT : r7.NV21 : r7.NV16 : r7.YV12 : r7.YUV_420_888 : r7.BITMAP);
        w7Var.f(i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? x7.ANDROID_MEDIA_IMAGE : x7.FILEPATH : x7.BYTEBUFFER : x7.BYTEARRAY : x7.BITMAP);
        w7Var.c(Integer.valueOf(i10));
        w7Var.e(Integer.valueOf(i11));
        w7Var.g(Integer.valueOf(i12));
        w7Var.b(Long.valueOf(j8));
        w7Var.h(Integer.valueOf(i13));
        z7 j9 = w7Var.j();
        f8 f8Var = new f8();
        f8Var.d(j9);
        final ob e8 = xb.e(f8Var);
        final String b8 = this.f14279e.j() ? (String) this.f14279e.g() : k3.n.a().b(this.f14281g);
        m5.g.d().execute(new Runnable() { // from class: z3.vb
            @Override // java.lang.Runnable
            public final void run() {
                wb.this.b(e8, e8Var, b8);
            }
        });
    }
}
