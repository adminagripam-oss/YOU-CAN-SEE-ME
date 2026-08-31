package androidx.camera.core.impl.utils;

import android.view.Surface;
/* loaded from: classes.dex */
public abstract class SurfaceUtil {

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f2726a = 0;

        /* renamed from: b  reason: collision with root package name */
        public int f2727b = 0;

        /* renamed from: c  reason: collision with root package name */
        public int f2728c = 0;
    }

    static {
        System.loadLibrary("surface_util_jni");
    }

    public static a a(Surface surface) {
        int[] nativeGetSurfaceInfo = nativeGetSurfaceInfo(surface);
        a aVar = new a();
        aVar.f2726a = nativeGetSurfaceInfo[0];
        aVar.f2727b = nativeGetSurfaceInfo[1];
        aVar.f2728c = nativeGetSurfaceInfo[2];
        return aVar;
    }

    private static native int[] nativeGetSurfaceInfo(Surface surface);
}
