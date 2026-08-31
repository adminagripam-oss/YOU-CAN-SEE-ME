package l0;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
/* loaded from: classes.dex */
public abstract class z {
    public static z j(Bitmap bitmap, d0.g gVar, Rect rect, int i8, Matrix matrix, b0.x xVar) {
        return new b(bitmap, gVar, 42, new Size(bitmap.getWidth(), bitmap.getHeight()), rect, i8, matrix, xVar);
    }

    public static z k(androidx.camera.core.n nVar, d0.g gVar, Rect rect, int i8, Matrix matrix, b0.x xVar) {
        return l(nVar, gVar, new Size(nVar.getWidth(), nVar.getHeight()), rect, i8, matrix, xVar);
    }

    public static z l(androidx.camera.core.n nVar, d0.g gVar, Size size, Rect rect, int i8, Matrix matrix, b0.x xVar) {
        if (k0.b.i(nVar.e())) {
            g1.e.k(gVar, "JPEG image must have Exif.");
        }
        return new b(nVar, gVar, nVar.e(), size, rect, i8, matrix, xVar);
    }

    public static z m(byte[] bArr, d0.g gVar, int i8, Size size, Rect rect, int i9, Matrix matrix, b0.x xVar) {
        return new b(bArr, gVar, i8, size, rect, i9, matrix, xVar);
    }

    public abstract b0.x a();

    public abstract Rect b();

    public abstract Object c();

    public abstract d0.g d();

    public abstract int e();

    public abstract int f();

    public abstract Matrix g();

    public abstract Size h();

    public boolean i() {
        return d0.r.g(b(), h());
    }
}
