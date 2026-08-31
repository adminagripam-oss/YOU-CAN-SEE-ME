package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import android.util.Size;
import b0.h2;
/* loaded from: classes.dex */
public class StretchedVideoResolutionQuirk implements h2 {
    private static boolean g() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto e5 play".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean h() {
        return g();
    }

    public Size f(int i8) {
        if (i8 != 4) {
            if (i8 != 5) {
                if (i8 != 6) {
                    return null;
                }
                return new Size(1440, 1080);
            }
            return new Size(960, 720);
        }
        return new Size(640, 480);
    }
}
