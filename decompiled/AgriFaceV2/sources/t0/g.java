package t0;

import b0.h1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public abstract class g implements h1 {
    public static g h(int i8, int i9, List list, List list2) {
        g1.e.b(!list2.isEmpty(), "Should contain at least one VideoProfile.");
        h1.c cVar = (h1.c) list2.get(0);
        return new a(i8, i9, Collections.unmodifiableList(new ArrayList(list)), Collections.unmodifiableList(new ArrayList(list2)), !list.isEmpty() ? (h1.a) list.get(0) : null, cVar);
    }

    public static g i(h1 h1Var) {
        return h(h1Var.g(), h1Var.a(), h1Var.b(), h1Var.c());
    }

    public abstract h1.a j();

    public abstract h1.c k();
}
