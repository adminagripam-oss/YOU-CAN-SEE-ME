package s;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class q0 extends p0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public q0(Context context) {
        super(context);
    }

    @Override // s.p0, s.s0, s.o0.b
    public CameraCharacteristics e(String str) {
        try {
            return this.f11504a.getCameraCharacteristics(str);
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        }
    }

    @Override // s.p0, s.s0, s.o0.b
    public void f(String str, Executor executor, CameraDevice.StateCallback stateCallback) {
        try {
            this.f11504a.openCamera(str, executor, stateCallback);
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        }
    }
}
