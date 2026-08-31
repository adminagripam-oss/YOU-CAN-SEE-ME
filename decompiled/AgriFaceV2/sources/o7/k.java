package o7;
/* loaded from: classes.dex */
final class k implements t6.d, v6.e {

    /* renamed from: e  reason: collision with root package name */
    private final t6.d f9903e;

    /* renamed from: f  reason: collision with root package name */
    private final t6.g f9904f;

    public k(t6.d dVar, t6.g gVar) {
        this.f9903e = dVar;
        this.f9904f = gVar;
    }

    @Override // v6.e
    public v6.e e() {
        t6.d dVar = this.f9903e;
        if (dVar instanceof v6.e) {
            return (v6.e) dVar;
        }
        return null;
    }

    @Override // t6.d
    public t6.g getContext() {
        return this.f9904f;
    }

    @Override // t6.d
    public void i(Object obj) {
        this.f9903e.i(obj);
    }
}
