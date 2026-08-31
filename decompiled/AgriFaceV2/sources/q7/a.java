package q7;

import c7.p;
import r6.k;
import r6.l;
import r6.r;
import t6.d;
import u6.c;
/* loaded from: classes.dex */
public abstract class a {
    private static final void a(d dVar, Throwable th) {
        k.a aVar = k.f11363e;
        dVar.i(k.a(l.a(th)));
        throw th;
    }

    public static final void b(p pVar, Object obj, d dVar, c7.l lVar) {
        d a8;
        d b8;
        try {
            a8 = c.a(pVar, obj, dVar);
            b8 = c.b(a8);
            k.a aVar = k.f11363e;
            p7.k.b(b8, k.a(r.f11372a), lVar);
        } catch (Throwable th) {
            a(dVar, th);
        }
    }

    public static final void c(d dVar, d dVar2) {
        d b8;
        try {
            b8 = c.b(dVar);
            k.a aVar = k.f11363e;
            p7.k.c(b8, k.a(r.f11372a), null, 2, null);
        } catch (Throwable th) {
            a(dVar2, th);
        }
    }

    public static /* synthetic */ void d(p pVar, Object obj, d dVar, c7.l lVar, int i8, Object obj2) {
        if ((i8 & 4) != 0) {
            lVar = null;
        }
        b(pVar, obj, dVar, lVar);
    }
}
