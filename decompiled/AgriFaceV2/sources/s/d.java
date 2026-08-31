package s;

import android.hardware.camera2.CameraCaptureSession;
/* loaded from: classes.dex */
public abstract class d {
    public static void a(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        stateCallback.onCaptureQueueEmpty(cameraCaptureSession);
    }
}
