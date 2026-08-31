package k7;

import java.util.concurrent.CancellationException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract /* synthetic */ class j1 {
    public static final s a(e1 e1Var) {
        return new h1(e1Var);
    }

    public static /* synthetic */ s b(e1 e1Var, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            e1Var = null;
        }
        return i1.a(e1Var);
    }

    public static final void c(t6.g gVar, CancellationException cancellationException) {
        e1 e1Var = (e1) gVar.c(e1.f8727b);
        if (e1Var != null) {
            e1Var.d(cancellationException);
        }
    }

    public static final void d(e1 e1Var) {
        if (!e1Var.b()) {
            throw e1Var.E();
        }
    }
}
