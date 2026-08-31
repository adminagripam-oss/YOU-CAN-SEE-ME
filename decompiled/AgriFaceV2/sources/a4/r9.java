package a4;
/* loaded from: classes.dex */
public final class r9 {

    /* renamed from: a  reason: collision with root package name */
    private final p9 f1032a;

    /* renamed from: b  reason: collision with root package name */
    private final Integer f1033b;

    /* renamed from: c  reason: collision with root package name */
    private final Integer f1034c;

    /* renamed from: d  reason: collision with root package name */
    private final Boolean f1035d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ r9(o9 o9Var, q9 q9Var) {
        p9 p9Var;
        Integer num;
        p9Var = o9Var.f909a;
        this.f1032a = p9Var;
        num = o9Var.f910b;
        this.f1033b = num;
        this.f1034c = null;
        this.f1035d = null;
    }

    public final p9 a() {
        return this.f1032a;
    }

    public final Integer b() {
        return this.f1033b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof r9) {
            r9 r9Var = (r9) obj;
            return k3.p.a(this.f1032a, r9Var.f1032a) && k3.p.a(this.f1033b, r9Var.f1033b) && k3.p.a(null, null) && k3.p.a(null, null);
        }
        return false;
    }

    public final int hashCode() {
        return k3.p.b(this.f1032a, this.f1033b, null, null);
    }
}
