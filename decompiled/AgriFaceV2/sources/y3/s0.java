package y3;
/* loaded from: classes.dex */
public final class s0 {

    /* renamed from: a  reason: collision with root package name */
    private static s0 f13268a;

    private s0() {
    }

    public static synchronized s0 a() {
        s0 s0Var;
        synchronized (s0.class) {
            if (f13268a == null) {
                f13268a = new s0();
            }
            s0Var = f13268a;
        }
        return s0Var;
    }

    public static void b() {
        r0.a();
    }
}
