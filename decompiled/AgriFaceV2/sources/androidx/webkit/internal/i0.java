package androidx.webkit.internal;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
/* loaded from: classes.dex */
public abstract class i0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static ServiceInfo a(PackageManager packageManager, ComponentName componentName, PackageManager.ComponentInfoFlags componentInfoFlags) {
        ServiceInfo serviceInfo;
        serviceInfo = packageManager.getServiceInfo(componentName, componentInfoFlags);
        return serviceInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PackageManager.ComponentInfoFlags b(long j8) {
        PackageManager.ComponentInfoFlags of;
        of = PackageManager.ComponentInfoFlags.of(j8);
        return of;
    }
}
