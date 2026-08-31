package t0;

import android.util.Rational;
import b0.g1;
import b0.h1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import x0.k;
import y.f1;
import y0.o1;
import y0.q1;
/* loaded from: classes.dex */
public class b implements g1 {

    /* renamed from: c  reason: collision with root package name */
    private final g1 f11635c;

    /* renamed from: d  reason: collision with root package name */
    private final m.a f11636d;

    /* renamed from: e  reason: collision with root package name */
    private final Map f11637e = new HashMap();

    public b(g1 g1Var, m.a aVar) {
        this.f11635c = g1Var;
        this.f11636d = aVar;
    }

    private h1 c(h1 h1Var, int i8, int i9) {
        h1.c cVar;
        if (h1Var == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(h1Var.c());
        Iterator it = h1Var.c().iterator();
        while (true) {
            if (!it.hasNext()) {
                cVar = null;
                break;
            }
            cVar = (h1.c) it.next();
            if (cVar.g() == 0) {
                break;
            }
        }
        h1.c k8 = k(g(cVar, i8, i9), this.f11636d);
        if (k8 != null) {
            arrayList.add(k8);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return h1.b.h(h1Var.g(), h1Var.a(), h1Var.b(), arrayList);
    }

    private static int d(int i8) {
        if (i8 == 0 || i8 == 1 || i8 == 2 || i8 == 3 || i8 == 4) {
            return 5;
        }
        throw new IllegalArgumentException("Unexpected HDR format: " + i8);
    }

    private static String e(int i8) {
        return h1.f(i8);
    }

    private static int f(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 != 3) {
                        if (i8 == 4) {
                            return -1;
                        }
                        throw new IllegalArgumentException("Unexpected HDR format: " + i8);
                    }
                    return 8192;
                }
                return 4096;
            }
            return 2;
        }
        return 1;
    }

    private static h1.c g(h1.c cVar, int i8, int i9) {
        if (cVar == null) {
            return null;
        }
        int e8 = cVar.e();
        String i10 = cVar.i();
        int j8 = cVar.j();
        if (i8 != cVar.g()) {
            e8 = d(i8);
            i10 = e(e8);
            j8 = f(i8);
        }
        return h1.c.a(e8, i10, j(cVar.c(), i9, cVar.b()), cVar.f(), cVar.k(), cVar.h(), j8, i9, cVar.d(), i8);
    }

    private h1 h(int i8) {
        if (this.f11637e.containsKey(Integer.valueOf(i8))) {
            return (h1) this.f11637e.get(Integer.valueOf(i8));
        }
        if (this.f11635c.b(i8)) {
            h1 c8 = c(this.f11635c.a(i8), 1, 10);
            this.f11637e.put(Integer.valueOf(i8), c8);
            return c8;
        }
        return null;
    }

    private static h1.c i(h1.c cVar, int i8) {
        return h1.c.a(cVar.e(), cVar.i(), i8, cVar.f(), cVar.k(), cVar.h(), cVar.j(), cVar.b(), cVar.d(), cVar.g());
    }

    private static int j(int i8, int i9, int i10) {
        if (i9 == i10) {
            return i8;
        }
        int doubleValue = (int) (i8 * new Rational(i9, i10).doubleValue());
        if (f1.f("BackupHdrProfileEncoderProfilesProvider")) {
            f1.a("BackupHdrProfileEncoderProfilesProvider", String.format("Base Bitrate(%dbps) * Bit Depth Ratio (%d / %d) = %d", Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(doubleValue)));
        }
        return doubleValue;
    }

    static h1.c k(h1.c cVar, m.a aVar) {
        if (cVar == null) {
            return null;
        }
        o1 f8 = k.f(cVar);
        q1 q1Var = (q1) aVar.apply(f8);
        if (q1Var == null || !q1Var.a(cVar.k(), cVar.h())) {
            return null;
        }
        int e8 = f8.e();
        int intValue = ((Integer) q1Var.c().clamp(Integer.valueOf(e8))).intValue();
        return intValue == e8 ? cVar : i(cVar, intValue);
    }

    @Override // b0.g1
    public h1 a(int i8) {
        return h(i8);
    }

    @Override // b0.g1
    public boolean b(int i8) {
        return this.f11635c.b(i8) && h(i8) != null;
    }
}
