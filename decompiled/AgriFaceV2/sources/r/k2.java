package r;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
/* loaded from: classes.dex */
public abstract class k2 {
    public static boolean a(s.o0 o0Var, String str) {
        if ("robolectric".equals(Build.FINGERPRINT)) {
            return true;
        }
        try {
            int[] iArr = (int[]) o0Var.c(str).a(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            if (iArr != null) {
                for (int i8 : iArr) {
                    if (i8 == 0) {
                        return true;
                    }
                }
            }
            return false;
        } catch (s.g e8) {
            throw new y.d1(n2.a(e8));
        }
    }
}
