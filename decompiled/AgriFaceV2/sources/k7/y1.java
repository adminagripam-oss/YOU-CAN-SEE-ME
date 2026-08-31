package k7;

import t6.g;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class y1 implements g.b, g.c {

    /* renamed from: e  reason: collision with root package name */
    public static final y1 f8800e = new y1();

    private y1() {
    }

    @Override // t6.g
    public t6.g G(g.c cVar) {
        return g.b.a.c(this, cVar);
    }

    @Override // t6.g
    public t6.g M(t6.g gVar) {
        return g.b.a.d(this, gVar);
    }

    @Override // t6.g.b, t6.g
    public g.b c(g.c cVar) {
        return g.b.a.b(this, cVar);
    }

    @Override // t6.g
    public Object g(Object obj, c7.p pVar) {
        return g.b.a.a(this, obj, pVar);
    }

    @Override // t6.g.b
    public g.c getKey() {
        return this;
    }
}
