package k7;
/* loaded from: classes.dex */
public abstract class a extends l1 implements t6.d, d0 {

    /* renamed from: g  reason: collision with root package name */
    private final t6.g f8715g;

    public a(t6.g gVar, boolean z7, boolean z8) {
        super(z8);
        if (z7) {
            a0((e1) gVar.c(e1.f8727b));
        }
        this.f8715g = gVar.M(this);
    }

    protected void D0(Object obj) {
        y(obj);
    }

    protected void E0(Throwable th, boolean z7) {
    }

    protected void F0(Object obj) {
    }

    public final void G0(f0 f0Var, Object obj, c7.p pVar) {
        f0Var.e(pVar, obj, this);
    }

    @Override // k7.l1
    protected String J() {
        return h0.a(this) + " was cancelled";
    }

    @Override // k7.l1
    public final void Z(Throwable th) {
        c0.a(this.f8715g, th);
    }

    @Override // k7.l1, k7.e1
    public boolean b() {
        return super.b();
    }

    @Override // t6.d
    public final t6.g getContext() {
        return this.f8715g;
    }

    @Override // k7.d0
    public t6.g h() {
        return this.f8715g;
    }

    @Override // t6.d
    public final void i(Object obj) {
        Object g02 = g0(y.d(obj, null, 1, null));
        if (g02 == m1.f8762b) {
            return;
        }
        D0(g02);
    }

    @Override // k7.l1
    public String i0() {
        String b8 = z.b(this.f8715g);
        if (b8 == null) {
            return super.i0();
        }
        return '\"' + b8 + "\":" + super.i0();
    }

    @Override // k7.l1
    protected final void n0(Object obj) {
        if (!(obj instanceof u)) {
            F0(obj);
            return;
        }
        u uVar = (u) obj;
        E0(uVar.f8789a, uVar.a());
    }
}
