package y3;
/* loaded from: classes.dex */
final class d extends v0 {

    /* renamed from: g  reason: collision with root package name */
    private final f f13217g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(f fVar, int i8) {
        super(fVar.size(), i8);
        this.f13217g = fVar;
    }

    @Override // y3.v0
    protected final Object b(int i8) {
        return this.f13217g.get(i8);
    }
}
