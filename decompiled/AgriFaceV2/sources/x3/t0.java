package x3;
/* loaded from: classes.dex */
final class t0 extends r0 {

    /* renamed from: g  reason: collision with root package name */
    private final v0 f12698g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t0(v0 v0Var, int i8) {
        super(v0Var.size(), i8);
        this.f12698g = v0Var;
    }

    @Override // x3.r0
    protected final Object b(int i8) {
        return this.f12698g.get(i8);
    }
}
