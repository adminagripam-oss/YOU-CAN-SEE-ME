package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Locale;
/* loaded from: classes.dex */
public class StillCaptureFlashStopRepeatingQuirk implements h2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        String str = Build.MANUFACTURER;
        Locale locale = Locale.US;
        return "SAMSUNG".equals(str.toUpperCase(locale)) && Build.MODEL.toUpperCase(locale).startsWith("SM-A716");
    }
}
