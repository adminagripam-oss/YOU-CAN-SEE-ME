package k7;
/* loaded from: classes.dex */
public abstract class k1 extends w implements o0, a1 {

    /* renamed from: h  reason: collision with root package name */
    public l1 f8746h;

    @Override // k7.a1
    public boolean b() {
        return true;
    }

    @Override // k7.o0
    public void dispose() {
        x().r0(this);
    }

    @Override // k7.a1
    public p1 h() {
        return null;
    }

    @Override // p7.s
    public String toString() {
        return h0.a(this) + '@' + h0.b(this) + "[job@" + h0.b(x()) + ']';
    }

    public final l1 x() {
        l1 l1Var = this.f8746h;
        if (l1Var != null) {
            return l1Var;
        }
        d7.k.n("job");
        return null;
    }

    public final void y(l1 l1Var) {
        this.f8746h = l1Var;
    }
}
