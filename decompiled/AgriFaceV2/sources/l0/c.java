package l0;

import java.util.List;
import l0.t0;
/* loaded from: classes.dex */
final class c extends t0.b {

    /* renamed from: a  reason: collision with root package name */
    private final l0 f8832a;

    /* renamed from: b  reason: collision with root package name */
    private final List f8833b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(l0 l0Var, List list) {
        if (l0Var == null) {
            throw new NullPointerException("Null surfaceEdge");
        }
        this.f8832a = l0Var;
        if (list == null) {
            throw new NullPointerException("Null outConfigs");
        }
        this.f8833b = list;
    }

    @Override // l0.t0.b
    public List a() {
        return this.f8833b;
    }

    @Override // l0.t0.b
    public l0 b() {
        return this.f8832a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof t0.b) {
            t0.b bVar = (t0.b) obj;
            return this.f8832a.equals(bVar.b()) && this.f8833b.equals(bVar.a());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f8832a.hashCode() ^ 1000003) * 1000003) ^ this.f8833b.hashCode();
    }

    public String toString() {
        return "In{surfaceEdge=" + this.f8832a + ", outConfigs=" + this.f8833b + "}";
    }
}
