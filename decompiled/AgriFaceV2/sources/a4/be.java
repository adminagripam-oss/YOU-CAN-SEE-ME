package a4;
/* loaded from: classes.dex */
public abstract class be {

    /* renamed from: a  reason: collision with root package name */
    private static ae f298a;

    public static synchronized qd a(gd gdVar) {
        qd qdVar;
        synchronized (be.class) {
            if (f298a == null) {
                f298a = new ae(null);
            }
            qdVar = (qd) f298a.b(gdVar);
        }
        return qdVar;
    }

    public static synchronized qd b(String str) {
        qd a8;
        synchronized (be.class) {
            a8 = a(gd.d(str).c());
        }
        return a8;
    }
}
