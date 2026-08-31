package r0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l extends b1 {

    /* renamed from: a  reason: collision with root package name */
    private final long f11082a;

    /* renamed from: b  reason: collision with root package name */
    private final long f11083b;

    /* renamed from: c  reason: collision with root package name */
    private final b f11084c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(long j8, long j9, b bVar) {
        this.f11082a = j8;
        this.f11083b = j9;
        if (bVar == null) {
            throw new NullPointerException("Null audioStats");
        }
        this.f11084c = bVar;
    }

    @Override // r0.b1
    public b a() {
        return this.f11084c;
    }

    @Override // r0.b1
    public long b() {
        return this.f11083b;
    }

    @Override // r0.b1
    public long c() {
        return this.f11082a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b1) {
            b1 b1Var = (b1) obj;
            return this.f11082a == b1Var.c() && this.f11083b == b1Var.b() && this.f11084c.equals(b1Var.a());
        }
        return false;
    }

    public int hashCode() {
        long j8 = this.f11082a;
        long j9 = this.f11083b;
        return ((((((int) (j8 ^ (j8 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j9 >>> 32) ^ j9))) * 1000003) ^ this.f11084c.hashCode();
    }

    public String toString() {
        return "RecordingStats{recordedDurationNanos=" + this.f11082a + ", numBytesRecorded=" + this.f11083b + ", audioStats=" + this.f11084c + "}";
    }
}
