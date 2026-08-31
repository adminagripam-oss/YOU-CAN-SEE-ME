package y;

import android.graphics.Matrix;
import b0.y2;
/* loaded from: classes.dex */
final class d extends c1 {

    /* renamed from: a  reason: collision with root package name */
    private final y2 f12748a;

    /* renamed from: b  reason: collision with root package name */
    private final long f12749b;

    /* renamed from: c  reason: collision with root package name */
    private final int f12750c;

    /* renamed from: d  reason: collision with root package name */
    private final Matrix f12751d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(y2 y2Var, long j8, int i8, Matrix matrix) {
        if (y2Var == null) {
            throw new NullPointerException("Null tagBundle");
        }
        this.f12748a = y2Var;
        this.f12749b = j8;
        this.f12750c = i8;
        if (matrix == null) {
            throw new NullPointerException("Null sensorToBufferTransformMatrix");
        }
        this.f12751d = matrix;
    }

    @Override // y.c1, y.w0
    public y2 b() {
        return this.f12748a;
    }

    @Override // y.c1, y.w0
    public long c() {
        return this.f12749b;
    }

    @Override // y.c1
    public int e() {
        return this.f12750c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c1) {
            c1 c1Var = (c1) obj;
            return this.f12748a.equals(c1Var.b()) && this.f12749b == c1Var.c() && this.f12750c == c1Var.e() && this.f12751d.equals(c1Var.f());
        }
        return false;
    }

    @Override // y.c1
    public Matrix f() {
        return this.f12751d;
    }

    public int hashCode() {
        long j8 = this.f12749b;
        return ((((((this.f12748a.hashCode() ^ 1000003) * 1000003) ^ ((int) (j8 ^ (j8 >>> 32)))) * 1000003) ^ this.f12750c) * 1000003) ^ this.f12751d.hashCode();
    }

    public String toString() {
        return "ImmutableImageInfo{tagBundle=" + this.f12748a + ", timestamp=" + this.f12749b + ", rotationDegrees=" + this.f12750c + ", sensorToBufferTransformMatrix=" + this.f12751d + "}";
    }
}
