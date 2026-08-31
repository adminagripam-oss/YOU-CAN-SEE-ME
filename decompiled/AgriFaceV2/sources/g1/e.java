package g1;

import java.util.Locale;
/* loaded from: classes.dex */
public abstract class e {
    public static void a(boolean z7) {
        if (!z7) {
            throw new IllegalArgumentException();
        }
    }

    public static void b(boolean z7, Object obj) {
        if (!z7) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void c(boolean z7, String str, Object... objArr) {
        if (!z7) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static float d(float f8, float f9, float f10, String str) {
        if (f8 >= f9) {
            if (f8 <= f10) {
                return f8;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", str, Float.valueOf(f9), Float.valueOf(f10)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", str, Float.valueOf(f9), Float.valueOf(f10)));
    }

    public static int e(int i8, int i9, int i10, String str) {
        if (i8 >= i9) {
            if (i8 <= i10) {
                return i8;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Integer.valueOf(i9), Integer.valueOf(i10)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Integer.valueOf(i9), Integer.valueOf(i10)));
    }

    public static long f(long j8, long j9, long j10, String str) {
        if (j8 >= j9) {
            if (j8 <= j10) {
                return j8;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Long.valueOf(j9), Long.valueOf(j10)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Long.valueOf(j9), Long.valueOf(j10)));
    }

    public static int g(int i8) {
        if (i8 >= 0) {
            return i8;
        }
        throw new IllegalArgumentException();
    }

    public static int h(int i8, String str) {
        if (i8 >= 0) {
            return i8;
        }
        throw new IllegalArgumentException(str);
    }

    public static int i(int i8, int i9) {
        if ((i8 & i9) == i8) {
            return i8;
        }
        throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i8) + ", but only 0x" + Integer.toHexString(i9) + " are allowed");
    }

    public static Object j(Object obj) {
        obj.getClass();
        return obj;
    }

    public static Object k(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(String.valueOf(obj2));
    }

    public static void l(boolean z7) {
        m(z7, null);
    }

    public static void m(boolean z7, String str) {
        if (!z7) {
            throw new IllegalStateException(str);
        }
    }
}
