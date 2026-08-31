package l0;

import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ImageProcessingUtil;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import n0.d;
import n0.e;
import y.f1;
/* loaded from: classes.dex */
public class x {

    /* renamed from: c  reason: collision with root package name */
    protected Thread f8948c;

    /* renamed from: g  reason: collision with root package name */
    protected EGLConfig f8952g;

    /* renamed from: i  reason: collision with root package name */
    protected Surface f8954i;

    /* renamed from: a  reason: collision with root package name */
    protected final AtomicBoolean f8946a = new AtomicBoolean(false);

    /* renamed from: b  reason: collision with root package name */
    protected final Map f8947b = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    protected EGLDisplay f8949d = EGL14.EGL_NO_DISPLAY;

    /* renamed from: e  reason: collision with root package name */
    protected EGLContext f8950e = EGL14.EGL_NO_CONTEXT;

    /* renamed from: f  reason: collision with root package name */
    protected int[] f8951f = n0.d.f9718a;

    /* renamed from: h  reason: collision with root package name */
    protected EGLSurface f8953h = EGL14.EGL_NO_SURFACE;

    /* renamed from: j  reason: collision with root package name */
    protected Map f8955j = Collections.emptyMap();

    /* renamed from: k  reason: collision with root package name */
    protected d.f f8956k = null;

    /* renamed from: l  reason: collision with root package name */
    protected d.e f8957l = d.e.UNKNOWN;

    /* renamed from: m  reason: collision with root package name */
    private int f8958m = -1;

    private void a(int i8) {
        GLES20.glActiveTexture(33984);
        n0.d.g("glActiveTexture");
        GLES20.glBindTexture(36197, i8);
        n0.d.g("glBindTexture");
    }

    private void b(y.a0 a0Var, e.a aVar) {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        this.f8949d = eglGetDisplay;
        if (Objects.equals(eglGetDisplay, EGL14.EGL_NO_DISPLAY)) {
            throw new IllegalStateException("Unable to get EGL14 display");
        }
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(this.f8949d, iArr, 0, iArr, 1)) {
            this.f8949d = EGL14.EGL_NO_DISPLAY;
            throw new IllegalStateException("Unable to initialize EGL14");
        }
        if (aVar != null) {
            aVar.c(iArr[0] + "." + iArr[1]);
        }
        int i8 = a0Var.d() ? 10 : 8;
        int[] iArr2 = {12324, i8, 12323, i8, 12322, i8, 12321, a0Var.d() ? 2 : 8, 12325, 0, 12326, 0, 12352, a0Var.d() ? 64 : 4, 12610, a0Var.d() ? -1 : 1, 12339, 5, 12344};
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!EGL14.eglChooseConfig(this.f8949d, iArr2, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            throw new IllegalStateException("Unable to find a suitable EGLConfig");
        }
        EGLConfig eGLConfig = eGLConfigArr[0];
        int[] iArr3 = new int[3];
        iArr3[0] = 12440;
        iArr3[1] = a0Var.d() ? 3 : 2;
        iArr3[2] = 12344;
        EGLContext eglCreateContext = EGL14.eglCreateContext(this.f8949d, eGLConfig, EGL14.EGL_NO_CONTEXT, iArr3, 0);
        n0.d.f("eglCreateContext");
        this.f8952g = eGLConfig;
        this.f8950e = eglCreateContext;
        int[] iArr4 = new int[1];
        EGL14.eglQueryContext(this.f8949d, eglCreateContext, 12440, iArr4, 0);
        Log.d("OpenGlRenderer", "EGLContext created, client version " + iArr4[0]);
    }

    private void d() {
        EGLDisplay eGLDisplay = this.f8949d;
        EGLConfig eGLConfig = this.f8952g;
        Objects.requireNonNull(eGLConfig);
        this.f8953h = n0.d.n(eGLDisplay, eGLConfig, 1, 1);
    }

    private g1.d e(y.a0 a0Var) {
        n0.d.i(this.f8946a, false);
        try {
            b(a0Var, null);
            d();
            i(this.f8953h);
            String glGetString = GLES20.glGetString(7939);
            String eglQueryString = EGL14.eglQueryString(this.f8949d, 12373);
            if (glGetString == null) {
                glGetString = "";
            }
            if (eglQueryString == null) {
                eglQueryString = "";
            }
            return new g1.d(glGetString, eglQueryString);
        } catch (IllegalStateException e8) {
            f1.m("OpenGlRenderer", "Failed to get GL or EGL extensions: " + e8.getMessage(), e8);
            return new g1.d("", "");
        } finally {
            l();
        }
    }

    private void l() {
        for (d.f fVar : this.f8955j.values()) {
            fVar.b();
        }
        this.f8955j = Collections.emptyMap();
        this.f8956k = null;
        if (!Objects.equals(this.f8949d, EGL14.EGL_NO_DISPLAY)) {
            EGLDisplay eGLDisplay = this.f8949d;
            EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
            for (n0.g gVar : this.f8947b.values()) {
                if (!Objects.equals(gVar.a(), EGL14.EGL_NO_SURFACE) && !EGL14.eglDestroySurface(this.f8949d, gVar.a())) {
                    n0.d.e("eglDestroySurface");
                }
            }
            this.f8947b.clear();
            if (!Objects.equals(this.f8953h, EGL14.EGL_NO_SURFACE)) {
                EGL14.eglDestroySurface(this.f8949d, this.f8953h);
                this.f8953h = EGL14.EGL_NO_SURFACE;
            }
            if (!Objects.equals(this.f8950e, EGL14.EGL_NO_CONTEXT)) {
                EGL14.eglDestroyContext(this.f8949d, this.f8950e);
                this.f8950e = EGL14.EGL_NO_CONTEXT;
            }
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.f8949d);
            this.f8949d = EGL14.EGL_NO_DISPLAY;
        }
        this.f8952g = null;
        this.f8958m = -1;
        this.f8957l = d.e.UNKNOWN;
        this.f8954i = null;
        this.f8948c = null;
    }

    private void q(ByteBuffer byteBuffer, Size size, float[] fArr) {
        g1.e.b(byteBuffer.capacity() == (size.getWidth() * size.getHeight()) * 4, "ByteBuffer capacity is not equal to width * height * 4.");
        g1.e.b(byteBuffer.isDirect(), "ByteBuffer is not direct.");
        int u7 = n0.d.u();
        GLES20.glActiveTexture(33985);
        n0.d.g("glActiveTexture");
        GLES20.glBindTexture(3553, u7);
        n0.d.g("glBindTexture");
        GLES20.glTexImage2D(3553, 0, 6407, size.getWidth(), size.getHeight(), 0, 6407, 5121, null);
        n0.d.g("glTexImage2D");
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        int t8 = n0.d.t();
        GLES20.glBindFramebuffer(36160, t8);
        n0.d.g("glBindFramebuffer");
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, u7, 0);
        n0.d.g("glFramebufferTexture2D");
        GLES20.glActiveTexture(33984);
        n0.d.g("glActiveTexture");
        GLES20.glBindTexture(36197, this.f8958m);
        n0.d.g("glBindTexture");
        this.f8954i = null;
        GLES20.glViewport(0, 0, size.getWidth(), size.getHeight());
        GLES20.glScissor(0, 0, size.getWidth(), size.getHeight());
        d.f fVar = (d.f) g1.e.j(this.f8956k);
        if (fVar instanceof d.g) {
            ((d.g) fVar).h(fArr);
        }
        GLES20.glDrawArrays(5, 0, 4);
        n0.d.g("glDrawArrays");
        GLES20.glReadPixels(0, 0, size.getWidth(), size.getHeight(), 6408, 5121, byteBuffer);
        n0.d.g("glReadPixels");
        GLES20.glBindFramebuffer(36160, 0);
        n0.d.s(u7);
        n0.d.r(t8);
        a(this.f8958m);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public n0.g c(Surface surface) {
        try {
            EGLDisplay eGLDisplay = this.f8949d;
            EGLConfig eGLConfig = this.f8952g;
            Objects.requireNonNull(eGLConfig);
            EGLSurface q8 = n0.d.q(eGLDisplay, eGLConfig, surface, this.f8951f);
            Size x7 = n0.d.x(this.f8949d, q8);
            return n0.g.d(q8, x7.getWidth(), x7.getHeight());
        } catch (IllegalArgumentException | IllegalStateException e8) {
            f1.m("OpenGlRenderer", "Failed to create EGL surface: " + e8.getMessage(), e8);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public n0.g f(Surface surface) {
        g1.e.m(this.f8947b.containsKey(surface), "The surface is not registered.");
        n0.g gVar = (n0.g) this.f8947b.get(surface);
        Objects.requireNonNull(gVar);
        return gVar;
    }

    public int g() {
        n0.d.i(this.f8946a, true);
        n0.d.h(this.f8948c);
        return this.f8958m;
    }

    public n0.e h(y.a0 a0Var, Map map) {
        n0.d.i(this.f8946a, false);
        e.a a8 = n0.e.a();
        try {
            if (a0Var.d()) {
                g1.d e8 = e(a0Var);
                String str = (String) g1.e.j((String) e8.f7099a);
                String str2 = (String) g1.e.j((String) e8.f7100b);
                if (!str.contains("GL_EXT_YUV_target")) {
                    f1.l("OpenGlRenderer", "Device does not support GL_EXT_YUV_target. Fallback to SDR.");
                    a0Var = y.a0.f12730d;
                }
                this.f8951f = n0.d.k(str2, a0Var);
                a8.d(str);
                a8.b(str2);
            }
            b(a0Var, a8);
            d();
            i(this.f8953h);
            a8.e(n0.d.w());
            this.f8955j = n0.d.o(a0Var, map);
            int p8 = n0.d.p();
            this.f8958m = p8;
            s(p8);
            this.f8948c = Thread.currentThread();
            this.f8946a.set(true);
            return a8.a();
        } catch (IllegalArgumentException | IllegalStateException e9) {
            l();
            throw e9;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void i(EGLSurface eGLSurface) {
        g1.e.j(this.f8949d);
        g1.e.j(this.f8950e);
        if (!EGL14.eglMakeCurrent(this.f8949d, eGLSurface, eGLSurface, this.f8950e)) {
            throw new IllegalStateException("eglMakeCurrent failed");
        }
    }

    public void j(Surface surface) {
        n0.d.i(this.f8946a, true);
        n0.d.h(this.f8948c);
        if (this.f8947b.containsKey(surface)) {
            return;
        }
        this.f8947b.put(surface, n0.d.f9729l);
    }

    public void k() {
        if (this.f8946a.getAndSet(false)) {
            n0.d.h(this.f8948c);
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void m(Surface surface, boolean z7) {
        if (this.f8954i == surface) {
            this.f8954i = null;
            i(this.f8953h);
        }
        n0.g gVar = (n0.g) (z7 ? this.f8947b.remove(surface) : this.f8947b.put(surface, n0.d.f9729l));
        if (gVar == null || gVar == n0.d.f9729l) {
            return;
        }
        try {
            EGL14.eglDestroySurface(this.f8949d, gVar.a());
        } catch (RuntimeException e8) {
            f1.m("OpenGlRenderer", "Failed to destroy EGL surface: " + e8.getMessage(), e8);
        }
    }

    public void n(long j8, float[] fArr, Surface surface) {
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
            GLES20.glViewport(0, 0, f8.c(), f8.b());
            GLES20.glScissor(0, 0, f8.c(), f8.b());
        }
        d.f fVar = (d.f) g1.e.j(this.f8956k);
        if (fVar instanceof d.g) {
            ((d.g) fVar).h(fArr);
        }
        GLES20.glDrawArrays(5, 0, 4);
        n0.d.g("glDrawArrays");
        EGLExt.eglPresentationTimeANDROID(this.f8949d, f8.a(), j8);
        if (EGL14.eglSwapBuffers(this.f8949d, f8.a())) {
            return;
        }
        f1.l("OpenGlRenderer", "Failed to swap buffers with EGL error: 0x" + Integer.toHexString(EGL14.eglGetError()));
        m(surface, false);
    }

    public void o(d.e eVar) {
        n0.d.i(this.f8946a, true);
        n0.d.h(this.f8948c);
        if (this.f8957l != eVar) {
            this.f8957l = eVar;
            s(this.f8958m);
        }
    }

    public Bitmap p(Size size, float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(size.getWidth() * size.getHeight() * 4);
        q(allocateDirect, size, fArr);
        Bitmap createBitmap = Bitmap.createBitmap(size.getWidth(), size.getHeight(), Bitmap.Config.ARGB_8888);
        allocateDirect.rewind();
        ImageProcessingUtil.j(createBitmap, allocateDirect, size.getWidth() * 4);
        return createBitmap;
    }

    public void r(Surface surface) {
        n0.d.i(this.f8946a, true);
        n0.d.h(this.f8948c);
        m(surface, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void s(int i8) {
        d.f fVar = (d.f) this.f8955j.get(this.f8957l);
        if (fVar == null) {
            throw new IllegalStateException("Unable to configure program for input format: " + this.f8957l);
        }
        if (this.f8956k != fVar) {
            this.f8956k = fVar;
            fVar.f();
            Log.d("OpenGlRenderer", "Using program for input format " + this.f8957l + ": " + this.f8956k);
        }
        a(i8);
    }
}
