package d0;

import y.f1;
/* loaded from: classes.dex */
public abstract class c {
    public static int a(int i8, int i9, boolean z7) {
        int i10 = (z7 ? (i9 - i8) + 360 : i9 + i8) % 360;
        if (f1.h("CameraOrientationUtil")) {
            f1.a("CameraOrientationUtil", String.format("getRelativeImageRotation: destRotationDegrees=%s, sourceRotationDegrees=%s, isOppositeFacing=%s, result=%s", Integer.valueOf(i8), Integer.valueOf(i9), Boolean.valueOf(z7), Integer.valueOf(i10)));
        }
        return i10;
    }

    public static int b(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 == 3) {
                        return 270;
                    }
                    throw new IllegalArgumentException("Unsupported surface rotation: " + i8);
                }
                return 180;
            }
            return 90;
        }
        return 0;
    }
}
