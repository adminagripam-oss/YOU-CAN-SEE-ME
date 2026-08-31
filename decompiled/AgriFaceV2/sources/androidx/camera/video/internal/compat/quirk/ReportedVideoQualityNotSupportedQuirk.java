package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
import b0.g0;
import java.util.Arrays;
import java.util.Locale;
import r0.v;
/* loaded from: classes.dex */
public class ReportedVideoQualityNotSupportedQuirk implements VideoQualityQuirk, SurfaceProcessingQuirk {
    private static boolean f() {
        return "Huawei".equalsIgnoreCase(Build.BRAND) && "HMA-L29".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean g() {
        return "Huawei".equalsIgnoreCase(Build.BRAND) && "LYA-AL00".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean h() {
        return "Huawei".equalsIgnoreCase(Build.MANUFACTURER) && Arrays.asList("JNY-L21A", "JNY-L01A", "JNY-L21B", "JNY-L22A", "JNY-L02A", "JNY-L22B", "JNY-LX1").contains(Build.MODEL.toUpperCase(Locale.US));
    }

    private static boolean i() {
        return "OPPO".equalsIgnoreCase(Build.BRAND) && "PHT110".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean j() {
        return "Vivo".equalsIgnoreCase(Build.BRAND) && "vivo 1820".equalsIgnoreCase(Build.MODEL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean k() {
        return f() || g() || j() || h() || i();
    }

    @Override // androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk
    public boolean a() {
        return f() || g() || h() || i();
    }

    @Override // androidx.camera.video.internal.compat.quirk.VideoQualityQuirk
    public boolean c(g0 g0Var, v vVar) {
        if (f() || g()) {
            return vVar == v.f11270d;
        } else if (j()) {
            return vVar == v.f11268b || vVar == v.f11269c;
        } else if (h()) {
            return g0Var.d() == 0 && (vVar == v.f11269c || vVar == v.f11268b);
        } else if (i()) {
            return g0Var.d() == 1 && vVar == v.f11270d;
        } else {
            return false;
        }
    }
}
