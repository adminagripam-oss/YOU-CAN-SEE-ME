package a4;
/* loaded from: classes.dex */
final class n0 extends e {

    /* renamed from: g  reason: collision with root package name */
    private final p0 f841g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n0(p0 p0Var, int i8) {
        super(p0Var.size(), i8);
        this.f841g = p0Var;
    }

    @Override // a4.e
    protected final Object b(int i8) {
        return this.f841g.get(i8);
    }
}
