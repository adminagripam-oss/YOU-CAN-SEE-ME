package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Size;
import b0.h2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import s.b0;
import s.t0;
import y.f1;
/* loaded from: classes.dex */
public class CamcorderProfileResolutionQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private final t0 f2627a;

    /* renamed from: b  reason: collision with root package name */
    private List f2628b = null;

    public CamcorderProfileResolutionQuirk(b0 b0Var) {
        this.f2627a = b0Var.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g(b0 b0Var) {
        Integer num = (Integer) b0Var.a(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        return num != null && num.intValue() == 2;
    }

    public List f() {
        if (this.f2628b == null) {
            Size[] c8 = this.f2627a.c(34);
            this.f2628b = c8 != null ? Arrays.asList((Size[]) c8.clone()) : Collections.emptyList();
            f1.a("CamcorderProfileResolutionQuirk", "mSupportedResolutions = " + this.f2628b);
        }
        return new ArrayList(this.f2628b);
    }
}
