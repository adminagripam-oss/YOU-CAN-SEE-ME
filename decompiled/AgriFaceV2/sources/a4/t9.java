package a4;
/* loaded from: classes.dex */
public final class t9 {

    /* renamed from: a  reason: collision with root package name */
    private Long f1100a;

    /* renamed from: b  reason: collision with root package name */
    private ca f1101b;

    /* renamed from: c  reason: collision with root package name */
    private Boolean f1102c;

    /* renamed from: d  reason: collision with root package name */
    private Boolean f1103d;

    /* renamed from: e  reason: collision with root package name */
    private Boolean f1104e;

    public final t9 a(Boolean bool) {
        this.f1103d = bool;
        return this;
    }

    public final t9 b(Boolean bool) {
        this.f1104e = bool;
        return this;
    }

    public final t9 c(Long l8) {
        this.f1100a = Long.valueOf(l8.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final t9 d(ca caVar) {
        this.f1101b = caVar;
        return this;
    }

    public final t9 e(Boolean bool) {
        this.f1102c = bool;
        return this;
    }

    public final v9 f() {
        return new v9(this, null);
    }
}
