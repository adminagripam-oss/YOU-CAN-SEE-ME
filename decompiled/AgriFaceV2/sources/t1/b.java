package t1;

import android.view.animation.Interpolator;
/* loaded from: classes.dex */
abstract class b implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    private final float[] f11652a;

    /* renamed from: b  reason: collision with root package name */
    private final float f11653b;

    /* JADX INFO: Access modifiers changed from: protected */
    public b(float[] fArr) {
        this.f11652a = fArr;
        this.f11653b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f8) {
        if (f8 >= 1.0f) {
            return 1.0f;
        }
        if (f8 <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f11652a;
        int min = Math.min((int) ((fArr.length - 1) * f8), fArr.length - 2);
        float f9 = this.f11653b;
        float f10 = (f8 - (min * f9)) / f9;
        float[] fArr2 = this.f11652a;
        float f11 = fArr2[min];
        return f11 + (f10 * (fArr2[min + 1] - f11));
    }
}
