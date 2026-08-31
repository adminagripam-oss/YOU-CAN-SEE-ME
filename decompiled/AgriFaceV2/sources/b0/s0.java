package b0;

import android.util.Range;
import b0.u0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class s0 {

    /* renamed from: i  reason: collision with root package name */
    public static final u0.a f4371i = u0.a.a("camerax.core.captureConfig.rotation", Integer.TYPE);

    /* renamed from: j  reason: collision with root package name */
    public static final u0.a f4372j = u0.a.a("camerax.core.captureConfig.jpegQuality", Integer.class);

    /* renamed from: k  reason: collision with root package name */
    private static final u0.a f4373k = u0.a.a("camerax.core.captureConfig.resolvedFrameRate", Range.class);

    /* renamed from: a  reason: collision with root package name */
    final List f4374a;

    /* renamed from: b  reason: collision with root package name */
    final u0 f4375b;

    /* renamed from: c  reason: collision with root package name */
    final int f4376c;

    /* renamed from: d  reason: collision with root package name */
    final boolean f4377d;

    /* renamed from: e  reason: collision with root package name */
    final List f4378e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f4379f;

    /* renamed from: g  reason: collision with root package name */
    private final y2 f4380g;

    /* renamed from: h  reason: collision with root package name */
    private final x f4381h;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final Set f4382a;

        /* renamed from: b  reason: collision with root package name */
        private z1 f4383b;

        /* renamed from: c  reason: collision with root package name */
        private int f4384c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f4385d;

        /* renamed from: e  reason: collision with root package name */
        private List f4386e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f4387f;

        /* renamed from: g  reason: collision with root package name */
        private c2 f4388g;

        /* renamed from: h  reason: collision with root package name */
        private x f4389h;

        public a() {
            this.f4382a = new HashSet();
            this.f4383b = a2.d0();
            this.f4384c = -1;
            this.f4385d = false;
            this.f4386e = new ArrayList();
            this.f4387f = false;
            this.f4388g = c2.g();
        }

        public static a j(g3 g3Var) {
            b k8 = g3Var.k(null);
            if (k8 != null) {
                a aVar = new a();
                k8.a(g3Var, aVar);
                return aVar;
            }
            throw new IllegalStateException("Implementation is missing option unpacker for " + g3Var.U(g3Var.toString()));
        }

        public static a k(s0 s0Var) {
            return new a(s0Var);
        }

        public void a(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                c((n) it.next());
            }
        }

        public void b(y2 y2Var) {
            this.f4388g.f(y2Var);
        }

        public void c(n nVar) {
            if (this.f4386e.contains(nVar)) {
                return;
            }
            this.f4386e.add(nVar);
        }

        public void d(u0.a aVar, Object obj) {
            this.f4383b.Y(aVar, obj);
        }

        public void e(u0 u0Var) {
            for (u0.a aVar : u0Var.d()) {
                this.f4383b.f(aVar, null);
                this.f4383b.F(aVar, u0Var.h(aVar), u0Var.b(aVar));
            }
        }

        public void f(b1 b1Var) {
            this.f4382a.add(b1Var);
        }

        public void g(String str, Object obj) {
            this.f4388g.i(str, obj);
        }

        public s0 h() {
            return new s0(new ArrayList(this.f4382a), f2.b0(this.f4383b), this.f4384c, this.f4385d, new ArrayList(this.f4386e), this.f4387f, y2.c(this.f4388g), this.f4389h);
        }

        public void i() {
            this.f4382a.clear();
        }

        public Range l() {
            return (Range) this.f4383b.f(s0.f4373k, u2.f4433a);
        }

        public Set m() {
            return this.f4382a;
        }

        public int n() {
            return this.f4384c;
        }

        public boolean o(n nVar) {
            return this.f4386e.remove(nVar);
        }

        public void p(x xVar) {
            this.f4389h = xVar;
        }

        public void q(Range range) {
            d(s0.f4373k, range);
        }

        public void r(int i8) {
            this.f4388g.i("CAPTURE_CONFIG_ID_KEY", Integer.valueOf(i8));
        }

        public void s(u0 u0Var) {
            this.f4383b = a2.e0(u0Var);
        }

        public void t(boolean z7) {
            this.f4385d = z7;
        }

        public void u(int i8) {
            if (i8 != 0) {
                d(g3.C, Integer.valueOf(i8));
            }
        }

        public void v(int i8) {
            this.f4384c = i8;
        }

        public void w(boolean z7) {
            this.f4387f = z7;
        }

        public void x(int i8) {
            if (i8 != 0) {
                d(g3.D, Integer.valueOf(i8));
            }
        }

        private a(s0 s0Var) {
            HashSet hashSet = new HashSet();
            this.f4382a = hashSet;
            this.f4383b = a2.d0();
            this.f4384c = -1;
            this.f4385d = false;
            this.f4386e = new ArrayList();
            this.f4387f = false;
            this.f4388g = c2.g();
            hashSet.addAll(s0Var.f4374a);
            this.f4383b = a2.e0(s0Var.f4375b);
            this.f4384c = s0Var.f4376c;
            this.f4386e.addAll(s0Var.c());
            this.f4387f = s0Var.m();
            this.f4388g = c2.h(s0Var.j());
            this.f4385d = s0Var.f4377d;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(g3 g3Var, a aVar);
    }

    s0(List list, u0 u0Var, int i8, boolean z7, List list2, boolean z8, y2 y2Var, x xVar) {
        this.f4374a = list;
        this.f4375b = u0Var;
        this.f4376c = i8;
        this.f4378e = Collections.unmodifiableList(list2);
        this.f4379f = z8;
        this.f4380g = y2Var;
        this.f4381h = xVar;
        this.f4377d = z7;
    }

    public static s0 b() {
        return new a().h();
    }

    public List c() {
        return this.f4378e;
    }

    public x d() {
        return this.f4381h;
    }

    public Range e() {
        Range range = (Range) this.f4375b.f(f4373k, u2.f4433a);
        Objects.requireNonNull(range);
        return range;
    }

    public int f() {
        Object d8 = this.f4380g.d("CAPTURE_CONFIG_ID_KEY");
        if (d8 == null) {
            return -1;
        }
        return ((Integer) d8).intValue();
    }

    public u0 g() {
        return this.f4375b;
    }

    public int h() {
        Integer num = (Integer) this.f4375b.f(g3.C, 0);
        Objects.requireNonNull(num);
        return num.intValue();
    }

    public List i() {
        return Collections.unmodifiableList(this.f4374a);
    }

    public y2 j() {
        return this.f4380g;
    }

    public int k() {
        return this.f4376c;
    }

    public int l() {
        Integer num = (Integer) this.f4375b.f(g3.D, 0);
        Objects.requireNonNull(num);
        return num.intValue();
    }

    public boolean m() {
        return this.f4379f;
    }
}
