package r;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Range;
import android.util.Rational;
/* loaded from: classes.dex */
class j3 implements y.b0 {

    /* renamed from: a  reason: collision with root package name */
    private final Object f10539a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private final s.b0 f10540b;

    /* renamed from: c  reason: collision with root package name */
    private int f10541c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j3(s.b0 b0Var, int i8) {
        this.f10540b = b0Var;
        this.f10541c = i8;
    }

    @Override // y.b0
    public Range a() {
        return (Range) this.f10540b.a(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
    }

    @Override // y.b0
    public Rational b() {
        return !d() ? Rational.ZERO : (Rational) this.f10540b.a(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
    }

    public int c() {
        int i8;
        synchronized (this.f10539a) {
            i8 = this.f10541c;
        }
        return i8;
    }

    public boolean d() {
        Range range = (Range) this.f10540b.a(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
        return (range == null || ((Integer) range.getLower()).intValue() == 0 || ((Integer) range.getUpper()).intValue() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(int i8) {
        synchronized (this.f10539a) {
            this.f10541c = i8;
        }
    }
}
