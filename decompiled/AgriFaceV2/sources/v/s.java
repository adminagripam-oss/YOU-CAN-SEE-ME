package v;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Log;
import androidx.concurrent.futures.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import r.s0;
import v.s;
/* loaded from: classes.dex */
public class s {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f12308a;

    /* renamed from: b  reason: collision with root package name */
    private final List f12309b = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends CameraCaptureSession.CaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        final i4.d f12310a = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: v.r
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object c8;
                c8 = s.a.this.c(aVar);
                return c8;
            }
        });

        /* renamed from: b  reason: collision with root package name */
        c.a f12311b;

        a() {
        }

        private void b() {
            c.a aVar = this.f12311b;
            if (aVar != null) {
                aVar.c(null);
                this.f12311b = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object c(c.a aVar) {
            this.f12311b = aVar;
            return "RequestCompleteListener[" + this + "]";
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            b();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            b();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i8) {
            b();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i8, long j8) {
            b();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j8, long j9) {
            b();
        }
    }

    public s(boolean z7) {
        this.f12308a = z7;
    }

    private CameraCaptureSession.CaptureCallback c() {
        final a aVar = new a();
        final i4.d dVar = aVar.f12310a;
        this.f12309b.add(dVar);
        Log.d("RequestMonitor", "RequestListener " + aVar + " monitoring " + this);
        dVar.d(new Runnable() { // from class: v.p
            @Override // java.lang.Runnable
            public final void run() {
                s.this.f(aVar, dVar);
            }
        }, e0.c.b());
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(a aVar, i4.d dVar) {
        Log.d("RequestMonitor", "RequestListener " + aVar + " done " + this);
        this.f12309b.remove(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void g(List list) {
        return null;
    }

    public CameraCaptureSession.CaptureCallback d(CameraCaptureSession.CaptureCallback captureCallback) {
        return h() ? s0.b(c(), captureCallback) : captureCallback;
    }

    public i4.d e() {
        return this.f12309b.isEmpty() ? f0.n.p(null) : f0.n.B(f0.n.G(f0.n.F(new ArrayList(this.f12309b)), new m.a() { // from class: v.q
            @Override // m.a
            public final Object apply(Object obj) {
                Void g8;
                g8 = s.g((List) obj);
                return g8;
            }
        }, e0.c.b()));
    }

    public boolean h() {
        return this.f12308a;
    }

    public void i() {
        LinkedList linkedList = new LinkedList(this.f12309b);
        while (!linkedList.isEmpty()) {
            i4.d dVar = (i4.d) linkedList.poll();
            Objects.requireNonNull(dVar);
            dVar.cancel(true);
        }
    }
}
