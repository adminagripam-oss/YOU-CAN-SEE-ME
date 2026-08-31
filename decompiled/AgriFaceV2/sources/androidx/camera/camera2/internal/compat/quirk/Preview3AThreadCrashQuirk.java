package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
/* loaded from: classes.dex */
public class Preview3AThreadCrashQuirk implements h2 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return "samsungexynos7870".equalsIgnoreCase(Build.HARDWARE);
    }
}
