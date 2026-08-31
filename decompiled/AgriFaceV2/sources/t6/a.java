package t6;

import c7.p;
import d7.k;
import t6.g;
/* loaded from: classes.dex */
public abstract class a implements g.b {

    /* renamed from: e  reason: collision with root package name */
    private final g.c f11907e;

    public a(g.c cVar) {
        k.e(cVar, "key");
        this.f11907e = cVar;
    }

    @Override // t6.g
    public g G(g.c cVar) {
        return g.b.a.c(this, cVar);
    }

    @Override // t6.g
    public g M(g gVar) {
        return g.b.a.d(this, gVar);
    }

    @Override // t6.g.b, t6.g
    public g.b c(g.c cVar) {
        return g.b.a.b(this, cVar);
    }

    @Override // t6.g
    public Object g(Object obj, p pVar) {
        return g.b.a.a(this, obj, pVar);
    }

    @Override // t6.g.b
    public g.c getKey() {
        return this.f11907e;
    }
}
