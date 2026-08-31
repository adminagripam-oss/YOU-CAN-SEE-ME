package m0;

import java.util.List;
import l0.l0;
import m0.r;
/* loaded from: classes.dex */
final class b extends r.b {

    /* renamed from: a  reason: collision with root package name */
    private final l0 f9076a;

    /* renamed from: b  reason: collision with root package name */
    private final l0 f9077b;

    /* renamed from: c  reason: collision with root package name */
    private final List f9078c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(l0 l0Var, l0 l0Var2, List list) {
        if (l0Var == null) {
            throw new NullPointerException("Null primarySurfaceEdge");
        }
        this.f9076a = l0Var;
        if (l0Var2 == null) {
            throw new NullPointerException("Null secondarySurfaceEdge");
        }
        this.f9077b = l0Var2;
        if (list == null) {
            throw new NullPointerException("Null outConfigs");
        }
        this.f9078c = list;
    }

    @Override // m0.r.b
    public List a() {
        return this.f9078c;
    }

    @Override // m0.r.b
    public l0 b() {
        return this.f9076a;
    }

    @Override // m0.r.b
    public l0 c() {
        return this.f9077b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r.b) {
            r.b bVar = (r.b) obj;
            return this.f9076a.equals(bVar.b()) && this.f9077b.equals(bVar.c()) && this.f9078c.equals(bVar.a());
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f9076a.hashCode() ^ 1000003) * 1000003) ^ this.f9077b.hashCode()) * 1000003) ^ this.f9078c.hashCode();
    }

    public String toString() {
        return "In{primarySurfaceEdge=" + this.f9076a + ", secondarySurfaceEdge=" + this.f9077b + ", outConfigs=" + this.f9078c + "}";
    }
}
