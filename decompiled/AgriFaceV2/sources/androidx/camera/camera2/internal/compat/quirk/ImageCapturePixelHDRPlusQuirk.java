package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class ImageCapturePixelHDRPlusQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    public static final List f2643a = Arrays.asList("Pixel 2", "Pixel 2 XL", "Pixel 3", "Pixel 3 XL");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return f2643a.contains(Build.MODEL) && "Google".equals(Build.MANUFACTURER);
    }
}
