package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import b0.h2;
import s.b0;
/* loaded from: classes.dex */
public class AfRegionFlipHorizontallyQuirk implements h2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(b0 b0Var) {
        return Build.BRAND.equalsIgnoreCase("SAMSUNG") && Build.VERSION.SDK_INT < 33 && ((Integer) b0Var.a(CameraCharacteristics.LENS_FACING)).intValue() == 0;
    }
}
