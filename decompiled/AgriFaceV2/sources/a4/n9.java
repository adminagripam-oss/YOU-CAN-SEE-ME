package a4;
/* loaded from: classes.dex */
public final class n9 {

    /* renamed from: a  reason: collision with root package name */
    private final k9 f866a;

    /* renamed from: b  reason: collision with root package name */
    private final h9 f867b;

    /* renamed from: c  reason: collision with root package name */
    private final l9 f868c;

    /* renamed from: d  reason: collision with root package name */
    private final i9 f869d;

    /* renamed from: e  reason: collision with root package name */
    private final Boolean f870e;

    /* renamed from: f  reason: collision with root package name */
    private final Float f871f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ n9(g9 g9Var, m9 m9Var) {
        k9 k9Var;
        h9 h9Var;
        l9 l9Var;
        i9 i9Var;
        Boolean bool;
        Float f8;
        k9Var = g9Var.f599a;
        this.f866a = k9Var;
        h9Var = g9Var.f600b;
        this.f867b = h9Var;
        l9Var = g9Var.f601c;
        this.f868c = l9Var;
        i9Var = g9Var.f602d;
        this.f869d = i9Var;
        bool = g9Var.f603e;
        this.f870e = bool;
        f8 = g9Var.f604f;
        this.f871f = f8;
    }

    public final h9 a() {
        return this.f867b;
    }

    public final i9 b() {
        return this.f869d;
    }

    public final k9 c() {
        return this.f866a;
    }

    public final l9 d() {
        return this.f868c;
    }

    public final Boolean e() {
        return this.f870e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof n9) {
            n9 n9Var = (n9) obj;
            return k3.p.a(this.f866a, n9Var.f866a) && k3.p.a(this.f867b, n9Var.f867b) && k3.p.a(this.f868c, n9Var.f868c) && k3.p.a(this.f869d, n9Var.f869d) && k3.p.a(this.f870e, n9Var.f870e) && k3.p.a(this.f871f, n9Var.f871f);
        }
        return false;
    }

    public final Float f() {
        return this.f871f;
    }

    public final int hashCode() {
        return k3.p.b(this.f866a, this.f867b, this.f868c, this.f869d, this.f870e, this.f871f);
    }
}
