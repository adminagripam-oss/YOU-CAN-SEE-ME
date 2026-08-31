package r0;
/* loaded from: classes.dex */
public abstract class b1 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static b1 d(long j8, long j9, b bVar) {
        g1.e.b(j8 >= 0, "duration must be positive value.");
        g1.e.b(j9 >= 0, "bytes must be positive value.");
        return new l(j8, j9, bVar);
    }

    public abstract b a();

    public abstract long b();

    public abstract long c();
}
