package b0;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.LinkedHashSet;
import y.q;
/* loaded from: classes.dex */
public abstract class q0 {

    /* renamed from: a  reason: collision with root package name */
    private static final y.q f4327a = new q.a().b(2).a();

    /* loaded from: classes.dex */
    private static class a {
        static int a(Context context) {
            return context.getDeviceId();
        }
    }

    /* loaded from: classes.dex */
    public static class b extends Exception {

        /* renamed from: e  reason: collision with root package name */
        private int f4328e;

        public b(String str, int i8, Throwable th) {
            super(str, th);
            this.f4328e = i8;
        }

        public int a() {
            return this.f4328e;
        }
    }

    public static void a(Context context, l0 l0Var, y.q qVar) {
        Integer d8;
        LinkedHashSet a8;
        int i8 = 0;
        IllegalArgumentException illegalArgumentException = null;
        if (Build.VERSION.SDK_INT >= 34 && a.a(context) != 0) {
            if (l0Var.a().isEmpty()) {
                throw new b("No cameras available", 0, null);
            }
            y.f1.a("CameraValidator", "Virtual device with ID: " + a.a(context) + " has " + a8.size() + " cameras. Skipping validation.");
            return;
        }
        if (qVar != null) {
            try {
                d8 = qVar.d();
                if (d8 == null) {
                    y.f1.l("CameraValidator", "No lens facing info in the availableCamerasSelector, don't verify the camera lens facing.");
                    return;
                }
            } catch (IllegalStateException e8) {
                y.f1.d("CameraValidator", "Cannot get lens facing from the availableCamerasSelector don't verify the camera lens facing.", e8);
                return;
            }
        } else {
            d8 = null;
        }
        y.f1.a("CameraValidator", "Verifying camera lens facing on " + Build.DEVICE + ", lensFacingInteger: " + d8);
        PackageManager packageManager = context.getPackageManager();
        try {
            if (packageManager.hasSystemFeature("android.hardware.camera") && (qVar == null || d8.intValue() == 1)) {
                y.q.f12884d.e(l0Var.a());
                i8 = 1;
            }
        } catch (IllegalArgumentException e9) {
            illegalArgumentException = e9;
            y.f1.m("CameraValidator", "Camera LENS_FACING_BACK verification failed", illegalArgumentException);
        }
        try {
            if (packageManager.hasSystemFeature("android.hardware.camera.front") && (qVar == null || d8.intValue() == 0)) {
                y.q.f12883c.e(l0Var.a());
                i8++;
            }
        } catch (IllegalArgumentException e10) {
            illegalArgumentException = e10;
            y.f1.m("CameraValidator", "Camera LENS_FACING_FRONT verification failed", illegalArgumentException);
        }
        try {
            f4327a.e(l0Var.a());
            y.f1.a("CameraValidator", "Found a LENS_FACING_EXTERNAL camera");
            i8++;
        } catch (IllegalArgumentException unused) {
        }
        if (illegalArgumentException == null) {
            return;
        }
        y.f1.c("CameraValidator", "Camera LensFacing verification failed, existing cameras: " + l0Var.a());
        throw new b("Expected camera missing from device.", i8, illegalArgumentException);
    }
}
