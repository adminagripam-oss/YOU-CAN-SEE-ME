package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
/* loaded from: classes.dex */
public class PreviewDelayWhenVideoCaptureIsBoundQuirk implements CaptureIntentPreviewQuirk, SurfaceProcessingQuirk {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return "Huawei".equalsIgnoreCase(Build.MANUFACTURER);
    }
}
