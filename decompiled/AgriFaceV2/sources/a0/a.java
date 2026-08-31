package a0;

import a0.i;
/* loaded from: classes.dex */
final class a extends i.b {

    /* renamed from: a  reason: collision with root package name */
    private final l0.z f1a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(l0.z zVar, int i8) {
        if (zVar == null) {
            throw new NullPointerException("Null packet");
        }
        this.f1a = zVar;
        this.f2b = i8;
    }

    @Override // a0.i.b
    int a() {
        return this.f2b;
    }

    @Override // a0.i.b
    l0.z b() {
        return this.f1a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i.b) {
            i.b bVar = (i.b) obj;
            return this.f1a.equals(bVar.b()) && this.f2b == bVar.a();
        }
        return false;
    }

    public int hashCode() {
        return ((this.f1a.hashCode() ^ 1000003) * 1000003) ^ this.f2b;
    }

    public String toString() {
        return "In{packet=" + this.f1a + ", jpegQuality=" + this.f2b + "}";
    }
}
