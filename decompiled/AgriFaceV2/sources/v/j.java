package v;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.internal.compat.quirk.ImageCapturePixelHDRPlusQuirk;
import q.a;
/* loaded from: classes.dex */
public class j {
    public void a(int i8, a.C0140a c0140a) {
        CaptureRequest.Key key;
        Boolean bool;
        if (((ImageCapturePixelHDRPlusQuirk) androidx.camera.camera2.internal.compat.quirk.b.b(ImageCapturePixelHDRPlusQuirk.class)) == null) {
            return;
        }
        if (i8 == 0) {
            key = CaptureRequest.CONTROL_ENABLE_ZSL;
            bool = Boolean.TRUE;
        } else if (i8 != 1) {
            return;
        } else {
            key = CaptureRequest.CONTROL_ENABLE_ZSL;
            bool = Boolean.FALSE;
        }
        c0140a.f(key, bool);
    }
}
