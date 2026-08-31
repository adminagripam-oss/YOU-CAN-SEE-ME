package m6;

import m6.t0;
/* loaded from: classes.dex */
public class e implements t0.c {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9267a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9268b;

    /* loaded from: classes.dex */
    public static class a {
        public o0.a a(Long l8, Long l9) {
            return new o0.a(l8.intValue(), l9.intValue());
        }
    }

    public e(v4 v4Var) {
        this(v4Var, new a());
    }

    @Override // m6.t0.c
    public void c(Long l8, Long l9, Long l10) {
        this.f9267a.a(this.f9268b.a(l9, l10), l8.longValue());
    }

    e(v4 v4Var, a aVar) {
        this.f9267a = v4Var;
        this.f9268b = aVar;
    }
}
