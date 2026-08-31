package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class PreviewPixelHDRnetQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final List f2652a = Arrays.asList("sunfish", "bramble", "redfin", "barbet");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return "Google".equals(Build.MANUFACTURER) && f2652a.contains(Build.DEVICE.toLowerCase(Locale.getDefault()));
    }
}
