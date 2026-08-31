package r;

import android.hardware.camera2.CameraCaptureSession;
/* loaded from: classes.dex */
final class p2 extends b0.n {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.CaptureCallback f10699a;

    private p2(CameraCaptureSession.CaptureCallback captureCallback) {
        if (captureCallback == null) {
            throw new NullPointerException("captureCallback is null");
        }
        this.f10699a = captureCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static p2 e(CameraCaptureSession.CaptureCallback captureCallback) {
        return new p2(captureCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraCaptureSession.CaptureCallback f() {
        return this.f10699a;
    }
}
