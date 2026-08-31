package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import b0.h2;
import s.b0;
/* loaded from: classes.dex */
public class CaptureNoResponseQuirk implements h2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(b0 b0Var) {
        String str = Build.HARDWARE;
        return ("samsungexynos7420".equalsIgnoreCase(str) || "universal7420".equalsIgnoreCase(str)) && ((Integer) b0Var.a(CameraCharacteristics.LENS_FACING)).intValue() == 1;
    }
}
