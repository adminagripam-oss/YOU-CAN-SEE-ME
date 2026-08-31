package s2;
/* loaded from: classes.dex */
public abstract class c {
    public static c d(Object obj) {
        return new a(null, obj, d.DEFAULT);
    }

    public static c e(Object obj) {
        return new a(null, obj, d.VERY_LOW);
    }

    public abstract Integer a();

    public abstract Object b();

    public abstract d c();
}
