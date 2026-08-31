package k7;

import r6.k;
/* loaded from: classes.dex */
public abstract class y {
    public static final Object a(Object obj, t6.d dVar) {
        if (obj instanceof u) {
            k.a aVar = r6.k.f11363e;
            obj = r6.l.a(((u) obj).f8789a);
        }
        return r6.k.a(obj);
    }

    public static final Object b(Object obj, c7.l lVar) {
        Throwable b8 = r6.k.b(obj);
        return b8 == null ? lVar != null ? new v(obj, lVar) : obj : new u(b8, false, 2, null);
    }

    public static final Object c(Object obj, j jVar) {
        Throwable b8 = r6.k.b(obj);
        return b8 == null ? obj : new u(b8, false, 2, null);
    }

    public static /* synthetic */ Object d(Object obj, c7.l lVar, int i8, Object obj2) {
        if ((i8 & 1) != 0) {
            lVar = null;
        }
        return b(obj, lVar);
    }
}
