package androidx.core.location;

import android.location.GnssMeasurementsEvent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.core.os.f;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    static final WeakHashMap f3015a = new WeakHashMap();

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        private static Class f3016a;

        /* renamed from: b  reason: collision with root package name */
        private static Method f3017b;

        static boolean a(LocationManager locationManager, String str, e eVar, androidx.core.location.b bVar, Looper looper) {
            try {
                if (f3016a == null) {
                    f3016a = Class.forName("android.location.LocationRequest");
                }
                if (f3017b == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", f3016a, LocationListener.class, Looper.class);
                    f3017b = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequest i8 = eVar.i(str);
                if (i8 != null) {
                    f3017b.invoke(locationManager, i8, bVar, looper);
                    return true;
                }
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
            }
            return false;
        }

        static boolean b(LocationManager locationManager, String str, e eVar, C0027c c0027c) {
            try {
                if (f3016a == null) {
                    f3016a = Class.forName("android.location.LocationRequest");
                }
                if (f3017b == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", f3016a, LocationListener.class, Looper.class);
                    f3017b = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequest i8 = eVar.i(str);
                if (i8 != null) {
                    synchronized (c.f3015a) {
                        f3017b.invoke(locationManager, i8, c0027c, Looper.getMainLooper());
                        c.a(locationManager, c0027c);
                    }
                    return true;
                }
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    private static class b {
        static boolean a(LocationManager locationManager, String str) {
            return locationManager.hasProvider(str);
        }

        static boolean b(LocationManager locationManager, Executor executor, GnssMeasurementsEvent.Callback callback) {
            return locationManager.registerGnssMeasurementsCallback(executor, callback);
        }

        static void c(LocationManager locationManager, String str, LocationRequest locationRequest, Executor executor, LocationListener locationListener) {
            locationManager.requestLocationUpdates(str, locationRequest, executor, locationListener);
        }
    }

    /* renamed from: androidx.core.location.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0027c implements LocationListener {
    }

    static void a(LocationManager locationManager, C0027c c0027c) {
        throw null;
    }

    public static void b(LocationManager locationManager, String str, e eVar, androidx.core.location.b bVar, Looper looper) {
        if (Build.VERSION.SDK_INT >= 31) {
            b.c(locationManager, str, eVar.h(), f.a(new Handler(looper)), bVar);
        } else if (a.a(locationManager, str, eVar, bVar, looper)) {
        } else {
            locationManager.requestLocationUpdates(str, eVar.b(), eVar.e(), bVar, looper);
        }
    }
}
