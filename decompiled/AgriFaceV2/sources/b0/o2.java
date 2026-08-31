package b0;

import android.util.Range;
import android.util.Rational;
/* loaded from: classes.dex */
public class o2 extends k1 {

    /* renamed from: b  reason: collision with root package name */
    private final g0 f4301b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4302c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f4303d;

    /* renamed from: e  reason: collision with root package name */
    private final z f4304e;

    /* loaded from: classes.dex */
    class a implements y.b0 {
        a() {
        }

        @Override // y.b0
        public Range a() {
            return new Range(0, 0);
        }

        @Override // y.b0
        public Rational b() {
            return Rational.ZERO;
        }
    }

    public o2(g0 g0Var, z zVar) {
        super(g0Var);
        this.f4302c = false;
        this.f4303d = false;
        this.f4301b = g0Var;
        this.f4304e = zVar;
        zVar.s(null);
        r(zVar.P());
        q(zVar.H());
    }

    @Override // b0.k1, b0.g0
    public g0 a() {
        return this.f4301b;
    }

    @Override // b0.k1, y.p
    public androidx.lifecycle.p m() {
        return !d0.p.b(null, 0) ? new androidx.lifecycle.s(g0.g.e(1.0f, 1.0f, 1.0f, 0.0f)) : this.f4301b.m();
    }

    @Override // b0.k1, y.p
    public y.b0 n() {
        return !d0.p.b(null, 7) ? new a() : this.f4301b.n();
    }

    public z p() {
        return this.f4304e;
    }

    public void q(boolean z7) {
        this.f4303d = z7;
    }

    public void r(boolean z7) {
        this.f4302c = z7;
    }
}
