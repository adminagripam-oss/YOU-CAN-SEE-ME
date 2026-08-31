package p7;
/* loaded from: classes.dex */
public abstract class d {

    /* renamed from: a */
    private static final h0 f10138a = new h0("CLOSED");

    public static final e b(e eVar) {
        while (true) {
            Object f8 = eVar.f();
            if (f8 == f10138a) {
                return eVar;
            }
            e eVar2 = (e) f8;
            if (eVar2 != null) {
                eVar = eVar2;
            } else if (eVar.j()) {
                return eVar;
            }
        }
    }

    public static final Object c(e0 e0Var, long j8, c7.p pVar) {
        while (true) {
            if (e0Var.f10143g >= j8 && !e0Var.h()) {
                return f0.a(e0Var);
            }
            Object f8 = e0Var.f();
            if (f8 == f10138a) {
                return f0.a(f10138a);
            }
            e0 e0Var2 = (e0) ((e) f8);
            if (e0Var2 == null) {
                e0Var2 = (e0) pVar.f(Long.valueOf(e0Var.f10143g + 1), e0Var);
                if (e0Var.l(e0Var2)) {
                    if (e0Var.h()) {
                        e0Var.k();
                    }
                }
            }
            e0Var = e0Var2;
        }
    }
}
