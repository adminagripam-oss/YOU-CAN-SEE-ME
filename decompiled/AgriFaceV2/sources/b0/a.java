package b0;

import android.util.Range;
import android.util.Size;
import b0.u2;
import java.util.List;
/* loaded from: classes.dex */
public abstract class a {
    public static a a(w2 w2Var, int i8, Size size, y.a0 a0Var, List list, u0 u0Var, Range range) {
        return new b(w2Var, i8, size, a0Var, list, u0Var, range);
    }

    public abstract List b();

    public abstract y.a0 c();

    public abstract int d();

    public abstract u0 e();

    public abstract Size f();

    public abstract w2 g();

    public abstract Range h();

    public u2 i(u0 u0Var) {
        u2.a d8 = u2.a(f()).b(c()).d(u0Var);
        if (h() != null) {
            d8.c(h());
        }
        return d8.a();
    }
}
