package r;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import b0.p;
/* loaded from: classes.dex */
final class o2 extends CameraCaptureSession.CaptureCallback {

    /* renamed from: a  reason: collision with root package name */
    private final b0.n f10688a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o2(b0.n nVar) {
        if (nVar == null) {
            throw new NullPointerException("cameraCaptureCallback is null");
        }
        this.f10688a = nVar;
    }

    private int a(CaptureRequest captureRequest) {
        Integer num;
        if ((captureRequest.getTag() instanceof b0.y2) && (num = (Integer) ((b0.y2) captureRequest.getTag()).d("CAPTURE_CONFIG_ID_KEY")) != null) {
            return num.intValue();
        }
        return -1;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        b0.y2 b8;
        super.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
        Object tag = captureRequest.getTag();
        if (tag != null) {
            g1.e.b(tag instanceof b0.y2, "The tagBundle object from the CaptureResult is not a TagBundle object.");
            b8 = (b0.y2) tag;
        } else {
            b8 = b0.y2.b();
        }
        this.f10688a.b(a(captureRequest), new g(b8, totalCaptureResult));
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
        super.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
        this.f10688a.c(a(captureRequest), new b0.p(p.a.ERROR));
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j8, long j9) {
        super.onCaptureStarted(cameraCaptureSession, captureRequest, j8, j9);
        this.f10688a.d(a(captureRequest));
    }
}
