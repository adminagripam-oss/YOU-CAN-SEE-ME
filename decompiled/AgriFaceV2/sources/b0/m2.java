package b0;

import b0.u0;
import java.util.Set;
/* loaded from: classes.dex */
public interface m2 extends u0 {
    @Override // b0.u0
    default boolean a(u0.a aVar) {
        return z().a(aVar);
    }

    @Override // b0.u0
    default Object b(u0.a aVar) {
        return z().b(aVar);
    }

    @Override // b0.u0
    default void c(String str, u0.b bVar) {
        z().c(str, bVar);
    }

    @Override // b0.u0
    default Set d() {
        return z().d();
    }

    @Override // b0.u0
    default Object e(u0.a aVar, u0.c cVar) {
        return z().e(aVar, cVar);
    }

    @Override // b0.u0
    default Object f(u0.a aVar, Object obj) {
        return z().f(aVar, obj);
    }

    @Override // b0.u0
    default Set g(u0.a aVar) {
        return z().g(aVar);
    }

    @Override // b0.u0
    default u0.c h(u0.a aVar) {
        return z().h(aVar);
    }

    u0 z();
}
