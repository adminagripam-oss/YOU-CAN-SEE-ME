package r0;

import r0.x;
/* loaded from: classes.dex */
final class i extends x.a {

    /* renamed from: a  reason: collision with root package name */
    private final v f11060a;

    /* renamed from: b  reason: collision with root package name */
    private final int f11061b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(v vVar, int i8) {
        if (vVar == null) {
            throw new NullPointerException("Null quality");
        }
        this.f11060a = vVar;
        this.f11061b = i8;
    }

    @Override // r0.x.a
    int a() {
        return this.f11061b;
    }

    @Override // r0.x.a
    v b() {
        return this.f11060a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof x.a) {
            x.a aVar = (x.a) obj;
            return this.f11060a.equals(aVar.b()) && this.f11061b == aVar.a();
        }
        return false;
    }

    public int hashCode() {
        return ((this.f11060a.hashCode() ^ 1000003) * 1000003) ^ this.f11061b;
    }

    public String toString() {
        return "QualityRatio{quality=" + this.f11060a + ", aspectRatio=" + this.f11061b + "}";
    }
}
