package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import android.util.Size;
import b0.h2;
/* loaded from: classes.dex */
public class ExtraSupportedOutputSizeQuirk implements h2 {
    private Size[] g() {
        return new Size[]{new Size(1440, 1080), new Size(960, 720)};
    }

    private static boolean h() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto e5 play".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean i() {
        return h();
    }

    public Size[] f(int i8) {
        return (i8 == 34 && h()) ? g() : new Size[0];
    }
}
