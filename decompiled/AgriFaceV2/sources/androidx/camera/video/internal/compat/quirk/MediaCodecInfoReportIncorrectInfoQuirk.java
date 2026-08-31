package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import android.util.Size;
import b0.h2;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public class MediaCodecInfoReportIncorrectInfoQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    public static final List f2778a = Arrays.asList("lg-k430", "redmi note 4", "m2003j15sc", "m2004j19c", "rmx3231", "v2117", "sm-a032f", "moto g(20)", "sm-a035m", "v2204", "23078pnd5g", "pht110", "23053rn02l", "rmx3710", "rmx3511", "v2207", "moto e20");

    public static Set f() {
        return g() ? Collections.singleton(new Size(1920, 1080)) : Collections.emptySet();
    }

    private static boolean g() {
        return f2778a.contains(Build.MODEL.toLowerCase(Locale.US));
    }

    private static boolean h() {
        return "Huawei".equalsIgnoreCase(Build.BRAND) && "mha-l29".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean i() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto c".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean j() {
        return "Nokia".equalsIgnoreCase(Build.BRAND) && "Nokia 1".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean k() {
        return "positivo".equalsIgnoreCase(Build.BRAND) && "twist 2 pro".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean l() {
        return "Redmi".equalsIgnoreCase(Build.BRAND) && "Redmi Note 8 Pro".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean m() {
        return "LGE".equalsIgnoreCase(Build.BRAND) && "LG-X230".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean n() {
        return "infinix".equalsIgnoreCase(Build.BRAND) && "infinix x650".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean o() {
        return j() || i() || n() || m() || h() || l() || k() || g();
    }
}
