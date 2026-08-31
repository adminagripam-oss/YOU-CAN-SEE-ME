package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class xh {

    /* renamed from: j  reason: collision with root package name */
    private static v f5919j;

    /* renamed from: k  reason: collision with root package name */
    private static final x f5920k = x.c("optional-module-barcode", "com.google.android.gms.vision.barcode");

    /* renamed from: a  reason: collision with root package name */
    private final String f5921a;

    /* renamed from: b  reason: collision with root package name */
    private final String f5922b;

    /* renamed from: c  reason: collision with root package name */
    private final ph f5923c;

    /* renamed from: d  reason: collision with root package name */
    private final m5.m f5924d;

    /* renamed from: e  reason: collision with root package name */
    private final f4.j f5925e;

    /* renamed from: f  reason: collision with root package name */
    private final f4.j f5926f;

    /* renamed from: g  reason: collision with root package name */
    private final String f5927g;

    /* renamed from: h  reason: collision with root package name */
    private final int f5928h;

    /* renamed from: i  reason: collision with root package name */
    private final Map f5929i = new HashMap();

    public xh(Context context, final m5.m mVar, ph phVar, String str) {
        new HashMap();
        this.f5921a = context.getPackageName();
        this.f5922b = m5.c.a(context);
        this.f5924d = mVar;
        this.f5923c = phVar;
        gi.a();
        this.f5927g = str;
        this.f5925e = m5.g.a().b(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.vh
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return xh.this.a();
            }
        });
        m5.g a8 = m5.g.a();
        Objects.requireNonNull(mVar);
        this.f5926f = a8.b(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.wh
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return m5.m.this.a();
            }
        });
        x xVar = f5920k;
        this.f5928h = xVar.containsKey(str) ? DynamiteModule.b(context, (String) xVar.get(str)) : -1;
    }

    private static synchronized v d() {
        synchronized (xh.class) {
            v vVar = f5919j;
            if (vVar != null) {
                return vVar;
            }
            androidx.core.os.h a8 = androidx.core.os.e.a(Resources.getSystem().getConfiguration());
            s sVar = new s();
            for (int i8 = 0; i8 < a8.f(); i8++) {
                sVar.a(m5.c.b(a8.c(i8)));
            }
            v b8 = sVar.b();
            f5919j = b8;
            return b8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ String a() {
        return k3.n.a().b(this.f5927g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(oh ohVar, vc vcVar, String str) {
        ohVar.d(vcVar);
        String b8 = ohVar.b();
        ng ngVar = new ng();
        ngVar.b(this.f5921a);
        ngVar.c(this.f5922b);
        ngVar.h(d());
        ngVar.g(Boolean.TRUE);
        ngVar.l(b8);
        ngVar.j(str);
        ngVar.i(this.f5926f.j() ? (String) this.f5926f.g() : this.f5924d.a());
        ngVar.d(10);
        ngVar.k(Integer.valueOf(this.f5928h));
        ohVar.a(ngVar);
        this.f5923c.a(ohVar);
    }

    public final void c(g4.a aVar, final vc vcVar) {
        final String b8;
        Map map = this.f5929i;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (map.get(vcVar) != null && elapsedRealtime - ((Long) this.f5929i.get(vcVar)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.f5929i.put(vcVar, Long.valueOf(elapsedRealtime));
        tc tcVar = aVar.f7121a;
        cc ccVar = aVar.f7122b;
        uc ucVar = aVar.f7123c;
        int i8 = aVar.f7124d;
        wc wcVar = new wc();
        wcVar.d(tcVar);
        tb tbVar = new tb();
        tbVar.b(ccVar);
        tbVar.a(ucVar);
        wcVar.f(tbVar.c());
        final oh e8 = ai.e(wcVar, i8);
        if (this.f5925e.j()) {
            b8 = (String) this.f5925e.g();
        } else {
            b8 = k3.n.a().b(this.f5927g);
        }
        m5.g.d().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.uh
            @Override // java.lang.Runnable
            public final void run() {
                xh.this.b(e8, vcVar, b8);
            }
        });
    }
}
