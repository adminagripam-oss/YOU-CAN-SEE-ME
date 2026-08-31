package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import s.b0;
/* loaded from: classes.dex */
public final class JpegHalCorruptImageQuirk implements SoftwareJpegEncodingPreferredQuirk {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f2650a = new HashSet(Arrays.asList("heroqltevzw", "heroqltetmo", "k61v1_basic_ref"));

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(b0 b0Var) {
        return f2650a.contains(Build.DEVICE.toLowerCase(Locale.US));
    }
}
