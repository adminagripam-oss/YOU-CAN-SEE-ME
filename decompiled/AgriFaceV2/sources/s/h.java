package s;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import java.util.List;
import java.util.concurrent.Executor;
import s.h;
/* loaded from: classes.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    private final a f11448a;

    /* loaded from: classes.dex */
    interface a {
        CameraCaptureSession a();

        int b(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback);

        int c(List list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends CameraCaptureSession.CaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        final CameraCaptureSession.CaptureCallback f11449a;

        /* renamed from: b  reason: collision with root package name */
        private final Executor f11450b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b(Executor executor, CameraCaptureSession.CaptureCallback captureCallback) {
            this.f11450b = executor;
            this.f11449a = captureCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j8) {
            s.c.a(this.f11449a, cameraCaptureSession, captureRequest, surface, j8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void i(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            this.f11449a.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void j(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            this.f11449a.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            this.f11449a.onCaptureProgressed(cameraCaptureSession, captureRequest, captureResult);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void l(CameraCaptureSession cameraCaptureSession, int i8) {
            this.f11449a.onCaptureSequenceAborted(cameraCaptureSession, i8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void m(CameraCaptureSession cameraCaptureSession, int i8, long j8) {
            this.f11449a.onCaptureSequenceCompleted(cameraCaptureSession, i8, j8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void n(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j8, long j9) {
            this.f11449a.onCaptureStarted(cameraCaptureSession, captureRequest, j8, j9);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureBufferLost(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final Surface surface, final long j8) {
            this.f11450b.execute(new Runnable() { // from class: s.j
                @Override // java.lang.Runnable
                public final void run() {
                    h.b.this.h(cameraCaptureSession, captureRequest, surface, j8);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
            this.f11450b.execute(new Runnable() { // from class: s.m
                @Override // java.lang.Runnable
                public final void run() {
                    h.b.this.i(cameraCaptureSession, captureRequest, totalCaptureResult);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final CaptureFailure captureFailure) {
            this.f11450b.execute(new Runnable() { // from class: s.o
                @Override // java.lang.Runnable
                public final void run() {
                    h.b.this.j(cameraCaptureSession, captureRequest, captureFailure);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final CaptureResult captureResult) {
            this.f11450b.execute(new Runnable() { // from class: s.l
                @Override // java.lang.Runnable
                public final void run() {
                    h.b.this.k(cameraCaptureSession, captureRequest, captureResult);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(final CameraCaptureSession cameraCaptureSession, final int i8) {
            this.f11450b.execute(new Runnable() { // from class: s.i
                @Override // java.lang.Runnable
                public final void run() {
                    h.b.this.l(cameraCaptureSession, i8);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(final CameraCaptureSession cameraCaptureSession, final int i8, final long j8) {
            this.f11450b.execute(new Runnable() { // from class: s.k
                @Override // java.lang.Runnable
                public final void run() {
                    h.b.this.m(cameraCaptureSession, i8, j8);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final long j8, final long j9) {
            this.f11450b.execute(new Runnable() { // from class: s.n
                @Override // java.lang.Runnable
                public final void run() {
                    h.b.this.n(cameraCaptureSession, captureRequest, j8, j9);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class c extends CameraCaptureSession.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        final CameraCaptureSession.StateCallback f11451a;

        /* renamed from: b  reason: collision with root package name */
        private final Executor f11452b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public c(Executor executor, CameraCaptureSession.StateCallback stateCallback) {
            this.f11452b = executor;
            this.f11451a = stateCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(CameraCaptureSession cameraCaptureSession) {
            this.f11451a.onActive(cameraCaptureSession);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void i(CameraCaptureSession cameraCaptureSession) {
            d.a(this.f11451a, cameraCaptureSession);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void j(CameraCaptureSession cameraCaptureSession) {
            this.f11451a.onClosed(cameraCaptureSession);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k(CameraCaptureSession cameraCaptureSession) {
            this.f11451a.onConfigureFailed(cameraCaptureSession);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void l(CameraCaptureSession cameraCaptureSession) {
            this.f11451a.onConfigured(cameraCaptureSession);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void m(CameraCaptureSession cameraCaptureSession) {
            this.f11451a.onReady(cameraCaptureSession);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void n(CameraCaptureSession cameraCaptureSession, Surface surface) {
            s.b.a(this.f11451a, cameraCaptureSession, surface);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onActive(final CameraCaptureSession cameraCaptureSession) {
            this.f11452b.execute(new Runnable() { // from class: s.s
                @Override // java.lang.Runnable
                public final void run() {
                    h.c.this.h(cameraCaptureSession);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onCaptureQueueEmpty(final CameraCaptureSession cameraCaptureSession) {
            this.f11452b.execute(new Runnable() { // from class: s.r
                @Override // java.lang.Runnable
                public final void run() {
                    h.c.this.i(cameraCaptureSession);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(final CameraCaptureSession cameraCaptureSession) {
            this.f11452b.execute(new Runnable() { // from class: s.q
                @Override // java.lang.Runnable
                public final void run() {
                    h.c.this.j(cameraCaptureSession);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(final CameraCaptureSession cameraCaptureSession) {
            this.f11452b.execute(new Runnable() { // from class: s.t
                @Override // java.lang.Runnable
                public final void run() {
                    h.c.this.k(cameraCaptureSession);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(final CameraCaptureSession cameraCaptureSession) {
            this.f11452b.execute(new Runnable() { // from class: s.p
                @Override // java.lang.Runnable
                public final void run() {
                    h.c.this.l(cameraCaptureSession);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onReady(final CameraCaptureSession cameraCaptureSession) {
            this.f11452b.execute(new Runnable() { // from class: s.u
                @Override // java.lang.Runnable
                public final void run() {
                    h.c.this.m(cameraCaptureSession);
                }
            });
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onSurfacePrepared(final CameraCaptureSession cameraCaptureSession, final Surface surface) {
            this.f11452b.execute(new Runnable() { // from class: s.v
                @Override // java.lang.Runnable
                public final void run() {
                    h.c.this.n(cameraCaptureSession, surface);
                }
            });
        }
    }

    private h(CameraCaptureSession cameraCaptureSession, Handler handler) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f11448a = new w(cameraCaptureSession);
        } else {
            this.f11448a = x.d(cameraCaptureSession, handler);
        }
    }

    public static h d(CameraCaptureSession cameraCaptureSession, Handler handler) {
        return new h(cameraCaptureSession, handler);
    }

    public int a(List list, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) {
        return this.f11448a.c(list, executor, captureCallback);
    }

    public int b(CaptureRequest captureRequest, Executor executor, CameraCaptureSession.CaptureCallback captureCallback) {
        return this.f11448a.b(captureRequest, executor, captureCallback);
    }

    public CameraCaptureSession c() {
        return this.f11448a.a();
    }
}
