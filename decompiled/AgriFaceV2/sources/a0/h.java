package a0;

import android.graphics.Matrix;
import android.graphics.Rect;
import java.util.List;
import java.util.concurrent.Executor;
import y.u0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h extends e1 {

    /* renamed from: b  reason: collision with root package name */
    private final Executor f38b;

    /* renamed from: c  reason: collision with root package name */
    private final u0.f f39c;

    /* renamed from: d  reason: collision with root package name */
    private final u0.g f40d;

    /* renamed from: e  reason: collision with root package name */
    private final Rect f41e;

    /* renamed from: f  reason: collision with root package name */
    private final Matrix f42f;

    /* renamed from: g  reason: collision with root package name */
    private final int f43g;

    /* renamed from: h  reason: collision with root package name */
    private final int f44h;

    /* renamed from: i  reason: collision with root package name */
    private final int f45i;

    /* renamed from: j  reason: collision with root package name */
    private final List f46j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Executor executor, u0.e eVar, u0.f fVar, u0.g gVar, Rect rect, Matrix matrix, int i8, int i9, int i10, List list) {
        if (executor == null) {
            throw new NullPointerException("Null appExecutor");
        }
        this.f38b = executor;
        this.f39c = fVar;
        this.f40d = gVar;
        if (rect == null) {
            throw new NullPointerException("Null cropRect");
        }
        this.f41e = rect;
        if (matrix == null) {
            throw new NullPointerException("Null sensorToBufferTransform");
        }
        this.f42f = matrix;
        this.f43g = i8;
        this.f44h = i9;
        this.f45i = i10;
        if (list == null) {
            throw new NullPointerException("Null sessionConfigCameraCaptureCallbacks");
        }
        this.f46j = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e1) {
            e1 e1Var = (e1) obj;
            if (this.f38b.equals(e1Var.g())) {
                e1Var.j();
                u0.f fVar = this.f39c;
                if (fVar != null ? fVar.equals(e1Var.l()) : e1Var.l() == null) {
                    u0.g gVar = this.f40d;
                    if (gVar != null ? gVar.equals(e1Var.m()) : e1Var.m() == null) {
                        if (this.f41e.equals(e1Var.i()) && this.f42f.equals(e1Var.o()) && this.f43g == e1Var.n() && this.f44h == e1Var.k() && this.f45i == e1Var.h() && this.f46j.equals(e1Var.p())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.e1
    public Executor g() {
        return this.f38b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.e1
    public int h() {
        return this.f45i;
    }

    public int hashCode() {
        int hashCode = (((this.f38b.hashCode() ^ 1000003) * 1000003) ^ 0) * 1000003;
        u0.f fVar = this.f39c;
        int hashCode2 = (hashCode ^ (fVar == null ? 0 : fVar.hashCode())) * 1000003;
        u0.g gVar = this.f40d;
        return ((((((((((((hashCode2 ^ (gVar != null ? gVar.hashCode() : 0)) * 1000003) ^ this.f41e.hashCode()) * 1000003) ^ this.f42f.hashCode()) * 1000003) ^ this.f43g) * 1000003) ^ this.f44h) * 1000003) ^ this.f45i) * 1000003) ^ this.f46j.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.e1
    public Rect i() {
        return this.f41e;
    }

    @Override // a0.e1
    public u0.e j() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.e1
    public int k() {
        return this.f44h;
    }

    @Override // a0.e1
    public u0.f l() {
        return this.f39c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.e1
    public u0.g m() {
        return this.f40d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.e1
    public int n() {
        return this.f43g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.e1
    public Matrix o() {
        return this.f42f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a0.e1
    public List p() {
        return this.f46j;
    }

    public String toString() {
        return "TakePictureRequest{appExecutor=" + this.f38b + ", inMemoryCallback=" + ((Object) null) + ", onDiskCallback=" + this.f39c + ", outputFileOptions=" + this.f40d + ", cropRect=" + this.f41e + ", sensorToBufferTransform=" + this.f42f + ", rotationDegrees=" + this.f43g + ", jpegQuality=" + this.f44h + ", captureMode=" + this.f45i + ", sessionConfigCameraCaptureCallbacks=" + this.f46j + "}";
    }
}
