package z3;
/* loaded from: classes.dex */
public abstract class fc {

    /* renamed from: a  reason: collision with root package name */
    private static ec f13668a;

    public static synchronized wb a(rb rbVar) {
        wb wbVar;
        synchronized (fc.class) {
            if (f13668a == null) {
                f13668a = new ec(null);
            }
            wbVar = (wb) f13668a.b(rbVar);
        }
        return wbVar;
    }

    public static synchronized wb b(String str) {
        wb a8;
        synchronized (fc.class) {
            a8 = a(rb.d("vision-common").c());
        }
        return a8;
    }
}
