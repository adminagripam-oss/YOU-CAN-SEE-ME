package y;

import android.graphics.Matrix;
import android.graphics.Rect;
import y.g2;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i extends g2.h {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f12844a;

    /* renamed from: b  reason: collision with root package name */
    private final int f12845b;

    /* renamed from: c  reason: collision with root package name */
    private final int f12846c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f12847d;

    /* renamed from: e  reason: collision with root package name */
    private final Matrix f12848e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f12849f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(Rect rect, int i8, int i9, boolean z7, Matrix matrix, boolean z8) {
        if (rect == null) {
            throw new NullPointerException("Null getCropRect");
        }
        this.f12844a = rect;
        this.f12845b = i8;
        this.f12846c = i9;
        this.f12847d = z7;
        if (matrix == null) {
            throw new NullPointerException("Null getSensorToBufferTransform");
        }
        this.f12848e = matrix;
        this.f12849f = z8;
    }

    @Override // y.g2.h
    public Rect a() {
        return this.f12844a;
    }

    @Override // y.g2.h
    public int b() {
        return this.f12845b;
    }

    @Override // y.g2.h
    public Matrix c() {
        return this.f12848e;
    }

    @Override // y.g2.h
    public int d() {
        return this.f12846c;
    }

    @Override // y.g2.h
    public boolean e() {
        return this.f12847d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g2.h) {
            g2.h hVar = (g2.h) obj;
            return this.f12844a.equals(hVar.a()) && this.f12845b == hVar.b() && this.f12846c == hVar.d() && this.f12847d == hVar.e() && this.f12848e.equals(hVar.c()) && this.f12849f == hVar.f();
        }
        return false;
    }

    @Override // y.g2.h
    public boolean f() {
        return this.f12849f;
    }

    public int hashCode() {
        return ((((((((((this.f12844a.hashCode() ^ 1000003) * 1000003) ^ this.f12845b) * 1000003) ^ this.f12846c) * 1000003) ^ (this.f12847d ? 1231 : 1237)) * 1000003) ^ this.f12848e.hashCode()) * 1000003) ^ (this.f12849f ? 1231 : 1237);
    }

    public String toString() {
        return "TransformationInfo{getCropRect=" + this.f12844a + ", getRotationDegrees=" + this.f12845b + ", getTargetRotation=" + this.f12846c + ", hasCameraTransform=" + this.f12847d + ", getSensorToBufferTransform=" + this.f12848e + ", isMirroring=" + this.f12849f + "}";
    }
}
