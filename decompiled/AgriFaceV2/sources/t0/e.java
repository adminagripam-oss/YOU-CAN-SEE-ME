package t0;

import b0.g1;
import b0.h1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import y.a0;
/* loaded from: classes.dex */
public class e implements g1 {

    /* renamed from: c  reason: collision with root package name */
    private final g1 f11641c;

    /* renamed from: d  reason: collision with root package name */
    private final a0 f11642d;

    /* renamed from: e  reason: collision with root package name */
    private final Map f11643e = new HashMap();

    public e(g1 g1Var, a0 a0Var) {
        this.f11641c = g1Var;
        this.f11642d = a0Var;
    }

    private static h1 c(h1 h1Var, a0 a0Var) {
        if (h1Var == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (h1.c cVar : h1Var.c()) {
            if (z0.b.f(cVar, a0Var)) {
                arrayList.add(cVar);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return h1.b.h(h1Var.g(), h1Var.a(), h1Var.b(), arrayList);
    }

    private h1 d(int i8) {
        if (this.f11643e.containsKey(Integer.valueOf(i8))) {
            return (h1) this.f11643e.get(Integer.valueOf(i8));
        }
        if (this.f11641c.b(i8)) {
            h1 c8 = c(this.f11641c.a(i8), this.f11642d);
            this.f11643e.put(Integer.valueOf(i8), c8);
            return c8;
        }
        return null;
    }

    @Override // b0.g1
    public h1 a(int i8) {
        return d(i8);
    }

    @Override // b0.g1
    public boolean b(int i8) {
        return this.f11641c.b(i8) && d(i8) != null;
    }
}
