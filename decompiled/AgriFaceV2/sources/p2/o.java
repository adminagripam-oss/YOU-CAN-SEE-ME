package p2;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
/* loaded from: classes.dex */
public interface o {
    boolean a(int i8, int i9);

    void b(x xVar);

    default boolean c(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    void d(b0 b0Var, o2.a aVar);

    void e(Activity activity, b0 b0Var, o2.a aVar);

    void f();
}
