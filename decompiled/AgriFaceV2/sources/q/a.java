package q;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import b0.a2;
import b0.f2;
import b0.u0;
import b0.z1;
import x.j;
import y.c0;
/* loaded from: classes.dex */
public final class a extends j {
    public static final u0.a J = u0.a.a("camera2.captureRequest.templateType", Integer.TYPE);
    public static final u0.a K = u0.a.a("camera2.cameraCaptureSession.streamUseCase", Long.TYPE);
    public static final u0.a L = u0.a.a("camera2.cameraDevice.stateCallback", CameraDevice.StateCallback.class);
    public static final u0.a M = u0.a.a("camera2.cameraCaptureSession.stateCallback", CameraCaptureSession.StateCallback.class);
    public static final u0.a N = u0.a.a("camera2.cameraCaptureSession.captureCallback", CameraCaptureSession.CaptureCallback.class);
    public static final u0.a O = u0.a.a("camera2.captureRequest.tag", Object.class);
    public static final u0.a P = u0.a.a("camera2.cameraCaptureSession.physicalCameraId", String.class);

    /* renamed from: q.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0140a implements c0 {

        /* renamed from: a  reason: collision with root package name */
        private final a2 f10205a = a2.d0();

        public a a() {
            return new a(f2.b0(this.f10205a));
        }

        public C0140a b(u0 u0Var) {
            e(u0Var, u0.c.OPTIONAL);
            return this;
        }

        @Override // y.c0
        public z1 c() {
            return this.f10205a;
        }

        public C0140a e(u0 u0Var, u0.c cVar) {
            for (u0.a aVar : u0Var.d()) {
                this.f10205a.F(aVar, cVar, u0Var.b(aVar));
            }
            return this;
        }

        public C0140a f(CaptureRequest.Key key, Object obj) {
            this.f10205a.Y(a.Z(key), obj);
            return this;
        }

        public C0140a g(CaptureRequest.Key key, Object obj, u0.c cVar) {
            this.f10205a.F(a.Z(key), cVar, obj);
            return this;
        }
    }

    public a(u0 u0Var) {
        super(u0Var);
    }

    public static u0.a Z(CaptureRequest.Key key) {
        return u0.a.b("camera2.captureRequest.option." + key.getName(), Object.class, key);
    }

    public j a0() {
        return j.a.f(z()).b();
    }

    public int b0(int i8) {
        return ((Integer) z().f(J, Integer.valueOf(i8))).intValue();
    }

    public CameraDevice.StateCallback c0(CameraDevice.StateCallback stateCallback) {
        return (CameraDevice.StateCallback) z().f(L, stateCallback);
    }

    public String d0(String str) {
        return (String) z().f(P, str);
    }

    public CameraCaptureSession.CaptureCallback e0(CameraCaptureSession.CaptureCallback captureCallback) {
        return (CameraCaptureSession.CaptureCallback) z().f(N, captureCallback);
    }

    public CameraCaptureSession.StateCallback f0(CameraCaptureSession.StateCallback stateCallback) {
        return (CameraCaptureSession.StateCallback) z().f(M, stateCallback);
    }

    public long g0(long j8) {
        return ((Long) z().f(K, Long.valueOf(j8))).longValue();
    }
}
