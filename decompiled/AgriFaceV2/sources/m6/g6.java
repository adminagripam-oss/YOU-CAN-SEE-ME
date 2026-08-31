package m6;

import java.util.Objects;
import m6.t0;
import o0.c;
/* loaded from: classes.dex */
public class g6 implements t0.o1 {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9302a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9303b;

    /* loaded from: classes.dex */
    public static class a {
        public o0.c a(o0.d dVar, o0.a aVar, o0.b bVar) {
            c.a aVar2 = new c.a();
            if (dVar != null) {
                aVar2.f(dVar);
            }
            if (aVar != null) {
                aVar2.d(aVar);
            }
            if (bVar != null) {
                aVar2.e(bVar);
            }
            return aVar2.a();
        }
    }

    public g6(v4 v4Var) {
        this(v4Var, new a());
    }

    @Override // m6.t0.o1
    public void c(Long l8, Long l9, Long l10, Long l11) {
        o0.d dVar;
        o0.a aVar;
        v4 v4Var = this.f9302a;
        a aVar2 = this.f9303b;
        o0.b bVar = null;
        if (l9 == null) {
            dVar = null;
        } else {
            dVar = (o0.d) v4Var.h(l9.longValue());
            Objects.requireNonNull(dVar);
        }
        if (l11 == null) {
            aVar = null;
        } else {
            aVar = (o0.a) this.f9302a.h(l11.longValue());
            Objects.requireNonNull(aVar);
        }
        if (l10 != null) {
            o0.b bVar2 = (o0.b) this.f9302a.h(l10.longValue());
            Objects.requireNonNull(bVar2);
            bVar = bVar2;
        }
        v4Var.a(aVar2.a(dVar, aVar, bVar), l8.longValue());
    }

    g6(v4 v4Var, a aVar) {
        this.f9302a = v4Var;
        this.f9303b = aVar;
    }
}
