package b0;

import android.util.Size;
/* loaded from: classes.dex */
public abstract class w2 {

    /* loaded from: classes.dex */
    public enum a {
        VGA(0),
        s720p(1),
        PREVIEW(2),
        s1440p(3),
        RECORD(4),
        MAXIMUM(5),
        ULTRA_MAXIMUM(6),
        NOT_SUPPORT(7);
        

        /* renamed from: e  reason: collision with root package name */
        final int f4464e;

        a(int i8) {
            this.f4464e = i8;
        }

        int e() {
            return this.f4464e;
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        PRIV,
        YUV,
        JPEG,
        JPEG_R,
        RAW
    }

    public static w2 a(b bVar, a aVar) {
        return new l(bVar, aVar, 0L);
    }

    public static w2 b(b bVar, a aVar, long j8) {
        return new l(bVar, aVar, j8);
    }

    public static b e(int i8) {
        return i8 == 35 ? b.YUV : i8 == 256 ? b.JPEG : i8 == 4101 ? b.JPEG_R : i8 == 32 ? b.RAW : b.PRIV;
    }

    public static w2 h(int i8, int i9, Size size, x2 x2Var) {
        b e8 = e(i9);
        a aVar = a.NOT_SUPPORT;
        int c8 = k0.d.c(size);
        if (i8 == 1) {
            if (c8 <= k0.d.c(x2Var.i(i9))) {
                aVar = a.s720p;
            } else if (c8 <= k0.d.c(x2Var.g(i9))) {
                aVar = a.s1440p;
            }
        } else if (c8 <= k0.d.c(x2Var.b())) {
            aVar = a.VGA;
        } else if (c8 <= k0.d.c(x2Var.e())) {
            aVar = a.PREVIEW;
        } else if (c8 <= k0.d.c(x2Var.f())) {
            aVar = a.RECORD;
        } else if (c8 <= k0.d.c(x2Var.c(i9))) {
            aVar = a.MAXIMUM;
        } else {
            Size k8 = x2Var.k(i9);
            if (k8 != null && c8 <= k0.d.c(k8)) {
                aVar = a.ULTRA_MAXIMUM;
            }
        }
        return a(e8, aVar);
    }

    public abstract a c();

    public abstract b d();

    public abstract long f();

    public final boolean g(w2 w2Var) {
        return w2Var.c().e() <= c().e() && w2Var.d() == d();
    }
}
