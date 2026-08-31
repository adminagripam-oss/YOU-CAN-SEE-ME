package a0;

import a0.o0;
/* loaded from: classes.dex */
final class f extends o0.b {

    /* renamed from: a  reason: collision with root package name */
    private final p0 f32a;

    /* renamed from: b  reason: collision with root package name */
    private final androidx.camera.core.n f33b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(p0 p0Var, androidx.camera.core.n nVar) {
        if (p0Var == null) {
            throw new NullPointerException("Null processingRequest");
        }
        this.f32a = p0Var;
        if (nVar == null) {
            throw new NullPointerException("Null imageProxy");
        }
        this.f33b = nVar;
    }

    @Override // a0.o0.b
    androidx.camera.core.n a() {
        return this.f33b;
    }

    @Override // a0.o0.b
    p0 b() {
        return this.f32a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof o0.b) {
            o0.b bVar = (o0.b) obj;
            return this.f32a.equals(bVar.b()) && this.f33b.equals(bVar.a());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f32a.hashCode() ^ 1000003) * 1000003) ^ this.f33b.hashCode();
    }

    public String toString() {
        return "InputPacket{processingRequest=" + this.f32a + ", imageProxy=" + this.f33b + "}";
    }
}
