package r;

import android.content.Context;
import android.media.CamcorderProfile;
import android.util.Pair;
import android.util.Size;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class d2 implements b0.e0 {

    /* renamed from: a  reason: collision with root package name */
    private final Map f10428a;

    /* renamed from: b  reason: collision with root package name */
    private final f f10429b;

    /* loaded from: classes.dex */
    class a implements f {
        a() {
        }

        @Override // r.f
        public CamcorderProfile a(int i8, int i9) {
            return CamcorderProfile.get(i8, i9);
        }

        @Override // r.f
        public boolean b(int i8, int i9) {
            return CamcorderProfile.hasProfile(i8, i9);
        }
    }

    public d2(Context context, Object obj, Set set) {
        this(context, new a(), obj, set);
    }

    private void c(Context context, s.o0 o0Var, Set set) {
        g1.e.j(context);
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            this.f10428a.put(str, new j4(context, str, o0Var, this.f10429b));
        }
    }

    @Override // b0.e0
    public b0.w2 a(int i8, String str, int i9, Size size) {
        j4 j4Var = (j4) this.f10428a.get(str);
        if (j4Var != null) {
            return j4Var.M(i8, i9, size);
        }
        return null;
    }

    @Override // b0.e0
    public Pair b(int i8, String str, List list, Map map, boolean z7, boolean z8) {
        g1.e.b(!map.isEmpty(), "No new use cases to be bound.");
        j4 j4Var = (j4) this.f10428a.get(str);
        if (j4Var != null) {
            return j4Var.A(i8, list, map, z7, z8);
        }
        throw new IllegalArgumentException("No such camera id in supported combination list: " + str);
    }

    d2(Context context, f fVar, Object obj, Set set) {
        this.f10428a = new HashMap();
        g1.e.j(fVar);
        this.f10429b = fVar;
        c(context, obj instanceof s.o0 ? (s.o0) obj : s.o0.a(context), set);
    }
}
