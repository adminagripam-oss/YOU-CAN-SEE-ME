package q3;

import android.content.Context;
import android.content.pm.PackageManager;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private static Boolean f10322a;

    /* renamed from: b  reason: collision with root package name */
    private static Boolean f10323b;

    /* renamed from: c  reason: collision with root package name */
    private static Boolean f10324c;

    /* renamed from: d  reason: collision with root package name */
    private static Boolean f10325d;

    public static boolean a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (f10325d == null) {
            boolean z7 = false;
            if (g.g() && packageManager.hasSystemFeature("android.hardware.type.automotive")) {
                z7 = true;
            }
            f10325d = Boolean.valueOf(z7);
        }
        return f10325d.booleanValue();
    }

    public static boolean b(Context context) {
        return f(context.getPackageManager());
    }

    public static boolean c(Context context) {
        if (!b(context) || g.f()) {
            if (d(context)) {
                return !g.g() || g.j();
            }
            return false;
        }
        return true;
    }

    public static boolean d(Context context) {
        if (f10323b == null) {
            boolean z7 = false;
            if (g.e() && context.getPackageManager().hasSystemFeature("cn.google")) {
                z7 = true;
            }
            f10323b = Boolean.valueOf(z7);
        }
        return f10323b.booleanValue();
    }

    public static boolean e(Context context) {
        if (f10324c == null) {
            boolean z7 = true;
            if (!context.getPackageManager().hasSystemFeature("android.hardware.type.iot") && !context.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
                z7 = false;
            }
            f10324c = Boolean.valueOf(z7);
        }
        return f10324c.booleanValue();
    }

    public static boolean f(PackageManager packageManager) {
        if (f10322a == null) {
            boolean z7 = false;
            if (g.d() && packageManager.hasSystemFeature("android.hardware.type.watch")) {
                z7 = true;
            }
            f10322a = Boolean.valueOf(z7);
        }
        return f10322a.booleanValue();
    }
}
