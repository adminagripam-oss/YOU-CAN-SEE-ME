package t4;
/* loaded from: classes.dex */
public final class j0 extends RuntimeException {

    /* loaded from: classes.dex */
    public interface a {
        Object get();
    }

    public j0(String str) {
        super(str);
    }

    public static Object a(a aVar) {
        try {
            return aVar.get();
        } catch (Exception e8) {
            throw new j0(e8);
        }
    }

    public j0(String str, Throwable th) {
        super(str, th);
    }

    public j0(Throwable th) {
        super(th);
    }
}
