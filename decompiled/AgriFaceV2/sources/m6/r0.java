package m6;

import m6.t0;
/* loaded from: classes.dex */
public class r0 implements t0.g0 {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9449a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9450b;

    /* loaded from: classes.dex */
    public static class a {
        public Boolean a(y.e0 e0Var) {
            return Boolean.valueOf(e0Var.c());
        }
    }

    public r0(v4 v4Var) {
        this(v4Var, new a());
    }

    @Override // m6.t0.g0
    public Boolean e(Long l8) {
        return this.f9450b.a((y.e0) this.f9449a.h(l8.longValue()));
    }

    r0(v4 v4Var, a aVar) {
        this.f9449a = v4Var;
        this.f9450b = aVar;
    }
}
