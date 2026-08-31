package r;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes.dex */
public interface k4 {

    /* loaded from: classes.dex */
    public interface a {
        Executor c();

        i4.d e(List list, long j8);

        i4.d i(CameraDevice cameraDevice, t.q qVar, List list);

        t.q l(int i8, List list, c cVar);

        boolean stop();
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Executor f10575a;

        /* renamed from: b  reason: collision with root package name */
        private final ScheduledExecutorService f10576b;

        /* renamed from: c  reason: collision with root package name */
        private final Handler f10577c;

        /* renamed from: d  reason: collision with root package name */
        private final b3 f10578d;

        /* renamed from: e  reason: collision with root package name */
        private final b0.l2 f10579e;

        /* renamed from: f  reason: collision with root package name */
        private final b0.l2 f10580f;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b(Executor executor, ScheduledExecutorService scheduledExecutorService, Handler handler, b3 b3Var, b0.l2 l2Var, b0.l2 l2Var2) {
            this.f10575a = executor;
            this.f10576b = scheduledExecutorService;
            this.f10577c = handler;
            this.f10578d = b3Var;
            this.f10579e = l2Var;
            this.f10580f = l2Var2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a a() {
            return new u4(this.f10579e, this.f10580f, this.f10578d, this.f10575a, this.f10576b, this.f10577c);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class c {
        /* JADX INFO: Access modifiers changed from: package-private */
        public void o(k4 k4Var) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void p(k4 k4Var) {
        }

        public void q(k4 k4Var) {
        }

        public abstract void r(k4 k4Var);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void s(k4 k4Var);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void t(k4 k4Var);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void u(k4 k4Var);

        /* JADX INFO: Access modifiers changed from: package-private */
        public void v(k4 k4Var, Surface surface) {
        }
    }

    c a();

    void b();

    void close();

    void d();

    int f(List list, CameraCaptureSession.CaptureCallback captureCallback);

    i4.d g();

    s.h h();

    void j(int i8);

    void k();

    CameraDevice m();

    int n(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback);
}
