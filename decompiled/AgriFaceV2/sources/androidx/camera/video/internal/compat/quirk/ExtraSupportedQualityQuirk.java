package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import android.util.Range;
import android.util.Size;
import b0.g0;
import b0.g1;
import b0.h1;
import b0.h2;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import k0.d;
import r0.d2;
import x0.k;
import y0.q1;
import z0.c;
/* loaded from: classes.dex */
public class ExtraSupportedQualityQuirk implements h2 {
    private Map g(g0 g0Var, g1 g1Var, m.a aVar) {
        h1 a8;
        h1.c b8;
        if (!"1".equals(g0Var.g()) || g1Var.b(4) || (b8 = c.b((a8 = g1Var.a(1)))) == null) {
            return null;
        }
        Range h8 = h(b8, aVar);
        Size size = d.f8306d;
        h1.b h9 = h1.b.h(a8.g(), a8.a(), a8.b(), Collections.singletonList(c.a(b8, size, h8)));
        HashMap hashMap = new HashMap();
        hashMap.put(4, h9);
        if (d.c(size) > d.c(new Size(b8.k(), b8.h()))) {
            hashMap.put(1, h9);
        }
        return hashMap;
    }

    private static Range h(h1.c cVar, m.a aVar) {
        q1 q1Var = (q1) aVar.apply(k.f(cVar));
        return q1Var != null ? q1Var.c() : d2.f11033b;
    }

    private static boolean i() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto c".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean j() {
        return i();
    }

    public Map f(g0 g0Var, g1 g1Var, m.a aVar) {
        return i() ? g(g0Var, g1Var, aVar) : Collections.emptyMap();
    }
}
