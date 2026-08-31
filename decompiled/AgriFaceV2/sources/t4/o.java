package t4;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class o {

    /* renamed from: b  reason: collision with root package name */
    private static final d f11872b = new d() { // from class: t4.n
        @Override // t4.d
        public final j4.h a(j4.w wVar, Integer num) {
            i e8;
            e8 = o.e((j) wVar, num);
            return e8;
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private static final o f11873c = g();

    /* renamed from: a  reason: collision with root package name */
    private final Map f11874a = new HashMap();

    private synchronized j4.h d(j4.w wVar, Integer num) {
        d dVar;
        dVar = (d) this.f11874a.get(wVar.getClass());
        if (dVar == null) {
            throw new GeneralSecurityException("Cannot create a new key for parameters " + wVar + ": no key creator for this class was registered.");
        }
        return dVar.a(wVar, num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static i e(j jVar, Integer num) {
        y4.a0 d8 = jVar.b().d();
        j4.i c8 = e.d().c(d8.a0());
        if (e.d().f(d8.a0())) {
            y4.y c9 = c8.c(d8.b0());
            return new i(d0.b(c9.a0(), c9.b0(), c9.Z(), d8.Z(), num), j4.g.a());
        }
        throw new GeneralSecurityException("Creating new keys is not allowed.");
    }

    public static o f() {
        return f11873c;
    }

    private static o g() {
        o oVar = new o();
        try {
            oVar.b(f11872b, j.class);
            return oVar;
        } catch (GeneralSecurityException e8) {
            throw new IllegalStateException("unexpected error.", e8);
        }
    }

    public synchronized void b(d dVar, Class cls) {
        d dVar2 = (d) this.f11874a.get(cls);
        if (dVar2 != null && !dVar2.equals(dVar)) {
            throw new GeneralSecurityException("Different key creator for parameters class " + cls + " already inserted");
        }
        this.f11874a.put(cls, dVar);
    }

    public j4.h c(j4.w wVar, Integer num) {
        return d(wVar, num);
    }
}
