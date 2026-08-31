package r;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
/* loaded from: classes.dex */
public abstract class b5 {
    public static boolean a(s.b0 b0Var) {
        int[] iArr;
        if (Build.VERSION.SDK_INT >= 33 && (iArr = (int[]) b0Var.a(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES)) != null && iArr.length != 0) {
            for (int i8 : iArr) {
                if (i8 == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
