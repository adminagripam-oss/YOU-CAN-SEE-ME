package io.flutter.view;

import android.hardware.display.DisplayManager;
import android.view.Choreographer;
import io.flutter.embedding.engine.FlutterJNI;
import java.util.Objects;
/* loaded from: classes.dex */
public class h {

    /* renamed from: e  reason: collision with root package name */
    private static h f8169e;

    /* renamed from: f  reason: collision with root package name */
    private static b f8170f;

    /* renamed from: b  reason: collision with root package name */
    private FlutterJNI f8172b;

    /* renamed from: a  reason: collision with root package name */
    private long f8171a = -1;

    /* renamed from: c  reason: collision with root package name */
    private c f8173c = new c(0);

    /* renamed from: d  reason: collision with root package name */
    private final FlutterJNI.b f8174d = new a();

    /* loaded from: classes.dex */
    class a implements FlutterJNI.b {
        a() {
        }

        private Choreographer.FrameCallback b(long j8) {
            if (h.this.f8173c != null) {
                h.this.f8173c.f8178a = j8;
                c cVar = h.this.f8173c;
                h.this.f8173c = null;
                return cVar;
            }
            return new c(j8);
        }

        @Override // io.flutter.embedding.engine.FlutterJNI.b
        public void a(long j8) {
            Choreographer.getInstance().postFrameCallback(b(j8));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements DisplayManager.DisplayListener {

        /* renamed from: a  reason: collision with root package name */
        private DisplayManager f8176a;

        b(DisplayManager displayManager) {
            this.f8176a = displayManager;
        }

        void a() {
            this.f8176a.registerDisplayListener(this, null);
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i8) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i8) {
            if (i8 == 0) {
                float refreshRate = this.f8176a.getDisplay(0).getRefreshRate();
                h.this.f8171a = (long) (1.0E9d / refreshRate);
                h.this.f8172b.setRefreshRateFPS(refreshRate);
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i8) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c implements Choreographer.FrameCallback {

        /* renamed from: a  reason: collision with root package name */
        private long f8178a;

        c(long j8) {
            this.f8178a = j8;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j8) {
            long nanoTime = System.nanoTime() - j8;
            h.this.f8172b.onVsync(nanoTime < 0 ? 0L : nanoTime, h.this.f8171a, this.f8178a);
            h.this.f8173c = this;
        }
    }

    private h(FlutterJNI flutterJNI) {
        this.f8172b = flutterJNI;
    }

    public static h f(DisplayManager displayManager, FlutterJNI flutterJNI) {
        if (f8169e == null) {
            f8169e = new h(flutterJNI);
        }
        if (f8170f == null) {
            h hVar = f8169e;
            Objects.requireNonNull(hVar);
            b bVar = new b(displayManager);
            f8170f = bVar;
            bVar.a();
        }
        if (f8169e.f8171a == -1) {
            float refreshRate = displayManager.getDisplay(0).getRefreshRate();
            f8169e.f8171a = (long) (1.0E9d / refreshRate);
            flutterJNI.setRefreshRateFPS(refreshRate);
        }
        return f8169e;
    }

    public void g() {
        this.f8172b.setAsyncWaitForVsyncDelegate(this.f8174d);
    }
}
