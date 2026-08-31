package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import b0.s0;
import b0.u0;
/* loaded from: classes.dex */
public final class ImageCaptureRotationOptionQuirk implements h2 {
    private static boolean f() {
        String str = Build.FINGERPRINT;
        if (!str.startsWith("generic") && !str.startsWith("unknown")) {
            String str2 = Build.MODEL;
            if (!str2.contains("google_sdk") && !str2.contains("Emulator") && !str2.contains("Cuttlefish") && !str2.contains("Android SDK built for x86") && !Build.MANUFACTURER.contains("Genymotion") && ((!Build.BRAND.startsWith("generic") || !Build.DEVICE.startsWith("generic")) && !Build.PRODUCT.equals("google_sdk") && !Build.HARDWARE.contains("ranchu"))) {
                return false;
            }
        }
        return true;
    }

    private static boolean g() {
        f();
        return false;
    }

    private static boolean h() {
        return "HONOR".equalsIgnoreCase(Build.BRAND) && "STK-LX1".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean i() {
        return "HUAWEI".equalsIgnoreCase(Build.BRAND) && "SNE-LX1".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean k() {
        return i() || h() || g();
    }

    public boolean j(u0.a aVar) {
        return aVar != s0.f4371i;
    }
}
