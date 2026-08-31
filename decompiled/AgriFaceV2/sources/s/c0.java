package s;

import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Handler;
import java.util.concurrent.Executor;
import s.c0;
/* loaded from: classes.dex */
public final class c0 {

    /* renamed from: a  reason: collision with root package name */
    private final a f11432a;

    /* loaded from: classes.dex */
    interface a {
        void a(t.q qVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends CameraDevice.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        final CameraDevice.StateCallback f11433a;

        /* renamed from: b  reason: collision with root package name */
        private final Executor f11434b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b(Executor executor, CameraDevice.StateCallback stateCallback) {
            this.f11434b = executor;
            this.f11433a = stateCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(CameraDevice cameraDevice) {
            this.f11433a.onClosed(cameraDevice);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(CameraDevice cameraDevice) {
            this.f11433a.onDisconnected(cameraDevice);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g(CameraDevice cameraDevice, int i8) {
            this.f11433a.onError(cameraDevice, i8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(CameraDevice cameraDevice) {
            this.f11433a.onOpened(cameraDevice);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(final CameraDevice cameraDevice) {
            this.f11434b.execute(new Runnable() { // from class: s.d0
                @Override // java.lang.Runnable
                public final void run() {
                    c0.b.this.e(cameraDevice);
                }
            });
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(final CameraDevice cameraDevice) {
            this.f11434b.execute(new Runnable() { // from class: s.e0
                @Override // java.lang.Runnable
                public final void run() {
                    c0.b.this.f(cameraDevice);
                }
            });
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(final CameraDevice cameraDevice, final int i8) {
            this.f11434b.execute(new Runnable() { // from class: s.g0
                @Override // java.lang.Runnable
                public final void run() {
                    c0.b.this.g(cameraDevice, i8);
                }
            });
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(final CameraDevice cameraDevice) {
            this.f11434b.execute(new Runnable() { // from class: s.f0
                @Override // java.lang.Runnable
                public final void run() {
                    c0.b.this.h(cameraDevice);
                }
            });
        }
    }

    private c0(CameraDevice cameraDevice, Handler handler) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f11432a = new j0(cameraDevice);
        } else {
            this.f11432a = i0.e(cameraDevice, handler);
        }
    }

    public static c0 b(CameraDevice cameraDevice, Handler handler) {
        return new c0(cameraDevice, handler);
    }

    public void a(t.q qVar) {
        this.f11432a.a(qVar);
    }
}
