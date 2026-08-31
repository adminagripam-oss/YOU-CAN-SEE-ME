package androidx.appcompat.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
/* loaded from: classes.dex */
public abstract class r extends Service {

    /* loaded from: classes.dex */
    private static class a {
        static int a() {
            return 512;
        }
    }

    public static ServiceInfo a(Context context) {
        return context.getPackageManager().getServiceInfo(new ComponentName(context, r.class), a.a() | 128);
    }
}
