package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import b0.h2;
/* loaded from: classes.dex */
public class VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk implements h2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return "Samsung".equalsIgnoreCase(Build.BRAND) && Build.VERSION.SDK_INT < 29;
    }
}
