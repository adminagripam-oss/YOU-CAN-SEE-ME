package androidx.lifecycle;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class n0 {

    /* renamed from: a  reason: collision with root package name */
    private final Map f3814a = new LinkedHashMap();

    public final void a() {
        for (k0 k0Var : this.f3814a.values()) {
            k0Var.a();
        }
        this.f3814a.clear();
    }

    public final k0 b(String str) {
        d7.k.e(str, "key");
        return (k0) this.f3814a.get(str);
    }

    public final Set c() {
        return new HashSet(this.f3814a.keySet());
    }

    public final void d(String str, k0 k0Var) {
        d7.k.e(str, "key");
        d7.k.e(k0Var, "viewModel");
        k0 k0Var2 = (k0) this.f3814a.put(str, k0Var);
        if (k0Var2 != null) {
            k0Var2.d();
        }
    }
}
