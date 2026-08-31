package v;

import androidx.camera.camera2.internal.compat.quirk.CrashWhenTakingPhotoWithAutoFlashAEModeQuirk;
import androidx.camera.camera2.internal.compat.quirk.ImageCaptureFailWithAutoFlashQuirk;
import b0.l2;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f12286a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f12287b;

    public b(l2 l2Var) {
        this.f12286a = l2Var.a(ImageCaptureFailWithAutoFlashQuirk.class);
        this.f12287b = androidx.camera.camera2.internal.compat.quirk.b.b(CrashWhenTakingPhotoWithAutoFlashAEModeQuirk.class) != null;
    }

    public int a(int i8) {
        if ((this.f12286a || this.f12287b) && i8 == 2) {
            return 1;
        }
        return i8;
    }
}
