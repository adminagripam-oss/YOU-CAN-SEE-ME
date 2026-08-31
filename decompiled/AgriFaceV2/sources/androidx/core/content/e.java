package androidx.core.content;

import android.content.Context;
import android.os.Process;
/* loaded from: classes.dex */
public abstract class e {
    public static int a(Context context, String str, int i8, int i9, String str2) {
        if (context.checkPermission(str, i8, i9) == -1) {
            return -1;
        }
        String c8 = androidx.core.app.e.c(str);
        if (c8 == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i9);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        return (Process.myUid() == i9 && g1.c.a(context.getPackageName(), str2) ? androidx.core.app.e.a(context, i9, c8, str2) : androidx.core.app.e.b(context, c8, str2)) == 0 ? 0 : -2;
    }

    public static int b(Context context, String str) {
        return a(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
