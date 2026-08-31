package a1;

import androidx.camera.video.internal.compat.quirk.ExtraSupportedQualityQuirk;
import b0.g0;
import b0.g1;
import b0.h1;
import b0.l2;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class b implements g1 {

    /* renamed from: c  reason: collision with root package name */
    private final g1 f153c;

    /* renamed from: d  reason: collision with root package name */
    private Map f154d;

    public b(g1 g1Var, l2 l2Var, g0 g0Var, m.a aVar) {
        this.f153c = g1Var;
        List c8 = l2Var.c(ExtraSupportedQualityQuirk.class);
        if (c8.isEmpty()) {
            return;
        }
        g1.e.l(c8.size() == 1);
        Map f8 = ((ExtraSupportedQualityQuirk) c8.get(0)).f(g0Var, g1Var, aVar);
        if (f8 != null) {
            this.f154d = new HashMap(f8);
        }
    }

    private h1 c(int i8) {
        Map map = this.f154d;
        return (map == null || !map.containsKey(Integer.valueOf(i8))) ? this.f153c.a(i8) : (h1) this.f154d.get(Integer.valueOf(i8));
    }

    @Override // b0.g1
    public h1 a(int i8) {
        return c(i8);
    }

    @Override // b0.g1
    public boolean b(int i8) {
        return c(i8) != null;
    }
}
