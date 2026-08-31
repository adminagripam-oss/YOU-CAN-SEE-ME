package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import s.b0;
/* loaded from: classes.dex */
public class JpegCaptureDownsizingQuirk implements SoftwareJpegEncodingPreferredQuirk {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f2649a = new HashSet(Arrays.asList("redmi note 8 pro"));

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(b0 b0Var) {
        return f2649a.contains(Build.MODEL.toLowerCase(Locale.US)) && ((Integer) b0Var.a(CameraCharacteristics.LENS_FACING)).intValue() == 0;
    }
}
