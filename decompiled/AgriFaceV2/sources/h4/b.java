package h4;
/* loaded from: classes.dex */
public abstract class b {
    public static Object a(Object obj) {
        obj.getClass();
        return obj;
    }

    public static void b(boolean z7, String str, Object obj) {
        if (!z7) {
            throw new IllegalStateException(c.a(str, obj));
        }
    }
}
