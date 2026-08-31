package k7;

import java.util.concurrent.CancellationException;
import t6.g;
/* loaded from: classes.dex */
public interface e1 extends g.b {

    /* renamed from: b  reason: collision with root package name */
    public static final b f8727b = b.f8728e;

    /* loaded from: classes.dex */
    public static final class a {
        public static /* synthetic */ void a(e1 e1Var, CancellationException cancellationException, int i8, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i8 & 1) != 0) {
                cancellationException = null;
            }
            e1Var.d(cancellationException);
        }

        public static Object b(e1 e1Var, Object obj, c7.p pVar) {
            return g.b.a.a(e1Var, obj, pVar);
        }

        public static g.b c(e1 e1Var, g.c cVar) {
            return g.b.a.b(e1Var, cVar);
        }

        public static /* synthetic */ o0 d(e1 e1Var, boolean z7, boolean z8, c7.l lVar, int i8, Object obj) {
            if (obj == null) {
                if ((i8 & 1) != 0) {
                    z7 = false;
                }
                if ((i8 & 2) != 0) {
                    z8 = true;
                }
                return e1Var.A(z7, z8, lVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: invokeOnCompletion");
        }

        public static t6.g e(e1 e1Var, g.c cVar) {
            return g.b.a.c(e1Var, cVar);
        }

        public static t6.g f(e1 e1Var, t6.g gVar) {
            return g.b.a.d(e1Var, gVar);
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements g.c {

        /* renamed from: e  reason: collision with root package name */
        static final /* synthetic */ b f8728e = new b();

        private b() {
        }
    }

    o0 A(boolean z7, boolean z8, c7.l lVar);

    CancellationException E();

    boolean b();

    void d(CancellationException cancellationException);

    boolean start();

    p t(r rVar);
}
