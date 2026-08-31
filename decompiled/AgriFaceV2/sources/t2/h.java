package t2;
/* loaded from: classes.dex */
final class h extends n {

    /* renamed from: a  reason: collision with root package name */
    private final long f11749a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(long j8) {
        this.f11749a = j8;
    }

    @Override // t2.n
    public long c() {
        return this.f11749a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof n) && this.f11749a == ((n) obj).c();
    }

    public int hashCode() {
        long j8 = this.f11749a;
        return ((int) (j8 ^ (j8 >>> 32))) ^ 1000003;
    }

    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.f11749a + "}";
    }
}
