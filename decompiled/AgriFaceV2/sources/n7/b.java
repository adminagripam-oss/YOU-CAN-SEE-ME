package n7;

import m7.r;
/* loaded from: classes.dex */
abstract class b extends o7.d {

    /* renamed from: d  reason: collision with root package name */
    private final c7.p f9774d;

    public b(c7.p pVar, t6.g gVar, int i8, m7.a aVar) {
        super(gVar, i8, aVar);
        this.f9774d = pVar;
    }

    static /* synthetic */ Object j(b bVar, r rVar, t6.d dVar) {
        Object c8;
        Object f8 = bVar.f9774d.f(rVar, dVar);
        c8 = u6.d.c();
        return f8 == c8 ? f8 : r6.r.f11372a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // o7.d
    public Object e(r rVar, t6.d dVar) {
        return j(this, rVar, dVar);
    }

    @Override // o7.d
    public String toString() {
        return "block[" + this.f9774d + "] -> " + super.toString();
    }
}
