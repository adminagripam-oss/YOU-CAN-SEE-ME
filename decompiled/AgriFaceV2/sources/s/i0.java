package s;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.InputConfiguration;
import android.os.Handler;
import java.util.List;
import s.h;
import s.k0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i0 extends h0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public i0(CameraDevice cameraDevice, Object obj) {
        super(cameraDevice, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i0 e(CameraDevice cameraDevice, Handler handler) {
        return new i0(cameraDevice, new k0.a(handler));
    }

    @Override // s.c0.a
    public void a(t.q qVar) {
        k0.c(this.f11465a, qVar);
        h.c cVar = new h.c(qVar.a(), qVar.e());
        List c8 = qVar.c();
        Handler handler = ((k0.a) g1.e.j((k0.a) this.f11466b)).f11467a;
        t.j b8 = qVar.b();
        try {
            if (b8 != null) {
                InputConfiguration inputConfiguration = (InputConfiguration) b8.a();
                g1.e.j(inputConfiguration);
                this.f11465a.createReprocessableCaptureSessionByConfigurations(inputConfiguration, t.q.h(c8), cVar, handler);
            } else if (qVar.d() == 1) {
                this.f11465a.createConstrainedHighSpeedCaptureSession(k0.d(c8), cVar, handler);
            } else {
                this.f11465a.createCaptureSessionByOutputConfigurations(t.q.h(c8), cVar, handler);
            }
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        }
    }
}
