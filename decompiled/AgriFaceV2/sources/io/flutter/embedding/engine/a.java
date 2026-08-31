package io.flutter.embedding.engine;

import a6.a;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import h6.m;
import h6.n;
import h6.o;
import h6.r;
import h6.s;
import h6.t;
import h6.u;
import h6.v;
import h6.w;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p6.h;
/* loaded from: classes.dex */
public class a implements h.a {

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f7714a;

    /* renamed from: b  reason: collision with root package name */
    private final FlutterRenderer f7715b;

    /* renamed from: c  reason: collision with root package name */
    private final a6.a f7716c;

    /* renamed from: d  reason: collision with root package name */
    private final c f7717d;

    /* renamed from: e  reason: collision with root package name */
    private final j6.a f7718e;

    /* renamed from: f  reason: collision with root package name */
    private final h6.a f7719f;

    /* renamed from: g  reason: collision with root package name */
    private final h6.g f7720g;

    /* renamed from: h  reason: collision with root package name */
    private final h6.k f7721h;

    /* renamed from: i  reason: collision with root package name */
    private final h6.l f7722i;

    /* renamed from: j  reason: collision with root package name */
    private final m f7723j;

    /* renamed from: k  reason: collision with root package name */
    private final n f7724k;

    /* renamed from: l  reason: collision with root package name */
    private final h6.f f7725l;

    /* renamed from: m  reason: collision with root package name */
    private final s f7726m;

    /* renamed from: n  reason: collision with root package name */
    private final o f7727n;

    /* renamed from: o  reason: collision with root package name */
    private final r f7728o;

    /* renamed from: p  reason: collision with root package name */
    private final t f7729p;

    /* renamed from: q  reason: collision with root package name */
    private final u f7730q;

    /* renamed from: r  reason: collision with root package name */
    private final v f7731r;

    /* renamed from: s  reason: collision with root package name */
    private final w f7732s;

    /* renamed from: t  reason: collision with root package name */
    private final io.flutter.plugin.platform.w f7733t;

    /* renamed from: u  reason: collision with root package name */
    private final Set f7734u;

    /* renamed from: v  reason: collision with root package name */
    private final b f7735v;

    /* renamed from: io.flutter.embedding.engine.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0106a implements b {
        C0106a() {
        }

        @Override // io.flutter.embedding.engine.a.b
        public void a() {
        }

        @Override // io.flutter.embedding.engine.a.b
        public void b() {
            z5.b.f("FlutterEngine", "onPreEngineRestart()");
            for (b bVar : a.this.f7734u) {
                bVar.b();
            }
            a.this.f7733t.m0();
            a.this.f7726m.g();
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a();

        void b();
    }

    public a(Context context, c6.d dVar, FlutterJNI flutterJNI, io.flutter.plugin.platform.w wVar, String[] strArr, boolean z7, boolean z8) {
        this(context, dVar, flutterJNI, wVar, strArr, z7, z8, null);
    }

    private void f() {
        z5.b.f("FlutterEngine", "Attaching to JNI.");
        this.f7714a.attachToNative();
        if (!z()) {
            throw new RuntimeException("FlutterEngine failed to attach to its native Object reference.");
        }
    }

    private boolean z() {
        return this.f7714a.isAttached();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a A(Context context, a.b bVar, String str, List list, io.flutter.plugin.platform.w wVar, boolean z7, boolean z8) {
        if (z()) {
            return new a(context, null, this.f7714a.spawn(bVar.f1364c, bVar.f1363b, str, list), wVar, null, z7, z8);
        }
        throw new IllegalStateException("Spawn can only be called on a fully constructed FlutterEngine");
    }

    @Override // p6.h.a
    public void a(float f8, float f9, float f10) {
        this.f7714a.updateDisplayMetrics(0, f8, f9, f10);
    }

    public void e(b bVar) {
        this.f7734u.add(bVar);
    }

    public void g() {
        z5.b.f("FlutterEngine", "Destroying.");
        for (b bVar : this.f7734u) {
            bVar.a();
        }
        this.f7717d.k();
        this.f7733t.i0();
        this.f7716c.m();
        this.f7714a.removeEngineLifecycleListener(this.f7735v);
        this.f7714a.setDeferredComponentManager(null);
        this.f7714a.detachFromNativeAndReleaseResources();
        z5.a.e().a();
    }

    public h6.a h() {
        return this.f7719f;
    }

    public f6.b i() {
        return this.f7717d;
    }

    public h6.f j() {
        return this.f7725l;
    }

    public a6.a k() {
        return this.f7716c;
    }

    public h6.k l() {
        return this.f7721h;
    }

    public j6.a m() {
        return this.f7718e;
    }

    public m n() {
        return this.f7723j;
    }

    public n o() {
        return this.f7724k;
    }

    public o p() {
        return this.f7727n;
    }

    public io.flutter.plugin.platform.w q() {
        return this.f7733t;
    }

    public e6.b r() {
        return this.f7717d;
    }

    public r s() {
        return this.f7728o;
    }

    public FlutterRenderer t() {
        return this.f7715b;
    }

    public s u() {
        return this.f7726m;
    }

    public t v() {
        return this.f7729p;
    }

    public u w() {
        return this.f7730q;
    }

    public v x() {
        return this.f7731r;
    }

    public w y() {
        return this.f7732s;
    }

    public a(Context context, c6.d dVar, FlutterJNI flutterJNI, io.flutter.plugin.platform.w wVar, String[] strArr, boolean z7, boolean z8, d dVar2) {
        AssetManager assets;
        this.f7734u = new HashSet();
        this.f7735v = new C0106a();
        try {
            assets = context.createPackageContext(context.getPackageName(), 0).getAssets();
        } catch (PackageManager.NameNotFoundException unused) {
            assets = context.getAssets();
        }
        z5.a e8 = z5.a.e();
        flutterJNI = flutterJNI == null ? e8.d().a() : flutterJNI;
        this.f7714a = flutterJNI;
        a6.a aVar = new a6.a(flutterJNI, assets);
        this.f7716c = aVar;
        aVar.l();
        z5.a.e().a();
        this.f7719f = new h6.a(aVar, flutterJNI);
        this.f7720g = new h6.g(aVar);
        this.f7721h = new h6.k(aVar);
        h6.l lVar = new h6.l(aVar);
        this.f7722i = lVar;
        this.f7723j = new m(aVar);
        this.f7724k = new n(aVar);
        this.f7725l = new h6.f(aVar);
        this.f7727n = new o(aVar);
        this.f7728o = new r(aVar, context.getPackageManager());
        this.f7726m = new s(aVar, z8);
        this.f7729p = new t(aVar);
        this.f7730q = new u(aVar);
        this.f7731r = new v(aVar);
        this.f7732s = new w(aVar);
        j6.a aVar2 = new j6.a(context, lVar);
        this.f7718e = aVar2;
        dVar = dVar == null ? e8.c() : dVar;
        if (!flutterJNI.isAttached()) {
            dVar.m(context.getApplicationContext());
            dVar.f(context, strArr);
        }
        flutterJNI.addEngineLifecycleListener(this.f7735v);
        flutterJNI.setPlatformViewsController(wVar);
        flutterJNI.setLocalizationPlugin(aVar2);
        e8.a();
        flutterJNI.setDeferredComponentManager(null);
        if (!flutterJNI.isAttached()) {
            f();
        }
        this.f7715b = new FlutterRenderer(flutterJNI);
        this.f7733t = wVar;
        wVar.g0();
        c cVar = new c(context.getApplicationContext(), this, dVar, dVar2);
        this.f7717d = cVar;
        aVar2.d(context.getResources().getConfiguration());
        if (z7 && dVar.e()) {
            g6.a.a(this);
        }
        p6.h.c(context, this);
        cVar.e(new l6.c(s()));
    }
}
