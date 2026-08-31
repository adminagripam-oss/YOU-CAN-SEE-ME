package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import s.b0;
/* loaded from: classes.dex */
public class ImageCaptureFailWithAutoFlashQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final List f2639a = Arrays.asList("sm-j700f", "sm-j710f");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(b0 b0Var) {
        return f2639a.contains(Build.MODEL.toLowerCase(Locale.US)) && ((Integer) b0Var.a(CameraCharacteristics.LENS_FACING)).intValue() == 0;
    }
}
