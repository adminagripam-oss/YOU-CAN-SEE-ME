package b0;

import android.util.Range;
import b0.h3;
import b0.q2;
import b0.s0;
import b0.u0;
/* loaded from: classes.dex */
public interface g3 extends g0.m, o1 {
    public static final u0.a A;
    public static final u0.a B;
    public static final u0.a C;
    public static final u0.a D;

    /* renamed from: t  reason: collision with root package name */
    public static final u0.a f4200t = u0.a.a("camerax.core.useCase.defaultSessionConfig", q2.class);

    /* renamed from: u  reason: collision with root package name */
    public static final u0.a f4201u = u0.a.a("camerax.core.useCase.defaultCaptureConfig", s0.class);

    /* renamed from: v  reason: collision with root package name */
    public static final u0.a f4202v = u0.a.a("camerax.core.useCase.sessionConfigUnpacker", q2.e.class);

    /* renamed from: w  reason: collision with root package name */
    public static final u0.a f4203w = u0.a.a("camerax.core.useCase.captureConfigUnpacker", s0.b.class);

    /* renamed from: x  reason: collision with root package name */
    public static final u0.a f4204x;

    /* renamed from: y  reason: collision with root package name */
    public static final u0.a f4205y;

    /* renamed from: z  reason: collision with root package name */
    public static final u0.a f4206z;

    /* loaded from: classes.dex */
    public interface a extends y.c0 {
        g3 d();
    }

    static {
        Class cls = Integer.TYPE;
        f4204x = u0.a.a("camerax.core.useCase.surfaceOccupancyPriority", cls);
        f4205y = u0.a.a("camerax.core.useCase.targetFrameRate", Range.class);
        Class cls2 = Boolean.TYPE;
        f4206z = u0.a.a("camerax.core.useCase.zslDisabled", cls2);
        A = u0.a.a("camerax.core.useCase.highResolutionDisabled", cls2);
        B = u0.a.a("camerax.core.useCase.captureType", h3.b.class);
        C = u0.a.a("camerax.core.useCase.previewStabilizationMode", cls);
        D = u0.a.a("camerax.core.useCase.videoStabilizationMode", cls);
    }

    default q2 C(q2 q2Var) {
        return (q2) f(f4200t, q2Var);
    }

    default int D() {
        return ((Integer) f(C, 0)).intValue();
    }

    default s0 K(s0 s0Var) {
        return (s0) f(f4201u, s0Var);
    }

    default q2 M() {
        return (q2) b(f4200t);
    }

    default boolean N(boolean z7) {
        return ((Boolean) f(A, Boolean.valueOf(z7))).booleanValue();
    }

    default boolean O(boolean z7) {
        return ((Boolean) f(f4206z, Boolean.valueOf(z7))).booleanValue();
    }

    default q2.e R(q2.e eVar) {
        return (q2.e) f(f4202v, eVar);
    }

    default s0.b k(s0.b bVar) {
        return (s0.b) f(f4203w, bVar);
    }

    default h3.b m() {
        return (h3.b) b(B);
    }

    default int n() {
        return ((Integer) f(D, 0)).intValue();
    }

    default Range p(Range range) {
        return (Range) f(f4205y, range);
    }

    default int v(int i8) {
        return ((Integer) f(f4204x, Integer.valueOf(i8))).intValue();
    }
}
