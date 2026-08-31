package a1;

import android.util.Size;
import androidx.camera.video.internal.compat.quirk.StretchedVideoResolutionQuirk;
import b0.g1;
import b0.h1;
import b0.l2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class c implements g1 {

    /* renamed from: c  reason: collision with root package name */
    private final g1 f155c;

    /* renamed from: d  reason: collision with root package name */
    private final l2 f156d;

    /* renamed from: e  reason: collision with root package name */
    private final Map f157e = new HashMap();

    public c(g1 g1Var, l2 l2Var) {
        this.f155c = g1Var;
        this.f156d = l2Var;
    }

    private h1 c(h1 h1Var, Size size) {
        ArrayList arrayList = new ArrayList();
        for (h1.c cVar : h1Var.c()) {
            arrayList.add(d(cVar, size));
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return h1.b.h(h1Var.g(), h1Var.a(), h1Var.b(), arrayList);
    }

    private static h1.c d(h1.c cVar, Size size) {
        return h1.c.a(cVar.e(), cVar.i(), cVar.c(), cVar.f(), size.getWidth(), size.getHeight(), cVar.j(), cVar.b(), cVar.d(), cVar.g());
    }

    private Size e(int i8) {
        for (StretchedVideoResolutionQuirk stretchedVideoResolutionQuirk : this.f156d.c(StretchedVideoResolutionQuirk.class)) {
            if (stretchedVideoResolutionQuirk != null) {
                return stretchedVideoResolutionQuirk.f(i8);
            }
        }
        return null;
    }

    private h1 f(int i8) {
        h1 h1Var;
        if (this.f157e.containsKey(Integer.valueOf(i8))) {
            return (h1) this.f157e.get(Integer.valueOf(i8));
        }
        if (this.f155c.b(i8)) {
            h1 a8 = this.f155c.a(i8);
            Objects.requireNonNull(a8);
            h1Var = a8;
            Size e8 = e(i8);
            if (e8 != null) {
                h1Var = c(h1Var, e8);
            }
        } else {
            h1Var = null;
        }
        this.f157e.put(Integer.valueOf(i8), h1Var);
        return h1Var;
    }

    @Override // b0.g1
    public h1 a(int i8) {
        return f(i8);
    }

    @Override // b0.g1
    public boolean b(int i8) {
        return this.f155c.b(i8) && f(i8) != null;
    }
}
