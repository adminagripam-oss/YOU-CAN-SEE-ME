package y0;

import android.util.Range;
/* loaded from: classes.dex */
public class l1 implements q1 {

    /* renamed from: a  reason: collision with root package name */
    private final q1 f13150a;

    public l1(q1 q1Var) {
        g1.e.a(q1Var.i());
        this.f13150a = q1Var;
    }

    @Override // y0.q1
    public int b() {
        return this.f13150a.f();
    }

    @Override // y0.q1
    public Range c() {
        return this.f13150a.c();
    }

    @Override // y0.q1
    public Range d(int i8) {
        return this.f13150a.e(i8);
    }

    @Override // y0.q1
    public Range e(int i8) {
        return this.f13150a.d(i8);
    }

    @Override // y0.q1
    public int f() {
        return this.f13150a.b();
    }

    @Override // y0.q1
    public Range g() {
        return this.f13150a.j();
    }

    @Override // y0.q1
    public boolean h(int i8, int i9) {
        return this.f13150a.h(i9, i8);
    }

    @Override // y0.q1
    public boolean i() {
        return this.f13150a.i();
    }

    @Override // y0.q1
    public Range j() {
        return this.f13150a.g();
    }
}
