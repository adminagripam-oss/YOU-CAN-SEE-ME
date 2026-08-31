package y3;
/* loaded from: classes.dex */
public abstract class q0 {

    /* renamed from: a  reason: collision with root package name */
    private static p0 f13266a;

    public static synchronized i0 a(e0 e0Var) {
        i0 i0Var;
        synchronized (q0.class) {
            if (f13266a == null) {
                f13266a = new p0(null);
            }
            i0Var = (i0) f13266a.b(e0Var);
        }
        return i0Var;
    }

    public static synchronized i0 b(String str) {
        i0 a8;
        synchronized (q0.class) {
            a8 = a(e0.d("common").c());
        }
        return a8;
    }
}
