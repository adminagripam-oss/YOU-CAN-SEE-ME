package m0;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLExt;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Size;
import android.view.Surface;
import java.util.Map;
import l0.x;
import n0.d;
import y.a0;
import y.e1;
import y.f1;
import y.v1;
/* loaded from: classes.dex */
public final class c extends x {

    /* renamed from: n  reason: collision with root package name */
    private int f9079n = -1;

    /* renamed from: o  reason: collision with root package name */
    private int f9080o = -1;

    /* renamed from: p  reason: collision with root package name */
    private final e1 f9081p;

    /* renamed from: q  reason: collision with root package name */
    private final e1 f9082q;

    public c(e1 e1Var, e1 e1Var2) {
        this.f9081p = e1Var;
        this.f9082q = e1Var2;
    }

    private static float[] u(Size size, Size size2, e1 e1Var) {
        float[] l8 = n0.d.l();
        float[] l9 = n0.d.l();
        float[] l10 = n0.d.l();
        Matrix.scaleM(l8, 0, size.getWidth() / size2.getWidth(), size.getHeight() / size2.getHeight(), 1.0f);
        Matrix.translateM(l9, 0, e1Var.c() / e1Var.e(), e1Var.d() / e1Var.b(), 0.0f);
        Matrix.multiplyMM(l10, 0, l8, 0, l9, 0);
        return l10;
    }

    private void w(n0.g gVar, v1 v1Var, SurfaceTexture surfaceTexture, e1 e1Var, int i8, boolean z7) {
        s(i8);
        GLES20.glViewport(0, 0, gVar.c(), gVar.b());
        GLES20.glScissor(0, 0, gVar.c(), gVar.b());
        float[] fArr = new float[16];
        surfaceTexture.getTransformMatrix(fArr);
        float[] fArr2 = new float[16];
        v1Var.r(fArr2, fArr, z7);
        d.f fVar = (d.f) g1.e.j(this.f8956k);
        if (fVar instanceof d.g) {
            ((d.g) fVar).h(fArr2);
        }
        fVar.e(u(new Size((int) (gVar.c() * e1Var.e()), (int) (gVar.b() * e1Var.b())), new Size(gVar.c(), gVar.b()), e1Var));
        fVar.d(e1Var.a());
        GLES20.glEnable(3042);
        GLES20.glBlendFuncSeparate(770, 771, 1, 771);
        GLES20.glDrawArrays(5, 0, 4);
        n0.d.g("glDrawArrays");
        GLES20.glDisable(3042);
    }

    @Override // l0.x
    public n0.e h(a0 a0Var, Map map) {
        n0.e h8 = super.h(a0Var, map);
        this.f9079n = n0.d.p();
        this.f9080o = n0.d.p();
        return h8;
    }

    @Override // l0.x
    public void k() {
        super.k();
        this.f9079n = -1;
        this.f9080o = -1;
    }

    public int t(boolean z7) {
        n0.d.i(this.f8946a, true);
        n0.d.h(this.f8948c);
        return z7 ? this.f9079n : this.f9080o;
    }

    public void v(long j8, Surface surface, v1 v1Var, SurfaceTexture surfaceTexture, SurfaceTexture surfaceTexture2) {
        n0.d.i(this.f8946a, true);
        n0.d.h(this.f8948c);
        n0.g f8 = f(surface);
        if (f8 == n0.d.f9729l) {
            f8 = c(surface);
            if (f8 == null) {
                return;
            }
            this.f8947b.put(surface, f8);
        }
        if (surface != this.f8954i) {
            i(f8.a());
            this.f8954i = surface;
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16384);
        n0.g gVar = f8;
        w(gVar, v1Var, surfaceTexture, this.f9081p, this.f9079n, true);
        w(gVar, v1Var, surfaceTexture2, this.f9082q, this.f9080o, true);
        EGLExt.eglPresentationTimeANDROID(this.f8949d, f8.a(), j8);
        if (EGL14.eglSwapBuffers(this.f8949d, f8.a())) {
            return;
        }
        f1.l("DualOpenGlRenderer", "Failed to swap buffers with EGL error: 0x" + Integer.toHexString(EGL14.eglGetError()));
        m(surface, false);
    }
}
