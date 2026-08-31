package androidx.core.content.res;
/* loaded from: classes.dex */
final class i {

    /* renamed from: k  reason: collision with root package name */
    static final i f2965k = k(b.f2936c, (float) ((b.h(50.0f) * 63.66197723675813d) / 100.0d), 50.0f, 2.0f, false);

    /* renamed from: a  reason: collision with root package name */
    private final float f2966a;

    /* renamed from: b  reason: collision with root package name */
    private final float f2967b;

    /* renamed from: c  reason: collision with root package name */
    private final float f2968c;

    /* renamed from: d  reason: collision with root package name */
    private final float f2969d;

    /* renamed from: e  reason: collision with root package name */
    private final float f2970e;

    /* renamed from: f  reason: collision with root package name */
    private final float f2971f;

    /* renamed from: g  reason: collision with root package name */
    private final float[] f2972g;

    /* renamed from: h  reason: collision with root package name */
    private final float f2973h;

    /* renamed from: i  reason: collision with root package name */
    private final float f2974i;

    /* renamed from: j  reason: collision with root package name */
    private final float f2975j;

    private i(float f8, float f9, float f10, float f11, float f12, float f13, float[] fArr, float f14, float f15, float f16) {
        this.f2971f = f8;
        this.f2966a = f9;
        this.f2967b = f10;
        this.f2968c = f11;
        this.f2969d = f12;
        this.f2970e = f13;
        this.f2972g = fArr;
        this.f2973h = f14;
        this.f2974i = f15;
        this.f2975j = f16;
    }

    static i k(float[] fArr, float f8, float f9, float f10, boolean z7) {
        float[][] fArr2 = b.f2934a;
        float f11 = fArr[0];
        float[] fArr3 = fArr2[0];
        float f12 = fArr[1];
        float f13 = fArr[2];
        float f14 = (fArr3[0] * f11) + (fArr3[1] * f12) + (fArr3[2] * f13);
        float[] fArr4 = fArr2[1];
        float f15 = (fArr4[0] * f11) + (fArr4[1] * f12) + (fArr4[2] * f13);
        float[] fArr5 = fArr2[2];
        float f16 = (f11 * fArr5[0]) + (f12 * fArr5[1]) + (f13 * fArr5[2]);
        float f17 = (f10 / 10.0f) + 0.8f;
        float d8 = ((double) f17) >= 0.9d ? b.d(0.59f, 0.69f, (f17 - 0.9f) * 10.0f) : b.d(0.525f, 0.59f, (f17 - 0.8f) * 10.0f);
        float exp = z7 ? 1.0f : (1.0f - (((float) Math.exp(((-f8) - 42.0f) / 92.0f)) * 0.2777778f)) * f17;
        double d9 = exp;
        if (d9 > 1.0d) {
            exp = 1.0f;
        } else if (d9 < 0.0d) {
            exp = 0.0f;
        }
        float[] fArr6 = {(((100.0f / f14) * exp) + 1.0f) - exp, (((100.0f / f15) * exp) + 1.0f) - exp, (((100.0f / f16) * exp) + 1.0f) - exp};
        float f18 = 1.0f / ((5.0f * f8) + 1.0f);
        float f19 = f18 * f18 * f18 * f18;
        float f20 = 1.0f - f19;
        float cbrt = (f19 * f8) + (0.1f * f20 * f20 * ((float) Math.cbrt(f8 * 5.0d)));
        float h8 = b.h(f9) / fArr[1];
        double d10 = h8;
        float sqrt = ((float) Math.sqrt(d10)) + 1.48f;
        float pow = 0.725f / ((float) Math.pow(d10, 0.2d));
        float pow2 = (float) Math.pow(((fArr6[2] * cbrt) * f16) / 100.0d, 0.42d);
        float[] fArr7 = {(float) Math.pow(((fArr6[0] * cbrt) * f14) / 100.0d, 0.42d), (float) Math.pow(((fArr6[1] * cbrt) * f15) / 100.0d, 0.42d), pow2};
        float f21 = fArr7[0];
        float f22 = fArr7[1];
        return new i(h8, ((((f21 * 400.0f) / (f21 + 27.13f)) * 2.0f) + ((f22 * 400.0f) / (f22 + 27.13f)) + (((400.0f * pow2) / (pow2 + 27.13f)) * 0.05f)) * pow, pow, pow, d8, f17, fArr6, cbrt, (float) Math.pow(cbrt, 0.25d), sqrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float a() {
        return this.f2966a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float b() {
        return this.f2969d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.f2973h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float d() {
        return this.f2974i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float e() {
        return this.f2971f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float f() {
        return this.f2967b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float g() {
        return this.f2970e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float h() {
        return this.f2968c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float[] i() {
        return this.f2972g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float j() {
        return this.f2975j;
    }
}
