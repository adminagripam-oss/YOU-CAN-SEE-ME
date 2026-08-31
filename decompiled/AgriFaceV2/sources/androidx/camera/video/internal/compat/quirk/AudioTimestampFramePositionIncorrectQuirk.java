package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class AudioTimestampFramePositionIncorrectQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final List f2773a = Arrays.asList("cph1920", "cph1923", "cph2015", "cph2083");

    private static boolean f() {
        return "oppo".equalsIgnoreCase(Build.BRAND) && f2773a.contains(Build.MODEL.toLowerCase(Locale.ROOT));
    }

    private static boolean g() {
        return "lge".equalsIgnoreCase(Build.BRAND) && "lg-m250".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean h() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto c".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean i() {
        return "realme".equalsIgnoreCase(Build.BRAND) && "rmx1941".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean j() {
        return "Xiaomi".equalsIgnoreCase(Build.BRAND) && "Redmi 6A".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean k() {
        return "vivo".equalsIgnoreCase(Build.BRAND) && "vivo 1820".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean l() {
        return "vivo".equalsIgnoreCase(Build.BRAND) && "VIVO Y17".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean m() {
        return f() || g() || h() || i() || j() || k() || l();
    }
}
