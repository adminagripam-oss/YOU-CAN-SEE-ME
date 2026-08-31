package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import b0.h2;
import s.b0;
/* loaded from: classes.dex */
public class AspectRatioLegacyApi21Quirk implements h2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g(b0 b0Var) {
        Integer num = (Integer) b0Var.a(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        if (num != null) {
            num.intValue();
            return false;
        }
        return false;
    }

    public int f() {
        return 2;
    }
}
