package n0;

import android.graphics.Rect;
import android.util.Size;
import d0.r;
import java.util.UUID;
import l0.l0;
/* loaded from: classes.dex */
public abstract class f {
    public static f h(int i8, int i9, Rect rect, Size size, int i10, boolean z7) {
        return i(i8, i9, rect, size, i10, z7, false);
    }

    public static f i(int i8, int i9, Rect rect, Size size, int i10, boolean z7, boolean z8) {
        return new b(UUID.randomUUID(), i8, i9, rect, size, i10, z7, z8);
    }

    public static f j(l0 l0Var) {
        return h(l0Var.t(), l0Var.p(), l0Var.n(), r.e(l0Var.n(), l0Var.q()), l0Var.q(), l0Var.w());
    }

    public abstract Rect a();

    public abstract int b();

    public abstract int c();

    public abstract Size d();

    public abstract int e();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract UUID f();

    public abstract boolean g();

    public abstract boolean k();
}
