package z3;
/* loaded from: classes.dex */
final class kc extends h6 {

    /* renamed from: g  reason: collision with root package name */
    private final mc f13840g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public kc(mc mcVar, int i8) {
        super(mcVar.size(), i8);
        this.f13840g = mcVar;
    }

    @Override // z3.h6
    protected final Object b(int i8) {
        return this.f13840g.get(i8);
    }
}
