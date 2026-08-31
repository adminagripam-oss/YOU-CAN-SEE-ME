package b0;

import b0.u0;
/* loaded from: classes.dex */
public interface o1 extends m2 {

    /* renamed from: h  reason: collision with root package name */
    public static final u0.a f4299h = u0.a.a("camerax.core.imageInput.inputFormat", Integer.TYPE);

    /* renamed from: i  reason: collision with root package name */
    public static final u0.a f4300i = u0.a.a("camerax.core.imageInput.inputDynamicRange", y.a0.class);

    default int B() {
        return ((Integer) b(f4299h)).intValue();
    }

    default boolean I() {
        return a(f4300i);
    }

    default y.a0 o() {
        return (y.a0) g1.e.j((y.a0) f(f4300i, y.a0.f12729c));
    }
}
