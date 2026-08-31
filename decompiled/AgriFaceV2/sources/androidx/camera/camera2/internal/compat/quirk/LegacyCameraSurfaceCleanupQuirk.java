package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import b0.h2;
import s.b0;
/* loaded from: classes.dex */
public class LegacyCameraSurfaceCleanupQuirk implements h2 {
    static boolean f(b0 b0Var) {
        Integer num = (Integer) b0Var.a(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        return num != null && num.intValue() == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g(b0 b0Var) {
        return Build.VERSION.SDK_INT < 29 && f(b0Var);
    }
}
