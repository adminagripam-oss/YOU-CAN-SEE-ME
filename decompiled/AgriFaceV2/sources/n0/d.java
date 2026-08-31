package n0;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import l0.a0;
import y.f1;
/* loaded from: classes.dex */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f9718a = {12344};

    /* renamed from: b  reason: collision with root package name */
    public static final int[] f9719b = {12445, 13632, 12344};

    /* renamed from: c  reason: collision with root package name */
    public static final String f9720c;

    /* renamed from: d  reason: collision with root package name */
    public static final String f9721d;

    /* renamed from: e  reason: collision with root package name */
    private static final a0 f9722e;

    /* renamed from: f  reason: collision with root package name */
    private static final a0 f9723f;

    /* renamed from: g  reason: collision with root package name */
    private static final a0 f9724g;

    /* renamed from: h  reason: collision with root package name */
    public static final float[] f9725h;

    /* renamed from: i  reason: collision with root package name */
    public static final FloatBuffer f9726i;

    /* renamed from: j  reason: collision with root package name */
    public static final float[] f9727j;

    /* renamed from: k  reason: collision with root package name */
    public static final FloatBuffer f9728k;

    /* renamed from: l  reason: collision with root package name */
    public static final n0.g f9729l;

    /* loaded from: classes.dex */
    class a implements a0 {
        a() {
        }

        @Override // l0.a0
        public String a(String str, String str2) {
            return String.format(Locale.US, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 %s;\nuniform samplerExternalOES %s;\nuniform float uAlphaScale;\nvoid main() {\n    vec4 src = texture2D(%s, %s);\n    gl_FragColor = vec4(src.rgb, src.a * uAlphaScale);\n}\n", str2, str, str, str2);
        }
    }

    /* loaded from: classes.dex */
    class b implements a0 {
        b() {
        }

        @Override // l0.a0
        public String a(String str, String str2) {
            return String.format(Locale.US, "#version 300 es\n#extension GL_OES_EGL_image_external_essl3 : require\nprecision mediump float;\nuniform samplerExternalOES %s;\nuniform float uAlphaScale;\nin vec2 %s;\nout vec4 outColor;\n\nvoid main() {\n  vec4 src = texture(%s, %s);\n  outColor = vec4(src.rgb, src.a * uAlphaScale);\n}", str, str2, str, str2);
        }
    }

    /* loaded from: classes.dex */
    class c implements a0 {
        c() {
        }

        @Override // l0.a0
        public String a(String str, String str2) {
            return String.format(Locale.US, "#version 300 es\n#extension GL_EXT_YUV_target : require\nprecision mediump float;\nuniform __samplerExternal2DY2YEXT %s;\nuniform float uAlphaScale;\nin vec2 %s;\nout vec4 outColor;\n\nvec3 yuvToRgb(vec3 yuv) {\n  const vec3 yuvOffset = vec3(0.0625, 0.5, 0.5);\n  const mat3 yuvToRgbColorMat = mat3(\n    1.1689f, 1.1689f, 1.1689f,\n    0.0000f, -0.1881f, 2.1502f,\n    1.6853f, -0.6530f, 0.0000f\n  );\n  return clamp(yuvToRgbColorMat * (yuv - yuvOffset), 0.0, 1.0);\n}\n\nvoid main() {\n  vec3 srcYuv = texture(%s, %s).xyz;\n  vec3 srcRgb = yuvToRgb(srcYuv);\n  outColor = vec4(srcRgb, uAlphaScale);\n}", str, str2, str, str2);
        }
    }

    /* renamed from: n0.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0131d extends f {
        public C0131d() {
            super("uniform mat4 uTransMatrix;\nattribute vec4 aPosition;\nvoid main() {\n    gl_Position = uTransMatrix * aPosition;\n}\n", "precision mediump float;\nuniform float uAlphaScale;\nvoid main() {\n    gl_FragColor = vec4(0.0, 0.0, 0.0, uAlphaScale);\n}\n");
        }
    }

    /* loaded from: classes.dex */
    public enum e {
        UNKNOWN,
        DEFAULT,
        YUV
    }

    /* loaded from: classes.dex */
    public static abstract class f {

        /* renamed from: a  reason: collision with root package name */
        protected int f9734a;

        /* renamed from: b  reason: collision with root package name */
        protected int f9735b = -1;

        /* renamed from: c  reason: collision with root package name */
        protected int f9736c = -1;

        /* renamed from: d  reason: collision with root package name */
        protected int f9737d = -1;

        /* JADX WARN: Removed duplicated region for block: B:32:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x007c  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected f(java.lang.String r7, java.lang.String r8) {
            /*
                r6 = this;
                java.lang.String r0 = "glAttachShader"
                r6.<init>()
                r1 = -1
                r6.f9735b = r1
                r6.f9736c = r1
                r6.f9737d = r1
                r2 = 35633(0x8b31, float:4.9932E-41)
                int r7 = n0.d.y(r2, r7)     // Catch: java.lang.IllegalArgumentException -> L6f java.lang.IllegalStateException -> L71
                r2 = 35632(0x8b30, float:4.9931E-41)
                int r8 = n0.d.y(r2, r8)     // Catch: java.lang.IllegalArgumentException -> L6a java.lang.IllegalStateException -> L6c
                int r2 = android.opengl.GLES20.glCreateProgram()     // Catch: java.lang.IllegalArgumentException -> L65 java.lang.IllegalStateException -> L67
                java.lang.String r3 = "glCreateProgram"
                n0.d.g(r3)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                android.opengl.GLES20.glAttachShader(r2, r7)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                n0.d.g(r0)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                android.opengl.GLES20.glAttachShader(r2, r8)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                n0.d.g(r0)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                android.opengl.GLES20.glLinkProgram(r2)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                r0 = 1
                int[] r3 = new int[r0]     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                r4 = 35714(0x8b82, float:5.0046E-41)
                r5 = 0
                android.opengl.GLES20.glGetProgramiv(r2, r4, r3, r5)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                r3 = r3[r5]     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                if (r3 != r0) goto L46
                r6.f9734a = r2     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                r6.c()
                return
            L46:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                r3.<init>()     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                java.lang.String r4 = "Could not link program: "
                r3.append(r4)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                java.lang.String r4 = android.opengl.GLES20.glGetProgramInfoLog(r2)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                r3.append(r4)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                java.lang.String r3 = r3.toString()     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                r0.<init>(r3)     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
                throw r0     // Catch: java.lang.IllegalArgumentException -> L61 java.lang.IllegalStateException -> L63
            L61:
                r0 = move-exception
                goto L75
            L63:
                r0 = move-exception
                goto L75
            L65:
                r0 = move-exception
                goto L68
            L67:
                r0 = move-exception
            L68:
                r2 = r1
                goto L75
            L6a:
                r0 = move-exception
                goto L6d
            L6c:
                r0 = move-exception
            L6d:
                r8 = r1
                goto L74
            L6f:
                r0 = move-exception
                goto L72
            L71:
                r0 = move-exception
            L72:
                r7 = r1
                r8 = r7
            L74:
                r2 = r8
            L75:
                if (r7 == r1) goto L7a
                android.opengl.GLES20.glDeleteShader(r7)
            L7a:
                if (r8 == r1) goto L7f
                android.opengl.GLES20.glDeleteShader(r8)
            L7f:
                if (r2 == r1) goto L84
                android.opengl.GLES20.glDeleteProgram(r2)
            L84:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: n0.d.f.<init>(java.lang.String, java.lang.String):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            int glGetAttribLocation = GLES20.glGetAttribLocation(this.f9734a, "aPosition");
            this.f9737d = glGetAttribLocation;
            d.j(glGetAttribLocation, "aPosition");
            int glGetUniformLocation = GLES20.glGetUniformLocation(this.f9734a, "uTransMatrix");
            this.f9735b = glGetUniformLocation;
            d.j(glGetUniformLocation, "uTransMatrix");
            int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.f9734a, "uAlphaScale");
            this.f9736c = glGetUniformLocation2;
            d.j(glGetUniformLocation2, "uAlphaScale");
        }

        public void b() {
            GLES20.glDeleteProgram(this.f9734a);
        }

        public void d(float f8) {
            GLES20.glUniform1f(this.f9736c, f8);
            d.g("glUniform1f");
        }

        public void e(float[] fArr) {
            GLES20.glUniformMatrix4fv(this.f9735b, 1, false, fArr, 0);
            d.g("glUniformMatrix4fv");
        }

        public void f() {
            GLES20.glUseProgram(this.f9734a);
            d.g("glUseProgram");
            GLES20.glEnableVertexAttribArray(this.f9737d);
            d.g("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(this.f9737d, 2, 5126, false, 0, (Buffer) d.f9726i);
            d.g("glVertexAttribPointer");
            e(d.l());
            d(1.0f);
        }
    }

    /* loaded from: classes.dex */
    public static class g extends f {

        /* renamed from: e  reason: collision with root package name */
        private int f9738e;

        /* renamed from: f  reason: collision with root package name */
        private int f9739f;

        /* renamed from: g  reason: collision with root package name */
        private int f9740g;

        public g(y.a0 a0Var, a0 a0Var2) {
            super(a0Var.d() ? d.f9721d : d.f9720c, d.v(a0Var2));
            this.f9738e = -1;
            this.f9739f = -1;
            this.f9740g = -1;
            c();
        }

        private void c() {
            c();
            int glGetUniformLocation = GLES20.glGetUniformLocation(this.f9734a, "sTexture");
            this.f9738e = glGetUniformLocation;
            d.j(glGetUniformLocation, "sTexture");
            int glGetAttribLocation = GLES20.glGetAttribLocation(this.f9734a, "aTextureCoord");
            this.f9740g = glGetAttribLocation;
            d.j(glGetAttribLocation, "aTextureCoord");
            int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.f9734a, "uTexMatrix");
            this.f9739f = glGetUniformLocation2;
            d.j(glGetUniformLocation2, "uTexMatrix");
        }

        private static a0 g(y.a0 a0Var, e eVar) {
            if (a0Var.d()) {
                boolean z7 = eVar != e.UNKNOWN;
                g1.e.b(z7, "No default sampler shader available for" + eVar);
                return eVar == e.YUV ? d.f9724g : d.f9723f;
            }
            return d.f9722e;
        }

        @Override // n0.d.f
        public void f() {
            super.f();
            GLES20.glUniform1i(this.f9738e, 0);
            GLES20.glEnableVertexAttribArray(this.f9740g);
            d.g("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(this.f9740g, 2, 5126, false, 0, (Buffer) d.f9728k);
            d.g("glVertexAttribPointer");
        }

        public void h(float[] fArr) {
            GLES20.glUniformMatrix4fv(this.f9739f, 1, false, fArr, 0);
            d.g("glUniformMatrix4fv");
        }

        public g(y.a0 a0Var, e eVar) {
            this(a0Var, g(a0Var, eVar));
        }
    }

    static {
        Locale locale = Locale.US;
        f9720c = String.format(locale, "uniform mat4 uTexMatrix;\nuniform mat4 uTransMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 %s;\nvoid main() {\n    gl_Position = uTransMatrix * aPosition;\n    %s = (uTexMatrix * aTextureCoord).xy;\n}\n", "vTextureCoord", "vTextureCoord");
        f9721d = String.format(locale, "#version 300 es\nin vec4 aPosition;\nin vec4 aTextureCoord;\nuniform mat4 uTexMatrix;\nuniform mat4 uTransMatrix;\nout vec2 %s;\nvoid main() {\n  gl_Position = uTransMatrix * aPosition;\n  %s = (uTexMatrix * aTextureCoord).xy;\n}\n", "vTextureCoord", "vTextureCoord");
        f9722e = new a();
        f9723f = new b();
        f9724g = new c();
        float[] fArr = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
        f9725h = fArr;
        f9726i = m(fArr);
        float[] fArr2 = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        f9727j = fArr2;
        f9728k = m(fArr2);
        f9729l = n0.g.d(EGL14.EGL_NO_SURFACE, 0, 0);
    }

    public static void e(String str) {
        try {
            f(str);
        } catch (IllegalStateException e8) {
            f1.d("GLUtils", e8.toString(), e8);
        }
    }

    public static void f(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError == 12288) {
            return;
        }
        throw new IllegalStateException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
    }

    public static void g(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError == 0) {
            return;
        }
        throw new IllegalStateException(str + ": GL error 0x" + Integer.toHexString(glGetError));
    }

    public static void h(Thread thread) {
        g1.e.m(thread == Thread.currentThread(), "Method call must be called on the GL thread.");
    }

    public static void i(AtomicBoolean atomicBoolean, boolean z7) {
        g1.e.m(z7 == atomicBoolean.get(), z7 ? "OpenGlRenderer is not initialized" : "OpenGlRenderer is already initialized");
    }

    public static void j(int i8, String str) {
        if (i8 >= 0) {
            return;
        }
        throw new IllegalStateException("Unable to locate '" + str + "' in program");
    }

    public static int[] k(String str, y.a0 a0Var) {
        int[] iArr = f9718a;
        if (a0Var.b() == 3) {
            if (str.contains("EGL_EXT_gl_colorspace_bt2020_hlg")) {
                return f9719b;
            }
            f1.l("GLUtils", "Dynamic range uses HLG encoding, but device does not support EGL_EXT_gl_colorspace_bt2020_hlg.Fallback to default colorspace.");
            return iArr;
        }
        return iArr;
    }

    public static float[] l() {
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        return fArr;
    }

    public static FloatBuffer m(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    public static EGLSurface n(EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i8, int i9) {
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, eGLConfig, new int[]{12375, i8, 12374, i9, 12344}, 0);
        f("eglCreatePbufferSurface");
        if (eglCreatePbufferSurface != null) {
            return eglCreatePbufferSurface;
        }
        throw new IllegalStateException("surface was null");
    }

    public static Map o(y.a0 a0Var, Map map) {
        Object gVar;
        e eVar;
        HashMap hashMap = new HashMap();
        e[] values = e.values();
        int length = values.length;
        for (int i8 = 0; i8 < length; i8++) {
            e eVar2 = values[i8];
            a0 a0Var2 = (a0) map.get(eVar2);
            if (a0Var2 != null) {
                gVar = new g(a0Var, a0Var2);
            } else if (eVar2 == e.YUV || eVar2 == (eVar = e.DEFAULT)) {
                gVar = new g(a0Var, eVar2);
            } else {
                g1.e.m(eVar2 == e.UNKNOWN, "Unhandled input format: " + eVar2);
                if (a0Var.d()) {
                    gVar = new C0131d();
                } else {
                    a0 a0Var3 = (a0) map.get(eVar);
                    gVar = a0Var3 != null ? new g(a0Var, a0Var3) : new g(a0Var, eVar);
                }
            }
            Log.d("GLUtils", "Shader program for input format " + eVar2 + " created: " + gVar);
            hashMap.put(eVar2, gVar);
        }
        return hashMap;
    }

    public static int p() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        g("glGenTextures");
        int i8 = iArr[0];
        GLES20.glBindTexture(36197, i8);
        g("glBindTexture " + i8);
        GLES20.glTexParameteri(36197, 10241, 9728);
        GLES20.glTexParameteri(36197, 10240, 9729);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        g("glTexParameter");
        return i8;
    }

    public static EGLSurface q(EGLDisplay eGLDisplay, EGLConfig eGLConfig, Surface surface, int[] iArr) {
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplay, eGLConfig, surface, iArr, 0);
        f("eglCreateWindowSurface");
        if (eglCreateWindowSurface != null) {
            return eglCreateWindowSurface;
        }
        throw new IllegalStateException("surface was null");
    }

    public static void r(int i8) {
        GLES20.glDeleteFramebuffers(1, new int[]{i8}, 0);
        g("glDeleteFramebuffers");
    }

    public static void s(int i8) {
        GLES20.glDeleteTextures(1, new int[]{i8}, 0);
        g("glDeleteTextures");
    }

    public static int t() {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        g("glGenFramebuffers");
        return iArr[0];
    }

    public static int u() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        g("glGenTextures");
        return iArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String v(a0 a0Var) {
        try {
            String a8 = a0Var.a("sTexture", "vTextureCoord");
            if (a8 != null && a8.contains("vTextureCoord") && a8.contains("sTexture")) {
                return a8;
            }
            throw new IllegalArgumentException("Invalid fragment shader");
        } catch (Throwable th) {
            if (th instanceof IllegalArgumentException) {
                throw th;
            }
            throw new IllegalArgumentException("Unable retrieve fragment shader source", th);
        }
    }

    public static String w() {
        Matcher matcher = Pattern.compile("OpenGL ES ([0-9]+)\\.([0-9]+).*").matcher(GLES20.glGetString(7938));
        if (matcher.find()) {
            return ((String) g1.e.j(matcher.group(1))) + "." + ((String) g1.e.j(matcher.group(2)));
        }
        return "0.0";
    }

    public static Size x(EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
        return new Size(z(eGLDisplay, eGLSurface, 12375), z(eGLDisplay, eGLSurface, 12374));
    }

    public static int y(int i8, String str) {
        int glCreateShader = GLES20.glCreateShader(i8);
        g("glCreateShader type=" + i8);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        f1.l("GLUtils", "Could not compile shader: " + str);
        GLES20.glDeleteShader(glCreateShader);
        throw new IllegalStateException("Could not compile shader type " + i8 + ":" + GLES20.glGetShaderInfoLog(glCreateShader));
    }

    public static int z(EGLDisplay eGLDisplay, EGLSurface eGLSurface, int i8) {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(eGLDisplay, eGLSurface, i8, iArr, 0);
        return iArr[0];
    }
}
