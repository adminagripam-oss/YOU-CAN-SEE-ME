package z3;
/* loaded from: classes.dex */
public final class w7 {

    /* renamed from: a  reason: collision with root package name */
    private Long f14266a;

    /* renamed from: b  reason: collision with root package name */
    private x7 f14267b;

    /* renamed from: c  reason: collision with root package name */
    private r7 f14268c;

    /* renamed from: d  reason: collision with root package name */
    private Integer f14269d;

    /* renamed from: e  reason: collision with root package name */
    private Integer f14270e;

    /* renamed from: f  reason: collision with root package name */
    private Integer f14271f;

    /* renamed from: g  reason: collision with root package name */
    private Integer f14272g;

    public final w7 b(Long l8) {
        this.f14266a = Long.valueOf(l8.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final w7 c(Integer num) {
        this.f14269d = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final w7 d(r7 r7Var) {
        this.f14268c = r7Var;
        return this;
    }

    public final w7 e(Integer num) {
        this.f14271f = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final w7 f(x7 x7Var) {
        this.f14267b = x7Var;
        return this;
    }

    public final w7 g(Integer num) {
        this.f14270e = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final w7 h(Integer num) {
        this.f14272g = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final z7 j() {
        return new z7(this, null);
    }
}
