package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class CameraUseInconsistentTimebaseQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f2774a = new HashSet(Arrays.asList("samsungexynos7570", "samsungexynos7870", "qcom"));

    /* renamed from: b  reason: collision with root package name */
    private static final Set f2775b = new HashSet(Arrays.asList("sm4350", "sm6375", "sm7325"));

    /* renamed from: c  reason: collision with root package name */
    private static final Set f2776c = new HashSet(Arrays.asList("m2007j20cg", "m2007j20ct"));

    private static boolean f() {
        return f2776c.contains(Build.MODEL.toLowerCase());
    }

    private static boolean g() {
        return "SAMSUNG".equalsIgnoreCase(Build.BRAND) && f2774a.contains(Build.HARDWARE.toLowerCase());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean h() {
        return i() || g() || f();
    }

    private static boolean i() {
        String str;
        if (Build.VERSION.SDK_INT >= 31) {
            Set set = f2775b;
            str = Build.SOC_MODEL;
            if (set.contains(str.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
