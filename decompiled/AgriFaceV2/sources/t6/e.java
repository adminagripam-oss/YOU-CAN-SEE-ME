package t6;

import d7.k;
import t6.g;
/* loaded from: classes.dex */
public interface e extends g.b {

    /* renamed from: d  reason: collision with root package name */
    public static final b f11913d = b.f11914e;

    /* loaded from: classes.dex */
    public static final class a {
        public static g.b a(e eVar, g.c cVar) {
            k.e(cVar, "key");
            if (!(cVar instanceof t6.b)) {
                if (e.f11913d == cVar) {
                    k.c(eVar, "null cannot be cast to non-null type E of kotlin.coroutines.ContinuationInterceptor.get");
                    return eVar;
                }
                return null;
            }
            t6.b bVar = (t6.b) cVar;
            if (bVar.a(eVar.getKey())) {
                g.b b8 = bVar.b(eVar);
                if (b8 instanceof g.b) {
                    return b8;
                }
                return null;
            }
            return null;
        }

        public static g b(e eVar, g.c cVar) {
            k.e(cVar, "key");
            if (!(cVar instanceof t6.b)) {
                return e.f11913d == cVar ? h.f11916e : eVar;
            }
            t6.b bVar = (t6.b) cVar;
            return (!bVar.a(eVar.getKey()) || bVar.b(eVar) == null) ? eVar : h.f11916e;
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements g.c {

        /* renamed from: e  reason: collision with root package name */
        static final /* synthetic */ b f11914e = new b();

        private b() {
        }
    }

    void j(d dVar);

    d q(d dVar);
}
