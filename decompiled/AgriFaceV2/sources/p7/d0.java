package p7;
/* loaded from: classes.dex */
public class d0 extends k7.a implements v6.e {

    /* renamed from: h  reason: collision with root package name */
    public final t6.d f10139h;

    public d0(t6.g gVar, t6.d dVar) {
        super(gVar, true, true);
        this.f10139h = dVar;
    }

    @Override // k7.a
    protected void D0(Object obj) {
        t6.d dVar = this.f10139h;
        dVar.i(k7.y.a(obj, dVar));
    }

    @Override // v6.e
    public final v6.e e() {
        t6.d dVar = this.f10139h;
        if (dVar instanceof v6.e) {
            return (v6.e) dVar;
        }
        return null;
    }

    @Override // k7.l1
    protected final boolean e0() {
        return true;
    }

    @Override // k7.l1
    protected void y(Object obj) {
        t6.d b8;
        b8 = u6.c.b(this.f10139h);
        k.c(b8, k7.y.a(obj, this.f10139h), null, 2, null);
    }
}
