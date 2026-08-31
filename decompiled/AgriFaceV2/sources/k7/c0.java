package k7;
/* loaded from: classes.dex */
public abstract class c0 {
    public static final void a(t6.g gVar, Throwable th) {
        try {
            b0 b0Var = (b0) gVar.c(b0.f8719a);
            if (b0Var != null) {
                b0Var.w(gVar, th);
            } else {
                p7.h.a(gVar, th);
            }
        } catch (Throwable th2) {
            p7.h.a(gVar, b(th, th2));
        }
    }

    public static final Throwable b(Throwable th, Throwable th2) {
        if (th == th2) {
            return th;
        }
        RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
        r6.b.a(runtimeException, th);
        return runtimeException;
    }
}
