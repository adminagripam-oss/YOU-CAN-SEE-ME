package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import b0.g0;
import r0.v;
/* loaded from: classes.dex */
public class VideoEncoderCrashQuirk implements VideoQualityQuirk {
    private static boolean f() {
        return "positivo".equalsIgnoreCase(Build.BRAND) && "twist 2 pro".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g() {
        return f();
    }

    @Override // androidx.camera.video.internal.compat.quirk.VideoQualityQuirk
    public boolean c(g0 g0Var, v vVar) {
        return f() && g0Var.d() == 0 && vVar == v.f11267a;
    }
}
