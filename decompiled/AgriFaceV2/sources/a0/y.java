package a0;

import a0.u;
import a0.y0;
import android.util.Size;
import androidx.camera.core.e;
import b0.n1;
import b0.o1;
import b0.q2;
import b0.s0;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class y {

    /* renamed from: f  reason: collision with root package name */
    private static int f136f;

    /* renamed from: g  reason: collision with root package name */
    static final j0.b f137g = new j0.b();

    /* renamed from: a  reason: collision with root package name */
    private final n1 f138a;

    /* renamed from: b  reason: collision with root package name */
    private final b0.s0 f139b;

    /* renamed from: c  reason: collision with root package name */
    private final u f140c;

    /* renamed from: d  reason: collision with root package name */
    private final o0 f141d;

    /* renamed from: e  reason: collision with root package name */
    private final u.c f142e;

    public y(n1 n1Var, Size size, y.l lVar, boolean z7, Size size2, int i8) {
        d0.q.a();
        this.f138a = n1Var;
        this.f139b = s0.a.j(n1Var).h();
        u uVar = new u();
        this.f140c = uVar;
        Executor e02 = n1Var.e0(e0.c.d());
        Objects.requireNonNull(e02);
        o0 o0Var = new o0(e02, null);
        this.f141d = o0Var;
        int B = n1Var.B();
        int i9 = i();
        n1Var.d0();
        u.c m8 = u.c.m(size, B, i9, z7, null, size2, i8);
        this.f142e = m8;
        o0Var.x(uVar.v(m8));
    }

    private l b(int i8, b0.r0 r0Var, e1 e1Var, u0 u0Var) {
        ArrayList arrayList = new ArrayList();
        String valueOf = String.valueOf(r0Var.hashCode());
        List<b0.t0> a8 = r0Var.a();
        Objects.requireNonNull(a8);
        for (b0.t0 t0Var : a8) {
            s0.a aVar = new s0.a();
            aVar.v(this.f139b.k());
            aVar.e(this.f139b.g());
            aVar.a(e1Var.p());
            aVar.f(this.f142e.k());
            aVar.t(l());
            if (k0.b.i(this.f142e.d())) {
                if (f137g.a()) {
                    aVar.d(b0.s0.f4371i, Integer.valueOf(e1Var.n()));
                }
                aVar.d(b0.s0.f4372j, Integer.valueOf(g(e1Var)));
            }
            aVar.e(t0Var.b().g());
            aVar.g(valueOf, Integer.valueOf(t0Var.a()));
            aVar.r(i8);
            aVar.c(this.f142e.a());
            arrayList.add(aVar.h());
        }
        return new l(arrayList, u0Var);
    }

    private b0.r0 c() {
        b0.r0 Z = this.f138a.Z(y.y.b());
        Objects.requireNonNull(Z);
        return Z;
    }

    private p0 d(int i8, b0.r0 r0Var, e1 e1Var, u0 u0Var, i4.d dVar) {
        return new p0(r0Var, e1Var.m(), e1Var.i(), e1Var.n(), e1Var.k(), e1Var.o(), u0Var, dVar, i8);
    }

    private int i() {
        Integer num = (Integer) this.f138a.f(n1.M, null);
        if (num != null) {
            return num.intValue();
        }
        Integer num2 = (Integer) this.f138a.f(o1.f4299h, null);
        return (num2 == null || num2.intValue() != 4101) ? 256 : 4101;
    }

    private boolean l() {
        return this.f142e.h() != null;
    }

    public void a() {
        d0.q.a();
        this.f140c.r();
        this.f141d.v();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g1.d e(e1 e1Var, u0 u0Var, i4.d dVar) {
        d0.q.a();
        b0.r0 c8 = c();
        int i8 = f136f;
        f136f = i8 + 1;
        return new g1.d(b(i8, c8, e1Var, u0Var), d(i8, c8, e1Var, u0Var, dVar));
    }

    public q2.b f(Size size) {
        q2.b q8 = q2.b.q(this.f138a, size);
        q8.h(this.f142e.k());
        if (this.f142e.h() != null) {
            q8.x(this.f142e.h());
        }
        return q8;
    }

    int g(e1 e1Var) {
        return ((e1Var.l() != null) && d0.r.g(e1Var.i(), this.f142e.j())) ? e1Var.h() == 0 ? 100 : 95 : e1Var.k();
    }

    public int h() {
        d0.q.a();
        return this.f140c.h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(y0.b bVar) {
        d0.q.a();
        this.f142e.b().accept(bVar);
    }

    public void k(e.a aVar) {
        d0.q.a();
        this.f140c.u(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(p0 p0Var) {
        d0.q.a();
        this.f142e.i().accept(p0Var);
    }
}
