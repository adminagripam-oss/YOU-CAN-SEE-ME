package org.tensorflow.lite.gpu;
/* loaded from: classes.dex */
abstract class GpuDelegateNative {

    /* renamed from: a  reason: collision with root package name */
    static final Throwable f9912a;

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f9913b = false;

    static {
        try {
            System.loadLibrary("tensorflowlite_gpu_jni");
            e = null;
        } catch (UnsatisfiedLinkError e8) {
            e = e8;
        }
        f9912a = e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        if (f9913b) {
            return;
        }
        try {
            nativeDoNothing();
            f9913b = true;
        } catch (UnsatisfiedLinkError e8) {
            Throwable th = f9912a;
            if (th == null) {
                th = e8;
            }
            UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError("Failed to load native GpuDelegate methods. Check that the correct native libraries are present, and, if using a custom native library, have been properly loaded via System.loadLibrary():\n  " + th);
            unsatisfiedLinkError.initCause(e8);
            unsatisfiedLinkError.addSuppressed(th);
            throw unsatisfiedLinkError;
        }
    }

    private static native void nativeDoNothing();
}
