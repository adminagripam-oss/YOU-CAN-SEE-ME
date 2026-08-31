package k7;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract /* synthetic */ class g {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [k7.e1, java.lang.Object, k7.a] */
    public static final e1 a(d0 d0Var, t6.g gVar, f0 f0Var, c7.p pVar) {
        t6.g d8 = z.d(d0Var, gVar);
        n1 n1Var = f0Var.f() ? new n1(d8, pVar) : new t1(d8, true);
        n1Var.G0(f0Var, n1Var, pVar);
        return n1Var;
    }

    public static /* synthetic */ e1 b(d0 d0Var, t6.g gVar, f0 f0Var, c7.p pVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            gVar = t6.h.f11916e;
        }
        if ((i8 & 2) != 0) {
            f0Var = f0.DEFAULT;
        }
        return f.a(d0Var, gVar, f0Var, pVar);
    }
}
