package a4;
/* loaded from: classes.dex */
public final class o2 {

    /* renamed from: a */
    private ca f881a;

    /* renamed from: b */
    private Boolean f882b;

    /* renamed from: c */
    private n9 f883c;

    /* renamed from: d */
    private Integer f884d;

    /* renamed from: e */
    private Integer f885e;

    public final o2 a(Integer num) {
        this.f884d = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final o2 b(n9 n9Var) {
        this.f883c = n9Var;
        return this;
    }

    public final o2 c(ca caVar) {
        this.f881a = caVar;
        return this;
    }

    public final o2 d(Boolean bool) {
        this.f882b = bool;
        return this;
    }

    public final o2 e(Integer num) {
        this.f885e = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final q2 f() {
        return new q2(this, null);
    }
}
