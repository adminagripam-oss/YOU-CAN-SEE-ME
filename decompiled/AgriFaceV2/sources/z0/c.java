package z0;

import android.util.Range;
import android.util.Size;
import b0.h1;
import x0.k;
/* loaded from: classes.dex */
public abstract class c {
    public static h1.c a(h1.c cVar, Size size, Range range) {
        return h1.c.a(cVar.e(), cVar.i(), k.e(cVar.c(), cVar.b(), cVar.b(), cVar.f(), cVar.f(), size.getWidth(), cVar.k(), size.getHeight(), cVar.h(), range), cVar.f(), size.getWidth(), size.getHeight(), cVar.j(), cVar.b(), cVar.d(), cVar.g());
    }

    public static h1.c b(h1 h1Var) {
        if (h1Var == null || h1Var.c().isEmpty()) {
            return null;
        }
        return (h1.c) h1Var.c().get(0);
    }
}
