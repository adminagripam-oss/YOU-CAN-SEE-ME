package k7;
/* loaded from: classes.dex */
public final class v1 {

    /* renamed from: a  reason: collision with root package name */
    public static final v1 f8794a = new v1();

    /* renamed from: b  reason: collision with root package name */
    private static final ThreadLocal f8795b = p7.m0.a(new p7.h0("ThreadLocalEventLoop"));

    private v1() {
    }

    public final q0 a() {
        ThreadLocal threadLocal = f8795b;
        q0 q0Var = (q0) threadLocal.get();
        if (q0Var == null) {
            q0 a8 = t0.a();
            threadLocal.set(a8);
            return a8;
        }
        return q0Var;
    }

    public final void b() {
        f8795b.set(null);
    }

    public final void c(q0 q0Var) {
        f8795b.set(q0Var);
    }
}
