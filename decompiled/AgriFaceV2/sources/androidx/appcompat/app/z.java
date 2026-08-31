package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;
/* loaded from: classes.dex */
class z {

    /* renamed from: d  reason: collision with root package name */
    private static z f1706d;

    /* renamed from: a  reason: collision with root package name */
    private final Context f1707a;

    /* renamed from: b  reason: collision with root package name */
    private final LocationManager f1708b;

    /* renamed from: c  reason: collision with root package name */
    private final a f1709c = new a();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        boolean f1710a;

        /* renamed from: b  reason: collision with root package name */
        long f1711b;

        a() {
        }
    }

    z(Context context, LocationManager locationManager) {
        this.f1707a = context;
        this.f1708b = locationManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static z a(Context context) {
        if (f1706d == null) {
            Context applicationContext = context.getApplicationContext();
            f1706d = new z(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f1706d;
    }

    private Location b() {
        Location c8 = androidx.core.content.e.b(this.f1707a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location c9 = androidx.core.content.e.b(this.f1707a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (c9 == null || c8 == null) ? c9 != null ? c9 : c8 : c9.getTime() > c8.getTime() ? c9 : c8;
    }

    private Location c(String str) {
        try {
            if (this.f1708b.isProviderEnabled(str)) {
                return this.f1708b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e8) {
            Log.d("TwilightManager", "Failed to get last known location", e8);
            return null;
        }
    }

    private boolean e() {
        return this.f1709c.f1711b > System.currentTimeMillis();
    }

    private void f(Location location) {
        long j8;
        a aVar = this.f1709c;
        long currentTimeMillis = System.currentTimeMillis();
        y b8 = y.b();
        b8.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        b8.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z7 = b8.f1705c == 1;
        long j9 = b8.f1704b;
        long j10 = b8.f1703a;
        b8.a(currentTimeMillis + 86400000, location.getLatitude(), location.getLongitude());
        long j11 = b8.f1704b;
        if (j9 == -1 || j10 == -1) {
            j8 = 43200000 + currentTimeMillis;
        } else {
            j8 = (currentTimeMillis > j10 ? j11 + 0 : currentTimeMillis > j9 ? j10 + 0 : j9 + 0) + 60000;
        }
        aVar.f1710a = z7;
        aVar.f1711b = j8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        a aVar = this.f1709c;
        if (e()) {
            return aVar.f1710a;
        }
        Location b8 = b();
        if (b8 != null) {
            f(b8);
            return aVar.f1710a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i8 = Calendar.getInstance().get(11);
        return i8 < 6 || i8 >= 22;
    }
}
