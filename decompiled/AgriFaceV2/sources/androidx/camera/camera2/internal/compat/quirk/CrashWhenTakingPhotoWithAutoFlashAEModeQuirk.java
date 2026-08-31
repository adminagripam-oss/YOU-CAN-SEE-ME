package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class CrashWhenTakingPhotoWithAutoFlashAEModeQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    static final List f2630a = Arrays.asList("SM-A3000", "SM-A3009", "SM-A300F", "SM-A300FU", "SM-A300G", "SM-A300H", "SM-A300M", "SM-A300X", "SM-A300XU", "SM-A300XZ", "SM-A300Y", "SM-A300YZ", "SM-J510FN", "5059X");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return f2630a.contains(Build.MODEL.toUpperCase(Locale.US));
    }
}
