package o7;

import r6.r;
/* loaded from: classes.dex */
public final class g extends f {
    public g(n7.c cVar, t6.g gVar, int i8, m7.a aVar) {
        super(cVar, gVar, i8, aVar);
    }

    @Override // o7.d
    protected d f(t6.g gVar, int i8, m7.a aVar) {
        return new g(this.f9895d, gVar, i8, aVar);
    }

    @Override // o7.f
    protected Object m(n7.d dVar, t6.d dVar2) {
        Object c8;
        Object a8 = this.f9895d.a(dVar, dVar2);
        c8 = u6.d.c();
        return a8 == c8 ? a8 : r.f11372a;
    }

    public /* synthetic */ g(n7.c cVar, t6.g gVar, int i8, m7.a aVar, int i9, d7.g gVar2) {
        this(cVar, (i9 & 2) != 0 ? t6.h.f11916e : gVar, (i9 & 4) != 0 ? -3 : i8, (i9 & 8) != 0 ? m7.a.SUSPEND : aVar);
    }
}
