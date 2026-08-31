package n0;

import android.opengl.EGLSurface;
/* loaded from: classes.dex */
final class c extends g {

    /* renamed from: a  reason: collision with root package name */
    private final EGLSurface f9715a;

    /* renamed from: b  reason: collision with root package name */
    private final int f9716b;

    /* renamed from: c  reason: collision with root package name */
    private final int f9717c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(EGLSurface eGLSurface, int i8, int i9) {
        if (eGLSurface == null) {
            throw new NullPointerException("Null eglSurface");
        }
        this.f9715a = eGLSurface;
        this.f9716b = i8;
        this.f9717c = i9;
    }

    @Override // n0.g
    public EGLSurface a() {
        return this.f9715a;
    }

    @Override // n0.g
    public int b() {
        return this.f9717c;
    }

    @Override // n0.g
    public int c() {
        return this.f9716b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            return this.f9715a.equals(gVar.a()) && this.f9716b == gVar.c() && this.f9717c == gVar.b();
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f9715a.hashCode() ^ 1000003) * 1000003) ^ this.f9716b) * 1000003) ^ this.f9717c;
    }

    public String toString() {
        return "OutputSurface{eglSurface=" + this.f9715a + ", width=" + this.f9716b + ", height=" + this.f9717c + "}";
    }
}
