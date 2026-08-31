package m6;

import android.util.Size;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class a6 implements t0.g1 {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9227a;

    /* renamed from: b  reason: collision with root package name */
    private final b f9228b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9229a;

        static {
            int[] iArr = new int[t0.x1.values().length];
            f9229a = iArr;
            try {
                iArr[t0.x1.SD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9229a[t0.x1.HD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9229a[t0.x1.FHD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9229a[t0.x1.UHD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9229a[t0.x1.LOWEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9229a[t0.x1.HIGHEST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public r0.y a(List list, r0.p pVar) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(a6.i(((t0.y1) it.next()).b()));
            }
            boolean z7 = pVar != null;
            if (arrayList.size() != 0) {
                if (arrayList.size() != 1) {
                    return z7 ? r0.y.g(arrayList, pVar) : r0.y.f(arrayList);
                }
                r0.v vVar = (r0.v) arrayList.get(0);
                return z7 ? r0.y.e(vVar, pVar) : r0.y.d(vVar);
            }
            throw new IllegalArgumentException("List of at least one Quality must be supplied to create QualitySelector.");
        }
    }

    public a6(v4 v4Var) {
        this(v4Var, new b());
    }

    public static r0.v i(t0.x1 x1Var) {
        switch (a.f9229a[x1Var.ordinal()]) {
            case 1:
                return r0.v.f11267a;
            case 2:
                return r0.v.f11268b;
            case 3:
                return r0.v.f11269c;
            case 4:
                return r0.v.f11270d;
            case 5:
                return r0.v.f11271e;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return r0.v.f11272f;
            default:
                throw new IllegalArgumentException("VideoQuality " + x1Var + " is unhandled by QualitySelectorHostApiImpl.");
        }
    }

    @Override // m6.t0.g1
    public t0.n1 b(Long l8, t0.x1 x1Var) {
        y.p pVar = (y.p) this.f9227a.h(l8.longValue());
        Objects.requireNonNull(pVar);
        Size k8 = r0.y.k(pVar, i(x1Var));
        return new t0.n1.a().c(Long.valueOf(k8.getWidth())).b(Long.valueOf(k8.getHeight())).a();
    }

    @Override // m6.t0.g1
    public void c(Long l8, List list, Long l9) {
        r0.p pVar;
        v4 v4Var = this.f9227a;
        b bVar = this.f9228b;
        if (l9 == null) {
            pVar = null;
        } else {
            pVar = (r0.p) v4Var.h(l9.longValue());
            Objects.requireNonNull(pVar);
        }
        v4Var.a(bVar.a(list, pVar), l8.longValue());
    }

    a6(v4 v4Var, b bVar) {
        this.f9227a = v4Var;
        this.f9228b = bVar;
    }
}
