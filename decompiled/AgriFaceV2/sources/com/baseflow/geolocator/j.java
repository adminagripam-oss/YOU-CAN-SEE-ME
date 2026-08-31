package com.baseflow.geolocator;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import i6.j;
import java.util.HashMap;
import java.util.Map;
import p2.b0;
import p2.n;
import p2.o;
import p2.v;
import p2.w;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j implements j.c {

    /* renamed from: a  reason: collision with root package name */
    private final q2.b f4899a;

    /* renamed from: b  reason: collision with root package name */
    private final p2.k f4900b;

    /* renamed from: c  reason: collision with root package name */
    private final p2.m f4901c;

    /* renamed from: d  reason: collision with root package name */
    final Map f4902d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private Context f4903e;

    /* renamed from: f  reason: collision with root package name */
    private Activity f4904f;

    /* renamed from: g  reason: collision with root package name */
    private i6.j f4905g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(q2.b bVar, p2.k kVar, p2.m mVar) {
        this.f4899a = bVar;
        this.f4900b = kVar;
        this.f4901c = mVar;
    }

    private void h(final j.d dVar, Context context) {
        n b8 = this.f4901c.b(context, new o2.a() { // from class: com.baseflow.geolocator.e
            @Override // o2.a
            public final void a(o2.b bVar) {
                j.i(j.d.this, bVar);
            }
        });
        if (b8 != null) {
            dVar.success(Integer.valueOf(b8.ordinal()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void i(j.d dVar, o2.b bVar) {
        dVar.error(bVar.toString(), bVar.e(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(boolean[] zArr, o oVar, String str, j.d dVar, Location location) {
        if (zArr[0]) {
            return;
        }
        zArr[0] = true;
        this.f4900b.g(oVar);
        this.f4902d.remove(str);
        dVar.success(v.b(location));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(boolean[] zArr, o oVar, String str, j.d dVar, o2.b bVar) {
        if (zArr[0]) {
            return;
        }
        zArr[0] = true;
        this.f4900b.g(oVar);
        this.f4902d.remove(str);
        dVar.error(bVar.toString(), bVar.e(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void l(j.d dVar, Location location) {
        dVar.success(v.b(location));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void m(j.d dVar, o2.b bVar) {
        dVar.error(bVar.toString(), bVar.e(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void n(j.d dVar, q2.a aVar) {
        dVar.success(Integer.valueOf(aVar.e()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void o(j.d dVar, o2.b bVar) {
        dVar.error(bVar.toString(), bVar.e(), null);
    }

    private void p(i6.i iVar, j.d dVar) {
        String str = (String) ((Map) iVar.f7536b).get("requestId");
        o oVar = (o) this.f4902d.get(str);
        if (oVar != null) {
            oVar.f();
        }
        this.f4902d.remove(str);
        dVar.success(null);
    }

    private void q(j.d dVar) {
        try {
            dVar.success(Integer.valueOf(this.f4899a.b(this.f4903e).e()));
        } catch (o2.c unused) {
            o2.b bVar = o2.b.permissionDefinitionsNotFound;
            dVar.error(bVar.toString(), bVar.e(), null);
        }
    }

    private void r(i6.i iVar, final j.d dVar) {
        try {
            if (!this.f4899a.f(this.f4903e)) {
                o2.b bVar = o2.b.permissionDenied;
                dVar.error(bVar.toString(), bVar.e(), null);
                return;
            }
            Map map = (Map) iVar.f7536b;
            boolean booleanValue = map.get("forceLocationManager") != null ? ((Boolean) map.get("forceLocationManager")).booleanValue() : false;
            w e8 = w.e(map);
            final String str = (String) map.get("requestId");
            final boolean[] zArr = {false};
            final o a8 = this.f4900b.a(this.f4903e, booleanValue, e8);
            this.f4902d.put(str, a8);
            this.f4900b.f(a8, this.f4904f, new b0() { // from class: com.baseflow.geolocator.h
                @Override // p2.b0
                public final void a(Location location) {
                    j.this.j(zArr, a8, str, dVar, location);
                }
            }, new o2.a() { // from class: com.baseflow.geolocator.i
                @Override // o2.a
                public final void a(o2.b bVar2) {
                    j.this.k(zArr, a8, str, dVar, bVar2);
                }
            });
        } catch (o2.c unused) {
            o2.b bVar2 = o2.b.permissionDefinitionsNotFound;
            dVar.error(bVar2.toString(), bVar2.e(), null);
        }
    }

    private void s(i6.i iVar, final j.d dVar) {
        try {
            if (this.f4899a.f(this.f4903e)) {
                Boolean bool = (Boolean) iVar.a("forceLocationManager");
                this.f4900b.c(this.f4903e, bool != null && bool.booleanValue(), new b0() { // from class: com.baseflow.geolocator.c
                    @Override // p2.b0
                    public final void a(Location location) {
                        j.l(j.d.this, location);
                    }
                }, new o2.a() { // from class: com.baseflow.geolocator.d
                    @Override // o2.a
                    public final void a(o2.b bVar) {
                        j.m(j.d.this, bVar);
                    }
                });
                return;
            }
            o2.b bVar = o2.b.permissionDenied;
            dVar.error(bVar.toString(), bVar.e(), null);
        } catch (o2.c unused) {
            o2.b bVar2 = o2.b.permissionDefinitionsNotFound;
            dVar.error(bVar2.toString(), bVar2.e(), null);
        }
    }

    private void t(j.d dVar) {
        this.f4900b.e(this.f4903e, new p2.c(dVar));
    }

    private void u(final j.d dVar) {
        try {
            this.f4899a.h(this.f4904f, new q2.c() { // from class: com.baseflow.geolocator.f
                @Override // q2.c
                public final void a(q2.a aVar) {
                    j.n(j.d.this, aVar);
                }
            }, new o2.a() { // from class: com.baseflow.geolocator.g
                @Override // o2.a
                public final void a(o2.b bVar) {
                    j.o(j.d.this, bVar);
                }
            });
        } catch (o2.c unused) {
            o2.b bVar = o2.b.permissionDefinitionsNotFound;
            dVar.error(bVar.toString(), bVar.e(), null);
        }
    }

    @Override // i6.j.c
    public void onMethodCall(i6.i iVar, j.d dVar) {
        boolean b8;
        String str = iVar.f7535a;
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1757019252:
                if (str.equals("getCurrentPosition")) {
                    c8 = 0;
                    break;
                }
                break;
            case -1156770336:
                if (str.equals("getLastKnownPosition")) {
                    c8 = 1;
                    break;
                }
                break;
            case -821636766:
                if (str.equals("openLocationSettings")) {
                    c8 = 2;
                    break;
                }
                break;
            case 347240634:
                if (str.equals("openAppSettings")) {
                    c8 = 3;
                    break;
                }
                break;
            case 356040619:
                if (str.equals("isLocationServiceEnabled")) {
                    c8 = 4;
                    break;
                }
                break;
            case 686218487:
                if (str.equals("checkPermission")) {
                    c8 = 5;
                    break;
                }
                break;
            case 746581438:
                if (str.equals("requestPermission")) {
                    c8 = 6;
                    break;
                }
                break;
            case 877043524:
                if (str.equals("getLocationAccuracy")) {
                    c8 = 7;
                    break;
                }
                break;
            case 1774650278:
                if (str.equals("cancelGetCurrentPosition")) {
                    c8 = '\b';
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                r(iVar, dVar);
                return;
            case 1:
                s(iVar, dVar);
                return;
            case 2:
                b8 = r2.a.b(this.f4903e);
                break;
            case 3:
                b8 = r2.a.a(this.f4903e);
                break;
            case 4:
                t(dVar);
                return;
            case 5:
                q(dVar);
                return;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                u(dVar);
                return;
            case 7:
                h(dVar, this.f4903e);
                return;
            case '\b':
                p(iVar, dVar);
                return;
            default:
                dVar.notImplemented();
                return;
        }
        dVar.success(Boolean.valueOf(b8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(Activity activity) {
        this.f4904f = activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(Context context, i6.b bVar) {
        if (this.f4905g != null) {
            Log.w("MethodCallHandlerImpl", "Setting a method call handler before the last was disposed.");
            x();
        }
        i6.j jVar = new i6.j(bVar, "flutter.baseflow.com/geolocator_android");
        this.f4905g = jVar;
        jVar.e(this);
        this.f4903e = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x() {
        i6.j jVar = this.f4905g;
        if (jVar == null) {
            Log.d("MethodCallHandlerImpl", "Tried to stop listening when no MethodChannel had been initialized.");
            return;
        }
        jVar.e(null);
        this.f4905g = null;
    }
}
