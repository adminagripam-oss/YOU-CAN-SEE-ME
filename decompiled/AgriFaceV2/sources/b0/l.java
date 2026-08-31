package b0;

import b0.w2;
/* loaded from: classes.dex */
final class l extends w2 {

    /* renamed from: a  reason: collision with root package name */
    private final w2.b f4271a;

    /* renamed from: b  reason: collision with root package name */
    private final w2.a f4272b;

    /* renamed from: c  reason: collision with root package name */
    private final long f4273c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(w2.b bVar, w2.a aVar, long j8) {
        if (bVar == null) {
            throw new NullPointerException("Null configType");
        }
        this.f4271a = bVar;
        if (aVar == null) {
            throw new NullPointerException("Null configSize");
        }
        this.f4272b = aVar;
        this.f4273c = j8;
    }

    @Override // b0.w2
    public w2.a c() {
        return this.f4272b;
    }

    @Override // b0.w2
    public w2.b d() {
        return this.f4271a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof w2) {
            w2 w2Var = (w2) obj;
            return this.f4271a.equals(w2Var.d()) && this.f4272b.equals(w2Var.c()) && this.f4273c == w2Var.f();
        }
        return false;
    }

    @Override // b0.w2
    public long f() {
        return this.f4273c;
    }

    public int hashCode() {
        long j8 = this.f4273c;
        return ((((this.f4271a.hashCode() ^ 1000003) * 1000003) ^ this.f4272b.hashCode()) * 1000003) ^ ((int) (j8 ^ (j8 >>> 32)));
    }

    public String toString() {
        return "SurfaceConfig{configType=" + this.f4271a + ", configSize=" + this.f4272b + ", streamUseCase=" + this.f4273c + "}";
    }
}
