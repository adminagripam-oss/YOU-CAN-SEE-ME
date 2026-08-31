package b0;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.InputConfiguration;
import android.util.Range;
import android.util.Size;
import b0.i;
import b0.q2;
import b0.s0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class q2 {

    /* renamed from: j  reason: collision with root package name */
    private static final List f4329j = Arrays.asList(1, 5, 3);

    /* renamed from: a  reason: collision with root package name */
    private final List f4330a;

    /* renamed from: b  reason: collision with root package name */
    private final f f4331b;

    /* renamed from: c  reason: collision with root package name */
    private final List f4332c;

    /* renamed from: d  reason: collision with root package name */
    private final List f4333d;

    /* renamed from: e  reason: collision with root package name */
    private final List f4334e;

    /* renamed from: f  reason: collision with root package name */
    private final d f4335f;

    /* renamed from: g  reason: collision with root package name */
    private final s0 f4336g;

    /* renamed from: h  reason: collision with root package name */
    private final int f4337h;

    /* renamed from: i  reason: collision with root package name */
    private InputConfiguration f4338i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: f  reason: collision with root package name */
        d f4344f;

        /* renamed from: g  reason: collision with root package name */
        InputConfiguration f4345g;

        /* renamed from: i  reason: collision with root package name */
        f f4347i;

        /* renamed from: a  reason: collision with root package name */
        final Set f4339a = new LinkedHashSet();

        /* renamed from: b  reason: collision with root package name */
        final s0.a f4340b = new s0.a();

        /* renamed from: c  reason: collision with root package name */
        final List f4341c = new ArrayList();

        /* renamed from: d  reason: collision with root package name */
        final List f4342d = new ArrayList();

        /* renamed from: e  reason: collision with root package name */
        final List f4343e = new ArrayList();

        /* renamed from: h  reason: collision with root package name */
        int f4346h = 0;

        a() {
        }
    }

    /* loaded from: classes.dex */
    public static class b extends a {
        public static b q(g3 g3Var, Size size) {
            e R = g3Var.R(null);
            if (R != null) {
                b bVar = new b();
                R.a(size, g3Var, bVar);
                return bVar;
            }
            throw new IllegalStateException("Implementation is missing option unpacker for " + g3Var.U(g3Var.toString()));
        }

        public b A(int i8) {
            if (i8 != 0) {
                this.f4340b.x(i8);
            }
            return this;
        }

        public b a(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                n nVar = (n) it.next();
                this.f4340b.c(nVar);
                if (!this.f4343e.contains(nVar)) {
                    this.f4343e.add(nVar);
                }
            }
            return this;
        }

        public b b(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                f((CameraDevice.StateCallback) it.next());
            }
            return this;
        }

        public b c(Collection collection) {
            this.f4340b.a(collection);
            return this;
        }

        public b d(List list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                k((CameraCaptureSession.StateCallback) it.next());
            }
            return this;
        }

        public b e(n nVar) {
            this.f4340b.c(nVar);
            if (!this.f4343e.contains(nVar)) {
                this.f4343e.add(nVar);
            }
            return this;
        }

        public b f(CameraDevice.StateCallback stateCallback) {
            if (this.f4341c.contains(stateCallback)) {
                return this;
            }
            this.f4341c.add(stateCallback);
            return this;
        }

        public b g(u0 u0Var) {
            this.f4340b.e(u0Var);
            return this;
        }

        public b h(b1 b1Var) {
            return i(b1Var, y.a0.f12730d);
        }

        public b i(b1 b1Var, y.a0 a0Var) {
            this.f4339a.add(f.a(b1Var).b(a0Var).a());
            return this;
        }

        public b j(n nVar) {
            this.f4340b.c(nVar);
            return this;
        }

        public b k(CameraCaptureSession.StateCallback stateCallback) {
            if (this.f4342d.contains(stateCallback)) {
                return this;
            }
            this.f4342d.add(stateCallback);
            return this;
        }

        public b l(b1 b1Var) {
            return m(b1Var, y.a0.f12730d, null, -1);
        }

        public b m(b1 b1Var, y.a0 a0Var, String str, int i8) {
            this.f4339a.add(f.a(b1Var).d(str).b(a0Var).c(i8).a());
            this.f4340b.f(b1Var);
            return this;
        }

        public b n(String str, Object obj) {
            this.f4340b.g(str, obj);
            return this;
        }

        public q2 o() {
            return new q2(new ArrayList(this.f4339a), new ArrayList(this.f4341c), new ArrayList(this.f4342d), new ArrayList(this.f4343e), this.f4340b.h(), this.f4344f, this.f4345g, this.f4346h, this.f4347i);
        }

        public b p() {
            this.f4339a.clear();
            this.f4340b.i();
            return this;
        }

        public List r() {
            return Collections.unmodifiableList(this.f4343e);
        }

        public boolean s(n nVar) {
            return this.f4340b.o(nVar) || this.f4343e.remove(nVar);
        }

        public b t(d dVar) {
            this.f4344f = dVar;
            return this;
        }

        public b u(Range range) {
            this.f4340b.q(range);
            return this;
        }

        public b v(u0 u0Var) {
            this.f4340b.s(u0Var);
            return this;
        }

        public b w(InputConfiguration inputConfiguration) {
            this.f4345g = inputConfiguration;
            return this;
        }

        public b x(b1 b1Var) {
            this.f4347i = f.a(b1Var).a();
            return this;
        }

        public b y(int i8) {
            if (i8 != 0) {
                this.f4340b.u(i8);
            }
            return this;
        }

        public b z(int i8) {
            this.f4340b.v(i8);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c implements d {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicBoolean f4348a = new AtomicBoolean(false);

        /* renamed from: b  reason: collision with root package name */
        private final d f4349b;

        public c(d dVar) {
            this.f4349b = dVar;
        }

        @Override // b0.q2.d
        public void a(q2 q2Var, g gVar) {
            if (this.f4348a.get()) {
                return;
            }
            this.f4349b.a(q2Var, gVar);
        }

        public void b() {
            this.f4348a.set(true);
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a(q2 q2Var, g gVar);
    }

    /* loaded from: classes.dex */
    public interface e {
        void a(Size size, g3 g3Var, b bVar);
    }

    /* loaded from: classes.dex */
    public static abstract class f {

        /* loaded from: classes.dex */
        public static abstract class a {
            public abstract f a();

            public abstract a b(y.a0 a0Var);

            public abstract a c(int i8);

            public abstract a d(String str);

            public abstract a e(List list);

            public abstract a f(int i8);
        }

        public static a a(b1 b1Var) {
            return new i.b().g(b1Var).e(Collections.emptyList()).d(null).c(-1).f(-1).b(y.a0.f12730d);
        }

        public abstract y.a0 b();

        public abstract int c();

        public abstract String d();

        public abstract List e();

        public abstract b1 f();

        public abstract int g();
    }

    /* loaded from: classes.dex */
    public enum g {
        SESSION_ERROR_SURFACE_NEEDS_RESET,
        SESSION_ERROR_UNKNOWN
    }

    /* loaded from: classes.dex */
    public static final class h extends a {

        /* renamed from: j  reason: collision with root package name */
        private final j0.f f4353j = new j0.f();

        /* renamed from: k  reason: collision with root package name */
        private boolean f4354k = true;

        /* renamed from: l  reason: collision with root package name */
        private boolean f4355l = false;

        /* renamed from: m  reason: collision with root package name */
        private List f4356m = new ArrayList();

        private List d() {
            ArrayList arrayList = new ArrayList();
            for (f fVar : this.f4339a) {
                arrayList.add(fVar.f());
                for (b1 b1Var : fVar.e()) {
                    arrayList.add(b1Var);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(q2 q2Var, g gVar) {
            for (d dVar : this.f4356m) {
                dVar.a(q2Var, gVar);
            }
        }

        private void g(Range range) {
            Range range2 = u2.f4433a;
            if (range.equals(range2)) {
                return;
            }
            if (this.f4340b.l().equals(range2)) {
                this.f4340b.q(range);
            } else if (this.f4340b.l().equals(range)) {
            } else {
                this.f4354k = false;
                y.f1.a("ValidatingBuilder", "Different ExpectedFrameRateRange values");
            }
        }

        private void h(int i8) {
            if (i8 != 0) {
                this.f4340b.u(i8);
            }
        }

        private void i(int i8) {
            if (i8 != 0) {
                this.f4340b.x(i8);
            }
        }

        public void b(q2 q2Var) {
            s0 j8 = q2Var.j();
            if (j8.k() != -1) {
                this.f4355l = true;
                this.f4340b.v(q2.e(j8.k(), this.f4340b.n()));
            }
            g(j8.e());
            h(j8.h());
            i(j8.l());
            this.f4340b.b(q2Var.j().j());
            this.f4341c.addAll(q2Var.c());
            this.f4342d.addAll(q2Var.k());
            this.f4340b.a(q2Var.i());
            this.f4343e.addAll(q2Var.m());
            if (q2Var.d() != null) {
                this.f4356m.add(q2Var.d());
            }
            if (q2Var.g() != null) {
                this.f4345g = q2Var.g();
            }
            this.f4339a.addAll(q2Var.h());
            this.f4340b.m().addAll(j8.i());
            if (!d().containsAll(this.f4340b.m())) {
                y.f1.a("ValidatingBuilder", "Invalid configuration due to capture request surfaces are not a subset of surfaces");
                this.f4354k = false;
            }
            if (q2Var.l() != this.f4346h && q2Var.l() != 0 && this.f4346h != 0) {
                y.f1.a("ValidatingBuilder", "Invalid configuration due to that two non-default session types are set");
                this.f4354k = false;
            } else if (q2Var.l() != 0) {
                this.f4346h = q2Var.l();
            }
            if (q2Var.f4331b != null) {
                if (this.f4347i == q2Var.f4331b || this.f4347i == null) {
                    this.f4347i = q2Var.f4331b;
                } else {
                    y.f1.a("ValidatingBuilder", "Invalid configuration due to that two different postview output configs are set");
                    this.f4354k = false;
                }
            }
            this.f4340b.e(j8.g());
        }

        public q2 c() {
            if (this.f4354k) {
                ArrayList arrayList = new ArrayList(this.f4339a);
                this.f4353j.d(arrayList);
                return new q2(arrayList, new ArrayList(this.f4341c), new ArrayList(this.f4342d), new ArrayList(this.f4343e), this.f4340b.h(), !this.f4356m.isEmpty() ? new d() { // from class: b0.r2
                    @Override // b0.q2.d
                    public final void a(q2 q2Var, q2.g gVar) {
                        q2.h.this.f(q2Var, gVar);
                    }
                } : null, this.f4345g, this.f4346h, this.f4347i);
            }
            throw new IllegalArgumentException("Unsupported session configuration combination");
        }

        public boolean e() {
            return this.f4355l && this.f4354k;
        }
    }

    q2(List list, List list2, List list3, List list4, s0 s0Var, d dVar, InputConfiguration inputConfiguration, int i8, f fVar) {
        this.f4330a = list;
        this.f4332c = Collections.unmodifiableList(list2);
        this.f4333d = Collections.unmodifiableList(list3);
        this.f4334e = Collections.unmodifiableList(list4);
        this.f4335f = dVar;
        this.f4336g = s0Var;
        this.f4338i = inputConfiguration;
        this.f4337h = i8;
        this.f4331b = fVar;
    }

    public static q2 b() {
        return new q2(new ArrayList(), new ArrayList(0), new ArrayList(0), new ArrayList(0), new s0.a().h(), null, null, 0, null);
    }

    public static int e(int i8, int i9) {
        List list = f4329j;
        return list.indexOf(Integer.valueOf(i8)) >= list.indexOf(Integer.valueOf(i9)) ? i8 : i9;
    }

    public List c() {
        return this.f4332c;
    }

    public d d() {
        return this.f4335f;
    }

    public u0 f() {
        return this.f4336g.g();
    }

    public InputConfiguration g() {
        return this.f4338i;
    }

    public List h() {
        return this.f4330a;
    }

    public List i() {
        return this.f4336g.c();
    }

    public s0 j() {
        return this.f4336g;
    }

    public List k() {
        return this.f4333d;
    }

    public int l() {
        return this.f4337h;
    }

    public List m() {
        return this.f4334e;
    }

    public List n() {
        ArrayList arrayList = new ArrayList();
        for (f fVar : this.f4330a) {
            arrayList.add(fVar.f());
            for (b1 b1Var : fVar.e()) {
                arrayList.add(b1Var);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int o() {
        return this.f4336g.k();
    }
}
