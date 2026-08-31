package z1;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private static long f13373a;

    /* renamed from: b  reason: collision with root package name */
    private static Method f13374b;

    /* renamed from: c  reason: collision with root package name */
    private static Method f13375c;

    /* renamed from: d  reason: collision with root package name */
    private static Method f13376d;

    /* renamed from: e  reason: collision with root package name */
    private static Method f13377e;

    public static void a(String str, int i8) {
        int i9 = Build.VERSION.SDK_INT;
        String l8 = l(str);
        if (i9 >= 29) {
            c.a(l8, i8);
        } else {
            b(l8, i8);
        }
    }

    private static void b(String str, int i8) {
        try {
            if (f13375c == null) {
                f13375c = Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
            }
            f13375c.invoke(null, Long.valueOf(f13373a), str, Integer.valueOf(i8));
        } catch (Exception e8) {
            g("asyncTraceBegin", e8);
        }
    }

    public static void c(String str) {
        b.a(l(str));
    }

    public static void d(String str, int i8) {
        int i9 = Build.VERSION.SDK_INT;
        String l8 = l(str);
        if (i9 >= 29) {
            c.b(l8, i8);
        } else {
            e(l8, i8);
        }
    }

    private static void e(String str, int i8) {
        try {
            if (f13376d == null) {
                f13376d = Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
            }
            f13376d.invoke(null, Long.valueOf(f13373a), str, Integer.valueOf(i8));
        } catch (Exception e8) {
            g("asyncTraceEnd", e8);
        }
    }

    public static void f() {
        b.b();
    }

    private static void g(String str, Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof RuntimeException)) {
                throw new RuntimeException(cause);
            }
            throw ((RuntimeException) cause);
        }
        Log.v("Trace", "Unable to call " + str + " via reflection", exc);
    }

    public static boolean h() {
        return Build.VERSION.SDK_INT >= 29 ? c.c() : i();
    }

    private static boolean i() {
        try {
            if (f13374b == null) {
                f13373a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                f13374b = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) f13374b.invoke(null, Long.valueOf(f13373a))).booleanValue();
        } catch (Exception e8) {
            g("isTagEnabled", e8);
            return false;
        }
    }

    public static void j(String str, int i8) {
        int i9 = Build.VERSION.SDK_INT;
        String l8 = l(str);
        if (i9 >= 29) {
            c.d(l8, i8);
        } else {
            k(l8, i8);
        }
    }

    private static void k(String str, int i8) {
        try {
            if (f13377e == null) {
                f13377e = Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            }
            f13377e.invoke(null, Long.valueOf(f13373a), str, Integer.valueOf(i8));
        } catch (Exception e8) {
            g("traceCounter", e8);
        }
    }

    private static String l(String str) {
        return str.length() <= 127 ? str : str.substring(0, 127);
    }
}
