package s;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class p0 extends s0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public p0(Context context) {
        super(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static p0 i(Context context) {
        return new p0(context);
    }

    private boolean j(Throwable th) {
        return Build.VERSION.SDK_INT == 28 && k(th);
    }

    private static boolean k(Throwable th) {
        StackTraceElement[] stackTrace;
        if (!th.getClass().equals(RuntimeException.class) || (stackTrace = th.getStackTrace()) == null || stackTrace.length < 0) {
            return false;
        }
        return "_enableShutterSound".equals(stackTrace[0].getMethodName());
    }

    private void l(Throwable th) {
        throw new g(10001, th);
    }

    @Override // s.s0, s.o0.b
    public void c(Executor executor, CameraManager.AvailabilityCallback availabilityCallback) {
        this.f11504a.registerAvailabilityCallback(executor, availabilityCallback);
    }

    @Override // s.s0, s.o0.b
    public void d(CameraManager.AvailabilityCallback availabilityCallback) {
        this.f11504a.unregisterAvailabilityCallback(availabilityCallback);
    }

    @Override // s.s0, s.o0.b
    public CameraCharacteristics e(String str) {
        try {
            return super.e(str);
        } catch (RuntimeException e8) {
            if (j(e8)) {
                l(e8);
            }
            throw e8;
        }
    }

    @Override // s.s0, s.o0.b
    public void f(String str, Executor executor, CameraDevice.StateCallback stateCallback) {
        try {
            this.f11504a.openCamera(str, executor, stateCallback);
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        } catch (IllegalArgumentException e9) {
            throw e9;
        } catch (SecurityException e10) {
        } catch (RuntimeException e11) {
            if (j(e11)) {
                l(e11);
            }
            throw e11;
        }
    }
}
