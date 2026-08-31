package p7;
/* loaded from: classes.dex */
public abstract class h {
    public static final void a(t6.g gVar, Throwable th) {
        for (k7.b0 b0Var : g.a()) {
            try {
                b0Var.w(gVar, th);
            } catch (Throwable th2) {
                g.b(k7.c0.b(th, th2));
            }
        }
        try {
            r6.b.a(th, new i(gVar));
        } catch (Throwable unused) {
        }
        g.b(th);
    }
}
