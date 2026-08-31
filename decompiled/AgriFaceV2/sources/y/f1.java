package y;

import android.util.Log;
/* loaded from: classes.dex */
public abstract class f1 {

    /* renamed from: a  reason: collision with root package name */
    private static int f12787a = 3;

    public static void a(String str, String str2) {
        String k8 = k(str);
        if (g(k8, 3)) {
            Log.d(k8, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        String k8 = k(str);
        if (g(k8, 3)) {
            Log.d(k8, str2, th);
        }
    }

    public static void c(String str, String str2) {
        String k8 = k(str);
        if (g(k8, 6)) {
            Log.e(k8, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        String k8 = k(str);
        if (g(k8, 6)) {
            Log.e(k8, str2, th);
        }
    }

    public static void e(String str, String str2) {
        String k8 = k(str);
        if (g(k8, 4)) {
            Log.i(k8, str2);
        }
    }

    public static boolean f(String str) {
        return g(k(str), 3);
    }

    private static boolean g(String str, int i8) {
        return f12787a <= i8 || Log.isLoggable(str, i8);
    }

    public static boolean h(String str) {
        return g(k(str), 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i() {
        f12787a = 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j(int i8) {
        f12787a = i8;
    }

    private static String k(String str) {
        return str;
    }

    public static void l(String str, String str2) {
        String k8 = k(str);
        if (g(k8, 5)) {
            Log.w(k8, str2);
        }
    }

    public static void m(String str, String str2, Throwable th) {
        String k8 = k(str);
        if (g(k8, 5)) {
            Log.w(k8, str2, th);
        }
    }
}
