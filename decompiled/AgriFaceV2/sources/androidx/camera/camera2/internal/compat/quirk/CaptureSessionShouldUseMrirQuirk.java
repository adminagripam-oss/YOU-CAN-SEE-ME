package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
/* loaded from: classes.dex */
public class CaptureSessionShouldUseMrirQuirk implements h2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return "google".equalsIgnoreCase(Build.BRAND) && Build.VERSION.SDK_INT >= 35;
    }
}
