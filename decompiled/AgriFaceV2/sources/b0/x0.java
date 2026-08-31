package b0;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class x0 {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f4471a = Collections.unmodifiableSet(EnumSet.of(t.PASSIVE_FOCUSED, t.PASSIVE_NOT_FOCUSED, t.LOCKED_FOCUSED, t.LOCKED_NOT_FOCUSED));

    /* renamed from: b  reason: collision with root package name */
    private static final Set f4472b = Collections.unmodifiableSet(EnumSet.of(v.CONVERGED, v.UNKNOWN));

    /* renamed from: c  reason: collision with root package name */
    private static final Set f4473c;

    /* renamed from: d  reason: collision with root package name */
    private static final Set f4474d;

    static {
        r rVar = r.CONVERGED;
        r rVar2 = r.FLASH_REQUIRED;
        r rVar3 = r.UNKNOWN;
        Set unmodifiableSet = Collections.unmodifiableSet(EnumSet.of(rVar, rVar2, rVar3));
        f4473c = unmodifiableSet;
        EnumSet copyOf = EnumSet.copyOf((Collection) unmodifiableSet);
        copyOf.remove(rVar2);
        copyOf.remove(rVar3);
        f4474d = Collections.unmodifiableSet(copyOf);
    }

    public static boolean a(x xVar, boolean z7) {
        boolean z8 = xVar.d() == s.OFF || xVar.d() == s.UNKNOWN || f4471a.contains(xVar.k());
        boolean z9 = xVar.j() == q.OFF;
        boolean z10 = !z7 ? !(z9 || f4473c.contains(xVar.e())) : !(z9 || f4474d.contains(xVar.e()));
        boolean z11 = (xVar.i() == u.OFF) || f4472b.contains(xVar.f());
        y.f1.a("ConvergenceUtils", "checkCaptureResult, AE=" + xVar.e() + " AF =" + xVar.k() + " AWB=" + xVar.f());
        return z8 && z10 && z11;
    }
}
