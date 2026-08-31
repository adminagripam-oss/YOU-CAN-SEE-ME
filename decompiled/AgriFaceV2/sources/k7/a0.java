package k7;

import t6.e;
import t6.g;
/* loaded from: classes.dex */
public abstract class a0 extends t6.a implements t6.e {

    /* renamed from: f  reason: collision with root package name */
    public static final a f8716f = new a(null);

    /* loaded from: classes.dex */
    public static final class a extends t6.b {

        /* renamed from: k7.a0$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static final class C0117a extends d7.l implements c7.l {

            /* renamed from: f  reason: collision with root package name */
            public static final C0117a f8717f = new C0117a();

            C0117a() {
                super(1);
            }

            @Override // c7.l
            /* renamed from: b */
            public final a0 g(g.b bVar) {
                if (bVar instanceof a0) {
                    return (a0) bVar;
                }
                return null;
            }
        }

        private a() {
            super(t6.e.f11913d, C0117a.f8717f);
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }
    }

    public a0() {
        super(t6.e.f11913d);
    }

    @Override // t6.a, t6.g
    public t6.g G(g.c cVar) {
        return e.a.b(this, cVar);
    }

    public abstract void P(t6.g gVar, Runnable runnable);

    public boolean Q(t6.g gVar) {
        return true;
    }

    public a0 R(int i8) {
        p7.p.a(i8);
        return new p7.o(this, i8);
    }

    @Override // t6.a, t6.g.b, t6.g
    public g.b c(g.c cVar) {
        return e.a.a(this, cVar);
    }

    @Override // t6.e
    public final void j(t6.d dVar) {
        d7.k.c(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        ((p7.j) dVar).q();
    }

    @Override // t6.e
    public final t6.d q(t6.d dVar) {
        return new p7.j(this, dVar);
    }

    public String toString() {
        return h0.a(this) + '@' + h0.b(this);
    }
}
