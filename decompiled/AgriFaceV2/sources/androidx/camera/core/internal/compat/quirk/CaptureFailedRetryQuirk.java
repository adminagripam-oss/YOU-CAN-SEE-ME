package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import android.util.Pair;
import b0.h2;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public class CaptureFailedRetryQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f2729a = new HashSet(Collections.singletonList(Pair.create("SAMSUNG", "SM-G981U1")));

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g() {
        String str = Build.BRAND;
        Locale locale = Locale.US;
        return f2729a.contains(Pair.create(str.toUpperCase(locale), Build.MODEL.toUpperCase(locale)));
    }

    public int f() {
        return 1;
    }
}
