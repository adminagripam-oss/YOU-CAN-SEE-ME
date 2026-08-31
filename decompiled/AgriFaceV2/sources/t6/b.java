package t6;

import c7.l;
import d7.k;
import t6.g;
/* loaded from: classes.dex */
public abstract class b implements g.c {

    /* renamed from: e  reason: collision with root package name */
    private final l f11908e;

    /* renamed from: f  reason: collision with root package name */
    private final g.c f11909f;

    public b(g.c cVar, l lVar) {
        k.e(cVar, "baseKey");
        k.e(lVar, "safeCast");
        this.f11908e = lVar;
        this.f11909f = cVar instanceof b ? ((b) cVar).f11909f : cVar;
    }

    public final boolean a(g.c cVar) {
        k.e(cVar, "key");
        return cVar == this || this.f11909f == cVar;
    }

    public final g.b b(g.b bVar) {
        k.e(bVar, "element");
        return (g.b) this.f11908e.g(bVar);
    }
}
