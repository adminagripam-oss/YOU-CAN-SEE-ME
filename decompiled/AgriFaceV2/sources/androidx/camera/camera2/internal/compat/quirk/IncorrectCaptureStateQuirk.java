package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import b0.h2;
import s.b0;
/* loaded from: classes.dex */
public class IncorrectCaptureStateQuirk implements h2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(b0 b0Var) {
        Integer num = (Integer) b0Var.a(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        return num != null && num.intValue() == 2;
    }
}
