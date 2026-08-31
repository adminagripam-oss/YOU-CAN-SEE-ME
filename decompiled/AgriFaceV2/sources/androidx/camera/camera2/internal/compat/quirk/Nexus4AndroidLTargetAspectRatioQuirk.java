package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class Nexus4AndroidLTargetAspectRatioQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final List f2651a = Arrays.asList("NEXUS 4");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g() {
        "GOOGLE".equalsIgnoreCase(Build.BRAND);
        return false;
    }

    public int f() {
        return 2;
    }
}
