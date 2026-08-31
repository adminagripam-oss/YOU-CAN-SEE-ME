package k7;
/* loaded from: classes.dex */
public class h1 extends l1 implements s {

    /* renamed from: g  reason: collision with root package name */
    private final boolean f8736g;

    public h1(e1 e1Var) {
        super(true);
        a0(e1Var);
        this.f8736g = D0();
    }

    private final boolean D0() {
        l1 x7;
        p W = W();
        q qVar = W instanceof q ? (q) W : null;
        if (qVar != null && (x7 = qVar.x()) != null) {
            while (!x7.T()) {
                p W2 = x7.W();
                q qVar2 = W2 instanceof q ? (q) W2 : null;
                if (qVar2 != null) {
                    x7 = qVar2.x();
                    if (x7 == null) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // k7.l1
    public boolean T() {
        return this.f8736g;
    }

    @Override // k7.l1
    public boolean U() {
        return true;
    }
}
