package r0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends b {

    /* renamed from: b  reason: collision with root package name */
    private final int f11027b;

    /* renamed from: c  reason: collision with root package name */
    private final double f11028c;

    /* renamed from: d  reason: collision with root package name */
    private final Throwable f11029d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(int i8, double d8, Throwable th) {
        this.f11027b = i8;
        this.f11028c = d8;
        this.f11029d = th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r0.b
    public double a() {
        return this.f11028c;
    }

    @Override // r0.b
    public int b() {
        return this.f11027b;
    }

    @Override // r0.b
    public Throwable c() {
        return this.f11029d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (this.f11027b == bVar.b() && Double.doubleToLongBits(this.f11028c) == Double.doubleToLongBits(bVar.a())) {
                Throwable th = this.f11029d;
                Throwable c8 = bVar.c();
                if (th == null) {
                    if (c8 == null) {
                        return true;
                    }
                } else if (th.equals(c8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int doubleToLongBits = (((this.f11027b ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.f11028c) >>> 32) ^ Double.doubleToLongBits(this.f11028c)))) * 1000003;
        Throwable th = this.f11029d;
        return doubleToLongBits ^ (th == null ? 0 : th.hashCode());
    }

    public String toString() {
        return "AudioStats{audioState=" + this.f11027b + ", audioAmplitudeInternal=" + this.f11028c + ", errorCause=" + this.f11029d + "}";
    }
}
