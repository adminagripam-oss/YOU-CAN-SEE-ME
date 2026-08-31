package com.baseflow.geolocator;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import com.baseflow.geolocator.GeolocatorLocationService;
import i6.c;
import p2.b0;
import p2.o;
import p2.v;
import p2.w;
/* loaded from: classes.dex */
public class GeolocatorLocationService extends Service {

    /* renamed from: i  reason: collision with root package name */
    private o f4865i;

    /* renamed from: a  reason: collision with root package name */
    private final String f4857a = "GeolocatorLocationService:Wakelock";

    /* renamed from: b  reason: collision with root package name */
    private final String f4858b = "GeolocatorLocationService:WifiLock";

    /* renamed from: c  reason: collision with root package name */
    private final a f4859c = new a(this);

    /* renamed from: d  reason: collision with root package name */
    private boolean f4860d = false;

    /* renamed from: e  reason: collision with root package name */
    private int f4861e = 0;

    /* renamed from: f  reason: collision with root package name */
    private int f4862f = 0;

    /* renamed from: g  reason: collision with root package name */
    private Activity f4863g = null;

    /* renamed from: h  reason: collision with root package name */
    private p2.k f4864h = null;

    /* renamed from: j  reason: collision with root package name */
    private PowerManager.WakeLock f4866j = null;

    /* renamed from: k  reason: collision with root package name */
    private WifiManager.WifiLock f4867k = null;

    /* renamed from: l  reason: collision with root package name */
    private p2.b f4868l = null;

    /* loaded from: classes.dex */
    class a extends Binder {

        /* renamed from: e  reason: collision with root package name */
        private final GeolocatorLocationService f4869e;

        a(GeolocatorLocationService geolocatorLocationService) {
            this.f4869e = geolocatorLocationService;
        }

        public GeolocatorLocationService a() {
            return this.f4869e;
        }
    }

    private int i() {
        return Build.VERSION.SDK_INT < 29 ? 3 : 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void j(c.b bVar, Location location) {
        bVar.success(v.b(location));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void k(c.b bVar, o2.b bVar2) {
        bVar.error(bVar2.toString(), bVar2.e(), null);
    }

    private void l(p2.d dVar) {
        WifiManager wifiManager;
        PowerManager powerManager;
        m();
        if (dVar.f() && (powerManager = (PowerManager) getApplicationContext().getSystemService("power")) != null) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, "GeolocatorLocationService:Wakelock");
            this.f4866j = newWakeLock;
            newWakeLock.setReferenceCounted(false);
            this.f4866j.acquire();
        }
        if (!dVar.g() || (wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi")) == null) {
            return;
        }
        WifiManager.WifiLock createWifiLock = wifiManager.createWifiLock(i(), "GeolocatorLocationService:WifiLock");
        this.f4867k = createWifiLock;
        createWifiLock.setReferenceCounted(false);
        this.f4867k.acquire();
    }

    private void m() {
        PowerManager.WakeLock wakeLock = this.f4866j;
        if (wakeLock != null && wakeLock.isHeld()) {
            this.f4866j.release();
            this.f4866j = null;
        }
        WifiManager.WifiLock wifiLock = this.f4867k;
        if (wifiLock == null || !wifiLock.isHeld()) {
            return;
        }
        this.f4867k.release();
        this.f4867k = null;
    }

    public boolean c(boolean z7) {
        return z7 ? this.f4862f == 1 : this.f4861e == 0;
    }

    public void d(p2.d dVar) {
        p2.b bVar = this.f4868l;
        if (bVar != null) {
            bVar.f(dVar, this.f4860d);
            l(dVar);
        }
    }

    public void e() {
        if (this.f4860d) {
            Log.d("FlutterGeolocator", "Stop service in foreground.");
            stopForeground(1);
            m();
            this.f4860d = false;
            this.f4868l = null;
        }
    }

    public void f(p2.d dVar) {
        if (this.f4868l != null) {
            Log.d("FlutterGeolocator", "Service already in foreground mode.");
            d(dVar);
        } else {
            Log.d("FlutterGeolocator", "Start service in foreground mode.");
            p2.b bVar = new p2.b(getApplicationContext(), "geolocator_channel_01", 75415, dVar);
            this.f4868l = bVar;
            bVar.d(dVar.b());
            startForeground(75415, this.f4868l.a());
            this.f4860d = true;
        }
        l(dVar);
    }

    public void g() {
        this.f4861e++;
        Log.d("FlutterGeolocator", "Flutter engine connected. Connected engine count " + this.f4861e);
    }

    public void h() {
        this.f4861e--;
        Log.d("FlutterGeolocator", "Flutter engine disconnected. Connected engine count " + this.f4861e);
    }

    public void n(Activity activity) {
        this.f4863g = activity;
    }

    public void o(p2.k kVar) {
        this.f4864h = kVar;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d("FlutterGeolocator", "Binding to location service.");
        return this.f4859c;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("FlutterGeolocator", "Creating service.");
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d("FlutterGeolocator", "Destroying location service.");
        q();
        e();
        this.f4864h = null;
        this.f4868l = null;
        Log.d("FlutterGeolocator", "Destroyed location service.");
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i8, int i9) {
        return 1;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.d("FlutterGeolocator", "Unbinding from location service.");
        return super.onUnbind(intent);
    }

    public void p(boolean z7, w wVar, final c.b bVar) {
        this.f4862f++;
        p2.k kVar = this.f4864h;
        if (kVar != null) {
            o a8 = kVar.a(getApplicationContext(), Boolean.TRUE.equals(Boolean.valueOf(z7)), wVar);
            this.f4865i = a8;
            this.f4864h.f(a8, this.f4863g, new b0() { // from class: n2.a
                @Override // p2.b0
                public final void a(Location location) {
                    GeolocatorLocationService.j(c.b.this, location);
                }
            }, new o2.a() { // from class: n2.b
                @Override // o2.a
                public final void a(o2.b bVar2) {
                    GeolocatorLocationService.k(c.b.this, bVar2);
                }
            });
        }
    }

    public void q() {
        p2.k kVar;
        this.f4862f--;
        Log.d("FlutterGeolocator", "Stopping location service.");
        o oVar = this.f4865i;
        if (oVar == null || (kVar = this.f4864h) == null) {
            return;
        }
        kVar.g(oVar);
    }
}
