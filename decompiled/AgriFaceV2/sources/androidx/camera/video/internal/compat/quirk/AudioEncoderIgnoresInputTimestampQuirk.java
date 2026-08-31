package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import b0.h2;
/* loaded from: classes.dex */
public class AudioEncoderIgnoresInputTimestampQuirk implements h2 {
    private static boolean f() {
        return "Sony".equalsIgnoreCase(Build.BRAND) && "G3125".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g() {
        return f();
    }
}
