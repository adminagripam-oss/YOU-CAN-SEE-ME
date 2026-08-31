package a4;
/* loaded from: classes.dex */
public final class ua {

    /* renamed from: a  reason: collision with root package name */
    private v9 f1128a;

    /* renamed from: b  reason: collision with root package name */
    private r9 f1129b;

    /* renamed from: c  reason: collision with root package name */
    private n9 f1130c;

    /* renamed from: d  reason: collision with root package name */
    private Integer f1131d;

    /* renamed from: e  reason: collision with root package name */
    private Integer f1132e;

    public final ua d(Integer num) {
        this.f1131d = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final ua e(n9 n9Var) {
        this.f1130c = n9Var;
        return this;
    }

    public final ua f(r9 r9Var) {
        this.f1129b = r9Var;
        return this;
    }

    public final ua g(v9 v9Var) {
        this.f1128a = v9Var;
        return this;
    }

    public final ua h(Integer num) {
        this.f1132e = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final wa i() {
        return new wa(this, null);
    }
}
