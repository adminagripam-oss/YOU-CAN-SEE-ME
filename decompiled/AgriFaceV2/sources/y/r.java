package y;
/* loaded from: classes.dex */
public abstract class r {

    /* loaded from: classes.dex */
    public static abstract class a {
        public static a a(int i8) {
            return b(i8, null);
        }

        public static a b(int i8, Throwable th) {
            return new c(i8, th);
        }

        public abstract Throwable c();

        public abstract int d();
    }

    /* loaded from: classes.dex */
    public enum b {
        PENDING_OPEN,
        OPENING,
        OPEN,
        CLOSING,
        CLOSED
    }

    public static r a(b bVar) {
        return b(bVar, null);
    }

    public static r b(b bVar, a aVar) {
        return new y.b(bVar, aVar);
    }

    public abstract a c();

    public abstract b d();
}
