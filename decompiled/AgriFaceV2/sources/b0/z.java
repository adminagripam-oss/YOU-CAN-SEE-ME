package b0;

import b0.u0;
/* loaded from: classes.dex */
public interface z extends m2 {

    /* renamed from: a  reason: collision with root package name */
    public static final u0.a f4487a = u0.a.a("camerax.core.camera.useCaseConfigFactory", h3.class);

    /* renamed from: b  reason: collision with root package name */
    public static final u0.a f4488b = u0.a.a("camerax.core.camera.compatibilityId", l1.class);

    /* renamed from: c  reason: collision with root package name */
    public static final u0.a f4489c = u0.a.a("camerax.core.camera.useCaseCombinationRequiredRule", Integer.class);

    /* renamed from: d  reason: collision with root package name */
    public static final u0.a f4490d = u0.a.a("camerax.core.camera.SessionProcessor", s2.class);

    /* renamed from: e  reason: collision with root package name */
    public static final u0.a f4491e = u0.a.a("camerax.core.camera.isZslDisabled", Boolean.class);

    /* renamed from: f  reason: collision with root package name */
    public static final u0.a f4492f = u0.a.a("camerax.core.camera.isPostviewSupported", Boolean.class);

    /* renamed from: g  reason: collision with root package name */
    public static final u0.a f4493g = u0.a.a("camerax.core.camera.isCaptureProcessProgressSupported", Boolean.class);

    l1 G();

    default boolean H() {
        return ((Boolean) f(f4493g, Boolean.FALSE)).booleanValue();
    }

    default boolean P() {
        return ((Boolean) f(f4492f, Boolean.FALSE)).booleanValue();
    }

    default int X() {
        return ((Integer) f(f4489c, 0)).intValue();
    }

    default h3 l() {
        return (h3) f(f4487a, h3.f4218a);
    }

    default s2 s(s2 s2Var) {
        androidx.appcompat.app.w.a(f(f4490d, s2Var));
        return null;
    }
}
