package a4;
/* loaded from: classes.dex */
public final class c9 {

    /* renamed from: a  reason: collision with root package name */
    private Long f319a;

    /* renamed from: b  reason: collision with root package name */
    private Long f320b;

    /* renamed from: c  reason: collision with root package name */
    private Long f321c;

    /* renamed from: d  reason: collision with root package name */
    private Long f322d;

    /* renamed from: e  reason: collision with root package name */
    private Long f323e;

    /* renamed from: f  reason: collision with root package name */
    private Long f324f;

    public final c9 a(Long l8) {
        this.f321c = Long.valueOf(l8.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final c9 b(Long l8) {
        this.f322d = Long.valueOf(l8.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final c9 c(Long l8) {
        this.f319a = Long.valueOf(l8.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final c9 d(Long l8) {
        this.f323e = Long.valueOf(l8.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final c9 e(Long l8) {
        this.f320b = Long.valueOf(l8.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final c9 f(Long l8) {
        this.f324f = Long.valueOf(l8.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final e9 g() {
        return new e9(this, null);
    }
}
