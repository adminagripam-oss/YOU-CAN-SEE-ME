package p2;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import androidx.core.location.e;
import java.util.List;
/* loaded from: classes.dex */
class p implements o, androidx.core.location.b {

    /* renamed from: a  reason: collision with root package name */
    private final LocationManager f10071a;

    /* renamed from: b  reason: collision with root package name */
    private final a0 f10072b;

    /* renamed from: c  reason: collision with root package name */
    private final w f10073c;

    /* renamed from: d  reason: collision with root package name */
    public Context f10074d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f10075e = false;

    /* renamed from: f  reason: collision with root package name */
    private Location f10076f;

    /* renamed from: g  reason: collision with root package name */
    private String f10077g;

    /* renamed from: h  reason: collision with root package name */
    private b0 f10078h;

    /* renamed from: i  reason: collision with root package name */
    private o2.a f10079i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10080a;

        static {
            int[] iArr = new int[l.values().length];
            f10080a = iArr;
            try {
                iArr[l.lowest.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10080a[l.low.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10080a[l.high.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10080a[l.best.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10080a[l.bestForNavigation.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10080a[l.medium.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public p(Context context, w wVar) {
        this.f10071a = (LocationManager) context.getSystemService("location");
        this.f10073c = wVar;
        this.f10074d = context;
        this.f10072b = new a0(context, wVar);
    }

    private static int g(l lVar) {
        int i8 = a.f10080a[lVar.ordinal()];
        if (i8 == 1 || i8 == 2) {
            return 104;
        }
        return (i8 == 3 || i8 == 4 || i8 == 5) ? 100 : 102;
    }

    private static String h(LocationManager locationManager, l lVar) {
        List<String> providers = locationManager.getProviders(true);
        if (lVar == l.lowest) {
            return "passive";
        }
        if (!providers.contains("fused") || Build.VERSION.SDK_INT < 31) {
            if (providers.contains("gps")) {
                return "gps";
            }
            if (providers.contains("network")) {
                return "network";
            }
            if (providers.isEmpty()) {
                return null;
            }
            return providers.get(0);
        }
        return "fused";
    }

    static boolean i(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z7 = time > 120000;
        boolean z8 = time < -120000;
        boolean z9 = time > 0;
        if (z7) {
            return true;
        }
        if (z8) {
            return false;
        }
        float accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z10 = accuracy > 0.0f;
        boolean z11 = accuracy < 0.0f;
        boolean z12 = accuracy > 200.0f;
        boolean equals = location.getProvider() != null ? location.getProvider().equals(location2.getProvider()) : false;
        if (z11) {
            return true;
        }
        if (!z9 || z10) {
            return z9 && !z12 && equals;
        }
        return true;
    }

    @Override // p2.o
    public boolean a(int i8, int i9) {
        return false;
    }

    @Override // p2.o
    public void b(x xVar) {
        xVar.b(this.f10071a == null ? false : c(this.f10074d));
    }

    @Override // p2.o
    public void d(b0 b0Var, o2.a aVar) {
        Location location = null;
        for (String str : this.f10071a.getProviders(true)) {
            Location lastKnownLocation = this.f10071a.getLastKnownLocation(str);
            if (lastKnownLocation != null && i(lastKnownLocation, location)) {
                location = lastKnownLocation;
            }
        }
        b0Var.a(location);
    }

    @Override // p2.o
    public void e(Activity activity, b0 b0Var, o2.a aVar) {
        long j8;
        float f8;
        int i8;
        if (!c(this.f10074d)) {
            aVar.a(o2.b.locationServicesDisabled);
            return;
        }
        this.f10078h = b0Var;
        this.f10079i = aVar;
        l lVar = l.best;
        w wVar = this.f10073c;
        if (wVar != null) {
            float b8 = (float) wVar.b();
            l a8 = this.f10073c.a();
            j8 = a8 == l.lowest ? Long.MAX_VALUE : this.f10073c.c();
            i8 = g(a8);
            f8 = b8;
            lVar = a8;
        } else {
            j8 = 0;
            f8 = 0.0f;
            i8 = 102;
        }
        String h8 = h(this.f10071a, lVar);
        this.f10077g = h8;
        if (h8 == null) {
            aVar.a(o2.b.locationServicesDisabled);
            return;
        }
        androidx.core.location.e a9 = new e.c(j8).c(f8).d(j8).e(i8).a();
        this.f10075e = true;
        this.f10072b.h();
        androidx.core.location.c.b(this.f10071a, this.f10077g, a9, this, Looper.getMainLooper());
    }

    @Override // p2.o
    public void f() {
        this.f10075e = false;
        this.f10072b.i();
        this.f10071a.removeUpdates(this);
    }

    @Override // android.location.LocationListener
    public synchronized void onLocationChanged(Location location) {
        if (i(location, this.f10076f)) {
            this.f10076f = location;
            if (this.f10078h != null) {
                this.f10072b.f(location);
                this.f10078h.a(this.f10076f);
            }
        }
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        if (str.equals(this.f10077g)) {
            if (this.f10075e) {
                this.f10071a.removeUpdates(this);
            }
            o2.a aVar = this.f10079i;
            if (aVar != null) {
                aVar.a(o2.b.locationServicesDisabled);
            }
            this.f10077g = null;
        }
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i8, Bundle bundle) {
        if (i8 == 2) {
            onProviderEnabled(str);
        } else if (i8 == 0) {
            onProviderDisabled(str);
        }
    }
}
