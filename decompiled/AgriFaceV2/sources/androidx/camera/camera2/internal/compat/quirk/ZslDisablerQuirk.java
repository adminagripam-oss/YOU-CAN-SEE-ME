package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class ZslDisablerQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final List f2657a = Arrays.asList("SM-F936", "SM-S901U", "SM-S908U", "SM-S908U1");

    /* renamed from: b  reason: collision with root package name */
    private static final List f2658b = Arrays.asList("MI 8");

    private static boolean f(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (Build.MODEL.toUpperCase(Locale.US).startsWith((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    private static boolean g() {
        return "samsung".equalsIgnoreCase(Build.BRAND) && f(f2657a);
    }

    private static boolean h() {
        return "xiaomi".equalsIgnoreCase(Build.BRAND) && f(f2658b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean i() {
        return g() || h();
    }
}
