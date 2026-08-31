package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import b0.h2;
/* loaded from: classes.dex */
public class StopCodecAfterSurfaceRemovalCrashMediaServerQuirk implements h2 {
    private static boolean f() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto c".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g() {
        return f();
    }
}
