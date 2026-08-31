package m0;
/* loaded from: classes.dex */
final class a extends d {

    /* renamed from: a  reason: collision with root package name */
    private final n0.f f9074a;

    /* renamed from: b  reason: collision with root package name */
    private final n0.f f9075b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(n0.f fVar, n0.f fVar2) {
        if (fVar == null) {
            throw new NullPointerException("Null primaryOutConfig");
        }
        this.f9074a = fVar;
        if (fVar2 == null) {
            throw new NullPointerException("Null secondaryOutConfig");
        }
        this.f9075b = fVar2;
    }

    @Override // m0.d
    public n0.f a() {
        return this.f9074a;
    }

    @Override // m0.d
    public n0.f b() {
        return this.f9075b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            return this.f9074a.equals(dVar.a()) && this.f9075b.equals(dVar.b());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f9074a.hashCode() ^ 1000003) * 1000003) ^ this.f9075b.hashCode();
    }

    public String toString() {
        return "DualOutConfig{primaryOutConfig=" + this.f9074a + ", secondaryOutConfig=" + this.f9075b + "}";
    }
}
