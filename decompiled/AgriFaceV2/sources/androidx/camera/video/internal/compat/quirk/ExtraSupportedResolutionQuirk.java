package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
/* loaded from: classes.dex */
public class ExtraSupportedResolutionQuirk implements SurfaceProcessingQuirk {
    private static boolean f() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto e5 play".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g() {
        return f();
    }
}
