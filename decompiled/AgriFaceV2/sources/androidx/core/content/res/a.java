package androidx.core.content.res;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final float f2925a;

    /* renamed from: b  reason: collision with root package name */
    private final float f2926b;

    /* renamed from: c  reason: collision with root package name */
    private final float f2927c;

    /* renamed from: d  reason: collision with root package name */
    private final float f2928d;

    /* renamed from: e  reason: collision with root package name */
    private final float f2929e;

    /* renamed from: f  reason: collision with root package name */
    private final float f2930f;

    /* renamed from: g  reason: collision with root package name */
    private final float f2931g;

    /* renamed from: h  reason: collision with root package name */
    private final float f2932h;

    /* renamed from: i  reason: collision with root package name */
    private final float f2933i;

    a(float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        this.f2925a = f8;
        this.f2926b = f9;
        this.f2927c = f10;
        this.f2928d = f11;
        this.f2929e = f12;
        this.f2930f = f13;
        this.f2931g = f14;
        this.f2932h = f15;
        this.f2933i = f16;
    }

    private static a b(float f8, float f9, float f10) {
        float f11 = 100.0f;
        float f12 = 1000.0f;
        float f13 = 0.0f;
        a aVar = null;
        float f14 = 1000.0f;
        while (Math.abs(f13 - f11) > 0.01f) {
            float f15 = ((f11 - f13) / 2.0f) + f13;
            int p8 = e(f15, f9, f8).p();
            float b8 = b.b(p8);
            float abs = Math.abs(f10 - b8);
            if (abs < 0.2f) {
                a c8 = c(p8);
                float a8 = c8.a(e(c8.k(), c8.i(), f8));
                if (a8 <= 1.0f) {
                    aVar = c8;
                    f12 = abs;
                    f14 = a8;
                }
            }
            if (f12 == 0.0f && f14 == 0.0f) {
                break;
            } else if (b8 < f10) {
                f13 = f15;
            } else {
                f11 = f15;
            }
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a c(int i8) {
        float[] fArr = new float[7];
        float[] fArr2 = new float[3];
        d(i8, i.f2965k, fArr, fArr2);
        return new a(fArr2[0], fArr2[1], fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6]);
    }

    static void d(int i8, i iVar, float[] fArr, float[] fArr2) {
        float pow;
        b.f(i8, fArr2);
        float[][] fArr3 = b.f2934a;
        float f8 = fArr2[0];
        float[] fArr4 = fArr3[0];
        float f9 = fArr2[1];
        float f10 = fArr2[2];
        float f11 = (fArr4[0] * f8) + (fArr4[1] * f9) + (fArr4[2] * f10);
        float[] fArr5 = fArr3[1];
        float f12 = (fArr5[0] * f8) + (fArr5[1] * f9) + (fArr5[2] * f10);
        float[] fArr6 = fArr3[2];
        float f13 = iVar.i()[0] * f11;
        float f14 = iVar.i()[1] * f12;
        float f15 = iVar.i()[2] * ((f8 * fArr6[0]) + (f9 * fArr6[1]) + (f10 * fArr6[2]));
        float pow2 = (float) Math.pow((iVar.c() * Math.abs(f13)) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow((iVar.c() * Math.abs(f14)) / 100.0d, 0.42d);
        float pow4 = (float) Math.pow((iVar.c() * Math.abs(f15)) / 100.0d, 0.42d);
        float signum = ((Math.signum(f13) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum2 = ((Math.signum(f14) * 400.0f) * pow3) / (pow3 + 27.13f);
        float signum3 = ((Math.signum(f15) * 400.0f) * pow4) / (pow4 + 27.13f);
        double d8 = signum3;
        float f16 = ((float) (((signum * 11.0d) + (signum2 * (-12.0d))) + d8)) / 11.0f;
        float f17 = ((float) ((signum + signum2) - (d8 * 2.0d))) / 9.0f;
        float f18 = signum2 * 20.0f;
        float f19 = (((signum * 20.0f) + f18) + (21.0f * signum3)) / 20.0f;
        float f20 = (((signum * 40.0f) + f18) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2(f17, f16)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f21 = (3.1415927f * atan2) / 180.0f;
        float pow5 = ((float) Math.pow((f20 * iVar.f()) / iVar.a(), iVar.b() * iVar.j())) * 100.0f;
        float b8 = (4.0f / iVar.b()) * ((float) Math.sqrt(pow5 / 100.0f)) * (iVar.a() + 4.0f) * iVar.d();
        float sqrt = ((float) Math.sqrt(pow5 / 100.0d)) * ((float) Math.pow(1.64d - Math.pow(0.29d, iVar.e()), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos((((((double) atan2) < 20.14d ? 360.0f + atan2 : atan2) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * iVar.g()) * iVar.h()) * ((float) Math.sqrt((f16 * f16) + (f17 * f17)))) / (f19 + 0.305f), 0.9d));
        float d9 = iVar.d() * sqrt;
        float sqrt2 = ((float) Math.sqrt((pow * iVar.b()) / (iVar.a() + 4.0f))) * 50.0f;
        float f22 = (1.7f * pow5) / ((0.007f * pow5) + 1.0f);
        float log = ((float) Math.log((0.0228f * d9) + 1.0f)) * 43.85965f;
        double d10 = f21;
        float cos = ((float) Math.cos(d10)) * log;
        float sin = log * ((float) Math.sin(d10));
        fArr2[0] = atan2;
        fArr2[1] = sqrt;
        if (fArr != null) {
            fArr[0] = pow5;
            fArr[1] = b8;
            fArr[2] = d9;
            fArr[3] = sqrt2;
            fArr[4] = f22;
            fArr[5] = cos;
            fArr[6] = sin;
        }
    }

    private static a e(float f8, float f9, float f10) {
        return f(f8, f9, f10, i.f2965k);
    }

    private static a f(float f8, float f9, float f10, i iVar) {
        double d8;
        float b8 = (4.0f / iVar.b()) * ((float) Math.sqrt(f8 / 100.0d)) * (iVar.a() + 4.0f) * iVar.d();
        float d9 = f9 * iVar.d();
        float sqrt = ((float) Math.sqrt(((f9 / ((float) Math.sqrt(d8))) * iVar.b()) / (iVar.a() + 4.0f))) * 50.0f;
        float f11 = (1.7f * f8) / ((0.007f * f8) + 1.0f);
        float log = ((float) Math.log((d9 * 0.0228d) + 1.0d)) * 43.85965f;
        double d10 = (3.1415927f * f10) / 180.0f;
        return new a(f10, f9, f8, b8, d9, sqrt, f11, log * ((float) Math.cos(d10)), log * ((float) Math.sin(d10)));
    }

    public static int m(float f8, float f9, float f10) {
        return n(f8, f9, f10, i.f2965k);
    }

    static int n(float f8, float f9, float f10, i iVar) {
        if (f9 < 1.0d || Math.round(f10) <= 0.0d || Math.round(f10) >= 100.0d) {
            return b.a(f10);
        }
        float min = f8 < 0.0f ? 0.0f : Math.min(360.0f, f8);
        a aVar = null;
        boolean z7 = true;
        float f11 = 0.0f;
        float f12 = f9;
        while (Math.abs(f11 - f9) >= 0.4f) {
            a b8 = b(min, f12, f10);
            if (!z7) {
                if (b8 == null) {
                    f9 = f12;
                } else {
                    f11 = f12;
                    aVar = b8;
                }
                f12 = ((f9 - f11) / 2.0f) + f11;
            } else if (b8 != null) {
                return b8.o(iVar);
            } else {
                f12 = ((f9 - f11) / 2.0f) + f11;
                z7 = false;
            }
        }
        return aVar == null ? b.a(f10) : aVar.o(iVar);
    }

    float a(a aVar) {
        float l8 = l() - aVar.l();
        float g8 = g() - aVar.g();
        float h8 = h() - aVar.h();
        return (float) (Math.pow(Math.sqrt((l8 * l8) + (g8 * g8) + (h8 * h8)), 0.63d) * 1.41d);
    }

    float g() {
        return this.f2932h;
    }

    float h() {
        return this.f2933i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float i() {
        return this.f2926b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float j() {
        return this.f2925a;
    }

    float k() {
        return this.f2927c;
    }

    float l() {
        return this.f2931g;
    }

    int o(i iVar) {
        float f8;
        float pow = (float) Math.pow(((((double) i()) == 0.0d || ((double) k()) == 0.0d) ? 0.0f : i() / ((float) Math.sqrt(k() / 100.0d))) / Math.pow(1.64d - Math.pow(0.29d, iVar.e()), 0.73d), 1.1111111111111112d);
        double j8 = (j() * 3.1415927f) / 180.0f;
        float a8 = iVar.a() * ((float) Math.pow(k() / 100.0d, (1.0d / iVar.b()) / iVar.j()));
        float cos = ((float) (Math.cos(2.0d + j8) + 3.8d)) * 0.25f * 3846.1538f * iVar.g() * iVar.h();
        float f9 = a8 / iVar.f();
        float sin = (float) Math.sin(j8);
        float cos2 = (float) Math.cos(j8);
        float f10 = (((0.305f + f9) * 23.0f) * pow) / (((cos * 23.0f) + ((11.0f * pow) * cos2)) + ((pow * 108.0f) * sin));
        float f11 = cos2 * f10;
        float f12 = f10 * sin;
        float f13 = f9 * 460.0f;
        float f14 = (((451.0f * f11) + f13) + (288.0f * f12)) / 1403.0f;
        float f15 = ((f13 - (891.0f * f11)) - (261.0f * f12)) / 1403.0f;
        float signum = Math.signum(f14) * (100.0f / iVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f14) * 27.13d) / (400.0d - Math.abs(f14))), 2.380952380952381d));
        float signum2 = Math.signum(f15) * (100.0f / iVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f15) * 27.13d) / (400.0d - Math.abs(f15))), 2.380952380952381d));
        float signum3 = Math.signum(((f13 - (f11 * 220.0f)) - (f12 * 6300.0f)) / 1403.0f) * (100.0f / iVar.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f8) * 27.13d) / (400.0d - Math.abs(f8))), 2.380952380952381d));
        float f16 = signum / iVar.i()[0];
        float f17 = signum2 / iVar.i()[1];
        float f18 = signum3 / iVar.i()[2];
        float[][] fArr = b.f2935b;
        float[] fArr2 = fArr[0];
        float f19 = (fArr2[0] * f16) + (fArr2[1] * f17) + (fArr2[2] * f18);
        float[] fArr3 = fArr[1];
        float[] fArr4 = fArr[2];
        return androidx.core.graphics.a.a(f19, (fArr3[0] * f16) + (fArr3[1] * f17) + (fArr3[2] * f18), (f16 * fArr4[0]) + (f17 * fArr4[1]) + (f18 * fArr4[2]));
    }

    int p() {
        return o(i.f2965k);
    }
}
