package a4;
/* loaded from: classes.dex */
public final class o9 {

    /* renamed from: a  reason: collision with root package name */
    private p9 f909a;

    /* renamed from: b  reason: collision with root package name */
    private Integer f910b;

    public final o9 a(p9 p9Var) {
        this.f909a = p9Var;
        return this;
    }

    public final o9 b(Integer num) {
        this.f910b = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final r9 d() {
        return new r9(this, null);
    }
}
