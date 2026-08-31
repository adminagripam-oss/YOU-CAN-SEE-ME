package y;

import android.graphics.Rect;
import android.util.Size;
import y.v1;
/* loaded from: classes.dex */
final class f extends v1.a {

    /* renamed from: a  reason: collision with root package name */
    private final Size f12782a;

    /* renamed from: b  reason: collision with root package name */
    private final Rect f12783b;

    /* renamed from: c  reason: collision with root package name */
    private final b0.h0 f12784c;

    /* renamed from: d  reason: collision with root package name */
    private final int f12785d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f12786e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(Size size, Rect rect, b0.h0 h0Var, int i8, boolean z7) {
        if (size == null) {
            throw new NullPointerException("Null inputSize");
        }
        this.f12782a = size;
        if (rect == null) {
            throw new NullPointerException("Null inputCropRect");
        }
        this.f12783b = rect;
        this.f12784c = h0Var;
        this.f12785d = i8;
        this.f12786e = z7;
    }

    @Override // y.v1.a
    public b0.h0 a() {
        return this.f12784c;
    }

    @Override // y.v1.a
    public Rect b() {
        return this.f12783b;
    }

    @Override // y.v1.a
    public Size c() {
        return this.f12782a;
    }

    @Override // y.v1.a
    public boolean d() {
        return this.f12786e;
    }

    @Override // y.v1.a
    public int e() {
        return this.f12785d;
    }

    public boolean equals(Object obj) {
        b0.h0 h0Var;
        if (obj == this) {
            return true;
        }
        if (obj instanceof v1.a) {
            v1.a aVar = (v1.a) obj;
            return this.f12782a.equals(aVar.c()) && this.f12783b.equals(aVar.b()) && ((h0Var = this.f12784c) != null ? h0Var.equals(aVar.a()) : aVar.a() == null) && this.f12785d == aVar.e() && this.f12786e == aVar.d();
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f12782a.hashCode() ^ 1000003) * 1000003) ^ this.f12783b.hashCode()) * 1000003;
        b0.h0 h0Var = this.f12784c;
        return ((((hashCode ^ (h0Var == null ? 0 : h0Var.hashCode())) * 1000003) ^ this.f12785d) * 1000003) ^ (this.f12786e ? 1231 : 1237);
    }

    public String toString() {
        return "CameraInputInfo{inputSize=" + this.f12782a + ", inputCropRect=" + this.f12783b + ", cameraInternal=" + this.f12784c + ", rotationDegrees=" + this.f12785d + ", mirroring=" + this.f12786e + "}";
    }
}
