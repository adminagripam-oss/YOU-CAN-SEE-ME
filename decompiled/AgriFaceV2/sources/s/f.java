package s;

import android.hardware.camera2.CameraManager;
/* loaded from: classes.dex */
public abstract class f {
    public static void a(CameraManager.AvailabilityCallback availabilityCallback) {
        availabilityCallback.onCameraAccessPrioritiesChanged();
    }
}
