package y;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import b0.g3;
import b0.h3;
import b0.q2;
import b0.u0;
import b0.u2;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class h2 {

    /* renamed from: d  reason: collision with root package name */
    private g3 f12829d;

    /* renamed from: e  reason: collision with root package name */
    private g3 f12830e;

    /* renamed from: f  reason: collision with root package name */
    private g3 f12831f;

    /* renamed from: g  reason: collision with root package name */
    private u2 f12832g;

    /* renamed from: h  reason: collision with root package name */
    private g3 f12833h;

    /* renamed from: i  reason: collision with root package name */
    private Rect f12834i;

    /* renamed from: k  reason: collision with root package name */
    private b0.h0 f12836k;

    /* renamed from: l  reason: collision with root package name */
    private b0.h0 f12837l;

    /* renamed from: m  reason: collision with root package name */
    private String f12838m;

    /* renamed from: a  reason: collision with root package name */
    private final Set f12826a = new HashSet();

    /* renamed from: b  reason: collision with root package name */
    private final Object f12827b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private a f12828c = a.INACTIVE;

    /* renamed from: j  reason: collision with root package name */
    private Matrix f12835j = new Matrix();

    /* renamed from: n  reason: collision with root package name */
    private q2 f12839n = q2.b();

    /* renamed from: o  reason: collision with root package name */
    private q2 f12840o = q2.b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum a {
        ACTIVE,
        INACTIVE
    }

    /* loaded from: classes.dex */
    public interface b {
        void d(h2 h2Var);

        void f(h2 h2Var);

        void m(h2 h2Var);

        void n(h2 h2Var);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public h2(g3 g3Var) {
        this.f12830e = g3Var;
        this.f12831f = g3Var;
    }

    private void S(b bVar) {
        this.f12826a.remove(bVar);
    }

    private void a(b bVar) {
        this.f12826a.add(bVar);
    }

    public abstract g3.a A(b0.u0 u0Var);

    public Rect B() {
        return this.f12834i;
    }

    public boolean C(int i8) {
        for (Integer num : y()) {
            if (l0.u0.b(i8, num.intValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean D(b0.h0 h0Var) {
        int n8 = n();
        if (n8 == -1 || n8 == 0) {
            return false;
        }
        if (n8 != 1) {
            if (n8 == 2) {
                return h0Var.i();
            }
            throw new AssertionError("Unknown mirrorMode: " + n8);
        }
        return true;
    }

    public g3 E(b0.g0 g0Var, g3 g3Var, g3 g3Var2) {
        b0.a2 d02;
        if (g3Var2 != null) {
            d02 = b0.a2.e0(g3Var2);
            d02.f0(g0.m.F);
        } else {
            d02 = b0.a2.d0();
        }
        if (this.f12830e.a(b0.p1.f4309j) || this.f12830e.a(b0.p1.f4313n)) {
            u0.a aVar = b0.p1.f4317r;
            if (d02.a(aVar)) {
                d02.f0(aVar);
            }
        }
        g3 g3Var3 = this.f12830e;
        u0.a aVar2 = b0.p1.f4317r;
        if (g3Var3.a(aVar2)) {
            u0.a aVar3 = b0.p1.f4315p;
            if (d02.a(aVar3) && ((o0.c) this.f12830e.b(aVar2)).d() != null) {
                d02.f0(aVar3);
            }
        }
        for (u0.a aVar4 : this.f12830e.d()) {
            b0.u0.x(d02, d02, this.f12830e, aVar4);
        }
        if (g3Var != null) {
            for (u0.a aVar5 : g3Var.d()) {
                if (!aVar5.c().equals(g0.m.F.c())) {
                    b0.u0.x(d02, d02, g3Var, aVar5);
                }
            }
        }
        if (d02.a(b0.p1.f4313n)) {
            u0.a aVar6 = b0.p1.f4309j;
            if (d02.a(aVar6)) {
                d02.f0(aVar6);
            }
        }
        u0.a aVar7 = b0.p1.f4317r;
        if (d02.a(aVar7) && ((o0.c) d02.b(aVar7)).a() != 0) {
            d02.Y(g3.f4206z, Boolean.TRUE);
        }
        return M(g0Var, A(d02));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void F() {
        this.f12828c = a.ACTIVE;
        I();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void G() {
        this.f12828c = a.INACTIVE;
        I();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void H() {
        for (b bVar : this.f12826a) {
            bVar.n(this);
        }
    }

    public final void I() {
        int ordinal = this.f12828c.ordinal();
        if (ordinal == 0) {
            for (b bVar : this.f12826a) {
                bVar.m(this);
            }
        } else if (ordinal == 1) {
            for (b bVar2 : this.f12826a) {
                bVar2.d(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void J() {
        for (b bVar : this.f12826a) {
            bVar.f(this);
        }
    }

    public void K() {
    }

    public void L() {
    }

    protected abstract g3 M(b0.g0 g0Var, g3.a aVar);

    public void N() {
    }

    public void O() {
    }

    protected abstract u2 P(b0.u0 u0Var);

    protected abstract u2 Q(u2 u2Var, u2 u2Var2);

    public void R() {
    }

    public void T(l lVar) {
        g1.e.a(true);
    }

    public void U(Matrix matrix) {
        this.f12835j = new Matrix(matrix);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean V(int i8) {
        int V = ((b0.p1) j()).V(-1);
        if (V == -1 || V != i8) {
            g3.a A = A(this.f12830e);
            k0.e.a(A, i8);
            this.f12830e = A.d();
            b0.h0 g8 = g();
            this.f12831f = g8 == null ? this.f12830e : E(g8.l(), this.f12829d, this.f12833h);
            return true;
        }
        return false;
    }

    public void W(Rect rect) {
        this.f12834i = rect;
    }

    public final void X(b0.h0 h0Var) {
        R();
        synchronized (this.f12827b) {
            b0.h0 h0Var2 = this.f12836k;
            if (h0Var == h0Var2) {
                S(h0Var2);
                this.f12836k = null;
            }
            b0.h0 h0Var3 = this.f12837l;
            if (h0Var == h0Var3) {
                S(h0Var3);
                this.f12837l = null;
            }
        }
        this.f12832g = null;
        this.f12834i = null;
        this.f12831f = this.f12830e;
        this.f12829d = null;
        this.f12833h = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void Y(List list) {
        if (list.isEmpty()) {
            return;
        }
        this.f12839n = (q2) list.get(0);
        if (list.size() > 1) {
            this.f12840o = (q2) list.get(1);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            for (b0.b1 b1Var : ((q2) it.next()).n()) {
                if (b1Var.g() == null) {
                    b1Var.s(getClass());
                }
            }
        }
    }

    public void Z(u2 u2Var, u2 u2Var2) {
        this.f12832g = Q(u2Var, u2Var2);
    }

    public void a0(b0.u0 u0Var) {
        this.f12832g = P(u0Var);
    }

    public final void b(b0.h0 h0Var, b0.h0 h0Var2, g3 g3Var, g3 g3Var2) {
        synchronized (this.f12827b) {
            this.f12836k = h0Var;
            this.f12837l = h0Var2;
            a(h0Var);
            if (h0Var2 != null) {
                a(h0Var2);
            }
        }
        this.f12829d = g3Var;
        this.f12833h = g3Var2;
        this.f12831f = E(h0Var.l(), this.f12829d, this.f12833h);
        K();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g3 c() {
        return this.f12830e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d() {
        return ((b0.p1) this.f12831f).T(-1);
    }

    public u2 e() {
        return this.f12832g;
    }

    public Size f() {
        u2 u2Var = this.f12832g;
        if (u2Var != null) {
            return u2Var.e();
        }
        return null;
    }

    public b0.h0 g() {
        b0.h0 h0Var;
        synchronized (this.f12827b) {
            h0Var = this.f12836k;
        }
        return h0Var;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b0.d0 h() {
        synchronized (this.f12827b) {
            b0.h0 h0Var = this.f12836k;
            if (h0Var == null) {
                return b0.d0.f4163a;
            }
            return h0Var.p();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String i() {
        b0.h0 g8 = g();
        return ((b0.h0) g1.e.k(g8, "No camera attached to use case: " + this)).l().g();
    }

    public g3 j() {
        return this.f12831f;
    }

    public abstract g3 k(boolean z7, h3 h3Var);

    public l l() {
        return null;
    }

    public int m() {
        return this.f12831f.B();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int n() {
        return ((b0.p1) this.f12831f).W(-1);
    }

    public String o() {
        g3 g3Var = this.f12831f;
        String U = g3Var.U("<UnknownUseCase-" + hashCode() + ">");
        Objects.requireNonNull(U);
        return U;
    }

    public String p() {
        return this.f12838m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int q(b0.h0 h0Var) {
        return r(h0Var, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int r(b0.h0 h0Var, boolean z7) {
        int i8 = h0Var.l().i(z());
        return !h0Var.j() && z7 ? d0.r.u(-i8) : i8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public q1 s() {
        b0.h0 g8 = g();
        Size f8 = f();
        if (g8 == null || f8 == null) {
            return null;
        }
        Rect B = B();
        if (B == null) {
            B = new Rect(0, 0, f8.getWidth(), f8.getHeight());
        }
        return new q1(f8, B, q(g8));
    }

    public b0.h0 t() {
        b0.h0 h0Var;
        synchronized (this.f12827b) {
            h0Var = this.f12837l;
        }
        return h0Var;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String u() {
        if (t() == null) {
            return null;
        }
        return t().l().g();
    }

    public q2 v() {
        return this.f12840o;
    }

    public Matrix w() {
        return this.f12835j;
    }

    public q2 x() {
        return this.f12839n;
    }

    protected Set y() {
        return Collections.emptySet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int z() {
        return ((b0.p1) this.f12831f).V(0);
    }
}
