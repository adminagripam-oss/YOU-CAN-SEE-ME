package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Range;
import b0.h2;
import s.b0;
/* loaded from: classes.dex */
public class AeFpsRangeLegacyQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private final Range f2626a;

    public AeFpsRangeLegacyQuirk(b0 b0Var) {
        this.f2626a = i((Range[]) b0Var.a(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
    }

    private Range f(Range range) {
        int intValue = ((Integer) range.getUpper()).intValue();
        int intValue2 = ((Integer) range.getLower()).intValue();
        if (((Integer) range.getUpper()).intValue() >= 1000) {
            intValue = ((Integer) range.getUpper()).intValue() / 1000;
        }
        if (((Integer) range.getLower()).intValue() >= 1000) {
            intValue2 = ((Integer) range.getLower()).intValue() / 1000;
        }
        return new Range(Integer.valueOf(intValue2), Integer.valueOf(intValue));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean h(b0 b0Var) {
        Integer num = (Integer) b0Var.a(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        return num != null && num.intValue() == 2;
    }

    private Range i(Range[] rangeArr) {
        Range range = null;
        if (rangeArr != null && rangeArr.length != 0) {
            for (Range range2 : rangeArr) {
                Range f8 = f(range2);
                if (((Integer) f8.getUpper()).intValue() == 30 && (range == null || ((Integer) f8.getLower()).intValue() < ((Integer) range.getLower()).intValue())) {
                    range = f8;
                }
            }
        }
        return range;
    }

    public Range g() {
        return this.f2626a;
    }
}
