package r0;

import b0.z2;
import y.g2;
/* loaded from: classes.dex */
public interface b2 {

    /* loaded from: classes.dex */
    public enum a {
        ACTIVE_STREAMING,
        ACTIVE_NON_STREAMING,
        INACTIVE
    }

    void a(g2 g2Var);

    default void b(g2 g2Var, z2 z2Var) {
        a(g2Var);
    }

    default b0.d2 c() {
        return c1.f11018c;
    }

    default b0.d2 d() {
        return b0.w0.g(Boolean.FALSE);
    }

    default d1 e(y.p pVar) {
        return d1.f11031a;
    }

    default void f(a aVar) {
    }

    default b0.d2 g() {
        return b0.w0.g(null);
    }
}
