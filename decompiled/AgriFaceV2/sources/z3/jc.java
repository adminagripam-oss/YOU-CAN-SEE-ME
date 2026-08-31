package z3;
/* loaded from: classes.dex */
public final class jc {

    /* renamed from: a  reason: collision with root package name */
    private static jc f13811a;

    private jc() {
    }

    public static synchronized jc a() {
        jc jcVar;
        synchronized (jc.class) {
            if (f13811a == null) {
                f13811a = new jc();
            }
            jcVar = f13811a;
        }
        return jcVar;
    }

    public static final boolean b() {
        return ic.a("mlkit-dev-profiling");
    }
}
