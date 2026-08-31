package org.tensorflow.lite.gpu;

import java.io.Closeable;
/* loaded from: classes.dex */
public class GpuDelegate implements Closeable {

    /* renamed from: e  reason: collision with root package name */
    private long f9911e;

    public GpuDelegate() {
        this(new a());
    }

    private static native long createDelegate(boolean z7, boolean z8, int i8, String str, String str2, int i9);

    private static native void deleteDelegate(long j8);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j8 = this.f9911e;
        if (j8 != 0) {
            deleteDelegate(j8);
            this.f9911e = 0L;
        }
    }

    public GpuDelegate(a aVar) {
        GpuDelegateNative.a();
        this.f9911e = createDelegate(aVar.f(), aVar.a(), aVar.c(), aVar.e(), aVar.d(), aVar.b().e());
    }
}
