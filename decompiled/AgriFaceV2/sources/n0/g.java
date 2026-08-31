package n0;

import android.opengl.EGLSurface;
/* loaded from: classes.dex */
public abstract class g {
    public static g d(EGLSurface eGLSurface, int i8, int i9) {
        return new c(eGLSurface, i8, i9);
    }

    public abstract EGLSurface a();

    public abstract int b();

    public abstract int c();
}
