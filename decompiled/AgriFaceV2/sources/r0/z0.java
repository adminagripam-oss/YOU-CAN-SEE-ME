package r0;

import android.util.Size;
import b0.l2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class z0 implements d1 {

    /* renamed from: b  reason: collision with root package name */
    private final b0.g1 f11297b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f11298c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f11299d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private final Map f11300e = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public z0(int i8, b0.g0 g0Var, m.a aVar) {
        boolean z7 = i8 == 0 || i8 == 1;
        g1.e.b(z7, "Not a supported video capabilities source: " + i8);
        b0.g1 j8 = g0Var.j();
        l2 c8 = androidx.camera.video.internal.compat.quirk.a.c();
        b0.g1 bVar = new a1.b(j8, c8, g0Var, aVar);
        b0.g1 cVar = new a1.c(i8 == 1 ? new t0.f(bVar, v.b(), Collections.singleton(y.a0.f12730d), g0Var.l(34), aVar) : bVar, c8);
        this.f11297b = new a1.d(h(g0Var) ? new t0.b(cVar, aVar) : cVar, g0Var, c8);
        for (y.a0 a0Var : g0Var.b()) {
            o oVar = new o(new t0.e(this.f11297b, a0Var));
            if (!oVar.f().isEmpty()) {
                this.f11299d.put(a0Var, oVar);
            }
        }
        this.f11298c = g0Var.f();
    }

    private o e(y.a0 a0Var) {
        if (b0.f1.c(a0Var, g())) {
            return new o(new t0.e(this.f11297b, a0Var));
        }
        return null;
    }

    private o f(y.a0 a0Var) {
        Map map;
        if (a0Var.e()) {
            map = this.f11299d;
        } else if (!this.f11300e.containsKey(a0Var)) {
            o e8 = e(a0Var);
            this.f11300e.put(a0Var, e8);
            return e8;
        } else {
            map = this.f11300e;
        }
        return (o) map.get(a0Var);
    }

    private static boolean h(b0.g0 g0Var) {
        for (y.a0 a0Var : g0Var.b()) {
            Integer valueOf = Integer.valueOf(a0Var.b());
            int a8 = a0Var.a();
            if (valueOf.equals(3) && a8 == 10) {
                return true;
            }
        }
        return false;
    }

    @Override // r0.d1
    public t0.g a(Size size, y.a0 a0Var) {
        o f8 = f(a0Var);
        if (f8 == null) {
            return null;
        }
        return f8.b(size);
    }

    @Override // r0.d1
    public v b(Size size, y.a0 a0Var) {
        o f8 = f(a0Var);
        return f8 == null ? v.f11273g : f8.c(size);
    }

    @Override // r0.d1
    public t0.g c(v vVar, y.a0 a0Var) {
        o f8 = f(a0Var);
        if (f8 == null) {
            return null;
        }
        return f8.e(vVar);
    }

    @Override // r0.d1
    public List d(y.a0 a0Var) {
        o f8 = f(a0Var);
        return f8 == null ? new ArrayList() : f8.f();
    }

    public Set g() {
        return this.f11299d.keySet();
    }
}
