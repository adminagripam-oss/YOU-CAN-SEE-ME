package com.baseflow.geolocator;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.baseflow.geolocator.GeolocatorLocationService;
import e6.a;
/* loaded from: classes.dex */
public class a implements e6.a, f6.a {

    /* renamed from: d  reason: collision with root package name */
    private GeolocatorLocationService f4874d;

    /* renamed from: e  reason: collision with root package name */
    private j f4875e;

    /* renamed from: f  reason: collision with root package name */
    private m f4876f;

    /* renamed from: h  reason: collision with root package name */
    private b f4878h;

    /* renamed from: i  reason: collision with root package name */
    private f6.c f4879i;

    /* renamed from: g  reason: collision with root package name */
    private final ServiceConnection f4877g = new ServiceConnectionC0063a();

    /* renamed from: a  reason: collision with root package name */
    private final q2.b f4871a = q2.b.c();

    /* renamed from: b  reason: collision with root package name */
    private final p2.k f4872b = p2.k.b();

    /* renamed from: c  reason: collision with root package name */
    private final p2.m f4873c = p2.m.a();

    /* renamed from: com.baseflow.geolocator.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class ServiceConnectionC0063a implements ServiceConnection {
        ServiceConnectionC0063a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            z5.b.a("FlutterGeolocator", "Geolocator foreground service connected");
            if (iBinder instanceof GeolocatorLocationService.a) {
                a.this.g(((GeolocatorLocationService.a) iBinder).a());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            z5.b.a("FlutterGeolocator", "Geolocator foreground service disconnected");
            if (a.this.f4874d != null) {
                a.this.f4874d.n(null);
                a.this.f4874d = null;
            }
        }
    }

    private void d(Context context) {
        context.bindService(new Intent(context, GeolocatorLocationService.class), this.f4877g, 1);
    }

    private void e() {
        f6.c cVar = this.f4879i;
        if (cVar != null) {
            cVar.c(this.f4872b);
            this.f4879i.d(this.f4871a);
        }
    }

    private void f() {
        z5.b.a("FlutterGeolocator", "Disposing Geolocator services");
        j jVar = this.f4875e;
        if (jVar != null) {
            jVar.x();
            this.f4875e.v(null);
            this.f4875e = null;
        }
        m mVar = this.f4876f;
        if (mVar != null) {
            mVar.k();
            this.f4876f.i(null);
            this.f4876f = null;
        }
        b bVar = this.f4878h;
        if (bVar != null) {
            bVar.d(null);
            this.f4878h.f();
            this.f4878h = null;
        }
        GeolocatorLocationService geolocatorLocationService = this.f4874d;
        if (geolocatorLocationService != null) {
            geolocatorLocationService.n(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(GeolocatorLocationService geolocatorLocationService) {
        z5.b.a("FlutterGeolocator", "Initializing Geolocator services");
        this.f4874d = geolocatorLocationService;
        geolocatorLocationService.o(this.f4872b);
        this.f4874d.g();
        m mVar = this.f4876f;
        if (mVar != null) {
            mVar.i(geolocatorLocationService);
        }
    }

    private void h() {
        f6.c cVar = this.f4879i;
        if (cVar != null) {
            cVar.a(this.f4872b);
            this.f4879i.b(this.f4871a);
        }
    }

    private void i(Context context) {
        GeolocatorLocationService geolocatorLocationService = this.f4874d;
        if (geolocatorLocationService != null) {
            geolocatorLocationService.h();
        }
        context.unbindService(this.f4877g);
    }

    @Override // f6.a
    public void onAttachedToActivity(f6.c cVar) {
        z5.b.a("FlutterGeolocator", "Attaching Geolocator to activity");
        this.f4879i = cVar;
        h();
        j jVar = this.f4875e;
        if (jVar != null) {
            jVar.v(cVar.getActivity());
        }
        m mVar = this.f4876f;
        if (mVar != null) {
            mVar.h(cVar.getActivity());
        }
        GeolocatorLocationService geolocatorLocationService = this.f4874d;
        if (geolocatorLocationService != null) {
            geolocatorLocationService.n(this.f4879i.getActivity());
        }
    }

    @Override // e6.a
    public void onAttachedToEngine(a.b bVar) {
        j jVar = new j(this.f4871a, this.f4872b, this.f4873c);
        this.f4875e = jVar;
        jVar.w(bVar.a(), bVar.b());
        m mVar = new m(this.f4871a, this.f4872b);
        this.f4876f = mVar;
        mVar.j(bVar.a(), bVar.b());
        b bVar2 = new b();
        this.f4878h = bVar2;
        bVar2.d(bVar.a());
        this.f4878h.e(bVar.a(), bVar.b());
        d(bVar.a());
    }

    @Override // f6.a
    public void onDetachedFromActivity() {
        z5.b.a("FlutterGeolocator", "Detaching Geolocator from activity");
        e();
        j jVar = this.f4875e;
        if (jVar != null) {
            jVar.v(null);
        }
        m mVar = this.f4876f;
        if (mVar != null) {
            mVar.h(null);
        }
        GeolocatorLocationService geolocatorLocationService = this.f4874d;
        if (geolocatorLocationService != null) {
            geolocatorLocationService.n(null);
        }
        if (this.f4879i != null) {
            this.f4879i = null;
        }
    }

    @Override // f6.a
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override // e6.a
    public void onDetachedFromEngine(a.b bVar) {
        i(bVar.a());
        f();
    }

    @Override // f6.a
    public void onReattachedToActivityForConfigChanges(f6.c cVar) {
        onAttachedToActivity(cVar);
    }
}
