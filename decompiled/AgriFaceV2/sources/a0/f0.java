package a0;

import a0.o0;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import b0.x;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f0 implements l0.y {
    private static l0.z b(p0 p0Var, d0.g gVar, androidx.camera.core.n nVar) {
        return l0.z.k(nVar, gVar, p0Var.b(), p0Var.f(), p0Var.g(), d(nVar));
    }

    private static l0.z c(p0 p0Var, d0.g gVar, androidx.camera.core.n nVar) {
        Size size = new Size(nVar.getWidth(), nVar.getHeight());
        int f8 = p0Var.f() - gVar.s();
        Size e8 = e(f8, size);
        Matrix c8 = d0.r.c(new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight()), new RectF(0.0f, 0.0f, e8.getWidth(), e8.getHeight()), f8);
        return l0.z.l(nVar, gVar, e8, f(p0Var.b(), c8), gVar.s(), g(p0Var.g(), c8), d(nVar));
    }

    private static b0.x d(androidx.camera.core.n nVar) {
        return nVar.p() instanceof g0.c ? ((g0.c) nVar.p()).d() : x.a.l();
    }

    private static Size e(int i8, Size size) {
        return d0.r.h(d0.r.u(i8)) ? new Size(size.getHeight(), size.getWidth()) : size;
    }

    private static Rect f(Rect rect, Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        rectF.sort();
        Rect rect2 = new Rect();
        rectF.round(rect2);
        return rect2;
    }

    private static Matrix g(Matrix matrix, Matrix matrix2) {
        Matrix matrix3 = new Matrix(matrix);
        matrix3.postConcat(matrix2);
        return matrix3;
    }

    @Override // l0.y
    /* renamed from: a */
    public l0.z apply(o0.b bVar) {
        d0.g j8;
        androidx.camera.core.n a8 = bVar.a();
        p0 b8 = bVar.b();
        if (k0.b.i(a8.e())) {
            try {
                j8 = d0.g.j(a8);
                a8.i()[0].c().rewind();
            } catch (IOException e8) {
                throw new y.v0(1, "Failed to extract EXIF data.", e8);
            }
        } else {
            j8 = null;
        }
        if (y.f137g.b(a8)) {
            g1.e.k(j8, "JPEG image must have exif.");
            return c(b8, j8, a8);
        }
        return b(b8, j8, a8);
    }
}
