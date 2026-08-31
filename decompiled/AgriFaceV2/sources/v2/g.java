package v2;
/* loaded from: classes.dex */
public abstract class g {

    /* loaded from: classes.dex */
    public enum a {
        OK,
        TRANSIENT_ERROR,
        FATAL_ERROR
    }

    public static g a() {
        return new b(a.FATAL_ERROR, -1L);
    }

    public static g d(long j8) {
        return new b(a.OK, j8);
    }

    public static g e() {
        return new b(a.TRANSIENT_ERROR, -1L);
    }

    public abstract long b();

    public abstract a c();
}
