package d0;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import java.util.Locale;
/* loaded from: classes.dex */
public abstract class r {

    /* renamed from: a  reason: collision with root package name */
    public static final RectF f6420a = new RectF(-1.0f, -1.0f, 1.0f, 1.0f);

    public static float a(float f8, float f9, float f10, float f11) {
        float f12 = (f8 * f10) + (f9 * f11);
        float f13 = (f8 * f11) - (f9 * f10);
        double sqrt = Math.sqrt((f8 * f8) + (f9 * f9)) * Math.sqrt((f10 * f10) + (f11 * f11));
        return (float) Math.toDegrees(Math.atan2(f13 / sqrt, f12 / sqrt));
    }

    public static Matrix b(RectF rectF) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(f6420a, rectF, Matrix.ScaleToFit.FILL);
        return matrix;
    }

    public static Matrix c(RectF rectF, RectF rectF2, int i8) {
        return d(rectF, rectF2, i8, false);
    }

    public static Matrix d(RectF rectF, RectF rectF2, int i8, boolean z7) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(rectF, f6420a, Matrix.ScaleToFit.FILL);
        matrix.postRotate(i8);
        if (z7) {
            matrix.postScale(-1.0f, 1.0f);
        }
        matrix.postConcat(b(rectF2));
        return matrix;
    }

    public static Size e(Rect rect, int i8) {
        return o(l(rect), i8);
    }

    public static int f(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return u((int) Math.round(Math.atan2(fArr[3], fArr[0]) * 57.29577951308232d));
    }

    public static boolean g(Rect rect, Size size) {
        return (rect.left == 0 && rect.top == 0 && rect.width() == size.getWidth() && rect.height() == size.getHeight()) ? false : true;
    }

    public static boolean h(int i8) {
        if (i8 == 90 || i8 == 270) {
            return true;
        }
        if (i8 == 0 || i8 == 180) {
            return false;
        }
        throw new IllegalArgumentException("Invalid rotation degrees: " + i8);
    }

    public static boolean i(Size size, Size size2) {
        return j(size, false, size2, false);
    }

    public static boolean j(Size size, boolean z7, Size size2, boolean z8) {
        float width;
        float width2;
        float width3;
        float f8;
        if (z7) {
            width = size.getWidth() / size.getHeight();
            width2 = width;
        } else {
            width = (size.getWidth() + 1.0f) / (size.getHeight() - 1.0f);
            width2 = (size.getWidth() - 1.0f) / (size.getHeight() + 1.0f);
        }
        if (z8) {
            width3 = size2.getWidth() / size2.getHeight();
            f8 = width3;
        } else {
            float width4 = (size2.getWidth() + 1.0f) / (size2.getHeight() - 1.0f);
            width3 = (size2.getWidth() - 1.0f) / (size2.getHeight() + 1.0f);
            f8 = width4;
        }
        return width >= width3 && f8 >= width2;
    }

    public static boolean k(Matrix matrix) {
        float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
        matrix.mapVectors(fArr);
        return a(fArr[0], fArr[1], fArr[2], fArr[3]) > 0.0f;
    }

    public static Size l(Rect rect) {
        return new Size(rect.width(), rect.height());
    }

    public static String m(Rect rect) {
        return String.format(Locale.US, "%s(%dx%d)", rect, Integer.valueOf(rect.width()), Integer.valueOf(rect.height()));
    }

    public static Size n(Size size) {
        return new Size(size.getHeight(), size.getWidth());
    }

    public static Size o(Size size, int i8) {
        boolean z7 = i8 % 90 == 0;
        g1.e.b(z7, "Invalid rotation degrees: " + i8);
        return h(u(i8)) ? n(size) : size;
    }

    public static Rect p(Size size) {
        return q(size, 0, 0);
    }

    public static Rect q(Size size, int i8, int i9) {
        return new Rect(i8, i9, size.getWidth() + i8, size.getHeight() + i9);
    }

    public static RectF r(Size size) {
        return s(size, 0, 0);
    }

    public static RectF s(Size size, int i8, int i9) {
        return new RectF(i8, i9, i8 + size.getWidth(), i9 + size.getHeight());
    }

    public static Matrix t(Matrix matrix, Rect rect) {
        Matrix matrix2 = new Matrix(matrix);
        matrix2.postTranslate(-rect.left, -rect.top);
        return matrix2;
    }

    public static int u(int i8) {
        return ((i8 % 360) + 360) % 360;
    }
}
