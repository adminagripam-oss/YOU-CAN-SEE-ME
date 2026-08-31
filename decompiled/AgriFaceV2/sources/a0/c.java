package a0;

import a0.w;
/* loaded from: classes.dex */
final class c extends w.a {

    /* renamed from: a  reason: collision with root package name */
    private final l0.z f15a;

    /* renamed from: b  reason: collision with root package name */
    private final int f16b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(l0.z zVar, int i8) {
        if (zVar == null) {
            throw new NullPointerException("Null packet");
        }
        this.f15a = zVar;
        this.f16b = i8;
    }

    @Override // a0.w.a
    int a() {
        return this.f16b;
    }

    @Override // a0.w.a
    l0.z b() {
        return this.f15a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof w.a) {
            w.a aVar = (w.a) obj;
            return this.f15a.equals(aVar.b()) && this.f16b == aVar.a();
        }
        return false;
    }

    public int hashCode() {
        return ((this.f15a.hashCode() ^ 1000003) * 1000003) ^ this.f16b;
    }

    public String toString() {
        return "In{packet=" + this.f15a + ", jpegQuality=" + this.f16b + "}";
    }
}
