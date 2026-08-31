package b0;

import java.util.Iterator;
import java.util.Set;
/* loaded from: classes.dex */
public final class f1 {

    /* renamed from: a  reason: collision with root package name */
    public static final f1 f4179a = new f1();

    private f1() {
    }

    private final boolean a(y.a0 a0Var, y.a0 a0Var2) {
        g1.e.m(a0Var2.e(), "Fully specified range is not actually fully specified.");
        return a0Var.a() == 0 || a0Var.a() == a0Var2.a();
    }

    private final boolean b(y.a0 a0Var, y.a0 a0Var2) {
        g1.e.m(a0Var2.e(), "Fully specified range is not actually fully specified.");
        int b8 = a0Var.b();
        if (b8 == 0) {
            return true;
        }
        int b9 = a0Var2.b();
        return (b8 == 2 && b9 != 1) || b8 == b9;
    }

    public static final boolean c(y.a0 a0Var, Set set) {
        Object obj;
        d7.k.e(a0Var, "dynamicRangeToTest");
        d7.k.e(set, "fullySpecifiedDynamicRanges");
        if (a0Var.e()) {
            return set.contains(a0Var);
        }
        Iterator it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (f4179a.d(a0Var, (y.a0) obj)) {
                break;
            }
        }
        return obj != null;
    }

    private final boolean d(y.a0 a0Var, y.a0 a0Var2) {
        return a(a0Var, a0Var2) && b(a0Var, a0Var2);
    }
}
