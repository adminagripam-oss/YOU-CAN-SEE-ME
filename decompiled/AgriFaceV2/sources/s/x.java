package s;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import java.util.List;
import java.util.concurrent.Executor;
import s.h;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class x implements h.a {

    /* renamed from: a  reason: collision with root package name */
    final CameraCaptureSession f11521a;

    /* renamed from: b  reason: collision with root package name */
    final Object f11522b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final Handler f11523a;

        a(Handler handler) {
            this.f11523a = handler;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(CameraCaptureSession cameraCaptureSession, Object obj) {
        this.f11521a = (CameraCaptureSession) g1.e.j(cameraCaptureSession);
        this.f11522b = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static h.a d(CameraCaptureSession cameraCaptureSession, Handler handler) {
        return new x(cameraCaptureSession, new a(handler));
    }

    @Override // s.h.a
    public CameraCaptureSession a() {
        return this.f11521a;
    }

    @Override // s.h.a
    public int b(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) {
        return this.f11521a.setRepeatingRequest(captureRequest, new h.b(executor, captureCallback), ((a) this.f11522b).f11523a);
    }

    @Override // s.h.a
    public int c(List list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) {
        return this.f11521a.captureBurst(list, new h.b(executor, captureCallback), ((a) this.f11522b).f11523a);
    }
}
