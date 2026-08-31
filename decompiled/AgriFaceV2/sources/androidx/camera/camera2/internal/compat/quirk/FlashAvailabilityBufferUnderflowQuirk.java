package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import android.util.Pair;
import b0.h2;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public class FlashAvailabilityBufferUnderflowQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f2637a = new HashSet();

    static {
        f("sprd", "lemp");
        f("sprd", "DM20C");
    }

    private static void f(String str, String str2) {
        Set set = f2637a;
        Locale locale = Locale.US;
        set.add(new Pair(str.toLowerCase(locale), str2.toLowerCase(locale)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g() {
        Set set = f2637a;
        String str = Build.MANUFACTURER;
        Locale locale = Locale.US;
        return set.contains(new Pair(str.toLowerCase(locale), Build.MODEL.toLowerCase(locale)));
    }
}
