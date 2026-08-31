package q2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
/* loaded from: classes.dex */
public abstract class f {
    private static PackageInfo a(Context context) {
        PackageManager.PackageInfoFlags of;
        PackageInfo packageInfo;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        if (Build.VERSION.SDK_INT < 33) {
            return packageManager.getPackageInfo(packageName, 4096);
        }
        of = PackageManager.PackageInfoFlags.of(4096L);
        packageInfo = packageManager.getPackageInfo(packageName, of);
        return packageInfo;
    }

    public static boolean b(Context context, String str) {
        try {
            String[] strArr = a(context).requestedPermissions;
            if (strArr != null) {
                for (String str2 : strArr) {
                    if (str2.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        return false;
    }
}
