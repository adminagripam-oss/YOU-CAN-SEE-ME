package a4;
/* loaded from: classes.dex */
public final class ce {

    /* renamed from: a  reason: collision with root package name */
    private static ce f349a;

    private ce() {
    }

    public static synchronized ce a() {
        ce ceVar;
        synchronized (ce.class) {
            if (f349a == null) {
                f349a = new ce();
            }
            ceVar = f349a;
        }
        return ceVar;
    }
}
