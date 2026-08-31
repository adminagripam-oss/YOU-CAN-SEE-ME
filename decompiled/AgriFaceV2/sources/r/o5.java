package r;

import android.hardware.camera2.CameraCharacteristics;
/* loaded from: classes.dex */
abstract class o5 {
    public static boolean a(s.b0 b0Var, int i8) {
        int[] iArr = (int[]) b0Var.a(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        if (iArr != null) {
            for (int i9 : iArr) {
                if (i9 == i8) {
                    return true;
                }
            }
        }
        return false;
    }
}
