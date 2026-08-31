package d0;
/* loaded from: classes.dex */
final class l {

    /* renamed from: a  reason: collision with root package name */
    private final long f6416a;

    /* renamed from: b  reason: collision with root package name */
    private final long f6417b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(double d8) {
        this((long) (d8 * 10000.0d), 10000L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a() {
        return this.f6417b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long b() {
        return this.f6416a;
    }

    public String toString() {
        return this.f6416a + "/" + this.f6417b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(long j8, long j9) {
        this.f6416a = j8;
        this.f6417b = j9;
    }
}
