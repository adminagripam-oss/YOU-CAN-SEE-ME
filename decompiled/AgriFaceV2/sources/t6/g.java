package t6;

import c7.p;
import d7.k;
import d7.l;
import t6.e;
/* loaded from: classes.dex */
public interface g {

    /* loaded from: classes.dex */
    public static final class a {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: t6.g$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0153a extends l implements p {

            /* renamed from: f  reason: collision with root package name */
            public static final C0153a f11915f = new C0153a();

            C0153a() {
                super(2);
            }

            @Override // c7.p
            /* renamed from: b */
            public final g f(g gVar, b bVar) {
                t6.c cVar;
                k.e(gVar, "acc");
                k.e(bVar, "element");
                g G = gVar.G(bVar.getKey());
                h hVar = h.f11916e;
                if (G == hVar) {
                    return bVar;
                }
                e.b bVar2 = e.f11913d;
                e eVar = (e) G.c(bVar2);
                if (eVar == null) {
                    cVar = new t6.c(G, bVar);
                } else {
                    g G2 = G.G(bVar2);
                    if (G2 == hVar) {
                        return new t6.c(bVar, eVar);
                    }
                    cVar = new t6.c(new t6.c(G2, bVar), eVar);
                }
                return cVar;
            }
        }

        public static g a(g gVar, g gVar2) {
            k.e(gVar2, "context");
            return gVar2 == h.f11916e ? gVar : (g) gVar2.g(gVar, C0153a.f11915f);
        }
    }

    /* loaded from: classes.dex */
    public interface b extends g {

        /* loaded from: classes.dex */
        public static final class a {
            public static Object a(b bVar, Object obj, p pVar) {
                k.e(pVar, "operation");
                return pVar.f(obj, bVar);
            }

            public static b b(b bVar, c cVar) {
                k.e(cVar, "key");
                if (k.a(bVar.getKey(), cVar)) {
                    k.c(bVar, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
                    return bVar;
                }
                return null;
            }

            public static g c(b bVar, c cVar) {
                k.e(cVar, "key");
                return k.a(bVar.getKey(), cVar) ? h.f11916e : bVar;
            }

            public static g d(b bVar, g gVar) {
                k.e(gVar, "context");
                return a.a(bVar, gVar);
            }
        }

        @Override // t6.g
        b c(c cVar);

        c getKey();
    }

    /* loaded from: classes.dex */
    public interface c {
    }

    g G(c cVar);

    g M(g gVar);

    b c(c cVar);

    Object g(Object obj, p pVar);
}
