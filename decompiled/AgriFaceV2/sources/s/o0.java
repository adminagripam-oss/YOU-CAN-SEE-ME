package s;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.util.ArrayMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import s.o0;
/* loaded from: classes.dex */
public final class o0 {

    /* renamed from: a  reason: collision with root package name */
    private final b f11490a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f11491b = new ArrayMap(4);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a extends CameraManager.AvailabilityCallback {

        /* renamed from: a  reason: collision with root package name */
        private final Executor f11492a;

        /* renamed from: b  reason: collision with root package name */
        final CameraManager.AvailabilityCallback f11493b;

        /* renamed from: c  reason: collision with root package name */
        private final Object f11494c = new Object();

        /* renamed from: d  reason: collision with root package name */
        private boolean f11495d = false;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(Executor executor, CameraManager.AvailabilityCallback availabilityCallback) {
            this.f11492a = executor;
            this.f11493b = availabilityCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d() {
            f.a(this.f11493b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(String str) {
            this.f11493b.onCameraAvailable(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(String str) {
            this.f11493b.onCameraUnavailable(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void g() {
            synchronized (this.f11494c) {
                this.f11495d = true;
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAccessPrioritiesChanged() {
            synchronized (this.f11494c) {
                if (!this.f11495d) {
                    this.f11492a.execute(new Runnable() { // from class: s.l0
                        @Override // java.lang.Runnable
                        public final void run() {
                            o0.a.this.d();
                        }
                    });
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(final String str) {
            synchronized (this.f11494c) {
                if (!this.f11495d) {
                    this.f11492a.execute(new Runnable() { // from class: s.n0
                        @Override // java.lang.Runnable
                        public final void run() {
                            o0.a.this.e(str);
                        }
                    });
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(final String str) {
            synchronized (this.f11494c) {
                if (!this.f11495d) {
                    this.f11492a.execute(new Runnable() { // from class: s.m0
                        @Override // java.lang.Runnable
                        public final void run() {
                            o0.a.this.f(str);
                        }
                    });
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        static b a(Context context, Handler handler) {
            int i8 = Build.VERSION.SDK_INT;
            return i8 >= 30 ? new r0(context) : i8 >= 29 ? new q0(context) : i8 >= 28 ? p0.i(context) : s0.h(context, handler);
        }

        Set b();

        void c(Executor executor, CameraManager.AvailabilityCallback availabilityCallback);

        void d(CameraManager.AvailabilityCallback availabilityCallback);

        CameraCharacteristics e(String str);

        void f(String str, Executor executor, CameraDevice.StateCallback stateCallback);

        String[] g();
    }

    private o0(b bVar) {
        this.f11490a = bVar;
    }

    public static o0 a(Context context) {
        return b(context, d0.m.a());
    }

    public static o0 b(Context context, Handler handler) {
        return new o0(b.a(context, handler));
    }

    public b0 c(String str) {
        b0 b0Var;
        synchronized (this.f11491b) {
            b0Var = (b0) this.f11491b.get(str);
            if (b0Var == null) {
                try {
                    b0Var = b0.e(this.f11490a.e(str), str);
                    this.f11491b.put(str, b0Var);
                } catch (AssertionError e8) {
                    throw new g(10002, e8.getMessage(), e8);
                }
            }
        }
        return b0Var;
    }

    public String[] d() {
        return this.f11490a.g();
    }

    public Set e() {
        return this.f11490a.b();
    }

    public void f(String str, Executor executor, CameraDevice.StateCallback stateCallback) {
        this.f11490a.f(str, executor, stateCallback);
    }

    public void g(Executor executor, CameraManager.AvailabilityCallback availabilityCallback) {
        this.f11490a.c(executor, availabilityCallback);
    }

    public void h(CameraManager.AvailabilityCallback availabilityCallback) {
        this.f11490a.d(availabilityCallback);
    }
}
