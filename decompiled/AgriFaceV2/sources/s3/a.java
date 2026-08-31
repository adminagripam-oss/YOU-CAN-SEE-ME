package s3;

import android.content.Context;
import q3.g;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private static Context f11557a;

    /* renamed from: b  reason: collision with root package name */
    private static Boolean f11558b;

    public static synchronized boolean a(Context context) {
        Boolean bool;
        Boolean bool2;
        synchronized (a.class) {
            Context applicationContext = context.getApplicationContext();
            Context context2 = f11557a;
            if (context2 != null && (bool2 = f11558b) != null && context2 == applicationContext) {
                return bool2.booleanValue();
            }
            f11558b = null;
            if (!g.g()) {
                try {
                    context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                    f11558b = Boolean.TRUE;
                } catch (ClassNotFoundException unused) {
                    bool = Boolean.FALSE;
                }
                f11557a = applicationContext;
                return f11558b.booleanValue();
            }
            bool = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
            f11558b = bool;
            f11557a = applicationContext;
            return f11558b.booleanValue();
        }
    }
}
