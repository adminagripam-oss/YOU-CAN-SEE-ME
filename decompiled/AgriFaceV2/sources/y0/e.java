package y0;
/* loaded from: classes.dex */
final class e extends p1 {

    /* renamed from: e  reason: collision with root package name */
    private final int f13047e;

    /* renamed from: f  reason: collision with root package name */
    private final int f13048f;

    /* renamed from: g  reason: collision with root package name */
    private final int f13049g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(int i8, int i9, int i10) {
        this.f13047e = i8;
        this.f13048f = i9;
        this.f13049g = i10;
    }

    @Override // y0.p1
    public int b() {
        return this.f13049g;
    }

    @Override // y0.p1
    public int c() {
        return this.f13047e;
    }

    @Override // y0.p1
    public int d() {
        return this.f13048f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof p1) {
            p1 p1Var = (p1) obj;
            return this.f13047e == p1Var.c() && this.f13048f == p1Var.d() && this.f13049g == p1Var.b();
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f13047e ^ 1000003) * 1000003) ^ this.f13048f) * 1000003) ^ this.f13049g;
    }

    public String toString() {
        return "VideoEncoderDataSpace{standard=" + this.f13047e + ", transfer=" + this.f13048f + ", range=" + this.f13049g + "}";
    }
}
