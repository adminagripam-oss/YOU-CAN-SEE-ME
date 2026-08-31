package r0;

import r0.p;
/* loaded from: classes.dex */
final class e extends p.b {

    /* renamed from: b  reason: collision with root package name */
    private final v f11035b;

    /* renamed from: c  reason: collision with root package name */
    private final int f11036c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(v vVar, int i8) {
        if (vVar == null) {
            throw new NullPointerException("Null fallbackQuality");
        }
        this.f11035b = vVar;
        this.f11036c = i8;
    }

    @Override // r0.p.b
    v e() {
        return this.f11035b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof p.b) {
            p.b bVar = (p.b) obj;
            return this.f11035b.equals(bVar.e()) && this.f11036c == bVar.f();
        }
        return false;
    }

    @Override // r0.p.b
    int f() {
        return this.f11036c;
    }

    public int hashCode() {
        return ((this.f11035b.hashCode() ^ 1000003) * 1000003) ^ this.f11036c;
    }

    public String toString() {
        return "RuleStrategy{fallbackQuality=" + this.f11035b + ", fallbackRule=" + this.f11036c + "}";
    }
}
