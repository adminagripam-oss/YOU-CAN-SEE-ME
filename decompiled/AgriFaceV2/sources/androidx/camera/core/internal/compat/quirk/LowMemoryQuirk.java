package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public class LowMemoryQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f2733a = new HashSet(Arrays.asList("SM-A520W", "MOTOG3"));

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return f2733a.contains(Build.MODEL.toUpperCase(Locale.US));
    }
}
