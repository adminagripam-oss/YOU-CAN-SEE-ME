package r;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
class h2 extends CameraCaptureSession.CaptureCallback {

    /* renamed from: b  reason: collision with root package name */
    a f10504b = null;

    /* renamed from: a  reason: collision with root package name */
    final Map f10503a = new HashMap();

    /* loaded from: classes.dex */
    interface a {
        void a(CameraCaptureSession cameraCaptureSession, int i8, boolean z7);
    }

    private List b(CaptureRequest captureRequest) {
        List list = (List) this.f10503a.get(captureRequest);
        return list != null ? list : Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(CaptureRequest captureRequest, List list) {
        List list2 = (List) this.f10503a.get(captureRequest);
        if (list2 == null) {
            this.f10503a.put(captureRequest, list);
            return;
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        this.f10503a.put(captureRequest, arrayList);
    }

    public void c(a aVar) {
        this.f10504b = aVar;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureBufferLost(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j8) {
        for (CameraCaptureSession.CaptureCallback captureCallback : b(captureRequest)) {
            s.c.a(captureCallback, cameraCaptureSession, captureRequest, surface, j8);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        for (CameraCaptureSession.CaptureCallback captureCallback : b(captureRequest)) {
            captureCallback.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
        for (CameraCaptureSession.CaptureCallback captureCallback : b(captureRequest)) {
            captureCallback.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
        for (CameraCaptureSession.CaptureCallback captureCallback : b(captureRequest)) {
            captureCallback.onCaptureProgressed(cameraCaptureSession, captureRequest, captureResult);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i8) {
        for (List<CameraCaptureSession.CaptureCallback> list : this.f10503a.values()) {
            for (CameraCaptureSession.CaptureCallback captureCallback : list) {
                captureCallback.onCaptureSequenceAborted(cameraCaptureSession, i8);
            }
        }
        a aVar = this.f10504b;
        if (aVar != null) {
            aVar.a(cameraCaptureSession, i8, true);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i8, long j8) {
        for (List<CameraCaptureSession.CaptureCallback> list : this.f10503a.values()) {
            for (CameraCaptureSession.CaptureCallback captureCallback : list) {
                captureCallback.onCaptureSequenceCompleted(cameraCaptureSession, i8, j8);
            }
        }
        a aVar = this.f10504b;
        if (aVar != null) {
            aVar.a(cameraCaptureSession, i8, false);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j8, long j9) {
        for (CameraCaptureSession.CaptureCallback captureCallback : b(captureRequest)) {
            captureCallback.onCaptureStarted(cameraCaptureSession, captureRequest, j8, j9);
        }
    }
}
