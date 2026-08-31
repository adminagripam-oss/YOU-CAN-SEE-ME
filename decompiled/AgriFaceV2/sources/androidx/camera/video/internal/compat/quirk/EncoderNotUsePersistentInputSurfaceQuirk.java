package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import b0.h2;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class EncoderNotUsePersistentInputSurfaceQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final List f2777a = Arrays.asList("SM-N9208", "SM-G920V");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return f2777a.contains(Build.MODEL.toUpperCase());
    }
}
