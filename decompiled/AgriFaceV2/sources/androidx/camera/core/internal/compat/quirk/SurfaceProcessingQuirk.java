package androidx.camera.core.internal.compat.quirk;

import b0.h2;
import b0.l2;
/* loaded from: classes.dex */
public interface SurfaceProcessingQuirk extends h2 {
    static boolean b(l2 l2Var) {
        for (SurfaceProcessingQuirk surfaceProcessingQuirk : l2Var.c(SurfaceProcessingQuirk.class)) {
            if (surfaceProcessingQuirk.a()) {
                return true;
            }
        }
        return false;
    }

    default boolean a() {
        return true;
    }
}
