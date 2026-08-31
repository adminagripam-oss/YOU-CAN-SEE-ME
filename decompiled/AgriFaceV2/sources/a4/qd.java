package a4;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class qd {

    /* renamed from: k  reason: collision with root package name */
    private static p0 f986k;

    /* renamed from: l  reason: collision with root package name */
    private static final r0 f987l = r0.c("optional-module-barcode", "com.google.android.gms.vision.barcode");

    /* renamed from: a  reason: collision with root package name */
    private final String f988a;

    /* renamed from: b  reason: collision with root package name */
    private final String f989b;

    /* renamed from: c  reason: collision with root package name */
    private final pd f990c;

    /* renamed from: d  reason: collision with root package name */
    private final m5.m f991d;

    /* renamed from: e  reason: collision with root package name */
    private final f4.j f992e;

    /* renamed from: f  reason: collision with root package name */
    private final f4.j f993f;

    /* renamed from: g  reason: collision with root package name */
    private final String f994g;

    /* renamed from: h  reason: collision with root package name */
    private final int f995h;

    /* renamed from: i  reason: collision with root package name */
    private final Map f996i = new HashMap();

    /* renamed from: j  reason: collision with root package name */
    private final Map f997j = new HashMap();

    public qd(Context context, final m5.m mVar, pd pdVar, String str) {
        this.f988a = context.getPackageName();
        this.f989b = m5.c.a(context);
        this.f991d = mVar;
        this.f990c = pdVar;
        ce.a();
        this.f994g = str;
        this.f992e = m5.g.a().b(new Callable() { // from class: a4.id
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return qd.this.b();
            }
        });
        m5.g a8 = m5.g.a();
        mVar.getClass();
        this.f993f = a8.b(new Callable() { // from class: a4.jd
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return m5.m.this.a();
            }
        });
        r0 r0Var = f987l;
        this.f995h = r0Var.containsKey(str) ? DynamiteModule.b(context, (String) r0Var.get(str)) : -1;
    }

    static long a(List list, double d8) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d8 / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    private static synchronized p0 i() {
        synchronized (qd.class) {
            p0 p0Var = f986k;
            if (p0Var != null) {
                return p0Var;
            }
            androidx.core.os.h a8 = androidx.core.os.e.a(Resources.getSystem().getConfiguration());
            m0 m0Var = new m0();
            for (int i8 = 0; i8 < a8.f(); i8++) {
                m0Var.c(m5.c.b(a8.c(i8)));
            }
            p0 d8 = m0Var.d();
            f986k = d8;
            return d8;
        }
    }

    private final String j() {
        return this.f992e.j() ? (String) this.f992e.g() : k3.n.a().b(this.f994g);
    }

    private final boolean k(da daVar, long j8, long j9) {
        return this.f996i.get(daVar) == null || j8 - ((Long) this.f996i.get(daVar)).longValue() > TimeUnit.SECONDS.toMillis(30L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ String b() {
        return k3.n.a().b(this.f994g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void c(ed edVar, da daVar, String str) {
        edVar.e(daVar);
        String b8 = edVar.b();
        fc fcVar = new fc();
        fcVar.b(this.f988a);
        fcVar.c(this.f989b);
        fcVar.h(i());
        fcVar.g(Boolean.TRUE);
        fcVar.l(b8);
        fcVar.j(str);
        fcVar.i(this.f993f.j() ? (String) this.f993f.g() : this.f991d.a());
        fcVar.d(10);
        fcVar.k(Integer.valueOf(this.f995h));
        edVar.d(fcVar);
        this.f990c.a(edVar);
    }

    public final void d(ed edVar, da daVar) {
        e(edVar, daVar, j());
    }

    public final void e(final ed edVar, final da daVar, final String str) {
        m5.g.d().execute(new Runnable() { // from class: a4.kd
            @Override // java.lang.Runnable
            public final void run() {
                qd.this.c(edVar, daVar, str);
            }
        });
    }

    public final void f(od odVar, da daVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (k(daVar, elapsedRealtime, 30L)) {
            this.f996i.put(daVar, Long.valueOf(elapsedRealtime));
            e(odVar.zza(), daVar, j());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void g(da daVar, r5.h hVar) {
        u0 u0Var = (u0) this.f997j.get(daVar);
        if (u0Var != null) {
            for (Object obj : u0Var.c()) {
                ArrayList<Long> arrayList = new ArrayList(u0Var.a(obj));
                Collections.sort(arrayList);
                c9 c9Var = new c9();
                long j8 = 0;
                for (Long l8 : arrayList) {
                    j8 += l8.longValue();
                }
                c9Var.a(Long.valueOf(j8 / arrayList.size()));
                c9Var.c(Long.valueOf(a(arrayList, 100.0d)));
                c9Var.f(Long.valueOf(a(arrayList, 75.0d)));
                c9Var.d(Long.valueOf(a(arrayList, 50.0d)));
                c9Var.b(Long.valueOf(a(arrayList, 25.0d)));
                c9Var.e(Long.valueOf(a(arrayList, 0.0d)));
                e(hVar.a(obj, arrayList.size(), c9Var.g()), daVar, j());
            }
            this.f997j.remove(daVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void h(final da daVar, Object obj, long j8, final r5.h hVar) {
        if (!this.f997j.containsKey(daVar)) {
            this.f997j.put(daVar, t.r());
        }
        ((u0) this.f997j.get(daVar)).d(obj, Long.valueOf(j8));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (k(daVar, elapsedRealtime, 30L)) {
            this.f996i.put(daVar, Long.valueOf(elapsedRealtime));
            m5.g.d().execute(new Runnable(daVar, hVar, null) { // from class: a4.md

                /* renamed from: f  reason: collision with root package name */
                public final /* synthetic */ da f834f;

                /* renamed from: g  reason: collision with root package name */
                public final /* synthetic */ r5.h f835g;

                @Override // java.lang.Runnable
                public final void run() {
                    qd.this.g(this.f834f, this.f835g);
                }
            });
        }
    }
}
