package o7;

import c7.p;
import d7.v;
import p7.l0;
/* loaded from: classes.dex */
public abstract class e {
    public static final /* synthetic */ n7.d a(n7.d dVar, t6.g gVar) {
        return d(dVar, gVar);
    }

    public static final Object b(t6.g gVar, Object obj, Object obj2, p pVar, t6.d dVar) {
        Object c8;
        Object c9 = l0.c(gVar, obj2);
        try {
            Object f8 = ((p) v.a(pVar, 2)).f(obj, new k(dVar, gVar));
            l0.a(gVar, c9);
            c8 = u6.d.c();
            if (f8 == c8) {
                v6.h.c(dVar);
            }
            return f8;
        } catch (Throwable th) {
            l0.a(gVar, c9);
            throw th;
        }
    }

    public static /* synthetic */ Object c(t6.g gVar, Object obj, Object obj2, p pVar, t6.d dVar, int i8, Object obj3) {
        if ((i8 & 4) != 0) {
            obj2 = l0.b(gVar);
        }
        return b(gVar, obj, obj2, pVar, dVar);
    }

    public static final n7.d d(n7.d dVar, t6.g gVar) {
        return dVar instanceof j ? dVar : new l(dVar, gVar);
    }
}
