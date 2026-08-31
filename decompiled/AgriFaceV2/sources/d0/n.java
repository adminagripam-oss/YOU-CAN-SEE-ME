package d0;

import android.opengl.Matrix;
/* loaded from: classes.dex */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    private static final float[] f6419a = new float[16];

    private static void a(float[] fArr, float f8, float f9) {
        Matrix.translateM(fArr, 0, -f8, -f9, 0.0f);
    }

    private static void b(float[] fArr, float f8, float f9) {
        Matrix.translateM(fArr, 0, f8, f9, 0.0f);
    }

    public static void c(float[] fArr, float f8, float f9, float f10) {
        b(fArr, f9, f10);
        Matrix.rotateM(fArr, 0, f8, 0.0f, 0.0f, 1.0f);
        a(fArr, f9, f10);
    }

    public static void d(float[] fArr, float f8) {
        b(fArr, 0.0f, f8);
        Matrix.scaleM(fArr, 0, 1.0f, -1.0f, 1.0f);
        a(fArr, 0.0f, f8);
    }
}
