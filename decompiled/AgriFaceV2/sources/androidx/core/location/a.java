package androidx.core.location;

import android.location.Location;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private static Field f3011a;

    /* renamed from: b  reason: collision with root package name */
    private static Integer f3012b;

    /* renamed from: c  reason: collision with root package name */
    private static Integer f3013c;

    /* renamed from: d  reason: collision with root package name */
    private static Integer f3014d;

    /* renamed from: androidx.core.location.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0026a {
        static float a(Location location) {
            return location.getBearingAccuracyDegrees();
        }

        static float b(Location location) {
            return location.getSpeedAccuracyMetersPerSecond();
        }

        static float c(Location location) {
            return location.getVerticalAccuracyMeters();
        }

        static boolean d(Location location) {
            return location.hasBearingAccuracy();
        }

        static boolean e(Location location) {
            return location.hasSpeedAccuracy();
        }

        static boolean f(Location location) {
            return location.hasVerticalAccuracy();
        }

        static void g(Location location) {
            try {
                a.c().setByte(location, (byte) (a.c().getByte(location) & (~a.d())));
            } catch (IllegalAccessException e8) {
                IllegalAccessError illegalAccessError = new IllegalAccessError();
                illegalAccessError.initCause(e8);
                throw illegalAccessError;
            } catch (NoSuchFieldException e9) {
                NoSuchFieldError noSuchFieldError = new NoSuchFieldError();
                noSuchFieldError.initCause(e9);
                throw noSuchFieldError;
            }
        }

        static void h(Location location) {
            try {
                a.c().setByte(location, (byte) (a.c().getByte(location) & (~a.e())));
            } catch (IllegalAccessException e8) {
                IllegalAccessError illegalAccessError = new IllegalAccessError();
                illegalAccessError.initCause(e8);
                throw illegalAccessError;
            } catch (NoSuchFieldException e9) {
                NoSuchFieldError noSuchFieldError = new NoSuchFieldError();
                noSuchFieldError.initCause(e9);
                throw noSuchFieldError;
            }
        }

        static void i(Location location) {
            try {
                a.c().setByte(location, (byte) (a.c().getByte(location) & (~a.f())));
            } catch (IllegalAccessException | NoSuchFieldException e8) {
                IllegalAccessError illegalAccessError = new IllegalAccessError();
                illegalAccessError.initCause(e8);
                throw illegalAccessError;
            }
        }

        static void j(Location location, float f8) {
            location.setBearingAccuracyDegrees(f8);
        }

        static void k(Location location, float f8) {
            location.setSpeedAccuracyMetersPerSecond(f8);
        }

        static void l(Location location, float f8) {
            location.setVerticalAccuracyMeters(f8);
        }
    }

    public static float a(Location location) {
        return C0026a.a(location);
    }

    public static long b(Location location) {
        return TimeUnit.NANOSECONDS.toMillis(location.getElapsedRealtimeNanos());
    }

    static Field c() {
        if (f3011a == null) {
            Field declaredField = Location.class.getDeclaredField("mFieldsMask");
            f3011a = declaredField;
            declaredField.setAccessible(true);
        }
        return f3011a;
    }

    static int d() {
        if (f3013c == null) {
            Field declaredField = Location.class.getDeclaredField("HAS_BEARING_ACCURACY_MASK");
            declaredField.setAccessible(true);
            f3013c = Integer.valueOf(declaredField.getInt(null));
        }
        return f3013c.intValue();
    }

    static int e() {
        if (f3012b == null) {
            Field declaredField = Location.class.getDeclaredField("HAS_SPEED_ACCURACY_MASK");
            declaredField.setAccessible(true);
            f3012b = Integer.valueOf(declaredField.getInt(null));
        }
        return f3012b.intValue();
    }

    static int f() {
        if (f3014d == null) {
            Field declaredField = Location.class.getDeclaredField("HAS_VERTICAL_ACCURACY_MASK");
            declaredField.setAccessible(true);
            f3014d = Integer.valueOf(declaredField.getInt(null));
        }
        return f3014d.intValue();
    }

    public static float g(Location location) {
        return C0026a.b(location);
    }

    public static float h(Location location) {
        return C0026a.c(location);
    }

    public static boolean i(Location location) {
        return C0026a.d(location);
    }

    public static boolean j(Location location) {
        return C0026a.e(location);
    }

    public static boolean k(Location location) {
        return C0026a.f(location);
    }

    public static boolean l(Location location) {
        return location.isFromMockProvider();
    }
}
