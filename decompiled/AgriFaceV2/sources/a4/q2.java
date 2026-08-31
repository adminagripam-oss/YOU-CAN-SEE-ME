package a4;
/* loaded from: classes.dex */
public final class q2 {

    /* renamed from: a  reason: collision with root package name */
    private final ca f959a;

    /* renamed from: b  reason: collision with root package name */
    private final Boolean f960b;

    /* renamed from: c  reason: collision with root package name */
    private final r9 f961c = null;

    /* renamed from: d  reason: collision with root package name */
    private final n9 f962d;

    /* renamed from: e  reason: collision with root package name */
    private final Integer f963e;

    /* renamed from: f  reason: collision with root package name */
    private final Integer f964f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ q2(o2 o2Var, p2 p2Var) {
        this.f959a = o2.h(o2Var);
        this.f960b = o2.i(o2Var);
        this.f962d = o2.g(o2Var);
        this.f963e = o2.j(o2Var);
        this.f964f = o2.k(o2Var);
    }

    public final n9 a() {
        return this.f962d;
    }

    public final ca b() {
        return this.f959a;
    }

    public final Boolean c() {
        return this.f960b;
    }

    public final Integer d() {
        return this.f963e;
    }

    public final Integer e() {
        return this.f964f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof q2) {
            q2 q2Var = (q2) obj;
            return k3.p.a(this.f959a, q2Var.f959a) && k3.p.a(this.f960b, q2Var.f960b) && k3.p.a(null, null) && k3.p.a(this.f962d, q2Var.f962d) && k3.p.a(this.f963e, q2Var.f963e) && k3.p.a(this.f964f, q2Var.f964f);
        }
        return false;
    }

    public final int hashCode() {
        return k3.p.b(this.f959a, this.f960b, null, this.f962d, this.f963e, this.f964f);
    }
}
