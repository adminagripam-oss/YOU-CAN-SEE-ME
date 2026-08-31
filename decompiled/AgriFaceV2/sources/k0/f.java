package k0;

import androidx.camera.core.n;
import b0.r;
import b0.t;
import b0.v;
import b0.x;
import b0.y;
import k0.c;
import y.w0;
/* loaded from: classes.dex */
public final class f extends a {
    public f(int i8, c.a aVar) {
        super(i8, aVar);
    }

    private boolean d(w0 w0Var) {
        x a8 = y.a(w0Var);
        return (a8.k() == t.LOCKED_FOCUSED || a8.k() == t.PASSIVE_FOCUSED) && a8.e() == r.CONVERGED && a8.f() == v.CONVERGED;
    }

    @Override // k0.a, k0.c
    /* renamed from: c */
    public void b(n nVar) {
        if (d(nVar.p())) {
            super.b(nVar);
        } else {
            this.f8297d.a(nVar);
        }
    }
}
