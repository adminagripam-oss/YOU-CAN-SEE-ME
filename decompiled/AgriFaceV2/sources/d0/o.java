package d0;

import o0.c;
/* loaded from: classes.dex */
public abstract class o {
    public static o0.c a(o0.c cVar, o0.c cVar2) {
        if (cVar2 == null) {
            return cVar;
        }
        if (cVar == null) {
            return cVar2;
        }
        c.a b8 = c.a.b(cVar);
        if (cVar2.b() != null) {
            b8.d(cVar2.b());
        }
        if (cVar2.d() != null) {
            b8.f(cVar2.d());
        }
        if (cVar2.c() != null) {
            b8.e(cVar2.c());
        }
        if (cVar2.a() != 0) {
            b8.c(cVar2.a());
        }
        return b8.a();
    }
}
