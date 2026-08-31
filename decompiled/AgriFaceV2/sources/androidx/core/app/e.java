package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
/* loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static <T> T a(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        static int b(AppOpsManager appOpsManager, String str, String str2) {
            return appOpsManager.noteProxyOp(str, str2);
        }

        static int c(AppOpsManager appOpsManager, String str, String str2) {
            return appOpsManager.noteProxyOpNoThrow(str, str2);
        }

        static String d(String str) {
            return AppOpsManager.permissionToOp(str);
        }
    }

    /* loaded from: classes.dex */
    static class b {
        static int a(AppOpsManager appOpsManager, String str, int i8, String str2) {
            if (appOpsManager == null) {
                return 1;
            }
            return appOpsManager.checkOpNoThrow(str, i8, str2);
        }

        static String b(Context context) {
            return context.getOpPackageName();
        }

        static AppOpsManager c(Context context) {
            return (AppOpsManager) context.getSystemService(AppOpsManager.class);
        }
    }

    public static int a(Context context, int i8, String str, String str2) {
        if (Build.VERSION.SDK_INT >= 29) {
            AppOpsManager c8 = b.c(context);
            int a8 = b.a(c8, str, Binder.getCallingUid(), str2);
            return a8 != 0 ? a8 : b.a(c8, str, i8, b.b(context));
        }
        return b(context, str, str2);
    }

    public static int b(Context context, String str, String str2) {
        return a.c((AppOpsManager) a.a(context, AppOpsManager.class), str, str2);
    }

    public static String c(String str) {
        return a.d(str);
    }
}
