package z3;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class p implements d5.g {

    /* renamed from: a  reason: collision with root package name */
    private boolean f13976a = false;

    /* renamed from: b  reason: collision with root package name */
    private boolean f13977b = false;

    /* renamed from: c  reason: collision with root package name */
    private d5.c f13978c;

    /* renamed from: d  reason: collision with root package name */
    private final l f13979d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(l lVar) {
        this.f13979d = lVar;
    }

    private final void c() {
        if (this.f13976a) {
            throw new d5.b("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f13976a = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(d5.c cVar, boolean z7) {
        this.f13976a = false;
        this.f13978c = cVar;
        this.f13977b = z7;
    }

    @Override // d5.g
    public final d5.g b(String str) {
        c();
        this.f13979d.e(this.f13978c, str, this.f13977b);
        return this;
    }

    @Override // d5.g
    public final d5.g d(boolean z7) {
        c();
        this.f13979d.f(this.f13978c, z7 ? 1 : 0, this.f13977b);
        return this;
    }
}
