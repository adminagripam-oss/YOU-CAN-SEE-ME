package r;
/* loaded from: classes.dex */
class i5 implements y.j2 {

    /* renamed from: a  reason: collision with root package name */
    private float f10532a;

    /* renamed from: b  reason: collision with root package name */
    private final float f10533b;

    /* renamed from: c  reason: collision with root package name */
    private final float f10534c;

    /* renamed from: d  reason: collision with root package name */
    private float f10535d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i5(float f8, float f9) {
        this.f10533b = f8;
        this.f10534c = f9;
    }

    private float e(float f8) {
        float f9 = this.f10533b;
        float f10 = this.f10534c;
        if (f9 == f10) {
            return 0.0f;
        }
        if (f8 == f9) {
            return 1.0f;
        }
        if (f8 == f10) {
            return 0.0f;
        }
        float f11 = 1.0f / f10;
        return ((1.0f / f8) - f11) / ((1.0f / f9) - f11);
    }

    @Override // y.j2
    public float a() {
        return this.f10533b;
    }

    @Override // y.j2
    public float b() {
        return this.f10532a;
    }

    @Override // y.j2
    public float c() {
        return this.f10535d;
    }

    @Override // y.j2
    public float d() {
        return this.f10534c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(float f8) {
        if (f8 <= this.f10533b && f8 >= this.f10534c) {
            this.f10532a = f8;
            this.f10535d = e(f8);
            return;
        }
        throw new IllegalArgumentException("Requested zoomRatio " + f8 + " is not within valid range [" + this.f10534c + " , " + this.f10533b + "]");
    }
}
