package b0;

import b0.u0;
import java.util.concurrent.Executor;
import y.u0;
/* loaded from: classes.dex */
public final class n1 implements g3, p1, g0.h {
    public static final u0.a J;
    public static final u0.a K;
    public static final u0.a L;
    public static final u0.a M;
    public static final u0.a N;
    public static final u0.a O;
    public static final u0.a P;
    public static final u0.a Q;
    public static final u0.a R;
    public static final u0.a S;
    public static final u0.a T;
    public static final u0.a U;
    public static final u0.a V;
    private final f2 I;

    static {
        Class cls = Integer.TYPE;
        J = u0.a.a("camerax.core.imageCapture.captureMode", cls);
        K = u0.a.a("camerax.core.imageCapture.flashMode", cls);
        L = u0.a.a("camerax.core.imageCapture.captureBundle", r0.class);
        M = u0.a.a("camerax.core.imageCapture.bufferFormat", Integer.class);
        N = u0.a.a("camerax.core.imageCapture.outputFormat", Integer.class);
        O = u0.a.a("camerax.core.imageCapture.maxCaptureStages", Integer.class);
        P = u0.a.a("camerax.core.imageCapture.imageReaderProxyProvider", y.b1.class);
        Q = u0.a.a("camerax.core.imageCapture.useSoftwareJpegEncoder", Boolean.TYPE);
        R = u0.a.a("camerax.core.imageCapture.flashType", cls);
        S = u0.a.a("camerax.core.imageCapture.jpegCompressionQuality", cls);
        T = u0.a.a("camerax.core.imageCapture.screenFlash", u0.i.class);
        U = u0.a.a("camerax.core.useCase.postviewResolutionSelector", o0.c.class);
        V = u0.a.a("camerax.core.useCase.isPostviewEnabled", Boolean.class);
    }

    public n1(f2 f2Var) {
        this.I = f2Var;
    }

    @Override // b0.o1
    public int B() {
        return ((Integer) b(o1.f4299h)).intValue();
    }

    public r0 Z(r0 r0Var) {
        return (r0) f(L, r0Var);
    }

    public int a0() {
        return ((Integer) b(J)).intValue();
    }

    public int b0(int i8) {
        return ((Integer) f(K, Integer.valueOf(i8))).intValue();
    }

    public int c0(int i8) {
        return ((Integer) f(R, Integer.valueOf(i8))).intValue();
    }

    public y.b1 d0() {
        androidx.appcompat.app.w.a(f(P, null));
        return null;
    }

    public Executor e0(Executor executor) {
        return (Executor) f(g0.h.E, executor);
    }

    public int f0() {
        return ((Integer) b(S)).intValue();
    }

    public u0.i g0() {
        return (u0.i) f(T, null);
    }

    public boolean h0() {
        return a(J);
    }

    @Override // b0.m2
    public u0 z() {
        return this.I;
    }
}
