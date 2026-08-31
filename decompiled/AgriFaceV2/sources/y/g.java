package y;

import y.v1;
/* loaded from: classes.dex */
final class g extends v1.b {

    /* renamed from: a  reason: collision with root package name */
    private final int f12790a;

    /* renamed from: b  reason: collision with root package name */
    private final v1 f12791b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(int i8, v1 v1Var) {
        this.f12790a = i8;
        if (v1Var == null) {
            throw new NullPointerException("Null surfaceOutput");
        }
        this.f12791b = v1Var;
    }

    @Override // y.v1.b
    public int a() {
        return this.f12790a;
    }

    @Override // y.v1.b
    public v1 b() {
        return this.f12791b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof v1.b) {
            v1.b bVar = (v1.b) obj;
            return this.f12790a == bVar.a() && this.f12791b.equals(bVar.b());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f12790a ^ 1000003) * 1000003) ^ this.f12791b.hashCode();
    }

    public String toString() {
        return "Event{eventCode=" + this.f12790a + ", surfaceOutput=" + this.f12791b + "}";
    }
}
