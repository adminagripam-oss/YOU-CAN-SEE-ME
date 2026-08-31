package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import r.t;
import s.b0;
/* loaded from: classes.dex */
public class TorchFlashRequiredFor3aUpdateQuirk implements h2 {

    /* renamed from: b  reason: collision with root package name */
    private static final List f2654b = Arrays.asList("PIXEL 6A", "PIXEL 6 PRO", "PIXEL 7", "PIXEL 7A", "PIXEL 7 PRO", "PIXEL 8", "PIXEL 8 PRO");

    /* renamed from: a  reason: collision with root package name */
    private final b0 f2655a;

    public TorchFlashRequiredFor3aUpdateQuirk(b0 b0Var) {
        this.f2655a = b0Var;
    }

    private static boolean f(b0 b0Var) {
        return g() && j(b0Var);
    }

    private static boolean g() {
        for (String str : f2654b) {
            if (Build.MODEL.toUpperCase(Locale.US).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean h(b0 b0Var) {
        return Build.VERSION.SDK_INT >= 28 && t.Q(b0Var, 5) == 5;
    }

    private static boolean j(b0 b0Var) {
        return ((Integer) b0Var.a(CameraCharacteristics.LENS_FACING)).intValue() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean k(b0 b0Var) {
        return f(b0Var);
    }

    public boolean i() {
        return !h(this.f2655a);
    }
}
