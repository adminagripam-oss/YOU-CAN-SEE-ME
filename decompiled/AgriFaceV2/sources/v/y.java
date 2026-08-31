package v;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.internal.compat.quirk.CaptureIntentPreviewQuirk;
import androidx.camera.camera2.internal.compat.quirk.ImageCaptureFailedForVideoSnapshotQuirk;
import b0.l2;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class y {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f12318a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f12319b;

    public y(l2 l2Var) {
        this.f12318a = CaptureIntentPreviewQuirk.e(l2Var);
        this.f12319b = l2Var.a(ImageCaptureFailedForVideoSnapshotQuirk.class);
    }

    public Map a(int i8) {
        HashMap hashMap;
        CaptureRequest.Key key;
        int i9;
        if (i8 == 3 && this.f12318a) {
            hashMap = new HashMap();
            key = CaptureRequest.CONTROL_CAPTURE_INTENT;
            i9 = 1;
        } else if (i8 != 4 || !this.f12319b) {
            return Collections.emptyMap();
        } else {
            hashMap = new HashMap();
            key = CaptureRequest.CONTROL_CAPTURE_INTENT;
            i9 = 2;
        }
        hashMap.put(key, Integer.valueOf(i9));
        return Collections.unmodifiableMap(hashMap);
    }
}
