package b0;

import androidx.camera.core.f;
import b0.u0;
/* loaded from: classes.dex */
public final class m1 implements g3, p1, g0.n {
    public static final u0.a J = u0.a.a("camerax.core.imageAnalysis.backpressureStrategy", f.b.class);
    public static final u0.a K = u0.a.a("camerax.core.imageAnalysis.imageQueueDepth", Integer.TYPE);
    public static final u0.a L = u0.a.a("camerax.core.imageAnalysis.imageReaderProxyProvider", y.b1.class);
    public static final u0.a M = u0.a.a("camerax.core.imageAnalysis.outputImageFormat", f.e.class);
    public static final u0.a N = u0.a.a("camerax.core.imageAnalysis.onePixelShiftEnabled", Boolean.class);
    public static final u0.a O = u0.a.a("camerax.core.imageAnalysis.outputImageRotationEnabled", Boolean.class);
    private final f2 I;

    public m1(f2 f2Var) {
        this.I = f2Var;
    }

    @Override // b0.o1
    public int B() {
        return 35;
    }

    public int Z(int i8) {
        return ((Integer) f(J, Integer.valueOf(i8))).intValue();
    }

    public int a0(int i8) {
        return ((Integer) f(K, Integer.valueOf(i8))).intValue();
    }

    public y.b1 b0() {
        androidx.appcompat.app.w.a(f(L, null));
        return null;
    }

    public Boolean c0(Boolean bool) {
        return (Boolean) f(N, bool);
    }

    public int d0(int i8) {
        return ((Integer) f(M, Integer.valueOf(i8))).intValue();
    }

    public Boolean e0(Boolean bool) {
        return (Boolean) f(O, bool);
    }

    @Override // b0.m2
    public u0 z() {
        return this.I;
    }
}
