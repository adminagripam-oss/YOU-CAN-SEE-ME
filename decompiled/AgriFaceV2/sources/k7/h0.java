package k7;

import r6.k;
/* loaded from: classes.dex */
public abstract class h0 {
    public static final String a(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static final String b(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final String c(t6.d dVar) {
        String a8;
        if (dVar instanceof p7.j) {
            return dVar.toString();
        }
        try {
            k.a aVar = r6.k.f11363e;
            a8 = r6.k.a(dVar + '@' + b(dVar));
        } catch (Throwable th) {
            k.a aVar2 = r6.k.f11363e;
            a8 = r6.k.a(r6.l.a(th));
        }
        if (r6.k.b(a8) != null) {
            a8 = dVar.getClass().getName() + '@' + b(dVar);
        }
        return (String) a8;
    }
}
