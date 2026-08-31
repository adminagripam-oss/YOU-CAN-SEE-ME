package b0;
/* loaded from: classes.dex */
public class n2 extends j1 {

    /* renamed from: c  reason: collision with root package name */
    private final d0 f4287c;

    public n2(d0 d0Var, s2 s2Var) {
        super(d0Var);
        this.f4287c = d0Var;
    }

    @Override // b0.j1, b0.d0
    public d0 a() {
        return this.f4287c;
    }

    @Override // b0.j1, y.k
    public i4.d g(int i8) {
        return !d0.p.b(null, 7) ? f0.n.n(new IllegalStateException("ExposureCompensation is not supported")) : this.f4287c.g(i8);
    }

    @Override // b0.j1, y.k
    public i4.d i() {
        return this.f4287c.i();
    }

    @Override // b0.j1, y.k
    public i4.d k(float f8) {
        return !d0.p.b(null, 0) ? f0.n.n(new IllegalStateException("Zoom is not supported")) : this.f4287c.k(f8);
    }

    @Override // b0.j1, y.k
    public i4.d o(y.d0 d0Var) {
        y.d0 a8 = d0.p.a(null, d0Var);
        return a8 == null ? f0.n.n(new IllegalStateException("FocusMetering is not supported")) : this.f4287c.o(a8);
    }

    @Override // b0.j1, y.k
    public i4.d q(boolean z7) {
        return !d0.p.b(null, 6) ? f0.n.n(new IllegalStateException("Torch is not supported")) : this.f4287c.q(z7);
    }
}
