package b0;

import android.graphics.Rect;
import b0.q2;
import java.util.Collections;
import java.util.List;
import y.u0;
/* loaded from: classes.dex */
public interface d0 extends y.k {

    /* renamed from: a  reason: collision with root package name */
    public static final d0 f4163a = new b();

    /* loaded from: classes.dex */
    class a implements a0.k {
        a() {
        }

        @Override // a0.k
        public i4.d a() {
            return f0.n.p(null);
        }

        @Override // a0.k
        public i4.d b() {
            return f0.n.p(null);
        }
    }

    /* loaded from: classes.dex */
    class b implements d0 {
        b() {
        }

        @Override // b0.d0
        public void b(q2.b bVar) {
        }

        @Override // b0.d0
        public u0 e() {
            return null;
        }

        @Override // y.k
        public i4.d g(int i8) {
            return f0.n.p(0);
        }

        @Override // b0.d0
        public i4.d h(List list, int i8, int i9) {
            return f0.n.p(Collections.emptyList());
        }

        @Override // y.k
        public i4.d i() {
            return f0.n.p(null);
        }

        @Override // b0.d0
        public void j() {
        }

        @Override // y.k
        public i4.d k(float f8) {
            return f0.n.p(null);
        }

        @Override // b0.d0
        public void l(u0 u0Var) {
        }

        @Override // b0.d0
        public Rect m() {
            return new Rect();
        }

        @Override // b0.d0
        public void n(int i8) {
        }

        @Override // y.k
        public i4.d o(y.d0 d0Var) {
            return f0.n.p(y.e0.b());
        }

        @Override // y.k
        public i4.d q(boolean z7) {
            return f0.n.p(null);
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends Exception {

        /* renamed from: e  reason: collision with root package name */
        private p f4165e;

        public c(p pVar) {
            this.f4165e = pVar;
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a();

        void b(List list);
    }

    default d0 a() {
        return this;
    }

    void b(q2.b bVar);

    default void c(u0.i iVar) {
    }

    default i4.d d(int i8, int i9) {
        return f0.n.p(new a());
    }

    u0 e();

    default void f() {
    }

    i4.d h(List list, int i8, int i9);

    void j();

    void l(u0 u0Var);

    Rect m();

    void n(int i8);

    default void p() {
    }
}
