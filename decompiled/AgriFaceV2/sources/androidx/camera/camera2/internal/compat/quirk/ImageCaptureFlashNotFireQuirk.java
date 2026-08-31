package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import s.b0;
/* loaded from: classes.dex */
public class ImageCaptureFlashNotFireQuirk implements UseTorchAsFlashQuirk {

    /* renamed from: a  reason: collision with root package name */
    private static final List f2641a = Arrays.asList("itel w6004");

    /* renamed from: b  reason: collision with root package name */
    private static final List f2642b = Arrays.asList("sm-j700f", "sm-j710f");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(b0 b0Var) {
        List list = f2642b;
        String str = Build.MODEL;
        Locale locale = Locale.US;
        return (list.contains(str.toLowerCase(locale)) && ((Integer) b0Var.a(CameraCharacteristics.LENS_FACING)).intValue() == 0) || f2641a.contains(str.toLowerCase(locale));
    }
}
