package androidx.camera.camera2.internal.compat.quirk;

import b0.h2;
import b0.l2;
/* loaded from: classes.dex */
public interface CaptureIntentPreviewQuirk extends h2 {
    static boolean e(l2 l2Var) {
        for (CaptureIntentPreviewQuirk captureIntentPreviewQuirk : l2Var.c(CaptureIntentPreviewQuirk.class)) {
            if (captureIntentPreviewQuirk.d()) {
                return true;
            }
        }
        return false;
    }

    default boolean d() {
        return true;
    }
}
