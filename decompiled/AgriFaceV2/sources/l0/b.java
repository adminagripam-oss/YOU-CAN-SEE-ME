package l0;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b extends z {

    /* renamed from: a  reason: collision with root package name */
    private final Object f8823a;

    /* renamed from: b  reason: collision with root package name */
    private final d0.g f8824b;

    /* renamed from: c  reason: collision with root package name */
    private final int f8825c;

    /* renamed from: d  reason: collision with root package name */
    private final Size f8826d;

    /* renamed from: e  reason: collision with root package name */
    private final Rect f8827e;

    /* renamed from: f  reason: collision with root package name */
    private final int f8828f;

    /* renamed from: g  reason: collision with root package name */
    private final Matrix f8829g;

    /* renamed from: h  reason: collision with root package name */
    private final b0.x f8830h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Object obj, d0.g gVar, int i8, Size size, Rect rect, int i9, Matrix matrix, b0.x xVar) {
        if (obj == null) {
            throw new NullPointerException("Null data");
        }
        this.f8823a = obj;
        this.f8824b = gVar;
        this.f8825c = i8;
        if (size == null) {
            throw new NullPointerException("Null size");
        }
        this.f8826d = size;
        if (rect == null) {
            throw new NullPointerException("Null cropRect");
        }
        this.f8827e = rect;
        this.f8828f = i9;
        if (matrix == null) {
            throw new NullPointerException("Null sensorToBufferTransform");
        }
        this.f8829g = matrix;
        if (xVar == null) {
            throw new NullPointerException("Null cameraCaptureResult");
        }
        this.f8830h = xVar;
    }

    @Override // l0.z
    public b0.x a() {
        return this.f8830h;
    }

    @Override // l0.z
    public Rect b() {
        return this.f8827e;
    }

    @Override // l0.z
    public Object c() {
        return this.f8823a;
    }

    @Override // l0.z
    public d0.g d() {
        return this.f8824b;
    }

    @Override // l0.z
    public int e() {
        return this.f8825c;
    }

    public boolean equals(Object obj) {
        d0.g gVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof z) {
            z zVar = (z) obj;
            return this.f8823a.equals(zVar.c()) && ((gVar = this.f8824b) != null ? gVar.equals(zVar.d()) : zVar.d() == null) && this.f8825c == zVar.e() && this.f8826d.equals(zVar.h()) && this.f8827e.equals(zVar.b()) && this.f8828f == zVar.f() && this.f8829g.equals(zVar.g()) && this.f8830h.equals(zVar.a());
        }
        return false;
    }

    @Override // l0.z
    public int f() {
        return this.f8828f;
    }

    @Override // l0.z
    public Matrix g() {
        return this.f8829g;
    }

    @Override // l0.z
    public Size h() {
        return this.f8826d;
    }

    public int hashCode() {
        int hashCode = (this.f8823a.hashCode() ^ 1000003) * 1000003;
        d0.g gVar = this.f8824b;
        return ((((((((((((hashCode ^ (gVar == null ? 0 : gVar.hashCode())) * 1000003) ^ this.f8825c) * 1000003) ^ this.f8826d.hashCode()) * 1000003) ^ this.f8827e.hashCode()) * 1000003) ^ this.f8828f) * 1000003) ^ this.f8829g.hashCode()) * 1000003) ^ this.f8830h.hashCode();
    }

    public String toString() {
        return "Packet{data=" + this.f8823a + ", exif=" + this.f8824b + ", format=" + this.f8825c + ", size=" + this.f8826d + ", cropRect=" + this.f8827e + ", rotationDegrees=" + this.f8828f + ", sensorToBufferTransform=" + this.f8829g + ", cameraCaptureResult=" + this.f8830h + "}";
    }
}
