package s;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.SessionConfiguration;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j0 extends i0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public j0(CameraDevice cameraDevice) {
        super((CameraDevice) g1.e.j(cameraDevice), null);
    }

    @Override // s.i0, s.c0.a
    public void a(t.q qVar) {
        SessionConfiguration sessionConfiguration = (SessionConfiguration) qVar.j();
        g1.e.j(sessionConfiguration);
        try {
            this.f11465a.createCaptureSession(sessionConfiguration);
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        }
    }
}
