package z3;

import android.os.SystemClock;
/* loaded from: classes.dex */
public abstract class hc {
    public static void a(wb wbVar, int i8, int i9, long j8, int i10, int i11, int i12, int i13) {
        wbVar.c(b(i8, i9, j8, i10, i11, i12, i13), e8.INPUT_IMAGE_CONSTRUCTION);
    }

    private static gc b(int i8, int i9, long j8, int i10, int i11, int i12, int i13) {
        return new gc(i8, i9, i12, i10, i11, SystemClock.elapsedRealtime() - j8, i13);
    }
}
