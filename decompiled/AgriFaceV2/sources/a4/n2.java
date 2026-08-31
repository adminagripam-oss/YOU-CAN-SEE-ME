package a4;
/* loaded from: classes.dex */
public final class n2 {

    /* renamed from: a  reason: collision with root package name */
    private q2 f842a;

    /* renamed from: b  reason: collision with root package name */
    private Integer f843b;

    /* renamed from: c  reason: collision with root package name */
    private e9 f844c;

    public final n2 a(Integer num) {
        this.f843b = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final n2 b(e9 e9Var) {
        this.f844c = e9Var;
        return this;
    }

    public final n2 c(q2 q2Var) {
        this.f842a = q2Var;
        return this;
    }

    public final s2 e() {
        return new s2(this, null);
    }
}
