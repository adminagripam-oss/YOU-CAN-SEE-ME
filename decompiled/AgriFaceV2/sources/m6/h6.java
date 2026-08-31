package m6;

import android.util.Size;
import m6.t0;
/* loaded from: classes.dex */
public class h6 implements t0.p1 {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9315a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9316b;

    /* loaded from: classes.dex */
    public static class a {
        public o0.d a(Size size, Long l8) {
            return new o0.d(size, l8.intValue());
        }
    }

    public h6(v4 v4Var) {
        this(v4Var, new a());
    }

    @Override // m6.t0.p1
    public void b(Long l8, t0.n1 n1Var, Long l9) {
        o0.d a8;
        if (n1Var == null && l9 == null) {
            a8 = o0.d.f9817c;
        } else if (n1Var == null) {
            throw new IllegalArgumentException("A bound size must be specified if a non-null fallback rule is specified to create a valid ResolutionStrategy.");
        } else {
            a8 = this.f9316b.a(new Size(n1Var.c().intValue(), n1Var.b().intValue()), l9);
        }
        this.f9315a.a(a8, l8.longValue());
    }

    h6(v4 v4Var, a aVar) {
        this.f9315a = v4Var;
        this.f9316b = aVar;
    }
}
