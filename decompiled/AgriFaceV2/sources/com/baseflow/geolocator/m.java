package com.baseflow.geolocator;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import i6.c;
import java.util.Map;
import p2.b0;
import p2.o;
import p2.v;
import p2.w;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class m implements c.d {

    /* renamed from: a  reason: collision with root package name */
    private final q2.b f4908a;

    /* renamed from: b  reason: collision with root package name */
    private i6.c f4909b;

    /* renamed from: c  reason: collision with root package name */
    private Context f4910c;

    /* renamed from: d  reason: collision with root package name */
    private Activity f4911d;

    /* renamed from: e  reason: collision with root package name */
    private GeolocatorLocationService f4912e;

    /* renamed from: f  reason: collision with root package name */
    private p2.k f4913f;

    /* renamed from: g  reason: collision with root package name */
    private o f4914g;

    public m(q2.b bVar, p2.k kVar) {
        this.f4908a = bVar;
        this.f4913f = kVar;
    }

    private void e(boolean z7) {
        p2.k kVar;
        Log.e("FlutterGeolocator", "Geolocator position updates stopped");
        GeolocatorLocationService geolocatorLocationService = this.f4912e;
        if (geolocatorLocationService == null || !geolocatorLocationService.c(z7)) {
            Log.e("FlutterGeolocator", "There is still another flutter engine connected, not stopping location service");
        } else {
            this.f4912e.q();
            this.f4912e.e();
        }
        o oVar = this.f4914g;
        if (oVar == null || (kVar = this.f4913f) == null) {
            return;
        }
        kVar.g(oVar);
        this.f4914g = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f(c.b bVar, Location location) {
        bVar.success(v.b(location));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(c.b bVar, o2.b bVar2) {
        bVar.error(bVar2.toString(), bVar2.e(), null);
    }

    @Override // i6.c.d
    public void a(Object obj) {
        e(true);
    }

    @Override // i6.c.d
    public void b(Object obj, final c.b bVar) {
        try {
            if (!this.f4908a.f(this.f4910c)) {
                o2.b bVar2 = o2.b.permissionDenied;
                bVar.error(bVar2.toString(), bVar2.e(), null);
            } else if (this.f4912e == null) {
                Log.e("FlutterGeolocator", "Location background service has not started correctly");
            } else {
                Map map = (Map) obj;
                boolean booleanValue = (map == null || map.get("forceLocationManager") == null) ? false : ((Boolean) map.get("forceLocationManager")).booleanValue();
                w e8 = w.e(map);
                p2.d i8 = map != null ? p2.d.i((Map) map.get("foregroundNotificationConfig")) : null;
                if (i8 != null) {
                    Log.e("FlutterGeolocator", "Geolocator position updates started using Android foreground service");
                    this.f4912e.p(booleanValue, e8, bVar);
                    this.f4912e.f(i8);
                    return;
                }
                Log.e("FlutterGeolocator", "Geolocator position updates started");
                o a8 = this.f4913f.a(this.f4910c, Boolean.TRUE.equals(Boolean.valueOf(booleanValue)), e8);
                this.f4914g = a8;
                this.f4913f.f(a8, this.f4911d, new b0() { // from class: com.baseflow.geolocator.k
                    @Override // p2.b0
                    public final void a(Location location) {
                        m.f(c.b.this, location);
                    }
                }, new o2.a() { // from class: com.baseflow.geolocator.l
                    @Override // o2.a
                    public final void a(o2.b bVar3) {
                        m.g(c.b.this, bVar3);
                    }
                });
            }
        } catch (o2.c unused) {
            o2.b bVar3 = o2.b.permissionDefinitionsNotFound;
            bVar.error(bVar3.toString(), bVar3.e(), null);
        }
    }

    public void h(Activity activity) {
        if (activity == null && this.f4914g != null && this.f4909b != null) {
            k();
        }
        this.f4911d = activity;
    }

    public void i(GeolocatorLocationService geolocatorLocationService) {
        this.f4912e = geolocatorLocationService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(Context context, i6.b bVar) {
        if (this.f4909b != null) {
            Log.w("FlutterGeolocator", "Setting a event call handler before the last was disposed.");
            k();
        }
        i6.c cVar = new i6.c(bVar, "flutter.baseflow.com/geolocator_updates_android");
        this.f4909b = cVar;
        cVar.d(this);
        this.f4910c = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        if (this.f4909b == null) {
            Log.d("FlutterGeolocator", "Tried to stop listening when no MethodChannel had been initialized.");
            return;
        }
        e(false);
        this.f4909b.d(null);
        this.f4909b = null;
    }
}
